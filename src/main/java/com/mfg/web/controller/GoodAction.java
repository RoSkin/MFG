package com.mfg.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfg.entity.Good;
import com.mfg.service.BaseService;

@Controller
public class GoodAction {
	@Autowired
	private BaseService mongoDBService;
	
	@RequestMapping(value="/api/good",method=RequestMethod.GET)
	@ResponseBody
	public List<Good> getGoods(@RequestParam("page") int page,@RequestParam("size") int size){
		return mongoDBService.get(Good.class, page, size);
	}
	
	@RequestMapping(value="/api/good",method=RequestMethod.POST)
	public void createGood(@RequestBody Good good){
		mongoDBService.create(good);
	}
	
	@RequestMapping(value="/api/good",method=RequestMethod.PUT)
	public void updateGoods(@RequestBody Good good){
		mongoDBService.update(good);
	}
	
	@RequestMapping(value="/api/good/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteGood(@PathVariable String id){
		Good good = mongoDBService.getById(Good.class, id);
		mongoDBService.delete(good);
    }
	
	@RequestMapping(value="/api/good/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Good getGood(@PathVariable String id){
       return mongoDBService.getById(Good.class, id);
    }
}
