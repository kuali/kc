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
package org.kuali.coeus.common.budget.impl.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetAddPersonnelPeriodEvent;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetSavePersonnelPeriodEvent;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.common.framework.ruleengine.KcEventResult;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@KcBusinessRule("budgetPersonnelPeriodRule")
public class BudgetPersonnelPeriodRule {

    public static final String PERSON_SEQUENCE_NUMBER = "personSequenceNumber";
    public static final String START_DATE = "startDate";
    public static final String PERCENT_EFFORT = "percentEffort";
    public static final String PERCENT_CHARGED = "percentCharged";
    public static final String END_DATE = "endDate";
    public static final String CAN_NOT_BE_AFTER = "can not be after";
    public static final String CAN_NOT_BE_BEFORE = "can not be before";
    public static final String LOWER_END_DATE = "end date";
    public static final String LOWER_START_DATE = "start date";

    @KcEventMethod
    public KcEventResult validateAddBudgetPersonnelPeriod(BudgetAddPersonnelPeriodEvent event) {
    	KcEventResult result = new KcEventResult();
    	result.getMessageMap().addToErrorPath(event.getErrorPath());
        verifyProjectPersonnel(event, result);
    	verifyPersonnelEffortAndCharged(event.getBudgetPersonnelDetails(), result);
    	verifyPersonnelDates(event.getBudgetPersonnelDetails(),event.getBudgetPeriod(), result);
    	result.getMessageMap().removeFromErrorPath(event.getErrorPath());
    	return result;
    }

    @KcEventMethod
    public KcEventResult validateSaveBudgetPersonnelPeriod(BudgetSavePersonnelPeriodEvent event) {
        KcEventResult result = new KcEventResult();
        result.getMessageMap().addToErrorPath(event.getErrorPath());
        verifyPersonnelEffortAndCharged(event.getBudgetPersonnelDetails(), result);
        verifyPersonnelDates(event.getBudgetPersonnelDetails(),event.getBudgetPeriod(), result);
    	if(!isSummaryPerson(event.getBudgetPersonnelDetails())) {
            List<BudgetPersonnelDetails> budgetPersonnelDetails = getBudgetPersonnelDetails(event.getBudgetLineItem(), event.getBudgetPersonnelDetails(), event.getEditLineIndex());
            verifyDuplicatePerson(event.getBudgetLineItem(), event.getBudgetPersonnelDetails(), budgetPersonnelDetails, result);
    	}
        result.getMessageMap().removeFromErrorPath(event.getErrorPath());
        return result;
    }

    protected void verifyProjectPersonnel(BudgetAddPersonnelPeriodEvent event, KcEventResult result) {
    	BudgetLineItem newBudgetLineItem = event.getBudgetLineItem(); 
    	BudgetPersonnelDetails newBudgetPersonnelDetails = event.getBudgetPersonnelDetails(); 
    	List<BudgetLineItem> budgetLineItemList = event.getBudgetPeriod().getBudgetLineItems();
    	String newBudgetCategoryTypeCode = newBudgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode();
        for(BudgetLineItem budgetLineItem : budgetLineItemList) {
        	String existingBudgetCategoryTypeCode = budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode();
        	if(existingBudgetCategoryTypeCode.equalsIgnoreCase(newBudgetCategoryTypeCode)) {
                if(newBudgetLineItem.getCostElement().equalsIgnoreCase(budgetLineItem.getCostElement()) && 
                        StringUtils.equals(newBudgetLineItem.getGroupName(), budgetLineItem.getGroupName())) {
                	if(isSummaryPerson(newBudgetPersonnelDetails)) {
                		addSummaryPersonnelLineItemErrorMessage(budgetLineItem, event, result);
                    	break;
                	}else {
                		verifyPersonnel(budgetLineItem, newBudgetLineItem, newBudgetPersonnelDetails, event, result);
                		if(!result.getSuccess()) {
                        	break;
                		}
                	}
                }
        	}
        }
    }

    protected boolean isSummaryPerson(BudgetPersonnelDetails budgetPersonnelDetails) {
    	return budgetPersonnelDetails.getPersonSequenceNumber().equals(BudgetConstants.BudgetPerson.SUMMARYPERSON.getPersonSequenceNumber());
    }
    
    /**
     * User adding a new summary line
     * Condition matched (object code and group)
     * Error either summary line already exists or personnel line item exists
     */
    protected void addSummaryPersonnelLineItemErrorMessage(BudgetLineItem budgetLineItem, BudgetAddPersonnelPeriodEvent event, KcEventResult result) {
        //Summary is already added and user is attempting to add a second summary
    	String errorKey = event.getErrorKeyPerson() != null ? event.getErrorKeyPerson() : PERSON_SEQUENCE_NUMBER;
        if(budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
            result.getMessageMap().putError(errorKey, KeyConstants.ERROR_SUMMARY_LINEITEM_EXISTS);
            result.setSuccess(false);
        }else {
            //Condition where Personnel are already added for the line item
            result.getMessageMap().putError(errorKey, KeyConstants.ERROR_PERSONNEL_EXISTS);
            result.setSuccess(false);
        }
    }
    
    /**
     * User adding a new personnel line
     * Condition matched (object code and group)
     * Error check whether summary line already exists or personnel line is duplicate
     */
    protected void verifyPersonnel(BudgetLineItem budgetLineItem, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetails, BudgetAddPersonnelPeriodEvent event, KcEventResult result) {
    	String errorKey = event.getErrorKeyPerson() != null ? event.getErrorKeyPerson() : PERSON_SEQUENCE_NUMBER;
        if(budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
            result.getMessageMap().putError(errorKey, KeyConstants.ERROR_SUMMARY_LINEITEM_EXISTS);
            result.setSuccess(false);
        }else {
            List<BudgetPersonnelDetails> budgetPersonnelDetails = getBudgetPersonnelDetails(budgetLineItem, newBudgetPersonnelDetails);
        	verifyDuplicatePerson(budgetLineItem, newBudgetPersonnelDetails, budgetPersonnelDetails, result);
        }
    }
    
    /**
     * Check for duplicate person while adding new personnel and in edit mode.
     * In new mode, person should not exist in the list with applicable unique key
     * In edit mode, allow update on same person and make sure the duplicate key is validated (change in start date).
     */
    protected void verifyDuplicatePerson(BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetails, List<BudgetPersonnelDetails> budgetPersonnelDetailsList, KcEventResult result) {
    	Set<String> uniquePersonDetails = new HashSet<String>();
    	for(BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelDetailsList) {
        	if(!uniquePersonDetails.add(getBudgetPersonnelPeriodUniqueKey(budgetPersonnelDetails))) {
                result.getMessageMap().putError(START_DATE, KeyConstants.ERROR_DUPLICATE_PERSON, newBudgetPersonnelDetails.getBudgetPerson().getPersonName());
                result.setSuccess(false);
                break;
        	}
    	}
    }

    protected List<BudgetPersonnelDetails> getBudgetPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetails) {
    	List<BudgetPersonnelDetails> budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
    	budgetPersonnelDetailsList.addAll(budgetLineItem.getBudgetPersonnelDetailsList());
    	budgetPersonnelDetailsList.add(newBudgetPersonnelDetails);
    	return budgetPersonnelDetailsList;
    }
   
    protected List<BudgetPersonnelDetails> getBudgetPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetails, int editLineIndex) {
    	List<BudgetPersonnelDetails> budgetPersonnelDetailsList = getBudgetPersonnelDetails(budgetLineItem, newBudgetPersonnelDetails);
    	BudgetPersonnelDetails existingBudgetPersonnelDetails = budgetPersonnelDetailsList.get(editLineIndex);
    	budgetPersonnelDetailsList.remove(existingBudgetPersonnelDetails);
    	return budgetPersonnelDetailsList;
    }
   
    protected String getBudgetPersonnelPeriodUniqueKey(BudgetPersonnelDetails budgetPersonnelDetails) {
    	StringBuffer uniqueKey = new StringBuffer();
    	uniqueKey.append(budgetPersonnelDetails.getPersonSequenceNumber());
    	uniqueKey.append(budgetPersonnelDetails.getJobCode());
    	uniqueKey.append(budgetPersonnelDetails.getStartDate());
    	return uniqueKey.toString();
    }
    
    protected void verifyPersonnelEffortAndCharged(BudgetPersonnelDetails budgetPersonnelDetails, KcEventResult result) {
        if(budgetPersonnelDetails.getPercentEffort().isGreaterThan(new ScaleTwoDecimal(100))){
            result.getMessageMap().putError(PERCENT_EFFORT, KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            result.setSuccess(false);
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(new ScaleTwoDecimal(100))){
            result.getMessageMap().putError(PERCENT_CHARGED, KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
            result.setSuccess(false);
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
            result.getMessageMap().putError(PERCENT_CHARGED, KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            result.setSuccess(false);
        }
    }

    protected void verifyPersonnelDates(BudgetPersonnelDetails budgetPersonnelDetails, BudgetPeriod budgetPeriod, KcEventResult result) {
        if(budgetPersonnelDetails.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
            result.getMessageMap().putError(END_DATE, KeyConstants.ERROR_PERSONNEL_DETAIL_DATES);
            result.setSuccess(false);
        }
        if(budgetPeriod.getEndDate().compareTo(budgetPersonnelDetails.getEndDate()) < 0) {
            result.getMessageMap().putError(END_DATE, KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {CAN_NOT_BE_AFTER, LOWER_END_DATE});
            result.setSuccess(false);
        }
        if(budgetPeriod.getStartDate().compareTo(budgetPersonnelDetails.getEndDate()) > 0) {
            result.getMessageMap().putError(END_DATE, KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {CAN_NOT_BE_BEFORE, LOWER_START_DATE});
            result.setSuccess(false);
        }
        if(budgetPeriod.getStartDate().compareTo(budgetPersonnelDetails.getStartDate()) > 0) {
            result.getMessageMap().putError(START_DATE, KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {CAN_NOT_BE_BEFORE, LOWER_START_DATE});
            result.setSuccess(false);
        }
        if(budgetPeriod.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
            result.getMessageMap().putError(START_DATE, KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {CAN_NOT_BE_AFTER, LOWER_END_DATE});
            result.setSuccess(false);
        }
    }

}
