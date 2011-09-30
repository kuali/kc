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
package org.kuali.kra.common.notification;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationRenderingService;
import org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kim.bo.role.dto.KimRoleInfo;
import org.kuali.rice.kim.bo.types.dto.KimTypeAttributeInfo;
import org.kuali.rice.kim.bo.types.dto.KimTypeInfo;
import org.kuali.rice.kim.service.KimTypeInfoService;
import org.kuali.rice.kim.service.RoleManagementService;

/**
 * 
 * This class provides a class for all notifications to extend.  Its main purpose is to
 * provide a holding place for the required services, as well as define a few methods
 * that should be implemented by any notification.
 */
public abstract class NotificationContextBase implements NotificationContext {

    private KcNotificationRenderingService notificationRenderingService;
    private KcNotificationModuleRoleService notificationModuleRuleService;
    private KcNotificationRoleQualifierService notificationRoleQualifierService;
        
    /**
     * 
     * This method returns the associated coeus module as defined in the
     * CoeusModule object
     * @return the coeus module code
     * @see org.kuali.kra.bo.CoeusModule
     */
    public abstract String getModuleCode();
    
    /**
     * 
     * This method returns the action type code needed to send notifications
     * by the KcNotificationService
     * @return the action type code
     */
    public abstract String getActionTypeCode();
        
    /**
     * 
     * This method sends the notifications
     */
    public abstract void sendNotification();

    
    public KcNotificationRenderingService getNotificationRenderingService() {
        return notificationRenderingService;
    }

    public void setNotificationRenderingService(KcNotificationRenderingService notificationRenderingService) {
        this.notificationRenderingService = notificationRenderingService;
    }

    public KcNotificationModuleRoleService getNotificationModuleRuleService() {
        return notificationModuleRuleService;
    }

    public void setNotificationModuleRuleService(KcNotificationModuleRoleService notificationModuleRuleService) {
        this.notificationModuleRuleService = notificationModuleRuleService;
    }

    public KcNotificationRoleQualifierService getNotificationRoleQualifierService() {
        return notificationRoleQualifierService;
    }

    public void setNotificationRoleQualifierService(KcNotificationRoleQualifierService notificationRoleQualifierService) {
        this.notificationRoleQualifierService = notificationRoleQualifierService;
    }



    

}
