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


public enum AwardRoleConstants {    

    AWARD_BUDGET_APPROVER("Award Budget Approver"),
    AWARD_BUDGET_ADMINISTRATOR("Award Budget Admnistrator"), AWARD_MODIFIER("Award Modifier"),
    OSP_ADMINISTRATOR("OSP Administrator"), AWARD_BUDGET_AGGREGATOR("Award Budget Aggregator"),
    AWARD_BUDGET_MAINTAINER("Award Budget Maintainer"), AWARD_ATTACHMENTS_MAINTAINER("Award Attachments Maintainer"),
    AWARD_BUDGET_MODIFIER("Award Budget Modifier"),AWARD_VIEWER("Award Viewer"),
    AWARD_BUDGET_VIEWER("Award Budget Viewer"),AWARD_ATTACHMENTS_VIEWER("Award Attachments Viewer"),
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

