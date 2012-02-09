/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.CoPiInfoDO;
import org.kuali.kra.proposaldevelopment.bo.CostShareInfoDO;
import org.kuali.rice.kew.api.exception.WorkflowException;

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
    
    public Object getProposalFieldValueFromDBColumnName(String proposalNumber, String dbColumnName) ;
    
    public Award getProposalCurrentAwardVersion(ProposalDevelopmentDocument proposal);
    
    public InstitutionalProposal getProposalContinuedFromVersion(ProposalDevelopmentDocument proposal);
    
    public String getDataOverrideLookupDisplayReturnValue( String lookupClassName );
    
    public String getDataOverrideLookupDisplayDisplayValue( String lookupClassName, String value, String displayAttributeName );
    
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
    public void deleteProposal(ProposalDevelopmentDocument proposalDocument) throws WorkflowException;
    
    public Budget getFinalBudget (DevelopmentProposal proposal);
    
    public List<CoPiInfoDO> getCoPiPiInfo (DevelopmentProposal proposal);
    
    public List<CostShareInfoDO> getCostShareInfo (Budget budget);

    public InstitutionalProposal getInstitutionalProposal(String devProposalNumber);
    
}
