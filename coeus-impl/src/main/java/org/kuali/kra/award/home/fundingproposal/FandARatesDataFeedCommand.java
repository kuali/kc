/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.service.FiscalYearMonthService;

import java.sql.Date;
import java.util.List;

class FandARatesDataFeedCommand extends ProposalDataFeedCommandBase {
    private static final String FANDA_COMMENT_PATTERN = "Added Unrecovered F & A from Proposal Number %s";
    
    private FiscalYearMonthService fiscalYearMonthService;
    
    /**
     * Constructs a FandARatesDataFeedCommand
     * @param award
     * @param proposal
     */
    public FandARatesDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }
   
    @Override
    void performDataFeed() {
        if (mergeType != FundingProposalMergeType.NOCHANGE) {
            int copyCount = 0;
            List<InstitutionalProposalUnrecoveredFandA> fAndAs = proposal.getInstitutionalProposalUnrecoveredFandAs();
            for (InstitutionalProposalUnrecoveredFandA ipUnrecoveredFandA : fAndAs) {
                award.add(copyFandA(ipUnrecoveredFandA));
                copyCount++;
            }
            if (copyCount > 0) {
                addFandARateComment(award, proposal);
            }
        }
    }
    
    private void addFandARateComment(Award award, InstitutionalProposal proposal) {
        String newComment = String.format(FANDA_COMMENT_PATTERN, proposal.getProposalNumber());
        appendComments(findOrCreateCommentOfSpecifiedType(new AwardCommentFactory().createFandaRateComment()), newComment);
    }

    private String convertOnCampusBooleanToString(boolean onCampusFlag) {
        return onCampusFlag ? Constants.ON_CAMUS_FLAG : Constants.OFF_CAMUS_FLAG;
    }

    /**
     * Copies an InstitutionalProposalUnrecoveredFandA to an AwardFandaRate
     * @param ipUnrecoveredFandA
     * @return AwardFandaRate
     */
    private AwardFandaRate copyFandA(InstitutionalProposalUnrecoveredFandA ipUnrecoveredFandA) {
        AwardFandaRate awardFandA = new AwardFandaRate();
        awardFandA.setApplicableFandaRate(ipUnrecoveredFandA.getApplicableIndirectcostRate());
        awardFandA.setFandaRateTypeCode(ipUnrecoveredFandA.getIndirectcostRateTypeCode()==null?null:ipUnrecoveredFandA.getIndirectcostRateTypeCode().toString());
        awardFandA.setFiscalYear(ipUnrecoveredFandA.getFiscalYear());
        awardFandA.setOnCampusFlag(convertOnCampusBooleanToString(ipUnrecoveredFandA.getOnCampusFlag()));
        awardFandA.setSourceAccount(ipUnrecoveredFandA.getSourceAccount());
        awardFandA.setUnderrecoveryOfIndirectCost(ipUnrecoveredFandA.getAmount());
        Integer fy = Integer.parseInt(ipUnrecoveredFandA.getFiscalYear());
        awardFandA.setStartDate(new Date(this.getFiscalYearMonthService().getFiscalYearStartDate(fy).getTimeInMillis()));
        awardFandA.setEndDate(new Date(this.getFiscalYearMonthService().getFiscalYearEndDate(fy).getTimeInMillis()));
        return awardFandA;
    }
    
    protected FiscalYearMonthService getFiscalYearMonthService() {
        if (this.fiscalYearMonthService == null) {
            this.fiscalYearMonthService = KcServiceLocator.getService(FiscalYearMonthService.class);
        }
        return this.fiscalYearMonthService;
    }
    
}
