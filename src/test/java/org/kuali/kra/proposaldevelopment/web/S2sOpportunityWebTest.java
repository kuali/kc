/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * This class tests tests the Sponsor & Program Information panel of the Proposal Development
 * Proposal web page.
 */
public class S2sOpportunityWebTest extends ProposalDevelopmentWebTestBase {


    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String S2S_OPPORTUNITY_PAGE = "methodToCall.headerTab.headerDispatch.save.navigateTo.grantsGov.x";

    /**
     * Modified for KRACOEUS-252 - CFDA number, Opportunity ID/programAnnouncementNumber formatting
     * @throws Exception
     */
    @Test
    public void testS2sOpportunity() throws Exception {

        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        HtmlPage s2sOpportunityPage = clickOn(proposalPage, S2S_OPPORTUNITY_PAGE);
        
        // s2s opportunity fields
        setFieldValue(s2sOpportunityPage, "document.opportunityId", "13");
        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.opportunityTitle", "UnitTestingTheS2S");
//        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.s2sSubmissionTypeCode", "Application");
//        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.revisionCode", "Increase Award");
        setFieldValue(s2sOpportunityPage, "document.cfdaNumber", "33");
        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.competetionId", "50");
        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.openingDate", "2007-08-14");
        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.closingDate", "2008-01-14");
        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.instructionUrl", "http://www.google.com");
        setFieldValue(s2sOpportunityPage, "newS2sOpportunity.schemaUrl", "http://www.rediff.com");
        
        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");

        HtmlPage saveds2sOpportunityPage = clickOn(s2sOpportunityPage, "methodToCall.save", "Kuali :: Proposal Development Document");

        assertDoesNotContain(saveds2sOpportunityPage, ERRORS_FOUND_ON_PAGE);

        // make sure the document saved correctly
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber);
        assertNotNull(doc);

        verifySavedRequiredFields(doc, DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_OWNED_BY_UNIT, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "2007-08-14", "2007-08-21", DEFAULT_PROPOSAL_TYPE_CODE);

        // check s2s opportunity fields
        assertEquals("13", doc.getS2sOpportunity().getOpportunity());
        assertEquals("UnitTestingTheS2S", doc.getS2sOpportunity().getOpportunityTitle());
//        assertEquals("2", doc.getS2sSubmissionTypeCode());
//        assertEquals("1", doc.getRevisionCode());
        assertEquals("33", doc.getS2sOpportunity().getCfdaNumber());
        assertEquals("50", doc.getS2sOpportunity().getCompetetionId());
        assertEquals("2007-08-14", doc.getS2sOpportunity().getOpeningDate());        
        assertEquals("2008-01-14", doc.getS2sOpportunity().getClosingDate());
        assertEquals("http://www.google.com", doc.getS2sOpportunity().getInstructionUrl());
        assertEquals("http://www.rediff.com", doc.getS2sOpportunity().getSchemaUrl());
        
        // make sure the fields we set are displayed on the form after saving
        assertContains(saveds2sOpportunityPage, "Document was successfully saved.");

        // sponsor program info fields
        assertEquals("13", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.opportunityId"));
        assertEquals("UnitTestingTheS2S", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.opportunityTitle"));
//        assertEquals("2", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.s2sSubmissionTypeCode"));
//        assertEquals("1", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.revisionCode"));
        assertEquals("33", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.cfdaNumber"));
        assertEquals("50", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.competetionId"));
        assertEquals("2007-08-14", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.openingDate"));
        assertEquals("2008-01-14", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.closingDate"));
        assertEquals("http://www.google.com", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.instructionUrl"));
        assertEquals("http://www.rediff.com", getFieldValue(saveds2sOpportunityPage, "newS2sOpportunity.schemaUrl"));   
    } 
}
