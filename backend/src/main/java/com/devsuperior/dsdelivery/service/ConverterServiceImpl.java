package com.devsuperior.dsdelivery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.model.Product;

@Service
public class ConverterServiceImpl {
	
	
	public List<ProductDTO> converter(List<Product> lista){
		return lista.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
	}

}
