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
package org.kuali.kra.iacuc.notification;

import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.rice.krad.service.BusinessObjectService;

// class used to satisfy java bean convention
public class IacucProtocolNotification extends ProtocolNotification {

    private static final long serialVersionUID = -41490937286230774L;

    public IacucProtocolNotification() {
        super();
    }

    public void persistOwningObject(KcPersistableBusinessObjectBase object) {
        IacucProtocol protocol = (IacucProtocol)object;
        this.setOwningDocumentIdFk(protocol.getLastProtocolAction().getProtocolActionId());
        protocol.getLastProtocolAction().addNotification(this);
        KcServiceLocator.getService(BusinessObjectService.class).save(this);
    }

    public static IacucProtocolNotification copy(KcNotification notification) {
        IacucProtocolNotification newNotification = new IacucProtocolNotification();
        copy(notification, newNotification);
        return newNotification;
    }
}
