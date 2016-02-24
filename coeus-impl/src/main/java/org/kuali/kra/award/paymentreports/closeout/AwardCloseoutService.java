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
package org.kuali.kra.award.paymentreports.closeout;

import org.kuali.kra.award.home.Award;

/**
 * 
 * This class represents the AwardCloseoutService - which are related to Award Closeout panel on Payment Reports and Terms panel.
 * 
 */
public interface AwardCloseoutService {
    
    /**
     * 
     * Whenever a save occurs on Payment, Reports and Terms tab; This method gets called from the action upon save 
     * and updates the due dates for award closeout static reports.
     *
     * @param award
     */
    void updateCloseoutDueDatesBeforeSave(Award award);
}
