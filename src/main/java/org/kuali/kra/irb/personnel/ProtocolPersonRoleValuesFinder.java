/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.personnel;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * This class is to get valid values for protocol person role
 * based on an assigned role. A source role can be change only to 
 * specific target roles in the list. This list is obtained from 
 * person role mapping. Include source role first and then start adding
 * target roles to the list.
 */
public class ProtocolPersonRoleValuesFinder extends KeyValuesBase {
    private String sourceRoleId;
    private String sourceRoleReferenceObject = "sourceRole";
    private String targetRoleReferenceObject = "targetRole";

    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        final List<ProtocolPersonRoleMapping> validPersonRoles = getProtocolPersonnelService().getPersonRoleMapping(getSourceRoleId());
        
        List<ConcreteKeyValue> keyValues = new ArrayList<ConcreteKeyValue>();
        keyValues.add(new ConcreteKeyValue(getSourceRoleId(), getSourceRoleDescription()));
        for(ProtocolPersonRoleMapping protocolPersonRole : validPersonRoles) {
            keyValues.add(new ConcreteKeyValue(protocolPersonRole.getTargetRoleId(), getTargetRoleDescription(protocolPersonRole)));
        }
        Collections.sort(keyValues);
        
        List<KeyValue> returnKeyValues = new ArrayList<KeyValue>();
        returnKeyValues.addAll(keyValues);
        return returnKeyValues;
    }

    /**
     * This method is used to lookup the source role object and return description
     * @return String - source role name
     */
    private String getSourceRoleDescription() {
        return getProtocolPersonnelService().getProtocolPersonRole(getSourceRoleId()).getDescription();
    }
    
    /**
     * This method is used to refresh target role object and return description
     * @param protocolPersonRole
     * @return String - target role name
     */
    private String getTargetRoleDescription(ProtocolPersonRoleMapping protocolPersonRole) {
        protocolPersonRole.refreshReferenceObject(targetRoleReferenceObject);
        return protocolPersonRole.getTargetRole().getDescription(); 
    }

    /**
     * Locate from Spring a singleton instance of the <code>{@link ProtocolPersonnelService}</code>.
     * 
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        return getService(ProtocolPersonnelService.class);
    }

    public String getSourceRoleId() {
        return sourceRoleId;
    }

    public void setSourceRoleId(String sourceRoleId) {
        this.sourceRoleId = sourceRoleId;
    }

}
