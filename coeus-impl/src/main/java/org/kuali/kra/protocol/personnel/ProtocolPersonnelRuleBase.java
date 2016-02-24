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
package org.kuali.kra.protocol.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains rules to validate protocol personnel.
 */
public abstract class ProtocolPersonnelRuleBase extends KcTransactionalDocumentRuleBase {

    protected static final String ERROR_PROPERTY_NEW_PERSON = "newProtocolPerson"; 
    protected static final String ERROR_PROPERTY_EXISTING_PERSON = "document.protocolList[0].protocolPersons";
    protected static final String ERROR_PROPERTY_PERSON_ROLE = ".protocolPersonRoleId"; 
    protected static final String ERROR_PROPERTY_PERSON_UNIT = ".protocolPersonUnit"; 
    protected static final String ERROR_PROPERTY_PERSON_INDEX = "[personIndex]";
    protected static final String PERSON_INDEX = "personIndex";
    protected static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    
    private ProtocolPersonnelService protocolPersonnelService;

    /**
     * Runs the rules for adding a protocol personnel.
     * @param addProtocolPersonnelEvent The event invoking the add protocol personnel rules
     * @return True if the protocol personnel is valid, false otherwise
     */
    public boolean processAddProtocolPersonnelEvent(AddProtocolPersonnelEventBase addProtocolPersonnelEvent) {
        boolean isValid = true;

        ProtocolPersonBase protocolPerson = addProtocolPersonnelEvent.getProtocolPerson();
        if (isEmptyPersonOrRole(protocolPerson)) {
            isValid = false;
        } else {
            List<ProtocolPersonBase> protocolPersons = getProtocolPersons(addProtocolPersonnelEvent);
            isValid &= !isDuplicateInvestigator(protocolPerson, protocolPersons, true);
            isValid &= !isPISameAsCoI(protocolPerson, protocolPersons);
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
    private boolean isDuplicateInvestigator(ProtocolPersonBase protocolPerson, List<ProtocolPersonBase> protocolPersons, boolean newPerson) {
        boolean investigatorDuplicate = false;
        if (getProtocolPersonnelService().isPrincipalInvestigator(protocolPerson)) {
            investigatorDuplicate = isDuplicatePI(protocolPersons, protocolPersons, true);
        }
        return investigatorDuplicate;
    }
    
    private boolean isPISameAsCoI(ProtocolPersonBase newProtocolPerson, List<ProtocolPersonBase> protocolPersons) {
        ProtocolPersonBase pi = getProtocolPersonnelService().getPrincipalInvestigator(protocolPersons);
        boolean duplicatePerson = getProtocolPersonnelService().isPISameAsCoI(pi, newProtocolPerson);
        if (duplicatePerson) {
            reportError(formatErrorPropertyName(true, protocolPersons.indexOf(pi), ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PROTOCOL_PERSONNEL_PI_SAMEAS_COI);
        }
        return duplicatePerson;
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
    private boolean isDuplicatePI(List<ProtocolPersonBase> searchableProtocolPersons, List<ProtocolPersonBase> allProtocolPersons, boolean newPerson) {
        boolean investigatorDuplicate = false;
        ProtocolPersonBase principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(searchableProtocolPersons);
        if (principalInvestigator != null) {
            investigatorDuplicate = true;
            reportError(formatErrorPropertyName(newPerson, allProtocolPersons.indexOf(principalInvestigator), ERROR_PROPERTY_PERSON_ROLE), 
                    KeyConstants.ERROR_PROTOCOL_PERSONNEL_MULTIPLE_PI);
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
    protected String formatErrorPropertyName(boolean newPerson, int personIndex, String errorKey) {
        String errorProperty = null;
        if (newPerson) {
            errorProperty = ERROR_PROPERTY_NEW_PERSON.concat(errorKey);
        } else {
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
    private boolean isDuplicatePerson(ProtocolPersonBase protocolPerson, AddProtocolPersonnelEventBase addProtocolPersonnelEvent) {
        boolean duplicatePerson = false;
        if (getProtocolPersonnelService().isDuplicatePerson(getProtocolPersons(addProtocolPersonnelEvent), protocolPerson)) {
            duplicatePerson = true;
            reportError(formatErrorPropertyName(true, 0, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_DUPLICATE_PROTOCOL_PERSONNEL);
        }
        return duplicatePerson;
    }

    /**
     * This method is to check if user selected a person and protocol role for a new person
     * ProtocolBase person and role fields are mandatory
     * @param protocolPerson
     * @return true / false
     */
    private boolean isEmptyPersonOrRole(ProtocolPersonBase protocolPerson) {
        boolean personRoleEmpty = false;
        if (StringUtils.isEmpty(protocolPerson.getProtocolPersonRoleId()) 
                || (StringUtils.isEmpty(protocolPerson.getPersonId()) 
                && protocolPerson.getRolodexId() == null)) {
            reportError(formatErrorPropertyName(true, 0, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PROTOCOL_PERSONNEL_ROLE_MANDATORY);
            personRoleEmpty = true;
        }
        return personRoleEmpty;
    }
    
    /**
     * This method is to get list of protocol persons from document
     * @param addProtocolPersonnelEvent
     * @return
     */
    private List<ProtocolPersonBase> getProtocolPersons(AddProtocolPersonnelEventBase addProtocolPersonnelEvent) {
        return ((ProtocolDocumentBase) addProtocolPersonnelEvent.getDocument()).getProtocol().getProtocolPersons();        
    }

    /**
     * Runs the rules for saving a protocol personnel.
     * @param saveProtocolPersonnelEvent The event invoking the save protocol personnel rules
     * @return True if the protocol personnel are valid, false otherwise
     */
    public boolean processSaveProtocolPersonnelEvent(SaveProtocolPersonnelEventBase saveProtocolPersonnelEvent) {
        boolean isValid = true;
        
        ProtocolDocumentBase protocolDocument = (ProtocolDocumentBase) saveProtocolPersonnelEvent.getDocument();
        List<ProtocolPersonBase> protocolPersons = protocolDocument.getProtocol().getProtocolPersons();
        
        getProtocolPersonnelService().syncProtocolPersonRoleChanges(protocolPersons);
        getProtocolPersonnelService().switchInvestigatorCoInvestigatorRole(protocolPersons);
        
        isValid &= isValidPrincipalInvestigator(protocolPersons);
        isValid &= isValidPersonUnit(protocolPersons);
        
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
    private boolean isValidPrincipalInvestigator(List<ProtocolPersonBase> protocolPersons) {
        boolean investigatorValid = true;
        ProtocolPersonBase principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(protocolPersons);
        if (principalInvestigator == null) {
            investigatorValid = false;
            reportError(formatErrorPropertyName(true, 0, ERROR_PROPERTY_PERSON_ROLE), KeyConstants.ERROR_PRINCIPAL_INVESTIGATOR_NOT_FOUND);
        } else {
            List<ProtocolPersonBase> existingProtocolPersons = new ArrayList<ProtocolPersonBase>();
            existingProtocolPersons.addAll(protocolPersons);
            existingProtocolPersons.remove(principalInvestigator);
            investigatorValid &= !isDuplicatePI(existingProtocolPersons, protocolPersons, false); 
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
    private boolean isValidPersonUnit(List<ProtocolPersonBase> protocolPersons) {
        boolean personUnitValid = true;
        for (ProtocolPersonBase protocolPerson : protocolPersons) {
            protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
            if (protocolPerson.getProtocolPersonRole().isUnitDetailsRequired()) {
                int personIndex = protocolPersons.indexOf(protocolPerson);
                if (protocolPerson.getProtocolUnits().size() == 0) {
                    reportError(formatErrorPropertyName(false, personIndex, ERROR_PROPERTY_PERSON_UNIT), KeyConstants.ERROR_PROTOCOL_UNIT_NOT_FOUND);
                    personUnitValid = false;
                } else {
                    boolean leadUnitExists = isPersonLeadUnitExists(protocolPerson.getProtocolUnits());
                    if (!leadUnitExists) {
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
    private boolean isPersonLeadUnitExists(List<ProtocolUnitBase> protocolUnits) {
        boolean unitExists = false;
        for (ProtocolUnitBase protocolUnit : protocolUnits) {
            if (protocolUnit.getLeadUnitFlag()) {
                unitExists = true;
                break;
            }
        }
        return unitExists;
    }
    
    
    /**
     * Gets the ProtocolBase Personnel Service.
     * @return the ProtocolBase Personnel Service
     */
    public ProtocolPersonnelService getProtocolPersonnelService() {
        if (protocolPersonnelService == null) {
            protocolPersonnelService = getProtocolPersonnelServiceHook();
        }
        return protocolPersonnelService;
    }
    
    public abstract ProtocolPersonnelService getProtocolPersonnelServiceHook();
    
    /**
     * Sets the ProtocolBase Personnel Service.
     * @param protocolPersonnelService the ProtocolBase Personnel Service
     */
    public void setProtocolPersonnelService(ProtocolPersonnelService protocolPersonnelService) {
        this.protocolPersonnelService = protocolPersonnelService;
    }
    
}
