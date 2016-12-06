package com.shop.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.BaseCodeMapper;
import com.shop.base.entity.BaseCodeModel;
import com.shop.base.service.BaseCodeService;

import redis.clients.jedis.tool.JedisTool;

@Service
public class BaseCodeServiceImpl implements BaseCodeService {
	@Autowired
	private BaseCodeMapper baseCodeMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return baseCodeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BaseCodeModel record) {
		return baseCodeMapper.insert(record);
	}

	@Override
	public int insertSelective(BaseCodeModel record) {
		return baseCodeMapper.insertSelective(record);
	}

	@Override
	public BaseCodeModel selectByPrimaryKey(Integer id) {
		return baseCodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseCodeModel record) {
		return baseCodeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BaseCodeModel record) {
		return baseCodeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BaseCodeModel> findList(BaseCodeModel record) {
		return baseCodeMapper.findList(record);
	}

	@Override
	public String transCode(String type, String code) {
		return baseCodeMapper.transCode(type, code);
	}

	@Override
	public String getCodeByType(String type) {
		String code = "";
		try {
			@SuppressWarnings("unchecked")
			Map<String,Object> baseCodeMap = (Map<String,Object>) JedisTool.getMap("baseCodeMap");
			if(baseCodeMap==null || baseCodeMap.size()==0){
				Map<String,Object> map = new HashMap<String,Object>();
				List<BaseCodeModel> baseCodeList = baseCodeMapper.findList(new BaseCodeModel());
				for(BaseCodeModel baseCode:baseCodeList){
					map.put(baseCode.getType(), baseCode.getCode());
				}
				baseCodeMap = map;
				JedisTool.setMap("baseCodeMap", baseCodeMap);
			}
			code = (String) baseCodeMap.get(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

}
