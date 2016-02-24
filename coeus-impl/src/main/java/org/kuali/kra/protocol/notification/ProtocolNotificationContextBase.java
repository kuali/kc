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
package org.kuali.kra.protocol.notification;


import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public abstract class ProtocolNotificationContextBase extends NotificationContextBase {

    private static final long serialVersionUID = 6642334312368480034L;
    
    private String documentNumber;
    private String actionTypeCode;
    private String contextName;
    private List<EmailAttachment> emailAttachments;
    private String forwardName;
    // This is for assign reviewer.
    private boolean populateRole;
    
    /**
     * Constructs a protocol notification context and sets the necessary services.
     * @param protocol
     * @param protocolOnlineReview
     * @param actionTypeCode
     * @param contextName
     */
    public ProtocolNotificationContextBase(ProtocolBase protocol, ProtocolOnlineReviewBase protocolOnlineReview, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        this(protocol, actionTypeCode, contextName, renderer);
        ((ProtocolNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocolOnlineReview(protocolOnlineReview);
    }

    /**
     * Constructs an IRB notification context and sets the necessary services.
     * @param protocol
     * @param actionTypeCode
     * @param contextName
     */
    public ProtocolNotificationContextBase(ProtocolBase protocol, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        super(renderer);
        
        this.documentNumber = protocol.getProtocolDocument().getDocumentNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        
        setNotificationModuleRoleService(KcServiceLocator.getService(KcNotificationModuleRoleService.class));
    }
    
    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }
    
    @Override
    public String getActionTypeCode() {
        return actionTypeCode;
    }
    
    @Override
    public String getContextName() {
        return contextName;
    }

    @Override
    public List<EmailAttachment> getEmailAttachments() {
        return emailAttachments;
    }

    /**
     * 
     * This method sets a list of email attachments
     * @param emailAttachments
     */
    public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public boolean isPopulateRole() {
        return populateRole;
    }

    public void setPopulateRole(boolean populateRole) {
        this.populateRole = populateRole;
    }

    /**
     * This is a hook/hack for assign reviewer/submit for review, which has potential of multiple reviewers
     * reviewers are supposed to be processed separately, but for 'prompt user', it merged into one prompt.
     * 'reviewer role' are the same, but the role qualifiers' are different which is based on context.
     * the role qualifier are populated when we merge all recipients in to one list.
     * So, when sendnotification, it is just using the last 'context', so at this point, we don't want
     * rolequalifiers being populated again.  If it is populated again, all reviewer role will retrieve same reviewer because 
     * the context are the same at the point of 'send'.
     * Unless, there is better approach, we'll stick with this hack for now.
     * isPopulateRole is only 'true' for this case, so the other cases will stay the same as before this change.
     * @see org.kuali.coeus.common.notification.impl.NotificationContextBase#populateRoleQualifiers(org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient)
     */
    @Override
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        if (!isPopulateRole() || CollectionUtils.isEmpty(notificationRecipient.getRoleQualifiers())) {
            super.populateRoleQualifiers(notificationRecipient);
        }
    }
    
}
