package com.jinhui.service.agreementpowertemplate;

import com.jinhui.model.AgreementPowerTemplate;

public interface AgreementPowerTemplateService {
	
	public void addAgreementPower(AgreementPowerTemplate agreementPowerTemplate) throws Exception;

	public void modNotNullAgreementPower(AgreementPowerTemplate agreementPowerTemplate)throws Exception;

	public AgreementPowerTemplate findAgreementPower( AgreementPowerTemplate agreementPowerTemplate);

}
