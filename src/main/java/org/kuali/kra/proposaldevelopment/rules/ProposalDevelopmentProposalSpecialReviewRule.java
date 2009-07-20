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
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class contains the business rules that apply to ProposalSpecialReviews.  It also implements the 
 * AddProposalSpecialReviewRule to run those rules on newly added SpecialReviews.
 */
public class ProposalDevelopmentProposalSpecialReviewRule extends ResearchDocumentRuleBase implements AddProposalSpecialReviewRule {
    private static final String NEW_PROPOSAL_SPECIAL_REVIEW = "newPropSpecialReview";
    private static final String APPLICATION_DATE_FIELD = "applicationDate";
    private static final String APPLICATION_DATE_TITLE = "Application Date";
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String APPROVAL_DATE_TITLE = "Approval Date";
    private static final String APPROVAL_TYPE_CODE_FIELD = "approvalTypeCode";
    private static final String EXEMPT_NUMBER_TITLE = "Exempt Number";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String EXPIRATION_DATE_TITLE = "Expiration Date";
    private static final String NEW_EXEMPT_NUMBERS_FIELD = "newExemptNumbers";
    private static final String PROTOCOL_NUMBER_FIELD = "protocolNumber";
    private static final String PROTOCOL_NUMBER_TITLE = "Protocol Number";
    private static final String SPECIAL_REVIEW_CODE_FIELD = "specialReviewCode";

    private static final String APPROVED_STATUS = "2";
    private static final String NEW_PROPOSAL_SPECIAL_REVIEW_PATH = "newPropSpecialReview";
    private static final String VALID_SPECIAL_REVIEW_APPROVAL_REFERENCE_OBJECT = "validSpecialReviewApproval";

    /**
     * {@inheritDoc}
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule#processAddProposalSpecialReviewBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent)
     */
    public boolean processAddProposalSpecialReviewBusinessRules(AddProposalSpecialReviewEvent addProposalSpecialReviewEvent) {
        ProposalSpecialReview proposalSpecialReview = addProposalSpecialReviewEvent.getProposalSpecialReview();
        GlobalVariables.getErrorMap().addToErrorPath(NEW_PROPOSAL_SPECIAL_REVIEW_PATH);
        boolean retval = processValidSpecialReviewBusinessRules(proposalSpecialReview, NEW_EXEMPT_NUMBERS_FIELD);
        retval &= processProposalSpecialReviewBusinessRules(proposalSpecialReview);
        GlobalVariables.getErrorMap().removeFromErrorPath(NEW_PROPOSAL_SPECIAL_REVIEW_PATH);
        return retval;
    }

    /**
     * This method tests the "generic" rules that apply to SpecialReviews including required fields and date ordering.
     * It calls the reportError method to report the errors and assumes that the error path is set.
     * 
     * @param proposalSpecialReview the special review being checked
     * @return true if all rules have passed.
     */
    public boolean processProposalSpecialReviewBusinessRules(ProposalSpecialReview proposalSpecialReview) {
        boolean rulePassed = true;

        if (StringUtils.isBlank(proposalSpecialReview.getApprovalTypeCode())) {
            rulePassed = false;
            reportError(APPROVAL_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
        }
        if (StringUtils.isBlank(proposalSpecialReview.getSpecialReviewCode())) {
            rulePassed = false;
            reportError(SPECIAL_REVIEW_CODE_FIELD, KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
        }
        if (!APPROVED_STATUS.equals(proposalSpecialReview.getApprovalTypeCode()) && proposalSpecialReview.getApprovalDate() != null) {
            rulePassed = false;
            reportError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_NOT_APPROVED_SPECIALREVIEW, APPROVAL_DATE_TITLE);
        }
        if (proposalSpecialReview.getApplicationDate() != null && proposalSpecialReview.getApprovalDate() != null
                && proposalSpecialReview.getApprovalDate().before(proposalSpecialReview.getApplicationDate())) {
            rulePassed = false;
            reportError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_ORDERING, APPROVAL_DATE_TITLE,
                    APPLICATION_DATE_TITLE);
        }
        if (proposalSpecialReview.getApprovalDate() != null && proposalSpecialReview.getExpirationDate() != null
                && proposalSpecialReview.getExpirationDate().before(proposalSpecialReview.getApprovalDate())) {
            rulePassed = false;
            reportError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_ORDERING, EXPIRATION_DATE_TITLE,
                    APPROVAL_DATE_TITLE);
        }
        if (proposalSpecialReview.getApplicationDate() != null && proposalSpecialReview.getExpirationDate() != null
                && proposalSpecialReview.getExpirationDate().before(proposalSpecialReview.getApplicationDate())) {
            rulePassed = false;
            reportError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_ORDERING, EXPIRATION_DATE_TITLE,
                    APPLICATION_DATE_TITLE);
        }

        return rulePassed;
    }

    /**
     * This method tests the validity rules that apply to SpecialReviews.  These are set in the framework and can include
     * contingent requiredness of fields among other things.  It calls the reportError method to report the errors and 
     * assumes that the error path is set.
     * 
     * @param proposalSpecialReview the special review being checked
     * @param exemptFieldName the name of the form field containing selected exemptions
     * @return true if all rules have passed.
     */
    public boolean processValidSpecialReviewBusinessRules(ProposalSpecialReview proposalSpecialReview, String exemptFieldName) {
        boolean rulePassed = true;
        proposalSpecialReview.refreshReferenceObject(VALID_SPECIAL_REVIEW_APPROVAL_REFERENCE_OBJECT);
        if (StringUtils.isNotBlank(proposalSpecialReview.getApprovalTypeCode())
                && StringUtils.isNotBlank(proposalSpecialReview.getSpecialReviewCode())) {
            ValidSpecialReviewApproval validSpRevApproval = proposalSpecialReview.getValidSpecialReviewApproval();
            if (validSpRevApproval != null) {
                if (validSpRevApproval.isProtocolNumberFlag() && StringUtils.isBlank(proposalSpecialReview.getProtocolNumber())) {
                    rulePassed = false;
                    reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, PROTOCOL_NUMBER_TITLE,
                            getValidSpecialReviewApprovalErrorString(validSpRevApproval));
                }
                if (validSpRevApproval.isApplicationDateFlag() && proposalSpecialReview.getApplicationDate() == null) {
                    rulePassed = false;
                    reportError(APPLICATION_DATE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, PROTOCOL_NUMBER_TITLE,
                            getValidSpecialReviewApprovalErrorString(validSpRevApproval));
                }
                if (validSpRevApproval.isApprovalDateFlag() && proposalSpecialReview.getApprovalDate() == null) {
                    rulePassed = false;
                    reportError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, PROTOCOL_NUMBER_TITLE,
                            getValidSpecialReviewApprovalErrorString(validSpRevApproval));
                }
                if (validSpRevApproval.isExemptNumberFlag()
                        && (proposalSpecialReview.getProposalExemptNumbers() == null || proposalSpecialReview
                                .getProposalExemptNumbers().size() < 1)) {
                    rulePassed = false;
                    reportErrorWithoutFullErrorPath(exemptFieldName, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW,
                            EXEMPT_NUMBER_TITLE, getValidSpecialReviewApprovalErrorString(validSpRevApproval));
                }
                if (!validSpRevApproval.isExemptNumberFlag() && proposalSpecialReview.getProposalExemptNumbers() != null
                        && proposalSpecialReview.getProposalExemptNumbers().size() > 0) {
                    rulePassed = false;
                    reportErrorWithoutFullErrorPath(exemptFieldName, KeyConstants.ERROR_EXEMPT_NUMBER_SELECTED, getValidSpecialReviewApprovalErrorString(validSpRevApproval));
                }
            }
        }
        return rulePassed;
    }

    /*
     * This method composes the String used when reporting specifics of a ValidSpecialReview error.
     */
    private String getValidSpecialReviewApprovalErrorString(ValidSpecialReviewApproval validSpRevApproval) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(validSpRevApproval.getSpecialReview().getDescription());
        stringBuilder.append("/");
        stringBuilder.append(validSpRevApproval.getSpecialReviewApprovalType().getDescription());
        return stringBuilder.toString();
    }
    

}
