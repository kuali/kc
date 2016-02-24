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
package org.kuali.kra.external.budget.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.module.external.kc.service.BudgetAdjustmentService;
import org.kuali.kfs.module.external.kc.service.BudgetAdjustmentServiceSOAP;
import org.kuali.kra.infrastructure.Constants;

import javax.xml.ws.WebServiceClient;
import java.net.MalformedURLException;
import java.net.URL;



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
            String serviceEndPointUrl = getConfigurationService().getPropertyValueAsString(Constants.FIN_SYSTEM_INTEGRATION_SERVICE_URL);
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
