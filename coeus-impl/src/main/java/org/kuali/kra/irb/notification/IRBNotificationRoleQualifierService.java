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
package org.kuali.kra.irb.notification;

import org.kuali.coeus.common.notification.impl.service.KcNotificationRoleQualifierService;
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
