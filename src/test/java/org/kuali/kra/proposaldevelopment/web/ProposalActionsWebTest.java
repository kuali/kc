/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web;

import java.util.List;

import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProposalActionsWebTest extends ProposalDevelopmentWebTestBase {
    
    private static final String VALID_OPPORTUNITY_ID_APP_S2_S_TEST_SF424_V2 = "CWG-05";
    private static final String VALID_CFDA_NUMBER_00_000 = "00.000";
    private static final String GRANTS_GOV_TAB_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.grantsGov.x";
    private static final String PROPOSAL_ACTIONS_TAB_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.actions.x";
    private static final String BUDGET_VERSIONS_TAB_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    
    /**
     * 
     * Test Grants.gov lookup when both CFDA Number and Opportunity Id are passed to the lookup helper service
     * then tests the ProposalActions DataValidation
     * @throws Exception
     */
    @Test
    public void testDataValidationGrantsDotGovErrors() throws Exception{

        HtmlPage proposalPage = getProposalDevelopmentPage();
        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);        
        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");                
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", VALID_CFDA_NUMBER_00_000);
        setFieldValue(proposalPage, "document.programAnnouncementNumber", VALID_OPPORTUNITY_ID_APP_S2_S_TEST_SF424_V2);              
 
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_TAB_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","opportunityId",VALID_OPPORTUNITY_ID_APP_S2_S_TEST_SF424_V2,false);
        assertContains(page2,VALID_CFDA_NUMBER_00_000);
        assertContains(page2,VALID_OPPORTUNITY_ID_APP_S2_S_TEST_SF424_V2);
        
        setFieldValue(page2,"document.s2sOpportunity.s2sSubmissionTypeCode","1");
        HtmlPage page3 = clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        
        assertEquals(doc.getS2sOpportunity().getOpportunityId(),VALID_OPPORTUNITY_ID_APP_S2_S_TEST_SF424_V2);
        assertEquals(doc.getS2sOpportunity().getCfdaNumber(),VALID_CFDA_NUMBER_00_000);
        assertEquals(doc.getS2sOpportunity().getS2sSubmissionTypeCode().toString(),"1");
        
        ProposalDevelopmentDocument propDevDoc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        
        if (propDevDoc.getS2sOpportunity() != null) {
            log.debug("Saved document has opportunity["+propDevDoc.getS2sOpportunity().getOpportunityId()+"]");
        } else {
            log.debug("Saved document has opportunity is null");
        }
        
        HtmlPage proposalActionsPage = clickOn(page3, PROPOSAL_ACTIONS_TAB_NAME);
        
        HtmlPage auditOffProposalPage = clickOn(proposalActionsPage, 
                                                "methodToCall.toggleTab.tabDataValidation", 
                                                "Kuali :: Proposal Development Document");
        
        
        HtmlPage auditOnProposalPage = clickOn(auditOffProposalPage, "methodToCall.activate", "Kuali :: Proposal Development Document");
        
        List<HtmlElement> grantsDotGovErrors = getAllElementsByName(auditOnProposalPage, "methodToCall.toggleTab.tabGrantsGovGrantsGovErrors", false);

        int numberGDGErrors = grantsDotGovErrors.size();
        System.out.println("# errors = ["+numberGDGErrors+"]");
        
        org.junit.Assert.assertTrue(numberGDGErrors>0);
        
        HtmlPage budgetVersionsPage = clickOn(page3, BUDGET_VERSIONS_TAB_NAME);
        
        HtmlPage nextProposalActionsPage = clickOn(budgetVersionsPage, PROPOSAL_ACTIONS_TAB_NAME);
        
        List<HtmlElement> nextGrantsDotGovErrors = getAllElementsByName(nextProposalActionsPage, "methodToCall.toggleTab.tabGrantsGovGrantsGovErrors", false);

        assertTrue(nextGrantsDotGovErrors.size()==numberGDGErrors);
        
    }
    


}
