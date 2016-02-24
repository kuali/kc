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
package org.kuali.coeus.common.notification.impl.rules;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

/**
 * Overrides the custom save and approve methods of the maintenance document processing to check uniqueness constraints.
 */
public class NotificationTypeMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

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
    protected boolean isDocumentValidForSave(MaintenanceDocument document) {
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
        
        NotificationType newNotificationType = (NotificationType) document.getNewMaintainableObject().getDataObject();
        
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
            
            for (ListIterator<NotificationTypeRecipient> inner = newNotificationTypeRecipients.listIterator(outer.nextIndex()); inner.hasNext();) {
                int currentIndex = inner.nextIndex();
                NotificationTypeRecipient innerNotificationTypeRecipient = inner.next();
                
                if (StringUtils.equals(outerNotificationTypeRecipient.getRoleName(), innerNotificationTypeRecipient.getRoleName())
                        && StringUtils.equals(outerNotificationTypeRecipient.getRoleSubQualifier(), innerNotificationTypeRecipient.getRoleSubQualifier())) {
                    isValid = false;
                    putFieldError(String.format(ROLE_FIELD_NAME, currentIndex), 
                        KeyConstants.ERROR_NOTIFICATION_ROLE_NAME_EXISTS, new String[] {innerNotificationTypeRecipient.getRoleName()});
                }
            }
        }
        
        return isValid;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
}
