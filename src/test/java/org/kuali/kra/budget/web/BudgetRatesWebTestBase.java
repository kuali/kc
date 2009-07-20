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


import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;


public abstract class BudgetRatesWebTestBase extends ProposalDevelopmentWebTestBase {
    private HtmlPage budgetRatesPage;
    private static final String NEW_BUDGET_VERSION_NAME = "newBudgetVersionName";
    private static final String ADD_BUDGET_VERSION_BUTTON = "methodToCall.addBudgetVersion";
    private static final String PDDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    private static final String BDOC_BUDGET_RATES_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.rates.x";
    private static final String APPLICABLE_RATE_ELEMENT_NAME_START = "document.budgetProposalRates[";

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "005894";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_REQUESTED_START_DATE = "01/01/2002";
    private static final String DEFAULT_PROPOSAL_REQUESTED_END_DATE = "12/31/2005";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_TYPE_CODE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "000001";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        HtmlPage proposalBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage budgetVersionsPage = addBudgetVersion(proposalBudgetVersionsPage);
        /* get budget rates page */
        budgetRatesPage = clickOn(budgetVersionsPage, BDOC_BUDGET_RATES_LINK_NAME);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    public HtmlPage getBudgetRatesPage() {
        return budgetRatesPage;
    }


    /**
     * Create a new proposal development document and navigate to budget versions page.
     * 
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage getBudgetVersionsPage() throws Exception {
        HtmlPage proposalPage = getProposalDevelopmentPage();
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
     * Add a new budget version to the given budgetVersionsPage, and open the budget.
     * 
     * @param budgetVersionsPage
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage addBudgetVersion(HtmlPage budgetVersionsPage) throws Exception {
        setFieldValue(budgetVersionsPage, NEW_BUDGET_VERSION_NAME, "Test Budget Version - 1");
        HtmlElement addBtn = getElementByName(budgetVersionsPage, ADD_BUDGET_VERSION_BUTTON, true);
        budgetVersionsPage = clickOn(addBtn);
        HtmlElement openBtn = getElementByName(budgetVersionsPage, "methodToCall.openBudgetVersion.line0.x");
        budgetVersionsPage = clickOn(openBtn);
        return budgetVersionsPage;
    }
    
    /**
     * Save and return to rates page.
     * Save in rates page will redirect to summary tab
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage saveAndReturnRatesPage(HtmlPage ratesPage) throws Exception {
        HtmlPage summaryPage = clickOn(ratesPage, "methodToCall.save");
        /* back to rates page again. Save in rates page will redirect to summary tab */
        HtmlPage newRatesPage = clickOn(summaryPage, BDOC_BUDGET_RATES_LINK_NAME);
        return newRatesPage;
        
    }

    protected String getFirstRatesIDForGivenTable(HtmlPage page, String tableName) throws Exception{
        HtmlTable table = getTable(page, tableName);
        assertNotNull(table);
        String applicableRateId = null;
        Iterator iterator = table.getAllHtmlChildElements();
        while (iterator.hasNext()) {
            HtmlElement child = (HtmlElement) iterator.next();
            if(child.getId().startsWith(APPLICABLE_RATE_ELEMENT_NAME_START)) {
                applicableRateId = child.getId();
                break;
            }
        }
        return applicableRateId;
    }

}
