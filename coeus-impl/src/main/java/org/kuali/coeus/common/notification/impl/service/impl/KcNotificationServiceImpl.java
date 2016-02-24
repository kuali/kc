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
package org.kuali.coeus.common.notification.impl.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.mail.KcEmailService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.view.wizard.framework.WizardResultsDto;
import org.kuali.coeus.common.notification.impl.NotificationContext;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.ken.api.notification.Notification;
import org.kuali.rice.ken.api.notification.NotificationChannel;
import org.kuali.rice.ken.api.notification.NotificationContentType;
import org.kuali.rice.ken.api.notification.NotificationPriority;
import org.kuali.rice.ken.api.notification.NotificationProducer;
import org.kuali.rice.ken.api.notification.NotificationRecipient;
import org.kuali.rice.ken.api.notification.NotificationSender;
import org.kuali.rice.ken.api.service.SendNotificationService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Defines methods for creating and sending KC Notifications.
 */
public class KcNotificationServiceImpl implements KcNotificationService {

    private static final String MODULE_CODE = "moduleCode";
    private static final String ACTION_CODE = "actionCode";
    private static final String NOTIFICATION_TYPE_ID = "notificationTypeId";
    private static final String DOCUMENT_NUMBER = "documentNumber";
    
    private static final Log LOG = LogFactory.getLog(KcNotificationServiceImpl.class);
    private static final String KC_NOTIFICATION_DOC_TYPE_NAME = "KcNotificationDocumentTypeName";
    
    private static final String KC_DEFAULT_EMAIL_RECIPIENT = "KC_DEFAULT_EMAIL_RECIPIENT";
    private static final String ACTIVE = "active";
    private static final String TRUE = "true";

    protected NotificationChannel kcNotificationChannel;
    protected NotificationProducer systemNotificationProducer;
    
    private BusinessObjectService businessObjectService;
    private SendNotificationService sendNotificationService;
    private RoleService roleManagementService;
    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;
    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;
    private ParameterService parameterService;
    private IdentityService identityService;
    private KcEmailService kcEmailService;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public NotificationType getNotificationType(NotificationContext context) {
        return getNotificationType(context.getModuleCode(), context.getActionTypeCode());
    }
    
    @Override
    public NotificationType getNotificationType(String moduleCode, String actionTypeCode) {
        NotificationType notificationType = null;
        
        List<NotificationType> notificationTypes = new ArrayList<NotificationType>();
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_CODE, moduleCode);
        fieldValues.put(ACTION_CODE, actionTypeCode);
        fieldValues.put(ACTIVE, TRUE);
        notificationTypes.addAll(getBusinessObjectService().findMatching(NotificationType.class, fieldValues));
        
        if (!notificationTypes.isEmpty()) {
            notificationType = notificationTypes.get(0);
        }
        
        return notificationType;
    }

    @Override
    public KcNotification createNotificationObject(NotificationContext context) {
        KcNotification notification = new KcNotification();
        
        NotificationType notificationType = getNotificationType(context);
        if (notificationType != null) {
            notification.setNotificationTypeId(notificationType.getNotificationTypeId());
            notification.setDocumentNumber(context.getDocumentNumber());
            String instanceSubject = context.replaceContextVariables(notificationType.getSubject());
            notification.setSubject(instanceSubject);
            String instanceMessage = context.replaceContextVariables(notificationType.getMessage());
            notification.setMessage(instanceMessage);
            notification.setNotificationType(notificationType);
            notification.setCreateUser(getCreateUser());
            notification.setCreateTimestamp(getCreateTimestamp());
        }
        
        return notification;
    }

	protected Timestamp getCreateTimestamp() {
		return KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp();
	}

	protected String getCreateUser() {
		return GlobalVariables.getUserSession().getPrincipalName();
	}
    
    @Override
    public void saveNotification(KcNotification notification) {
        getBusinessObjectService().save(notification);
    }

    @Override
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
    
    @Override
    public void sendNotification(NotificationContext context) {
        KcNotification notification = createNotificationObject(context);

        if (notification.getNotificationType() != null && notification.getNotificationType().isActive()) {
            String contextName = context.getContextName();
            String subject = notification.getSubject();
            String message = notification.getMessage();
            Collection<NotificationRecipient.Builder> notificationRecipients = getNotificationRecipients(context);

            sendNotification(contextName, subject, message, notificationRecipients);
            sendEmailNotification(subject, message, notificationRecipients, context.getEmailAttachments());
        }
    }
    
    @Override
    public void sendNotification(NotificationContext context, KcNotification notification, List<NotificationTypeRecipient> notificationTypeRecipients) {
        String contextName = context.getContextName();
        String subject = notification.getSubject();
        String message = notification.getMessage();
        Set<NotificationRecipient.Builder> notificationRecipients = getNotificationRecipients(notificationTypeRecipients, context);
        Set<String> emailRecipients = getEmailRecipients(notificationTypeRecipients);

        sendNotification(contextName, subject, message, notificationRecipients);
        sendEmailNotification(subject, message, notificationRecipients, context.getEmailAttachments());
        sendEmailNotification(getKcEmailService().getDefaultFromAddress(), emailRecipients, subject, message, context.getEmailAttachments());
    }
    
    @Override
    public void sendNotification(String contextName, String subject, String message, List<String> principalNames) {
        Collection<NotificationRecipient.Builder> notificationRecipients = getNotificationRecipients(principalNames);

        sendNotification(contextName, subject, message, notificationRecipients);
        sendEmailNotification(subject, message, notificationRecipients, Collections.<EmailAttachment>emptyList());
    }
    
    @Override
    public void sendEmailNotification(NotificationContext context) {
        if (isEmailEnabled()) {
            KcNotification notification = createNotificationObject(context);

            if (notification.getNotificationType() != null && notification.getNotificationType().isActive()) {
                String subject = notification.getSubject();
                String message = notification.getMessage();
                Collection<NotificationRecipient.Builder> notificationRecipients = getNotificationRecipients(context);
                Set<String> toAddresses = getRecipientEmailAddresses(notificationRecipients);

                String fromAddress = getKcEmailService().getDefaultFromAddress();

                sendEmailNotification(fromAddress, toAddresses, subject, message, context.getEmailAttachments());
            }
        }
    }
    
    public void sendNotification(String contextName, String subject, String message, Collection<NotificationRecipient.Builder> notificationRecipients) {
        sendNotification(contextName, subject, message, notificationRecipients, null);
    }

    public void sendNotification(String contextName, String subject, String message, Collection<NotificationRecipient.Builder> notificationRecipients, String docTypeName) {
        LOG.info("Sending Notification [" + contextName + "]");
        
        // if doc type name is not specified we default to the KC notification doc type
        if(docTypeName == null) {
            docTypeName = getKCNotificationDocTypeName();
        }
        
        Notification.Builder notification = getNotification();
        
        notification.setTitle(subject);
        notification.setContent(NotificationConstants.XML_MESSAGE_CONSTANTS.CONTENT_SIMPLE_OPEN + NotificationConstants.XML_MESSAGE_CONSTANTS.MESSAGE_OPEN 
                + message + NotificationConstants.XML_MESSAGE_CONSTANTS.MESSAGE_CLOSE + NotificationConstants.XML_MESSAGE_CONSTANTS.CONTENT_CLOSE);
        
        notification.setRecipients(new ArrayList<NotificationRecipient.Builder>(notificationRecipients));
        notification.setDocTypeName(docTypeName);
        executorService.execute(() -> sendNotificationService.sendNotification(notification.build()));
    }
    
    private String getKCNotificationDocTypeName() {
        return parameterService.getParameterValueAsString(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                KC_NOTIFICATION_DOC_TYPE_NAME);
    }
    
    private Notification.Builder getNotification() {
        Notification.Builder notification = Notification.Builder.create();
        
        NotificationPriority.Builder priority = NotificationPriority.Builder.create();
        priority.setId(getNotificationParameter("NORMAL_NOTIFICATION_PRIORITY_ID"));
        notification.setPriority(priority);
        
        NotificationContentType.Builder contentType = NotificationContentType.Builder.create();
        contentType.setId(getNotificationParameter("SIMPLE_NOTIFICATION_CONTENT_TYPE_ID"));
        notification.setContentType(contentType);
        
        notification.setDeliveryType(NotificationConstants.DELIVERY_TYPES.FYI);
        
        notification.setProducer(getSystemNotificationProducer());
        notification.setChannel(getKcNotificationChannel());
        
        notification.setSenders(getNotificationSenders());
        
        return notification;
    }
    
    private NotificationProducer.Builder getSystemNotificationProducer() {
        if (this.systemNotificationProducer == null) {
            NotificationProducer.Builder np = NotificationProducer.Builder.create();
            np.setName(NotificationConstants.NOTIFICATION_PRODUCERS.NOTIFICATION_SYSTEM_PRODUCER_NAME);
            np.setDescription(NotificationConstants.NOTIFICATION_PRODUCERS.NOTIFICATION_SYSTEM_PRODUCER_DESCRIPTION);
            np.setContactInfo(NotificationConstants.NOTIFICATION_PRODUCERS.NOTIFICATION_SYSTEM_PRODUCER_CONTACT_INFO);
            
            np.setId(getNotificationParameter("SYSTEM_NOTIFICATION_PRODUCER_ID"));
            List<Long> notificationChannels = new ArrayList<Long>();
            notificationChannels.add(getKcNotificationChannel().getId());
            np.setChannelIds(notificationChannels);
            this.systemNotificationProducer = np.build();
        }
        return NotificationProducer.Builder.create(systemNotificationProducer);
    }
    
    private NotificationChannel.Builder getKcNotificationChannel() {
        if (this.kcNotificationChannel == null) {
            NotificationChannel.Builder nc = NotificationChannel.Builder.create();
            nc.setId(getNotificationParameter("KC_NOTIFICATION_CHANNEL_ID"));
            this.kcNotificationChannel = nc.build();
        }
        return NotificationChannel.Builder.create(kcNotificationChannel);
    }
    
    private List<NotificationSender.Builder> getNotificationSenders() {
        List<NotificationSender.Builder> senders = new ArrayList<NotificationSender.Builder>();
        NotificationSender.Builder sender = NotificationSender.Builder.create();
        sender.setSenderName(getConfiguredSender());
        senders.add(sender);

        return senders;
    }
    
    private String getConfiguredSender() {
        String senderName = parameterService.getParameterValueAsString(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                "ACTION_LIST_DEFAULT_FROM_USER");
        if (StringUtils.isBlank(senderName)) {
            //If not configured fall back to admin
            senderName = "admin";
        }
        
        return senderName;
    }
    
    private Long getNotificationParameter(String parameterName) {
        String parameterValue = parameterService.getParameterValueAsString(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                parameterName);
        return new Long(parameterValue);
    }
    
    public Set<NotificationRecipient.Builder> getNotificationRecipients(NotificationContext context) {
        Set<NotificationRecipient.Builder> uniqueRecipients = new TreeSet<>(new Comparator<NotificationRecipient.Builder>() {
            public int compare(NotificationRecipient.Builder o1, NotificationRecipient.Builder o2) {
                return o1.getRecipientId().compareTo(o2.getRecipientId());
            }
        });
        
        List<NotificationTypeRecipient> notificationRecipients = getNotificationType(context).getNotificationTypeRecipients();
        
        uniqueRecipients.addAll(getRoleRecipients(notificationRecipients, context));

        return uniqueRecipients;
    }
    
    private Set<NotificationRecipient.Builder> getNotificationRecipients(List<NotificationTypeRecipient> notificationRecipients, NotificationContext context) {
        Set<NotificationRecipient.Builder> uniqueRecipients = new TreeSet<NotificationRecipient.Builder>(new Comparator<NotificationRecipient.Builder>() {
            public int compare(NotificationRecipient.Builder o1, NotificationRecipient.Builder o2) {
                return o1.getRecipientId().compareTo(o2.getRecipientId());
            }
        });
        
        List<NotificationTypeRecipient> roleRecipients = new ArrayList<NotificationTypeRecipient>();
        List<NotificationTypeRecipient> personRecipients = new ArrayList<NotificationTypeRecipient>();
        
        for (NotificationTypeRecipient notificationRecipient : notificationRecipients) {
            if (StringUtils.isNotBlank(notificationRecipient.getRoleName())) {
                roleRecipients.add(notificationRecipient);
            } else if (StringUtils.isNotBlank(notificationRecipient.getPersonId())) {
                personRecipients.add(notificationRecipient);
            }
        }
        
        uniqueRecipients.addAll(getRoleRecipients(roleRecipients, context));
        uniqueRecipients.addAll(getPersonRecipients(personRecipients));
        
        
        return uniqueRecipients;
    }
    
    private Set<String> getEmailRecipients(List<NotificationTypeRecipient> notificationRecipients) {
        Set<String> uniqueEmailAddresses = new TreeSet<String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        
        List<NotificationTypeRecipient> rolodexRecipients = new ArrayList<NotificationTypeRecipient>();
        
        for (NotificationTypeRecipient notificationRecipient : notificationRecipients) {
            if (StringUtils.isNotBlank(notificationRecipient.getRolodexId())) {
                rolodexRecipients.add(notificationRecipient);
            }
        }
        
        uniqueEmailAddresses.addAll(getRolodexRecipients(rolodexRecipients));
        
        return uniqueEmailAddresses;
    }
    
    private Set<NotificationRecipient.Builder> getNotificationRecipients(List<String> principalNames) {
        Set<NotificationRecipient.Builder> uniqueRecipients = new TreeSet<NotificationRecipient.Builder>(new Comparator<NotificationRecipient.Builder>() {
            public int compare(NotificationRecipient.Builder o1, NotificationRecipient.Builder o2) {
                return o1.getRecipientId().compareTo(o2.getRecipientId());
            }
        });
        
        for (String principalName : principalNames) {
            LOG.info("Processing recipient: " + principalName + ".");
            
            NotificationRecipient.Builder recipient = NotificationRecipient.Builder.create();
            recipient.setRecipientId(principalName);
            recipient.setRecipientType(KimConstants.KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE.getCode());
            uniqueRecipients.add(recipient);
        }
        
        return uniqueRecipients;
    }
    
    private List<NotificationRecipient.Builder> getRoleRecipients(List<NotificationTypeRecipient> notificationRecipients, NotificationContext context) {
        List<NotificationRecipient.Builder> roleRecipients = new ArrayList<NotificationRecipient.Builder>();
        
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
                        + "Notification will not be sent for notificationTypeRecipient" + roleRecipient.toString(), e);
            }
        }
        roleRecipients.addAll(createRoleRecipients(recipients));
        
        return roleRecipients;
    }
    
    private List<NotificationRecipient.Builder> createRoleRecipients(List<NotificationTypeRecipient> roleRecipients) {
        List<NotificationRecipient.Builder> recipients = new ArrayList<NotificationRecipient.Builder>();
        
        for (NotificationTypeRecipient roleRecipient : roleRecipients) {
            LOG.info("Processing recipient: " + roleRecipient.getRoleName() + " with " + roleRecipient.getRoleQualifiers().size() + " qualifiers.");
            String roleNamespace = StringUtils.substringBefore(roleRecipient.getRoleName(), Constants.COLON);
            String roleName = StringUtils.substringAfter(roleRecipient.getRoleName(), Constants.COLON);
    
            Collection<String> roleMembers = getRoleMemberPrincipalIds(roleNamespace, roleName, roleRecipient.getRoleQualifiers());
            for (String roleMember : roleMembers) {
                NotificationRecipient.Builder recipient = NotificationRecipient.Builder.create();
                try {
                recipient.setRecipientId(getPersonUserName(roleMember));
                recipient.setRecipientType(MemberType.PRINCIPAL.getCode());
                recipients.add(recipient);
                } catch (IllegalArgumentException e) {
                    // Quietly ignore recipients that no longer exist
                	LOG.info("Invalid recipient", e);
                }
            }
        } 
        
        return recipients;
    }

	protected String getPersonUserName(String personId) {
		return getKcPersonService().getKcPersonByPersonId(personId).getUserName();
	}

	protected Collection<String> getRoleMemberPrincipalIds(String roleNamespace, String roleName, final Map<String, String> roleQualifiers) {
		return roleManagementService.getRoleMemberPrincipalIds(roleNamespace, roleName, roleQualifiers);
	}
    
    private List<NotificationRecipient.Builder> getPersonRecipients(List<NotificationTypeRecipient> notificationRecipients) {
        List<NotificationRecipient.Builder> recipients = new ArrayList<NotificationRecipient.Builder>();
        
        for (NotificationTypeRecipient notificationRecipient : notificationRecipients) {
            LOG.info("Processing recipient: " + notificationRecipient.getPersonId() + ".");
            
            NotificationRecipient.Builder recipient = NotificationRecipient.Builder.create();
            recipient.setRecipientId(getPersonUserName(notificationRecipient.getPersonId()));
            recipient.setRecipientType(KimConstants.KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE.getCode());
            recipients.add(recipient);
        }
        
        return recipients;
            }
    
    private List<String> getRolodexRecipients(List<NotificationTypeRecipient> notificationRecipients) {
        List<String> recipients = new ArrayList<String>();

        for (NotificationTypeRecipient notificationRecipient : notificationRecipients) {
            LOG.info("Processing recipient: " + notificationRecipient.getRolodexId() + ".");
            CollectionUtils.addIgnoreNull(recipients, getRolodexEmailAddress(notificationRecipient.getRolodexId()));
        }
        
        return recipients;
    }

	protected String getRolodexEmailAddress(final String rolodexId) {
		RolodexContract rolodex = getRolodexService().getRolodex(Integer.parseInt(rolodexId));
		String emailAddress = null;
		if (StringUtils.isNotBlank(rolodex.getEmailAddress())) {
		    emailAddress = rolodex.getEmailAddress();
		}
		return emailAddress;
	}
    
    private void sendEmailNotification(String subject, String message, Collection<NotificationRecipient.Builder> notificationRecipients, List<EmailAttachment> attachments) {
        if (isEmailEnabled()) {
            Set<String> toAddresses = getRecipientEmailAddresses(notificationRecipients);              
            sendEmailNotification(getKcEmailService().getDefaultFromAddress(), toAddresses, subject, message, attachments);
        }
    }
        
            
    private void sendEmailNotification(String fromAddress, Set<String> toAddresses, String subject, String message, List<EmailAttachment> attachments) {
        if (isEmailEnabled()) {
            getKcEmailService().sendEmailWithAttachments(fromAddress, toAddresses, subject, null, null, 
                                                     message, true, attachments);
        }
    }
    
    private Set<String> getRecipientEmailAddresses(Collection<NotificationRecipient.Builder> recipients) {
        Set<String> emailAddresses = new HashSet<String>();
        
        if (CollectionUtils.isNotEmpty(recipients)) {
            for (NotificationRecipient.Builder recipient : recipients) {
            	emailAddresses.addAll(getRecipientEmailAddresses(recipient));
            }
        }
        
        if(emailAddresses.isEmpty()){
        	String defaultEmailRecipient = parameterService.getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE,KC_DEFAULT_EMAIL_RECIPIENT);
        	if(defaultEmailRecipient!=null){
        		emailAddresses.add(defaultEmailRecipient);
        	}
        }
        return emailAddresses;
    }

	protected Set<String> getRecipientEmailAddresses(NotificationRecipient.Builder recipient) {
		Set<String> recipientEmailAddresses = new HashSet<String>();
		Entity entityInfo = null;
		try {
		  final String principalName = recipient.getRecipientId();
		  if (principalName != null && principalName.length() > 0) {
		    entityInfo = getIdentityService().getEntityByPrincipalName(recipient.getRecipientId());
		  }
		} catch (RiceIllegalArgumentException e) {
		  LOG.info("getRecipientEmailAddresses: Principal cannot be found: " + recipient.getRecipientId());
		}
		if (entityInfo != null) {
		    List<EntityTypeContactInfo> entityTypes = entityInfo.getEntityTypeContactInfos();
		    if (CollectionUtils.isNotEmpty(entityTypes)) {
		        for (EntityTypeContactInfo entityType : entityTypes) {
		            if (StringUtils.equals(KimConstants.EntityTypes.PERSON, entityType.getEntityTypeCode())) {
		                if (entityType.getDefaultEmailAddress() != null &&
		                        StringUtils.isNotBlank(entityType.getDefaultEmailAddress().getEmailAddress())) {
		                	recipientEmailAddresses.add(entityType.getDefaultEmailAddress().getEmailAddress());
		                    LOG.info("Added recipient email: " + entityType.getDefaultEmailAddress().getEmailAddress() +
		                             " for KIM user: " + recipient.getRecipientId());
		                }
		            }
		        }
		    }
		}
		return recipientEmailAddresses;
	}
    
    private boolean isEmailEnabled() {
        boolean emailEnabled = false;

        try {
            emailEnabled = parameterService.getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE,  
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, "EMAIL_NOTIFICATIONS_ENABLED");
        } catch (Exception e) {
            LOG.warn("Email Notifications parameter not configured, defaulting to disabled.");
        }
        
        return emailEnabled;
    }

    private void fillinNotificationObject(KcNotification notification, NotificationContext context, List<NotificationTypeRecipient> notificationTypeRecipients) {
        fillinNotificationObject(notification, context);
        String resultList = notification.getRecipients();
        for (NotificationTypeRecipient recipient: notificationTypeRecipients) {
            if (recipient.getPersonId() != null) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(recipient.getPersonId()); 
                if (person != null) {
                    if(resultList != null) {
                        resultList += ", " + person.getUserName();
                    }else {
                        resultList = person.getUserName();
                    }
                }
            }
        }            
        notification.setRecipients(resultList);
    }
    
    private void fillinNotificationObject(KcNotification notification, NotificationContext context) {
        NotificationType notificationType = getNotificationType(context);
        // some fields will already be set if we get here from one of the notification editors
        if (notificationType != null) {
            if (notification.getNotificationType() == null) {
                notification.setNotificationTypeId(notificationType.getNotificationTypeId());
                notification.setSubject(context.replaceContextVariables(StringUtils.isEmpty(notification.getSubject()) ? notificationType.getSubject() : notification.getSubject()));
                notification.setMessage(context.replaceContextVariables(StringUtils.isEmpty(notification.getMessage()) ? notificationType.getMessage() : notification.getMessage()));
                notification.setNotificationType(notificationType);
            }
            notification.setDocumentNumber(context.getDocumentNumber());
            Collection<NotificationRecipient.Builder> notificationRecipients = getNotificationRecipients(context);
            String resultList = "";
            for (NotificationRecipient.Builder recipient: notificationRecipients) {
                resultList += ", " + recipient.getRecipientId();
            }       
            
            if(resultList != "") {
                notification.setRecipients(resultList.substring(2));
            }
        }
    }
    
    
    
    public void sendNotificationAndPersist(NotificationContext context, KcNotification notification, KcPersistableBusinessObjectBase object) {
        fillinNotificationObject(notification, context);
        if (notification.getNotificationType() != null && notification.getNotificationType().isActive()) {
            sendNotification(context);
            notification.persistOwningObject(object);
        }
    }

    public void sendNotificationAndPersist(NotificationContext context, KcNotification notification, List<NotificationTypeRecipient> notificationTypeRecipients, KcPersistableBusinessObjectBase object) {
        fillinNotificationObject(notification, context, notificationTypeRecipients);
        if (notification.getNotificationType() != null && notification.getNotificationType().isActive()) {
            sendNotification(context, notification, notificationTypeRecipients);
            notification.persistOwningObject(object);
        }
    }

    public List<NotificationTypeRecipient> addRecipient(List<Object> results) {
        List<NotificationTypeRecipient> recipients = new ArrayList<NotificationTypeRecipient>();
        for (Object object : results) {
            WizardResultsDto result = (WizardResultsDto) object;
            NotificationTypeRecipient recipient = new NotificationTypeRecipient();
            if (result.isSelected() == true){
                if (result.getKcPerson() != null) {
                    recipient.setPersonId(result.getKcPerson().getPersonId());
                    recipient.setFullName(result.getKcPerson().getFullName());
                } else if (result.getRolodex() != null) {
                    recipient.setRolodexId(result.getRolodex().getRolodexId().toString());
                    recipient.setFullName(result.getRolodex().getFullName());
                } else if (result.getRole() != null) {
                    recipient.setRoleName(result.getRole().getNamespaceCode() + ":" + result.getRole().getName());
                    recipient.setFullName(result.getRole().getNamespaceCode() + ":" + result.getRole().getName());
                } else {
                    continue;
                }
                recipients.add(recipient);
            }
        }
        return recipients;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public SendNotificationService getSendNotificationService() {
        return sendNotificationService;
    }

    public void setSendNotificationService(SendNotificationService sendNotificationService) {
        this.sendNotificationService = sendNotificationService;
    }

    public RoleService getRoleManagementService() {
        return roleManagementService;
    }

    public void setRoleManagementService(RoleService roleManagementService) {
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
    
    public IdentityService getIdentityService() {
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    public KcEmailService getKcEmailService() {
        return kcEmailService;
    }

    public void setKcEmailService(KcEmailService kcEmailService) {
        this.kcEmailService = kcEmailService;
    }
}
