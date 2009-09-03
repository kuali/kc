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
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardFundingProposalBean implements Serializable {
    private static final long serialVersionUID = 7278945841002454778L;

    private AwardForm awardForm;
    private InstitutionalProposal newFundingProposal;
    
    private List<Award> allAwardsForAwardNumber;
    
    public AwardFundingProposalBean(AwardForm awardForm) {
        this.awardForm = awardForm;
        createNewFundingProposal();
    }

    AwardFundingProposalBean() {
        
    }
    
    /**
     * This method adds a Funding Proposal
     */
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
     * This method returns all Award versions for the awardNumber of the Award associated found from the AwardDocument on the associated AwardForm.
     * @return
     */
    public List<Award> getAllAwardsForAwardNumber() {
        if(allAwardsForAwardNumber == null) {
            Award thisAward = getAward();
            allAwardsForAwardNumber = getAwardService().findAwardsForAwardNumber(thisAward.getAwardNumber());
            if(thisAward.isPersisted()) {
                replaceThisAwardInListOfFoundAwards(thisAward);
            } else {
                addUnsavedAwardToListOfAwards(thisAward);
            }
        }
        return allAwardsForAwardNumber;
    }

    private void replaceThisAwardInListOfFoundAwards(Award thisAward) {
        int lastIndex = allAwardsForAwardNumber.size() - 1; 
        if(lastIndex >= 0 && allAwardsForAwardNumber.get(lastIndex).getAwardId().equals(thisAward.getAwardId())) {
            allAwardsForAwardNumber.set(lastIndex, thisAward);
        }
    }

    private void addUnsavedAwardToListOfAwards(Award thisAward) {
        if(thisAward.getAwardId() == null) {
            allAwardsForAwardNumber.add(thisAward);
        }
    }
    
    /**
     * This method returns the size of the allAwardsForAwardNumber collection 
     * @return
     */
    public int getAllAwardsForAwardNumberSize() {
        return getAllAwardsForAwardNumber().size();
    }

    /**
     * Gets the newFundingProposal attribute. 
     * @return Returns the newFundingProposal.
     */
    public InstitutionalProposal getNewFundingProposal() {
        lazilyLoadFundingProposal();
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
    BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    AwardService getAwardService() {
        return (AwardService) KraServiceLocator.getService(AwardService.class);
    }
    
    private void createNewFundingProposal() {
        newFundingProposal = new InstitutionalProposal();
        newFundingProposal.setProposalNumber(null);
    }
    
    /**
     * @return
     */
    Award getAward() {
        return awardForm.getAwardDocument().getAward();
    }
    
    private void lazilyLoadFundingProposal() {
        Long proposalId = newFundingProposal.getProposalId();
        String proposalNumber = newFundingProposal.getProposalNumber();
        InstitutionalProposal foundProposal = null;
        if(proposalId != null && proposalNumber == null) {
            foundProposal = findProposalById(proposalId);
        } else if(proposalNumber != null && proposalId == null) {
            foundProposal = findProposalByProposalNumber(proposalNumber);
        }
        if(foundProposal != null) {
            newFundingProposal = foundProposal; 
        }
    }

    private InstitutionalProposal findProposalById(Long proposalId) {
        Map<String, Object> identifiers = new HashMap<String, Object>();
        identifiers.put("proposalId", proposalId);
        return (InstitutionalProposal) getBusinessObjectService().findByPrimaryKey(InstitutionalProposal.class, identifiers);
    }
    
    private InstitutionalProposal findProposalByProposalNumber(String proposalNumber) {
        LookupableHelperService service = getInstitutionalProposalLookupService();
        service.setBusinessObjectClass(InstitutionalProposal.class);
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", proposalNumber);
        @SuppressWarnings("unchecked") List foundProposals = service.getSearchResults(criteria);        
        InstitutionalProposal proposal = foundProposals.size() == 1 ? (InstitutionalProposal) foundProposals.toArray()[0] : null;
        return proposal;
    }
    
    private LookupableHelperService getInstitutionalProposalLookupService() {
        return KraServiceLocator.getService("institutionalProposalLookupableHelperService");
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
        boolean valid =  newFundingProposal.getProposalId() != null;
        if(!valid) {
            String msgArg = newFundingProposal.getProposalNumber();
            if(msgArg == null) {
                msgArg = "(empty)";
            }
            GlobalVariables.getErrorMap().putError("fundingProposalBean.newFundingProposal", "error.fundingproposal.not.found", msgArg);
        }
        return valid;
    }
}
