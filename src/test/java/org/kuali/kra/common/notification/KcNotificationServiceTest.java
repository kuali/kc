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
package org.kuali.kra.common.notification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class KcNotificationServiceTest extends KcUnitTestBase {
    
    protected KcNotificationService notificationService;
    
    @Before
    public void setUp() throws Exception {
        notificationService = KraServiceLocator.getService(KcNotificationService.class);
    }

    @After
    public void tearDown() throws Exception {
        notificationService = null;
    }
    
    @Test
    public void testCreateNotifications() {
//        notificationService.createNotifications("1", "2", "3", new NotificationContext());
    }
    
    @Test
    public void testSaveNotifications() {
//        List<KcNotification> notifications = new ArrayList<KcNotification>();
//        KcNotification notification = new KcNotification();
//        notification.setNotificationTypeId(1L);
//        notification.setDocumentNumber("1");
//        notification.setSubject("foo");
//        notification.setMessage("bar");
//        notifications.add(notification);
//        notificationService.saveNotifications(notifications);
    }
    
    @Test
    public void testGetNotifications() {
//        Set<String> actionCodes = new HashSet<String>();
//        actionCodes.add("3");
//        notificationService.getNotifications("1", "2", actionCodes);
    }
    
    @Test
    public void testSendNotifications() {
//        notificationService.sendNotifications(new ArrayList<KcNotification>(), new NotificationContext());
    }

}
