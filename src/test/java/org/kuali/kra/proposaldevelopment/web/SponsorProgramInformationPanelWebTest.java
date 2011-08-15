/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * This class tests tests the Sponsor & Program Information panel of the Proposal Development
 * Proposal web page.
 */

public class SponsorProgramInformationPanelWebTest extends ProposalDevelopmentWebTestBase {


    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String DEADLINE_DATE= "document.developmentProposalList[0].deadlineDate";
    private static final String DEADLINE_TYPE= "document.developmentProposalList[0].deadlineType";
    private static final String PRIME_SPONSOR_CODE= "document.developmentProposalList[0].primeSponsorCode";
    private static final String NSF_CODE= "document.developmentProposalList[0].nsfCode";
    private static final String AGENCY_DIVISION_CODE= "document.developmentProposalList[0].agencyDivisionCode";
    private static final String PROGRAM_ANNOUNCEMENT_TITLE= "document.developmentProposalList[0].programAnnouncementTitle";
    private static final String NOTICE_OF_OPPORTUNITY_CODE= "document.developmentProposalList[0].noticeOfOpportunityCode";
    private static final String CFDA_NUMBER= "document.developmentProposalList[0].cfdaNumber";
    private static final String PROGRAM_ANNOUNCEMENT_NUMBER= "document.developmentProposalList[0].programAnnouncementNumber";
    private static final String SPONSOR_PROPOSAL_NUMBER= "document.developmentProposalList[0].sponsorProposalNumber";
    private static final String SUBCONTRACTS= "document.developmentProposalList[0].subcontracts";
    private static final String AGENCY_PROGRAM_CODE= "document.developmentProposalList[0].agencyProgramCode";

    /**
     * Modified for KRACOEUS-252 - CFDA number, Opportunity ID/programAnnouncementNumber formatting
     * @throws Exception
     */
    @Test
    public void testSponsorProgramInformationPanel() throws Exception {

        HtmlPage proposalPage = getProposalDevelopmentPage();

        //setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "000659", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "000661", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT, PRIME_SPONSOR_CODE);
        DateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = new java.util.Date();
        String currentDate= dateFormat.format(date);
        
       
        
        // sponsor program info fields
        setFieldValue(proposalPage, DEADLINE_DATE, currentDate);
        setFieldValue(proposalPage, DEADLINE_TYPE, "P"); //3
        setFieldValue(proposalPage, PRIME_SPONSOR_CODE, "000659");
        setFieldValue(proposalPage, NSF_CODE, "J.02"); //39
        setFieldValue(proposalPage, AGENCY_DIVISION_CODE, "123");
        setFieldValue(proposalPage, PROGRAM_ANNOUNCEMENT_TITLE, "we want to give you money");
        setFieldValue(proposalPage, NOTICE_OF_OPPORTUNITY_CODE, "2"); //8
        setFieldValue(proposalPage, CFDA_NUMBER, "12.456");
        setFieldValue(proposalPage, PROGRAM_ANNOUNCEMENT_NUMBER, "Program #122");
        setFieldValue(proposalPage, SPONSOR_PROPOSAL_NUMBER, "234567");
        setFieldValue(proposalPage, SUBCONTRACTS, "on");
        setFieldValue(proposalPage, AGENCY_PROGRAM_CODE, "456");

        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");

        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");

        assertDoesNotContain(savedProposalPage, ERRORS_FOUND_ON_PAGE);

        // make sure the document saved correctly
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(doc);

        verifySavedRequiredFields(doc, DEFAULT_PROPOSAL_ACTIVITY_TYPE, 
                DEFAULT_PROPOSAL_OWNED_BY_UNIT, 
                DEFAULT_DOCUMENT_DESCRIPTION, "000661", 
                DEFAULT_PROPOSAL_TITLE, "2007-08-14", "2007-08-21",
                DEFAULT_PROPOSAL_TYPE_CODE, "000659");

        // check sponsor program info fields
        assertEquals("P", doc.getDevelopmentProposal().getDeadlineType());
        assertEquals("000659", doc.getDevelopmentProposal().getPrimeSponsorCode());
        assertEquals("J.02", doc.getDevelopmentProposal().getNsfCode());
        assertEquals("123", doc.getDevelopmentProposal().getAgencyDivisionCode());
        assertEquals("we want to give you money", doc.getDevelopmentProposal().getProgramAnnouncementTitle());
        assertEquals("2", doc.getDevelopmentProposal().getNoticeOfOpportunityCode());
        assertEquals("12.456", doc.getDevelopmentProposal().getCfdaNumber());
        assertEquals("Program #122", doc.getDevelopmentProposal().getProgramAnnouncementNumber());
        assertEquals("234567", doc.getDevelopmentProposal().getSponsorProposalNumber());
        assertTrue("Subcontracts should be true", doc.getDevelopmentProposal().getSubcontracts());
        assertEquals("456", doc.getDevelopmentProposal().getAgencyProgramCode());

        // make sure the fields we set are displayed on the form after saving
        //assertContains(savedProposalPage, "Document was successfully saved.");
        assertTrue(savedProposalPage.asXml().contains("Document was successfully saved."));
        // sponsor program info fields
        assertEquals(currentDate, getFieldValue(savedProposalPage, DEADLINE_DATE));
        assertEquals("P", getFieldValue(savedProposalPage, DEADLINE_TYPE));
        assertEquals("000659", getFieldValue(savedProposalPage, PRIME_SPONSOR_CODE));
        assertEquals("J.02", getFieldValue(savedProposalPage, NSF_CODE));
        assertEquals("123", getFieldValue(savedProposalPage, AGENCY_DIVISION_CODE));
        assertEquals("we want to give you money", getFieldValue(savedProposalPage, PROGRAM_ANNOUNCEMENT_TITLE));
        assertEquals("2", getFieldValue(savedProposalPage, NOTICE_OF_OPPORTUNITY_CODE));
        assertEquals("12.456", getFieldValue(savedProposalPage, CFDA_NUMBER));
        assertEquals("Program #122", getFieldValue(savedProposalPage, PROGRAM_ANNOUNCEMENT_NUMBER));
        assertEquals("234567", getFieldValue(savedProposalPage, SPONSOR_PROPOSAL_NUMBER));
        assertEquals("on", getFieldValue(savedProposalPage, SUBCONTRACTS));
        assertEquals("456", getFieldValue(savedProposalPage, AGENCY_PROGRAM_CODE));

        // test label
        final HtmlDivision sponsorNameDiv = (HtmlDivision) savedProposalPage.getHtmlElementById("sponsorName.div");
        assertEquals("U.S. Department of State Supply & Transportation Division", sponsorNameDiv.asText());

        // test label
        final HtmlDivision primeSponsorNameDiv = (HtmlDivision) savedProposalPage.getHtmlElementById("primeSponsorName.div");
        assertTrue(primeSponsorNameDiv.asXml().contains("National Energy Technology Laboratory"));
    }

    /**
     * This method is a test for KRACOEUS-212
     *
     * 1) Initiate a new Proposal Development Document
     * 2) lookup a prime sponsor
     * 3) Click "Main Menu" tab, and then initiate a new Proposal Development Document
     * 4) The prime sponsor name from the previous form gets displayed on what should be a clean doc/form
     *
     * Depending on the number of times you go back and forth, sometimes the name is displayed and sometimes not.
     * @throws Exception
     */
    @Test
    public void testSaveThenNewDocClean() throws Exception {

        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", 
                DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", 
                DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, 
                DEFAULT_PROPOSAL_OWNED_BY_UNIT, "000659");

        setFieldValue(proposalPage, PRIME_SPONSOR_CODE, "000659");

        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");

        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");

        assertDoesNotContain(savedProposalPage, ERRORS_FOUND_ON_PAGE);

        // make sure the document saved correctly
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(doc);

        verifySavedRequiredFields(doc, DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_OWNED_BY_UNIT, 
                DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "2007-08-14", 
                "2007-08-21", DEFAULT_PROPOSAL_TYPE_CODE, "000659");

        // check sponsor program info fields
        assertEquals("000659", doc.getDevelopmentProposal().getPrimeSponsorCode());

        // make sure the fields we set are displayed on the form after saving
        //assertContains(savedProposalPage, "Document was successfully saved.");
        assertTrue(savedProposalPage.asXml().contains("Document was successfully saved."));

        // sponsor program info fields
        assertEquals("000659", getFieldValue(savedProposalPage, PRIME_SPONSOR_CODE));

        // test label
        final HtmlDivision primeSponsorNameDiv = (HtmlDivision) savedProposalPage.getHtmlElementById("primeSponsorName.div");
        //assertEquals("Kuwait Petroleum Corporation", primeSponsorNameDiv.asText());

        // click main menu link
        // initiate a new document
        setProposalDevelopmentPage(buildProposalDevelopmentPage());
        HtmlPage newProposalPage = getProposalDevelopmentPage();

        // make sure prime sponsor code is blank and not carried over from previous doc
        assertEquals("", getFieldValue(newProposalPage, PRIME_SPONSOR_CODE));

        // test label
        final HtmlDivision newPrimeSponsorNameDiv = (HtmlDivision) newProposalPage.getHtmlElementById("primeSponsorName.div");
        assertEquals("", newPrimeSponsorNameDiv.asText());
    }
}
