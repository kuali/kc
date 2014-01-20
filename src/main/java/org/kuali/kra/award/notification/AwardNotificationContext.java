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
package org.kuali.kra.award.notification;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationContextBase;
import org.kuali.kra.common.notification.NotificationRenderer;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.util.EmailAttachment;

import java.util.List;

/**
 * This class extends the notification context base and provides some helpful functions for any Award specific events.
 */
public class AwardNotificationContext extends NotificationContextBase {

    private static final long serialVersionUID = -8704592268298791182L;
    
    private Award award;
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
    public AwardNotificationContext(Award award, String actionTypeCode, String contextName, NotificationRenderer renderer, String forwardName) {
        super(renderer);
        
        this.award = award;
        this.documentNumber = award.getAwardDocument().getDocumentNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        this.forwardName = forwardName;
        
        setNotificationService(KraServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KraServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRoleQualifierService(KraServiceLocator.getService(AwardNotificationRoleQualifierService.class));
        ((AwardNotificationRoleQualifierService)getNotificationRoleQualifierService()).setAward(award);
    }
    
    public AwardNotificationContext(Award award, String actionTypeCode, String contextName) {
        this(award, actionTypeCode, contextName, KraServiceLocator.getService(AwardNotificationRenderer.class), Constants.MAPPING_AWARD_ACTIONS_PAGE);
        ((AwardNotificationRenderer)getRenderer()).setAward(award);
    }
    
    public AwardNotificationContext(Award award, String actionTypeCode, String contextName, String forwardName) {
        this(award, actionTypeCode, contextName, KraServiceLocator.getService(AwardNotificationRenderer.class), forwardName);
        ((AwardNotificationRenderer)getRenderer()).setAward(award);
    }    
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.AWARD_MODULE_CODE;
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
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#getEmailAttachments()
     */
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

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }
    
}