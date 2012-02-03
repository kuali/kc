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
package org.kuali.kra.infrastructure;

/**
 * The set of all Permissions used by KRA.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface PermissionConstants {
    
    /* Proposal/Budget Permissions */
    
    public static final String CREATE_PROPOSAL = "Create ProposalDevelopmentDocument";
    public static final String MODIFY_PROPOSAL = "Modify ProposalDevelopmentDocument";
    public static final String VIEW_PROPOSAL = "View Proposal";
    public static final String MODIFY_NARRATIVE = "Modify Narrative";
    public static final String VIEW_NARRATIVE = "View Narratives";
    public static final String MODIFY_BUDGET = "Modify Budget";
    public static final String MODIFY_PROPOSAL_RATES = "Modify Proposal Rates";
    public static final String VIEW_BUDGET = "View Budget";
    public static final String MAINTAIN_PROPOSAL_ACCESS = "Modify ProposalPermissions";
    public static final String CERTIFY = "Certify";
    public static final String PRINT_PROPOSAL = "Print Proposal";
    public static final String ALTER_PROPOSAL_DATA = "Alter Proposal Data";
    public static final String SUBMIT_TO_SPONSOR = "Submit to Sponsor";
    public static final String SUBMIT_PROPOSAL = "Submit ProposalDevelopmentDocument";
    public static final String ADD_PROPOSAL_VIEWER = "Add Proposal Viewer";
    public static final String MAINTAIN_PROPOSAL_HIERARCHY = "Maintain ProposalHierarchy";
    public static final String DELETE_PROPOSAL = "Delete Proposal";
        
    /* IRB Permissions */
    
    public static final String CREATE_PROTOCOL = "Create ProtocolDocument";
    public static final String MODIFY_PROTOCOL = "Modify Protocol";
    public static final String DELETE_PROTOCOL = "Delete Protocol";
    public static final String MODIFY_ANY_PROTOCOL = "Modify Any Protocol";
    public static final String VIEW_PROTOCOL = "View Protocol";
    public static final String SUBMIT_PROTOCOL = "Submit Protocol";
    public static final String MAINTAIN_PROTOCOL_ACCESS = "Modify ProtocolPermissions";
    public static final String ADD_PROTOCOL_NOTES = "Add Notes";
    public static final String CREATE_AMMENDMENT = "Create Ammendment";
    public static final String CREATE_RENEWAL = "Create Renewal";
    public static final String MAINTAIN_PROTOCOL_RELATED_PROJ = "Maintain Protocol Related Proj";
    public static final String EDIT_PROTOCOL_BILLABLE = "Edit Protocol Billable";
    public static final String ADMINSTRATIVE_CORRECTION = "Administrative Correction";
    public static final String MAINTAIN_IRB_CORRESP_TEMPLATE = "MAINTAIN_IRB_CORRESP_TEMPLATE";
    public static final String MAINTAIN_PROTOCOL_SUBMISSIONS = "Maintain Protocol Submissions";
    public static final String MAINTAIN_PROTO_REVIEW_COMMENTS = "Maintain Protocol Review Comments";
    public static final String PERFORM_IRB_ACTIONS_ON_PROTO = "Perform IRB Actions on a Protocol";
    public static final String VIEW_RESTRICTED_NOTES = "View Restricted Notes";
    public static final String ANSWER_PROTOCOL_QUESTIONNAIRE = "Answer Protocol Questionnaire";
    public static final String MAINTAIN_ONLINE_REVIEWS = "Maintain Protocol Online Reviews";
    public static final String MODIFY_PROTOCOL_SUBMISSION = "Modify Protocol Submission";
    public static final String REVIEW_NOT_REQUIRED = "Protocol Review Not Required";
    public static final String MAINTAIN_NOTES = "Maintain Protocol Notes";
    public static final String CREATE_ANY_AMENDMENT = "Create Any Amendment";
    public static final String CREATE_ANY_RENEWAL = "Create Any Renewal";
    public static final String SUBMIT_ANY_PROTOCOL = "Submit Any Protocol";
    public static final String MAINTAIN_ANY_PROTOCOL_ACCESS = "Maintain Any Protocol Access";
    public static final String ADD_ANY_PROTOCOL_NOTES = "Add Any Protocol Notes";

    /* IRB - Online Review Permissions */
    
    public static final String MODIFY_PROTOCOL_ONLINE_REVIEW = "Modify Protocol Online Review";
    public static final String VIEW_PROTOCOL_ONLINE_REVIEW = "View Protocol Online Review";
    public static final String MAINTAIN_PROTOCOL_ONLINE_REVIEW = "Maintain Protocol Online Review";
    public static final String MAINTAIN_PROTOCOL_ONLINE_REVIEW_COMMENTS = "Maintain Protocol Online Review Comments";
    public static final String ADD_PROTOCOL_ONLINE_REVIEW_COMMENT = "Add Protocol Online Review Comment";
    
    

    /*
     * Committee Permissions
     */
    public static final String ADD_COMMITTEE = "Create CommitteeDocument";
    public static final String VIEW_COMMITTEE = "View Committee";
    public static final String MODIFY_COMMITTEE = "Modify Committee";
    public static final String GENERATE_MINUTES = "Generate Minutes";
    public static final String GENERATE_SCHEDULE = "Generate Schedule";
    public static final String MODIFY_SCHEDULE = "Modify Schedule";
    public static final String MAINTAIN_MEMBERSHIPS = "Maintain Memberships";
    public static final String MAINTAIN_MINUTES = "Maintain Minutes";
    public static final String GENERATE_AGENDA = "Generate Agenda";
    public static final String VIEW_SCHEDULE = "View Schedule";
    public static final String PERFORM_COMMITTEE_ACTIONS = "Perform Committee Actions";

    
    /*
     * Questionnaire Permissions
     */
    public static final String VIEW_QUESTION = "View Question";
    public static final String MODIFY_QUESTION = "Modify Question";
    public static final String VIEW_QUESTIONNAIRE = "View Questionnaire";
    public static final String MODIFY_QUESTIONNAIRE = "Modify Questionnaire";
    public static final String MAINTAIN_QUESTIONNAIRE_USAGE = "Maintain Questionnaire Usage";
    /*
     * Correspondence Template Permissions
     */
    public static final String VIEW_CORRESPONDENCE_TEMPLATE = "View Correspondence Template";
    public static final String MODIFY_CORRESPONDENCE_TEMPLATE = "Modify Correspondence Template";

    // notification template
    public static final String VIEW_NOTIFICATION_TEMPLATE = "View Notification Template";
    public static final String MODIFY_NOTIFICATION_TEMPLATE = "Modify Notification Template";

    // Notifications Permissions
    public static final String VIEW_NOTIFICATION = "View Notification";
    public static final String MODIFY_NOTIFICATION = "Modify Notification";

    /*
     * Batch Correspondence Detail Permissions
     */
    public static final String VIEW_BATCH_CORRESPONDENCE_DETAIL = "View Batch Correspondence Detail";
    public static final String MODIFY_BATCH_CORRESPONDENCE_DETAIL = "Modify Batch Correspondence Detail";
    
    /*
     * Institutional Proposal
     */
    String CREATE_INSTITUTIONAL_PROPOSAL = "Create Institutional Proposal";
    String SUBMIT_INSTITUTIONAL_PROPOSAL = "Submit Institutional Proposal";
    public static final String EDIT_INSTITUTE_PROPOSAL = "Edit Institutional Proposal";
    
    String DOCUMENT_TYPE_ATTRIBUTE_QUALIFIER = "documentTypeName";
    
    
    /**
     * Negotiation
     */
    public static final String NEGOTIATION_CREATE_NEGOTIATION = "CREATE NEGOTIATION";
    public static final String NEGOTIATION_MODIFIY_NEGOTIATION = "MODIFY NEGOTIATION";
    public static final String NEGOTIATION_CREATE_ACTIVITIES = "CREATE ACTIVITIES";
    public static final String NEGOTIATION_MODIFY_ACTIVITIES = "MODIFY ACTIVITIES";
    public static final String NEGOTIATION_VIEW_NEGOTIATION_UNRESTRICTED = "VIEW NEGOTIATION - UNRESTRICTED";
    public static final String NEGOTIATION_VIEW_NEGOTIATION = "VIEW NEGOTIATION";
    
    /**
     * COI disclosure
     */
    public static final String MAINTAIN_COI_DISCLOSURE = "Maintain Coi Disclosure";
    public static final String VIEW_COI_DISCLOSURE = "View Coi Disclosure";
    public static final String MAINTAIN_COI_DISCLOSURE_NOTES = "Maintain Coi Disclosure Notes";
    public static final String MAINTAIN_COI_DISCLOSURE_ATTACHMENTS = "Maintain Coi Disclosure Attachments";
    public static final String VIEW_RESTRICTED_COI_NOTES = "View Coi Restricted Notes";
    public static final String MAINTAIN_COI_REVIEWERS = "Maintain Coi Reviewers";

    
    /*
     * Subaward
     */
     public static final String CREATE_SUBAWARD = "CREATE SUBAWARD";
     public static final String MODIFY_SUBAWARD = "MODIFY SUBAWARD";
     public static final String VIEW_SUBAWARD = "VIEW SUBAWARD";
     
     /*
      * IACUC permissions
      */
     
     public static final String ADD_ANY_IACUC_PROTOCOL_NOTES = "Add Any IACUC Protocol Notes";                                                   
     public static final String ADD_IACUC_PROTOCOL_NOTES = "Add IACUC  Protocol Notes";                                                      
     public static final String COPY_IACUC_PROTOCOL = "Copy IACUC  Protocol";                                                           
     public static final String CREATE_ANY_IACUC_AMENDMENT = "Create Any IACUC Protocol Amendment";                                        
     public static final String CREATE_ANY_IACUC_CONT_REVIEW = "Create Any IACUC Protocol Continuation Review";             
     public static final String CREATE_ANY_IACUC_CONT_REV_AM = "Create Any IACUC Protocol Continuation Review with Amendment";
     public static final String CREATE_ANY_IACUC_RENEWAL = "Create Any IACUC Protocol Renewals";                                         
     public static final String CREATE_ANY_IACUC_REN_AMEN = "Create Any IACUC Protocol Renewal with Amendment";                           
     public static final String CREATE_IACUC_AMENDMENT = "Create IACUC Amendment";                                    
     public static final String CREATE_IACUC_CONT_REVIEW = "Create IACUC Protocol Continuation Review";              
     public static final String CREATE_IACUC_CONT_REV_AM = "Create IACUC Protocol Continuation Review with Amendment"; 
     public static final String CREATE_IACUC_PROTOCOL = "Create IACUC Protocol";                                                          
     public static final String CREATE_IACUC_RENEWAL = "Create IACUC protocol Renewal";                                      
     public static final String CREATE_IACUC_RENEWAL_AMENDMENT = "Create Renewal with Amendment for an IACUC Protocol";                        
     public static final String DELETE_ANY_IACUC_PROTOCOL = "Delete Any IACUC Protocol";                                                      
     public static final String DELETE_IACUC_PROTOCOL = "Delete IACUC  Protocol";                                                         
     public static final String IACUC_ADMINISTRATIVE_CORRECT = "IACUC Protocol Administrative Corrections";                          
     public static final String MAINTAIN_ANY_IACUC_PROT_ACCESS = "Maintain Any IACUC Protocol Access";                                             
     public static final String MAINTAIN_IACUC_CORR_TEMPLATES = "Maintain IACUC Correspondence Templates";               
     public static final String MAINTAIN_IACUC_PROTOCOL_ACCESS = "Modify IACUC ProtocolPermissions";                                                
     public static final String MAINTAIN_IACUC_PROTO_SUBMISION = "Modify IACUC Protocol Submission";                                       
     public static final String MAINTAIN_IACUC_RESEARCH_AREAS = "Maintain IACUC Areas of Research";                                               
     public static final String MAINTAIN_IACUC_REVIEW_COMMENTS = "Maintain IACUC Protocol Review Comments";                                        
     public static final String MODIFY_ANY_IACUC_PROTOCOL = "Maintain Any IACUC Protocol";                                                    
     public static final String MODIFY_IACUC_PROTOCOL = "Modify IACUC Protocol";                                                          
     public static final String PERFORM_IACUC_ACTIONS_ON_PROTO = "Perform IACUC Actions on IACUC Protocol";                                              
     public static final String REVIEW_IACUC_PROTOCOL = "Review IACUC Protocol";                                                          
     public static final String SUBMIT_ANY_IACUC_PROTOCOL = "Submit Any IACUC Protocol";                                                      
     public static final String SUBMIT_IACUC_PROTOCOL = "Submit IACUC  protocol for Review";                                            
     public static final String VIEW_ANY_IACUC_PROTOCOL = "View Any IACUC Protocol";                                    
     public static final String VIEW_IACUC_PROTOCOL = "View IACUC Protocol";
     public static final String VIEW_IACUC_RESTRICTED_NOTES = "View IACUC Protocol Restricted Notes";

}
