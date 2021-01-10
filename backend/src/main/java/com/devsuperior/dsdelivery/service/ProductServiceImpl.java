package com.devsuperior.dsdelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdelivery.model.Product;
import com.devsuperior.dsdelivery.repository.ProductRepository;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

	@Autowired
	private ProductRepository repository;
	
	@Override
	protected ProductRepository getRepository() {
		return repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAllByOrderByName() {
		return getRepository().findAllByOrderByName();
	}
	
	

}
