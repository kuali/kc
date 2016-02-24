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
package org.kuali.kra.protocol.notification;

import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines a document-specific instance of a Notification Type.
 */
public class ProtocolNotification extends KcNotification {

    private static final long serialVersionUID = -8718347978876523074L;

    public void persistOwningObject(KcPersistableBusinessObjectBase object) {
        ProtocolBase protocol = (ProtocolBase)object;
        protocol.getLastProtocolAction().addNotification(this);
        KcServiceLocator.getService(BusinessObjectService.class).save(protocol);
        KcServiceLocator.getService(BusinessObjectService.class).save(this);
    }

    public static void copy(KcNotification source, KcNotification target) {
        target.setNotificationId(source.getNotificationId());
        target.setNotificationTypeId(source.getNotificationTypeId());
        target.setDocumentNumber(source.getDocumentNumber());
        target.setRecipients(source.getRecipients());
        target.setSubject(source.getSubject());
        target.setMessage(source.getMessage());
        target.setNotificationType(source.getNotificationType());
        target.setNotificationTypeId(source.getNotificationTypeId());
    }

}
