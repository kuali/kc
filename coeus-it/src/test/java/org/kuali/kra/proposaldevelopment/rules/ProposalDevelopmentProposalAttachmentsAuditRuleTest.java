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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentProposalAttachmentsAuditRule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.GlobalVariables;

import static org.junit.Assert.*;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;

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
        assertTrue(GlobalVariables.getAuditErrorMap().get(ATTACHMENT_PAGE_NAME + "." + ATTACHMENT_PROPOSAL_SECTION_NAME) == null);
        
        developmentProposal.getNarratives().add(narrativeIncomplete);
        assertFalse(rule.checkForIncompleteAttachments(developmentProposal));
        assertFalse(((AuditCluster) GlobalVariables.getAuditErrorMap().get(ATTACHMENT_PAGE_NAME + "." + ATTACHMENT_PROPOSAL_SECTION_NAME)).getAuditErrorList().isEmpty());
    }

}
