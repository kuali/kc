/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetServiceImpl;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardPeriodDetail;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class BudgetSubAwardServiceTest {

    protected static String directLt = "4";
    protected static String directGt = "3";
    protected static String indirectLt = "2";
    protected static String indirectGt = "1";
    
    protected BudgetSubAwardServiceImpl service;
    protected BudgetSubAwards subAward;
    protected Budget budget;
    protected int budgetLineItem = 28;
    protected String budgetDocumentNumber = "1498";
    
    protected Mockery context;
    
    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery();
        service = new BudgetSubAwardServiceImpl();
        budget = new Budget();
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
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new BudgetDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new BudgetDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new BudgetDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new BudgetDecimal(75000L));
        
        List<Map<String, BudgetDecimal>> expectedResults = new ArrayList<Map<String, BudgetDecimal>>();
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.get(0).put(indirectGt, new BudgetDecimal(75000L));
        expectedResults.get(0).put(indirectLt, new BudgetDecimal(0));
        expectedResults.get(0).put(directGt, new BudgetDecimal(125000L));
        expectedResults.get(0).put(directLt, new BudgetDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new BudgetDecimal(75000L));
        expectedResults.get(1).put(indirectLt, new BudgetDecimal(0));
        expectedResults.get(1).put(directGt, new BudgetDecimal(150000L));
        expectedResults.get(1).put(directLt, new BudgetDecimal(0));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }
    
    @Test
    public void testExample2WithNoLineItems() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new BudgetDecimal(15000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new BudgetDecimal(7500L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new BudgetDecimal(15000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new BudgetDecimal(7500L));
        
        List<Map<String, BudgetDecimal>> expectedResults = new ArrayList<Map<String, BudgetDecimal>>();
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.get(0).put(indirectGt, new BudgetDecimal(0L));
        expectedResults.get(0).put(indirectLt, new BudgetDecimal(7500L));
        expectedResults.get(0).put(directGt, new BudgetDecimal(0));
        expectedResults.get(0).put(directLt, new BudgetDecimal(15000L));
        expectedResults.get(1).put(indirectGt, new BudgetDecimal(7500L));
        expectedResults.get(1).put(indirectLt, new BudgetDecimal(0L));
        expectedResults.get(1).put(directGt, new BudgetDecimal(12500L));
        expectedResults.get(1).put(directLt, new BudgetDecimal(2500L));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }
    
    @Test
    public void testExample3WithNoLineItems() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new BudgetDecimal(20000));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new BudgetDecimal(10000));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new BudgetDecimal(20000));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new BudgetDecimal(10000));
        
        List<Map<String, BudgetDecimal>> expectedResults = new ArrayList<Map<String, BudgetDecimal>>();
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.get(0).put(indirectGt, new BudgetDecimal(5000));
        expectedResults.get(0).put(indirectLt, new BudgetDecimal(5000));
        expectedResults.get(0).put(directGt, new BudgetDecimal(0));
        expectedResults.get(0).put(directLt, new BudgetDecimal(20000));
        expectedResults.get(1).put(indirectGt, new BudgetDecimal(10000));
        expectedResults.get(1).put(indirectLt, new BudgetDecimal(0L));
        expectedResults.get(1).put(directGt, new BudgetDecimal(20000));
        expectedResults.get(1).put(directLt, new BudgetDecimal(0));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    } 
    
    @Test
    public void testExample1WithCostShare() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new BudgetDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new BudgetDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setCostShare(new BudgetDecimal(1000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new BudgetDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new BudgetDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setCostShare(new BudgetDecimal(1000L));
        
        List<Map<String, BudgetDecimal>> expectedResults = new ArrayList<Map<String, BudgetDecimal>>();
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.get(0).put(indirectGt, new BudgetDecimal(75000L));
        expectedResults.get(0).put(indirectLt, new BudgetDecimal(0));
        expectedResults.get(0).put(directGt, new BudgetDecimal(125000L));
        expectedResults.get(0).put(directLt, new BudgetDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new BudgetDecimal(75000L));
        expectedResults.get(1).put(indirectLt, new BudgetDecimal(0));
        expectedResults.get(1).put(directGt, new BudgetDecimal(150000L));
        expectedResults.get(1).put(directLt, new BudgetDecimal(0));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(1000L);
    }
    
    @Test
    public void testExample1WithLineItems() throws Exception {
        subAward.getBudgetSubAwardPeriodDetails().get(0).setDirectCost(new BudgetDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(0).setIndirectCost(new BudgetDecimal(75000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setDirectCost(new BudgetDecimal(150000L));
        subAward.getBudgetSubAwardPeriodDetails().get(1).setIndirectCost(new BudgetDecimal(75000L));
        
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            BudgetLineItem newLineItem = new BudgetLineItem();
            newLineItem.setSubAwardNumber(subAward.getSubAwardNumber());
            newLineItem.setDirectCost(new BudgetDecimal(100L));
            newLineItem.setCostSharingAmount(new BudgetDecimal(100L));
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
        
        List<Map<String, BudgetDecimal>> expectedResults = new ArrayList<Map<String, BudgetDecimal>>();
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.add(new HashMap<String, BudgetDecimal>());
        expectedResults.get(0).put(indirectGt, new BudgetDecimal(75000L));
        expectedResults.get(0).put(indirectLt, new BudgetDecimal(0));
        expectedResults.get(0).put(directGt, new BudgetDecimal(125000L));
        expectedResults.get(0).put(directLt, new BudgetDecimal(25000L));
        expectedResults.get(1).put(indirectGt, new BudgetDecimal(75000L));
        expectedResults.get(1).put(indirectLt, new BudgetDecimal(0));
        expectedResults.get(1).put(directGt, new BudgetDecimal(150000L));
        expectedResults.get(1).put(directLt, new BudgetDecimal(0));
        service.generateSubAwardLineItems(subAward, budget);
        assertExpectedResults(expectedResults);
        assertCostShare(0);
    }     
    
    protected void assertExpectedResults(List<Map<String, BudgetDecimal>> results) {
        int index = 0;
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            for (BudgetLineItem lineItem : period.getBudgetLineItems()) {
                Assert.assertEquals(subAward.getSubAwardNumber(), lineItem.getSubAwardNumber());
                Assert.assertEquals(results.get(index).get(lineItem.getCostElement()), lineItem.getLineItemCost());
            }
            for (Map.Entry<String, BudgetDecimal> entry : results.get(index).entrySet()) {
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
            Assert.assertEquals(new BudgetDecimal(costShareAmount), period.getBudgetLineItems().get(0).getCostSharingAmount());
            for (int i = 1; i < budget.getBudgetPeriods().size(); i++) {
                Assert.assertEquals(BudgetDecimal.ZERO, period.getBudgetLineItems().get(i).getCostSharingAmount());
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
    
    protected class BudgetServiceMock extends BudgetServiceImpl {
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
    }
}
