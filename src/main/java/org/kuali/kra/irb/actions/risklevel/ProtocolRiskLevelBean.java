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

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;

/**
 * Encapsulates the actions that can be performed on a ProtocolRiskLevel.
 */
public class ProtocolRiskLevelBean implements Serializable {
    
    private static final long serialVersionUID = -7683369421103168798L;
    
    private ProtocolRiskLevel newProtocolRiskLevel;
    
    /**
     * Constructs a ProtocolRiskLevelBean.
     */
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
     * Initializes a newProtocolRiskLevel to the current settings of the persisted risk level at index and sets the persisted risk level's status to inactive.
     *
     * @param protocol The protocol referencing the risk levels
     * @param index The position of the updated risk level in the list of risk levels
     */
    public void updateProtocolRiskLevel(Protocol protocol, int index) {
        ProtocolRiskLevel persistedProtocolRiskLevel = protocol.getProtocolRiskLevels().get(index);
        newProtocolRiskLevel.setRiskLevelCode(persistedProtocolRiskLevel.getRiskLevelCode());
        newProtocolRiskLevel.setDateAssigned(persistedProtocolRiskLevel.getDateAssigned());
        newProtocolRiskLevel.setComments(persistedProtocolRiskLevel.getComments());
        
        persistedProtocolRiskLevel.setStatus(Constants.STATUS_INACTIVE);
    }
    
    /**
     * Removes the protocol risk level at index from the list of protocol risk levels and adds it to the list of deleted protocol risk levels.
     *
     * @param protocol The protocol referencing the risk levels
     * @param index The position of the deleted risk level in the list of risk levels
     */
    public void deleteProtocolRiskLevel(Protocol protocol, int index) {
        if (index >= 0 && index < protocol.getProtocolRiskLevels().size()) {
            protocol.getProtocolRiskLevels().remove(index);
        }
    }

}