/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.negotiations.rules;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationFixtureFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.util.GlobalVariables;

public class NegotiationActivityRuleTest extends KcUnitTestBase {

    private NegotiationActivityRuleImpl rule;
    private Negotiation negotiation;
    private NegotiationActivity activity;
    
    @Before
    public void setUp() {
        rule = new NegotiationActivityRuleImpl();
        negotiation = NegotiationFixtureFactory.createNegotiationFixture();
        activity = new NegotiationActivity();
        activity.setActivityTypeId(1L);
        activity.setLocationId(1L);
        activity.setDescription("Testing");
        activity.setStartDate(negotiation.getNegotiationStartDate());
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testActivityRuleSuccess() {
        assertTrue(rule.validateNegotiationActivity(activity, negotiation));
        assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testStartDateBefore() {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(activity.getStartDate());
        startDate.add(Calendar.DAY_OF_YEAR, -1);
        activity.setStartDate(new java.sql.Date(startDate.getTime().getTime()));
        assertFalse(rule.validateNegotiationActivity(activity, negotiation));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testFollowupBefore() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, -1);
        activity.setFollowupDate(new java.sql.Date(date.getTime().getTime()));
        assertFalse(rule.validateNegotiationActivity(activity, negotiation));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());        
    }
    
    @Test
    public void testEndBeforeStart() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, -1);
        activity.setEndDate(new java.sql.Date(date.getTime().getTime()));
        assertFalse(rule.validateNegotiationActivity(activity, negotiation));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());        
    }
    
    @Test
    public void testEndAfterNegotiationEnd() {
        Calendar date = Calendar.getInstance();
        negotiation.setNegotiationEndDate(new java.sql.Date(date.getTime().getTime()));
        date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, -1);
        activity.setEndDate(new java.sql.Date(date.getTime().getTime()));
        assertFalse(rule.validateNegotiationActivity(activity, negotiation));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());        
    }
}
