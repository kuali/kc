/*
 * Copyright 2007-2008 The Kuali Foundation
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
package org.kuali.kra.external.award;

import org.kuali.kra.external.award.impl.AccountCreationClientImpl;
import org.kuali.kra.external.award.impl.AccountCreationKSBClientImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.FactoryBean;

public class AccountCreationClientFactoryBean implements FactoryBean {

	private boolean sharedRice;
	private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;

	public Object getObject() throws Exception {
	    AccountCreationClient object = null; 
		if(sharedRice)
		    object = (AccountCreationClient) (AccountCreationKSBClientImpl.getInstance());
		else
		    object = (AccountCreationClient) (AccountCreationClientImpl.getInstance());
		
		object.setDocumentService(documentService);
		object.setBusinessObjectService(businessObjectService);
        object.setParameterService(parameterService);

		return object;
	}

	public Class getObjectType() {
		return AccountCreationClient.class;
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

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}