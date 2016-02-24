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
package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.List;

public interface ProposalDevelopmentService {

    void initializeUnitOrganizationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
    /**
     * This method initializes the siteNumber property on proposal sites that don't have it set yet.
     */
    void initializeProposalSiteNumbers(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
   /**
     * This method returns a Map of Units for which the user represented by the userId passed in has the role Proposal Aggregator
     * @param userId unique identifer representing the user whose units will be returned
     * @return A Map in the form of Unit Number, Unit Name representing the units for which the userId passed in has the Proposal Aggregator role.
     */
    List<Unit> getDefaultModifyProposalUnitsForUser(String userId);
    
    Object getBudgetFieldValueFromDBColumnName(String documentNumber, String dbColumnName);

    /**
     * For the proposal, based on the hierarchy status and sponsor code is grants gov enabled
     */
    boolean isGrantsGovEnabledForProposal(DevelopmentProposal proposal);
    /**
     *
     * Deletes the proposal and any budgets associated with this document and then cancels document
     */
    ProposalDevelopmentDocument deleteProposal(ProposalDevelopmentDocument proposalDocument) throws WorkflowException;
    
    Budget getFinalBudget (DevelopmentProposal proposal);
    
    InstitutionalProposal getInstitutionalProposal(String devProposalNumber);

    String BUDGET_SUMMARY_INDICATOR = "enableBudgetSummaryPanel";
    String SUMMARY_QUESTIONS_INDICATOR = "enableSummaryQuestionsPanel";
    String SUMMARY_ATTACHMENTS_INDICATOR = "enableSummaryAttachmentsPanel";
    String SUMMARY_KEYWORDS_INDICATOR = "enableSummaryKeywordsPanel";
    String ENABLE_IP_GENERATION_PROMPT_DIALOG = "enableIPGenerationPromptDialog";

    /**
     * Get the units that the user has tht can create proposal.
     * If the user has the create proposal in the global space, all of
     * the units will be returned.  If the user doesn't have create proposal
     * in any unit, an empty list is returned.
     * @param userId the user's username
     * @return the list of units the user has this permission in
     */
    List<Unit> getUnitsForCreateProposal(String userId);
    
    boolean autogenerateInstitutionalProposal();

    String getIPGenerateOption(DevelopmentProposal developmentProposal);

    boolean isProposalReniewedOrChangeCorrected(DevelopmentProposal developmentProposal);
}
