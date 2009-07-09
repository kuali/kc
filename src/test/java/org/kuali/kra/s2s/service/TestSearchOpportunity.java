/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.util.S2STestUtils;
import org.kuali.kra.s2s.service.S2SService;

public class TestSearchOpportunity extends Assert {
    
    private static final Logger LOG = Logger.getLogger(TestSearchOpportunity.class);
    S2SService s2SService =((S2SService) KraServiceLocator.getService(S2SService.class));
    
    @Test
    public void testSearchOpportunityForCfdaNumber(){
        LOG.info("Searching for cfda number 00.000");
        List<S2sOpportunity> opportunityList=null;
        try {
            opportunityList = s2SService.searchOpportunity("00.000", null, null);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        assertNotNull(opportunityList);
        assertTrue(opportunityList.size()>0);
    }
    
    @Test
    public void testSearchOpportunityForOpportunityId(){
        List<S2sOpportunity> opportunityList=null;
        try {
            opportunityList = s2SService.searchOpportunity("00.000", "APPLE-INDV-1", null);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        assertNotNull(opportunityList);
        assertTrue(opportunityList.size()>0);
    }
    
    @Test
    public void testSearchOpportunityForCfdaNumberAndOpportunityId(){
        List<S2sOpportunity> opportunityList=null;
        try {
            opportunityList = s2SService.searchOpportunity("00.000", "APPLE-INDV-1", null);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        assertNotNull(opportunityList);
        assertTrue(opportunityList.size()>0);
    }
    
    @Test
    public void parseOpportunityTest() {
        List<S2sOpportunity> opportunityList=null;
        try {
            opportunityList = s2SService.searchOpportunity("00.000", S2STestUtils.TEST_OPPORTUNITY_ID, null);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        assertNotNull(opportunityList);
        assertTrue(opportunityList.size() > 0);
        S2sOpportunity opp = opportunityList.get(0);
        List<S2sOppForms> oppForms = s2SService.parseOpportunityForms(opp);
        assertNotNull(oppForms);
        assertTrue(oppForms.size() > 0);
        LOG.info(oppForms);
    }
}
