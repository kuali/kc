/*
 * Copyright 2005-2014 The Kuali Foundation
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

import java.util.HashMap;

public interface IacucProtocolSpeciesService {

    /**
     * This method adds Protocol Species to the List of Protocol Species.
     * @param protocol which contains list of ProtocolSpecies.
     * @param ProtocolSpecies object is added to ProtocolSpecies list.
     */
    public abstract void addProtocolSpecies(IacucProtocol protocol, IacucProtocolSpecies protocolSpecies);

    /**
     * This method is to get formatted new protocol species
     * @param protocol
     * @param protocolSpecies
     * @return
     */
    public IacucProtocolSpecies getNewProtocolSpecies(IacucProtocol protocol, IacucProtocolSpecies protocolSpecies);
    
    
    /**
     * This method is to get a map of old and new protocol species id
     * @param protocol
     * @return
     */
    public HashMap<Integer, Integer> getNewProtocolSpeciesMap(IacucProtocol protocol);
    
    
}
