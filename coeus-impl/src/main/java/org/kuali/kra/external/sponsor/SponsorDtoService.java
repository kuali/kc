package org.kuali.kra.external.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.kra.award.home.AwardConstants;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.external.service.KcDtoServiceBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class SponsorDtoService extends KcDtoServiceBase<SponsorDTO, Sponsor> {

	private ParameterService parameterService;
	private KcDtoService<RolodexDTO, Rolodex> rolodexDtoService;
	
	@Override
	public SponsorDTO buildDto(Sponsor sponsor) {
		SponsorDTO result = new SponsorDTO();
		result.setSponsorCode(sponsor.getSponsorCode());
		result.setAcronym(sponsor.getAcronym());
		result.setSponsorName(sponsor.getSponsorName());
		result.setSponsorTypeCode(sponsor.getSponsorTypeCode());
    	if (sponsor.getSponsorType() != null) {
    		result.setSponsorTypeDescription(sponsor.getSponsorType().getDescription());
    	}
    	result.setCustomerTypeCode(sponsor.getCustomerTypeCode());
    	result.setCustomerNumber(sponsor.getCustomerNumber());
    	result.setCageNumber(sponsor.getCageNumber());
    	result.setDodacNumber(sponsor.getDodacNumber());
    	result.setDunAndBradstreetNumber(sponsor.getDunAndBradstreetNumber());
    	result.setDunsPlusFourNumber(sponsor.getDunsPlusFourNumber());
    	result.setDunningCampaignId(sponsor.getDunningCampaignId());
    	result.setState(sponsor.getState());
    	result.setStateAgency(StringUtils.equals(sponsor.getSponsorTypeCode(), getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, AwardConstants.STATE_SPONSOR_TYPE_PARAM)));
    	result.setActive(sponsor.isActive());
    	
    	if (sponsor.getRolodex() != null) {
    		result.setContactInformation(rolodexDtoService.buildDto(sponsor.getRolodex()));
    	}
    	return result;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	public KcDtoService<RolodexDTO, Rolodex> getRolodexDtoService() {
		return rolodexDtoService;
	}

	public void setRolodexDtoService(
			KcDtoService<RolodexDTO, Rolodex> rolodexDtoService) {
		this.rolodexDtoService = rolodexDtoService;
	}

}
