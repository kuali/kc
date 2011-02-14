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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Runs the rule processing for saving a Protocol Funding Source.
 */
public class SaveProtocolFundingSourceRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<SaveProtocolFundingSourceEvent> {

    private static final String FUNDING_SOURCE_NUMBER = "fundingSourceNumber";
    
    private AwardService awardService;
    private InstitutionalProposalService institutionalProposalService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(SaveProtocolFundingSourceEvent event) {
        boolean rulePassed = true;
        
        int i = 0;
        for (ProtocolFundingSource protocolFundingSource : event.getProtocolFundingSources()) {
            String errorPath = event.getErrorPathPrefix() + "[" + i++ + "]";
            
            GlobalVariables.getMessageMap().addToErrorPath(errorPath);
            Integer fundingSourceType = protocolFundingSource.getFundingSourceTypeCode();
            String fundingSourceId = protocolFundingSource.getFundingSource();
            String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
            if (StringUtils.equals(FundingSourceType.AWARD, String.valueOf(fundingSourceType))) {
                Award award = getAward(fundingSourceId);
                if (!award.getAwardDocument().getPessimisticLocks().isEmpty()) {
                    reportError(FUNDING_SOURCE_NUMBER, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_AWARD_LOCKED, fundingSourceNumber);
                }
            } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, String.valueOf(fundingSourceType))) {
                InstitutionalProposal institutionalProposal = getInstitutionalProposal(fundingSourceId);
                if (!institutionalProposal.getInstitutionalProposalDocument().getPessimisticLocks().isEmpty()) {
                    reportError(FUNDING_SOURCE_NUMBER, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_INSTITUTIONAL_PROPOSAL_LOCKED, fundingSourceNumber);
                }
            }
            GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        }
        
        return rulePassed;
    }
    
    private Award getAward(String awardId) {
        Award award = null;
        
        if (StringUtils.isNotBlank(awardId)) {
            award = getAwardService().getAward(Long.valueOf(awardId));
        }
        
        return award;
    }
    
    private InstitutionalProposal getInstitutionalProposal(String institutionalProposalId) {
        InstitutionalProposal institutionalProposal = null;
        
        if (StringUtils.isNotBlank(institutionalProposalId)) {
            institutionalProposal = getInstitutionalProposalService().getInstitutionalProposal(institutionalProposalId);
        }
        
        return institutionalProposal;
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