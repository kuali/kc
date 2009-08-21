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
package org.kuali.kra.award.home.fundingproposal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kns.service.BusinessObjectService;

public class AwardFundingProposalBean implements Serializable {
    private static final long serialVersionUID = 7278945841002454778L;

    private AwardForm awardForm;
    private InstitutionalProposal newFundingProposal;
    
    public AwardFundingProposalBean(AwardForm awardForm) {
        this.awardForm = awardForm;
        createNewFundingProposal();
    }

    public void addFundingProposal() {
        if(getNewFundingProposal() != null) {
            if(validateForAdd()) {
                getAward().add(newFundingProposal);
                performDataFeeds(getAward(), newFundingProposal);
                createNewFundingProposal();
            }
        }
    }

    /**
     * This method deletes a Funding proposal for the specified index
     * @param index
     */
    public void deleteAwardFundingProposal(int index) {
        if(index >= 0) {
            getAward().removeFundingProposal(index);
        }
    }
    
    /**
     * Gets the newFundingProposal attribute. 
     * @return Returns the newFundingProposal.
     */
    public InstitutionalProposal getNewFundingProposal() {
        lazilyLoadFundingProposal(newFundingProposal.getProposalId());
        return newFundingProposal;
    }

    /**
     * Sets the newFundingProposal attribute value.
     * @param newFundingProposal The newFundingProposal to set.
     */
    public void setNewFundingProposal(InstitutionalProposal newFundingProposal) {
        this.newFundingProposal = newFundingProposal;
    }

    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    private void createNewFundingProposal() {
        newFundingProposal = new InstitutionalProposal();
        newFundingProposal.setProposalNumber(null);
    }
    
    /**
     * @return
     */
    private Award getAward() {
        return awardForm.getAwardDocument().getAward();
    }
    
    private void lazilyLoadFundingProposal(Long proposalId) {
        if(proposalId != null && newFundingProposal.getProposalNumber() == null) {
            Map<String, Object> identifiers = new HashMap<String, Object>();
            identifiers.put("proposalId", proposalId);
            newFundingProposal = (InstitutionalProposal) getBusinessObjectService().findByPrimaryKey(InstitutionalProposal.class, identifiers);
        }
    }
    
    private void performDataFeeds(Award award, InstitutionalProposal proposal) {
        new CommentsDataFeedCommand(award, proposal).performDataFeed();
        new SponsorDataFeedCommand(award, proposal).performDataFeed();
        new DatesDataFeedCommand(award, proposal).performDataFeed();
        new SpecialReviewDataFeedCommand(award, proposal).performDataFeed();
        new CostSharingDataFeedCommand(award, proposal).performDataFeed();
        new FandARatesDataFeedCommand(award, proposal).performDataFeed();
        new KeywordsDataFeedCommand(award, proposal).performDataFeed();
    }

    private boolean validateForAdd() {
        return newFundingProposal.getProposalNumber() != null;
    }
}
