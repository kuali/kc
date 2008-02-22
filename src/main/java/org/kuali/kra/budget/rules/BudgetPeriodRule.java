/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.rules;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.AddBudgetPeriodRule;
import org.kuali.kra.budget.rule.DeleteBudgetPeriodRule;
import org.kuali.kra.budget.rule.SaveBudgetPeriodRule;
import org.kuali.kra.budget.rule.event.AddBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.DeleteBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.GenerateBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.SaveBudgetPeriodEvent;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class BudgetPeriodRule extends ResearchDocumentRuleBase implements AddBudgetPeriodRule, SaveBudgetPeriodRule, DeleteBudgetPeriodRule{

    private static final String NEW_BUDGET_PERIOD = "newBudgetPeriod";
    private static final String BUDGET_SUMMARY = "budgetSummary";
    private Date projectStartDate;
    private Date projectEndDate;
    private Date previousPeriodEndDate;
    private String[] errorParameter;

    /**
     * 
     * @see org.kuali.kra.budget.rule.AddBudgetPeriodRule#processAddBudgetPeriodBusinessRules(org.kuali.kra.budget.rule.event.AddBudgetPeriodEvent)
     */
    public boolean processAddBudgetPeriodBusinessRules(AddBudgetPeriodEvent addBudgetPeriodEvent) {
        BudgetDocument document = (BudgetDocument)addBudgetPeriodEvent.getDocument();
        BudgetPeriod newBudgetPeriod = addBudgetPeriodEvent.getBudgetPeriod();
        
        boolean rulePassed = true;
        //String errorPath = NEW_BUDGET_PERIOD;

        if (!isValidBudgetPeriod(document, newBudgetPeriod)) {
            rulePassed = false;
        }

        return rulePassed;
    }

    public boolean processSaveBudgetPeriodBusinessRules(SaveBudgetPeriodEvent saveBudgetPeriodEvent) {
        BudgetDocument document = (BudgetDocument)saveBudgetPeriodEvent.getDocument();
        BudgetPeriod newBudgetPeriod = saveBudgetPeriodEvent.getBudgetPeriod();
        
        boolean rulePassed = true;

        if (!isValidBudgetPeriod(document, newBudgetPeriod)) {
            rulePassed = false;
        }else if(!isValidBudgetPeriodBoundaries(document)){
            rulePassed = false;
        }else if(!isBudgetStatusValid(document)) {
            rulePassed = false;
        }

        return rulePassed;
    }

    public boolean processGenerateBudgetPeriodBusinessRules(GenerateBudgetPeriodEvent generateBudgetPeriodEvent) {
        BudgetDocument document = (BudgetDocument)generateBudgetPeriodEvent.getDocument();
        BudgetPeriod newBudgetPeriod = generateBudgetPeriodEvent.getBudgetPeriod();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        boolean rulePassed = true;
        int budgetPeriodNumber = 0;
        /* check budget periods are valid.
         * check for valid periods in line item.
         * look for line item in period 1 - line items should exist for period 1 to generate 
         * budget periods.
         * if line item exists for period 1 look for line item in period 2 - line items in period 2 indicate 
         * that budget periods already generated 
         * */
        if (!isValidBudgetPeriod(document, newBudgetPeriod)) {
            rulePassed = false;
        }else if(!isValidBudgetPeriodBoundaries(document)){
            rulePassed = false;
        }else if(!getBudgetSummaryService().budgetLineItemExists(document, budgetPeriodNumber)) {
            errorMap.addToErrorPath("noPanel");
            rulePassed = false;
            errorMap.putError("noFocus", KeyConstants.ERROR_PERIOD_LINE_ITEM_DOESNOT_EXIST);
        }else if(getBudgetSummaryService().budgetLineItemExists(document, budgetPeriodNumber+1)) {
            errorMap.addToErrorPath("noPanel");
            rulePassed = false;
            errorMap.putError("noFocus", KeyConstants.ERROR_GENERATE_PERIOD);
        }
        errorMap.removeFromErrorPath("noPanel");
        return rulePassed;
    }

    public boolean processDeleteBudgetPeriodBusinessRules(DeleteBudgetPeriodEvent deleteBudgetPeriodEvent) {
        BudgetDocument document = (BudgetDocument)deleteBudgetPeriodEvent.getDocument();
        int budgetPeriodNumber = deleteBudgetPeriodEvent.getBudgetPeriodNumber();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        
        boolean rulePassed = true;

        if(getBudgetSummaryService().budgetLineItemExists(document, budgetPeriodNumber)) {
            errorMap.addToErrorPath("document.budgetPeriods[" + budgetPeriodNumber + "]");
            rulePassed = false;
            errorMap.putError("startDate", KeyConstants.ERROR_LINE_ITEM_EXISTS);
            errorMap.removeFromErrorPath("document.budgetPeriods[" + budgetPeriodNumber + "]");
        }
        return rulePassed;
    }

    private boolean isBudgetStatusValid(BudgetDocument budgetDocument) {
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        boolean statusValid = true;
        String budgetStatusCompleteCode = getKualiConfigurationService().getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();
        String budgetStatus = budgetDocument.getProposal().getBudgetStatus();
        boolean finalVersionFlag = budgetDocument.getFinalVersionFlag();
        errorMap.addToErrorPath(BUDGET_SUMMARY);
        if (budgetStatus!= null 
                && budgetStatus.equals(budgetStatusCompleteCode) 
                && !finalVersionFlag) {
            errorMap.putError("document.proposal.budgetStatus", KeyConstants.ERROR_NO_FINAL_BUDGET);
            finalVersionFlag = false;
        }
        errorMap.removeFromErrorPath(BUDGET_SUMMARY);
        return statusValid;
    }
    
    private boolean isValidBudgetPeriodBoundaries(BudgetDocument budgetDocument) {
        boolean validBoundaries = true;
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        List<BudgetLineItem> budgetLineItems = budgetDocument.getBudgetLineItems();
        List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetDocument.getBudgetPersonnelDetailsList();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            String[] dateParams = {budgetPeriod.getBudgetPeriod()+""};
            setErrorParameter(dateParams);
            /* get all line items for each budget period */
            Collection<BudgetLineItem> periodLineItems = new ArrayList();
            Collection<BudgetPersonnelDetails> periodPersonnelDetails = new ArrayList();
            /* filter by budget period */
            Integer budgetPeriodNumber = budgetPeriod.getBudgetPeriod();
            int index = budgetPeriodNumber - 1;
            errorMap.addToErrorPath("document.budgetPeriods[" + index + "]");
            periodLineItems = getBudgetSummaryService().getBudgetLineItemForPeriod(budgetDocument, budgetPeriodNumber); 
            periodPersonnelDetails = getBudgetSummaryService().getBudgetPersonnelDetailsForPeriod(budgetDocument, budgetPeriodNumber); 
            /* check line items */
            for(BudgetLineItem periodLineItem: periodLineItems) {
                if(budgetPeriod.getBudgetPeriod() == periodLineItem.getBudgetPeriod()) {
                    validBoundaries = isDateBefore(periodLineItem.getStartDate(), budgetPeriod.getStartDate(), "startDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                    if(validBoundaries) {
                        validBoundaries = isDateAfter(periodLineItem.getStartDate(), budgetPeriod.getEndDate(), "startDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                    }
                    if(validBoundaries) {
                        validBoundaries = isDateAfter(periodLineItem.getEndDate(), budgetPeriod.getEndDate(), "endDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                    }
                    if(validBoundaries) {
                        validBoundaries = isDateBefore(periodLineItem.getEndDate(), budgetPeriod.getStartDate(), "endDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                    }
                    break;
                }
            }
            /* check personnel line items */
            if(validBoundaries) {
                for(BudgetPersonnelDetails periodPersonnelDetail: periodPersonnelDetails) {
                    if(budgetPeriod.getBudgetPeriod() == periodPersonnelDetail.getBudgetPeriod()) {
                        validBoundaries = isDateBefore(periodPersonnelDetail.getStartDate(), budgetPeriod.getStartDate(), "startDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                        if(validBoundaries) {
                            validBoundaries = isDateAfter(periodPersonnelDetail.getStartDate(), budgetPeriod.getEndDate(), "startDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                        }
                        if(validBoundaries) {
                            validBoundaries = isDateAfter(periodPersonnelDetail.getEndDate(), budgetPeriod.getEndDate(), "endDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                        }
                        if(validBoundaries) {
                            validBoundaries = isDateBefore(periodPersonnelDetail.getEndDate(), budgetPeriod.getStartDate(), "endDate", KeyConstants.ERROR_LINE_ITEM_DATE_DOESNOTMATCH, errorMap, dateParams, validBoundaries);
                        }
                        break;
                    }
                }
            }
            errorMap.removeFromErrorPath("document.budgetPeriods[" + index + "]");
            /* check cal amounts */
        }
        return validBoundaries;
        
    }

    private boolean isValidBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod newBudgetPeriod) {
        setProjectStartDate(budgetDocument.getStartDate());
        setProjectEndDate(budgetDocument.getEndDate());
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        boolean validBudgetPeriod = true;
        Date previousPeriodStartDate = null;
        Date previousPeriodEndDate = null;
        Date periodStartDate = null;
        Date periodEndDate = null;
        int index = 0;
        ErrorMap errorMap = GlobalVariables.getErrorMap();

        Date newPeriodStartDate = null;
        Date newPeriodEndDate = null;
        boolean insertBudgetPeriod = false;
        
        /* check new budget period */
        if (newBudgetPeriod != null) {
            newPeriodStartDate = newBudgetPeriod.getStartDate();
            newPeriodEndDate = newBudgetPeriod.getEndDate();
            errorMap.addToErrorPath(NEW_BUDGET_PERIOD);
            if(newPeriodStartDate == null) {
                errorMap.putError("startDate", KeyConstants.ERROR_PERIOD_START_REQUIRED);
                validBudgetPeriod = false;
            }
            if(newPeriodEndDate == null) {
                errorMap.putError("endDate", KeyConstants.ERROR_PERIOD_END_REQUIRED);
                validBudgetPeriod = false;
            }
            insertBudgetPeriod = validBudgetPeriod;
            errorMap.removeFromErrorPath(NEW_BUDGET_PERIOD);
        }
        
        int totalBudgetPeriods = budgetPeriods.size() - 1;
        /* verify existing budget periods */
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            errorMap.addToErrorPath("document.budgetPeriods[" + index + "]");
            periodStartDate = budgetPeriod.getStartDate();
            periodEndDate = budgetPeriod.getEndDate();
            Date validDateBefore;
            boolean isDateNull = false;
            String[] dateParams = {index+1+""};
            setErrorParameter(dateParams);
            if(periodStartDate == null) {
                errorMap.putError("startDate", KeyConstants.ERROR_PERIOD_START_REQUIRED);
                validBudgetPeriod = false;
                isDateNull = true;
            }
            if(periodEndDate == null) {
                errorMap.putError("endDate", KeyConstants.ERROR_PERIOD_END_REQUIRED);
                validBudgetPeriod = false;
                isDateNull = true;
            }
            /* if date not null, validate budget period */
            if(!isDateNull){
                /* check first record */
                if(previousPeriodStartDate == null) {
                    validDateBefore = projectStartDate;
                }else {
                    validDateBefore = previousPeriodEndDate;
                }
                int dateCompareValue = compareDate(periodStartDate, periodEndDate, previousPeriodEndDate);
                if(dateCompareValue > 0) {
                    saveErrors(dateCompareValue, errorMap);
                    validBudgetPeriod = false;
                }
                errorMap.removeFromErrorPath("document.budgetPeriods[" + index + "]");
                if(insertBudgetPeriod) {
                    dateCompareValue = 0;
                    errorMap.addToErrorPath(NEW_BUDGET_PERIOD);
                    /* check if new period start date is before current period start date */
                    if(newPeriodStartDate.before(periodStartDate) || (index == totalBudgetPeriods && newPeriodStartDate.after(periodEndDate))) {
                        boolean lastRecord = false;
                        int periodNum = index;
                        if(index == totalBudgetPeriods) {
                            lastRecord = true;
                            periodNum = index + 1;
                        }
                        String[] newPeriodDateParams = {periodNum+"", periodNum+1+""};
                        setErrorParameter(newPeriodDateParams);
                        /* check new budget period */
                        if(newPeriodStartDate.before(getProjectStartDate())) {
                            dateCompareValue = 18;
                        }else if (newPeriodStartDate.after(getProjectEndDate())) {
                            dateCompareValue = 40;
                        }else if (newPeriodEndDate.after(getProjectEndDate())) {
                            dateCompareValue = 41;
                        }else if(newPeriodStartDate.before(validDateBefore)) {
                            dateCompareValue = 28;
                        }else if ((index < totalBudgetPeriods) && newPeriodEndDate.after(periodStartDate)) {
                            if(!lastRecord) {
                                dateCompareValue = 29;
                            }else {
                                dateCompareValue = 30;
                            }
                        }
                        if(dateCompareValue > 0) {
                            saveErrors(dateCompareValue, errorMap);
                            validBudgetPeriod = false;
                        }
                        insertBudgetPeriod = false;
                        newBudgetPeriod.setBudgetPeriod(periodNum+1);
                    }
                    errorMap.removeFromErrorPath(NEW_BUDGET_PERIOD);
                }
            }
            previousPeriodStartDate = budgetPeriod.getStartDate();
            previousPeriodEndDate = budgetPeriod.getEndDate();
            index++;
        }
        return validBudgetPeriod;
    }
    
    private void saveErrors(int dateCompareValue, ErrorMap errorMap) {
        String errorKey = null;
        String errorProperty = null;
        switch(dateCompareValue) {
            case 1: // period start date after project end date
                errorProperty = "startDate";
                errorKey = KeyConstants.ERROR_PERIOD_START_BEFORE_PROJECT_START;
                break;
            case 2: // period start date after period end date
                errorProperty = "startDate";
                errorKey = KeyConstants.ERROR_PERIOD_START_AFTER_PERIOD_END;
                break;
            case 3: // period start date before project start date
                errorProperty = "startDate";
                errorKey = KeyConstants.ERROR_PERIOD_START_AFTER_PROJECT_END;
                break;
            case 4: // period end date before project start date 
                errorProperty = "endDate";
                errorKey = KeyConstants.ERROR_PERIOD_END_BEFORE_PROJECT_START;
                break;
            case 5: // period end date after project end date
                errorProperty = "endDate";
                errorKey = KeyConstants.ERROR_PERIOD_END_AFTER_PROJECT_END;
                break;
            case 6: // period start date before previous period end date
                errorProperty = "startDate";
                errorKey = KeyConstants.ERROR_PERIOD_START_BEFORE_PREVIOUS_START;
                break;
            case 7: // period end date before previous period end date
                errorProperty = "endDate";
                errorKey = KeyConstants.ERROR_PERIOD_END_BEFORE_PREVIOUS_END;
                break;
            case 18: // new budget period start date less than project start date
                errorProperty = "startDate";
                errorKey = KeyConstants.ERROR_PERIOD_START_BEFORE_PROJECT_START;
                break;
            case 28: // new budget period start date is before previous period end date
                errorProperty = "startDate";
                errorKey = KeyConstants.ERROR_NEW_PERIOD_VALID;
                break;
            case 29: // new budget period end date is after next period start date
                errorProperty = "endDate";
                errorKey = KeyConstants.ERROR_NEW_PERIOD_VALID;;
                break;
            case 30: // new budget period end date is after next period start date
                errorProperty = "endDate";
                errorKey = KeyConstants.ERROR_NEW_PERIOD_PROJECT_END;;
                break;
            case 40: // new budget period start date is after project/proposal end date
                errorProperty = "startDate";
                errorKey = KeyConstants.ERROR_NEW_PERIOD_START_AFTER_PROJECT_END;
                break;
            case 41: // new budget period end date is after project/proposal end date
                errorProperty = "endDate";
                errorKey = KeyConstants.ERROR_NEW_PERIOD_END_DATE;
                break;
        }
        errorMap.putError(errorProperty, errorKey, getErrorParameter());
    }
    
    private int compareDate(Date periodStartDate, Date periodEndDate, Date previousPeriodEndDate){
        int returnValue = 0;
        if(periodStartDate.after(getProjectEndDate())) {
            returnValue = 1;
        }else if(periodStartDate.after(periodEndDate)) {
            returnValue = 2;
        }else if(periodStartDate.before(getProjectStartDate())) {
            returnValue = 3;
        }else if(periodEndDate.before(getProjectStartDate())) {
            returnValue = 4;
        }else if(periodEndDate.after(getProjectEndDate())) {
            returnValue = 5;
        }else if(previousPeriodEndDate != null && periodStartDate.before(previousPeriodEndDate)) {
            returnValue = 6;
        }else if(previousPeriodEndDate != null && periodEndDate.before(previousPeriodEndDate)) {
            returnValue = 7;
        }
        return returnValue;
    }

    private boolean isDateAfter(Date startDate, Date endDate, String errorProperty, String errorKey, ErrorMap errorMap, String[] dateParams, boolean budgetPeriodsValid) {
        boolean isValid = budgetPeriodsValid;
        if(startDate.after(endDate)) {
            errorMap.putError(errorProperty, errorKey, dateParams);
            isValid = false;
        }
        return isValid;
    }
    
    private boolean isDateBefore(Date startDate, Date endDate,  String errorProperty, String errorKey, ErrorMap errorMap,  String[] dateParams, boolean budgetPeriodsValid) {
        boolean isValid = budgetPeriodsValid;
        if(startDate.before(endDate)) {
            errorMap.putError(errorProperty, errorKey, dateParams);
            isValid = false;
        }
        return isValid;
    }
    
    private BudgetSummaryService getBudgetSummaryService() {
        return getService(BudgetSummaryService.class);
    }

    private void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
    
    private Date getProjectStartDate() {
        return projectStartDate;
    }

    private Date getProjectEndDate() {
        return projectEndDate;
    }

    private void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String[] getErrorParameter() {
        return errorParameter;
    }

    public void setErrorParameter(String[] errorParameter) {
        this.errorParameter = errorParameter;
    }
}

