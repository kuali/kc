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
package org.kuali.kra.award.paymentreports.paymentschedule;

import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This interface defines the rule processing method
 */
public interface AwardPaymentScheduleRule extends BusinessRule {
    public static final String PAYMENT_SCHEDULE_ITEMS_LIST_ERROR_KEY = "paymentScheduleItems";
    
    /**
     * This method is used to validate AwardPaymentSchedule items in an Award
     * @param awardApprovedEquipmentRuleEvent
     * @return
     */
    boolean processAwardPaymentScheduleBusinessRules(AwardPaymentScheduleRuleEvent event);
    
    /**
     * 
     * This method is used to validate a new AwardPaymentSchedule to be added to an Award
     * @param event
     * @return
     */
    boolean processAddAwardPaymentScheduleBusinessRules(AddAwardPaymentScheduleRuleEvent event);
}
