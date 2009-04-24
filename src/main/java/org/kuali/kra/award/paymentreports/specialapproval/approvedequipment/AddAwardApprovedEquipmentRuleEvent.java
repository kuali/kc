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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;

/**
 * This class is for rule validation when adding new item. It's NOT used by the rule framework
 */
public class AddAwardApprovedEquipmentRuleEvent extends AwardApprovedEquipmentRuleEvent {

    /**
     * Constructs a AddAwardApprovedEquipmentRuleEvent.java.
     * @param errorPathPrefix
     * @param awardDocument
     * @param approvedEquipmentItem
     * @param minimumCapitalization
     */
    public AddAwardApprovedEquipmentRuleEvent(String errorPathPrefix, AwardDocument awardDocument, Award award,
            AwardApprovedEquipment approvedEquipmentItem, MinimumCapitalizationInfo minimumCapitalization) {
        super(errorPathPrefix, awardDocument, award, approvedEquipmentItem, minimumCapitalization);
    }

    /**
     * The rule impl is used here because the rule doesn't define the Add behavior
     * 
     * @see org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipmentRuleEvent
     *  #invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardApprovedEquipmentRuleImpl)rule).processAddAwardApprovedEquipmentBusinessRules(this);
    }
}
