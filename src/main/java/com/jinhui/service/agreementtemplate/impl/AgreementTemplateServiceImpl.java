package com.jinhui.service.agreementtemplate.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhui.mapper.agreementtemplate.AgreementTemplateMapper;
import com.jinhui.model.AgreementTemplate;
import com.jinhui.service.agreementtemplate.AgreementTemplateService;

@Service
public class AgreementTemplateServiceImpl implements AgreementTemplateService {
	Logger logger = LoggerFactory.getLogger(AgreementTemplateServiceImpl.class);
	@Autowired
	private AgreementTemplateMapper agreementTemplateMapper;

	@Override
	public List<AgreementTemplate> queryTemplateList(AgreementTemplate agreementTemplate) {

		return agreementTemplateMapper.queryTemplateList(agreementTemplate);
	}

	@Override
	public void addTemplateInfo(AgreementTemplate agreementTemplate)throws Exception {

		agreementTemplateMapper.addTemplateInfo(agreementTemplate);
	}

	@Override
	public void delIdTemplateInfo(Long fid)throws Exception {

		agreementTemplateMapper.delIdTemplateInfo(fid);
	}

	@Override
	public AgreementTemplate queryTemplateByFid(Long fid) {
		return agreementTemplateMapper.queryTemplateByFid(fid);
	}

}
