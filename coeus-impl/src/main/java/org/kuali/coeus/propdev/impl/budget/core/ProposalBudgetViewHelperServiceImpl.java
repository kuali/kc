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
package org.kuali.coeus.propdev.impl.budget.core;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.common.impl.KcViewHelperServiceImpl;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetNavigationService;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModular;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularIdc;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.coeus.sys.impl.validation.DataValidationItem;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service("proposalBudgetViewHelperService")
@Scope("prototype")
public class ProposalBudgetViewHelperServiceImpl extends KcViewHelperServiceImpl {

    public static final String SINGLE_POINT_ENTRY_FLAG = "SINGLE_POINT_ENTRY_FLAG";
    public static final String ERROR_BUDGET_PERIOD_MINIMUM = "error.budget.period.minimum";
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("kcBusinessRulesEngine")
    private KcBusinessRulesEngine kcBusinessRulesEngine;
    
    @Autowired
    @Qualifier("proposalBudgetService")
    private ProposalBudgetService proposalBudgetService;
    
    @Autowired
    @Qualifier("proposalHierarchyService")
    private ProposalHierarchyService proposalHierarchyService;

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
    @Qualifier("proposalBudgetNavigationService")
    private ProposalBudgetNavigationService proposalBudgetNavigationService;

    public void finalizeNavigationLinks(Action action, Object model, String direction) {
        ProposalBudgetForm propBudgetForm = (ProposalBudgetForm) model;
        getProposalBudgetNavigationService().createNavigationLink(action,propBudgetForm,direction);
    }


    public boolean isBudgetLineItemEditable(String selectedCollectionPath, String index, HashMap<String,List<String>> editableBudgetLineItem) {
    	boolean retVal = false;
        if (editableBudgetLineItem.containsKey(selectedCollectionPath)) {
            if (editableBudgetLineItem.get(selectedCollectionPath).contains(index)) {
                retVal = true;
            }
        }
        return retVal;
    }

    @Override
    public void processBeforeAddLine(ViewModel model, Object addLine, String collectionId, String collectionPath) {
        if (addLine instanceof BudgetProjectIncome) {
            BudgetProjectIncome budgetProjectIncome = (BudgetProjectIncome) addLine;
            budgetProjectIncome.setBudgetId(((ProposalBudgetForm) model).getBudget().getBudgetId());
            budgetProjectIncome.setDocumentComponentId(((ProposalBudgetForm) model).getBudget().getNextValue(budgetProjectIncome.getDocumentComponentIdKey()));
            budgetProjectIncome.setBudgetPeriod(((ProposalBudgetForm) model).getBudget().getBudgetPeriod(budgetProjectIncome));
        }
        if (addLine instanceof BudgetUnrecoveredFandA) {
            BudgetUnrecoveredFandA budgetUnrecoveredFandA = (BudgetUnrecoveredFandA) addLine;
            budgetUnrecoveredFandA.setBudget(((ProposalBudgetForm) model).getBudget());
            budgetUnrecoveredFandA.setDocumentComponentId(((ProposalBudgetForm) model).getBudget().getNextValue(budgetUnrecoveredFandA.getDocumentComponentIdKey()));
        }
        if (addLine instanceof BudgetCostShare) {
            BudgetCostShare budgetCostShare = (BudgetCostShare) addLine;
            budgetCostShare.setBudget(((ProposalBudgetForm) model).getBudget());
            budgetCostShare.setDocumentComponentId(((ProposalBudgetForm) model).getBudget().getNextValue(budgetCostShare.getDocumentComponentIdKey()));
        }
        if (addLine instanceof BudgetModularIdc) {
            BudgetModularIdc budgetModularIdc = (BudgetModularIdc) addLine;
            try {
                budgetModularIdc.setBudgetModular((BudgetModular)PropertyUtils.getNestedProperty(model, StringUtils.replace(collectionPath, ".budgetModularIdcs", "")));
            } catch (Exception e) {
                throw new RuntimeException("proposal budget modular cannot be retrieved from propdev budget",e);
            }
            budgetModularIdc.setBudgetPeriod(budgetModularIdc.getBudgetModular().getBudgetPeriod());
            budgetModularIdc.setBudgetId(budgetModularIdc.getBudgetModular().getBudgetId());
            budgetModularIdc.setBudgetPeriodId(budgetModularIdc.getBudgetModular().getBudgetPeriodId());
            budgetModularIdc.setRateNumber(((ProposalBudgetForm) model).getBudget().getNextValue("rateNumber"));
          }
    }

    @Override
    public void processAfterSaveLine(ViewModel model, Object lineObject, String collectionId, String collectionPath) {
    	getDataObjectService().save(lineObject);
    }

    @Override
    protected boolean performDeleteLineValidation(ViewModel model, String collectionId, String collectionPath,
                                                  Object deleteLine) {
        if (deleteLine instanceof BudgetSubAwards) {
            getDataObjectService().delete(deleteLine);
        }
        else if (deleteLine instanceof BudgetPeriod) {
            try {
                @SuppressWarnings("unchecked")
                final List<BudgetPeriod> periods = (List<BudgetPeriod>) PropertyUtils.getProperty(model, collectionPath);

                // Do not allow deletion of last budget period
                if (periods != null && periods.size() == 1) {
                    getGlobalVariableService().getMessageMap().putError(collectionPath, ERROR_BUDGET_PERIOD_MINIMUM);
                    return false;
                }
            }
            catch (InvocationTargetException|NoSuchMethodException|IllegalAccessException  e ) {
                throw new RuntimeException("Problem reading the periods from Budget period collection", e);
            }
        }
       return super.performDeleteLineValidation(model,collectionId,collectionPath,deleteLine);
    }

    public String getProposalStatusForDisplay(DevelopmentProposal proposal) {
        final ProposalState state = proposal.getHierarchyAwareProposalStatus();
        return state != null ? state.getDescription() : "";
    }

    public String getWizardMaxResults() {
        return getParameterService().getParameterValueAsString(KRADConstants.KRAD_NAMESPACE,
                KRADConstants.DetailTypes.LOOKUP_PARM_DETAIL_TYPE,
                KRADConstants.SystemGroupParameterNames.LOOKUP_RESULTS_LIMIT);
    }
    
    public boolean syncRequiresEndDateExtension(DevelopmentProposal proposal) {
    	DevelopmentProposal hierarchyProposal = getProposalHierarchyService().getDevelopmentProposal(proposal.getHierarchyParentProposalNumber());
    	return getProposalHierarchyService().needToExtendProjectDate(hierarchyProposal, proposal);
    }
    
    public boolean syncAllRequiresEndDateExtension(DevelopmentProposal hierarchyProposal) {
    	return getProposalHierarchyService().needToExtendProjectDate(hierarchyProposal);
    }    

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public boolean isShowModularBudgetQuestion(String sponsorCode) {
        return getSponsorHierarchyService().isSponsorNihMultiplePi(sponsorCode);
    }

    public boolean displayPersonSalaryByPeriod() {
        return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, BudgetConstants.ENABLE_BUDGET_CALCULATED_SALARY);
    }

    public String getDateFromTimeStamp(Timestamp timestamp) {
        return ObjectUtils.isNull(timestamp)? "" : getDateTimeService().toDateString(new Date(timestamp.getTime()));
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public List<DataValidationItem> populateDataValidation(ProposalBudgetForm form) {
        getGlobalVariableService().getAuditErrorMap().clear();
        applyBudgetAuditRules(form);
        return populateDataValidation();
    }

    public boolean applyBudgetAuditRules(ProposalBudgetForm form) {
        return getKcBusinessRulesEngine().applyRules(new BudgetAuditRuleEvent(form.getBudget()));
    }
    
	public KcBusinessRulesEngine getKcBusinessRulesEngine() {
		return kcBusinessRulesEngine;
	}

	public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
		this.kcBusinessRulesEngine = kcBusinessRulesEngine;
	}

	public ProposalHierarchyService getProposalHierarchyService() {
		return proposalHierarchyService;
	}

	public void setProposalHierarchyService(
			ProposalHierarchyService proposalHierarchyService) {
		this.proposalHierarchyService = proposalHierarchyService;
	}
    
	public boolean isBudgetMarkedForSubmission(Budget finalBudget, Budget currentBudget) {
		return getProposalBudgetService().isBudgetMarkedForSubmission(finalBudget, currentBudget);
	}

	public ProposalBudgetService getProposalBudgetService() {
		return proposalBudgetService;
	}

	public void setProposalBudgetService(ProposalBudgetService proposalBudgetService) {
		this.proposalBudgetService = proposalBudgetService;
	}

    public boolean displaySubawardPeriodWarning(Budget budget) {
        return hasMultiplePeriods(budget) && isEmptyBudgetLineItemExists(budget);
    }

    private boolean hasMultiplePeriods(Budget budget) {
        return budget.getBudgetPeriods().size() > 1;
    }

    private boolean isEmptyBudgetLineItemExists(Budget budget) {
        for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (CollectionUtils.isEmpty(budgetPeriod.getBudgetLineItems())) {
                return true;
            }
        }
        return false;
    }

    public boolean budgetLineItemsExistInLaterPeriods(List<BudgetPeriod> budgetPeriods) {
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if (budgetPeriod.getBudgetPeriod() != 1 && !budgetPeriod.getBudgetLineItems().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void prepareHierarchySummary(ProposalBudgetForm form) {
        if (form.getDevelopmentProposal().isInHierarchy()) {
            form.setHierarchyDevelopmentProposals(getProposalHierarchyService().getHierarchyProposals(form.getDevelopmentProposal()));

            form.getHierarchyDevelopmentProposals().stream()
                    .filter(developmentProposal -> developmentProposal.getHierarchySummaryBudget().getBudgetSummaryDetails().isEmpty())
                    .forEach(developmentProposal -> getBudgetCalculationService().populateBudgetSummaryTotals(developmentProposal.getHierarchySummaryBudget()));
        }
    }

    public boolean isSinglePointEntry() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,Constants.PARAMETER_COMPONENT_DOCUMENT, SINGLE_POINT_ENTRY_FLAG);
    }

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    public ProposalBudgetNavigationService getProposalBudgetNavigationService() {
        return proposalBudgetNavigationService;
    }

    public void setProposalBudgetNavigationService(ProposalBudgetNavigationService proposalBudgetNavigationService) {
        this.proposalBudgetNavigationService = proposalBudgetNavigationService;
    }
}
