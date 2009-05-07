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

import java.util.List;

import org.kuali.kra.irb.Protocol;


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
     * This method sets/updates the principal investigator person
     * @param protocol
     * @param person
     */
    public void updatePrincipalInvestigator(Protocol protocol, ProtocolPerson person);

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

}