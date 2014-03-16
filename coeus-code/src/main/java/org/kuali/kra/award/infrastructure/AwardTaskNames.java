/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.infrastructure;

/**
 * 
 * This class...
 */
public enum AwardTaskNames {
    MODIFY_AWARD("modifyAward"),VIEW_AWARD("viewAward"),MODIFY_AWARD_ROLES("modifyAwardRoles"),ADD_AWARD_NOTES("addAwardNotes"),
    MAINTAIN_REPORT_TRACKING("maintainReportTracking"),CREATE_AWARD_ACCOUNT("createAwardAccount");

    private String awardTaskName;   
     
    AwardTaskNames(String awardTaskName) {
        this.awardTaskName = awardTaskName;
    }    
    
    public String getAwardTaskName() {
        return awardTaskName;
    }

}

