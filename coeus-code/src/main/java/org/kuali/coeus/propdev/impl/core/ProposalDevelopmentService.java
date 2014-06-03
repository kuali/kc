/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software

 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.person.CoPiInfoDO;
import org.kuali.coeus.propdev.impl.budget.CostShareInfoDO;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.List;
import java.util.Map;

public interface ProposalDevelopmentService {
    
    public void initializeUnitOrganizationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
    /**
     * This method initializes the siteNumber property on proposal sites that don't have it set yet.
     * @param proposalDevelopmentDocument
     */
    public void initializeProposalSiteNumbers(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
   /**
     * This method returns a Map of Units for which the user represented by the userId passed in has the role Proposal Aggregator
     * @param userId unique identifer representing the user whose units will be returned
     * @return A Map in the form of Unit Number, Unit Name representing the units for which the userId passed in has the Proposal Aggregator role.
     */
    public List<Unit> getDefaultModifyProposalUnitsForUser(String userId);

    public String populateProposalEditableFieldMetaDataForAjaxCall(String proposalNumber, String editableFieldDBColumn);
    
    public String populateBudgetEditableFieldMetaDataForAjaxCall(String proposalNumber, String documentNumber, String editableFieldDBColumn);
    
    public Object getProposalFieldValueFromDBColumnName(String proposalNumber, String dbColumnName) ;
    
    public Object getBudgetFieldValueFromDBColumnName(String documentNumber, String dbColumnName);
    
    public String getDataOverrideLookupDisplayReturnValue( String lookupClassName );

    
    /**
     * For the proposal, based on the hierarchy status and sponsor code is grants gov enabled 
     * @param proposal
     * @return
     */
    public boolean isGrantsGovEnabledForProposal(DevelopmentProposal proposal);
    
    /**
     * On a sponsor change, will grants gov be enabled after the change
     * @param proposalNumber
     * @param sponsorCode
     * @return
     */
    public boolean isGrantsGovEnabledOnSponsorChange(String proposalNumber, String sponsorCode);
    
    /**
     * 
     * Deletes the proposal and any budgets associated with this document and then cancels document
     * @param proposalDocument
     * @throws WorkflowException
     */
    public ProposalDevelopmentDocument deleteProposal(ProposalDevelopmentDocument proposalDocument) throws WorkflowException;
    
    public Budget getFinalBudget (DevelopmentProposal proposal);
    
    public List<CoPiInfoDO> getCoPiPiInfo (DevelopmentProposal proposal);
    
    public List<CostShareInfoDO> getCostShareInfo (Budget budget);

    public InstitutionalProposal getInstitutionalProposal(String devProposalNumber);
    
    public boolean canSaveProposalXml(ProposalDevelopmentDocument document);
    
    public static final String PROPOSAL_NARRATIVE_TYPE_GROUP = "proposalNarrativeTypeGroup";
    public static final String DELIVERY_INFO_DISPLAY_INDICATOR = "deliveryInfoDisplayIndicator";
    public static final String PROPOSAL_SUMMARY_INDICATOR = "enableProposalSummaryPanel";
    public static final String BUDGET_SUMMARY_INDICATOR = "enableBudgetSummaryPanel";
    public static final String KEY_PERSONNEL_INDICATOR = "enableKeyPersonnelPanel";
    public static final String SPECIAL_REVIEW_INDICATOR = "enableSpecialReviewPanel";
    public static final String SUMMARY_PRINT_FORMS_INDICATOR = "enableSummaryPrintPanel";
    public static final String CUSTOM_DATA_INFO_INDICATOR = "enableCustomDataInfoPanel";
    public static final String SUMMARY_QUESTIONS_INDICATOR = "enableSummaryQuestionsPanel";
    public static final String SUMMARY_ATTACHMENTS_INDICATOR = "enableSummaryAttachmentsPanel";
    public static final String SUMMARY_KEYWORDS_INDICATOR = "enableSummaryKeywordsPanel";
    public static final String PROPOSAL_SUMMARY_DISCLAIMER_INDICATOR = "propSummaryDisclaimerText";
    public static final String SUMMARY_DATA_VALIDATION_INDICATOR = "enableSummaryDataValidationPanel";
    public static final String SUMMARY_SPECIAL_REVIEW_LIST = "proposal.summary.validSpecialReviewCodes";
    public static final String PROPOSAL_NARRATIVE_TYPE_GROUP2 = "proposalNarrativeTypeGroup";

    public Map<String, Parameter> getProposalDevelopmentParameters();
       
    public static enum ProposalLockingRegion {
        GENERAL, BUDGET, ATTACHMENTS;
    }
    
    public static final String PROPOSAL_ATTACHMENT_TYPE_GROUP_CODE = "P";
    
    public ProposalDevelopmentDocument updateProposalDocument(ProposalDevelopmentDocument pdDocument, ProposalLockingRegion region) throws Exception;
    
    /**
     * 
     * Called to load all necessary elements in the proposal development document
     * @param document
     */
    public void loadDocument(ProposalDevelopmentDocument document);
    
    /**
     * This method produces a list of strings containg the methodToCall to be registered for each of the 
     * ProposalColumnsToAlter lookup buttons that can be rendered on the Proposal Data Override tab. The execute method in this class
     * puts this list into the form.  The Proposal Data Override tag file then calls registerEditableProperty on each when rendering the tab.
     * 
     * @param proposalNumber The proposal number for which we are generating the list for.
     * @return Possible editable properties that can be called from the page.
     */
    public List<String> constructColumnsToAlterLookupMTCs(String proposalNumber);
    
    public void sortS2sForms(DevelopmentProposal proposal);

    /**
     * Get the units that the user has tht can create proposal.
     * If the user has the create proposal in the global space, all of
     * the units will be returned.  If the user doesn't have create proposal
     * in any unit, an empty list is returned.
     * @param userId the user's username
     * @return the list of units the user has this permission in
     */
    public List<Unit> getUnitsForCreateProposal(String userId);

    public void initializeProposalSites(ProposalDevelopmentDocument developmentDocument);
}
