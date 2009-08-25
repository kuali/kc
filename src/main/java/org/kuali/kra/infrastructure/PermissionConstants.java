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
package org.kuali.kra.infrastructure;

/**
 * The set of all Permissions used by KRA.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface PermissionConstants {
    
    /* Proposal/Budget Permissions */
    
    public static final String CREATE_PROPOSAL = "CREATE_PROPOSAL";
    public static final String MODIFY_PROPOSAL = "MODIFY_PROPOSAL";
    public static final String VIEW_PROPOSAL = "VIEW_PROPOSAL";
    public static final String MODIFY_NARRATIVE = "MODIFY_NARRATIVE";
    public static final String VIEW_NARRATIVE = "VIEW_NARRATIVE";
    public static final String MODIFY_BUDGET = "MODIFY_BUDGET";
    public static final String VIEW_BUDGET = "VIEW_BUDGET";
    public static final String MAINTAIN_PROPOSAL_ACCESS = "MAINTAIN_PROPOSAL_ACCESS";
    public static final String CERTIFY = "CERTIFY";
    public static final String PRINT_PROPOSAL = "PRINT_PROPOSAL";
    public static final String ALTER_PROPOSAL_DATA = "ALTER_PROPOSAL_DATA";
    public static final String SUBMIT_TO_SPONSOR = "SUBMIT_TO_SPONSOR";
    public static final String SUBMIT_PROPOSAL = "SUBMIT_PROPOSAL";
    public static final String ADD_PROPOSAL_VIEWER = "ADD_PROPOSAL_VIEWER";
    
    /* IRB Permissions */
    
    public static final String CREATE_PROTOCOL = "CREATE_PROTOCOL";
    public static final String MODIFY_PROTOCOL = "MODIFY_PROTOCOL";
    public static final String VIEW_PROTOCOL = "VIEW_PROTOCOL";
    public static final String SUBMIT_PROTOCOL = "SUBMIT_PROTOCOL";
    public static final String MAINTAIN_PROTOCOL_ACCESS = "MAINTAIN_PROTOCOL_ACCESS";
    public static final String ADD_PROTOCOL_NOTES = "ADD_PROTOCOL_NOTES";
    public static final String CREATE_AMMENDMENT = "CREATE_AMMENDMENT";
    public static final String CREATE_RENEWAL = "CREATE_RENEWAL";
    public static final String MAINTAIN_PROTOCOL_RELATED_PROJ = "MAINTAIN_PROTOCOL_RELATED_PROJ";
    public static final String EDIT_PROTOCOL_BILLABLE = "EDIT_PROTOCOL_BILLABLE";
    public static final String ADMINSTRATIVE_CORRECTION = "ADMINSTRATIVE_CORRECTION";
    public static final String MAINTAIN_IRB_CORRESP_TEMPLATE = "MAINTAIN_IRB_CORRESP_TEMPLATE";
    public static final String MAINTAIN_PROTOCOL_SUBMISSIONS = "MAINTAIN_PROTOCOL_SUBMISSIONS";
    public static final String MAINTAIN_PROTO_REVIEW_COMMENTS = "MAINTAIN_PROTO_REVIEW_COMMENTS";
    public static final String PERFORM_IRB_ACTIONS_ON_PROTO = "PERFORM_IRB_ACTIONS_ON_PROTO";
    public static final String VIEW_RESTRICTED_NOTES = "VIEW_RESTRICTED_NOTES";
    
    /*
     * Committee Permissions
     */
    public static final String ADD_COMMITTEE = "ADD_COMMITTEE";
    public static final String VIEW_COMMITTEE = "VIEW_COMMITTEE";
    public static final String MODIFY_COMMITTEE = "MODIFY_COMMITTEE";
    public static final String GENERATE_MINUTES = "GENERATE_MINUTES";
    public static final String GENERATE_SCHEDULE = "GENERATE_SCHEDULE";
    public static final String MODIFY_SCHEDULE = "MODIFY_SCHEDULE";
    public static final String MAINTAIN_MEMBERSHIPS = "MAINTAIN_MEMBERSHIPS";
    public static final String MAINTAIN_MINUTES = "MAINTAIN_MINUTES";
    public static final String GENERATE_AGENDA = "GENERATE_AGENDA";
    
    /*
     * Questionnaire Permissions
     */
    public static final String VIEW_QUESTIONNAIRE = "VIEW_QUESTIONNAIRE";
    public static final String MODIFY_QUESTIONNAIRE = "MODIFY_QUESTIONNAIRE";
}
