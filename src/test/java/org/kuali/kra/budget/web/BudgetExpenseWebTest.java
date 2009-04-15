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

import org.junit.Test;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetExpenseWebTest extends ProposalDevelopmentWebTestBase {
    private static final String PDDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    private static final String BDOC_BUDGET_EXPENSE_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.expenses.x";

    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    protected static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    
    private static final String NEW_BUDGET_VERSION_NAME = "newBudgetVersionName";
    private static final String ADD_BUDGET_VERSION_BUTTON = "methodToCall.addBudgetVersion";
    
    @Test
    public void testBudgetExpensePage() throws Exception {        
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();        

        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);        
        HtmlPage budgetExpensePage = getBudgetExpensesPage(bBudgetVersionsPage);
        
        assertContains(budgetExpensePage,"Budget Overview (Period 1)");
        assertContains(budgetExpensePage,"Select Budget Period:");
        assertContains(budgetExpensePage,"Personnel");
        assertContains(budgetExpensePage,"Equipment");
        assertContains(budgetExpensePage,"Travel");
        assertContains(budgetExpensePage,"Participant Support");
        assertContains(budgetExpensePage,"Other Direct");    
    }
    
    @Test
    public void testBudgetExpenseAddOneBudgetLineItemAndSave() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetExpensePage = getBudgetExpensesPage(bBudgetVersionsPage);
        
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "5");
        
        final HtmlForm form2 = (HtmlForm) budgetExpensePage.getForms().get(0);        
        String completeButtonName=getImageTagName(budgetExpensePage, "methodToCall.addBudgetLineItem");        
        final HtmlImageInput button = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page2 = (HtmlPage) button.click();
        
        HtmlPage page3 = clickOn(page2, "methodToCall.save");
        assertDoesNotContain(page3, ERRORS_FOUND_ON_PAGE);
        assertContains(page3,SAVE_SUCCESS_MESSAGE);
    }
        
    @Test
    public void testBudgetExpenseDeleteOneBudgetLineItemAndSave() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetExpensePage = getBudgetExpensesPage(bBudgetVersionsPage);
        
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "5");
        
        final HtmlForm form2 = (HtmlForm) budgetExpensePage.getForms().get(0);
        String completeButtonName=getImageTagName(budgetExpensePage, "methodToCall.addBudgetLineItem");
        final HtmlImageInput button = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page2 = (HtmlPage) button.click();
        
        HtmlPage page3 = clickOn(page2, "methodToCall.save");
        assertDoesNotContain(page3, ERRORS_FOUND_ON_PAGE);
        assertContains(page3,SAVE_SUCCESS_MESSAGE);
        
        final HtmlForm form3 = (HtmlForm) page3.getForms().get(0);
        completeButtonName=getImageTagName(page3, "methodToCall.deleteBudgetLineItem");
        final HtmlImageInput button1 = (HtmlImageInput) form3.getInputByName(completeButtonName);
        HtmlPage page4 = (HtmlPage) button1.click();
        
        HtmlPage page5 = clickOn(page4, "methodToCall.save");
        assertDoesNotContain(page5, ERRORS_FOUND_ON_PAGE);
        assertContains(page5,SAVE_SUCCESS_MESSAGE);        
    }
    
    @Test
    public void testBudgetExpenseAddMultipleBudgetLineItemsToSameCategoryTypeAndSave() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetExpensePage = getBudgetExpensesPage(bBudgetVersionsPage);
        
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "5");
        
        final HtmlForm form2 = (HtmlForm) budgetExpensePage.getForms().get(0);
        String completeButtonName = "methodToCall.addBudgetLineItem.budgetCategoryTypeCodeE.catTypeIndex0.anchorEquipment";
        final HtmlImageInput button = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page2 = (HtmlPage) button.click();
        
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem2");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "5");
        
        completeButtonName = "methodToCall.addBudgetLineItem.budgetCategoryTypeCodeE.catTypeIndex0.anchorEquipment";
        
        final HtmlImageInput button1 = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page3 = (HtmlPage) button1.click();
        
        HtmlPage page4 = clickOn(page3, "methodToCall.save");
        assertDoesNotContain(page4, ERRORS_FOUND_ON_PAGE);
        assertContains(page4,SAVE_SUCCESS_MESSAGE);
    }
    
    @Test
    public void testBudgetExpenseDeleteMultipleBudgetLinesToSameCategoryTypeAndSave() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetExpensePage = getBudgetExpensesPage(bBudgetVersionsPage);
        
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "5");
        
        final HtmlForm form2 = (HtmlForm) budgetExpensePage.getForms().get(0);        
        String completeButtonName = "methodToCall.addBudgetLineItem.budgetCategoryTypeCodeE.catTypeIndex0.anchorEquipment";        
        final HtmlImageInput button = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page2 = (HtmlPage) button.click();
        
        setFieldValue(page2, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(page2, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem2");
        setFieldValue(page2, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(page2, "newBudgetLineItems[0].lineItemCost", "5");
        
        final HtmlForm form3 = (HtmlForm) page2.getForms().get(0);  
        completeButtonName = "methodToCall.addBudgetLineItem.budgetCategoryTypeCodeE.catTypeIndex0.anchorEquipment";        
        final HtmlImageInput button1 = (HtmlImageInput) form3.getInputByName(completeButtonName);
        HtmlPage page3 = (HtmlPage) button1.click();
        
        HtmlPage page4 = clickOn(page3, "methodToCall.save");
        assertDoesNotContain(page4, ERRORS_FOUND_ON_PAGE);
        assertContains(page4,SAVE_SUCCESS_MESSAGE);
        System.out.println(page4.asXml().replaceAll(" ", "")); 
        
        final HtmlForm form4 = (HtmlForm) page4.getForms().get(0);
        completeButtonName = "methodToCall.deleteBudgetLineItem.line0.anchor2";
        final HtmlImageInput button2 = (HtmlImageInput) form4.getInputByName(completeButtonName);
        HtmlPage page5 = (HtmlPage) button2.click();

        final HtmlForm form5 = (HtmlForm) page5.getForms().get(0);
        completeButtonName = "methodToCall.deleteBudgetLineItem.line0.anchor2";
        final HtmlImageInput button3 = (HtmlImageInput) form5.getInputByName(completeButtonName);
        HtmlPage page6 = (HtmlPage) button3.click();
        
        HtmlPage page7 = clickOn(page6, "methodToCall.save");
        assertDoesNotContain(page7, ERRORS_FOUND_ON_PAGE);
        assertContains(page7,SAVE_SUCCESS_MESSAGE); 
    }
    
    @Test
    public void testBudgetExpenseAddMultipleBudgetLineItemsToDifferentCategoryTypeAndSave() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetExpensePage = getBudgetExpensesPage(bBudgetVersionsPage);
        
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "5");
        
        final HtmlForm form2 = (HtmlForm) budgetExpensePage.getForms().get(0);
        String completeButtonName=getImageTagName(budgetExpensePage, "methodToCall.addBudgetLineItem");
        final HtmlImageInput button = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page2 = (HtmlPage) button.click();
        
        setFieldValue(budgetExpensePage, "newBudgetLineItems[1].costElement", "420050");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[1].lineItemDescription", "TravelLineItem");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[1].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[1].lineItemCost", "5");
        
        completeButtonName="methodToCall.addBudgetLineItem.budgetCategoryTypeCodeT.catTypeIndex1.anchorTravel";
        final HtmlImageInput button1 = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page3 = (HtmlPage) button1.click();
        HtmlPage page4 = clickOn(page3, "methodToCall.save");
        assertDoesNotContain(page4, ERRORS_FOUND_ON_PAGE);
        assertContains(page4,SAVE_SUCCESS_MESSAGE);
    }
    
    @Test
    public void testBudgetExpenseDeleteMultipleBudgetLinesToDifferentCategoryTypeAndSave() throws Exception {
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetExpensePage = getBudgetExpensesPage(bBudgetVersionsPage);
                
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "421809");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemDescription", "EquipmentLineItem");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].quantity", "10");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "5");
        
        final HtmlForm form2 = (HtmlForm) budgetExpensePage.getForms().get(0);        
        //String completeButtonName=getImageTagName(budgetExpensePage, "methodToCall.addBudgetLineItem");   
        String completeButtonName = "methodToCall.addBudgetLineItem.budgetCategoryTypeCodeE.catTypeIndex0.anchorEquipment";     
        final HtmlImageInput button = (HtmlImageInput) form2.getInputByName(completeButtonName);
        HtmlPage page2 = (HtmlPage) button.click();
        final HtmlForm form3 = (HtmlForm) page2.getForms().get(0);        
        
        setFieldValue(page2, "newBudgetLineItems[1].costElement", "420050");
        setFieldValue(page2, "newBudgetLineItems[1].lineItemDescription", "TravelLineItem");
        setFieldValue(page2, "newBudgetLineItems[1].quantity", "10");
        setFieldValue(page2, "newBudgetLineItems[1].lineItemCost", "5");
        
        completeButtonName="methodToCall.addBudgetLineItem.budgetCategoryTypeCodeT.catTypeIndex1.anchorTravel";        
        final HtmlImageInput button1 = (HtmlImageInput) form3.getInputByName(completeButtonName);
        HtmlPage page3 = (HtmlPage) button1.click();
        
        HtmlPage page4 = clickOn(page3, "methodToCall.save");
        assertDoesNotContain(page4, ERRORS_FOUND_ON_PAGE);
        assertContains(page4,SAVE_SUCCESS_MESSAGE);
        
        final HtmlForm form4 = (HtmlForm) page4.getForms().get(0);
        //completeButtonName=getImageTagName(page4, "methodToCall.deleteBudgetLineItem");  
        completeButtonName = "methodToCall.deleteBudgetLineItem.line0.anchor2";
        final HtmlImageInput button2 = (HtmlImageInput) form3.getInputByName(completeButtonName);
        HtmlPage page5 = (HtmlPage) button2.click();
        
        //completeButtonName="methodToCall.deleteBudgetLineItem.line0.anchor3";
        completeButtonName = "methodToCall.deleteBudgetLineItem.line0.anchor2";
        final HtmlForm form5 = (HtmlForm) page5.getForms().get(0);        
        final HtmlImageInput button3 = (HtmlImageInput) form5.getInputByName(completeButtonName);
        HtmlPage page6 = (HtmlPage) button3.click();
        
        HtmlPage page7 = clickOn(page6, "methodToCall.save");
        assertDoesNotContain(page7, ERRORS_FOUND_ON_PAGE);
        assertContains(page7,SAVE_SUCCESS_MESSAGE); 
    }
    
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
        HtmlElement openBtn = getElementByName(budgetVersionsPage, "methodToCall.openBudgetVersion.line0.x");
        budgetVersionsPage = clickOn(openBtn);
        return budgetVersionsPage;
    }
    
    /**
     * Returns the expense page by clicking the expense tab link
     * 
     * @return HtmlPage
     * @throws Exception
     */
    protected HtmlPage getBudgetExpensesPage(HtmlPage htmlPage) throws Exception {
        return clickOn(htmlPage, BDOC_BUDGET_EXPENSE_LINK_NAME);
    }
    

    private String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        int idx1 = page.asXml().indexOf(uniqueNamePrefix);
        //int idx2 = page.asXml().indexOf(".((##)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).anchor", idx1);
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }

}
