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
