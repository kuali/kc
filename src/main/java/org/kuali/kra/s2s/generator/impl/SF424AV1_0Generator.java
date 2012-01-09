/*
 * Copyright 2005-2010 The Kuali Foundation.
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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.core.BudgetCategoryMapping;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov SF424AV1_0. Form is generated using XMLBean classes and is based on SF424AV1_0
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SF424AV1_0Generator extends SF424BaseGenerator {

    private static final Log LOG = LogFactory.getLog(SF424AV1_0Generator.class);

    private Budget budget = null;

    /**
     * 
     * This method returns BudgetInformationDocument object based on proposal development document which contains the informations
     * such as BudgetSummary, BudgetCategories, NonFedResources, BudgetForecastedCashNeeds, FederalFundsNeeded
     * 
     * @return budgetInformationDocument {@link XmlObject} of type BudgetInformationDocument.
     */
    private BudgetInformationDocument getSF424A() {
        BudgetInformationDocument budgetInformationDocument = BudgetInformationDocument.Factory.newInstance();
        try {
            BudgetDocument budgetDocument = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
            budget = budgetDocument==null?null:budgetDocument.getBudget();
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return budgetInformationDocument;
        }
        BudgetInformationType SF424A = BudgetInformationType.Factory.newInstance();
        SF424A.setCoreSchemaVersion(CORE_SCHEMA_VERSION_1_0);
        SF424A.setFormVersionIdentifier(S2SConstants.FORMVERSION_1_0);
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
        BudgetDecimal constructionCost = BudgetDecimal.ZERO;
        BudgetDecimal contractualCost = BudgetDecimal.ZERO;
        BudgetDecimal equipmentCost = BudgetDecimal.ZERO;
        BudgetDecimal personnelCost = BudgetDecimal.ZERO;
        BudgetDecimal suppliesCost = BudgetDecimal.ZERO;
        BudgetDecimal otherCost = BudgetDecimal.ZERO;
        BudgetDecimal travelCost = BudgetDecimal.ZERO;
        BudgetDecimal programIncome = BudgetDecimal.ZERO;
        BudgetDecimal calculatedCost = BudgetDecimal.ZERO;

        BudgetCategories budgetCategories = BudgetCategories.Factory.newInstance();
        CategoryTotals categoryTotals = CategoryTotals.Factory.newInstance();
        if (budget == null) {
            return budgetCategories;
        }
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(0);

        CategorySet[] categorySetArray = new CategorySet[1];
        CategorySet categorySet = CategorySet.Factory.newInstance();
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() != null) {
            pdDoc.getDevelopmentProposal().getS2sOpportunity().refreshNonUpdateableReferences();
            categorySet.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
        }

        List<BudgetCategoryMap> budgetCategoryMapList = s2sBudgetCalculatorService.getBudgetCategoryMapList(
                new ArrayList<String>(), new ArrayList<String>());
        for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetCategoryMap budgetCategoryMap : budgetCategoryMapList) {
                for (BudgetCategoryMapping budgetCategoryMapping : budgetCategoryMap.getBudgetCategoryMappings()) {
                    if (budgetLineItem.getBudgetCategoryCode().equals(budgetCategoryMapping.getBudgetCategoryCode())) {
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
            for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : budgetLineItem
                    .getBudgetLineItemCalculatedAmounts()) {
                budgetLineItemCalculatedAmount.refreshReferenceObject("rateClass");
                if (budgetLineItemCalculatedAmount.getRateClass().getRateClassType().equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS)
                        || budgetLineItemCalculatedAmount.getRateClass().getRateClassType().equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS)) {
                    calculatedCost = calculatedCost.add(budgetLineItemCalculatedAmount.getCalculatedCost());
                }
            }
        }

        for (BudgetProjectIncome budgetProjectIncome : budget.getBudgetProjectIncomes()) {
            programIncome = programIncome.add(new BudgetDecimal(budgetProjectIncome.getProjectIncome().bigDecimalValue()));
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
        BudgetDecimal totalFedCost = BudgetDecimal.ZERO;
        BudgetDecimal costSharing = BudgetDecimal.ZERO;
        BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
        SummaryLineItem[] summaryLineItemArray = new SummaryLineItem[1];
        SummaryLineItem summaryLineItem = SummaryLineItem.Factory.newInstance();
        boolean hasBudgetLineItem = false;
        
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() != null) {
            pdDoc.getDevelopmentProposal().getS2sOpportunity().refreshNonUpdateableReferences();
            summaryLineItem.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
            summaryLineItem.setCFDANumber(pdDoc.getDevelopmentProposal().getS2sOpportunity().getCfdaNumber());
        }
        if (budget != null) {
        	budget.refreshNonUpdateableReferences();
        	for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                     hasBudgetLineItem = true;
                    if(budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag())
                        costSharing=costSharing.add(lineItem.getCostSharingAmount());
                    }
            }
        	 if(!hasBudgetLineItem && budget.getSubmitCostSharingFlag()){
        	     costSharing=costSharing.add(budget.getCostSharingAmount());
        	 }
            totalFedCost = budget.getTotalCost().subtract(costSharing);
            summaryLineItem.setBudgetFederalNewOrRevisedAmount(totalFedCost.bigDecimalValue());
            summaryLineItem.setBudgetNonFederalNewOrRevisedAmount(costSharing.bigDecimalValue());
            summaryLineItem.setBudgetTotalNewOrRevisedAmount(budget.getTotalCost().bigDecimalValue());
            summaryLineItemArray[0] = summaryLineItem;
            budgetSummary.setSummaryLineItemArray(summaryLineItemArray);

            SummaryTotals summaryTotals = SummaryTotals.Factory.newInstance();
            summaryTotals.setBudgetFederalNewOrRevisedAmount(totalFedCost.bigDecimalValue());
            summaryTotals.setBudgetNonFederalNewOrRevisedAmount(costSharing.bigDecimalValue());
            summaryTotals.setBudgetTotalNewOrRevisedAmount(budget.getTotalCost().bigDecimalValue());
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
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() != null) {
            pdDoc.getDevelopmentProposal().getS2sOpportunity().refreshNonUpdateableReferences();
            resourceLineItem.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
        }
        if (budget != null) {
            BudgetDecimal fedNonFedCost = BudgetDecimal.ZERO;
            for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                    hasBudegetLineItem = true;
                    if(budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag())
                        fedNonFedCost=fedNonFedCost.add(lineItem.getCostSharingAmount());
                }
            }
            if(!hasBudegetLineItem && budget.getSubmitCostSharingFlag()){
                fedNonFedCost=fedNonFedCost.add(budget.getCostSharingAmount());
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
        BudgetDecimal totalFedCost = BudgetDecimal.ZERO;
        BudgetDecimal costSharing = BudgetDecimal.ZERO;
        BudgetForecastedCashNeeds budgetForecastedCashNeeds = BudgetForecastedCashNeeds.Factory.newInstance();
        if (budget != null) {
            BudgetFirstYearAmounts budgetFirstYearAmounts = BudgetFirstYearAmounts.Factory.newInstance();
            BudgetFirstQuarterAmounts budgetFirstQuarterAmounts = BudgetFirstQuarterAmounts.Factory.newInstance();
            BudgetSecondQuarterAmounts budgetSecondQuarterAmounts = BudgetSecondQuarterAmounts.Factory.newInstance();
            BudgetThirdQuarterAmounts budgetThirdQuarterAmounts = BudgetThirdQuarterAmounts.Factory.newInstance();
            BudgetFourthQuarterAmounts budgetFourthQuarterAmounts = BudgetFourthQuarterAmounts.Factory.newInstance();
            for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                    if(budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag())
                        costSharing=costSharing.add(lineItem.getCostSharingAmount());
                    }
            }
            totalFedCost = budget.getTotalCost().subtract(costSharing);
            BudgetDecimal totalEstimation = budget.getTotalCost().divide(new BudgetDecimal(4));
            BudgetDecimal costShareEstimation = costSharing.divide(new BudgetDecimal(4));
            BudgetDecimal totalFedEstimation = totalFedCost.divide(new BudgetDecimal(4));

            budgetFirstYearAmounts.setBudgetFederalForecastedAmount(totalFedCost.bigDecimalValue());
            budgetFirstYearAmounts.setBudgetNonFederalForecastedAmount(costSharing.bigDecimalValue());
            budgetFirstYearAmounts.setBudgetTotalForecastedAmount(budget.getTotalCost().bigDecimalValue());

            budgetForecastedCashNeeds.setBudgetFirstYearAmounts(budgetFirstYearAmounts);

            budgetFirstQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation.bigDecimalValue());
            budgetFirstQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation.bigDecimalValue());
            budgetFirstQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation.bigDecimalValue());

            budgetForecastedCashNeeds.setBudgetFirstQuarterAmounts(budgetFirstQuarterAmounts);

            budgetSecondQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation.bigDecimalValue());
            budgetSecondQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation.bigDecimalValue());
            budgetSecondQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation.bigDecimalValue());

            budgetForecastedCashNeeds.setBudgetSecondQuarterAmounts(budgetSecondQuarterAmounts);

            budgetThirdQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation.bigDecimalValue());
            budgetThirdQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation.bigDecimalValue());
            budgetThirdQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation.bigDecimalValue());

            budgetForecastedCashNeeds.setBudgetThirdQuarterAmounts(budgetThirdQuarterAmounts);

            budgetFourthQuarterAmounts.setBudgetFederalForecastedAmount(totalFedEstimation.bigDecimalValue());
            budgetFourthQuarterAmounts.setBudgetNonFederalForecastedAmount(costShareEstimation.bigDecimalValue());
            budgetFourthQuarterAmounts.setBudgetTotalForecastedAmount(totalEstimation.bigDecimalValue());

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

        BudgetDecimal firstYearCost = BudgetDecimal.ZERO;
        BudgetDecimal firstYearCostSharing = BudgetDecimal.ZERO;
        BudgetDecimal firstYearNetCost = BudgetDecimal.ZERO;
        BudgetDecimal secondYearCost = BudgetDecimal.ZERO;
        BudgetDecimal secondYearCostSharing = BudgetDecimal.ZERO;
        BudgetDecimal secondYearNetCost = BudgetDecimal.ZERO;
        BudgetDecimal thirdYearCost = BudgetDecimal.ZERO;
        BudgetDecimal thirdYearCostSharing = BudgetDecimal.ZERO;
        BudgetDecimal thirdYearNetCost = BudgetDecimal.ZERO;
        BudgetDecimal fourthYearCost = BudgetDecimal.ZERO;
        BudgetDecimal fourthYearCostSharing = BudgetDecimal.ZERO;
        BudgetDecimal fourthYearNetCost = BudgetDecimal.ZERO;

        FederalFundsNeeded federalFundsNeeded = FederalFundsNeeded.Factory.newInstance();
        if (budget == null) {
            return federalFundsNeeded;
        }

        FundsTotals fundsTotals = FundsTotals.Factory.newInstance();

        FundsLineItem[] fundsLineItemArray = new FundsLineItem[1];
        FundsLineItem fundsLineItem = FundsLineItem.Factory.newInstance();

        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() != null) {
            pdDoc.getDevelopmentProposal().getS2sOpportunity().refreshNonUpdateableReferences();
            fundsLineItem.setActivityTitle(pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());
        }

        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetPeriod() == S2SConstants.BUDGET_PERIOD_2) {
                firstYearCost = firstYearCost.add(budgetPeriod.getTotalCost());
                firstYearCostSharing = firstYearCostSharing.add(budgetPeriod.getCostSharingAmount());
                firstYearNetCost = firstYearCost.subtract(firstYearCostSharing);
            }
            if (budgetPeriod.getBudgetPeriod() == S2SConstants.BUDGET_PERIOD_3) {
                secondYearCost = secondYearCost.add(budgetPeriod.getTotalCost());
                secondYearCostSharing = secondYearCostSharing.add(budgetPeriod.getCostSharingAmount());
                secondYearNetCost = secondYearCost.subtract(secondYearCostSharing);
            }
            if (budgetPeriod.getBudgetPeriod() == S2SConstants.BUDGET_PERIOD_4) {
                thirdYearCost = thirdYearCost.add(budgetPeriod.getTotalCost());
                thirdYearCostSharing = thirdYearCostSharing.add(budgetPeriod.getCostSharingAmount());
                thirdYearNetCost = thirdYearCost.subtract(thirdYearCostSharing);
            }
            if (budgetPeriod.getBudgetPeriod() == S2SConstants.BUDGET_PERIOD_5) {
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
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getSF424A();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        BudgetInformationType SF424A = (BudgetInformationType) xmlObject;
        BudgetInformationDocument budgetInformationDocument = BudgetInformationDocument.Factory.newInstance();
        budgetInformationDocument.setBudgetInformation(SF424A);
        return budgetInformationDocument;
    }

}
