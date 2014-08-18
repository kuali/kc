/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.service;

import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;


/**
 * This is the Award Sponsor Term Service Interface.
 */
public interface AwardSponsorTermService {
    
    /**
     * This method assigns all of the Sponsor Term Types for Panel header display
     * @return
     */
    List<KeyValue> retrieveSponsorTermTypesToAwardFormForPanelHeaderDisplay();
    
    /**
     * This method creates an empty list of Sponsor Term objects corresponding to Sponsor Term Types
     * @param sponsorTermTypes
     * @return
     */
    List<SponsorTerm> getEmptyNewSponsorTerms(List<KeyValue> sponsorTermTypes);
    
}
