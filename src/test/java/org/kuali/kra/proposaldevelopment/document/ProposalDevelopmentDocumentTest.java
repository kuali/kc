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
package org.kuali.kra.proposaldevelopment.document;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.rice.KNSServiceLocator;

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

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "12345", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);
        checkDocumentFields(savedDocument, document.getDocumentNumber(), "ProposalDevelopmentDocumentTest test doc", "12345", "project title", "1", "1", "000001");
    }

    @Test public void testSaveWithoutProposalTypeCode() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "12345", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", null, "000001");

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

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "12345", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "2", "000001");

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

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "12345", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "2", "000001");
        document.setSponsorProposalNumber("234567");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);
        checkDocumentFields(savedDocument, document.getDocumentNumber(), "ProposalDevelopmentDocumentTest test doc", "12345", "project title", "1", "2", "000001");
        assertEquals("234567", savedDocument.getSponsorProposalNumber());
    }

    @Test public void testSaveWithSponsorProgramInfo() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "12345", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "2", "000001");

        // sponsor program info fields
        document.setDeadlineDate(new Date(System.currentTimeMillis()));
        document.setDeadlineType("1");
        document.setPrimeSponsorCode("001044");
        document.setCurrentAwardNumber("123456");
        document.setNsfCode("J.02");
        document.setAgencyDivisionCode("123");
        document.setProgramAnnouncementTitle("we want to give you money");
        document.setNoticeOfOpportunityCode("2");
        document.setCfdaNumber("123456");
        // opportunity id
        document.setProgramAnnouncementNumber("123478");
        document.setSponsorProposalNumber("234567");
        document.setContinuedFrom("98765432");
        document.setSubcontracts(true);
        document.setAgencyProgramCode("456");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);
        checkDocumentFields(savedDocument, document.getDocumentNumber(), "ProposalDevelopmentDocumentTest test doc", "12345", "project title", "1", "2", "000001");

        // check sponsor program info fields
        assertEquals("1", savedDocument.getDeadlineType());
        assertEquals("001044", savedDocument.getPrimeSponsorCode());
        assertEquals("123456", savedDocument.getCurrentAwardNumber());
        assertEquals("J.02", savedDocument.getNsfCode());
        assertEquals("123", savedDocument.getAgencyDivisionCode());
        assertEquals("we want to give you money", savedDocument.getProgramAnnouncementTitle());
        assertEquals("2", savedDocument.getNoticeOfOpportunityCode());
        assertEquals("123456", savedDocument.getCfdaNumber());
        assertEquals("123478", savedDocument.getProgramAnnouncementNumber());
        assertEquals("234567", savedDocument.getSponsorProposalNumber());
        assertEquals("98765432", savedDocument.getContinuedFrom());
        assertTrue("Subcontracts should be true", savedDocument.getSubcontracts());
        assertEquals("456", savedDocument.getAgencyProgramCode());

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
        document.getDocumentHeader().setFinancialDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnit(ownedByUnit);
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
        assertEquals(description, doc.getDocumentHeader().getFinancialDocumentDescription());
        assertEquals(sponsorCode, doc.getSponsorCode());
        assertEquals(title, doc.getTitle());
        // check dates
        assertEquals(activityTypeCode, doc.getActivityTypeCode());
        assertEquals(proposalTypeCode, doc.getProposalTypeCode());
        assertEquals(ownedByUnit, doc.getOwnedByUnit());
    }
}