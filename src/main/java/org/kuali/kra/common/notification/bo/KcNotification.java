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
package org.kuali.kra.common.notification.bo;

import java.util.ArrayList;
import java.util.List;


public class KcNotification {
    
    private String subject;
    private String message;
    private List<NotificationTypeRecipient> notificationTypeRecipients;
    
    public List<NotificationTypeRecipient> getNotificationTypeRecipients() {
        return notificationTypeRecipients;
    }

    public void setNotificationTypeRecipients(List<NotificationTypeRecipient> notificationTypeRecipients) {
        this.notificationTypeRecipients = notificationTypeRecipients;
    }

    public KcNotification() {
        init();
    }
    
    protected void init() {
        this.setNotificationTypeRecipients(new ArrayList<NotificationTypeRecipient>());
    }
    
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}
