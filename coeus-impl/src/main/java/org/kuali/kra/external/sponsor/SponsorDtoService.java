/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
