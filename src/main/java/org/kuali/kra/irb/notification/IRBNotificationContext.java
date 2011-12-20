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
package org.kuali.kra.irb.notification;


import java.util.List;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationRenderer;
import org.kuali.kra.common.notification.NotificationContextBase;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.util.EmailAttachment;

/**
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public class IRBNotificationContext extends NotificationContextBase {

    private static final long serialVersionUID = 6642334312368480034L;
    
    private String documentNumber;
    private String actionTypeCode;
    private String contextName;
    private List<EmailAttachment> emailAttachments;
    private String forwardName;
    
    /**
     * Constructs an IRB notification context and sets the necessary services.
     * @param protocol
     * @param protocolOnlineReview
     * @param actionTypeCode
     * @param contextName
     */
    public IRBNotificationContext(Protocol protocol, ProtocolOnlineReview protocolOnlineReview, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        this(protocol, actionTypeCode, contextName, renderer);
        
        ((IRBNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocolOnlineReview(protocolOnlineReview);
    }

    /**
     * Constructs an IRB notification context and sets the necessary services.
     * @param protocol
     * @param actionTypeCode
     * @param contextName
     */
    public IRBNotificationContext(Protocol protocol, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        super(renderer);
        
        this.documentNumber = protocol.getProtocolDocument().getDocumentNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        
        setNotificationService(KraServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KraServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRoleQualifierService(KraServiceLocator.getService(IRBNotificationRoleQualifierService.class));
        
        ((IRBNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocol(protocol);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getDocumentNumber()
     */
    public String getDocumentNumber() {
        return documentNumber;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#getActionTypeCode()
     */
    public String getActionTypeCode() {
        return actionTypeCode;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#getContextName()
     */
    public String getContextName() {
        return contextName;
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContext#getEmailAttachments()
     */
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
    
}