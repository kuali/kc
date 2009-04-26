/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.List;

import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

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
    public List getKeyValues() {
        final List<ProtocolPersonRoleMapping> validPersonRoles = getProtocolPersonnelService().getPersonRoleMapping(getSourceRoleId());
        boolean firstRole = true;
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for(ProtocolPersonRoleMapping protocolPersonRole : validPersonRoles) {
            if(firstRole) {
                keyValues.add(new KeyLabelPair(getSourceRoleId(), getSourceRoleDescription(protocolPersonRole)));
                firstRole = false;
            }
            keyValues.add(new KeyLabelPair(protocolPersonRole.getTargetRoleId(), getTargetRoleDescription(protocolPersonRole)));
        }
        return keyValues;
    }

    /**
     * This method is used to refresh source role object and return description
     * @param protocolPersonRole
     * @return String - source role name
     */
    private String getSourceRoleDescription(ProtocolPersonRoleMapping protocolPersonRole) {
        protocolPersonRole.refreshReferenceObject(sourceRoleReferenceObject);
        return protocolPersonRole.getSourceRole().getDescription(); 
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
