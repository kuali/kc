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
package org.kuali.kra.common.notification.service;

import java.util.List;

import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.bo.KcNotification;

/**
 * API for processing and sending KC Notifications.
 */
public interface KcNotificationService {
    
    /**
     * Get a list of KC Notifications based on an Action Code and Notification Context.
     * The Notification Types associated with the Action Code will be retrieved from persistent storage,
     * and translated into context-specific KC Notification instances based on the Notification Context.
     * 
     * @param actionCode
     * @param notificationContext
     * @return The list of KC Notifications
     */
    List<KcNotification> getNotifications(String actionCode, NotificationContext notificationContext);
    
    /**
     * Send the list of KC Notifications. The context is needed to populate context-specific role qualifiers
     * within the Role-based Recipients.
     * 
     * @param notifications
     * @param notificationContext
     */
    void sendNotifications(List<KcNotification> notifications, NotificationContext notificationContext);

}
