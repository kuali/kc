/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This interface defines the rule processing method
 */
public interface AwardApprovedForeignTravelRule extends BusinessRule {
    String APPROVED_FOREIGN_TRAVEL_LIST_ERROR_KEY = "approvedForeignTravel";
    String ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD = "error.award.approvedforeigntravel.field.invalid";
    String ERROR_AWARD_APPROVED_FOREIGN_TRAVEL_NOT_UNIQUE = "error.award.approvedforeigntravel.duplicaterow";
    String ERROR_AWARD_APPROVED_FOREIGN_TRAVEL_END_DATE_BEFORE_START_DATE = "error.award.approvedforeigntravel.enddate.before.startdate";
    
    /**
     * This method is used to validate AwardApprovedForeignTravel in an Award
     * @param event
     * @return
     */
    boolean processAwardApprovedForeignTravelBusinessRules(AwardApprovedForeignTravelRuleEvent event);
}
