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
import org.kuali.kra.negotiations.bo.NegotiationFixtureFactory;
import org.kuali.kra.negotiations.service.NegotiationServiceImpl;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class NegotiationDocumentRuleTest extends KcIntegrationTestBase {

    protected Negotiation negotiation;
    protected NegotiationDocumentRule rule;
    
    @Before
    public void setUp() {
        negotiation = NegotiationFixtureFactory.createNegotiationFixture();
        rule = new NegotiationDocumentRule();
        rule.setNegotiationService(new NegotiationServiceMock());
    }
    
    @After
    public void tearDown() {
        negotiation = null;
    }

    @Test
    public void testEndDateRule_inProgress() {
        negotiation.setNegotiationEndDate(null);
        assertTrue(rule.validateEndDate(negotiation));
    }
    
    @Test
    public void testEndDateRule_inProgressFailure() {
        assertFalse(rule.validateEndDate(negotiation));
    }
    
    @Test
    public void testEndDateRule_CompletedFailure() {
        negotiation.setNegotiationEndDate(null);
        negotiation.getNegotiationStatus().setCode("COM");
        assertFalse(rule.validateEndDate(negotiation));
    }
    
    @Test
    public void testEndDateRule_Completed() {
        negotiation.getNegotiationStatus().setCode("COM");
        assertTrue(rule.validateEndDate(negotiation));        
    }
    
    @Test
    public void testEndDateRule_EndDateBeforeStart() {
        negotiation.setNegotiationEndDate(new java.sql.Date(2005, 1, 1));
        negotiation.getNegotiationStatus().setCode("COM");
        assertFalse(rule.validateEndDate(negotiation));        
    }
    
    class NegotiationServiceMock extends NegotiationServiceImpl {
        public List<String> getInProgressStatusCodes() {
            return Arrays.asList(new String[]{"IP", "TESTIP"});
        }
        public List<String> getCompletedStatusCodes() {
            return Arrays.asList(new String[]{"SUP", "COM"});
        }
    }
}
