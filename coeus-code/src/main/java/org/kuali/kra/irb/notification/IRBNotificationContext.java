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