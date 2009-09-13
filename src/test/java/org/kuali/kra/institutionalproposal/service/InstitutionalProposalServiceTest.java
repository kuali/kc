/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;

public class InstitutionalProposalServiceTest extends KraTestBase {
    
    private InstitutionalProposalService institutionalProposalService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testCreateInstitutionalProposal() throws Exception {
        DevelopmentProposal developmentProposal = getBaseDevelopmentProposal();
        String proposalNumber = getInstitutionalProposalService().createInstitutionalProposal(developmentProposal);
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("proposalNumber", proposalNumber);
        List<InstitutionalProposal> proposals = 
            new ArrayList<InstitutionalProposal>(getBusinessObjectService().findMatching(InstitutionalProposal.class, fieldValues));
        assertEquals(proposals.size(), 1);
        InstitutionalProposal institutionalProposal = proposals.get(0);
        assertEquals(developmentProposal.getActivityTypeCode(), institutionalProposal.getActivityTypeCode());
        assertEquals(developmentProposal.getSponsorCode(), institutionalProposal.getSponsorCode());
        assertEquals(developmentProposal.getTitle(), institutionalProposal.getTitle());
        assertEquals(developmentProposal.getProposalTypeCode(), institutionalProposal.getProposalTypeCode().toString());
    }
    
    private DevelopmentProposal getBaseDevelopmentProposal() {
        DevelopmentProposal developmentProposal = new DevelopmentProposal();
        developmentProposal.setSponsorCode("005891");
        developmentProposal.setTitle("An awesome proposal");
        developmentProposal.setActivityTypeCode("1");
        developmentProposal.setProposalTypeCode("1");
        return developmentProposal;
    }
    
    private InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KraServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }

}
