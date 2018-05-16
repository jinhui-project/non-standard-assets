package com.jinhui.mapper.sales;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Sales;

public interface SalesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sales record);

    int insertSelective(Sales record);

    Sales selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sales record);

    int updateByPrimaryKey(Sales record);
    
    List<Sales> getSalesListByProductId(@Param("productId") Integer productId,@Param("belongGid") String belongGid);
}