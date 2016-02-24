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
import java.util.List;

/**
 * Provides a value finder for the Notification Type Recipient Role Namespace and Role name combination.
 */
public class NotificationTypeRecipientRoleNameValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    private KeyValuesService keyValuesService;

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
