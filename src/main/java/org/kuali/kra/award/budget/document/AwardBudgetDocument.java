/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.budget.document;

import java.util.List;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.commitments.FandaRateType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterConstants.COMPONENT;
import org.kuali.rice.kns.service.ParameterConstants.NAMESPACE;

@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_AWARD_BUDGET)
@COMPONENT(component=Constants.PARAMETER_COMPONENT_DOCUMENT)
public class AwardBudgetDocument extends BudgetDocument<org.kuali.kra.award.home.Award> {

    private static final String AWARD_BUDGET_DOCUMENT_TYPE_CODE = "ABGT";

    private BudgetDecimal obligatedTotal;
    private boolean budgetRateTypePopulated;
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3564659576355229703L;

    @Override
    public void initialize() {
        Award award = getParentDocument().getBudgetParent();
        AwardBudgetExt awardBudget = getAwardBudget();
        awardBudget.setObligatedTotal(new BudgetDecimal(award.getObligatedTotal().bigDecimalValue()));
        List<BudgetRate> budgetRates = awardBudget.getBudgetRates();
        populateBudgetRateTypes(awardBudget,budgetRates);
    }
    
    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#getDocumentTypeCode()
     */
    @Override
    public String getDocumentTypeCode() {
        return AWARD_BUDGET_DOCUMENT_TYPE_CODE;
    }

    /**
     * 
     * This method returns Budget object. Creates new budget instance if the budgets list is empty
     * @return Budget
     */
    public Budget getBudget(){
        if(getBudgets().isEmpty()){
            getBudgets().add(new AwardBudgetExt());
        }
        Budget budget = getBudgets().get(0);
        return budget; 
    }
    
    private void populateBudgetRateTypes(Budget budget,List<BudgetRate> budgetRates) {
        for (BudgetRate budgetRate : budgetRates) {
            if(RateClassType.OVERHEAD.getRateClassType().equals(budgetRate.getRateClassType())){
                budgetRate.setBudget(budget);
                if(budgetRate.getNonEditableRateFlag()){
                    budgetRate.setRateType(createAwardRateType(budgetRate));
                }
            }
        }
        
    }

    private RateType createAwardRateType(BudgetRate rate) {
        FandaRateType awardRateType = getBusinesssObjectService().findBySinglePrimaryKey(FandaRateType.class, rate.getRateTypeCode());
        RateType newRateType = new RateType();
        newRateType.setRateClassCode(rate.getRateClassCode());
        newRateType.setRateTypeCode(awardRateType.getFandaRateTypeCode().toString());
        newRateType.setDescription(awardRateType.getDescription());
        return newRateType;
    }

    private BusinessObjectService getBusinesssObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    public AwardBudgetExt getAwardBudget(){
        return (AwardBudgetExt)getBudget();
    }

    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public BudgetDecimal getObligatedTotal() {
        return obligatedTotal;
    }

    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedTotal(BudgetDecimal obligatedTotal) {
        this.obligatedTotal = obligatedTotal;
    }

}
