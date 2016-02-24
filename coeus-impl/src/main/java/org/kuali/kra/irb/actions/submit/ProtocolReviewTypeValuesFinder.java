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
package org.kuali.kra.irb.actions.submit;

import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Assembles the Protocol Review Types to display in the drop-down menu when
 * submitting a protocol to the IRB office for review.
 */
public class ProtocolReviewTypeValuesFinder extends IrbActionsKeyValuesBase {

    private static final String PERMISSION_NAME = "View Active Protocol Review Types";

    private PermissionService permissionService;

    private List<ProtocolReviewType>allReviewTypes = null;
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = filterActiveProtocolReviewTypes();
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        return keyValues;
    }
    
    private List<KeyValue> filterActiveProtocolReviewTypes() {
        final List<KeyValue> filteredKeyValues = new ArrayList<KeyValue>();
        
        boolean canViewNonGlobalReviewTypes = getPermissionService().hasPermission(
                GlobalVariables.getUserSession().getPrincipalId(), KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE , PERMISSION_NAME);
        
        for (ProtocolReviewType item : getAllReviewTypes()) {
            if (item.isGlobalFlag() || canViewNonGlobalReviewTypes) {
                filteredKeyValues.add(new ConcreteKeyValue(item.getReviewTypeCode(), item.getDescription()));
            }
        }
        
        return filteredKeyValues;
    }

    @SuppressWarnings("unchecked")
    public List<ProtocolReviewType> getAllReviewTypes() {
        if (allReviewTypes == null) {
            allReviewTypes = new ArrayList<ProtocolReviewType>();
            Collection<ProtocolReviewType> prTypes = this.getKeyValuesService().findAll(ProtocolReviewType.class);
            for (ProtocolReviewType protocolReviewType : prTypes) {
                allReviewTypes.add(protocolReviewType);
            }
        }
        return allReviewTypes;
    }

    public PermissionService getPermissionService() {
        if (permissionService == null) {
            permissionService = KimApiServiceLocator.getPermissionService();
        }
        return permissionService;
    }
    

}
