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
     * This method deletes ProtocolPerson from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolPersons
     * @param lineNumber to be deleted
     */
    public abstract void deleteProtocolPerson(Protocol protocol, int lineNumber);
    
    /**
     * This method adds ProtocolUnit to the List of selected ProtocolPerson.
     * @param protocol which contains list of ProtocolPersons.
     * @param protocolPerson - Selected protocol person from the list
     * @param ProtocolUnit object is added to selected ProtocolPerson.
     */
    public abstract void addProtocolPersonUnit(Protocol protocol, ProtocolPerson protocolPerson, ProtocolUnit newProtocolPersonUnit);
    

    /**
     * This method will clear ProtocolPerson address from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolPersons
     * @param lineNumber to clear location address
     */
    //public abstract void clearProtocolPerson(Protocol protocol, int lineNumber);

}