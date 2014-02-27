/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.personnel;

import java.util.List;




/**
 * This class represents service interface for protocol personnel
 * training. Each institution can customize this interface to deal
 * with person training.
 */
public interface ProtocolPersonTrainingService {

    /**
     * This method is to set trained flag for each person
     * This method is invoked while navigating to personnel page to set the trained flag for 
     * each person in the list.
     * Hook this service method to external / other training session as required. 
     * @param protocolPersons
     */
    public void updatePersonTrained(List<ProtocolPersonBase> protocolPersons);
    
    /**
     * This method is to set trained flag for a person
     * Set true / false
     * This method is invoked from isPersonTrained and while adding a new person to the list.
     * @param protocolPerson
     */
    public void setTrainedFlag(ProtocolPersonBase protocolPerson);
}
