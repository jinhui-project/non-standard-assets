package com.jinhui.mapper.agreementtemplate;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.AgreementTemplate;

public interface AgreementTemplateMapper {
	
	
	
	public List<AgreementTemplate> queryTemplateList(AgreementTemplate agreementTemplate);
	
	
	public void addTemplateInfo(AgreementTemplate agreementTemplate);
	
	
	public void delIdTemplateInfo(@Param("fid") Long fid);
	
	public AgreementTemplate queryTemplateByFid(@Param("fid") Long fid);

}
