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
package org.kuali.kra.budget.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

public class BudgetCalculationServiceTest extends KraTestBase {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(BudgetCalculationServiceTest.class);
    private BudgetCalculationService budgetCalculationService;
    private DocumentService documentService = null;
    private static List objectCodes = new ArrayList();
    static {
        objectCodes.add(0, "400250");
        objectCodes.add(1, "400350");
        objectCodes.add(2, "400770");
        objectCodes.add(3, "420050");
    }
    private static List objectCodeTotals = new ArrayList();
    static {
        objectCodeTotals.add(0, new BudgetDecimal(2000.00d));
        objectCodeTotals.add(1, new BudgetDecimal(2000.00d));
        objectCodeTotals.add(2, new BudgetDecimal(1000.00d));
        objectCodeTotals.add(3, new BudgetDecimal(3000.00d));
    }

    private static List rateClassCodes = new ArrayList();
    static {
        rateClassCodes.add(0, "1");
        rateClassCodes.add(1, "2");
        rateClassCodes.add(2, "5");
        rateClassCodes.add(3, "8");
    }

    private static List CalExpensesTotals = new ArrayList();
    static {
        CalExpensesTotals.add(0, new BudgetDecimal(2000.00d));
        CalExpensesTotals.add(1, new BudgetDecimal(4000.00d));
        CalExpensesTotals.add(2, new BudgetDecimal(6000.00d));
        CalExpensesTotals.add(3, new BudgetDecimal(8000.00d));
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        budgetCalculationService = getService(BudgetCalculationService.class);
        documentService = KNSServiceLocator.getDocumentService();
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        budgetCalculationService = null;
        documentService = null;
        super.tearDown();
    }

    private BudgetDocument createBudgetDocument() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService
                .getNewDocument("ProposalDevelopmentDocument");
        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());
        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title",
                requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
        documentService.saveDocument(document);
        BudgetDocument bd = (BudgetDocument) documentService.getNewDocument("BudgetDocument");
        setBaseDocumentFields(bd, document.getProposalNumber());
        documentService.saveDocument(bd);
        BudgetDocument savedBudgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(bd.getDocumentNumber());
        assertNotNull("Budget document not saved", savedBudgetDocument);
        return savedBudgetDocument;
    }

    /**
     * 
     * This method is to test calculateBudgetTotals method.
     * set up 4 budget periods, 4 object codes and 4 rate types. 
     * 
     * @throws Exception
     */
    @Test
    public void calculateBudgetTotalsTest() throws Exception {
        List<String> errors = new ArrayList<String>();
        // set up 4 bd periods 01/01/2007 - 12/31/2110
        BudgetDocument bd = createBudgetDocument();
        assertNotNull("Budget document not saved", bd);

        List<BudgetPeriod> periods = bd.getBudgetPeriods();
        // set up period line item costs
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(0));
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(1));
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(2));
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(3));
        budgetCalculationService.calculateBudgetTotals(bd);

        // check object code total
        int sortingIndex = 0;
        for (Map.Entry<CostElement, List> objectCodeTotalMap : bd.getObjectCodeTotals().entrySet()) {
            // the order is ok
            assertEquals(objectCodes.get(sortingIndex), ((CostElement) objectCodeTotalMap.getKey()).getCostElement());
            for (Object objectCodeTotal : objectCodeTotalMap.getValue()) {
                // calculated amount is OK
                assertEquals(objectCodeTotals.get(sortingIndex), (BudgetDecimal) objectCodeTotal);
            }
            ++sortingIndex;
        }

        // check calculated expenses
        sortingIndex = 0;
        for (Map.Entry<RateType, List> calculatedExpensesTotalMap : bd.getCalculatedExpenseTotals().entrySet()) {
            assertEquals(rateClassCodes.get(sortingIndex), ((RateType) calculatedExpensesTotalMap.getKey()).getRateClassCode());

            for (Object CalculatedExpensesTotal : calculatedExpensesTotalMap.getValue()) {
                assertEquals(CalExpensesTotals.get(sortingIndex), (BudgetDecimal) CalculatedExpensesTotal);
            }
            ++sortingIndex;
        }

    }

    private void setPeriodLineItemCost(BudgetDocument bd, BudgetPeriod bp) {
        List periodBudgetLineItems = bp.getBudgetLineItems();
        if (periodBudgetLineItems == null) {
            periodBudgetLineItems = new ArrayList();
        }
        periodBudgetLineItems.add(getLineItem(bp, 1, "400250", 1000.00d, 100.00d));
        setLineItemCalAmts((BudgetLineItem) periodBudgetLineItems.get(0));

        periodBudgetLineItems.add(getLineItem(bp, 2, "400350", 2000.00d, 100.00d));
        setLineItemCalAmts((BudgetLineItem) periodBudgetLineItems.get(1));

        periodBudgetLineItems.add(getLineItem(bp, 3, "400770", 1000.00d, 100.00d));
        periodBudgetLineItems.add(getLineItem(bp, 4, "420050", 3000.00d, 100.00d));
        periodBudgetLineItems.add(getLineItem(bp, 5, "400250", 1000.00d, 100.00d));

    }

    private void setLineItemCalAmts(BudgetLineItem bli) {
        List budgetLineItemCalAmts = bli.getBudgetLineItemCalculatedAmounts();
        if (budgetLineItemCalAmts == null) {
            budgetLineItemCalAmts = new ArrayList();
        }
        budgetLineItemCalAmts.add(getLineItemCalAmt(bli, "1", "1", 1000.00d));
        budgetLineItemCalAmts.add(getLineItemCalAmt(bli, "2", "1", 2000.00d));
        budgetLineItemCalAmts.add(getLineItemCalAmt(bli, "5", "1", 3000.00d));
        budgetLineItemCalAmts.add(getLineItemCalAmt(bli, "8", "1", 4000.00d));

    }


    private BudgetLineItem getLineItem(BudgetPeriod bp, int lineItemNumber, String costElement, double lineItemCost,
            double costSharingAmount) {
        BudgetLineItem bli = new BudgetLineItem();
        bli.setProposalNumber(bp.getProposalNumber());
        bli.setBudgetVersionNumber(bp.getBudgetVersionNumber());
        bli.setBudgetPeriod(bp.getBudgetPeriod());
        bli.setStartDate(bp.getStartDate());
        bli.setEndDate(bp.getEndDate());
        bli.setApplyInRateFlag(true);
        bli.setOnOffCampusFlag(true);
        bli.setLineItemNumber(lineItemNumber);
        bli.setBudgetCategoryCode("1");
        bli.setCostElement(costElement);
        bli.setLineItemCost(new BudgetDecimal(lineItemCost));
        bli.setCostSharingAmount(new BudgetDecimal(costSharingAmount));
        bli.setUpdateTimestamp(bp.getUpdateTimestamp());
        bli.setUpdateUser(bp.getUpdateUser());
        return bli;

    }

    private BudgetLineItemCalculatedAmount getLineItemCalAmt(BudgetLineItem bli, String rateClass, String rateType,
            double calculatedCost) {
        BudgetLineItemCalculatedAmount BliCalAmt = new BudgetLineItemCalculatedAmount();
        BliCalAmt.setProposalNumber(bli.getProposalNumber());
        BliCalAmt.setBudgetVersionNumber(bli.getBudgetVersionNumber());
        BliCalAmt.setBudgetPeriod(bli.getBudgetPeriod());
        BliCalAmt.setRateClassCode(rateClass);
        BliCalAmt.setRateTypeCode(rateType);
        BliCalAmt.setLineItemNumber(bli.getLineItemNumber());
        BliCalAmt.setCalculatedCost(new BudgetDecimal(calculatedCost));
        BliCalAmt.setUpdateTimestamp(bli.getUpdateTimestamp());
        BliCalAmt.setUpdateUser(bli.getUpdateUser());
        return BliCalAmt;

    }

    // TODO : some of the private methods are copied from lineitemcalculationtest, so
    // probably should create a utility or helper class to share these private methods.
    
    private void setBaseDocumentFields(BudgetDocument bd, String proposalNumber) {
        bd.getDocumentHeader().setDocumentDescription("Test budget calculation");
        bd.setProposalNumber(proposalNumber);
        bd.setBudgetVersionNumber(1);
        bd.setStartDate(java.sql.Date.valueOf("2007-01-01"));
        bd.setEndDate(java.sql.Date.valueOf("2010-12-31"));
        bd.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
        bd.setUpdateUser("KRADEV");
        bd.setOhRateClassCode("1");
        bd.setUrRateClassCode("1");
        bd.setModularBudgetFlag(false);
        bd.setActivityTypeCode("1");
    }


    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title,
            Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode,
            String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }

}
