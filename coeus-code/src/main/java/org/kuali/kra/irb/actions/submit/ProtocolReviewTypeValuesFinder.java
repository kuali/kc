/*
 * Copyright 2005-2014 The Kuali Foundation
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
