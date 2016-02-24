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
package org.kuali.kra.subaward.notification;

import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.bo.SubAward;

import java.util.List;

/**
 * This class extends the notification context base and provides some helpful functions for any Award specific events.
 */
public class SubAwardNotificationContext extends NotificationContextBase {

    private SubAward subAward;
    private String documentNumber;
    private String actionTypeCode;
    private String contextName;
    private List<EmailAttachment> emailAttachments;
    private String forwardName;

    /**
     * Constructs an Award notification context and sets the necessary services.
     * @param award
     * @param actionTypeCode
     * @param contextName
     * @param renderer
     */
    public SubAwardNotificationContext(SubAward subAward, String actionTypeCode, String contextName, NotificationRenderer renderer, String forwardName) {
        super(renderer);
        
        this.subAward = subAward;
        this.documentNumber = subAward.getSubAwardDocument().getDocumentNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        this.forwardName = forwardName;
        
        setNotificationService(KcServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KcServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRoleQualifierService(KcServiceLocator.getService(SubAwardNotificationRoleQualifierService.class));
        ((SubAwardNotificationRoleQualifierService)getNotificationRoleQualifierService()).setSubAward(subAward);
    }
    
    public SubAwardNotificationContext(SubAward subAward, String actionTypeCode, String contextName) {
        this(subAward, actionTypeCode, contextName, new SubAwardNotificationRenderer(subAward), Constants.MAPPING_AWARD_ACTIONS_PAGE);
        ((SubAwardNotificationRenderer)getRenderer()).setSubAward(subAward);
    }
    
    public SubAwardNotificationContext(SubAward subAward, String actionTypeCode, String contextName, String forwardName) {
        this(subAward, actionTypeCode, contextName, new SubAwardNotificationRenderer(subAward), forwardName);
        ((SubAwardNotificationRenderer)getRenderer()).setSubAward(subAward);
    }    
    
    @Override
    public String getModuleCode() {
        return CoeusModule.AWARD_MODULE_CODE;
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
     * {@inheritDoc}
     * @param emailAttachments
     */
    public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }

    public SubAward getSubAward() {
        return subAward;
    }

    public void setSubAward(SubAward subAward) {
        this.subAward = subAward;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }
    
}
