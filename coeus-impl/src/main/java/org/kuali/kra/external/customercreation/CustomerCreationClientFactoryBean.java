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
package org.kuali.kra.external.customercreation;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.external.sponsor.SponsorDTO;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.FactoryBean;

public class CustomerCreationClientFactoryBean implements FactoryBean<CustomerCreationClient> {

	private boolean sharedRice;
	private ParameterService parameterService;
	private KcDtoService<SponsorDTO, Sponsor> sponsorDtoService;
    

	public CustomerCreationClient getObject() throws Exception {
		CustomerCreationClient object = null; 
		if(sharedRice)
		    object = (CustomerCreationClient) (CustomerCreationKSBClientImpl.getInstance());
		else
		    object = (CustomerCreationClient) (CustomerCreationClientImpl.getInstance());

		object.setParameterService(parameterService);
        object.setSponsorDtoService(sponsorDtoService);

		return object;
	}

	public Class getObjectType() {
		return CustomerCreationClient.class;
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

	public KcDtoService<SponsorDTO, Sponsor> getSponsorDtoService() {
		return sponsorDtoService;
	}

	public void setSponsorDtoService(
			KcDtoService<SponsorDTO, Sponsor> sponsorDtoService) {
		this.sponsorDtoService = sponsorDtoService;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}
}