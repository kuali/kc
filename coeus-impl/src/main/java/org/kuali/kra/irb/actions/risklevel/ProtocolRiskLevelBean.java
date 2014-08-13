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