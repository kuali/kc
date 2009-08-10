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
package org.kuali.kra.proposaldevelopment.document;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.exception.ValidationException;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class tests the ProposalDevelopmentDocument
 */
public class ProposalDevelopmentDocumentTest extends KraTestBase {

    private DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        documentService = null;
        super.tearDown();
    }

    @Test public void testSave() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);
        checkDocumentFields(savedDocument, document.getDocumentNumber(), "ProposalDevelopmentDocumentTest test doc", "005770", "project title", "1", "1", "000001");
    }

    @Test public void testSaveWithoutProposalTypeCode() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", null, "000001");

        boolean caughtException = false;

        try {
            documentService.saveDocument(document);
        } catch (ValidationException e) {
            assertEquals(e.toString(), "org.kuali.core.exceptions.ValidationException: Unreported errors occured during business rule evaluation (rule developer needs to put meaningful error messages into global ErrorMap)");
            caughtException = true;
        }

        assertTrue("Should have thrown a ValidationException", caughtException);
    }

    @Test public void testSaveWithError() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "2", "000001");

        boolean caughtException = false;

        try {
            documentService.saveDocument(document);
        } catch (ValidationException e) {
            assertEquals(e.toString(), "org.kuali.core.exceptions.ValidationException: business rule evaluation failed");
            caughtException = true;
        }

        assertTrue("Should have thrown a ValidationException", caughtException);
    }

    @Test public void testSaveValidNonNew() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "2", "000001");
        document.getDevelopmentProposal().setContinuedFrom("234567");
        document.getDevelopmentProposal().setSponsorProposalNumber("12345");
        
        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);
        checkDocumentFields(savedDocument, document.getDocumentNumber(), "ProposalDevelopmentDocumentTest test doc", "005770", "project title", "1", "2", "000001");
        assertEquals("234567", savedDocument.getDevelopmentProposal().getContinuedFrom());
    }

    @Test public void testSaveWithSponsorProgramInfo() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "2", "000001");

        // sponsor program info fields
        document.getDevelopmentProposal().setDeadlineDate(new Date(System.currentTimeMillis()));
        document.getDevelopmentProposal().setDeadlineType("1");
        document.getDevelopmentProposal().setPrimeSponsorCode("001044");
        document.getDevelopmentProposal().setCurrentAwardNumber("123456");
        document.getDevelopmentProposal().setNsfCode("J.02");
        document.getDevelopmentProposal().setAgencyDivisionCode("123");
        document.getDevelopmentProposal().setProgramAnnouncementTitle("we want to give you money");
        document.getDevelopmentProposal().setNoticeOfOpportunityCode("2");
        document.getDevelopmentProposal().setCfdaNumber("12.345a");
        // opportunity id
        document.getDevelopmentProposal().setProgramAnnouncementNumber("123478");
        document.getDevelopmentProposal().setSponsorProposalNumber("234567");
        document.getDevelopmentProposal().setContinuedFrom("98765432");
        document.getDevelopmentProposal().setSubcontracts(true);
        document.getDevelopmentProposal().setAgencyProgramCode("456");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);
        checkDocumentFields(savedDocument, document.getDocumentNumber(), "ProposalDevelopmentDocumentTest test doc", "005770", "project title", "1", "2", "000001");

        // check sponsor program info fields
        assertEquals("1", savedDocument.getDevelopmentProposal().getDeadlineType());
        assertEquals("001044", savedDocument.getDevelopmentProposal().getPrimeSponsorCode());
        assertEquals("123456", savedDocument.getDevelopmentProposal().getCurrentAwardNumber());
        assertEquals("J.02", savedDocument.getDevelopmentProposal().getNsfCode());
        assertEquals("123", savedDocument.getDevelopmentProposal().getAgencyDivisionCode());
        assertEquals("we want to give you money", savedDocument.getDevelopmentProposal().getProgramAnnouncementTitle());
        assertEquals("2", savedDocument.getDevelopmentProposal().getNoticeOfOpportunityCode());
        assertEquals("12.345a", savedDocument.getDevelopmentProposal().getCfdaNumber());
        assertEquals("123478", savedDocument.getDevelopmentProposal().getProgramAnnouncementNumber());
        assertEquals("234567", savedDocument.getDevelopmentProposal().getSponsorProposalNumber());
        assertEquals("98765432", savedDocument.getDevelopmentProposal().getContinuedFrom());
        assertTrue("Subcontracts should be true", savedDocument.getDevelopmentProposal().getSubcontracts());
        assertEquals("456", savedDocument.getDevelopmentProposal().getAgencyProgramCode());

    }

    /**
     * This method sets the base/required document fields
     * @param document ProposalDevelopmentDocument to set fields for
     * @param title String title to set
     * @param requestedStartDateInitial Date start date to set
     * @param requestedEndDateInitila Date end date to set
     * @param activityTypeCode String activity type code to set
     * @param proposalTypeCode String proposal type code to set
     * @param ownedByUnit String owned-by unit to set
     */
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
    }

    /**
     * This method checks document fields against expected values
     * @param doc ProposalDevelopmentDocument doc to check
     * @param documentNumber String document number to check
     * @param description String description to check
     * @param sponsorCode String sponsor code to check
     * @param title String title to check
     * @param activityTypeCode String activity type code to check
     * @param proposalTypeCode String proposal type code to check
     * @param ownedByUnit String owned-by unit to check
     */
    private void checkDocumentFields(ProposalDevelopmentDocument doc, String documentNumber, String description, String sponsorCode, String title, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        assertEquals(documentNumber, doc.getDocumentNumber());
        assertEquals(description, doc.getDocumentHeader().getDocumentDescription());
        assertEquals(sponsorCode, doc.getDevelopmentProposal().getSponsorCode());
        assertEquals(title, doc.getDevelopmentProposal().getTitle());
        // check dates
        assertEquals(activityTypeCode, doc.getDevelopmentProposal().getActivityTypeCode());
        assertEquals(proposalTypeCode, doc.getDevelopmentProposal().getProposalTypeCode());
        assertEquals(ownedByUnit, doc.getDevelopmentProposal().getOwnedByUnitNumber());
    }
}