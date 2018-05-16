package com.jinhui.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.ChnProvince;

public interface ChnProvinceMapper {

    ChnProvince selectByProvinceCode(@Param("provinceCode") String provinceCode);
    
    List<ChnProvince> selectProvinceList();

}