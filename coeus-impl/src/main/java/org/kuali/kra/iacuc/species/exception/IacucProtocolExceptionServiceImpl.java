/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.iacuc.species.exception;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IacucProtocolExceptionServiceImpl implements IacucProtocolExceptionService {
    private SequenceAccessorService sequenceAccessorService;
    private static final String PROTOCOL_EXCEPTION_SEQUENCE_ID = "SEQ_IACUC_PROTO_EXCEPTION_ID";
    private static final String REFERENCE_EXCEPTION_CATEGORY = "iacucExceptionCategory";
    private static final String REFERENCE_PROTOCOL_SPECIES = "iacucSpecies";

    public void addProtocolException(IacucProtocol protocol, IacucProtocolException protocolException) {
        protocol.getIacucProtocolExceptions().add(getNewProtocolException(protocol, protocolException));
    }
    
    public IacucProtocolException getNewProtocolException(IacucProtocol protocol, IacucProtocolException protocolException) {
        protocolException.setIacucProtocolExceptionId(getNextProtocolExceptionSequence());
        protocolException.setExceptionId(getNextProtocolExceptionId(protocol));
        protocolException.setProtocolNumber(protocol.getProtocolNumber());
        protocolException.setSequenceNumber(protocol.getSequenceNumber());
        
        refreshSpeciesReferenceObjects(protocolException);
        return protocolException;
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
        return getSequenceAccessorService().getNextAvailableSequenceNumber(PROTOCOL_EXCEPTION_SEQUENCE_ID, IacucProtocolException.class).intValue();
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
            List<IacucProtocolException> sortedProtocolExceptions = getSortedExceptions(protocol);
            IacucProtocolException protocolException = sortedProtocolExceptions.get(totalExceptions - 1);
            nextExceptionId = protocolException.getExceptionId() + 1;
        }
        return nextExceptionId;
    }

    /**
     * This method is to get a sorted list of current protocol exceptions.
     * @param protocol
     * @return
     */
    public List<IacucProtocolException> getSortedExceptions(IacucProtocol protocol) {
        List<IacucProtocolException> protocolExceptionsList = new ArrayList<IacucProtocolException>();
        for (IacucProtocolException exception : protocol.getIacucProtocolExceptions()) {
            protocolExceptionsList.add((IacucProtocolException) ObjectUtils.deepCopy(exception));
        }
        Collections.sort(protocolExceptionsList, new Comparator<IacucProtocolException>() {
            public int compare(IacucProtocolException exception1, IacucProtocolException exception2) {
                return exception1.getExceptionId().compareTo(exception2.getExceptionId());
            }
        });
        return protocolExceptionsList;
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
