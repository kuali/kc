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
package org.kuali.kra.external.award.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.module.external.kc.service.AccountCreationService;
import org.kuali.kfs.module.external.kc.service.AccountCreationServiceSOAP;
import org.kuali.kra.external.award.AccountCreationClient;
import org.kuali.kra.infrastructure.Constants;

import javax.xml.ws.WebServiceClient;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an account.
 */

public final class AccountCreationClientImpl extends AccountCreationClientBase {
    
    public final static URL WSDL_LOCATION;
    
    private static AccountCreationClientImpl client;
    
    private static final Log LOG = LogFactory.getLog(AccountCreationClientImpl.class);
    
    private AccountCreationClientImpl() {
    }

    public static AccountCreationClient getInstance() {
        if (client == null) {
            client = new AccountCreationClientImpl();
        }
        return client;
    }
      
    static
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (null == cl) {
            cl = AccountCreationClientImpl.class.getClassLoader();
        }
        String wsdlPath =  ((WebServiceClient) (AccountCreationServiceSOAP.class.getAnnotation(WebServiceClient.class))).wsdlLocation();
        WSDL_LOCATION = cl.getResource(wsdlPath); 
    }
    
    @Override
    protected AccountCreationService getServiceHandle() {
        URL wsdlURL = null;
        
        boolean getFinSystemURLFromWSDL = getParameterService().getParameterValueAsBoolean("KC-AWARD", "Document", Constants.GET_FIN_SYSTEM_URL_FROM_WSDL);
        
        if (getFinSystemURLFromWSDL) {
            wsdlURL = WSDL_LOCATION;
        } else {
            String serviceEndPointUrl = getConfigurationService().getPropertyValueAsString(Constants.FIN_SYSTEM_INTEGRATION_SERVICE_URL);
            try {
                wsdlURL = new URL(serviceEndPointUrl + SOAP_SERVICE_NAME + "?wsdl");
            } catch (MalformedURLException mue) {
                LOG.error("Could not construct financial system URL from config file: " + mue.getMessage());
            }
        }
        
        AccountCreationServiceSOAP ss = new AccountCreationServiceSOAP(wsdlURL, SERVICE_NAME);
        return ss.getAccountCreationServicePort();
    }

}
