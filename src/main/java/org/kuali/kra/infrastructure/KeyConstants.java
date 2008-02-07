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
package org.kuali.kra.infrastructure;

public class KeyConstants {
    public static final String ERROR_REQUIRED_FOR_APPROVED_SPECIALREVIEW = "error.required.for.approved.specialReview";
    public static final String ERROR_NOT_APPROVED_SPECIALREVIEW = "error.not.approved.specialReview";
    public static final String ERROR_REQUIRED_SELECT_APPROVAL_STATUS = "error.required.select.approval.status";
    public static final String ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE = "error.required.select.special.review.code";

    public static final String ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW = "error.required.for.valid.specialReview";
    public static final String ERROR_REQUIRED_FOR_PROPLOCATION = "error.required.for.propLocation";
    public static final String ERROR_REQUIRED_FOR_PROPLOCATION_NAME = "error.required.for.locationName";
    public static final String ERROR_REQUIRED_FOR_PROPOSALTYPE_NOTNEW = "error.required.for.proposalType.notNew";
    public static final String ERROR_REQUIRED_PROPOSAL_SPONSOR_ID = "error.required.proposalSponsorId";
    public static final String WARNING_EMPTY_DEADLINE_DATE = "warning.empty.deadline.date";
    public static final String WARNING_PAST_DEADLINE_DATE = "warning.past.deadline.date";
    public static final String ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW = "error.approvalDate.before.applicationDate.for.valid.specialReview";
    public static final String ERROR_START_DATE_AFTER_END_DATE = "error.start.date.after.end.date";

    // Proposal Types System Parameter Names
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW = "proposaldevelopment.proposaltype.new";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL = "proposaldevelopment.proposaltype.renewal";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION = "proposaldevelopment.proposaltype.revision";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_COMPETING_CONT = "proposaldevelopment.proposaltype.competing.continuation";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_NON_COMPETING_CONT = "proposaldevelopment.proposaltype.noncompeting.continuation";
    
    // Key Personnel Mojo
    public static final String ERROR_INVESTIGATOR_UNITS_UPBOUND = "error.investigatorUnits.upbound";
    public static final String ERROR_INVESTIGATOR_UPBOUND = "error.principalInvestigators.upbound";
    public static final String ERROR_INVESTIGATOR_LOWBOUND = "error.principalInvestigators.lowbound";
    public static final String ERROR_MISSING_PERSON_ROLE = "error.missingPersonRole";
    public static final String ERROR_PROPOSAL_PERSON_EXISTS = "error.proposalPersonExists";
    public static final String ERROR_TOTAL_CREDIT_SPLIT_UPBOUND = "error.totalCreditSplit.upbound";
    public static final String ERROR_CREDIT_SPLIT_LOWBOUND = "error.creditSplit.lowbound";
    public static final String ERROR_CREDIT_SPLIT_UPBOUND = "error.creditSplit.upbound";
    public static final String ERROR_DELETE_LEAD_UNIT = "error.deleteLeadUnit";
    public static final String ERROR_ADD_EXISTING_UNIT = "error.addExistingUnit";
    
    public static final String ERROR_REQUIRED_FOR_FILE_NAME="error.required.for.fileName";
    public static final String ERROR_ABSTRACT_TYPE_NOT_SELECTED = "error.abstractType.notselected";
    public static final String ERROR_ABSTRACT_TYPE_INVALID = "error.abstractType.invalid";
    public static final String ERROR_ABSTRACT_TYPE_DUPLICATE = "error.abstractType.duplicate";
    public static final String QUESTION_DELETE_ABSTRACT_CONFIRMATION = "document.question.deleteAbstract.text";
    public static final String QUESTION_DELETE_ATTACHMENT_CONFIRMATION = "document.question.deleteAttachment.text";    
    public static final String ERROR_NARRATIVE_TYPE_DUPLICATE = "error.proposalAttachment.narrativeType.allowMulitple";
    public static final String ERROR_ATTACHMENT_TYPE_NOT_SELECTED = "error.proposalAttachment.narrativeType.notSelected";
    public static final String ERROR_ATTACHMENT_STATUS_NOT_SELECTED = "error.proposalAttachment.narrativeStatus.notSelected";
    public static final String ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED = "error.proposalAttachment.description.required";
    public static final String ERROR_PERSONNEL_ATTACHMENT_DESCRITPION_REQUIRED = "error.personnelAttachment.description.required";
    public static final String ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED = "error.personnelAttachment.person.required";
    
    public static final String ERROR_NARRATIVE_STATUS_INVALID = "error.proposalAttachment.moduleStatusCode.invalid";
    
    public static final String ERROR_MISSING = "error.missing";
    public static final String ERROR_INVALUD = "error.invalid";
    
    public static final String ERROR_PERSON_EDITABLE_FIELD_EXISTS = "error.person.editable.field.exists";
    public static final String ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS = "error.investigator.credit.type.exists";

    
    //AuthZ Errors
    public static final String ERROR_AUTHORIZATION_DOCUMENT_INITIATION = "error.authorization.document.initiation";
        
    // proposal ynq errors
    public static final String ERROR_REQUIRED_FOR_EXPLANATION = "error.required.for.explanation";
    public static final String ERROR_REQUIRED_FOR_REVIEW_DATE = "error.required.for.reviewDate";
    
    // Budget Versions errors
    public static final String ERROR_NO_FINAL_BUDGET = "error.final.budget.required";
    
    // KRA Proposal Permission 
    public static final String QUESTION_DELETE_PROPOSAL_USER_CONFIRMATION = "document.question.deleteProposalUser.text";
    public static final String ERROR_NO_PERMISSION = "error.no.permission";
    public static final String ERROR_UNKNOWN_USERNAME = "error.unknown.username";
    public static final String ERROR_DUPLICATE_PROPOSAL_USER = "error.duplicate.proposalUser";
    public static final String ERROR_AGGREGATOR_INCLUSIVE = "error.aggregator.inclusive";
    public static final String ERROR_LAST_AGGREGATOR = "error.last.aggregator";
}
