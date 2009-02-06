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
package org.kuali.kra.irb.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolLocation;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolPersonnelRule;
import org.kuali.kra.irb.rule.SaveProtocolPersonnelRule;
import org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolPersonnelEvent;
import org.kuali.kra.irb.service.ProtocolPersonnelService;

public class ProtocolPersonnelRule extends ProtocolDocumentRule implements AddProtocolPersonnelRule, SaveProtocolPersonnelRule {

    private static final String ERROR_PROPERTY_PERSON_ROLE = "newProtocolPerson.protocolPersonRoleId"; 
    private static final String ERROR_PROPERTY_ORGANIZATION_TYPE_CODE = "newProtocolLocation.protocolOrganizationTypeCode"; 
    
    /**
     * @see org.kuali.kra.irb.rule.AddProtocolPersonnelRule#processAddProtocolPersonnelBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent)
     */
    public boolean processAddProtocolPersonnelBusinessRules(AddProtocolPersonnelEvent addProtocolPersonnelEvent) {
        boolean isValid = true;

        ProtocolPerson protocolPerson = addProtocolPersonnelEvent.getProtocolPerson();
        if(isEmptyPersonRole(protocolPerson)) {
            isValid = false;
        }else {
            isValid = !isDuplicateInvestigator(protocolPerson, addProtocolPersonnelEvent);
            isValid &= !isDuplicatePerson(protocolPerson, addProtocolPersonnelEvent);
        }
        return isValid;
    }
    
    /**
     * This method is to check if Principal Investigator role is already assigned to a person
     * @param protocolPerson
     * @param addProtocolPersonnelEvent
     * @return true / false
     */
    private boolean isDuplicateInvestigator(ProtocolPerson protocolPerson, AddProtocolPersonnelEvent addProtocolPersonnelEvent) {
        boolean investigatorDuplicate = false;
        if(protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
            if(getProtocolPersonnelService().isPIExists(getProtocolPersons(addProtocolPersonnelEvent))) {
                investigatorDuplicate = true;
                reportError(ERROR_PROPERTY_PERSON_ROLE, KeyConstants.ERROR_PROTOCOL_PERSONNEL_MULTIPLE_PI);
            }
        }
        return investigatorDuplicate;
    }
    
    /**
     * This method is to check if Person already exists in the list
     * @param protocolPerson
     * @param addProtocolPersonnelEvent
     * @return true / false
     */
    private boolean isDuplicatePerson(ProtocolPerson protocolPerson, AddProtocolPersonnelEvent addProtocolPersonnelEvent) {
        boolean duplicatePerson = false;
        if(getProtocolPersonnelService().isDuplicatePerson(getProtocolPersons(addProtocolPersonnelEvent), protocolPerson)) {
            duplicatePerson = true;
            reportError(ERROR_PROPERTY_PERSON_ROLE, KeyConstants.ERROR_DUPLICATE_PROTOCOL_PERSONNEL);
        }
        return duplicatePerson;
    }

    /**
     * This method is to check if user selected a protocol role for a new person
     * Protocol person role is mandatory
     * @param protocolPerson
     * @return true / false
     */
    private boolean isEmptyPersonRole(ProtocolPerson protocolPerson) {
        boolean personRoleEmpty = false;
        if(StringUtils.isEmpty(protocolPerson.getProtocolPersonRoleId())) {
            reportError(ERROR_PROPERTY_PERSON_ROLE, KeyConstants.ERROR_PROTOCOL_PERSONNEL_ROLE_MANDATORY);
            personRoleEmpty = true;
        }
        return personRoleEmpty;
    }
    
 
    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        return KraServiceLocator.getService(ProtocolPersonnelService.class);
    }
    
    /**
     * This method is to get list of protocol persons from document
     * @param addProtocolPersonnelEvent
     * @return
     */
    private List<ProtocolPerson> getProtocolPersons(AddProtocolPersonnelEvent addProtocolPersonnelEvent) {
        return ((ProtocolDocument)addProtocolPersonnelEvent.getDocument()).getProtocol().getProtocolPersons();        
    }

    /**
     * @see org.kuali.kra.irb.rule.SaveProtocolPersonnelRule#processSaveProtocolPersonnelBusinessRules(org.kuali.kra.irb.rule.event.SaveProtocolPersonnelEvent)
     */
    public boolean processSaveProtocolPersonnelBusinessRules(SaveProtocolPersonnelEvent saveProtocolPersonnelEvent) {
        boolean isValid = true;
        ProtocolDocument protocolDocument = (ProtocolDocument)saveProtocolPersonnelEvent.getDocument();
        isValid = getProtocolPersonnelService().isRoleChangePermitted(protocolDocument.getProtocol(), 0);
        return isValid;
    }

}
