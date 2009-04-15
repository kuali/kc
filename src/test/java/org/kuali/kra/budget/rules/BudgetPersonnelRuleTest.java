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
package org.kuali.kra.budget.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetService;

/**
 * Tests the BudgetPersonnelRule class.
 */
public class BudgetPersonnelRuleTest {

    private Mockery context;
    
    @Before
    public void setupJMock() {
        context = new JUnit4Mockery();
    }
    
    @Before
    public void setupErrorMap() {
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    /**
     * Test instantiating with null BusinessObjectService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullBusinessObjectService() {
        new BudgetPersonnelRule(null, context.mock(KualiConfigurationService.class), context.mock(BudgetService.class));
    }
    
    /**
     * Test instantiating with null KualiConfigurationService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullKualiConfigurationService() {
        new BudgetPersonnelRule(context.mock(BusinessObjectService.class), null, context.mock(BudgetService.class));
    }
    
    /**
     * Test instantiating with null BudgetService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullBudgetService() {
        new BudgetPersonnelRule(context.mock(BusinessObjectService.class), context.mock(KualiConfigurationService.class), null);
    }
    
    /**
     * Tests calling processCheckForJobCodeChange with a null document.
     */
    @Test(expected = NullPointerException.class)
    public void testProcessCheckForJobCodeChangeNullDoc() {
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(KualiConfigurationService.class), context.mock(BudgetService.class));
        rule.processCheckForJobCodeChange(null, 1);
    }
    
    /**
     * Tests calling processCheckForJobCodeChange with a invalid period number.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testProcessCheckForJobCodeChangeInvalidPeriod() {
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(KualiConfigurationService.class), context.mock(BudgetService.class));
        rule.processCheckForJobCodeChange(new BudgetDocument(), 0);
    }
    
    /**
     * This method checks that a validation error occurs when a person's job code is null but has details.
     */
    @Test
    public void testJobCodeNullDetails() {
        final BudgetService budgetService = context.mock(BudgetService.class);
        
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(KualiConfigurationService.class), budgetService);
        
        context.checking(new Expectations() {
            {   
                oneOf(budgetService).getApplicableCostElements("1234", "1", "1");
                will(returnValue(Collections.emptyList()));
            }});
        
        BudgetDocument doc = getBudgetDoc();
        doc.getBudgetPerson(0).setJobCode(null);
        
        rule.processCheckForJobCodeChange(doc, 1);
        
        context.assertIsSatisfied();
        Assert.assertEquals(1, GlobalVariables.getErrorMap().size());
        Assert.assertTrue(GlobalVariables.getErrorMap().containsKey("document.budgetPersons[0].jobCode"));
    }
    
    /**
     * This method checks that validation errors do not occur when a
     * person's job code is null and does not have details.
     */
    @Test
    public void testJobCodeNullNoDetails() {
        final BudgetService budgetService = context.mock(BudgetService.class);
        
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(KualiConfigurationService.class), budgetService);
        
        context.checking(new Expectations() {
            {   
                oneOf(budgetService).getApplicableCostElements("1234", "1", "1");
                will(returnValue(Collections.emptyList()));
            }});
        
        BudgetDocument doc = getBudgetDoc();
        doc.getBudgetPerson(0).setJobCode("Foo");
        
        rule.processCheckForJobCodeChange(doc, 1);
        
        context.assertIsSatisfied();
        Assert.assertEquals(0, GlobalVariables.getErrorMap().size());
    }
    
    /**
     * Gets a budget doc with the minimum fields set to execute the current set of tests.
     * @return the BudgetDocument
     */
    private BudgetDocument getBudgetDoc() {
        BudgetDocument doc = new BudgetDocument();
        
        List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
        BudgetPeriod period = new BudgetPeriod();
        
        List<BudgetLineItem> lineItems = new ArrayList<BudgetLineItem>();
        BudgetLineItem lineItem = new BudgetLineItem();
        lineItem.setLineItemSequence(1);
        
        List<BudgetPersonnelDetails> lineItemDetails = new ArrayList<BudgetPersonnelDetails>();
        BudgetPersonnelDetails details = new BudgetPersonnelDetails();
        details.setLineItemSequence(1);
        details.setPersonSequenceNumber(1);
        lineItemDetails.add(details);
        
        lineItem.setBudgetPersonnelDetailsList(lineItemDetails);
        
        lineItems.add(lineItem);
        period.setBudgetLineItems(lineItems);
        
        periods.add(period);
        
        List<BudgetPerson> persons = new ArrayList<BudgetPerson>();
        BudgetPerson person = new BudgetPerson() {
            private String jc;
            @Override
            public void setJobCode(String jobCode) {
                jc = jobCode;
            }
            @Override
            public String getJobCode() {
                return jc;
            }
        };
        
        person.setPersonSequenceNumber(1);
        person.setProposalNumber("1234");
        person.setBudgetVersionNumber(1);
        
        persons.add(person);
        
        doc.setBudgetPeriods(periods);
        doc.setBudgetPersons(persons);
        doc.setProposalNumber("1234");
        doc.setBudgetVersionNumber(1);
        return doc;
    }
}
