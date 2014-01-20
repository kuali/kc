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
package org.kuali.kra.coi.notification;


import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.common.notification.NotificationContextBase;
import org.kuali.kra.common.notification.NotificationRenderer;
import org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.util.EmailAttachment;

import java.util.List;

/**
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public class FinancialEntityNotificationContext extends NotificationContextBase {

    private String entityNumber;
    private String actionTypeCode;
    private String contextName;
    
    /**
     * Constructs a COI notification context and sets the necessary services.
     * @param coiDisclosure
     * @param actionTypeCode
     * @param contextName
     */
    public FinancialEntityNotificationContext(PersonFinIntDisclosure disclosure, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        super(renderer);

        this.entityNumber = disclosure.getEntityNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        setNotificationService(KraServiceLocator.getService(KcNotificationService.class));
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.COI_DISCLOSURE_MODULE_CODE;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getDocumentNumber()
     */
    public String getEntityNumber() {
        return entityNumber;
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

    @Override
    public List<EmailAttachment> getEmailAttachments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDocumentNumber() {
        // TODO Auto-generated method stub
        return null;
    }
 
    public KcNotificationRoleQualifierService getNotificationRoleQualifierService() {
        if (super.getNotificationRoleQualifierService() == null) {
            setNotificationRoleQualifierService(KraServiceLocator.getService(CoiNotificationRoleQualifierService.class));
        }
        return super.getNotificationRoleQualifierService();
    }

    
}