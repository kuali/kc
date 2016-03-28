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
 * The set of all Roles used by KRA.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface RoleConstants {

    // Role Types
    
    public static final String PROPOSAL_ROLE_TYPE = Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
    public static final String OSP_ROLE_TYPE = "KC-ADM";
    public static final String DEPARTMENT_ROLE_TYPE = "KC-UNT";
    public static final String PROTOCOL_ROLE_TYPE = Constants.MODULE_NAMESPACE_IRB;
    public static final String PROTOCOL_DOC_ROLE_TYPE = Constants.MODULE_DOC_ROLE_NAMESPACE_PROTOCOL;
    public static final String AWARD_ROLE_TYPE = Constants.MODULE_NAMESPACE_AWARD;
    public static final String NEGOTIATION_ROLE_TYPE = Constants.MODULE_NAMESPACE_NEGOTIATION;
    public static final String COI_DISCLOSURE_ROLE_TYPE = Constants.MODULE_NAMESPACE_COIDISCLOSURE;
    public static final String IACUC_ROLE_TYPE = Constants.MODULE_NAMESPACE_IACUC;
    public static final String INSTITUTIONAL_PROPOSAL_TYPE = Constants.MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL;

    // The names of the standard roles as used by KIM to identify a role.
    
    public static final String PROPOSAL_CREATOR = "Proposal Creator";
    public static final String AGGREGATOR = "Aggregator";
    public static final String AGGREGATOR_DOCUMENT_LEVEL = "Aggregator Document Level";
    public static final String NARRATIVE_WRITER = "Narrative Writer";
    public static final String BUDGET_CREATOR = "Budget Creator";
    public static final String VIEWER = "Viewer";
    public static final String VIEWER_DOCUMENT_LEVEL = "Viewer Document Level";
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
    public static final String ACCESS_PROPOSAL_PERSON_INSTITUTIONAL_SALARIES = "Access_Proposal_Person_Institutional_Salaries";
    public static final String VIEW_INSTITUTIONALLY_MAINTAINED_SALARIES = "View Institutionally Maintained Salaries";
    
    
    public static final String KC_ADMIN_NAMESPACE = "KC-ADM";
    
    public static final String NEGOTIATION_NEGOTIATOR = "Negotiator";
    public static final String NEGOTIATION_NEGOTIATION_ADMINISTRATOR = "Negotiation Administrator";
    public static final String NEGOTIATION_PI = "Negotiation PI";
    public static final String NEGOTIATION_COI = "Negotiation COI";
    public static final String NEGOTIATION_KP = "Negotiation KP";
    
    public static final String COI_ADMINISTRATOR = "COI Administrator";
    public static final String COI_REVIEWER = "COI Reviewer";
    
    public static final String PI = "PI";
    
    public static final String IACUC_PROTOCOL_AGGREGATOR = "IACUC Protocol Aggregator";
    public static final String IACUC_PROTOCOL_VIEWER = "IACUC Protocol Viewer";
    public static final String IACUC_PROTOCOL_APPROVER = "IACUC ProtocolApprover";
    public static final String IACUC_PROTOCOL_UNASSIGNED = "IACUC Protocol Unassigned";
    public static final String IACUC_PROTOCOL_CREATOR = "IACUC Protocol Creator";
    public static final String IACUC_PROTOCOL_DELETER = "IACUC Protocol Deleter";
    public static final String MODIFY_IACUC_PROTOCOLS = "Modify IACUC Protocols";
    public static final String ACTIVE_IACUC_COMMITTEE_MEMBER = "Active IACUC Committee Member";
    public static final String ACTIVE_IACUC_COMMITTEE_MEMBER_ON_PROTOCOL = "Active IACUC Committee Member on Protocol";
    public static final String ACTIVE_IACUC_COMMITTEE_MEMBER_ON_SCHEDULED_DATE = "Active IACUC Committee Member on Scheduled Date";
    public static final String IACUC_PROTOCOL_REVIEWER = "IACUC Reviewer";
    public static final String IACUC_PROTOCOL_ONLINE_REVIEWER = "IACUC Online Reviewer";
    public static final String IACUC_ADMINISTRATOR = "IACUC Administrator";

}


