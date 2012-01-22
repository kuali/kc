/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.external.award.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.service.AccountCreationService;
import org.kuali.kra.external.award.AccountCreationClient;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;



/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an account.
 */

public final class AccountCreationKSBClientImpl extends AccountCreationClientBase {
    private static final Log LOG = LogFactory.getLog(AccountCreationKSBClientImpl.class);
    
    private AccountCreationKSBClientImpl() {  }
    
    public static AccountCreationClient getInstance() {
      if (ksbClient == null)
          ksbClient = new AccountCreationKSBClientImpl();
      return ksbClient;
    }

    private static AccountCreationKSBClientImpl ksbClient;


    @Override
    protected AccountCreationService getServiceHandle() {
        AccountCreationService accountCreationService = (AccountCreationService) GlobalResourceLoader.getService(SERVICE_NAME);
        return accountCreationService;
    }
}
