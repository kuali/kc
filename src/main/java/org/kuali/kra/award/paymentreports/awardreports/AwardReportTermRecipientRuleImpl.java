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
package org.kuali.kra.award.paymentreports.awardreports;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class AwardReportTermRecipientRuleImpl extends ResearchDocumentRuleBase 
                                            implements AwardReportTermRecipientRule {
    
    private static final String AWARD_REPORT_TERM_RECIPIENT_CONTACT_ID_PROPERTY = "contactId";
    private static final String AWARD_REPORT_TERM_RECIPIENT_RELODEX_ID_PROPERTY="rolodexId";
    private static final String CONTACT_ERROR_PARM = "Contact (Contact)";
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
        AwardReportTermRecipient awardReportTermRecipientItemForValidation = event.getAwardReportTermRecipientItemForValidation();
        
        return areRequiredFieldsComplete(awardReportTermRecipientItemForValidation) && processCommonValidations(event)
            && validateContactAndOrganizationAreBothNotSelected(awardReportTermRecipientItemForValidation);        
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
    protected boolean isUnique(List<AwardReportTermRecipient> awardReportTermRecipientItems, AwardReportTermRecipient awardReportTermRecipientItem) {
        boolean duplicateFound = false;
        ArrayList<String> contactRecipients = new ArrayList<String>();
        ArrayList<String> rolodexRecipients = new ArrayList<String>();
        for (AwardReportTermRecipient listItem : awardReportTermRecipientItems) {
            if (listItem != null) {
                if (listItem.getContactId() != null) {
                    duplicateFound = checkStringInList(listItem.getContactId().toString(), contactRecipients);
                } else {
                    duplicateFound = checkStringInList(listItem.getRolodexId().toString(), rolodexRecipients);
                }
                if (duplicateFound) {
                    break;
                }
            }
        }
        if (!duplicateFound && awardReportTermRecipientItem != null) {
            if (awardReportTermRecipientItem.getContactId() != null) {
                duplicateFound = checkStringInList(awardReportTermRecipientItem.getContactId().toString(), contactRecipients);
            } else {
                duplicateFound = checkStringInList(awardReportTermRecipientItem.getRolodexId().toString(), rolodexRecipients);
            }
        }
        
        if (duplicateFound) {
            if (!hasDuplicateErrorBeenReported()) {
                reportError("AwardReportTermRecipient", KeyConstants.ERROR_AWARD_REPORT_TERM_RECIPIENT_ITEM_NOT_UNIQUE);
            }
        }
        return !duplicateFound;
    }
    
    private boolean checkStringInList(String receipient, ArrayList<String> recipientList) {
        boolean exists = false;
        if (recipientList.contains(receipient)) {
            exists = true;
        } else {
            recipientList.add(receipient);
        }
        return exists;
    }

    /**
     * Validate required fields present
     * @param equipmentItem
     * @return
     */
    boolean areRequiredFieldsComplete(AwardReportTermRecipient awardReportTermRecipientItem) {        
        boolean itemValid = awardReportTermRecipientItem.getContactId() != null || awardReportTermRecipientItem.getRolodexId() != null;
        if(!itemValid){
            reportError(AWARD_REPORT_TERM_RECIPIENT_RELODEX_ID_PROPERTY, KeyConstants.ERROR_REQUIRED_ORGANIZATION_FIELD);   
        }
        return itemValid;
    }
    
    /**
     * This method...
     * @param awardReportTermRecipientItemForValidation TODO
     */
    boolean validateContactAndOrganizationAreBothNotSelected(AwardReportTermRecipient awardReportTermRecipientItemForValidation) {
        boolean itemValid = !(awardReportTermRecipientItemForValidation.getContactId() != null 
                                && awardReportTermRecipientItemForValidation.getRolodexId() != null);
        
        if(!itemValid){
            reportError(AWARD_REPORT_TERM_RECIPIENT_CONTACT_ID_PROPERTY, KeyConstants.ERROR_BOTH_SPONSOR_AND_ROLODEX_ARE_SELECTED, CONTACT_ERROR_PARM, ORGANIZATION_ERROR_PARM);
        }
        
        return itemValid;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_AWARD_REPORT_TERM_RECIPIENT_ITEM_NOT_UNIQUE);
    }
}
