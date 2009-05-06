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
package org.kuali.kra.irb.personnel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class contains rules to validate protocol personnel.
 */
public class ProtocolPersonnelRule extends ResearchDocumentRuleBase implements AddProtocolPersonnelRule, SaveProtocolPersonnelRule {

    private static final String ERROR_PROPERTY_NEW_PERSON = "newProtocolPerson"; 
    private static final String ERROR_PROPERTY_EXISTING_PERSON = "document.protocolList[0].protocolPersons";
    private static final String ERROR_PROPERTY_PERSON_ROLE = ".protocolPersonRoleId"; 
    private static final String ERROR_PROPERTY_PERSON_UNIT = ".protocolPersonUnit"; 
    private String ERROR_PROPERTY_PERSON_INDEX = "[personIndex]";
    private String PERSON_INDEX = "personIndex";
    private ProtocolPersonnelService protocolPersonnelService;
    private static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    
    /**
     * @see org.kuali.kra.irb.personnel.AddProtocolPersonnelRule#processAddProtocolPersonnelBusinessRules(org.kuali.kra.irb.personnel.AddProtocolPersonnelEvent)
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
     * This method is to check for duplicate investigator
     * @param protocolPerson
     * @param protocolPersons
     * @param newPerson
     * @return
     */
    private boolean isDuplicateInvestigator(ProtocolPerson protocolPerson, List<ProtocolPerson> protocolPersons, boolean newPerson) {
        boolean investigatorDuplicate = false;
        if(getProtocolPersonnelService().isPrincipalInvestigator(protocolPerson)) {
            investigatorDuplicate = isDuplicatePI(protocolPersons, protocolPersons, true);
        }
        return investigatorDuplicate;
    }
    
    /**
     * This method is to check if Principal Investigator role is already assigned to a person
     * get investigator person if existing so that this helps to display message in appropriate tab.
     * Invoked before adding new person and before save.
     * When adding new person, search the list if new person role is PI
     * Before save search the list and identify PI. Filter the person list excluding identified PI.
     * Search the filtered list to see if there is another person with PI role and if exists 
     * add error key for that person to display appropriate error message.
     * @param searchableProtocolPersons - filtered list of protocol persons. Filtered before save.
     * @param allProtocolPersons - entire person list
     * @param newPerson - to identify whether invoked during add or save
     * @return
     */
    private boolean isDuplicatePI(List<ProtocolPerson> searchableProtocolPersons, List<ProtocolPerson> allProtocolPersons, boolean newPerson) {
        boolean investigatorDuplicate = false;
        ProtocolPerson principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(searchableProtocolPersons);
        if(principalInvestigator != null) {
            investigatorDuplicate = true;
            reportError(formatErrorPropertyName(newPerson, allProtocolPersons.indexOf(principalInvestigator), ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PROTOCOL_PERSONNEL_MULTIPLE_PI);
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
        if(protocolPersonnelService == null) {
            protocolPersonnelService = KraServiceLocator.getService(ProtocolPersonnelService.class);
        }
        return protocolPersonnelService;
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
     * @see org.kuali.kra.irb.personnel.SaveProtocolPersonnelRule#processSaveProtocolPersonnelBusinessRules(org.kuali.kra.irb.personnel.SaveProtocolPersonnelEvent)
     */
    public boolean processSaveProtocolPersonnelBusinessRules(SaveProtocolPersonnelEvent saveProtocolPersonnelEvent) {
        boolean isValid = true;
        ProtocolDocument protocolDocument = (ProtocolDocument)saveProtocolPersonnelEvent.getDocument();
        isValid &= isValidPrincipalInvestigator(protocolDocument.getProtocol().getProtocolPersons());
        isValid &= isValidPersonUnit(protocolDocument.getProtocol().getProtocolPersons());
        return isValid;
    }
    
    /**
     * This method is to check whether the protocol has a valid Principal Investigator
     * Method is triggered during save to check the person list
     * Check to see if PI exists. If found check to see if there is a duplicate.
     * Only one PI is permitted for a protocol.
     * PI and Co-I roles can switch. 
     * If Co-I is changed to PI then PI role is changed to Co-I and vice versa.
     * Perform switch before validating duplicate Investigator.
     * @param protocolPersons
     * @return
     */
    private boolean isValidPrincipalInvestigator(List<ProtocolPerson> protocolPersons) {
        boolean investigatorValid = true;
        getProtocolPersonnelService().switchInvestigatorCoInvestigatorRole(protocolPersons);
        ProtocolPerson principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(protocolPersons);
        if(principalInvestigator == null) {
            investigatorValid = false;
            reportError(formatErrorPropertyName(true, 0, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PRINCIPAL_INVESTIGATOR_NOT_FOUND);
        }else {
            List<ProtocolPerson> existingProtocolPersons = new ArrayList<ProtocolPerson>();
            existingProtocolPersons.addAll(protocolPersons);
            existingProtocolPersons.remove(principalInvestigator);
            investigatorValid = !isDuplicatePI(existingProtocolPersons, protocolPersons, false); 
        }
        return investigatorValid;
    }

    /**
     * This method is to check if at least one unit is defined for a person.
     * We need to figure out whether unit details is mandatory for the person.
     * Check person role to find whether unit is required. Roles might have changed before
     * Save is trigger. So refresh person role to see if Unit Details is Required.
     * If unit(s) defined, check to see if one is marked as lead unit.
     * @param protocolPersons
     * @return true / false
     */
    private boolean isValidPersonUnit(List<ProtocolPerson> protocolPersons) {
        boolean personUnitValid = true;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
            if(protocolPerson.getProtocolPersonRole().isUnitDetailsRequired()) {
                int personIndex = protocolPersons.indexOf(protocolPerson);
                if(protocolPerson.getProtocolUnits().size() == 0) {
                    reportError(formatErrorPropertyName(false, personIndex, ERROR_PROPERTY_PERSON_UNIT), KeyConstants.ERROR_PROTOCOL_UNIT_NOT_FOUND);
                    personUnitValid = false;
                }else {
                    boolean leadUnitExists = isPersonLeadUnitExists(protocolPerson.getProtocolUnits());
                    if(!leadUnitExists) {
                        reportError(formatErrorPropertyName(false, personIndex, ERROR_PROPERTY_PERSON_UNIT), KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NOT_DEFINED);
                        personUnitValid = false;
                    }
                }
            }
        }
        return personUnitValid;
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
    
}
