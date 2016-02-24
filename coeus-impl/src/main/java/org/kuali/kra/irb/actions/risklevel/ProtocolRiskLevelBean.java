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

import java.io.Serializable;

/**
 * Encapsulates the actions that can be performed on a ProtocolRiskLevel.
 */
public class ProtocolRiskLevelBean implements Serializable {

    private static final long serialVersionUID = -3726620115307425457L;

    private String errorPropertyKey;
    
    private ProtocolRiskLevel newProtocolRiskLevel;
    

    public ProtocolRiskLevelBean(String errorPropertyKey) {
        this.errorPropertyKey = errorPropertyKey + ".protocolRiskLevelBean";
        
        newProtocolRiskLevel = new ProtocolRiskLevel();
    }

    public String getErrorPropertyKey() {
        return errorPropertyKey;
    }
    
    public ProtocolRiskLevel getNewProtocolRiskLevel() {
        return newProtocolRiskLevel;
    }
    
    public void setNewProtocolRiskLevel(ProtocolRiskLevel newProtocolRiskLevel) {
        this.newProtocolRiskLevel = newProtocolRiskLevel;
    }

}
