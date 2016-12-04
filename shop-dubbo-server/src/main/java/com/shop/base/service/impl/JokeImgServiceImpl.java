package com.shop.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeImgMapper;
import com.shop.base.entity.JokeImgModel;
import com.shop.base.service.JokeImgService;

@Service
public class JokeImgServiceImpl implements JokeImgService {

	@Autowired  
	private JokeImgMapper tblSjsJokeImgMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tblSjsJokeImgMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JokeImgModel record) {
		return tblSjsJokeImgMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeImgModel record) {
		return tblSjsJokeImgMapper.insertSelective(record);
	}

	@Override
	public JokeImgModel selectByPrimaryKey(Integer id) {
		return tblSjsJokeImgMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeImgModel record) {
		return tblSjsJokeImgMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(JokeImgModel record) {
		return tblSjsJokeImgMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public List<JokeImgModel> queryJokeImgNewList(JokeImgModel tblSjsJokeImg) {
		return tblSjsJokeImgMapper.queryJokeImgNewList(tblSjsJokeImg);
	}

}
