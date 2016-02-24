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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public interface InstitutionalProposalPersonCreditSplitRule extends BusinessRule {

    String PROPOSAL_CREDIT_SPLIT_LIST_ERROR_KEY = "document.institutionalProposalList[0].projectPersons.institutionalProposalPersonCreditSplits";
    String PROPOSAL_PERSON_CREDIT_SPLIT_ERROR_MSG_KEY = "error.award.person.credit.split.error";
    
    /**
     * Check if the {@link InvestigatorCreditType} requires totals add to 100
     * @param event
     * @return
     */
    boolean checkInstitutionalProposalPersonCreditSplitTotals(InstitutionalProposalPersonCreditSplitRuleEvent event);
}
