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
package org.kuali.kra.irb.protocol.location;

import org.kuali.kra.irb.Protocol;


public interface ProtocolLocationService {

    /**
     * This method adds ProtocolLocation to the List of ProtocolLocations.
     * @param protocol which contains list of ProtocolLocations.
     * @param ProtocolLocation object is added to ProtocolLocations list.
     */
    public abstract void addProtocolLocation(Protocol protocol, ProtocolLocation protocolLocation);
    
    /**
     * This method adds a default ProtocolLocation to the List of ProtocolLocations.
     * i.e. Initialize protocol location with a default organization
     * @param protocol which contains list of ProtocolLocations.
     */
    public abstract void addDefaultProtocolLocation(Protocol protocol);

    /**
     * This method will clear ProtocolLocation address from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolLocations
     * @param lineNumber to clear location address
     */
    public abstract void clearProtocolLocationAddress(Protocol protocol, int lineNumber);

}