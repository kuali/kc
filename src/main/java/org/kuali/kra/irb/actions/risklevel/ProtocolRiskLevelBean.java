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
package org.kuali.kra.irb.actions.risklevel;

import java.io.Serializable;

import org.kuali.kra.irb.Protocol;

public class ProtocolRiskLevelBean implements Serializable {
    
    private static final long serialVersionUID = -7683369421103168798L;
    
    private ProtocolRiskLevel newProtocolRiskLevel;
    
    public ProtocolRiskLevelBean() {
        newProtocolRiskLevel = new ProtocolRiskLevel();
    }
    
    public ProtocolRiskLevel getNewProtocolRiskLevel() {
        return newProtocolRiskLevel;
    }
    
    /**
     * Adds the current newProtocolRiskLevel to the list of protocol risk levels and links it to a Protocol.
     * @param protocol The protocol to link the newProtocolRiskLevel to
     */
    public void addNewProtocolRiskLevel(Protocol protocol) {
        newProtocolRiskLevel.setProtocolId(protocol.getProtocolId());
        newProtocolRiskLevel.setProtocol(protocol);
        protocol.getProtocolRiskLevels().add(newProtocolRiskLevel);
        newProtocolRiskLevel = new ProtocolRiskLevel();
    }
    
    /**
     * Removes the protocol risk level at index from the list of protocol risk levels and adds it to the list of deleted protocol risk levels.
     */
    public void deleteProtocolRiskLevel(Protocol protocol, int index) {
        if (index >= 0 && index < protocol.getProtocolRiskLevels().size()) {
            protocol.getProtocolRiskLevels().remove(index);
        }
    }

}