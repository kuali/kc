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

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;

public class IacucProtocolNotificationRequestBean extends ProtocolNotificationRequestBeanBase {


    private static final long serialVersionUID = 539022630037045456L;

    public IacucProtocol getIacucProtocol() {
        return (IacucProtocol)getProtocol();
    }

    public IacucProtocolOnlineReview getIacucProtocolOnlineReview() {
        return (IacucProtocolOnlineReview)getProtocolOnlineReview();
    }

    public IacucProtocolNotificationRequestBean(IacucProtocol protocol, String actionType, String description) {
        super(protocol, actionType, description);
    }

    
    public IacucProtocolNotificationRequestBean(IacucProtocol protocol, IacucProtocolOnlineReview protocolOnlineReview, String actionType,
            String description, String docNumber, String olrEvent) {
        super(protocol, protocolOnlineReview, actionType, description, docNumber, olrEvent);
    }
 
        
}
