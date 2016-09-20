package com.mfg.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mfg.dao.BaseDao;
import com.mfg.page.Page;

@Service("mongoDBService")
public class MongoDBService implements BaseService{
	
	@Autowired
	private BaseDao mongoDBBaseDao;
	

	public <T> T getById(Class<T> entityClass, String id) {
		return mongoDBBaseDao.findById(entityClass, id);
	}

	public <T> List<T> getAll(Class<T> entityClass) {
		return mongoDBBaseDao.findAll(entityClass);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Object obj) {
		mongoDBBaseDao.remove(obj);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(Object obj) {
		mongoDBBaseDao.add(obj);
	}

	public <T> List<T> get(Class<T> entityClass, int skip, int limit) {
		return mongoDBBaseDao.find(entityClass, skip, limit);
	}

	public <T> List<T> getBypage(Class<T> entityClass, Query query, Page page) {
		return mongoDBBaseDao.findByQuery(entityClass, query, page);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Object obj) {
		mongoDBBaseDao.saveOrUpdate(obj);
	}

}
