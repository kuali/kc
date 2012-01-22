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
package org.kuali.kra.award.detailsdates;

import java.io.Serializable;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KeywordsService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;

/**
 * This class encapsulates presentation-specific data and behavior
 * for the award details and dates functionality.
 */
public class DetailsAndDatesFormHelper implements Serializable {
    
    private static final long serialVersionUID = 3960093546695537698L;

    private AwardForm parent;
    private Sponsor sponsorToBecomeAwardTransferringSponsor;
    
    /**
     * Constructs a ApprovedSubawardFormHelper
     * @param parent
     */
    public DetailsAndDatesFormHelper(AwardForm parent) {
        this.parent = parent;
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        sponsorToBecomeAwardTransferringSponsor = new Sponsor(); 
    }
    
    public Sponsor getSponsorToBecomeAwardTransferringSponsor() {
        return sponsorToBecomeAwardTransferringSponsor;
    }

    public void setSponsorToBecomeAwardTransferringSponsor(Sponsor sponsorToBecomeAwardTransferringSponsor) {
        this.sponsorToBecomeAwardTransferringSponsor = sponsorToBecomeAwardTransferringSponsor;
    }

    public AwardForm getParent() {
        return parent;
    }

    public void setParent(AwardForm parent) {
        this.parent = parent;
    }

    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }
    
    /**
     * 
     * This method adds a new AwardTransferringSponsor to the list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void addAwardTransferringSponsor() throws Exception {
        Sponsor awardTransferringSponsor = parent.getDetailsAndDatesFormHelper().getSponsorToBecomeAwardTransferringSponsor();
        boolean rulePassed = getKualiRuleService().applyRules(
                new AddAwardTransferringSponsorEvent("", parent.getAwardDocument(), 
                        parent.getAwardDocument().getAward(), awardTransferringSponsor));
        if (rulePassed) {
            Sponsor dbSponsor = (Sponsor) getBusinessObjectService().retrieve(awardTransferringSponsor);
            parent.getAwardDocument().getAward().addAwardTransferringSponsor(dbSponsor);
        }
    }
    
    /**
     * 
     * This method removes an AwardTransferringSponsor from the list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void deleteAwardTransferringSponsor(int lineToDelete) throws Exception {
        parent.getAwardDocument().getAward().getAwardTransferringSponsors().remove(lineToDelete);
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    protected KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }
    
}
