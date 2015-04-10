/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.core;

public class ProposalDevelopmentConstants {

	public static class KradConstants {
        public static final String REJECT_DIALOG = "PropDev-SubmitPage-RejectDialog";
		public static final String BUDGET_PAGE = "PropDev-BudgetPage";
        public static final String SUBMIT_PAGE = "PropDev-SubmitPage";
		public static final String PREVIOUS_PAGE_ARG = "previous";
		public static final String NEXT_PAGE_ARG = "next";
        public static final String DELIVERY_INFO_PAGE = "PropDev-DeliveryInfoPage";
        public static final String COMPLIANCE_ADD_DIALOG = "PropDev-CompliancePage_AddDialog";

        public static final String PERSONNEL_QUESTIONNAIRE = "personnelQuestionnaire";
        public static final String PROP_DEV_ATTACHMENTS_PAGE_PROPOSAL_DETAILS = "PropDev-AttachmentsPage-ProposalDetails";
        public static final String PROP_DEV_ATTACHMENTS_PAGE_PERSONNEL_DETAILS = "PropDev-AttachmentsPage-PersonnelDetails";
        public static final String PROP_DEV_ATTACHMENTS_PAGE_ABSTRACT_DETAILS = "PropDev-AttachmentsPage-AbstractDetails";
        public static final String PROP_DEV_ATTACHMENTS_PAGE_INTERNAL_DETAILS = "PropDev-AttachmentsPage-InternalDetails";
        public static final String PROP_DEV_ATTACHMENTS_PAGE_NOTE_DETAILS = "PropDev-AttachmentsPage-NoteDetails";
        public static final String PROP_DEV_ATTACHMENT_PAGE_VIEW_EDIT_RIGHT_DIALOG = "PropDev-AttachmentPage-ViewEditRightDialog";
        public static final String PROP_DEV_ATTACHMENT_PAGE_CONFIRM_ADD_ATTACHMENT_TO_CHILD = "PropDev-AttachmentPage-ConfirmAddAttachmentToChild";
        public static final String PROP_PER_DOC_TYPE = "propPerDocType";
        public static final String NARRATIVE_TYPE = "narrativeType";
        public static final String NARRATIVE_STATUS = "narrativeStatus";
        public static final String KC_SEND_NOTIFICATION_WIZARD = "Kc-SendNotification-Wizard";
        public static final String ATTACHMENT_TYPE = "attachmentType";
        public static final String PROPOSAL_ATTACHMENT = "proposalAttachment";
        public static final String INSTITUTE_ATTACHMENT = "instituteAttachment";
        public static final String BINDING_PATH = "bindingPath";
        public static final String PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER_NARRATIVE = "proposalDevelopmentAttachmentHelper.narrative";
        public static final String PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER_BIOGRAPHY = "proposalDevelopmentAttachmentHelper.biography";
        public static final String PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER = "proposalDevelopmentAttachmentHelper";
    }

    public static class Parameters {
        public static final String KEY_PERSON_CERTIFICATION_SELF_CERTIFY_ONLY = "KEY_PERSON_CERTIFICATION_SELF_CERTIFY_ONLY";
        public static final String NOTIFY_ALL_CERTIFICATIONS_COMPLETE = "notifyAggregatorWhenAllCertificationsComplete";
    }

    public static class ParameterValues {
        public static final String KEY_PERSON_CERTIFICATION_BEFORE_SUBMIT = "BS";
        public static final String KEY_PERSON_CERTIFICATION_BEFORE_APPROVE = "BA";
    }

    public static class PropertyConstants {
        public static final String DOCUMENT_DEVELOPMENT_PROPOSAL = "document.developmentProposal";
        public static final String PERSONNEL_BIOS = DOCUMENT_DEVELOPMENT_PROPOSAL + ".propPersonBios";
        public static final String NARRATIVES = DOCUMENT_DEVELOPMENT_PROPOSAL + ".narratives";
        public static final String INSTITUTE_ATTACHMENTS = DOCUMENT_DEVELOPMENT_PROPOSAL + ".instituteAttachments";
        public static final String PROPOSAL_PERSONS = DOCUMENT_DEVELOPMENT_PROPOSAL + ".proposalPersons";
    }
    
    public static class NotificationConstants {
    	public static final String NOTIFICATION_STEP_0 = "0";
    	public static final String NOTIFICATION_STEP_2 = "2";
    	public static final String NOTIFICATION_S2S_SUBMIT_ACTION_CODE = "101";
    	public static final String NOTIFICATION_S2S_SUBMIT_CONTEXT_NAME =  "Proposal Submitted";
    }
    
    public static class ResubmissionOptions {
    	public static final String GENERATE_NEW_IP = "N";
    	public static final String GENERATE_NEW_VERSION_OF_IP = "A";
    	public static final String GENERATE_NEW_VERSION_OF_ORIGINAL_IP = "O";
    	public static final String DO_NOT_GENERATE_NEW_IP = "X";
    }
    
    public static class PropDevDocumentActions {
		public static final String SUBMIT_TO_S2S = "submitToS2S";
		public static final String SUBMIT_TO_SPONSOR = "submitToSponsor";
        public static final String NOTIFY_PROPOSAL_PERSONS = "NOTIFY_PROPOSAL_PERSONS";
    }
    
    public static class PropDevParameterConstants {
		public static final String CHANGE_CORRECTED_CODE = "S2S_SUBMISSION_TYPE_CODE_CHANGE_CORRECTED_APPLICATION";
	    public static final String PROPOSAL_TYPE_CODE_NEW_PARM = "PROPOSAL_TYPE_CODE_NEW";
	    public static final String PROPOSAL_TYPE_CODE_RESUBMISSION_PARM = "PROPOSAL_TYPE_CODE_RESUBMISSION";
	    public static final String PROPOSAL_TYPE_CODE_RENEWAL_PARM = "PROPOSAL_TYPE_CODE_RENEWAL";
	    public static final String PROPOSAL_TYPE_CODE_CONTINUATION_PARM = "PROPOSAL_TYPE_CODE_CONTINUATION";
	    public static final String PROPOSAL_TYPE_CODE_REVISION_PARM = "PROPOSAL_TYPE_CODE_REVISION";
	    public static final String PROPOSAL_TYPE_CODE_TASK_ORDER_PARM = "PROPOSAL_TYPE_CODE_TASK_ORDER";
    }
    
    public static class AuthConstants {
		public static final String MODIFY_PROPOSAL_EDIT_MODE = "modifyProposal";
		public static final String VIEW_ONLY_PROPOSAL_EDIT_MODE = "viewProposal";
        public static final String MAINTAIN_HIERARCHY_EDIT_MODE = "maintainProposalHierarchy";
        public static final String CAN_SAVE_CERTIFICATION = "canSaveCertification";
    }
    
    
}
