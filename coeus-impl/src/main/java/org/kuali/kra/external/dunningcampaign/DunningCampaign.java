package org.kuali.kra.external.dunningcampaign;

import org.kuali.rice.krad.bo.BusinessObject;

public class DunningCampaign implements BusinessObject {

	private String campaignID;
	private String campaignDescription;
	private boolean active;
	
	public String getCampaignID() {
		return campaignID;
	}
	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}
	public String getCampaignDescription() {
		return campaignDescription;
	}
	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public void refresh() {	}
}
