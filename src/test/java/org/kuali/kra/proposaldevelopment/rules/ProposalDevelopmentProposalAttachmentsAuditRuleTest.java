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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentProposalAttachmentsAuditRule.AUDIT_CLUSTER_KEY;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProposalDevelopmentProposalAttachmentsAuditRuleTest extends ProposalDevelopmentRuleTestBase {
    
    ProposalDevelopmentProposalAttachmentsAuditRule rule;
    ProposalDevelopmentDocument proposalDevelopmentDocument;
    DevelopmentProposal developmentProposal;
    Narrative narrativeComplete;
    Narrative narrativeIncomplete;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentProposalAttachmentsAuditRule();
        proposalDevelopmentDocument = this.getNewProposalDevelopmentDocument();
        developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        narrativeComplete = new Narrative();
        narrativeComplete.setModuleStatusCode("C");
        narrativeIncomplete = new Narrative();
        narrativeIncomplete.setModuleStatusCode("I");
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testIncompleteAttachments() {
        developmentProposal.getNarratives().add(narrativeComplete);
        assertTrue(rule.checkForIncompleteAttachments(developmentProposal));
        assertTrue(GlobalVariables.getAuditErrorMap().get(AUDIT_CLUSTER_KEY) == null);
        
        developmentProposal.getNarratives().add(narrativeIncomplete);
        assertFalse(rule.checkForIncompleteAttachments(developmentProposal));
        assertFalse(((AuditCluster) GlobalVariables.getAuditErrorMap().get(AUDIT_CLUSTER_KEY)).getAuditErrorList().isEmpty());        
    }

}
