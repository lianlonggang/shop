package com.shop.base.service;

import java.util.List;

import com.shop.base.entity.BaseCodeModel;

public interface BaseCodeService {
	int deleteByPrimaryKey(Integer id);

    int insert(BaseCodeModel record);

    int insertSelective(BaseCodeModel record);

    BaseCodeModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCodeModel record);

    int updateByPrimaryKey(BaseCodeModel record);
    
    List<BaseCodeModel> findList(BaseCodeModel record);
    
    String transCode(String type,String code);
    
    String getCodeByType(String type);
}
