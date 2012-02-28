/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.calculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.NotEquals;
import org.kuali.kra.budget.calculator.query.Operator;
import org.kuali.kra.budget.rates.AbstractBudgetRate;
import org.kuali.kra.budget.rates.BudgetLaRate;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.RateClassBaseExclusion;
import org.kuali.kra.budget.rates.RateClassBaseInclusion;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class is responsible for calculating the base unit <code>BreakUpInterval</code> for the budget
 */
public class BreakupIntervalServiceImpl implements BreakupIntervalService {
    private BusinessObjectService businessObjectService;
    private List<RateClassBaseInclusion> rateClassBaseInclusions;
    private List<RateClassBaseExclusion> rateClassBaseExclusions;

    public List<RateClassBaseInclusion> getRateClassBaseInclusions() {
        return rateClassBaseInclusions;
    }
    public void setRateClassBaseInclusions(List<RateClassBaseInclusion> rateClassBaseInclusions) {
        this.rateClassBaseInclusions = rateClassBaseInclusions;
    }
    public List<RateClassBaseExclusion> getRateClassBaseExclusions() {
        return rateClassBaseExclusions;
    }
    public void setRateClassBaseExclusions(List<RateClassBaseExclusion> rateClassBaseExclusions) {
        this.rateClassBaseExclusions = rateClassBaseExclusions;
    }

    private void initializeRateClassBases() {
        setRateClassBaseInclusions((List<RateClassBaseInclusion>) getBusinessObjectService().findAll(RateClassBaseInclusion.class));
        setRateClassBaseExclusions((List<RateClassBaseExclusion>) getBusinessObjectService().findAll(RateClassBaseExclusion.class));
    }
    /**
     * @see org.kuali.kra.budget.calculator.BreakupIntervalService#calculate(org.kuali.kra.budget.calculator.BreakUpInterval)
     */
    @Override
    public void calculate(BreakUpInterval breakupInterval) {
        initializeRateClassBases();
        QueryList<RateAndCost> rateAndCosts = breakupInterval.getRateAndCosts();
        List<RateClassBaseInclusion> rateClassInclusions = (List<RateClassBaseInclusion>) getBusinessObjectService().findAll(RateClassBaseInclusion.class);
        for (RateClassBaseInclusion rateClassBaseInclusion : rateClassInclusions) {
            if(canApplyRate(rateClassBaseInclusion,rateAndCosts)){
                findAndApplyRateRecursively(breakupInterval,rateClassBaseInclusion,rateAndCosts);
            }
        }
        breakupInterval.setUnderRecovery(rateAndCosts.sumObjects("underRecovery"));
    }
    private boolean canApplyRate(RateClassBaseInclusion rateClassBaseInclusion, QueryList<RateAndCost> rateAndCosts) {
        Equals eqRateClassCode = new Equals("rateClassCode",rateClassBaseInclusion.getRateClassCode());
        Equals eqRateTypeCode = new Equals("rateTypeCode",rateClassBaseInclusion.getRateTypeCode());
        QueryList<RateAndCost> applicableRateList = rateAndCosts.filter(rateClassBaseInclusion.getRateTypeCode()==null?
                                                                            eqRateClassCode:new And(eqRateClassCode,eqRateTypeCode));
        return !applicableRateList.isEmpty();
    }
    private void findAndApplyRateRecursively(BreakUpInterval breakupInterval,RateClassBaseInclusion rateClassBaseInclusion, QueryList<RateAndCost> rateAndCosts) {
        RateClassInclusionTree rateClassInclusionTree = getRateClassInclusionTree(rateClassBaseInclusion.getRateClassBaseInclusionId());
        List<Node<RateClassBaseInclusion>> rateClassInclusions = rateClassInclusionTree.toList();
        int numberOfNodes = rateClassInclusions.size();
        BudgetDecimal baseCost = breakupInterval.getApplicableAmt(); 
        BudgetDecimal baseCostSharing = breakupInterval.getApplicableAmtCostSharing();
        BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
        BudgetDecimal calculatedCostSharing = BudgetDecimal.ZERO;
        for (int i = numberOfNodes - 1; i >= 0; i--) {
            Node<RateClassBaseInclusion> rateClassInclusionNode = rateClassInclusions.get(i);
            RateClassBaseInclusion rcbi = rateClassInclusionNode.getData();
            QueryList<RateAndCost> applicableRateList = filterApplicableRates(rateAndCosts,rcbi);
            if(!applicableRateList.isEmpty()){
                for (RateAndCost rateAndCost : applicableRateList) {
                    if(!rateAndCost.getRateClassType().equals(RateClassType.OVERHEAD.getRateClassType()) &&
                            !rateAndCost.isApplyRateFlag()) continue;
                    BudgetDecimal rate = getAppliedRate(breakupInterval,rateAndCost);
                    calculatedCost = baseCost.percentage(rate);
                    calculatedCostSharing = baseCostSharing.percentage(rate);
                    if(i==0){
                        rateAndCost.setAppliedRate(rate);
                        rateAndCost.setCalculatedCost(rateAndCost.getCalculatedCost().add(calculatedCost));
                        rateAndCost.setCalculatedCostSharing(rateAndCost.getCalculatedCostSharing().add(calculatedCostSharing));
                        rateAndCost.setBaseAmount(rateAndCost.getBaseAmount().add(baseCost));
                        rateAndCost.setBaseCostSharingAmount(rateAndCost.getBaseCostSharingAmount().add(baseCostSharing));
                        rateAndCost.setCalculated(true);
                        if(rateAndCost.getRateClassType().equals(RateClassType.OVERHEAD.getRateClassType())){
                            calculateUnderRecovery(breakupInterval,rateAndCost);
                        }
                    }
                }
                baseCost = calculatedCost;
                baseCostSharing = calculatedCostSharing;
            }else{
                baseCost = BudgetDecimal.ZERO;
                baseCostSharing = BudgetDecimal.ZERO;
            }
        }
    }

    private BudgetDecimal getAppliedRate(BreakUpInterval breakupInterval, RateAndCost rateAndCost) {
        Equals eqRateClassCode = new Equals("rateClassCode",rateAndCost.getRateClassCode());
        Equals eqRateTypeCode = new Equals("rateTypeCode",rateAndCost.getRateTypeCode());
        And rcAndRt = new And(eqRateClassCode,eqRateTypeCode);
        QueryList<BudgetRate> breakupIntervalRates = breakupInterval.getBudgetProposalRates();
        QueryList<BudgetLaRate> breakupIntervalLaRates = breakupInterval.getBudgetProposalLaRates();
        @SuppressWarnings("rawtypes")
        QueryList applicableRates = breakupIntervalRates.filter(rcAndRt);
        if(applicableRates.isEmpty()){
            applicableRates = breakupIntervalLaRates.filter(rcAndRt);
        }
        return applicableRates.isEmpty()?BudgetDecimal.ZERO:((AbstractBudgetRate)applicableRates.get(0)).getApplicableRate();
    }
    private void calculateUnderRecovery(BreakUpInterval breakupInterval, RateAndCost rateAndCost) {
        BudgetDecimal underRecoveryRate = BudgetDecimal.ZERO;
        BudgetDecimal instituteRate = BudgetDecimal.ZERO;
        if (breakupInterval.getURRatesBean() != null) {
            instituteRate = breakupInterval.getURRatesBean().getInstituteRate();
        } else {
            instituteRate = filterInstituteRate(breakupInterval,rateAndCost);
        }
        if (!rateAndCost.isApplyRateFlag()) {
            underRecoveryRate = instituteRate;
            rateAndCost.setCalculatedCost(BudgetDecimal.ZERO);
            rateAndCost.setCalculatedCostSharing(BudgetDecimal.ZERO);
        }else {
            underRecoveryRate = instituteRate.subtract(getAppliedRate(breakupInterval, rateAndCost));
        }
        BudgetDecimal underRecovery = rateAndCost.getBaseAmount().percentage(underRecoveryRate);
        BudgetDecimal underRecoveryCostShare = rateAndCost.getBaseCostSharingAmount().percentage(underRecoveryRate);
        rateAndCost.setUnderRecovery(underRecovery.add(underRecoveryCostShare));
    }
    private BudgetDecimal filterInstituteRate(BreakUpInterval breakupInterval,RateAndCost rateAndCost) {
        Equals eqRateClassCode = new Equals("rateClassCode",rateAndCost.getRateClassCode());
        Equals eqRateTypeCode = new Equals("rateTypeCode",rateAndCost.getRateTypeCode());
        And rcAndRt = new And(eqRateClassCode,eqRateTypeCode);
        QueryList<BudgetRate> breakupIntervalRates = breakupInterval.getBudgetProposalRates();
        QueryList<BudgetRate> applicableRates = breakupIntervalRates.filter(rcAndRt);
        return applicableRates.isEmpty()?BudgetDecimal.ZERO:applicableRates.get(0).getInstituteRate();
    }
    private QueryList<RateAndCost> filterApplicableRates(QueryList<RateAndCost> rateAndCosts, RateClassBaseInclusion rcbi) {
        Equals eqRateClassCode = new Equals("rateClassCode",rcbi.getRateClassCode());
        Equals eqRateTypeCode = new Equals("rateTypeCode",rcbi.getRateTypeCode());
        Operator rcAndRt = rcbi.getRateTypeCode()==null?eqRateClassCode:new And(eqRateClassCode,eqRateTypeCode);
        QueryList<RateAndCost> applicableRateList = rateAndCosts.filter(rcAndRt);
        List<RateClassBaseInclusion> rateClassInclusions = (List<RateClassBaseInclusion>) getBusinessObjectService().findAll(RateClassBaseInclusion.class);
        for (RateClassBaseInclusion rateClassInclusion : rateClassInclusions) {
            if(rcbi.getRateClassCode().equals(rateClassInclusion.getRateClassCode()) && rcbi.getRateTypeCode()==null && rateClassInclusion.getRateTypeCode()!=null){
                NotEquals neRateTypeCode = new NotEquals("rateTypeCode",rateClassInclusion.getRateTypeCode());
                applicableRateList = applicableRateList.filter(neRateTypeCode);
            }
        }
        return applicableRateList;
    }

    /**
     * Non-generic subclass of Tree<Task>
     */
    public class RateClassInclusionTree extends Tree<RateClassBaseInclusion> {
        public RateClassInclusionTree() {
            super();
        }
    }

    /**
     * Non-generic subclass of Node<T>
     */
    public class RateClassBaseInclusionNode extends Node<RateClassBaseInclusion> {
        public RateClassBaseInclusionNode() {
            super();
        }
    }
    /**
     * Gets a single TaskTree by id. This is the head of a recursive method call
     * to getRecursive().
     * @param id the id of the TaskTree.
     * @return a TaskTree for that id.
     */
    public RateClassInclusionTree getRateClassInclusionTree(Long id) {
        RateClassInclusionTree taskTree = new RateClassInclusionTree();
        Node<RateClassBaseInclusion> rootElement = new Node<RateClassBaseInclusion>(
                getBusinessObjectFactory().findBySinglePrimaryKey(RateClassBaseInclusion.class, id));
        getRecursive(rootElement, taskTree);
        taskTree.setRootElement(rootElement);
        return taskTree;
    }
     
    private BusinessObjectService getBusinessObjectFactory() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * Recursively descends the Tree and populates the TaskTree object.
     * @param taskElement the root Node.
     * @param tree the TaskTree object to populate.
     */
    private void getRecursive(Node<RateClassBaseInclusion> taskElement, RateClassInclusionTree tree) {
        List<RateClassBaseInclusion> children = findRateClassInclusions(taskElement.getData().getRateClassCodeIncl(),taskElement.getData().getRateTypeCodeIncl());
        List<Node<RateClassBaseInclusion>> childElements = new ArrayList<Node<RateClassBaseInclusion>>();
        for (Iterator<RateClassBaseInclusion> it = children.iterator(); it.hasNext();) {
            RateClassBaseInclusion childTask = it.next();
            Node<RateClassBaseInclusion> childElement = new Node<RateClassBaseInclusion>(childTask);
            if(!isExcluded(taskElement.getData().getRateClassCode(),taskElement.getData().getRateTypeCode(),childElement)){
                childElements.add(childElement);
                getRecursive(childElement, tree);
            }
        }
        taskElement.setChildren(childElements);
    }

    private boolean isExcluded(String rateClassCode,String rateTypeCode, Node<RateClassBaseInclusion> childElement) {
        Equals eqRateClassCode = new Equals("rateClassCode",rateClassCode);
        Equals eqRateTypeCode = new Equals("rateTypeCode",rateTypeCode);
        Operator rcAndRt = rateTypeCode==null?eqRateClassCode:new And(eqRateClassCode,eqRateTypeCode);
        Equals eqRateClassCodeExcl = new Equals("rateClassCodeExcl",childElement.getData().getRateClassCode());
        Equals eqRateTypeCodeExcl = new Equals("rateTypeCodeExcl",childElement.getData().getRateTypeCode());
        Operator rcExclAndRtExcl = eqRateTypeCodeExcl==null?eqRateClassCodeExcl:new And(eqRateClassCodeExcl,eqRateTypeCodeExcl);
        And rcAndRcExcl = new And(rcAndRt,rcExclAndRtExcl);
        QueryList<RateClassBaseExclusion> rateClassQueryList = new QueryList<RateClassBaseExclusion>(getRateClassBaseExclusions());
        QueryList<RateClassBaseExclusion> filteredRateClasses = rateClassQueryList.filter(rcAndRcExcl);
        return !filteredRateClasses.isEmpty();
    }

    private List<RateClassBaseInclusion> findRateClassInclusions(String rateClassCode,String rateTypeCode) {
        QueryList<RateClassBaseInclusion> rateInclusions = new QueryList<RateClassBaseInclusion>(getRateClassBaseInclusions());
        rateInclusions.sort("rateClassCodeIncl");
        Equals eqRateClassCode = new Equals("rateClassCode",rateClassCode);
        Equals eqRateTypeCode = new Equals("rateTypeCode",rateTypeCode);
        Operator rcAndRt = rateTypeCode==null?eqRateClassCode:new And(eqRateClassCode,eqRateTypeCode);
        List<RateClassBaseInclusion> inclusions = (List<RateClassBaseInclusion>) rateInclusions.filter(rcAndRt);
        for (RateClassBaseInclusion rateClassBaseInclusion : inclusions) {
            List<RateClassBaseInclusion> dependentInclusions = findRateClassInclusions(rateClassBaseInclusion.getRateClassCodeIncl(),rateClassBaseInclusion.getRateTypeCodeIncl());
            for (RateClassBaseInclusion rateClassBaseInclusion2 : dependentInclusions) {
                rateClassBaseInclusion2.setParentRateClassBaseInclusionId(rateClassBaseInclusion.getRateClassBaseInclusionId());
            }
        }
        return inclusions;
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }    
}
