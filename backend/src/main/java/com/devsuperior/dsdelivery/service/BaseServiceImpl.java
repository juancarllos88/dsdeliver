package com.devsuperior.dsdelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdelivery.repository.BaseRepository;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T>{

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getRepository().findAll();
	}
	
	
	@Override
	@Transactional
	public T insert(T entity) {
		return getRepository().save(entity);
	}
	
	@Override
	@Transactional
	public T findById(Long id) {
		return getRepository().getOne(id);
	}
	
	
	protected abstract BaseRepository<T, Long> getRepository();

}
