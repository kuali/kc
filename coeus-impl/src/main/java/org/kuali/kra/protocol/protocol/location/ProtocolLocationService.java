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
package org.kuali.kra.protocol.protocol.location;

import org.kuali.kra.protocol.ProtocolBase;


public interface ProtocolLocationService {

    /**
     * This method adds ProtocolLocationBase to the List of ProtocolLocations.
     * @param protocol which contains list of ProtocolLocations.
     * @param ProtocolLocationBase object is added to ProtocolLocations list.
     */
    public abstract void addProtocolLocation(ProtocolBase protocol, ProtocolLocationBase protocolLocation);
    
    /**
     * This method adds a default ProtocolLocationBase to the List of ProtocolLocations.
     * i.e. Initialize protocol location with a default organization
     * @param protocol which contains list of ProtocolLocations.
     */
    public abstract void addDefaultProtocolLocation(ProtocolBase protocol);

    /**
     * This method will clear ProtocolLocationBase address from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolLocations
     * @param lineNumber to clear location address
     */
    public abstract void clearProtocolLocationAddress(ProtocolBase protocol, int lineNumber);

}
