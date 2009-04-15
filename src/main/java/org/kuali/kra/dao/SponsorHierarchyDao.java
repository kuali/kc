/*
 * Copyright 2006-2009 The Kuali Foundation.
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
package org.kuali.kra.dao;

import java.util.Iterator;

public interface SponsorHierarchyDao {
    
    /**
     * 
     * This method is to get the sposor hierarchy name for the drop down
     * @return
     */
    
    public Iterator getTopSponsorHierarchy();
    
    /**
     * 
     * This method execute all the scripts that was created in sponsor hierarchy maint
     * @param sqls
     */
    public void runScripts(final String[] sqls);
    
    /**
     * 
     * This method is to load all sponsor codes for future duplicate code checking in sponsor hierarchy maint
     * @param hierarchyName
     * @return
     */
    public Iterator getAllSponsors(String hierarchyName);
    
    /**
     * 
     * This method is to retrieve sponsor codes for the group.  It's using PS because
     * it's not working with reportquery and dwr/ajax.
     * @param hierarchyName
     * @param level
     * @param levelName
     * @return
     */
    public String getSponsorCodesForGroup(String hierarchyName, int level, String[] levelName);
    
    /**
     * 
     * This method is to retrieve subgroup names when dynamic loading sponsor hierarchy.
     * @param hierarchyName
     * @param level
     * @param levelName
     * @return
     */
    public String getsubGroups(String hierarchyName, int level, String[] levelName);

    public String getSponsorCodesForDeletedGroup(String hierarchyName, int level, String[] levelName);


}
