package com.cg.dao;

import java.util.List;

import com.cg.entity.Customer;
import com.cg.entity.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class OrderDaoImpl implements OrderDao
{
	EntityManagerFactory emf= Persistence.createEntityManagerFactory("abesdb");
	@Override
	public boolean addOrder(Order order, int custId) 
	{
		EntityManager em= emf.createEntityManager();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		Customer customer= em.find(Customer.class, custId);
		if(customer==null) {
			System.out.println("Customer not found with Id: "+custId);
			return false;
		}
		order.setCustomer(customer);
		em.persist(order);
		tx.commit();
		em.close();
		return true;
	}

	@Override
	public Order getOrder(int orderId) {
		EntityManager em= emf.createEntityManager();
		Order order= em.find(Order.class,orderId);
		return order;
	}

	@Override
	public List<Order> getOrders(String custName) {
		EntityManager em= emf.createEntityManager();
		String jpql= "from Order o inner join fetch o.customer c where c.customerName= :name";
		TypedQuery<Order> qry= em.createQuery(jpql,Order.class);
		qry.setParameter("name", custName);
		List<Order> list= qry.getResultList();
		
		return list;
	}

}
