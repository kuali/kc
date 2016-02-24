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
package org.kuali.kra.award.subcontracting.goalsAndExpenditures;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

public class AwardSubcontractingGoalsExpendituresServiceTest {

    private static final String AWARD_NUMBER = "awardNumber";
    private static final String FRESH_BO_NUMBER = "fresh BO number";
    private static final String PRESTORED_BO_NUMBER = "prestored BO number";
    private static final String SAVE_BO_NUMBER = "save BO number";
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    private AwardSubcontractingGoalsExpendituresServiceImpl serviceImpl;
    
    @Before
    public void setUp() throws Exception {
        serviceImpl = new AwardSubcontractingGoalsExpendituresServiceImpl(); 
        serviceImpl.setBusinessObjectService(context.mock(BusinessObjectService.class));        
    }

    
    // test with the mock BO service returning null for the input award number
    @Test
    public void testGetGoalsExpendituresBOForAwardFresh() {
        final String awardNumber = FRESH_BO_NUMBER;
        final Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(AWARD_NUMBER, awardNumber);
        
        context.checking(new Expectations() {{
          one(serviceImpl.getBusinessObjectService()).findByPrimaryKey(AwardSubcontractingBudgetedGoals.class, fieldValues);
          will(returnValue(null));
        }});
        
        // invoke the service method
        AwardSubcontractingBudgetedGoals actualBO = this.serviceImpl.getBudgetedGoalsBOForAward(awardNumber);
        
        Assert.assertNotNull(actualBO);
        Assert.assertTrue(actualBO.isFresh());
    }   
    
    
    // test with the mock BO service returning some non-null dummy BO for the input award number
    @Test
    public void testGetGoalsExpendituresBOForAwardPreStored() {
        final String awardNumber = PRESTORED_BO_NUMBER;
        final Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(AWARD_NUMBER, awardNumber);
        final AwardSubcontractingBudgetedGoals returnBO = new AwardSubcontractingBudgetedGoals();
        returnBO.setAwardNumber(awardNumber);
        
        context.checking(new Expectations() {{          
          one(serviceImpl.getBusinessObjectService()).findByPrimaryKey(AwardSubcontractingBudgetedGoals.class, fieldValues);
          will(returnValue(returnBO));
        }});
        
        // invoke the service method
        AwardSubcontractingBudgetedGoals actualBO = this.serviceImpl.getBudgetedGoalsBOForAward(awardNumber);
        
        Assert.assertNotNull(actualBO);
        Assert.assertFalse(actualBO.isFresh());
        Assert.assertSame(returnBO, actualBO);
        // assert that the award number of the dummy BO returned by the mock is not changed by the service method
        Assert.assertEquals(awardNumber, actualBO.getAwardNumber());
    }
    
    @Test
    public void testSaveGoalsExpendituresBO() {
        // create a 'fresh' BO for saving (the non-default BO constructor ensures fresh flag is set).
        final AwardSubcontractingBudgetedGoals toBeSavedBO = new AwardSubcontractingBudgetedGoals(SAVE_BO_NUMBER);
        
        context.checking(new Expectations() {{          
            one(serviceImpl.getBusinessObjectService()).save(toBeSavedBO);
        }});        
        // invoke the service method
        Assert.assertTrue(toBeSavedBO.isFresh());
        this.serviceImpl.saveBudgetedGoalsBO(toBeSavedBO);
        Assert.assertFalse(toBeSavedBO.isFresh());
        
        
       // test with saving the same BO which is now 'unfresh'
        context.checking(new Expectations() {{          
            one(serviceImpl.getBusinessObjectService()).save(toBeSavedBO);
        }});        
        // invoke the service method
        this.serviceImpl.saveBudgetedGoalsBO(toBeSavedBO);
        Assert.assertFalse(toBeSavedBO.isFresh());
    }
    
}
