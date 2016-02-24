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
package org.kuali.kra.award.infrastructure;


public enum AwardPermissionConstants {
        VIEW_ANY_PROPOSAL("View Any Proposal"),APPROVE_AWARD_BUDGET("Approve AwardBudget"),CREATE_AWARD_BUDGET("Create AwardBudget")
        ,MODIFY_AWARD_BUDGET("Modify AwardBudget"),SUBMIT_AWARD_BUDGET("Submit AwardBudget"),VIEW_AWARD_BUDGET("View AwardBudget")
        ,MAINTAIN_AWARD_BUDGET_ROUTING("Maintain AwardBudgetRouting"),POST_AWARD_BUDGET("Post AwardBudget")
        ,CREATE_AWARD("Create Award"),MAINTAIN_REPORTING_REQUIREMENTS("Maintain Reporting Requirements")
        ,MODIFY_AWARD("Modify Award"),VIEW_AWARD("View Award"),MAINTAIN_NOTEPAD_ENTRIES("Maintain Notepad Entries")
        ,MAINTAIN_AWARD_ATTACHMENTS("Maintain Award Attachments")
        ,SUBMIT_AWARD("Submit Award")
        ,VIEW_AWARD_ATTACHMENTS("View Award Attachments"),VIEW_AWARDS_AT_UNIT("View Award At Unit"),VIEW_AWARD_TEMPLATE("View Award Template")
        ,MODIFY_AWARD_REPORT_TRACKING("Modify Award Report Tracking"),CREATE_AWARD_ACCOUNT("Create Award Account");

    private String permission;   
     
    AwardPermissionConstants(String permission) {
        this.permission = permission;
    }    
    
    public String getAwardPermission() {
        return permission;
    }

}

