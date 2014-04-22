/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.impl.sponsor;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.impl.sponsor.hierarchy.SponsorHierarchyForm;

import java.util.Collection;
import java.util.List;

public interface SponsorHierarchyMaintenanceService {

    /**
     * 
     * This method to get the list of unique sponsorhierarchy name.
     * @return
     */
    public String getTopSponsorHierarchy();
    
    /**
     * 
     * This method is to retrieve next level nodes.  It is called by ajax.
     * @param node
     * @return
     */
    public String getSubSponsorHierarchiesForTreeView(String hierarchyName, String depth, String groups) ;

    /**
     * 
     * This method save the new hierarchy that is coming from copy action
     * @param sponsorHierarchyForm
     */
    public void copySponsorHierarchy(SponsorHierarchyForm sponsorHierarchyForm);
    
    /**
     * 
     * This method is to delete the selected hierarchy
     * @param sponsorHierarchyForm
     */
    public void deleteSponsorHierarchy(SponsorHierarchyForm sponsorHierarchyForm);
    
    /**
     * 
     * This method the sponsor hierarchy name for drop downs
     * @return
     */
    public Collection getTopSponsorHierarchyList();
    
    /**
     * 
     * This method to load data for sponsor hierarchy maint
     * @param hierarchyName
     * @return
     */
    public String loadToSponsorHierachyMt(String hierarchyName);
    
    
    /**
     * 
     * This method get the sponsor codes for the groups that is selected for deletion.
     * @param hierarchyName
     * @param depth
     * @param groups
     * @return
     */
    public String getSponsorCodes(String hierarchyName, String depth, String groups);

    /**
     * 
     * This method is to put the sponsorcodes for the hierarchy in session. so it can be checked to be excluded for search.
     * @param sponsorCodes
     */
    public void updateSponsorCodes(String sponsorCodes);
    
    /**
     * 
     * Insert new sponsors into the hierarchy defined by hierarchy name and levels. Will only queue action as this will be used primarily by Ajax. To
     * execute the action call executeActions.
     * @param hierarchyName
     * @param sponsorCodes
     * @param levels
     * @param sortIds
     */
    public void insertSponsor(String hierarchyName, String[] sponsorCodes, String[] levels,
            Integer[] sortIds);
    
    /**
     * 
     * Delete the sponsor identified by hierarchy name and levels. Will only queue action as this will be used primarily by Ajax. To
     * execute the action call executeActions
     * @param hierarchyName
     * @param sponsorCode can be null to delete all sponsors defined by levels
     * @param levels
     */
    public void deleteSponsor(String hierarchyName, String sponsorCode, String[] levels);
    
    /**
     * 
     * Change the hierarchy group name at the specified level. Will only queue action as this will be used primarily by Ajax. To
     * execute the action call executeActions.
     * @param hierarchyName
     * @param levelToChange
     * @param oldGroupName
     * @param newGroupName
     * @param levels
     */
    public void updateGroupName(String hierarchyName, Integer levelToChange, String oldGroupName, String newGroupName, String[] levels);
 
    /**
     * 
     * Change the sponsor sort order at the specified level. Will only queue action as this will be used primarily by Ajax. To
     * execute the action call executeActions.
     * @param hierarchyName
     * @param levelToChange
     * @param moveDown
     * @param levels
     */
    public void changeSponsorSortOrder(String hierarchyName, Integer levelToChange, Boolean moveDown, String[] levels);
    
    /**
     * 
     * Execute all actions queued by insertSponsor, deleteSponsor, updateGroupName or changeSponsorSortOrder
     */
    public void executeActions();
    
    /**
     * 
     * Remove all previously queued actions
     */
    public void clearCurrentActions();
    
    /**
     * Get the list of groupings at the specified level.
     * @param hierarchyName
     * @param level
     * @return
     */
    List<String> getUniqueGroupingNames(String hierarchyName, Integer level);
}
