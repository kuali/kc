package org.kuali.coeus.propdev.impl.core;

public class ProposalDevelopmentConstants {

	public static class KradConstants {
        public static final String REJECT_DIALOG = "PropDev-SubmitPage-RejectDialog";
		public static final String BUDGET_PAGE = "PropDev-BudgetPage";
        public static final String SUBMIT_PAGE = "PropDev-SubmitPage";
		public static final String PREVIOUS_PAGE_ARG = "previous";
		public static final String NEXT_PAGE_ARG = "next";

        public static final String PERSONNEL_QUESTIONNAIRE = "personnelQuestionnaire";
	}

    public static class Parameters {
        public static final String KEY_PERSON_CERTIFICATION_SELF_CERTIFY_ONLY = "KEY_PERSON_CERTIFICATION_SELF_CERTIFY_ONLY";
        public static final String NOTIFY_ALL_CERTIFICATIONS_COMPLETE = "notifyAggregatorWhenAllCertificationsComplete";
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
    }
    
    
}
