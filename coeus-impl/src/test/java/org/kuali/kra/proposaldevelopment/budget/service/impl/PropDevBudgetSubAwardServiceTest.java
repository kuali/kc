/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.proposaldevelopment.budget.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.subaward.PropDevPropDevBudgetSubAwardServiceImpl;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.impl.core.AbstractBudgetService;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.*;

public class PropDevBudgetSubAwardServiceTest {

    protected static String directLt = "4";
    protected static String directGt = "3";
    protected static String indirectLt = "2";
    protected static String indirectGt = "1";
    protected static String NIH_SPONSOR_CODE = "000340";
    protected static String NON_NIH_SPONSOR_CODE = "000500";
    
    protected PropDevPropDevBudgetSubAwardServiceImpl service;
    protected BudgetSubAwards subAward;
    protected ProposalDevelopmentBudgetExt budget;
    protected SponsorHierarchyService sponsorHierarchyService;

    protected Mockery context;
    
    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
        service = new PropDevPropDevBudgetSubAwardServiceImpl();
        budget = new ProposalDevelopmentBudgetExt();
        budget.setBudgetId(1L+12);
        budget.setDevelopmentProposal(new DevelopmentProposal());
        budget.getDevelopmentProposal().setSponsorCode(NIH_SPONSOR_CODE);
        subAward = new BudgetSubAwards();
        subAward.setBudgetId(budget.getBudgetId());
        subAward.setSubAwardNumber(1+206);
		Organization testOrg = new Organization();
		testOrg.setOrganizationName("University of Maine");
		testOrg.setOrganizationId("000040");
		subAward.setOrganization(testOrg);
        budget.getBudgetSubAwards().add(subAward);
        for (int i = 0; i < 2; i++) {
            BudgetPeriod period = new BudgetPeriod();

            period.setBudget(budget);
            period.setBudgetPeriod(i);
            period.setBudgetPeriodId(i+1029L);
            budget.getBudgetPeriods().add(period);
            BudgetSubAwardPeriodDetail detail = new BudgetSubAwardPeriodDetail(subAward, period);
            detail.setBudgetSubAward(subAward);
            subAward.getBudgetSubAwardPeriodDetails().add(detail);
        }
        final ParameterService parmService = context.mock(ParameterService.class);
        sponsorHierarchyService = context.mock(SponsorHierarchyService.class);
        context.checking(new Expectations(){{
            one(parmService).getParameterValueAsString(Budget.class, Constants.SUBCONTRACTOR_DIRECT_LT_25K_PARAM);
            will(returnValue(directLt));
            one(parmService).getParameterValueAsString(Budget.class, Constants.SUBCONTRACTOR_DIRECT_GT_25K_PARAM);
            will(returnValue(directGt));
            one(parmService).getParameterValueAsString(Budget.class, Constants.SUBCONTRACTOR_F_AND_A_LT_25K_PARAM);
            will(returnValue(indirectLt));
            one(parmService).getParameterValueAsString(Budget.class, Constants.SUBCONTRACTOR_F_AND_A_GT_25K_PARAM);
            will(returnValue(indirectGt));
        }});
        service.setParameterService(parmService);
        service.setSponsorHierarchyService(sponsorHierarchyService);
        service.setBudgetService(new BudgetServiceMock());
    }
    
    @Test
    public void testExample1WithNoLineItems() throws Exception {
        context.checking(new Expectations(){{
            one(sponsorHierarchyService).isSponsorNihMultiplePi(NIH_SPONSOR_CODE);
            will(returnValue(true));
        }});
        
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(75000L));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(0).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(125000L));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(1).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(150000L));
        expectedResults.get(1).put(directLt, ScaleTwoDecimal.ZERO);
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }
    
    @Test
    public void testExample2WithNoLineItems() throws Exception {
        context.checking(new Expectations(){{
            one(sponsorHierarchyService).isSponsorNihMultiplePi(NIH_SPONSOR_CODE);
            will(returnValue(true));
        }});
        
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(15000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(7500L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(15000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(7500L));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(0L));
        expectedResults.get(0).put(indirectLt, new ScaleTwoDecimal(7500L));
        expectedResults.get(0).put(directGt, ScaleTwoDecimal.ZERO);
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(15000L));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(7500L));
        expectedResults.get(1).put(indirectLt, new ScaleTwoDecimal(0L));
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(12500L));
        expectedResults.get(1).put(directLt, new ScaleTwoDecimal(2500L));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }
    
    @Test
    public void testExample3WithNoLineItems() throws Exception {
        context.checking(new Expectations(){{
            one(sponsorHierarchyService).isSponsorNihMultiplePi(NIH_SPONSOR_CODE);
            will(returnValue(true));
        }});
        
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(20000));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(10000));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(20000));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(10000));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(5000));
        expectedResults.get(0).put(indirectLt, new ScaleTwoDecimal(5000));
        expectedResults.get(0).put(directGt, ScaleTwoDecimal.ZERO);
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(20000));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(10000));
        expectedResults.get(1).put(indirectLt, new ScaleTwoDecimal(0L));
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(20000));
        expectedResults.get(1).put(directLt, ScaleTwoDecimal.ZERO);
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    } 
    
    @Test
    public void testExample1WithCostShare() throws Exception {
        context.checking(new Expectations(){{
            one(sponsorHierarchyService).isSponsorNihMultiplePi(NIH_SPONSOR_CODE);
            will(returnValue(true));
        }});
        
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setCostShare(new ScaleTwoDecimal(1000L));

        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setCostShare(new ScaleTwoDecimal(1000L));

        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<>();
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(0).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(125000L));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(25000L));

        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(1).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(150000L));
        expectedResults.get(1).put(directLt, ScaleTwoDecimal.ZERO);
        service.generateSubAwardLineItems(subAward, budget);


        BudgetPeriod period = budget.getBudgetPeriods().get(0);
        Assert.assertTrue(period.getBudgetLineItems().size() == 4);
        List<BudgetLineItem> lineItemsFoundForDirectGt = findLineItemsByCostElement(period.getBudgetLineItems(), directGt);
        Assert.assertTrue(lineItemsFoundForDirectGt.size() == 2);
        ScaleTwoDecimal totalDirectCost = lineItemsFoundForDirectGt.get(0).getLineItemCost().add(lineItemsFoundForDirectGt.get(1).getLineItemCost());
        Assert.assertTrue(totalDirectCost.compareTo(new ScaleTwoDecimal(125000L)) == 0);
        ScaleTwoDecimal totalCostSharing = lineItemsFoundForDirectGt.get(0).getCostSharingAmount().add(lineItemsFoundForDirectGt.get(1).getCostSharingAmount());
        Assert.assertTrue(totalCostSharing.compareTo(new ScaleTwoDecimal(1000L)) == 0);
        List<BudgetLineItem> lineItemsFound = findLineItemsByCostElement(period.getBudgetLineItems(), directLt);
        Assert.assertTrue(lineItemsFound.get(0).getLineItemCost().compareTo(new ScaleTwoDecimal(25000L)) == 0);
        lineItemsFound = findLineItemsByCostElement(period.getBudgetLineItems(), indirectGt);
        Assert.assertTrue(lineItemsFound.get(0).getLineItemCost().compareTo(new ScaleTwoDecimal(75000L)) == 0);
        lineItemsFound = findLineItemsByCostElement(period.getBudgetLineItems(), indirectLt);
        Assert.assertTrue(lineItemsFound.size() == 0);

        period = budget.getBudgetPeriods().get(1);
        Assert.assertTrue(period.getBudgetLineItems().size() == 3);
        lineItemsFoundForDirectGt = findLineItemsByCostElement(period.getBudgetLineItems(), directGt);
        Assert.assertTrue(lineItemsFoundForDirectGt.size() == 2);
        totalDirectCost = lineItemsFoundForDirectGt.get(0).getLineItemCost().add(lineItemsFoundForDirectGt.get(1).getLineItemCost());
        Assert.assertTrue(totalDirectCost.compareTo(new ScaleTwoDecimal(150000L)) == 0);
        totalCostSharing = lineItemsFoundForDirectGt.get(0).getCostSharingAmount().add(lineItemsFoundForDirectGt.get(1).getCostSharingAmount());
        Assert.assertTrue(totalCostSharing.compareTo(new ScaleTwoDecimal(1000L)) == 0);
        lineItemsFound = findLineItemsByCostElement(period.getBudgetLineItems(), directLt);
        Assert.assertTrue(lineItemsFound.size() == 0);
        lineItemsFound = findLineItemsByCostElement(period.getBudgetLineItems(), indirectGt);
        Assert.assertTrue(lineItemsFound.get(0).getLineItemCost().compareTo(new ScaleTwoDecimal(75000L)) == 0);
        lineItemsFound = findLineItemsByCostElement(period.getBudgetLineItems(), indirectLt);
        Assert.assertTrue(lineItemsFound.size() == 0);



    }

    
    @Test
    public void testExample1WithLineItems() throws Exception {
        context.checking(new Expectations(){{
            one(sponsorHierarchyService).isSponsorNihMultiplePi(NIH_SPONSOR_CODE);
            will(returnValue(true));
        }});
        
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(75000L));
        
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            BudgetLineItem newLineItem = new BudgetLineItem();
            newLineItem.setBudgetSubAward(subAward);
            newLineItem.setSubAwardNumber(subAward.getSubAwardNumber());
            newLineItem.setDirectCost(new ScaleTwoDecimal(100L));
            newLineItem.setCostSharingAmount(new ScaleTwoDecimal(100L));
            newLineItem.setCostElement(directLt);
            newLineItem.setLineItemNumber(1);
            period.getBudgetLineItems().add(newLineItem);
            newLineItem = (BudgetLineItem) org.kuali.rice.krad.util.ObjectUtils.deepCopy(newLineItem);
            newLineItem.setCostElement(directGt);
            newLineItem.setLineItemNumber(2);
            period.getBudgetLineItems().add(newLineItem);
            newLineItem = (BudgetLineItem) org.kuali.rice.krad.util.ObjectUtils.deepCopy(newLineItem);
            newLineItem.setCostElement(indirectLt);
            newLineItem.setLineItemNumber(3);
            period.getBudgetLineItems().add(newLineItem);
            newLineItem = (BudgetLineItem) org.kuali.rice.krad.util.ObjectUtils.deepCopy(newLineItem);
            newLineItem.setCostElement(indirectGt);
            newLineItem.setLineItemNumber(4);
            period.getBudgetLineItems().add(newLineItem);
        }
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<>();
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(0).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(125000L));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(1).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(150000L));
        expectedResults.get(1).put(directLt, ScaleTwoDecimal.ZERO);
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }     
    
    protected void assertExpectedResults(List<Map<String, ScaleTwoDecimal>> results) {
        int index = 0;
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            for (BudgetLineItem lineItem : period.getBudgetLineItems()) {
                Assert.assertEquals(subAward.getSubAwardNumber(), lineItem.getSubAwardNumber());
                Assert.assertEquals(results.get(index).get(lineItem.getCostElement()), lineItem.getLineItemCost());
            }
            for (Map.Entry<String, ScaleTwoDecimal> entry : results.get(index).entrySet()) {
                final List<BudgetLineItem> lineItemsByCostElement = findLineItemsByCostElement(period.getBudgetLineItems(), entry.getKey());
                if (entry.getValue().isZero()) {
                    Assert.assertTrue(lineItemsByCostElement.size() == 0);
                } else {
                    Assert.assertEquals(entry.getValue(), lineItemsByCostElement.get(0).getLineItemCost());
                }
            }
            index++;
        }
    }

    protected void assertCostShare(long costShareAmount) {
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            Collections.sort(period.getBudgetLineItems(), new Comparator<BudgetLineItem>() {
                public int compare(BudgetLineItem arg0, BudgetLineItem arg1) {
                    return arg0.getLineItemNumber().compareTo(arg1.getLineItemNumber());
                }
            });
            Assert.assertEquals(new ScaleTwoDecimal(costShareAmount), period.getBudgetLineItems().get(0).getCostSharingAmount());
            for (int i = 1; i < period.getBudgetLineItems().size(); i++) {
                Assert.assertEquals(ScaleTwoDecimal.ZERO, period.getBudgetLineItems().get(i).getCostSharingAmount());
            }
        }
    }
    
    protected List<BudgetLineItem> findLineItemsByCostElement(List<BudgetLineItem> lineItems, String costElement) {
        List<BudgetLineItem> lineItemsFound = new ArrayList<>();
        for (BudgetLineItem lineItem : lineItems) {
            if (StringUtils.equals(lineItem.getCostElement(), costElement) && ObjectUtils.equals(lineItem.getSubAwardNumber(), subAward.getSubAwardNumber())) {
                lineItemsFound.add(lineItem);
            }
        }
        return lineItemsFound;
    }
    
    @Test
    public void testExample1WithNonNih() throws Exception {
        context.checking(new Expectations(){{
            one(sponsorHierarchyService).isSponsorNihMultiplePi(NON_NIH_SPONSOR_CODE);
            will(returnValue(false));
        }});
        budget.getDevelopmentProposal().setSponsorCode(NON_NIH_SPONSOR_CODE);
        
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(75000L));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<>();
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.add(new HashMap<>());
        expectedResults.get(0).put(indirectGt, ScaleTwoDecimal.ZERO);
        expectedResults.get(0).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(200000L));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(25000L));
        expectedResults.get(1).put(indirectGt, ScaleTwoDecimal.ZERO);
        expectedResults.get(1).put(indirectLt, ScaleTwoDecimal.ZERO);
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(225000L));
        expectedResults.get(1).put(directLt, ScaleTwoDecimal.ZERO);
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }    
    
    protected class BudgetServiceMock extends AbstractBudgetService {
        int newLineItemNumber = 28;
        public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod) {
            newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
            newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
            newBudgetLineItem.setBudgetPeriodBO(budgetPeriod);
            newBudgetLineItem.setStartDate(budgetPeriod.getStartDate());
            newBudgetLineItem.setEndDate(budgetPeriod.getEndDate());
            newBudgetLineItem.setBudgetId(budget.getBudgetId());
            newBudgetLineItem.setLineItemNumber(newLineItemNumber++);
            newBudgetLineItem.setApplyInRateFlag(true);
            newBudgetLineItem.setSubmitCostSharingFlag(budget.getSubmitCostSharingFlag());
        }
		@Override
		protected Budget getNewBudgetVersion(BudgetParentDocument parent,
				String versionName, Map options) {
			return null;
		}
		@Override
		public boolean isBudgetVersionNameValid(BudgetParent parent, String name) {
			return true;
		}

        @Override
        public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod) {
            return null;
        }
    }
}
