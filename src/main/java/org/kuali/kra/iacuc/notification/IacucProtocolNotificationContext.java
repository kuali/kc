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
package org.kuali.kra.iacuc.notification;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationRenderer;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.notification.ProtocolNotificationContext;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;


/**
 * This class extends the notification context base and provides some helpful functions for
 * any Protocol- specific events.
 */
public class IacucProtocolNotificationContext extends ProtocolNotificationContext {

    private static final long serialVersionUID = 7517888688386565168L;

    public IacucProtocolNotificationContext(Protocol protocol, ProtocolOnlineReview protocolOnlineReview, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        super(protocol, protocolOnlineReview, actionTypeCode, contextName, renderer);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

}
