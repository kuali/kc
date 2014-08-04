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

import gov.grants.apply.forms.sf424AV10.BudgetCategoriesDocument.BudgetCategories;
import gov.grants.apply.forms.sf424AV10.BudgetFirstQuarterAmountsDocument.BudgetFirstQuarterAmounts;
import gov.grants.apply.forms.sf424AV10.BudgetFirstYearAmountsDocument.BudgetFirstYearAmounts;
import gov.grants.apply.forms.sf424AV10.BudgetForecastedCashNeedsDocument.BudgetForecastedCashNeeds;
import gov.grants.apply.forms.sf424AV10.BudgetFourthQuarterAmountsDocument.BudgetFourthQuarterAmounts;
import gov.grants.apply.forms.sf424AV10.BudgetInformationDocument;
import gov.grants.apply.forms.sf424AV10.BudgetInformationType;
import gov.grants.apply.forms.sf424AV10.BudgetSecondQuarterAmountsDocument.BudgetSecondQuarterAmounts;
import gov.grants.apply.forms.sf424AV10.BudgetSummaryDocument.BudgetSummary;
import gov.grants.apply.forms.sf424AV10.BudgetThirdQuarterAmountsDocument.BudgetThirdQuarterAmounts;
import gov.grants.apply.forms.sf424AV10.CategorySetDocument.CategorySet;
import gov.grants.apply.forms.sf424AV10.CategoryTotalsDocument.CategoryTotals;
import gov.grants.apply.forms.sf424AV10.FederalFundsNeededDocument.FederalFundsNeeded;
import gov.grants.apply.forms.sf424AV10.FundsLineItemDocument.FundsLineItem;
import gov.grants.apply.forms.sf424AV10.FundsTotalsDocument.FundsTotals;
import gov.grants.apply.forms.sf424AV10.NonFederalResourcesDocument.NonFederalResources;
import gov.grants.apply.forms.sf424AV10.ResourceLineItemDocument.ResourceLineItem;
import gov.grants.apply.forms.sf424AV10.ResourceTotalsDocument.ResourceTotals;
import gov.grants.apply.forms.sf424AV10.SummaryLineItemDocument.SummaryLineItem;
import gov.grants.apply.forms.sf424AV10.SummaryTotalsDocument.SummaryTotals;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.budget.api.core.BudgetContract;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemCalculatedAmountContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.budget.BudgetPeriodNum;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMappingContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov SF424AV1_0. Form is generated using XMLBean classes and is based on SF424AV1_0
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("SF424AV1_0Generator")
public class SF424AV1_0Generator extends SF424BaseGenerator {

    private static final Log LOG = LogFactory.getLog(SF424AV1_0Generator.class);

    private BudgetContract budget = null;

    @Value("http://apply.grants.gov/forms/SF424A-V1.0")
    private String namespace;

    @Value("SF424A-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/SF424A-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.sf424AV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    /**
     * 
     * This method returns BudgetInformationDocument object based on proposal development document which contains the informations
     * such as BudgetSummary, BudgetCategories, NonFedResources, BudgetForecastedCashNeeds, FederalFundsNeeded
     * 
     * @return budgetInformationDocument {@link XmlObject} of type BudgetInformationDocument.
     */
    private BudgetInformationDocument getSF424A() {
        BudgetInformationDocument budgetInformationDocument = BudgetInformationDocument.Factory.newInstance();

        budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());

        BudgetInformationType SF424A = BudgetInformationType.Factory.newInstance();
        SF424A.setCoreSchemaVersion(CORE_SCHEMA_VERSION_1_0);
        SF424A.setFormVersionIdentifier(FormVersion.v1_0.getVersion());
        // value is hard coded
        SF424A.setProgramType(NON_CONSTRUCTION);

        BudgetSummary budgetSummary = getBudgetSummary();
        BudgetCategories budgetCategories = getBudgetCategories();
        NonFederalResources nonFederalResources = getNonFederalResources();
        BudgetForecastedCashNeeds budgetForecastedCashNeeds = getBudgetForecastedCashNeeds();
        FederalFundsNeeded federalFundsNeeded = getFederalFundsNeeded();

        SF424A.setBudgetSummary(budgetSummary);
        SF424A.setBudgetCategories(budgetCategories);
        SF424A.setNonFederalResources(nonFederalResources);
        SF424A.setBudgetForecastedCashNeeds(budgetForecastedCashNeeds);
        SF424A.setFederalFundsNeeded(federalFundsNeeded);
        budgetInformationDocument.setBudgetInformation(SF424A);
        return budgetInformationDocument;
    }

    /**
     * 
     * This method gives the information of BudgetCategories like CategorySet, CategoryTotals
     * 
     * @return BudgetCategories budget categories total amount.
     */
    private BudgetCategories getBudgetCategories() {
        ScaleTwoDecimal constructionCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal contractualCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal equipmentCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal personnelCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal suppliesCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal otherCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal travelCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal programIncome = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;

        BudgetCategories budgetCategories = BudgetCategories.Factory.newInstance();
        CategoryTotals categoryTotals = CategoryTotals.Factory.newInstance();
        if (budget == null) {
            return budgetCategories;
        }
        BudgetPeriodContract budgetPeriod = budget.getBudgetPeriods().get(0);

        CategorySet[] categorySetArray = new CategorySet[1];
        CategorySet categorySet = CategorySet.Factory.newInstance();
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionType() != null) {
            categorySet.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
        }

        List<? extends BudgetCategoryMapContract> budgetCategoryMapList = s2sBudgetCalculatorService.getBudgetCategoryMapList(
                new ArrayList<String>(), new ArrayList<String>());
        for (BudgetLineItemContract budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetCategoryMapContract budgetCategoryMap : budgetCategoryMapList) {
                for (BudgetCategoryMappingContract budgetCategoryMapping : budgetCategoryMap.getBudgetCategoryMappings()) {
                    if (budgetLineItem.getBudgetCategory().getCode().equals(budgetCategoryMapping.getBudgetCategoryCode())) {
                        if (budgetCategoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_CONSTRUCTION)) {
                            constructionCost = constructionCost.add(budgetLineItem.getLineItemCost());
                        }
                        else if (budgetCategoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_CONTRACTUAL)) {
                            contractualCost = contractualCost.add(budgetLineItem.getLineItemCost());
                        }
                        else if (budgetCategoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_EQUIPMENT)) {
                            equipmentCost = equipmentCost.add(budgetLineItem.getLineItemCost());
                        }
                        else if (budgetCategoryMap.getCategoryType().equals(TARGET_CATEGORY_TYPE_CODE_PERSONNEL)) {
                            personnelCost = personnelCost.add(budgetLineItem.getLineItemCost());
                        }
                        else if (budgetCategoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_SUPPLIES)) {
                            suppliesCost = suppliesCost.add(budgetLineItem.getLineItemCost());
                        }
                        else if (budgetCategoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_TRAVEL)
                                || budgetCategoryMap.getTargetCategoryCode().equals(TARGET_CATEGORY_CODE_FOREIGN_TRAVEL)) {
                            travelCost = travelCost.add(budgetLineItem.getLineItemCost());
                        }
                       	else{
                        	otherCost = otherCost.add(budgetLineItem.getLineItemCost());
                        }
                    }
                }
            }
            for (BudgetLineItemCalculatedAmountContract budgetLineItemCalculatedAmount : budgetLineItem
                    .getBudgetLineItemCalculatedAmounts()) {
                if (budgetLineItemCalculatedAmount.getRateClass().getRateClassType().getCode().equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS)
                        || budgetLineItemCalculatedAmount.getRateClass().getRateClassType().getCode().equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS)) {
                    calculatedCost = calculatedCost.add(budgetLineItemCalculatedAmount.getCalculatedCost());
                }
            }
        }

        for (BudgetProjectIncomeContract budgetProjectIncome : budget.getBudgetProjectIncomes()) {
            programIncome = programIncome.add(new ScaleTwoDecimal(budgetProjectIncome.getProjectIncome().bigDecimalValue()));
        }

        categorySet.setBudgetConstructionRequestedAmount(constructionCost.bigDecimalValue());
        categoryTotals.setBudgetConstructionRequestedAmount(constructionCost.bigDecimalValue());
        categorySet.setBudgetContractualRequestedAmount(contractualCost.bigDecimalValue());
        categoryTotals.setBudgetContractualRequestedAmount(contractualCost.bigDecimalValue());
        categorySet.setBudgetEquipmentRequestedAmount(equipmentCost.bigDecimalValue());
        categoryTotals.setBudgetEquipmentRequestedAmount(equipmentCost.bigDecimalValue());
        categorySet.setBudgetFringeBenefitsRequestedAmount(calculatedCost.bigDecimalValue());
        categoryTotals.setBudgetFringeBenefitsRequestedAmount(calculatedCost.bigDecimalValue());
        categorySet.setBudgetIndirectChargesAmount(budget.getTotalIndirectCost().bigDecimalValue());
        categoryTotals.setBudgetIndirectChargesAmount(budget.getTotalIndirectCost().bigDecimalValue());
        categorySet.setBudgetOtherRequestedAmount(otherCost.bigDecimalValue());
        categoryTotals.setBudgetOtherRequestedAmount(otherCost.bigDecimalValue());
        categorySet.setBudgetPersonnelRequestedAmount(personnelCost.bigDecimalValue());
        categoryTotals.setBudgetPersonnelRequestedAmount(personnelCost.bigDecimalValue());
        categorySet.setBudgetSuppliesRequestedAmount(suppliesCost.bigDecimalValue());
        categoryTotals.setBudgetSuppliesRequestedAmount(suppliesCost.bigDecimalValue());
        categorySet.setBudgetTotalAmount(budget.getTotalCost().bigDecimalValue());
        categorySet.setBudgetTotalDirectChargesAmount(budget.getTotalDirectCost().bigDecimalValue());
        categorySet.setBudgetTravelRequestedAmount(travelCost.bigDecimalValue());
        categoryTotals.setBudgetTravelRequestedAmount(travelCost.bigDecimalValue());
        categorySet.setProgramIncomeAmount(programIncome.bigDecimalValue());
        categoryTotals.setProgramIncomeAmount(programIncome.bigDecimalValue());
        categoryTotals.setBudgetTotalAmount(budget.getTotalCost().bigDecimalValue());
        categoryTotals.setBudgetTotalDirectChargesAmount(budget.getTotalDirectCost().bigDecimalValue());

        categorySetArray[0] = categorySet;
        budgetCategories.setCategorySetArray(categorySetArray);
        budgetCategories.setCategoryTotals(categoryTotals);
        return budgetCategories;
    }

    /**
     * 
     * This method gives the information of BudgetSummary which consists of SummaryLineItem, SummaryTotals
     * 
     * @return BudgetSummary budget summary total.
     */
    private BudgetSummary getBudgetSummary() {
        BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
        SummaryLineItem[] summaryLineItemArray = new SummaryLineItem[1];
        SummaryLineItem summaryLineItem = SummaryLineItem.Factory.newInstance();
        boolean hasBudgetLineItem = false;
        
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionType() != null) {
            summaryLineItem.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
            summaryLineItem.setCFDANumber(pdDoc.getDevelopmentProposal().getS2sOpportunity().getCfdaNumber());
        }
        if (budget != null) {
            ScaleTwoDecimal fedNonFedCost = budget.getTotalCost();
            ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;

            for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                    hasBudgetLineItem = true;
                    if (budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag()) {
                        costSharingAmount =  costSharingAmount.add(lineItem.getCostSharingAmount());
                        List<? extends BudgetLineItemCalculatedAmountContract> calculatedAmounts = lineItem.getBudgetLineItemCalculatedAmounts();
                        for (BudgetLineItemCalculatedAmountContract budgetLineItemCalculatedAmount : calculatedAmounts) {
                             costSharingAmount =  costSharingAmount.add(budgetLineItemCalculatedAmount.getCalculatedCostSharing());
                        }
                        
                    }
                }
            }
            if(!hasBudgetLineItem && budget.getSubmitCostSharingFlag()){
                costSharingAmount = budget.getCostSharingAmount();      
            }
            fedNonFedCost = fedNonFedCost.add(costSharingAmount);
            summaryLineItem.setBudgetFederalNewOrRevisedAmount(budget.getTotalCost().bigDecimalValue());
            summaryLineItem.setBudgetNonFederalNewOrRevisedAmount(costSharingAmount.bigDecimalValue());
            summaryLineItem.setBudgetTotalNewOrRevisedAmount(fedNonFedCost.bigDecimalValue());
            summaryLineItemArray[0] = summaryLineItem;
            budgetSummary.setSummaryLineItemArray(summaryLineItemArray);

            SummaryTotals summaryTotals = SummaryTotals.Factory.newInstance();
            summaryTotals.setBudgetFederalNewOrRevisedAmount(budget.getTotalCost().bigDecimalValue());
            summaryTotals.setBudgetNonFederalNewOrRevisedAmount(costSharingAmount.bigDecimalValue());
            summaryTotals.setBudgetTotalNewOrRevisedAmount(fedNonFedCost.bigDecimalValue());
            budgetSummary.setSummaryTotals(summaryTotals);
        }

        return budgetSummary;
    }

    /**
     * 
     * This method gives the information of NonFederalResources which consists of ResourceLineItem, ResourceTotals
     * 
     * @return NonFederalResources non-federal resource details.
     */
    private NonFederalResources getNonFederalResources() {
        NonFederalResources nonFederalResources = NonFederalResources.Factory.newInstance();
        ResourceLineItem[] resourceLineItemArray = new ResourceLineItem[1];
        ResourceLineItem resourceLineItem = ResourceLineItem.Factory.newInstance();
        boolean hasBudegetLineItem = false;
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionType() != null) {
            resourceLineItem.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
        }
        if (budget != null) {
            ScaleTwoDecimal fedNonFedCost = ScaleTwoDecimal.ZERO;
            for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                    hasBudegetLineItem = true;
                    if (budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag()) {
                        fedNonFedCost = fedNonFedCost.add(lineItem.getCostSharingAmount());
                        for (BudgetLineItemCalculatedAmountContract budgetLineItemCalculatedAmount : lineItem.getBudgetLineItemCalculatedAmounts()) {
                            fedNonFedCost = fedNonFedCost.add(budgetLineItemCalculatedAmount.getCalculatedCostSharing());
                        }
                    }
                }
            }
            if (!hasBudegetLineItem && budget.getSubmitCostSharingFlag()) {
                fedNonFedCost = fedNonFedCost.add(budget.getCostSharingAmount());
            }
            resourceLineItem.setBudgetApplicantContributionAmount(fedNonFedCost.bigDecimalValue());
            resourceLineItem.setBudgetTotalContributionAmount(fedNonFedCost.bigDecimalValue());
            resourceLineItemArray[0] = resourceLineItem;
            nonFederalResources.setResourceLineItemArray(resourceLineItemArray);

            ResourceTotals resourceTotals = ResourceTotals.Factory.newInstance();
            resourceTotals.setBudgetApplicantContributionAmount(fedNonFedCost.bigDecimalValue());
            resourceTotals.setBudgetTotalContributionAmount(fedNonFedCost.bigDecimalValue());
            nonFederalResources.setResourceTotals(resourceTotals);
        }

        return nonFederalResources;
    }

    /**
     * 
     * This method gives the information of BudgetForecastedCashNeeds which consists of budgetFirstYearAmounts,
     * budgetFirstQuarterAmounts budgetSecondQuarterAmounts, budgetThirdQuarterAmounts, budgetFourthQuarterAmounts
     * 
     * @return budgetForecastedCashNeeds budget calculated for every quarter.
     */
    private BudgetForecastedCashNeeds getBudgetForecastedCashNeeds() {
        ScaleTwoDecimal totalFedCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal costSharing = ScaleTwoDecimal.ZERO;
        BigDecimal totalEstimation = ScaleTwoDecimal.ZERO.bigDecimalValue();
        BigDecimal costShareEstimation = ScaleTwoDecimal.ZERO.bigDecimalValue();
        BigDecimal totalFedEstimation = ScaleTwoDecimal.ZERO.bigDecimalValue();
        BudgetForecastedCashNeeds budgetForecastedCashNeeds = BudgetForecastedCashNeeds.Factory.newInstance();
        if (budget != null) {
            BudgetFirstYearAmounts budgetFirstYearAmounts = BudgetFirstYearAmounts.Factory.newInstance();
            BudgetFirstQuarterAmounts budgetFirstQuarterAmounts = BudgetFirstQuarterAmounts.Factory.newInstance();
            BudgetSecondQuarterAmounts budgetSecondQuarterAmounts = BudgetSecondQuarterAmounts.Factory.newInstance();
            BudgetThirdQuarterAmounts budgetThirdQuarterAmounts = BudgetThirdQuarterAmounts.Factory.newInstance();
            BudgetFourthQuarterAmounts budgetFourthQuarterAmounts = BudgetFourthQuarterAmounts.Factory.newInstance();
            for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                    if (budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag()) {                      
                        if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P1.getNum()) {
                            costSharing = costSharing.add(lineItem.getCostSharingAmount());                       
                            for (BudgetLineItemCalculatedAmountContract budgetLineItemCalculatedAmount : lineItem.getBudgetLineItemCalculatedAmounts()) {
                                costSharing = costSharing.add(budgetLineItemCalculatedAmount.getCalculatedCostSharing());
                            }
                        }
                    }
                }
                if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P1.getNum()) {
                    totalFedCost = budgetPeriod.getTotalCost();
                    totalFedEstimation = totalFedCost.bigDecimalValue().divide(new ScaleTwoDecimal(4).bigDecimalValue(), RoundingMode.HALF_UP);
                    costShareEstimation = costSharing.bigDecimalValue().divide(new ScaleTwoDecimal(4).bigDecimalValue(), RoundingMode.HALF_UP);
                    totalEstimation = totalFedEstimation.add(costShareEstimation);                    
                }
            }

            budgetFirstYearAmounts.setBudgetFederalForecastedAmount(totalFedCost.bigDecimalValue());
            budgetFirstYearAmounts.setBudgetNonFederalForecastedAmount(costSharing.bigDecimalValue());
            budgetFirstYearAmounts.setBudgetTotalForecastedAmount(costSharing.add(totalFedCost).bigDecimalValue());

            budgetForecastedCashNeeds.setBudgetFirstYearAmounts(budgetFirstYearAmounts);

            budgetFirstQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation);
            budgetFirstQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation);
            budgetFirstQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation);

            budgetForecastedCashNeeds.setBudgetFirstQuarterAmounts(budgetFirstQuarterAmounts);

            budgetSecondQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation);
            budgetSecondQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation);
            budgetSecondQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation);

            budgetForecastedCashNeeds.setBudgetSecondQuarterAmounts(budgetSecondQuarterAmounts);

            budgetThirdQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation);
            budgetThirdQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation);
            budgetThirdQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation);

            budgetForecastedCashNeeds.setBudgetThirdQuarterAmounts(budgetThirdQuarterAmounts);

            budgetFourthQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation);
            budgetFourthQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation);
            budgetFourthQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation);

            budgetForecastedCashNeeds.setBudgetFourthQuarterAmounts(budgetFourthQuarterAmounts);
        }        
        return budgetForecastedCashNeeds;
    }

    /**
     * 
     * This method gives the information of FederalFundsNeeded which consists of FundsLineItem, FundsTotals
     * 
     * @return FederalFundsNeeded federal budget funds required for the corresponding years.
     */
    private FederalFundsNeeded getFederalFundsNeeded() {

        ScaleTwoDecimal firstYearCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal firstYearCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal firstYearNetCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal secondYearCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal secondYearCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal secondYearNetCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal thirdYearCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal thirdYearCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal thirdYearNetCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fourthYearCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fourthYearCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fourthYearNetCost = ScaleTwoDecimal.ZERO;

        FederalFundsNeeded federalFundsNeeded = FederalFundsNeeded.Factory.newInstance();
        if (budget == null) {
            return federalFundsNeeded;
        }

        FundsTotals fundsTotals = FundsTotals.Factory.newInstance();

        FundsLineItem[] fundsLineItemArray = new FundsLineItem[1];
        FundsLineItem fundsLineItem = FundsLineItem.Factory.newInstance();

        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionType() != null) {
            fundsLineItem.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
        }

        for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P2.getNum()) {
                firstYearNetCost = firstYearNetCost.add(budgetPeriod.getTotalCost());
            }
            if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P3.getNum()) {
                secondYearNetCost = secondYearNetCost.add(budgetPeriod.getTotalCost());
            }
            if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P4.getNum()) {
                thirdYearNetCost = thirdYearNetCost.add(budgetPeriod.getTotalCost());
            }
            if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P5.getNum()) {
                fourthYearCost = fourthYearCost.add(budgetPeriod.getTotalCost());
                fourthYearCostSharing = fourthYearCostSharing.add(budgetPeriod.getCostSharingAmount());
                fourthYearNetCost = fourthYearCost.subtract(fourthYearCostSharing);
            }
        }
        fundsLineItem.setBudgetFirstYearAmount(firstYearNetCost.bigDecimalValue());
        fundsTotals.setBudgetFirstYearAmount(firstYearNetCost.bigDecimalValue());
        fundsLineItem.setBudgetSecondYearAmount(secondYearNetCost.bigDecimalValue());
        fundsTotals.setBudgetSecondYearAmount(secondYearNetCost.bigDecimalValue());
        fundsLineItem.setBudgetThirdYearAmount(thirdYearNetCost.bigDecimalValue());
        fundsTotals.setBudgetThirdYearAmount(thirdYearNetCost.bigDecimalValue());
        fundsLineItem.setBudgetFourthYearAmount(fourthYearNetCost.bigDecimalValue());
        fundsTotals.setBudgetFourthYearAmount(fourthYearNetCost.bigDecimalValue());

        fundsLineItemArray[0] = fundsLineItem;
        federalFundsNeeded.setFundsLineItemArray(fundsLineItemArray);
        federalFundsNeeded.setFundsTotals(fundsTotals);
        return federalFundsNeeded;
    }

    /**
     * This method creates {@link XmlObject} of type {@link BudgetInformationDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getSF424A();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
