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

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;

/**
 * Implements the <code>ProtocolRiskLevelService</code>.
 */
public class ProtocolRiskLevelServiceImpl implements ProtocolRiskLevelService {
    
    @Override
    public void addRiskLevel(ProtocolRiskLevel newProtocolRiskLevel, Protocol protocol) {
        newProtocolRiskLevel.setProtocolId(protocol.getProtocolId());
        newProtocolRiskLevel.setProtocol(protocol);
        protocol.getProtocolRiskLevels().add(newProtocolRiskLevel);
    }
    
    @Override
    public void updateRiskLevel(ProtocolRiskLevel currentProtocolRiskLevel, ProtocolRiskLevel newProtocolRiskLevel) {
        currentProtocolRiskLevel.setStatus(Constants.STATUS_INACTIVE);
        
        newProtocolRiskLevel.setRiskLevelCode(currentProtocolRiskLevel.getRiskLevelCode());
        newProtocolRiskLevel.setDateAssigned(currentProtocolRiskLevel.getDateAssigned());
        newProtocolRiskLevel.setComments(currentProtocolRiskLevel.getComments());
    }
    
    @Override
    public void deleteRiskLevel(int index, Protocol protocol) {
        if (index >= 0 && index < protocol.getProtocolRiskLevels().size()) {
            protocol.getProtocolRiskLevels().remove(index);
        }
    }

}
