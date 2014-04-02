/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.core.BudgetCategoryMapping;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.proposaldevelopment.budget.service.ProposalBudgetService;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class has methods that are common to all the versions of ED524Budget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class ED524BudgetBaseGenerator extends S2SBaseFormGenerator {
    protected S2SUtilService s2sUtilService;
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
    protected ParameterService parameterService;
    protected ProposalBudgetService proposalBudgetService;
    protected static final String INDIRECT_COST_RATE_AGREEMENT_NONE = "NONE";
    protected static final String APPROVING_FEDERAL_AGENCY_OTHER = "Other";
    protected static final String APPROVING_FEDERAL_AGENCY_ED = "ED";
    // Rate class types
    protected static final String RATE_CLASS_TYPE_EMPLOYEE_BENEFITS = "E";
    protected static final String RATE_CLASS_TYPE_VACATION = "V";
    protected static final String RATE_CLASS_TYPE_OTHER = "O";
    // Target Category Code
    protected static final String TARGET_CATEGORY_CODE_SUBCONTRACT = "04";
    protected static final String TARGET_CATEGORY_CODE_PURCHASED_EQUIPMENT = "42";
    protected static final String TARGET_CATEGORY_CODE_MATERIOALS_AND_SUPPLIES = "43";
    protected static final String TARGET_CATEGORY_CODE_TRAVEL = "73";
    protected static final String TARGET_CATEGORY_CODE_FOREIGN_TRAVEL = "74";
    protected static final String TARGET_CATEGORY_CODE_PARTICIPANT_STIPENDS = "75";
    protected static final String TARGET_CATEGORY_TYPE_CODE_PERSONNEL = "P";
    // Rate type code
    protected static final int RATE_TYPE_ADMINISTRATIVE_SALARIES = 2;
    protected static final int RATE_TYPE_SUPPORT_STAFF_SALARIES = 3;

    protected ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal totalDirectCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal totalIndirectCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal totalCostSharing = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal indirectCS = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal supplyCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal supplyCostCS = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal otherCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal otherCostCS = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal categoryCostFringe = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal categoryCostCSFringe = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal equipmentCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal equipmentCostCS = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal contractualCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal contractualCostCS = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal travelCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal travelCostCS = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal trainingCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal trainingCostCS = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal personnelCost = ScaleTwoDecimal.ZERO;
    protected ScaleTwoDecimal personnelCostCS = ScaleTwoDecimal.ZERO;

    protected static final String DHHS_AGREEMENT = "DHHS_AGREEMENT";
    protected static final String DHHS_DEFAULT_VALUE = "0";
    protected static final String AGENCY_VALUE = "DHHS";
    protected static final String RESTIRCTED_QUESTION = " ";
    protected static final String DEFAULT_LEGAL_NAME = "NONE";

    List<BudgetCategoryMap> budgetCategoryMapListWithoutFilter;


    public ED524BudgetBaseGenerator() {
        s2sUtilService = KcServiceLocator.getService(S2SUtilService.class);
        s2sBudgetCalculatorService = KcServiceLocator.getService(S2SBudgetCalculatorService.class);
        parameterService = KcServiceLocator.getService(ParameterService.class);
        budgetCategoryMapListWithoutFilter = s2sBudgetCalculatorService.getBudgetCategoryMapList(new ArrayList<String>(),
                new ArrayList<String>());
        proposalBudgetService = KcServiceLocator.getService(ProposalBudgetService.class);
    }

    protected void getTotalCosts(BudgetPeriod budgetPeriod) {
        totalCost = budgetPeriod.getTotalCost();
        totalDirectCost = budgetPeriod.getTotalDirectCost();
        totalIndirectCost = budgetPeriod.getTotalIndirectCost();
        totalCostSharing = budgetPeriod.getCostSharingAmount();

        getTrainingCosts(budgetPeriod);
        totalDirectCost = totalDirectCost.subtract(trainingCost);
    }

    protected void getIndirectCosts(BudgetPeriod budgetPeriod) {
        indirectCS = ScaleTwoDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetLineItemCalculatedAmount budgetLineItemCalAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                budgetLineItemCalAmount.refreshNonUpdateableReferences();
                if (budgetLineItemCalAmount.getRateClass()!=null && 
                            RATE_CLASS_TYPE_OTHER.equals(budgetLineItemCalAmount.getRateClass().getRateClassType())) {
                    indirectCS = indirectCS.add(budgetLineItemCalAmount.getCalculatedCostSharing());
                }
            }
        }
    }

    protected void getSuppliesCosts(BudgetPeriod budgetPeriod) {
        supplyCost = ScaleTwoDecimal.ZERO;
        supplyCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMap categoryMap : budgetCategoryMapListWithoutFilter) {
            if (TARGET_CATEGORY_CODE_MATERIOALS_AND_SUPPLIES.equals(categoryMap.getTargetCategoryCode())) {
                for (BudgetCategoryMapping categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategoryCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            supplyCost = supplyCost.add(lineItem.getLineItemCost());
                            supplyCostCS = supplyCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getOtherCosts(BudgetPeriod budgetPeriod) {
        categoryCostFringe = ScaleTwoDecimal.ZERO;
        categoryCostCSFringe = ScaleTwoDecimal.ZERO;
        otherCost = ScaleTwoDecimal.ZERO;
        otherCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersonnelDetails
                        .getBudgetPersonnelCalculatedAmounts()) {
                    budgetPersonnelCalculatedAmount.refreshReferenceObject("rateClass");
                    if ((budgetPersonnelCalculatedAmount.getRateClass().getRateClassType()
                            .equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS) && Integer.parseInt(budgetPersonnelCalculatedAmount
                            .getRateTypeCode()) != RATE_TYPE_SUPPORT_STAFF_SALARIES)
                            || ((budgetPersonnelCalculatedAmount.getRateClass().getRateClassType().equals(RATE_CLASS_TYPE_VACATION) && Integer
                                    .parseInt(budgetPersonnelCalculatedAmount.getRateTypeCode()) != RATE_TYPE_ADMINISTRATIVE_SALARIES))) {

                        categoryCostFringe = categoryCostFringe.add(budgetPersonnelCalculatedAmount.getCalculatedCost());
                        categoryCostCSFringe = categoryCostCSFringe.add(budgetPersonnelCalculatedAmount.getCalculatedCostSharing());
                    }
                }
            }
        }


        List<String> filterTargetCategoryCodes = new ArrayList<String>();
        filterTargetCategoryCodes.add(TARGET_CATEGORY_CODE_SUBCONTRACT);
        filterTargetCategoryCodes.add(TARGET_CATEGORY_CODE_PURCHASED_EQUIPMENT);
        filterTargetCategoryCodes.add(TARGET_CATEGORY_CODE_MATERIOALS_AND_SUPPLIES);
        filterTargetCategoryCodes.add(TARGET_CATEGORY_CODE_TRAVEL);
        filterTargetCategoryCodes.add(TARGET_CATEGORY_CODE_FOREIGN_TRAVEL);
        filterTargetCategoryCodes.add(TARGET_CATEGORY_CODE_PARTICIPANT_STIPENDS);

        List<BudgetCategoryMap> budgetCategoryMapList = s2sBudgetCalculatorService.getBudgetCategoryMapList(
                filterTargetCategoryCodes, new ArrayList<String>());

        for (BudgetCategoryMap budgetCategoryMap : budgetCategoryMapList) {
            if (budgetCategoryMap.getCategoryType().equals(RATE_CLASS_TYPE_OTHER)) {
                for (BudgetCategoryMapping categoryMapping : budgetCategoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategoryCode().equals(categoryMapping.getBudgetCategoryCode())) {
                            otherCost = otherCost.add(lineItem.getLineItemCost());
                            otherCostCS = otherCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
        for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetLineItemCalculatedAmount budgetLineItemCalAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                budgetLineItemCalAmount.refreshReferenceObject("rateClass");
                if (!budgetLineItemCalAmount.getRateClass().getRateClassType().equals(RATE_CLASS_TYPE_OTHER)) {
                    otherCost = otherCost.add(budgetLineItemCalAmount.getCalculatedCost());
                    otherCostCS = otherCostCS.add(budgetLineItemCalAmount.getCalculatedCostSharing());
                }
            }
        }

        otherCost = otherCost.subtract(categoryCostFringe);
        otherCostCS = otherCostCS.subtract(categoryCostCSFringe);
    }

    protected void getEquipmentCosts(BudgetPeriod budgetPeriod) {
        equipmentCost = ScaleTwoDecimal.ZERO;
        equipmentCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMap categoryMap : budgetCategoryMapListWithoutFilter) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_PURCHASED_EQUIPMENT)) {
                for (BudgetCategoryMapping categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategoryCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            equipmentCost = equipmentCost.add(lineItem.getLineItemCost());
                            equipmentCostCS = equipmentCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getContractualCosts(BudgetPeriod budgetPeriod) {
        contractualCost = ScaleTwoDecimal.ZERO;
        contractualCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMap categoryMap : budgetCategoryMapListWithoutFilter) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_SUBCONTRACT)) {
                for (BudgetCategoryMapping categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategoryCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            contractualCost = contractualCost.add(lineItem.getLineItemCost());
                            contractualCostCS = contractualCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getTravelCosts(BudgetPeriod budgetPeriod) {
        travelCost = ScaleTwoDecimal.ZERO;
        travelCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMap categoryMap : budgetCategoryMapListWithoutFilter) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_TRAVEL)
                    || categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_FOREIGN_TRAVEL)) {
                for (BudgetCategoryMapping categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategoryCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            travelCost = travelCost.add(lineItem.getLineItemCost());
                            travelCostCS = travelCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getTrainingCosts(BudgetPeriod budgetPeriod) {
        trainingCost = ScaleTwoDecimal.ZERO;
        trainingCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMap categoryMap : budgetCategoryMapListWithoutFilter) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_PARTICIPANT_STIPENDS)) {
                for (BudgetCategoryMapping categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategoryCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            trainingCost = trainingCost.add(lineItem.getLineItemCost());
                            trainingCostCS = trainingCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    public void getPersonnelCosts(BudgetPeriod budgetPeriod) {
        personnelCost = ScaleTwoDecimal.ZERO;
        personnelCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMap categoryMap : budgetCategoryMapListWithoutFilter) {
            if (categoryMap.getCategoryType().equals(TARGET_CATEGORY_TYPE_CODE_PERSONNEL)) {
                for (BudgetCategoryMapping categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategoryCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            personnelCost = personnelCost.add(lineItem.getLineItemCost());
                            personnelCostCS = personnelCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected String getAgencyName() {
        String agencyName = "";
        String dhhs = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, DHHS_AGREEMENT);
        if (dhhs != null) {
            if (dhhs.length() > 0) {
                agencyName = AGENCY_VALUE;
            }
        }
        return agencyName;
    }

}
