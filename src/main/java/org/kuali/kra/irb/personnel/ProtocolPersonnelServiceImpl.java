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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.rice.kns.service.BusinessObjectService;


class ProtocolPersonnelServiceImpl implements ProtocolPersonnelService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolPersonnelServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private ProtocolPersonTrainingService protocolPersonTrainingService;
    private static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    private static final String REFERENCE_PERSON = "person";
    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String REFERENCE_UNIT = "unit";
    
    private static final boolean LEAD_UNIT_FLAG_ON = true;
    private static final int PI_CHANGED = 0;
    private static final int COI_CHANGED = 1;
    private static final int ROLE_UNCHANGED = -1;
    private static final int RESET_SELECTED_UNIT_FOR_PERSON = 0;
    
    private static final int AFFILIATION_TYPE_OTHER = 0;
    private static final int AFFILIATION_TYPE_STUDENT_INVESTIGATOR = 1;
    private static final int AFFILIATION_TYPE_FACULTY_SUPERVISOR = 2;
    
    
    /**
     * Sets the protocolPersonTrainingService attribute value.
     * 
     * @param protocolPersonTrainingService The protocolPersonTrainingService to set.
     */
    public void setProtocolPersonTrainingService(ProtocolPersonTrainingService protocolPersonTrainingService) {
        this.protocolPersonTrainingService = protocolPersonTrainingService;
    }

    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#addProtocolPerson(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public void addProtocolPerson(Protocol protocol, ProtocolPerson protocolPerson) {
        
        //TODO - How to handle protocol number and sequence number
        protocolPerson.setProtocolNumber("0");
        protocolPerson.setSequenceNumber(0);
        //Refresh Person or Rolodex
        if(!StringUtils.isBlank(protocolPerson.getPersonId())) {
            protocolPerson.refreshReferenceObject(REFERENCE_PERSON);
        }else {
            protocolPerson.refreshReferenceObject(REFERENCE_ROLODEX);
        }
        protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
        getProtocolPersonTrainingService().setTrainedFlag(protocolPerson);
        protocol.getProtocolPersons().add(protocolPerson);
    }

    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#deleteProtocolPerson(org.kuali.kra.irb.Protocol, int)
     */
    public void deleteProtocolPerson(Protocol protocol) {
        List<ProtocolPerson> deletedPersons = new ArrayList<ProtocolPerson>();
        for(ProtocolPerson protocolPerson : protocol.getProtocolPersons()) {
            if(protocolPerson.isDelete()) {
                deletedPersons.add(protocolPerson);
                this.deleteAssociatedPersonnelAttachments(protocolPerson.getProtocolPersonId(), protocol.getAttachmentPersonnels());
            }
        }
        protocol.getProtocolPersons().removeAll(deletedPersons);
    }
    
    /**
     * When deleting a Person, attachments associated with that person must also get deleted.
     * 
     * <p>
     * Implementation note:  This method manually deletes personnel attachments from a Protocol
     * because the Protocol contains the personnel attachments rather than the ProtocolPerson containing
     * the personnel attachments.  If the ProtocolPerson contained the personnel attachments obj could be
     * used to delete records.  The reason for the "Protocol"-"personnel attachment" relationship is
     * to makes all "Protocol"-"note/attachment" relationships consistent.
     * </p>
     * 
     * @param protocolPersonId the person id that is deleted.
     * @param toDelete the Collection to delete from.
     */
    private void deleteAssociatedPersonnelAttachments(Integer protocolPersonId, Collection<ProtocolAttachmentPersonnel> toDelete) {
        
        for (final Iterator<ProtocolAttachmentPersonnel> i = toDelete.iterator(); i.hasNext();) {
            final ProtocolAttachmentPersonnel attachment = i.next();
            if (attachment.getPerson().getProtocolPersonId().equals(protocolPersonId)) {
                i.remove();
            }
        }
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#addProtocolPersonUnit(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public void addProtocolPersonUnit(List<ProtocolUnit> protocolPersonUnits, ProtocolPerson protocolPerson, int selectedPersonIndex) {
        ProtocolUnit newProtocolPersonUnit = protocolPersonUnits.get(selectedPersonIndex);
        newProtocolPersonUnit.setProtocolNumber(protocolPerson.getProtocolNumber());
        newProtocolPersonUnit.setProtocolPersonId(protocolPerson.getProtocolPersonId());

        newProtocolPersonUnit.refreshReferenceObject(REFERENCE_UNIT);
        protocolPerson.addProtocolUnit(newProtocolPersonUnit);
        if(newProtocolPersonUnit.getLeadUnitFlag()) {
            protocolPerson.setSelectedUnit(protocolPerson.getProtocolUnits().size() - 1);
            setLeadUnit(protocolPerson);
        }
        protocolPersonUnits.remove(selectedPersonIndex);
        protocolPersonUnits.add(selectedPersonIndex,new ProtocolUnit());
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#deleteProtocolPersonUnit(java.util.List, org.kuali.kra.irb.personnel.ProtocolPerson, int)
     */
    public void deleteProtocolPersonUnit(Protocol protocol, int selectedPersonIndex, int lineNumber) {
        ProtocolPerson selectedPerson =  protocol.getProtocolPerson(selectedPersonIndex);
        ProtocolUnit protocolUnit = selectedPerson.getProtocolUnit(lineNumber);
        if(protocolUnit.getLeadUnitFlag()) {
            selectedPerson.setSelectedUnit(RESET_SELECTED_UNIT_FOR_PERSON);
        }
        selectedPerson.getProtocolUnits().remove(protocolUnit);
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#switchInvestigatorCoInvestigatorRole(java.util.List)
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
        int roleChanged = ROLE_UNCHANGED;
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
    public List<ProtocolPersonRoleMapping> getPersonRoleMapping(String sourceRoleId) {
        List<ProtocolPersonRoleMapping> personRoleMappings = new ArrayList<ProtocolPersonRoleMapping>();
        Map<String, Object> matchingKeys = new HashMap<String, Object>();
        matchingKeys.put("sourceRoleId", sourceRoleId);
        personRoleMappings.addAll(getBusinessObjectService().findMatching(ProtocolPersonRoleMapping.class, matchingKeys));
        return personRoleMappings;
    }

    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#syncProtocolPersonRoleChanges(java.util.List)
     */
    public void syncProtocolPersonRoleChanges(List<ProtocolPerson> protocolPersons) {
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(!isUnitDetailsRequired(protocolPerson)) {
                protocolPerson.getProtocolUnits().removeAll(protocolPerson.getProtocolUnits());
            }else {
                setLeadUnit(protocolPerson);
            }
            syncPersonRoleAndAffiliation(protocolPerson);
        }
    }
    
    /**
     * This method is to reset all existing lead units and set the current
     * selected unit.
     * @param protocolPerson
     */
    private void setLeadUnit(ProtocolPerson protocolPerson) {
        if(protocolPerson.getProtocolUnits().size() > 0) {
            protocolPerson.resetAllProtocolLeadUnits();
            setLeadUnitFlag(protocolPerson);
        }
    }
    
    /**
     * This method is to check whether unit details is requried for a person role.
     * Refresh person role first so that if there is any change in the role before save the new 
     * role will be set to that person.
     * @param protocolPerson
     * @return boolean true / false
     */
    private boolean isUnitDetailsRequired(ProtocolPerson protocolPerson) {
        boolean unitDetailsRequried = true;
        protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
        if (!protocolPerson.getProtocolPersonRole().isUnitDetailsRequired()) {
            unitDetailsRequried = false;
        }
        return unitDetailsRequried;
    }
    
    /**
     * This method is to check whether Affiliation details is requried for a person role.
     * We need to refresh Person Role to reflect recent changes.
     * Person role refresh is taken care in isUnitDetailsRequired method which is
     * invoked prior to this method.
     * @param protocolPerson
     * @return
     */
    private boolean isAffiliationDetailsRequired(ProtocolPerson protocolPerson) {
        boolean affiliationDetailsRequried = true;
        if (!protocolPerson.getProtocolPersonRole().isAffiliationDetailsRequired()) {
            affiliationDetailsRequried = false;
        }
        return affiliationDetailsRequried;
    }

    /**
     * This method is to set lead unit flag
     * @param protocolPerson
     */
    private void setLeadUnitFlag(ProtocolPerson protocolPerson) {
        protocolPerson.getProtocolUnit(protocolPerson.getSelectedUnit()).setLeadUnitFlag(LEAD_UNIT_FLAG_ON);
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#selectProtocolUnit(java.util.List)
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
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#isPIExists()
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
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#isDuplicateUnit(java.util.List, org.kuali.kra.irb.personnel.ProtocolUnit)
     */
    public boolean isDuplicateUnit(ProtocolPerson protocolPerson, ProtocolUnit newProtocolUnit) {
        boolean duplicateUnit = false;
        for(ProtocolUnit protocolUnit : protocolPerson.getProtocolUnits()) {
            if(protocolUnit.getUnitNumber().equalsIgnoreCase(newProtocolUnit.getUnitNumber())) {
                duplicateUnit = true;
                break;
            }
        }
        return duplicateUnit;
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#isDuplicatePerson(java.util.List, org.kuali.kra.irb.personnel.ProtocolPerson)
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
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#getPrincipalInvestigator(java.util.List)
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
     * This method is to check if the person has the role Co-Investigator
     * @param protocolPerson
     * @return true / false
     */
    private boolean isCoInvestigator(ProtocolPerson protocolPerson) {
        boolean isCoI = false;
        if(protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(getCoInvestigatorRole())) {
            isCoI = true;
        }
        return isCoI;
        
    }

    /**
     * This method sets/updates the principal investigator person
     * @param protocol
     * @param person
     */
    public void updatePrincipalInvestigator(Protocol protocol, ProtocolPerson person) {
        person.setProtocolPersonRoleId(getPrincipalInvestigatorRole());
        List<ProtocolPerson> protocolPersons = protocol.getProtocolPersons();
        ProtocolPerson currentPi = getPrincipalInvestigator(protocolPersons);
        if (currentPi == null ) {
            protocolPersons.add(person);
        } else if (!isDuplicatePerson(protocolPersons,person)) {
            protocol.getProtocolPersons().remove(currentPi);
            protocol.getProtocolPersons().add(person);
        }
        
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#syncPersonRoleAndUnit(org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public void syncPersonRoleAndUnit(ProtocolPerson protocolPerson) {
        if(!isUnitDetailsRequired(protocolPerson)) {
            protocolPerson.getProtocolUnits().removeAll(protocolPerson.getProtocolUnits());
        }
    }

    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#syncPersonRoleAndAffiliation(org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public void syncPersonRoleAndAffiliation(ProtocolPerson protocolPerson) {
        if(!isAffiliationDetailsRequired(protocolPerson)) {
            protocolPerson.setAffiliationTypeCode(null);
        }
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#isValidStudentFacultyMatch(java.util.List)
     */
    public boolean isValidStudentFacultyMatch(List<ProtocolPerson> protocolPersons) {
        boolean validInvestigator = true;
        HashMap<Integer, Integer> investigatorAffiliation = new HashMap<Integer, Integer>();
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if(isAffiliationStudentInvestigatorOrFacultySupervisor(protocolPerson)) {
                updateAffiliationCount(protocolPerson, investigatorAffiliation);
            }
        }
        Integer studentAffiliationCount = investigatorAffiliation.get(getStudentAffiliationType()) == null
                                          ? 0 : investigatorAffiliation.get(getStudentAffiliationType());
        Integer facultySupervisorAffiliationCount = investigatorAffiliation.get(getFacultySupervisorAffiliationType()) == null
                                                    ? 0 : investigatorAffiliation.get(getFacultySupervisorAffiliationType());
        if(studentAffiliationCount > 0 && studentAffiliationCount.compareTo(facultySupervisorAffiliationCount) != 0) {
            validInvestigator = false;
        }
        return validInvestigator;
    }
    
    /**
     * This method is to check whether affiliation code is student investigator or
     * faculty supervisor
     * @param protocolPerson
     * @return true / false
     */
    private boolean isAffiliationStudentInvestigatorOrFacultySupervisor(ProtocolPerson protocolPerson) {
        if(protocolPerson.getAffiliationTypeCode() != null &&
             ((protocolPerson.getAffiliationTypeCode().compareTo(getStudentAffiliationType()) == 0 || 
                 protocolPerson.getAffiliationTypeCode().compareTo(getFacultySupervisorAffiliationType()) == 0))) {
                 return true;
        }
        return false;
    }

    /**
     * This method is to set the total count for each affiliation type
     * @param protocolPerson
     * @param investigatorAffiliation
     */
    private void updateAffiliationCount(ProtocolPerson protocolPerson, HashMap<Integer, Integer> investigatorAffiliation) {
        Integer totalCountForAffiliation = 0;
        totalCountForAffiliation = investigatorAffiliation.get(protocolPerson.getAffiliationTypeCode());
        if(totalCountForAffiliation == null) {
            investigatorAffiliation.put(protocolPerson.getAffiliationTypeCode(), 1);
        }else {
            investigatorAffiliation.remove(protocolPerson.getAffiliationTypeCode());
            investigatorAffiliation.put(protocolPerson.getAffiliationTypeCode(), totalCountForAffiliation++);
        }
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

    /**
     * This method is to get student investigator affiliation type
     * @return Integer
     */
    private Integer getStudentAffiliationType() {
        return Constants.AFFILIATION_STUDENT_INVESTIGATOR_TYPE;
    }

    /**
     * This method is to get faculty supervisor affiliation type
     * @return
     */
    private Integer getFacultySupervisorAffiliationType() {
        return Constants.AFFILIATION_FACULTY_SUPERVISOR_TYPE;
    }

    /**
     * Gets the protocolPersonTrainingService attribute.
     * 
     * @return Returns the protocolPersonTrainingService.
     */
    public ProtocolPersonTrainingService getProtocolPersonTrainingService() {
        return protocolPersonTrainingService;
    }

}
