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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.kuali.rice.krad.rules.rule.BusinessRule;

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
