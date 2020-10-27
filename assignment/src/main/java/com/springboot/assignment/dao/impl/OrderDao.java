package com.springboot.assignment.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.assignment.dao.IOrderDao;
import com.springboot.assignment.entities.OrderDetails;

@Repository
public class OrderDao implements IOrderDao {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public OrderDetails getById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		OrderDetails order = currentSession.get(OrderDetails.class, id);
		return order;
	}

	@Override
	public List<OrderDetails> getByCustomerId(int customerId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<OrderDetails> orderQuery = currentSession.createQuery("from OrderDetails ord Where ord.customer.customerId=:customerId",  OrderDetails.class);
		orderQuery.setParameter("customerId", customerId);
		return orderQuery.getResultList();
	}

	@Override
	public OrderDetails save(OrderDetails orderDetails) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(orderDetails);
		return currentSession.get(OrderDetails.class, orderDetails.getOrderId());
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		OrderDetails order = currentSession.get(OrderDetails.class, id);
		currentSession.delete(order);
	}

}
