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
package org.kuali.kra.irb.protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class implements protocol service details.
 */
class ProtocolProtocolServiceImpl implements ProtocolProtocolService{

    BusinessObjectService businessObjectService;
    
    /**
     * 
     * @see org.kuali.kra.irb.protocol.ProtocolProtocolService#loadProtocolForEdit(org.kuali.kra.irb.ProtocolDocument, java.lang.String)
     */
    @SuppressWarnings("unchecked") 
    public void loadProtocolForEdit(ProtocolDocument document, String protocolNumber) {
        Map fieldMap = new HashMap();
        fieldMap.put(Constants.PROPERTY_PROTOCOL_NUMBER,protocolNumber);
        List<Protocol> protocols = (List<Protocol>) businessObjectService.findMatching(Protocol.class, fieldMap);
        document.setProtocol((Protocol) protocols.get(0));
        
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
