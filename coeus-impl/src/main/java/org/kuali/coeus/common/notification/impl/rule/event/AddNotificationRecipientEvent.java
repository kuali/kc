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
package org.kuali.coeus.common.notification.impl.rule.event;

import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.AddNotificationRecipientRule;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Represents the event for adding a Notification Type Recipient.
 */
public class AddNotificationRecipientEvent extends KcDocumentEventBaseExtension {
    
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
    public KcBusinessRule<AddNotificationRecipientEvent> getRule() {
        return new AddNotificationRecipientRule();
    }

}
