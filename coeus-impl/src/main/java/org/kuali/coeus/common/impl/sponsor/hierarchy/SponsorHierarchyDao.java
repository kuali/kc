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
package org.kuali.coeus.common.impl.sponsor.hierarchy;

import java.util.Iterator;
import java.util.List;

public interface SponsorHierarchyDao {
    
    /**
     * 
     * This method is to get the sposor hierarchy name for the drop down
     * @return
     */
    
    public Iterator getTopSponsorHierarchy();
    
    /**
     * 
     * This method is to load all sponsor codes for future duplicate code checking in sponsor hierarchy maint
     * @param hierarchyName
     * @return
     */
    public Iterator getAllSponsors(String hierarchyName);
    
    /**
     * Get the unique grouping names at specificed level in the specified hierarchy.
     * @param hierarchyName
     * @param depth
     * @return
     */
    List<String> getUniqueNamesAtLevel(String hierarchyName, int depth);


}
