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
package org.kuali.kra.award.paymentreports.awardreports;

import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class AwardReportTermRecipientRuleImpl extends KcTransactionalDocumentRuleBase
                                            implements AwardReportTermRecipientRule {
    
    private static final String AWARD_REPORT_TERM_RECIPIENT_CONTACT_ID_PROPERTY = "contactId";
    private static final String AWARD_REPORT_TERM_RECIPIENT_RELODEX_ID_PROPERTY="rolodexId";
    private static final String CONTACT_ERROR_PARM = "Contact Type (Contact)";
    private static final String ORGANIZATION_ERROR_PARM = "Name/Organization (Organization)";
    
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
        ArrayList<String> rolodexRecipientsWithNullContactId = new ArrayList<String>();
        for (AwardReportTermRecipient listItem : awardReportTermRecipientItems) {
            if (listItem != null) {
                if(listItem.getContactId() == null && listItem.getRolodex() != null && !rolodexRecipientsWithNullContactId.contains(listItem.getRolodexId())){
                   rolodexRecipientsWithNullContactId.add(listItem.getRolodexId().toString()) ;
                }
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
            if (awardReportTermRecipientItem.getContactId() != null && !contactRecipients.isEmpty()) {
                duplicateFound = checkStringInList(awardReportTermRecipientItem.getContactId().toString(), contactRecipients);
                if(!duplicateFound){
                    Integer rolodexId = getRolodexIdFromContactId(awardReportTermRecipientItem.getContactId());
                    if(rolodexId != null){
                        duplicateFound = checkStringInList(rolodexId.toString(), rolodexRecipientsWithNullContactId);
                    }
                }
            } else {
                if(awardReportTermRecipientItem.getRolodexId() != null){
                    duplicateFound = checkStringInList(awardReportTermRecipientItem.getRolodexId().toString(), rolodexRecipients);
                }else{
                   if(awardReportTermRecipientItem.getContactId() != null){
                       Integer rolodexId = getRolodexIdFromContactId(awardReportTermRecipientItem.getContactId());
                       if(rolodexId != null){
                           duplicateFound = checkStringInList(rolodexId.toString(), rolodexRecipients);
                       }
                   }
                   
                }
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

    boolean validateContactAndOrganizationAreBothNotSelected(AwardReportTermRecipient awardReportTermRecipientItemForValidation) {
        boolean itemValid = !(awardReportTermRecipientItemForValidation.getContactId() != null 
                                && awardReportTermRecipientItemForValidation.getRolodexId() != null);
        
        if(!itemValid){
            reportError(AWARD_REPORT_TERM_RECIPIENT_CONTACT_ID_PROPERTY, 
                    KeyConstants.ERROR_BOTH_SPONSOR_AND_ROLODEX_ARE_SELECTED, CONTACT_ERROR_PARM, ORGANIZATION_ERROR_PARM);
        }
        
        return itemValid;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_AWARD_REPORT_TERM_RECIPIENT_ITEM_NOT_UNIQUE);
    }
    
    private  Integer getRolodexIdFromContactId(Long contactId){
        
        Collection<AwardSponsorContact> awardSponsorContacts = getSponsorContactsUsingKeyValuesService(contactId);
        Integer rolodexId = null;
        if(awardSponsorContacts.size()>1){
            throw new MultipleSponsorContactsException(awardSponsorContacts.size());
        }
        
        for(AwardSponsorContact awardSponsorContact: awardSponsorContacts){
           rolodexId = awardSponsorContact.getRolodexId();
        } 
        return rolodexId;
    }
    @SuppressWarnings("all")
    private Collection<AwardSponsorContact> getSponsorContactsUsingKeyValuesService(Long contactId){        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("awardContactId", contactId);
        return getKeyValuesService().findMatching(AwardSponsorContact.class, map);
    }
    /**
     * 
     * This is a wrapper method for the retrieval of KeyValuesService.
     * 
     * @return
     */
    protected KeyValuesService getKeyValuesService(){
        return KcServiceLocator.getService(KeyValuesService.class);
    }
    
}
