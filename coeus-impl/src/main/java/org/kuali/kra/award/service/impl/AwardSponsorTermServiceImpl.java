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
package org.kuali.kra.award.service.impl;

import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTermType;
import org.kuali.kra.award.service.AwardSponsorTermService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * This is the service class for Term tab in Award Payments Reports and Terms page.
 */
@Transactional
public class AwardSponsorTermServiceImpl implements AwardSponsorTermService {

    
    /**
     * 
     * This method retrieves the list of Sponsor Term types from Database.
     * 
     * @param
     */
    public List<KeyValue> retrieveSponsorTermTypesToAwardFormForPanelHeaderDisplay(){
        PersistableBusinessObjectValuesFinder persistableBusinessObjectValuesFinder = new PersistableBusinessObjectValuesFinder();
        persistableBusinessObjectValuesFinder.setBusinessObjectClass(SponsorTermType.class);
        persistableBusinessObjectValuesFinder.setKeyAttributeName("sponsorTermTypeCode");
        persistableBusinessObjectValuesFinder.setLabelAttributeName("description");
        return persistableBusinessObjectValuesFinder.getKeyValues();
    }
    
    /**
     * 
     * This method creates and returns a list of empty Sponsor Terms
     * 
     * @param awardForm
     * @param reportClasses
     */
    public List<SponsorTerm> getEmptyNewSponsorTerms(List<KeyValue> sponsorTermTypes){
        List<SponsorTerm> sponsorTerms = new ArrayList<SponsorTerm>();
        for(int i=0;i<sponsorTermTypes.size();i++){
            sponsorTerms.add(new SponsorTerm());
        }
        return sponsorTerms;
    }

}
