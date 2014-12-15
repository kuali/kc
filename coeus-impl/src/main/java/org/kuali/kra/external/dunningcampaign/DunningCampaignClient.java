package org.kuali.kra.external.dunningcampaign;

import java.util.List;
import java.util.Map;

import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public interface DunningCampaignClient {

    public DunningCampaign getDunningCampaign(String campaignID);

	public List<DunningCampaign> getMatching(Map<String, String> searchCriteria);	
	
	public void setParameterService(ParameterService parameterService);
}
