/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.List;

import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.ProposalExemptNumber;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

public interface ProposalDevelopmentService {
    
    public void initializeUnitOrganzationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
    /**
     * This method returns a Map of Units for which the user represented by the userId passed in has the role Proposal Aggregator
     * @param userId unique identifer representing the user whose units will be returned
     * @return A Map in the form of Unit Number, Unit Name representing the units for which the userId passed in has the Proposal Aggregator role.
     */
    public List<Unit> getDefaultModifyProposalUnitsForUser(String userId);

    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos);
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService();
    
    /**
     * 
     * This method to invoke audit rule check for budget if status is final only
     * This is called by PD's turnon validation
     * @param proposalDevelopmentDocument
     * @return
     * TODO : not sure this is the proper place for this method.  Maybe implemented in BudgetService or ResearchDocumentBaseService ?
     */
    public boolean validateBudgetAuditRule(ProposalDevelopmentDocument proposalDevelopmentDocument) throws Exception;

    /**
     * 
     * This method to check the audit rule when try to save 'budgetversion' and with 'complete' status.
     * @param proposalDevelopmentDocument
     * @return
     * @throws Exception
     * TODO : not sure this is the proper place for this method.  Maybe implemented in BudgetService or ResearchDocumentBaseService ?
     */
    public boolean validateBudgetAuditRuleBeforeSaveBudgetVersion(ProposalDevelopmentDocument proposalDevelopmentDocument) throws Exception;

    /**
     * 
     * This method is to get exemption type key value pairs
     * @return
     */
    public List<KeyLabelPair> getExemptionTypeKeyValues();

    /**
     * 
     * This method is to initialize document exemptnumbers in form before navigate to special review 
     * @param proposalDevelopmentForm
     */
    public void populateExemptNumbersToForm(ProposalDevelopmentForm proposalDevelopmentForm);

    /**
     * 
     * This method populate the selected exempt numbers to proposalexemptnumbers before save
     * @param proposalDevelopmentForm
     */
    public void populateProposalExempNumbers(ProposalDevelopmentForm proposalDevelopmentForm);

    public String populateProposalEditableFieldMetaDataForAjaxCall(String proposalNumber, String editableFieldDBColumn);
    
    public Object getProposalFieldValueFromDBColumnName(String proposalNumber, String dbColumnName) ;
}
