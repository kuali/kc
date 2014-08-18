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


import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.common.notification.impl.service.KcNotificationRoleQualifierService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;

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
        setNotificationService(KcServiceLocator.getService(KcNotificationService.class));
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.COI_DISCLOSURE_MODULE_CODE;
    }
    
    public String getEntityNumber() {
        return entityNumber;
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

        return null;
    }

    @Override
    public String getDocumentNumber() {

        return null;
    }
 
    public KcNotificationRoleQualifierService getNotificationRoleQualifierService() {
        if (super.getNotificationRoleQualifierService() == null) {
            setNotificationRoleQualifierService(KcServiceLocator.getService(CoiNotificationRoleQualifierService.class));
        }
        return super.getNotificationRoleQualifierService();
    }

    
}