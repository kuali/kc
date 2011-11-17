/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.common.notification.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kim.bo.impl.RoleImpl;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;

/**
 * Provides a value finder for the Notification Type Recipient Role Namespace and Role name combination.
 */
public class NotificationTypeRecipientRoleNameValuesFinder extends KeyValuesBase {
    
    private KeyValuesService keyValuesService;

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<?> getKeyValues() {
        Collection<RoleImpl> roles = getKeyValuesService().findAll(RoleImpl.class);
        
        List<KeyLabelSortByValue> keyValues = new ArrayList<KeyLabelSortByValue>();
        keyValues.add(new KeyLabelSortByValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        for (RoleImpl role : roles) {
            String roleKey = role.getNamespaceCode() + ":" + role.getRoleName();
            keyValues.add(new KeyLabelSortByValue(roleKey, roleKey));                            
        }

        // sort values for usability
        Collections.sort(keyValues);
        return keyValues;
    }
    
    public KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService = (KeyValuesService) KraServiceLocator.getService(KeyValuesService.class);
        }
        return keyValuesService;
    }
    
    public void setKeyValuesService(KeyValuesService keyValuesService) {
        this.keyValuesService = keyValuesService;
    }

}
