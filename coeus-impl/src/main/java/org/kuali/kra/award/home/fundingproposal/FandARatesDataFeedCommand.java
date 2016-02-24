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
package org.kuali.kra.award.home.fundingproposal;

import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;

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
