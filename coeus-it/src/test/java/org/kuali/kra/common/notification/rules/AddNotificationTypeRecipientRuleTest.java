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
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.AddNotificationRecipientRule;
import org.kuali.coeus.common.notification.impl.rule.event.AddNotificationRecipientEvent;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;
import java.util.List;

public class AddNotificationTypeRecipientRuleTest extends KcIntegrationTestBase {
    
    private static final String ROLE_NAME = "KC-UNT:IRB Administrator";
    private static final String PERSON_ID = "10000000004";
    private static final String ROLODEX_ID = "253";
    
    private static final String NEW_ROLE_NAME = "KC-PROTOCOL:Protocol Aggregator";
    private static final String NEW_PERSON_ID = "10000000002";
    private static final String NEW_ROLODEX_ID = "254";

    @Test
    public void testNewRoleNameOK() {
        new TemplateRuleTest<AddNotificationRecipientEvent, AddNotificationRecipientRule>() {
            
            @Override
            protected void prerequisite() {
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                notificationTypeRecipient.setRoleName(NEW_ROLE_NAME);
                
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(ROLE_NAME, null, null);
                
                event = new AddNotificationRecipientEvent(null, notificationTypeRecipient, notificationTypeRecipients);
                rule = new AddNotificationRecipientRule();
                expectedReturnValue = true;
            }
            
        };
    }
    
    @Test
    public void testNewPersonIdOK() {
        new TemplateRuleTest<AddNotificationRecipientEvent, AddNotificationRecipientRule>() {
            
            @Override
            protected void prerequisite() {
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                notificationTypeRecipient.setPersonId(NEW_PERSON_ID);
                
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(null, PERSON_ID, null);
                
                event = new AddNotificationRecipientEvent(null, notificationTypeRecipient, notificationTypeRecipients);
                rule = new AddNotificationRecipientRule();
                expectedReturnValue = true;
            }
            
        };
    }
    
    @Test
    public void testNewRolodexIdOK() {
        new TemplateRuleTest<AddNotificationRecipientEvent, AddNotificationRecipientRule>() {
            
            @Override
            protected void prerequisite() {
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                notificationTypeRecipient.setRoleName(NEW_ROLODEX_ID);
                
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(null, null, ROLODEX_ID);
                
                event = new AddNotificationRecipientEvent(null, notificationTypeRecipient, notificationTypeRecipients);
                rule = new AddNotificationRecipientRule();
                expectedReturnValue = true;
            }
            
        };
    }
    
    @Test
    public void testInvalidRecipient() {
        new TemplateRuleTest<AddNotificationRecipientEvent, AddNotificationRecipientRule>() {
            
            @Override
            protected void prerequisite() {
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(ROLE_NAME, null, null);
                
                event = new AddNotificationRecipientEvent(null, notificationTypeRecipient, notificationTypeRecipients);
                rule = new AddNotificationRecipientRule();
                expectedReturnValue = false;
            }
            
        };
    }
    
    @Test
    public void testDuplicateRoles() {
        new TemplateRuleTest<AddNotificationRecipientEvent, AddNotificationRecipientRule>() {
            
            @Override
            protected void prerequisite() {
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                notificationTypeRecipient.setRoleName(ROLE_NAME);
                
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(ROLE_NAME, null, null);
                
                event = new AddNotificationRecipientEvent(null, notificationTypeRecipient, notificationTypeRecipients);
                rule = new AddNotificationRecipientRule();
                expectedReturnValue = false;
            }
            
        };
    }
    
    @Test
    public void testDuplicatePersonIds() {
        new TemplateRuleTest<AddNotificationRecipientEvent, AddNotificationRecipientRule>() {
            
            @Override
            protected void prerequisite() {
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                notificationTypeRecipient.setPersonId(PERSON_ID);
                
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(null, PERSON_ID, null);
                
                event = new AddNotificationRecipientEvent(null, notificationTypeRecipient, notificationTypeRecipients);
                rule = new AddNotificationRecipientRule();
                expectedReturnValue = false;
            }
            
        };
    }
    
    @Test
    public void testDuplicateRolodexIds() {
        new TemplateRuleTest<AddNotificationRecipientEvent, AddNotificationRecipientRule>() {
            
            @Override
            protected void prerequisite() {
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                notificationTypeRecipient.setRolodexId(ROLODEX_ID);
                
                List<NotificationTypeRecipient> notificationTypeRecipients = getDefaultNotificationTypeRecipients(null, null, ROLODEX_ID);
                
                event = new AddNotificationRecipientEvent(null, notificationTypeRecipient, notificationTypeRecipients);
                rule = new AddNotificationRecipientRule();
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
