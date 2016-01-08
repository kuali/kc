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
package org.kuali.coeus.propdev.impl.budget.hierarchy;

import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.ERROR_BUDGET_PERIOD_DURATION_INCONSISTENT;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.ERROR_BUDGET_START_DATE_INCONSISTENT;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.PARAMETER_NAME_DIRECT_COST_ELEMENT;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.PARAMETER_NAME_INDIRECT_COST_ELEMENT;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardAttachment;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardFiles;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyBudgetTypeConstants;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyDao;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyErrorWarningDto;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyException;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcDataObject;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("proposalBudgetHierarchyService")
@Transactional
public class ProposalBudgetHierarchyServiceImpl implements ProposalBudgetHierarchyService {
	
	private static final Log LOG = LogFactory.getLog(ProposalBudgetHierarchyServiceImpl.class);
	
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
	@Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;
	
	@Autowired
	@Qualifier("proposalBudgetService")
	private ProposalBudgetService budgetService;
	
	@Autowired
	@Qualifier("budgetSummaryService")
	private BudgetSummaryService budgetSummaryService;
	
	@Autowired
	@Qualifier("budgetCalculationService")
	private BudgetCalculationService budgetCalculationService;
	
	@Autowired
	@Qualifier("proposalHierarchyDao")
	private ProposalHierarchyDao proposalHierarchyDao;

    @Override
	public void persistProposalHierarchyBudget(DevelopmentProposal hierarchyProposal) {
		dataObjectService.save(getHierarchyBudget(hierarchyProposal));
	}

    @Override
    public void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<BudgetPeriod> oldBudgetPeriods) {
    	synchronizeChildBudget(hierarchyProposal, childProposal, getSyncableBudget(childProposal), oldBudgetPeriods);
    }

    @Override
    public void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, ProposalDevelopmentBudgetExt budget) {
    	synchronizeChildBudget(hierarchyProposal, budget.getBudgetParent(), budget, budget.getBudgetPeriods());
    }

    @Override
    public void synchronizeAllChildBudgets(DevelopmentProposal hierarchyProposal) {
    	List<BudgetPeriod> oldBudgetPeriods = getHierarchyBudget(hierarchyProposal).getBudgetPeriods();
        removeMergeableChildBudgetElements(getHierarchyBudget(hierarchyProposal));
        for (DevelopmentProposal childProposal : proposalHierarchyDao.getHierarchyChildProposals(hierarchyProposal.getProposalNumber())) {
    		ProposalDevelopmentBudgetExt budget = getSyncableBudget(childProposal);
    		synchronizeChildBudget(hierarchyProposal, childProposal, budget, oldBudgetPeriods);
    		dataObjectService.save(budget);
    		dataObjectService.save(childProposal);
    	}
    }
    
    protected void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, ProposalDevelopmentBudgetExt childBudget, 
    		List<BudgetPeriod> oldBudgetPeriods) {
    	String hierarchyBudgetTypeCode = childProposal.getHierarchyBudgetType();
    	String childProposalNumber = childProposal.getProposalNumber();
    	ProposalDevelopmentBudgetExt parentBudget = getHierarchyBudget(hierarchyProposal);
    	
    	removeChildBudgetElements(hierarchyProposal, parentBudget, childProposal.getProposalNumber());
    	
    	synchBudgetPeriods(childBudget,parentBudget);
    	
        boolean isOriginatingChildBudget = StringUtils.equals(childProposal.getProposalNumber(), hierarchyProposal.getHierarchyOriginatingChildProposalNumber());    	
        try {
            if( isOriginatingChildBudget ) {
                parentBudget.setUrRateClassCode( childBudget.getUrRateClassCode() );
                parentBudget.setOhRateClassCode( childBudget.getOhRateClassCode() );
            }
            
            BudgetPerson newPerson;
            Map<Integer, BudgetPerson> personMap = new HashMap<>();
            for (BudgetPerson person : childBudget.getBudgetPersons()) {
                newPerson = (BudgetPerson) deepCopy(person);
                newPerson.setBudget(parentBudget);
                newPerson.setPersonSequenceNumber(parentBudget.getNextValue(
                        Constants.PERSON_SEQUENCE_NUMBER));
                newPerson.setBudgetId(parentBudget.getBudgetId());
                newPerson.setHierarchyProposalNumber(childProposalNumber);
                newPerson.setHierarchyProposal(childProposal);
                newPerson.setVersionNumber(null);
                parentBudget.addBudgetPerson(newPerson);
                personMap.put(person.getPersonSequenceNumber(), newPerson);
            }
            
            BudgetSubAwards newSubAwards;
            Map<Integer, BudgetSubAwards> subAwardMap = new HashMap<>();
            for (BudgetSubAwards childSubAwards : childBudget.getBudgetSubAwards()) {
               	//pre-fetch all lob objects from the subawards as JPA/Eclipselink doesn't do this for lazy loaded lobs
            	//during the deep-copy operation below
            	childSubAwards.getSubAwardXmlFileData();
            	for (BudgetSubAwardAttachment origAttachment : childSubAwards.getBudgetSubAwardAttachments()) {
            		origAttachment.getData();
            	}
            	for (BudgetSubAwardFiles budgetSubAwardFiles : childSubAwards.getBudgetSubAwardFiles()) {
            		budgetSubAwardFiles.getSubAwardXmlFileData();
            	}
                newSubAwards = (BudgetSubAwards) deepCopy(childSubAwards);
                newSubAwards.setBudget(parentBudget);
                newSubAwards.setBudgetId(parentBudget.getBudgetId());
                newSubAwards.setBudgetVersionNumber(parentBudget.getBudgetVersionNumber());
                newSubAwards.setSubAwardNumber(parentBudget.getNextValue("subAwardNumber") != null ? parentBudget.getNextValue("subAwardNumber") : 1);
                newSubAwards.setVersionNumber(null);
                newSubAwards.setHierarchyProposalNumber(childProposalNumber);
                for (BudgetSubAwardAttachment attachment : newSubAwards.getBudgetSubAwardAttachments()) {
                	attachment.setBudgetSubAward(newSubAwards);
                    attachment.setId(null);
                    attachment.setVersionNumber(null);
                }
                for (BudgetSubAwardFiles files : newSubAwards.getBudgetSubAwardFiles()) {
                	files.setBudgetSubAward(newSubAwards);
                    files.setVersionNumber(null);
                }
                parentBudget.getBudgetSubAwards().add(newSubAwards);
                subAwardMap.put(childSubAwards.getSubAwardNumber(), newSubAwards);
            }
            
            int parentStartPeriod = getCorrespondingParentPeriod(oldBudgetPeriods, childBudget);
            if (parentStartPeriod == -1) {
                throw new ProposalHierarchyException("Cannot find a parent budget period that corresponds to the child period.");
            }

            List<BudgetPeriod> childPeriods = childBudget.getBudgetPeriods();
            BudgetPeriod parentPeriod, childPeriod;
            Long budgetId = parentBudget.getBudgetId();

            for (BudgetCostShare costShare : childBudget.getBudgetCostShares()) {
                if (StringUtils.isNotEmpty(costShare.getSourceAccount())) {
                    final BudgetCostShare newCostShare = (BudgetCostShare) deepCopy(costShare);
                    newCostShare.setBudget(parentBudget);
                    newCostShare.setBudgetId(budgetId);
                    newCostShare.setDocumentComponentId(parentBudget.getNextValue(newCostShare.getDocumentComponentIdKey()));
                    newCostShare.setObjectId(null);
                    newCostShare.setVersionNumber(null);
                    newCostShare.setHierarchyProposalNumber(childProposalNumber);
                    newCostShare.setHiddenInHierarchy(true);
                    parentBudget.add(newCostShare);
                }
            }

            for (BudgetUnrecoveredFandA unrecoveredFandA : childBudget.getBudgetUnrecoveredFandAs()) {
                if (StringUtils.isNotEmpty(unrecoveredFandA.getSourceAccount())) {
                    final Optional<BudgetUnrecoveredFandA> matchedUnrecoveredFandA = parentBudget.getBudgetUnrecoveredFandAs().stream().filter(parentUnrecoveredFandA ->
                        parentUnrecoveredFandA.getFiscalYear().equals(unrecoveredFandA.getFiscalYear()) &&
                                parentUnrecoveredFandA.getApplicableRate().equals(unrecoveredFandA.getApplicableRate()) &&
                                parentUnrecoveredFandA.getOnCampusFlag().equals(unrecoveredFandA.getOnCampusFlag()) &&
                                parentUnrecoveredFandA.getSourceAccount().equals(unrecoveredFandA.getSourceAccount())
                    ).findFirst();

                    if (matchedUnrecoveredFandA.isPresent()) {
                        final BudgetUnrecoveredFandA parentUnrecoveredFandA = matchedUnrecoveredFandA.get();
                        parentUnrecoveredFandA.setAmount(parentUnrecoveredFandA.getAmount().add(unrecoveredFandA.getAmount()));
                    } else {
                        final BudgetUnrecoveredFandA newUnrecoveredFandA = (BudgetUnrecoveredFandA) deepCopy(unrecoveredFandA);
                        newUnrecoveredFandA.setBudget(parentBudget);
                        newUnrecoveredFandA.setBudgetId(budgetId);
                        newUnrecoveredFandA.setDocumentComponentId(parentBudget.getNextValue(newUnrecoveredFandA.getDocumentComponentIdKey()));
                        newUnrecoveredFandA.setObjectId(null);
                        newUnrecoveredFandA.setVersionNumber(null);
                        newUnrecoveredFandA.setHierarchyProposalNumber(childProposalNumber);
                        newUnrecoveredFandA.setHiddenInHierarchy(true);
                        parentBudget.add(newUnrecoveredFandA);
                    }
                }
            }

            for (BudgetPeriod childPeriod1 : childPeriods) {
                childPeriod = childPeriod1;
                parentPeriod = findOrCreateMatchingPeriod(childPeriod, parentBudget);

                Integer budgetPeriod = parentPeriod.getBudgetPeriod();
                BudgetLineItem parentLineItem;
                Integer lineItemNumber;

                if (StringUtils.equals(hierarchyBudgetTypeCode, HierarchyBudgetTypeConstants.SubBudget.code())) {
                    for (BudgetLineItem childLineItem : childPeriod.getBudgetLineItems()) {
                        parentLineItem = (BudgetLineItem) deepCopy(childLineItem);
                        lineItemNumber = parentBudget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER);

                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setHierarchyProposal(childProposal);

                        parentLineItem.setBudgetLineItemId(null);
                        parentLineItem.setBudgetPeriodId(parentPeriod.getBudgetPeriodId());
                        parentLineItem.setBudgetPeriod(parentPeriod.getBudgetPeriod());
                        parentLineItem.setBudgetPeriodBO(parentPeriod);
                        parentLineItem.setBudget(parentBudget);
                        parentLineItem.setBudgetId(parentBudget.getBudgetId());
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setVersionNumber(null);
                        parentLineItem.setObjectId(null);

                        if (parentLineItem.getSubAwardNumber() != null) {
                            BudgetSubAwards subAward = subAwardMap.get(childLineItem.getSubAwardNumber());
                            parentLineItem.setSubAwardNumber(subAward.getSubAwardNumber());
                            parentLineItem.setBudgetSubAward(subAward);
                        }

                        for (BudgetLineItemCalculatedAmount calAmt : parentLineItem.getBudgetLineItemCalculatedAmounts()) {
                            calAmt.setBudgetLineItem(parentLineItem);
                            calAmt.setBudgetLineItemCalculatedAmountId(null);
                            calAmt.setBudgetLineItemId(null);
                            calAmt.setBudgetId(budgetId);

                            calAmt.setBudgetPeriod(budgetPeriod);
                            calAmt.setLineItemNumber(lineItemNumber);
                            calAmt.setVersionNumber(null);
                            calAmt.setObjectId(null);
                        }
                        BudgetPerson budgetPerson;
                        for (BudgetPersonnelDetails details : parentLineItem.getBudgetPersonnelDetailsList()) {
                            budgetPerson = personMap.get(details.getPersonSequenceNumber());
                            details.setBudgetLineItem(parentLineItem);
                            details.setBudgetPerson(budgetPerson);
                            details.setJobCode(budgetPerson.getJobCode());
                            details.setPersonId(budgetPerson.getPersonRolodexTbnId());
                            details.setPersonSequenceNumber(budgetPerson.getPersonSequenceNumber());
                            details.setPersonNumber(parentBudget.getNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));

                            details.setBudgetPersonnelLineItemId(null);
                            details.setBudget(parentBudget);
                            details.setBudgetId(budgetId);
                            details.setBudgetPeriod(budgetPeriod);
                            details.setLineItemNumber(lineItemNumber);
                            details.setVersionNumber(null);
                            details.setObjectId(null);

                            for (BudgetPersonnelCalculatedAmount calAmt : details.getBudgetPersonnelCalculatedAmounts()) {
                                calAmt.setBudgetPersonnelCalculatedAmountId(null);
                                calAmt.setBudgetPersonnelLineItem(details);
                                calAmt.setBudgetId(budgetId);

                                calAmt.setBudgetPeriod(budgetPeriod);
                                calAmt.setLineItemNumber(lineItemNumber);
                                calAmt.setVersionNumber(null);
                                calAmt.setObjectId(null);
                            }
                        }
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                } else { // subproject budget

                    CostElement costElement;
                    String directCostElement = parameterService.getParameterValueAsString(Budget.class, PARAMETER_NAME_DIRECT_COST_ELEMENT);
                    String indirectCostElement = parameterService.getParameterValueAsString(Budget.class, PARAMETER_NAME_INDIRECT_COST_ELEMENT);

                    if (childPeriod.getTotalIndirectCost().isNonZero()) {
                        costElement = dataObjectService.findUnique(CostElement.class, QueryByCriteria.Builder.forAttribute("costElement", indirectCostElement).build());
                        parentLineItem = parentBudget.getNewBudgetLineItem();
                        parentLineItem.setLineItemDescription(childProposalNumber);
                        parentLineItem.setStartDate(parentPeriod.getStartDate());
                        parentLineItem.setEndDate(parentPeriod.getEndDate());
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setHierarchyProposal(childProposal);
                        parentLineItem.setBudget(parentBudget);
                        parentLineItem.setBudgetId(parentBudget.getBudgetId());
                        parentLineItem.setBudgetPeriodId(parentPeriod.getBudgetPeriodId());
                        parentLineItem.setBudgetPeriod(parentPeriod.getBudgetPeriod());
                        parentLineItem.setBudgetPeriodBO(parentPeriod);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setLineItemSequence(lineItemNumber);
                        parentLineItem.setLineItemCost(childPeriod.getTotalIndirectCost());
                        parentLineItem.setIndirectCost(childPeriod.getTotalIndirectCost());
                        parentLineItem.setCostElementBO(costElement);
                        parentLineItem.setCostElement(costElement.getCostElement());
                        parentLineItem.setBudgetCategoryCode(costElement.getBudgetCategoryCode());
                        parentLineItem.setOnOffCampusFlag(costElement.getOnOffCampusFlag());
                        parentLineItem.setApplyInRateFlag(true);
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                    if (childPeriod.getTotalDirectCost().isNonZero()) {
                        costElement = dataObjectService.findUnique(CostElement.class, QueryByCriteria.Builder.forAttribute("costElement", directCostElement).build());
                        parentLineItem = parentBudget.getNewBudgetLineItem();
                        parentLineItem.setLineItemDescription(childProposalNumber);
                        parentLineItem.setStartDate(parentPeriod.getStartDate());
                        parentLineItem.setEndDate(parentPeriod.getEndDate());
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setHierarchyProposal(childProposal);
                        parentLineItem.setBudget(parentBudget);
                        parentLineItem.setBudgetId(parentBudget.getBudgetId());
                        parentLineItem.setBudgetPeriodId(parentPeriod.getBudgetPeriodId());
                        parentLineItem.setBudgetPeriod(parentPeriod.getBudgetPeriod());
                        parentLineItem.setBudgetPeriodBO(parentPeriod);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setLineItemSequence(lineItemNumber);
                        parentLineItem.setLineItemCost(childPeriod.getTotalDirectCost());
                        parentLineItem.setDirectCost(childPeriod.getTotalDirectCost());
                        parentLineItem.setCostElementBO(costElement);
                        parentLineItem.setCostElement(costElement.getCostElement());
                        parentLineItem.setBudgetCategoryCode(costElement.getBudgetCategoryCode());
                        parentLineItem.setOnOffCampusFlag(costElement.getOnOffCampusFlag());
                        parentLineItem.setApplyInRateFlag(true);
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                }
            }
            parentBudget.setStartDate(parentBudget.getBudgetPeriod(0).getStartDate());
            parentBudget.setEndDate(parentBudget.getBudgetPeriod(parentBudget.getBudgetPeriods().size()-1).getEndDate());
        }
        catch (Exception e) {
            LOG.error("Problem copying line items to parent", e);
            throw new ProposalHierarchyException("Problem copying line items to parent", e);
        }

        budgetService.recalculateBudget(parentBudget);
        childProposal.setLastSyncedBudget(childBudget);
        childBudget.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childBudget));
    }
    
    protected void synchBudgetPeriods(ProposalDevelopmentBudgetExt childBudget, ProposalDevelopmentBudgetExt parentBudget) {
         List<BudgetPeriod> periodsToDelete = new ArrayList<>();
         parentBudget.getBudgetPeriods().forEach(parentBudgetPeriod -> {
        	 if (!childBudget.getBudgetPeriods().stream().anyMatch(childBudgetPeriod -> childBudgetPeriod.getStartDate().equals(parentBudgetPeriod.getStartDate()) 
        			 && childBudgetPeriod.getEndDate().equals(parentBudgetPeriod.getEndDate()))) {
        		 periodsToDelete.add(parentBudgetPeriod);
        	 }
         });
         periodsToDelete.forEach(period -> {
        	 if (period.getBudgetModular() != null) {
                 getDataObjectService().delete(period.getBudgetModular());
             }
        	 parentBudget.getBudgetPeriods().remove(period);
         });
    }
    
    protected BudgetPeriod findOrCreateMatchingPeriod(BudgetPeriod childPeriod, Budget parentBudget) {
        int priorPeriodIndex = 0;
        int index = 0;
        for (BudgetPeriod period : parentBudget.getBudgetPeriods()) {
            if (period.getStartDate().equals(childPeriod.getStartDate()) && period.getEndDate().equals(childPeriod.getEndDate())) {
                return period;
            } else if (period.getEndDate().before(childPeriod.getStartDate())) {
                priorPeriodIndex = index;
            }
            index++;
        }
        //otherwise add it after most recent budget period which should be valid based on previous validations.
        BudgetPeriod result = new BudgetPeriod();
        result.setStartDate(childPeriod.getStartDate());
        result.setEndDate(childPeriod.getEndDate());
        result.setBudgetPeriod(parentBudget.getBudgetPeriods().get(priorPeriodIndex).getBudgetPeriod()+1);
        result.setBudget(parentBudget);

        getBudgetSummaryService().addBudgetPeriod(parentBudget, result);
        return result;
    }

    @Override
    public void removeMergeableChildBudgetElements(ProposalDevelopmentBudgetExt parentBudget) {
        QueryByCriteria query = QueryByCriteria.Builder.fromPredicates(PredicateFactory.isNotNull("hierarchyProposalNumber"), PredicateFactory.equal("budgetId", parentBudget.getBudgetId()));
        dataObjectService.deleteMatching(BudgetUnrecoveredFandA.class, query);

        parentBudget.setBudgetUnrecoveredFandAs(parentBudget.getBudgetUnrecoveredFandAs().stream()
                .filter(fAndA -> fAndA.getHierarchyProposalNumber() == null)
                .collect(Collectors.toList()));
    }

    @Override
    public void removeChildBudgetElements(DevelopmentProposal parentProposal, ProposalDevelopmentBudgetExt parentBudget, String childProposalNumber) {
        QueryByCriteria query = QueryByCriteria.Builder.fromPredicates(PredicateFactory.equal("hierarchyProposalNumber", childProposalNumber), PredicateFactory.equal("budgetId", parentBudget.getBudgetId()));
        dataObjectService.deleteMatching(BudgetProjectIncome.class, query);
        dataObjectService.deleteMatching(BudgetCostShare.class, query);
        
        for (Iterator<BudgetSubAwards> iter = parentBudget.getBudgetSubAwards().iterator(); iter.hasNext();) {
        	BudgetSubAwards subAward = iter.next();
        	if (StringUtils.equals(childProposalNumber, subAward.getHierarchyProposalNumber())) {
        		List<BudgetLineItem> lineItems = getDataObjectService().findMatching(BudgetLineItem.class, QueryByCriteria.Builder.fromPredicates(
    				PredicateFactory.equal("budgetId", subAward.getBudgetId()), 
    				PredicateFactory.equal("subAwardNumber", subAward.getSubAwardNumber()))).getResults();
	    		for (BudgetPeriod period : parentBudget.getBudgetPeriods()) {
	    			period.getBudgetLineItems().removeAll(lineItems);
	    		}
        		iter.remove();
        	}
        }

        for (Iterator<BudgetPerson> iter = parentBudget.getBudgetPersons().iterator(); iter.hasNext(); ) {
        	BudgetPerson person = iter.next();
        	if (StringUtils.equals(childProposalNumber, person.getHierarchyProposalNumber())) {
        		iter.remove();
        	}
        }
        List<BudgetPeriod> periodsToDelete = new ArrayList<>();
        for (int i = parentBudget.getBudgetPeriods().size()-1; i >= 0; i--) {
            boolean deletePeriods = false;
        	BudgetPeriod period = parentBudget.getBudgetPeriod(i);
	        for (Iterator<BudgetLineItem> lineItemIter = period.getBudgetLineItems().iterator(); lineItemIter.hasNext();) {
	        	BudgetLineItem lineItem = lineItemIter.next();
	        	if (StringUtils.equals(childProposalNumber, lineItem.getHierarchyProposalNumber())) {
                    deletePeriods = true;
	        		lineItemIter.remove();
	        	}
	        }
	        if (deletePeriods) {
	        	if (parentBudget.getBudgetPeriods().get(i).getBudgetLineItems().size() == 0 && i > 0) {
	        		periodsToDelete.add(parentBudget.getBudgetPeriods().get(i));
	        	}
	        }
        }
        for (BudgetPeriod period : periodsToDelete) {
            if (period.getBudgetModular() != null) {
                getDataObjectService().delete(period.getBudgetModular());
            }
        	parentBudget.getBudgetPeriods().remove(period);
        }

        parentBudget.setEndDate(parentBudget.getBudgetPeriods().get(parentBudget.getBudgetPeriods().size()-1).getEndDate());       
    }

    @Override
    public void initializeBudget (DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) throws ProposalHierarchyException {
    	budgetService.addBudgetVersion(hierarchyProposal.getProposalDocument(), "Hierarchy Budget", null);
    	
    	ProposalDevelopmentBudgetExt parentBudget = getHierarchyBudget(hierarchyProposal);
    	
        Budget childBudget = getSyncableBudget(childProposal);
        BudgetPeriod parentPeriod, childPeriod;
        for (int i=0; i < childBudget.getBudgetPeriods().size(); i++) {
            parentPeriod = parentBudget.getBudgetPeriod(i);
            childPeriod = childBudget.getBudgetPeriod(i);
            parentPeriod.setStartDate(childPeriod.getStartDate());
            parentPeriod.setEndDate(childPeriod.getEndDate());
            parentPeriod.setBudgetPeriod(childPeriod.getBudgetPeriod());
        }
        
        parentBudget.setCostSharingAmount(new ScaleTwoDecimal(0));
        parentBudget.setTotalCost(new ScaleTwoDecimal(0));
        parentBudget.setTotalDirectCost(new ScaleTwoDecimal(0));
        parentBudget.setTotalIndirectCost(new ScaleTwoDecimal(0));
        parentBudget.setUnderrecoveryAmount(new ScaleTwoDecimal(0));
        
        parentBudget.setOhRateClassCode(childBudget.getOhRateClassCode());
        parentBudget.setOhRateTypeCode(childBudget.getOhRateTypeCode());
        parentBudget.setUrRateClassCode(childBudget.getUrRateClassCode());
        dataObjectService.save(parentBudget);
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal,
            DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException {
        List<ProposalHierarchyErrorWarningDto> retval = new ArrayList<>();
        ProposalDevelopmentBudgetExt parentBudget = getHierarchyBudget(hierarchyProposal);
    	if (parentBudget != null) {
            Budget childBudget = getSyncableBudget(childProposal);

            // check that child budget starts on one of the budget period starts
            int correspondingStart = getCorrespondingParentPeriod(parentBudget.getBudgetPeriods(), childBudget);
            if (correspondingStart == -1) {
                retval.add(new ProposalHierarchyErrorWarningDto(ERROR_BUDGET_START_DATE_INCONSISTENT, Boolean.TRUE, childProposal.getProposalNumber()));
            }
            // check that child budget periods map to parent periods
            else {
                List<BudgetPeriod> parentPeriods = parentBudget.getBudgetPeriods();
                List<BudgetPeriod> childPeriods = childBudget.getBudgetPeriods();
                BudgetPeriod parentPeriod, childPeriod;
                int i;
                int j;
                for (i = correspondingStart, j = 0; i < parentPeriods.size() && j < childPeriods.size(); i++, j++) {
                    parentPeriod = parentPeriods.get(i);
                    childPeriod = childPeriods.get(j);
                    if (!parentPeriod.getStartDate().equals(childPeriod.getStartDate())
                            || !parentPeriod.getEndDate().equals(childPeriod.getEndDate())) {
                        retval.add(new ProposalHierarchyErrorWarningDto(ERROR_BUDGET_PERIOD_DURATION_INCONSISTENT, Boolean.TRUE, childProposal.getProposalNumber()));
                        break;
                    }
                }
                if (retval.isEmpty()
                        && !allowEndDateChange
                        && (j < childPeriods.size()
                        || childProposal.getRequestedEndDateInitial().after(hierarchyProposal.getRequestedEndDateInitial()))) {
                    retval.add(new ProposalHierarchyErrorWarningDto(QUESTION_EXTEND_PROJECT_DATE_CONFIRM, Boolean.TRUE, childProposal.getProposalNumber()));
                }
            }
        }
        return retval;
    }    

    @Override
    public ProposalDevelopmentBudgetExt getHierarchyBudget(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
    	if (!hierarchyProposal.getBudgets().isEmpty()) {
    		return hierarchyProposal.getBudgets().get(0);
    	} else {
    		return null;
    	}
    }

    @Override
    public ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException {
    	if (childProposal.getFinalBudget() == null) {
    		return childProposal.getLatestBudget();
    	} else {
    		return childProposal.getFinalBudget();
    	}
    }
    
    protected int getCorrespondingParentPeriod(List<BudgetPeriod> oldBudgetPeriods, Budget childBudget) {
        int correspondingStart = -1;
 
        // using start date of first period as start date and end date of last period
        // as end because budget start and end are not particularly reliable
        Date childStart = childBudget.getBudgetPeriod(0).getStartDate();
        Date parentStart = oldBudgetPeriods.get(0).getStartDate();
        Date parentEnd = oldBudgetPeriods.get(oldBudgetPeriods.size()-1).getEndDate();
        // check that child budget starts somewhere during parent budget
        if (childStart.compareTo(parentStart) >= 0
                && childStart.compareTo(parentEnd) < 0) {
            // check that child budget starts on one of the budget period starts

            for (int i=0; i<oldBudgetPeriods.size(); i++) {
                if (childStart.equals(oldBudgetPeriods.get(i).getStartDate())) {
                    correspondingStart = i;
                    break;
                }
            }
        }
        return correspondingStart;
    }
    
    /**
     * Creates a hash of the data pertinent to a hierarchy for comparison during hierarchy syncing. 
     */
    @Override
    public int computeHierarchyHashCode(Budget budget) {
        int prime = 31;
        int result = 1;
        budgetCalculationService.calculateBudget(budget);
        budgetCalculationService.calculateBudgetSummaryTotals(budget);
        result = prime * result + budget.getBudgetSummaryTotals().hashCode();
        return result;
    }

    @Override
    public List<ProposalDevelopmentBudgetExt> getHierarchyBudgets(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        return hierarchyProposal.getProposalDocument().getDevelopmentProposal().getBudgets().stream().collect(Collectors.toList());
    }
    
    protected KcDataObject deepCopy(KcDataObject oldObject) {
        return getDataObjectService().copyInstance(oldObject, CopyOption.RESET_OBJECT_ID, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER);
    }

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	public BudgetSummaryService getBudgetSummaryService() {
		return budgetSummaryService;
	}

	public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
		this.budgetSummaryService = budgetSummaryService;
	}

	public ProposalBudgetService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(ProposalBudgetService budgetService) {
		this.budgetService = budgetService;
	} 
}
