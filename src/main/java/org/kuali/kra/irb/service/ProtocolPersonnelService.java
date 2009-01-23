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
package org.kuali.kra.irb.service;

import java.util.List;

import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolUnit;


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
     * @param protocolPerson - Selected protocol person from the list
     * @param selectedPersonIndex - Unit is removed from specific person in the list
     * @param lineNumber - deleted line number
     */
    public abstract void deleteProtocolPersonUnit(Protocol protocol, ProtocolPerson protocolPerson, int selectedPersonIndex, int lineNumber);

}