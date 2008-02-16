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
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * Tests the Budget Versions page for Proposal Development Document and Budget Document.
 */
public class BudgetVersionsWebTest extends ProposalDevelopmentWebTestBase {
    
    private static final String PDDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    private static final String BDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.versions.x";
    private static final String NEW_BUDGET_VERSION_NAME = "newBudgetVersionName";
    private static final String FINAL_BUDGET_VERSION = "finalBudgetVersion";
    private static final String B_FIRST_BUDGET_STATUS = "document.proposal.budgetVersionOverview[" + 0 + "].budgetStatus";
    private static final String PD_FIRST_BUDGET_STATUS = "document.budgetVersionOverview[" + 0 + "].budgetStatus";
    
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    
    private static final String BUDGET_VERSIONS_TABLE = "budget-versions-table";
    
    private static final String ADD_BUDGET_VERSION_BUTTON = "methodToCall.addBudgetVersion";
    
    /***********************************************************************
     * Unit Tests for Budget Versions
     ***********************************************************************/
    
    /**
     * Test adding a new budget version.
     * 
     * @throws Exception
     */
    @Test
    public void testAddBudgetVersion() throws Exception {
        
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlElement proposalNumber = getElementByName(pdBudgetVersionsPage, "proposalDevelopmentDocument.proposalNumber");
        String pdDocNbr = getDocNbr(pdBudgetVersionsPage);
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        
        // Verify the budget version is there.
        HtmlTable table = getTable(bBudgetVersionsPage, BUDGET_VERSIONS_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 3);
        
        String budgetStatusIncompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE).getParameterValue();
        
        // Verify the fields are correct.
        HtmlElement bProposalNumber = getElementByName(bBudgetVersionsPage, "budgetDocument.proposalNumber");
        assertEquals(proposalNumber, bProposalNumber);
        
        String budgetStatus = getFieldValue(bBudgetVersionsPage, B_FIRST_BUDGET_STATUS);
        assertEquals(budgetStatus, budgetStatusIncompleteCode);
        
        String finalVersion = getFieldValue(bBudgetVersionsPage, FINAL_BUDGET_VERSION);
        assertNull("final version is: " + finalVersion, finalVersion);
        
        bBudgetVersionsPage = addBudgetVersion(bBudgetVersionsPage);
        
        // Check proposal development doc
        closeDoc(bBudgetVersionsPage);
        HtmlPage proposalPage = docSearch(pdDocNbr);
        pdBudgetVersionsPage = clickOn(proposalPage, PDDOC_BUDGET_VERSIONS_LINK_NAME);
        
        HtmlTable pdTable = getTable(pdBudgetVersionsPage, BUDGET_VERSIONS_TABLE);
        assertTrue("row count is " + pdTable.getRowCount(), pdTable.getRowCount() == 4);
    }
    
    /**
     * Test saving the budget versions page in an invalid state - page should display an error message and not save.
     * 
     * @throws Exception
     */
    @Test
    public void testSaveBudgetVersionsInvalid() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        
        String budgetStatusCompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();
           
        // Saving with version marked complete but not final causes error.
        setFieldValue(bBudgetVersionsPage, B_FIRST_BUDGET_STATUS, budgetStatusCompleteCode);
        HtmlPage savedPage = saveDoc(bBudgetVersionsPage);
        assertContains(savedPage, ERRORS_FOUND_ON_PAGE);
        assertDoesNotContain(savedPage, SAVE_SUCCESS_MESSAGE);
    }
    
    /**
     * Test saving the budget versions page in a valid state.
     * 
     * @throws Exception
     */
    @Test
    public void testSaveBudgetVersionsValid() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        String bDocNbr = getDocNbr(bBudgetVersionsPage);
        
        String budgetStatusCompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();
           
        setFieldValue(bBudgetVersionsPage, B_FIRST_BUDGET_STATUS, budgetStatusCompleteCode);
        setFieldValue(bBudgetVersionsPage, FINAL_BUDGET_VERSION, "1");
        HtmlPage savedPage = saveDoc(bBudgetVersionsPage);
        assertDoesNotContain(savedPage, ERRORS_FOUND_ON_PAGE);
        assertContains(savedPage, SAVE_SUCCESS_MESSAGE);
        closeDoc(savedPage);
        
        // FIXME These won't work until save proposal status from budget is resolved
//        HtmlPage openPage = docSearch(bDocNbr);
//        openPage = clickOn(openPage, BDOC_BUDGET_VERSIONS_LINK_NAME);
//        String budgetStatus = getFieldValue(openPage, B_FIRST_BUDGET_STATUS);
//        assertEquals("budget status is: " + budgetStatus, budgetStatus, budgetStatusCompleteCode);
//        
//        String finalVersion = getFieldValue(openPage, FINAL_BUDGET_VERSION);
//        assertEquals("final versions is: " + finalVersion, finalVersion, "1");
    }
    
    /**
     * Test opening an existing budget version.
     * 
     * @throws Exception
     */
    @Test
    public void testOpenBudgetVersion() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        String pdDocNbr = getDocNbr(pdBudgetVersionsPage);
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        String budgetDocNbr = getDocNbr(bBudgetVersionsPage);
        
        closeDoc(bBudgetVersionsPage);
        HtmlPage proposalPage = docSearch(pdDocNbr);
        pdBudgetVersionsPage = clickOn(proposalPage, PDDOC_BUDGET_VERSIONS_LINK_NAME);
        bBudgetVersionsPage = openBudgetVersion(pdBudgetVersionsPage, 0);
        
        assertEquals(budgetDocNbr, getDocNbr(bBudgetVersionsPage));
    }
    
    /**
     * Test copying an existing budget version.
     * 
     * @throws Exception
     */
    @Test
    public void testCopyBudgetVersion() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        String pdDocNbr = getDocNbr(pdBudgetVersionsPage);
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        String budgetDocNbr = getDocNbr(bBudgetVersionsPage);
        
        BudgetDocument budgetDocument = (BudgetDocument) getDocument(budgetDocNbr);
        
        bBudgetVersionsPage = copyBudgetVersion(bBudgetVersionsPage, 0);
        
        assertEquals(budgetDocNbr, getCopiedFromDocNbr(bBudgetVersionsPage));
        
        closeDoc(bBudgetVersionsPage);
        HtmlPage proposalPage = docSearch(pdDocNbr);
        pdBudgetVersionsPage = clickOn(proposalPage, PDDOC_BUDGET_VERSIONS_LINK_NAME);
        
        HtmlTable table = getTable(bBudgetVersionsPage, BUDGET_VERSIONS_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 4);
    }
    
    /***********************************************************************
     * Helper methods
     ***********************************************************************/
    
    /**
     * Create a new proposal development document and navigate to budget versions page.
     * 
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage getBudgetVersionsPage() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        this.setDefaultRequiredFields(proposalPage);
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
    
    protected HtmlPage openBudgetVersion(HtmlPage budgetVersionsPage, int versionToOpen) throws Exception {
        HtmlElement openBtn = getElementByName(budgetVersionsPage, "methodToCall.openBudgetVersion.line" + versionToOpen + ".x");
        budgetVersionsPage = clickOn(openBtn);
        return budgetVersionsPage;
    }
    
    protected HtmlPage copyBudgetVersion(HtmlPage budgetVersionsPage, int versionToCopy) throws Exception {
        HtmlElement copyBtn = getElementByName(budgetVersionsPage, "methodToCall.copyBudgetVersion.line" + versionToCopy + ".x");
        budgetVersionsPage = clickOn(copyBtn);
        return budgetVersionsPage;
    }
    
    /**
     * Saves and closes the document and then perform a search for it.
     *
     * @param docPage the document page to close.
     * @return the Abstracts & Attachments web page
     * @throws Exception
     */
    protected HtmlPage saveAndSearch(HtmlPage docPage) throws Exception {
        HtmlPage proposalPage = saveAndSearchDoc(docPage);
        return clickOn(proposalPage, PDDOC_BUDGET_VERSIONS_LINK_NAME);
    }
    
    protected final String getCopiedFromDocNbr(HtmlPage page) {
        HtmlTable table = getTable(page, "headerarea");
        HtmlTableCell cell = table.getCellAt(0, 5);
        return cell.asText().trim();
    }

}
