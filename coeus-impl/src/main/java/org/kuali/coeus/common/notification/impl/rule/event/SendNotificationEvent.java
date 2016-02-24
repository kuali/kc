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

import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.SendNotificationRule;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Represents the event for sending a Notification.
 */
public class SendNotificationEvent extends KcDocumentEventBaseExtension {
    
    private static final String NEW_NOTIFICATION_RECIPIENT_FIELD = "notificationHelper.newNotificationRecipient.";
    
    private KcNotification notification;
    
    private List<NotificationTypeRecipient> notificationTypeRecipients;
    
    /**
     * Constructs a SendNotificationEvent.
     * 
     * @param errorPathPrefix
     * @param document
     * @param specialReview
     */
    public SendNotificationEvent(Document document, KcNotification notification, List<NotificationTypeRecipient> notificationTypeRecipients) {
        super("sending notification", NEW_NOTIFICATION_RECIPIENT_FIELD, document);
        this.notification = notification;
        this.notificationTypeRecipients = notificationTypeRecipients;
    }

    public KcNotification getNotification() {
        return notification;
    }

    public void setNotification(KcNotification notification) {
        this.notification = notification;
    }

    public List<NotificationTypeRecipient> getNotificationTypeRecipients() {
        return notificationTypeRecipients;
    }

    public void setNotificationTypeRecipients(List<NotificationTypeRecipient> notificationTypeRecipients) {
        this.notificationTypeRecipients = notificationTypeRecipients;
    }

    @Override
    public KcBusinessRule<SendNotificationEvent> getRule() {
        return new SendNotificationRule();
    }

}
