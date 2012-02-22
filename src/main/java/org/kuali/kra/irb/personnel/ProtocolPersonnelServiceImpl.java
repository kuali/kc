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
package org.kuali.kra.irb.personnel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.PersonEditableService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;


public class ProtocolPersonnelServiceImpl implements ProtocolPersonnelService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolPersonnelServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private SequenceAccessorService sequenceAccessorService;
    private ProtocolPersonTrainingService protocolPersonTrainingService;
    private PersonEditableService personEditableService;

    private static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    private static final String REFERENCE_PERSON = "person";
    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String REFERENCE_UNIT = "unit";
    private static final String PROTOCOL_ATTACHMENT_TYPE = "type";
    
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
        
        Integer nextPersonId = getSequenceAccessorService().getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID").intValue();
        protocolPerson.setProtocolPersonId(nextPersonId);
        //TODO - How to handle protocol number and sequence number
        protocolPerson.setProtocolNumber(protocol.getProtocolNumber());
        protocolPerson.setSequenceNumber(protocol.getSequenceNumber());
        //Refresh Rolodex
        if(StringUtils.isBlank(protocolPerson.getPersonId())) {
            protocolPerson.refreshReferenceObject(REFERENCE_ROLODEX);
            personEditableService.populateContactFieldsFromRolodexId(protocolPerson);
        } else {
            personEditableService.populateContactFieldsFromPersonId(protocolPerson);
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
    protected void deleteAssociatedPersonnelAttachments(Integer protocolPersonId, Collection<ProtocolAttachmentPersonnel> toDelete) {
        
        for (final Iterator<ProtocolAttachmentPersonnel> i = toDelete.iterator(); i.hasNext();) {
            final ProtocolAttachmentPersonnel attachment = i.next();
            if (attachment.getPerson().getProtocolPersonId().equals(protocolPersonId)) {
                i.remove();
            }
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#addProtocolPersonAttachment(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel, int)
     */
    public void addProtocolPersonAttachment(Protocol protocol, ProtocolAttachmentPersonnel newAttachment, int selectedPersonIndex) {

        // syncNewFile
        if (newAttachment.getNewFile() != null && StringUtils.isNotBlank(newAttachment.getNewFile().getFileName())) {
            final AttachmentFile newFile = AttachmentFile.createFromFormFile(newAttachment.getNewFile());
            //setting the sequence number to the old file sequence number
            if (newAttachment.getFile() != null) {
                newFile.setSequenceNumber(newAttachment.getFile().getSequenceNumber());
            }
            newAttachment.setFile(newFile);
            // set to null, so the subsequent post will not creating new file again
            newAttachment.setNewFile(null);
        }
        //assignDocumentId
        if (newAttachment.isNew()) {
            int maxDocId = 0;
            for (ProtocolPerson person : protocol.getProtocolPersons()) {
                for (ProtocolAttachmentPersonnel attachment : person.getAttachmentPersonnels()) {
                    if (attachment.getTypeCode().equals(newAttachment.getTypeCode()) 
                            && (maxDocId < attachment.getDocumentId())) {
                        maxDocId = attachment.getDocumentId();
                    }
                }
            }
            newAttachment.setDocumentId(maxDocId + 1);
        }
        
        newAttachment.setProtocolId(protocol.getProtocolId());
        newAttachment.setProtocolNumber(protocol.getProtocolNumber());
        newAttachment.refreshReferenceObject(PROTOCOL_ATTACHMENT_TYPE);
        protocol.getProtocolPerson(selectedPersonIndex).getAttachmentPersonnels().add(newAttachment);
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#addProtocolPersonUnit(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public void addProtocolPersonUnit(List<ProtocolUnit> protocolPersonUnits, ProtocolPerson protocolPerson, int selectedPersonIndex) {
        ProtocolUnit newProtocolPersonUnit = protocolPersonUnits.get(selectedPersonIndex);
        newProtocolPersonUnit.setProtocolNumber(protocolPerson.getProtocolNumber());
        newProtocolPersonUnit.setProtocolPersonId(protocolPerson.getProtocolPersonId());
        newProtocolPersonUnit.setPersonId(protocolPerson.getPersonId());

        newProtocolPersonUnit.refreshReferenceObject(REFERENCE_UNIT);
        protocolPerson.addProtocolUnit(newProtocolPersonUnit);
        if (newProtocolPersonUnit.getLeadUnitFlag()) {
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
//        selectedPerson.getProtocolUnits().remove(protocolUnit);
        selectedPerson.getProtocolUnits().remove(lineNumber);
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
    protected int getPIOrCoIChanged(List<ProtocolPerson> protocolPersons) {
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
    protected void updatePersonRole(ProtocolPerson protocolPerson, String targetRole) {
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
    protected boolean isRoleChangedToNewRole(ProtocolPerson protocolPerson, String newRole) {
        return ((!StringUtils.equalsIgnoreCase(protocolPerson.getPreviousPersonRoleId(), protocolPerson.getProtocolPersonRoleId())) 
                && StringUtils.equalsIgnoreCase(protocolPerson.getProtocolPersonRoleId(), newRole));
    }
    
    /**
     * This method is to get person holding Investigator role or Co-Investigator role
     * based on role parameter
     * @param protocolPersons
     * @param role
     * @return ProtocolPerson - Investigator or Co-Investigator
     */
    protected ProtocolPerson getPreviousInvestigator(List<ProtocolPerson> protocolPersons, String role) {
        for(ProtocolPerson protocolPerson : protocolPersons) {
            if((StringUtils.equalsIgnoreCase(protocolPerson.getPreviousPersonRoleId(), protocolPerson.getProtocolPersonRoleId())) 
                    && StringUtils.equalsIgnoreCase(protocolPerson.getProtocolPersonRoleId(), role)) {
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
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#getProtocolPersonRole(java.lang.String)
     */
    public ProtocolPersonRole getProtocolPersonRole(String sourceRoleId) {
        return getBusinessObjectService().findBySinglePrimaryKey(ProtocolPersonRole.class, sourceRoleId);
    }

    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#syncProtocolPersonRoleChanges(java.util.List)
     */
    public void syncProtocolPersonRoleChanges(List<ProtocolPerson> protocolPersons) {
        for (ProtocolPerson protocolPerson : protocolPersons) {
            if (!isUnitDetailsRequired(protocolPerson)) {
                protocolPerson.getProtocolUnits().removeAll(protocolPerson.getProtocolUnits());
            } else {
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
    protected void setLeadUnit(ProtocolPerson protocolPerson) {
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
    protected boolean isUnitDetailsRequired(ProtocolPerson protocolPerson) {
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
    protected boolean isAffiliationDetailsRequired(ProtocolPerson protocolPerson) {
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
    protected void setLeadUnitFlag(ProtocolPerson protocolPerson) {
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
    
    public boolean isPISameAsCoI(ProtocolPerson pi, ProtocolPerson newProtocolPerson) {
        boolean duplicatePerson = false;
        // if existing PI changed to new role and the new person assigned as pi, then pi is null
        if(pi != null && newProtocolPerson != null && StringUtils.isNotEmpty(pi.getPersonKey()) && StringUtils.isNotEmpty(newProtocolPerson.getPersonKey())) {
            if(newProtocolPerson.getPersonKey().equalsIgnoreCase(pi.getPersonKey()) && isCoInvestigator(newProtocolPerson)) { 
                duplicatePerson = true;
            }
        }
        return duplicatePerson; 
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
    protected boolean isCoInvestigator(ProtocolPerson protocolPerson) {
        boolean isCoI = false;
        if(protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(getCoInvestigatorRole())) {
            isCoI = true;
        }
        return isCoI;
        
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#setPrincipalInvestigator(org.kuali.kra.irb.personnel.ProtocolPerson, 
     *                                                                                    org.kuali.kra.irb.Protocol)
     */
    public void setPrincipalInvestigator(ProtocolPerson newPrincipalInvestigator, Protocol protocol) {
        if (protocol != null) {
            ProtocolPerson currentPrincipalInvestigator = getPrincipalInvestigator(protocol.getProtocolPersons());
            
            if (newPrincipalInvestigator != null) {
                newPrincipalInvestigator.setProtocolPersonRoleId(getPrincipalInvestigatorRole());
                if (currentPrincipalInvestigator == null) {
                    protocol.getProtocolPersons().add(newPrincipalInvestigator);
                } else if (!isDuplicatePerson(protocol.getProtocolPersons(), newPrincipalInvestigator)) {
                    protocol.getProtocolPersons().remove(currentPrincipalInvestigator);
                    protocol.getProtocolPersons().add(newPrincipalInvestigator);
                }
                
                // Assign the PI the AGGREGATOR role if PI has a personId.
                if (newPrincipalInvestigator.getPersonId() != null) {
                    personEditableService.populateContactFieldsFromPersonId(newPrincipalInvestigator);
                    KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
                    kraAuthService.addRole(newPrincipalInvestigator.getPersonId(), RoleConstants.PROTOCOL_AGGREGATOR, protocol);
                    kraAuthService.addRole(newPrincipalInvestigator.getPersonId(), RoleConstants.PROTOCOL_APPROVER, protocol);
                } else {
                    personEditableService.populateContactFieldsFromRolodexId(newPrincipalInvestigator);
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#getLeadUnit(org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public ProtocolUnit getLeadUnit(ProtocolPerson principalInvestigator) {
        ProtocolUnit leadUnit = null;
        if (principalInvestigator != null) {
            for (ProtocolUnit unit : principalInvestigator.getProtocolUnits()) {
                if (unit.getLeadUnitFlag()) {
                    leadUnit = unit;
                    break;
                }
            }
        }
        return leadUnit;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.personnel.ProtocolPersonnelService#setLeadUnit(org.kuali.kra.irb.personnel.ProtocolUnit, 
     *                                                                       org.kuali.kra.irb.personnel.ProtocolPerson, org.kuali.kra.irb.Protocol)
     */
    public void setLeadUnit(ProtocolUnit newLeadUnit, ProtocolPerson principalInvestigator, Protocol protocol) {
        if (principalInvestigator != null) {
            ProtocolUnit currentLeadUnit = getLeadUnit(principalInvestigator);
            
            if (newLeadUnit != null && protocol != null) {
                newLeadUnit.setPersonId(principalInvestigator.getPersonId());
                newLeadUnit.setProtocolNumber(protocol.getProtocolNumber());
                newLeadUnit.refreshReferenceObject("unit");
                if (currentLeadUnit == null) {
                    principalInvestigator.getProtocolUnits().add(newLeadUnit);
                } else if (!isDuplicateUnit(principalInvestigator, newLeadUnit)) {
                    principalInvestigator.getProtocolUnits().remove(currentLeadUnit);
                    principalInvestigator.getProtocolUnits().add(newLeadUnit);
                }
            }
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
    protected boolean isAffiliationStudentInvestigatorOrFacultySupervisor(ProtocolPerson protocolPerson) {
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
    protected void updateAffiliationCount(ProtocolPerson protocolPerson, HashMap<Integer, Integer> investigatorAffiliation) {
        Integer totalCountForAffiliation = 0;
        totalCountForAffiliation = investigatorAffiliation.get(protocolPerson.getAffiliationTypeCode());
        if(totalCountForAffiliation == null) {
            investigatorAffiliation.put(protocolPerson.getAffiliationTypeCode(), 1);
        }else {
            investigatorAffiliation.remove(protocolPerson.getAffiliationTypeCode());
            investigatorAffiliation.put(protocolPerson.getAffiliationTypeCode(), totalCountForAffiliation++);
        }
    }
    
    public List<Integer>getAffiliationStudentMap(List<ProtocolPerson> protocolPersons) {
        List<Integer> results = new ArrayList<Integer>();
        for(int i=0; i<protocolPersons.size(); i++) {
            ProtocolPerson protocolPerson = protocolPersons.get(i);
            if (isAffiliationStudentInvestigatorOrFacultySupervisor(protocolPerson)) {
                results.add(new Integer(i));
            }
        }
        return results;
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
     * Gets the SequenceAccessorService attribute.
     * 
     * @return Returns the SequenceAccessorService.
     */
    public SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService;
    }

    /**
     * Sets the SequenceAccessorService attribute value.
     * 
     * @param sequenceAccessorService The SequenceAccessorService to set.
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
     /**
     * This method is to get principal investigator role
     * @return String - PI role
     */
    protected String getPrincipalInvestigatorRole() {
        return Constants.PRINCIPAL_INVESTIGATOR_ROLE;
    }

    /**
     * This method is to get co-investigator role
     * @return String - CO-Investigator role
     */
    protected String getCoInvestigatorRole() {
        return Constants.CO_INVESTIGATOR_ROLE;
    }

    /**
     * This method is to get student investigator affiliation type
     * @return Integer
     */
    protected Integer getStudentAffiliationType() {
        return Constants.AFFILIATION_STUDENT_INVESTIGATOR_TYPE;
    }

    /**
     * This method is to get faculty supervisor affiliation type
     * @return
     */
    protected Integer getFacultySupervisorAffiliationType() {
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

    public void setPersonEditableService(PersonEditableService personEditableService) {
        this.personEditableService = personEditableService;
    }

}
