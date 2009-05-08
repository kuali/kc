/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.test.mocks;

import java.util.List;

import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonTrainingService;

/**
 * A Mock for the ProtocolPersonTrainingService.
 */
public class MockProtocolPersonTrainingService implements ProtocolPersonTrainingService {
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonTrainingService#updatePersonTrained(java.util.List)
     */
    public void updatePersonTrained(List<ProtocolPerson> protocolPersons) {
        for(ProtocolPerson protocolPerson : protocolPersons) {
            setTrainedFlag(protocolPerson);
        }
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonTrainingService#setTrainedFlag(org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public void setTrainedFlag(ProtocolPerson protocolPerson) {
        protocolPerson.setTrained(true);
    }

}
