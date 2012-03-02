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
package org.kuali.kra.irb.protocol.funding;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Runs the rule processing for saving a Protocol Funding Source.
 */
public class SaveProtocolFundingSourceLinkRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<SaveProtocolFundingSourceLinkEvent> {

    private static final String FUNDING_SOURCE_NUMBER = "fundingSourceNumber";
    
    private SpecialReviewService specialReviewService;
    private AwardService awardService;
    private InstitutionalProposalService institutionalProposalService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(SaveProtocolFundingSourceLinkEvent event) {
        boolean rulePassed = true;
        
        for (ProtocolFundingSource protocolFundingSource : event.getProtocolFundingSources()) {
            String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
            String fundingSourceTypeCode = protocolFundingSource.getFundingSourceTypeCode();
            String protocolNumber = protocolFundingSource.getProtocolNumber();
            
            if (!getSpecialReviewService().isLinkedToSpecialReview(fundingSourceNumber, fundingSourceTypeCode, protocolNumber)) {
                rulePassed &= validateProtocolFundingSource(protocolFundingSource);
            }
        }
        
        for (ProtocolFundingSource protocolFundingSource : event.getDeletedProtocolFundingSources()) {
            rulePassed &= validateProtocolFundingSource(protocolFundingSource);
        }
        
        return rulePassed;
    }
    
    private boolean validateProtocolFundingSource(ProtocolFundingSource protocolFundingSource) {
        boolean isValid = true;
        
        String fundingSourceType = protocolFundingSource.getFundingSourceTypeCode();
        String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
        if (StringUtils.equals(FundingSourceType.AWARD, String.valueOf(fundingSourceType))) {
            Award award = getAward(fundingSourceNumber);
            award.getAwardDocument().refreshPessimisticLocks();
            if (!award.getAwardDocument().getPessimisticLocks().isEmpty()) {
                isValid = false;
                reportError(FUNDING_SOURCE_NUMBER, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_AWARD_LOCKED, fundingSourceNumber);
            }
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, String.valueOf(fundingSourceType))) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposal(fundingSourceNumber);
            institutionalProposal.getInstitutionalProposalDocument().refreshPessimisticLocks();
            if (!institutionalProposal.getInstitutionalProposalDocument().getPessimisticLocks().isEmpty()) {
                isValid = false;
                reportError(FUNDING_SOURCE_NUMBER, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_INSTITUTIONAL_PROPOSAL_LOCKED, fundingSourceNumber);
            }
        }
        
        return isValid;
    }
    
    private Award getAward(String fundingSourceNumber) {
        Award award = null;
        
        List<Award> awards = getAwardService().findAwardsForAwardNumber(fundingSourceNumber);
        
        if (!awards.isEmpty()) {
            award = awards.get(awards.size() - 1);
        }
        
        return award;
    }
    
    private InstitutionalProposal getInstitutionalProposal(String fundingSourceNumber) {
        InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getActiveInstitutionalProposalVersion(fundingSourceNumber);
        
        if (institutionalProposal == null) {
            institutionalProposal = getInstitutionalProposalService().getPendingInstitutionalProposalVersion(fundingSourceNumber);
        }

        return institutionalProposal;
    }
    
    private SpecialReviewService getSpecialReviewService() {
        if (specialReviewService == null) {
            specialReviewService = KraServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }
    
    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }
    
    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KraServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }
    
    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KraServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }
    
    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }

}