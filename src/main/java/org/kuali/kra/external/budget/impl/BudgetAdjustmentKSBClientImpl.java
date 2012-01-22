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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.budgetService.BudgetAdjustmentService;
import org.kuali.kra.external.budget.BudgetAdjustmentClient;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;

/**
 * This class is an implementation of the Budget Adjustment client that uses the KSB
 * to communicate with the financial system.
 */
public class BudgetAdjustmentKSBClientImpl  extends BudgetAdjustmentClientBase {
    private static final Log LOG = LogFactory.getLog(BudgetAdjustmentKSBClientImpl.class);
    private static  BudgetAdjustmentKSBClientImpl  ksbClient;

    private BudgetAdjustmentKSBClientImpl() {  }
    
    public static BudgetAdjustmentClient getInstance() {
      if (ksbClient == null)
          ksbClient = new BudgetAdjustmentKSBClientImpl();
      return ksbClient;
    }

    @Override
    protected BudgetAdjustmentService getServiceHandle() {
        BudgetAdjustmentService budgetAdjustmentService = (BudgetAdjustmentService) GlobalResourceLoader.getService(SERVICE_NAME);
        return budgetAdjustmentService;
    }
}
