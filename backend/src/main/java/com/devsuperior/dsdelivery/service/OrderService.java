package com.devsuperior.dsdelivery.service;

import java.util.List;

import com.devsuperior.dsdelivery.model.Order;

public interface OrderService extends BaseService<Order>{
	
	
	List<Order> findOrdersWithProducts();

	Order setDelivered(Long id);
	
	

}
