/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.impl.core.AbstractBudgetService;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.budget.core.ProposalAddBudgetVersionEvent;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardAttachment;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardFiles;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.budget.subaward.PropDevBudgetSubAwardService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.PersistenceOption;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Qualifier("budgetPersonService")
    private BudgetPersonService budgetPersonService;

    @Autowired
    @Qualifier("budgetModularService")
    private BudgetModularService budgetModularService;
    
    @Override
    public ProposalDevelopmentBudgetExt getNewBudgetVersion(BudgetParentDocument<DevelopmentProposal> parentDocument,String budgetName, Map<String, Object> options){
        Integer budgetVersionNumber = parentDocument.getNextBudgetVersionNumber();
        DevelopmentProposal budgetParent = parentDocument.getBudgetParent();
        ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();

        budget.setDevelopmentProposal(budgetParent);
        
        budget.setBudgetVersionNumber(budgetVersionNumber);
        
        budget.setName(budgetName);
        budget.setStartDate(budgetParent.getRequestedStartDateInitial());
        budget.setEndDate(budgetParent.getRequestedEndDateInitial());
        budget.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        budget.setCreateUser(getGlobalVariableService().getUserSession().getLoggedInUserPrincipalName());
        budget.setOhRateTypeCode(this.parameterService.getParameterValueAsString(Budget.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        budget.setOhRateClassCode(this.parameterService.getParameterValueAsString(Budget.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
        budget.setUrRateClassCode(this.parameterService.getParameterValueAsString(Budget.class, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        budget.setBudgetStatus(this.parameterService.getParameterValueAsString(Budget.class, budgetParent.getDefaultBudgetStatusParameter()));
        if (options != null && options.containsKey("modularBudgetFlag")) {
        	budget.setModularBudgetFlag((Boolean) options.get("modularBudgetFlag"));
        } else {
        	budget.setModularBudgetFlag(this.parameterService.getParameterValueAsBoolean(Budget.class, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
        }
		boolean success = isBudgetVersionNameValid(budgetParent, budgetName);
        if(!success)
            return null;

        //load budget rates
        budget.setRateClassTypesReloaded(true);
        budget.getRateClassTypes();
        getBudgetPersonService().synchBudgetPersonsToProposal(budget);
        if (budget.getStartDate() != null) {
            budget.setBudgetPeriods(getBudgetSummaryService().generateBudgetPeriods(budget));
        }

        budget = saveBudget(budget);

        if (budget.getModularBudgetFlag()) {
            for (BudgetPeriod period : budget.getBudgetPeriods())  {
                getBudgetModularService().generateModularPeriod(period);
            }
            budget = saveBudget(budget);
        }

        List<ProposalDevelopmentBudgetExt> budgets = budgetParent.getBudgets();
        if (budgets == null) {
            budgets = new ArrayList<>();
            budgetParent.setBudgets(budgets);
        }

        budgets.add(budget);
        return budget;
    }
    
    @Override
    public boolean isBudgetVersionNameValid(BudgetParent parent, String name) {
    	return getKcBusinessRulesEngine().applyRules(new ProposalAddBudgetVersionEvent("addBudgetDto", parent, name));
    }

    @Override
    public ProposalDevelopmentBudgetExt getFinalBudgetVersion(ProposalDevelopmentDocument parentDocument) throws WorkflowException {
        ProposalDevelopmentBudgetExt finalBudget = parentDocument.getDevelopmentProposal().getFinalBudget();
        if (finalBudget == null) {
	        final List<ProposalDevelopmentBudgetExt> budgetVersions = parentDocument.getDevelopmentProposal().getBudgets();
	        if (budgetVersions != null && !budgetVersions.isEmpty()) {
	            QueryList<ProposalDevelopmentBudgetExt> budgetVersionsQuery = new QueryList<ProposalDevelopmentBudgetExt>();
	            budgetVersionsQuery.sort("budgetVersionNumber", false);
	            finalBudget = budgetVersionsQuery.get(0);
	        }
        }
        return finalBudget;
    }  

    public boolean isRateOverridden(BudgetPeriod budgetPeriod){
        return false;
    }

    protected ProposalDevelopmentBudgetExt saveBudget(ProposalDevelopmentBudgetExt budget) {
    	return getDataObjectService().save(budget, PersistenceOption.FLUSH);
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
            getPropDevBudgetSubAwardService().generateSubAwardLineItems(subAward, (ProposalDevelopmentBudgetExt) budget);
        }
        budgetCalculationService.calculateBudget(budget);
    }
    
    @Override
    public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod) {
    	return copyBudgetVersion((ProposalDevelopmentBudgetExt) budget, onlyOnePeriod, null);
    }

    @Override
    public ProposalDevelopmentBudgetExt copyBudgetVersion(ProposalDevelopmentBudgetExt budget, boolean onlyOnePeriod, DevelopmentProposal developmentProposal){
    	ProposalDevelopmentBudgetExt newBudget = copyBudgetVersionInternal((ProposalDevelopmentBudgetExt) budget, developmentProposal);
        if (onlyOnePeriod) {
            //Copy full first version, then include empty periods for remainder
            List<BudgetPeriod> oldBudgetPeriods = newBudget.getBudgetPeriods(); 
            for ( int i = 1 ; i < oldBudgetPeriods.size(); i++ ) {
                BudgetPeriod period = oldBudgetPeriods.get(i);
                period.getBudgetLineItems().clear();
                period.setCostSharingAmount(ScaleTwoDecimal.ZERO);
                period.setExpenseTotal(ScaleTwoDecimal.ZERO);
                period.setTotalCost(ScaleTwoDecimal.ZERO);
                period.setTotalCostLimit(ScaleTwoDecimal.ZERO);
                period.setTotalDirectCost(ScaleTwoDecimal.ZERO);
                period.setDirectCostLimit(ScaleTwoDecimal.ZERO);
                period.setTotalIndirectCost(ScaleTwoDecimal.ZERO);
                period.setUnderrecoveryAmount(ScaleTwoDecimal.ZERO);
            }            

            if (newBudget.getBudgetSubAwards() != null && newBudget.getBudgetSubAwards().size() > 0) {
                for (BudgetSubAwards budgetSubAwards : newBudget.getBudgetSubAwards()) {
                    List<BudgetSubAwardPeriodDetail> budetSubawardPeriodDetail = budgetSubAwards.getBudgetSubAwardPeriodDetails();
                    for ( int i = 1 ; i < budetSubawardPeriodDetail.size(); i++ ) {
                        BudgetSubAwardPeriodDetail period = budetSubawardPeriodDetail.get(i);
                        period.setAmountsModified(true);
                        period.setCostShare(ScaleTwoDecimal.ZERO);
                        period.setDirectCost(ScaleTwoDecimal.ZERO);
                        period.setIndirectCost(ScaleTwoDecimal.ZERO);
                        period.setTotalCost(ScaleTwoDecimal.ZERO);
                    }
                }
            }
        }
        
        newBudget.setBudgetVersionNumber(newBudget.getBudgetParent().getNextBudgetVersionNumber());

        copyLineItemToPersonnelDetails(newBudget);

        return newBudget;
    } 
    
    protected ProposalDevelopmentBudgetExt copyBudgetVersionInternal(ProposalDevelopmentBudgetExt budget, DevelopmentProposal developmentProposal) {
        for (BudgetSubAwards subAwards : budget.getBudgetSubAwards()) {
        	//pre-fetch all lob objects from the subawards as JPA/Eclipselink doesn't do this for lazy loaded lobs
        	//during the deep-copy operation below
        	subAwards.getSubAwardXmlFileData();
        	for (BudgetSubAwardAttachment origAttachment : subAwards.getBudgetSubAwardAttachments()) {
        		origAttachment.getData();
        	}
        	for (BudgetSubAwardFiles files : subAwards.getBudgetSubAwardFiles()) {
        		files.getSubAwardXfdFileData();
        		files.getSubAwardXmlFileData();
        	}
        }
        DevelopmentProposal parent = budget.getDevelopmentProposal();
        budget.setDevelopmentProposal(null);
		ProposalDevelopmentBudgetExt doServiceCopy = 
				(ProposalDevelopmentBudgetExt) getDataObjectService().copyInstance(budget, CopyOption.RESET_OBJECT_ID, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER);
		budget.setDevelopmentProposal(parent);
		if (developmentProposal != null) {
			doServiceCopy.setDevelopmentProposal(developmentProposal);
		} else {
			doServiceCopy.setDevelopmentProposal(parent);
		}
		return doServiceCopy;
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

	public BudgetPersonService getBudgetPersonService() {
		return budgetPersonService;
	}

	public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
		this.budgetPersonService = budgetPersonService;
	}

    public BudgetModularService getBudgetModularService() {
        return budgetModularService;
    }

    public void setBudgetModularService(BudgetModularService budgetModularService) {
        this.budgetModularService = budgetModularService;
    }

    public boolean isBudgetMarkedForSubmission(Budget finalBudget, Budget currentBudget) {
		boolean budgetMarkedForSubmission = false;
        if (finalBudget != null) {
        	if(finalBudget.getBudgetId().equals(currentBudget.getBudgetId())) {
        		budgetMarkedForSubmission = true;
        	}
        }
		return budgetMarkedForSubmission;
    }
    
}
