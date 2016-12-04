package com.shop.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeAgreeHisMapper;
import com.shop.base.entity.JokeAgreeHisModel;
import com.shop.base.service.JokeAgreeHisService;

@Service
public class JokeAgreeHisServiceImpl implements JokeAgreeHisService {

	@Autowired
	private JokeAgreeHisMapper jokeAgreeHisMapper;

	@Override
	public int deleteByPrimaryKey(Integer agreeHisId) {
		return jokeAgreeHisMapper.deleteByPrimaryKey(agreeHisId);
	}

	@Override
	public int insert(JokeAgreeHisModel record) {
		return jokeAgreeHisMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeAgreeHisModel record) {
		return jokeAgreeHisMapper.insertSelective(record);
	}

	@Override
	public JokeAgreeHisModel selectByPrimaryKey(Integer agreeHisId) {
		return jokeAgreeHisMapper.selectByPrimaryKey(agreeHisId);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeAgreeHisModel record) {
		return jokeAgreeHisMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JokeAgreeHisModel record) {
		return jokeAgreeHisMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<JokeAgreeHisModel> selectAgreeHisList(JokeAgreeHisModel record) {
		return jokeAgreeHisMapper.selectAgreeHisList(record);
	}

}
