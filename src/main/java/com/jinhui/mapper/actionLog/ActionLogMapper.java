package com.jinhui.mapper.actionLog;

import com.jinhui.model.ActionLog;

/**
 * 操作日志mapper
 * @time 2017-4-5 上午9:57:12  
 * @author wsc
 * @desc
 *
 */
public interface ActionLogMapper {

    int insertSelective(ActionLog record);

}