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
package org.kuali.kra.s2s.service;


import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;

public class FindOpportunityTest{
    private static final Logger LOG = Logger.getLogger(FindOpportunityTest.class);
    private S2SService getS2SService(){
        return KraServiceLocator.getService(S2SService.class);
    }
    @Test
    public void testSearchOpportunity() throws Exception{
        List<S2sOpportunity> l = getS2SService().searchOpportunity("00.000", "APPLE-INDV-1", null);
        Assert.assertFalse(l.isEmpty());
        LOG.info(l.get(0));
    }
    @Test
    public void parseOpportunityTest() throws Exception{
        List<S2sOpportunity> l = getS2SService().searchOpportunity("00.000", null, null);
        S2sOpportunity opp = l.get(0);
        List<S2sOppForms> oppForms = getS2SService().parseOpportunityForms(opp);
        Assert.assertFalse(oppForms.isEmpty());
    }
}
