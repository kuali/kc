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
package org.kuali.kra.budget.service;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
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
import org.kuali.kra.budget.service.impl.BudgetCalculationServiceImpl;
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
    private Mockery context = new JUnit4Mockery();
    
    private static final List objectCodes = new ArrayList();
    static {
        objectCodes.add(0, "400250");
        objectCodes.add(1, "400350");
        objectCodes.add(2, "400770");
        objectCodes.add(3, "420050");
    }
    private static final List objectCodeTotals = new ArrayList();
    static {
        objectCodeTotals.add(0, new BudgetDecimal(2000.00d));
        objectCodeTotals.add(1, new BudgetDecimal(2000.00d));
        objectCodeTotals.add(2, new BudgetDecimal(1000.00d));
        objectCodeTotals.add(3, new BudgetDecimal(3000.00d));
    }

    private static final List rateClassCodes = new ArrayList();
    static {
        rateClassCodes.add(0, "1");
        rateClassCodes.add(1, "2");
        rateClassCodes.add(2, "5");
        rateClassCodes.add(3, "8");
    }

    private static final List CalExpensesTotals = new ArrayList();
    static {
        CalExpensesTotals.add(0, new BudgetDecimal(2000.00d));
        CalExpensesTotals.add(1, new BudgetDecimal(4000.00d));
        CalExpensesTotals.add(2, new BudgetDecimal(6000.00d));
        CalExpensesTotals.add(3, new BudgetDecimal(8000.00d));
    }


    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        budgetCalculationService = getService(BudgetCalculationService.class);
        documentService = KNSServiceLocator.getDocumentService();
    }

    @Override
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
        Date requestedStartDateInitial = new Date(107, 0, 1);
        Date requestedEndDateInitial = new Date(110, 0, 1);
        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title",
                requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
        documentService.saveDocument(document);
        BudgetDocument bd = (BudgetDocument) documentService.getNewDocument("BudgetDocument");
        setBaseDocumentFields(bd, document.getDevelopmentProposal().getProposalNumber());
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
        
        // set up 4 bd periods 01/01/2007 - 12/31/2110
        BudgetDocument bd = createBudgetDocument();
        assertNotNull("Budget document not saved", bd);

        bd.getBudgetPeriods();
        // set up period line item costs
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(0));
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(1));
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(2));
        setPeriodLineItemCost(bd, bd.getBudgetPeriods().get(3));
        budgetCalculationService.calculateBudgetTotals(bd);

        // check object code total
        int sortingIndex = 0;
        for (Map.Entry<CostElement, List<BudgetDecimal>> objectCodeTotalMap : bd.getObjectCodeTotals().entrySet()) {
            // the order is ok
            assertEquals(objectCodes.get(sortingIndex), (objectCodeTotalMap.getKey()).getCostElement());
            for (Object objectCodeTotal : objectCodeTotalMap.getValue()) {
                // calculated amount is OK
                assertEquals(objectCodeTotals.get(sortingIndex), objectCodeTotal);
            }
            ++sortingIndex;
        }

        // check calculated expenses
        sortingIndex = 0;
        for (Map.Entry<RateType, List<BudgetDecimal>> calculatedExpensesTotalMap : bd.getCalculatedExpenseTotals().entrySet()) {
            assertEquals(rateClassCodes.get(sortingIndex), ((RateType) calculatedExpensesTotalMap.getKey()).getRateClassCode());

            for (Object CalculatedExpensesTotal : calculatedExpensesTotalMap.getValue()) {
                assertEquals(CalExpensesTotals.get(sortingIndex), CalculatedExpensesTotal);
            }
            ++sortingIndex;
        }

    }
    
    /**
     * This tests that the ensureBudgetPeriodHasSyncedCosts method
     * correctly syncs the costs with line items. 
     * 
     * <p>
     * Assuming isCalculationRequired is working correctly.
     * </p>
     */
    @Test
    public void syncCostsToBudgetPeriodCalcRequiredTest() throws Exception {
        BudgetDocument bd = createBudgetDocument();
        List<BudgetPeriod> periods = bd.getBudgetPeriods();
        
        // set up period line item costs
        setPeriodLineItemCost(bd, periods.get(0));
        setPeriodLineItemCost(bd, periods.get(1));
        setPeriodLineItemCost(bd, periods.get(2));
        setPeriodLineItemCost(bd, periods.get(3));
        
        Class<BudgetCalculationServiceImpl> c = BudgetCalculationServiceImpl.class;
        Method m = c.getDeclaredMethod("ensureBudgetPeriodHasSyncedCosts", BudgetDocument.class);
        m.setAccessible(true);
        
        //make them different
        for (BudgetPeriod period : periods) {
            for (BudgetLineItem lineItem : period.getBudgetLineItems()) {
                lineItem.setTotalCostSharingAmount(lineItem.getTotalCostSharingAmount().add(new BudgetDecimal(20)));
                lineItem.setDirectCost(lineItem.getDirectCost().add(new BudgetDecimal(20)));
                lineItem.setIndirectCost(lineItem.getIndirectCost().add(new BudgetDecimal(20)));
                lineItem.setUnderrecoveryAmount(lineItem.getUnderrecoveryAmount().add(new BudgetDecimal(20)));
            }
        }
        
        for (BudgetPeriod period : periods) {
            Assert.assertFalse("period : " + period.getTotalCost() + " line items: " + period.getSumTotalCostAmountFromLineItems(), period.getTotalCost().equals(period.getSumTotalCostAmountFromLineItems()));
            Assert.assertFalse("period : " + period.getCostSharingAmount() + " line items: " + period.getSumTotalCostSharingAmountFromLineItems(), period.getCostSharingAmount().equals(period.getSumTotalCostSharingAmountFromLineItems()));
            Assert.assertFalse("period : " + period.getTotalDirectCost() + " line items: " + period.getSumDirectCostAmountFromLineItems(), period.getTotalDirectCost().equals(period.getSumDirectCostAmountFromLineItems()));
            Assert.assertFalse("period : " + period.getTotalIndirectCost() + " line items: " + period.getSumIndirectCostAmountFromLineItems(), period.getTotalIndirectCost().equals(period.getSumIndirectCostAmountFromLineItems()));
            Assert.assertFalse("period : " + period.getUnderrecoveryAmount() + " line items: " + period.getSumUnderreoveryAmountFromLineItems(), period.getUnderrecoveryAmount().equals(period.getSumUnderreoveryAmountFromLineItems()));
        }
        
        m.invoke(new BudgetCalculationServiceImpl(), bd);
        
        for (BudgetPeriod period : periods) {
            Assert.assertEquals(period.getTotalCost(), period.getSumTotalCostAmountFromLineItems());
            Assert.assertEquals(period.getCostSharingAmount(), period.getSumTotalCostSharingAmountFromLineItems());
            Assert.assertEquals(period.getTotalDirectCost(), period.getSumDirectCostAmountFromLineItems());
            Assert.assertEquals(period.getTotalIndirectCost(), period.getSumIndirectCostAmountFromLineItems());
            Assert.assertEquals(period.getUnderrecoveryAmount(), period.getSumUnderreoveryAmountFromLineItems());
        }
    }
    
    /**
     * Tests that Cost sharing gets initialized when all line items and budget periods
     * contain a zero cost sharing amount.
     * 
     * @throws Exception
     */
    @Test
    public void initCostDependentItemsZeroCostSharedBothTest() throws Exception {
        
        final BudgetDocument bd = createBudgetDocument();
        List<BudgetPeriod> periods = bd.getBudgetPeriods();
        
        // set up period line item costs
        setPeriodLineItemCost(bd, periods.get(0));
        setPeriodLineItemCost(bd, periods.get(1));
        setPeriodLineItemCost(bd, periods.get(2));
        setPeriodLineItemCost(bd, periods.get(3));
        
        for (BudgetPeriod period : periods) {
            period.setCostSharingAmount(BudgetDecimal.ZERO);
            period.setUnderrecoveryAmount(new BudgetDecimal(20));
            for (BudgetLineItem lineItem : period.getBudgetLineItems()) {
                lineItem.setTotalCostSharingAmount(BudgetDecimal.ZERO);
                lineItem.setUnderrecoveryAmount(new BudgetDecimal(20));
            }
        }
        
        final BudgetDistributionAndIncomeService mockService = context.mock(BudgetDistributionAndIncomeService.class);
        context.checking(new Expectations() {
            {
                oneOf(mockService).initializeCostSharingCollectionDefaults(bd);
            }
        });
        
        
        BudgetCalculationServiceImpl calcService = new BudgetCalculationServiceImpl();
        calcService.setBudgetDistributionAndIncomeService(mockService);
        
        Class<BudgetCalculationServiceImpl> c = BudgetCalculationServiceImpl.class;
        Method m = c.getDeclaredMethod("initCostDependentItems", BudgetDocument.class);
        m.setAccessible(true);
        
        m.invoke(calcService, bd);

        context.assertIsSatisfied();
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
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
    }
    
    private void setPositiveCostShareOnPeriodsAndLineItems(Collection<BudgetPeriod> periods) {
        for (BudgetPeriod period : periods) {
            period.setCostSharingAmount(new BudgetDecimal(20));
            for (BudgetLineItem lineItem : period.getBudgetLineItems()) {
                lineItem.setTotalCostSharingAmount(new BudgetDecimal(20));
            }
        }
    }
    
    private void setPositiveFandAOnPeriodsAndLineItems(Collection<BudgetPeriod> periods) {
        for (BudgetPeriod period : periods) {
            period.setUnderrecoveryAmount(new BudgetDecimal(20));
            for (BudgetLineItem lineItem : period.getBudgetLineItems()) {
                lineItem.setUnderrecoveryAmount(new BudgetDecimal(20));
            }
        }
    }
}
