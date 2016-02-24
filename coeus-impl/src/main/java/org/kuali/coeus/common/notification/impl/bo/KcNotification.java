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
package org.kuali.coeus.common.notification.impl.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Defines a document-specific instance of a Notification Type.
 */
public class KcNotification extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 8649080269418978865L;

    private Long notificationId;

    private Long notificationTypeId;

    private String documentNumber;

    private String recipients;
    
    private String subject;

    private String message;

    private Long owningDocumentIdFk;

    private NotificationType notificationType;

    private String createUser;
    
    private Timestamp createTimestamp;
    
    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        if (notificationType == null) {
            this.refreshReferenceObject("notificationType");
        }
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Long getOwningDocumentIdFk() {
        return owningDocumentIdFk;
    }

    public void setOwningDocumentIdFk(Long owningDocumentIdFk) {
        this.owningDocumentIdFk = owningDocumentIdFk;
    }
    
    
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        // fall back to update timestamp for backwards compatibility
        return createTimestamp != null ? createTimestamp : getUpdateTimestamp();
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUpdateTimestampString() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return (getCreateTimestamp() == null ? "" : dateFormat.format(getCreateTimestamp()));
    }

    public void persistOwningObject(KcPersistableBusinessObjectBase object) {
        KcServiceLocator.getService(BusinessObjectService.class).save(object);
    }

    public void resetPersistenceState() {
        setNotificationId(null);
        setOwningDocumentIdFk(null);
    }
    
    @Override
    protected void prePersist() {
        super.prePersist();
        if (StringUtils.isEmpty(createUser)) {
            createUser = GlobalVariables.getUserSession().getPrincipalName();
            createTimestamp = KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp();  
        }
    }

}
