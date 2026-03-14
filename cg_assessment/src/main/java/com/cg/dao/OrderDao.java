package com.cg.dao;

import java.util.List;

import com.cg.entity.Order;

public interface OrderDao 
{
	public boolean addOrder(Order order, int custId);
	public Order getOrder(int orderId);
	public List<Order> getOrders(String custName);

}
