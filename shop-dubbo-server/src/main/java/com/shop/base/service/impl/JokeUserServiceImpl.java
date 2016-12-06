package com.shop.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeUserMapper;
import com.shop.base.entity.JokeUserModel;
import com.shop.base.service.JokeUserService;

@Service
public class JokeUserServiceImpl implements JokeUserService {
	@Autowired
	private JokeUserMapper jokeUserMapper;

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return jokeUserMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(JokeUserModel record) {
		return jokeUserMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeUserModel record) {
		return jokeUserMapper.insertSelective(record);
	}

	@Override
	public JokeUserModel selectByPrimaryKey(Integer userId) {
		return jokeUserMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeUserModel record) {
		return jokeUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JokeUserModel record) {
		return jokeUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public JokeUserModel selectByUserCode(String userCode) {
		return jokeUserMapper.selectByUserCode(userCode);
	}

}
