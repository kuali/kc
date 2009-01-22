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
package org.kuali.kra.irb.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.service.ProtocolPersonnelService;


public class ProtocolPersonnelServiceImpl implements ProtocolPersonnelService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolPersonnelServiceImpl.class);
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#addProtocolPerson(org.kuali.kra.irb.bo.Protocol, org.kuali.kra.irb.bo.ProtocolPerson)
     */
    public void addProtocolPerson(Protocol protocol, ProtocolPerson protocolPerson) {
        
        //TODO - How to handle protocol number and sequence number
        protocolPerson.setProtocolNumber("0");
        protocolPerson.setSequenceNumber(0);
        protocolPerson.setProtocolId(protocol.getProtocolId());
        //Refresh Person or Rolodex
        if(!StringUtils.isBlank(protocolPerson.getPersonId())) {
            protocolPerson.refreshReferenceObject("person");
        }else {
            protocolPerson.refreshReferenceObject("rolodex");
        }
        protocolPerson.refreshReferenceObject("protocolPersonRole");
        protocol.getProtocolPersons().add(protocolPerson);
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#deleteProtocolPerson(org.kuali.kra.irb.bo.Protocol, int)
     */
    public void deleteProtocolPerson(Protocol protocol, int lineNumber) {

        protocol.getProtocolPersons().remove(lineNumber);  

    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#addProtocolPersonUnit(org.kuali.kra.irb.bo.Protocol, org.kuali.kra.irb.bo.ProtocolPerson)
     */
    public void addProtocolPersonUnit(Protocol protocol, ProtocolPerson protocolPerson, ProtocolUnit newProtocolPersonUnit) {
        newProtocolPersonUnit.setProtocolNumber(protocolPerson.getProtocolNumber());
        newProtocolPersonUnit.setProtocolPersonId(protocolPerson.getProtocolPersonId());

        //TODO - How to handle protocol number and sequence number
        newProtocolPersonUnit.setProtocolNumber("0");
        newProtocolPersonUnit.setSequenceNumber(0);
        
        newProtocolPersonUnit.refreshReferenceObject("unit");
        protocolPerson.addProtocolUnit(newProtocolPersonUnit);
    }
    
}
