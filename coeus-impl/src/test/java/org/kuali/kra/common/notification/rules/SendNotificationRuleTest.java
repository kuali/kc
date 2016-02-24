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
package org.kuali.kra.common.notification.rules;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.SendNotificationRule;
import org.kuali.coeus.common.notification.impl.rule.event.SendNotificationEvent;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.ArrayList;
import java.util.List;

public class SendNotificationRuleTest  {
    
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
                rule.setErrorReporter(new ErrorReporterImpl());
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
                rule.setErrorReporter(new ErrorReporterImpl());
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
