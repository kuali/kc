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
package org.kuali.kra.award.budget;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.ParameterService;

public class AwardBudgetExt extends Budget { 
    

    private static final long serialVersionUID = 1L;

    private String awardBudgetStatusCode; 
    private String awardBudgetTypeCode; 
    private BudgetDecimal obligatedTotal;
    private BudgetDecimal obligatedAmount=BudgetDecimal.ZERO;
    private AwardBudgetStatus awardBudgetStatus; 
    private AwardBudgetType awardBudgetType;
    private String description;
    private String budgetInitiator;
    private BudgetVersionOverview prevBudget;
    private List<BudgetDecimal> budgetsTotals;
    private List<AwardBudgetLimit> awardBudgetLimits;
    
    public AwardBudgetExt() {
        super();
        awardBudgetLimits = new ArrayList<AwardBudgetLimit>();
    } 
    public AwardBudgetPeriodExt getNewBudgetPeriod(){
        return new AwardBudgetPeriodExt();
    }
    public BudgetLineItem getNewBudgetLineItem() {
        return new AwardBudgetLineItemExt();
    }
    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new AwardBudgetPersonnelDetailsExt();
    }

    public String getAwardBudgetStatusCode() {
        return awardBudgetStatusCode;
    }

    public void setAwardBudgetStatusCode(String awardBudgetStatusCode) {
        this.awardBudgetStatusCode = awardBudgetStatusCode;
    }

    public String getAwardBudgetTypeCode() {
        return awardBudgetTypeCode;
    }

    public void setAwardBudgetTypeCode(String awardBudgetTypeCode) {
        this.awardBudgetTypeCode = awardBudgetTypeCode;
    }

    public AwardBudgetStatus getAwardBudgetStatus() {
        return awardBudgetStatus;
    }

    public void setAwardBudgetStatus(AwardBudgetStatus awardBudgetStatus) {
        this.awardBudgetStatus = awardBudgetStatus;
    }

    public AwardBudgetType getAwardBudgetType() {
        return awardBudgetType;
    }

    public void setAwardBudgetType(AwardBudgetType awardBudgetType) {
        this.awardBudgetType = awardBudgetType;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("budgetId", this.getBudgetId());
        hashMap.put("obligatedTotal", this.getObligatedTotal());
        hashMap.put("obligatedAmount", this.getObligatedAmount());
        hashMap.put("awardBudgetStatusCode", this.getAwardBudgetStatusCode());
        hashMap.put("awardBudgetTypeCode", this.getAwardBudgetTypeCode());
        return hashMap;
    }

    /**
     * Gets the ohRatesNonEditable attribute. 
     * @return Returns the ohRatesNonEditable.
     */
    public boolean getOhRatesNonEditable() {
        Award award = (Award)getBudgetDocument().getParentDocument().getBudgetParent();
        return award.getAwardFandaRate().isEmpty()?false:true;
    }

    /**
     * Gets the ebRatesNonEditable attribute. 
     * @return Returns the ebRatesNonEditable.
     */
    public boolean getEbRatesNonEditable() {
        Award award = (Award)getBudgetDocument().getParentDocument().getBudgetParent();
        return ((award.getSpecialEbRateOffCampus()!=null && award.getSpecialEbRateOffCampus().isPositive())||
                 (award.getSpecialEbRateOnCampus()!=null && award.getSpecialEbRateOnCampus().isPositive()))?true:false;
    }

    /**
     * Gets the obligatedTotal attribute. 
     * @return Returns the obligatedTotal.
     */
    public BudgetDecimal getObligatedTotal() {
        return obligatedTotal==null?BudgetDecimal.ZERO:obligatedTotal;
    }

    /**
     * Sets the obligatedTotal attribute value.
     * @param obligatedTotal The obligatedTotal to set.
     */
    public void setObligatedTotal(BudgetDecimal obligatedAmount) {
        this.obligatedTotal = obligatedAmount;
    }
    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public BudgetDecimal getObligatedAmount() {
        return obligatedAmount==null?BudgetDecimal.ZERO:obligatedAmount;
    }
    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(BudgetDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }
    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the budgetInitiator attribute. 
     * @return Returns the budgetInitiator.
     */
    public String getBudgetInitiator() {
        return budgetInitiator;
    }
    /**
     * Sets the budgetInitiator attribute value.
     * @param budgetInitiator The budgetInitiator to set.
     */
    public void setBudgetInitiator(String budgetInitiator) {
        this.budgetInitiator = budgetInitiator;
    }
    
    public BudgetVersionOverview getPrevBudget() {
        if (prevBudget == null && this.getBudgetDocument() != null) {
            Integer version = 0;
            for (BudgetDocumentVersion budgetDocumentVersion : this.getBudgetDocument().getParentDocument().getBudgetDocumentVersions()) {
                for (BudgetVersionOverview budgetVersionOverview : budgetDocumentVersion.getBudgetVersionOverviews()) {
                    if (budgetVersionOverview != null && budgetVersionOverview.getBudgetVersionNumber() > version
                            && "9".equals(((AwardBudgetVersionOverviewExt)budgetVersionOverview).getAwardBudgetStatusCode())
                            && budgetVersionOverview.getBudgetVersionNumber() < this.getBudgetVersionNumber()) {
                        version = budgetVersionOverview.getBudgetVersionNumber();
                        prevBudget = budgetVersionOverview;
                    }
                }
            }
            if (prevBudget == null) {
                prevBudget = new BudgetVersionOverview();
                prevBudget.setCostSharingAmount(BudgetDecimal.ZERO);
                prevBudget.setTotalCost(BudgetDecimal.ZERO);
                prevBudget.setTotalCostLimit(BudgetDecimal.ZERO);
                prevBudget.setTotalDirectCost(BudgetDecimal.ZERO);
                prevBudget.setTotalIndirectCost(BudgetDecimal.ZERO);
                prevBudget.setUnderrecoveryAmount(BudgetDecimal.ZERO);
            }
        }
        return prevBudget;
    }
    
    
    public void setPrevBudget(BudgetVersionOverview prevBudget) {
        this.prevBudget = prevBudget;
    }
    public List<BudgetDecimal> getBudgetsTotals() {
        addBudgetTotals();
        return budgetsTotals;
    }
    public void setBudgetsTotals(List<BudgetDecimal> budgetsTotals) {
        this.budgetsTotals = budgetsTotals;
    }
    
    private void addBudgetTotals() {
        List <BudgetDecimal> totals = new ArrayList<BudgetDecimal>();
        totals.add(this.getTotalCost().add(getPrevBudget().getTotalCost()));
        totals.add(this.getTotalDirectCost().add(getPrevBudget().getTotalDirectCost()));
        totals.add(this.getTotalIndirectCost().add(getPrevBudget().getTotalIndirectCost()));
        totals.add(this.getUnderrecoveryAmount().add(getPrevBudget().getUnderrecoveryAmount()));
        totals.add(this.getCostSharingAmount().add(getPrevBudget().getCostSharingAmount()));
        this.setBudgetsTotals(totals);

    }
    public String getRebudgetFlag(){
        String rebudgetTypeCode = KraServiceLocator.getService(ParameterService.class).
                    getParameterValue(AwardBudgetDocument.class,KeyConstants.AWARD_BUDGET_TYPE_REBUDGET);
        return Boolean.toString(getAwardBudgetTypeCode().equals(rebudgetTypeCode));
    }
    public List<AwardBudgetLimit> getAwardBudgetLimits() {
        return awardBudgetLimits;
    }
    public void setAwardBudgetLimits(List<AwardBudgetLimit> awardBudgetLimits) {
        this.awardBudgetLimits = awardBudgetLimits;
    }

}