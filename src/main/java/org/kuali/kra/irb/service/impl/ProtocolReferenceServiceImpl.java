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
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.bo.ProtocolReferenceType;
import org.kuali.kra.irb.service.ProtocolReferenceService;


public class ProtocolReferenceServiceImpl implements ProtocolReferenceService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolReferenceServiceImpl.class);
    
    private BusinessObjectService businessObjectService;
   

    /**
     * Set the Business Object Service.  Injected by the Spring Framework.
     * @param businessObjectService the business object service
     */            
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolReferenceService#addProtocolReference(org.kuali.kra.irb.document.ProtocolDocument, org.kuali.kra.irb.bo.ProtocolReference)
     */
    public void addProtocolReference(Protocol protocol, ProtocolReference protocolReference) {
        
        Map<String, Integer> keyMap = new HashMap<String, Integer>();
        keyMap.put("protocolReferenceTypeCode", protocolReference.getProtocolReferenceTypeCode());        
        ProtocolReferenceType potocolReferenceType = (ProtocolReferenceType) businessObjectService.findByPrimaryKey(ProtocolReferenceType.class, keyMap);        
        protocolReference.setProtocolReferenceType(potocolReferenceType);
        
        //TODO Framework problem of 2 saves, protocolNumber & SequenceNumber are not null fields and they are only available after one saves new protocol.
        protocolReference.setProtocolNumber("0");
        protocolReference.setSequenceNumber(0);
        
        protocol.getProtocolReferences().add(protocolReference);
    }

}
