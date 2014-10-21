/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.SubmissionInfoServiceImpl;
import org.kuali.coeus.propdev.impl.sponsor.ProposalDevelopmentSponsorProgramInformationAuditRule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.*;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;


/**
 * This class tests the ProposalDevelopmentSponsorProgramInformationAuditRule class
 */
public class ProposalDevelopmentSponsorProgramInformationAuditRuleTest extends KcIntegrationTestBase {

    private DocumentService documentService = null;
    private ParameterService parameterService = null;
    private ProposalDevelopmentSponsorProgramInformationAuditRule auditRule = null;
    
    private String proposalTypeCodeRenewal;
    private String proposalTypeCodeResubmission;

    Date tomorrow;
    
    private class SubmissionInfoServiceMock extends SubmissionInfoServiceImpl {

        @Override
        public String getProposalCurrentAwardSponsorAwardNumber(String currentAwardNumber) {
            if (StringUtils.isNotBlank(currentAwardNumber)) {
                Award award = new Award();
                award.setAwardNumber(currentAwardNumber);
                award.setSponsorAwardNumber("AW123456");
                return award.getSponsorAwardNumber();
            } else {
               return null;
            }
        }
        @Override
        public String getProposalContinuedFromVersionSponsorProposalNumber(String s) {
            if (StringUtils.isNotBlank(s)) {
                InstitutionalProposal instProposal = new InstitutionalProposal();
                instProposal.setProposalNumber(s);
                instProposal.setSponsorProposalNumber("IP123456");
                return instProposal.getSponsorProposalNumber();
            } else {
                return null;
            }
        }
    }    

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KRADServiceLocatorWeb.getDocumentService();
        parameterService = CoreFrameworkServiceLocator.getParameterService();
        auditRule = new ProposalDevelopmentSponsorProgramInformationAuditRule();
        auditRule.setParameterService(parameterService);
        
        proposalTypeCodeRenewal = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL);
        proposalTypeCodeResubmission = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RESUBMISSION);

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        tomorrow = new Date(calendar.getTimeInMillis());           
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setAuditErrorMap(null);
        documentService = null;
        auditRule = null;
    }
    
    private ProposalDevelopmentDocument getNewProposalDevelopmentDocument() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        document.getDevelopmentProposal().setPrimeSponsorCode("000100");
        return document;
    }

    @Test public void testValidDate() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();

        document.getDevelopmentProposal().setDeadlineDate(tomorrow);;
        assertTrue("Audit Rule shouldn't produce any audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(0, GlobalVariables.getAuditErrorMap().size());
    }

    @Test public void testEmptyDate() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();

        validateAuditRule(document, KeyConstants.WARNING_EMPTY_DEADLINE_DATE);
    }

    @Test public void testPastDate() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();

        // Create a date in the past - yesterday
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, -1);
        Date deadlineDate = new Date(calendar.getTimeInMillis());

        document.getDevelopmentProposal().setDeadlineDate(deadlineDate);
        validateAuditRule(document, KeyConstants.WARNING_PAST_DEADLINE_DATE);
    }
    
    @Test public void testRequireSponsorIdWhenRenewal() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        proposal.setDeadlineDate(tomorrow);
        proposal.setProposalTypeCode(proposalTypeCodeRenewal);
        proposal.setS2sOpportunity(new S2sOpportunity());
        proposal.getS2sOpportunity().setOpportunityId("12345");
        proposal.getS2sOpportunity().setCfdaNumber("00.000");
        proposal.setCfdaNumber("00.000");
        proposal.setProgramAnnouncementTitle("Test Title");
        
        auditRule.setSubmissionInfoService(new SubmissionInfoServiceMock());
        validateGGAuditRules(document,SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD, true);
        GlobalVariables.getAuditErrorMap().clear();
        proposal.setSponsorProposalNumber("AA123456");
        validateGGAuditRules(document, SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD, false);
        GlobalVariables.getAuditErrorMap().clear();
        auditRule.setSubmissionInfoService(null);
        auditRule.setParameterService(null);
    }

    @Test public void testRequireSponsorIdWhenResubmission() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        proposal.setDeadlineDate(tomorrow);
        proposal.setProposalTypeCode(proposalTypeCodeResubmission);
        proposal.setS2sOpportunity(new S2sOpportunity());
        proposal.getS2sOpportunity().setOpportunityId("12345");
        proposal.getS2sOpportunity().setCfdaNumber("00.000");
        proposal.setCfdaNumber("00.000");
        proposal.setProgramAnnouncementTitle("Test Title");

        auditRule.setSubmissionInfoService(new SubmissionInfoServiceMock());
        validateGGAuditRules(document, SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD_FOR_RESUBMIT, true);
        GlobalVariables.getAuditErrorMap().clear();
        proposal.setSponsorProposalNumber("AA123456");
        validateGGAuditRules(document, SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD_FOR_RESUBMIT, false);
        GlobalVariables.getAuditErrorMap().clear();
        proposal.setSponsorProposalNumber(null);
        proposal.setCurrentAwardNumber("000001-0001");
        validateGGAuditRules(document, SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD_FOR_RESUBMIT, true);
        GlobalVariables.getAuditErrorMap().clear();
        proposal.setContinuedFrom("1");
        validateGGAuditRules(document, SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD_FOR_RESUBMIT, false);
        GlobalVariables.getAuditErrorMap().clear();
        auditRule.setSubmissionInfoService(null);
        auditRule.setParameterService(null);
    }

    /**
     * This method validates the audit rule processing
     * @param document document to check
     * @param messageKey messageKey for the AuditError
     */
    private void validateAuditRule(ProposalDevelopmentDocument document, String messageKey) {
        assertFalse("Audit Rule should produce a Warning audit error", auditRule.processRunAuditBusinessRules(document));
        assertEquals(1, GlobalVariables.getAuditErrorMap().size());
        AuditCluster auditCluster = GlobalVariables.getAuditErrorMap().get(SPONSOR_PROGRAM_INFO_PAGE_NAME+AUDIT_WARNINGS);

        assertEquals(SPONSOR_PROGRAM_INFO_PAGE_NAME, auditCluster.getLabel());
        List auditErrors = auditCluster.getAuditErrorList();
        assertEquals(1, auditErrors.size());
        AuditError auditError = (AuditError) auditErrors.get(0);

        assertEquals("document.developmentProposal.deadlineDate", auditError.getErrorKey());
        assertEquals(messageKey, auditError.getMessageKey());
        assertEquals(SPONSOR_PROGRAM_INFO_PAGE_ID, auditError.getLink());

        assertEquals("Warnings", auditCluster.getCategory());
    }
    
    private void validateGGAuditRules(ProposalDevelopmentDocument document, String fieldKey, String messageKey, boolean expectError) {
        assertTrue("Audit Rule did not produce expected results", auditRule.processRunAuditBusinessRules(document) ^ expectError);
        assertEquals(expectError?1:0, GlobalVariables.getAuditErrorMap().size());
        AuditCluster auditCluster = GlobalVariables.getAuditErrorMap().get(SPONSOR_PROGRAM_INFO_PAGE_NAME+AUDIT_ERRORS);

        if (expectError) {
            assertEquals(SPONSOR_PROGRAM_INFO_PAGE_NAME, auditCluster.getLabel());
            List auditErrors = auditCluster.getAuditErrorList();
            assertEquals(1, auditErrors.size());
            AuditError auditError = (AuditError) auditErrors.get(0);
    
            assertEquals(fieldKey, auditError.getErrorKey());
            assertEquals(messageKey, auditError.getMessageKey());
            assertEquals(SPONSOR_PROGRAM_INFO_PAGE_ID, auditError.getLink());
    
            assertEquals(AUDIT_ERRORS, auditCluster.getCategory());
        }
    }
    

}
