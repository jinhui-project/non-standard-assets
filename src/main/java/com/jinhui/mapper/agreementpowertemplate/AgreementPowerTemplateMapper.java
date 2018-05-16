package com.jinhui.mapper.agreementpowertemplate;

import com.jinhui.model.AgreementPowerTemplate;

public interface AgreementPowerTemplateMapper {
	
	
	void addAgreementPower(AgreementPowerTemplate agreementPowerTemplate);
	
	
	void modNotNullAgreementPower( AgreementPowerTemplate agreementPowerTemplate);
	
	AgreementPowerTemplate queryAgreementPower(AgreementPowerTemplate agreementPowerTemplate);

}
