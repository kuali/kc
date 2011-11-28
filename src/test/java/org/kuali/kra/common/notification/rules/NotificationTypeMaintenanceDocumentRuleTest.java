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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.BusinessObjectService;

public class NotificationTypeMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    
    private static final String MODULE_CODE_FIELD_NAME = "moduleCode";
    private static final String ACTION_CODE_FIELD_NAME = "actionCode";
    private static final String ROLE_FIELD_NAME = "notificationTypeRecipients[%d].roleName";
    
    private static final Long NOTIFICATION_TYPE_ID_10000 = 10000L;
    private static final Long NOTIFICATION_TYPE_ID_10001 = 10001L;
    private static final String MODULE_CODE_1 = "1";
    private static final String MODULE_CODE_2 = "2";
    private static final String ACTION_CODE_101 = "101";
    private static final String ACTION_CODE_102 = "102";
    private static final String ROLE_NAME_1 = "NMSPC-1:Role 1";
    private static final String ROLE_NAME_2 = "NMSPC-1:Role 2";
    
    private NotificationTypeMaintenanceDocumentRule rule;
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        rule = new NotificationTypeMaintenanceDocumentRule();
        rule.setBusinessObjectService(getMockBusinessObjectService());
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        
        super.tearDown();
    }
    
    @Test
    public void testModuleCodeActionCodeUnique() throws Exception {
        MaintenanceDocument notificationTypeMaintenanceDocument = getNotificationTypeMaintenanceDocument(NOTIFICATION_TYPE_ID_10001, MODULE_CODE_2, ACTION_CODE_102);
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(notificationTypeMaintenanceDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(notificationTypeMaintenanceDocument));
    }
    
    @Test
    public void testModuleCodeActionCodeNotUnique() throws Exception {
        MaintenanceDocument notificationTypeMaintenanceDocument = getNotificationTypeMaintenanceDocument(NOTIFICATION_TYPE_ID_10000, MODULE_CODE_1, ACTION_CODE_101);
        
        assertFalse(rule.processCustomRouteDocumentBusinessRules(notificationTypeMaintenanceDocument));
        assertFalse(rule.processCustomApproveDocumentBusinessRules(notificationTypeMaintenanceDocument));
        assertFieldErrorExists(ACTION_CODE_FIELD_NAME, KeyConstants.ERROR_NOTIFICATION_MODULE_CODE_ACTION_CODE_COMBINATION_EXISTS);
    }
    
    @Test
    public void testNotificationTypeIdRoleIdRoleNameUnique() throws Exception {
        MaintenanceDocument notificationTypeMaintenanceDocument = getNotificationTypeMaintenanceDocument(NOTIFICATION_TYPE_ID_10001, MODULE_CODE_2, ACTION_CODE_102, ROLE_NAME_2);
    
        assertTrue(rule.processCustomRouteDocumentBusinessRules(notificationTypeMaintenanceDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(notificationTypeMaintenanceDocument));
    }
    
    @Test
    public void testNotificationTypeIdRoleIdRoleNameNotUnique() throws Exception {
        MaintenanceDocument notificationTypeMaintenanceDocument = getNotificationTypeMaintenanceDocument(NOTIFICATION_TYPE_ID_10001, MODULE_CODE_2, ACTION_CODE_102, ROLE_NAME_1);
    
        assertFalse(rule.processCustomRouteDocumentBusinessRules(notificationTypeMaintenanceDocument));
        assertFalse(rule.processCustomApproveDocumentBusinessRules(notificationTypeMaintenanceDocument));
        assertFieldErrorExists(String.format(ROLE_FIELD_NAME, 1), KeyConstants.ERROR_NOTIFICATION_ROLE_NAME_EXISTS);
    }
    
    private MaintenanceDocument getNotificationTypeMaintenanceDocument(Long notificationTypeId, String moduleCode, String actionCode) throws Exception {
        NotificationType notificationType = new NotificationType();
        notificationType.setNotificationTypeId(notificationTypeId);
        notificationType.setModuleCode(moduleCode);
        notificationType.setActionCode(actionCode);
        
        return newMaintDoc(notificationType);
    }
    
    private MaintenanceDocument getNotificationTypeMaintenanceDocument(Long notificationTypeId, String moduleCode, String actionCode, String roleId) throws Exception {
        NotificationType notificationType = new NotificationType();
        notificationType.setNotificationTypeId(notificationTypeId);
        notificationType.setModuleCode(moduleCode);
        notificationType.setActionCode(actionCode);
        
        NotificationTypeRecipient notificationTypeRecipient1 = new NotificationTypeRecipient();
        notificationTypeRecipient1.setRoleName(ROLE_NAME_1);
        
        notificationType.getNotificationTypeRecipients().add(notificationTypeRecipient1);
        
        NotificationTypeRecipient notificationTypeRecipient2 = new NotificationTypeRecipient();
        notificationTypeRecipient2.setRoleName(roleId);
        
        notificationType.getNotificationTypeRecipients().add(notificationTypeRecipient2);
        
        return newMaintDoc(notificationType);
    }
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            Map<String, String> fieldValues1 = new HashMap<String, String>();
            fieldValues1.put(MODULE_CODE_FIELD_NAME, MODULE_CODE_1);
            fieldValues1.put(ACTION_CODE_FIELD_NAME, ACTION_CODE_101);
            
            NotificationType notificationType = new NotificationType();
            notificationType.setModuleCode(MODULE_CODE_1);
            notificationType.setActionCode(ACTION_CODE_101);
            
            allowing(service).findMatching(NotificationType.class, fieldValues1);
            will(returnValue(Collections.singleton(notificationType)));
        }});
        
        context.checking(new Expectations() {{
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(MODULE_CODE_FIELD_NAME, MODULE_CODE_2);
            fieldValues.put(ACTION_CODE_FIELD_NAME, ACTION_CODE_102);
            
            allowing(service).findMatching(NotificationType.class, fieldValues);
            will(returnValue(Collections.EMPTY_LIST));
        }});
        
        return service;
    }

}
