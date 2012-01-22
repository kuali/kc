/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.nonpersonnel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class BudgetExpensesAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetExpensesAuditRule.class);
    

    /**
     * 
     * This method is to validate budget expense business rules
     * 
     */
    public boolean processRunAuditBusinessRules(Document document) {
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        BudgetDocument budgetDocument = (BudgetDocument) document;
        boolean retval = true;
        int i = 0;
        if ( budgetDocument.getBudget().getTotalCostLimit().isGreaterThan(new BudgetDecimal(0)) &&
                budgetDocument.getBudget().getTotalCost().isGreaterThan(budgetDocument.getBudget().getTotalCostLimit()) ) {
            String key = "budgetParametersOverviewWarnings";
            AuditCluster auditCluster = (AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(key);
            if (auditCluster == null) {
                List<AuditError> auditErrors = new ArrayList<AuditError>();
                auditCluster = new AuditCluster(Constants.BUDGET_PARAMETERS_OVERVIEW_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS);
                KNSGlobalVariables.getAuditErrorMap().put(key, auditCluster);
            }
            List<AuditError> auditErrors = auditCluster.getAuditErrorList();
            auditErrors.add(new AuditError("document.budget.totalCostLimit", 
                    KeyConstants.WARNING_TOTAL_COST_LIMIT_EXCEEDED, 
                    Constants.BUDGET_PARAMETERS_PAGE_METHOD + "." + Constants.BUDGET_PARAMETERS_OVERVIEW_PANEL_ANCHOR));
            retval=false;
            
        }
       for (BudgetPeriod budgetPeriod : budgetDocument.getBudget().getBudgetPeriods()) {
            if(budgetPeriod.getTotalCostLimit().isGreaterThan(new BudgetDecimal(0)) && budgetPeriod.getTotalCost().isGreaterThan(budgetPeriod.getTotalCostLimit())){            
                String key = "budgetPeriodProjectDateAuditWarnings";
                AuditCluster auditCluster = (AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(key);
                if (auditCluster == null) {
                    List<AuditError> auditErrors = new ArrayList<AuditError>();
                    auditCluster = new AuditCluster(Constants.BUDGET_PARAMETERS_TOTALS_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS);
                    KNSGlobalVariables.getAuditErrorMap().put(key, auditCluster);
                }
                List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                auditErrors.add(new AuditError("document.budget.budgetPeriods[" + (budgetPeriod.getBudgetPeriod() - 1) + "].totalCostLimit", 
                        KeyConstants.WARNING_PERIOD_COST_LIMIT_EXCEEDED, 
                        Constants.BUDGET_PARAMETERS_PAGE_METHOD + "." + Constants.BUDGET_PARAMETERS_TOTALS_PANEL_ANCHOR));
                retval=false;
            } 
            
            // budget personnel budget effective warning 
            int j = 0;
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                String panelName = budgetService.getBudgetExpensePanelName(budgetPeriod, budgetLineItem);
                
                if(budgetLineItem.getUnderrecoveryAmount() != null && budgetLineItem.getUnderrecoveryAmount().isNegative()) {
                    String key = "budgetNonPersonnelAuditWarnings" + budgetPeriod.getBudgetPeriod()+panelName;
                    AuditCluster auditCluster = (AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(key);
                    if (auditCluster == null) {
                        List<AuditError> auditErrors = new ArrayList<AuditError>();
                        auditCluster = new AuditCluster(panelName+" Budget Period "+budgetPeriod.getBudgetPeriod(), auditErrors, Constants.AUDIT_WARNINGS);
                        KNSGlobalVariables.getAuditErrorMap().put(key, auditCluster);
                    }
                    List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                    auditErrors.add(new AuditError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+j+"].underrecoveryAmount", KeyConstants.WARNING_UNRECOVERED_FA_NEGATIVE, Constants.BUDGET_EXPENSES_PAGE_METHOD + "." + budgetLineItem.getBudgetCategory().getBudgetCategoryType().getDescription() + "&viewBudgetPeriod=" + budgetPeriod.getBudgetPeriod() + "&selectedBudgetLineItemIndex=" + j + "&activePanelName=" + panelName));
                    retval=false;
                }
                    
                int k = 0;
                for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                    if (StringUtils.isNotEmpty(budgetPersonnelDetails.getEffdtAfterStartdtMsg())) {
                        String key = "budgetPersonnelBudgetAuditWarnings"+budgetPeriod.getBudgetPeriod();
                        AuditCluster auditCluster = (AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(key);
                        if (auditCluster == null) {
                            List<AuditError> auditErrors = new ArrayList<AuditError>();
                            auditCluster = new AuditCluster(Constants.PERSONNEL_BUDGET_PANEL_NAME + " (Period " +budgetPeriod.getBudgetPeriod()+")", auditErrors, Constants.AUDIT_WARNINGS);
                            KNSGlobalVariables.getAuditErrorMap().put(key, auditCluster);
                        }
                        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                        auditErrors.add(new AuditError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+j+"].budgetPersonnelDetailsList["+k+"].salaryRequested", KeyConstants.WARNING_EFFDT_AFTER_PERIOD_START_DATE, Constants.BUDGET_EXPENSES_PAGE_METHOD + "." + Constants.BUDGET_EXPENSES_OVERVIEW_PANEL_ANCHOR + "&viewBudgetPeriod=" + budgetPeriod.getBudgetPeriod() + "&selectedBudgetLineItemIndex=" + j + "&personnelDetailLine="+k, new String[]{budgetPersonnelDetails.getBudgetPerson().getPersonName()}));
                        retval=false;

                    }
                    if (budgetPersonnelDetails.getBudgetPerson().getCalculationBase().equals(BudgetDecimal.ZERO)) {
                        String key = "budgetPersonnelBudgetAuditWarnings"+budgetPeriod.getBudgetPeriod();
                        AuditCluster auditCluster = (AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(key);
                        if (auditCluster == null) {
                            List<AuditError> auditErrors = new ArrayList<AuditError>();
                            auditCluster = new AuditCluster(Constants.PERSONNEL_BUDGET_PANEL_NAME+ " (Period " +budgetPeriod.getBudgetPeriod() +")", auditErrors, Constants.AUDIT_WARNINGS);
                            KNSGlobalVariables.getAuditErrorMap().put(key, auditCluster);
                        }
                        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                        auditErrors.add(new AuditError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+j+"].budgetPersonnelDetailsList["+k+"].salaryRequested", KeyConstants.WARNING_BASE_SALARY_ZERO, Constants.BUDGET_EXPENSES_PAGE_METHOD + "." + Constants.BUDGET_EXPENSES_OVERVIEW_PANEL_ANCHOR + "&viewBudgetPeriod=" + budgetPeriod.getBudgetPeriod() + "&selectedBudgetLineItemIndex=" + j + "&personnelDetailLine="+k, new String[]{budgetPersonnelDetails.getBudgetPerson().getPersonName()}));
                        retval=false;

                    }

                    k++;
                }
                j++;
            }
        }
        
        return retval;

    }
}


