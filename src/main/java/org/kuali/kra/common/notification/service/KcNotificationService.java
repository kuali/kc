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
import java.util.Set;

import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;

/**
 * Defines methods for creating and sending KC Notifications.
 */
public interface KcNotificationService {
    
    /**
     * Returns the Notification Type based on a Notification Context.
     * 
     * @param context
     * @return The Notification Type
     */
    NotificationType getNotificationType(NotificationContext context);
    
    /**
     * Returns the Notification Type based on a Module Code and Action Code.
     * 
     * @param moduleCode
     * @param actionCode
     * @return The Notification Type
     */
    NotificationType getNotificationType(String moduleCode, String actionCode);
    
    /**
     * Creates a KC Notification based on the Notification Context.  The Notification Type associated with the Module Code and Action Code is 
     * retrieved from persistent storage and translated into a context-specific KC Notification instance based on the Notification Context.
     * 
     * @param notificationContext
     * @return The KC Notification
     */
    KcNotification createNotification(NotificationContext notificationContext);
    
    /**
     * Saves a KC Notifications.
     * 
     * @param notification
     */
    void saveNotification(KcNotification notification);
    
    /**
     * Retrieves a list of KC Notifications based on a Document Number, a Module Code, and a set of Action Codes.
     * 
     * @param documentNumber
     * @param moduleCode
     * @param actionCodes
     * @return The list of KC Notifications
     */
    List<KcNotification> getNotifications(String documentNumber, String moduleCode, Set<String> actionCodes);
    
    /**
     * Send an unedited KC Notification, using the context to populate context-specific role qualifiers within the Role-based Recipients.
     * 
     * @param notificationContext
     */
    void sendNotification(NotificationContext notificationContext);
    
    /**
     * Send a previously saved or edited KC Notification, using the context to populate context-specific role qualifiers within the Role-based Recipients and 
     * additionally sending the notification to other non-role users.
     * 
     * @param notificationContext
     * @param notification
     * @param notificationRecipients
     */
    void sendNotification(NotificationContext notificationContext, KcNotification notification, List<NotificationTypeRecipient> notificationRecipients);

    /**
     * Send an unedited KC Notification to {@code principalNames} using the given {@code subject} and {@code message}.
     * 
     * @param contextName
     * @param subject
     * @param message
     * @param principalNames
     */
    void sendNotification(String contextName, String subject, String message, List<String> principalNames);
    
}