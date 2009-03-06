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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolPersonnelRule;
import org.kuali.kra.irb.rule.SaveProtocolPersonnelRule;
import org.kuali.kra.irb.rule.UpdateProtocolPersonnelRule;
import org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.UpdateProtocolPersonnelEvent;
import org.kuali.kra.irb.service.ProtocolPersonnelService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class contains rules to validate protocol personnel.
 */
public class ProtocolPersonnelRule extends ResearchDocumentRuleBase implements AddProtocolPersonnelRule, SaveProtocolPersonnelRule, UpdateProtocolPersonnelRule {

    private static final String ERROR_PROPERTY_NEW_PERSON = "newProtocolPerson"; 
    private static final String ERROR_PROPERTY_EXISTING_PERSON = "document.protocol.protocolPersons";
    private static final String ERROR_PROPERTY_PERSON_ROLE = ".protocolPersonRoleId"; 
    private static final String ERROR_PROPERTY_PERSON_UNIT = ".protocolPersonUnit"; 
    private String ERROR_PROPERTY_PERSON_INDEX = "[personIndex]";
    private String PERSON_INDEX = "personIndex";
    
    /**
     * @see org.kuali.kra.irb.rule.AddProtocolPersonnelRule#processAddProtocolPersonnelBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent)
     */
    public boolean processAddProtocolPersonnelBusinessRules(AddProtocolPersonnelEvent addProtocolPersonnelEvent) {
        boolean isValid = true;

        ProtocolPerson protocolPerson = addProtocolPersonnelEvent.getProtocolPerson();
        if(isEmptyPersonOrRole(protocolPerson)) {
            isValid = false;
        }else {
            isValid = !isDuplicateInvestigator(protocolPerson, getProtocolPersons(addProtocolPersonnelEvent), true);
            isValid &= !isDuplicatePerson(protocolPerson, addProtocolPersonnelEvent);
        }
        return isValid;
    }
    
    /**
     * This method is to check if Principal Investigator role is already assigned to a person
     * get investigator person if existing so that this helps to display message in appropriate tab.
     * @param protocolPerson
     * @param protocolPersons
     * @return true / false
     */
    private boolean isDuplicateInvestigator(ProtocolPerson protocolPerson, List<ProtocolPerson> protocolPersons, boolean newPerson) {
        boolean investigatorDuplicate = false;
        if(getProtocolPersonnelService().isPrincipalInvestigator(protocolPerson)) {
            ProtocolPerson principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(protocolPersons);
            if(principalInvestigator != null) {
                investigatorDuplicate = true;
                reportError(formatErrorPropertyName(newPerson, protocolPersons.indexOf(principalInvestigator), ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PROTOCOL_PERSONNEL_MULTIPLE_PI);
            }
        }
        return investigatorDuplicate;
    }
    
    /**
     * This method is to format error property for new person and persons in the list based 
     * on person index.
     * Index is applied for existing person list. For new person index is ignored.
     * This is to display message in appropriate tab
     * @param newPerson
     * @param personIndex
     * @param errorKey
     * @return String - formatted error property
     */
    private String formatErrorPropertyName(boolean newPerson, int personIndex, String errorKey) {
        String errorProperty = null;
        if(newPerson) {
            errorProperty = ERROR_PROPERTY_NEW_PERSON.concat(errorKey);
        }else {
            errorProperty = new StringBuilder(ERROR_PROPERTY_EXISTING_PERSON)
            .append(ERROR_PROPERTY_PERSON_INDEX.replaceAll(PERSON_INDEX, Integer.toString(personIndex)))
            .append(errorKey)
            .toString();
        }
        return errorProperty;
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
            reportError(formatErrorPropertyName(true, 0, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_DUPLICATE_PROTOCOL_PERSONNEL);
        }
        return duplicatePerson;
    }

    /**
     * This method is to check if user selected a person and protocol role for a new person
     * Protocol person and role fields are mandatory
     * @param protocolPerson
     * @return true / false
     */
    private boolean isEmptyPersonOrRole(ProtocolPerson protocolPerson) {
        boolean personRoleEmpty = false;
        if(StringUtils.isEmpty(protocolPerson.getProtocolPersonRoleId()) ||
                (StringUtils.isEmpty(protocolPerson.getPersonId()) &&
                protocolPerson.getRolodexId() == null)) {
            reportError(formatErrorPropertyName(true, 0, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PROTOCOL_PERSONNEL_ROLE_MANDATORY);
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
        isValid &= isValidRoleChange(protocolDocument.getProtocol().getProtocolPersons());
        isValid &= isValidPrincipalInvestigator(protocolDocument.getProtocol().getProtocolPersons());
        isValid &= isValidPersonUnit(protocolDocument.getProtocol().getProtocolPersons());
        return isValid;
    }
    
    /**
     * This method is to check whether the protocol has a valid Principal Investigator
     * Method is triggered during save to check the person list
     * Check to see if PI exists. If found check to see if there is a duplicate.
     * Only one PI is permitted for a protocol.
     * @param protocolPersons
     * @return
     */
    private boolean isValidPrincipalInvestigator(List<ProtocolPerson> protocolPersons) {
        boolean investigatorValid = true;
        ProtocolPerson principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(protocolPersons);
        if(principalInvestigator == null) {
            investigatorValid = false;
            reportError(formatErrorPropertyName(true, 0, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PRINCIPAL_INVESTIGATOR_NOT_FOUND);
        }else {
            List<ProtocolPerson> existingProtocolPersons = new ArrayList<ProtocolPerson>();
            existingProtocolPersons.addAll(protocolPersons);
            existingProtocolPersons.remove(principalInvestigator);
            investigatorValid = !isDuplicateInvestigator(principalInvestigator, existingProtocolPersons, false);
        }
        return investigatorValid;
    }

    /**
     * This method is to check if at least one unit is defined for a person.
     * We need to figure out whether unit details is mandatory for the person.
     * Check person role to find whether unit is required.
     * If unit(s) defined, check to see if one is marked as lead unit.
     * @param protocolPersons
     * @return true / false
     */
    private boolean isValidPersonUnit(List<ProtocolPerson> protocolPersons) {
        boolean personUnitValid = true;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(isUnitDetailsRequired(protocolPerson)) {
                int personIndex = protocolPersons.indexOf(protocolPerson);
                if(protocolPerson.getProtocolUnits().size() == 0) {
                    reportError(formatErrorPropertyName(false, personIndex, ERROR_PROPERTY_PERSON_UNIT), KeyConstants.ERROR_PROTOCOL_UNIT_NOT_FOUND);
                }else {
                    boolean leadUnitExists = isPersonLeadUnitExists(protocolPerson.getProtocolUnits());
                    if(!leadUnitExists) {
                        reportError(formatErrorPropertyName(false, personIndex, ERROR_PROPERTY_PERSON_UNIT), KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NOT_DEFINED);
                    }
                    personUnitValid &= leadUnitExists;
                }
            }
        }
        return personUnitValid;
    }
    
    /**
     * This method is to check whether unit details should be configured for the 
     * person based on person role.
     * @param protocolPerson
     * @return true / false
     */
    private boolean isUnitDetailsRequired(ProtocolPerson protocolPerson) {
        if(protocolPerson.getProtocolPersonRole().isUnitDetailsRequired()) {
            return true;
        }else {
            return false;
        }
    }
 
    /**
     * This method is to check if lead unit exists in a protocol unit list
     * defined for a person
     * @param protocolUnits
     * @return true / false
     */
    private boolean isPersonLeadUnitExists(List<ProtocolUnit> protocolUnits) {
        boolean unitExists = false;
        for(ProtocolUnit protocolUnit : protocolUnits) {
            if(protocolUnit.getLeadUnitFlag()) {
                unitExists = true;
                break;
            }
        }
        return unitExists;
    }
    
    /**
     * This method is to check whether role change is permitted for each person role
     * Method triggered during save to check all persons in the list
     * If role change is valid check to see if we need to switch Investigator and Co-Investigator
     * roles.
     * @param protocolPersons
     * @return
     */
    private boolean isValidRoleChange(List<ProtocolPerson> protocolPersons) {
        boolean roleChangeValid = true;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(!protocolPerson.getPreviousPersonRoleId().equalsIgnoreCase(protocolPerson.getProtocolPersonRoleId())) {
                int personIndex = protocolPersons.indexOf(protocolPerson);
                roleChangeValid &= isRoleValid(protocolPerson, personIndex);
            }
        }
        if(roleChangeValid) {
            updateInvestigatorCoInvestigatorRole(protocolPersons);
        }
        return roleChangeValid;
    }
    
    /**
     * This method is to update investigator co-investigator roles when
     * person switch roles.
     * @param protocolPersons
     */
    private void updateInvestigatorCoInvestigatorRole(List<ProtocolPerson> protocolPersons) {
        getProtocolPersonnelService().switchInvestigatorCoInvestigatorRole(protocolPersons);
    }
    
    /**
     * This method is to check if changed role is valid and permitted
     * invoked from valid check during save and update view methods
     * @param protocolPerson
     * @param personIndex
     * @return
     */
    private boolean isRoleValid(ProtocolPerson protocolPerson, int personIndex) {
        if(!getProtocolPersonnelService().isRoleChangePermitted(protocolPerson)) {
            reportError(formatErrorPropertyName(false, personIndex, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_ROLE_CHANGE_NOT_PERMITTED);
            return false;
        }
        return true;
    }


    /**
     * @see org.kuali.kra.irb.rule.UpdateProtocolPersonnelRule#processUpdateProtocolPersonnelBusinessRules(org.kuali.kra.irb.rule.event.UpdateProtocolPersonnelEvent)
     */
    public boolean processUpdateProtocolPersonnelBusinessRules(UpdateProtocolPersonnelEvent updateProtocolPersonnelEvent) {
        boolean isValid = true;
        int PersonIndex = updateProtocolPersonnelEvent.getPersonIndex();
        List<ProtocolPerson> protocolPersons = ((ProtocolDocument)updateProtocolPersonnelEvent.getDocument()).getProtocol().getProtocolPersons(); 
        ProtocolPerson protocolPerson = protocolPersons.get(PersonIndex);
        isValid &= isRoleValid(protocolPerson, PersonIndex);
        if(isValid) {
            updateInvestigatorCoInvestigatorRole(protocolPersons);
        }
        return isValid;
    }
    
}
