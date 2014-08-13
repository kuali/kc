/*
 * Copyright 2005-2014 The Kuali Foundation
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