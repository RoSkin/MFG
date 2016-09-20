package com.mfg.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mfg.entity.Good;
import com.mfg.page.Page;

@Repository(value = "mongoDBBaseDao")
@Transactional
public class MongoDBBaseDao implements BaseDao {
	@Autowired
	@Qualifier("mongoTemplate")
	protected MongoTemplate mongoTemplate;


	public <T> T findById(Class<T> entityClass, String id) {
		return this.mongoTemplate.findById(id, entityClass);
	}

	public <T> List<T> findAll(Class<T> entityClass) {
		return this.mongoTemplate.findAll(entityClass);
	}

	public void remove(Object obj) {
		this.mongoTemplate.remove(obj);
	}

	public void add(Object obj) {
		this.mongoTemplate.insert(obj);

	}

	public void saveOrUpdate(Object obj) {
		this.mongoTemplate.save(obj);

	}
	
	public <T> List<T> findByQuery(Class<T> entityClass, Query query, Page page) {
		Long count = this.count(entityClass, query);
		page.setCount(count);
		int pageNumber = page.getCurrent();
		int pageSize = page.getPageCount();
		query.skip((pageNumber - 1) * pageSize).limit(pageSize);
		return this.mongoTemplate.find(query, entityClass);
	}
	
	public <T> Long count(Class<T> entityClass, Query query) {
		return this.mongoTemplate.count(query, entityClass);
	}

	public <T> List<T> find(Class<T> entityClass,int skip, int limit) {
		Query query = getQuery();  
        query.skip(skip);  
        query.limit(limit);  
        return mongoTemplate.find(query, entityClass);
	}
	private Query getQuery() {  
        Good good = new Good();
        Query query = new Query();  
        if (good.getId() != null) {  
            Criteria criteria = Criteria.where("id").is(good.getId());  
            query.addCriteria(criteria);  
        }  
        if (good.getAge() > 0) {  
            Criteria criteria = Criteria.where("age").gt(good.getAge());  
            query.addCriteria(criteria);  
        }  
        if (good.getName() != null) {  
            Criteria criteria = Criteria.where("name").regex("^" + good.getName());  
            query.addCriteria(criteria);  
        }  
        return query;  
    }  
	
}
