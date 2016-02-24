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
package org.kuali.kra.protocol.protocol.funding;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;

import java.util.List;

/**
 * Runs the rule processing for saving a ProtocolBase Funding Source.
 */
public class SaveProtocolFundingSourceLinkRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<SaveProtocolFundingSourceLinkEvent> {

    private static final String FUNDING_SOURCE_NUMBER = "fundingSourceNumber";
    
    private SpecialReviewService specialReviewService;
    private AwardService awardService;
    private InstitutionalProposalService institutionalProposalService;
    
    @Override
    public boolean processRules(SaveProtocolFundingSourceLinkEvent event) {
        boolean rulePassed = true;
        
        for (ProtocolFundingSourceBase protocolFundingSource : event.getProtocolFundingSources()) {
            String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
            String fundingSourceTypeCode = protocolFundingSource.getFundingSourceTypeCode();
            String protocolNumber = protocolFundingSource.getProtocolNumber();
            
            if (!getSpecialReviewService().isLinkedToSpecialReview(fundingSourceNumber, fundingSourceTypeCode, protocolNumber)) {
                rulePassed &= validateProtocolFundingSource(protocolFundingSource);
            }
        }
        
        return rulePassed;
    }
    
    private boolean validateProtocolFundingSource(ProtocolFundingSourceBase protocolFundingSource) {
        boolean isValid = true;
        
        String fundingSourceType = protocolFundingSource.getFundingSourceTypeCode();
        String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
        if (StringUtils.equals(FundingSourceType.AWARD, String.valueOf(fundingSourceType))) {
            Award award = getAward(fundingSourceNumber);
            if (!award.getAwardDocument().getPessimisticLocks().isEmpty()) {
                isValid = false;
                reportError(FUNDING_SOURCE_NUMBER, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_AWARD_LOCKED, fundingSourceNumber);
            }
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, String.valueOf(fundingSourceType))) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposal(fundingSourceNumber);
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
            specialReviewService = KcServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }
    
    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }
    
    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KcServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }
    
    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }
    
    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }

}
