/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.budget.impl.period;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.kuali.coeus.common.budget.framework.core.AwardBudgetSaveEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.AddBudgetPeriodAndTotalEvent;
import org.kuali.coeus.common.budget.framework.period.AddBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.period.BudgetSummaryErrorConstants;
import org.kuali.coeus.common.budget.framework.period.DeleteBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.SaveBudgetPeriodAndTotalEvent;
import org.kuali.coeus.common.budget.framework.period.GenerateBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@KcBusinessRule("budgetPeriodRule")
public class BudgetPeriodRule {
    private static final Log LOG = LogFactory.getLog(BudgetPeriodRule.class);

    private static final String NEW_BUDGET_PERIOD = "newBudgetPeriod";
    private static final String DEFAULT_ERROR_PATH_PREFIX = "document.budgetPeriods";
    private static final String DEFAULT_COST_LIMIT_ERROR_PATH_PREFIX = "document.budget.budgetPeriods";
    private static final String DEFAULT_NEW_BUDGET_PERIOD_ERROR_PATH_PREFIX = "newBudgetPeriod.error";
    private static final String BUDGET_PERIOD_ERROR_PATH_PREFIX = "budget.budgetPeriods";

    private ParameterService parameterService;

    private GlobalVariableService globalVariableService;

    @KcEventMethod
    public boolean processAddBudgetPeriodBusinessRules(AddBudgetPeriodEvent event) {
        Budget budget = event.getBudget();
        BudgetPeriod newBudgetPeriod = event.getBudgetPeriod();
        
        boolean rulePassed = true;

        if (!isValidBudgetPeriod(budget, DEFAULT_ERROR_PATH_PREFIX)) {
            rulePassed = false;
        }

        if(rulePassed && (newBudgetPeriod != null)) {
        	rulePassed = isValidNewBudgetPeriod(budget, newBudgetPeriod, NEW_BUDGET_PERIOD);
        }
        
        if (rulePassed) {
            rulePassed = isValidToInsert(budget, newBudgetPeriod, DEFAULT_NEW_BUDGET_PERIOD_ERROR_PATH_PREFIX);
        }
        
        return rulePassed;
    }

    @KcEventMethod
    public boolean processAddBudgetPeriodBusinessRules(AddBudgetPeriodAndTotalEvent event) {
        Budget budget = event.getBudget();
        BudgetPeriod newBudgetPeriod = event.getBudgetPeriod();
        boolean rulePassed = isValidNewBudgetPeriod(budget, newBudgetPeriod, event.getErrorPath());
        
    	if(rulePassed) {
            rulePassed = isValidToInsert(budget, newBudgetPeriod, event.getErrorPath().concat("startDate"));
    	}
        return rulePassed;
    }
    
    @KcEventMethod
    public boolean processSaveBudgetPeriodBusinessRules(AwardBudgetSaveEvent event) {
        Budget budget = event.getBudget();
        boolean rulePassed = true;

        if (!isValidBudgetPeriod(budget, DEFAULT_ERROR_PATH_PREFIX)) {
            rulePassed = false;
        }else if(!isValidBudgetPeriodBoundaries(budget, DEFAULT_ERROR_PATH_PREFIX)){
            rulePassed = false;
        }
        if (!budget.isProposalBudget()) {
            rulePassed &= isValidBudgetPeriodCostLimit(budget, DEFAULT_COST_LIMIT_ERROR_PATH_PREFIX);
        }

        return rulePassed;
    }

    @KcEventMethod
    public boolean processSaveBudgetPeriodBusinessRules(SaveBudgetPeriodAndTotalEvent event) {
        Budget budget = event.getBudget();
        boolean rulePassed = true;

        if (!isValidBudgetPeriod(budget, event.getErrorPath())) {
            rulePassed = false;
        } else if (!isValidBudgetPeriodBoundaries(budget, event.getErrorPath())){
            rulePassed = false;
        }
        if (!budget.isProposalBudget()) {
            rulePassed &= isValidBudgetPeriodCostLimit(budget, event.getErrorPath());
        }

        return rulePassed;
    }

    @KcEventMethod
    public boolean processSaveBudgetPeriodBusinessRules(BudgetSaveEvent event) {
        Budget budget = event.getBudget();
        boolean rulePassed = true;

        if (!isValidBudgetPeriod(budget, BUDGET_PERIOD_ERROR_PATH_PREFIX)) {
            rulePassed = false;
        } else if (!isValidBudgetPeriodBoundaries(budget, BUDGET_PERIOD_ERROR_PATH_PREFIX)){
            rulePassed = false;
        }
        if (!budget.isProposalBudget()) {
            rulePassed &= isValidBudgetPeriodCostLimit(budget, event.getErrorPath());
        } else {
            rulePassed &= doBudgetPeriodsCoverProposal(budget);
        }

        return rulePassed;
    }


    @KcEventMethod
    public boolean processGenerateBudgetPeriodBusinessRules(GenerateBudgetPeriodEvent event) {
        Budget document = event.getBudget();
        BudgetPeriod newBudgetPeriod = event.getBudgetPeriod();
        MessageMap errorMap = getGlobalVariableService().getMessageMap();
        boolean rulePassed = true;
        int budgetPeriodNumber = 0;
        
        //1. Check budget periods are valid
        //2. Check for valid periods in line item
        //3. Look for line item in period 1 (needed to generate budget periods)
        //4. Check for other periods to populate
        //5. Make sure other periods have no pre-existing line items
        if (!isValidBudgetPeriod(document, DEFAULT_ERROR_PATH_PREFIX)) {
            rulePassed = false;
        } else if (newBudgetPeriod != null && !isValidNewBudgetPeriod(document, newBudgetPeriod, NEW_BUDGET_PERIOD)) {
            rulePassed = false;
        } else if (!isValidBudgetPeriodBoundaries(document, DEFAULT_ERROR_PATH_PREFIX)){
            rulePassed = false;
        } else if(!getBudgetSummaryService().budgetLineItemExists(document, budgetPeriodNumber)) {
            errorMap.addToErrorPath(NEW_BUDGET_PERIOD);
            rulePassed = false;
            saveErrors("ERROR_PERIOD_LINE_ITEM_DOESNOT_EXIST", errorMap);
        } else if (document.getBudgetPeriods().size() <= (budgetPeriodNumber+1)) {
            errorMap.addToErrorPath(NEW_BUDGET_PERIOD);
            rulePassed = false;
            saveErrors("ERROR_NO_FUTURE_PERIOD_TO_GENERATE", errorMap);
        } else {
            String errorParam = "";
            for (int i=budgetPeriodNumber+1; i<document.getBudgetPeriods().size(); i++) {
                if (getBudgetSummaryService().budgetLineItemExists(document, i)) {
                    errorParam += ("" + (i+1) + ", ");
                }
            }
            if (errorParam.length() > 0) {
                errorMap.addToErrorPath(NEW_BUDGET_PERIOD);
                rulePassed = false;
                errorParam = errorParam.substring(0, errorParam.length()-2);
                saveErrors("ERROR_GENERATE_PERIOD", errorMap, errorParam);
            }
        }
        errorMap.removeFromErrorPath(NEW_BUDGET_PERIOD);
        return rulePassed;
    }

    @KcEventMethod
    public boolean processDeleteBudgetPeriodBusinessRules(DeleteBudgetPeriodEvent event) {
        Budget budget = event.getBudget();
        int budgetPeriodNumber = event.getBudgetPeriodNumber();
        MessageMap errorMap = getGlobalVariableService().getMessageMap();
        boolean rulePassed = true;

        if(getBudgetSummaryService().budgetLineItemExists(budget, budgetPeriodNumber)) {
            errorMap.addToErrorPath("document.budgetPeriods[" + budgetPeriodNumber + "]");
            rulePassed = false;
            saveErrors("ERROR_LINE_ITEM_EXISTS", errorMap);
            errorMap.removeFromErrorPath("document.budgetPeriods[" + budgetPeriodNumber + "]");
        }
        return rulePassed;
    }

    private Interval createIntervalFromPeriod(BudgetPeriod budgetPeriod) {
        DateTime startDate = new DateTime(budgetPeriod.getStartDate().getTime());
        //add one to end date for joda time interval comparison
        DateTime endDate = new DateTime(budgetPeriod.getEndDate().getTime()).plusDays(1);
        return new Interval(startDate, endDate);
    }

    protected boolean doBudgetPeriodsCoverProposal(Budget budget) {
        if (CollectionUtils.isEmpty(budget.getBudgetPeriods())) {
            return false;
        }
        List<BudgetPeriod> sortedBudgetPeriods = budget.getBudgetPeriods()
                .stream()
                .sorted((period1, period2) -> period1.getStartDate().compareTo(period2.getStartDate())).collect(Collectors.toList());

        int nextIndex = 1;
        for (BudgetPeriod currentBudgetPeriod : sortedBudgetPeriods) {
            if (nextIndex < budget.getBudgetPeriods().size()) {
                BudgetPeriod nextBudgetPeriod = sortedBudgetPeriods.get(nextIndex);
                Interval currentPeriodInterval =createIntervalFromPeriod(currentBudgetPeriod);
                Interval nextPeriodInterval = createIntervalFromPeriod(nextBudgetPeriod);
                if (!currentPeriodInterval.abuts(nextPeriodInterval)) {
                    saveErrors("ERROR_PERIOD_GAPS", getGlobalVariableService().getMessageMap());
                    return false;
                }
            }
            nextIndex++;
        }

        Interval budgetInterval = new Interval(sortedBudgetPeriods.get(0).getStartDate().getTime(),
                sortedBudgetPeriods.get(sortedBudgetPeriods.size() - 1).getEndDate().getTime());

        Interval proposalInterval = new Interval(budget.getBudgetParent().getRequestedStartDateInitial().getTime(),
                budget.getBudgetParent().getRequestedEndDateInitial().getTime());

        if (!budgetInterval.equals(proposalInterval)) {
            saveErrors("ERROR_PERIOD_GAPS", getGlobalVariableService().getMessageMap());
            return false;
        }
        return true;
    }

    private boolean isValidBudgetPeriodBoundaries(Budget budget, String errorPathPrefix) {
        boolean validBoundaries = true;
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        MessageMap errorMap = getGlobalVariableService().getMessageMap();
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            String[] dateParams = {budgetPeriod.getBudgetPeriod()+""};
            /* get all line items for each budget period */
            Collection<BudgetLineItem> periodLineItems = new ArrayList();
            Collection<BudgetPersonnelDetails> periodPersonnelDetails = new ArrayList();
            /* filter by budget period */
            Integer budgetPeriodNumber = budgetPeriod.getBudgetPeriod();
            int index = budgetPeriodNumber - 1;
            errorMap.addToErrorPath(errorPathPrefix+"[" + index + "]");
            /* check line items */
            periodLineItems = budgetPeriod.getBudgetLineItems(); 
            BUDGET_LINEITEM_LOOP:
            for(BudgetLineItem periodLineItem: periodLineItems) {
                if(budgetPeriod.getBudgetPeriod() == periodLineItem.getBudgetPeriod()) {
                    if((periodLineItem.getStartDate().before(budgetPeriod.getStartDate())) || 
                    (periodLineItem.getStartDate().after(budgetPeriod.getEndDate())) || 
                    (periodLineItem.getEndDate().after(budgetPeriod.getEndDate())) ||
                    (periodLineItem.getEndDate().before(budgetPeriod.getStartDate()))){
                        saveErrors("ERROR_LINE_ITEM_DATE_DOESNOTMATCH", errorMap, dateParams);
                        validBoundaries = false;
                        break;
                    }
                }
                /* check personnel line items */
                periodPersonnelDetails = periodLineItem.getBudgetPersonnelDetailsList();
                for(BudgetPersonnelDetails periodPersonnelDetail: periodPersonnelDetails) {
                    if(budgetPeriod.getBudgetPeriod() == periodPersonnelDetail.getBudgetPeriod()) {
                        if((periodPersonnelDetail.getStartDate().before(budgetPeriod.getStartDate())) || 
                                (periodPersonnelDetail.getStartDate().after(budgetPeriod.getEndDate())) || 
                                (periodPersonnelDetail.getEndDate().after(budgetPeriod.getEndDate())) ||
                                (periodPersonnelDetail.getEndDate().before(budgetPeriod.getStartDate()))){
                                    saveErrors("ERROR_LINE_ITEM_DATE_DOESNOTMATCH", errorMap, dateParams);
                                    validBoundaries = false;
                                    break BUDGET_LINEITEM_LOOP;
                                }
                    }
                }
            }
            errorMap.removeFromErrorPath(errorPathPrefix+"[" + index + "]");
        }
        return validBoundaries;
        
    }

    private boolean isValidBudgetPeriodCostLimit(Budget budget, String errorPathPrefix) {
        boolean valid = true;
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        MessageMap errorMap = getGlobalVariableService().getMessageMap();
        int i = 0;
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            if (((AwardBudgetExt)budget).getObligatedTotal().isPositive() &&
            		budgetPeriod.getTotalCostLimit().isGreaterThan(((AwardBudgetExt)budget).getObligatedTotal())) {
                getGlobalVariableService().getMessageMap().putError(errorPathPrefix+"["+ i +"].totalCostLimit", 
                        KeyConstants.ERROR_PERIOD_COST_LIMIT_EXCEED_OBLIGATED_TOTAL);
               valid = false;
            }
            i++;
        }
        return valid;
        
    }

    /* check new budget period */
    private boolean isValidNewBudgetPeriod(Budget budget, BudgetPeriod newBudgetPeriod, String errorPathPrefix) {
        MessageMap errorMap = getGlobalVariableService().getMessageMap();
        boolean validNewBudgetPeriod = true;
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        Date previousPeriodStartDate = null;
        Date previousPeriodEndDate = null;
        Date periodStartDate = null;
        Date periodEndDate = null;
        Date newPeriodStartDate = null;
        Date newPeriodEndDate = null;
        int index = 0;

        /* check new budget period */
        newPeriodStartDate = newBudgetPeriod.getStartDate();
        newPeriodEndDate = newBudgetPeriod.getEndDate();
        errorMap.addToErrorPath(errorPathPrefix);
        if(newPeriodStartDate == null) {
            saveErrors("ERROR_PERIOD_START_REQUIRED", errorMap);
            validNewBudgetPeriod = false;
        }
        if(newPeriodEndDate == null) {
            saveErrors("ERROR_PERIOD_END_REQUIRED", errorMap);
            validNewBudgetPeriod = false;
        }
        errorMap.removeFromErrorPath(errorPathPrefix);

        if (CollectionUtils.isEmpty(budgetPeriods)){
            newBudgetPeriod.setBudgetPeriod(1);
        }

        /* if dates are valid, check further where we can insert this new date */
        if(validNewBudgetPeriod) {
            int totalBudgetPeriods = budgetPeriods.size() - 1;
            errorMap.addToErrorPath(errorPathPrefix);
            for(BudgetPeriod budgetPeriod: budgetPeriods) {
                Date validDateBefore;
                periodStartDate = budgetPeriod.getStartDate();
                periodEndDate = budgetPeriod.getEndDate();
                String dateCompareValue = null;
                /* check first record */
                if(previousPeriodStartDate == null) {
                    validDateBefore = budget.getStartDate();
                }else {
                    validDateBefore = previousPeriodEndDate;
                }
                /* check if entered new period already exists in budget periods list */
                int periodNum = index;
                String[] newPeriodDateParams = {periodNum+"", periodNum+1+""};
                String invalidErrorMessage = null;
                if(index == 0 || index == totalBudgetPeriods) {
                    invalidErrorMessage = "ERROR_NEW_PERIOD_INVALID";
                }else {
                    invalidErrorMessage = "ERROR_NEW_PERIOD_VALID";
                }
                if((newPeriodStartDate.compareTo(periodStartDate) == 0) || (newPeriodEndDate.compareTo(periodEndDate) == 0)) {
                    saveErrors(invalidErrorMessage, errorMap, newPeriodDateParams);
                    validNewBudgetPeriod = false;
                    break;
                }else if(newPeriodStartDate.before(periodStartDate) || (index == totalBudgetPeriods && newPeriodStartDate.after(periodEndDate))) {     
                    /* check if new period start date is before current period start date */
                    boolean lastRecord = false;
                    if(index == totalBudgetPeriods) {
                        lastRecord = true;
                        if(newPeriodStartDate.after(periodEndDate)) {
                            periodNum = index + 1;
                        }
                    }
                    /* check new budget period */
                    if(newPeriodStartDate.before(budget.getStartDate())) {
                        dateCompareValue = "ERROR_PERIOD_START_BEFORE_PROJECT_START"; 
                    }else if (newPeriodStartDate.after(budget.getEndDate())) {
                        dateCompareValue = "ERROR_NEW_PERIOD_START_AFTER_PROJECT_END"; 
                    }else if (newPeriodEndDate.after(budget.getEndDate())) {
                        dateCompareValue = "ERROR_NEW_PERIOD_END_DATE"; 
                    }else if(newPeriodStartDate.before(validDateBefore)) {
                        dateCompareValue = invalidErrorMessage; 
                    }else if ((index < totalBudgetPeriods) && newPeriodEndDate.after(periodStartDate)) {
                        if(!lastRecord) {
                            dateCompareValue = invalidErrorMessage; 
                        }else {
                            dateCompareValue = "ERROR_NEW_PERIOD_PROJECT_END"; 
                        }
                    }
                    if(dateCompareValue != null) {
                        saveErrors(dateCompareValue, errorMap, newPeriodDateParams);
                        validNewBudgetPeriod = false;
                    }else {
                        newBudgetPeriod.setBudgetPeriod(periodNum+1);
                    }
                    break;
                }else if(newPeriodStartDate.compareTo(periodEndDate) <= 0) {
                    dateCompareValue = "ERROR_NEW_PERIOD_START_BEFORE_PREVIOUS_END";
                    saveErrors(dateCompareValue, errorMap, newPeriodDateParams);
                    validNewBudgetPeriod = false;
                    break;
                }
                previousPeriodStartDate = budgetPeriod.getStartDate();
                previousPeriodEndDate = budgetPeriod.getEndDate();
                index++;
            }
            errorMap.removeFromErrorPath(errorPathPrefix);
        }
        return validNewBudgetPeriod;
    }

    /* check existing budget periods */
    private boolean isValidBudgetPeriod(Budget budget, String errorPathPrefix) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        boolean validBudgetPeriod = true;
        Date previousPeriodStartDate = null;
        Date previousPeriodEndDate = null;
        Date periodStartDate = null;
        Date periodEndDate = null;
        int index = 0;
        MessageMap errorMap = getGlobalVariableService().getMessageMap();

        /* verify existing budget periods */
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            errorMap.addToErrorPath(errorPathPrefix +"[" + index + "]");
            periodStartDate = budgetPeriod.getStartDate();
            periodEndDate = budgetPeriod.getEndDate();
            Date validDateBefore;
            boolean isDateNull = false;
            String[] dateParams = {index+1+""};
            /* check for changes - start date is null */
            if(periodStartDate == null) {
                saveErrors("ERROR_PERIOD_START_REQUIRED", errorMap, dateParams);
                validBudgetPeriod = false;
                isDateNull = true;
            }
            /* check for changes - end date is null */
            if(periodEndDate == null) {
                saveErrors("ERROR_PERIOD_END_REQUIRED", errorMap, dateParams);
                validBudgetPeriod = false;
                isDateNull = true;
            }
            /* if date not null, validate budget period */
            if(!isDateNull){
                /* check first record */
                if(previousPeriodStartDate == null) {
                    validDateBefore = budget.getStartDate();
                }else {
                    validDateBefore = previousPeriodEndDate;
                }
                String dateCompareValue = compareDate(budget, periodStartDate, periodEndDate, previousPeriodEndDate);
                if(dateCompareValue != null) {
                    saveErrors(dateCompareValue, errorMap, dateParams);
                    validBudgetPeriod = false;
                }
                errorMap.removeFromErrorPath(errorPathPrefix +"[" + index + "]");
            }
            previousPeriodStartDate = budgetPeriod.getStartDate();
            previousPeriodEndDate = budgetPeriod.getEndDate();
            index++;
        }
        
        return validBudgetPeriod;
    }
    
    private void saveErrors(String errorValue, MessageMap errorMap, String...parameters) {
        BudgetSummaryErrorConstants budgetSummaryErrorConstants =  BudgetSummaryErrorConstants.valueOf(errorValue);
        String errorKey = budgetSummaryErrorConstants.errorKey();
        String errorProperty = budgetSummaryErrorConstants.errorProperty();
        errorMap.putError(errorProperty, errorKey, parameters);
    }
    
    private String compareDate(Budget budget, Date periodStartDate, Date periodEndDate, Date previousPeriodEndDate) {
        String returnErrorValue = null;
        LOG.info("prd st dt " + periodStartDate.getTime() + periodEndDate.getTime() + budget.getStartDate().getTime()
                + budget.getEndDate().getTime());
        Date budgetEndDate = new Date(budget.getBudgetEndDate().getTime());
        Date budgetStartDate = new Date(budget.getBudgetStartDate().getTime());
        if (periodStartDate.after(budgetEndDate)) {
            LOG.info("ERROR_PERIOD_START_AFTER_PROJECT_END" + periodStartDate + budget.getEndDate());
            returnErrorValue = "ERROR_PERIOD_START_AFTER_PROJECT_END";
        } else if (periodStartDate.before(budgetStartDate)) {
            LOG.info("ERROR_PERIOD_START_BEFORE_PROJECT_START" + periodStartDate + budget.getStartDate());
            returnErrorValue = "ERROR_PERIOD_START_BEFORE_PROJECT_START";
        } else if (periodEndDate.before(budget.getStartDate())) {
            LOG.info("ERROR_PERIOD_END_BEFORE_PROJECT_START" + periodEndDate + budget.getStartDate());
            returnErrorValue = "ERROR_PERIOD_END_BEFORE_PROJECT_START";
        } else if (periodEndDate.after(budgetEndDate)) {
            LOG.info("ERROR_PERIOD_END_AFTER_PROJECT_END" + periodEndDate + budget.getEndDate());
            returnErrorValue = "ERROR_PERIOD_END_AFTER_PROJECT_END";
        } else if (periodStartDate.after(periodEndDate)) {
            LOG.info("ERROR_PERIOD_START_AFTER_PERIOD_END" + periodStartDate + periodEndDate);
            returnErrorValue = "ERROR_PERIOD_START_AFTER_PERIOD_END";
        } else if (previousPeriodEndDate != null && !periodStartDate.after(previousPeriodEndDate)) {
            LOG.info("ERROR_PERIOD_START_BEFORE_PREVIOUS_END" + previousPeriodEndDate + periodStartDate);
            returnErrorValue = "ERROR_PERIOD_START_BEFORE_PREVIOUS_END";
        } else if (previousPeriodEndDate != null && !periodEndDate.after(previousPeriodEndDate)) {
            LOG.info("ERROR_PERIOD_END_BEFORE_PREVIOUS_END" + previousPeriodEndDate + periodEndDate);
            returnErrorValue = "ERROR_PERIOD_END_BEFORE_PREVIOUS_END";
        }
        return returnErrorValue;
    }

    private BudgetSummaryService getBudgetSummaryService() {
        return KcServiceLocator.getService(BudgetSummaryService.class);
    }
    
    private boolean isValidToInsert(Budget budget, BudgetPeriod newBudgetPeriod, String errorPathPrefix) {
        
        int expenseExistStatus = checkExpenseInBudget(budget);
        MessageMap errorMap = getGlobalVariableService().getMessageMap();
        if (CollectionUtils.isNotEmpty(budget.getBudgetPeriods())) {
            if (newBudgetPeriod.getEndDate().before(budget.getBudgetPeriod(0).getStartDate())) {
                // insert before 1st period
                if (expenseExistStatus >= 1) {
                    errorMap.putError(errorPathPrefix, KeyConstants.ERROR_INSERT_BUDGET_PERIOD);
                    return false;
                }
            } else if (newBudgetPeriod.getEndDate().before(budget.getBudgetPeriod(budget.getBudgetPeriods().size() - 1).getStartDate()) && expenseExistStatus > 1) {
                errorMap.putError(errorPathPrefix, KeyConstants.ERROR_INSERT_BUDGET_PERIOD);
                return false;
            }
        }
        return true;
    }
    
    private int checkExpenseInBudget(Budget budget) {
        int retVal = 0;
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (CollectionUtils.isNotEmpty(budgetPeriod.getBudgetLineItems())) {
                retVal = budgetPeriod.getBudgetPeriod();
            }
        }
        return retVal;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}

