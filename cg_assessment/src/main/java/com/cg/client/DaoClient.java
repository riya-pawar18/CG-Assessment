package com.cg.client;
import com.cg.dao.OrderDao;
import com.cg.dao.OrderDaoImpl;
import com.cg.entity.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
public class DaoClient {
    static OrderDao dao = new OrderDaoImpl();
    static Scanner scan = new Scanner(System.in);
        
	public static void main(String[] args) {
		String opt = null;
		do {
			System.out.println("1-ADD, 2--VIEW BY ORDER ID, 3--VIEW BY CUSTOMER NAME");
			int mtype = scan.nextInt();
			processMenu(mtype);
			System.out.println("press y to continue");
			opt=scan.next();
		}while(opt.equalsIgnoreCase("y"));

	}
	
	public static void processMenu(int mtype) {
		switch(mtype) {
		case 1:
			addOrder();
			break;
		case 2:
			viewOrderByOrderID();
			break;
		case 3:
			viewOrdersByCustName();
			break;
		default:
			System.out.println("Invalid option");
		}
	}

	public static void addOrder() 
	{
		System.out.println("Enter order date (enter in d-m-yyyy): ");
		String date= scan.next();
		System.out.println("Enter order amount: ");
		double amt= scan.nextDouble();
		System.out.println("Enter customer id: ");
		int custId= scan.nextInt();
		
		Order order= new Order();
		order.setOrderDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy")));
		order.setOrderAmt(amt);
		if(dao.addOrder(order, custId))
			System.out.println("Order added successfully!");
	}
	
	public static void viewOrderByOrderID() 
	{
		System.out.println("Enter order id: ");
		int orderId= scan.nextInt();
		Order order= dao.getOrder(orderId);
		System.out.println("Order id: "+order.getOrderId());
		System.out.println("Order date: "+order.getOrderDate());
		System.out.println("Order amount: "+order.getOrderAmt());
		System.out.println("Customer name: "+order.getCustomer().getCustomerName());
		
	}
	
	public static void viewOrdersByCustName() 
	{
		System.out.println("Enter customer name: ");
		String custName= scan.next();
		List<Order> list= dao.getOrders(custName);
		for(Order o:list)
		{
			System.out.println("Order id: "+o.getOrderId());
			System.out.println("Order date: "+o.getOrderDate());
			System.out.println("Order amount: "+o.getOrderAmt());
			System.out.println("Customer name: "+o.getCustomer().getCustomerName());
		}
		
	}
 }
