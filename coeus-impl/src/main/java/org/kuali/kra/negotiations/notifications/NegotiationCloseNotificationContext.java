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
package org.kuali.kra.negotiations.notifications;

import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.negotiations.document.NegotiationDocument;

import java.util.List;

/**
 * Notification Context for Negotiation Closed action.
 */
public class NegotiationCloseNotificationContext extends NotificationContextBase {
    
    private static final long serialVersionUID = 70308097840555645L;
    private static final String NEGOTIATION_NOTIFICATION_MODULE_CODE = "5";
    private static final String NEGOTIATION_CLOSE_NOTIFICATION_ACTION_CODE = "100";
    
    private NegotiationDocument negotiationDocument;
    private List<EmailAttachment> emailAttachments;
    
    public NegotiationCloseNotificationContext(NegotiationDocument negotiationDocument) {
        super(KcServiceLocator.getService(NegotiationNotificationRenderer.class));
        ((NegotiationNotificationRenderer) getRenderer()).setNegotiation(negotiationDocument.getNegotiation());
        this.negotiationDocument = negotiationDocument;
        setNotificationService(KcServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KcServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRoleQualifierService(KcServiceLocator.getService(NegotiationNotificationRoleQualifierService.class));
    
        ((NegotiationNotificationRoleQualifierService) getNotificationRoleQualifierService()).setNegotiation(negotiationDocument.getNegotiation());
    }
    
    @Override
    public String getModuleCode() {
        return NEGOTIATION_NOTIFICATION_MODULE_CODE;
    }

    @Override
    public String getActionTypeCode() {
        return NEGOTIATION_CLOSE_NOTIFICATION_ACTION_CODE;
    }

    @Override
    public String getDocumentNumber() {
        return negotiationDocument.getDocumentNumber();
    }

    @Override
    public String getContextName() {
        return "Close Notification";
    }

    public NegotiationDocument getNegotiationDocument() {
        return negotiationDocument;
    }

    public void setNegotiationDocument(NegotiationDocument negotiationDocument) {
        this.negotiationDocument = negotiationDocument;
    }

    public List<EmailAttachment> getEmailAttachments() {
        return emailAttachments;
    }

    public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }

}
