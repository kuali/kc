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
package org.kuali.coeus.common.notification.impl.lookup.keyvalue;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRole;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides a value finder for the Notification Type Recipient Role Namespace and Role name combination.
 */
public class NotificationTypeRecipientRoleNameValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    private KeyValuesService keyValuesService;

    @Override
   	public Map<String, String> getKeyLabelMap() {
           Map<String, String> keyLabelMap = new HashMap<String, String>();

           List<KeyValue> keyLabels = getKeyValues();
           if (keyLabels != null) {
            for (KeyValue keyLabel : keyLabels) {
           	    keyLabelMap.put(keyLabel.getKey(), keyLabel.getValue());
            }
           }
           return keyLabelMap;
     }
    
    @Override
    public List<KeyValue> getKeyValues() {

        String moduleCode = null;

        Document doc = getDocument();
        
        if (doc != null && doc instanceof MaintenanceDocumentBase) {
            NotificationType notificationType = (NotificationType) doc.getNoteTarget();
            if (notificationType != null) {
                moduleCode = notificationType.getModuleCode();
            }
        }
        
        List<KeyLabelSortByValue> keyValues = new ArrayList<KeyLabelSortByValue>();
        keyValues.add(new KeyLabelSortByValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        if (moduleCode != null) {
            List<NotificationModuleRole> moduleRoles = getKcNotificationModuleRoleService().getNotificationModuleRoles(moduleCode);
            if (CollectionUtils.isNotEmpty(moduleRoles)) {
                for (NotificationModuleRole moduleRole : moduleRoles) {
                    keyValues.add(new KeyLabelSortByValue(moduleRole.getRoleName(), moduleRole.getRoleName()));
                }
            }
        }

        // sort values for usability
        Collections.sort(keyValues);
        List<KeyValue> returnKeyValues = new ArrayList<KeyValue>();
        returnKeyValues.addAll(keyValues);
        
        return returnKeyValues;
    }
    
    public KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService = (KeyValuesService) KcServiceLocator.getService(KeyValuesService.class);
        }
        return keyValuesService;
    }
    
    public void setKeyValuesService(KeyValuesService keyValuesService) {
        this.keyValuesService = keyValuesService;
    }

    public KcNotificationModuleRoleService getKcNotificationModuleRoleService() {
        return KcServiceLocator.getService(KcNotificationModuleRoleService.class);
    }

}
