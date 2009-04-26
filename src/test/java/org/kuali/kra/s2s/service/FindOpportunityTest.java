/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.s2s.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.impl.S2SServiceImpl;

import edu.mit.coeus.utils.S2SConstants;

public class FindOpportunityTest extends KraTestBase implements S2SConstants{
    private static final Logger LOG = Logger.getLogger(S2SServiceImpl.class);
    @Before
	public void setUp() throws Exception {
		super.setUp();
	}
    @After
	public void tearDown() throws Exception {
		super.tearDown();
	}
    private S2SService getS2SService(){
        return getService(S2SService.class);
    }
    @Test
    public void testSearchOpportunity(){
        List<S2sOpportunity> l = getS2SService().searchOpportunity("00.000", null, null);
        assertNotNull(l);
        assertTrue(l.size()>0);
        LOG.info(l.get(0));
    }
    @Test
    public void parseOpportunityTest(){
        List<S2sOpportunity> l = getS2SService().searchOpportunity("00.000", null, null);
        assertNotNull(l);
        assertTrue(l.size()>0);
        S2sOpportunity opp = l.get(0);
        List<S2sOppForms> oppForms = getS2SService().parseOpportunityForms(opp);
        assertNotNull(oppForms);
        assertTrue(oppForms.size()>0);
//        LOG.info(oppForms);
    }
}
