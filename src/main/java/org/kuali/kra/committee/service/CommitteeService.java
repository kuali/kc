/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.committee.service;

import java.util.Collection;
import java.util.List;

import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;

/**
 * The Committee Service provides a set of methods for
 * working with committees.
 */
public interface CommitteeService {

    /**
     * Retrieve a committee from the database based upon its Committee ID.
     * @param committeeId the committee ID
     * @return the committee or null if not found
     */
    public Committee getCommitteeById(String committeeId);
   
    /**
     * Add the research areas to the committee.  Duplicates are not added.
     * @param committee the committee to add to
     * @param researchAreas the research areas to add
     */
    public void addResearchAreas(Committee committee, Collection<ResearchArea> researchAreas);

    /**
     * Get the valid upcoming committee dates for scheduling a protocol.
     * This method is used by CommitteeScheduleValuesFinder.
     * @param id the committee's unique id
     * @return list of key/value pairs with the dates  
     */
    public List<KeyLabelPair> getValidCommitteeDates(String committeeId);
    
    /**
     * Get the valid upcoming committee dates for scheduling a protocol.
     * This method is used exclusively by DWR for obtaining a list to show
     * to the end user in a drop-down menu.  To handle the conversion from
     * Java to JavaScript, the list is returned as a comma separated string.
     * The format is: <key1>,<description1>,<key2>,<description2>,...
     * @param committeeId the committee's unique id
     * @return the string representation of schedule dates
     */
    public String getValidCommitteeDatesForAjax(String committeeId);
}
