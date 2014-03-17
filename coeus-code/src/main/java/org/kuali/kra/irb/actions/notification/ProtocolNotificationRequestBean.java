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
package org.kuali.kra.irb.actions.notification;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;

public class ProtocolNotificationRequestBean extends ProtocolNotificationRequestBeanBase {
    

    private static final long serialVersionUID = -4383148548571108022L;
    
    public ProtocolNotificationRequestBean(Protocol protocol, String actionType, String description) {
        super(protocol, actionType, description);
    }

    
    public ProtocolNotificationRequestBean(Protocol protocol, ProtocolOnlineReview protocolOnlineReview, String actionType, String description, String docNumber, String olrEvent) {
        super(protocol, protocolOnlineReview, actionType, description, docNumber, olrEvent);
    }
 
    public Protocol getProtocol() {
        return (Protocol)super.getProtocol();
    }
    
    public ProtocolOnlineReview getProtocolOnlineReview() {
        return (ProtocolOnlineReview)super.getProtocolOnlineReview();
    }
}
