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


import org.kuali.kra.irb.Protocol;


class ProtocolReferenceServiceImpl implements ProtocolReferenceService {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolReferenceServiceImpl.class);
    
    /**
     * @see org.kuali.kra.irb.protocol.ProtocolReferenceService#addProtocolReference(org.kuali.kra.irb.document.ProtocolDocument, org.kuali.kra.irb.protocol.ProtocolReference)
     */
    public void addProtocolReference(Protocol protocol, ProtocolReference protocolReference) {
        
        protocolReference.refreshReferenceObject("protocolReferenceType");
        
        //TODO Framework problem of 2 saves, protocolNumber & SequenceNumber are not null fields and they are only available after one saves new protocol.
        protocolReference.setProtocolNumber("0");
        protocolReference.setSequenceNumber(0);
        
        protocol.getProtocolReferences().add(protocolReference);
    }

}
