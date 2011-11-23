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
 * 
 * This class...
 */
public enum AwardPermissionConstants {
        VIEW_ANY_PROPOSAL("View Any Proposal"),APPROVE_AWARD_BUDGET("Approve AwardBudget"),CREATE_AWARD_BUDGET("Create AwardBudget")
        ,MODIFY_AWARD_BUDGET("Modify AwardBudget"),SUBMIT_AWARD_BUDGET("Submit AwardBudget"),VIEW_AWARD_BUDGET("View AwardBudget")
        ,MAINTAIN_AWARD_BUDGET_ROUTING("Maintain AwardBudgetRouting"),POST_AWARD_BUDGET("Post AwardBudget")
        ,CREATE_AWARD("Create Award"),MAINTAIN_REPORTING_REQUIREMENTS("Maintain Reporting Requirements")
        ,MODIFY_AWARD("Modify Award"),VIEW_AWARD("View Award"),MAINTAIN_NOTEPAD_ENTRIES("Maintain Notepad Entries")
        ,MAINTAIN_AWARD_DOCUMENTS("Maintain Award Documents")
        ,VIEW_AWARD_DOCUMENTS("View Award Documents"),VIEW_AWARDS_AT_UNIT("View Award At Unit"),VIEW_AWARD_TEMPLATE("View Award Template")
        ,MODIFY_AWARD_REPORT_TRACKING("Modify Award Report Tracking");

    private String permission;   
     
    AwardPermissionConstants(String permission) {
        this.permission = permission;
    }    
    
    public String getAwardPermission() {
        return permission;
    }

}

