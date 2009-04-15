/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.io.IOException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetInflationRatesWebTest extends BudgetRatesWebTestBase {
    private static final String PERSONNEL_INFLATION_SYNC_TEXT = "Faculty Salaries (6/1) No 2000 07/01/1999";
    private static final String BUDGET_RATE_TYPE = "Administrative Salaries (7/1)";
    private static final String PERSONNEL_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.personnel.x";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    private static final String SYNC_INFLATION_RATES_BUTTON = "methodToCall.syncRates.line2.anchor2";
    private static final String PROCESS_ANSWER_YES = "methodToCall.processAnswer.button0"; 
    private static final String BDOC_BUDGET_RATES_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.rates.x";

    /**
     * Test inflation sync based on person salary effective
     * date
     * 
     * @throws Exception
     */
    @Test
    public void testSyncInflationRates() throws Exception {

        HtmlPage budgetRatesPage = getBudgetRatesPage();
        /* check page initialized */
        assertContains(budgetRatesPage, BUDGET_RATE_TYPE);
        assertDoesNotContain(budgetRatesPage, PERSONNEL_INFLATION_SYNC_TEXT);
        
        /* add budget personnel and return to rates page */
        HtmlPage ratesPage = addBudgetPersonnelAndGetRatesPage(budgetRatesPage);
        assertDoesNotContain(ratesPage, ERRORS_FOUND_ON_PAGE);
        assertContains(ratesPage, SAVE_SUCCESS_MESSAGE);

        HtmlElement addBtn = getElementByName(ratesPage, SYNC_INFLATION_RATES_BUTTON, true);
        HtmlPage savedRatesPageAfterSync = clickOn(addBtn);

        HtmlElement confirmationBtn = getElementByName(savedRatesPageAfterSync, PROCESS_ANSWER_YES, true);
        HtmlPage confirmedRatesPageAfterSync = clickOn(confirmationBtn);

        HtmlPage ratesPageAfterSync = saveAndReturnRatesPage(confirmedRatesPageAfterSync); 
        
        assertContains(ratesPageAfterSync, PERSONNEL_INFLATION_SYNC_TEXT);
    }

    
    /**
     * Add new budget person 
     * 
     *
     */
    protected HtmlPage addBudgetPersonnelAndGetRatesPage(HtmlPage page) throws Exception {
        HtmlPage budgetPersonnelPage = clickOn(page, PERSONNEL_IMAGE_NAME);
        budgetPersonnelPage = multiLookup(budgetPersonnelPage, "org.kuali.kra.bo.Person", "personId", "000000003");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[0].jobCode", "AA004");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[0].appointmentTypeCode", "1");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[0].calculationBase", "100000");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[0].effectiveDate", "01/01/2000");
        HtmlPage ratesPage = clickOn(budgetPersonnelPage, BDOC_BUDGET_RATES_LINK_NAME);
        return ratesPage;
    }


    /**
     * Overriding multiLookup because the select all methodToCall is different for some reason for budget lookup. This needs to be verified in
     * other places.
     */
    @Override
    protected final HtmlPage multiLookup(HtmlPage page, String tag, String searchFieldId, String searchValue) throws IOException {
        HtmlPage lookupPage = clickOnLookup(page, tag);

        if (searchFieldId != null) {
            assertTrue(searchValue != null);
            setFieldValue(lookupPage, searchFieldId, searchValue);
        }

        // click on the search button
        HtmlImageInput searchBtn = (HtmlImageInput) getElement(lookupPage, "methodToCall.search", "search", "search");
        HtmlPage resultsPage = (HtmlPage) searchBtn.click();

        HtmlImageInput selectAllBtn = (HtmlImageInput) getElement(resultsPage, "methodToCall.selectAll.(::;true;::).x", null, null);
        HtmlPage selectedPage = (HtmlPage) selectAllBtn.click();

        HtmlImageInput returnAllBtn = (HtmlImageInput) getElement(selectedPage, "methodToCall.prepareToReturnSelectedResults.x", null, null);
        HtmlPage returnPage = (HtmlPage) returnAllBtn.click();

        return returnPage;
    }

}
