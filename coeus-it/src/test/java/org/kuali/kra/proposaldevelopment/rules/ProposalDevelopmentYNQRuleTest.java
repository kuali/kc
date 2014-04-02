/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.Ynq;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
public class ProposalDevelopmentYNQRuleTest extends ProposalDevelopmentRuleTestBase{

    private ProposalDevelopmentDocumentRule rule = null;
    private List<ProposalYnq> proposalYnqs;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentDocumentRule();
        bos = KcServiceLocator.getService(BusinessObjectService.class);
        proposalYnqs = (List)bos.findAll(Ynq.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        bos = null;
        proposalYnqs=null;
        super.tearDown();
    }

    /** 
     * 
     * This method is used to check ynqs are OK before do the real rule test
     */
    @Test
    public void checkCodes() {
        assertNotNull(proposalYnqs);
        assertTrue(proposalYnqs.size()>1);
    }


    /**
     * Test initial proposal ynq.
     * 
     * @throws Exception
     */
    @Test
    public void testInitialProposalYnq() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertTrue(rule.processProposalYNQBusinessRule(document, false));
    }

    /**
     * Test routing proposal ynq.
     * 
     * @throws Exception
     */
    @Test
    public void testRoutingProposalYnq() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.getDevelopmentProposal().getYnqGroupNames();

        for (ProposalYnq ynq : document.getDevelopmentProposal().getProposalYnqs()) {
            ynq.setAnswer("Y");
            ynq.setExplanation("Because I said so");
            ynq.setReviewDate(new Date(System.currentTimeMillis()));
            
        }

        assertTrue(rule.processProposalYNQBusinessRule(document, true));
    }
}