/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.external.customercreation;

import org.kuali.kfs.module.external.kc.service.CustomerCreationService;
import org.kuali.kfs.module.external.kc.service.DunningCampaignService;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;

public final class CustomerCreationKSBClientImpl extends CustomerCreationClientBase {
    
    private CustomerCreationKSBClientImpl() {  }
    
    public static CustomerCreationClient getInstance() {
      if (ksbClient == null)
          ksbClient = new CustomerCreationKSBClientImpl();
      return ksbClient;
    }

    private static CustomerCreationKSBClientImpl ksbClient;

    @Override
    protected CustomerCreationService getServiceHandle() {
    	CustomerCreationService service = (CustomerCreationService) GlobalResourceLoader.getService(SERVICE_NAME);
        return service;
    }
}
