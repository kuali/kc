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
package org.kuali.kra.award.home.fundingproposal;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

class FandARatesDataFeedCommand extends ProposalDataFeedCommandBase {
    private static final String FANDA_COMMENT_PATTERN = "Added Unrecovered F & A from Proposal Number %s";
    
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    
    /**
     * Constructs a FandARatesDataFeedCommand
     * @param award
     * @param proposal
     */
    public FandARatesDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }
   
    /**
     * @see org.kuali.kra.award.home.fundingproposal.ProposalDataFeedCommandBase#performDataFeed()
     */
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
    
    private void assignDates(AwardFandaRate awardFandA, Calendar calendar) {
        awardFandA.setStartDate(calculateFirstDayOfYear(calendar));
        awardFandA.setEndDate(calculateLastDayOfYear(calendar));
    }

    private void calculateDates(AwardFandaRate awardFandA) {
        String fiscalYearStartDate = readFiscalYearStartDate();
        if(fiscalYearStartDate != null) {
            calculateDatesFromSystemParam(awardFandA, fiscalYearStartDate);
        } else {
            calculateDatesForCalendarYear(awardFandA);
        }
    }

    private void calculateDatesForCalendarYear(AwardFandaRate awardFandA) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(getFiscalYear(awardFandA), Calendar.JANUARY, 1);
        assignDates(awardFandA, calendar);
    }

    private void calculateDatesFromSystemParam(AwardFandaRate awardFandA, String fiscalYearStartDate) {
        Calendar calendar = GregorianCalendar.getInstance();
        String[] tokens = fiscalYearStartDate.split("/");
        calendar.set(getFiscalYear(awardFandA), Integer.valueOf(tokens[0]) - 1, Integer.valueOf(tokens[1]));
        assignDates(awardFandA, calendar);
    }

    /*
     * note: method has side effect of changing parameter calendar
     */
    private Date calculateFirstDayOfYear(Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_YEAR) != 1) {
            calendar.add(Calendar.YEAR, -1);
        }
        return new Date(calendar.getTimeInMillis());
    }
    
    /*
     * note: method has side effect of changing parameter calendar
     */
    private Date calculateLastDayOfYear(Calendar calendar) {
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DATE, -1);
        return new Date(calendar.getTimeInMillis());
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
        calculateDates(awardFandA);
        return awardFandA;
    }

    private Integer getFiscalYear(AwardFandaRate awardFandA) {
        return Integer.valueOf(awardFandA.getFiscalYear());
    }
    
    private String readFiscalYearStartDate() {
        return this.getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_CURRENT_FISCAL_YEAR);
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * Looks up and returns the BusinessObjectService.
     * @return the business object service. 
     */
    protected BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);        
        }
        return this.businessObjectService;
    }
    
}
