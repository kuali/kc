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
package org.kuali.kra.award.detailsdates;

import org.kuali.coeus.common.framework.keyword.KeywordsService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;

import java.io.Serializable;

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
        init();
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
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
    protected KualiRuleService getKualiRuleService() {
        return KcServiceLocator.getService(KualiRuleService.class);
    }
    
}
