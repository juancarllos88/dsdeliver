package com.devsuperior.dsdelivery.service;

import java.util.List;

import com.devsuperior.dsdelivery.model.Product;

public interface ProductService extends BaseService<Product>{
	
	
	public List<Product> findAllByOrderByName();

}
