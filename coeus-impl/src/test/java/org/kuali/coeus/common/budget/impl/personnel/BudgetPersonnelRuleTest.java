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
package org.kuali.coeus.common.budget.impl.personnel;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetSavePersonnelEvent;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tests the BudgetPersonnelRule class.
 */
public class BudgetPersonnelRuleTest {

    private Mockery context;
    private BudgetPersonnelRule rule;
    
    @Before
    public void setupJMock() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }
    
    @Before 
    public void setup() {
    	rule = new BudgetPersonnelRule();
    	rule.setBoService(context.mock(BusinessObjectService.class));
    	rule.setParamService(context.mock(ParameterService.class));
    	rule.setBudgetPersonService(context.mock(BudgetPersonService.class));
    }
    
    @Before
    public void setupErrorMap() {
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    /**
     * Tests calling processCheckForJobCodeChange with a null document.
     */
    @Test(expected = NullPointerException.class)
    public void testProcessCheckForJobCodeChangeNullDoc() {
        rule.processCheckForJobCodeChange(new BudgetSavePersonnelEvent(null, null));
    }
    
    /**
     * Tests calling processCheckForJobCodeChange with a invalid period number.
     */
    @Test(expected = NullPointerException.class)
    public void testProcessCheckForJobCodeChangeInvalidPeriod() {
        rule.processCheckForJobCodeChange(new BudgetSavePersonnelEvent(getBudget(), null));
    }
    
    /**
     * This method checks that a validation error occurs when a person's job code is null but has details.
     */
    @Test
    public void testJobCodeNullDetails() {
        final Budget budget = getBudget();
        budget.getBudgetPerson(0).setJobCode(null);
        
        context.checking(new Expectations() {
            {   
                oneOf(rule.getBudgetPersonService()).getApplicableCostElements(budget, "1");
                will(returnValue(Collections.emptyList()));
            }});
        
        rule.processCheckForJobCodeChange(new BudgetSavePersonnelEvent(budget, budget.getBudgetPeriod(0)));

        context.assertIsSatisfied();
        Assert.assertEquals(1, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.budgetPersons[0].jobCode"));
    }
    
    /**
     * This method checks that validation errors do not occur when a
     * person's job code is null and does not have details.
     */
    @Test
    public void testJobCodeNullNoDetails() {
        final Budget budget = getBudget();
        budget.getBudgetPerson(0).setJobCode("Foo");
        
        context.checking(new Expectations() {
            {
                oneOf(rule.getBudgetPersonService()).getApplicableCostElements(budget, "1");
                will(returnValue(Collections.emptyList()));
            }});
        
        rule.processCheckForJobCodeChange(new BudgetSavePersonnelEvent(budget, budget.getBudgetPeriod(0)));

        context.assertIsSatisfied();
        Assert.assertEquals(0, GlobalVariables.getMessageMap().getErrorMessages().size());
    }
    
    /**
     * Gets a budget doc with the minimum fields set to execute the current set of tests.
     * @return the BudgetDocument
     */
    private Budget getBudget() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
        
        List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
        BudgetPeriod period = new BudgetPeriod();
        
        List<BudgetLineItem> lineItems = new ArrayList<BudgetLineItem>();
        BudgetLineItem lineItem = budget.getNewBudgetLineItem();
        lineItem.setLineItemSequence(1);
        
        List<BudgetPersonnelDetails> lineItemDetails = new ArrayList<BudgetPersonnelDetails>();
        BudgetPersonnelDetails details = lineItem.getNewBudgetPersonnelLineItem();
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
        person.setBudgetId(1L);
        person.setBudget(budget);
        
        persons.add(person);
        
        budget.setBudgetPeriods(periods);
        budget.setBudgetPersons(persons);
        budget.setBudgetVersionNumber(1);
        return budget;
    }
}
