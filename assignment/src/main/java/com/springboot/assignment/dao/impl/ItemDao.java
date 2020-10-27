package com.springboot.assignment.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.assignment.dao.IItemDao;
import com.springboot.assignment.entities.ItemDetails;

@Repository
public class ItemDao implements IItemDao{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<ItemDetails> getAllAvailableItems() {
		//System.out.println("USING HIBERNATE IMPL");
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ItemDetails> itemQuery = currentSession.createQuery("from ItemDetails i where i.stockQuantity>0",  ItemDetails.class);
		return itemQuery.getResultList();
	}

	@Override
	public ItemDetails getById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		ItemDetails item = currentSession.get(ItemDetails.class, id);
		return item;
	}

	@Override
	public ItemDetails save(ItemDetails item) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(item);
		return currentSession.get(ItemDetails.class, item.getItemId());
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		ItemDetails item = currentSession.get(ItemDetails.class, id);
		currentSession.delete(item);
	}

}
