package com.mfg.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.mfg.page.Page;

public interface BaseDao {
	<T> T findById(Class<T> entityClass, String id);

	<T> List<T> findAll(Class<T> entityClass);

	void remove(Object obj);

	void add(Object obj);

	void saveOrUpdate(Object obj);
	
	<T> List<T> find(Class<T> entityClass,int skip, int limit);
	<T> List<T> findByQuery(Class<T> entityClass, Query query, Page page);
}
