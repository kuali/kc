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
package org.kuali.kra.costshare;

import java.util.List;

import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.AuditError;

/**
 * 
 * This class extends ResearchDocumentRuleBase as an abstract and implents validation functions for cost share stuff.
 */
public abstract class CostShareRuleResearchDocumentBase extends ResearchDocumentRuleBase {
    
    private CostShareService costShareService;
    
    /**
     * 
     * This method validate the project period field.
     * @param projectPeriod
     * @param projectPeriodField
     * @param numberOfProjectPeriods
     * @return
     */
    public boolean validateProjectPeriod(Object projectPeriod, String projectPeriodField, int numberOfProjectPeriods) {
        boolean valid = true;
        if (projectPeriod != null) {
            try {
                int projectPeriodInt = Integer.parseInt(projectPeriod.toString().trim());
                
                if (validateAsFiscalYear()) {
                    if (projectPeriodInt < Constants.MIN_FISCAL_YEAR || projectPeriodInt > Constants.MAX_FISCAL_YEAR) {
                        valid = false;
                        reportError(projectPeriodField, KeyConstants.ERROR_FISCAL_YEAR_RANGE, getProjectPeriodLabel());
                    }
              } else if (validateAsProjectPeriod() && numberOfProjectPeriods > -1) {
                  if (projectPeriodInt <= 0) {
                      valid = false;
                      String[] params = {getProjectPeriodLabel(), String.valueOf(numberOfProjectPeriods)};
                      reportError(projectPeriodField, KeyConstants.ERROR_PROJECT_PERIOD_RANGE, params);
                  }
                } else {
                    //the project period is not a project period nor is it a fiscal year, no validation requirements at this time.
                }
            } catch (NumberFormatException e) {
                valid = false;
                reportError(projectPeriodField, KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT, getProjectPeriodLabel());
            }
        } else {
            valid = false;
            reportError(projectPeriodField, KeyConstants.ERROR_FISCAL_YEAR_REQUIRED, getProjectPeriodLabel());
        }
        return valid;
    }
    
    /**
     * 
     * This method...
     * @param projectPeriod
     * @param projectPeriodField
     * @return
     */
    public boolean validateProjectPeriod(Object projectPeriod, String projectPeriodField) {
        return validateProjectPeriod(projectPeriod, projectPeriodField, -1);
    }
    
    public boolean validatePeriodNumber(BudgetCostShare costShare, String projectPeriodField, int numberOfProjectPeriods, List<AuditError> auditErrors) {

        int projectPeriodInt = Integer.parseInt(costShare.getProjectPeriod().toString().trim());
        if (projectPeriodInt <= 0 || projectPeriodInt > numberOfProjectPeriods) {
            AuditError auditError = new AuditError(projectPeriodField, KeyConstants.ERROR_PROJECT_PERIOD_RANGE,
                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                    new String[] {Integer.toString(projectPeriodInt), Integer.toString(numberOfProjectPeriods), Integer.toString(numberOfProjectPeriods)});
            auditErrors.add(auditError);
            return false;
        }
        return true;
    }


    
    
    protected CostShareService getCostShareService() {
        if (costShareService == null) {
            costShareService = KraServiceLocator.getService(CostShareService.class);
        }
        return costShareService;
    }
    
    public void setCostShareService(CostShareService costShareService) {
        this.costShareService = costShareService;
    }
    
    private String getProjectPeriodLabel() {
        String label = getCostShareService().getCostShareLabel();
        return label;
    }
    
    private boolean validateAsFiscalYear() {
        boolean retVal = getCostShareService().validateProjectPeriodAsFiscalYear();
        return retVal;
    }
    
    private boolean validateAsProjectPeriod() {
        boolean retVal = getCostShareService().validateProjectPeriodAsProjectPeriod();
        return retVal;
        
    }

}
