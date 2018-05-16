package com.jinhui.mapper.abs;

import com.jinhui.model.abs.PeWhiteList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeWhiteListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeWhiteList record);

    int insertSelective(PeWhiteList record);

    PeWhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeWhiteList record);

    int updateByPrimaryKey(PeWhiteList record);

    /**
     * 根据原始权益人查询白名单列表
     * @param originalHolder
     * @return
     */
    List<PeWhiteList> selectByOriginalHolder(String originalHolder);

    /**
     * 根据应收债务人模糊查询白名单列表
     * @return
     */
    List<PeWhiteList> selectByName(@Param("originalHolder") String originalHolder, @Param("debtor") String debtor);


    /**
     * 條件查詢
     * @param debtor
     * @param periodStart
     * @param periodStop
     * @return
     */
    List<PeWhiteList> selectByQueryParam(@Param("originalHolder") String originalHolder, @Param("debtor") String debtor, @Param("periodStart") String periodStart, @Param("periodStop") String periodStop);


    /**
     *根据应收债务人和原始权益人的名字更新合同信息或者账期
     * @param record
     * @return
     */
    int updateByDebtor(PeWhiteList record);
}