/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
