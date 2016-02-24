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

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class is for rule validation when adding new item
 */
public class AddAwardApprovedForeignTravelRuleEvent extends AwardApprovedForeignTravelRuleEvent {

    /**
     * Constructs a AddAwardApprovedEquipmentRuleEvent
     * 
     * This event is NOT used by the rule framework, but is used to support adding
     *  
     * @param errorPathPrefix
     * @param awardDocument
     * @param approvedEquipmentItem
     * @param minimumCapitalization
     */
    public AddAwardApprovedForeignTravelRuleEvent(String errorPathPrefix, AwardDocument awardDocument, Award award,
                                                    AwardApprovedForeignTravel approvedForeignTravel) {
        super(errorPathPrefix, awardDocument, award, approvedForeignTravel);
    }

    /**
     * @see org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRuleEvent
     *  #invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardApprovedForeignTravelRuleImpl) rule).processAddAwardApprovedForeignTravelBusinessRules(this);
    }
}
