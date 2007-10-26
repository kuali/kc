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
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AbstractsRule;
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
public class ProposalDevelopmentDocumentRule extends ResearchDocumentRuleBase implements AddKeyPersonRule, AddNarrativeRule, DocumentAuditRule, AbstractsRule {

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;

        retval &= super.processCustomRouteDocumentBusinessRules(document);

        retval &= new ProposalDevelopmentKeyPersonsRule().processCustomRouteDocumentBusinessRules(document);

        return retval;
    }

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
        valid &= processOrganizationLocationBusinessRule(proposalDevelopmentDocument);
        valid &= processSpecialReviewBusinessRule(proposalDevelopmentDocument);
        valid &= processSponsorProgramInformationBusinessRule(proposalDevelopmentDocument);
        valid &= processPersonnelAttachmentBusinessRule(proposalDevelopmentDocument);
        valid &= processInstitutionalAttachmentBusinessRule(proposalDevelopmentDocument);

        GlobalVariables.getErrorMap().removeFromErrorPath("document");

        return valid;
    }

    /**
     * This method validates 'Proposal Special review'. It checks
     * validSpecialReviewApproval table, and if there is a match, then checks
     * protocalnumberflag, applicationdateflag, and approvaldataflag.
     *
     * @param proposalDevelopmentDocument : The proposalDevelopmentDocument that is being validated
     * @return valid Does the validation pass
     */
    private boolean processSpecialReviewBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        int i = 0;

        for (ProposalSpecialReview propSpecialReview : proposalDevelopmentDocument.getPropSpecialReviews()) {
            errorMap.addToErrorPath("propSpecialReviews[" + i + "]");
            propSpecialReview.refreshReferenceObject("validSpecialReviewApproval");
            if (StringUtils.isNotBlank(propSpecialReview.getApprovalTypeCode()) && StringUtils.isNotBlank(propSpecialReview.getSpecialReviewCode())) {
                ValidSpecialReviewApproval validSpRevApproval = propSpecialReview.getValidSpecialReviewApproval();
                if (validSpRevApproval != null) {
                    if (validSpRevApproval.isProtocolNumberFlag() && StringUtils.isNotBlank(propSpecialReview.getProtocolNumber())) {
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

    /**
     * This method validates Sponsor & Program Information related fields on
     * the Proposal Development Document.
     * @param proposalDevelopmentDocument document to validate
     * @return boolean whether the validation passed or not
     */
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
            errorMap.putError(Constants.SPONSOR_PROPOSAL_NUMBER_PROPERTY_KEY, KeyConstants.ERROR_REQUIRED_FOR_PROPOSALTYPE_NOTNEW, Constants.SPONSOR_PROPOSAL_NUMBER_LABEL);
        }

        return valid;
    }

    /**
     *
     * Validate organization/location rule. specifically, at least one location is required.
     * @param proposalDevelopmentDocument
     * @return
     */
    private boolean processOrganizationLocationBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        if (proposalDevelopmentDocument.getOrganizationId()!=null && (proposalDevelopmentDocument.getProposalLocations().size()==0 ||
                (proposalDevelopmentDocument.getProposalLocations().size()==1 && ((ProposalLocation)(proposalDevelopmentDocument.getProposalLocations().get(0))).getLocationSequenceNumber()==null))) {
            errorMap.addToErrorPath("newPropLocation");
            errorMap.putError("location", KeyConstants.ERROR_REQUIRED_FOR_PROPLOCATION);
            errorMap.removeFromErrorPath("newPropLocation");
            valid = false;
        }
        return valid;

    }


    /**
     * This method validates 'Personnel Attachment'. It checks the following :
     * If attachment type and description are not empty, then filename is a required field.
     *
     * @param proposalDevelopmentDocument : The proposalDevelopmentDocument that is being validated
     * @return valid Does the validation pass
     */
    private boolean processPersonnelAttachmentBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        int i = 0;

        for (ProposalPersonBiography propPersonBio : proposalDevelopmentDocument.getPropPersonBios()) {
            errorMap.addToErrorPath("propPersonBios[" + i + "]");
            propPersonBio.refresh();
            if (StringUtils.isNotBlank(propPersonBio.getDocumentTypeCode()) && StringUtils.isNotBlank(propPersonBio.getPersonId())) {
                    if (StringUtils.isBlank(propPersonBio.getFileName())) {
                        valid = false;
                        errorMap.putError("fileName", KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME, "File Name");
                    }


            }
            errorMap.removeFromErrorPath("propPersonBios[" + i++ + "]");
        }
        return valid;
    }

    /**
     * This method validates 'Institute Attachment'. It checks the following :
     * If attachment type and description are not empty, then filename is a required field.
     *
     * @param proposalDevelopmentDocument : The proposalDevelopmentDocument that is being validated
     * @return valid Does the validation pass
     */
    private boolean processInstitutionalAttachmentBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        int i = 0;

        // TODO : this will combine errors with proposal attachments panel
        for (Narrative institute : proposalDevelopmentDocument.getNarratives()) {
            errorMap.addToErrorPath("institutes[" + i + "]");
            institute.refresh();
            if (StringUtils.isNotBlank(institute.getNarrativeTypeCode()) && StringUtils.isNotBlank(institute.getModuleTitle())) {
                    if (StringUtils.isBlank(institute.getFileName())) {
                        valid = false;
                        errorMap.putError("fileName", KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME, "File Name");
                    }


            }
            errorMap.removeFromErrorPath("institutes[" + i++ + "]");
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

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.AbstractsRule#processAddAbstractBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalAbstract)
     */
    public boolean processAddAbstractBusinessRules(ProposalDevelopmentDocument document, ProposalAbstract proposalAbstract) {
        return new ProposalDevelopmentAbstractsRule().processAddAbstractBusinessRules(document, proposalAbstract);
	}

}
