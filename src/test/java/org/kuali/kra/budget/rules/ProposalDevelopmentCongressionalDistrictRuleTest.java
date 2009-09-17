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
package org.kuali.kra.budget.rules;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ProposalSiteEventBase;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentCongressionalDistrictRule;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentRuleTestBase;
import org.kuali.kra.proposaldevelopment.web.struts.form.CongressionalDistrictHelper;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public class ProposalDevelopmentCongressionalDistrictRuleTest extends ProposalDevelopmentRuleTestBase {
    private static final String ERROR_PATH_PREFIX = "document.developmentProposalList[0]";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @Test
    public void testProcessDeleteCongressionalDistrictRules() throws Exception {
        // set up districts, proposal site, document
        CongressionalDistrict district1 = new CongressionalDistrict();
        district1.setCongressionalDistrict("AZ-8");
        CongressionalDistrict district2 = new CongressionalDistrict();
        district2.setCongressionalDistrict("CA-11");
        
        ProposalSite proposalSite = new ProposalSite();
        proposalSite.addCongressionalDistrict(district1);
        proposalSite.addCongressionalDistrict(district2);
        
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.getDevelopmentProposal().setApplicantOrganization(proposalSite);
        
        DeleteProposalCongressionalDistrictEvent deleteCongressionalDistrictEvent;
        ProposalDevelopmentCongressionalDistrictRule proposalDevelopmentCongressionalDistrictRule;

        boolean result;
        
        // test two valid deletion events
        deleteCongressionalDistrictEvent = new DeleteProposalCongressionalDistrictEvent(ERROR_PATH_PREFIX, document, proposalSite, "0");
        proposalDevelopmentCongressionalDistrictRule = new ProposalDevelopmentCongressionalDistrictRule();
        result = proposalDevelopmentCongressionalDistrictRule.processDeleteCongressionalDistrictRules(deleteCongressionalDistrictEvent);
        assertNoErrors(result);
        deleteCongressionalDistrictEvent = new DeleteProposalCongressionalDistrictEvent(ERROR_PATH_PREFIX, document, proposalSite, "1");
        proposalDevelopmentCongressionalDistrictRule = new ProposalDevelopmentCongressionalDistrictRule();
        result = proposalDevelopmentCongressionalDistrictRule.processDeleteCongressionalDistrictRules(deleteCongressionalDistrictEvent);
        assertNoErrors(result);
        
        // try passing an invalid district index
        deleteCongressionalDistrictEvent = new DeleteProposalCongressionalDistrictEvent(ERROR_PATH_PREFIX, document, proposalSite, "ABC");
        proposalDevelopmentCongressionalDistrictRule = new ProposalDevelopmentCongressionalDistrictRule();
        result = proposalDevelopmentCongressionalDistrictRule.processDeleteCongressionalDistrictRules(deleteCongressionalDistrictEvent);
        assertOneError(result, proposalDevelopmentCongressionalDistrictRule, deleteCongressionalDistrictEvent, "newPropLocation.location", KeyConstants.ERROR_PROPOSAL_SITES_INDEX_INVALID_FORMAT);
    }
    
    @Test
    public void testProcessAddCongressionalDistrictRules() throws Exception {
        ProposalSite proposalSite = new ProposalSite();
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.getDevelopmentProposal().setApplicantOrganization(proposalSite);
        
        // set up districts, proposal site, document
        CongressionalDistrict district1 = new CongressionalDistrict();
        district1.setCongressionalDistrict("AZ-8");
        CongressionalDistrict district2 = new CongressionalDistrict();
        district2.setCongressionalDistrict("CA-11");
        
        CongressionalDistrictHelper districtHelper = new CongressionalDistrictHelper();
        boolean result;

        AddProposalCongressionalDistrictEvent addCongressionalDistrictEvent;
        ProposalDevelopmentCongressionalDistrictRule proposalDevelopmentCongressionalDistrictRule;
        
        // test valid add
        districtHelper.setNewState("AZ");
        districtHelper.setNewDistrictNumber("5");
        addCongressionalDistrictEvent = new AddProposalCongressionalDistrictEvent(ERROR_PATH_PREFIX, document, proposalSite, districtHelper);
        proposalDevelopmentCongressionalDistrictRule = new ProposalDevelopmentCongressionalDistrictRule();
        result = proposalDevelopmentCongressionalDistrictRule.processAddCongressionalDistrictRules(addCongressionalDistrictEvent);
        assertNoErrors(result);
        
        // test invalid state
        districtHelper.setNewState("XX");
        districtHelper.setNewDistrictNumber("5");
        addCongressionalDistrictEvent = new AddProposalCongressionalDistrictEvent(ERROR_PATH_PREFIX, document, proposalSite, districtHelper);
        GlobalVariables.getErrorMap().clearErrorMessages();
        proposalDevelopmentCongressionalDistrictRule = new ProposalDevelopmentCongressionalDistrictRule();
        result = proposalDevelopmentCongressionalDistrictRule.processAddCongressionalDistrictRules(addCongressionalDistrictEvent);
        assertOneError(result, proposalDevelopmentCongressionalDistrictRule, addCongressionalDistrictEvent, "newPropLocation.location", KeyConstants.ERROR_PROPOSAL_SITES_STATE_CODE_INVALID);
        
        // test invalid district number
        districtHelper.setNewState("OR");
        districtHelper.setNewDistrictNumber("X");
        addCongressionalDistrictEvent = new AddProposalCongressionalDistrictEvent(ERROR_PATH_PREFIX, document, proposalSite, districtHelper);
        GlobalVariables.getErrorMap().clearErrorMessages();
        proposalDevelopmentCongressionalDistrictRule = new ProposalDevelopmentCongressionalDistrictRule();
        result = proposalDevelopmentCongressionalDistrictRule.processAddCongressionalDistrictRules(addCongressionalDistrictEvent);
        assertOneError(result, proposalDevelopmentCongressionalDistrictRule, addCongressionalDistrictEvent, "newPropLocation.location", KeyConstants.ERROR_PROPOSAL_SITES_DISTRICT_NUMBER_INVALID_FORMAT);
    }
    
    private void assertNoErrors(boolean ruleCheckResult) {
        assertTrue(ruleCheckResult);
        assertEquals(0, GlobalVariables.getErrorMap().getErrorCount());
    }
    
    private void assertOneError(boolean ruleCheckResult, ProposalDevelopmentCongressionalDistrictRule rule, ProposalSiteEventBase event, String propertyKey, String expectedErrorKey) {
        assertFalse(ruleCheckResult);
        TypedArrayList errorMessages = GlobalVariables.getErrorMap().getErrorMessagesForProperty(propertyKey);
        assertNotNull(errorMessages);
        assertEquals(1, errorMessages.size());
        String actualErrorKey = ((ErrorMessage)errorMessages.get(0)).getErrorKey();
        assertEquals(expectedErrorKey, actualErrorKey);
    }
}