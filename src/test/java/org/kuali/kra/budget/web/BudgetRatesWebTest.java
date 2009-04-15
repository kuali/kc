/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.web;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetRatesWebTest extends BudgetRatesWebTestBase {
    private static final String BDOC_BUDGET_RATES_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.rates.x";
    private static final String UPDATE_VIEW_BUTTON = "methodToCall.updateRatesView";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";

    private static final String SYNC_RATES_FnA_TAB = "methodToCall.syncRates.line0.anchor0";
    private static final String RESET_RATES_FnA_TAB = "methodToCall.resetRates.line0.anchor0";
    private static final String SYNC_RATES_OTHER_TAB = "methodToCall.syncRates.line6.anchor6";
    
    private static final String BUDGET_RATE_TYPE = "Administrative Salaries (7/1)";
    private static final String ON_CAMPUS_TEXT = "Yes";
    private static final String OFF_CAMPUS_TEXT = "No";
    private static final String VIEW_LOCATION = "viewLocation";

    private static final String PROCESS_ANSWER_YES = "methodToCall.processAnswer.button0"; 
    private static final String RESEARCH_TABLE = "Research F & A";
    private static final String OTHER_TABLE = "Other";


    /**
     * Test SYNC rates - change the rate and sync the value to get back the old value
     * @throws Exception
     */
    @Test
    public void testSyncRate() throws Exception {
        HtmlPage budgetRatesPage = getBudgetRatesPage();
        /* Research tab */
        syncRate(budgetRatesPage, RESEARCH_TABLE, SYNC_RATES_FnA_TAB);
    }
    
    private void syncRate(HtmlPage budgetRatesPage, String tableName, String syncButton)  throws Exception {
        String applicableRateField = getFirstRatesIDForGivenTable(budgetRatesPage, tableName);
        
        String oldApplicableRate = getFieldValue(budgetRatesPage, applicableRateField); 
        String newApplicableRate = "99.00";
        
        setFieldValue(budgetRatesPage, applicableRateField, newApplicableRate);
        assertEquals(newApplicableRate, getFieldValue(budgetRatesPage, applicableRateField));

        final HtmlPage savedRatesPage = saveAndReturnRatesPage(budgetRatesPage); 
        
        HtmlElement addBtn = getElementByName(savedRatesPage, syncButton, true);
        final HtmlPage syncRatesPage = clickOn(addBtn);

        HtmlElement confirmationBtn = getElementByName(syncRatesPage, PROCESS_ANSWER_YES, true);
        final HtmlPage confirmedRatesPageAfterSync = clickOn(confirmationBtn);

        final HtmlPage ratesPageAfterSync = saveAndReturnRatesPage(confirmedRatesPageAfterSync); 

        applicableRateField = getFirstRatesIDForGivenTable(ratesPageAfterSync, tableName);
        assertEquals(oldApplicableRate, getFieldValue(ratesPageAfterSync, applicableRateField));
        
    }


    /**
     * Test RESET rates - change the rate and reset the value to get back the old value
     * 
     * @throws Exception
     */
    @Test
    public void testResetRate() throws Exception {
        
        HtmlPage budgetRatesPage = getBudgetRatesPage();
        
        String applicableRateField = getFirstRatesIDForGivenTable(budgetRatesPage, RESEARCH_TABLE);
        String oldApplicableRate = getFieldValue(budgetRatesPage, applicableRateField);
        String newApplicableRate = "99.00";
        
        setFieldValue(budgetRatesPage, applicableRateField, newApplicableRate);
        
        assertEquals(newApplicableRate, getFieldValue(budgetRatesPage, applicableRateField));
        
        HtmlElement addBtn = getElementByName(budgetRatesPage, RESET_RATES_FnA_TAB, true);
        final HtmlPage ratesPageAfterSync = clickOn(addBtn);
        
        HtmlElement confirmationBtn = getElementByName(ratesPageAfterSync, PROCESS_ANSWER_YES, true);
        final HtmlPage confirmedRatesPageAfterSync = clickOn(confirmationBtn);

        final HtmlPage savedRatesPageAfterSyncSaved = clickOn(confirmedRatesPageAfterSync, "methodToCall.save");
        
        /* back to rates page again. Save in rates page will redirect to summary tab */
        HtmlPage ratesPage = clickOn(savedRatesPageAfterSyncSaved, BDOC_BUDGET_RATES_LINK_NAME);

        applicableRateField = getFirstRatesIDForGivenTable(ratesPage, RESEARCH_TABLE);
        assertEquals(oldApplicableRate, getFieldValue(ratesPage, applicableRateField));
    }

    @Test
    public void testSaveBudgetRates() throws Exception {
        HtmlPage budgetRatesPage = getBudgetRatesPage();
        /* check page initialized */
        assertContains(budgetRatesPage, BUDGET_RATE_TYPE);
        
        HtmlPage saveBudgetSummaryPage = saveDoc(budgetRatesPage);
        assertDoesNotContain(saveBudgetSummaryPage, ERRORS_FOUND_ON_PAGE);
        assertContains(saveBudgetSummaryPage, SAVE_SUCCESS_MESSAGE);
        
    }

    /**
     * Test select view - select one location and update to filter the list
     * 
     * @throws Exception
     */
    @Ignore
    @Test
    public void testSelectView() throws Exception {
       
        HtmlPage budgetRatesPage = getBudgetRatesPage();
        String onCampusFlag = Constants.ON_CAMUS_FLAG;
           
        setFieldValue(budgetRatesPage, VIEW_LOCATION, onCampusFlag);
        HtmlElement addBtn = getElementByName(budgetRatesPage, UPDATE_VIEW_BUTTON, true);
        budgetRatesPage = clickOn(addBtn);
        assertContains(budgetRatesPage, ON_CAMPUS_TEXT);
        assertDoesNotContain(budgetRatesPage, OFF_CAMPUS_TEXT);
    }

}
