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
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class ProtocolPersonnelServiceImplBase implements ProtocolPersonnelService {
    
    private BusinessObjectService businessObjectService;
    private SequenceAccessorService sequenceAccessorService;
    private ProtocolPersonTrainingService protocolPersonTrainingService;
    private KcPersonService kcPersonService;
    private UnitService unitService;
    private ParameterService parameterService;
    
    protected PersonEditableService personEditableService;
    

    private static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";
    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String REFERENCE_UNIT = "unit";
    private static final String PROTOCOL_ATTACHMENT_TYPE = "type";
    protected static final String ASSIGN_PRINCIPAL_INVESTIGATOR_TO_WORKFLOW = "ASSIGN_PRINCIPAL_INVESTIGATOR_TO_WORKFLOW";
    
    private static final boolean LEAD_UNIT_FLAG_ON = true;
    private static final int PI_CHANGED = 0;
    private static final int COI_CHANGED = 1;
    private static final int ROLE_UNCHANGED = -1;
    private static final int RESET_SELECTED_UNIT_FOR_PERSON = 0;
    
    
    /**
     * Sets the protocolPersonTrainingService attribute value.
     * 
     * @param protocolPersonTrainingService The protocolPersonTrainingService to set.
     */
    public void setProtocolPersonTrainingService(ProtocolPersonTrainingService protocolPersonTrainingService) {
        this.protocolPersonTrainingService = protocolPersonTrainingService;
    }


    @Override
    public void addProtocolPerson(ProtocolBase protocol, ProtocolPersonBase protocolPerson) {

        Integer nextPersonId = getSequenceAccessorService().getNextAvailableSequenceNumber(getSequenceNumberNameHook(), protocolPerson.getClass()).intValue();
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
        populateUnitFromPrimaryDepartmentCode(protocol, protocolPerson);
        protocol.getProtocolPersons().add(protocolPerson);
    }
    
    /**
     * 
     * This method provides the name of the sequence number
     * @return the sequence name
     */
    protected abstract String getSequenceNumberNameHook();


    @Override
    public void deleteProtocolPerson(ProtocolBase protocol) {
        List<ProtocolPersonBase> deletedPersons = new ArrayList<ProtocolPersonBase>();
        for(ProtocolPersonBase protocolPerson : protocol.getProtocolPersons()) {
            if(protocolPerson.isDelete()) {
                deletedPersons.add(protocolPerson);
            }
        }
        protocol.getProtocolPersons().removeAll(deletedPersons);
    }
    
    @Override
    public void addProtocolPersonAttachment(ProtocolBase protocol, ProtocolAttachmentPersonnelBase newAttachment, int selectedPersonIndex) {

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
            for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
                for (ProtocolAttachmentPersonnelBase attachment : person.getAttachmentPersonnels()) {
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
    
    @Override
    public void addProtocolPersonUnit(List<ProtocolUnitBase> protocolPersonUnits, ProtocolPersonBase protocolPerson, int selectedPersonIndex) {
        ProtocolUnitBase newProtocolPersonUnit = protocolPersonUnits.get(selectedPersonIndex);
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
        protocolPersonUnits.add(selectedPersonIndex, createNewProtocolUnitInstanceHook());
    }
    
    protected abstract ProtocolUnitBase createNewProtocolUnitInstanceHook();
    protected abstract boolean isDuplicatePersonAllowed();


    @Override
    public void deleteProtocolPersonUnit(ProtocolBase protocol, int selectedPersonIndex, int lineNumber) {
        ProtocolPersonBase selectedPerson =  protocol.getProtocolPerson(selectedPersonIndex);
        ProtocolUnitBase protocolUnit = selectedPerson.getProtocolUnit(lineNumber);
        if(protocolUnit.getLeadUnitFlag()) {
            selectedPerson.setSelectedUnit(RESET_SELECTED_UNIT_FOR_PERSON);
        }
        selectedPerson.getProtocolUnits().remove(lineNumber);
    }
    
    @Override
    public void switchInvestigatorCoInvestigatorRole(List<ProtocolPersonBase> protocolPersons) {
        ProtocolPersonBase investigator = null;
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
    protected int getPIOrCoIChanged(List<ProtocolPersonBase> protocolPersons) {
        int roleChanged = ROLE_UNCHANGED;
        for(ProtocolPersonBase protocolPerson : protocolPersons) {
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
    protected void updatePersonRole(ProtocolPersonBase protocolPerson, String targetRole) {
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
    protected boolean isRoleChangedToNewRole(ProtocolPersonBase protocolPerson, String newRole) {
        return ((!StringUtils.equalsIgnoreCase(protocolPerson.getPreviousPersonRoleId(), protocolPerson.getProtocolPersonRoleId())) 
                && StringUtils.equalsIgnoreCase(protocolPerson.getProtocolPersonRoleId(), newRole));
    }
    
    /**
     * This method is to get person holding Investigator role or Co-Investigator role
     * based on role parameter
     * @param protocolPersons
     * @param role
     * @return ProtocolPersonBase - Investigator or Co-Investigator
     */
    protected ProtocolPersonBase getPreviousInvestigator(List<ProtocolPersonBase> protocolPersons, String role) {
        for(ProtocolPersonBase protocolPerson : protocolPersons) {
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
     * @return Collection&lt;PersonTraining&gt;
     */
    public List<ProtocolPersonRoleMappingBase> getPersonRoleMapping(String sourceRoleId) {
        List<ProtocolPersonRoleMappingBase> personRoleMappings = new ArrayList<ProtocolPersonRoleMappingBase>();
        Map<String, Object> matchingKeys = new HashMap<String, Object>();
        matchingKeys.put("sourceRoleId", sourceRoleId);
        personRoleMappings.addAll(getBusinessObjectService().findMatching(getProtocolPersonRoleMappingClassHook(), matchingKeys));
        return personRoleMappings;
    }
    
    /**
     * 
     * This method allows for the correct version fo the protocol person role mapping class to be returned (IRB or IACUC)
     * @return the class
     */
    public abstract Class<? extends ProtocolPersonRoleMappingBase> getProtocolPersonRoleMappingClassHook();
    
    @Override
    public ProtocolPersonRoleBase getProtocolPersonRole(String sourceRoleId) {
        return getBusinessObjectService().findBySinglePrimaryKey(getProtocolPersonRoleClassHook(), sourceRoleId);
    }
    
    /**
     * 
     * This method returns the proper protocol person role class for the instance (IRB or IACUC)
     * @return the protocol person role class
     */
    public abstract Class<? extends ProtocolPersonRoleBase> getProtocolPersonRoleClassHook();

    @Override
    public void syncProtocolPersonRoleChanges(List<ProtocolPersonBase> protocolPersons) {
        for (ProtocolPersonBase protocolPerson : protocolPersons) {
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
    protected void setLeadUnit(ProtocolPersonBase protocolPerson) {
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
    protected boolean isUnitDetailsRequired(ProtocolPersonBase protocolPerson) {
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
    protected boolean isAffiliationDetailsRequired(ProtocolPersonBase protocolPerson) {
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
    protected void setLeadUnitFlag(ProtocolPersonBase protocolPerson) {
        protocolPerson.getProtocolUnit(protocolPerson.getSelectedUnit()).setLeadUnitFlag(LEAD_UNIT_FLAG_ON);
    }
    
    @Override
    public void selectProtocolUnit(List<ProtocolPersonBase> protocolPersons) {
        for(ProtocolPersonBase protocolPerson : protocolPersons) {
            int selectedUnit = 0;
            for(ProtocolUnitBase protocolUnit : protocolPerson.getProtocolUnits()) {
                if(protocolUnit.getLeadUnitFlag()) {
                    protocolPerson.setSelectedUnit(selectedUnit);
                    break;
                }
                selectedUnit++;
            }
        }
    }

    
    @Override
    public boolean isDuplicateUnit(ProtocolPersonBase protocolPerson, ProtocolUnitBase newProtocolUnit) {
        boolean duplicateUnit = false;
        for(ProtocolUnitBase protocolUnit : protocolPerson.getProtocolUnits()) {
            if(protocolUnit.getUnitNumber().equalsIgnoreCase(newProtocolUnit.getUnitNumber())) {
                duplicateUnit = true;
                break;
            }
        }
        return duplicateUnit;
    }
    
    
    public boolean isPISameAsCoI(ProtocolPersonBase pi, ProtocolPersonBase newProtocolPerson) {
        boolean duplicatePerson = false;
        // if existing PI changed to new role and the new person assigned as pi, then pi is null
        if(pi != null && newProtocolPerson != null && StringUtils.isNotEmpty(pi.getPersonKey()) && StringUtils.isNotEmpty(newProtocolPerson.getPersonKey())) {
            if(newProtocolPerson.getPersonKey().equalsIgnoreCase(pi.getPersonKey()) && isCoInvestigator(newProtocolPerson)) { 
                duplicatePerson = true;
            }
        }
        return duplicatePerson; 
    }
    
    
    
    @Override
    public boolean isDuplicatePerson(List<ProtocolPersonBase> protocolPersons, ProtocolPersonBase newProtocolPerson) {
        boolean duplicatePerson = false;
        if(!isDuplicatePersonAllowed()) {
            for(ProtocolPersonBase protocolPerson : protocolPersons) {
                if(protocolPerson.getPersonUniqueKey().equalsIgnoreCase(newProtocolPerson.getPersonUniqueKey())) {
                    duplicatePerson = true;
                }
            }
        }
        return duplicatePerson;
    }

    
    
    @Override
    public ProtocolPersonBase getPrincipalInvestigator(List<ProtocolPersonBase> protocolPersons) {
        ProtocolPersonBase principalInvestigator = null;
        for(ProtocolPersonBase protocolPerson : protocolPersons) {
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
    public boolean isPrincipalInvestigator(ProtocolPersonBase protocolPerson) {
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
    protected boolean isCoInvestigator(ProtocolPersonBase protocolPerson) {
        boolean isCoI = false;
        if(protocolPerson.getProtocolPersonRoleId().equalsIgnoreCase(getCoInvestigatorRole())) {
            isCoI = true;
        }
        return isCoI;
        
    }
    
    @Override
    public ProtocolUnitBase getLeadUnit(ProtocolPersonBase principalInvestigator) {
        ProtocolUnitBase leadUnit = null;
        if (principalInvestigator != null) {
            for (ProtocolUnitBase unit : principalInvestigator.getProtocolUnits()) {
                if (unit.getLeadUnitFlag()) {
                    leadUnit = unit;
                    break;
                }
            }
        }
        return leadUnit;
    }

 
    @Override
    public void setLeadUnit(ProtocolUnitBase newLeadUnit, ProtocolPersonBase principalInvestigator, ProtocolBase protocol) {
        if (principalInvestigator != null) {
            ProtocolUnitBase currentLeadUnit = getLeadUnit(principalInvestigator);
            
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
    
    
    
    
    @Override
    public void syncPersonRoleAndUnit(ProtocolPersonBase protocolPerson) {
        if(!isUnitDetailsRequired(protocolPerson)) {
            protocolPerson.getProtocolUnits().removeAll(protocolPerson.getProtocolUnits());
        }
    }

    @Override
    public void syncPersonRoleAndAffiliation(ProtocolPersonBase protocolPerson) {
        if(!isAffiliationDetailsRequired(protocolPerson)) {
            protocolPerson.setAffiliationTypeCode(null);
        }
    }
    
    @Override
    public boolean isValidStudentFacultyMatch(List<ProtocolPersonBase> protocolPersons) {
        boolean validInvestigator = true;
        HashMap<Integer, Integer> investigatorAffiliation = new HashMap<Integer, Integer>();
        for(ProtocolPersonBase protocolPerson : protocolPersons) {
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
    protected boolean isAffiliationStudentInvestigatorOrFacultySupervisor(ProtocolPersonBase protocolPerson) {
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
    protected void updateAffiliationCount(ProtocolPersonBase protocolPerson, HashMap<Integer, Integer> investigatorAffiliation) {
        Integer totalCountForAffiliation = 0;
        totalCountForAffiliation = investigatorAffiliation.get(protocolPerson.getAffiliationTypeCode());
        if(totalCountForAffiliation == null) {
            investigatorAffiliation.put(protocolPerson.getAffiliationTypeCode(), 1);
        }else {
            investigatorAffiliation.remove(protocolPerson.getAffiliationTypeCode());
            investigatorAffiliation.put(protocolPerson.getAffiliationTypeCode(), totalCountForAffiliation++);
        }
    }
    
    public List<Integer>getAffiliationStudentMap(List<ProtocolPersonBase> protocolPersons) {
        List<Integer> results = new ArrayList<Integer>();
        for(int i=0; i<protocolPersons.size(); i++) {
            ProtocolPersonBase protocolPerson = protocolPersons.get(i);
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


    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }


    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public UnitService getUnitService() {
        return unitService;
    }


    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }


    protected void populateUnitFromPrimaryDepartmentCode(ProtocolBase protocol, ProtocolPersonBase protocolPerson) {
        if (protocolPerson.getPersonId() != null) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(protocolPerson.getPersonId());
            String unitNumber = person.getOrganizationIdentifier();
            if (unitNumber != null) {
                Unit unit = getUnitService().getUnit(unitNumber);
                
                if (unit != null) {
                    ProtocolUnitBase newProtocolPersonUnit = createNewProtocolUnitInstanceHook();
                    newProtocolPersonUnit.setUnit(unit);
                    newProtocolPersonUnit.setUnitName(unit.getUnitName());
                    newProtocolPersonUnit.setUnitNumber(unit.getUnitNumber());
                    newProtocolPersonUnit.setProtocolNumber(protocolPerson.getProtocolNumber());
                    newProtocolPersonUnit.setProtocolPersonId(protocolPerson.getProtocolPersonId());
                    newProtocolPersonUnit.setPersonId(protocolPerson.getPersonId());
                    newProtocolPersonUnit.setLeadUnitFlag(true);
        
                    protocolPerson.addProtocolUnit(newProtocolPersonUnit);
                    if (newProtocolPersonUnit.getLeadUnitFlag()) {
                        protocolPerson.setSelectedUnit(protocolPerson.getProtocolUnits().size() - 1);
                        setLeadUnit(protocolPerson);
                    }
                }
               
            }
        }

    }


	public ParameterService getParameterService() {
		return parameterService;
	}


	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
