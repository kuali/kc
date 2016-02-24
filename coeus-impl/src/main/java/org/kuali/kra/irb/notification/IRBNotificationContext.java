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
package org.kuali.kra.irb.notification;


import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;

/**
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public class IRBNotificationContext extends ProtocolNotificationContextBase {

    private static final long serialVersionUID = 6642334312368480034L;
    
    /**
     * Constructs an IRB notification context and sets the necessary services.
     * @param protocol
     * @param protocolOnlineReview
     * @param actionTypeCode
     * @param contextName
     */
    public IRBNotificationContext(Protocol protocol, ProtocolOnlineReview protocolOnlineReview, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        this(protocol, actionTypeCode, contextName, renderer);        
        ((IRBNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocolOnlineReview(protocolOnlineReview);
    }

    /**
     * Constructs an IRB notification context and sets the necessary services.
     * @param protocol
     * @param actionTypeCode
     * @param contextName
     */
    public IRBNotificationContext(Protocol protocol, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        super(protocol, actionTypeCode, contextName, renderer);
        setNotificationRoleQualifierService(KcServiceLocator.getService(IRBNotificationRoleQualifierService.class));
        ((IRBNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocol(protocol);
    }
    
    public IRBNotificationContext(Protocol protocol, String actionTypeCode, String contextName, NotificationRenderer renderer, String forwardName) {
        this(protocol, actionTypeCode, contextName, renderer);        
        setForwardName(forwardName);
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }    
}
