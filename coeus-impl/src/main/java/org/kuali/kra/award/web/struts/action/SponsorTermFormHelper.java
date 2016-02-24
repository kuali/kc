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
package org.kuali.kra.award.web.struts.action;

import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.core.api.util.KeyValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a helper class for sponsor term form helper
 */
public class SponsorTermFormHelper implements Serializable {

    private AwardForm parent;
    
    private List<SponsorTerm> newSponsorTerms;
    private List<KeyValue> sponsorTermTypes;
    
    /**
     * Constructs a CostShareFormHelper
     * @param parent
     */
    public SponsorTermFormHelper(AwardForm parent) {
        this.parent = parent;
        setNewSponsorTerms(new ArrayList<SponsorTerm>());
        sponsorTermTypes = new ArrayList<KeyValue>();
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
    public List<KeyValue> getSponsorTermTypes() {
        return sponsorTermTypes;
    }
    
    /**
     * Sets the awardSponsorTermsTypes attribute value.
     * @param awardSponsorTermsTypes The awardSponsorTermsTypes to set.
     */
    public void setSponsorTermTypes(List<KeyValue> sponsorTermTypes) {
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
