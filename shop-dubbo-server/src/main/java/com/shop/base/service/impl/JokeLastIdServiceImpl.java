package com.shop.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeLastIdMapper;
import com.shop.base.entity.JokeLastIdModel;
import com.shop.base.service.JokeLastIdService;

@Service
public class JokeLastIdServiceImpl implements JokeLastIdService {

	@Autowired  
	private JokeLastIdMapper jokeLastIdMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return jokeLastIdMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JokeLastIdModel record) {
		return jokeLastIdMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeLastIdModel record) {
		return jokeLastIdMapper.insertSelective(record);
	}

	@Override
	public JokeLastIdModel selectByPrimaryKey(Integer id) {
		return jokeLastIdMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeLastIdModel record) {
		return jokeLastIdMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JokeLastIdModel record) {
		return jokeLastIdMapper.updateByPrimaryKey(record);
	}

	@Override
	public JokeLastIdModel findByModel(JokeLastIdModel record) {
		return jokeLastIdMapper.findByModel(record);
	}

	@Override
	public int addLastId(String jokeType) {
		return jokeLastIdMapper.addLastId(jokeType);
	}


}
