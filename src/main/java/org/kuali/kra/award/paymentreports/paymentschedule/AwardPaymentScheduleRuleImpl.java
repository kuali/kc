/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.List;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class AwardPaymentScheduleRuleImpl extends ResearchDocumentRuleBase 
                                            implements AwardPaymentScheduleRule {
        
    private static final String PAYMENT_SCHEDULE_DUE_DATE_PROPERTY = "dueDate";
    private static final String DUE_DATE_ERROR_PARM = "Due Date (Due Date)";

    /**
     * 
     * @see org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRule#processAwardPaymentScheduleBusinessRules(
     * org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRuleEvent)
     */
    public boolean processAwardPaymentScheduleBusinessRules(AwardPaymentScheduleRuleEvent event) {
        return processCommonValidations(event);        
    }
    /**
     * 
     * This method processes new AwardPaymentSchedule rules
     * 
     * @param event
     * @return
     */
    public boolean processAddAwardPaymentScheduleBusinessRules(AddAwardPaymentScheduleRuleEvent event) {
        return processCommonValidations(event);        
    }
    
    private boolean processCommonValidations(AwardPaymentScheduleRuleEvent event) {
        AwardPaymentSchedule paymentScheduleItem = event.getPaymentScheduleItemForValidation();        
        List<AwardPaymentSchedule> items = event.getAward().getPaymentScheduleItems();
        return isUnique(items, paymentScheduleItem);
    }
    
    /**
     * An payment schedule item is unique if no other matching items are in the collection
     * To know if this is a new add or an edit of an existing equipment item, we check 
     * the identifier for nullity. If null, this is an add; otherwise, it's an update
     * If an update, then we expect to find one match in the collection (itself). If an add, 
     * we expect to find no matches in the collection 
     * @param paymentScheduleItems
     * @param paymentScheduleItem
     * @return
     */
    boolean isUnique(List<AwardPaymentSchedule> paymentScheduleItems, AwardPaymentSchedule paymentScheduleItem) {
        boolean duplicateFound = false;
        for(AwardPaymentSchedule listItem: paymentScheduleItems) {
            duplicateFound = paymentScheduleItem != listItem && listItem.equals(paymentScheduleItem);
            if(duplicateFound) {
                break;
            }
        }
        
        if(duplicateFound) {
            if(!hasDuplicateErrorBeenReported()) {
                reportError(PAYMENT_SCHEDULE_ITEMS_LIST_ERROR_KEY, 
                        KeyConstants.ERROR_AWARD_PAYMENT_SCHEDULE_ITEM_NOT_UNIQUE, DUE_DATE_ERROR_PARM);
            }
        }
        return !duplicateFound;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_AWARD_PAYMENT_SCHEDULE_ITEM_NOT_UNIQUE);
    }
}
