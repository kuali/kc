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
package org.kuali.kra.budget.document;

import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.budget.AwardBudgeCostTotalAuditRule;
import org.kuali.kra.award.budget.AwardBudgetBudgetTypeAuditRule;
import org.kuali.kra.award.budget.AwardBudgetCostLimitAuditRule;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.distributionincome.AddBudgetCostShareEvent;
import org.kuali.kra.budget.distributionincome.AddBudgetCostShareRule;
import org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeEvent;
import org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeRule;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.budget.distributionincome.BudgetCostShareAuditRule;
import org.kuali.kra.budget.distributionincome.BudgetCostShareRuleImpl;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncomeRuleImpl;
import org.kuali.kra.budget.distributionincome.BudgetUnrecoveredFandAAuditRule;
import org.kuali.kra.budget.nonpersonnel.BudgetExpensesAuditRule;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.AddBudgetPeriodEvent;
import org.kuali.kra.budget.parameters.AddBudgetPeriodRule;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.parameters.BudgetPeriodAuditRule;
import org.kuali.kra.budget.parameters.BudgetPeriodRule;
import org.kuali.kra.budget.parameters.DeleteBudgetPeriodEvent;
import org.kuali.kra.budget.parameters.DeleteBudgetPeriodRule;
import org.kuali.kra.budget.parameters.GenerateBudgetPeriodEvent;
import org.kuali.kra.budget.parameters.GenerateBudgetPeriodRule;
import org.kuali.kra.budget.parameters.SaveBudgetPeriodEvent;
import org.kuali.kra.budget.parameters.SaveBudgetPeriodRule;
import org.kuali.kra.budget.personnel.BudgetPersonnelAuditRule;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRule;
import org.kuali.kra.budget.rates.BudgetLaRate;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.BudgetRateAuditRule;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.budget.modular.SyncModularBudgetRule;
import org.kuali.kra.rules.ActivityTypeAuditRule;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;

public class BudgetDocumentRule extends CostShareRuleResearchDocumentBase implements AddBudgetPeriodRule, AddBudgetCostShareRule, AddBudgetProjectIncomeRule, SaveBudgetPeriodRule, DeleteBudgetPeriodRule, GenerateBudgetPeriodRule, DocumentAuditRule, SyncModularBudgetRule {

    /** 
     * @see org.kuali.kra.budget.distributionincome.AddBudgetCostShareRule#processAddBudgetCostShareBusinessRules(org.kuali.kra.budget.distributionincome.AddBudgetCostShareEvent)
     */
    public boolean processAddBudgetCostShareBusinessRules(AddBudgetCostShareEvent addBudgetCostShareEvent) {
        return new BudgetCostShareRuleImpl().processAddBudgetCostShareBusinessRules(addBudgetCostShareEvent);
    }
    
    /**
     * @see org.kuali.kra.budget.parameters.AddBudgetPeriodRule#processAddBudgetPeriodBusinessRules(org.kuali.kra.budget.document.BudgetDocument,org.kuali.kra.budget.parameters.BudgetPeriod)
     */
    public boolean processAddBudgetPeriodBusinessRules(AddBudgetPeriodEvent addBudgetPeriodEvent) {
        return new BudgetPeriodRule().processAddBudgetPeriodBusinessRules(addBudgetPeriodEvent);    
    }

    /**
     * @see org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeRule#processAddBudgetProjectIncomeBusinessRules(org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeEvent)
     */
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
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(document, getMaxDictionaryValidationDepth(), VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        GlobalVariables.getMessageMap().addToErrorPath("parentDocument");
        if (ObjectUtils.isNull(budgetDocument.getParentDocument())) {
            budgetDocument.refreshReferenceObject("parentDocument");
        }
        if(Boolean.valueOf(budgetDocument.getParentDocument().getProposalBudgetFlag())){
            valid &= processBudgetVersionsBusinessRule(budgetDocument.getParentDocument(), true);
        } 
//        else {
//            valid &= processBudgetTypeBusinessRules(budgetDocument);
//        }
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
            if(budgetCostShare.getSharePercentage()!=null && (budgetCostShare.getSharePercentage().isLessThan(new BudgetDecimal(0)) || 
               budgetCostShare.getSharePercentage().isGreaterThan(new BudgetDecimal(100)))) {
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
        GlobalVariables.getMessageMap().removeFromErrorPath("budget");
        GlobalVariables.getMessageMap().addToErrorPath("budgets[0]"); 
        i = 0;
        for (BudgetProjectIncome budgetProjectIncome : budgetDocument.getBudget().getBudgetProjectIncomes()) {
            String errorPath = "budgetProjectIncomes[" + i + "]";
            errorMap.addToErrorPath(errorPath);
            if (budgetProjectIncome.getProjectIncome() == null || !budgetProjectIncome.getProjectIncome().isGreaterThan(new KualiDecimal(0.00))) {
                errorMap.putError("projectIncome", "error.projectIncome.negativeOrZero");
                valid = false;
            }          
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        GlobalVariables.getMessageMap().removeFromErrorPath("budgets[0]");
        GlobalVariables.getMessageMap().addToErrorPath("budget");
        
        return valid;
    }

    // change to audit rule
//    protected boolean processBudgetTypeBusinessRules(BudgetDocument budgetDocument) {
//        boolean valid = true;
//        MessageMap errorMap = GlobalVariables.getMessageMap();
//        errorMap.removeFromErrorPath("parentDocument");
//        errorMap.removeFromErrorPath("document");
//        
//        AwardBudgetExt awardBudget = (AwardBudgetExt)budgetDocument.getBudget();
//        if ("2".equals(awardBudget.getAwardBudgetTypeCode()) && StringUtils.isBlank(awardBudget.getComments())) {
//            errorMap.putError("document.budget.comments", KeyConstants.ERROR_REQUIRED, "Comments(Comments)");
//            valid = false;
//        }
//        GlobalVariables.getMessageMap().addToErrorPath("document");        
//        GlobalVariables.getMessageMap().addToErrorPath("parentDocument");
//        return valid;
//    }

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
            String rateClassType = budgetRate.getRateClass().getRateClassTypeT().getDescription();
            String errorPath = "budgetProposalRate[" + rateClassType + "][" + i + "]";
            errorMap.addToErrorPath(errorPath);
            /* look for applicable rate */
            if(budgetRate.isApplicableRateNull()) {
                valid = false;
                errorMap.putError("applicableRate", KeyConstants.ERROR_REQUIRED_APPLICABLE_RATE);
            }else if(!BudgetDecimal.isNumeric(budgetRate.getApplicableRate().toString())) {
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
            if (ObjectUtils.isNotNull(budgetLaRate.getRateClass()) && ObjectUtils.isNotNull(budgetLaRate.getRateClass().getRateClassTypeT())) {
                rateClassType = budgetLaRate.getRateClass().getRateClassTypeT().getDescription();
            }
            String errorPath = "budgetRate[" + rateClassType + "][" + i + "]";
            errorMap.addToErrorPath(errorPath);
            /* look for applicable rate */
            if(budgetLaRate.isApplicableRateNull()) {
                valid = false;
                errorMap.putError("applicableRate", KeyConstants.ERROR_REQUIRED_APPLICABLE_RATE);
            }else if(!BudgetDecimal.isNumeric(budgetLaRate.getApplicableRate().toString())) {
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
    private int verifyApplicableRate(BudgetDecimal applicableRate) {
        // problematic, such as -100.00 will get 'less than or equal to 999.99 error, also problem with negative less than 1. so rewrote it
//        int decimalIndex = applicableRate.indexOf(Constants.APPLICABLE_RATE_DECIMAL_CHAR);
        int rateValue = 0;
//        String precision = applicableRate.substring(0, decimalIndex);
//        String scale = applicableRate.substring(decimalIndex+1, applicableRate.length());
//        
//        if(precision.length() > Constants.APPLICABLE_RATE_PRECISION || scale.length() > Constants.APPLICABLE_RATE_SCALE) {
//            rateValue = 1;
//        }else if(Integer.parseInt(precision) < 0) {
//            rateValue = -1;
//        }
        if (applicableRate.isNegative()) {
            rateValue = -1;
        } else if (applicableRate.isGreaterThan(new BudgetDecimal(Constants.APPLICABLE_RATE_LIMIT))) {
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
        //TODO - put budget expense validation rules here.
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
//                if (budgetLineItem!=null && budgetLineItem.getCostSharingAmount() != null && budgetLineItem.getCostSharingAmount().isNegative()) {
//                    errorMap.putError("budgetPeriod[" + i +"].budgetLineItem[" + j + "].costSharingAmount", KeyConstants.ERROR_NEGATIVE_AMOUNT,"Cost Sharing");
//                    valid = false;
//                }
                if (budgetLineItem!=null && budgetLineItem.getQuantity() != null && budgetLineItem.getQuantity().intValue()<0) {
                    errorMap.putError("budgetPeriod[" + i +"].budgetLineItem[" + j + "].quantity", KeyConstants.ERROR_NEGATIVE_AMOUNT,"Quantity");
                    valid = false;
                }
//                if (budgetLineItem!=null && budgetLineItem.getLineItemCost() != null && budgetLineItem.getLineItemCost().isNegative()) {
//                    errorMap.putError("budgetPeriod[" + i +"].budgetLineItem[" + j + "].lineItemCost", KeyConstants.ERROR_NEGATIVE_AMOUNT,"Total Base Cost");
//                    valid = false;
//                }
//                if(budgetLineItem.getEndDate().compareTo(budgetLineItem.getStartDate()) <=0 ) {                        
//                        errorMap.putError("budgetPeriod["+i+"].budgetLineItem["+j+"].endDate", KeyConstants.ERROR_LINE_ITEM_DATES);
//                        return false;
//                }
         
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
                    if(budgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
                        errorMap.putError("budgetPeriod[" + i +"].budgetLineItems[" + j + "].budgetPersonnelDetailsList[" + k + "].percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
                    }
                    if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
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
    
    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean retval = true;
        
        retval &= super.processRunAuditBusinessRules(document);

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

        if (!Boolean.valueOf(((BudgetDocument)document).getParentDocument().getProposalBudgetFlag())){
            retval &= new AwardBudgetBudgetTypeAuditRule().processRunAuditBusinessRules(document);
            retval &= new AwardBudgeCostTotalAuditRule().processRunAuditBusinessRules(document);
            retval &= new AwardBudgetCostLimitAuditRule().processRunAuditBusinessRules(document);
        }
        if (retval) {
            processRunAuditBudgetVersionRule(((BudgetDocument) document).getParentDocument());
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

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey("budgetPersonnelAuditErrors")) {
           KNSGlobalVariables.getAuditErrorMap().put("budgetPersonnelAuditErrors", new AuditCluster("Budget Personnel Information", auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get("budgetPersonnelAuditErrors")).getAuditErrorList();
        }
        
        return auditErrors;
    }

}
