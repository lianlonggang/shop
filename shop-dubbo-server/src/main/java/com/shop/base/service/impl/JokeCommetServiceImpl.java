package com.shop.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeCommetMapper;
import com.shop.base.entity.JokeCommetModel;
import com.shop.base.service.JokeCommetService;

@Service
public class JokeCommetServiceImpl implements JokeCommetService {

	@Autowired
	private JokeCommetMapper jokeCommetMapper;

	@Override
	public int deleteByPrimaryKey(Integer commetId) {
		return jokeCommetMapper.deleteByPrimaryKey(commetId);
	}

	@Override
	public int insert(JokeCommetModel record) {
		return jokeCommetMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeCommetModel record) {
		return jokeCommetMapper.insertSelective(record);
	}

	@Override
	public JokeCommetModel selectByPrimaryKey(Integer commetId) {
		return jokeCommetMapper.selectByPrimaryKey(commetId);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeCommetModel record) {
		return jokeCommetMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(JokeCommetModel record) {
		return jokeCommetMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(JokeCommetModel record) {
		return jokeCommetMapper.updateByPrimaryKey(record);
	}

}
