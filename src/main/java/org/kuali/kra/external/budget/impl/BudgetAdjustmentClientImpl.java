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

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceClient;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.budgetService.BudgetAdjustmentService;
import org.kuali.kfs.integration.cg.budgetService.BudgetAdjustmentServiceSOAP;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;



/**
 * This class is an implementation of the BudgetAdjustmentClient. 
 * This implementation is used to communicate with the financial system
 * via SOAP.
 */
public class BudgetAdjustmentClientImpl extends BudgetAdjustmentClientBase {
    private static final Log LOG = LogFactory.getLog(BudgetAdjustmentClientImpl.class);
    public final static URL WSDL_LOCATION;
    protected static final QName SERVICE_NAME = new QName("KFS", "budgetAdjustmentServiceSOAP");
    private static BudgetAdjustmentClientImpl client;

    
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
        BudgetAdjustmentServiceSOAP ss = new BudgetAdjustmentServiceSOAP(WSDL_LOCATION, SERVICE_NAME);
        return ss.getBudgetAdjustmentServicePort();  
    }


}
