/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolVersionServiceImpl;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kns.service.SessionDocumentService;
import org.kuali.rice.krad.service.BusinessObjectService;


/**
 * Protocol Version Service Implementation.
 */
public class IacucProtocolVersionServiceImpl extends ProtocolVersionServiceImpl {
    
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    
    protected String getProtocolDocumentTypeHook() {
        return "IacucProtocolDocument";
    }
    
    protected Protocol getNewProtocol(Protocol protocol) throws Exception {
        IacucProtocol iacucProtocol = (IacucProtocol)protocol;
        return versioningService.createNewVersion(iacucProtocol);
    }

    /**
     * @see org.kuali.kra.protocol.ProtocolVersionService#getProtocolVersion(java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public IacucProtocol getProtocolVersion(String protocolNumber, Integer sequenceNumber) {
        IacucProtocol protocol = null;
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("protocolNumber", protocolNumber);
        fields.put("sequenceNumber", sequenceNumber);
        Collection<IacucProtocol> protocols = businessObjectService.findMatching(IacucProtocol.class, fields);
        if (protocols.size() == 1) {
            protocol = protocols.iterator().next();
        }
        return protocol;
    }

}
