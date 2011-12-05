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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.rule.SendNotificationRule;
import org.kuali.kra.common.notification.rule.event.SendNotificationEvent;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class SendNotificationRuleTest extends KcUnitTestBase {
    
    private static final String SUBJECT = "Subject";
    private static final String MESSAGE = "Message";

    private static final String ROLE_NAME = "KC-UNT:IRB Administrator";
    private static final String PERSON_ID = "10000000004";
    private static final String ROLODEX_ID = "253";

    @Test
    public void testOK() {
        new TemplateRuleTest<SendNotificationEvent, SendNotificationRule>() {
            
            @Override
            protected void prerequisite() {
                KcNotification notification = new KcNotification();
                notification.setSubject(SUBJECT);
                notification.setMessage(MESSAGE);
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(ROLE_NAME, PERSON_ID, ROLODEX_ID);
                
                event = new SendNotificationEvent(null, notification, notificationTypeRecipients);
                rule = new SendNotificationRule();
                expectedReturnValue = true;
            }
            
        };
    }
    
    @Test
    public void testEmptyRecipient() {
        new TemplateRuleTest<SendNotificationEvent, SendNotificationRule>() {
            
            @Override
            protected void prerequisite() {
                KcNotification notification = new KcNotification();
                notification.setSubject(SUBJECT);
                notification.setMessage(MESSAGE);
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(null, null, null);
                
                event = new SendNotificationEvent(null, notification, notificationTypeRecipients);
                rule = new SendNotificationRule();
                expectedReturnValue = false;
            }
            
        };
    }
    
    private List<NotificationTypeRecipient> getDefaultNotificationTypeRecipients(String roleName, String personId, String rolodexId) {
        List<NotificationTypeRecipient> notificationTypeRecipients = new ArrayList<NotificationTypeRecipient>();
        
        if (StringUtils.isNotBlank(roleName)) {
            NotificationTypeRecipient notificationTypeRecipientRoleName = new NotificationTypeRecipient();
            notificationTypeRecipientRoleName.setRoleName(roleName);
            notificationTypeRecipients.add(notificationTypeRecipientRoleName);
        }
        
        if (StringUtils.isNotBlank(personId)) {
            NotificationTypeRecipient notificationTypeRecipientPersonId = new NotificationTypeRecipient();
            notificationTypeRecipientPersonId.setPersonId(personId);
            notificationTypeRecipients.add(notificationTypeRecipientPersonId);
        }
        
        if (StringUtils.isNotBlank(rolodexId)) {
            NotificationTypeRecipient notificationTypeRecipientRolodexId = new NotificationTypeRecipient();
            notificationTypeRecipientRolodexId.setRolodexId(rolodexId);
            notificationTypeRecipients.add(notificationTypeRecipientRolodexId);
        }
        
        return notificationTypeRecipients;
    }
    
}