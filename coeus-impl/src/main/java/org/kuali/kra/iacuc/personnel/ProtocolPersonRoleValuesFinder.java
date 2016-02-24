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
package org.kuali.kra.iacuc.personnel;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.personnel.ProtocolPersonRoleMappingBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is to get valid values for protocol person role
 * based on an assigned role. A source role can be change only to 
 * specific target roles in the list. This list is obtained from 
 * person role mapping. Include source role first and then start adding
 * target roles to the list.
 */
public class ProtocolPersonRoleValuesFinder extends UifKeyValuesFinderBase {
    private String sourceRoleId;
    private String sourceRoleReferenceObject = "sourceRole";
    private String targetRoleReferenceObject = "targetRole";

    @Override
    public List<KeyValue> getKeyValues() {
        final List<ProtocolPersonRoleMappingBase> validPersonRoles = getProtocolPersonnelService().getPersonRoleMapping(getSourceRoleId());
        
        List<ConcreteKeyValue> keyValues = new ArrayList<ConcreteKeyValue>();
        keyValues.add(new ConcreteKeyValue(getSourceRoleId(), getSourceRoleDescription()));
        for(ProtocolPersonRoleMappingBase protocolPersonRole : validPersonRoles) {
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
    private String getTargetRoleDescription(ProtocolPersonRoleMappingBase protocolPersonRole) {
        protocolPersonRole.refreshReferenceObject(targetRoleReferenceObject);
        return protocolPersonRole.getTargetRole().getDescription(); 
    }

    /**
     * Locate from Spring a singleton instance of the <code>{@link ProtocolPersonnelService}</code>.
     * 
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        return (ProtocolPersonnelService) KcServiceLocator.getService("iacucProtocolPersonnelService");
    }

    public String getSourceRoleId() {
        return sourceRoleId;
    }

    public void setSourceRoleId(String sourceRoleId) {
        this.sourceRoleId = sourceRoleId;
    }

}
