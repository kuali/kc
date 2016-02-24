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
package org.kuali.kra.external.dunningcampaign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.module.external.kc.dto.DunningCampaignDTO;
import org.kuali.kfs.module.external.kc.dto.HashMapElement;
import org.kuali.kfs.module.external.kc.service.DunningCampaignService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public abstract class DunningCampaignClientBase implements DunningCampaignClient {
	
	protected static final String SOAP_SERVICE_NAME = "dunningCampaignServiceSOAP";
	protected static final QName SERVICE_NAME = new QName(Constants.FINANCIAL_SYSTEM_SERVICE_NAMESPACE, SOAP_SERVICE_NAME);
    private static final String ERROR_MESSAGE = "Cannot connect to the service. The service may be down, please try again later.";
    private static final Log LOG = LogFactory.getLog(DunningCampaignClientBase.class);
    private ParameterService parameterService;
    
    protected abstract DunningCampaignService getServiceHandle();
	
	@Override
	public DunningCampaign getDunningCampaign(String campaignID) {
		try {
            DunningCampaignService port = getServiceHandle();
            LOG.info("Connecting to financial system...");
            DunningCampaign campaign = getDunningCampaign(port.getDunningCampaign(campaignID));
			return campaign;
		} catch (Exception e) {
			LOG.error(ERROR_MESSAGE + e.getMessage(), e);
			return null;
		}
	}
	
	protected DunningCampaign getDunningCampaign(DunningCampaignDTO dto) {
		if (dto != null) {
			DunningCampaign campaign = new DunningCampaign();
			campaign.setCampaignID(dto.getCampaignID());
			campaign.setCampaignDescription(dto.getCampaignDescription());
			campaign.setActive(dto.isActive());
			return campaign;
		} else {
			return null;
		}
	}
	
	protected List<DunningCampaign> getDunningCampaign(List<DunningCampaignDTO> dtos) {
		if (dtos != null) {
			List<DunningCampaign> result = new ArrayList<DunningCampaign>();
			for (DunningCampaignDTO dto : dtos) {
				result.add(getDunningCampaign(dto));
			}
			return result;
		} else {
			return null;
		}
	}	

	@Override
	public List<DunningCampaign> getMatching(Map<String, String> searchCriteria) {
		try {
			List<HashMapElement> mapElements = new ArrayList<HashMapElement>();
			for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
				HashMapElement element = new HashMapElement();
				element.setKey(entry.getKey());
				element.setValue(entry.getValue());
				mapElements.add(element);
			}
            DunningCampaignService port = getServiceHandle();
            LOG.info("Connecting to financial system...");
            List<DunningCampaign> campaigns = getDunningCampaign(port.getMatching(mapElements));
			return campaigns;
		} catch (Exception e) {
			LOG.error(ERROR_MESSAGE + e.getMessage(), e);
			return null;
		}

	}

	protected ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
