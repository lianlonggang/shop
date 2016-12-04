package com.shop.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeImgLastIdMapper;
import com.shop.base.entity.JokeImgLastIdModel;
import com.shop.base.service.JokeImgLastIdService;

@Service
public class JokeImgLastIdServiceImpl implements JokeImgLastIdService {

	@Autowired  
	private JokeImgLastIdMapper jokeImgLastIdMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return jokeImgLastIdMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JokeImgLastIdModel record) {
		return jokeImgLastIdMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeImgLastIdModel record) {
		return jokeImgLastIdMapper.insertSelective(record);
	}

	@Override
	public JokeImgLastIdModel selectByPrimaryKey(Integer id) {
		return jokeImgLastIdMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeImgLastIdModel record) {
		return jokeImgLastIdMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JokeImgLastIdModel record) {
		return jokeImgLastIdMapper.updateByPrimaryKey(record);
	}


}
