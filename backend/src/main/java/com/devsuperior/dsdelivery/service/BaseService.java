package com.devsuperior.dsdelivery.service;

import java.util.List;

public interface BaseService<T> {
	
	public List<T> findAll();
 
	public T insert(T entity);
	
	public T findById (Long id);
}
