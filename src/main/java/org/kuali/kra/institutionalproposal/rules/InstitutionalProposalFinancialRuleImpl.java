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
package org.kuali.kra.institutionalproposal.rules;

import java.sql.Date;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class...
 */
public class InstitutionalProposalFinancialRuleImpl extends ResearchDocumentRuleBase implements InstitutionalProposalFinancialRule {

    private static final String REQUESTED_INITIAL_START_DATE = "Requested Initial Start Date";
    private static final String REQUESTED_INITIAL_END_DATE = "Requested Initial End Date";
    private static final String REQUESTED_TOTAL_START_DATE = "Requested Total Start Date";
    private static final String REQUESTED_TOTAL_END_DATE = "Requested Total End Date";
    
    private static final String DIRECT_COST_INITIAL_PERIOD = "Direct Cost Initial Period";
    private static final String DIRECT_COST_TOTAL_PERIOD = "Direct Cost Total Period";
    private static final String INDIRECT_COST_INITIAL_PERIOD = "Indirect Cost Initial Period";
    private static final String INDIRECT_COST_TOTAL_PERIOD = "Indirect Cost Total Period";

    
    public boolean processInstitutionalProposalFinancialRules(
            InstitutionalProposalFinancialRuleEvent institutionalProposalFinancialRuleEvent) {
        boolean valid = true;
       InstitutionalProposal institutionalProposal = institutionalProposalFinancialRuleEvent.getInstitutionalProposalForValidation();
       if(institutionalProposal.getRequestedStartDateInitial() != null && institutionalProposal.getRequestedEndDateInitial() != null) {
           if (!(validateFirstDatePriorToSecondDate(institutionalProposal.getRequestedStartDateInitial(),
                   institutionalProposal.getRequestedEndDateInitial()))) {
               this.reportError("document.institutionalProposalList[0].requestedStartDateInitial", KeyConstants.ERROR_FINANCIAL_DATES, 
                       REQUESTED_INITIAL_START_DATE, REQUESTED_INITIAL_END_DATE);
               valid = false;
           }
       }
       if(institutionalProposal.getRequestedStartDateTotal() != null && institutionalProposal.getRequestedEndDateTotal() != null) {
           if (!(validateFirstDatePriorToSecondDate(institutionalProposal.getRequestedStartDateTotal(),
                   institutionalProposal.getRequestedEndDateTotal())))  {
               this.reportError("document.institutionalProposalList[0].requestedStartDateTotal", KeyConstants.ERROR_FINANCIAL_DATES, 
                       REQUESTED_TOTAL_START_DATE, REQUESTED_TOTAL_END_DATE);
               valid = false;
           }
       }
       if(institutionalProposal.getRequestedStartDateTotal() != null && institutionalProposal.getRequestedStartDateInitial() != null) {
           if (!(validateFirstDatePriorToSecondDate(institutionalProposal.getRequestedStartDateTotal(),
                   institutionalProposal.getRequestedStartDateInitial()))) {
               this.reportError("document.institutionalProposalList[0].requestedStartDateTotal", KeyConstants.ERROR_FINANCIAL_DATES, 
                       REQUESTED_TOTAL_START_DATE, REQUESTED_INITIAL_START_DATE);
               valid = false;
           }
       }
       if(institutionalProposal.getRequestedStartDateTotal() != null && institutionalProposal.getRequestedStartDateInitial() != null) {
           if (!(validateFirstDatePriorToSecondDate(institutionalProposal.getRequestedEndDateInitial(),
                   institutionalProposal.getRequestedEndDateTotal()))) {
               this.reportError("document.institutionalProposalList[0].requestedEndDateInitial", KeyConstants.ERROR_FINANCIAL_DATES, 
                       REQUESTED_INITIAL_END_DATE, REQUESTED_TOTAL_END_DATE);
               valid = false;
           }
       }
       valid = testFinancialCostFields(institutionalProposal);
       return valid;
    }
    
    /**
     * This method tests if first date falls after second date.
     * @param Date firstDate, Date secondDate
     * @return
     */
    private boolean validateFirstDatePriorToSecondDate(Date firstDate, Date secondDate) {
        return firstDate.before(secondDate) || firstDate.equals(secondDate);
    }
    
    /**
     * This method test currency fields of Financial Tag.
     * @param institutionalProposal
     * @return
     */
    private boolean testFinancialCostFields(InstitutionalProposal institutionalProposal) {
        boolean valid = true;
        if(institutionalProposal.getTotalDirectCostInitial().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalDirectCostInitial", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    DIRECT_COST_INITIAL_PERIOD);
            valid = false;
        }
        if(institutionalProposal.getTotalDirectCostTotal().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalDirectCostTotal", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    DIRECT_COST_TOTAL_PERIOD);
            valid = false;
        }
        if(institutionalProposal.getTotalIndirectCostInitial().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalIndirectCostInitial", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    INDIRECT_COST_INITIAL_PERIOD);
            valid = false;
        }
        if(institutionalProposal.getTotalIndirectCostTotal().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalIndirectCostTotal", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    INDIRECT_COST_TOTAL_PERIOD);
            valid = false;
        }
        return valid;
    }

}
