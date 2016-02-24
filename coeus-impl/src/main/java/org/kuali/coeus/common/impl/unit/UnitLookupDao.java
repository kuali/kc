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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.unit.Unit;

public interface UnitLookupDao {
    
    /**
     * This method finds the unit object whose number matches the argument <code>unitNumber</code>. The lookup logic is 
     * case insensitive i.e. the return value is the same irrespective of the case of the characters in the argument. 
     * @param unitNumber
     * @return the matching unit object or null if no match was found.
     */
    public Unit findUnitbyNumberCaseInsensitive(String unitNumber);
    
    /**
     * Return the top unit in the hierarchy, or the one with a NULL parent unit
     */
    public Unit getTopUnit();

}
