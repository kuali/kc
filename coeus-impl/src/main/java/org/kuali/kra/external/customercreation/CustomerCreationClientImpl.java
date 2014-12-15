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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.module.external.kc.service.CustomerCreationService;
import org.kuali.kfs.module.external.kc.service.CustomerCreationServiceSOAP;
import org.kuali.kfs.module.external.kc.service.DunningCampaignService;
import org.kuali.kfs.module.external.kc.service.DunningCampaignServiceSOAP;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.config.property.ConfigContext;

import javax.xml.ws.WebServiceClient;
import java.net.MalformedURLException;
import java.net.URL;

public final class CustomerCreationClientImpl extends CustomerCreationClientBase {
    
    public final static URL WSDL_LOCATION;
    
    private static CustomerCreationClientImpl client;
    
    private static final Log LOG = LogFactory.getLog(CustomerCreationClientImpl.class);
    
    private CustomerCreationClientImpl() {
    }

    public static CustomerCreationClientImpl getInstance() {
        if (client == null) {
            client = new CustomerCreationClientImpl();
        }
        return client;
    }
      
    static
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (null == cl) {
            cl = CustomerCreationClientImpl.class.getClassLoader();
        }
        String wsdlPath =  ((WebServiceClient) (DunningCampaignServiceSOAP.class.getAnnotation(WebServiceClient.class))).wsdlLocation();
        WSDL_LOCATION = cl.getResource(wsdlPath); 
    }
    
    @Override
    protected CustomerCreationService getServiceHandle() {
        URL wsdlURL = null;
        
        boolean getFinSystemURLFromWSDL = getParameterService().getParameterValueAsBoolean("KC-AWARD", "Document", Constants.GET_FIN_SYSTEM_URL_FROM_WSDL);
        
        if (getFinSystemURLFromWSDL) {
            wsdlURL = WSDL_LOCATION;
        } else {
            String serviceEndPointUrl = ConfigContext.getCurrentContextConfig().getProperty(Constants.FIN_SYSTEM_INTEGRATION_SERVICE_URL);
            try {
                wsdlURL = new URL(serviceEndPointUrl + SOAP_SERVICE_NAME + "?wsdl");
            } catch (MalformedURLException mue) {
                LOG.error("Could not construct financial system URL from config file: " + mue.getMessage());
            }
        }
        
        CustomerCreationServiceSOAP ss = new CustomerCreationServiceSOAP(wsdlURL, SERVICE_NAME);
        return ss.getCustomerCreationServicePort();
    }

}