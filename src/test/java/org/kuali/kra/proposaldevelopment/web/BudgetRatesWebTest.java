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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.Test;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetRatesWebTest extends ProposalDevelopmentWebTestBase{
    private static final String PDDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    private static final String BDOC_BUDGET_RATES_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.rates.x";
    private static final String NEW_BUDGET_VERSION_NAME = "newBudgetVersionName";
    private static final String UPDATE_VIEW_BUTTON = "methodToCall.updateRatesView";
    private static final String RESET_RATES_BUTTON = "methodToCall.resetRates.line0.anchor1";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    private static final String ADD_BUDGET_VERSION_BUTTON = "methodToCall.addBudgetVersion";
    private static final String ADD_NEW_BUDGET_START = "newBudgetPeriod.startDate";
    private static final String ADD_NEW_BUDGET_END = "newBudgetPeriod.endDate";

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "005894";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_REQUESTED_START_DATE = "01/01/2000";
    private static final String DEFAULT_PROPOSAL_REQUESTED_END_DATE = "12/31/2005";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_TYPE_CODE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "000001";
    private static final String BUDGET_RATE_TYPE = "Administrative Salaries (7/1)";
    private static final String ON_CAMPUS_TEXT = "Yes";
    private static final String OFF_CAMPUS_TEXT = "No";
    private static final String VIEW_LOCATION = "viewLocation";
    private static final String APPLICABLE_RATE_FIELD = "document.budgetProposalRates[0].applicableRate";

    @Test
    public void testSaveBudgetRates() throws Exception {
        /* get budget version page in proposal development module */
        HtmlPage proposalBudgetVersionsPage = getBudgetVersionsPage();
        /* add new version and open budget version page in budget module */
        HtmlPage budgetVersionsPage = addBudgetVersion(proposalBudgetVersionsPage);
        /* get budget summary page */
        HtmlPage budgetRatesPage = clickOn(budgetVersionsPage, BDOC_BUDGET_RATES_LINK_NAME);

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
    @Test
    public void testSelectView() throws Exception {
        /* get budget version page in proposal development module */
        HtmlPage proposalBudgetVersionsPage = getBudgetVersionsPage();
        /* add new version and open budget version page in budget module */
        HtmlPage budgetVersionsPage = addBudgetVersion(proposalBudgetVersionsPage);
        /* get budget summary page */
        HtmlPage budgetRatesPage = clickOn(budgetVersionsPage, BDOC_BUDGET_RATES_LINK_NAME);
        
        String onCampusFlag = Constants.ON_CAMUS_FLAG;
           
        setFieldValue(budgetRatesPage, VIEW_LOCATION, onCampusFlag);
        HtmlElement addBtn = getElementByName(budgetRatesPage, UPDATE_VIEW_BUTTON, true);
        budgetRatesPage = clickOn(addBtn);
        assertContains(budgetRatesPage, ON_CAMPUS_TEXT);
        assertDoesNotContain(budgetRatesPage, OFF_CAMPUS_TEXT);
    }

    /**
     * Test RESET rates - change the rate and reset the value to get back the old value
     * 
     * @throws Exception
     */
    @Test
    public void testResetRate() throws Exception {
        /* get budget version page in proposal development module */
        HtmlPage proposalBudgetVersionsPage = getBudgetVersionsPage();
        /* add new version and open budget version page in budget module */
        HtmlPage budgetVersionsPage = addBudgetVersion(proposalBudgetVersionsPage);
        /* get budget summary page */
        HtmlPage budgetRatesPage = clickOn(budgetVersionsPage, BDOC_BUDGET_RATES_LINK_NAME);
        
        String oldApplicableRate = getFieldValue(budgetRatesPage, APPLICABLE_RATE_FIELD);
        String newApplicableRate = "99";
        
        setFieldValue(budgetRatesPage, APPLICABLE_RATE_FIELD, newApplicableRate);

        assertEquals(newApplicableRate, getFieldValue(budgetRatesPage, APPLICABLE_RATE_FIELD));
        
        HtmlElement addBtn = getElementByName(budgetRatesPage, RESET_RATES_BUTTON, true);
        budgetRatesPage = clickOn(addBtn);

        assertEquals(oldApplicableRate, getFieldValue(budgetRatesPage, APPLICABLE_RATE_FIELD));
    }
    
    /**
     * Create a new proposal development document and navigate to budget versions page.
     * 
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage getBudgetVersionsPage() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                DEFAULT_PROPOSAL_REQUESTED_START_DATE,
                DEFAULT_PROPOSAL_REQUESTED_END_DATE,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                DEFAULT_PROPOSAL_TYPE_CODE,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        HtmlPage budgetVersionsPage = clickOn(proposalPage, PDDOC_BUDGET_VERSIONS_LINK_NAME);
        return budgetVersionsPage;
    }

    /**
     * Add a new budget version to the given budgetVersionsPage
     * 
     * @param budgetVersionsPage
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage addBudgetVersion(HtmlPage budgetVersionsPage) throws Exception {
        setFieldValue(budgetVersionsPage, NEW_BUDGET_VERSION_NAME, "Test Budget Version - 1");
        HtmlElement addBtn = getElementByName(budgetVersionsPage, ADD_BUDGET_VERSION_BUTTON, true);
        budgetVersionsPage = clickOn(addBtn);
        return budgetVersionsPage;
    }

}
