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
package org.kuali.kra.irb.actions.correspondence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class simply maps a protocol action type to a protocol correspondence template, and returns a list of ProtocolCorrespondenceTemplate objects.
 */
public class ProtocolActionTypeToCorrespondenceTemplateServiceImpl implements ProtocolActionTypeToCorrespondenceTemplateService {
    
    private static Map<String, List<String>> actionTypesToCorrespondenceType;
    // Should be better to use corr type code instead of description or best to have a valid action type/corres type table set up
    static {
        actionTypesToCorrespondenceType = new HashMap<String, List<String>>();
        actionTypesToCorrespondenceType.put(ProtocolActionType.SUBMIT_TO_IRB, Arrays.asList("Protocol Submission Report #1", "Protocol Submission Report #2"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.WITHDRAWN, Arrays.asList("Withdrawal Notice"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.REQUEST_TO_CLOSE, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.REQUEST_FOR_TERMINATION, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.NOTIFY_IRB, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.AMENDMENT_CREATED, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.MODIFY_AMENDMENT_SECTION, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.RENEWAL_CREATED, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.ASSIGN_TO_AGENDA, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.APPROVED, Arrays.asList("Approval Letter"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.DISAPPROVED, Arrays.asList("Rejection Letter"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.PROTOCOL_CREATED, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.CLOSED_FOR_ENROLLMENT, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.RESPONSE_APPROVAL, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.IRB_ACKNOWLEDGEMENT, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.IRB_REVIEW_NOT_REQUIRED, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.DATA_ANALYSIS_ONLY, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.REOPEN_ENROLLMENT, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, Arrays.asList("Closure Notice"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.TERMINATED, Arrays.asList("Termination Notice"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.SUSPENDED, Arrays.asList("Suspension notice"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.EXPIRED, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.SUSPENDED_BY_DSMB, Arrays.asList("Suspension notice"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.EXPEDITE_APPROVAL, Arrays.asList("Expedited Approval Letter"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.GRANT_EXEMPTION, Arrays.asList("Grant Exemption Notice")); 
        actionTypesToCorrespondenceType.put(ProtocolActionType.ADMINISTRATIVE_CORRECTION, Arrays.asList(""));
        actionTypesToCorrespondenceType.put(ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, Arrays.asList("Specific Minor Revisions Letter"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, Arrays.asList("Substantive Revisions Required Letter"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.DEFERRED, Arrays.asList("Notice Of Deferral"));
        actionTypesToCorrespondenceType.put(ProtocolActionType.ABANDON_PROTOCOL, Arrays.asList("Abandon Notice"));
        /**
         * The following correspondence types don't map to a Protocol Action at this time:
         * Agenda Report, Committee Roster Report, 
         * Protocol Optional Report #1,  Protocol Optional Report #2, Reminder to IRB Notification #1, Reminder to IRB Notification #2,
         * Reminder to IRB Notification #3, Renewal Reminder Letter #1, Renewal Reminder Letter #2, Renewal Reminder Letter #3,
         * Schedule Minutes, Schedule Optional Report #1, Schedule Optional Report #2
         */
    }
    
    private BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.irb.actions.correspondence.ProtocolActionTypeToCorrespondenceTemplateService#getTemplatesByProtocolAction(java.lang.String)
     */
    public List<ProtocolCorrespondenceTemplate> getTemplatesByProtocolAction(String protocolActionType) {
        if (actionTypesToCorrespondenceType.containsKey(protocolActionType)) {
            List<ProtocolCorrespondenceTemplate> templates = new ArrayList<ProtocolCorrespondenceTemplate>();
            
            Collection<ProtocolCorrespondenceType> protocolCorrespondenceTypes = this.getBusinessObjectService().findAll(ProtocolCorrespondenceType.class);
            List<String> correspondenceTypeDescriptions = actionTypesToCorrespondenceType.get(protocolActionType);
            
            for (ProtocolCorrespondenceType correspondenceType : protocolCorrespondenceTypes) {
                if (correspondenceTypeDescriptions.contains(correspondenceType.getDescription())) {
                    templates.addAll(correspondenceType.getProtocolCorrespondenceTemplates());
                }
            }
            
            return templates;
            
        } else {
            throw new IllegalArgumentException("An illegal protocol action type was provided");
        }
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService){
        this.businessObjectService = businessObjectService;
    }
    
    public BusinessObjectService getBusinessObjectService(){
        return this.businessObjectService;
    }
}
