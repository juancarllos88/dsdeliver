package com.devsuperior.dsdelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.model.Product;
import com.devsuperior.dsdelivery.service.ConverterServiceImpl;
import com.devsuperior.dsdelivery.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> listar() {
		List<Product> products = service.findAllByOrderByName();
		return ResponseEntity.ok(converter.converter(products));
		
	}

}
