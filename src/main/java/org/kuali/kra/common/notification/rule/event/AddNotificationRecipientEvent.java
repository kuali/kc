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
package org.kuali.kra.common.notification.rule.event;

import java.util.List;

import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.rule.AddNotificationRecipientRule;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.kns.document.Document;

/**
 * Represents the event for adding a Notification Type Recipient.
 */
public class AddNotificationRecipientEvent extends KraDocumentEventBaseExtension {
    
    private static final String NEW_NOTIFICATION_RECIPIENT_FIELD = "notificationHelper.newNotificationRecipient.";
    
    private NotificationTypeRecipient notificationTypeRecipient;
    
    private List<NotificationTypeRecipient> notificationTypeRecipients;
    
    /**
     * Constructs an AddNotificationRecipientEvent.
     * 
     * @param document The parent document
     * @param notificationTypeRecipient The Notification Type Recipient object to validate
     * @param notificationTypeRecipients The existing Notification Type Recipient objects
     */
    public AddNotificationRecipientEvent(Document document, NotificationTypeRecipient notificationTypeRecipient, 
                                         List<NotificationTypeRecipient> notificationTypeRecipients) {
        super("adding notification type recipient", NEW_NOTIFICATION_RECIPIENT_FIELD, document);
        this.notificationTypeRecipient = notificationTypeRecipient;
        this.notificationTypeRecipients = notificationTypeRecipients;
    }
    
    public NotificationTypeRecipient getNotificationTypeRecipient() {
        return notificationTypeRecipient;
    }

    public void setNotificationTypeRecipient(NotificationTypeRecipient notificationTypeRecipient) {
        this.notificationTypeRecipient = notificationTypeRecipient;
    }

    public List<NotificationTypeRecipient> getNotificationTypeRecipients() {
        return notificationTypeRecipients;
    }

    public void setNotificationTypeRecipients(List<NotificationTypeRecipient> notificationTypeRecipients) {
        this.notificationTypeRecipients = notificationTypeRecipients;
    }

    @Override
    public BusinessRuleInterface<AddNotificationRecipientEvent> getRule() {
        return new AddNotificationRecipientRule();
    }

}