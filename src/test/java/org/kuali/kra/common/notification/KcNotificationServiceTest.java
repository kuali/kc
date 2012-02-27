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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcEmailService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.util.EmailAttachment;
import org.kuali.rice.ken.bo.Notification;
import org.kuali.rice.ken.service.NotificationService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class KcNotificationServiceTest extends KcUnitTestBase {
    
    private static final String MODULE_CODE_FIELD = "moduleCode";
    private static final String ACTION_CODE_FIELD = "actionCode";
    private static final String DOCUMENT_NUMBER_FIELD = "documentNumber";
    private static final String NOTIFICATION_TYPE_ID_FIELD = "notificationTypeId";
    
    private static final String MODULE_CODE_VALUE = "1";
    private static final String ACTION_TYPE_CODE_VALUE_101 = "101";
    private static final String ACTION_TYPE_CODE_VALUE_102 = "102";
    private static final String DOCUMENT_NUMBER_VALUE = "1";
    private static final String CONTEXT_NAME_VALUE = "Notification Context";
    private static final Long NOTIFICATION_TYPE_ID_VALUE = 1L;
    private static final String SUBJECT_VALUE = "Message Subject";
    private static final String MESSAGE_VALUE = "Message Text";
    private static final String PRINCIPAL_ID_VALUE_QUICKSTART = "10000000001";
    private static final String PRINCIPAL_NAME_VALUE_JTESTER = "jtester";
    private static final String PRINCIPAL_NAME_VALUE_MAJORS = "majors";
    private static final String ROLODEX_ID_VALUE_UNIVERSITY = "1";
    private static final String EMAIL_ADDRESS_VALUE_QUICKSTART = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_VALUE_JTESTER = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_VALUE_MAJORS = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_VALUE_APP_ADMIN = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_UNIVERSITY = "firstname@kuali.org";
    private static final String DEFAULT_FROM_ADDRESS_VALUE = "bogus@kuali.org";
    
    private Mockery context = new JUnit4Mockery();
    
    private KcNotificationServiceImpl service;
    
    @Before
    public void setUp() throws Exception {
        service = new KcNotificationServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }
    
    @Test
    public void testGetNotificationTypeFromContext() throws Exception {
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        
        NotificationContext notificationContext = getMockNotificationContext();
        NotificationType notificationType = service.getNotificationType(notificationContext);
        
        assertEquals(MODULE_CODE_VALUE, notificationType.getModuleCode());
        assertEquals(ACTION_TYPE_CODE_VALUE_101, notificationType.getActionCode());
        
        context.assertIsSatisfied();
    }
    
    @Test
    public void testGetNotificationTypeFromCodes() throws Exception {
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        
        NotificationType notificationType = service.getNotificationType(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101);
        
        assertEquals(MODULE_CODE_VALUE, notificationType.getModuleCode());
        assertEquals(ACTION_TYPE_CODE_VALUE_101, notificationType.getActionCode());
        
        context.assertIsSatisfied();
    }
    
    @Test
    public void testCreateNotification() throws Exception {
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        
        NotificationContext notificationContext = getMockNotificationContext();
        KcNotification notification = service.createNotification(notificationContext);
        
        assertEquals(DOCUMENT_NUMBER_VALUE, notification.getDocumentNumber());
        assertEquals(SUBJECT_VALUE, notification.getSubject());
        assertEquals(MESSAGE_VALUE, notification.getMessage());
        
        context.assertIsSatisfied();
    }
    
    @Test
    public void testSaveNotification() throws Exception {
        service.setBusinessObjectService(getMockSaveBusinessObjectService());
        
        KcNotification notification = new KcNotification();
        notification.setDocumentNumber(DOCUMENT_NUMBER_VALUE);
        notification.setSubject(SUBJECT_VALUE);
        notification.setMessage(MESSAGE_VALUE);
        
        service.saveNotification(notification);
        
        context.assertIsSatisfied();
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetNotifications() throws Exception {
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101, ACTION_TYPE_CODE_VALUE_102));
        
        Set<String> actionTypeCodes = new HashSet<String>();
        actionTypeCodes.add(ACTION_TYPE_CODE_VALUE_101);
        actionTypeCodes.add(ACTION_TYPE_CODE_VALUE_102);

        List<KcNotification> notifications = service.getNotifications(DOCUMENT_NUMBER_VALUE, MODULE_CODE_VALUE, actionTypeCodes);
        
        for (KcNotification notification : notifications) {
            NotificationType notificationType = notification.getNotificationType();
            assertEquals(MODULE_CODE_VALUE, notificationType.getModuleCode());
            assertThat(notificationType.getActionCode(), Matchers.anyOf(new IsEqual<String>(ACTION_TYPE_CODE_VALUE_101), 
                                                                        new IsEqual<String>(ACTION_TYPE_CODE_VALUE_102)));
            assertEquals(SUBJECT_VALUE, notification.getSubject());
            assertEquals(MESSAGE_VALUE, notification.getMessage());
        }
        
        context.assertIsSatisfied();
    }
    
    @Test
    public void testSendNotificationFromContext() throws Exception {
        Set<String> personEmailAddresses = new HashSet<String>();
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_APP_ADMIN);
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_QUICKSTART);
        
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        service.setParameterService(getParameterService());
        service.setNotificationService(getMockNotificationService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses));
        service.setRoleManagementService(KraServiceLocator.getService(RoleService.class));
        service.setKcPersonService(KraServiceLocator.getService(KcPersonService.class));
        service.setIdentityService(KraServiceLocator.getService(IdentityService.class));
        
        NotificationContext notificationContext = getMockNotificationContext();
        
        service.sendNotification(notificationContext);
        
        context.assertIsSatisfied();
    }
    
    @Test
    public void testSendNotificationFromContextWithRecipients() throws Exception {
        Set<String> personEmailAddresses = new HashSet<String>();
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_APP_ADMIN);
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_QUICKSTART);
        
        Set<String> rolodexEmailAddresses = new HashSet<String>();
        rolodexEmailAddresses.add(EMAIL_ADDRESS_UNIVERSITY);

        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        service.setParameterService(getParameterService());
        service.setNotificationService(getMockNotificationService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses, rolodexEmailAddresses));
        service.setKcPersonService(KraServiceLocator.getService(KcPersonService.class));
        service.setRolodexService(KraServiceLocator.getService(RolodexService.class));
        service.setIdentityService(KraServiceLocator.getService(IdentityService.class));
        
        NotificationContext notificationContext = getMockNotificationContext();
        KcNotification notification = new KcNotification();
        notification.setDocumentNumber(DOCUMENT_NUMBER_VALUE);
        notification.setSubject(SUBJECT_VALUE);
        notification.setMessage(MESSAGE_VALUE);
        
        List<NotificationTypeRecipient> notificationTypeRecipients = new ArrayList<NotificationTypeRecipient>();
        NotificationTypeRecipient person = new NotificationTypeRecipient();
        person.setPersonId(PRINCIPAL_ID_VALUE_QUICKSTART);
        notificationTypeRecipients.add(person);
        NotificationTypeRecipient rolodex = new NotificationTypeRecipient();
        rolodex.setRolodexId(ROLODEX_ID_VALUE_UNIVERSITY);
        notificationTypeRecipients.add(rolodex);
        
        service.sendNotification(notificationContext, notification, notificationTypeRecipients);
        
        context.assertIsSatisfied();
    }
    
    @Test
    public void testSendNotificationWithPrincipalNames() throws Exception {
        Set<String> personEmailAddresses = new HashSet<String>();
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_JTESTER);
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_MAJORS);
        
        service.setParameterService(getParameterService());
        service.setNotificationService(getMockNotificationService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses));
        service.setIdentityService(KraServiceLocator.getService(IdentityService.class));
        
        List<String> principalIds = new ArrayList<String>();
        principalIds.add(PRINCIPAL_NAME_VALUE_JTESTER);
        principalIds.add(PRINCIPAL_NAME_VALUE_MAJORS);
        
        service.sendNotification(CONTEXT_NAME_VALUE, SUBJECT_VALUE, MESSAGE_VALUE, principalIds);
        
        context.assertIsSatisfied();
    }
    
    @Test
    public void testSendEmailNotification() throws Exception {
        Set<String> personEmailAddresses = new HashSet<String>();
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_APP_ADMIN);
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_QUICKSTART);
        
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        service.setParameterService(getParameterService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses));
        service.setRoleManagementService(KraServiceLocator.getService(RoleService.class));
        service.setKcPersonService(KraServiceLocator.getService(KcPersonService.class));
        service.setIdentityService(KraServiceLocator.getService(IdentityService.class));
        
        NotificationContext notificationContext = getMockNotificationContext();
                
        service.sendEmailNotification(notificationContext);
        
        context.assertIsSatisfied();
    }
    
    private NotificationContext getMockNotificationContext() throws UnknownRoleException {
        final NotificationContext notificationContext = context.mock(NotificationContext.class);
        context.checking(new Expectations() {{
            allowing(notificationContext).getModuleCode(); 
            will(returnValue(MODULE_CODE_VALUE));
            
            allowing(notificationContext).getActionTypeCode(); 
            will(returnValue(ACTION_TYPE_CODE_VALUE_101));
            
            allowing(notificationContext).getDocumentNumber();
            will(returnValue(DOCUMENT_NUMBER_VALUE));
            
            allowing(notificationContext).getContextName();
            will(returnValue(CONTEXT_NAME_VALUE));
            
            allowing(notificationContext).replaceContextVariables(SUBJECT_VALUE);
            will(returnValue(SUBJECT_VALUE));
            
            allowing(notificationContext).replaceContextVariables(MESSAGE_VALUE);
            will(returnValue(MESSAGE_VALUE));
            
            allowing(notificationContext).populateRoleQualifiers(with(any(NotificationTypeRecipient.class)));
            
            allowing(notificationContext).getEmailAttachments();
            will(returnValue(Collections.<EmailAttachment>emptyList()));
        }});
        return notificationContext;
    }
    
    @SuppressWarnings("unchecked")
    private BusinessObjectService getMockSearchBusinessObjectService(final String moduleCode, final String... actionTypeCodes) {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            
            List<Matcher<Map<String, String>>> matchers = new ArrayList<Matcher<Map<String, String>>>();
            for (String actionTypeCode : actionTypeCodes) {
                Map<String, String> fieldValues = new HashMap<String, String>();
                fieldValues.put(MODULE_CODE_FIELD, moduleCode);
                fieldValues.put(ACTION_CODE_FIELD, actionTypeCode);
                matchers.add(equal(fieldValues));
            }
            Matcher<Map<String, String>>[] matcherArray = matchers.toArray(new Matcher[matchers.size()]);
            
            List<NotificationType> notificationTypes = new ArrayList<NotificationType>();
            for (String actionTypeCode : actionTypeCodes) {
                NotificationType notificationType = new NotificationType();
                notificationType.setNotificationTypeId(NOTIFICATION_TYPE_ID_VALUE);
                notificationType.setModuleCode(moduleCode);
                notificationType.setActionCode(actionTypeCode);
                notificationType.setSubject(SUBJECT_VALUE);
                notificationType.setMessage(MESSAGE_VALUE);
                notificationType.setActive(true);
                
                List<NotificationTypeRecipient> notificationTypeRecipients = new ArrayList<NotificationTypeRecipient>();
                NotificationTypeRecipient notificationTypeRecipient = new NotificationTypeRecipient();
                notificationTypeRecipient.setRoleName("KC-SYS:KC Superuser");
                notificationTypeRecipient.setRoleQualifiers(new HashMap<String, String>());
                notificationTypeRecipients.add(notificationTypeRecipient);
                
                notificationType.setNotificationTypeRecipients(notificationTypeRecipients);
                notificationTypes.add(notificationType);
            }
            
            allowing(service).findMatching(with(any(Class.class)), with(Matchers.anyOf(matcherArray))); 
            will(returnValue(notificationTypes));
            
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(NOTIFICATION_TYPE_ID_FIELD, NOTIFICATION_TYPE_ID_VALUE.toString());
            fieldValues.put(DOCUMENT_NUMBER_FIELD, DOCUMENT_NUMBER_VALUE);
            
            List<KcNotification> notifications = new ArrayList<KcNotification>();
            for (NotificationType notificationType : notificationTypes) {
                KcNotification notification = new KcNotification();
                notification.setDocumentNumber(DOCUMENT_NUMBER_VALUE);
                notification.setSubject(SUBJECT_VALUE);
                notification.setMessage(MESSAGE_VALUE);
                notification.setNotificationType(notificationType);
                notifications.add(notification);
            }
            
            allowing(service).findMatching(KcNotification.class, fieldValues);
            will(returnValue(notifications));
        }});
        return service;
    }
    
    private BusinessObjectService getMockSaveBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            oneOf(service).save(with(any(KcNotification.class)));
        }});
        return service;
    }
    
    protected ParameterService getParameterService() {
        final ParameterService parameterService = context.mock(ParameterService.class);
        context.checking(new Expectations() {{
            allowing(parameterService).getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                                                         "NORMAL_NOTIFICATION_PRIORITY_ID"); 
            will(returnValue("1"));
            
            allowing(parameterService).getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                                                         "SIMPLE_NOTIFICATION_CONTENT_TYPE_ID"); 
            will(returnValue("1"));
            
            allowing(parameterService).getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                                                         "SYSTEM_NOTIFICATION_PRODUCER_ID"); 
            will(returnValue("1"));
            
            allowing(parameterService).getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                                                         "KC_NOTIFICATION_CHANNEL_ID"); 
            will(returnValue("1"));
            
            allowing(parameterService).getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                                                         "ACTION_LIST_DEFAULT_FROM_USER"); 
            will(returnValue("admin"));
            
            allowing(parameterService).getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                                                             "EMAIL_NOTIFICATIONS_ENABLED"); 
            will(returnValue(true));
        }});
        return parameterService;
    }
    
    private NotificationService getMockNotificationService() {
        final NotificationService service = context.mock(NotificationService.class);
        context.checking(new Expectations() {{
            oneOf(service).sendNotification(with(any(Notification.class)));
        }});
        return service;
    }
    
    private KcEmailService getMockKcEmailService(final Set<String> personEmailAddresses) {
        final KcEmailService service = context.mock(KcEmailService.class);
        context.checking(new Expectations() {{
            allowing(service).getDefaultFromAddress();
            will(returnValue(DEFAULT_FROM_ADDRESS_VALUE));
            
            oneOf(service).sendEmailWithAttachments(DEFAULT_FROM_ADDRESS_VALUE, personEmailAddresses, SUBJECT_VALUE, null, null, MESSAGE_VALUE, true, 
                                                    Collections.<EmailAttachment>emptyList());
        }});
        return service;
    }
    
    private KcEmailService getMockKcEmailService(final Set<String> personEmailAddresses, final Set<String> rolodexEmailAddresses) {
        final KcEmailService service = context.mock(KcEmailService.class);
        context.checking(new Expectations() {{
            allowing(service).getDefaultFromAddress();
            will(returnValue(DEFAULT_FROM_ADDRESS_VALUE));
            
            oneOf(service).sendEmailWithAttachments(DEFAULT_FROM_ADDRESS_VALUE, personEmailAddresses, SUBJECT_VALUE, null, null, MESSAGE_VALUE, true, 
                                                    Collections.<EmailAttachment>emptyList());
            
            oneOf(service).sendEmailWithAttachments(DEFAULT_FROM_ADDRESS_VALUE, rolodexEmailAddresses, SUBJECT_VALUE, null, null, MESSAGE_VALUE, true, 
                                                    Collections.<EmailAttachment>emptyList());
        }});
        return service;
    }

}