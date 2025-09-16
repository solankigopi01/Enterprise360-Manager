package com.business.services;

import java.util.List;

import com.business.entities.Orders;
import com.business.entities.User;

public interface IOrderService {
	public List<Orders> getAllOrders();
	public String saveOrder(Orders order);
	public String updateOrder(int id,Orders order);
	public String deleteOrder(int id);
	public List<Orders> getOrdersForUser(User user);
}
