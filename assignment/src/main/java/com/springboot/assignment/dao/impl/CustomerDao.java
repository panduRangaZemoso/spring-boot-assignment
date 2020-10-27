package com.springboot.assignment.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.assignment.dao.ICustomerDao;
import com.springboot.assignment.entities.Customer;

@Repository
public class CustomerDao implements ICustomerDao{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Customer> getAll() {
		//System.out.println("USING HIBERNATE IMPL");
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Customer> customerQuery = currentSession.createQuery("from Customer",  Customer.class);
		return customerQuery.getResultList();
	}

	@Override
	public Customer getById(int id) {
		//System.out.println("USING HIBERNATE IMPL");
		Session currentSession = entityManager.unwrap(Session.class);
		Customer customer = currentSession.get(Customer.class, id);
		return customer;
	}
	
	@Override
	public Customer getByEmail(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Customer> customerQuery = currentSession.createQuery("from Customer where "
																	+ "email=:email",  Customer.class);
		customerQuery.setParameter("email", email);
		
		List<Customer> customers = customerQuery.getResultList();
		if(customers == null || customers.isEmpty()) {
			return null;
		}
		return customers.get(0);
	}

	@Override
	public Customer save(Customer customer) {
		Session currentSession = entityManager.unwrap(Session.class);
		//currentSession.saveOrUpdate(customer);
		Customer dbCustomer = (Customer) currentSession.merge(customer);
		
		return currentSession.get(Customer.class, dbCustomer.getCustomerId());
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Customer customer = currentSession.get(Customer.class, id);
		currentSession.delete(customer);
	}

}
