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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(KraUnitTestClassRunner.class) 
@PerTestUnitTestData(
        @UnitTestData(order = { 
                UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, 
        sqlStatements = {
                      @UnitTestSql("delete from institute_rates"),
                      @UnitTestSql("delete from institute_la_rates")
                      }, 
        sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_new_rates.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/update_institute_rates.sql", delimiter = ";")
                })
        )
    
public class BudgetPersonnelExpenseWebTest extends BudgetExpenseWebTest {
    private static final String BDOC_BUDGET_PERSONNEL_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.personnel.x";
    private static final String BDOC_ADD_PERSONNEL_EXPENSE_LINK_NAME = "methodToCall.addPersonnelLineItem.budgetCategoryTypeCodeP.catTypeIndex0.anchorPersonnelDetail";
    
    @Test
    public void testFullFlow() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        HtmlForm proposalForm = (HtmlForm) proposalPage.getForms().get(0);
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) proposalForm.getInputByName("document.documentHeader.documentNumber");
        
        HtmlPage pdBudgetVersionsPage = getBudgetVersionsPage();
        HtmlPage bBudgetVersionsPage = addBudgetVersion(pdBudgetVersionsPage);
        HtmlPage budgetPersonnelPage = getBudgetPersonnelPage(bBudgetVersionsPage);
        
        assignBudgetPersonnel(budgetPersonnelPage);
        budgetPersonnelPage = clickOn(budgetPersonnelPage, "methodToCall.save");
        assignPersonnelExpenses(budgetPersonnelPage);
        
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
        assertEquals("400040", firstPeriodFirstLineItem.getCostElement());
        
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
        assertEquals(new BudgetDecimal("91350.00"), firstPersonDetails.getSalaryRequested());
        assertEquals(new BudgetDecimal("30450.00"), firstPersonDetails.getCostSharingAmount());
        assertEquals(new BudgetDecimal("25.00"), firstPersonDetails.getCostSharingPercent());
        assertEquals(new BudgetDecimal("0.00"), firstPersonDetails.getUnderrecoveryAmount());
        
        List<BudgetPersonnelCalculatedAmount> personnelCalculatedAmounts = firstPersonDetails.getBudgetCalculatedAmounts();
        assertNotNull(personnelCalculatedAmounts);

        assertEquals(lineItemCalculatedAmounts.size(), personnelCalculatedAmounts.size());
        assertTrue(lineItemCalculatedAmounts.get(0).getApplyRateFlag());

        Collections.sort(lineItemCalculatedAmounts, new LineItemCalcAmountComparator());

        BudgetLineItemCalculatedAmount firstCalculatedAmount = lineItemCalculatedAmounts.get(0);
        assertNotNull(firstCalculatedAmount);
        assertEquals("10", firstCalculatedAmount.getRateClassCode());
        assertEquals("1", firstCalculatedAmount.getRateTypeCode());
        assertEquals(new BudgetDecimal("0.00"), firstCalculatedAmount.getCalculatedCost());
        assertEquals(new BudgetDecimal("0.00"), firstCalculatedAmount.getCalculatedCostSharing());

        BudgetLineItemCalculatedAmount secondCalculatedAmount = lineItemCalculatedAmounts.get(1);
        assertNotNull(secondCalculatedAmount);
        assertEquals("1", secondCalculatedAmount.getRateClassCode());
        assertEquals("1", secondCalculatedAmount.getRateTypeCode());
        assertEquals(new BudgetDecimal("38090.48"), secondCalculatedAmount.getCalculatedCost());
        assertEquals(new BudgetDecimal("12696.83"), secondCalculatedAmount.getCalculatedCostSharing());

        BudgetLineItemCalculatedAmount thirdCalculatedAmount = lineItemCalculatedAmounts.get(2);
        assertNotNull(thirdCalculatedAmount);
        assertEquals("11", thirdCalculatedAmount.getRateClassCode());
        assertEquals("1", thirdCalculatedAmount.getRateTypeCode());
        assertEquals(new BudgetDecimal("0.00"), thirdCalculatedAmount.getCalculatedCost());
        assertEquals(new BudgetDecimal("0.00"), thirdCalculatedAmount.getCalculatedCostSharing());

        BudgetLineItemCalculatedAmount fourthCalculatedAmount = lineItemCalculatedAmounts.get(3);
        assertNotNull(fourthCalculatedAmount);
        assertEquals("12", fourthCalculatedAmount.getRateClassCode());
        assertEquals("1", fourthCalculatedAmount.getRateTypeCode());
        assertEquals(new BudgetDecimal("0.00"), fourthCalculatedAmount.getCalculatedCost());
        assertEquals(new BudgetDecimal("0.00"), fourthCalculatedAmount.getCalculatedCostSharing());


        BudgetLineItemCalculatedAmount fifthCalculatedAmount = lineItemCalculatedAmounts.get(4);
        assertNotNull(fifthCalculatedAmount);
        assertEquals("5", fifthCalculatedAmount.getRateClassCode());
        assertEquals("1", fifthCalculatedAmount.getRateTypeCode());
        assertEquals(new BudgetDecimal("24664.50"), fifthCalculatedAmount.getCalculatedCost());
        assertEquals(new BudgetDecimal("8221.50"), fifthCalculatedAmount.getCalculatedCostSharing());

        BudgetLineItemCalculatedAmount sixthCalculatedAmount = lineItemCalculatedAmounts.get(5);
        assertNotNull(sixthCalculatedAmount);
        assertEquals("5", sixthCalculatedAmount.getRateClassCode());
        assertEquals("3", sixthCalculatedAmount.getRateTypeCode());
        assertEquals(new BudgetDecimal("0.00"), sixthCalculatedAmount.getCalculatedCost());
        assertEquals(new BudgetDecimal("0.00"), sixthCalculatedAmount.getCalculatedCostSharing());

        BudgetLineItemCalculatedAmount seventhCalculatedAmount = lineItemCalculatedAmounts.get(6);
        assertNotNull(seventhCalculatedAmount);
        assertEquals("8", seventhCalculatedAmount.getRateClassCode());
        assertEquals("2", seventhCalculatedAmount.getRateTypeCode());
        assertEquals(new BudgetDecimal("0.00"), seventhCalculatedAmount.getCalculatedCost());
        assertEquals(new BudgetDecimal("0.00"), seventhCalculatedAmount.getCalculatedCostSharing());
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
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[0].jobCode", "AA025");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[0].appointmentTypeCode", "7");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[0].calculationBase", "120000");
        budgetPersonnelPage = clickOn(budgetPersonnelPage, "methodToCall.save");
        budgetPersonnelPage = multiLookup(budgetPersonnelPage, "org.kuali.kra.bo.Person", "personId", "000000008");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[1].jobCode", "AA025");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[1].appointmentTypeCode", "7");
        setFieldValue(budgetPersonnelPage,"document.budgetPersons[1].calculationBase", "120000");
        budgetPersonnelPage = clickOn(budgetPersonnelPage, "methodToCall.save");
    }

    protected void setDefaultRequiredFields(HtmlPage page) {
        setRequiredFields(page, "TestPersonnelExpenses-1",
                                "000162",
                                "TestPersonnelExpenses-1",
                                "01/01/2007",
                                "12/31/2009",
                                "1",
                                DEFAULT_PROPOSAL_TYPE_CODE,
                                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
    }
    
    protected HtmlPage getBudgetPersonnelPage(HtmlPage htmlPage) throws Exception {
        return clickOn(htmlPage, BDOC_BUDGET_PERSONNEL_LINK_NAME);
    }
    
    private void assignPersonnelExpenses(HtmlPage budgetPersonnnelExpensePage) throws Exception {
        setFieldValue(budgetPersonnnelExpensePage, "newBudgetPersonnelDetails.personSequenceNumber", "1");
        setFieldValue(budgetPersonnnelExpensePage, "newBudgetLineItems[0].costElement", "400040");
        setFieldValue(budgetPersonnnelExpensePage, "newGroupName", "Faculty");
        
        budgetPersonnnelExpensePage = clickOn(budgetPersonnnelExpensePage, BDOC_ADD_PERSONNEL_EXPENSE_LINK_NAME+"Period1");

        setFieldValue(budgetPersonnnelExpensePage, "document.budgetPeriod[0].budgetLineItem[0].budgetPersonnelDetailsList[0].periodTypeCode", "3");
        setFieldValue(budgetPersonnnelExpensePage, "document.budgetPeriod[0].budgetLineItem[0].budgetPersonnelDetailsList[0].percentEffort", "100");
        setFieldValue(budgetPersonnnelExpensePage, "document.budgetPeriod[0].budgetLineItem[0].budgetPersonnelDetailsList[0].percentCharged", "75");
        
        budgetPersonnnelExpensePage = clickOn(budgetPersonnnelExpensePage, "methodToCall.calculateSalary.line0.personnel0.anchor4");
        budgetPersonnnelExpensePage = clickOn(budgetPersonnnelExpensePage, "methodToCall.save");
        
        assertDoesNotContain(budgetPersonnnelExpensePage, ERRORS_FOUND_ON_PAGE);
        assertContains(budgetPersonnnelExpensePage,SAVE_SUCCESS_MESSAGE);
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

class LineItemCalcAmountComparator implements Comparator<BudgetLineItemCalculatedAmount> {
    public int compare(BudgetLineItemCalculatedAmount lineItemCalculatedAmount1, BudgetLineItemCalculatedAmount lineItemCalculatedAmount2) {
        BudgetLineItemCalculatedAmount l1 = (BudgetLineItemCalculatedAmount) lineItemCalculatedAmount1;
        BudgetLineItemCalculatedAmount l2 = (BudgetLineItemCalculatedAmount) lineItemCalculatedAmount2;
      
        return (l1.getRateClassCode()+l1.getRateTypeCode()).compareTo((l2.getRateClassCode()+l2.getRateTypeCode()));
    }
  }
