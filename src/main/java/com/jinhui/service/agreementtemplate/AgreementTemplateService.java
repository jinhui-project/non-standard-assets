package com.jinhui.service.agreementtemplate;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.AgreementTemplate;

public interface AgreementTemplateService {
	
	
	public List<AgreementTemplate> queryTemplateList(AgreementTemplate agreementTemplate);
	
	
	public void addTemplateInfo(AgreementTemplate agreementTemplate)throws Exception;
	
	
	public void delIdTemplateInfo( Long fid)throws Exception;
	
	public AgreementTemplate queryTemplateByFid( Long fid);

}
