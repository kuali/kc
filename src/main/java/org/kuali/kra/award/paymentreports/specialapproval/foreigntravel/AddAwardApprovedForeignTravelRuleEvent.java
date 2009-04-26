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

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.kns.rule.BusinessRule;

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
