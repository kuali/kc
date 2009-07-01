/*
 * Copyright 2008 The Kuali Foundation.
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCategoryMap;
import org.kuali.kra.budget.bo.BudgetCategoryMapping;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;

/**
 * This abstract class has methods that are common to all the versions of ED524Budget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class ED524BudgetBaseGenerator extends S2SBaseFormGenerator {
    protected S2SUtilService s2sUtilService;
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
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

    protected BudgetDecimal totalCost = BudgetDecimal.ZERO;
    protected BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
    protected BudgetDecimal totalIndirectCost = BudgetDecimal.ZERO;
    protected BudgetDecimal totalCostSharing = BudgetDecimal.ZERO;
    protected BudgetDecimal indirectCS = BudgetDecimal.ZERO;
    protected BudgetDecimal supplyCost = BudgetDecimal.ZERO;
    protected BudgetDecimal supplyCostCS = BudgetDecimal.ZERO;
    protected BudgetDecimal otherCost = BudgetDecimal.ZERO;
    protected BudgetDecimal otherCostCS = BudgetDecimal.ZERO;
    protected BudgetDecimal categoryCostFringe = BudgetDecimal.ZERO;
    protected BudgetDecimal categoryCostCSFringe = BudgetDecimal.ZERO;
    protected BudgetDecimal equipmentCost = BudgetDecimal.ZERO;
    protected BudgetDecimal equipmentCostCS = BudgetDecimal.ZERO;
    protected BudgetDecimal contractualCost = BudgetDecimal.ZERO;
    protected BudgetDecimal contractualCostCS = BudgetDecimal.ZERO;
    protected BudgetDecimal travelCost = BudgetDecimal.ZERO;
    protected BudgetDecimal travelCostCS = BudgetDecimal.ZERO;
    protected BudgetDecimal trainingCost = BudgetDecimal.ZERO;
    protected BudgetDecimal trainingCostCS = BudgetDecimal.ZERO;
    protected BudgetDecimal personnelCost = BudgetDecimal.ZERO;
    protected BudgetDecimal personnelCostCS = BudgetDecimal.ZERO;

    protected static final String DHHS_AGREEMENT = "DHHS_AGREEMENT";
    protected static final String DHHS_DEFAULT_VALUE = "0";
    protected static final String AGENCY_VALUE = "DHHS";
    protected static final String RESTIRCTED_QUESTION = " ";
    protected static final String DEFAULT_LEGAL_NAME = "NONE";

    List<BudgetCategoryMap> budgetCategoryMapListWithoutFilter;

    /**
     * 
     * Constructs a ED524BudgetBaseGenerator.java.
     */
    public ED524BudgetBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
        s2sBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);

        budgetCategoryMapListWithoutFilter = s2sBudgetCalculatorService.getBudgetCategoryMapList(new ArrayList<String>(),
                new ArrayList<String>());
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
        indirectCS = BudgetDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetLineItemCalculatedAmount budgetLineItemCalAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                if (RATE_CLASS_TYPE_OTHER.equals(budgetLineItemCalAmount.getRateClass().getRateClassType())) {
                    indirectCS = indirectCS.add(budgetLineItemCalAmount.getCalculatedCostSharing());
                }
            }
        }
    }

    protected void getSuppliesCosts(BudgetPeriod budgetPeriod) {
        supplyCost = BudgetDecimal.ZERO;
        supplyCostCS = BudgetDecimal.ZERO;
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
        categoryCostFringe = BudgetDecimal.ZERO;
        categoryCostCSFringe = BudgetDecimal.ZERO;
        otherCost = BudgetDecimal.ZERO;
        otherCostCS = BudgetDecimal.ZERO;
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
        equipmentCost = BudgetDecimal.ZERO;
        equipmentCostCS = BudgetDecimal.ZERO;
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
        contractualCost = BudgetDecimal.ZERO;
        contractualCostCS = BudgetDecimal.ZERO;
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
        travelCost = BudgetDecimal.ZERO;
        travelCostCS = BudgetDecimal.ZERO;
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
        trainingCost = BudgetDecimal.ZERO;
        trainingCostCS = BudgetDecimal.ZERO;
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
        personnelCost = BudgetDecimal.ZERO;
        personnelCostCS = BudgetDecimal.ZERO;
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
        String dhhs = s2sUtilService.getParameterValue(DHHS_AGREEMENT);
        if (dhhs != null) {
            if (dhhs.length() > 0) {
                agencyName = AGENCY_VALUE;
            }
        }
        return agencyName;
    }

}
