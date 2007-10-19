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

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.ValidSpRevApproval;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.PropLocation;
import org.kuali.kra.proposaldevelopment.bo.PropSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.KNSServiceLocator;

/**
 * This class...
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentRule extends ResearchDocumentRuleBase implements AddKeyPersonRule, AddNarrativeRule, DocumentAuditRule {

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof ProposalDevelopmentDocument)) {
            return false;
        }

        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;

        GlobalVariables.getErrorMap().addToErrorPath("document");

        // changing this to '0' so it doesn't validate reference objects within a list
        KNSServiceLocator.getDictionaryValidationService().validateDocument(proposalDevelopmentDocument);
        if (proposalDevelopmentDocument.getOrganizationId()!=null && (proposalDevelopmentDocument.getPropLocations().size()==0 ||
                (proposalDevelopmentDocument.getPropLocations().size()==1 && ((PropLocation)(proposalDevelopmentDocument.getPropLocations().get(0))).getLocationSequenceNumber()==null))) {
            // should have one just added by form
            // this is a business rule, so put it here.  If needed we can always create a new prop location if the last one is deleted in deletelocation action ?
            GlobalVariables.getErrorMap().addToErrorPath("newPropLocation");
            GlobalVariables.getErrorMap().putError("location", KeyConstants.ERROR_REQUIRED_FOR_PROPLOCATION);
            GlobalVariables.getErrorMap().removeFromErrorPath("newPropLocation");
        }
        valid &= processSpecialReviewBusinessRule(proposalDevelopmentDocument);
        valid &= processSponsorProgramInformationBusinessRule(proposalDevelopmentDocument);

        GlobalVariables.getErrorMap().removeFromErrorPath("document");

        return valid;
    }

    private boolean processSpecialReviewBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        int i = 0;

        for (PropSpecialReview propSpecialReview : proposalDevelopmentDocument.getPropSpecialReviews()) {
            errorMap.addToErrorPath("propSpecialReviews[" + i + "]");
            propSpecialReview.refresh();
            propSpecialReview.refreshReferenceObject("validSpRevApproval");
            if (propSpecialReview.getApprovalTypeCode() != null && propSpecialReview.getSpecialReviewCode() != null) {
                ValidSpRevApproval validSpRevApproval = propSpecialReview.getValidSpRevApproval();
                if (validSpRevApproval != null) {
                    if (validSpRevApproval.isProtocolNumberFlag() && propSpecialReview.getProtocolNumber() == null) {
                        valid = false;
                        errorMap.putError("protocolNumber", KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, "Protocol Number",
                                validSpRevApproval.getSpecialReview().getDescription() + "/"
                                        + validSpRevApproval.getSpecialReviewApprovalType().getDescription());
                    }
                    if (validSpRevApproval.isApplicationDateFlag() && propSpecialReview.getApplicationDate() == null) {
                        valid = false;
                        errorMap.putError("applicationDate", KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW,
                                "Protocol Number", validSpRevApproval.getSpecialReview().getDescription() + "/"
                                        + validSpRevApproval.getSpecialReviewApprovalType().getDescription());
                    }
                    if (validSpRevApproval.isApprovalDateFlag() && propSpecialReview.getApprovalDate() == null) {
                        valid = false;
                        errorMap.putError("approvalDate", KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, "Protocol Number",
                                validSpRevApproval.getSpecialReview().getDescription() + "/"
                                        + validSpRevApproval.getSpecialReviewApprovalType().getDescription());
                    }

                }

            }
            errorMap.removeFromErrorPath("propSpecialReviews[" + i++ + "]");
        }
        return valid;
    }

    private boolean processSponsorProgramInformationBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        // "Sponsor Proposal Id" must be entered if the proposal type is not new (i.e. resubmission)
        // or if the proposal type is new and the grants.gov submission type is "changed/corrected".
        String proposalTypeCodeNew = getKualiConfigurationService().getParameter(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW).getParameterValue();
        if (StringUtils.isNotEmpty(proposalDevelopmentDocument.getProposalTypeCode()) &&
                !proposalDevelopmentDocument.getProposalTypeCode().equals(proposalTypeCodeNew) &&
                StringUtils.isEmpty(proposalDevelopmentDocument.getSponsorProposalNumber())) {
            valid = false;
            errorMap.putError("sponsorProgramNumber", KeyConstants.ERROR_REQUIRED_FOR_PROPOSALTYPE_NOTNEW, "Sponsor Program Number");
        }

        return valid;
    }

    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        return new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person);
    }

    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent addNarrativeEvent) {
        return new ProposalDevelopmentNarrativeRule().processAddNarrativeBusinessRules(addNarrativeEvent);    }

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        return new ProposalDevelopmentSponsorProgramInformationAuditRule().processRunAuditBusinessRules(document);
    }

}
