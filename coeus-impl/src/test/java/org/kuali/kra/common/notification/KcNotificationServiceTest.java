/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.common.notification;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.mail.KcEmailService;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.notification.impl.NotificationContext;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;
import org.kuali.coeus.common.notification.impl.service.impl.KcNotificationServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.ken.api.notification.Notification;
import org.kuali.rice.ken.api.notification.NotificationRecipient;
import org.kuali.rice.ken.api.service.SendNotificationService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

import com.google.common.collect.Lists;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
public class KcNotificationServiceTest {

    private static final String PRINCIPAL_ID_VALUE_QUICKSTART = "10000000001";
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
    private static final String PRINCIPAL_ID_VALUE_CHEW = "10000000005";
    private static final String PRINCIPAL_NAME_VALUE_JTESTER = "jtester";
    private static final String PRINCIPAL_NAME_VALUE_MAJORS = "majors";
    private static final String ROLODEX_ID_VALUE_UNIVERSITY = "1";
    private static final String EMAIL_ADDRESS_VALUE_CHEW = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_VALUE_JTESTER = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_VALUE_MAJORS = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_VALUE_APP_ADMIN = "kcnotification@gmail.com";
    private static final String EMAIL_ADDRESS_UNIVERSITY = "firstname@kuali.org";
    private static final String DEFAULT_FROM_ADDRESS_VALUE = "bogus@kuali.org";
    private static final String TRUE = "true";
    private static final String ACTIVE = "active";

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    private KcNotificationServiceImpl service;
    
    @Before
    public void setUp() throws Exception {
        service = new KcNotificationServiceImpl() {
        	@Override
        	protected String getCreateUser() {
        		return PRINCIPAL_NAME_VALUE_MAJORS;
        	}
        	@Override
        	protected Timestamp getCreateTimestamp() {
        		return new Timestamp(5000L);
        	}
        	@Override
        	protected Collection<String> getRoleMemberPrincipalIds(String roleNamespace, String roleName, final Map<String, String> roleQualifiers) {
        		return Lists.<String>newArrayList(PRINCIPAL_ID_VALUE_CHEW, PRINCIPAL_ID_VALUE_QUICKSTART);
        	}
        	@Override
        	protected String getPersonUserName(String personId) {
        		return PRINCIPAL_NAME_VALUE_JTESTER;
        	}
        	@Override
        	protected Set<String> getRecipientEmailAddresses(NotificationRecipient.Builder recipient) {
        		Set<String> addresses = new HashSet<>();
        		addresses.add(EMAIL_ADDRESS_VALUE_JTESTER);
        		return addresses;
        	}
        	@Override
        	protected String getRolodexEmailAddress(final String rolodexId) {
        		return EMAIL_ADDRESS_UNIVERSITY;
        	}
        };
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
        KcNotification notification = service.createNotificationObject(notificationContext);
        
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
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_CHEW);
        
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        service.setParameterService(getParameterService());
        service.setSendNotificationService(getMockNotificationService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses));
        service.setRoleManagementService(KcServiceLocator.getService(RoleService.class));
        service.setKcPersonService(KcServiceLocator.getService(KcPersonService.class));
        service.setIdentityService(KcServiceLocator.getService(IdentityService.class));
        
        NotificationContext notificationContext = getMockNotificationContext();
        
        service.sendNotification(notificationContext);
        TimeUnit.SECONDS.sleep(5);
        context.assertIsSatisfied();
    }
    
    @Test
    public void testSendNotificationFromContextWithRecipients() throws Exception {
        Set<String> personEmailAddresses = new HashSet<String>();
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_APP_ADMIN);
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_CHEW);
        
        Set<String> rolodexEmailAddresses = new HashSet<String>();
        rolodexEmailAddresses.add(EMAIL_ADDRESS_UNIVERSITY);

        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        service.setParameterService(getParameterService());
        service.setSendNotificationService(getMockNotificationService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses, rolodexEmailAddresses));
        service.setKcPersonService(KcServiceLocator.getService(KcPersonService.class));
        service.setRolodexService(KcServiceLocator.getService(RolodexService.class));
        service.setIdentityService(KcServiceLocator.getService(IdentityService.class));
        
        NotificationContext notificationContext = getMockNotificationContext();
        KcNotification notification = new KcNotification();
        notification.setDocumentNumber(DOCUMENT_NUMBER_VALUE);
        notification.setSubject(SUBJECT_VALUE);
        notification.setMessage(MESSAGE_VALUE);
        
        List<NotificationTypeRecipient> notificationTypeRecipients = new ArrayList<NotificationTypeRecipient>();
        NotificationTypeRecipient person = new NotificationTypeRecipient();
        person.setPersonId(PRINCIPAL_ID_VALUE_CHEW);
        notificationTypeRecipients.add(person);
        NotificationTypeRecipient rolodex = new NotificationTypeRecipient();
        rolodex.setRolodexId(ROLODEX_ID_VALUE_UNIVERSITY);
        notificationTypeRecipients.add(rolodex);
        
        service.sendNotification(notificationContext, notification, notificationTypeRecipients);
        TimeUnit.SECONDS.sleep(5);

        context.assertIsSatisfied();
    }
    
    @Test
    public void testSendNotificationWithPrincipalNames() throws Exception {
        Set<String> personEmailAddresses = new HashSet<String>();
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_JTESTER);
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_MAJORS);
        
        service.setParameterService(getParameterService());
        service.setSendNotificationService(getMockNotificationService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses));
        service.setIdentityService(KcServiceLocator.getService(IdentityService.class));
        
        List<String> principalIds = new ArrayList<String>();
        principalIds.add(PRINCIPAL_NAME_VALUE_JTESTER);
        principalIds.add(PRINCIPAL_NAME_VALUE_MAJORS);
        
        service.sendNotification(CONTEXT_NAME_VALUE, SUBJECT_VALUE, MESSAGE_VALUE, principalIds);
        TimeUnit.SECONDS.sleep(5);

        context.assertIsSatisfied();
    }
    
    @Test
    public void testSendEmailNotification() throws Exception {
        Set<String> personEmailAddresses = new HashSet<String>();
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_APP_ADMIN);
        personEmailAddresses.add(EMAIL_ADDRESS_VALUE_CHEW);
        
        service.setBusinessObjectService(getMockSearchBusinessObjectService(MODULE_CODE_VALUE, ACTION_TYPE_CODE_VALUE_101));
        service.setParameterService(getParameterService());
        service.setKcEmailService(getMockKcEmailService(personEmailAddresses));
        service.setRoleManagementService(KcServiceLocator.getService(RoleService.class));
        service.setKcPersonService(KcServiceLocator.getService(KcPersonService.class));
        service.setIdentityService(KcServiceLocator.getService(IdentityService.class));
        
        NotificationContext notificationContext = getMockNotificationContext();
                
        service.sendEmailNotification(notificationContext);
        TimeUnit.SECONDS.sleep(5);

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
                fieldValues.put(ACTIVE, TRUE);
                matchers.add(equal(fieldValues));
            }
            Matcher<Map<String, ?>>[] matcherArray = matchers.toArray(new Matcher[matchers.size()]);
            
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
            allowing(service).<BusinessObject>findMatching(with(any(Class.class)), with(Matchers.anyOf(matcherArray)));
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
            
            allowing(parameterService).getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                                                            "KcNotificationDocumentTypeName"); 
            will(returnValue("KcNotificationDocument"));
        }});
        return parameterService;
    }
    
    private SendNotificationService getMockNotificationService() {
        final SendNotificationService service = context.mock(SendNotificationService.class);
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
