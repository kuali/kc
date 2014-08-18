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
package org.kuali.kra.protocol.notification;

import org.kuali.coeus.common.notification.impl.service.KcNotificationRoleQualifierService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

/**
 * Defines the service to fill in module role qualifier information for ProtocolBase.
 */
public interface ProtocolNotificationRoleQualifierService extends KcNotificationRoleQualifierService {

    /**
     * Returns the ProtocolBase.
     * 
     * @return the ProtocolBase
     */
    ProtocolBase getProtocol();
    
    /**
     * Sets the ProtocolBase.
     *
     * @param protocol the ProtocolBase to set
     */
    void setProtocol(ProtocolBase protocol);

    /**
     * Returns the ProtocolBase Online Review.
     * 
     * @return the ProtocolBase Online Review
     */
    ProtocolOnlineReviewBase getProtocolOnlineReview();
    
    /**
     * Sets the ProtocolBase Online Review.
     *
     * @param protocolOnlineReview the ProtocolBase Online Review to set
     */
    void setProtocolOnlineReview(ProtocolOnlineReviewBase protocolOnlineReview);
    
}
