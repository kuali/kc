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
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.common.notification.bo.NotificationModuleRole;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.service.NotificationModuleRoleService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * Provides a value finder for the Notification Type Recipient Role Namespace and Role name combination.
 */
public class NotificationTypeRecipientRoleNameValuesFinder extends KeyValuesBase {
    
    private KeyValuesService keyValuesService;

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {

        String moduleCode = null;
        KualiForm form = KNSGlobalVariables.getKualiForm();
        if ((form != null) && (form instanceof KualiDocumentFormBase)) {
            Document doc = ((KualiDocumentFormBase)form).getDocument();
            if (doc != null) {
                NotificationType notificationType = (NotificationType) doc.getNoteTarget();
                if (notificationType != null) {
                    moduleCode = notificationType.getModuleCode();
                }
            }  
        }
        
        List<KeyLabelSortByValue> keyValues = new ArrayList<KeyLabelSortByValue>();
        keyValues.add(new KeyLabelSortByValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        if (moduleCode != null) {
            List<NotificationModuleRole> moduleRoles = getNotificationModuleRoleService().getModuleRolesByModuleName(moduleCode);
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
            keyValuesService = (KeyValuesService) KraServiceLocator.getService(KeyValuesService.class);
        }
        return keyValuesService;
    }
    
    public void setKeyValuesService(KeyValuesService keyValuesService) {
        this.keyValuesService = keyValuesService;
    }

    public NotificationModuleRoleService getNotificationModuleRoleService() {
        return KraServiceLocator.getService(NotificationModuleRoleService.class);
    }

}
