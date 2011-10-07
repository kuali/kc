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
package org.kuali.kra.common.notification.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.ken.bo.Notification;
import org.kuali.rice.ken.bo.NotificationChannel;
import org.kuali.rice.ken.bo.NotificationContentType;
import org.kuali.rice.ken.bo.NotificationPriority;
import org.kuali.rice.ken.bo.NotificationProducer;
import org.kuali.rice.ken.bo.NotificationRecipient;
import org.kuali.rice.ken.service.NotificationService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.RoleManagementService;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;

public class KcNotificationServiceImpl implements KcNotificationService {
    
    private static final String MODULE_CODE = "moduleCode";
    private static final String ACTION_CODE = "actionCode";
    private static final String NOTIFICATION_TYPE_ID = "notificationTypeId";
    private static final String DOCUMENT_NUMBER = "documentNumber";
    
    private static final Log LOG = LogFactory.getLog(KcNotificationServiceImpl.class);
    
    protected NotificationChannel kcNotificationChannel;
    protected NotificationProducer systemNotificationProducer;
    
    private BusinessObjectService businessObjectService;
    private NotificationService notificationService;
    private RoleManagementService roleManagementService;
    private KcPersonService kcPersonService;
    private ParameterService parameterService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#createNotifications(java.lang.String, java.lang.String, java.lang.String, 
     *      org.kuali.kra.common.notification.NotificationContext)
     */
    @SuppressWarnings("unchecked")
    public List<KcNotification> createNotifications(String documentNumber, String moduleCode, String actionCode, NotificationContext context) {
        List<KcNotification> notifications = new ArrayList<KcNotification>();
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_CODE, moduleCode);
        fieldValues.put(ACTION_CODE, actionCode);
        Collection<NotificationType> notificationTypes = getBusinessObjectService().findMatching(NotificationType.class, fieldValues);
        
        for (NotificationType notificationType : notificationTypes) {
            KcNotification notification = new KcNotification();
            notification.setNotificationTypeId(notificationType.getNotificationTypeId());
            notification.setDocumentNumber(documentNumber);
            String instanceSubject = context.replaceContextVariables(notificationType.getSubject());
            notification.setSubject(instanceSubject);
            String instanceMessage = context.replaceContextVariables(notificationType.getMessage());
            notification.setMessage(instanceMessage);
            notification.setNotificationType(notificationType);
            notifications.add(notification);
        }
        
        return notifications;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#saveNotifications(java.util.List)
     */
    public void saveNotifications(List<KcNotification> notifications) {
        getBusinessObjectService().save(notifications);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#getNotifications(java.lang.String, java.lang.String, java.util.Set)
     */
    @SuppressWarnings("unchecked")
    public List<KcNotification> getNotifications(String documentNumber, String moduleCode, Set<String> actionCodes) {
        List<KcNotification> notifications = new ArrayList<KcNotification>();
        
        for (String actionCode : actionCodes) {
            Map<String, String> notificationTypeFieldValues = new HashMap<String, String>();
            notificationTypeFieldValues.put(MODULE_CODE, moduleCode);
            notificationTypeFieldValues.put(ACTION_CODE, actionCode);
            Collection<NotificationType> notificationTypes = getBusinessObjectService().findMatching(NotificationType.class, notificationTypeFieldValues);
            
            for (NotificationType notificationType : notificationTypes) {
                Map<String, String> notificationFieldValues = new HashMap<String, String>();
                notificationFieldValues.put(NOTIFICATION_TYPE_ID, notificationType.getNotificationTypeId().toString());
                notificationFieldValues.put(DOCUMENT_NUMBER, documentNumber);
                notifications.addAll(getBusinessObjectService().findMatching(KcNotification.class, notificationFieldValues));
            }
        }
        
        return notifications;
    }
    
    public void sendNotifications(List<KcNotification> kcNotifications, NotificationContext context) {
        
        List<Notification> notifications = new ArrayList<Notification>();
        for (KcNotification kcNotification : kcNotifications) {
            Notification notification = new Notification();
            
            NotificationPriority priority = new NotificationPriority();
            priority.setId(getNotificationParameter("NORMAL_NOTIFICATION_PRIORITY_ID"));
            notification.setPriority(priority);
            
            NotificationContentType contentType = new NotificationContentType();
            contentType.setId(getNotificationParameter("SIMPLE_NOTIFICATION_CONTENT_TYPE_ID"));
            notification.setContentType(contentType);
            
            notification.setProducer(getSystemNotificationProducer());
            notification.setChannel(getKcNotificationChannel());
            
            notification.setTitle(kcNotification.getSubject());
            notification.setContent(NotificationConstants.XML_MESSAGE_CONSTANTS.CONTENT_SIMPLE_OPEN
                    + NotificationConstants.XML_MESSAGE_CONSTANTS.MESSAGE_OPEN + kcNotification.getMessage() + NotificationConstants.XML_MESSAGE_CONSTANTS.MESSAGE_CLOSE+ NotificationConstants.XML_MESSAGE_CONSTANTS.CONTENT_CLOSE);
            notification.setDeliveryType(NotificationConstants.DELIVERY_TYPES.FYI);
            setupRecipients(kcNotification, notification, context);
            notifications.add(notification);
        }
        
        for (Notification notification : notifications) {
            notificationService.sendNotification(notification);
        }
    }
    
    private void setupRecipients(KcNotification kcNotification, Notification notification, NotificationContext context) {
        TreeSet<NotificationRecipient> uniqueRecipients = new TreeSet<NotificationRecipient>(new Comparator<NotificationRecipient>() {
            public int compare(NotificationRecipient o1, NotificationRecipient o2) {
                return o1.getRecipientId().compareTo(o2.getRecipientId());
            }
        });
        List<NotificationTypeRecipient> roleRecipients = new ArrayList<NotificationTypeRecipient>();
        Collections.sort(kcNotification.getNotificationType().getNotificationTypeRecipients(), new Comparator<NotificationTypeRecipient>() {
            public int compare(NotificationTypeRecipient roleRecipeient1, NotificationTypeRecipient roleRecipeient2) {
                return roleRecipeient2.getRoleName().compareTo(roleRecipeient1.getRoleName());
            }
        });
        String roleName = null;
        for (NotificationTypeRecipient roleRecipient : kcNotification.getNotificationType().getNotificationTypeRecipients()) {
            try {
                if (StringUtils.isBlank(roleName) || roleName.equals(roleRecipient.getRoleName())) {
                    context.populateRoleQualifiers(roleRecipient);
                    roleRecipients.add(roleRecipient);
                    if (StringUtils.isBlank(roleName)) {
                        roleName = roleRecipient.getRoleName();
                    }
                } else {
                    uniqueRecipients.addAll(resolveRoleRecipients(roleRecipients));
                    roleRecipients = new ArrayList<NotificationTypeRecipient>();
                    context.populateRoleQualifiers(roleRecipient);
                    roleRecipients.add(roleRecipient);
                    roleName = roleRecipient.getRoleName();

                }
                // notification.getRecipients().addAll(resolveRoleRecipients(roleRecipient));
            } catch (UnknownRoleException e) {
                LOG.error("Role id " + e.getRoleId() + " not recognized for context " + e.getContext() + ". "
                        + "Notification will not be sent for notificationTypeRecipient" + roleRecipient.toString());
                e.printStackTrace();
            }
        }
        uniqueRecipients.addAll(resolveRoleRecipients(roleRecipients));
        notification.getRecipients().addAll(uniqueRecipients);
    }
    
    protected NotificationProducer getSystemNotificationProducer() {
        if (this.systemNotificationProducer == null) {
            NotificationProducer np = NotificationConstants.NOTIFICATION_PRODUCERS.NOTIFICATION_SYSTEM_PRODUCER;
            np.setId(getNotificationParameter("SYSTEM_NOTIFICATION_PRODUCER_ID"));
            List<NotificationChannel> notificationChannels = new ArrayList<NotificationChannel>();
            notificationChannels.add(getKcNotificationChannel());
            np.setChannels(notificationChannels);
            this.systemNotificationProducer = np;
        }
        return systemNotificationProducer;
    }
    
    protected NotificationChannel getKcNotificationChannel() {
        if (this.kcNotificationChannel == null) {
            NotificationChannel nc = new NotificationChannel();
            nc.setId(getNotificationParameter("KC_NOTIFICATION_CHANNEL_ID"));
            this.kcNotificationChannel = nc;
        }
        return kcNotificationChannel;
    }
    
    protected List<NotificationRecipient> resolveRoleRecipients(List<NotificationTypeRecipient> roleRecipients) {
        List<NotificationRecipient> recipients = new ArrayList<NotificationRecipient>();
        for (NotificationTypeRecipient roleRecipient : roleRecipients) {
            LOG.info("Processing recipient: " + roleRecipient.getRoleName() + " with " + roleRecipient.getRoleQualifiers().size() + " qualifiers.");
            String roleNamespace = StringUtils.substringBefore(roleRecipient.getRoleName(), Constants.COLON);
            String roleName = StringUtils.substringAfter(roleRecipient.getRoleName(), Constants.COLON);
    
            Collection<String> roleMembers = roleManagementService.getRoleMemberPrincipalIds(roleNamespace, roleName, roleRecipient.getRoleQualifiers());
            for (String roleMember : roleMembers) {
                NotificationRecipient recipient = new NotificationRecipient();
                recipient.setRecipientId(kcPersonService.getKcPersonByPersonId(roleMember).getUserName());
                recipient.setRecipientType(KimConstants.KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE);
                recipients.add(recipient);
            }
        } 
        
        return recipients;
    }
    
    protected Long getNotificationParameter(String parameterName) {
        String parameterValue = parameterService.getParameterValue(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                parameterName);
        return new Long(parameterValue);
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public RoleManagementService getRoleManagementService() {
        return roleManagementService;
    }

    public void setRoleManagementService(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
