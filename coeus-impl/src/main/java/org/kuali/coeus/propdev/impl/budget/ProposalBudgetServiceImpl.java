/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionRule;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.common.budget.impl.core.AbstractBudgetService;
import org.kuali.coeus.common.budget.impl.version.BudgetVersionRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.budget.subaward.PropDevBudgetSubAwardService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * This class process requests for ProposalBudget
 */
@Component("proposalBudgetService")
public class ProposalBudgetServiceImpl extends AbstractBudgetService<DevelopmentProposal> implements ProposalBudgetService {

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    
    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
    @Qualifier("propDevBudgetSubAwardService")
    private PropDevBudgetSubAwardService propDevBudgetSubAwardService;
    
    @Autowired
    @Qualifier("proposalBudgetVersionRule")
    private AddBudgetVersionRule addBudgetVersionRule;
    
    @Override
    public Budget getNewBudgetVersion(BudgetParentDocument<DevelopmentProposal> parentDocument,String budgetName, Map<String, Object> options){
        Integer budgetVersionNumber = parentDocument.getNextBudgetVersionNumber();
        DevelopmentProposal budgetParent = parentDocument.getBudgetParent();
        ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
        budget.setDevelopmentProposal(budgetParent);
        
        budget.setBudgetVersionNumber(budgetVersionNumber);
        
        budget.setName(budgetName);
        budget.setStartDate(budgetParent.getRequestedStartDateInitial());
        budget.setEndDate(budgetParent.getRequestedEndDateInitial());
        budget.setOhRateTypeCode(this.parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        budget.setOhRateClassCode(this.parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
        budget.setUrRateClassCode(this.parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        budget.setModularBudgetFlag(this.parameterService.getParameterValueAsBoolean(BudgetDocument.class, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
        budget.setBudgetStatus(this.parameterService.getParameterValueAsString(BudgetDocument.class, budgetParent.getDefaultBudgetStatusParameter()));
        budget.setModularBudgetFlag((Boolean) options.get("modularBudgetFlag"));
        boolean success;
		try {
			success = getAddBudgetVersionRule().processAddBudgetVersion(new AddBudgetVersionEvent("addBudgetDto", parentDocument.getBudgetParent(), budgetName));
		} catch (WorkflowException e) {
			throw new RuntimeException(e);
		}
        if(!success)
            return null;

        //Rates-Refresh Scenario-1
        budget.setRateClassTypesReloaded(true);
        return saveBudget(budget);
    }

    @Override
    public BudgetDocument<DevelopmentProposal> getFinalBudgetVersion(BudgetParentDocument<DevelopmentProposal> parentDocument) throws WorkflowException {
        BudgetDocument<DevelopmentProposal> budgetDocument = null;

        if (parentDocument.getFinalBudgetVersion() != null) {
            budgetDocument = (BudgetDocument<DevelopmentProposal>) documentService.getByDocumentHeaderId(parentDocument.getFinalBudgetVersion().getDocumentNumber());
        } else {
            final List<BudgetDocumentVersion> budgetVersions = parentDocument.getBudgetDocumentVersions();

            QueryList<BudgetVersionOverview> budgetVersionOverviews = new QueryList<BudgetVersionOverview>();
            for (BudgetDocumentVersion budgetDocumentVersion : budgetVersions) {
                budgetVersionOverviews.add(budgetDocumentVersion.getBudgetVersionOverview());
            }
            if (!budgetVersionOverviews.isEmpty()) {
                budgetVersionOverviews.sort("budgetVersionNumber", false);
                BudgetVersionOverview budgetVersionOverview = budgetVersionOverviews.get(0);

                budgetDocument = (BudgetDocument<DevelopmentProposal>) documentService.getByDocumentHeaderId(budgetVersionOverview.getDocumentNumber());
            }
        }
        return budgetDocument;
    }

    public boolean isRateOverridden(BudgetPeriod budgetPeriod){
        return false;
    }

    protected ProposalDevelopmentBudgetExt saveBudget(ProposalDevelopmentBudgetExt budget) {
    	return getDataObjectService().save(budget);
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
    public Budget copyBudgetVersion(Budget budget){
        return copyBudgetVersion(budget, false);
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
            getPropDevBudgetSubAwardService().generateSubAwardLineItems(subAward, budget);
        }
        budgetCalculationService.calculateBudget(budget);
    }

    /**
     * Do nothing for proposal budget
     * @see org.kuali.coeus.common.budget.framework.core.BudgetCommonService#removeBudgetSummaryPeriodCalcAmounts(org.kuali.coeus.common.budget.framework.period.BudgetPeriod)
     */
    public void removeBudgetSummaryPeriodCalcAmounts(BudgetPeriod budgetPeriod) {
    }
    public void populateSummaryCalcAmounts(Budget budget, BudgetPeriod budgetPeriod) {
        // DO NOTHING
    }
    
    @Override
    public boolean validateAddingNewBudget(BudgetParentDocument<DevelopmentProposal> parentDocument) {
        return true;
    }
    public void recalculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod) {
        budgetCalculationService.calculateBudget(budget);
    }
    protected PropDevBudgetSubAwardService getPropDevBudgetSubAwardService() {
        return propDevBudgetSubAwardService;
    }
    public void setPropDevBudgetSubAwardService(PropDevBudgetSubAwardService propDevBudgetSubAwardService) {
        this.propDevBudgetSubAwardService = propDevBudgetSubAwardService;
    }

	public AddBudgetVersionRule getAddBudgetVersionRule() {
		return addBudgetVersionRule;
	}

	public void setAddBudgetVersionRule(AddBudgetVersionRule addBudgetVersionRule) {
		this.addBudgetVersionRule = addBudgetVersionRule;
	}

}
