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
package org.kuali.kra.iacuc.species;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.*;

public class IacucProtocolSpeciesServiceImpl implements IacucProtocolSpeciesService {
    private SequenceAccessorService sequenceAccessorService;
    private static final String PROTOCOL_SPECIES_SEQUENCE_ID = "SEQ_IACUC_PROTOCOL_SPECIES_ID";
    private static final String REFERENCE_SPECIES = "iacucSpecies";
    private static final String REFERENCE_SPECIES_COUNT_TYPE = "iacucSpeciesCountType";
    private static final String REFERENCE_SPECIES_PAIN_CATEGORY = "iacucPainCategory";

    public void addProtocolSpecies(IacucProtocol protocol, IacucProtocolSpecies protocolSpecies) {
        protocol.getIacucProtocolSpeciesList().add(getNewProtocolSpecies(protocol, protocolSpecies));
    }
    
    public IacucProtocolSpecies getNewProtocolSpecies(IacucProtocol protocol, IacucProtocolSpecies protocolSpecies) {
        protocolSpecies.setIacucProtocolSpeciesId(getNextProtocolSpeciesSequence());
        protocolSpecies.setSpeciesId(getNextProtocolSpeciesId(protocol));
        //TODO - How to handle protocol number and sequence number
        protocolSpecies.setProtocolNumber(protocol.getProtocolNumber());
        protocolSpecies.setSequenceNumber(protocol.getSequenceNumber());
        protocolSpecies.setProtocolId(protocol.getProtocolId());
        refreshSpeciesReferenceObjects(protocolSpecies);
        return protocolSpecies;
    }
    
    public HashMap<Integer, Integer> getNewProtocolSpeciesMap(IacucProtocol protocol) {
        List<IacucProtocolSpecies> protocolSpeciesList = protocol.getIacucProtocolSpeciesList();
        HashMap<Integer, Integer> speciesIdMapping = new HashMap<Integer,Integer>();
        for(IacucProtocolSpecies protocolSpecies : protocolSpeciesList) {
            if(ObjectUtils.isNull(protocolSpecies.getIacucProtocolSpeciesId())) {
                protocolSpecies.setIacucProtocolSpeciesId(getNextProtocolSpeciesSequence());
            }
            Integer oldProtocolSpeciesId = protocolSpecies.getOldProtocolSpeciesId() == null ? protocolSpecies.getIacucProtocolSpeciesId() : protocolSpecies.getOldProtocolSpeciesId();  
            speciesIdMapping.put(oldProtocolSpeciesId, protocolSpecies.getIacucProtocolSpeciesId());
        }
        return speciesIdMapping;
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
        return getSequenceAccessorService().getNextAvailableSequenceNumber(PROTOCOL_SPECIES_SEQUENCE_ID, IacucProtocolSpecies.class).intValue();
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
            List<IacucProtocolSpecies> sortedProtocolSpecies = getSortedSpecies(protocol);
            IacucProtocolSpecies protocolSpecies = sortedProtocolSpecies.get(totalSpecies - 1);
            nextSpeciesId = protocolSpecies.getSpeciesId() + 1;
        }
        return nextSpeciesId;
    }

    /**
     * This method is to get a sorted list of current protocol species.
     * @param protocol
     * @return
     */
    public List<IacucProtocolSpecies> getSortedSpecies(IacucProtocol protocol) {
        List<IacucProtocolSpecies> protocolSpeciesList = new ArrayList<IacucProtocolSpecies>();
        for (IacucProtocolSpecies species : protocol.getIacucProtocolSpeciesList()) {
            protocolSpeciesList.add((IacucProtocolSpecies) ObjectUtils.deepCopy(species));
        }
        Collections.sort(protocolSpeciesList, new Comparator<IacucProtocolSpecies>() {
            public int compare(IacucProtocolSpecies species1, IacucProtocolSpecies species2) {
                return species1.getSpeciesId().compareTo(species2.getSpeciesId());
            }
        });
        return protocolSpeciesList;
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
