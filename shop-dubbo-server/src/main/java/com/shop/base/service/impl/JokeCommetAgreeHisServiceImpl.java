package com.shop.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeCommetAgreeHisMapper;
import com.shop.base.entity.JokeCommetAgreeHisModel;
import com.shop.base.service.JokeCommetAgreeHisService;

@Service
public class JokeCommetAgreeHisServiceImpl implements JokeCommetAgreeHisService {

	@Autowired
	private JokeCommetAgreeHisMapper jokeCommetAgreeHisMapper;

	@Override
	public int deleteByPrimaryKey(Integer agreeHisId) {
		return jokeCommetAgreeHisMapper.deleteByPrimaryKey(agreeHisId);
	}

	@Override
	public int insert(JokeCommetAgreeHisModel record) {
		return jokeCommetAgreeHisMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeCommetAgreeHisModel record) {
		return jokeCommetAgreeHisMapper.insertSelective(record);
	}

	@Override
	public JokeCommetAgreeHisModel selectByPrimaryKey(Integer agreeHisId) {
		return jokeCommetAgreeHisMapper.selectByPrimaryKey(agreeHisId);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeCommetAgreeHisModel record) {
		return jokeCommetAgreeHisMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JokeCommetAgreeHisModel record) {
		return jokeCommetAgreeHisMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<JokeCommetAgreeHisModel> selectCommetAgreeHisList(JokeCommetAgreeHisModel record) {
		return jokeCommetAgreeHisMapper.selectCommetAgreeHisList(record);
	}

}
