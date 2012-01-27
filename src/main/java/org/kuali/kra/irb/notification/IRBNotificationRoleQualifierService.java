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

import org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;

/**
 * Defines the service to fill in module role qualifier information for Protocol.
 */
public interface IRBNotificationRoleQualifierService extends KcNotificationRoleQualifierService {

    /**
     * Returns the Protocol.
     * 
     * @return the Protocol
     */
    Protocol getProtocol();
    
    /**
     * Sets the Protocol.
     *
     * @param protocol the Protocol to set
     */
    void setProtocol(Protocol protocol);

    /**
     * Returns the Protocol Online Review.
     * 
     * @return the Protocol Online Review
     */
    ProtocolOnlineReview getProtocolOnlineReview();
    
    /**
     * Sets the Protocol Online Review.
     *
     * @param protocolOnlineReview the Protocol Online Review to set
     */
    void setProtocolOnlineReview(ProtocolOnlineReview protocolOnlineReview);
    
}