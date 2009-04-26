/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.bo.ValidCalcType;
import org.kuali.kra.budget.bo.ValidCeRateType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.test.KraUnitTestClassRunner;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(KraUnitTestClassRunner.class)
public class BudgetPersonnelExpenseWebTest extends BudgetExpenseWebTest {
    private static final String BDOC_BUDGET_PERSONNEL_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.personnel.x";
    private static final String BDOC_ADD_PERSONNEL_EXPENSE_LINK_NAME = "methodToCall.addBudgetLineItem.budgetCategoryTypeCodeP.catTypeIndex0.anchorPersonnel";
    private static final String PERSONNEL_BUDGET_IMAGE_NAME = "methodToCall.personnelBudget.line0.anchor2";
    private static final String ADD_PERSONNEL_BUDGET_IMAGE_NAME = "methodToCall.addBudgetPersonnelDetails.anchorPersonnelBudget";
    private static final String BUDGET_RETURN_TO_EXPENSES_LINK_NAME = "methodToCall.returnToExpenses";
    
    @Test
    public void testFullFlow() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        HtmlForm proposalForm = (HtmlForm) proposalPage.getForms().get(0);
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) proposalForm.getInputByName("document.documentHeader.documentNumber");
        
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetPersonnelPage = getBudgetPersonnelPage(bBudgetVersionsPage);
        
        assignBudgetPersonnel(budgetPersonnelPage);
        HtmlPage budgetExpensePage = getBudgetExpensesPage(budgetPersonnelPage);
        assignPersonnelExpenses(budgetExpensePage);
        
        HtmlPage page3 = clickOn(budgetExpensePage, "methodToCall.save");
        assertDoesNotContain(page3, ERRORS_FOUND_ON_PAGE);
        assertContains(page3,SAVE_SUCCESS_MESSAGE);
        
        ProposalDevelopmentDocument proposalDoc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(proposalDoc);
        List<BudgetVersionOverview> budgetVersions = proposalDoc.getBudgetVersionOverviews();
        assertNotNull(budgetVersions);
        assertEquals(1, budgetVersions.size());
        BudgetVersionOverview firstVersion = budgetVersions.get(0);
        assertNotNull(firstVersion);
        BudgetDocument budgetDoc = (BudgetDocument) documentService.getByDocumentHeaderId(firstVersion.getDocumentNumber());
        assertNotNull(budgetDoc);
        
        List<BudgetPeriod> periods = budgetDoc.getBudgetPeriods();
        assertNotNull(periods);
        assertEquals(3, periods.size());
        
        BudgetPeriod firstPeriod = periods.get(0);
        List<BudgetLineItem> firstPeriodLineItems = firstPeriod.getBudgetLineItems();
        assertEquals(1, firstPeriodLineItems.size());
        
        BudgetLineItem firstPeriodFirstLineItem = firstPeriodLineItems.get(0);
        assertNotNull(firstPeriodFirstLineItem);
        
        List<BudgetPerson> budgetPersonnel = budgetDoc.getBudgetPersons();
        assertNotNull(budgetPersonnel);
        assertEquals(2, budgetPersonnel.size());
        
        BudgetPerson firstBudgetPerson = budgetPersonnel.get(0);
        assertEquals("000000003", firstBudgetPerson.getPersonId());
        assertEquals("AA025", firstBudgetPerson.getJobCode());
        assertEquals("7", firstBudgetPerson.getAppointmentTypeCode());
        assertEquals(firstPeriod.getStartDate(), firstBudgetPerson.getEffectiveDate());
        assertEquals(new BudgetDecimal("120000"), firstBudgetPerson.getCalculationBase());
        assertEquals(firstPeriod.getStartDate(), firstBudgetPerson.getStartDate());
        assertEquals("400025", firstPeriodFirstLineItem.getCostElement());
        
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts = firstPeriodFirstLineItem.getBudgetLineItemCalculatedAmounts();
        assertNotNull(lineItemCalculatedAmounts);
        Map<String, BudgetPersonnelCalculatedAmount> expectedLineItemCalcAmounts = getExpectedLineItemCalculatedAmounts(firstPeriodFirstLineItem);
        assertEquals(expectedLineItemCalcAmounts.size(), lineItemCalculatedAmounts.size());
        
        for(BudgetLineItemCalculatedAmount lineItemCalcAmt: lineItemCalculatedAmounts) {
            BudgetPersonnelCalculatedAmount expected = expectedLineItemCalcAmounts.get(lineItemCalcAmt.getRateClassCode()+ lineItemCalcAmt.getRateTypeCode());
            assertNotNull(expected);
        }
        
        assertNotNull(firstPeriodFirstLineItem.getBudgetPersonnelDetailsList());
        assertEquals(1, firstPeriodFirstLineItem.getBudgetPersonnelDetailsList().size());
        
        BudgetPersonnelDetails firstPersonDetails = firstPeriodFirstLineItem.getBudgetPersonnelDetails(0);
        assertNotNull(firstPersonDetails);
        assertEquals(firstPeriodFirstLineItem.getStartDate(), firstPersonDetails.getStartDate());
        assertEquals(firstPeriodFirstLineItem.getEndDate(), firstPersonDetails.getEndDate());
        assertEquals(new BudgetDecimal("75.00"), firstPersonDetails.getPercentCharged());
        assertEquals(new BudgetDecimal("100.00"), firstPersonDetails.getPercentEffort());;
        assertEquals("3", firstPersonDetails.getPeriodTypeCode());
        
        //Calculated Numbers / Computed Values
        System.out.println(firstPersonDetails.toString());
        assertEquals(new BudgetDecimal("91350.00"), firstPersonDetails.getSalaryRequested());
        assertEquals(new BudgetDecimal("30450.00"), firstPersonDetails.getCostSharingAmount());
        assertEquals(new BudgetDecimal("25.00"), firstPersonDetails.getCostSharingPercent());
        assertEquals(new BudgetDecimal("0.00"), firstPersonDetails.getUnderrecoveryAmount());
        
        List<BudgetPersonnelCalculatedAmount> personnelCalculatedAmounts = firstPersonDetails.getBudgetCalculatedAmounts();
        assertNotNull(personnelCalculatedAmounts);
        assertEquals(lineItemCalculatedAmounts.size(), personnelCalculatedAmounts.size());
        assertFalse(lineItemCalculatedAmounts.get(0).getApplyRateFlag());
    }

    private Map<String, BudgetPersonnelCalculatedAmount> getExpectedLineItemCalculatedAmounts(BudgetLineItem firstPeriodFirstLineItem) {
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();
        Map fieldValues = new HashMap();
        fieldValues.put("costElement", firstPeriodFirstLineItem.getCostElement());
        List<ValidCeRateType> costElementRates = (List<ValidCeRateType>) boService.findMatching(ValidCeRateType.class, fieldValues);
        Map<String, BudgetPersonnelCalculatedAmount> expectedLineItemCalcAmounts = new HashMap<String, BudgetPersonnelCalculatedAmount>(); 
        for(ValidCeRateType ceRateType: costElementRates) {
            if(!ceRateType.getRateClassType().equalsIgnoreCase("I")) {
                if(ceRateType.getRateClassType().equalsIgnoreCase("O") && 
                        ceRateType.getRateClassCode().equalsIgnoreCase("1") && 
                        ceRateType.getRateTypeCode().equalsIgnoreCase("1") ) {
                    expectedLineItemCalcAmounts.put(ceRateType.getRateClassCode()+ceRateType.getRateTypeCode(), newBudgetLineItemCalculatedAmount(ceRateType.getRateClassCode(), ceRateType.getRateTypeCode(), ceRateType.getRateClassType()));
                } else if(!ceRateType.getRateClassType().equalsIgnoreCase("O")) {
                    expectedLineItemCalcAmounts.put(ceRateType.getRateClassCode()+ceRateType.getRateTypeCode(), newBudgetLineItemCalculatedAmount(ceRateType.getRateClassCode(), ceRateType.getRateTypeCode(), ceRateType.getRateClassType()));
                }
            }
        }
        
        List<String> rateClassTypes = new ArrayList<String>();
        rateClassTypes.add("E");
        rateClassTypes.add("V");
        
        fieldValues.clear(); 
        fieldValues.put("rateClassType", rateClassTypes);
        List<ValidCalcType> calcTypes = (List<ValidCalcType>) boService.findMatching(ValidCalcType.class, fieldValues);
        for(ValidCalcType calcType: calcTypes) {
            expectedLineItemCalcAmounts.put(calcType.getRateClassCode()+calcType.getRateTypeCode(), newBudgetLineItemCalculatedAmount(calcType.getRateClassCode(), calcType.getRateTypeCode(), calcType.getRateClassType()));
        }
        
        return expectedLineItemCalcAmounts;
    }
    
    private BudgetPersonnelCalculatedAmount newBudgetLineItemCalculatedAmount(String rateClassCode, String rateTypeCode,
            String rateClassType) {
            BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmt = new BudgetPersonnelCalculatedAmount();
            budgetPersonnelCalculatedAmt.setRateClassType(rateClassType);
            budgetPersonnelCalculatedAmt.setRateClassCode(rateClassCode);
            budgetPersonnelCalculatedAmt.setRateTypeCode(rateTypeCode);
            budgetPersonnelCalculatedAmt.setApplyRateFlag(true);
            budgetPersonnelCalculatedAmt.refreshReferenceObject("rateType");
            budgetPersonnelCalculatedAmt.refreshReferenceObject("rateClass");
            
            return budgetPersonnelCalculatedAmt;
    }

    private void assignBudgetPersonnel(HtmlPage budgetPersonnelPage) throws Exception {
        budgetPersonnelPage = multiLookup(budgetPersonnelPage, "org.kuali.kra.bo.Person", "personId", "000000003");
        setFieldValue(budgetPersonnelPage,"document.budgetPerson[0].jobCode", "AA025");
        setFieldValue(budgetPersonnelPage,"document.budgetPerson[0].appointmentTypeCode", "7");
        setFieldValue(budgetPersonnelPage,"document.budgetPerson[0].calculationBase", "120000");
        clickOn(budgetPersonnelPage, "methodToCall.save");
        
        budgetPersonnelPage = multiLookup(budgetPersonnelPage, "org.kuali.kra.bo.Person", "personId", "000000008");
        setFieldValue(budgetPersonnelPage,"document.budgetPerson[1].jobCode", "AA025");
        setFieldValue(budgetPersonnelPage,"document.budgetPerson[1].appointmentTypeCode", "7");
        setFieldValue(budgetPersonnelPage,"document.budgetPerson[1].calculationBase", "120000");
        clickOn(budgetPersonnelPage, "methodToCall.save");
    }

    protected void setDefaultRequiredFields(HtmlPage page) {
        setRequiredFields(page, "TestPersonnelExpenses-1",
                                "000162",
                                "TestPersonnelExpenses-1",
                                "01/01/2007",
                                "12/31/2009",
                                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                                DEFAULT_PROPOSAL_TYPE_CODE,
                                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
    }
    
    protected HtmlPage getBudgetPersonnelPage(HtmlPage htmlPage) throws Exception {
        return clickOn(htmlPage, BDOC_BUDGET_PERSONNEL_LINK_NAME);
    }
    
    private void assignPersonnelExpenses(HtmlPage budgetExpensePage) throws Exception {
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].costElement", "400025");
        setFieldValue(budgetExpensePage, "newBudgetLineItems[0].lineItemCost", "240000");
        budgetExpensePage = clickOn(budgetExpensePage, BDOC_ADD_PERSONNEL_EXPENSE_LINK_NAME);
        setFieldValue(budgetExpensePage, "document.budgetPeriods[0].budgetLineItems[0].budgetLineItemCalculatedAmounts[0].applyRateFlag", "off");
        budgetExpensePage = clickOn(budgetExpensePage, "methodToCall.save");
        budgetExpensePage = clickOn(budgetExpensePage, PERSONNEL_BUDGET_IMAGE_NAME);

        setFieldValue(budgetExpensePage, "newBudgetPersonnelDetails.personSequenceNumber", "1");
        setFieldValue(budgetExpensePage, "newBudgetPersonnelDetails.periodTypeCode", "3");
        setFieldValue(budgetExpensePage, "newBudgetPersonnelDetails.percentEffort", "100");
        setFieldValue(budgetExpensePage, "newBudgetPersonnelDetails.percentCharged", "75");
        budgetExpensePage = clickOn(budgetExpensePage, ADD_PERSONNEL_BUDGET_IMAGE_NAME);
        
        clickOn(budgetExpensePage, "methodToCall.save");
        clickOn(budgetExpensePage, "methodToCall.calculateSalary.line0.anchor2");
        clickOn(budgetExpensePage, "methodToCall.save");
        budgetExpensePage = clickOn(budgetExpensePage, BUDGET_RETURN_TO_EXPENSES_LINK_NAME);
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
