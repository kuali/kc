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

import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.FactoryBean;

public class DunningCampaignClientFactoryBean implements FactoryBean<DunningCampaignClient> {

	private boolean sharedRice;
    private ParameterService parameterService;

	public DunningCampaignClient getObject() throws Exception {
	    DunningCampaignClient object = null; 
		if(sharedRice)
		    object = (DunningCampaignClient) (DunningCampaignKSBClientImpl.getInstance());
		else
		    object = (DunningCampaignClient) (DunningCampaignClientImpl.getInstance());

        object.setParameterService(parameterService);

		return object;
	}

	public Class getObjectType() {
		return DunningCampaignClient.class;
	}

	public boolean isSingleton() {
		return true;
	}

    public boolean isSharedRice() {
        return sharedRice;
    }

    public void setSharedRice(boolean sharedRice) {
        this.sharedRice = sharedRice;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
