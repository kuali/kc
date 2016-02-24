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
package org.kuali.coeus.common.budget.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.budget.framework.core.*;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

/**
 * This class implements methods specified by BudgetDocumentService interface
 */
public abstract class AbstractBudgetService<T extends BudgetParent> implements BudgetService<T> {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AbstractBudgetService.class);
    public static final String BUDGET_ID = "budgetId";
    public static final String VALID_CE_RATE_TYPES = "validCeRateTypes";
    public static final String RATE_CLASS_TYPE = "rateClassType";
    public static final String ACTIVITY_TYPE_CODE = "activityTypeCode";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;


    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Override
    public Budget addBudgetVersion(BudgetParentDocument<T> budgetParentDocument, String versionName, Map<String,Object> options) {
        if (!isBudgetVersionNameValid(budgetParentDocument.getBudgetParent(), versionName)) {
            LOG.debug("Buffered Version not Valid");
            return null;
        }

        return getNewBudgetVersion(budgetParentDocument, versionName, options);
    }

    protected abstract Budget getNewBudgetVersion(BudgetParentDocument<T> parent, String versionName, Map<String, Object> options);

    @Override
    public boolean validInflationCeRate(BudgetLineItemBase budgetLineItem) {
        final CostElement costElementBO = getCostElement(budgetLineItem.getCostElement());

        budgetLineItem.setCostElementBO(costElementBO);
        final List<ValidCeRateType> validCeRateTypes = costElementBO.getValidCeRateTypes();
        final QueryList<ValidCeRateType> qValidCeRateTypes = validCeRateTypes == null ? new QueryList<>() : new QueryList<>(validCeRateTypes);

        // Check whether it contains Inflation Rate
        final Equals eqInflation = new Equals(RATE_CLASS_TYPE, RateClassType.INFLATION.getRateClassType());
        final QueryList<ValidCeRateType> inflationValidCeRates = qValidCeRateTypes.filter(eqInflation);
        return !inflationValidCeRates.isEmpty();
    }

    protected CostElement getCostElement(String costElement) {
        CostElement costElementBO = businessObjectService.findBySinglePrimaryKey(CostElement.class, costElement);
        costElementBO.refreshReferenceObject(VALID_CE_RATE_TYPES);
        return costElementBO;
    }

    @Override
    public String getActivityTypeForBudget(Budget budget) {
        BudgetParent budgetParent = budget.getBudgetParent().getDocument().getBudgetParent();
        Map<String,Object> qMap = new HashMap<>();
        qMap.put(BUDGET_ID, budget.getBudgetId());
        List<BudgetRate> allPropRates = (List<BudgetRate>)businessObjectService.findMatching(BudgetRate.class, qMap);
        if (CollectionUtils.isNotEmpty(allPropRates)) {
            qMap.put(ACTIVITY_TYPE_CODE, budgetParent.getActivityTypeCode());
            Collection<BudgetRate> matchActivityTypePropRates =businessObjectService.findMatching(
                BudgetRate.class, qMap);
            if (CollectionUtils.isNotEmpty(matchActivityTypePropRates)) {
                for (BudgetRate budgetRate : allPropRates) { 
                    if (!budgetRate.getActivityTypeCode().equals(budgetParent.getActivityTypeCode())) {
                        return budgetRate.getActivityTypeCode();
                    }
                }
                return budgetParent.getActivityTypeCode();                
            } else {
                return allPropRates.get(0).getActivityTypeCode();
            }
        }
                
        return "x";
        
    }

    
    @Override
    public Collection<BudgetRate> getSavedProposalRates(Budget budgetToOpen) {
        Map<String,Long> qMap = new HashMap<>();
        qMap.put(BUDGET_ID,budgetToOpen.getBudgetId());
        return businessObjectService.findMatching(BudgetRate.class, qMap);
    }

    /**
     * 
     * Do this so that new personnel details(or copied ones) can be calculated
     */
    protected void copyLineItemToPersonnelDetails(Budget budget) {
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetLineItems() != null && !budgetPeriod.getBudgetLineItems().isEmpty()) {
                for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {        
                    if (budgetLineItem.getBudgetPersonnelDetailsList() != null && !budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
                        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                        	budgetPersonnelDetails.setBudget(budgetLineItem.getBudget());
                            budgetPersonnelDetails.setBudgetId(budgetLineItem.getBudgetId());
                            budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
                            budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
                            budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
                            budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
                       }
                    }
                }
            }
        }
    }    

    protected boolean isBudgetFormulatedCostEnabled() {
        String formulatedCostEnabled = getParameterService().getParameterValueAsString(Budget.class, Constants.FORMULATED_COST_ENABLED);
        return (formulatedCostEnabled!=null && formulatedCostEnabled.equalsIgnoreCase("Y"));
    }
    protected List<String> getFormulatedCostElements() {
        String formulatedCEsValue = getParameterService().getParameterValueAsString(Budget.class, Constants.FORMULATED_COST_ELEMENTS);
        String[] formulatedCEs = formulatedCEsValue==null?new String[0]:formulatedCEsValue.split(",");
        return Arrays.asList(formulatedCEs);
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
