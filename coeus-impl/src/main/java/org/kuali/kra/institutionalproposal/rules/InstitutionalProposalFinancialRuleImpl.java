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
package org.kuali.kra.institutionalproposal.rules;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

import java.sql.Date;


public class InstitutionalProposalFinancialRuleImpl extends KcTransactionalDocumentRuleBase implements InstitutionalProposalFinancialRule {

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
       if(institutionalProposal.getRequestedEndDateTotal() != null && institutionalProposal.getRequestedEndDateInitial() != null) {
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
        if(institutionalProposal.getTotalDirectCostInitial() != null && institutionalProposal.getTotalDirectCostInitial().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalDirectCostInitial", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    DIRECT_COST_INITIAL_PERIOD);
            valid = false;
        }
        if(institutionalProposal.getTotalDirectCostTotal() != null && institutionalProposal.getTotalDirectCostTotal().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalDirectCostTotal", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    DIRECT_COST_TOTAL_PERIOD);
            valid = false;
        }
        if(institutionalProposal.getTotalIndirectCostInitial() != null && institutionalProposal.getTotalIndirectCostInitial().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalIndirectCostInitial", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    INDIRECT_COST_INITIAL_PERIOD);
            valid = false;
        }
        if(institutionalProposal.getTotalIndirectCostTotal() != null && institutionalProposal.getTotalIndirectCostTotal().isNegative()) {
            this.reportError("document.institutionalProposalList[0].totalIndirectCostTotal", KeyConstants.ERROR_FINANCIAL_COSTS, 
                    INDIRECT_COST_TOTAL_PERIOD);
            valid = false;
        }
        return valid;
    }

}
