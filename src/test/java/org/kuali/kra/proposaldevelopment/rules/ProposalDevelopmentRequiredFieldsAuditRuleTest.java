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
package org.kuali.kra.proposaldevelopment.rules;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.impl.ProposalDevelopmentServiceImpl;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class tests the ProposalDevelopmentSponsorProgramInformationAuditRule class
 */
public class ProposalDevelopmentRequiredFieldsAuditRuleTest extends KcUnitTestBase {

    private DocumentService documentService = null;
    private ParameterService parameterService = null;
    private ProposalDevelopmentProposalRequiredFieldsAuditRule auditRule = null;
    
    private String proposalTypeCodeRenewal;
    private String proposalTypeCodeRevision;
    private String proposalTypeCodeContinuation;
    private String proposalTypeCodeResubmission;
    private String proposalTypeCodeNew;
    private String changeCorrectedTypeCode;
    
    private ProposalDevelopmentDocument pdDoc = null;
    private DevelopmentProposal proposal = null;
    
    Date tomorrow;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        KNSGlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KRADServiceLocatorWeb.getDocumentService();
        parameterService = CoreFrameworkServiceLocator.getParameterService();
        auditRule = new ProposalDevelopmentProposalRequiredFieldsAuditRule();
        
        proposalTypeCodeRenewal = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL);
        proposalTypeCodeRevision = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION);
        proposalTypeCodeContinuation = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION);
        proposalTypeCodeResubmission = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RESUBMISSION);
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
        KNSGlobalVariables.setAuditErrorMap(null);
        documentService = null;
        auditRule = null;
        super.tearDown();
    }
    
    @Test public void testRequireSponsorIdWhenNew() throws Exception {
        auditRule.setProposalDevelopmentService(new ProposalDevelopmentServiceMock());
        proposal.setProposalTypeCode(proposalTypeCodeNew);
        proposal.getS2sOpportunity().setS2sSubmissionTypeCode(changeCorrectedTypeCode);
        proposal.setSponsorProposalNumber(null);
        validateAuditRule(pdDoc, Constants.ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP, "requiredFieldsAuditErrors", true);
        KNSGlobalVariables.getAuditErrorMap().clear();
        proposal.setSponsorProposalNumber("AA123456");
        validateAuditRule(pdDoc, Constants.ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP, "requiredFieldsAuditErrors", false);
        KNSGlobalVariables.getAuditErrorMap().clear();
        proposal.setSponsorProposalNumber(null);
        proposal.setContinuedFrom("1");
        //will report error as the instprop found won't be linked to a propdev
        validateAuditRule(pdDoc, Constants.ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP, "requiredFieldsAuditErrors", true);
        auditRule.setProposalDevelopmentService(null);
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
        assertEquals(expectError?1:0, KNSGlobalVariables.getAuditErrorMap().size());
        AuditCluster auditCluster = (AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(auditKey);

        if (expectError) {
            assertEquals("Required Fields for Saving Document ", auditCluster.getLabel());
            List auditErrors = auditCluster.getAuditErrorList();
            assertEquals(1, auditErrors.size());
            AuditError auditError = (AuditError) auditErrors.get(0);
    
            assertEquals(fieldKey, auditError.getErrorKey());
            assertEquals(messageKey, auditError.getMessageKey());
            assertEquals("proposal.RequiredFieldsforSavingDocument", auditError.getLink());
    
            assertEquals(Constants.AUDIT_ERRORS, auditCluster.getCategory());
        }
    }
    
    class ProposalDevelopmentServiceMock extends ProposalDevelopmentServiceImpl {
        public InstitutionalProposal getProposalContinuedFromVersion(ProposalDevelopmentDocument doc) {
            if (StringUtils.isNotBlank(doc.getDevelopmentProposal().getContinuedFrom())) {
                InstitutionalProposal instProposal = new InstitutionalProposal();
                instProposal.setProposalNumber(doc.getDevelopmentProposal().getContinuedFrom());
                instProposal.setSponsorProposalNumber("IP123456");
                return instProposal;
            } else {
                return null;
            }
        }
    }
}
