package com.jinhui.service.agreementpowertemplate.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhui.mapper.agreementpowertemplate.AgreementPowerTemplateMapper;
import com.jinhui.model.AgreementPowerTemplate;
import com.jinhui.service.agreementpowertemplate.AgreementPowerTemplateService;
@Service
public class AgreementPowerTemplateServiceImpl implements AgreementPowerTemplateService {
	@Autowired
	private AgreementPowerTemplateMapper agreementPowerTemplateMapper;

	@Override
	public void addAgreementPower(AgreementPowerTemplate agreementPowerTemplate)throws Exception {
		agreementPowerTemplateMapper.addAgreementPower(agreementPowerTemplate);
	}

	@Override
	public void modNotNullAgreementPower(AgreementPowerTemplate agreementPowerTemplate) throws Exception {
		agreementPowerTemplateMapper.modNotNullAgreementPower(agreementPowerTemplate);
	}

	@Override
	public AgreementPowerTemplate findAgreementPower(AgreementPowerTemplate agreementPowerTemplate) {
		return agreementPowerTemplateMapper.queryAgreementPower(agreementPowerTemplate);
	}




}
