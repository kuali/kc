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
 * The set of all Roles used by KRA.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface RoleConstants {
    
    // Role Types
    
    public static final String PROPOSAL_ROLE_TYPE = Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
    public static final String OSP_ROLE_TYPE = "KC-ADM";
    public static final String DEPARTMENT_ROLE_TYPE = "KC-UNT";
    public static final String PROTOCOL_ROLE_TYPE = Constants.MODULE_NAMESPACE_PROTOCOL;
    public static final String AWARD_ROLE_TYPE = Constants.MODULE_NAMESPACE_AWARD;
    public static final String NEGOTIATION_ROLE_TYPE = Constants.MODULE_NAMESPACE_NEGOTIATION;
    public static final String COI_DISCLOSURE_ROLE_TYPE = Constants.MODULE_NAMESPACE_COIDISCLOSURE;

    // The names of the standard roles as used by KIM to identify a role.
    
    public static final String PROPOSAL_CREATOR = "Proposal Creator";
    public static final String AGGREGATOR = "Aggregator";
    public static final String NARRATIVE_WRITER = "Narrative Writer";
    public static final String BUDGET_CREATOR = "Budget Creator";
    public static final String VIEWER = "Viewer";
    public static final String UNASSIGNED = "unassigned";
    public static final String PROTOCOL_CREATOR = "Protocol Creator";
    public static final String PROTOCOL_AGGREGATOR = "Protocol Aggregator";
    public static final String PROTOCOL_VIEWER = "Protocol Viewer";
    public static final String PROTOCOL_UNASSIGNED = "Protocol Unassigned";
    public static final String PROTOCOL_APPROVER = "ProtocolApprover";
    public static final String ACTIVE_COMMITTEE_MEMBER = "Active Committee Member";
    public static final String ACTIVE_COMMITTEE_MEMBER_SCHEDULED_DATE = "Active Committee Member On Scheduled Date";
    public static final String ACTIVE_COMMITTEE_MEMBER_ON_PROTOCOL = "Active Committee Member On Protocol";
    
    public static final String IRB_ADMINISTRATOR = "IRB Administrator";
    public static final String FUNDING_SOURCE_MONITOR = "Funding Source Monitor";
    public static final String IRB_REVIEWER = "IRB Reviewer";
    public static final String IRB_PROTOCOL_ONLINE_REVIEWER = "IRB Online Reviewer";
    
    public static final String OSP_ADMINISTRATOR = "OSP Administrator";
    
    public static final String AGGREGATOR_ONLY = "Aggregator Only";
    public static final String BUDGET_CREATOR_ONLY = "Budget Creator Only";
    
    public static final String KC_ADMIN_NAMESPACE = "KC-ADM";
    
    public static final String NEGOTIATION_NEGOTIATOR = "Negotiator";
    public static final String NEGOTIATION_NEGOTIATION_ADMINISTRATOR = "Negotiation Administrator";
    public static final String NEGOTIATION_PI = "Negotiation PI";
    public static final String NEGOTIATION_COI = "Negotiation COI";
    public static final String NEGOTIATION_KP = "Negotiation KP";
    
    public static final String COI_ADMINISTRATOR = "COI Administrator";
    public static final String COI_REVIEWER = "COI Reviewer";
    
    public static final String PI = "PI";
}


