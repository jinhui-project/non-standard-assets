package com.jinhui.mapper.orgPreferCollect;

import com.jinhui.model.OrgPreferCollect;

public interface OrgPreferCollectMapper {

    int insertSelective(OrgPreferCollect record);

    OrgPreferCollect selectByOrgId(Integer organizationId);
    
    int updateByPrimaryKeySelective(OrgPreferCollect record);

}