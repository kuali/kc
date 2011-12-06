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

import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;


public interface ProtocolPersonnelService {

    /**
     * This method adds ProtocolPerson to the List of ProtocolPersons.
     * @param protocol which contains list of ProtocolPersons.
     * @param ProtocolPerson object is added to ProtocolPersons list.
     */
    public abstract void addProtocolPerson(Protocol protocol, ProtocolPerson protocolPerson);
    
    /**
     * This method deletes ProtocolPerson(s) - those marked as delete
     * @param protocol which contains list of ProtocolPersons
     */
    public abstract void deleteProtocolPerson(Protocol protocol);
    
    /**
     * This method add ProtocolPersonAttachment to a Person of the Protocol.
     * @param protocol - the protocol 
     * @param newAttachment - the new attachment to be added
     * @param selectedPersonIndex - the attachment is added to the specific person in the list
     */
    public abstract void addProtocolPersonAttachment(Protocol protocol, ProtocolAttachmentPersonnel newAttachment, int selectedPersonIndex);
    
    /**
     * This method adds ProtocolUnit to the List of selected ProtocolPerson.
     * @param protocolPersonUnits - New list of protocol units for each person
     * @param protocolPerson - Selected protocol person from the list
     * @param selectedPersonIndex - Unit is added to specific person in the list
     */
    public abstract void addProtocolPersonUnit(List<ProtocolUnit> protocolPersonUnits, ProtocolPerson protocolPerson, int selectedPersonIndex);
    

    /**
     * This method will delete ProtocolUnit from the List of protocol person units at specified position(selectedPersonIndex)
     * @param protocolPersonUnits - New list of protocol units for each person
     * @param selectedPersonIndex - Unit is removed from specific person in the list
     * @param lineNumber - deleted line number
     */
    public abstract void deleteProtocolPersonUnit(Protocol protocol, int selectedPersonIndex, int lineNumber);

    /**
     * This method is used to update selected protocol lead unit in the list.
     * Each Protocol Person has index of selected lead unit
     * UI display is handled through selectedUnit index to group lead unit radio.
     * Update selected unit in the list based on selectedUnit indicator in each protocolPerson
     * Also update affiliation type which is linked to person role.
     * @param protocolPersons
     */
    public void syncProtocolPersonRoleChanges(List<ProtocolPerson> protocolPersons);

    /**
     * This method is to select protocol lead unit for each person.
     * UI display is handled through selectedUnit index to group lead unit radio.
     * So we need to set this indicator for each person once we fetch all protocolPersons.
     * @param protocolPersons
     */
    public void selectProtocolUnit(List<ProtocolPerson> protocolPersons);
    
    /**
     * This method is to check if Principal Investigator already exists.
     * Only one Principal Investigator may be assigned to each protocol
     * @param protocolPersons
     * @return true / false
     */
    public boolean isPIExists(List<ProtocolPerson> protocolPersons);
    
    /**
     * This method is to check for duplicate person.
     * Same person can be listed in multiple roles. Validate with person and role
     * @param protocolPersons
     * @param newProtocolPerson
     * @return true / false
     */
    public boolean isDuplicatePerson(List<ProtocolPerson> protocolPersons, ProtocolPerson newProtocolPerson);

    /**
     * This method is to get principal investigator person
     * This method also helps to check whether at least one investigator exists in person list
     * Return first found investigator so that we can check for duplicate if any
     * @param protocolPersons
     * @return null if no investigator else ProtocolPerson as investigator
     */
    public ProtocolPerson getPrincipalInvestigator(List<ProtocolPerson> protocolPersons);
    
    /**
     * This method is to check whether a person has role of Principal Investigator
     * @param protocolPerson
     * @return
     */
    public boolean isPrincipalInvestigator(ProtocolPerson protocolPerson);
    
    /**
     * This method sets the principal investigator.
     * @param newPrincipalInvestigator The new instance of the principal investigator
     * @param protocol The protocol that will contain the investigator
     */
    public void setPrincipalInvestigator(ProtocolPerson newPrincipalInvestigator, Protocol protocol);

    /**
     * This method gets the lead unit of the principal investigator.
     * @param principalInvestigator The principal investigator to search
     * @return The lead unit associated with the principal investigator
     */
    public ProtocolUnit getLeadUnit(ProtocolPerson principalInvestigator);
    
    /**
     * This method sets the lead unit of the principal investigator.
     * @param newLeadUnit The new instance of the lead unit
     * @param principalInvestigator The principal investigator to set
     * @param protocol The protocol that contains the principal investigator
     */
    public void setLeadUnit(ProtocolUnit newLeadUnit, ProtocolPerson principalInvestigator, Protocol protocol);
    
    /**
     * This method is to see if there is any change in the protocol person role.
     * swapping Investigator and Co-Investigator role is permitted.
     * If Co-investigator is made a PI then the previous PI automatically is made a Co-investigator
     * and vice versa
     * @param protocolPersons
     */
    public void switchInvestigatorCoInvestigatorRole(List<ProtocolPerson> protocolPersons);
    
    /**
     * This method is to get valid target person roles for a given source role.
     * A role can be changed only to specific other roles defined.
     * @param sourceRoleId
     * @return
     */
    public List<ProtocolPersonRoleMapping> getPersonRoleMapping(String sourceRoleId);
    
    /**
     * This method returns the protocolPersonRole based on the sourceRoleId.
     * @param sourceRoleId
     * @return
     */
    public ProtocolPersonRole getProtocolPersonRole(String sourceRoleId);


    /**
     * This method is to check whether new unit already exists in the list
     * for a person
     * @param protocolPerson
     * @param newProtocolUnit
     * @return true / false
     */
    public boolean isDuplicateUnit(ProtocolPerson protocolPerson, ProtocolUnit newProtocolUnit);
    
    /**
     * This method is to update person unit based on change in person role.
     * @param protocolPerson
     */
    public void syncPersonRoleAndUnit(ProtocolPerson protocolPerson);
    
    /**
     * This method is to update person affiliation type based on change in person role.
     * @param protocolPerson
     */
    public void syncPersonRoleAndAffiliation(ProtocolPerson protocolPerson);
    
    /**
     * This method is to check whether Student Investigator and Faculty Supervisor combination is valid.
     * If one PI or Co-Investigator has Student Investigator selected as Affiliation type then another PI or 
     * Co-Investigator must have Faculty Supervisor check.  
     * There may be multiple student investigators and faculty supervisors. 
     * This check is done prior to submission 
     * @param protocolPersons
     * @return
     */
    public boolean isValidStudentFacultyMatch(List<ProtocolPerson> protocolPersons);
    
    /** This method is to check if the person playing the PI role is also being added as a CO-I 
     * @param protocolPersons
     * @param newProtocolPerson
     * @return 
     */
    public boolean isPISameAsCoI(ProtocolPerson piProtocolPerson, ProtocolPerson newProtocolPerson);

    /**
     * This method returns a list of affiliated students from a list of personnel.
     * @param protocolPersons
     * @return affiliatedStudents
     */
    public List<Integer>getAffiliationStudentMap(List<ProtocolPerson> protocolPersons);
}
