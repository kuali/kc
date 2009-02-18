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
package org.kuali.kra.irb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolPersonRoleMapping;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.service.ProtocolPersonnelService;


public class ProtocolPersonnelServiceImpl implements ProtocolPersonnelService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolPersonnelServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    private static final String REFERENCE_PERSON = "person";
    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String REFERENCE_UNIT = "unit";
    
    private static final boolean LEAD_UNIT_FLAG_ON = true;
    private static final int PI_CHANGED = 0;
    private static final int COI_CHANGED = 1;
    
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#addProtocolPerson(org.kuali.kra.irb.bo.Protocol, org.kuali.kra.irb.bo.ProtocolPerson)
     */
    public void addProtocolPerson(Protocol protocol, ProtocolPerson protocolPerson) {
        
        //TODO - How to handle protocol number and sequence number
        protocolPerson.setProtocolNumber("0");
        protocolPerson.setSequenceNumber(0);
        protocolPerson.setProtocolId(protocol.getProtocolId());
        //Refresh Person or Rolodex
        if(!StringUtils.isBlank(protocolPerson.getPersonId())) {
            protocolPerson.refreshReferenceObject(REFERENCE_PERSON);
        }else {
            protocolPerson.refreshReferenceObject(REFERENCE_ROLODEX);
        }
        protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
        protocol.getProtocolPersons().add(protocolPerson);
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#deleteProtocolPerson(org.kuali.kra.irb.bo.Protocol, int)
     */
    public void deleteProtocolPerson(Protocol protocol) {
        List<ProtocolPerson> deletedPersons = new ArrayList<ProtocolPerson>();
        for(ProtocolPerson protocolPerson : protocol.getProtocolPersons()) {
            if(protocolPerson.isDelete()) {
                deletedPersons.add(protocolPerson);
            }
        }
        protocol.getProtocolPersons().removeAll(deletedPersons);
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#addProtocolPersonUnit(org.kuali.kra.irb.bo.Protocol, org.kuali.kra.irb.bo.ProtocolPerson)
     */
    public void addProtocolPersonUnit(List<ProtocolUnit> protocolPersonUnits, ProtocolPerson protocolPerson, int selectedPersonIndex) {
        ProtocolUnit newProtocolPersonUnit = protocolPersonUnits.get(selectedPersonIndex);
        newProtocolPersonUnit.setProtocolNumber(protocolPerson.getProtocolNumber());
        newProtocolPersonUnit.setProtocolPersonId(protocolPerson.getProtocolPersonId());

        //TODO - How to handle protocol number and sequence number
        newProtocolPersonUnit.setProtocolNumber("0");
        newProtocolPersonUnit.setSequenceNumber(0);
        
        newProtocolPersonUnit.refreshReferenceObject(REFERENCE_UNIT);
        protocolPerson.addProtocolUnit(newProtocolPersonUnit);
        setLeadUnitFlag(protocolPerson);

        protocolPersonUnits.remove(selectedPersonIndex);
        protocolPersonUnits.add(selectedPersonIndex,new ProtocolUnit());
        
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#deleteProtocolPersonUnit(java.util.List, org.kuali.kra.irb.bo.ProtocolPerson, int)
     */
    public void deleteProtocolPersonUnit(Protocol protocol, ProtocolPerson protocolPerson, int selectedPersonIndex, int lineNumber) {
        ProtocolPerson selectedPerson =  protocol.getProtocolPerson(selectedPersonIndex);
        ProtocolUnit protocolUnit = selectedPerson.getProtocolUnit(lineNumber);
        selectedPerson.removeProtocolUnit(protocolUnit);
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#isRoleChangePermitted(org.kuali.kra.irb.bo.Protocol, int)
     */
    public boolean isRoleChangePermitted(ProtocolPerson protocolPerson) {
        boolean isRolePermitted = true;
        if(!protocolPerson.getPreviousPersonRoleId().equalsIgnoreCase(protocolPerson.getProtocolPersonRoleId())) {
            if(!isRolePermitted(getPersonRoleMapping(protocolPerson.getPreviousPersonRoleId()), protocolPerson)) {
                isRolePermitted = false;
            }
        }
        return isRolePermitted;
    }
    
    /**
     * This method is to check whether role change is permitted based on role mapping
     * and the role that has changed
     * @param personRoleMappings
     * @param selectedProtocolPerson
     * @return true / false
     */
    private boolean isRolePermitted(List<ProtocolPersonRoleMapping> personRoleMappings, ProtocolPerson selectedProtocolPerson) {
        for(ProtocolPersonRoleMapping personRoleMapping : personRoleMappings) {
            if(personRoleMapping.getTargetRoleId().equalsIgnoreCase(selectedProtocolPerson.getProtocolPersonRoleId())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#switchInvestigatorCoInvestigatorRole(java.util.List)
     */
    public void switchInvestigatorCoInvestigatorRole(List<ProtocolPerson> protocolPersons) {
        ProtocolPerson investigator = null;
        String personNewRole = null;
        switch(getPIOrCoIChanged(protocolPersons)) {
            case PI_CHANGED :
                investigator = getPreviousInvestigator(protocolPersons, getPrincipalInvestigatorRole());
                personNewRole = getCoInvestigatorRole();
                break;
            case COI_CHANGED :
                investigator = getPreviousInvestigator(protocolPersons, getCoInvestigatorRole());
                personNewRole = getPrincipalInvestigatorRole();
                break;
        }

        if(investigator != null) {
            updatePersonRole(investigator, personNewRole);
        }
    }
    
    /**
     * This method is to identify which role has changed 
     * Principal Investigator or Co-Investigator
     * @param protocolPersons
     * @return int
     */
    private int getPIOrCoIChanged(List<ProtocolPerson> protocolPersons) {
        int roleChanged = -1;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(isRoleChangedToNewRole(protocolPerson, getPrincipalInvestigatorRole())) {
                roleChanged = PI_CHANGED;
                break;
            }else if(isRoleChangedToNewRole(protocolPerson, getCoInvestigatorRole())) {
                roleChanged = COI_CHANGED;
                break;
            }
        }
        return roleChanged;
    }
    
    /**
     * This method is to update person with new role
     * @param protocolPerson
     * @param targetRole
     */
    private void updatePersonRole(ProtocolPerson protocolPerson, String targetRole) {
        if(protocolPerson != null) {
            protocolPerson.setProtocolPersonRoleId(targetRole);
            protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
        }
    }
    
    /**
     * This method is to check if there is a change in the role - comparing previous role and current
     * role and if the new role is of Principal Investigator or Co Investigator as parameter input.
     * @param protocolPerson
     * @param newRole
     * @return true / false
     */
    private boolean isRoleChangedToNewRole(ProtocolPerson protocolPerson, String newRole) {
        return ((!protocolPerson.getPreviousPersonRoleId().equalsIgnoreCase(protocolPerson.getProtocolPersonRoleId())) 
                && protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(newRole));
    }
    
    /**
     * This method is to get person holding Investigator role or Co-Investigator role
     * based on role parameter
     * @param protocolPersons
     * @param role
     * @return ProtocolPerson - Investigator or Co-Investigator
     */
    private ProtocolPerson getPreviousInvestigator(List<ProtocolPerson> protocolPersons, String role) {
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if((protocolPerson.getPreviousPersonRoleId().equalsIgnoreCase(protocolPerson.getProtocolPersonRoleId())) 
                    && protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(role)) {
                return protocolPerson;
            }
            
        }
        return null;
    }

    /**
     * This method is to fetch person role mapping data based on source role id
     * @param sourceRoleId
     * @return Collection<PersonTraining>
     */
    private List<ProtocolPersonRoleMapping> getPersonRoleMapping(String sourceRoleId) {
        List<ProtocolPersonRoleMapping> personRoleMappings = new ArrayList<ProtocolPersonRoleMapping>();
        Map<String, Object> matchingKeys = new HashMap<String, Object>();
        matchingKeys.put("sourceRoleId", sourceRoleId);
        personRoleMappings.addAll(getBusinessObjectService().findMatching(ProtocolPersonRoleMapping.class, matchingKeys));
        return personRoleMappings;
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#updateProtocolUnit(java.util.List)
     */
    public void updateProtocolUnit(List<ProtocolPerson> protocolPersons) {
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(protocolPerson.getProtocolUnits().size() > 0) {
                protocolPerson.resetAllProtocolLeadUnits();
                setLeadUnitFlag(protocolPerson);
            }
        }
    }
    
    /**
     * This method is to set lead unit flag
     * @param protocolPerson
     */
    private void setLeadUnitFlag(ProtocolPerson protocolPerson) {
        protocolPerson.getProtocolUnit(protocolPerson.getSelectedUnit()).setLeadUnitFlag(LEAD_UNIT_FLAG_ON);
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#selectProtocolUnit(java.util.List)
     */
    public void selectProtocolUnit(List<ProtocolPerson> protocolPersons) {
        for(ProtocolPerson protocolPerson : protocolPersons) {
            int selectedUnit = 0;
            for(ProtocolUnit protocolUnit : protocolPerson.getProtocolUnits()) {
                if(protocolUnit.getLeadUnitFlag()) {
                    protocolPerson.setSelectedUnit(selectedUnit);
                    break;
                }
                selectedUnit++;
            }
        }
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#isPIExists()
     */
    public boolean isPIExists(List<ProtocolPerson> protocolPersons) {
        boolean investigatorExists = false;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(getPrincipalInvestigatorRole())){
                investigatorExists = true;
                break;
            }
        }
        return investigatorExists;
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#isDuplicatePerson(java.util.List, org.kuali.kra.irb.bo.ProtocolPerson)
     */
    public boolean isDuplicatePerson(List<ProtocolPerson> protocolPersons, ProtocolPerson newProtocolPerson) {
        boolean duplicatePerson = false;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(protocolPerson.getPersonUniqueKey().equalsIgnoreCase(newProtocolPerson.getPersonUniqueKey())) {
                duplicatePerson = true;
            }
        }
        return duplicatePerson;
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#getPrincipalInvestigator(java.util.List)
     */
    public ProtocolPerson getPrincipalInvestigator(List<ProtocolPerson> protocolPersons) {
        ProtocolPerson principalInvestigator = null;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(isPrincipalInvestigator(protocolPerson)) {
                principalInvestigator = protocolPerson;
                break;
            }
        }
        return principalInvestigator;
    }

    /**
     * This method is to check if the person has the role Principal Investigator
     * @param protocolPerson
     * @return true / false
     */
    public boolean isPrincipalInvestigator(ProtocolPerson protocolPerson) {
        boolean isInvestigator = false;
        if(protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(getPrincipalInvestigatorRole())) {
            isInvestigator = true;
        }
        return isInvestigator;
        
    }
    
    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * This method is to get principal investigator role
     * @return String - PI role
     */
    private String getPrincipalInvestigatorRole() {
        return Constants.PRINCIPAL_INVESTIGATOR_ROLE;
    }

    /**
     * This method is to get co-investigator role
     * @return String - CO-Investigator role
     */
    private String getCoInvestigatorRole() {
        return Constants.CO_INVESTIGATOR_ROLE;
    }

}
