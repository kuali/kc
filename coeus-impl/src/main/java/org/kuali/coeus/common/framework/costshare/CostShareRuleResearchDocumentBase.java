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
package org.kuali.coeus.common.framework.costshare;

import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 
 * This class extends ResearchDocumentRuleBase as an abstract and implents validation functions for cost share stuff.
 */
public abstract class CostShareRuleResearchDocumentBase extends KcTransactionalDocumentRuleBase {

	@Autowired
	@Qualifier("errorReporter")
	private ErrorReporter errorReporter;
	
    private CostShareService costShareService;
    
    /**
     * 
     * This method validate the project period field.
     * @param projectPeriod
     * @param projectPeriodField
     * @param numberOfProjectPeriods
     * @return
     */
    protected boolean validateProjectPeriod(Object projectPeriod, String projectPeriodField, int numberOfProjectPeriods) {
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
            costShareService = KcServiceLocator.getService(CostShareService.class);
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

	public ErrorReporter getErrorReporter() {
		if (errorReporter == null) {
			errorReporter = KcServiceLocator.getService(ErrorReporter.class);
		}
		return errorReporter;
	}

	public void setErrorReporter(ErrorReporter errorReporter) {
		this.errorReporter = errorReporter;
	}

	public void reportError(String propertyName, String errorKey,
			String... errorParams) {
		getErrorReporter().reportError(propertyName, errorKey, errorParams);
	}

}
