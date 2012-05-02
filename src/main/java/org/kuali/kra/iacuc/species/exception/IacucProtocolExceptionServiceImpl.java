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
package org.kuali.kra.iacuc.species.exception;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.rice.krad.service.SequenceAccessorService;

public class IacucProtocolExceptionServiceImpl implements IacucProtocolExceptionService {
    private SequenceAccessorService sequenceAccessorService;
    private static final String PROTOCOL_EXCEPTION_SEQUENCE_ID = "SEQ_IACUC_PROTO_EXCEPTION_ID";
    private static final String REFERENCE_EXCEPTION_CATEGORY = "iacucExceptionCategory";
    private static final String REFERENCE_PROTOCOL_SPECIES = "iacucProtocolSpecies";

    public void addProtocolException(IacucProtocol protocol, IacucProtocolException protocolException) {
        protocolException.setIacucProtocolExceptionId(getNextProtocolExceptionSequence());
        protocolException.setExceptionId(getNextProtocolExceptionId(protocol));
        protocolException.setProtocolNumber(protocol.getProtocolNumber());
        protocolException.setSequenceNumber(protocol.getSequenceNumber());
        
        refreshSpeciesReferenceObjects(protocolException);
        
        protocol.getIacucProtocolExceptions().add(protocolException);
    }
    
    /**
     * This method is to refresh all reference objects in protocol exception
     * @param protocolException
     */
    private void refreshSpeciesReferenceObjects(IacucProtocolException protocolException) {
        protocolException.refreshReferenceObject(REFERENCE_EXCEPTION_CATEGORY);
        protocolException.refreshReferenceObject(REFERENCE_PROTOCOL_SPECIES);
    }
    
    /**
     * This method is to get the next sequence number for protocol exception
     * This is the primary key
     * @return
     */
    private Integer getNextProtocolExceptionSequence() {
        return getSequenceAccessorService().getNextAvailableSequenceNumber(PROTOCOL_EXCEPTION_SEQUENCE_ID).intValue();
    }

    /**
     * This method is to get the next protocol exception id.
     * It is just a serial number generated based on the list of exceptions
     * @param protocol
     * @return
     */
    private Integer getNextProtocolExceptionId(IacucProtocol protocol) {
        int totalExceptions = protocol.getIacucProtocolExceptions().size();
        Integer nextExceptionId = 1;
        if(totalExceptions > 0) {
            IacucProtocolException protocolException = protocol.getIacucProtocolExceptions().get(totalExceptions - 1);
            nextExceptionId = protocolException.getIacucProtocolExceptionId() + 1;
        }
        return nextExceptionId;
    }

    /**
     * Gets the SequenceAccessorService attribute.
     * 
     * @return Returns the SequenceAccessorService.
     */
    public SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService;
    }

    /**
     * Sets the SequenceAccessorService attribute value.
     * 
     * @param sequenceAccessorService The SequenceAccessorService to set.
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
}
