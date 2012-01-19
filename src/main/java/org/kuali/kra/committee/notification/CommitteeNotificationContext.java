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
package org.kuali.kra.committee.notification;


import java.util.List;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.common.notification.NotificationRenderer;
import org.kuali.kra.common.notification.NotificationContextBase;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.util.EmailAttachment;
import org.springframework.util.CollectionUtils;

/**
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public class CommitteeNotificationContext extends NotificationContextBase {

    private static final long serialVersionUID = 6642334312368480034L;

    private Committee committee;
    private CommitteeSchedule committeeSchedule;
    private String actionTypeCode;
    private String contextName;
    private CommitteeNotificationRoleQualifierService committeeNotificationRoleQualifierService;
    
    /**
     * Constructs a Committee notification context and sets the necessary services.
     * @param protocol
     * @param actionTypeCode
     * @param contextName
     */
    public CommitteeNotificationContext(CommitteeSchedule committeeSchedule, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        super(renderer);

        this.committee = committeeSchedule.getCommittee();
        this.committeeSchedule = committeeSchedule;
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        
        setNotificationService(KraServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KraServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRoleQualifierService(KraServiceLocator.getService(CommitteeNotificationRoleQualifierService.class));
        ((CommitteeNotificationRoleQualifierService) getNotificationRoleQualifierService()).setCommitteeSchedule(this.committeeSchedule);
        ((CommitteeNotificationRoleQualifierService) getNotificationRoleQualifierService()).setCommittee(this.committee);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.COMMITTEE_MODULE_CODE;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getDocumentNumber()
     */
    public String getDocumentNumber() {
        return committee.getCommitteeDocument().getDocumentNumber();
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
     * This is a hook/hack for assign reviewer/submit for review, which has potential of multiple reviewers
     * reviewers are supposed to be processed separately, but for 'prompt user', it merged into one prompt.
     * 'reviewer role' are the same, but the role qualifiers' are different which is based on context.
     * the role qualifier are populated when we merge all recipients in to one list.
     * So, when sendnotification, it is just using the last 'context', so at this point, we don't want
     * rolequalifiers being populated again.  If it is populated again, all reviewer role will retrieve same reviewer because 
     * the context are the same at the point of 'send'.
     * Unless, there is better approach, we'll stick with this hack for now.
     * isPopulateRole is only 'true' for this case, so the other cases will stay the same as before this change.
     * @see org.kuali.kra.common.notification.NotificationContextBase#populateRoleQualifiers(org.kuali.kra.common.notification.bo.NotificationTypeRecipient)
     */
    @Override
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        if (CollectionUtils.isEmpty(notificationRecipient.getRoleQualifiers())) {
            super.populateRoleQualifiers(notificationRecipient);
        }
    }

    @Override
    public List<EmailAttachment> getEmailAttachments() {
        // TODO Auto-generated method stub
        return null;
    }
 
    public CommitteeNotificationRoleQualifierService getCommitteeNotificationRoleQualifierService() {
        return committeeNotificationRoleQualifierService;
    }
    
    public void setCommitteeNotificationRoleQualifierService(CommitteeNotificationRoleQualifierService committeeNotificationRoleQualifierService) {
        this.committeeNotificationRoleQualifierService= committeeNotificationRoleQualifierService;
    }
}