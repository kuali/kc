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
package org.kuali.kra.budget.document;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;

import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetProjectIncome;

public class BudgetDocumentTest {
    
    private static final int DEVIATION_LIMIT_MS = 1000;
    private static final int DAY_1 = 1;
    private static final int YEAR_2000 = 2000;
    
    private Mockery context;
    
    @Before
    public void setupMockery() {
        this.context = new JUnit4Mockery();
    }
    
    /**
     * Tests calculating a date from a string.
     * 
     * @throws Exception
     */
    @Test
    public void testCalculatingDatefromString() throws Exception {
        BudgetDocument bd = new BudgetDocument();
        Date fiscalYearDate = bd.createDateFromString("07/01/2000");
        
        Calendar cal = Calendar.getInstance();
        cal.set(YEAR_2000, Calendar.JULY, DAY_1, 0, 0, 0);
        Assert.assertTrue(cal.getTimeInMillis() - fiscalYearDate.getTime() < DEVIATION_LIMIT_MS);
    }
    
    /**
     * Tests that project incomes are deleted when a budget period deletion is detected.
     * 
     * @throws Exception
     */
    @Test
    public void handlePeriodToProjectIncomeRelationshipItemsToDelete() throws Exception {
        
        final BusinessObjectService mockBOS = context.mock(BusinessObjectService.class);
        
        final BudgetDocument bd = new BudgetDocument() {
            @Override
            protected <T> T getService(Class<T> serviceClass) {
                if (BusinessObjectService.class.equals(serviceClass)) {
                    return (T) mockBOS;
                }
                throw new RuntimeException("unexpected request for service " + serviceClass);
            }
        };
        bd.setProposalNumber("1234");
        bd.setBudgetVersionNumber(Integer.valueOf(1));
        
        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
        final BudgetPeriod p = new BudgetPeriod();
        p.setBudgetPeriodId(Long.valueOf(1));
        p.setProposalNumber("1234");
        periods.add(p);
        
        context.checking(new Expectations() {
            {
                final Map<Object, Object> matchCriteria = new HashMap<Object, Object>();
                matchCriteria.put("proposalNumber", bd.getProposalNumber());
                matchCriteria.put("budgetVersionNumber", bd.getBudgetVersionNumber());
                
                oneOf(mockBOS).findMatching(BudgetPeriod.class, matchCriteria);
                will(returnValue(periods));
            }
            
            {
                final Map<String, Collection<Long>> matchCriteria = new HashMap<String, Collection<Long>>();
                Collection<Long> ids = new ArrayList<Long>();
                ids.add(Long.valueOf(1));
                matchCriteria.put("budgetPeriodId", ids);
                oneOf(mockBOS).deleteMatching(BudgetProjectIncome.class, matchCriteria);
            }
        });
        
        
        Method m = bd.getClass().getSuperclass().getDeclaredMethod("handlePeriodToProjectIncomeRelationship");
       
        m.setAccessible(true);
        m.invoke(bd);
        
        context.assertIsSatisfied();
    }
    
    /**
     * Tests that project incomes are NOT deleted when a budget period deletion is detected.
     * 
     * @throws Exception
     */
    @Test
    public void handlePeriodToProjectIncomeRelationshipNoItemsToDelete() throws Exception {
        final BusinessObjectService mockBOS = context.mock(BusinessObjectService.class);
        
        final BudgetDocument bd = new BudgetDocument() {
            @Override
            protected <T> T getService(Class<T> serviceClass) {
                if (BusinessObjectService.class.equals(serviceClass)) {
                    return (T) mockBOS;
                }
                throw new RuntimeException("unexpected request for service " + serviceClass);
            }
        };
        bd.setProposalNumber("1234");
        bd.setBudgetVersionNumber(Integer.valueOf(1));
        
        context.checking(new Expectations() {
            {
                final Map<Object, Object> matchCriteria = new HashMap<Object, Object>();
                matchCriteria.put("proposalNumber", bd.getProposalNumber());
                matchCriteria.put("budgetVersionNumber", bd.getBudgetVersionNumber());
                
                oneOf(mockBOS).findMatching(BudgetPeriod.class, matchCriteria);
                will(returnValue(Collections.emptyList()));
            }
            
            {
                final Map<String, Collection<Long>> matchCriteria = new HashMap<String, Collection<Long>>();
                Collection<Long> ids = new ArrayList<Long>();
                ids.add(Long.valueOf(1));
                matchCriteria.put("budgetPeriodId", ids);
                never(mockBOS).deleteMatching(BudgetProjectIncome.class, matchCriteria);
            }
        });
        
        
        Method m = bd.getClass().getSuperclass().getDeclaredMethod("handlePeriodToProjectIncomeRelationship");
        m.setAccessible(true);
        m.invoke(bd);
        
        context.assertIsSatisfied();
    }
    
    /**
     * Tests that project incomes are NOT deleted when a budget period deletion is not detected.
     * This tests the condition where periods exist but are matched with that in the database.
     * 
     * @throws Exception
     */
    @Test
    public void handlePeriodToProjectIncomeRelationshipNoItemsToDeletePeriodsExist() throws Exception {
        
        final BusinessObjectService mockBOS = context.mock(BusinessObjectService.class);
        
        final BudgetDocument bd = new BudgetDocument() {
            @Override
            protected <T> T getService(Class<T> serviceClass) {
                if (BusinessObjectService.class.equals(serviceClass)) {
                    return (T) mockBOS;
                }
                throw new RuntimeException("unexpected request for service " + serviceClass);
            }
        };
        bd.setProposalNumber("1234");
        bd.setBudgetVersionNumber(Integer.valueOf(1));
        
        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
        final BudgetPeriod p = new BudgetPeriod();
        p.setBudgetPeriodId(Long.valueOf(1));
        p.setProposalNumber("1234");
        periods.add(p);
        
        bd.setBudgetPeriods(periods);
        
        context.checking(new Expectations() {
            {
                final Map<Object, Object> matchCriteria = new HashMap<Object, Object>();
                matchCriteria.put("proposalNumber", bd.getProposalNumber());
                matchCriteria.put("budgetVersionNumber", bd.getBudgetVersionNumber());
                
                oneOf(mockBOS).findMatching(BudgetPeriod.class, matchCriteria);
                will(returnValue(periods));
            }
            
            {
                final Map<String, Collection<Long>> matchCriteria = new HashMap<String, Collection<Long>>();
                Collection<Long> ids = new ArrayList<Long>();
                ids.add(Long.valueOf(1));
                matchCriteria.put("budgetPeriodId", ids);
                never(mockBOS).deleteMatching(BudgetProjectIncome.class, matchCriteria);
            }
        });
        
        
        Method m = bd.getClass().getSuperclass().getDeclaredMethod("handlePeriodToProjectIncomeRelationship");
        m.setAccessible(true);
        m.invoke(bd);
        
        context.assertIsSatisfied();
    }
    
    /**
     * Tests that project incomes are deleted when a budget period deletion is detected.
     * This tests the condition where periods exist and are new periods (i.e. no id assigned yet).
     * 
     * @throws Exception
     */
    @Test
    public void handlePeriodToProjectIncomeRelationshipNoItemsToDeleteNewPeriodsExist() throws Exception {
        
        final BusinessObjectService mockBOS = context.mock(BusinessObjectService.class);
        
        final BudgetDocument bd = new BudgetDocument() {
            @Override
            protected <T> T getService(Class<T> serviceClass) {
                if (BusinessObjectService.class.equals(serviceClass)) {
                    return (T) mockBOS;
                }
                throw new RuntimeException("unexpected request for service " + serviceClass);
            }
        };
        bd.setProposalNumber("1234");
        bd.setBudgetVersionNumber(Integer.valueOf(1));
        
        final List<BudgetPeriod> newPeriods = new ArrayList<BudgetPeriod>();
        final BudgetPeriod newP = new BudgetPeriod();
        newP.setBudgetPeriodId(null);
        newP.setProposalNumber("1234");
        newPeriods.add(newP);
        
        bd.setBudgetPeriods(newPeriods);
        
        context.checking(new Expectations() {
            {
                final Map<Object, Object> matchCriteria = new HashMap<Object, Object>();
                matchCriteria.put("proposalNumber", bd.getProposalNumber());
                matchCriteria.put("budgetVersionNumber", bd.getBudgetVersionNumber());
                
                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
                final BudgetPeriod p = new BudgetPeriod();
                p.setBudgetPeriodId(Long.valueOf(1));
                p.setProposalNumber("1234");
                periods.add(p);
                
                oneOf(mockBOS).findMatching(BudgetPeriod.class, matchCriteria);
                will(returnValue(periods));
            }
            
            {
                final Map<String, Collection<Long>> matchCriteria = new HashMap<String, Collection<Long>>();
                Collection<Long> ids = new ArrayList<Long>();
                ids.add(Long.valueOf(1));
                matchCriteria.put("budgetPeriodId", ids);
                oneOf(mockBOS).deleteMatching(BudgetProjectIncome.class, matchCriteria);
            }
        });
        
        
        Method m = bd.getClass().getSuperclass().getDeclaredMethod("handlePeriodToProjectIncomeRelationship");
        m.setAccessible(true);
        m.invoke(bd);
        
        context.assertIsSatisfied();
    }
}
