package com.jinhui.mapper.abs;


import com.jinhui.model.abs.ChangeHistoryVo;
import com.jinhui.model.abs.PeFundChangeHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeFundChangeHistoryMapper {

    int insertSelective(PeFundChangeHistory record);

    PeFundChangeHistory selectNewestRecord(@Param("aid") Integer aid);

}