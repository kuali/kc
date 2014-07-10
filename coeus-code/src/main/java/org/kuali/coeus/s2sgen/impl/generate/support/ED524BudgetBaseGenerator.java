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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemCalculatedAmountContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelCalculatedAmountContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.s2sgen.impl.budget.S2SCommonBudgetService;
import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMappingContract;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.budget.S2SBudgetCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class has methods that are common to all the versions of ED524Budget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class ED524BudgetBaseGenerator extends S2SBaseFormGenerator {

    @Autowired
    @Qualifier("s2SDateTimeService")
    protected S2SDateTimeService s2SDateTimeService;

    @Autowired
    @Qualifier("s2SBudgetCalculatorService")
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    protected S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("s2SCommonBudgetService")
    protected S2SCommonBudgetService s2SCommonBudgetService;

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

    protected static final String AGENCY_VALUE = "DHHS";
    protected static final String RESTIRCTED_QUESTION = " ";
    protected static final String DEFAULT_LEGAL_NAME = "NONE";

    private List<? extends BudgetCategoryMapContract> budgetCategoryMapListWithoutFilter;

    public List<? extends BudgetCategoryMapContract> getBudgetCategoryMapListWithoutFilter() {
        if (budgetCategoryMapListWithoutFilter == null) {
            budgetCategoryMapListWithoutFilter = s2sBudgetCalculatorService.getBudgetCategoryMapList(new ArrayList<String>(),
                    new ArrayList<String>());
        }

        return budgetCategoryMapListWithoutFilter;
    }

    protected void getTotalCosts(BudgetPeriodContract budgetPeriod) {
        totalCost = budgetPeriod.getTotalCost();
        totalDirectCost = budgetPeriod.getTotalDirectCost();
        totalIndirectCost = budgetPeriod.getTotalIndirectCost();
        totalCostSharing = budgetPeriod.getCostSharingAmount();

        getTrainingCosts(budgetPeriod);
        totalDirectCost = totalDirectCost.subtract(trainingCost);
    }

    protected void getIndirectCosts(BudgetPeriodContract budgetPeriod) {
        indirectCS = ScaleTwoDecimal.ZERO;
        for (BudgetLineItemContract budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetLineItemCalculatedAmountContract budgetLineItemCalAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                if (budgetLineItemCalAmount.getRateClass()!=null &&
                            RATE_CLASS_TYPE_OTHER.equals(budgetLineItemCalAmount.getRateClass().getRateClassType().getCode())) {
                    indirectCS = indirectCS.add(budgetLineItemCalAmount.getCalculatedCostSharing());
                }
            }
        }
    }

    protected void getSuppliesCosts(BudgetPeriodContract budgetPeriod) {
        supplyCost = ScaleTwoDecimal.ZERO;
        supplyCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMapContract categoryMap : getBudgetCategoryMapListWithoutFilter()) {
            if (TARGET_CATEGORY_CODE_MATERIOALS_AND_SUPPLIES.equals(categoryMap.getTargetCategoryCode())) {
                for (BudgetCategoryMappingContract categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategory().getCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            supplyCost = supplyCost.add(lineItem.getLineItemCost());
                            supplyCostCS = supplyCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getOtherCosts(BudgetPeriodContract budgetPeriod) {
        categoryCostFringe = ScaleTwoDecimal.ZERO;
        categoryCostCSFringe = ScaleTwoDecimal.ZERO;
        otherCost = ScaleTwoDecimal.ZERO;
        otherCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetLineItemContract budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetPersonnelDetailsContract budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                for (BudgetPersonnelCalculatedAmountContract budgetPersonnelCalculatedAmount : budgetPersonnelDetails
                        .getBudgetPersonnelCalculatedAmounts()) {
                    if ((budgetPersonnelCalculatedAmount.getRateClass().getRateClassType().getCode()
                            .equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS) && Integer.parseInt(budgetPersonnelCalculatedAmount
                            .getRateTypeCode()) != RATE_TYPE_SUPPORT_STAFF_SALARIES)
                            || ((budgetPersonnelCalculatedAmount.getRateClass().getRateClassType().getCode().equals(RATE_CLASS_TYPE_VACATION) && Integer
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

        List<? extends BudgetCategoryMapContract> budgetCategoryMapList = s2sBudgetCalculatorService.getBudgetCategoryMapList(
                filterTargetCategoryCodes, new ArrayList<String>());

        for (BudgetCategoryMapContract budgetCategoryMap : budgetCategoryMapList) {
            if (budgetCategoryMap.getCategoryType().equals(RATE_CLASS_TYPE_OTHER)) {
                for (BudgetCategoryMappingContract categoryMapping : budgetCategoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategory().getCode().equals(categoryMapping.getBudgetCategoryCode())) {
                            otherCost = otherCost.add(lineItem.getLineItemCost());
                            otherCostCS = otherCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
        for (BudgetLineItemContract budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetLineItemCalculatedAmountContract budgetLineItemCalAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                if (!budgetLineItemCalAmount.getRateClass().getRateClassType().getCode().equals(RATE_CLASS_TYPE_OTHER)) {
                    otherCost = otherCost.add(budgetLineItemCalAmount.getCalculatedCost());
                    otherCostCS = otherCostCS.add(budgetLineItemCalAmount.getCalculatedCostSharing());
                }
            }
        }

        otherCost = otherCost.subtract(categoryCostFringe);
        otherCostCS = otherCostCS.subtract(categoryCostCSFringe);
    }

    protected void getEquipmentCosts(BudgetPeriodContract budgetPeriod) {
        equipmentCost = ScaleTwoDecimal.ZERO;
        equipmentCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMapContract categoryMap : getBudgetCategoryMapListWithoutFilter()) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_PURCHASED_EQUIPMENT)) {
                for (BudgetCategoryMappingContract categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategory().getCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            equipmentCost = equipmentCost.add(lineItem.getLineItemCost());
                            equipmentCostCS = equipmentCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getContractualCosts(BudgetPeriodContract budgetPeriod) {
        contractualCost = ScaleTwoDecimal.ZERO;
        contractualCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMapContract categoryMap : getBudgetCategoryMapListWithoutFilter()) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_SUBCONTRACT)) {
                for (BudgetCategoryMappingContract categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategory().getCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            contractualCost = contractualCost.add(lineItem.getLineItemCost());
                            contractualCostCS = contractualCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getTravelCosts(BudgetPeriodContract budgetPeriod) {
        travelCost = ScaleTwoDecimal.ZERO;
        travelCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMapContract categoryMap : getBudgetCategoryMapListWithoutFilter()) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_TRAVEL)
                    || categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_FOREIGN_TRAVEL)) {
                for (BudgetCategoryMappingContract categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategory().getCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            travelCost = travelCost.add(lineItem.getLineItemCost());
                            travelCostCS = travelCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    protected void getTrainingCosts(BudgetPeriodContract budgetPeriod) {
        trainingCost = ScaleTwoDecimal.ZERO;
        trainingCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMapContract categoryMap : getBudgetCategoryMapListWithoutFilter()) {
            if (categoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_PARTICIPANT_STIPENDS)) {
                for (BudgetCategoryMappingContract categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategory().getCode().equals(categoryMappping.getBudgetCategoryCode())) {
                            trainingCost = trainingCost.add(lineItem.getLineItemCost());
                            trainingCostCS = trainingCostCS.add(lineItem.getCostSharingAmount());
                        }
                    }
                }
            }
        }
    }

    public void getPersonnelCosts(BudgetPeriodContract budgetPeriod) {
        personnelCost = ScaleTwoDecimal.ZERO;
        personnelCostCS = ScaleTwoDecimal.ZERO;
        for (BudgetCategoryMapContract categoryMap : getBudgetCategoryMapListWithoutFilter()) {
            if (categoryMap.getCategoryType().equals(TARGET_CATEGORY_TYPE_CODE_PERSONNEL)) {
                for (BudgetCategoryMappingContract categoryMappping : categoryMap.getBudgetCategoryMappings()) {
                    for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                        if (lineItem.getBudgetCategory().getCode().equals(categoryMappping.getBudgetCategoryCode())) {
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
        String dhhs = s2SConfigurationService.getValueAsString(ConfigurationConstants.DHHS_AGREEMENT);
        if (dhhs != null) {
            if (dhhs.length() > 0) {
                agencyName = AGENCY_VALUE;
            }
        }
        return agencyName;
    }

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }

    public S2SBudgetCalculatorService getS2sBudgetCalculatorService() {
        return s2sBudgetCalculatorService;
    }

    public void setS2sBudgetCalculatorService(S2SBudgetCalculatorService s2sBudgetCalculatorService) {
        this.s2sBudgetCalculatorService = s2sBudgetCalculatorService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    public S2SCommonBudgetService getS2SCommonBudgetService() {
        return s2SCommonBudgetService;
    }

    public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
        this.s2SCommonBudgetService = s2SCommonBudgetService;
    }
}
