package com.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.Orders;
import com.business.entities.User;
import com.business.repositories.OrderRepository;

@Service
public class OrderServicesImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public List<Orders> getAllOrders() {
		List<Orders> list=orderRepo.findAll();
		return list;
	}

	@Override
	public String saveOrder(Orders order) {
		orderRepo.save(order);
		return "Order is saved...";
	}

	@Override
	public String updateOrder(int id, Orders order) {
		Optional<Orders> opt=orderRepo.findById(id);
		if(opt.isEmpty()) {
			return "Order not exist with thid id...";
		}
		else {
			orderRepo.save(order);
			return "Order is updated...";
		}
	}

	@Override
	public String deleteOrder(int id) {
		Optional<Orders> opt=orderRepo.findById(id);
		if(opt.isEmpty()) {
			return "Order not exist with thid id...";
		}
		else {
			orderRepo.deleteById(id);
			return "Order is deleted...";
		}
	}

	@Override
	public List<Orders> getOrdersForUser(User user) {
		return orderRepo.findOrdersByUser(user);
	}

}
