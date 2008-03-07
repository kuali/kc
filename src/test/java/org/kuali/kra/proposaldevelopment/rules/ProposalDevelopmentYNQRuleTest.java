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
package org.kuali.kra.proposaldevelopment.rules;

import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;

public class ProposalDevelopmentYNQRuleTest extends ProposalDevelopmentRuleTestBase{

    private static final String NEW_PROPOSAL_SPECIAL_REVIEW = "newPropSpecialReview";
    private ProposalDevelopmentDocumentRule rule = null;
    private List<ProposalYnq> proposalYnqs;
    private List<SpecialReview> specialReviewCodes;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentDocumentRule();
        //dateFormat = DateFormat.getDateInstance();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        proposalYnqs = (List)bos.findAll(ProposalYnq.class);
        //specialReviewCodes = (List)bos.findAll(SpecialReview.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        //dateFormat = null;
        bos = null;
        proposalYnqs=null;
        //specialReviewCodes=null;
        super.tearDown();
    }

    /** 
     * 
     * This method approval type and special review codes are OK before do the real rule test
     */
    @Test
    public void checkCodes() {
        assertNotNull(proposalYnqs);
        //assertNotNull(specialReviewCodes);
        assertTrue(proposalYnqs.size()>1);
        //assertTrue(specialReviewCodes.size()>1);
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
        assertTrue(rule.processProposalYNQBusinessRule(document, true));
    }

    /**
     * Test routing proposal ynq.
     * 
     * @throws Exception
     */
    @Test
    public void testRoutingProposalPersonYnq() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertTrue(rule.processProposalPersonYNQBusinessRule(document));
    }

}
