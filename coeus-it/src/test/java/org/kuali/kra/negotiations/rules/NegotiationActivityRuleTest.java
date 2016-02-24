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
package org.kuali.kra.negotiations.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationFixtureFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Calendar;
import static org.junit.Assert.*;
public class NegotiationActivityRuleTest extends KcIntegrationTestBase {

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
