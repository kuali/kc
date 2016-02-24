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

import org.kuali.kra.award.infrastructure.AwardPermissionConstants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PermissionAttributes {
    private static final String ATTR_DOCUMENT_TYPE_NAME = "documentTypeName";
    private static final String ATTR_SECTION_NAME = "sectionName";
    private static final String ATTR_DOCUMENT_ACTION = "documentAction";
    
    private static final String DOC_TYPE_PROPOSAL_DEVELOPMENT = "ProposalDevelopmentDocument";
    private static final String DOC_TYPE_PROTOCOL = "ProtocolDocument";
    private static final String DOC_TYPE_COMMITTEE = "CommitteeDocument";
    private static final String DOC_TYPE_MEETING_MANAGEMENT = "MeetingManagementDocument";
    private static final String DOC_TYPE_AWARD = "AwardDocument";
    private static final String DOC_TYPE_TIMEANDMONEY = "TimeAndMoneyDocument";
    private static final String DOC_TYPE_AWARD_BUDGET = "AwardBudgetDocument";
   
    private static final String SECTION_PROPOSAL = "proposal";
    private static final String SECTION_BUDGET = "budget";
    private static final String SECTION_NARRATIVE = "narrative";
    private static final String SECTION_PROPOSAL_PERMISSIONS = "proposal_permissions";
    private static final String SECTION_PROTOCOL = "protocol";
    private static final String SECTION_PROTOCOL_PERMISSIONS = "protocol_permissions";
    private static final String SECTION_COMMITTEE = "committee";
    private static final String SECTION_MEMBERS = "members";
    private static final String SECTION_MINUTES = "minutes";
    private static final String SECTION_AGENDA = "agenda";
    private static final String SECTION_SCHEDULE = "schedule";
    private static final String SECTION_ACTIONS = "actions";
    private static final String SECTION_ATTACHMENTS = "attachments";
    private static final String SECTION_AWARD = "award";
    private static final String SECTION_AWARD_BUDGET = "award_budget";
    private static final String SECTION_PERSONNEL_SALARIES = "personnel_salaries";
    
    private static final String DOC_ACTION_SUBMIT_TO_SPONSOR = "submit_to_sponsor";
    private static final String DOC_ACTION_PRINT = "print";
    private static final String DOC_ACTION_CERTIFY = "certify";
    private static final String DOC_ACTION_ALTER_MASTER_DATA = "alter_master_data";
    private static final String DOC_ACTION_ADD_VIEWER = "add_viewer";
    private static final String DOC_ACTION_VIEW_MEMBER_DETAILS = "view_member_details";
    private static final String DOC_ACTION_ADD_NOTES = "add_notes";
    private static final String DOC_ACTION_VIEW_RESTRICTED_NOTES = "view_restricted_notes";
    private static final String DOC_ACTION_GENERATE_MINUTES = "generate_minutes";
    private static final String DOC_ACTION_GENERATE_AGENDA = "generate_agenda";
    private static final String DOC_ACTION_GENERATE_SCHEDULE = "generate_schedule";
    private static final String DOC_ACTION_CREATE_AMMENDMENT = "create_ammendment";
    private static final String DOC_ACTION_CREATE_RENEWAL = "create_renewal";
    private static final String DOC_ACTION_MODIFY_SUBMISSION_DETAILS = "modify_submission_details";
    private static final String DOC_ACTION_ADMIN_CORRECTION = "admin_correction";
    private static final String DOC_ACTION_POST_AWARD_BUDGET = "post_award_budget";
    private static final String DOC_ACTION_DELETE_PROPOSAL = "delete_proposal";

    private static final String DOC_TYPE_IACUC_PROTOCOL = "IacucProtocolDocument";
    private static final String DOC_ACTION_VIEW_IACUC_RESTRICTED_NOTES = "view_iacuc_restricted_notes";

    private static final Map<String, HashMap<String, String>> attributesMap;
    
    static {
        HashMap<String, HashMap<String, String>> tempAttributesMap = new HashMap<String, HashMap<String,String>>();
        
        HashMap<String, String> attributes = new HashMap<String, String>();  
        
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        tempAttributesMap.put(PermissionConstants.CREATE_PROPOSAL, attributes);

        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_SECTION_NAME, SECTION_PROPOSAL); 
        tempAttributesMap.put(PermissionConstants.MODIFY_PROPOSAL, attributes);

        tempAttributesMap.put(PermissionConstants.VIEW_PROPOSAL, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_SECTION_NAME, SECTION_NARRATIVE);  
        tempAttributesMap.put(PermissionConstants.MODIFY_NARRATIVE, attributes);

        tempAttributesMap.put(PermissionConstants.VIEW_NARRATIVE, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_SECTION_NAME, SECTION_BUDGET);  
        tempAttributesMap.put(PermissionConstants.MODIFY_BUDGET, attributes);

        tempAttributesMap.put(PermissionConstants.VIEW_BUDGET, attributes);
        
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_SECTION_NAME, SECTION_PERSONNEL_SALARIES);  
        tempAttributesMap.put(PermissionConstants.VIEW_SALARIES, attributes);
        
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        tempAttributesMap.put(PermissionConstants.RECALL_DOCUMENT, attributes);

        attributes = new HashMap<String, String>();
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_SECTION_NAME, SECTION_PROPOSAL_PERMISSIONS);  
        tempAttributesMap.put(PermissionConstants.MAINTAIN_PROPOSAL_ACCESS, attributes);
        
        attributes = new HashMap<String, String>();
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_CERTIFY); 
        tempAttributesMap.put(PermissionConstants.CERTIFY, attributes);
        attributes = new HashMap<String, String>(); 

        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_PRINT); 
        tempAttributesMap.put(PermissionConstants.PRINT_PROPOSAL, attributes);
        attributes = new HashMap<String, String>(); 
        
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_ALTER_MASTER_DATA); 
        tempAttributesMap.put(PermissionConstants.ALTER_PROPOSAL_DATA, attributes);
        attributes = new HashMap<String, String>(); 
        
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_SUBMIT_TO_SPONSOR); 
        tempAttributesMap.put(PermissionConstants.SUBMIT_TO_SPONSOR, attributes);
        attributes = new HashMap<String, String>(); 
        
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_DELETE_PROPOSAL); 
        tempAttributesMap.put(PermissionConstants.DELETE_PROPOSAL, attributes);
        attributes = new HashMap<String, String>();         
        
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_ADD_VIEWER); 
        tempAttributesMap.put(PermissionConstants.ADD_PROPOSAL_VIEWER, attributes);
        attributes = new HashMap<String, String>(); 
        
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        tempAttributesMap.put(PermissionConstants.SUBMIT_PROPOSAL, attributes);
        attributes = new HashMap<String, String>(); 

        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        tempAttributesMap.put(PermissionConstants.CREATE_PROTOCOL, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_SECTION_NAME, SECTION_PROTOCOL); 
        tempAttributesMap.put(PermissionConstants.MODIFY_PROTOCOL, attributes);

        tempAttributesMap.put(PermissionConstants.VIEW_PROTOCOL, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_SECTION_NAME, SECTION_PROTOCOL_PERMISSIONS); 
        tempAttributesMap.put(PermissionConstants.MAINTAIN_PROTOCOL_ACCESS, attributes);

        attributes = new HashMap<String, String>(); 

        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_ADD_NOTES); 
        tempAttributesMap.put(PermissionConstants.ADD_PROTOCOL_NOTES, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_VIEW_RESTRICTED_NOTES); 
        tempAttributesMap.put(PermissionConstants.VIEW_RESTRICTED_NOTES, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_IACUC_PROTOCOL); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_VIEW_IACUC_RESTRICTED_NOTES); 
        tempAttributesMap.put(PermissionConstants.VIEW_IACUC_RESTRICTED_NOTES, attributes);

        
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_CREATE_AMMENDMENT); 
        tempAttributesMap.put(PermissionConstants.CREATE_AMMENDMENT, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_CREATE_RENEWAL); 
        tempAttributesMap.put(PermissionConstants.CREATE_RENEWAL, attributes);
        
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_ADMIN_CORRECTION); 
        tempAttributesMap.put(PermissionConstants.ADMINSTRATIVE_CORRECTION, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        tempAttributesMap.put(PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO, attributes);
        tempAttributesMap.put(PermissionConstants.SUBMIT_PROTOCOL, attributes);
       
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROTOCOL); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_MODIFY_SUBMISSION_DETAILS); 
        tempAttributesMap.put(PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS, attributes);
        attributes = new HashMap<String, String>(); 

        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_COMMITTEE);  
        tempAttributesMap.put(PermissionConstants.ADD_COMMITTEE, attributes);
        
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_COMMITTEE);  
        attributes.put(ATTR_SECTION_NAME, SECTION_COMMITTEE); 
        tempAttributesMap.put(PermissionConstants.MODIFY_COMMITTEE, attributes);
        
        tempAttributesMap.put(PermissionConstants.VIEW_COMMITTEE, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_COMMITTEE);  
        attributes.put(ATTR_SECTION_NAME, SECTION_MEMBERS); 
        tempAttributesMap.put(PermissionConstants.MAINTAIN_MEMBERSHIPS, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_COMMITTEE);  
        attributes.put(ATTR_SECTION_NAME, SECTION_SCHEDULE); 
        tempAttributesMap.put(PermissionConstants.MODIFY_SCHEDULE, attributes);
 
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_COMMITTEE);  
        attributes.put(ATTR_SECTION_NAME, SECTION_SCHEDULE); 
        tempAttributesMap.put(PermissionConstants.VIEW_SCHEDULE, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_SECTION_NAME, SECTION_MINUTES); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_MEETING_MANAGEMENT); 
        tempAttributesMap.put(PermissionConstants.MAINTAIN_MINUTES, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_GENERATE_AGENDA); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_MEETING_MANAGEMENT); 
        tempAttributesMap.put(PermissionConstants.GENERATE_AGENDA, attributes);
        
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_MEETING_MANAGEMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_GENERATE_MINUTES); 
        tempAttributesMap.put(PermissionConstants.GENERATE_MINUTES, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_MEETING_MANAGEMENT); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_GENERATE_SCHEDULE); 
        tempAttributesMap.put(PermissionConstants.GENERATE_SCHEDULE, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_COMMITTEE);  
        attributes.put(ATTR_SECTION_NAME, SECTION_ACTIONS); 
        tempAttributesMap.put(PermissionConstants.PERFORM_COMMITTEE_ACTIONS, attributes);

        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_AWARD); 
        tempAttributesMap.put(AwardPermissionConstants.CREATE_AWARD.getAwardPermission(), attributes);

        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_AWARD); 
        attributes.put(ATTR_SECTION_NAME, SECTION_AWARD); 
        tempAttributesMap.put(AwardPermissionConstants.MODIFY_AWARD.getAwardPermission(), attributes);

        tempAttributesMap.put(AwardPermissionConstants.VIEW_AWARD.getAwardPermission(), attributes);
        
        attributes = new HashMap<String, String>(); 
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_AWARD_BUDGET);
        tempAttributesMap.put(AwardPermissionConstants.CREATE_AWARD_BUDGET.getAwardPermission(), attributes);
        
        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_AWARD_BUDGET); 
        attributes.put(ATTR_SECTION_NAME, SECTION_AWARD_BUDGET); 
        tempAttributesMap.put(AwardPermissionConstants.MODIFY_AWARD_BUDGET.getAwardPermission(), attributes);

        tempAttributesMap.put(AwardPermissionConstants.VIEW_AWARD_BUDGET.getAwardPermission(), attributes);

        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_AWARD_BUDGET); 
        tempAttributesMap.put(AwardPermissionConstants.SUBMIT_AWARD_BUDGET.getAwardPermission(), attributes);

        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_AWARD_BUDGET); 
        attributes.put(ATTR_DOCUMENT_ACTION, DOC_ACTION_POST_AWARD_BUDGET);
        tempAttributesMap.put(AwardPermissionConstants.POST_AWARD_BUDGET.getAwardPermission(), attributes);


        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_AWARD); 
        attributes.put(ATTR_SECTION_NAME, SECTION_ATTACHMENTS);
        tempAttributesMap.put(AwardPermissionConstants.MAINTAIN_AWARD_ATTACHMENTS.getAwardPermission(), attributes);
        
        tempAttributesMap.put(AwardPermissionConstants.VIEW_AWARD_ATTACHMENTS.getAwardPermission(), attributes);

        //Need to verify how this is going to be used
        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, DOC_TYPE_PROPOSAL_DEVELOPMENT); 
        tempAttributesMap.put(AwardPermissionConstants.VIEW_ANY_PROPOSAL.getAwardPermission(), attributes);
        
        attributes = new HashMap<String, String>();
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, "QuestionnaireMaintenanceDocument"); 
        tempAttributesMap.put(PermissionConstants.MODIFY_QUESTIONNAIRE, attributes);
        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, "QuestionnaireMaintenanceDocument"); 
        tempAttributesMap.put(PermissionConstants.VIEW_QUESTIONNAIRE, attributes);

        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, "QuestionMaintenanceDocument"); 
        tempAttributesMap.put(PermissionConstants.MODIFY_QUESTION, attributes);
        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, "QuestionMaintenanceDocument"); 
        tempAttributesMap.put(PermissionConstants.VIEW_QUESTION, attributes);

        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, "SubAwardDocument"); 
        tempAttributesMap.put(PermissionConstants.VIEW_SUBAWARD, attributes);
        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, "SubAwardDocument"); 
        tempAttributesMap.put(PermissionConstants.MODIFY_SUBAWARD, attributes);
        attributes = new HashMap<String, String>();  
        attributes.put(ATTR_DOCUMENT_TYPE_NAME, "SubAwardDocument"); 
        tempAttributesMap.put(PermissionConstants.CREATE_SUBAWARD, attributes);
        attributesMap = Collections.unmodifiableMap(tempAttributesMap);
    }
    
    public static Map<String, String> getAttributes(String permissionName) {
        return attributesMap.get(permissionName);
    }
}
