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

/**
 * This interface defines the rule processing method
 */
public interface AwardApprovedEquipmentRule extends BusinessRule {
    String APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY = "approvedEquipmentItems";
    
    /**
     * This method is used to validate AwardApprovedEquipment items in an Award
     * @param awardApprovedEquipmentRuleEvent
     * @return
     */
    boolean processAwardApprovedEquipmentBusinessRules(AwardApprovedEquipmentRuleEvent event);
}
