package com.devsuperior.dsdelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsdelivery.model.Order;
import com.devsuperior.dsdelivery.model.OrderStatus;
import com.devsuperior.dsdelivery.repository.OrderRepository;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService{

	@Autowired
	private OrderRepository repository;
	
	@Override
	protected OrderRepository getRepository() {
		return repository;
	}

	@Override
	public List<Order> findOrdersWithProducts() {
		return getRepository().findOrdersWithProducts();
	}
	
	@Override
	public Order setDelivered(Long id) {
		Order order = findById(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = insert(order);
		return order;
	}

	
	
	

}
