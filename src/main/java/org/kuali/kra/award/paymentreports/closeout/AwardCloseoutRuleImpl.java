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
package org.kuali.kra.award.paymentreports.closeout;

import java.util.List;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The AwardCloseoutRuleImpl
 */
public class AwardCloseoutRuleImpl extends ResearchDocumentRuleBase implements AwardCloseoutRule {
        
    private static final String CLOSEOUT_REPORT_NAME_PROPERTY = "closeoutReportName";
    private static final String REPORT_NAME_ERROR_PARM = "Final Report (Final Report)";

    /**
     * 
     * @see org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRule#processAwardCloseoutBusinessRules(
     *          org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRuleEvent)
     */
    public boolean processAwardCloseoutBusinessRules(AwardCloseoutRuleEvent event) {
        return processCommonValidations(event);        
    }
    /**
     * 
     * This method processes new AwardCloseout rules
     * 
     * @param event
     * @return
     */
    public boolean processAddAwardCloseoutBusinessRules(AddAwardCloseoutRuleEvent event) {
        return areRequiredFieldsComplete(event.getCloseoutItemForValidation()) && processCommonValidations(event);        
    }
    
    /**
     * 
     * This method processes the common validations.
     * 
     * @param event
     * @return
     */
    private boolean processCommonValidations(AwardCloseoutRuleEvent event) {
        AwardCloseout closeoutItem = event.getCloseoutItemForValidation();        
        List<AwardCloseout> items = event.getAward().getAwardCloseoutItems();
        return isUnique(items, closeoutItem);
    }
    
    /**
     * An closeout item is unique if no other matching items are in the collection
     * To know if this is a new add or an edit of an existing equipment item, we check 
     * the identifier for nullity. If null, this is an add; otherwise, it's an update
     * If an update, then we expect to find one match in the collection (itself). If an add, 
     * we expect to find no matches in the collection 
     * @param closeoutItems
     * @param closeoutItem
     * @return
     */
    boolean isUnique(List<AwardCloseout> closeoutItems, AwardCloseout closeoutItem) {
        boolean duplicateFound = false;
        for(AwardCloseout listItem: closeoutItems) {
            duplicateFound = closeoutItem != listItem && listItem.equals(closeoutItem);
            if(duplicateFound) {
                break;
            }
        }
        
        if(duplicateFound) {
            if(!hasDuplicateErrorBeenReported()) {
                reportError(CLOSEOUT_ITEMS_LIST_ERROR_KEY, KeyConstants.ERROR_AWARD_CLOSEOUT_ITEM_NOT_UNIQUE, REPORT_NAME_ERROR_PARM);
            }
        }
        return !duplicateFound;
    }

    /**
     * Validate required fields present
     * @param closeoutItem
     * @return
     */
    boolean areRequiredFieldsComplete(AwardCloseout closeoutItem) {        
        boolean itemValid = closeoutItem.getCloseoutReportName() != null;
        
        if(!itemValid) {
            reportError(CLOSEOUT_REPORT_NAME_PROPERTY, KeyConstants.ERROR_AWARD_CLOSEOUT_REPORT_NAME_REQUIRED, REPORT_NAME_ERROR_PARM);
        }        
        
        return itemValid;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getErrorMap().containsMessageKey(KeyConstants.ERROR_AWARD_CLOSEOUT_ITEM_NOT_UNIQUE);
    }
}