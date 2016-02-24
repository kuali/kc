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
package org.kuali.kra.award.notification;

import org.kuali.coeus.common.notification.impl.service.KcNotificationRoleQualifierService;
import org.kuali.kra.award.home.Award;

/**
 * Defines the service to fill in module role qualifier information for Award.
 */
public interface AwardNotificationRoleQualifierService extends KcNotificationRoleQualifierService {

    /**
     * Returns the Award.
     * 
     * @return the Award
     */
    Award getAward();
    
    /**
     * Sets the Award.
     *
     * @param award the Award to set
     */
    void setAward(Award award);
    
}
