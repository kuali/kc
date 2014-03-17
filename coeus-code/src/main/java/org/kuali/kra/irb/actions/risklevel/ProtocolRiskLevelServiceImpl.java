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