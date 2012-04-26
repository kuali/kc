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
package org.kuali.kra.iacuc.species;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.rice.krad.service.SequenceAccessorService;

public class IacucProtocolSpeciesServiceImpl implements IacucProtocolSpeciesService {
    private SequenceAccessorService sequenceAccessorService;
    private static final String PROTOCOL_SPECIES_SEQUENCE_ID = "SEQ_IACUC_PROTOCOL_SPECIES_ID";
    private static final String REFERENCE_SPECIES = "iacucSpecies";
    private static final String REFERENCE_SPECIES_COUNT_TYPE = "iacucSpeciesCountType";
    private static final String REFERENCE_SPECIES_PAIN_CATEGORY = "iacucPainCategory";

    public void addProtocolSpecies(IacucProtocol protocol, IacucProtocolSpecies protocolSpecies) {
        protocolSpecies.setIacucProtocolSpeciesId(getNextProtocolSpeciesSequence());
        protocolSpecies.setSpeciesId(getNextProtocolSpeciesId(protocol));
        //TODO - How to handle protocol number and sequence number
        protocolSpecies.setProtocolNumber(protocol.getProtocolNumber());
        protocolSpecies.setSequenceNumber(protocol.getSequenceNumber());
        
        refreshSpeciesReferenceObjects(protocolSpecies);
        
        protocol.getIacucProtocolSpeciesList().add(protocolSpecies);
    }
    
    /**
     * This method is to refresh all reference objects in protocol species
     * @param protocolSpecies
     */
    private void refreshSpeciesReferenceObjects(IacucProtocolSpecies protocolSpecies) {
        protocolSpecies.refreshReferenceObject(REFERENCE_SPECIES);
        protocolSpecies.refreshReferenceObject(REFERENCE_SPECIES_COUNT_TYPE);
        protocolSpecies.refreshReferenceObject(REFERENCE_SPECIES_PAIN_CATEGORY);
    }
    
    /**
     * This method is to get the next sequence number for protocol species
     * This is the primary key
     * @return
     */
    private Integer getNextProtocolSpeciesSequence() {
        return getSequenceAccessorService().getNextAvailableSequenceNumber(PROTOCOL_SPECIES_SEQUENCE_ID).intValue();
    }

    /**
     * This method is to get the next protocol species id.
     * It is just a serial number generated based on the list of species
     * @param protocol
     * @return
     */
    private Integer getNextProtocolSpeciesId(IacucProtocol protocol) {
        int totalSpecies = protocol.getIacucProtocolSpeciesList().size();
        Integer nextSpeciesId = 1;
        if(totalSpecies > 0) {
            IacucProtocolSpecies protocolSpecies = protocol.getIacucProtocolSpeciesList().get(totalSpecies - 1);
            nextSpeciesId = protocolSpecies.getIacucProtocolSpeciesId() + 1;
        }
        return nextSpeciesId;
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
