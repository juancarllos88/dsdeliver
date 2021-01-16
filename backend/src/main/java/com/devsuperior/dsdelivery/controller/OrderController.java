package com.devsuperior.dsdelivery.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsdelivery.dto.OrderDTO;
import com.devsuperior.dsdelivery.model.Order;
import com.devsuperior.dsdelivery.service.ConverterServiceImpl;
import com.devsuperior.dsdelivery.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<Order> orders = service.findOrdersWithProducts();
		return ResponseEntity.ok(converter.converterToOrderDTO(orders));
		
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO orderDTO) {
		Order order = converter.converterToOrder(orderDTO);
		Order orderBD = service.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderBD.getId())
				.toUri();
		return ResponseEntity.created(uri).body(converter.converterToOrderDTO(orderBD));
	}
	
	
	@PatchMapping("/{id}/delivered")
	public ResponseEntity<OrderDTO> delivered(@PathVariable Long id) {
		Order order = service.setDelivered(id);
		return ResponseEntity.ok(converter.converterToOrderDTO(order));
	}

}
