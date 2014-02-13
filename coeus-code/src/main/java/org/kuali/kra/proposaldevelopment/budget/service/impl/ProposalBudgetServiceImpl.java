/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.budget.service.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.AddBudgetVersionEvent;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService;
import org.kuali.kra.proposaldevelopment.budget.service.ProposalBudgetService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.ArrayList;

/**
 * This class process requests for ProposalBudget
 */
public class ProposalBudgetServiceImpl implements ProposalBudgetService {

    private DocumentService documentService;
    private ParameterService parameterService;
    private BudgetService<DevelopmentProposal> budgetService;
    private BudgetCalculationService budgetCalculationService;
    private BudgetSubAwardService budgetSubAwardService;
    

    public BudgetDocument<DevelopmentProposal> getNewBudgetVersion(BudgetParentDocument<DevelopmentProposal> parentDocument,
            String documentDescription) throws WorkflowException {
        BudgetDocument<DevelopmentProposal> budgetDocument;
        Integer budgetVersionNumber = parentDocument.getNextBudgetVersionNumber();
        budgetDocument = (BudgetDocument) documentService.getNewDocument(BudgetDocument.class);
        
        budgetDocument.setParentDocument(parentDocument);
        budgetDocument.setParentDocumentKey(parentDocument.getDocumentNumber());
        budgetDocument.setParentDocumentTypeCode(parentDocument.getDocumentTypeCode());
        budgetDocument.getDocumentHeader().setDocumentDescription(documentDescription);
        
        Budget budget = budgetDocument.getBudget();
        budget.setBudgetVersionNumber(budgetVersionNumber);
        budget.setBudgetDocument(budgetDocument);
        
        BudgetParent budgetParent = parentDocument.getBudgetParent();
        budget.setStartDate(budgetParent.getRequestedStartDateInitial());
        budget.setEndDate(budgetParent.getRequestedEndDateInitial());
        budget.setOhRateTypeCode(this.parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        budget.setOhRateClassCode(this.parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
        budget.setUrRateClassCode(this.parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        budget.setModularBudgetFlag(this.parameterService.getParameterValueAsBoolean(BudgetDocument.class, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
        budget.setBudgetStatus(this.parameterService.getParameterValueAsString(BudgetDocument.class, budgetParent.getDefaultBudgetStatusParameter()));
        boolean success = new BudgetVersionRule().processAddBudgetVersion(new AddBudgetVersionEvent("document.parentDocument.budgetDocumentVersion",budgetDocument.getParentDocument(),budget));
        if(!success)
            return null;

        //Rates-Refresh Scenario-1
        budget.setRateClassTypesReloaded(true);

        budgetDocument = saveBudgetDocument(budgetDocument);
        KcServiceLocator.getService(DataObjectService.class).flush(BudgetDocumentVersion.class);
        parentDocument.refreshBudgetDocumentVersions();
        return budgetDocument;
    }
    public boolean isRateOverridden(BudgetPeriod budgetPeriod){
        return false;
    }

    /**
     * This method...
     * @param budgetDocument
     * @param isProposalBudget
     * @param budget
     * @param budgetParent
     * @throws WorkflowException
     */
    protected BudgetDocument<DevelopmentProposal> saveBudgetDocument(BudgetDocument<DevelopmentProposal> budgetDocument) throws WorkflowException {
        budgetDocument = (BudgetDocument<DevelopmentProposal>) documentService.saveDocument(budgetDocument);
        return (BudgetDocument<DevelopmentProposal>) documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
    }

    /**
     * Gets the documentService attribute. 
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Gets the parameterService attribute. 
     * @return Returns the parameterService.
     */
    public ParameterService getParameterService() {
        return parameterService;
    }

    /**
     * Sets the parameterService attribute value.
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    public BudgetDocument<DevelopmentProposal> copyBudgetVersion(BudgetDocument<DevelopmentProposal> budgetDocument)
            throws WorkflowException {
        return copyBudgetVersion(budgetDocument, false);
    }
    public BudgetDocument<DevelopmentProposal> copyBudgetVersion(BudgetDocument<DevelopmentProposal> budgetDocument, boolean onlyOnePeriod)
            throws WorkflowException {
        return getBudgetService().copyBudgetVersion(budgetDocument, onlyOnePeriod);
    }
    /**
     * Sets the budgetService attribute value.
     * @param budgetService The budgetService to set.
     */
    public void setBudgetService(BudgetService<DevelopmentProposal> budgetService) {
        this.budgetService = budgetService;
    }
    /**
     * Gets the budgetService attribute. 
     * @return Returns the budgetService.
     */
    public BudgetService<DevelopmentProposal> getBudgetService() {
        return budgetService;
    }

    public void recalculateBudget(Budget budget) {
        budgetCalculationService.calculateBudget(budget);
    }
    /**
     * Sets the budgetCalculationService attribute value.
     * @param budgetCalculationService The budgetCalculationService to set.
     */
    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    /**
     * Gets the budgetCalculationService attribute. 
     * @return Returns the budgetCalculationService.
     */
    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }
    public void calculateBudgetOnSave(Budget budget) {
        for (BudgetSubAwards subAward : budget.getBudgetSubAwards()) {
            getBudgetSubAwardService().generateSubAwardLineItems(subAward, budget);
        }
        budgetCalculationService.calculateBudget(budget);
    }

    /**
     * Do nothing for proposal budget
     * @see org.kuali.kra.budget.core.BudgetCommonService#removeBudgetSummaryPeriodCalcAmounts(org.kuali.kra.budget.parameters.BudgetPeriod)
     */
    public void removeBudgetSummaryPeriodCalcAmounts(BudgetPeriod budgetPeriod) {
    }
    public void populateSummaryCalcAmounts(Budget budget, BudgetPeriod budgetPeriod) {
        // DO NOTHING
    }
    
    /**
     * 
     * @see org.kuali.kra.budget.core.BudgetCommonService#validateAddingNewBudget(org.kuali.kra.budget.document.BudgetParentDocument)
     */
    public boolean validateAddingNewBudget(BudgetParentDocument<DevelopmentProposal> parentDocument) {
        return true;
    }
    public void recalculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod) {
        budgetCalculationService.calculateBudget(budget);
    }
    protected BudgetSubAwardService getBudgetSubAwardService() {
        return budgetSubAwardService;
    }
    public void setBudgetSubAwardService(BudgetSubAwardService budgetSubAwardService) {
        this.budgetSubAwardService = budgetSubAwardService;
    }

}
