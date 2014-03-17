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
