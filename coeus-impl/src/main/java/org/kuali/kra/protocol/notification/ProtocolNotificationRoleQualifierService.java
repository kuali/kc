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
