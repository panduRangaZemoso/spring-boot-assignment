package com.springboot.assignment.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.assignment.dao.ICustomerCartDao;
import com.springboot.assignment.entities.CustomerCartDetails;

@Repository
public class CustomerCartDao implements ICustomerCartDao {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public CustomerCartDetails getByCustomerIdAndItemId(int customerId, int itemId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CustomerCartDetails> cartQuery = currentSession.createQuery("from CustomerCartDetails cd where cd.customer.customerId=:customerId "
																			+ " AND cd.item.itemId=:itemId",  CustomerCartDetails.class);
		cartQuery.setParameter("customerId", customerId);
		cartQuery.setParameter("itemId", itemId);
		
		List<CustomerCartDetails> customerCartDetails = cartQuery.getResultList();
		
		if(customerCartDetails==null || customerCartDetails.isEmpty()) {
			return null;
		}
		
		return customerCartDetails.get(0);
	}

	@Override
	public List<CustomerCartDetails> getByCustomerId(int customerId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CustomerCartDetails> cartQuery = currentSession.createQuery("from CustomerCartDetails cd where cd.customer.customerId=:customerId",  CustomerCartDetails.class);
		cartQuery.setParameter("customerId", customerId);
		return cartQuery.getResultList();
	}

	@Override
	public CustomerCartDetails save(CustomerCartDetails customerCartDetails) {
		Session currentSession = entityManager.unwrap(Session.class);
		//currentSession.saveOrUpdate(customerCartDetails);
		currentSession.merge(customerCartDetails);
		return customerCartDetails;
	}

	@Override
	public void deleteByCustomerIdAndItemId(int customerId, int itemId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<?> cartQuery = currentSession.createQuery("delete from CustomerCartDetails cd where cd.customer.customerId=:customerId "
																			+ " AND cd.item.itemId=:itemId");
		cartQuery.setParameter("customerId", customerId);
		cartQuery.setParameter("itemId", itemId);
		
		cartQuery.executeUpdate();
	}

	@Override
	public void deleteByCustomerId(int customerId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<?> cartQuery = currentSession.createQuery("delete from CustomerCartDetails cd where cd.customer.customerId=:customerId");
		cartQuery.setParameter("customerId", customerId);
		
		cartQuery.executeUpdate();
	}

}
