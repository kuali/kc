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
package org.kuali.kra.irb.service;

import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolLocation;


public interface ProtocolLocationService {

    /**
     * This method adds ProtocolLocation to the List of ProtocolLocations.
     * @param protocol which contains list of ProtocolLocations.
     * @param ProtocolLocation object is added to ProtocolLocations list.
     */
    public abstract void addProtocolLocation(Protocol protocol, ProtocolLocation protocolLocation);
    
    /**
     * This method deletes ProtocolLocation from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolLocations
     * @param lineNumber to be deleted
     */
    public abstract void deleteProtocolLocation(Protocol protocol, int lineNumber);

    /**
     * This method will clear ProtocolLocation address from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolLocations
     * @param lineNumber to clear location address
     */
    public abstract void clearProtocolLocationAddress(Protocol protocol, int lineNumber);

}