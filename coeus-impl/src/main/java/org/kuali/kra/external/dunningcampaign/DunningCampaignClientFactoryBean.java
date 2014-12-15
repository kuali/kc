/*
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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