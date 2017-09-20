package com.freeman.j2ee.service;

import java.util.List;

import com.freeman.model.IPrdCategory;


public class CCI21ServiceImpl extends BaseServiceImpl implements CCI21Service {



	public <E> List<E> getCategory() {
		String hql = "select a from " + IPrdCategory.class.getName()+ " a";
		return entityManager.findByHql(hql);
	}
	
}
