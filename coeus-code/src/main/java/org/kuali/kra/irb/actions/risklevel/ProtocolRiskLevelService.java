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
package org.kuali.kra.irb.actions.risklevel;

import org.kuali.kra.irb.Protocol;

/**
 * Provides services to add, update, and delete <code>ProtocolRiskLevel</code>s.
 */
public interface ProtocolRiskLevelService {

    /**
     * Adds the newProtocolRiskLevel to the list of protocolRiskLevels in the given protocol.
     * 
     * @param newProtocolRiskLevel the new protocol risk level to add
     * @param protocol the current protocol
     */
    void addRiskLevel(ProtocolRiskLevel newProtocolRiskLevel, Protocol protocol);

    /**
     * Sets the currentProtocolRiskLevel's status to inactive and creates an identical newProtocolRiskLevel for the user to add.
     * @param currentProtocolRiskLevel the current protocol risk level to update
     * @param newProtocolRiskLevel the new protocol risk level to create
     */
    void updateRiskLevel(ProtocolRiskLevel currentProtocolRiskLevel, ProtocolRiskLevel newProtocolRiskLevel);

    /**
     * Removes the protocolRiskLevel at index from the list of protocolRiskLevels in the given protocol.
     * @param index the index of the protocol risk level to remove
     * @param protocol the current protocol
     */
    void deleteRiskLevel(int index, Protocol protocol);

}