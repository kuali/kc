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


import java.net.URL;

import javax.xml.ws.WebServiceClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.service.AccountCreationService;
import org.kuali.kfs.integration.cg.service.AccountCreationServiceSOAP;
import org.kuali.kra.external.award.AccountCreationClient;



/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an account.
 */

public final class AccountCreationClientImpl extends AccountCreationClientBase {
    private static final Log LOG = LogFactory.getLog(AccountCreationClientImpl.class);
    public final static URL WSDL_LOCATION;
    
    private AccountCreationClientImpl() {
    }

    public static AccountCreationClient getInstance() {
        if (client == null)
            client = new AccountCreationClientImpl();
        return client;
      }

    private static AccountCreationClientImpl client;
      
    static
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if ( null == cl ) cl = AccountCreationClientImpl.class.getClassLoader();
        String wsdlPath =  ((WebServiceClient) (AccountCreationServiceSOAP.class.getAnnotation(WebServiceClient.class))).wsdlLocation();
        WSDL_LOCATION = cl.getResource(wsdlPath); 
    }
    
    @Override
    protected AccountCreationService getServiceHandle() {
        //String serviceEndPointUrl = ConfigContext.getCurrentContextConfig().getProperty(KeyConstants.KFS_ACCOUNT_CREATION_ENDPOINT);
        //wsdlURL = new URL(serviceEndPointUrl + "?wsdl");
        AccountCreationServiceSOAP ss = new AccountCreationServiceSOAP(WSDL_LOCATION, SERVICE_NAME);
        return ss.getAccountCreationServicePort();   
    }

}
