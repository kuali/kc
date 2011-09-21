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

import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

/**
 * Provides methods for creating a Budget document, clicking on tabs, and populating fields.
 */
public class BudgetSeleniumHelper extends KcSeleniumHelper {
    
    private static final String VERSIONS_LINK_NAME = "versions";
    private static final String PARAMETERS_LINK_NAME = "parameters";
    private static final String RATES_LINK_NAME = "rates";
    private static final String SUMMARY_LINK_NAME = "summaryTotals";
    private static final String PERSONNEL_LINK_NAME = "personnel";
    private static final String NON_PERSONNEL_LINK_NAME = "expenses";
    private static final String DISTRIBUTION_AND_INCOME_LINK_NAME = "distributionAndIncome";
    private static final String MODULAR_BUDGET_LINK_NAME = "modularBudget";
    private static final String ACTIONS_LINK_NAME = "budgetActions";
    
    private static final String LIST_PREFIX = "document.budget.";
    private static final String BUDGET_RATES_PREFIX = "budgetRates";
    private static final String APPLICABLE_RATE_ID = "applicableRate";
    private static final String BUDGET_PERIOD_PREFIX = LIST_PREFIX + "budgetPeriod";
    private static final String NEW_LINE_ITEM_PREFIX = "newBudgetLineItems";
    private static final String NEW_PERSON_TAG = "newBudgetPersons";
    private static final String PERSON_ID_ID = "personId";
    private static final String NEW_ROLODEX_TAG = "newBudgetRolodexes";
    private static final String ROLODEX_ID_ID = "rolodexId";
    private static final String BUDGET_PERSON_LIST_PREFIX = LIST_PREFIX + "budgetPersons";
    private static final String PERSON_JOB_CODE_ID = "jobCode";
    private static final String PERSON_SALARY_ID = "calculationBase";
    private static final String NEW_PERSON_ID = "newBudgetPersonnelDetails.personSequenceNumber";
    private static final String COST_ELEMENT_ID = "costElement";
    private static final String NEW_COST_ELEMENT_ID = NEW_LINE_ITEM_PREFIX + "[0]." + COST_ELEMENT_ID;
    private static final String BUDGET_LINE_ITEM_PREFIX = "budgetLineItem";
    private static final String PERSONNEL_DETAILS_PREFIX = "budgetPersonnelDetailsList";
    private static final String PERCENT_EFFORT_ID = "percentEffort";
    private static final String PERCENT_CHARGED_ID = "percentCharged";
    private static final String LINE_ITEM_COST_ID = "lineItemCost";
    private static final String ITEM_QUANTITY_ID = "quantity";
    private static final String COST_SHARE_PREFIX = LIST_PREFIX + "budgetCostShare";
    private static final String UNRECOVERED_FA_PREFIX = LIST_PREFIX + "budgetUnrecoveredFandA";
    private static final String ACCOUNT_ID_ID = "ACCOUNT_ID";
    private static final String AMOUNT_ID_ID = "AMOUNT_ID";

    private static final String DEFAULT_JOB_CODE = "AA023";
    private static final String DEFAULT_SALARY_AMOUNT = "100000";
    private static final String DEFAULT_PI_NAME = "Nicholas Majors";
    private static final String DEFAULT_JOB_CODE_NAME = "Faculty Salaries Non-Tenured - On";
    private static final String DEFAULT_PERCENT_CHARGED = "100";
    private static final String DEFAULT_PERCENT_EFFORT = "100";
    private static final String DEFAULT_LINE_ITEM_DESC1 = "Equipment - Not MTDC";
    private static final String DEFAULT_LINE_ITEM_QUANTITY1 = "1";
    private static final String DEFAULT_LINE_ITEM_DESC2 = "Travel";
    private static final String DEFAULT_LINE_ITEM_QUANTITY2 = null;
    private static final String DEFAULT_LINE_ITEM_AMT = "1000";
    
    private static final String ADD_NEW_PERSON_BUTTON = "addPersonnelLineItem.budgetCategoryTypeCodeP";
    private static final String ADD_BUDGET_LINE_ITEM_BUTTON = "addBudgetLineItem.budgetCategoryTypeCode";
    private static final String RETURN_TO_PROPOSAL_BUTTON = "methodToCall.returnToProposal";
    private static final String RETURN_TO_AWARD_BUTTON = "methodToCall.returnToAward";
    private static final String POST_BUDGET_BUTTON = "methodToCall.postAwardBudget";
    
    private static BudgetSeleniumHelper helper;
    
    private Map<Integer, String> lineItemTypeIdxToTypeCode;
    
    public static BudgetSeleniumHelper instance(WebDriver driver) {
        if (helper == null) {
            helper = new BudgetSeleniumHelper(driver);
        }
        return helper;
    }

    private BudgetSeleniumHelper(WebDriver driver) {
        super(driver);
        
        lineItemTypeIdxToTypeCode = new HashMap<Integer, String>();
        lineItemTypeIdxToTypeCode.put(0, "E");
        lineItemTypeIdxToTypeCode.put(1, "T");
        lineItemTypeIdxToTypeCode.put(2, "T");
        lineItemTypeIdxToTypeCode.put(3, "O");
    }
    
    /**
     * Navigate to the Budget Versions page.
     */
    public void clickBudgetVersionsTab() {
        click(VERSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Budget Parameters page.
     */
    public void clickBudgetParametersTab() {
        click(PARAMETERS_LINK_NAME);
    }
    
    /**
     * Navigate to the Budget Rates page.
     */
    public void clickBudgetRatesTab() {
        click(RATES_LINK_NAME);
    }
    
    /**
     * Navigate to the Budget Summary page.
     */
    public void clickBudgetSummaryTab() {
        click(SUMMARY_LINK_NAME);
    }
    
    /**
     * Navigate to the Budget Personnel page.
     */
    public void clickBudgetPersonnelTab() {
        click(PERSONNEL_LINK_NAME);
    }
    
    /**
     * Navigate to the Budget Non-Personnel page.
     */
    public void clickBudgetNonPersonnelTab() {
        click(NON_PERSONNEL_LINK_NAME);
    }
    
    /**
     * Navigate to the Budget Distribution & Income page.
     */
    public void clickBudgetDistributionAndIncomeTab() {
        click(DISTRIBUTION_AND_INCOME_LINK_NAME);
    }

    /**
     * Navigate to the Budget Modular Budget page.
     */
    public void clickBudgetModularBudgetTab() {
        click(MODULAR_BUDGET_LINK_NAME);
    }
    
    /**
     * Navigate to the Budget Actions page.
     */
    public void clickBudgetActionsTab() {
        click(ACTIONS_LINK_NAME);
    }
    
    /**
     * Return to the Proposal Development.
     */
    public void returnToProposal() {
        click(RETURN_TO_PROPOSAL_BUTTON);
    }
    
    /**
     * Return to the Award.
     */
    public void returnToAward() {
        click(RETURN_TO_AWARD_BUTTON);
    }
    
    /**
     * Adds a new person to this Budget.
     */
    public void addPersonnel() {
        clickBudgetPersonnelTab();
        clickYesAnswer();
        
        setPersonDetails(0, DEFAULT_JOB_CODE, DEFAULT_SALARY_AMOUNT);
        
        clickBudgetPersonnelTab();
        
        clickExpandAll();
        addPersonnelDetail(DEFAULT_PI_NAME, DEFAULT_JOB_CODE, DEFAULT_JOB_CODE_NAME);
        setPersonPercents(0, 0, 0, DEFAULT_PERCENT_EFFORT, DEFAULT_PERCENT_CHARGED);
    }
    
    /**
     * Adds new line items to this Budget.
     */
    public void addNonPersonnel() {
        clickBudgetNonPersonnelTab();
        
        addLineItem(0, DEFAULT_LINE_ITEM_DESC1, DEFAULT_LINE_ITEM_QUANTITY1, DEFAULT_LINE_ITEM_AMT);
        addLineItem(1, DEFAULT_LINE_ITEM_DESC2, DEFAULT_LINE_ITEM_QUANTITY2, DEFAULT_LINE_ITEM_AMT);
        
        saveDocument();
    }
    
    
    /**
     * Set the rate at the specified index.
     * @param index
     * @param rate
     */
    public void setRate(int index, String rate) {
        set(LIST_PREFIX + BUDGET_RATES_PREFIX + "[" + index + "]." + APPLICABLE_RATE_ID, rate);
    }
    
    /**
     * Add the person to the budget
     * @param personId
     */
    public void addPerson(String personId) {
        multiLookup(NEW_PERSON_TAG, PERSON_ID_ID, personId);
    }
    
    /**
     * Add the non-employee by rolodexid to the budget.
     * @param rolodexId
     */
    public void addRolodex(String rolodexId) {
        multiLookup(NEW_ROLODEX_TAG, ROLODEX_ID_ID, rolodexId);
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
        set(NEW_COST_ELEMENT_ID, jobCodeName);
        click(ADD_NEW_PERSON_BUTTON);
    }
    
    /**
     * Populate the percent effort and changed for the person.
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
        click(ADD_BUDGET_LINE_ITEM_BUTTON + lineItemTypeIdxToTypeCode.get(itemTypeIdx));
    }
    
    /**
     * Populate the cost share information
     * @param index
     * @param account
     * @param amount
     */
    public void populateCostShare(int index, String account, String amount) {
        String prefix = COST_SHARE_PREFIX + "[" + index + "].";
        set(prefix + ACCOUNT_ID_ID, account);
        if (amount != null) {
            set(prefix + AMOUNT_ID_ID, amount);
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
        set(prefix + ACCOUNT_ID_ID, account);
        if (amount != null) {
            set(prefix + AMOUNT_ID_ID, amount);
        }
    }
    
    public void postBudget() {
        click(POST_BUDGET_BUTTON);
    }
    
}