/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.dao;

import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AwardDao {
    public String getAwardNumber(Long awardId);

    /**
     * Does a non-wildcarded yet still limited search of awards, retrieved by the given criteria
     * @param fieldValues the field values to set
     * @return a Collection of found awards
     */
    public Collection<Award> retrieveAwardsByCriteria(Map<String, Object> fieldValues);

}
