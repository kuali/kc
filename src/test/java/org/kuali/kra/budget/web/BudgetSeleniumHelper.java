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
package org.kuali.kra.budget.web;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Helper provides methods for creating a budget document in the selenium framework.
 * Provides methods for clicking on tabs and populating various portions of the
 * budget.
 * 
 * This is not complete yet as it is primarily a proof of concept for a new
 * way to organize the selenium web tests.
 */
public class BudgetSeleniumHelper extends KcSeleniumTestBase {
    
    protected static final String VERSIONS_LINK_NAME = "versions";
    protected static final String PARAMETERS_LINK_NAME = "parameters";
    protected static final String RATES_LINK_NAME = "rates";
    protected static final String SUMMARY_LINK_NAME = "summaryTotals";
    protected static final String PERSONNEL_LINK_NAME = "personnel";
    protected static final String NON_PERSONNEL_LINK_NAME = "expenses";
    protected static final String DIST_AND_INCOME_LINK_NAME = "distributionAndIncome";
    protected static final String MODULAR_BUDGET_LINK_NAME = "modularBudget";
    protected static final String ACTIONS_LINK_NAME = "budgetActions";
    protected static final String RETURN_TO_PROPOSAL = "methodToCall.returnToProposal";
    
    protected static final String BUDGET_STATUS_ID = "document.parentDocument.budgetDocumentVersion[0].budgetVersionOverview.budgetStatus";
    protected static final String FINAL_FLAG_ID = "document.parentDocument.budgetDocumentVersion[0].budgetVersionOverview.finalVersionFlag";
    
    protected static final String LIST_PREFIX = "document.budget.";
    protected static final String BUDGET_PERIOD_PREFIX = LIST_PREFIX + "budgetPeriod";
    protected static final String NEW_LINE_ITEM_PREFIX = "newBudgetLineItems";
    
    protected static final String BUDGET_RATES_PREFIX = "budgetRates";
    protected static final String RATE_ID = "applicableRate";
    
    protected static final String PERSON_LOOKUP_NAME = "KcPerson";    
    protected static final String ROLODEX_LOOKUP_NAME = "NonOrganizationalRolodex";
    
    
    protected static final String BUDGET_PERSON_LIST_PREFIX = LIST_PREFIX + "budgetPersons";
    protected static final String PERSON_JOB_CODE_ID = "jobCode";
    protected static final String PERSON_APPOINTMENT_CODE_ID = "appointmentTypeCode";
    protected static final String PERSON_SALARY_ID = "calculationBase";
    protected static final String EFFECTIVE_DATE_ID = "effectiveDate";
    protected static final String NEW_PERSON_ID = "newBudgetPersonnelDetails.personSequenceNumber";
    protected static final String COST_ELEMENT_ID = "costElement";
    protected static final String NEW_COST_ELEMENT = NEW_LINE_ITEM_PREFIX + "[0]." + COST_ELEMENT_ID;
    protected static final String ADD_NEW_PERSON_LINK_NAME = "addPersonnelLineItem.budgetCategoryTypeCodeP";
    protected static final String BUDGET_LINE_ITEM_PREFIX = "budgetLineItem";
    protected static final String PERSONNEL_DETAILS_PREFIX = "budgetPersonnelDetailsList";
    protected static final String PERCENT_EFFORT_ID = "percentEffort";
    protected static final String PERCENT_CHARGED_ID = "percentCharged";
    protected static final String APPLY_TO_LATER_PERIODS = "applyToLaterPeriods.line";
    
    protected static final String LINE_ITEM_COST_ID = "lineItemCost";
    protected static final String ITEM_QUANTITY_ID = "quantity";
    //without the type qualifier (append E,T or other)
    protected static final String ADD_BUDGET_LINE_ITEM = "addBudgetLineItem.budgetCategoryTypeCode";
    
    protected Map<Integer, String> lineItemTypeIdxToTypeCode;
    
    protected static final String COST_SHARE_PREFIX = LIST_PREFIX + "budgetCostShare";
    protected static final String UNRECOVERED_FA_PREFIX = LIST_PREFIX + "budgetUnrecoveredFandA";
    protected static final String ACCOUNT_ID = "sourceAccount";
    protected static final String AMOUNT_ID ="amount";
    
    /**
     * Right now the web driver is a static property on KcSeleniumTestBase
     * and as long as this is used within a test extending from KcSeleniumTestBase it should be
     * set, but if this changes we will need to accept the web driver here
     * Constructs a BudgetSeleniumHelper.java.
     */
    public BudgetSeleniumHelper() {
        lineItemTypeIdxToTypeCode = new HashMap<Integer, String>();
        lineItemTypeIdxToTypeCode.put(0, "E");
        lineItemTypeIdxToTypeCode.put(1, "T");
        lineItemTypeIdxToTypeCode.put(2, "T");
        lineItemTypeIdxToTypeCode.put(3, "O");
    }
    
    /**
     * Set the rate at the specified index.
     * @param index
     * @param rate
     */
    public void setRate(int index, String rate) {
        set(LIST_PREFIX + BUDGET_RATES_PREFIX + "[" + index + "]." + RATE_ID, rate);
    }
    
    /**
     * Add the person to the budget
     * @param personId
     */
    public void addPerson(String personId) {
        multiLookup(PERSON_LOOKUP_NAME, "personId", personId);
    }
    
    /**
     * Add the non-employee by rolodexid to the budget.
     * @param rolodexId
     */
    public void addRolodex(String rolodexId) {
        multiLookup(ROLODEX_LOOKUP_NAME, "rolodexId", rolodexId);
    }
    
    /**
     * Populate the budget details for the person at personIndex
     * @param personIndex
     * @param jobCode
     * @param calculationBase
     */
    public void setPersonDetails(int personIndex, String jobCode, String calculationBase) {
        String personPrefix = BUDGET_PERSON_LIST_PREFIX + "[" + personIndex + "].";
        set(personPrefix + PERSON_JOB_CODE_ID, jobCode);
        set(personPrefix + PERSON_SALARY_ID, calculationBase);
    }
    
    /**
     * Add the personnel line item to the budget.
     * @param personName
     * @param jobCode
     * @param jobCodeName
     */
    public void addPersonnelDetail(String personName, String jobCode, String jobCodeName) {
        set(NEW_PERSON_ID, personName + " - " + jobCode);
        set(NEW_COST_ELEMENT, jobCodeName);
        click(ADD_NEW_PERSON_LINK_NAME);
    }
    
    /**
     * Populate the percent effor and changed for the person.
     * @param budgetPeriod
     * @param lineItem
     * @param detailsIdx
     * @param percentEffort
     * @param percentCharged
     */
    public void setPersonPercents(int budgetPeriod, int lineItem, int detailsIdx, String percentEffort, String percentCharged) {
        String prefix = BUDGET_PERIOD_PREFIX + "[" + budgetPeriod + "]." + BUDGET_LINE_ITEM_PREFIX + "[" + lineItem + "]."
            + PERSONNEL_DETAILS_PREFIX + "[" + detailsIdx + "].";
        set(prefix + PERCENT_EFFORT_ID, percentEffort);
        set(prefix + PERCENT_CHARGED_ID, percentCharged);
    }
    
    /**
     * Add a non-personnel line item to the budget.
     * @param itemTypeIdx
     * @param itemDescription
     * @param quantity
     * @param amount
     */
    public void addLineItem(Integer itemTypeIdx, String itemDescription, String quantity, String amount) {
        clickExpandAll();
        String prefix = NEW_LINE_ITEM_PREFIX + "[" + itemTypeIdx + "].";
        set(prefix + COST_ELEMENT_ID, itemDescription);
        set(prefix + LINE_ITEM_COST_ID, amount);
        set(prefix + ITEM_QUANTITY_ID, quantity);
        click(ADD_BUDGET_LINE_ITEM + lineItemTypeIdxToTypeCode.get(itemTypeIdx));
    }
    
    /**
     * Populate the cost share information
     * @param index
     * @param account
     * @param amount
     */
    public void populateCostShare(int index, String account, String amount) {
        String prefix = COST_SHARE_PREFIX + "[" + index + "].";
        set(prefix + "ACCOUNT_ID", account);
        if (amount != null) {
            set(prefix + "AMOUNT_ID", amount);
        }
    }
    
    /**
     * Populate unrecovered f&a information 
     * @param index
     * @param account
     * @param amount
     */
    public void populateUnrecoveredFandA(int index, String account, String amount) {
        String prefix = UNRECOVERED_FA_PREFIX + "[" + index + "].";
        set(prefix + "ACCOUNT_ID", account);
        if (amount != null) {
            set(prefix + "AMOUNT_ID", amount);
        }
    }
    
    public void clickBudgetPersonnelTab() {
        click(PERSONNEL_LINK_NAME);
    }
    
    public void clickNonPersonnelTab() {
        click(NON_PERSONNEL_LINK_NAME);
    }
    
    public void returnToProposal() {
        click(RETURN_TO_PROPOSAL);
    }
}
