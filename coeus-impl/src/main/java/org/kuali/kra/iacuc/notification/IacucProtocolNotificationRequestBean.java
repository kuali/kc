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
