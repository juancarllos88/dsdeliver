package com.devsuperior.dsdelivery.repository;

import java.util.List;

import com.devsuperior.dsdelivery.model.Product;


public interface ProductRepository extends BaseRepository<Product, Long>{
	
	List<Product> findAllByOrderByName();

}
