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
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.ken.bo.Notification;
import org.kuali.rice.ken.bo.NotificationChannel;
import org.kuali.rice.ken.bo.NotificationContentType;
import org.kuali.rice.ken.bo.NotificationPriority;
import org.kuali.rice.ken.bo.NotificationProducer;
import org.kuali.rice.ken.bo.NotificationRecipient;
import org.kuali.rice.ken.service.NotificationService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kim.service.RoleManagementService;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;

/**
 * Defines methods for creating and sending KC Notifications.
 */
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
    private RolodexService rolodexService;
    private ParameterService parameterService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#getNotificationType(org.kuali.kra.common.notification.NotificationContext)
     */
    public NotificationType getNotificationType(NotificationContext context) {
        return getNotificationType(context.getModuleCode(), context.getActionTypeCode());
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#getNotificationType(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public NotificationType getNotificationType(String moduleCode, String actionTypeCode) {
        NotificationType notificationType = null;
        
        List<NotificationType> notificationTypes = new ArrayList<NotificationType>();
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_CODE, moduleCode);
        fieldValues.put(ACTION_CODE, actionTypeCode);
        notificationTypes.addAll(getBusinessObjectService().findMatching(NotificationType.class, fieldValues));
        
        if (!notificationTypes.isEmpty()) {
            notificationType = notificationTypes.get(0);
        }
        
        return notificationType;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#createNotification(org.kuali.kra.common.notification.NotificationContext)
     */
    public KcNotification createNotification(NotificationContext context) {
        KcNotification notification = new KcNotification();
        
        NotificationType notificationType = getNotificationType(context);
        notification.setNotificationTypeId(notificationType.getNotificationTypeId());
        notification.setDocumentNumber(context.getDocumentNumber());
        String instanceSubject = context.replaceContextVariables(notificationType.getSubject());
        notification.setSubject(instanceSubject);
        String instanceMessage = context.replaceContextVariables(notificationType.getMessage());
        notification.setMessage(instanceMessage);
        notification.setNotificationType(notificationType);
        
        return notification;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#saveNotification(org.kuali.kra.common.notification.bo.KcNotification)
     */
    public void saveNotification(KcNotification notification) {
        getBusinessObjectService().save(notification);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#getNotifications(java.lang.String, java.lang.String, java.util.Set)
     */
    @SuppressWarnings("unchecked")
    public List<KcNotification> getNotifications(String documentNumber, String moduleCode, Set<String> actionCodes) {
        List<KcNotification> notifications = new ArrayList<KcNotification>();
        
        for (String actionCode : actionCodes) {
            NotificationType notificationType = getNotificationType(moduleCode, actionCode);
            
            Map<String, String> notificationFieldValues = new HashMap<String, String>();
            notificationFieldValues.put(NOTIFICATION_TYPE_ID, notificationType.getNotificationTypeId().toString());
            notificationFieldValues.put(DOCUMENT_NUMBER, documentNumber);
            notifications.addAll(getBusinessObjectService().findMatching(KcNotification.class, notificationFieldValues));
        }
        
        return notifications;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#sendNotification(org.kuali.kra.common.notification.bo.KcNotification, 
     *      org.kuali.kra.common.notification.NotificationContext)
     */
    public void sendNotification(NotificationContext context) {
        KcNotification notification = createNotification(context);
        String subject = notification.getSubject();
        String message = notification.getMessage();
        Collection<NotificationRecipient> notificationRecipients = getRecipients(context);

        sendNotification(context, subject, message, notificationRecipients);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationService#sendNotification(org.kuali.kra.common.notification.bo.KcNotification, 
     *      java.util.List, org.kuali.kra.common.notification.NotificationContext)
     */
    public void sendNotification(KcNotification notification, List<NotificationTypeRecipient> notificationTypeRecipients, NotificationContext context) {        
        String subject = notification.getSubject();
        String message = notification.getMessage();
        Collection<NotificationRecipient> notificationRecipients = getRecipients(notificationTypeRecipients, context);
        
        sendNotification(context, subject, message, notificationRecipients);
    }
    
    private void sendNotification(NotificationContext context, String subject, String message, Collection<NotificationRecipient> notificationRecipients) {
        LOG.info("Sending Notification [" + context.getContextName() + "]");
        
        Notification notification = getNotification(context);
        
        notification.setTitle(subject);
        notification.setContent(NotificationConstants.XML_MESSAGE_CONSTANTS.CONTENT_SIMPLE_OPEN + NotificationConstants.XML_MESSAGE_CONSTANTS.MESSAGE_OPEN 
                + message + NotificationConstants.XML_MESSAGE_CONSTANTS.MESSAGE_CLOSE + NotificationConstants.XML_MESSAGE_CONSTANTS.CONTENT_CLOSE);
        notification.getRecipients().addAll(notificationRecipients);
        
        notificationService.sendNotification(notification);
    }
    
    private Notification getNotification(NotificationContext context) {
        Notification notification = new Notification();
        
        NotificationPriority priority = new NotificationPriority();
        priority.setId(getNotificationParameter("NORMAL_NOTIFICATION_PRIORITY_ID"));
        notification.setPriority(priority);
        
        NotificationContentType contentType = new NotificationContentType();
        contentType.setId(getNotificationParameter("SIMPLE_NOTIFICATION_CONTENT_TYPE_ID"));
        notification.setContentType(contentType);
        
        notification.setDeliveryType(NotificationConstants.DELIVERY_TYPES.FYI);
        
        notification.setProducer(getSystemNotificationProducer());
        notification.setChannel(getKcNotificationChannel());
        
        return notification;
    }
    
    private NotificationProducer getSystemNotificationProducer() {
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
    
    private NotificationChannel getKcNotificationChannel() {
        if (this.kcNotificationChannel == null) {
            NotificationChannel nc = new NotificationChannel();
            nc.setId(getNotificationParameter("KC_NOTIFICATION_CHANNEL_ID"));
            this.kcNotificationChannel = nc;
        }
        return kcNotificationChannel;
    }
    
    private Long getNotificationParameter(String parameterName) {
        String parameterValue = parameterService.getParameterValue(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                parameterName);
        return new Long(parameterValue);
    }
    
    private Collection<NotificationRecipient> getRecipients(NotificationContext context) {
        TreeSet<NotificationRecipient> uniqueRecipients = new TreeSet<NotificationRecipient>(new Comparator<NotificationRecipient>() {
            public int compare(NotificationRecipient o1, NotificationRecipient o2) {
                return o1.getRecipientId().compareTo(o2.getRecipientId());
            }
        });
        
        List<NotificationTypeRecipient> notificationRecipients = getNotificationType(context).getNotificationTypeRecipients();
        
        uniqueRecipients.addAll(getRoleRecipients(notificationRecipients, context));
        
        return uniqueRecipients;
    }
    
    private Collection<NotificationRecipient> getRecipients(List<NotificationTypeRecipient> notificationRecipients, NotificationContext context) {
        TreeSet<NotificationRecipient> uniqueRecipients = new TreeSet<NotificationRecipient>(new Comparator<NotificationRecipient>() {
            public int compare(NotificationRecipient o1, NotificationRecipient o2) {
                return o1.getRecipientId().compareTo(o2.getRecipientId());
            }
        });
        
        List<NotificationTypeRecipient> roleRecipients = new ArrayList<NotificationTypeRecipient>();
        List<NotificationTypeRecipient> personRecipients = new ArrayList<NotificationTypeRecipient>();
        List<NotificationTypeRecipient> rolodexRecipients = new ArrayList<NotificationTypeRecipient>();
        for (NotificationTypeRecipient notificationRecipient : notificationRecipients) {
            if (StringUtils.isNotBlank(notificationRecipient.getRoleName())) {
                roleRecipients.add(notificationRecipient);
            } else if (StringUtils.isNotBlank(notificationRecipient.getPersonId())) {
                personRecipients.add(notificationRecipient);
            } else if (StringUtils.isNotBlank(notificationRecipient.getRolodexId())) {
                rolodexRecipients.add(notificationRecipient);
            }
        }
        
        uniqueRecipients.addAll(getRoleRecipients(roleRecipients, context));
        uniqueRecipients.addAll(getPersonRecipients(personRecipients));
        uniqueRecipients.addAll(getRolodexRecipients(rolodexRecipients));
        
        return uniqueRecipients;
    }
    
    private List<NotificationRecipient> getRoleRecipients(List<NotificationTypeRecipient> notificationRecipients, NotificationContext context) {
        List<NotificationRecipient> roleRecipients = new ArrayList<NotificationRecipient>();
        
        List<NotificationTypeRecipient> recipients = new ArrayList<NotificationTypeRecipient>();
        Collections.sort(notificationRecipients, new Comparator<NotificationTypeRecipient>() {
            public int compare(NotificationTypeRecipient roleRecipeient1, NotificationTypeRecipient roleRecipeient2) {
                return roleRecipeient2.getRoleName().compareTo(roleRecipeient1.getRoleName());
            }
        });
        String roleName = null;
        for (NotificationTypeRecipient roleRecipient : notificationRecipients) {
            try {
                if (StringUtils.isBlank(roleName) || roleName.equals(roleRecipient.getRoleName())) {
                    context.populateRoleQualifiers(roleRecipient);
                    recipients.add(roleRecipient);
                    if (StringUtils.isBlank(roleName)) {
                        roleName = roleRecipient.getRoleName();
                    }
                } else {
                    roleRecipients.addAll(createRoleRecipients(recipients));
                    recipients = new ArrayList<NotificationTypeRecipient>();
                    context.populateRoleQualifiers(roleRecipient);
                    recipients.add(roleRecipient);
                    roleName = roleRecipient.getRoleName();

                }
            } catch (UnknownRoleException e) {
                LOG.error("Role id " + e.getRoleId() + " not recognized for context " + e.getContext() + ". "
                        + "Notification will not be sent for notificationTypeRecipient" + roleRecipient.toString());
                e.printStackTrace();
            }
        }
        roleRecipients.addAll(createRoleRecipients(recipients));
        
        return roleRecipients;
    }
    
    private List<NotificationRecipient> createRoleRecipients(List<NotificationTypeRecipient> roleRecipients) {
        List<NotificationRecipient> recipients = new ArrayList<NotificationRecipient>();
        
        for (NotificationTypeRecipient roleRecipient : roleRecipients) {
            LOG.info("Processing recipient: " + roleRecipient.getRoleName() + " with " + roleRecipient.getRoleQualifiers().size() + " qualifiers.");
            String roleNamespace = StringUtils.substringBefore(roleRecipient.getRoleName(), Constants.COLON);
            String roleName = StringUtils.substringAfter(roleRecipient.getRoleName(), Constants.COLON);
    
            Collection<String> roleMembers = roleManagementService.getRoleMemberPrincipalIds(roleNamespace, roleName, roleRecipient.getRoleQualifiers());
            for (String roleMember : roleMembers) {
                NotificationRecipient recipient = new NotificationRecipient();
                recipient.setRecipientId(getKcPersonService().getKcPersonByPersonId(roleMember).getUserName());
                recipient.setRecipientType(KimConstants.KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE);
                recipients.add(recipient);
            }
        } 
        
        return recipients;
    }
    
    private List<NotificationRecipient> getPersonRecipients(List<NotificationTypeRecipient> notificationRecipients) {
        List<NotificationRecipient> personRecipients = new ArrayList<NotificationRecipient>();
        
        List<NotificationTypeRecipient> recipients = new ArrayList<NotificationTypeRecipient>();
        Collections.sort(notificationRecipients, new Comparator<NotificationTypeRecipient>() {
            public int compare(NotificationTypeRecipient roleRecipeient1, NotificationTypeRecipient roleRecipeient2) {
                return roleRecipeient2.getPersonId().compareTo(roleRecipeient1.getPersonId());
            }
        });
        String personId = null;
        for (NotificationTypeRecipient personRecipient : notificationRecipients) {
            if (StringUtils.isBlank(personId)) {
                recipients.add(personRecipient);
                personId = personRecipient.getPersonId();
            }
        }
        personRecipients.addAll(createPersonRecipients(recipients));
        
        return personRecipients;
    }
    
    private List<NotificationRecipient> createPersonRecipients(List<NotificationTypeRecipient> personRecipients) {
        List<NotificationRecipient> recipients = new ArrayList<NotificationRecipient>();
        
        for (NotificationTypeRecipient personRecipient : personRecipients) {
            LOG.info("Processing recipient: " + personRecipient.getPersonId() + ".");
            
            NotificationRecipient recipient = new NotificationRecipient();
            recipient.setRecipientId(getKcPersonService().getKcPersonByPersonId(personRecipient.getPersonId()).getUserName());
            recipient.setRecipientType(KimConstants.KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE);
            recipients.add(recipient);
        }
        
        return recipients;
    }
    
    private List<NotificationRecipient> getRolodexRecipients(List<NotificationTypeRecipient> notificationRecipients) {
        List<NotificationRecipient> rolodexRecipients = new ArrayList<NotificationRecipient>();
        
        List<NotificationTypeRecipient> recipients = new ArrayList<NotificationTypeRecipient>();
        Collections.sort(notificationRecipients, new Comparator<NotificationTypeRecipient>() {
            public int compare(NotificationTypeRecipient roleRecipeient1, NotificationTypeRecipient roleRecipeient2) {
                return roleRecipeient2.getRolodexId().compareTo(roleRecipeient1.getRolodexId());
            }
        });
        String rolodexId = null;
        for (NotificationTypeRecipient rolodexRecipient : notificationRecipients) {
            if (StringUtils.isBlank(rolodexId)) {
                recipients.add(rolodexRecipient);
                rolodexId = rolodexRecipient.getRolodexId();
            }
        }
        rolodexRecipients.addAll(createRolodexRecipients(recipients));
        
        return rolodexRecipients;
    }
    
    private List<NotificationRecipient> createRolodexRecipients(List<NotificationTypeRecipient> rolodexRecipients) {
        List<NotificationRecipient> recipients = new ArrayList<NotificationRecipient>();

// TODO: Make sure emails are sent to rolodex email addresses: this is NOT taken care of by KEN
//        for (NotificationTypeRecipient personRecipient : rolodexRecipients) {
//            LOG.info("Processing recipient: " + personRecipient.getRolodexId() + ".");
//            
//            NotificationRecipient recipient = new NotificationRecipient();
//            recipient.setRecipientId(getRolodexService().getRolodex(Integer.parseInt(personRecipient.getRolodexId())).getEmailAddress());
//            recipient.setRecipientType(KimConstants.KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE);
//            recipients.add(recipient);
//        }
        
        return recipients;
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
    
    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    public RolodexService getRolodexService() {
        return rolodexService;
    }
    
    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}