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

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.basic.ProposalDevelopmentProposalRequiredFieldsAuditRule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.SubmissionInfoServiceImpl;
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
public class ProposalDevelopmentRequiredFieldsAuditRuleTest extends KcIntegrationTestBase {

    private DocumentService documentService = null;
    private ParameterService parameterService = null;
    private ProposalDevelopmentProposalRequiredFieldsAuditRule auditRule = null;

    private String proposalTypeCodeNew;
    private String changeCorrectedTypeCode;
    
    private ProposalDevelopmentDocument pdDoc = null;
    private DevelopmentProposal proposal = null;
    
    Date tomorrow;


    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KRADServiceLocatorWeb.getDocumentService();
        parameterService = CoreFrameworkServiceLocator.getParameterService();
        auditRule = new ProposalDevelopmentProposalRequiredFieldsAuditRule();
        
        proposalTypeCodeNew = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW);
        
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        tomorrow = new Date(calendar.getTimeInMillis());
        
        pdDoc = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        proposal = pdDoc.getDevelopmentProposal();  
        proposal.setDeadlineDate(tomorrow);
        proposal.setProposalTypeCode(proposalTypeCodeNew);
        proposal.setS2sOpportunity(new S2sOpportunity());
        changeCorrectedTypeCode = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, "s2s.submissiontype.changedCorrected");
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setAuditErrorMap(null);
        documentService = null;
        auditRule = null;
    }
    
    @Test public void testRequireSponsorIdWhenNew() throws Exception {
        auditRule.setSubmissionInfoService(new SubmissionInfoServiceMock());
        proposal.setProposalTypeCode(proposalTypeCodeNew);
        proposal.getS2sOpportunity().setS2sSubmissionTypeCode(changeCorrectedTypeCode);
        proposal.setSponsorProposalNumber(null);
        validateAuditRule(pdDoc, ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP, "requiredFieldsAuditErrors", true);
        GlobalVariables.getAuditErrorMap().clear();
        proposal.setSponsorProposalNumber("AA123456");
        validateAuditRule(pdDoc, ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP, "requiredFieldsAuditErrors", false);
        GlobalVariables.getAuditErrorMap().clear();
        proposal.setSponsorProposalNumber(null);
        proposal.setContinuedFrom("1");
        //will report error as the instprop found won't be linked to a propdev
        validateAuditRule(pdDoc, ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP, "requiredFieldsAuditErrors", true);
        auditRule.setSubmissionInfoService(null);
        //not sure how to easily test the last use case where an IP must be linked to
        //a propdev with a Grants.Gov submission with a gg tracking id.
    }

    /**
     * This method validates the audit rule processing
     * @param document document to check
     * @param messageKey messageKey for the AuditError
     */
    private void validateAuditRule(ProposalDevelopmentDocument document, String fieldKey, String messageKey, String auditKey, boolean expectError) {
        assertTrue("Audit Rule did not produce expected results", auditRule.processRunAuditBusinessRules(document) ^ expectError);
        assertEquals(expectError?1:0, GlobalVariables.getAuditErrorMap().size());
        AuditCluster auditCluster = GlobalVariables.getAuditErrorMap().get(DETAILS_PAGE_NAME+".");

        if (expectError) {
            assertEquals(DETAILS_PAGE_NAME+".", auditCluster.getLabel());
            List auditErrors = auditCluster.getAuditErrorList();
            assertEquals(1, auditErrors.size());
            AuditError auditError = (AuditError) auditErrors.get(0);
    
            assertEquals(fieldKey, auditError.getErrorKey());
            assertEquals(messageKey, auditError.getMessageKey());
            assertEquals(DETAILS_PAGE_ID, auditError.getLink());
    
            assertEquals(Constants.AUDIT_ERRORS, auditCluster.getCategory());
        }
    }
    
    class SubmissionInfoServiceMock extends SubmissionInfoServiceImpl {
        @Override
        public Long getProposalContinuedFromVersionProposalId(String con) {
            if (StringUtils.isNotBlank(con)) {
                InstitutionalProposal instProposal = new InstitutionalProposal();
                instProposal.setProposalNumber(con);
                instProposal.setSponsorProposalNumber("IP123456");
                return instProposal.getProposalId();
            } else {
                return null;
            }
        }

        @Override
        public String getGgTrackingIdFromProposal(Long proposalId) {
            return " ";
        }
    }
}
