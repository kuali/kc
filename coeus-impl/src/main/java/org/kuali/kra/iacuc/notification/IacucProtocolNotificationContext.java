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

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRoleQualifierService;


/**
 * This class extends the notification context base and provides some helpful functions for
 * any Protocol- specific events.
 */
public class IacucProtocolNotificationContext extends ProtocolNotificationContextBase {

    private static final long serialVersionUID = 7517888688386565168L;

    public IacucProtocolNotificationContext(IacucProtocol protocol, IacucProtocolOnlineReview protocolOnlineReview, String actionTypeCode, String contextName, IacucProtocolNotificationRenderer renderer) {
        this(protocol, actionTypeCode, contextName, renderer);
        ((ProtocolNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocolOnlineReview(protocolOnlineReview);
    }
    
    public IacucProtocolNotificationContext(IacucProtocol protocol, String actionTypeCode, String contextName, IacucProtocolNotificationRenderer renderer, String forwardName) {
        this(protocol, actionTypeCode, contextName, renderer);
        setForwardName(forwardName);
    }
    
    public IacucProtocolNotificationContext(IacucProtocol protocol, String actionTypeCode, String contextName, IacucProtocolNotificationRenderer renderer) {
        super(protocol, actionTypeCode, contextName, renderer);
        setNotificationRoleQualifierService(KcServiceLocator.getService(IacucProtocolNotificationRoleQualifierService.class));
        ((IacucProtocolNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocol(protocol);
    }

    @Override
    public String getModuleCode() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    
}
