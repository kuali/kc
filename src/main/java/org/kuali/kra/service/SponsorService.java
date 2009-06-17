/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.service;

import java.util.Collection;

import org.kuali.kra.web.struts.form.SponsorHierarchyForm;

public interface SponsorService {
    /**
     * This method returns the sponsor name for a given sponsor code.
     * @param sponsorCode identifier for the sponsor
     * @return The name of the sponsor identified by this code.
     */
    public String getSponsorName(String sponsorCode);
    
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
     * This method is to run the sql scripts that are gathered during maintenance
     * @param hierarchyName
     * @param sqlScripts
     */
    public void saveSponsorHierachy(String hierarchyName, String sqlScripts);
    
    
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

    public void uploadScripts(String key, String scripts);

 }
