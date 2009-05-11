/*
 * Copyright 2006-2008 The Kuali Foundation
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
    VIEW_ANY_PROPOSAL("VIEW_ANY_PROPOSAL"),APPROVE_AWARD_BUDGET("APPROVE_AWARD_BUDGET"),CREATE_AWARD_BUDGET("CREATE_AWARD_BUDGET")
        ,MAINTAIN_AWARD_BUDGET_ROUTING("MAINTAIN_AWARD_BUDGET_ROUTING"),POST_AWARD_BUDGET("POST_AWARD_BUDGET")
        ,SUBMIT_ANY_AWARD_BUDGET("SUBMIT_ANY_AWARD_BUDGET"),CREATE_AWARD("CREATE_AWARD"),MAINTAIN_REPORTING_REQUIREMENTS("MAINTAIN_REPORTING_REQUIREMENTS")
        ,MODIFY_AWARD("MODIFY_AWARD"),VIEW_AWARD("VIEW_AWARD"),MAINTAIN_NOTEPAD_ENTRIES("MAINTAIN_NOTEPAD_ENTRIES"),MODIFY_AWARD_BUDGET("MODIFY_AWARD_BUDGET")
        ,SUBMIT_AWARD_BUDGET("SUBMIT_AWARD_BUDGET"),VIEW_AWARD_BUDGET("VIEW_AWARD_BUDGET"),MAINTAIN_AWARD_DOCUMENTS("MAINTAIN_AWARD_DOCUMENTS")
        ,VIEW_AWARD_DOCUMENTS("VIEW_AWARD_DOCUMENTS"),VIEW_AWARDS_AT_UNIT("VIEW_AWARDS_AT_UNIT"),VIEW_AWARD_TEMPLATE("VIEW_AWARD_TEMPLATE");

    private String permission;   
     
    AwardPermissionConstants(String permission) {
        this.permission = permission;
    }    
    
    public String getAwardPermission() {
        return permission;
    }

}

