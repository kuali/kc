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
