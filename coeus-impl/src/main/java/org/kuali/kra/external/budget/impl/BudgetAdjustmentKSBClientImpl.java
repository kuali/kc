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


import org.kuali.kfs.module.external.kc.service.BudgetAdjustmentService;
import org.kuali.kra.external.budget.BudgetAdjustmentClient;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;

/**
 * This class is an implementation of the Budget Adjustment client that uses the KSB
 * to communicate with the financial system.
 */
public class BudgetAdjustmentKSBClientImpl  extends BudgetAdjustmentClientBase {
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
