/*
 * Copyright 2005-2014 The Kuali Foundation
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