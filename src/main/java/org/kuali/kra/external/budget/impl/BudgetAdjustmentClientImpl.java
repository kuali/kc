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
package org.kuali.kra.external.budget.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.WebServiceClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.budgetService.BudgetAdjustmentService;
import org.kuali.kfs.integration.cg.budgetService.BudgetAdjustmentServiceSOAP;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.config.property.ConfigContext;



/**
 * This class is an implementation of the BudgetAdjustmentClient. 
 * This implementation is used to communicate with the financial system
 * via SOAP.
 */
public class BudgetAdjustmentClientImpl extends BudgetAdjustmentClientBase {
    
    public final static URL WSDL_LOCATION;
    
    private static BudgetAdjustmentClientImpl client;

    private static final Log LOG = LogFactory.getLog(BudgetAdjustmentClientImpl.class);

    
    private BudgetAdjustmentClientImpl() {
    }

    public static BudgetAdjustmentClientImpl getInstance() {
        if (client == null) {
            client = new BudgetAdjustmentClientImpl();
        }
        return client;
      }

    static
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (null == cl) {
            cl = BudgetAdjustmentClientImpl.class.getClassLoader();
        }
        String wsdlPath =  ((WebServiceClient) (BudgetAdjustmentServiceSOAP.class.getAnnotation(WebServiceClient.class))).wsdlLocation();
        WSDL_LOCATION = cl.getResource(wsdlPath); 
    }
    
    @Override
    protected BudgetAdjustmentService getServiceHandle() {
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
        
        BudgetAdjustmentServiceSOAP ss = new BudgetAdjustmentServiceSOAP(wsdlURL, SERVICE_NAME);
        return ss.getBudgetAdjustmentServicePort();  
    }


}
