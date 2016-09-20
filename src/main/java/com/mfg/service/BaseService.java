package com.mfg.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.mfg.page.Page;

public interface BaseService {
	<T> T getById(Class<T> entityClass, String id);

	<T> List<T> getAll(Class<T> entityClass);

	void delete(Object obj);

	void create(Object obj);
	
	void update(Object obj);
	
	<T> List<T> get(Class<T> entityClass,int skip, int limit);
	<T> List<T> getBypage(Class<T> entityClass, Query query, Page page);
}
