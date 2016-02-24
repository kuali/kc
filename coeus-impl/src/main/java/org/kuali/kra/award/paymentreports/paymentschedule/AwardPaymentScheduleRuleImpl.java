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
package org.kuali.kra.award.paymentreports.paymentschedule;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class AwardPaymentScheduleRuleImpl extends KcTransactionalDocumentRuleBase
                                            implements AwardPaymentScheduleRule {

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
