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
package org.kuali.kra.award.paymentreports.awardreports;

import java.util.List;

import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class AwardReportTermRecipientRuleImpl extends ResearchDocumentRuleBase 
                                            implements AwardReportTermRecipientRule {
    
    private static final String AWARD_REPORT_TERM_RECIPIENT_CONTACT_TYPE_PROPERTY = "contactTypeCode";
    private static final String AWARD_REPORT_TERM_RECIPIENT_ORGANIZATION_PROPERTY = "organization";
    private static final String CONTACT_TYPE_ERROR_PARM = "Contact Type (Contact Type)";
    private static final String ORGANIZATION_ERROR_PARM = "Organization (Organization)";
    

    /**
     * 
     * @see org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRule#processAwardPaymentScheduleBusinessRules(
     * org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRuleEvent)
     */
    public boolean processAwardReportTermRecipientBusinessRules(AwardReportTermRecipientRuleEvent event) {
        return processCommonValidations(event);        
    }
    /**
     * 
     * This method processes new AwardPaymentSchedule rules
     * 
     * @param event
     * @return
     */
    public boolean processAddAwardReportTermRecipientBusinessRules(AddAwardReportTermRecipientRuleEvent event) {
        return areRequiredFieldsComplete(event.getAwardReportTermRecipientItemForValidation()) && processCommonValidations(event);        
    }
    
    private boolean processCommonValidations(AwardReportTermRecipientRuleEvent event) {        
        AwardReportTermRecipient awardReportTermRecipientItem = event.getAwardReportTermRecipientItemForValidation();       
        
        List<AwardReportTermRecipient> items = event.getParentAwardReportTerm().getAwardReportTermRecipients();
        
        return isUnique(items, awardReportTermRecipientItem);
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
    boolean isUnique(List<AwardReportTermRecipient> awardReportTermRecipientItems, AwardReportTermRecipient awardReportTermRecipientItem) {
        boolean duplicateFound = false;
        for(AwardReportTermRecipient listItem: awardReportTermRecipientItems) {
            duplicateFound = awardReportTermRecipientItem != listItem && listItem.equals(awardReportTermRecipientItem);
            if(duplicateFound) {
                break;
            }
        }
        
        if(duplicateFound) {
            if(!hasDuplicateErrorBeenReported()) {
                reportError("AwardReportTermRecipient", KeyConstants.ERROR_AWARD_REPORT_TERM_RECIPIENT_ITEM_NOT_UNIQUE);
            }
        }
        return !duplicateFound;
    }

    /**
     * Validate required fields present
     * @param equipmentItem
     * @return
     */
    boolean areRequiredFieldsComplete(AwardReportTermRecipient awardReportTermRecipientItem) {        
        boolean itemValid = isContactTypeFieldComplete(awardReportTermRecipientItem);
        
        itemValid &= isOrganizationFieldComplete(awardReportTermRecipientItem);
        
        return itemValid;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getErrorMap().containsMessageKey(KeyConstants.ERROR_AWARD_REPORT_TERM_RECIPIENT_ITEM_NOT_UNIQUE);
    }
    
    protected boolean isContactTypeFieldComplete(AwardReportTermRecipient awardReportTermRecipientItem){
        boolean itemValid = awardReportTermRecipientItem.getContactTypeCode() != null;
        
        if(!itemValid) {            
            reportError(AWARD_REPORT_TERM_RECIPIENT_CONTACT_TYPE_PROPERTY, KeyConstants.ERROR_REQUIRED, CONTACT_TYPE_ERROR_PARM);
        }
        
        return itemValid;
    }
    
    protected boolean isOrganizationFieldComplete(AwardReportTermRecipient awardReportTermRecipientItem){
        boolean itemValid = awardReportTermRecipientItem.getRolodexId() != null;
        
        if(!itemValid) {            
            reportError(AWARD_REPORT_TERM_RECIPIENT_ORGANIZATION_PROPERTY, KeyConstants.ERROR_REQUIRED, ORGANIZATION_ERROR_PARM);
        }
        
        return itemValid;
    }
}