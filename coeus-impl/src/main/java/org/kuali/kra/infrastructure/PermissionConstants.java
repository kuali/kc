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
package org.kuali.kra.infrastructure;

/**
 * The set of all Permissions used by KRA.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface PermissionConstants {
    
    /* Proposal/Budget Permissions */
    
    String CREATE_PROPOSAL = "Create ProposalDevelopmentDocument";
    String MODIFY_PROPOSAL = "Modify ProposalDevelopmentDocument";
    String VIEW_PROPOSAL = "View Proposal";
    String VIEW_CERTIFICATION = "View Certification";
    String MODIFY_NARRATIVE = "Modify Narrative";
    String VIEW_NARRATIVE = "View Narratives";
    String MODIFY_BUDGET = "Modify Budget";
    String MODIFY_PROPOSAL_RATES = "Modify Proposal Rates";
    String VIEW_BUDGET = "View Budget";
    String MAINTAIN_PROPOSAL_ACCESS = "Modify ProposalPermissions";
    String CERTIFY = "Certify";
    String PRINT_PROPOSAL = "Print Proposal";
    String ALTER_PROPOSAL_DATA = "Alter Proposal Data";
    String SUBMIT_TO_SPONSOR = "Submit to Sponsor";
    String SUBMIT_PROPOSAL = "Submit ProposalDevelopmentDocument";
    String ADD_PROPOSAL_VIEWER = "Add Proposal Viewer";
    String MAINTAIN_PROPOSAL_HIERARCHY = "Maintain ProposalHierarchy";
    String DELETE_PROPOSAL = "Delete Proposal";
    String VIEW_SALARIES = "View Personnel Salaries";
    String RECALL_DOCUMENT = "Recall Document";
    String ADD_ADDRESS_BOOK = "Add Address Book";
    String NOTIFY_PROPOSAL_PERSONS = "NOTIFY_PROPOSAL_PERSONS";
    String MODIFY_S2S_ENROUTE = "Modify Proposal Development S2s While Enroute";
        
    /* IRB Permissions */
    
    String CREATE_PROTOCOL = "Create ProtocolDocument";
    String MODIFY_PROTOCOL = "Modify Protocol";
    String DELETE_PROTOCOL = "Delete Protocol";
    String MODIFY_ANY_PROTOCOL = "Modify Any Protocol";
    String VIEW_PROTOCOL = "View Protocol";
    String SUBMIT_PROTOCOL = "Submit Protocol";
    String MAINTAIN_PROTOCOL_ACCESS = "Modify ProtocolPermissions";
    String ADD_PROTOCOL_NOTES = "Add Notes";
    String CREATE_AMMENDMENT = "Create Ammendment";
    String CREATE_RENEWAL = "Create Renewal";
    String MAINTAIN_PROTOCOL_RELATED_PROJ = "Maintain Protocol Related Proj";
    String EDIT_PROTOCOL_BILLABLE = "Edit Protocol Billable";
    String ADMINSTRATIVE_CORRECTION = "Administrative Correction";
    String MAINTAIN_IRB_CORRESP_TEMPLATE = "MAINTAIN_IRB_CORRESP_TEMPLATE";
    String MAINTAIN_PROTOCOL_SUBMISSIONS = "Maintain Protocol Submissions";
    String ASSIGN_IRB_COMMITTEE = "Assign IRB Committee";
    String MAINTAIN_PROTO_REVIEW_COMMENTS = "Maintain Protocol Review Comments";
    String PERFORM_IRB_ACTIONS_ON_PROTO = "Perform IRB Actions on a Protocol";
    String VIEW_RESTRICTED_NOTES = "View Restricted Notes";
    String ANSWER_PROTOCOL_QUESTIONNAIRE = "Answer Protocol Questionnaire";
    String MAINTAIN_ONLINE_REVIEWS = "Maintain Protocol Online Reviews";
    String MODIFY_PROTOCOL_SUBMISSION = "Modify Protocol Submission";
    String REVIEW_NOT_REQUIRED = "Protocol Review Not Required";
    String MAINTAIN_NOTES = "Maintain Protocol Notes";
    String CREATE_ANY_AMENDMENT = "Create Any Amendment";
    String CREATE_ANY_RENEWAL = "Create Any Renewal";
    String SUBMIT_ANY_PROTOCOL = "Submit Any Protocol";
    String MAINTAIN_ANY_PROTOCOL_ACCESS = "Maintain Any Protocol Access";
    String ADD_ANY_PROTOCOL_NOTES = "Add Any Protocol Notes";

    /* IRB - Online Review Permissions */
    
    String MODIFY_PROTOCOL_ONLINE_REVIEW = "Modify Protocol Online Review";
    String VIEW_PROTOCOL_ONLINE_REVIEW = "View Protocol Online Review";
    String MAINTAIN_PROTOCOL_ONLINE_REVIEW = "Maintain Protocol Online Review";
    String MAINTAIN_PROTOCOL_ONLINE_REVIEW_COMMENTS = "Maintain Protocol Online Review Comments";
    String ADD_PROTOCOL_ONLINE_REVIEW_COMMENT = "Add Protocol Online Review Comment";
    
    

    /*
     * Committee Permissions
     */
    String ADD_COMMITTEE = "Create CommitteeDocument";
    String VIEW_COMMITTEE = "View Committee";
    String MODIFY_COMMITTEE = "Modify Committee";
    String GENERATE_MINUTES = "Generate Minutes";
    String GENERATE_SCHEDULE = "Generate Schedule";
    String MODIFY_SCHEDULE = "Modify Schedule";
    String MAINTAIN_MEMBERSHIPS = "Maintain Memberships";
    String MAINTAIN_MINUTES = "Maintain Minutes";
    String GENERATE_AGENDA = "Generate Agenda";
    String VIEW_SCHEDULE = "View Schedule";
    String PERFORM_COMMITTEE_ACTIONS = "Perform Committee Actions";

    
    String ADD_IACUC_COMMITTEE = "Create IACUCCommitteeDocument";
    String VIEW_IACUC_COMMITTEE = "View IACUCCommittee";
    String MODIFY_IACUC_COMMITTEE = "Modify IACUCCommittee";
    String MODIFY_IACUC_SCHEDULE = "Modify IACUC Schedule";
    String VIEW_IACUC_SCHEDULE = "View IACUC Schedule";
    String PERFORM_IACUC_COMMITTEE_ACTIONS = "Perform IACUCCommittee Actions";
    
    
    
    /*
     * Questionnaire Permissions
     */
    String VIEW_QUESTION = "View Question";
    String MODIFY_QUESTION = "Modify Question";
    String VIEW_QUESTIONNAIRE = "View Questionnaire";
    String MODIFY_QUESTIONNAIRE = "Modify Questionnaire";
    String MAINTAIN_QUESTIONNAIRE_USAGE = "Maintain Questionnaire Usage";
    /*
     * Correspondence Template Permissions
     */
    String VIEW_CORRESPONDENCE_TEMPLATE = "View Correspondence Template";
    String MODIFY_CORRESPONDENCE_TEMPLATE = "Modify Correspondence Template";

    // notification template
    String VIEW_NOTIFICATION_TEMPLATE = "View Notification Template";
    String MODIFY_NOTIFICATION_TEMPLATE = "Modify Notification Template";

    // Notifications Permissions
    String VIEW_NOTIFICATION = "View Notification";
    String MODIFY_NOTIFICATION = "Modify Notification";

    /*
     * Batch Correspondence Detail Permissions
     */
    String VIEW_BATCH_CORRESPONDENCE_DETAIL = "View Batch Correspondence Detail";
    String MODIFY_BATCH_CORRESPONDENCE_DETAIL = "Modify Batch Correspondence Detail";
    
    /*
     * Institutional Proposal
     */
    String CREATE_INSTITUTIONAL_PROPOSAL = "Create Institutional Proposal";
    String SUBMIT_INSTITUTIONAL_PROPOSAL = "Submit Institutional Proposal";
    String EDIT_INSTITUTE_PROPOSAL = "Edit Institutional Proposal";
    
    String DOCUMENT_TYPE_ATTRIBUTE_QUALIFIER = "documentTypeName";
    
    
    /**
     * Negotiation
     */
    String NEGOTIATION_CREATE_NEGOTIATION = "CREATE NEGOTIATION";
    String NEGOTIATION_MODIFIY_NEGOTIATION = "MODIFY NEGOTIATION";
    String NEGOTIATION_CREATE_ACTIVITIES = "CREATE ACTIVITIES";
    String NEGOTIATION_MODIFY_ACTIVITIES = "MODIFY ACTIVITIES";
    String NEGOTIATION_VIEW_NEGOTIATION_UNRESTRICTED = "VIEW NEGOTIATION - UNRESTRICTED";
    String NEGOTIATION_VIEW_NEGOTIATION = "VIEW NEGOTIATION";
    
    /**
     * COI disclosure
     */
    String MAINTAIN_COI_DISCLOSURE = "Maintain Coi Disclosure";
    String VIEW_COI_DISCLOSURE = "View Coi Disclosure";
    String MAINTAIN_COI_DISCLOSURE_NOTES = "Maintain Coi Disclosure Notes";
    String MAINTAIN_COI_DISCLOSURE_ATTACHMENTS = "Maintain Coi Disclosure Attachments";
    String VIEW_RESTRICTED_COI_NOTES = "View Coi Restricted Notes";
    String MAINTAIN_COI_REVIEWERS = "Maintain Coi Reviewers";
    String APPROVE_COI_DISCLOSURE = "Approve Coi Disclosure";
    String REPORT_COI_DISCLOSURE = "Report Coi Disclosure";
    String PERFORM_COI_DISCLOSURE_ACTIONS = "Perform Coi Disclosure Actions";
    String UPDATE_FE_STATUS_ADMIN = "Maintain Coi FE Status Admin";
    
    /*
     * Subaward
     */
     String CREATE_SUBAWARD = "CREATE SUBAWARD";
     String MODIFY_SUBAWARD = "MODIFY SUBAWARD";
     String VIEW_SUBAWARD = "VIEW SUBAWARD";
     String SUBMIT_SUBAWARD = "Submit Subaward Document";

     
     /*
      * IACUC permissions
      */
     
     String ADD_ANY_IACUC_PROTOCOL_NOTES = "Add Any IACUC Protocol Notes";                                                   
     String ADD_IACUC_PROTOCOL_NOTES = "Add IACUC Protocol Notes";                                                      
     String COPY_IACUC_PROTOCOL = "Copy IACUC Protocol";                                                           
     String CREATE_ANY_IACUC_AMENDMENT = "Create Any IACUC Amendment";                                        
     String CREATE_ANY_IACUC_CONT_REVIEW = "Create Any IACUC Protocol Continuation Review";             
     String CREATE_ANY_IACUC_CONT_REV_AM = "Create Any IACUC Protocol Continuation Review with Amendment";
     String CREATE_ANY_IACUC_RENEWAL = "Create Any IACUC Renewal";                                         
     String CREATE_ANY_IACUC_REN_AMEN = "Create Any IACUC Renewal with Amendment";                           
     String CREATE_IACUC_AMENDMENT = "Create IACUC Amendment";                                    
     String CREATE_IACUC_CONT_REVIEW = "Create IACUC Protocol Continuation Review";              
     String CREATE_IACUC_CONT_REV_AM = "Create IACUC Protocol Continuation Review with Amendment"; 
     String CREATE_IACUC_PROTOCOL = "Create IACUC Protocol";                                                          
     String CREATE_IACUC_RENEWAL = "Create IACUC Renewal";                                      
     String CREATE_IACUC_RENEWAL_AMENDMENT = "Create Renewal with Amendment for an IACUC Protocol";                        
     String DELETE_ANY_IACUC_PROTOCOL = "Delete Any IACUC Protocol";                                                      
     String DELETE_IACUC_PROTOCOL = "Delete IACUC Protocol";    
     String EDIT_IACUC_PROTOCOL_BILLABLE = "Edit Iacuc Protocol Billable";
     String IACUC_ADMINISTRATIVE_CORRECT = "IACUC Administrative Corrections";                          
     String MAINTAIN_ANY_IACUC_PROT_ACCESS = "Maintain Any IACUC Protocol Access";                                             
     String MAINTAIN_IACUC_CORR_TEMPLATES = "Maintain IACUC Correspondence Templates";               
     String MAINTAIN_IACUC_PROTOCOL_ACCESS = "Modify IACUC ProtocolPermissions";                                                
     String MODIFY_IACUC_PROTO_SUBMISSION = "Modify IACUC Protocol Submission";                                       
     String MAINTAIN_IACUC_RESEARCH_AREAS = "Maintain IACUC Research Areas";                                               
     String MAINTAIN_IACUC_REVIEW_COMMENTS = "Maintain IACUC Protocol Review Comments";                                        
     String MAINTAIN_IACUC_PROTOCOL_SUBMISSIONS = "Maintain IACUC Protocol Submissions";
     String ASSIGN_IACUC_COMMITTEE = "Assign IACUC Committee";
     String MODIFY_ANY_IACUC_PROTOCOL = "Modify Any IACUC Protocol";                                                    
     String MODIFY_IACUC_PROTOCOL = "Modify IACUC Protocol";                                                          
     String MODIFY_IACUC_PERMISSIONS = "Modify IACUC ProtocolPermissions";
     String PERFORM_IACUC_ACTIONS_ON_PROTO = "Perform IACUC Actions on Protocol";                                              
     String REVIEW_IACUC_PROTOCOL = "Review IACUC Protocol";                                                          
     String SUBMIT_ANY_IACUC_PROTOCOL = "Submit Any IACUC Protocol";                                                      
     String SUBMIT_IACUC_PROTOCOL = "Submit IACUC Protocol";
     String VIEW_ANY_IACUC_PROTOCOL = "View Any IACUC Protocol";                                    
     String VIEW_IACUC_PROTOCOL = "View IACUC Protocol";
     String VIEW_IACUC_RESTRICTED_NOTES = "View IACUC Restricted Notes";
     String MAINTAIN_IACUC_ONLINE_REVIEWS = "Maintain IACUC Protocol Online Reviews";
     String IACUC_REVIEW_NOT_REQUIRED = "Iacuc Protocol Review Not Required";

     /* IACUC - Online Review Permissions */
     
     String MODIFY_IACUC_PROTOCOL_ONLINE_REVIEW = "Modify Protocol Online Review";
     String VIEW_IACUC_PROTOCOL_ONLINE_REVIEW = "View Protocol Online Review";
     String MAINTAIN_IACUC_PROTOCOL_ONLINE_REVIEW = "Maintain IACUC Protocol Online Reviews";
     String MAINTAIN_IACUC_PROTOCOL_ONLINE_REVIEW_COMMENTS = "Maintain IACUC Protocol Online Review Comments";
     String ADD_IACUC_PROTOCOL_ONLINE_REVIEW_COMMENT = "Add Protocol Online Review Comment";
     String VIEW_IACUC_CORRESPONDENCE_TEMPLATE = "View IACUC Correspondence Template";
     String MODIFY_IACUC_CORRESPONDENCE_TEMPLATE = "Modify IACUC Correspondence Template";
     
     String VIEW_IACUC_BATCH_CORRESPONDENCE_DETAIL = "View IACUC Batch Correspondence Detail";
     String MODIFY_IACUC_BATCH_CORRESPONDENCE_DETAIL = "Modify IACUC Batch Correspondence Detail";

     /*
      * Unit Permissions 
      */
     String ADD_UNIT = "Add Unit";
     String MODIFY_UNIT = "Modify Unit";
     
     /**
      * Sponsor Hierarchy
     */      
     String SPONSOR_HIERARCHY_ADD = "Add Sponsor Hierarchy";
     String SPONSOR_HIERARCHY_MODIFY = "Modify Sponsor Hierarchy";
     String SPONSOR_HIERARCHY_DELETE = "Delete Sponsor Hierarchy";
     
     /* Research Areas */
     String MAINTAIN_AREA_OF_RESEARCH = "Create or Modify Research Areas";
     String MAINTAIN_IACUC_AREA_OF_RESEARCH = "Create or Modify Iacuc Research Areas";
     
     /* COI Undisclosed events */
     String VIEW_COI_UNDISCLOSED_EVENTS = "View COI Undisclosed Events";
    String VIEW_COI_DISPOSITION_STATUS = "View COI Disclosure Disposition";

    String RETURN_PROPOSAL_DEVELOPMENT_DOCUMENT = "Return ProposalDevelopmentDocument";
	String RUN_GLOBAL_REPORTS = "RUN GLOBAL REPORTS";
    String CANCEL_INSTITUTIONAL_PROPOSAL = "Cancel Institutional Proposal";
    String SAVE_INSTITUTIONAL_PROPOSAL = "Save Institutional Proposal";
    String INITIATE_DOCUMENT = "Initiate Document";

    String WRITE_CLASS = "Write Class";
    String READ_CLASS = "Read Class";
}
