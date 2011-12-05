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
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.rule.AddNotificationRecipientRule;
import org.kuali.kra.common.notification.rule.event.AddNotificationRecipientEvent;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class AddNotificationTypeRecipientRuleTest extends KcUnitTestBase {
    
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