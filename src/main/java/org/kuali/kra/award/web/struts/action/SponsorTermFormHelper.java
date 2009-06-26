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
package org.kuali.kra.award.web.struts.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This is a helper class for sponsor term form helper
 */
public class SponsorTermFormHelper implements Serializable {

    private AwardForm parent;
    
    private List<SponsorTerm> newSponsorTerms;
    private List<KeyLabelPair> sponsorTermTypes;
    
    /**
     * Constructs a CostShareFormHelper
     * @param parent
     */
    public SponsorTermFormHelper(AwardForm parent) {
        this.parent = parent;
        setNewSponsorTerms(new ArrayList<SponsorTerm>());
        sponsorTermTypes = new ArrayList<KeyLabelPair>();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newSponsorTerms = new ArrayList<SponsorTerm>();
    }

    /**
     * Gets the newSponsorTerms attribute. 
     * @return Returns the newSponsorTerms.
     */
    public List<SponsorTerm> getNewSponsorTerms() {
        return newSponsorTerms;
    }



    /**
     * Sets the newAwardSponsorTerms attribute value.
     * @param newAwardSponsorTerms The newAwardSponsorTerms to set.
     */
    public void setNewSponsorTerms(List<SponsorTerm> newSponsorTerms) {
        this.newSponsorTerms = newSponsorTerms;
    }
    
    /**
     * Gets the awardSponsorTermsTypes attribute. 
     * @return Returns the awardSponsorTermsTypes.
     */
    public List<KeyLabelPair> getSponsorTermTypes() {
        return sponsorTermTypes;
    }
    
    /**
     * Sets the awardSponsorTermsTypes attribute value.
     * @param awardSponsorTermsTypes The awardSponsorTermsTypes to set.
     */
    public void setSponsorTermTypes(List<KeyLabelPair> sponsorTermTypes) {
        this.sponsorTermTypes = sponsorTermTypes;
    }



    /**
     * This method returns the AwardDocument.
     * @return
     */
    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }
    
    
}
