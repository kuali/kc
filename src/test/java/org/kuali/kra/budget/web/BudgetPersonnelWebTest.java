/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.kuali.kra.logging.BufferedLogger.info;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.test.lifecycles.TransactionalLifecycle;

import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test class containing tests that deal with the "Project Personnel" tab of a {@link BudgetDocument}. The "Project Personnel" contains
 * {@link BudgetPerson} instances that have relationships with other tabs. Those relationships need to be tested as well as relationships
 * with the {@link ProposalDevelopmentDocument}
 *
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 */
/*@PerTestUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_PERIOD_TYPE.sql", delimiter = ";")
            })
    )*/
public class BudgetPersonnelWebTest extends BudgetWebTestBase {
    private static final String PERSONNEL_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.personnel.x";
    private static final String EXPENSES_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.expenses.x";
    private static final String DELETE_PERSON_IMAGE_NAME = "methodToCall.deleteBudgetPerson.line0.x";
    private static final String YES_BTN_ID =  "methodToCall.processAnswer.button0";
    private static final String ADD_PERSONNEL_EXPENSE_IMAGE_NAME = "methodToCall.addBudgetLineItem.budgetCategoryTypeCodeP.catTypeIndex0.anchorPersonnel";
    private static final String ADD_PERSONNEL_BUDGET_IMAGE_NAME = "methodToCall.addBudgetPersonnelDetails.anchorPersonnelBudget";
    private static final String PERSONNEL_BUDGET_IMAGE_NAME = "methodToCall.personnelBudget.line0.anchor2";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String ERRORS_DELETE_WITH_PERSONNEL_BUDGET = "Can not delete Geoff McGregor with associated details in the budget personnel line items";
    private TransactionalLifecycle transactionalLifecycle;

    private HtmlPage currentBudgetPage;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        currentBudgetPage = addBudgetVersion();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Adds a person to a Budget Version document, then tries to delete it.
     *
     */
    @Test
    public void addAndDeletePersonnel() throws Exception {
        assignBudgetPersonnel();
        navigateToBudgetPersonnel();
        deleteFirstBudgetPerson();

        info(currentBudgetPage.asText());
    }

    /**    
     *
     * @see org.kuali.kra.budget.bo.BudgetPerson
     * @see org.kuali.kra.budget.bo.BudgetLineItem;
     */
    /*
     * This test is modified and renamed to account for changes done in KRACOEUS-1528.
     */
    @Test
    public void deleteWithPersonnelExpenseShouldFail() throws Exception {        
        assignBudgetPersonnel();
        assignPersonnelExpenses();
        navigateToBudgetPersonnel();        
        currentBudgetPage = clickOn(currentBudgetPage, DELETE_PERSON_IMAGE_NAME);
        assertContains(currentBudgetPage, ERRORS_DELETE_WITH_PERSONNEL_BUDGET);
    }

    /**
     * Uses the immutable {@link HtmlPage} instance <code>currentBudgetPage</code>, and adds 
     * personnel to the page.
     *
     */
    private void assignBudgetPersonnel() throws Exception {
        navigateToBudgetPersonnel();
        currentBudgetPage = multiLookup(currentBudgetPage, "org.kuali.kra.bo.Person", "personId", "000000003");
        setFieldValue(currentBudgetPage,"document.budgetPersons[0].jobCode", "AA004");
        setFieldValue(currentBudgetPage,"document.budgetPerson[0].appointmentTypeCode", "1");
        setFieldValue(currentBudgetPage,"document.budgetPerson[0].calculationBase", "100");
    }

    /**
     * Deletes the first person on a budget personnel page. This assumes the <code>currentBudgetPage</code> is the budget personnel page. 
     * The Budget Personnel tag  requests confirmation when deleting a person. This will handle deleting on confirmation.
     *
     */
    private void deleteFirstBudgetPerson() throws Exception {
        currentBudgetPage = clickOn(currentBudgetPage, DELETE_PERSON_IMAGE_NAME);        
        currentBudgetPage = clickOn(currentBudgetPage, YES_BTN_ID);
    }

    /**
     * Uses HtmlUnit to navigate to the BudgetPersonnelPage and <code>currentBudgetPage</code> to it.
     */
    private void navigateToBudgetPersonnel() throws Exception {
            currentBudgetPage = clickOn(currentBudgetPage, PERSONNEL_IMAGE_NAME);
    }

    /**
     * Uses HtmlUnit to navigate to the Budget Expenses Page and <code>currentBudgetPage</code> to it.
     */
    private void navigateToExpenses() throws Exception {
        currentBudgetPage = clickOn(currentBudgetPage, EXPENSES_IMAGE_NAME);
    }

    /**
     * Uses the immutable {@link HtmlPage} instance <code>currentBudgetPage</code> to navigate to the budget
     * expenses tab, and adds a personnel expense for the already added personnel to the page.
     *
     */
    private void assignPersonnelExpenses() throws Exception {
        currentBudgetPage = clickOn(currentBudgetPage, EXPENSES_IMAGE_NAME);
        setFieldValue(currentBudgetPage, "newBudgetLineItems[0].costElement", "400005");
        setFieldValue(currentBudgetPage, "newBudgetLineItems[0].lineItemCost", "100");
        currentBudgetPage = clickOn(currentBudgetPage, ADD_PERSONNEL_EXPENSE_IMAGE_NAME);
        currentBudgetPage = clickOn(currentBudgetPage, PERSONNEL_BUDGET_IMAGE_NAME);
        assertContains(currentBudgetPage,"Personnel Budget");
        setFieldValue(currentBudgetPage, "newBudgetPersonnelDetails.personSequenceNumber", "1");
        setFieldValue(currentBudgetPage, "newBudgetPersonnelDetails.periodTypeCode", "2");
        currentBudgetPage = clickOn(currentBudgetPage, ADD_PERSONNEL_BUDGET_IMAGE_NAME);
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