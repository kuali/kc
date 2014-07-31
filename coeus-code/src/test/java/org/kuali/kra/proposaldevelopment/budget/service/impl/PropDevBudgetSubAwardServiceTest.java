/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.impl.core.AbstractBudgetService;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.*;

public class PropDevBudgetSubAwardServiceTest {

    protected static String directLt = "4";
    protected static String directGt = "3";
    protected static String indirectLt = "2";
    protected static String indirectGt = "1";
    
    protected PropDevPropDevBudgetSubAwardServiceImpl service;
    protected BudgetSubAwards subAward;
    protected Budget budget;

    protected Mockery context;
    
    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
        service = new PropDevPropDevBudgetSubAwardServiceImpl();
        budget = new ProposalDevelopmentBudgetExt();
        budget.setBudgetId(1L+12);
        subAward = new BudgetSubAwards();
        subAward.setBudgetId(budget.getBudgetId());
        subAward.setSubAwardNumber(1+206);
        subAward.setOrganizationName("My Testing Organization");
        budget.getBudgetSubAwards().add(subAward);
        for (int i = 0; i < 2; i++) {
            BudgetPeriod period = new BudgetPeriod();
            period.setBudgetId(budget.getBudgetId());
            period.setBudgetPeriod(i);
            period.setBudgetPeriodId(i+1029L);
            budget.getBudgetPeriods().add(period);
            BudgetSubAwardPeriodDetail detail = new BudgetSubAwardPeriodDetail(subAward, period);
            detail.setSubAwardNumber(subAward.getSubAwardNumber());
            subAward.getBudgetSubAwardPeriodDetails().add(detail);
        }
        final ParameterService parmService = context.mock(ParameterService.class);
        context.checking(new Expectations(){{
            one(parmService).getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_DIRECT_LT_25K_PARAM);
            will(returnValue(directLt));
            one(parmService).getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_DIRECT_GT_25K_PARAM);
            will(returnValue(directGt));
            one(parmService).getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_F_AND_A_LT_25K_PARAM);
            will(returnValue(indirectLt));
            one(parmService).getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_F_AND_A_GT_25K_PARAM);
            will(returnValue(indirectGt));   
        }});
        service.setParameterService(parmService);
        service.setBudgetService(new BudgetServiceMock());
    }
    
    @Test
    public void testExample1WithNoLineItems() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(75000L));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(0).put(indirectLt, new ScaleTwoDecimal(0));
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(125000L));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(1).put(indirectLt, new ScaleTwoDecimal(0));
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(150000L));
        expectedResults.get(1).put(directLt, new ScaleTwoDecimal(0));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }
    
    @Test
    public void testExample2WithNoLineItems() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(15000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(7500L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(15000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(7500L));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(0L));
        expectedResults.get(0).put(indirectLt, new ScaleTwoDecimal(7500L));
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(0));
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
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(20000));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(10000));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(20000));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(10000));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(5000));
        expectedResults.get(0).put(indirectLt, new ScaleTwoDecimal(5000));
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(0));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(20000));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(10000));
        expectedResults.get(1).put(indirectLt, new ScaleTwoDecimal(0L));
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(20000));
        expectedResults.get(1).put(directLt, new ScaleTwoDecimal(0));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    } 
    
    @Test
    public void testExample1WithCostShare() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setCostShare(new ScaleTwoDecimal(1000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setCostShare(new ScaleTwoDecimal(1000L));
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(0).put(indirectLt, new ScaleTwoDecimal(0));
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(125000L));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(1).put(indirectLt, new ScaleTwoDecimal(0));
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(150000L));
        expectedResults.get(1).put(directLt, new ScaleTwoDecimal(0));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(1000L);
    }
    
    @Test
    public void testExample1WithLineItems() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new ScaleTwoDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new ScaleTwoDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new ScaleTwoDecimal(75000L));
        
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            BudgetLineItem newLineItem = new BudgetLineItem();
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
        
        List<Map<String, ScaleTwoDecimal>> expectedResults = new ArrayList<Map<String, ScaleTwoDecimal>>();
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.add(new HashMap<String, ScaleTwoDecimal>());
        expectedResults.get(0).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(0).put(indirectLt, new ScaleTwoDecimal(0));
        expectedResults.get(0).put(directGt, new ScaleTwoDecimal(125000L));
        expectedResults.get(0).put(directLt, new ScaleTwoDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new ScaleTwoDecimal(75000L));
        expectedResults.get(1).put(indirectLt, new ScaleTwoDecimal(0));
        expectedResults.get(1).put(directGt, new ScaleTwoDecimal(150000L));
        expectedResults.get(1).put(directLt, new ScaleTwoDecimal(0));
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
                if (entry.getValue().isZero()) {
                    Assert.assertNull(findLineItemByCostElement(period.getBudgetLineItems(), entry.getKey()));
                } else {
                    Assert.assertEquals(entry.getValue(), findLineItemByCostElement(period.getBudgetLineItems(), entry.getKey()).getLineItemCost());
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
            for (int i = 1; i < budget.getBudgetPeriods().size(); i++) {
                Assert.assertEquals(ScaleTwoDecimal.ZERO, period.getBudgetLineItems().get(i).getCostSharingAmount());
            }
        }
    }
    
    protected BudgetLineItem findLineItemByCostElement(List<BudgetLineItem> lineItems, String costElement) {
        for (BudgetLineItem lineItem : lineItems) {
            if (StringUtils.equals(lineItem.getCostElement(), costElement) && ObjectUtils.equals(lineItem.getSubAwardNumber(), subAward.getSubAwardNumber())) {
                return lineItem;
            }
        }
        return null;
    }
    
    protected class BudgetServiceMock extends AbstractBudgetService {
        int newLineItemNumber = 28;
        public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod) {
            newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
            newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
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
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		protected void calculateBudgetOnSave(Budget budget) {
			// TODO Auto-generated method stub
			
		}
    }
}
