package com.devsuperior.dsdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsdelivery.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
