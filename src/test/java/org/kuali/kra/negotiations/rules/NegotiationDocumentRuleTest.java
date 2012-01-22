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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationFixtureFactory;
import org.kuali.kra.negotiations.service.NegotiationServiceImpl;


public class NegotiationDocumentRuleTest {

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
