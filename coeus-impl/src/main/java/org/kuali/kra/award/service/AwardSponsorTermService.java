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
