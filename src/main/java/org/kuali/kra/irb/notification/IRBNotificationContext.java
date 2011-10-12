/*
 * Copyright 2005-2010 The Kuali Foundation
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


import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationContextBase;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;

/**
 * 
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public abstract class IRBNotificationContext extends NotificationContextBase {
    
    protected Protocol protocol;
    protected ProtocolOnlineReview protocolOnlineReview;

    /**
     * 
     * Constructs a IRBNotificationContext.java and sets the necessary services.
     * @param protocol
     */
    public IRBNotificationContext() {
        setNotificationService(KraServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KraServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRenderingService(new IRBNotificationRenderingServiceImpl());
        setNotificationRoleQualifierService(new IRBNotificationRoleQualifierServiceImpl());
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
        ((IRBNotificationRenderingService) getNotificationRenderingService()).setProtocol(protocol);
        ((IRBNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocol(protocol);
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getDocumentNumber()
     */
    public String getDocumentNumber() {
        return getProtocol().getProtocolDocument().getDocumentNumber();
    }

    /**
     * 
     * This method allows the specifying of an individual protocol online review for
     * use in resolving role qualifier values.
     * @param onlineReview
     */
    public void setProtocolOnlineReview(ProtocolOnlineReview protocolOnlineReview) {
        this.protocolOnlineReview = protocolOnlineReview;
        ((IRBNotificationRoleQualifierService) getNotificationRoleQualifierService()).setProtocolOnlineReview(protocolOnlineReview);
    }
    
    public ProtocolOnlineReview getProtocolOnlineReview() {
        return protocolOnlineReview;
    }
    
}
