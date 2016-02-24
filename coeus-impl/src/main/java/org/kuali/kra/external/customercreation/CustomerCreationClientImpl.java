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
