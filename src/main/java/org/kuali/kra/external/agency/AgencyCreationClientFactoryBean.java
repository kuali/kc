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
package org.kuali.kra.external.agency;

import org.kuali.kra.external.agency.impl.AgencyCreationKSBClientImpl;
import org.kuali.kra.external.agency.impl.AgencyCreationClientImpl;
import org.springframework.beans.factory.FactoryBean;

public class AgencyCreationClientFactoryBean implements FactoryBean {

	private boolean sharedRice;

	public Object getObject() throws Exception {
	    AgencyCreationClient object = null; 
		if(sharedRice)
		    object = (AgencyCreationClient) (AgencyCreationKSBClientImpl.getInstance());
		else
		    object = (AgencyCreationClient) (AgencyCreationClientImpl.getInstance());
		
		return object;
	}

	public Class getObjectType() {
		return AgencyCreationClient.class;
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
}
