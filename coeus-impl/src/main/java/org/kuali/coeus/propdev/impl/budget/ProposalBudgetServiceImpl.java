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
package org.kuali.coeus.propdev.impl.budget;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.impl.core.AbstractBudgetService;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.bo.DocumentNextvalue;
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
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.PersistenceOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class process requests for ProposalBudget
 */
@Component("proposalBudgetService")
public class ProposalBudgetServiceImpl extends AbstractBudgetService<DevelopmentProposal> implements ProposalBudgetService {

    public static final String MODULAR_BUDGET_FLAG = "modularBudgetFlag";
    public static final String ADD_BUDGET_DTO = "addBudgetDto";
    public static final String COST_ELEMENT_BO = "costElementBO";
    public static final String BUDGET_CATEGORY = "budgetCategory";

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

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("kcBusinessRulesEngine")
    private KcBusinessRulesEngine kcBusinessRulesEngine;

    @Autowired
    @Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;

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
        budget.setOhRateTypeCode(getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        budget.setOhRateClassCode(getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
        budget.setUrRateClassCode(getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        budget.setBudgetStatus(getParameterService().getParameterValueAsString(Budget.class, budgetParent.getDefaultBudgetStatusParameter()));
        if (options != null && options.containsKey(MODULAR_BUDGET_FLAG)) {
        	budget.setModularBudgetFlag((Boolean) options.get(MODULAR_BUDGET_FLAG));
        } else {
        	budget.setModularBudgetFlag(getParameterService().getParameterValueAsBoolean(Budget.class, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
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
    	return getKcBusinessRulesEngine().applyRules(new ProposalAddBudgetVersionEvent(ADD_BUDGET_DTO, parent, name));
    }

    @Override
    public ProposalDevelopmentBudgetExt getFinalBudgetVersion(ProposalDevelopmentDocument parentDocument) {
        ProposalDevelopmentBudgetExt finalBudget = parentDocument.getDevelopmentProposal().getFinalBudget();
        if (finalBudget == null) {
        	return parentDocument.getDevelopmentProposal().getBudgets().stream()
        		.sorted((b1, b2) -> ObjectUtils.compare(b1.getBudgetVersionNumber(), b2.getBudgetVersionNumber()) * -1)
        		.findFirst().orElse(null);
        } else {
        	return finalBudget;
        }
    }  

    public boolean isRateOverridden(BudgetPeriod budgetPeriod){
        return false;
    }

    protected ProposalDevelopmentBudgetExt saveBudget(ProposalDevelopmentBudgetExt budget) {
    	return getDataObjectService().save(budget, PersistenceOption.FLUSH);
    }

    public Budget copyBudgetVersion(Budget budget){
        return copyBudgetVersion(budget, false);
    }

    public void recalculateBudget(Budget budget) {
        budgetCalculationService.calculateBudget(budget);
    }

    public void calculateBudgetOnSave(Budget budget) {
        for (BudgetSubAwards subAward : budget.getBudgetSubAwards()) {
            getPropDevBudgetSubAwardService().generateSubAwardLineItems(subAward, (ProposalDevelopmentBudgetExt) budget);
        }
        recalculateBudget(budget);
    }
    
    @Override
    public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod) {
    	return copyBudgetVersion((ProposalDevelopmentBudgetExt) budget, onlyOnePeriod, null);
    }

    @Override
    public ProposalDevelopmentBudgetExt copyBudgetVersion(ProposalDevelopmentBudgetExt budget, boolean onlyOnePeriod, DevelopmentProposal developmentProposal){
    	ProposalDevelopmentBudgetExt newBudget = copyBudgetVersionInternal(budget, developmentProposal);

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

        //budget is not saved yet so this effectively nulls out any foreign key fields from sequences
        //some fields are not managed by JPA so manually handling the relationships
        syncBudgetReferencesForCopy(budget);

        return newBudget;
    }

    @Override
    public void syncBudgetReferencesForCopy(ProposalDevelopmentBudgetExt budget) {

        Stream.concat(budget.getBudgetRates() != null ? budget.getBudgetRates().stream() : Stream.empty(),
                budget.getBudgetLaRates() != null ? budget.getBudgetLaRates().stream() : Stream.empty()).forEach(rate -> {
            rate.setBudgetId(budget.getBudgetId());
            rate.setBudget(budget);
        });

        if (budget.getBudgetProjectIncomes() != null) {
            budget.getBudgetProjectIncomes().forEach(income -> {
                income.setBudgetId(budget.getBudgetId());
                income.setBudgetPeriodId(income.getBudgetPeriod() != null ? income.getBudgetPeriod().getBudgetPeriodId() : null);
            });
        }

        if (budget.getBudgetCostShares() != null) {
            budget.getBudgetCostShares().forEach(costShare -> {
                costShare.setBudgetId(budget.getBudgetId());
                costShare.setBudget(budget);
            });
        }

        if (budget.getBudgetUnrecoveredFandAs() != null) {
            budget.getBudgetUnrecoveredFandAs().forEach(fAndA -> {
                fAndA.setBudgetId(budget.getBudgetId());
                fAndA.setBudget(budget);
            });
        }

        if (budget.getBudgetPersons() != null) {
            budget.getBudgetPersons().forEach(person -> {
                person.setBudgetId(budget.getBudgetId());
                person.setBudget(budget);

                if (person.getBudgetPersonSalaryDetails() != null) {
                    person.getBudgetPersonSalaryDetails().forEach(details -> details.setBudgetId(budget.getBudgetId()));
                }
            });
        }

        if (budget.getBudgetSubAwards() != null) {
            budget.getBudgetSubAwards().forEach(subaward -> {
                subaward.setBudgetId(budget.getBudgetId());
                subaward.setBudget(budget);
            });
        }

        if (budget.getBudgetPeriods() != null) {
            budget.getBudgetPeriods().forEach(period -> {
                if (period.getBudgetLineItems() != null) {
                    period.getBudgetLineItems().forEach(lineItem -> {
                        lineItem.setBudgetId(budget.getBudgetId());
                        lineItem.setBudget(budget);
                        lineItem.setBudgetPeriodId(period.getBudgetPeriodId());
                        lineItem.setBudgetPeriodBO(period);

                        if (lineItem.getBudgetLineItemCalculatedAmounts() != null) {
                            lineItem.getBudgetLineItemCalculatedAmounts().forEach(calcAmount -> {
                                calcAmount.setBudgetId(budget.getBudgetId());
                                calcAmount.setBudgetPeriodId(period.getBudgetPeriodId());
                            });
                        }

                        if (lineItem.getBudgetPersonnelDetailsList() != null) {
                            lineItem.getBudgetPersonnelDetailsList().forEach(details -> {
                                details.setBudgetId(budget.getBudgetId());
                                details.setBudget(budget);
                                details.setBudgetPeriodId(period.getBudgetPeriodId());
                                details.setBudgetPeriodBO(period);

                                if (details.getBudgetCalculatedAmounts() != null) {
                                    details.getBudgetCalculatedAmounts().forEach(amount -> {
                                        amount.setBudgetId(budget.getBudgetId());
                                        amount.setBudgetPeriodId(period.getBudgetPeriodId());
                                    });
                                }

                                if (details.getBudgetPersonnelRateAndBaseList() != null) {
                                    details.getBudgetPersonnelRateAndBaseList().forEach(amount -> {
                                        amount.setBudgetId(budget.getBudgetId());
                                        amount.setBudgetPeriodId(period.getBudgetPeriodId());
                                    });
                                }
                            });
                        }

                        if (period.getBudgetModular() != null) {
                            period.getBudgetModular().setBudgetId(budget.getBudgetId());
                            period.getBudgetModular().setBudgetPeriodId(period.getBudgetPeriodId());
                            period.getBudgetModular().setBudgetPeriodObj(period);

                            if (period.getBudgetModular().getBudgetModularIdcs() != null) {
                                period.getBudgetModular().getBudgetModularIdcs().forEach(idc -> idc.setBudgetId(budget.getBudgetId()));
                            }
                        }
                    });
                }
            });
        }
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
        ProposalDevelopmentBudgetExt doServiceCopy = getDataObjectService().copyInstance(budget, CopyOption.RESET_OBJECT_ID, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER);
        if (StringUtils.isBlank(doServiceCopy.getObjectId())) {
            doServiceCopy.setObjectId(UUID.randomUUID().toString());
        }

        budget.setDevelopmentProposal(parent);
		if (developmentProposal != null) {
			doServiceCopy.setDevelopmentProposal(developmentProposal);
		} else {
			doServiceCopy.setDevelopmentProposal(parent);
		}

        final List<DocumentNextvalue> nextValues = budget.getNextValues().stream().map(orig -> {
            final DocumentNextvalue copy = new DocumentNextvalue();
            copy.setPropertyName(orig.getPropertyName());
            copy.setDocumentKey(doServiceCopy.getObjectId());
            copy.setNextValue(orig.getNextValue());
            return copy;
        }).collect(Collectors.toList());

        doServiceCopy.setNextValues(nextValues);

		return doServiceCopy;
    }

    @Override
    public boolean validateAddingNewBudget(BudgetParentDocument<DevelopmentProposal> parentDocument) {
        return true;
    }
    public void recalculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod) {
        budgetCalculationService.calculateBudget(budget);
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

    @Override
    public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod) {
        Budget budget = budgetPeriod.getBudget();
        newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
        newBudgetLineItem.setBudgetPeriodBO(budgetPeriod);
        newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
        newBudgetLineItem.setStartDate(budgetPeriod.getStartDate());
        newBudgetLineItem.setEndDate(budgetPeriod.getEndDate());
        newBudgetLineItem.setBudgetId(budget.getBudgetId());
        newBudgetLineItem.setLineItemNumber(budget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER));
        newBudgetLineItem.setApplyInRateFlag(true);
        newBudgetLineItem.setSubmitCostSharingFlag(budget.getSubmitCostSharingFlag());
        refreshBudgetLineCostElement(newBudgetLineItem);
        // on/off campus flag enhancement
        String onOffCampusFlag = budget.getOnOffCampusFlag();
        if (onOffCampusFlag.equalsIgnoreCase(BudgetConstants.DEFAULT_CAMPUS_FLAG)) {
            newBudgetLineItem.setOnOffCampusFlag(newBudgetLineItem.getCostElementBO().getOnOffCampusFlag());
        } else {
            newBudgetLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));
        }
        newBudgetLineItem.setBudgetCategoryCode(newBudgetLineItem.getCostElementBO().getBudgetCategoryCode());
        newBudgetLineItem.setLineItemSequence(newBudgetLineItem.getLineItemNumber());
        refreshBudgetLineBudgetCategory(newBudgetLineItem);

        if(isBudgetFormulatedCostEnabled()){
            List<String> formulatedCostElements = getFormulatedCostElements();
            if(formulatedCostElements.contains(newBudgetLineItem.getCostElement())){
                newBudgetLineItem.setFormulatedCostElementFlag(true);
            }
        }
    }

    protected void refreshBudgetLineCostElement(BudgetLineItem newBudgetLineItem) {
        if(StringUtils.isNotEmpty(newBudgetLineItem.getCostElement())) {
            getDataObjectService().wrap(newBudgetLineItem).fetchRelationship(COST_ELEMENT_BO);
        }
    }

    protected void refreshBudgetLineBudgetCategory(BudgetLineItem newBudgetLineItem) {
        if(StringUtils.isNotEmpty(newBudgetLineItem.getBudgetCategoryCode())) {
            getDataObjectService().wrap(newBudgetLineItem).fetchRelationship(BUDGET_CATEGORY);
        }
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

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public KcBusinessRulesEngine getKcBusinessRulesEngine() {
        return kcBusinessRulesEngine;
    }

    public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
        this.kcBusinessRulesEngine = kcBusinessRulesEngine;
    }

    public BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }
}
