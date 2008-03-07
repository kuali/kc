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

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetSummaryWebTest extends ProposalDevelopmentWebTestBase {
    private static final String PDDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    private static final String BDOC_BUDGET_SUMMARY_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.summary.x";
    private static final String NEW_BUDGET_VERSION_NAME = "newBudgetVersionName";
    private static final String ADD_BUDGET_PERIOD_BUTTON = "methodToCall.addBudgetPeriod";
    private static final String DEL_BUDGET_PERIOD_BUTTON = "methodToCall.deleteBudgetPeriod.line1.anchor1";
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
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "IN-CARD";
    private static final String PERIOD2_START_DATE = "01/01/2001";
    private static final String PERIOD2_END_DATE = "12/31/2001";
    private static final String ADD_ERROR_MESSAGE = "New Period start date should be greater than Period 1 end date and less than Period 2 start date";
    
    @Test
    public void testSaveBudgetSummary() throws Exception {
        /* get budget version page in proposal development module */
        HtmlPage proposalBudgetVersionsPage = getBudgetVersionsPage();
        /* add new version and open budget version page in budget module */
        HtmlPage budgetVersionsPage = addBudgetVersion(proposalBudgetVersionsPage);
        /* get budget summary page */
        HtmlPage budgetSummaryPage = clickOn(budgetVersionsPage, BDOC_BUDGET_SUMMARY_LINK_NAME);

        /* check proposal start and end dates set in summary page */
        assertContains(budgetSummaryPage, DEFAULT_PROPOSAL_REQUESTED_START_DATE);
        assertContains(budgetSummaryPage, DEFAULT_PROPOSAL_REQUESTED_END_DATE);

        /* check default period initialized */
        assertContains(budgetSummaryPage, PERIOD2_START_DATE);
        assertContains(budgetSummaryPage, PERIOD2_END_DATE);
        
        HtmlPage saveBudgetSummaryPage = saveDoc(budgetSummaryPage);
        assertDoesNotContain(saveBudgetSummaryPage, ERRORS_FOUND_ON_PAGE);
        assertContains(saveBudgetSummaryPage, SAVE_SUCCESS_MESSAGE);
        
    }
    
    @Test
    public void testDelBudgetPeriod() throws Exception {
        HtmlPage budgetSummaryPage = delBudgetPeriod();
    }
    
    @Test
    public void testValidateAddBudgetPeriod() throws Exception {
        HtmlPage budgetSummaryPage = getBudgetSummaryAndPeriodPage();
        setFieldValue(budgetSummaryPage, ADD_NEW_BUDGET_START, PERIOD2_START_DATE);
        setFieldValue(budgetSummaryPage, ADD_NEW_BUDGET_END, PERIOD2_END_DATE);
        HtmlElement addBtn = getElementByName(budgetSummaryPage, ADD_BUDGET_PERIOD_BUTTON, true);
        budgetSummaryPage = clickOn(addBtn);
        assertContains(budgetSummaryPage, ADD_ERROR_MESSAGE);
    }

    @Test
    public void testDelAndAddBudgetPeriod() throws Exception {
        HtmlPage budgetSummaryPage = delBudgetPeriod();
        setFieldValue(budgetSummaryPage, ADD_NEW_BUDGET_START, PERIOD2_START_DATE);
        setFieldValue(budgetSummaryPage, ADD_NEW_BUDGET_END, PERIOD2_END_DATE);
        HtmlElement addBtn = getElementByName(budgetSummaryPage, ADD_BUDGET_PERIOD_BUTTON, true);
        budgetSummaryPage = clickOn(addBtn);
        assertContains(budgetSummaryPage, PERIOD2_START_DATE);
        assertContains(budgetSummaryPage, PERIOD2_END_DATE);
    }
    
    protected HtmlPage delBudgetPeriod() throws Exception {
        HtmlPage budgetSummaryPage = getBudgetSummaryAndPeriodPage();
        HtmlElement delBtn = getElementByName(budgetSummaryPage, DEL_BUDGET_PERIOD_BUTTON, true);
        HtmlPage budgetDelSummaryPage = clickOn(delBtn);
        assertDoesNotContain(budgetDelSummaryPage, PERIOD2_START_DATE);
        assertDoesNotContain(budgetDelSummaryPage, PERIOD2_END_DATE);
        HtmlPage saveBudgetSummaryPage = saveDoc(budgetDelSummaryPage);
        return saveBudgetSummaryPage;
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
     * Create a new proposal development document and navigate to budget summary page.
     * 
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage getBudgetSummaryAndPeriodPage() throws Exception {
        /* get budget version page in proposal development module */
        HtmlPage proposalBudgetVersionsPage = getBudgetVersionsPage();
        /* add new version and open budget version page in budget module */
        HtmlPage budgetVersionsPage = addBudgetVersion(proposalBudgetVersionsPage);
        /* get budget summary page */
        HtmlPage budgetSummaryPage = clickOn(budgetVersionsPage, BDOC_BUDGET_SUMMARY_LINK_NAME);
        return budgetSummaryPage;
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
