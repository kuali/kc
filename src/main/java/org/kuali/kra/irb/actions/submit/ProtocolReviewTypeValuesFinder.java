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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Assembles the Protocol Review Types to display in the drop-down menu when
 * submitting a protocol to the IRB office for review.
 */
public class ProtocolReviewTypeValuesFinder extends IrbActionsKeyValuesBase {

    private static final String PERMISSION_NAME = "View Active Protocol Review Types";

    private IdentityManagementService identityManagementService;

    private static List<ProtocolReviewType>allReviewTypes = null;
    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<?> getKeyValues() {
        List<KeyLabelPair> keyValues = filterActiveProtocolReviewTypes();
        keyValues.add(0, new KeyLabelPair(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        return keyValues;
    }
    
    private List<KeyLabelPair> filterActiveProtocolReviewTypes() {
        final List<KeyLabelPair> filteredKeyValues = new ArrayList<KeyLabelPair>();
        
        boolean canViewNonGlobalReviewTypes = getIdentityManagementService().hasPermission(
                GlobalVariables.getUserSession().getPrincipalId(), KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE , PERMISSION_NAME, new AttributeSet());
        
        for (ProtocolReviewType item : getAllReviewTypes()) {
            if (item.isGlobalFlag() || canViewNonGlobalReviewTypes) {
                filteredKeyValues.add(new KeyLabelPair(item.getReviewTypeCode(), item.getDescription()));
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

    public IdentityManagementService getIdentityManagementService() {
        if (identityManagementService == null) {
            identityManagementService = KraServiceLocator.getService(IdentityManagementService.class);
        }
        return identityManagementService;
    }
    

}
