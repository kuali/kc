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
package org.kuali.kra.proposaldevelopment.rules;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

/**
 * This class tests the ProposalDevelopmentSponsorProgramInformationAuditRule class
 */
public class ProposalDevelopmentSponsorProgramInformationAuditRuleTest extends KraTestBase {

    private DocumentService documentService = null;
    private ProposalDevelopmentSponsorProgramInformationAuditRule auditRule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KNSServiceLocator.getDocumentService();
        auditRule = new ProposalDevelopmentSponsorProgramInformationAuditRule();
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setAuditErrorMap(null);
        documentService = null;
        auditRule = null;
        super.tearDown();
    }

    @Test public void testValidDate() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        // Create a date in the past - tomorrow
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        Date deadlineDate = new Date(calendar.getTimeInMillis());

        document.setDeadlineDate(deadlineDate);
        assertTrue("Audit Rule shouldn't produce any audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(0, GlobalVariables.getAuditErrorMap().size());
    }

    @Test public void testEmptyDate() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        validateAuditRule(document, KeyConstants.WARNING_EMPTY_DEADLINE_DATE);
    }

    @Test public void testPastDate() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        // Create a date in the past - yesterday
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, -1);
        Date deadlineDate = new Date(calendar.getTimeInMillis());

        document.setDeadlineDate(deadlineDate);
        validateAuditRule(document, KeyConstants.WARNING_PAST_DEADLINE_DATE);
    }

    /**
     * This method validates the audit rule processing
     * @param document document to check
     * @param messageKey messageKey for the AuditError
     */
    private void validateAuditRule(ProposalDevelopmentDocument document, String messageKey) {
        assertFalse("Audit Rule should produce a Warning audit error", auditRule.processRunAuditBusinessRules(document));
        assertEquals(1, GlobalVariables.getAuditErrorMap().size());
        AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get("sponsorProgramInformationAuditWarnings");

        assertEquals("Sponsor & Program Information", auditCluster.getLabel());
        List auditErrors = auditCluster.getAuditErrorList();
        assertEquals(1, auditErrors.size());
        AuditError auditError = (AuditError) auditErrors.get(0);

        assertEquals("document.deadlineDate", auditError.getErrorKey());
        assertEquals(messageKey, auditError.getMessageKey());
        assertEquals("proposal.SponsorProgramInformation", auditError.getLink());

        assertEquals("Warnings", auditCluster.getCategory());
    }

}