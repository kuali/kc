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
package org.kuali.kra.common.notification.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Overrides the custom save and approve methods of the maintenance document processing to check uniqueness constraints.
 */
public class NotificationTypeMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {

    private static final String MODULE_CODE_FIELD_NAME = "moduleCode";
    private static final String ACTION_CODE_FIELD_NAME = "actionCode";
    private static final String ROLE_FIELD_NAME = "notificationTypeRecipients[%d].roleName";
    
    private transient BusinessObjectService businessObjectService;

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkUniqueness(document);
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkUniqueness(document);
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return checkUniqueness(document);
    }

    @Override 
    public boolean isDocumentValidForSave(MaintenanceDocument document) {
        return checkUniqueness(document);
    }

    /**
     * Validates the uniqueness constraints for {@code NotificationType} and {@code NotificationTypeRecipient}.
     *
     * @param document the maintenance document to check
     * @return true if all uniqueness constraints succeed, false otherwise
     */
    private boolean checkUniqueness(MaintenanceDocument document) {
        boolean isValid = true;
        
        NotificationType newNotificationType = (NotificationType) document.getNewMaintainableObject().getBusinessObject();
        
        isValid &= checkModuleCodeActionCodeUniqueness(newNotificationType);
        isValid &= checkNotificationTypeIdRoleNameUniqueness(newNotificationType);
        
        return isValid;
    }

    /**
     * Validates that the {@code moduleCode} and {@code actionCode} are unique.
     * 
     * @param newNotificationType the new {@code NotificationType} to check
     * @return true if the {@code moduleCode} and {@code actionCode} are unique, false otherwise
     */
    @SuppressWarnings("unchecked")
    private boolean checkModuleCodeActionCodeUniqueness(NotificationType newNotificationType) {
        boolean isValid = true;
        
        String moduleCode = newNotificationType.getModuleCode();
        String actionCode = newNotificationType.getActionCode();
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_CODE_FIELD_NAME, moduleCode);
        fieldValues.put(ACTION_CODE_FIELD_NAME, actionCode);
        Collection<NotificationType> matchingNotificationTypes = getBusinessObjectService().findMatching(NotificationType.class, fieldValues);
        
        for (NotificationType matchingNotificationType : matchingNotificationTypes) {
            if (!ObjectUtils.equals(newNotificationType.getNotificationTypeId(), matchingNotificationType.getNotificationTypeId())) {
                isValid = false;
                putFieldError(ACTION_CODE_FIELD_NAME, KeyConstants.ERROR_NOTIFICATION_MODULE_CODE_ACTION_CODE_COMBINATION_EXISTS, 
                    new String[] {moduleCode, actionCode});
                break;
            }
        }
        
        return isValid;
    }
    
    /**
     * Validates that the {@code notificationTypeId}, {@code roleName}, and {@code roleQualifier} are unique.
     * 
     * @param newNotificationType the {@code NotificationType} to check
     * @return true if the {@code notificationTypeId}, {@code roleName}, and {@code roleQualifier} are unique, false otherwise
     */
    private boolean checkNotificationTypeIdRoleNameUniqueness(NotificationType newNotificationType) {
        boolean isValid = true;
        
        List<NotificationTypeRecipient> newNotificationTypeRecipients = newNotificationType.getNotificationTypeRecipients();

        for (ListIterator<NotificationTypeRecipient> outer = newNotificationTypeRecipients.listIterator(); outer.hasNext();) {
            NotificationTypeRecipient outerNotificationTypeRecipient = outer.next();
            
            String outerRoleName = outerNotificationTypeRecipient.getRoleName();
            
            for (ListIterator<NotificationTypeRecipient> inner = newNotificationTypeRecipients.listIterator(outer.nextIndex()); inner.hasNext();) {
                int currentIndex = inner.nextIndex();
                NotificationTypeRecipient innerNotificationTypeRecipient = inner.next();
                
                String innerRoleName = innerNotificationTypeRecipient.getRoleName();
                
                if (StringUtils.equals(outerRoleName, innerRoleName)) {
                    isValid = false;
                    putFieldError(String.format(ROLE_FIELD_NAME, currentIndex), 
                        KeyConstants.ERROR_NOTIFICATION_ROLE_NAME_EXISTS, new String[] {innerRoleName});
                }
            }
        }
        
        return isValid;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
}