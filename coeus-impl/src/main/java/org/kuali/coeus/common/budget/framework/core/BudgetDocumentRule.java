/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.distribution.*;
import org.kuali.coeus.common.budget.framework.period.*;
import org.kuali.coeus.common.budget.framework.income.AddBudgetProjectIncomeEvent;
import org.kuali.coeus.common.budget.framework.income.AddBudgetProjectIncomeRule;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncomeRuleImpl;
import org.kuali.coeus.common.budget.impl.distribution.BudgetCostShareRuleImpl;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandAAuditRule;
import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.budget.AwardBudgeCostTotalAuditRule;
import org.kuali.kra.award.budget.AwardBudgetBudgetTypeAuditRule;
import org.kuali.kra.award.budget.AwardBudgetCostLimitAuditRule;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetExpensesAuditRule;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelAuditRule;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRule;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRateAuditRule;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.common.framework.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.budget.modular.SyncModularBudgetRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class BudgetDocumentRule extends CostShareRuleResearchDocumentBase implements AddBudgetPeriodRule, AddBudgetCostShareRule, AddBudgetProjectIncomeRule, SaveBudgetPeriodRule, DeleteBudgetPeriodRule, GenerateBudgetPeriodRule, DocumentAuditRule, SyncModularBudgetRule {

    /** 
     * @see org.kuali.coeus.common.budget.framework.distribution.AddBudgetCostShareRule#processAddBudgetCostShareBusinessRules(org.kuali.coeus.common.budget.framework.distribution.AddBudgetCostShareEvent)
     */
    public boolean processAddBudgetCostShareBusinessRules(AddBudgetCostShareEvent addBudgetCostShareEvent) {
        return new BudgetCostShareRuleImpl().processAddBudgetCostShareBusinessRules(addBudgetCostShareEvent);
    }
    
    @Override
    public boolean processAddBudgetPeriodBusinessRules(AddBudgetPeriodEvent addBudgetPeriodEvent) {
        return new BudgetPeriodRule().processAddBudgetPeriodBusinessRules(addBudgetPeriodEvent);    
    }

    @Override
    public boolean processAddBudgetProjectIncomeBusinessRules(AddBudgetProjectIncomeEvent addBudgetIncomeEvent) {
        return new BudgetProjectIncomeRuleImpl().processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent);
    }
    
    public boolean processSaveBudgetPeriodBusinessRules(SaveBudgetPeriodEvent saveBudgetPeriodEvent) {
        return new BudgetPeriodRule().processSaveBudgetPeriodBusinessRules(saveBudgetPeriodEvent);    
    }
    
    public boolean processDeleteBudgetPeriodBusinessRules(DeleteBudgetPeriodEvent deleteBudgetPeriodEvent) {
        return new BudgetPeriodRule().processDeleteBudgetPeriodBusinessRules(deleteBudgetPeriodEvent);
    }

    public boolean processGenerateBudgetPeriodBusinessRules(GenerateBudgetPeriodEvent generateBudgetPeriodEvent) {
        return new BudgetPeriodRule().processGenerateBudgetPeriodBusinessRules(generateBudgetPeriodEvent);    
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof BudgetDocument)) {
            return false;
        }

        boolean valid = true;
        
        BudgetDocument budgetDocument = (BudgetDocument) document;
        
        GlobalVariables.getMessageMap().addToErrorPath("document");        
        GlobalVariables.getMessageMap().addToErrorPath("parentDocument");
        if (ObjectUtils.isNull(budgetDocument.getBudget().getBudgetParent().getDocument())) {
            budgetDocument.refreshReferenceObject("parentDocument");
        }
        if(Boolean.valueOf(budgetDocument.getBudget().getBudgetParent().getDocument().getProposalBudgetFlag())){
            valid &= processBudgetVersionsBusinessRule(budgetDocument.getBudget().getBudgetParent().getDocument(), true);
        } 
        GlobalVariables.getMessageMap().removeFromErrorPath("parentDocument");
        
        GlobalVariables.getMessageMap().addToErrorPath("budget"); 
        valid &= processBudgetPersonnelBusinessRules(budgetDocument);
        
        valid &= processBudgetExpenseBusinessRules(budgetDocument);
        
        valid &= processBudgetPersonnelBudgetBusinessRules(budgetDocument);
        
        valid &= processBudgetRatesBusinessRule(budgetDocument);
        
        valid &= processBudgetProjectIncomeBusinessRule(budgetDocument);

        GlobalVariables.getMessageMap().removeFromErrorPath("budget");
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        
        
        return valid;
    }

    /**
    *
    * Validate budget project income. 
    * costshare percentage must be between 0 and 999.99
    * @param budgetDocument
    * @return
    */
    protected boolean processBudgetProjectIncomeBusinessRule(BudgetDocument budgetDocument) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        int i = 0;
        for (BudgetCostShare budgetCostShare : budgetDocument.getBudget().getBudgetCostShares()) {
            String errorPath = "budgetCostShare[" + i + "]";
            errorMap.addToErrorPath(errorPath);
            if(budgetCostShare.getSharePercentage()!=null && (budgetCostShare.getSharePercentage().isLessThan(new ScaleTwoDecimal(0)) ||
               budgetCostShare.getSharePercentage().isGreaterThan(new ScaleTwoDecimal(100)))) {
                errorMap.putError("sharePercentage", KeyConstants.ERROR_COST_SHARE_PERCENTAGE);
                valid = false;
            }
            //check for duplicate fiscal year and source accounts on all unchecked cost shares
            if (i < budgetDocument.getBudget().getBudgetCostShareCount()) {
                for (int j = i+1; j < budgetDocument.getBudget().getBudgetCostShareCount(); j++) {
                    BudgetCostShare tmpCostShare = budgetDocument.getBudget().getBudgetCostShare(j);
                    int thisFiscalYear = budgetCostShare.getProjectPeriod() == null ? Integer.MIN_VALUE : budgetCostShare.getProjectPeriod();
                    int otherFiscalYear = tmpCostShare.getProjectPeriod() == null ? Integer.MIN_VALUE : tmpCostShare.getProjectPeriod();
                    if (thisFiscalYear == otherFiscalYear
                            && StringUtils.equalsIgnoreCase(budgetCostShare.getSourceAccount(), tmpCostShare.getSourceAccount())) {
                        errorMap.putError("fiscalYear", KeyConstants.ERROR_COST_SHARE_DUPLICATE, 
                                thisFiscalYear == Integer.MIN_VALUE ? "" : thisFiscalYear+"", 
                                budgetCostShare.getSourceAccount()==null?"\"\"":budgetCostShare.getSourceAccount());
                        valid = false;
                    }
                }
            }        
            
            //validate project period stuff            
            String currentField = "document.budget.budgetCostShare[" + i + "].projectPeriod";
            int numberOfProjectPeriods = budgetDocument.getBudget().getBudgetPeriods().size();
            boolean validationCheck = this.validateProjectPeriod(budgetCostShare.getProjectPeriod(), currentField, numberOfProjectPeriods);            
            valid &= validationCheck;
            
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        //check project income for values that are not greater than 0
        i = 0;
        for (BudgetProjectIncome budgetProjectIncome : budgetDocument.getBudget().getBudgetProjectIncomes()) {
            String errorPath = "budgetProjectIncomes[" + i + "]";
            errorMap.addToErrorPath(errorPath);
            if (budgetProjectIncome.getProjectIncome() == null || !budgetProjectIncome.getProjectIncome().isGreaterThan(new ScaleTwoDecimal(0.00))) {
                errorMap.putError("projectIncome", "error.projectIncome.negativeOrZero");
                valid = false;
            }          
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        
        return valid;
    }

    /**
    *
    * Validate budget rates. 
    * Applicable rates are mandatory
    * @param budgetDocument
    * @return
    */
    protected boolean processBudgetRatesBusinessRule(BudgetDocument budgetDocument) {
        boolean valid = true;
        final int APPLICABLE_RATE_LENGTH_EXCEEDED = 1;
        final int APPLICABLE_RATE_NEGATIVE = -1;

        MessageMap errorMap = GlobalVariables.getMessageMap();
        int i = 0;
        for (BudgetRate budgetRate : budgetDocument.getBudget().getBudgetRates()) {
            String rateClassType = budgetRate.getRateClass().getRateClassType().getDescription();
            String errorPath = "budgetProposalRate[" + rateClassType + "][" + i + "]";
            errorMap.addToErrorPath(errorPath);
            /* look for applicable rate */
            if(budgetRate.isApplicableRateNull()) {
                valid = false;
                errorMap.putError("applicableRate", KeyConstants.ERROR_REQUIRED_APPLICABLE_RATE);
            }else if(!ScaleTwoDecimal.isNumeric(budgetRate.getApplicableRate().toString())) {
                valid = false;
                errorMap.putError("applicableRate", KeyConstants.ERROR_APPLICABLE_RATE_NOT_NUMERIC);
            }else {
                switch(verifyApplicableRate(budgetRate.getApplicableRate())) {
                    case APPLICABLE_RATE_LENGTH_EXCEEDED :
                        valid = false;
                        errorMap.putError("applicableRate", KeyConstants.ERROR_APPLICABLE_RATE_LIMIT, Constants.APPLICABLE_RATE_LIMIT);
                        break;
                    case APPLICABLE_RATE_NEGATIVE :
                        valid = false;
                        errorMap.putError("applicableRate", KeyConstants.ERROR_APPLICABLE_RATE_NEGATIVE);
                        break;
                        
                }
            }
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }

        i = 0;
        for (BudgetLaRate budgetLaRate : budgetDocument.getBudget().getBudgetLaRates()) {
            String rateClassType = "";
            if (ObjectUtils.isNotNull(budgetLaRate.getRateClass()) && ObjectUtils.isNotNull(budgetLaRate.getRateClass().getRateClassType())) {
                rateClassType = budgetLaRate.getRateClass().getRateClassType().getDescription();
            }
            String errorPath = "budgetRate[" + rateClassType + "][" + i + "]";
            errorMap.addToErrorPath(errorPath);
            /* look for applicable rate */
            if(budgetLaRate.isApplicableRateNull()) {
                valid = false;
                errorMap.putError("applicableRate", KeyConstants.ERROR_REQUIRED_APPLICABLE_RATE);
            }else if(!ScaleTwoDecimal.isNumeric(budgetLaRate.getApplicableRate().toString())) {
                valid = false;
                errorMap.putError("applicableRate", KeyConstants.ERROR_APPLICABLE_RATE_NOT_NUMERIC);
            }else {
                switch(verifyApplicableRate(budgetLaRate.getApplicableRate())) {
                    case APPLICABLE_RATE_LENGTH_EXCEEDED :
                        valid = false;
                        errorMap.putError("applicableRate", KeyConstants.ERROR_APPLICABLE_RATE_LIMIT, Constants.APPLICABLE_RATE_LIMIT);
                        break;
                    case APPLICABLE_RATE_NEGATIVE :
                        valid = false;
                        errorMap.putError("applicableRate", KeyConstants.ERROR_APPLICABLE_RATE_NEGATIVE);
                        break;
                        
                }
            }
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }

        return valid;
    }

    /**
     * This method checks for a valid applicable rate
     * 
     * @param applicableRate
     * @return
     */
    private int verifyApplicableRate(ScaleTwoDecimal applicableRate) {
        // problematic, such as -100.00 will get 'less than or equal to 999.99 error, also problem with negative less than 1. so rewrote it
        int rateValue = 0;
        if (applicableRate.isNegative()) {
            rateValue = -1;
        } else if (applicableRate.isGreaterThan(new ScaleTwoDecimal(Constants.APPLICABLE_RATE_LIMIT))) {
            rateValue = 1;            
        }            
        return rateValue;
    }
    /**
     * This method checks business rules related to Budget Personnel functionality
     * 
     * @param budgetDocument
     * @return
     */
    protected boolean processBudgetPersonnelBusinessRules(BudgetDocument budgetDocument) {
        return new BudgetPersonnelRule().processBudgetPersonnelBusinessRules(budgetDocument);
    }
    
    /**
     * This method checks business rules related to Budget Expenses functionality
     * 
     * @param budgetDocument
     * @return
     */
    protected boolean processBudgetExpenseBusinessRules(BudgetDocument budgetDocument) {
        boolean valid = true;

        MessageMap errorMap = GlobalVariables.getMessageMap();
        
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();
        int i=0;
        int j=0;
        for(BudgetPeriod budgetPeriod: budgetPeriods){
            j=0;
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            for(BudgetLineItem budgetLineItem: budgetLineItems){
                if(budgetLineItem!=null && budgetLineItem.getStartDate()!=null && budgetLineItem.getStartDate().before(budgetPeriod.getStartDate())){
                    errorMap.putError("budgetCategoryTypes[" + budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode() + "].budgetPeriods[" + i +"].budgetLineItems[" + j + "].startDate",KeyConstants.ERROR_LINEITEM_STARTDATE_BEFORE_PERIOD_STARTDATE);
                    valid = false;
                }
                if(budgetLineItem!=null && budgetLineItem.getEndDate()!=null && budgetLineItem.getEndDate().after(budgetPeriod.getEndDate())){
                    errorMap.putError("budgetCategoryTypes[" + budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode() + "].budgetPeriods[" + i +"].budgetLineItems[" + j + "].endDate",KeyConstants.ERROR_LINEITEM_ENDDATE_AFTER_PERIOD_ENDDATE);
                    valid = false;
                }

                if (budgetLineItem!=null && budgetLineItem.getQuantity() != null && budgetLineItem.getQuantity().intValue()<0) {
                    errorMap.putError("budgetPeriod[" + i +"].budgetLineItem[" + j + "].quantity", KeyConstants.ERROR_NEGATIVE_AMOUNT,"Quantity");
                    valid = false;
                }
         
                j++;
            }
            i++;
        }
        return valid;
    }
    
    /**
     * This method checks business rules related to Budget Personnel Budget functionality
     * 
     * @param budgetDocument
     * @return
     */
    protected boolean processBudgetPersonnelBudgetBusinessRules(BudgetDocument budgetDocument) {
        boolean valid = true;

        MessageMap errorMap = GlobalVariables.getMessageMap();
        
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();
        int i=0;
        int j=0;
        int k=0;
        for(BudgetPeriod budgetPeriod: budgetPeriods){
            j=0;
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            k=0;
            for(BudgetLineItem budgetLineItem: budgetLineItems){
                for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
                    if(budgetPersonnelDetails!=null && budgetPersonnelDetails.getStartDate()!=null && budgetPersonnelDetails.getStartDate().before(budgetLineItem.getStartDate())){
                        errorMap.putError("budgetPeriod[" + i +"].budgetLineItems[" + j + "].budgetPersonnelDetailsList[" + k + "].startDate",KeyConstants.ERROR_PERSONNELBUDGETLINEITEM_STARTDATE_BEFORE_LINEITEM_STARTDATE);
                        valid = false;
                    }
                    if(budgetPersonnelDetails!=null && budgetPersonnelDetails.getEndDate()!=null && budgetPersonnelDetails.getEndDate().after(budgetLineItem.getEndDate())){
                        errorMap.putError("budgetPeriod[" + i +"].budgetLineItems[" + j + "].budgetPersonnelDetailsList[" + k + "].endDate",KeyConstants.ERROR_PERSONNELBUDGETLINEITEM_ENDDATE_AFTER_LINEITEM_ENDDATE);
                        valid = false;
                    }                    
                    if(budgetPersonnelDetails.getPercentEffort().isGreaterThan(new ScaleTwoDecimal(100))){
                        errorMap.putError("budgetPeriod[" + i +"].budgetLineItems[" + j + "].budgetPersonnelDetailsList[" + k + "].percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
                    }
                    if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(new ScaleTwoDecimal(100))){
                        errorMap.putError("budgetPeriod[" + i +"].budgetLineItems[" + j + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
                    }
                    if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
                        errorMap.putError("budgetPeriod[" + i +"].budgetLineItems[" + j + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        return valid;
    }
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean retval = true;
        
        retval &= new KcDocumentBaseAuditRule().processRunAuditBusinessRules(document);

        retval &= new BudgetPeriodAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new BudgetExpensesAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new BudgetPersonnelAuditRule().processRunPersonnelAuditBusinessRules(document);

        retval &= new BudgetRateAuditRule().processRunAuditBusinessRules(document);
        
        // Skipping D and I audits for Awards Budgets since we've temporarily removed the Award D&I tab
        if (!(document instanceof AwardBudgetDocument)) {
        
            retval &= new BudgetUnrecoveredFandAAuditRule().processRunAuditBusinessRules(document);
        
            retval &= new BudgetCostShareAuditRule().processRunAuditBusinessRules(document);

        }
        
        retval &= new ActivityTypeAuditRule().processRunAuditBusinessRules(document);

        if (!((BudgetDocument)document).getBudget().isProposalBudget()){
            retval &= new AwardBudgetBudgetTypeAuditRule().processRunAuditBusinessRules(document);
            retval &= new AwardBudgeCostTotalAuditRule().processRunAuditBusinessRules(document);
            retval &= new AwardBudgetCostLimitAuditRule().processRunAuditBusinessRules(document);
        }
        if (retval) {
            processRunAuditBudgetVersionRule(((BudgetDocument) document).getBudget().getBudgetParent().getDocument());
        }
        
        return retval;
    }
    
    protected boolean processRunAuditBudgetVersionRule(BudgetParentDocument parentDocument) {
        // audit check for budgetversion with final status
        boolean finalAndCompleteBudgetVersionFound = false;
        boolean budgetVersionsExists = false;
        boolean retval = true;
        
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String budgetStatusCompleteCode = getParameterService().getParameterValueAsString(
                BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        for (BudgetDocumentVersion budgetDocumentVersion : parentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview(); 
            budgetVersionsExists = true;
            if (budgetVersion.isFinalVersionFlag()) {
                BudgetParent budgetParent =parentDocument.getBudgetParent(); 
                if (budgetParent.getBudgetStatus()!= null 
                        && budgetParent.getBudgetStatus().equals(budgetStatusCompleteCode)) {
                    finalAndCompleteBudgetVersionFound = true;
                }
            }
        }
        if (budgetVersionsExists && !finalAndCompleteBudgetVersionFound) {
            auditErrors.add(new AuditError("document.parentBudget.budgetVersionOverview", KeyConstants.AUDIT_ERROR_NO_BUDGETVERSION_COMPLETE_AND_FINAL, Constants.PD_BUDGET_VERSIONS_PAGE + "." + Constants.BUDGET_VERSIONS_PANEL_ANCHOR));
            retval = false;
        }
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put("budgetVersionErrors", new AuditCluster(Constants.BUDGET_VERSION_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }

        return retval;
    }
    
    public boolean processSyncModularBusinessRules(Document document) {
        if (!(document instanceof BudgetDocument)) {
            return false;
        }
        
        boolean valid = true;
        
        BudgetDocument budgetDocument = (BudgetDocument) document;
        
        GlobalVariables.getMessageMap().addToErrorPath("document");
        
        List budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();
        if (ObjectUtils.isNotNull(budgetPeriods) || budgetPeriods.size() >= 1) {
            BudgetPeriod period1 = (BudgetPeriod) budgetPeriods.get(0);
            if (ObjectUtils.isNull(period1.getBudgetLineItems()) || period1.getBudgetLineItems().isEmpty()) {
                valid = false;
            }
        } else {
            valid = false;
        }
        
        if (!valid) {
            GlobalVariables.getMessageMap().putError("modularBudget", KeyConstants.ERROR_NO_DETAILED_BUDGET);
        }
        
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        
        return valid;
    }

}
