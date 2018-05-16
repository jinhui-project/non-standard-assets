package com.jinhui.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.ChnArea;

public interface ChnAreaMapper {

    ChnArea selectByAreaCode(@Param("areaCode") String areaCode);
    
    List<ChnArea> selectAreaListByCityCode(@Param("cityCode") String cityCode);

}