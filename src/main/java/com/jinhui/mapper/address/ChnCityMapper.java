package com.jinhui.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.ChnCity;

public interface ChnCityMapper {

    ChnCity selectByCityCode(@Param("cityCode") String cityCode);
    
    List<ChnCity> selectCityListByProvinceCode(@Param("provinceCode") String provinceCode);

}