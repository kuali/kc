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

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;

import java.util.List;


public interface ProtocolPersonnelService {
 
    /**
     * This method adds ProtocolPersonBase to the List of ProtocolPersons.
     * @param protocol which contains list of ProtocolPersons.
     * @param ProtocolPersonBase object is added to ProtocolPersons list.
     */
    public abstract void addProtocolPerson(ProtocolBase protocol, ProtocolPersonBase protocolPerson);
    
    /**
     * This method deletes ProtocolPersonBase(s) - those marked as delete
     * @param protocol which contains list of ProtocolPersons
     */
    public abstract void deleteProtocolPerson(ProtocolBase protocol);
    
    /**
     * This method add ProtocolPersonAttachment to a Person of the ProtocolBase.
     * @param protocol - the protocol 
     * @param newAttachment - the new attachment to be added
     * @param selectedPersonIndex - the attachment is added to the specific person in the list
     */
    public abstract void addProtocolPersonAttachment(ProtocolBase protocol, ProtocolAttachmentPersonnelBase newAttachment, int selectedPersonIndex);
    
    /**
     * This method adds ProtocolUnitBase to the List of selected ProtocolPersonBase.
     * @param protocolPersonUnits - New list of protocol units for each person
     * @param protocolPerson - Selected protocol person from the list
     * @param selectedPersonIndex - Unit is added to specific person in the list
     */
    public abstract void addProtocolPersonUnit(List<ProtocolUnitBase> protocolPersonUnits, ProtocolPersonBase protocolPerson, int selectedPersonIndex);
    

    /**
     * This method will delete ProtocolUnitBase from the List of protocol person units at specified position(selectedPersonIndex)
     * @param protocolPersonUnits - New list of protocol units for each person
     * @param selectedPersonIndex - Unit is removed from specific person in the list
     * @param lineNumber - deleted line number
     */
    public abstract void deleteProtocolPersonUnit(ProtocolBase protocol, int selectedPersonIndex, int lineNumber);

    /**
     * This method is used to update selected protocol lead unit in the list.
     * Each ProtocolBase Person has index of selected lead unit
     * UI display is handled through selectedUnit index to group lead unit radio.
     * Update selected unit in the list based on selectedUnit indicator in each protocolPerson
     * Also update affiliation type which is linked to person role.
     * @param protocolPersons
     */
    public void syncProtocolPersonRoleChanges(List<ProtocolPersonBase> protocolPersons);

    /**
     * This method is to select protocol lead unit for each person.
     * UI display is handled through selectedUnit index to group lead unit radio.
     * So we need to set this indicator for each person once we fetch all protocolPersons.
     * @param protocolPersons
     */
    public void selectProtocolUnit(List<ProtocolPersonBase> protocolPersons);
    
    
    /**
     * This method is to check for duplicate person.
     * Same person can be listed in multiple roles. Validate with person and role
     * @param protocolPersons
     * @param newProtocolPerson
     * @return true / false
     */
    public boolean isDuplicatePerson(List<ProtocolPersonBase> protocolPersons, ProtocolPersonBase newProtocolPerson);

 
    
    
    /**
     * This method is to get principal investigator person
     * This method also helps to check whether at least one investigator exists in person list
     * Return first found investigator so that we can check for duplicate if any
     * @param protocolPersons
     * @return null if no investigator else ProtocolPersonBase as investigator
     */
    public ProtocolPersonBase getPrincipalInvestigator(List<ProtocolPersonBase> protocolPersons);

    
         
    /**
     * This method is to check whether a person has role of Principal Investigator
     * @param protocolPerson
     * @return
     */
    public boolean isPrincipalInvestigator(ProtocolPersonBase protocolPerson);
        
      
    /**
     * This method sets the principal investigator.
     * @param newPrincipalInvestigator The new instance of the principal investigator
     * @param protocol The protocol that will contain the investigator
     */
    public void setPrincipalInvestigator(ProtocolPersonBase newPrincipalInvestigator, ProtocolBase protocol);

         
    /**
     * This method gets the lead unit of the principal investigator.
     * @param principalInvestigator The principal investigator to search
     * @return The lead unit associated with the principal investigator
     */
    public ProtocolUnitBase getLeadUnit(ProtocolPersonBase principalInvestigator); 
    
    
    /**
     * This method sets the lead unit of the principal investigator.
     * @param newLeadUnit The new instance of the lead unit
     * @param principalInvestigator The principal investigator to set
     * @param protocol The protocol that contains the principal investigator
     */
    public void setLeadUnit(ProtocolUnitBase newLeadUnit, ProtocolPersonBase principalInvestigator, ProtocolBase protocol);

         
    /**
     * This method is to see if there is any change in the protocol person role.
     * swapping Investigator and Co-Investigator role is permitted.
     * If Co-investigator is made a PI then the previous PI automatically is made a Co-investigator
     * and vice versa
     * @param protocolPersons
     */
    public void switchInvestigatorCoInvestigatorRole(List<ProtocolPersonBase> protocolPersons);
    
    /**
     * This method is to get valid target person roles for a given source role.
     * A role can be changed only to specific other roles defined.
     * @param sourceRoleId
     * @return
     */
    public List<ProtocolPersonRoleMappingBase> getPersonRoleMapping(String sourceRoleId);
    
    /**
     * This method returns the protocolPersonRole based on the sourceRoleId.
     * @param sourceRoleId
     * @return
     */
    public ProtocolPersonRoleBase getProtocolPersonRole(String sourceRoleId);

    /**
     * This method is to check whether new unit already exists in the list
     * for a person
     * @param protocolPerson
     * @param newProtocolUnit
     * @return true / false
     */
    public boolean isDuplicateUnit(ProtocolPersonBase protocolPerson, ProtocolUnitBase newProtocolUnit);
    
    
    /**
     * This method is to update person unit based on change in person role.
     * @param protocolPerson
     */
    public void syncPersonRoleAndUnit(ProtocolPersonBase protocolPerson);
    
    /**
     * This method is to update person affiliation type based on change in person role.
     * @param protocolPerson
     */
    public void syncPersonRoleAndAffiliation(ProtocolPersonBase protocolPerson);
    
    /**
     * This method is to check whether Student Investigator and Faculty Supervisor combination is valid.
     * If one PI or Co-Investigator has Student Investigator selected as Affiliation type then another PI or 
     * Co-Investigator must have Faculty Supervisor check.  
     * There may be multiple student investigators and faculty supervisors. 
     * This check is done prior to submission 
     * @param protocolPersons
     * @return
     */
    public boolean isValidStudentFacultyMatch(List<ProtocolPersonBase> protocolPersons);
    
    /** This method is to check if the person playing the PI role is also being added as a CO-I 
     * @param protocolPersons
     * @param newProtocolPerson
     * @return 
     */
    public boolean isPISameAsCoI(ProtocolPersonBase piProtocolPerson, ProtocolPersonBase newProtocolPerson);

    /**
     * This method returns a list of affiliated students from a list of personnel.
     * @param protocolPersons
     * @return affiliatedStudents
     */
    public List<Integer>getAffiliationStudentMap(List<ProtocolPersonBase> protocolPersons);
    
    /**
     * @return whether the PI should be added to the workflow requests via IRB Approver role. Based on a system parameter
     */
    public boolean shouldPrincipalInvestigatorBeAddedToWorkflow();
    
}
