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

import java.util.HashMap;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.irb.bo.ParticipantType;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.service.ProtocolParticipantService;

public class ProtocolParticipantServiceImpl implements ProtocolParticipantService {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolParticipantServiceImpl.class);
    
    private BusinessObjectService businessObjectService;

    /**
     * Set the Business Object Service.  Injected by the Spring Framework.
     * @param businessObjectService the business object service
     */            
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolParticipantService#addProtocolParticipant(org.kuali.kra.irb.document.ProtocolDocument, org.kuali.kra.irb.bo.ProtocolParticipant)
     */
    public void addProtocolParticipant(Protocol protocol, ProtocolParticipant protocolParticipant) {
        Map keyMap = new HashMap();
        keyMap.put("participantTypeCode", protocolParticipant.getParticipantTypeCode());
        
        ParticipantType participantType = (ParticipantType) businessObjectService.findByPrimaryKey(ParticipantType.class, keyMap);
        
        protocolParticipant.setParticipantType(participantType);
        
        //TODO Framework problem of 2 saves
        protocolParticipant.setProtocolNumber("0");
        protocolParticipant.setSequenceNumber(0);
        
        protocol.getProtocolParticipants().add(protocolParticipant);
        
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolParticipantService#deleteProtocolParticipant(org.kuali.kra.irb.document.ProtocolDocument, java.lang.Integer)
     */
    public void deleteProtocolParticipant(Protocol protocol, int lineNumber) {
        protocol.getProtocolParticipants().remove(lineNumber);
    }

}
