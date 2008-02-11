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
        }else if(!getBudgetSummaryService().budgetLineItemExists(budgetPeriodNumber)) {
            errorMap.addToErrorPath("noPanel");
            rulePassed = false;
            errorMap.putError("noFocus", KeyConstants.ERROR_PERIOD_LINE_ITEM_DOESNOT_EXIST);
        }else if(getBudgetSummaryService().budgetLineItemExists(budgetPeriodNumber+1)) {
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

        if(getBudgetSummaryService().budgetLineItemExists(budgetPeriodNumber)) {
            errorMap.addToErrorPath("document.budgetPeriods[" + budgetPeriodNumber + "]");
            rulePassed = false;
            errorMap.putError("startDate", KeyConstants.ERROR_LINE_ITEM_EXISTS);
            errorMap.removeFromErrorPath("document.budgetPeriods[" + budgetPeriodNumber + "]");
        }
        return rulePassed;
    }

    private boolean isValidBudgetPeriodBoundaries(BudgetDocument budgetDocument) {
        boolean validBoundaries = true;
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        List<BudgetLineItem> budgetLineItems = budgetDocument.getBudgetLineItems();
        List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetDocument.getBudgetPersonnelDetailsList();
        //boolean validBudgetPeriod = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            String[] dateParams = {budgetPeriod.getBudgetPeriod()+""};
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
            }
            /* check personnel line items */
            if(validBoundaries) {
                for(BudgetPersonnelDetails periodPersonnelDetail: periodPersonnelDetails) {
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
                }
            }
            errorMap.removeFromErrorPath("document.budgetPeriods[" + index + "]");
            /* check cal amounts */
        }
        return validBoundaries;
        
    }

    private boolean isValidBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod newBudgetPeriod) {
        Date projectStartDate = budgetDocument.getStartDate();
        Date projectEndDate = budgetDocument.getEndDate();
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
            
            if(!isDateNull){
                /* check first record */
                if(previousPeriodStartDate == null) {
                    /* check entered budget period start date is before project/proposal start date */
                    validDateBefore = projectStartDate;
                    validBudgetPeriod = isDateBefore(periodStartDate, projectStartDate, "startDate", KeyConstants.ERROR_PERIOD_START_BEFORE_PROJECT_START, errorMap, dateParams, validBudgetPeriod);
                    /* check entered budget period start date is after project/proposal end date */ 
                    validBudgetPeriod = isDateAfter(periodStartDate, projectEndDate, "startDate", KeyConstants.ERROR_PERIOD_START_AFTER_PROJECT_END, errorMap, dateParams, validBudgetPeriod);
                    /* check entered budget period end date is after project/proposal end date */ 
                    validBudgetPeriod = isDateAfter(periodEndDate, projectEndDate, "endDate", KeyConstants.ERROR_PERIOD_END_AFTER_PROJECT_END, errorMap, dateParams, validBudgetPeriod);
                }else {
                    /* check entered budget period start date is before previous period end date */ 
                    validDateBefore = previousPeriodEndDate;
                    validBudgetPeriod = isDateBefore(periodStartDate, previousPeriodEndDate, "startDate", KeyConstants.ERROR_PERIOD_START_BEFORE_PREVIOUS_START, errorMap, dateParams, validBudgetPeriod);
                    /* check entered budget period start date is after project/proposal end date */ 
                    validBudgetPeriod = isDateAfter(periodStartDate, projectEndDate, "startDate", KeyConstants.ERROR_PERIOD_START_AFTER_PROJECT_END, errorMap, dateParams, validBudgetPeriod);
                    /* check entered budget period end date is after project/proposal end date */ 
                    validBudgetPeriod = isDateAfter(periodEndDate, projectEndDate, "endDate", KeyConstants.ERROR_PERIOD_END_AFTER_PROJECT_END, errorMap, dateParams, validBudgetPeriod);
                }
                errorMap.removeFromErrorPath("document.budgetPeriods[" + index + "]");
                if(insertBudgetPeriod) {
                    errorMap.addToErrorPath(NEW_BUDGET_PERIOD);
                    /* check if new period start date is before current period start date */
                    if(newPeriodStartDate.before(periodStartDate) || (index == totalBudgetPeriods && newPeriodStartDate.after(periodEndDate))) {
                        String errorKey = KeyConstants.ERROR_NEW_PERIOD_VALID;
                        int periodNum = index;
                        if(index == totalBudgetPeriods) {
                            errorKey = KeyConstants.ERROR_NEW_PERIOD_PROJECT_END;
                            periodNum = index + 1;
                            //periodStartDate = periodEndDate;
                        }
                        String[] newPeriodDateParams = {periodNum+"", periodNum+1+""};
                        /* check new budget period start date is before previous period end date */ 
                        validBudgetPeriod = isDateBefore(newPeriodStartDate, validDateBefore, "startDate", KeyConstants.ERROR_NEW_PERIOD_VALID, errorMap, newPeriodDateParams, validBudgetPeriod);
                        if(index < totalBudgetPeriods) {
                            /* check new budget period end date is before next period start date */
                            validBudgetPeriod = isDateAfter(newPeriodEndDate, periodStartDate, "endDate", errorKey, errorMap, newPeriodDateParams, validBudgetPeriod);
                        }
                        /* check entered budget period start date is after project/proposal end date */ 
                        validBudgetPeriod = isDateAfter(newPeriodStartDate, projectEndDate, "startDate", KeyConstants.ERROR_NEW_PERIOD_START_AFTER_PROJECT_END, errorMap, dateParams, validBudgetPeriod);
                        /* check entered budget period end date is after project/proposal end date */ 
                        validBudgetPeriod = isDateAfter(newPeriodEndDate, projectEndDate, "endDate", KeyConstants.ERROR_NEW_PERIOD_END_DATE, errorMap, dateParams, validBudgetPeriod);
                        insertBudgetPeriod = false;
                        //System.out.println("****** setPeriodIndex " + periodNum);
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

    private boolean insertBudgetPeriod(Date periodStartDate, Date periodEndDate, BudgetPeriod newBudgetPeriod) {
        boolean insertNewBudgetPeriod = false;
        return insertNewBudgetPeriod;
    }
    
    private boolean isDateAfter(Date startDate, Date endDate, String errorProperty, String errorKey, ErrorMap errorMap, String[] dateParams, boolean budgetPeriodsValid) {
        boolean isValid = budgetPeriodsValid;
        //String[] dateParams = {position+1+""};
        
        if(startDate.after(endDate)) {
            errorMap.putError(errorProperty, errorKey, dateParams);
            isValid = false;
        }
        return isValid;
    }

    private boolean isDateBefore(Date startDate, Date endDate,  String errorProperty, String errorKey, ErrorMap errorMap,  String[] dateParams, boolean budgetPeriodsValid) {
        boolean isValid = budgetPeriodsValid;
        //String[] dateParams = {position+1+""};
        if(startDate.before(endDate)) {
            errorMap.putError(errorProperty, errorKey, dateParams);
            isValid = false;
        }
        return isValid;
    }

    private BudgetSummaryService getBudgetSummaryService() {
        return getService(BudgetSummaryService.class);
    }

}

