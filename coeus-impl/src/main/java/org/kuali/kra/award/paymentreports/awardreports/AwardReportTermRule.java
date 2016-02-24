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
package org.kuali.kra.award.paymentreports.awardreports;

import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This interface defines the rule processing method
 */
public interface AwardReportTermRule extends BusinessRule {

    /**
     * This method is used to validate AwardPaymentSchedule items in an Award
     * @param awardApprovedEquipmentRuleEvent
     * @return
     */
    boolean processAwardReportTermBusinessRules(AwardReportTermRuleEvent event);
    
    /**
     * 
     * This method is used to validate a new AwardPaymentSchedule to be added to an Award
     * @param event
     * @return
     */
    boolean processAddAwardReportTermBusinessRules(AddAwardReportTermRuleEvent event);
    
}
