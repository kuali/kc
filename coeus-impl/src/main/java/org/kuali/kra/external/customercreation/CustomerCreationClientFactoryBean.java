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
