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
package org.kuali.coeus.common.budget.impl.calculator;

import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.calculator.*;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.AbstractBudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.RateClassBaseExclusion;
import org.kuali.coeus.common.budget.framework.rate.RateClassBaseInclusion;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.query.operator.And;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.query.operator.NotEquals;
import org.kuali.coeus.common.budget.framework.query.operator.Operator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * This class is responsible for calculating the base unit <code>BreakUpInterval</code> for the budget
 */
@Component("breakupIntervalService")
public class BreakupIntervalServiceImpl implements BreakupIntervalService {
    public static final String RATE_CLASS_CODE = "rateClassCode";
    public static final String RATE_TYPE_CODE = "rateTypeCode";
    @Autowired
    @Qualifier("businessObjectService")
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
    @Override
    public void calculate(BreakUpInterval breakupInterval) {
        initializeRateClassBases();
        QueryList<RateAndCost> rateAndCosts = breakupInterval.getRateAndCosts();
        List<RateClassBaseInclusion> rateClassInclusions = (List<RateClassBaseInclusion>) getBusinessObjectService().findAll(RateClassBaseInclusion.class);
            for (RateClassBaseInclusion rateClassBaseInclusion : rateClassInclusions) {
                if (canApplyRate(rateClassBaseInclusion, rateAndCosts)) {
                    findAndApplyRateRecursively(breakupInterval, rateClassBaseInclusion, rateAndCosts);
                }
            }
            breakupInterval.setUnderRecovery(rateAndCosts.sumObjects("underRecovery"));
    }

    private boolean canApplyRate(RateClassBaseInclusion rateClassBaseInclusion, QueryList<RateAndCost> rateAndCosts) {
        Equals eqRateClassCode = new Equals(RATE_CLASS_CODE,rateClassBaseInclusion.getRateClassCode());
        Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE,rateClassBaseInclusion.getRateTypeCode());
        QueryList<RateAndCost> applicableRateList = rateAndCosts.filter(rateClassBaseInclusion.getRateTypeCode()==null?
                                                                            eqRateClassCode:new And(eqRateClassCode,eqRateTypeCode));
        return !applicableRateList.isEmpty();
    }

    protected void findAndApplyRateRecursively(BreakUpInterval breakupInterval,RateClassBaseInclusion rateClassBaseInclusion,
                                               QueryList<RateAndCost> rateAndCosts) {
        RateClassInclusionTree rateClassInclusionTree = getRateClassInclusionTree(rateClassBaseInclusion.getRateClassBaseInclusionId());
        List<Node<RateClassBaseInclusion>> rateClassInclusions = rateClassInclusionTree.toList();
        int numberOfNodes = rateClassInclusions.size();
        ScaleTwoDecimal baseCost = breakupInterval.getApplicableAmt();
        ScaleTwoDecimal baseCostSharing = breakupInterval.getApplicableAmtCostSharing();
        ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal calculatedCostSharing = ScaleTwoDecimal.ZERO;
        for(int i = numberOfNodes - 1; i >= 0; i--) {
            Node<RateClassBaseInclusion> rateClassInclusionNode = rateClassInclusions.get(i);
            RateClassBaseInclusion rcbi = rateClassInclusionNode.getData();
            QueryList<RateAndCost> applicableRateList = filterApplicableRates(rateAndCosts,rcbi);
            if(!applicableRateList.isEmpty()) {
                for (RateAndCost rateAndCost : applicableRateList) {
                    if(!rateAndCost.getRateClassType().equals(RateClassType.OVERHEAD.getRateClassType()) &&
                            !rateAndCost.isApplyRateFlag()) continue;
                    ScaleTwoDecimal rate = getAppliedRate(breakupInterval,rateAndCost);
                    calculatedCost = baseCost.percentage(rate);
                    calculatedCostSharing = baseCostSharing.percentage(rate);
                    if(i == 0) {
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
            } else {
                baseCost = ScaleTwoDecimal.ZERO;
                baseCostSharing = ScaleTwoDecimal.ZERO;
            }
        }
    }

    protected ScaleTwoDecimal getAppliedRate(BreakUpInterval breakupInterval, RateAndCost rateAndCost) {
        Equals eqRateClassCode = new Equals(RATE_CLASS_CODE,rateAndCost.getRateClassCode());
        Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE,rateAndCost.getRateTypeCode());
        And rcAndRt = new And(eqRateClassCode,eqRateTypeCode);
        QueryList<BudgetRate> breakupIntervalRates = breakupInterval.getBudgetProposalRates();
        QueryList<BudgetLaRate> breakupIntervalLaRates = breakupInterval.getBudgetProposalLaRates();
        @SuppressWarnings("rawtypes")
        QueryList applicableRates = breakupIntervalRates.filter(rcAndRt);
        if(applicableRates.isEmpty()){
            applicableRates = breakupIntervalLaRates.filter(rcAndRt);
        }
        return applicableRates.isEmpty()? ScaleTwoDecimal.ZERO:((AbstractBudgetRate)applicableRates.get(0)).getApplicableRate();
    }

    public void calculateUnderRecovery(BreakUpInterval breakupInterval, RateAndCost rateAndCost) {
        ScaleTwoDecimal underRecoveryRate;
        ScaleTwoDecimal instituteRate;
        if (breakupInterval.getURRatesBean() != null) {
            instituteRate = breakupInterval.getURRatesBean().getApplicableRate();
        } else {
            instituteRate = filterInstituteRate(breakupInterval,rateAndCost);
        }
        if (!rateAndCost.isApplyRateFlag()) {
            underRecoveryRate = instituteRate;
            rateAndCost.setCalculatedCost(ScaleTwoDecimal.ZERO);
            rateAndCost.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
        } else {
            underRecoveryRate = instituteRate.subtract(getAppliedRate(breakupInterval, rateAndCost));
        }
        ScaleTwoDecimal underRecovery = rateAndCost.getBaseAmount().percentage(underRecoveryRate);
        ScaleTwoDecimal underRecoveryCostShare = rateAndCost.getBaseCostSharingAmount().percentage(underRecoveryRate);
        rateAndCost.setUnderRecovery(underRecovery.add(underRecoveryCostShare));
    }

    protected ScaleTwoDecimal filterInstituteRate(BreakUpInterval breakupInterval,RateAndCost rateAndCost) {
        Equals eqRateClassCode = new Equals(RATE_CLASS_CODE,rateAndCost.getRateClassCode());
        Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE,rateAndCost.getRateTypeCode());
        And rcAndRt = new And(eqRateClassCode,eqRateTypeCode);
        QueryList<BudgetRate> breakupIntervalRates = breakupInterval.getBudgetProposalRates();
        QueryList<BudgetRate> applicableRates = breakupIntervalRates.filter(rcAndRt);
        return applicableRates.isEmpty()? ScaleTwoDecimal.ZERO:applicableRates.get(0).getInstituteRate();
    }

    protected QueryList<RateAndCost> filterApplicableRates(QueryList<RateAndCost> rateAndCosts, RateClassBaseInclusion rcbi) {
        Equals eqRateClassCode = new Equals(RATE_CLASS_CODE,rcbi.getRateClassCode());
        Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE,rcbi.getRateTypeCode());
        Operator rcAndRt = rcbi.getRateTypeCode()==null?eqRateClassCode:new And(eqRateClassCode,eqRateTypeCode);
        QueryList<RateAndCost> applicableRateList = rateAndCosts.filter(rcAndRt);
        List<RateClassBaseInclusion> rateClassInclusions = (List<RateClassBaseInclusion>) getBusinessObjectService().findAll(RateClassBaseInclusion.class);
        for (RateClassBaseInclusion rateClassInclusion : rateClassInclusions) {
            if(rcbi.getRateClassCode().equals(rateClassInclusion.getRateClassCode()) && rcbi.getRateTypeCode()==null && rateClassInclusion.getRateTypeCode()!=null){
                NotEquals neRateTypeCode = new NotEquals(RATE_TYPE_CODE,rateClassInclusion.getRateTypeCode());
                applicableRateList = applicableRateList.filter(neRateTypeCode);
            }
        }
        return applicableRateList;
    }

    /**
     * Non-generic subclass of Tree&lt;Task&gt;
     */
    public class RateClassInclusionTree extends Tree<RateClassBaseInclusion> {
        public RateClassInclusionTree() {
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
                getBusinessObjectService().findBySinglePrimaryKey(RateClassBaseInclusion.class, id));
        getRecursive(rootElement, taskTree);
        taskTree.setRootElement(rootElement);
        return taskTree;
    }

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
        Equals eqRateClassCode = new Equals(RATE_CLASS_CODE,rateClassCode);
        Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE,rateTypeCode);
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
        Equals eqRateClassCode = new Equals(RATE_CLASS_CODE,rateClassCode);
        Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE,rateTypeCode);
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
