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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.lookup.keyvalue.SponsorTermTypeValuesFinder;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.service.AwardSponsorTermService;
/**
 * This is the service class for Term tab in Award Payments Reports and Terms page.
 */
public class AwardSponsorTermServiceImpl implements AwardSponsorTermService {

    
    /**
     * 
     * This method sets the List awardSponsorTermsTypes on the AwardForm
     * 
     * @param
     */
    public List<KeyLabelPair> assignSponsorTermTypesToAwardFormForPanelHeaderDisplay(){
        SponsorTermTypeValuesFinder sponsorTermTypeValuesFinder =
                                                 new SponsorTermTypeValuesFinder();
        List<KeyLabelPair> sponsorTermTypes = new ArrayList<KeyLabelPair>();
        
        sponsorTermTypes = sponsorTermTypeValuesFinder.getKeyValues();
   
        return sponsorTermTypes;
    }
    
    /**
     * 
     * This method assigns the empty list of Sponsor Terms to the awardForm.
     * 
     * @param awardForm
     * @param reportClasses
     */
    public List<SponsorTerm> addEmptyNewSponsorTerms(List<KeyLabelPair> sponsorTermTypes){
        List<SponsorTerm> sponsorTerms = new ArrayList<SponsorTerm>();
        for(int i=0;i<sponsorTermTypes.size();i++){
            sponsorTerms.add(new SponsorTerm());
        }
        return sponsorTerms;
    }

}
