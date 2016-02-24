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
