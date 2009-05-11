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
public enum AwardRoleConstants {    

    APPLICATION_ADMINISTRATOR("Application Administrator"), AWARD_BUDGET_APPROVER("Award Budget Approver"),
    AWARD_BUDGET_ADMINISTRATOR("Award Budget Admnistrator"), AWARD_MODIFIER("Award Modifier"),
    OSP_ADMINISTRATOR("OSP Administrator"), AWARD_BUDGET_AGGREGATOR("Award Budget Aggregator"),
    AWARD_BUDGET_MAINTAINER("Award Budget Maintainer"), AWARD_DOCUMENTS_MAINTAINER("Award Documents Maintainer"),
    AWARD_BUDGET_MODIFIER("Award Budget Modifier"),AWARD_VIEWER("Award Viewer"),
    AWARD_BUDGET_VIEWER("Award Budget Viewer"),AWARD_DOCUMENTS_VIEWER("Award Documents Viewer"),
    DEPARTMENTS_AWARDS_VIEWER("Departments Awards Viewer"),TEMPLATE_VIEWER("Template Viewer"),
    AWARD_UNASSIGNED("Award Unassigned");

    private String role;   
     
    AwardRoleConstants(String role) {
        this.role = role;
    }    
    
    public String getAwardRole() {
        return role;
    }
}

