/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.bo.SponsorTermType;
import org.kuali.kra.service.AwardSponsorTermService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.springframework.transaction.annotation.Transactional;
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
