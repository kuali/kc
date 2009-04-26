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
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.service.ProtocolProtocolService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolProtocolServiceImpl implements ProtocolProtocolService{

    BusinessObjectService businessObjectService;
    
    /**
     * 
     * @see org.kuali.kra.irb.service.ProtocolProtocolService#loadProtocolForEdit(org.kuali.kra.irb.document.ProtocolDocument, java.lang.String)
     */
    public void loadProtocolForEdit(ProtocolDocument document, String protocolNumber) {
        Map FieldMap = new HashMap();
        FieldMap.put(Constants.PROPERTY_PROTOCOL_NUMBER,protocolNumber );
        List protocols = (List)businessObjectService.findMatching(Protocol.class, FieldMap);
        document.setProtocol((Protocol)protocols.get(0));
        
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
