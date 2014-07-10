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
package org.kuali.coeus.s2sgen.impl.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.person.KcPersonRepositoryService;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.budget.api.core.BudgetContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemCalculatedAmountContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetRateAndBaseContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.common.budget.api.personnel.*;
import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.common.api.org.OrganizationRepositoryService;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularIdcContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;
import org.kuali.coeus.s2sgen.impl.validate.S2SErrorHandlerService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapService;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMappingContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonSalaryService;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * This class contains the implementation for common budget calculations required for S2S Form generators
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("s2SBudgetCalculatorService")
public class S2SBudgetCalculatorServiceImpl implements
                S2SBudgetCalculatorService {
    public static final String VALUE_UNKNOWN = "Unknown";
    private static final int MAX_KEY_PERSON_COUNT = 8;
    private static final String CATEGORY_TYPE_OTHER_DIRECT_COST = "O";
    private static final String LASALARIES = "LASALARIES";
    private static final String PERSONNEL_TYPE_GRAD = "Grad";
    private static final String PERSONNEL_TYPE_POSTDOC = "PostDoc";
    private static final String PERSONNEL_TYPE_UNDERGRAD = "UnderGrad";
    private static final String PERSONNEL_TYPE_SEC = "Sec";
    private static final String PERSONNEL_TYPE_OTHER = "Other";
    private static final String PERSONNEL_TYPE_OTHER_PROFESSIONALS = "Other Professionals";
    private static final String PERSONNEL_TYPE_ALLOCATED_ADMIN_SUPPORT = "Allocated Admin Support";
    private static final String ROLE_GRADUATE_STUDENTS = "Graduate Students";
    private static final String ROLE_POST_DOCTORAL_ASSOCIATES = "Post Doctoral Associates";
    private static final String ROLE_GRADUATE_UNDERGRADUATE_STUDENTS = "Undergraduate Students";
    private static final String ROLE_GRADUATE_SECRETARIAL_OR_CLERICAL = "Secretarial / Clerical";
    private static final String ROLE_GRADUATE_OTHER = "Other";
    private static final String ROLE_GRADUATE_OTHER_PROFESSIONALS = "Other Professionals";
    private static final String ROLE_GRADUATE_ALLOCATED_ADMIN_SUPPORT = "Allocated Admin Support";
    private static final String TARGET_CATEGORY_CODE_01 = "01";
    private static final String OTHER_DIRECT_COSTS = "Other Direct Costs";
    private static final String ALL_OTHER_COSTS = "All Other Costs";
    private static final String DESCRIPTION = "Description";
    private static final String DESCRIPTION_LA = "LA ";
    private static final String KEYPERSON_CO_PD_PI = "CO-PD/PI";
    private static final String NID_PD_PI = "PD/PI";
    private static final String NID_CO_PD_PI = "CO-INVESTIGATOR"; 
    private static final String KEYPERSON_OTHER = "Other (Specify)";
    public static final String SPONSOR = "S2S";
    private static final String PRINCIPAL_INVESTIGATOR_ROLE = "PD/PI";
    private static final BigDecimal POINT_ZERO_ONE = new ScaleTwoDecimal(0.01).bigDecimalValue();

    @Autowired
    @Qualifier("budgetCategoryMapService")
    private BudgetCategoryMapService budgetCategoryMapService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("budgetPersonSalaryService")
    private BudgetPersonSalaryService budgetPersonSalaryService;

    @Autowired
    @Qualifier("organizationRepositoryService")
    private OrganizationRepositoryService organizationRepositoryService;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    @Autowired
    @Qualifier("kcPersonRepositoryService")
    private KcPersonRepositoryService kcPersonRepositoryService;

    @Autowired
    @Qualifier("tbnPersonService")
    private TbnPersonService tbnPersonService;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("s2SDateTimeService")
    private S2SDateTimeService s2SDateTimeService;

    @Autowired
    @Qualifier("s2SProposalPersonService")
    private S2SProposalPersonService s2SProposalPersonService;

    @Autowired
    @Qualifier("s2SErrorHandlerService")
    private S2SErrorHandlerService s2SErrorHandlerService;

    @Autowired
    @Qualifier("s2SCommonBudgetService")
    private S2SCommonBudgetService s2SCommonBudgetService;

    @Override
    public String getParticipantSupportCategoryCode() {
        return s2SConfigurationService.getValueAsString(ConfigurationConstants.BUDGET_CATEGORY_TYPE_PARTICIPANT_SUPPORT);
    }

    @Override
    public List<? extends BudgetLineItemContract> getMatchingLineItems(List<? extends BudgetLineItemContract> lineItems, List<String> budgetCategoryType) {
        List<BudgetLineItemContract> result = new ArrayList<BudgetLineItemContract>();
        for (BudgetLineItemContract lineItem : lineItems) {
            if (budgetCategoryType.contains(lineItem.getBudgetCategory().getBudgetCategoryType().getCode())) {
                result.add(lineItem);
            }
        }
        return result;
    }

    /**
     * 
     * This method does the budget related calculations for a given ProposalDevelopmentDocumentContract and returns them in
     * BudgetSummaryInfo
     * 
     * @param pdDoc ProposalDevelopmentDocumentContract.
     * @return BudgetSummaryInfo corresponding to the ProposalDevelopmentDocumentContract object.
     * @throws S2SException
     */
    @Override
    public BudgetSummaryDto getBudgetInfo(ProposalDevelopmentDocumentContract pdDoc, List<BudgetPeriodDto> budgetPeriodInfos)
            throws S2SException {
        ProposalDevelopmentBudgetExtContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());
        BudgetSummaryDto budgetSummaryInfo = new BudgetSummaryDto();
        if (budget == null) {
            return budgetSummaryInfo;
        }

        budgetSummaryInfo.setBudgetPeriods(budgetPeriodInfos);
        budgetSummaryInfo.setCumTotalCosts(budget.getTotalCost());
        budgetSummaryInfo.setCumTotalIndirectCosts(budget.getTotalIndirectCost());
        budgetSummaryInfo.setCumTotalDirectCosts(budget.getTotalDirectCost());
        if (budget.getSubmitCostSharingFlag()) {
            budgetSummaryInfo.setCumTotalCostSharing(budget.getCostSharingAmount());
        }

        ScaleTwoDecimal totalDirectCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal lineItemCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal lineItemCostSharingAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fringeCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fringeCostSharingAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal budgetDetailsCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal budgetDetailsCostSharingAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal budgetDetailsFringeCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal budgetDetailsFringeCostSharingAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totPersFunds = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totPersNonFunds = ScaleTwoDecimal.ZERO;
        String budgetCategoryTypePersonnel = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_BUDGET_CATEGORY_TYPE_PERSONNEL);
        String rateTypeSupportStaffSalaries = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_RATE_TYPE_SUPPORT_STAFF_SALARIES);
        String rateTypeAdministrativeSalaries = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_RATE_TYPE_ADMINISTRATIVE_SALARIES);
                
        for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
            for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                totalDirectCostSharing = totalDirectCostSharing.add(lineItem.getCostSharingAmount());
                if (lineItem
                        .getBudgetCategory()
                        .getBudgetCategoryType()
                        .getCode()
                        .equals(budgetCategoryTypePersonnel)) {
                    lineItemCost = lineItemCost.add(lineItem.getLineItemCost());
                    if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                        lineItemCostSharingAmount = lineItemCostSharingAmount.add(lineItem.getCostSharingAmount());
                    }
                }

                for (BudgetLineItemCalculatedAmountContract lineItemCalAmt : lineItem.getBudgetLineItemCalculatedAmounts()) {
                    if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                        if (lineItemCalAmt.getRateClass().getRateClassType().getCode().equals(RateClassType.OVERHEAD.getRateClassType())) {
                            totalIndirectCostSharing = totalIndirectCostSharing.add(lineItemCalAmt.getCalculatedCostSharing());
                        }
                        else {
                            totalDirectCostSharing = totalDirectCostSharing.add(lineItemCalAmt.getCalculatedCostSharing());
                        }
                    }
                    if ((lineItemCalAmt.getRateClass().getCode().equals(
                            s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.S2SBUDGET_RATE_CLASS_CODE_EMPLOYEE_BENEFITS)) && !lineItemCalAmt.getRateTypeCode()
                            .equals(rateTypeSupportStaffSalaries))
                            || (lineItemCalAmt.getRateClass().getCode().equals(
                                    s2SConfigurationService.getValueAsString(
                                            ConfigurationConstants.S2SBUDGET_RATE_CLASS_CODE_VACATION)) && !lineItemCalAmt.getRateTypeCode()
                                    .equals(rateTypeAdministrativeSalaries))) {
                        if (lineItem
                                .getBudgetCategory()
                                .getBudgetCategoryType()
                                .getCode()
                                .equals(budgetCategoryTypePersonnel)) {
                            fringeCost = fringeCost.add(lineItemCalAmt.getCalculatedCost());
                            if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                                fringeCostSharingAmount = fringeCostSharingAmount.add(lineItemCalAmt.getCalculatedCostSharing());
                            }
                        }
                    }
                    if (lineItemCalAmt
                            .getRateClass()
                            .getRateClassType().getCode()
                            .equals(s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_LAB_ALLOCATION_SALARIES))) {
                        budgetDetailsCost = budgetDetailsCost.add(lineItemCalAmt.getCalculatedCost());
                        if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                            budgetDetailsCostSharingAmount = budgetDetailsCostSharingAmount.add(lineItemCalAmt
                                    .getCalculatedCostSharing());
                        }
                    }
                    if ((lineItemCalAmt
                            .getRateClass()
                            .getRateClassType().getCode()
                            .equals(s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_EMPLOYEE_BENEFITS)) && lineItemCalAmt.getRateTypeCode()
                            .equals(rateTypeSupportStaffSalaries))
                            || (lineItemCalAmt
                                    .getRateClass()
                                    .getRateClassType().getCode()
                                    .equals(s2SConfigurationService.getValueAsString(
                                            ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_VACATION)) && lineItemCalAmt.getRateTypeCode()
                                    .equals(rateTypeAdministrativeSalaries))) {
                        budgetDetailsFringeCost = budgetDetailsFringeCost.add(lineItemCalAmt.getCalculatedCost());
                        if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                            budgetDetailsFringeCostSharingAmount = budgetDetailsFringeCostSharingAmount.add(lineItemCalAmt
                                    .getCalculatedCostSharing());
                        }
                    }

                }
            }
        }
        if (budget.getSubmitCostSharingFlag()) {
            budgetSummaryInfo.setCumTotalDirectCostSharing(totalDirectCostSharing);
            budgetSummaryInfo.setCumTotalIndirectCostSharing(totalIndirectCostSharing);
        }
        totPersFunds = totPersFunds.add(lineItemCost).add(fringeCost).add(budgetDetailsCost).add(budgetDetailsFringeCost);
        totPersNonFunds = totPersNonFunds.add(lineItemCostSharingAmount).add(fringeCostSharingAmount)
                .add(budgetDetailsCostSharingAmount).add(budgetDetailsFringeCostSharingAmount);

        budgetSummaryInfo.setCumTotalNonFundsForPersonnel(totPersNonFunds);
        budgetSummaryInfo.setCumTotalFundsForPersonnel(totPersFunds);

        OtherDirectCostInfoDto otherDirectCostInfo = new OtherDirectCostInfoDto();
        List<OtherDirectCostInfoDto> cvOtherDirectCost = new ArrayList<OtherDirectCostInfoDto>();
        List<Map<String, String>> cvOtherCosts = new ArrayList<Map<String, String>>();

        ScaleTwoDecimal cumAlterations = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumConsultants = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumMaterials = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPubs = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumSubAward = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumComputer = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumEquipRental = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumAll = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumOtherType1 = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartStipends = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartSubsistence = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartTuition = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartOther = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartTravel = ScaleTwoDecimal.ZERO;
        int cumParticipantCount = 0;
        ScaleTwoDecimal cumAlterationsCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumConsultantsCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumMaterialsCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPubsCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumSubAwardCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumComputerCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumEquipRentalCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumAllCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumOtherType1CostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartStipendsCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartSubsistenceCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartTuitionCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartOtherCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumPartTravelCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal participantTotalCostSharing = ScaleTwoDecimal.ZERO;

        ScaleTwoDecimal totalDomesticTravel = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalForeignTravel = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalDomesticTravelNonFund = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalForeignTravelNonFund = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumTotalEquipFund = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal cumTotalEquipNonFund = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totCountOtherPersonnel = ScaleTwoDecimal.ZERO;

        if (budgetPeriodInfos != null) {
            for (BudgetPeriodDto budgetPeriodInfo : budgetPeriodInfos) {
                cvOtherDirectCost = budgetPeriodInfo.getOtherDirectCosts();
                otherDirectCostInfo = cvOtherDirectCost.get(0);
                cumAlterations = cumAlterations.add(otherDirectCostInfo.getAlterations());
                cumConsultants = cumConsultants.add(otherDirectCostInfo.getConsultants());
                cumMaterials = cumMaterials.add(otherDirectCostInfo.getmaterials());
                cumPubs = cumPubs.add(otherDirectCostInfo.getpublications());
                cumSubAward = cumSubAward.add(otherDirectCostInfo.getsubAwards());
                cumComputer = cumComputer.add(otherDirectCostInfo.getcomputer());
                cumEquipRental = cumEquipRental.add(otherDirectCostInfo.getEquipRental());
                cumAll = cumAll.add(otherDirectCostInfo.gettotalOtherDirect());

                cumPartStipends = cumPartStipends.add(otherDirectCostInfo.getPartStipends() == null ? ScaleTwoDecimal.ZERO
                        : otherDirectCostInfo.getPartStipends());
                cumPartTravel = cumPartTravel.add(otherDirectCostInfo.getPartTravel() == null ? ScaleTwoDecimal.ZERO
                        : otherDirectCostInfo.getPartTravel());
                cumPartSubsistence = cumPartSubsistence.add(otherDirectCostInfo.getPartSubsistence() == null ? ScaleTwoDecimal.ZERO
                        : otherDirectCostInfo.getPartSubsistence());
                cumPartTuition = cumPartTuition.add(otherDirectCostInfo.getPartTuition() == null ? ScaleTwoDecimal.ZERO
                        : otherDirectCostInfo.getPartTuition());
                cumPartOther = cumPartOther.add(otherDirectCostInfo.getPartOther() == null ? ScaleTwoDecimal.ZERO
                        : otherDirectCostInfo.getPartOther());
                cumParticipantCount = cumParticipantCount
                        + (otherDirectCostInfo.getParticpantTotalCount() == 0 ? 0 : otherDirectCostInfo.getParticpantTotalCount());
                if (budget.getSubmitCostSharingFlag()) {
                    cumAlterationsCostSharing = cumAlterationsCostSharing.add(otherDirectCostInfo.getAlterationsCostSharing());
                    cumConsultantsCostSharing = cumConsultantsCostSharing.add(otherDirectCostInfo.getConsultantsCostSharing());
                    cumMaterialsCostSharing = cumMaterialsCostSharing.add(otherDirectCostInfo.getMaterialsCostSharing());
                    cumPubsCostSharing = cumPubsCostSharing.add(otherDirectCostInfo.getPublicationsCostSharing());
                    cumSubAwardCostSharing = cumSubAwardCostSharing.add(otherDirectCostInfo.getSubAwardsCostSharing());
                    cumComputerCostSharing = cumComputerCostSharing.add(otherDirectCostInfo.getComputerCostSharing());
                    cumEquipRentalCostSharing = cumEquipRentalCostSharing.add(otherDirectCostInfo.getEquipRentalCostSharing());
                    cumAllCostSharing = cumAllCostSharing.add(otherDirectCostInfo.getTotalOtherDirectCostSharing());

                    cumPartStipendsCostSharing = cumPartStipendsCostSharing
                            .add(otherDirectCostInfo.getPartStipendsCostSharing() == null ? ScaleTwoDecimal.ZERO
                                    : otherDirectCostInfo.getPartStipendsCostSharing());
                    cumPartTravelCostSharing = cumPartTravelCostSharing
                            .add(otherDirectCostInfo.getPartTravelCostSharing() == null ? ScaleTwoDecimal.ZERO : otherDirectCostInfo
                                    .getPartTravelCostSharing());
                    cumPartSubsistenceCostSharing = cumPartSubsistenceCostSharing.add(otherDirectCostInfo
                            .getPartSubsistenceCostSharing() == null ? ScaleTwoDecimal.ZERO : otherDirectCostInfo
                            .getPartSubsistenceCostSharing());
                    cumPartTuitionCostSharing = cumPartTuitionCostSharing
                            .add(otherDirectCostInfo.getPartTuitionCostSharing() == null ? ScaleTwoDecimal.ZERO : otherDirectCostInfo
                                    .getPartTuitionCostSharing());

                    cumPartOtherCostSharing = cumPartOtherCostSharing
                            .add(otherDirectCostInfo.getPartOtherCostSharing() == null ? ScaleTwoDecimal.ZERO : otherDirectCostInfo
                                    .getPartOtherCostSharing());
                }
                else {
                    cumAlterationsCostSharing = cumAlterationsCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumConsultantsCostSharing = cumConsultantsCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumMaterialsCostSharing = cumMaterialsCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumPubsCostSharing = cumPubsCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumSubAwardCostSharing = cumSubAwardCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumComputerCostSharing = cumComputerCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumEquipRentalCostSharing = cumEquipRentalCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumAllCostSharing = cumAllCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumPartStipendsCostSharing = cumPartStipendsCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumPartTravelCostSharing = cumPartTravelCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumPartSubsistenceCostSharing = cumPartSubsistenceCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumPartTuitionCostSharing = cumPartTuitionCostSharing.add(ScaleTwoDecimal.ZERO);
                    cumPartOtherCostSharing = cumPartOtherCostSharing.add(ScaleTwoDecimal.ZERO);
                }
                totalDomesticTravel = totalDomesticTravel.add(budgetPeriodInfo.getDomesticTravelCost());
                totalForeignTravel = totalForeignTravel.add(budgetPeriodInfo.getForeignTravelCost());
                totalDomesticTravelNonFund = totalDomesticTravelNonFund.add(budgetPeriodInfo.getDomesticTravelCostSharing());
                totalForeignTravelNonFund = totalForeignTravelNonFund.add(budgetPeriodInfo.getForeignTravelCostSharing());
                totCountOtherPersonnel = totCountOtherPersonnel.add(budgetPeriodInfo.getOtherPersonnelTotalNumber());

                for (EquipmentDto equipmentInfo : budgetPeriodInfo.getEquipment()) {
                    cumTotalEquipFund = cumTotalEquipFund.add(equipmentInfo.getTotalFund());
                    cumTotalEquipNonFund = cumTotalEquipNonFund.add(equipmentInfo.getTotalNonFund());
                }

                Map<String, String> hmOthers = new HashMap<String, String>();
                cvOtherCosts = otherDirectCostInfo.getOtherCosts();
                for (int l = 0; l < cvOtherCosts.size(); l++) {
                    hmOthers = cvOtherCosts.get(l);
                    cumOtherType1 = cumOtherType1.add(new ScaleTwoDecimal(hmOthers.get(CostConstants.KEY_COST)));
                    cumOtherType1CostSharing = cumOtherType1CostSharing.add(new ScaleTwoDecimal(hmOthers
                            .get(CostConstants.KEY_COSTSHARING)));
                }
            }
        }

        budgetSummaryInfo.setCumDomesticTravelNonFund(totalDomesticTravelNonFund);
        budgetSummaryInfo.setCumForeignTravelNonFund(totalForeignTravelNonFund);
        budgetSummaryInfo.setCumTravelNonFund(totalDomesticTravelNonFund.add(totalForeignTravelNonFund));
        budgetSummaryInfo.setCumDomesticTravel(totalDomesticTravel);
        budgetSummaryInfo.setCumForeignTravel(totalForeignTravel);
        budgetSummaryInfo.setCumTravel(totalDomesticTravel.add(totalForeignTravel));
        budgetSummaryInfo.setpartOtherCost(cumPartOther);
        budgetSummaryInfo.setpartStipendCost(cumPartStipends);
        budgetSummaryInfo.setpartTravelCost(cumPartTravel);
        budgetSummaryInfo.setPartSubsistence(cumPartSubsistence);
        budgetSummaryInfo.setPartTuition(cumPartTuition);
        budgetSummaryInfo.setparticipantCount(cumParticipantCount);

        if (budget.getSubmitCostSharingFlag()) {
            // add costSaring for fedNonFedBudget report
            budgetSummaryInfo.setPartOtherCostSharing(cumPartOtherCostSharing);
            budgetSummaryInfo.setPartStipendCostSharing(cumPartStipendsCostSharing);
            budgetSummaryInfo.setPartTravelCostSharing(cumPartTravelCostSharing);
            budgetSummaryInfo.setPartSubsistenceCostSharing(cumPartSubsistenceCostSharing);
            budgetSummaryInfo.setPartTuitionCostSharing(cumPartTuitionCostSharing);
        }

        OtherDirectCostInfoDto summaryOtherDirectCostInfo = new OtherDirectCostInfoDto();
        summaryOtherDirectCostInfo.setAlterations(cumAlterations);
        summaryOtherDirectCostInfo.setcomputer(cumComputer);
        summaryOtherDirectCostInfo.setConsultants(cumConsultants);
        summaryOtherDirectCostInfo.setmaterials(cumMaterials);
        summaryOtherDirectCostInfo.setpublications(cumPubs);
        summaryOtherDirectCostInfo.setsubAwards(cumSubAward);
        summaryOtherDirectCostInfo.setEquipRental(cumEquipRental);
        summaryOtherDirectCostInfo.settotalOtherDirect(cumAll);

        summaryOtherDirectCostInfo.setPartStipends(cumPartStipends);
        summaryOtherDirectCostInfo.setPartTravel(cumPartTravel);
        summaryOtherDirectCostInfo.setPartSubsistence(cumPartSubsistence);
        summaryOtherDirectCostInfo.setPartTuition(cumPartTuition);
        summaryOtherDirectCostInfo.setPartOther(cumPartOther);
        summaryOtherDirectCostInfo.setParticipantTotal(cumPartStipends.add(cumPartTravel.add(cumPartOther.add(cumPartSubsistence
                .add(cumPartTravel)))));
        summaryOtherDirectCostInfo.setParticipantTotalCount(cumParticipantCount);
        // start add costSaring for fedNonFedBudget report
        summaryOtherDirectCostInfo.setAlterationsCostSharing(cumAlterationsCostSharing);
        summaryOtherDirectCostInfo.setComputerCostSharing(cumComputerCostSharing);
        summaryOtherDirectCostInfo.setConsultantsCostSharing(cumConsultantsCostSharing);
        summaryOtherDirectCostInfo.setMaterialsCostSharing(cumMaterialsCostSharing);
        summaryOtherDirectCostInfo.setPublicationsCostSharing(cumPubsCostSharing);
        summaryOtherDirectCostInfo.setSubAwardsCostSharing(cumSubAwardCostSharing);
        summaryOtherDirectCostInfo.setEquipRentalCostSharing(cumEquipRentalCostSharing);
        summaryOtherDirectCostInfo.setTotalOtherDirectCostSharing(cumAllCostSharing);

        summaryOtherDirectCostInfo.setPartStipendsCostSharing(cumPartStipendsCostSharing);
        summaryOtherDirectCostInfo.setPartTravelCostSharing(cumPartTravelCostSharing);
        summaryOtherDirectCostInfo.setPartTuitionCostSharing(cumPartTuitionCostSharing);
        summaryOtherDirectCostInfo.setPartSubsistenceCostSharing(cumPartSubsistenceCostSharing);
        summaryOtherDirectCostInfo.setPartOtherCostSharing(cumPartOtherCostSharing);

        participantTotalCostSharing = participantTotalCostSharing.add(cumPartStipendsCostSharing);
        participantTotalCostSharing = participantTotalCostSharing.add(cumPartTravelCostSharing);
        participantTotalCostSharing = participantTotalCostSharing.add(cumPartOtherCostSharing);
        participantTotalCostSharing = participantTotalCostSharing.add(cumPartTuitionCostSharing);
        participantTotalCostSharing = participantTotalCostSharing.add(cumPartSubsistenceCostSharing);
        summaryOtherDirectCostInfo.setParticipantTotalCostSharing(participantTotalCostSharing);

        List<Map<String, String>> cvAllOthers = new ArrayList<Map<String, String>>();
        HashMap<String, String> hmAllOthers = new HashMap<String, String>();
        hmAllOthers.put(CostConstants.KEY_COST, cumOtherType1.toString());
        hmAllOthers.put(CostConstants.KEY_COSTSHARING, cumOtherType1CostSharing.toString());
        cvAllOthers.add(hmAllOthers);
        summaryOtherDirectCostInfo.setOtherCosts(cvAllOthers);

        List<OtherDirectCostInfoDto> cvCumOtherDirectCost = new ArrayList<OtherDirectCostInfoDto>(); // all
        // periods
        cvCumOtherDirectCost.add(summaryOtherDirectCostInfo);
        budgetSummaryInfo.setOtherDirectCosts(cvCumOtherDirectCost);

        budgetSummaryInfo.setCumEquipmentFunds(cumTotalEquipFund);
        budgetSummaryInfo.setCumEquipmentNonFunds(cumTotalEquipNonFund);

        // hardcoded
        budgetSummaryInfo.setCumFee(ScaleTwoDecimal.ZERO);

        ScaleTwoDecimal totSrFunds = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totSrNonFunds = ScaleTwoDecimal.ZERO;

        if (budgetPeriodInfos != null) {
            for (BudgetPeriodDto budgetPeriodInfo : budgetPeriodInfos) {
                totSrFunds = totSrFunds.add(budgetPeriodInfo.getTotalFundsKeyPersons());
                totSrNonFunds = totSrNonFunds.add(budgetPeriodInfo.getTotalNonFundsKeyPersons());
            }
        }
        budgetSummaryInfo.setCumTotalFundsForSrPersonnel(totSrFunds);
        budgetSummaryInfo.setCumTotalNonFundsForSrPersonnel(totSrNonFunds);

        // other personnel - funds for other personnel will be difference
        // between total person funds and senior person funds
        budgetSummaryInfo.setCumTotalFundsForOtherPersonnel(totPersFunds.subtract(totSrFunds));
        budgetSummaryInfo.setCumTotalNonFundsForOtherPersonnel(totPersNonFunds.subtract(totSrNonFunds));
        budgetSummaryInfo.setCumNumOtherPersonnel(totCountOtherPersonnel);
        return budgetSummaryInfo;
    }

    /**
     * This method gets the list of BudgetPeriodInfo for the latest BudgetDocument of the given ProposalDevelopmentDocumentContract
     * 
     * @param pdDoc ProposalDevelopmentDocumentContract
     * @return a List of BudgetPeriodInfo corresponding to the ProposalDevelopmentDocumentContract object.
     * @throws S2SException
     */
    @Override
    public List<BudgetPeriodDto> getBudgetPeriods(ProposalDevelopmentDocumentContract pdDoc) throws S2SException {
        List<BudgetPeriodDto> budgetPeriods = new ArrayList<BudgetPeriodDto>();
        ProposalDevelopmentBudgetExtContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());
        if (budget == null) {
            return budgetPeriods;
        }

        for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
            BudgetPeriodDto bpData = new BudgetPeriodDto();
            ScaleTwoDecimal totalCostSharing = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal totalDirectCostSharing = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal totalIndirectCostSharing = ScaleTwoDecimal.ZERO;
            bpData.setLineItemCount(budgetPeriod.getBudgetLineItems().size());
            if (budget.getSubmitCostSharingFlag()) {
                for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                    if (lineItem.getSubmitCostSharingFlag()) {
                        totalCostSharing = totalCostSharing.add(lineItem.getCostSharingAmount());
                    }
                    for (BudgetLineItemCalculatedAmountContract lineItemCalculatedAmt : lineItem.getBudgetLineItemCalculatedAmounts()) {
                        if (lineItem.getSubmitCostSharingFlag()) {
                            if (lineItemCalculatedAmt.getRateClass().getRateClassType().getCode()
                                    .equals(RateClassType.OVERHEAD.getRateClassType())) {
                                totalIndirectCostSharing = totalIndirectCostSharing.add(lineItemCalculatedAmt
                                        .getCalculatedCostSharing());
                            }
                            else {
                                totalDirectCostSharing = totalDirectCostSharing.add(lineItemCalculatedAmt
                                        .getCalculatedCostSharing());
                            }
                        }
                    }
                }
                totalDirectCostSharing = totalDirectCostSharing.add(totalCostSharing);
            }

            // populate the budgetPeriod data from the BudgetPeriod
            bpData.setFinalVersionFlag(budget.getFinalVersionFlag().toString());
            bpData.setProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
            bpData.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
            bpData.setVersion(budget.getBudgetVersionNumber());
            bpData.setStartDate(new java.sql.Date(budgetPeriod.getStartDate().getTime()));
            bpData.setEndDate(new java.sql.Date(budgetPeriod.getEndDate().getTime()));
            bpData.setTotalCosts(budgetPeriod.getTotalCost());
            bpData.setDirectCostsTotal(budgetPeriod.getTotalDirectCost());
            bpData.setTotalIndirectCost(budgetPeriod.getTotalIndirectCost());
            bpData.setCostSharingAmount(budgetPeriod.getCostSharingAmount());
            if (budget.getSubmitCostSharingFlag()) {
                bpData.setTotalDirectCostSharing(totalDirectCostSharing);
                bpData.setTotalIndirectCostSharing(totalIndirectCostSharing);
            }
            bpData.setCognizantFedAgency(getCognizantFedAgency(pdDoc.getDevelopmentProposal()));

            bpData.setIndirectCosts(getIndirectCosts(budget, budgetPeriod));
            bpData.setEquipment(getEquipment(budgetPeriod, budget));
            bpData.setOtherDirectCosts(getOtherDirectCosts(budgetPeriod, budget));
            if (bpData.getOtherDirectCosts().size() > 0) {
                OtherDirectCostInfoDto otherCostInfo = bpData.getOtherDirectCosts().get(0);
                bpData.setDomesticTravelCost(otherCostInfo.getDomTravel());

                bpData.setForeignTravelCost(otherCostInfo.getForeignTravel());
                bpData.setTotalTravelCost(otherCostInfo.getTotTravel());
                if (budget.getSubmitCostSharingFlag()) {
                    // add costSaring for fedNonFedBudget report
                    bpData.setDomesticTravelCostSharing(otherCostInfo.getDomTravelCostSharing());
                    bpData.setForeignTravelCostSharing(otherCostInfo.getForeignTravelCostSharing());
                    bpData.setTotalTravelCostSharing(otherCostInfo.getTotTravelCostSharing());
                }
                // participants
                bpData.setpartOtherCost(otherCostInfo.getPartOther());
                bpData.setpartStipendCost(otherCostInfo.getPartStipends());
                bpData.setpartTravelCost(otherCostInfo.getPartTravel());
                bpData.setPartSubsistence(otherCostInfo.getPartSubsistence());
                bpData.setPartTuition(otherCostInfo.getPartTuition());
                bpData.setparticipantCount(otherCostInfo.getParticpantTotalCount());
                if (budget.getSubmitCostSharingFlag()) {
                    bpData.setPartOtherCostSharing(otherCostInfo.getPartOtherCostSharing());
                    bpData.setPartStipendCostSharing(otherCostInfo.getPartStipendsCostSharing());
                    bpData.setPartTravelCostSharing(otherCostInfo.getPartTravelCostSharing());
                    bpData.setPartTuitionCostSharing(otherCostInfo.getPartTuitionCostSharing());
                    bpData.setPartSubsistenceCostSharing(otherCostInfo.getPartSubsistenceCostSharing());
                }
            }
            List<List<KeyPersonDto>> keyPersonList = getKeyPersons(
                    budgetPeriod,pdDoc,MAX_KEY_PERSON_COUNT, budget);
            List<KeyPersonDto> keyPersons = new ArrayList<KeyPersonDto>();
            List<KeyPersonDto> extraPersons = new ArrayList<KeyPersonDto>();
            if (keyPersonList.size() > 0) {
                keyPersons = keyPersonList.get(0);
            }
            if (keyPersonList.size() > 1) {
                extraPersons = keyPersonList.get(1);
            }

            bpData.setKeyPersons(keyPersons);
            bpData.setExtraKeyPersons(extraPersons);

            ScaleTwoDecimal totalKeyPersonSum = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal totalKeyPersonSumCostSharing = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal totalAttKeyPersonSum = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal totalAttKeyPersonSumCostSharing = ScaleTwoDecimal.ZERO;
            if (keyPersons != null) {
                for (KeyPersonDto keyPerson : keyPersons) {
                    totalKeyPersonSum = totalKeyPersonSum.add(keyPerson.getFundsRequested());
                    if (budget.getSubmitCostSharingFlag()) {
                        totalKeyPersonSumCostSharing = totalKeyPersonSumCostSharing.add(keyPerson.getNonFundsRequested());
                    }
                }
            }

            if (extraPersons != null) {
                for (KeyPersonDto keyPerson : extraPersons) {
                    totalAttKeyPersonSum = totalAttKeyPersonSum.add(keyPerson.getFundsRequested());
                    if (budget.getSubmitCostSharingFlag()) {
                        // start add costSaring for fedNonFedBudget report
                        totalAttKeyPersonSumCostSharing = totalAttKeyPersonSumCostSharing.add(keyPerson.getNonFundsRequested());
                    }
                }
            }
            bpData.setTotalFundsKeyPersons(totalKeyPersonSum.add(totalAttKeyPersonSum));
            bpData.setTotalFundsAttachedKeyPersons(totalAttKeyPersonSum);
            bpData.setTotalNonFundsKeyPersons(totalKeyPersonSumCostSharing.add(totalAttKeyPersonSumCostSharing));
            bpData.setTotalNonFundsAttachedKeyPersons(totalAttKeyPersonSumCostSharing);

            List<OtherPersonnelDto> otherPersonnel = getOtherPersonnel(budgetPeriod, pdDoc);
            bpData.setOtherPersonnel(otherPersonnel);
            ScaleTwoDecimal otherPersonnelCount = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal otherPersonnelTotalFunds = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal otherPersonnelTotalNonFunds = ScaleTwoDecimal.ZERO;

            for (OtherPersonnelDto otherPersonnelInfo : otherPersonnel) {
                otherPersonnelCount = otherPersonnelCount.add(new ScaleTwoDecimal(otherPersonnelInfo.getNumberPersonnel()));
                otherPersonnelTotalFunds = otherPersonnelTotalFunds.add(otherPersonnelInfo.getCompensation().getFundsRequested());
                otherPersonnelTotalNonFunds = otherPersonnelTotalNonFunds.add(otherPersonnelInfo.getCompensation()
                        .getNonFundsRequested());
            }
            bpData.setTotalOtherPersonnelFunds(otherPersonnelTotalFunds);
            bpData.setOtherPersonnelTotalNumber(otherPersonnelCount);
            bpData.setTotalCompensation(otherPersonnelTotalFunds.add(totalKeyPersonSum).add(totalAttKeyPersonSum));
            bpData.setTotalOtherPersonnelNonFunds(otherPersonnelTotalNonFunds);
            bpData.setTotalCompensationCostSharing(otherPersonnelTotalNonFunds.add(totalKeyPersonSumCostSharing).add(
                    totalAttKeyPersonSumCostSharing));

            budgetPeriods.add(bpData);
        }
        return budgetPeriods;
    }

    protected String getCognizantFedAgency(DevelopmentProposalContract developmentProposal) {
        StringBuilder fedAgency = new StringBuilder();
        ProposalSiteContract applicantOrganization = developmentProposal.getApplicantOrganization();
        if (applicantOrganization != null && applicantOrganization.getOrganization() != null
                && applicantOrganization.getOrganization().getCognizantAuditor() != null) {
            fedAgency.append(organizationRepositoryService.getCognizantFedAgency(applicantOrganization.getOrganization()));
        }
        if (fedAgency.toString().length() == 0) {
            fedAgency.append(VALUE_UNKNOWN);
        }
        return fedAgency.toString();
    }

    /**
     * 
     * This method populates the {@link OtherPersonnelDto} business objects for the given {@link org.kuali.coeus.common.budget.api.period.BudgetPeriodContract} and
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param budgetPeriod given budget period.
     * @param pdDoc Proposal Development Document.
     * @return {@link List} of {@link OtherPersonnelDto}
     */
    protected List<OtherPersonnelDto> getOtherPersonnel(BudgetPeriodContract budgetPeriod, ProposalDevelopmentDocumentContract pdDoc) {
        List<OtherPersonnelDto> cvOtherPersonnel = new ArrayList<OtherPersonnelDto>();
        cvOtherPersonnel.add(getOtherPersonnelDetails(
                budgetPeriod,pdDoc,
                s2SConfigurationService.getValueAsString(
                        ConfigurationConstants.S2SBUDGET_CATEGORY_01_GRADUATES),PERSONNEL_TYPE_GRAD,
                ROLE_GRADUATE_STUDENTS));
        cvOtherPersonnel.add(getOtherPersonnelDetails(
                budgetPeriod,pdDoc,
                s2SConfigurationService.getValueAsString(
                        ConfigurationConstants.S2SBUDGET_CATEGORY_01_POSTDOCS),PERSONNEL_TYPE_POSTDOC,
                ROLE_POST_DOCTORAL_ASSOCIATES));
        cvOtherPersonnel.add(getOtherPersonnelDetails(
                budgetPeriod,pdDoc,
                s2SConfigurationService.getValueAsString(
                        ConfigurationConstants.S2SBUDGET_CATEGORY_01_UNDERGRADS),PERSONNEL_TYPE_UNDERGRAD,
                ROLE_GRADUATE_UNDERGRADUATE_STUDENTS));
        cvOtherPersonnel.add(getOtherPersonnelDetails(
                budgetPeriod,pdDoc,
                s2SConfigurationService.getValueAsString(
                        ConfigurationConstants.S2SBUDGET_CATEGORY_01_SECRETARIAL),PERSONNEL_TYPE_SEC,
                ROLE_GRADUATE_SECRETARIAL_OR_CLERICAL));
        cvOtherPersonnel.add(getOtherPersonnelDetails(
                budgetPeriod,pdDoc,
                s2SConfigurationService.getValueAsString(
                        ConfigurationConstants.S2SBUDGET_CATEGORY_01_OTHER),PERSONNEL_TYPE_OTHER,
                ROLE_GRADUATE_OTHER));
        cvOtherPersonnel.add(getOtherPersonnelDetails(
                budgetPeriod,pdDoc,
                s2SConfigurationService.getValueAsString(
                        ConfigurationConstants.S2SBUDGET_CATEGORY_01_OTHER_PROFS),PERSONNEL_TYPE_OTHER_PROFESSIONALS,
                ROLE_GRADUATE_OTHER_PROFESSIONALS));
        cvOtherPersonnel.add(getOtherPersonnelDetails(
                budgetPeriod,pdDoc,
                LASALARIES,PERSONNEL_TYPE_ALLOCATED_ADMIN_SUPPORT,
                ROLE_GRADUATE_ALLOCATED_ADMIN_SUPPORT));
        return cvOtherPersonnel;
    }

    /**
     * 
     * This method populates the details for {@link OtherPersonnelDto} business object for the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param budgetPeriod given budget period.
     * @param pdDoc Proposal Development Document.
     * @param category budget category
     * @param personnelType proposal personnel type.
     * @param role role of the proposal person.
     * @return OtherPersonnelInfo information about the other personnel.
     */
    protected OtherPersonnelDto getOtherPersonnelDetails(BudgetPeriodContract budgetPeriod, ProposalDevelopmentDocumentContract pdDoc,
            String category, String personnelType, String role) {
        OtherPersonnelDto otherPersonnelInfo = new OtherPersonnelDto();

        int count = 0;
        ScaleTwoDecimal salaryRequested = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal salaryCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal lineItemCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal lineItemCostSharingAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal mrLaCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal mrLaCostSharingAmount = ScaleTwoDecimal.ZERO;

        ScaleTwoDecimal fringeCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fringeCostSharingAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal mrLaFringeCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal mrLaFringeCostSharingAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal budgetLineItemFringeCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal budgetLineItemFringeCostSharingAmount = ScaleTwoDecimal.ZERO;

        ScaleTwoDecimal bdSalary = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal bdFringe = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal bdFunds = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal bdSalaryCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal bdFringeCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal bdNonFunds = ScaleTwoDecimal.ZERO;

        BigDecimal academicMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal summerMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal calendarMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal cycleMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        BigDecimal numberOfMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        String rateTypeSupportStaffSalaries = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_RATE_TYPE_SUPPORT_STAFF_SALARIES);
        String rateClassCodeEmployeeBenefits = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_RATE_CLASS_CODE_EMPLOYEE_BENEFITS);
        String rateClassCodeVacation = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_RATE_CLASS_CODE_VACATION);
        String rateTypeAdministrativeSalaries = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_RATE_TYPE_ADMINISTRATIVE_SALARIES);
        Map<String, String> personJobCodes = new HashMap<String, String>();
        boolean personExistsAsProposalPerson = false;
        
        // Calculate the salary and fringe for category
        // LASALARIES
        if (category.equalsIgnoreCase(LASALARIES)) {
            for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                // Caluclate LA for rate class type Y
                for (BudgetLineItemCalculatedAmountContract lineItemCalculatedAmount : lineItem
                        .getBudgetLineItemCalculatedAmounts()) {
                    if (lineItemCalculatedAmount
                            .getRateClass()
                            .getRateClassType().getCode()
                            .equals(s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_LAB_ALLOCATION_SALARIES))) {
                        mrLaCost = mrLaCost.add(lineItemCalculatedAmount.getCalculatedCost());
                        if(lineItem.getSubmitCostSharingFlag()){
                            mrLaCostSharingAmount = mrLaCostSharingAmount.add(lineItemCalculatedAmount
                                    .getCalculatedCostSharing());
                        }
                    }

                    // Calculate the fringe
                    if ((lineItemCalculatedAmount
                            .getRateClass()
                            .getRateClassType().getCode()
                            .equals(s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_EMPLOYEE_BENEFITS)) && lineItemCalculatedAmount
                                    .getRateTypeCode().equals(
                                            rateTypeSupportStaffSalaries))
                                            || (lineItemCalculatedAmount
                                                    .getRateClass()
                                                    .getRateClassType().getCode()
                                                    .equals(s2SConfigurationService.getValueAsString(
                                                            ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_VACATION)) && lineItemCalculatedAmount
                                                            .getRateTypeCode().equals(
                                                                    rateTypeAdministrativeSalaries))) {
                        mrLaFringeCost = mrLaFringeCost.add(lineItemCalculatedAmount.getCalculatedCost());
                        if(lineItem.getSubmitCostSharingFlag()){
                            mrLaFringeCostSharingAmount = mrLaFringeCostSharingAmount.add(lineItemCalculatedAmount
                                    .getCalculatedCostSharing());
                        }
                    }
                }
            }
        }
        else{
            for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                List<? extends BudgetCategoryMappingContract> budgetCategoryList = budgetCategoryMapService.findCatMappingByTargetAndMappingName(category, SPONSOR);

                for (BudgetCategoryMappingContract categoryMapping : budgetCategoryList) {
                    if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategory().getCode())) {
                        List<? extends BudgetPersonnelDetailsContract> lineItemPersonDetails = lineItem.getBudgetPersonnelDetailsList();
                        boolean personExist = !lineItemPersonDetails.isEmpty();
                        if (personExist) {
                            for (BudgetPersonnelDetailsContract personDetails : lineItemPersonDetails) {
                                if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategory().getCode())) {
                                    String budgetPersonId = personDetails.getPersonId();
                                    personExistsAsProposalPerson = false;
                                    // get sum of salary of other personnel, but
                                    // exclude the key persons and investigators
                                    for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                                        if (budgetPersonId.equals(proposalPerson.getPersonId())
                                                || (proposalPerson.getRolodexId() != null && budgetPersonId.equals(proposalPerson
                                                        .getRolodexId().toString()))) {
                                            personExistsAsProposalPerson = true;
                                            break;
                                        }
                                    }
                                    if (!personExistsAsProposalPerson) {
                                        salaryRequested = salaryRequested.add(personDetails.getSalaryRequested());
                                        if(lineItem.getSubmitCostSharingFlag()){
                                            salaryCostSharing = salaryCostSharing.add(personDetails.getCostSharingAmount());
                                        }
                                        numberOfMonths = s2SDateTimeService.getNumberOfMonths(personDetails.getStartDate(),
                                                personDetails.getEndDate()).bigDecimalValue();
                                        if (personDetails.getPeriodTypeCode().equals(
                                                s2SConfigurationService.getValueAsString(
                                                        ConfigurationConstants.S2SBUDGET_PERIOD_TYPE_ACADEMIC_MONTHS))) {
                                            if (lineItem.getSubmitCostSharingFlag()) {
                                                academicMonths = academicMonths.add(personDetails.getPercentEffort().bigDecimalValue()
                                                        .multiply(numberOfMonths).multiply(POINT_ZERO_ONE));
                                            } else {
                                                academicMonths = academicMonths.add(personDetails.getPercentCharged().bigDecimalValue()
                                                        .multiply(numberOfMonths).multiply(POINT_ZERO_ONE));
                                            }                                            
                                        }
                                        else if (personDetails.getPeriodTypeCode().equals(
                                                s2SConfigurationService.getValueAsString(
                                                        ConfigurationConstants.S2SBUDGET_PERIOD_TYPE_SUMMER_MONTHS))) {
                                            if (lineItem.getSubmitCostSharingFlag()) {
                                                summerMonths = summerMonths.add(personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths)
                                                        .multiply(POINT_ZERO_ONE));
                                            } else {
                                                summerMonths = summerMonths.add(personDetails.getPercentCharged().bigDecimalValue().multiply(numberOfMonths)
                                                        .multiply(POINT_ZERO_ONE));
                                            }
                                            
                                        }
                                        else if (personDetails.getPeriodTypeCode().equals(
                                                s2SConfigurationService.getValueAsString(
                                                        ConfigurationConstants.S2SBUDGET_PERIOD_TYPE_CALENDAR_MONTHS))) {
                                            if (lineItem.getSubmitCostSharingFlag()) {
                                                calendarMonths = calendarMonths.add(personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths)
                                                        .multiply(POINT_ZERO_ONE));
                                            } else {
                                                calendarMonths = calendarMonths.add(personDetails.getPercentCharged().bigDecimalValue().multiply(numberOfMonths)
                                                        .multiply(POINT_ZERO_ONE));
                                            }
                                        }
                                        else if (personDetails.getPeriodTypeCode().equals(
                                                s2SConfigurationService.getValueAsString(
                                                        ConfigurationConstants.S2SBUDGET_PERIOD_TYPE_CYCLE_MONTHS))) {
                                            cycleMonths = cycleMonths.add(personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths)
                                                    .multiply(POINT_ZERO_ONE));
                                        }
                                        // Get total count of unique
                                        // personId+jobCode combination for those
                                        // persons who are part of
                                        // BudgetPersonnelDetails but are not
                                        // proposal persons
                                        personJobCodes.put(personDetails.getPersonId() + personDetails.getJobCode(), "");
                                        // Calcculate the fringe cost
                                        for (BudgetPersonnelCalculatedAmountContract personCalculatedAmount :  personDetails
                                                .getBudgetPersonnelCalculatedAmounts()) {
                                            if ((personCalculatedAmount.getRateClass().getCode().equals(
                                                    rateClassCodeEmployeeBenefits) && !personCalculatedAmount
                                                    .getRateTypeCode().equals(
                                                            rateTypeSupportStaffSalaries))
                                                            || (personCalculatedAmount.getRateClass().getCode().equals(
                                                                    rateClassCodeVacation) && !personCalculatedAmount
                                                                    .getRateTypeCode().equals(
                                                                            rateTypeAdministrativeSalaries))) {
                                                fringeCost = fringeCost.add(personCalculatedAmount.getCalculatedCost());
                                                if(lineItem.getSubmitCostSharingFlag()){
                                                    fringeCostSharingAmount = fringeCostSharingAmount.add(personCalculatedAmount
                                                            .getCalculatedCostSharing());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            // personExist is false. No person found for the line
                            // item.
                            // get costs for this budget category that do not have
                            // persons attached to the cost element
                            lineItemCost = lineItemCost.add(lineItem.getLineItemCost());
                            if(lineItem.getSubmitCostSharingFlag()){
                                lineItemCostSharingAmount = lineItemCostSharingAmount.add(lineItem.getCostSharingAmount());
                            }
                            count = lineItem.getQuantity();
                            for (BudgetLineItemCalculatedAmountContract lineItemCalculatedAmount : lineItem
                                    .getBudgetLineItemCalculatedAmounts()) {

                                // Calculate fringe cost
                                if (lineItemCalculatedAmount.getRateClass().getRateClassType().getCode().equalsIgnoreCase("E")) {
                                    fringeCost = fringeCost.add(lineItemCalculatedAmount.getCalculatedCost());
                                }
                                if ((lineItemCalculatedAmount.getRateClass().getCode().equals(
                                        rateClassCodeEmployeeBenefits) && !lineItemCalculatedAmount
                                        .getRateTypeCode().equals(
                                                rateTypeSupportStaffSalaries))
                                                || (lineItemCalculatedAmount.getRateClass().getCode().equals(
                                                        rateClassCodeVacation) && !lineItemCalculatedAmount
                                                        .getRateTypeCode().equals(
                                                                rateTypeAdministrativeSalaries))) {
                                    if(lineItem.getSubmitCostSharingFlag()){
                                        fringeCostSharingAmount = fringeCostSharingAmount.add(lineItemCalculatedAmount
                                                .getCalculatedCostSharing());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // Set the salary amounts
        bdSalary = bdSalary.add(salaryRequested).add(lineItemCost).add(mrLaCost);
        bdSalaryCostSharing = bdSalaryCostSharing.add(salaryCostSharing).add(lineItemCostSharingAmount).add(mrLaCostSharingAmount);

        // Set the fringe costs
        bdFringe = bdFringe.add(fringeCost).add(budgetLineItemFringeCost).add(mrLaFringeCost);
        bdFringeCostSharing = bdFringeCostSharing.add(fringeCostSharingAmount).add(budgetLineItemFringeCostSharingAmount)
                .add(mrLaFringeCostSharingAmount);

        bdNonFunds = bdSalaryCostSharing.add(bdFringeCostSharing);
        bdFunds = bdSalary.add(bdFringe);

        count = personJobCodes.isEmpty() ? count : personJobCodes.size();
        otherPersonnelInfo.setNumberPersonnel(count);
        otherPersonnelInfo.setPersonnelType(personnelType);
        otherPersonnelInfo.setRole(role);

        CompensationDto compensationInfo = new CompensationDto();
        // not sure that we need base salary
        compensationInfo.setBaseSalary(ScaleTwoDecimal.ZERO);
        compensationInfo.setFringe(bdFringe);
        compensationInfo.setFundsRequested(bdFunds);
        compensationInfo.setRequestedSalary(bdSalary);
        compensationInfo.setSummerMonths(new ScaleTwoDecimal(summerMonths));
        compensationInfo.setAcademicMonths(new ScaleTwoDecimal(academicMonths));
        compensationInfo.setCalendarMonths(new ScaleTwoDecimal(calendarMonths));

        // start add costSaring for fedNonFedBudget report
        compensationInfo.setFringeCostSharing(bdFringeCostSharing);
        compensationInfo.setNonFundsRequested(bdNonFunds);
        compensationInfo.setCostSharingAmount(bdSalaryCostSharing);
        // end add costSaring for fedNonFedBudget report

        otherPersonnelInfo.setCompensation(compensationInfo);
        return otherPersonnelInfo;
    }

    /**
     * 
     * This method computes the indirect costs for a given {@link org.kuali.coeus.common.budget.api.period.BudgetPeriodContract}
     * 
     * @param budgetPeriod given BudgetPeriod.
     * @return IndirectCostInfo for the corresponding BudgetPeriod object.
     */
    @Override
    public IndirectCostDto getIndirectCosts(BudgetContract budget, BudgetPeriodContract budgetPeriod) {
        List<IndirectCostDetailsDto> indirectCostDetailList = new ArrayList<IndirectCostDetailsDto>();
        IndirectCostDetailsDto indirectCostDetails;
        ScaleTwoDecimal baseCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal baseCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal calculatedCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal appliedRate = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCosts = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCostSharing = ScaleTwoDecimal.ZERO;

        String description = "";
        boolean firstLoop = true;

        if (budget.getModularBudgetFlag()) {
            for (BudgetModularIdcContract budgetModularIdc : budgetPeriod.getBudgetModular().getBudgetModularIdcs()) {
                if (firstLoop) {
                    appliedRate = appliedRate.add(budgetModularIdc.getIdcRate());
                    description = budgetModularIdc.getDescription();
                    firstLoop = false;
                }
                baseCost = baseCost.add(budgetModularIdc.getIdcBase());
                calculatedCost = calculatedCost.add(budgetModularIdc.getFundsRequested());
            }
            indirectCostDetails = new IndirectCostDetailsDto();
            indirectCostDetails.setBase(baseCost);
            indirectCostDetails.setBaseCostSharing(baseCostSharing);
            indirectCostDetails.setCostSharing(calculatedCostSharing);
            indirectCostDetails.setCostType(description);
            indirectCostDetails.setFunds(calculatedCost);
            indirectCostDetails.setRate(appliedRate);
            indirectCostDetailList.add(indirectCostDetails);
            totalIndirectCosts = totalIndirectCosts.add(calculatedCost);
            totalIndirectCostSharing = totalIndirectCostSharing.add(calculatedCostSharing);
        }
        else {
            Map<String, IndirectCostDetailsDto> costDetailsMap = new HashMap<String, IndirectCostDetailsDto>();
            for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                for (BudgetRateAndBaseContract rateBase : lineItem.getBudgetRateAndBaseList()) {
                    RateClassContract rateClass = rateBase.getRateClass();
                    if (rateClass.getRateClassType().getCode().equals(RateClassType.OVERHEAD.getRateClassType())) {
                        String rateClassCode = rateClass.getCode();
                        String rateTypeCode = rateBase.getRateTypeCode();
                        appliedRate = rateBase.getAppliedRate();
                        StringBuilder keyBuilder = new StringBuilder();
                        keyBuilder.append(rateClassCode);
                        keyBuilder.append("-");
                        keyBuilder.append(rateTypeCode);
                        keyBuilder.append("-");
                        keyBuilder.append(appliedRate);
                        String key = keyBuilder.toString();
                        if (costDetailsMap.get(key) == null) {
                            indirectCostDetails = new IndirectCostDetailsDto();
                            indirectCostDetails.setBase(rateBase.getBaseCost() == null ? ScaleTwoDecimal.ZERO : rateBase
                                    .getBaseCost());
                            indirectCostDetails.setBaseCostSharing(rateBase.getBaseCostSharing() == null ? ScaleTwoDecimal.ZERO
                                    : rateBase.getBaseCostSharing());
                            if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                                indirectCostDetails.setCostSharing(rateBase.getCalculatedCostSharing() == null ? ScaleTwoDecimal.ZERO
                                        : rateBase.getCalculatedCostSharing());
                            }
                            indirectCostDetails.setCostType(rateClass.getDescription());
                            indirectCostDetails.setFunds(rateBase.getCalculatedCost() == null ? ScaleTwoDecimal.ZERO : rateBase
                                    .getCalculatedCost());
                            indirectCostDetails.setRate(appliedRate);
                        }
                        else {
                            indirectCostDetails = costDetailsMap.get(key);
                            baseCost = indirectCostDetails.getBase().add(
                                    rateBase.getBaseCost() == null ? ScaleTwoDecimal.ZERO : rateBase.getBaseCost());
                            baseCostSharing = indirectCostDetails.getBaseCostSharing().add(
                                    rateBase.getBaseCostSharing() == null ? ScaleTwoDecimal.ZERO : rateBase.getBaseCostSharing());
                            calculatedCost = indirectCostDetails.getFunds().add(
                                    rateBase.getCalculatedCost() == null ? ScaleTwoDecimal.ZERO : rateBase.getCalculatedCost());
                            if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                                calculatedCostSharing = indirectCostDetails.getCostSharing().add(
                                        rateBase.getCalculatedCostSharing() == null ? ScaleTwoDecimal.ZERO : rateBase
                                                .getCalculatedCostSharing());
                            } else if (!lineItem.getSubmitCostSharingFlag()&& budget.getSubmitCostSharingFlag()) {
                                calculatedCostSharing = indirectCostDetails.getCostSharing();
                            }
                            indirectCostDetails.setBase(baseCost);
                            indirectCostDetails.setBaseCostSharing(baseCostSharing);
                            indirectCostDetails.setCostSharing(calculatedCostSharing);
                            indirectCostDetails.setFunds(calculatedCost);
                        }
                        costDetailsMap.put(key, indirectCostDetails);

                        indirectCostDetailList = new ArrayList<IndirectCostDetailsDto>(costDetailsMap.values());
                        totalIndirectCosts = totalIndirectCosts.add(rateBase.getCalculatedCost() == null ? ScaleTwoDecimal.ZERO
                                : rateBase.getCalculatedCost());
                        if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                            totalIndirectCostSharing = totalIndirectCostSharing
                                    .add(rateBase.getCalculatedCostSharing() == null ? ScaleTwoDecimal.ZERO : rateBase
                                            .getCalculatedCostSharing());
                        }
                    }
                }
            }
        }
        IndirectCostDto indirectCostInfo = new IndirectCostDto();
        indirectCostInfo.setIndirectCostDetails(indirectCostDetailList);
        indirectCostInfo.setTotalIndirectCosts(totalIndirectCosts);
        indirectCostInfo.setTotalIndirectCostSharing(totalIndirectCostSharing);
        return indirectCostInfo;
    }

    /**
     * 
     * This method computes Other Dirtect Costs for the given {@link org.kuali.coeus.common.budget.api.period.BudgetPeriodContract} and Sponsor
     * 
     * @param budgetPeriod given BudgetPeriod.
     * @return List<OtherDirectCostInfo> list of OtherDirectCostInfo corresponding to the BudgetPeriod object.
     */
    protected List<OtherDirectCostInfoDto> getOtherDirectCosts(BudgetPeriodContract budgetPeriod, BudgetContract budget) {
        OtherDirectCostInfoDto otherDirectCostInfo = new OtherDirectCostInfoDto();

        List<CostDto> costInfoList = new ArrayList<CostDto>();
        List<String> filterTargetCategoryCodes = new ArrayList<String>();
        filterTargetCategoryCodes.add(s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_TARGET_CATEGORY_CODE_EQUIPMENT_COST));
        List<String> filterCategoryTypes = new ArrayList<String>();
        filterCategoryTypes.add(s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_FILTER_CATEGORY_TYPE_PERSONNEL));
        List<? extends BudgetCategoryMapContract> budgetCategoryMapList = getBudgetCategoryMapList(filterTargetCategoryCodes, filterCategoryTypes);

        boolean recordAdded;
        for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetCategoryMapContract budgetCategoryMap : budgetCategoryMapList) {
                recordAdded = false;
                for (BudgetCategoryMappingContract budgetCategoryMapping : budgetCategoryMap.getBudgetCategoryMappings()) {
                    if (lineItem.getBudgetCategory().getCode().equals(budgetCategoryMapping.getBudgetCategoryCode())) {
                        CostDto costInfo = new CostDto();
                        costInfo.setBudgetPeriod(budgetPeriod.getBudgetPeriod().intValue());
                        costInfo.setCost(lineItem.getLineItemCost());
                        if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                            costInfo.setCostSharing(lineItem.getCostSharingAmount());
                        }
                        costInfo.setCategory(budgetCategoryMap.getDescription());
                        costInfo.setCategoryType(budgetCategoryMap.getCategoryType());
                        if (lineItem.getQuantity() != null) {
                            costInfo.setQuantity(lineItem.getQuantity());
                        }
                        costInfoList.add(costInfo);
                        recordAdded = true;
                        break;
                    }
                }
                if (recordAdded) {
                    break;
                }
            }

            CostDto lineItemcostInfo = new CostDto();
            lineItemcostInfo.setBudgetPeriod(budgetPeriod.getBudgetPeriod().intValue());
            lineItemcostInfo.setCategory(OTHER_DIRECT_COSTS);
            lineItemcostInfo.setCategoryType(CATEGORY_TYPE_OTHER_DIRECT_COST);
            lineItemcostInfo.setQuantity(1);

            ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal totalCostSharing = ScaleTwoDecimal.ZERO;
            for (BudgetLineItemCalculatedAmountContract lineItemCalculatedAmt : lineItem.getBudgetLineItemCalculatedAmounts()) {
                if (lineItemCalculatedAmt
                        .getRateClass()
                        .getRateClassType().getCode()
                        .equals(s2SConfigurationService.getValueAsString(
                                ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_SALARIES_MS))) {
                    totalCost = totalCost.add(lineItemCalculatedAmt.getCalculatedCost());
                    if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                        totalCostSharing = totalCostSharing.add(lineItemCalculatedAmt.getCalculatedCostSharing());
                    }
                }
            }
            lineItemcostInfo.setCost(totalCost);
            if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                lineItemcostInfo.setCostSharing(totalCostSharing);
            }
            StringBuilder description = new StringBuilder();
            description.append(DESCRIPTION_LA);
            description.append(totalCost);
            description.append(";");
            lineItemcostInfo.setDescription(description.toString());
            costInfoList.add(lineItemcostInfo);
        }

        ScaleTwoDecimal totalOtherDirect = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalTravelCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalParticipantCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalOtherDirectCostSharing = ScaleTwoDecimal.ZERO;

        ScaleTwoDecimal totalTravelCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalParticipantCostSharing = ScaleTwoDecimal.ZERO;

        ScaleTwoDecimal materialCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal materialCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal consultantCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal consultantCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal publicationCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal publicationCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal computerCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal computerCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal alterationsCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal alterationsCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal subContractCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal subContractCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal equipmentRentalCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal equipmentRentalCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal domesticTravelCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal domesticTravelCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal foreignTravelCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal foreignTravelCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partStipendsCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partStipendsCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partTravelCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partTravelCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partTuitionCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partTuitionCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partSubsistenceCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partSubsistenceCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partOtherCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal partOtherCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal otherDirectCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal otherDirectCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal otherCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal otherCostSharing = ScaleTwoDecimal.ZERO;

        for (CostDto costInfo : costInfoList) {
            if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_MATERIALS_AND_SUPPLIES_CATEGORY))) {
                if (budget.getSubmitCostSharingFlag()) {
                    materialCostSharing = materialCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_CONSULTANT_COSTS_CATEGORY))) {
                consultantCost = consultantCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    consultantCostSharing = consultantCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_PUBLICATION_COSTS_CATEGORY))) {
                publicationCost = publicationCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    publicationCostSharing = publicationCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_COMPUTER_SERVICES_CATEGORY))) {
                computerCost = computerCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    computerCostSharing = computerCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_ALTERATIONS_CATEGORY))) {
                alterationsCost = alterationsCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    alterationsCostSharing = alterationsCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_SUBCONTRACT_CATEGORY))) {
                subContractCost = subContractCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    subContractCostSharing = subContractCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_EQUIPMENT_RENTAL_CATEGORY))) {
                equipmentRentalCost = equipmentRentalCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    equipmentRentalCostSharing = equipmentRentalCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_DOMESTIC_TRAVEL_CATEGORY))) {
                domesticTravelCost = domesticTravelCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    domesticTravelCostSharing = domesticTravelCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_FOREIGN_TRAVEL_CATEGORY))) {
                foreignTravelCost = foreignTravelCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    foreignTravelCostSharing = foreignTravelCostSharing.add(costInfo.getCostSharing());
                }
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_PARTICIPANT_STIPENDS_CATEGORY))) {
                partStipendsCost = partStipendsCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    partStipendsCostSharing = partStipendsCostSharing.add(costInfo.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing.add(costInfo.getCostSharing());
                }
                totalParticipantCost = totalParticipantCost.add(costInfo.getCost());
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_PARTICIPANT_TRAVEL_CATEGORY))) {
                partTravelCost = partTravelCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    partTravelCostSharing = partTravelCostSharing.add(costInfo.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing.add(costInfo.getCostSharing());
                }
                totalParticipantCost = totalParticipantCost.add(costInfo.getCost());
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_PARTICIPANT_TUITION_CATEGORY))) {
                partTuitionCost = partTuitionCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    partTuitionCostSharing = partTuitionCostSharing.add(costInfo.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing.add(costInfo.getCostSharing());
                }
                totalParticipantCost = totalParticipantCost.add(costInfo.getCost());
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_PARTICIPANT_SUBSISTENCE_CATEGORY))) {
                partSubsistenceCost = partSubsistenceCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    partSubsistenceCostSharing = partSubsistenceCostSharing.add(costInfo.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing.add(costInfo.getCostSharing());
                }
                totalParticipantCost = totalParticipantCost.add(costInfo.getCost());
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_PARTICIPANT_OTHER_CATEGORY))) {
                partOtherCost = partOtherCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    partOtherCostSharing = partOtherCostSharing.add(costInfo.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing.add(costInfo.getCostSharing());
                }
                totalParticipantCost = totalParticipantCost.add(costInfo.getCost());
            }
            else if (costInfo.getCategory().equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2SBUDGET_OTHER_DIRECT_COSTS_CATEGORY))) {
                otherDirectCost = otherDirectCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    otherDirectCostSharing = otherDirectCostSharing.add(costInfo.getCostSharing());
                }
            }
            else {
                otherCost = otherCost.add(costInfo.getCost());
                if (budget.getSubmitCostSharingFlag()) {
                    otherCostSharing = otherCostSharing.add(costInfo.getCostSharing());
                }
            }
        }

        otherDirectCostInfo.setmaterials(materialCost);
        otherDirectCostInfo.setMaterialsCostSharing(materialCostSharing);
        otherDirectCostInfo.setConsultants(consultantCost);
        otherDirectCostInfo.setConsultantsCostSharing(consultantCostSharing);
        otherDirectCostInfo.setpublications(publicationCost);
        otherDirectCostInfo.setPublicationsCostSharing(publicationCostSharing);
        otherDirectCostInfo.setcomputer(computerCost);
        otherDirectCostInfo.setComputerCostSharing(computerCostSharing);
        otherDirectCostInfo.setAlterations(alterationsCost);
        otherDirectCostInfo.setAlterationsCostSharing(alterationsCostSharing);
        otherDirectCostInfo.setsubAwards(subContractCost);
        otherDirectCostInfo.setSubAwardsCostSharing(subContractCostSharing);
        otherDirectCostInfo.setEquipRental(equipmentRentalCost);
        otherDirectCostInfo.setEquipRentalCostSharing(equipmentRentalCostSharing);
        otherDirectCostInfo.setDomTravel(domesticTravelCost);
        otherDirectCostInfo.setDomTravelCostSharing(domesticTravelCostSharing);
        otherDirectCostInfo.setForeignTravel(foreignTravelCost);
        otherDirectCostInfo.setForeignTravelCostSharing(foreignTravelCostSharing);
        otherDirectCostInfo.setPartStipends(partStipendsCost);
        otherDirectCostInfo.setPartStipendsCostSharing(partStipendsCostSharing);
        otherDirectCostInfo.setPartTravel(partTravelCost);
        otherDirectCostInfo.setPartTravelCostSharing(partTravelCostSharing);
        otherDirectCostInfo.setPartTuition(partTuitionCost);
        otherDirectCostInfo.setPartTuitionCostSharing(partTuitionCostSharing);
        otherDirectCostInfo.setPartSubsistence(partSubsistenceCost);
        otherDirectCostInfo.setPartSubsistenceCostSharing(partSubsistenceCostSharing);
        otherDirectCostInfo.setPartOther(partOtherCost);
        otherDirectCostInfo.setPartOtherCostSharing(partOtherCostSharing);
        otherDirectCostInfo.setParticipantTotal(totalParticipantCost);
        otherDirectCostInfo.setParticipantTotalCostSharing(totalParticipantCostSharing);
        otherDirectCostInfo.setParticipantTotalCount(budgetPeriod.getNumberOfParticipants() == null ? 0 : budgetPeriod.getNumberOfParticipants());

        totalOtherDirect = totalOtherDirect.add(materialCost);
        totalOtherDirect = totalOtherDirect.add(consultantCost);
        totalOtherDirect = totalOtherDirect.add(publicationCost);
        totalOtherDirect = totalOtherDirect.add(computerCost);
        totalOtherDirect = totalOtherDirect.add(alterationsCost);
        totalOtherDirect = totalOtherDirect.add(subContractCost);
        totalOtherDirect = totalOtherDirect.add(equipmentRentalCost);
        totalOtherDirect = totalOtherDirect.add(otherDirectCost);
        totalOtherDirect = totalOtherDirect.add(otherCost);

        totalTravelCost = totalTravelCost.add(domesticTravelCost);
        totalTravelCost = totalTravelCost.add(foreignTravelCost);
        if (budget.getSubmitCostSharingFlag()) {
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(materialCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(consultantCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(publicationCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(computerCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(alterationsCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(subContractCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(equipmentRentalCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(otherDirectCostSharing);
            totalOtherDirectCostSharing = totalOtherDirectCostSharing.add(otherCostSharing);

            totalTravelCostSharing = totalTravelCostSharing.add(domesticTravelCostSharing);
            totalTravelCostSharing = totalTravelCostSharing.add(foreignTravelCostSharing);
        }
        otherDirectCostInfo.settotalOtherDirect(totalOtherDirect);
        otherDirectCostInfo.setTotalOtherDirectCostSharing(totalOtherDirectCostSharing);
        otherDirectCostInfo.setTotTravel(totalTravelCost);
        otherDirectCostInfo.setTotTravelCostSharing(totalTravelCostSharing);

        List<Map<String, String>> otherCostDetails = new ArrayList<Map<String, String>>();
        Map<String, String> hmOtherDirectCostDetails = new HashMap<String, String>();
        hmOtherDirectCostDetails.put(CostConstants.KEY_COST, otherDirectCost.toString());
        hmOtherDirectCostDetails
                .put(DESCRIPTION,OTHER_DIRECT_COSTS);
        hmOtherDirectCostDetails.put(CostConstants.KEY_COSTSHARING, otherDirectCostSharing.toString());
        otherCostDetails.add(hmOtherDirectCostDetails);

        Map<String, String> hmOtherCostDetails = new HashMap<String, String>();
        hmOtherCostDetails.put(CostConstants.KEY_COST, otherCost.toString());
        hmOtherCostDetails
                .put(DESCRIPTION,ALL_OTHER_COSTS);
        hmOtherCostDetails.put(CostConstants.KEY_COSTSHARING, otherCostSharing.toString());
        otherCostDetails.add(hmOtherCostDetails);

        otherDirectCostInfo.setOtherCosts(otherCostDetails);
        List<OtherDirectCostInfoDto> otherDirectCosts = new ArrayList<OtherDirectCostInfoDto>();
        otherDirectCosts.add(otherDirectCostInfo);
        return otherDirectCosts;
    }

    private boolean canBudgetLineItemCostSharingInclude(BudgetContract budget, BudgetLineItemContract lineItem) {
        return budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag();
    }

    /**
     * This method returns a list of BudgetCategoryMap based on the input. The list returned will not contain the categories that
     * the codes passed as a list of String and also will not contain those that match the types passed as list of String. In case 2
     * empty lists are passed as parameters, the method will return entire list without applying any filters.
     * 
     * @param filterTargetCategoryCodes Category Codes that must be filtered
     * @param filterCategoryTypes Category types that must be filtered
     * @return a List of BudgetCategoryMap.
     * @see org.kuali.coeus.s2sgen.impl.budget.S2SBudgetCalculatorService#getBudgetCategoryMapList(java.util.List, java.util.List)
     */
    @Override
    public List<? extends BudgetCategoryMapContract> getBudgetCategoryMapList(List<String> filterTargetCategoryCodes, List<String> filterCategoryTypes) {
        List<? extends BudgetCategoryMappingContract> budgetCategoryMappingList;
        List<BudgetCategoryMapContract> budgetCategoryMapList = new ArrayList<BudgetCategoryMapContract>();
        budgetCategoryMappingList = budgetCategoryMapService.findCatMappingByMappingName(SPONSOR);

        boolean targetMatched;
        boolean duplicateExists;
        for (BudgetCategoryMappingContract categoryMapping : budgetCategoryMappingList) {
            targetMatched = true;
            // Apply mapping list filtering only if targetCategoryCodes size>0
            if (filterTargetCategoryCodes.size() > 0) {
                for (String targetCategoryCode : filterTargetCategoryCodes) {
                    if (categoryMapping.getTargetCategoryCode().equals(targetCategoryCode)) {
                        targetMatched = false;
                        break;
                    }
                }
            }

            if (targetMatched) {
                Iterator<? extends BudgetCategoryMapContract> filterList = budgetCategoryMapService.findCatMapByTargetAndMappingName(
                        categoryMapping.getTargetCategoryCode(), categoryMapping.getMappingName()).iterator();

                while (filterList.hasNext()) {
                    BudgetCategoryMapContract filterMap = filterList.next();
                    targetMatched = true;
                    if (filterCategoryTypes.size() > 0) {
                        for (String categoryType : filterCategoryTypes) {
                            if (filterMap.getCategoryType().trim().equals(categoryType.trim())) {
                                targetMatched = false;
                                break;
                            }
                        }
                    }

                    if (targetMatched) {
                        // Avoid adding duplicates to list
                        duplicateExists = false;
                        for (BudgetCategoryMapContract map : budgetCategoryMapList) {
                            if (filterMap.getTargetCategoryCode().equals(map.getTargetCategoryCode())) {
                                duplicateExists = true;
                                break;
                            }
                        }
                        if (!duplicateExists) {
                            budgetCategoryMapList.add(filterMap);
                        }
                    }
                }
            }
        }
        return budgetCategoryMapList;
    }

    /**
     * 
     * This method computes the Equipment Costs for a given {@link org.kuali.coeus.common.budget.api.period.BudgetPeriodContract}
     * 
     * @param budgetPeriod given BudgetPeriod.
     * @return List<EquipmentInfo> list of equipment cost corresponding to the BudgetPeriod object.
     */
    protected List<EquipmentDto> getEquipment(BudgetPeriodContract budgetPeriod, BudgetContract budget) {
        List<CostDto> cvExtraEquipment = new ArrayList<CostDto>();
        CostDto equipCostInfo;
        List<? extends BudgetCategoryMapContract> budgetCategoryMapList = getBudgetCategoryMapList(new ArrayList<String>(), new ArrayList<String>());

        ScaleTwoDecimal totalEquipFund = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalExtraEquipFund = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalEquipNonFund = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalExtraEquipNonFund = ScaleTwoDecimal.ZERO;
        Map<String, CostDto> costInfoMap = new HashMap<String, CostDto>();
        List<CostDto> costInfos = new ArrayList<CostDto>();
        for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetCategoryMapContract budgetCategoryMap : budgetCategoryMapList) {
                equipCostInfo = new CostDto();
                for (BudgetCategoryMappingContract budgetCategoryMapping : budgetCategoryMap.getBudgetCategoryMappings()) {
                    if (lineItem.getBudgetCategory().getCode().equals(budgetCategoryMapping.getBudgetCategoryCode())
                            && (budgetCategoryMapping.getTargetCategoryCode().equals( s2SConfigurationService.getValueAsString(
                            ConfigurationConstants.S2SBUDGET_TARGET_CATEGORY_CODE_EQUIPMENT_COST)))
                            && (budgetCategoryMapping.getMappingName().equals(SPONSOR))) {
                        equipCostInfo.setBudgetPeriod(budgetPeriod.getBudgetPeriod().intValue());
                        equipCostInfo.setCategory(budgetCategoryMap.getDescription());
                        equipCostInfo.setCategoryType(budgetCategoryMap.getCategoryType());
                        if (lineItem.getLineItemDescription() != null)
                            equipCostInfo.setDescription(lineItem.getLineItemDescription());
                        else
                            equipCostInfo.setDescription(lineItem.getCostElementBO().getDescription());
                        totalEquipFund = totalEquipFund.add(lineItem.getLineItemCost());
                        if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                            totalEquipNonFund = totalEquipNonFund.add(lineItem.getCostSharingAmount());
                        }

                        StringBuilder keyBuilder = new StringBuilder();
                        keyBuilder.append(budgetCategoryMap.getCategoryType());
                        keyBuilder.append("-");
                        keyBuilder.append(lineItem.getLineItemDescription());
                        String key = keyBuilder.toString();
                        if (costInfoMap.get(key) == null) {
                            equipCostInfo = new CostDto();
                            equipCostInfo.setBudgetPeriod(budgetPeriod.getBudgetPeriod().intValue());
                            equipCostInfo.setCategory(budgetCategoryMap.getDescription());
                            equipCostInfo.setCategoryType(budgetCategoryMap.getCategoryType());
                            if (lineItem.getLineItemDescription() != null)
                                equipCostInfo.setDescription(lineItem.getLineItemDescription());
                            else
                                equipCostInfo.setDescription(lineItem.getCostElementBO().getDescription());
                            equipCostInfo.setCost(lineItem.getLineItemCost());
                            if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                                equipCostInfo.setCostSharing(lineItem.getCostSharingAmount());
                            }
                        }
                        else {
                            equipCostInfo = costInfoMap.get(key);
                            equipCostInfo.setCost(equipCostInfo.getCost().add(lineItem.getLineItemCost()));
                            if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                                equipCostInfo.setCostSharing(equipCostInfo.getCostSharing().add(lineItem.getCostSharingAmount()));
                            }
                        }
                        costInfoMap.put(key, equipCostInfo);
                        costInfos.add(equipCostInfo);
                    }
                }
            }
        }
        EquipmentDto equipmentInfo = new EquipmentDto();

        if (costInfos.size() > 10) {
            for (int j = costInfos.size() - 1; j > 9; j--) {
                cvExtraEquipment.add(costInfos.get(j));
                CostDto extraCostInfo = (CostDto) costInfos.get(j);
                totalExtraEquipFund = totalExtraEquipFund.add(extraCostInfo.getCost());
                totalExtraEquipNonFund = totalExtraEquipNonFund.add(extraCostInfo.getCostSharing());
                costInfos.remove(j);
            }
            Collections.reverse(cvExtraEquipment);
            equipmentInfo.setExtraEquipmentList(cvExtraEquipment);
            equipmentInfo.setTotalExtraNonFund(totalExtraEquipNonFund);
        }
        equipmentInfo.setTotalExtraFund(totalExtraEquipFund);

        equipmentInfo.setEquipmentList(costInfos);
        equipmentInfo.setTotalFund(totalEquipFund);
        equipmentInfo.setTotalNonFund(totalEquipNonFund);

        List<EquipmentDto> equipmentInfos = new ArrayList<EquipmentDto>();
        equipmentInfos.add(equipmentInfo);
        return equipmentInfos;
    }

    /**
     * This method gets the {@link List} of Key Persons for a given {@link ProposalDevelopmentDocumentContract}
     * 
     * @param budgetPeriod given BudgetPeriod.
     * @param pdDoc Proposal Development Document.
     * @param numKeyPersons number of key persons.
     * @return List<List<KeyPersonInfo>> list of KeyPersonInfo list.
     */
    protected List<List<KeyPersonDto>> getKeyPersons(BudgetPeriodContract budgetPeriod, ProposalDevelopmentDocumentContract pdDoc,
            int numKeyPersons, BudgetContract budget) {
        List<KeyPersonDto> keyPersons = new ArrayList<KeyPersonDto>();
        List<KeyPersonDto> seniorPersons = new ArrayList<KeyPersonDto>();
        KeyPersonDto keyPerson = new KeyPersonDto();
        ProposalPersonContract principalInvestigator = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);

        // Create master list of contacts
        List<ProposalPersonContract> propPersons = new ArrayList<ProposalPersonContract>();
        if (principalInvestigator != null) {
            propPersons.add(principalInvestigator);
            keyPerson.setPersonId(principalInvestigator.getPersonId());
            keyPerson.setRolodexId(principalInvestigator.getRolodexId());
            keyPerson.setFirstName(principalInvestigator.getFirstName() == null ? FieldValueConstants.VALUE_UNKNOWN
                    : principalInvestigator.getFirstName());
            keyPerson.setLastName(principalInvestigator.getLastName() == null ? FieldValueConstants.VALUE_UNKNOWN : principalInvestigator
                    .getLastName());
            keyPerson.setMiddleName(principalInvestigator.getMiddleName());
            keyPerson.setRole(PRINCIPAL_INVESTIGATOR_ROLE);
            keyPerson.setNonMITPersonFlag(isPersonNonMITPerson(principalInvestigator));
            keyPersons.add(keyPerson);
        }

        for (ProposalPersonContract coInvestigator : s2SProposalPersonService.getCoInvestigators(pdDoc)) {
            propPersons.add(coInvestigator);
            keyPerson = new KeyPersonDto();
            keyPerson.setPersonId(coInvestigator.getPersonId());
            keyPerson.setRolodexId(coInvestigator.getRolodexId());
            keyPerson.setFirstName(coInvestigator.getFirstName() == null ? FieldValueConstants.VALUE_UNKNOWN : coInvestigator
                    .getFirstName());
            keyPerson.setLastName(coInvestigator.getLastName() == null ? FieldValueConstants.VALUE_UNKNOWN : coInvestigator.getLastName());
            keyPerson.setMiddleName(coInvestigator.getMiddleName());
            keyPerson.setNonMITPersonFlag(isPersonNonMITPerson(coInvestigator));

            if (sponsorHierarchyService.isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                if (coInvestigator.isMultiplePi()){
                    keyPerson.setRole(NID_PD_PI);
                }else{
                    keyPerson.setRole(NID_CO_PD_PI);
                }
            }else{
                keyPerson.setRole(KEYPERSON_CO_PD_PI);
            }
            keyPersons.add(keyPerson);
        }

        for (ProposalPersonContract propPerson : s2SProposalPersonService.getKeyPersons(pdDoc)) {
            propPersons.add(propPerson);
            keyPerson = new KeyPersonDto();
            keyPerson.setPersonId(propPerson.getPersonId());
            keyPerson.setRolodexId(propPerson.getRolodexId());
            keyPerson.setFirstName(propPerson.getFirstName() == null ? FieldValueConstants.VALUE_UNKNOWN : propPerson.getFirstName());
            keyPerson.setLastName(propPerson.getLastName() == null ? FieldValueConstants.VALUE_UNKNOWN : propPerson.getLastName());
            keyPerson.setMiddleName(propPerson.getMiddleName());
            keyPerson.setNonMITPersonFlag(isPersonNonMITPerson(propPerson));

            keyPerson.setRole(getBudgetPersonRoleOther());
            keyPerson.setKeyPersonRole(propPerson.getProjectRole());
            keyPersons.add(keyPerson);
        }

        boolean personAlreadyAdded = false;
        List<? extends BudgetCategoryMappingContract> budgetCategoryList = budgetCategoryMapService.findCatMappingByTargetAndMappingName(TARGET_CATEGORY_CODE_01, SPONSOR);
        for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetPersonnelDetailsContract budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                personAlreadyAdded = false;
                for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                    if (proposalPersonEqualsBudgetPerson(proposalPerson, budgetPersonnelDetails)) {
                        personAlreadyAdded = true;
                        break;
                    }
                }
                if (!personAlreadyAdded) {
                    if (budgetPersonnelDetails.getBudgetPerson().getNonEmployeeFlag()) {
                        if (budgetPersonnelDetails.getBudgetPerson().getRolodexId() != null) {
                            RolodexContract rolodexPerson = rolodexService.getRolodex(budgetPersonnelDetails.getBudgetPerson().getRolodexId());
                            keyPerson = new KeyPersonDto();
                            keyPerson.setRolodexId(rolodexPerson.getRolodexId());
                            keyPerson.setFirstName(rolodexPerson.getFirstName() == null ? FieldValueConstants.VALUE_UNKNOWN
                                    : rolodexPerson.getFirstName());
                            keyPerson.setLastName(rolodexPerson.getLastName() == null ? FieldValueConstants.VALUE_UNKNOWN : rolodexPerson
                                    .getLastName());
                            keyPerson.setMiddleName(rolodexPerson.getMiddleName());
                            keyPerson.setRole(StringUtils.isNotBlank(rolodexPerson.getTitle()) ? rolodexPerson.getTitle()
                                    : getBudgetPersonRoleOther());
                            keyPerson.setNonMITPersonFlag(true);

                            if (isSeniorLineItem(budgetCategoryList, lineItem.getBudgetCategory().getCode())) {
                                keyPersons.add(keyPerson);
                            }
                        }else if (StringUtils.isNotBlank(budgetPersonnelDetails.getBudgetPerson().getTbnId())) {
                            TbnPersonContract tbnPerson = tbnPersonService.getTbnPerson(budgetPersonnelDetails.getBudgetPerson().getTbnId());
                            if (tbnPerson != null) {
                                keyPerson = new KeyPersonDto();
                                String[] tbnNames = tbnPerson.getPersonName().split(" ");
                                int nameIndex = 0;
                                keyPerson.setPersonId(tbnPerson.getId());
                                keyPerson.setFirstName(tbnNames.length >= 1 ? tbnNames[nameIndex++] : FieldValueConstants.VALUE_UNKNOWN);
                                keyPerson.setMiddleName(tbnNames.length >= 3 ? tbnNames[nameIndex++] : " ");
                                keyPerson.setLastName(tbnNames.length >= 2 ? tbnNames[nameIndex++] : FieldValueConstants.VALUE_UNKNOWN);
                                keyPerson.setRole(tbnPerson.getPersonName());
                                keyPerson.setNonMITPersonFlag(false);

                                if (isSeniorLineItem( budgetCategoryList, lineItem.getBudgetCategory().getCode())) {
                                    keyPersons.add(keyPerson);
                                }

                            }
                        }
                    }else {

                        KcPersonContract kcPerson = kcPersonRepositoryService.findKcPersonByPersonId(budgetPersonnelDetails.getBudgetPerson().getPersonId());
                        if (kcPerson != null) {
                            keyPerson = new KeyPersonDto();
                            keyPerson.setPersonId(kcPerson.getPersonId());
                            keyPerson.setFirstName(kcPerson.getFirstName() == null ? FieldValueConstants.VALUE_UNKNOWN : kcPerson
                                    .getFirstName());
                            keyPerson.setLastName(kcPerson.getLastName() == null ? FieldValueConstants.VALUE_UNKNOWN : kcPerson
                                    .getLastName());
                            keyPerson.setMiddleName(kcPerson.getMiddleName());
                            keyPerson.setNonMITPersonFlag(false);
                            keyPerson.setRole(getBudgetPersonRoleOther());
                            if (isSeniorLineItem( budgetCategoryList, lineItem.getBudgetCategory().getCode())) {
                                keyPersons.add(keyPerson);
                            }
                        }
                    }
                }
            }
        }
        for(KeyPersonDto seniorPerson : keyPersons){
            if(seniorPerson.getRole().equals(NID_PD_PI)||hasPersonnelBudget(budgetPeriod,seniorPerson)){
                seniorPersons.add(seniorPerson);
            }            
        }

        List<KeyPersonDto> nKeyPersons = getNKeyPersons(seniorPersons, true, numKeyPersons);
        List<KeyPersonDto> extraPersons = getNKeyPersons(seniorPersons, false, numKeyPersons);
        CompensationDto compensationInfo;
        for (KeyPersonDto keyPersonInfo : nKeyPersons) {
            keyPerson = keyPersonInfo;
            compensationInfo = getCompensation(keyPerson, budgetPeriod, pdDoc.getDevelopmentProposal().getProposalNumber(), budget);
            keyPerson.setAcademicMonths(compensationInfo.getAcademicMonths());
            keyPerson.setCalendarMonths(compensationInfo.getCalendarMonths());
            keyPerson.setSummerMonths(compensationInfo.getSummerMonths());
            keyPerson.setBaseSalary(compensationInfo.getBaseSalary());
            keyPerson.setRequestedSalary(compensationInfo.getRequestedSalary());
            keyPerson.setFundsRequested(compensationInfo.getFundsRequested());
            keyPerson.setFringe(compensationInfo.getFringe());
            // start add costSaring for fedNonFedBudget report
            keyPerson.setCostSharingAmount(compensationInfo.getCostSharingAmount());
            keyPerson.setNonFundsRequested(compensationInfo.getNonFundsRequested());
            keyPerson.setFringeCostSharing(compensationInfo.getFringeCostSharing());
            // end add costSaring for fedNonFedBudget report
        }

        if (extraPersons != null) {
            for (KeyPersonDto keyPersonInfo : extraPersons) {
                keyPerson = keyPersonInfo;
                compensationInfo = getCompensation(keyPerson, budgetPeriod, pdDoc.getDevelopmentProposal().getProposalNumber(), budget);

                keyPerson.setAcademicMonths(compensationInfo.getAcademicMonths());
                keyPerson.setCalendarMonths(compensationInfo.getCalendarMonths());
                keyPerson.setSummerMonths(compensationInfo.getSummerMonths());
                keyPerson.setBaseSalary(compensationInfo.getBaseSalary());
                keyPerson.setRequestedSalary(compensationInfo.getRequestedSalary());
                keyPerson.setFundsRequested(compensationInfo.getFundsRequested());
                keyPerson.setFringe(compensationInfo.getFringe());
                // start add costSaring for fedNonFedBudget report
                keyPerson.setCostSharingAmount(compensationInfo.getCostSharingAmount());
                keyPerson.setNonFundsRequested(compensationInfo.getNonFundsRequested());
                keyPerson.setFringeCostSharing(compensationInfo.getFringeCostSharing());
                // end add costSaring for fedNonFedBudget report
            }
        }

        List<List<KeyPersonDto>> listKeyPersons = new ArrayList<List<KeyPersonDto>>();
        listKeyPersons.add(nKeyPersons);
        listKeyPersons.add(extraPersons);
        return listKeyPersons;
    }

    private Boolean hasPersonnelBudget(BudgetPeriodContract budgetPeriod,KeyPersonDto keyPerson){

        for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetPersonnelDetailsContract budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                if( budgetPersonnelDetails.getPersonId().equals(keyPerson.getPersonId())){
                    return true;
                } 
            }
        }
        return false;
    }

    private String getBudgetPersonRoleOther() {
        return KEYPERSON_OTHER;
    }

    private boolean isSeniorLineItem(List<? extends BudgetCategoryMappingContract> budgetCategoryList, String budgetCategoryCode) {
        boolean isSeniorLineItem = false;
        for (BudgetCategoryMappingContract categoryMapping : budgetCategoryList) {
            if (categoryMapping.getBudgetCategoryCode().equals(budgetCategoryCode)) {
                isSeniorLineItem = true;
            }
        }
        return isSeniorLineItem;
    }

    protected boolean proposalPersonEqualsBudgetPerson(ProposalPersonContract proposalPerson, BudgetPersonnelDetailsContract budgetPersonnelDetails) {
        boolean equal = false;
        if (proposalPerson != null && budgetPersonnelDetails != null) {
            String budgetPersonId = budgetPersonnelDetails.getPersonId();
            if ((proposalPerson.getPersonId() != null && proposalPerson.getPersonId().equals(budgetPersonId))
                    || (proposalPerson.getRolodexId() != null && proposalPerson.getRolodexId().toString().equals(budgetPersonId))) {
                equal = true;
            }
        }
        return equal;
    }

    /**
     * This method determines whether a {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract} is a Non MIT person
     * 
     * @param proposalPerson ProposalPerson.
     * @return boolean true if Non MIT Person false otherwise.
     * @see org.kuali.coeus.s2sgen.impl.budget.S2SBudgetCalculatorService#isPersonNonMITPerson(ProposalPersonContract)
     */
    @Override
    public boolean isPersonNonMITPerson(ProposalPersonContract proposalPerson) {
        return proposalPerson.getPersonId() == null;
    }

    /**
     * 
     * This method computes the CompensationInfo for given person, {@link org.kuali.coeus.common.budget.api.period.BudgetPeriodContract} and Proposal Numnber
     * 
     * @param keyPerson id of the proposal person.
     * @param budgetPeriod given BudgetPeriod.
     * @param proposalNumber propsal number.
     * 
     * @return {@link CompensationDto} corresponding to the personId,budgetPeriod and proposalNumber.
     */
    protected CompensationDto getCompensation(KeyPersonDto keyPerson, BudgetPeriodContract budgetPeriod, String proposalNumber, BudgetContract budget) {
        CompensationDto compensationInfo = new CompensationDto();
        BigDecimal summerMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal academicMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal calendarMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        ScaleTwoDecimal totalSal = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal baseAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalSalCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal fringeCostSharing = ScaleTwoDecimal.ZERO;
        BigDecimal numberOfMonths = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        String budgetCatagoryCodePersonnel = s2SConfigurationService.getValueAsString(
                ConfigurationConstants.S2SBUDGET_BUDGET_CATEGORY_CODE_PERSONNEL);
        
        for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
            
            for (BudgetPersonnelDetailsContract personDetails : lineItem.getBudgetPersonnelDetailsList()) {
                if (keyPersonEqualsBudgetPerson(keyPerson, personDetails)) {
                    numberOfMonths = s2SDateTimeService.getNumberOfMonths(personDetails.getStartDate(), personDetails.getEndDate()).bigDecimalValue();
                    if (personDetails.getPeriodTypeCode().equals(
                            s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.S2SBUDGET_PERIOD_TYPE_ACADEMIC_MONTHS))) {
                        if (lineItem.getSubmitCostSharingFlag()) {
                            academicMonths = academicMonths.add(personDetails.getPercentEffort().bigDecimalValue()
                                    .multiply(numberOfMonths).multiply(POINT_ZERO_ONE));
                        } else {
                            academicMonths = academicMonths.add(personDetails.getPercentCharged().bigDecimalValue()
                                    .multiply(numberOfMonths).multiply(POINT_ZERO_ONE));
                        }
                    }
                    else if (personDetails.getPeriodTypeCode().equals(
                            s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.S2SBUDGET_PERIOD_TYPE_SUMMER_MONTHS))) {
                        if (lineItem.getSubmitCostSharingFlag()) {
                            summerMonths = summerMonths.add(personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths)
                                    .multiply(POINT_ZERO_ONE));
                        } else {
                            summerMonths = summerMonths.add(personDetails.getPercentCharged().bigDecimalValue().multiply(numberOfMonths)
                                    .multiply(POINT_ZERO_ONE));
                        }
                    }
                    else {
                        if (StringUtils.isNotBlank(personDetails.getBudgetPerson().getTbnId())) {
                            if (lineItem.getBudgetCategory()
                                    .getCode().equals(budgetCatagoryCodePersonnel)) {
                                if (lineItem.getSubmitCostSharingFlag()) {
                                    calendarMonths = calendarMonths.add(personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths)
                                            .multiply(POINT_ZERO_ONE));
                                } else {
                                    calendarMonths = calendarMonths.add(personDetails.getPercentCharged().bigDecimalValue().multiply(numberOfMonths)
                                            .multiply(POINT_ZERO_ONE));
                                }
                            } 
                        }else {
                            if (lineItem.getSubmitCostSharingFlag()) {
                                calendarMonths = calendarMonths.add(personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths)
                                        .multiply(POINT_ZERO_ONE));
                            }
                            else {
                                calendarMonths = calendarMonths.add(personDetails.getPercentCharged().bigDecimalValue().multiply(numberOfMonths)
                                        .multiply(POINT_ZERO_ONE));
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(personDetails.getBudgetPerson().getTbnId() ) ){
                        if(lineItem.getBudgetCategory()
                                .getCode().equals(budgetCatagoryCodePersonnel)){
                                    totalSal = totalSal.add(personDetails.getSalaryRequested());
                                }
                    }else{
                        totalSal = totalSal.add(personDetails.getSalaryRequested());
                    }
                    if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                        if (StringUtils.isNotBlank(personDetails.getBudgetPerson().getTbnId() ) ){
                            if(lineItem.getBudgetCategory()
                                    .getCode().equals(budgetCatagoryCodePersonnel)){
                        totalSalCostSharing = totalSalCostSharing.add(personDetails.getCostSharingAmount());
                            }
                        }else{
                            totalSalCostSharing = totalSalCostSharing.add(personDetails.getCostSharingAmount());
                        }
                    }
                    for (BudgetPersonnelCalculatedAmountContract personCalculatedAmt : personDetails.getBudgetPersonnelCalculatedAmounts()) {
                        if ((personCalculatedAmt
                                .getRateClass()
                                .getRateClassType().getCode()
                                .equals(s2SConfigurationService.getValueAsString(
                                        ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_EMPLOYEE_BENEFITS)) && !personCalculatedAmt
                                .getRateTypeCode().equals(
                                        s2SConfigurationService.getValueAsString(
                                                ConfigurationConstants.S2SBUDGET_RATE_TYPE_SUPPORT_STAFF_SALARIES)))
                                || (personCalculatedAmt
                                        .getRateClass()
                                        .getRateClassType().getCode()
                                        .equals(s2SConfigurationService.getValueAsString(
                                                ConfigurationConstants.S2SBUDGET_RATE_CLASS_TYPE_VACATION)) && !personCalculatedAmt
                                        .getRateTypeCode().equals(
                                                s2SConfigurationService.getValueAsString(
                                                        ConfigurationConstants.S2SBUDGET_RATE_TYPE_ADMINISTRATIVE_SALARIES)))) {
                            if (StringUtils.isNotBlank(personDetails.getBudgetPerson().getTbnId() ) ){
                                if(lineItem.getBudgetCategory()
                                        .getCode().equals(budgetCatagoryCodePersonnel)){
                                    fringe = fringe.add(personCalculatedAmt.getCalculatedCost());
                                }
                            }
                            else{
                                fringe = fringe.add(personCalculatedAmt.getCalculatedCost());
                            }
                            if (canBudgetLineItemCostSharingInclude(budget, lineItem)) {
                                if (StringUtils.isNotBlank(personDetails.getBudgetPerson().getTbnId() ) ){
                                    if(lineItem.getBudgetCategory()
                                            .getCode().equals(budgetCatagoryCodePersonnel)){
                                        fringeCostSharing = fringeCostSharing.add(personCalculatedAmt.getCalculatedCostSharing());
                                    }
                                }
                                else{ 
                                    fringeCostSharing = fringeCostSharing.add(personCalculatedAmt.getCalculatedCostSharing());
                                }
                            }
                        }
                    }
                    BudgetPersonContract budgetPerson = personDetails.getBudgetPerson();
                    if (budgetPerson != null) {
                        baseAmount = budgetPerson.getCalculationBase();
                        // baseAmount must be set to the first record value in
                        // case
                        // the execution doesnt enter the if condition below
                        String apptTypeCode = budgetPerson.getAppointmentType().getCode();
                        if (!apptTypeCode.equals(s2SConfigurationService.getValueAsString(
                                ConfigurationConstants.S2SBUDGET_APPOINTMENT_TYPE_SUM_EMPLOYEE))
                                && !apptTypeCode.equals(s2SConfigurationService.getValueAsString(
                                ConfigurationConstants.S2SBUDGET_APPOINTMENT_TYPE_TMP_EMPLOYEE))) {
                            baseAmount = budgetPerson.getCalculationBase();
                        }
                    }
                }

            }
        }
        compensationInfo.setAcademicMonths(new ScaleTwoDecimal(academicMonths));
        compensationInfo.setCalendarMonths(new ScaleTwoDecimal(calendarMonths));
        compensationInfo.setSummerMonths(new ScaleTwoDecimal(summerMonths));
        compensationInfo.setRequestedSalary(totalSal);
        compensationInfo.setBaseSalary(baseAmount);
        compensationInfo.setCostSharingAmount(totalSalCostSharing);
        compensationInfo.setFringe(fringe);
        compensationInfo.setFundsRequested(totalSal.add(fringe));
        compensationInfo.setFringeCostSharing(fringeCostSharing);
        compensationInfo.setNonFundsRequested(totalSalCostSharing.add(fringeCostSharing));
        return compensationInfo;
    }


    /**
     * 
     * This method limits the number of key persons to n, returns list of key persons, first n in case firstN is true, or all other
     * than first n, in case of firstN being false
     * 
     * @param keyPersons list of {@link KeyPersonDto}
     * @param firstN value that determines whether the returned list should contain first n persons or the rest of persons
     * @param n number of key persons that are considered as not extra persons
     * @return list of {@link KeyPersonDto}
     */
    protected List<KeyPersonDto> getNKeyPersons(List<KeyPersonDto> keyPersons, boolean firstN, int n) {
        KeyPersonDto keyPersonInfo, previousKeyPersonInfo;
        int size = keyPersons.size();

        for (int i = size - 1; i > 0; i--) {
            keyPersonInfo = (KeyPersonDto) (keyPersons.get(i));
            previousKeyPersonInfo = (KeyPersonDto) (keyPersons.get(i - 1));
            if (keyPersonInfo.getPersonId() != null && previousKeyPersonInfo.getPersonId() != null
                    && keyPersonInfo.getPersonId().equals(previousKeyPersonInfo.getPersonId())) {
                keyPersons.remove(i);
            }
            else if (keyPersonInfo.getRolodexId() != null && previousKeyPersonInfo.getRolodexId() != null
                    && keyPersonInfo.getRolodexId().equals(previousKeyPersonInfo.getRolodexId())) {
                keyPersons.remove(i);
            }
        }

        size = keyPersons.size();
        if (firstN) {
            List<KeyPersonDto> firstNPersons = new ArrayList<KeyPersonDto>();

            // Make sure we don't exceed the size of the list.
            if (size > n) {
                size = n;
            }
            // remove extras
            for (int i = 0; i < size; i++) {
                firstNPersons.add(keyPersons.get(i));
            }
            return firstNPersons;
        }
        else {
            // return extra people
            List<KeyPersonDto> extraPersons = new ArrayList<KeyPersonDto>();
            for (int i = n; i < size; i++) {
                extraPersons.add(keyPersons.get(i));
            }
            return extraPersons;
        }
    }

    @Override
    public ScaleTwoDecimal getBaseSalaryByPeriod(Long budgetId, int budgetPeriod, KeyPersonDto person ) {
        return budgetPersonSalaryService.findBaseSalaryForFirstPeriod(budgetId, person.getPersonId() != null ? person.getPersonId() : person.getRolodexId().toString(), budgetPeriod);
    }

    /**
     * This method compares a key person with budget person. It checks whether the key person is from PERSON or ROLODEX and matches
     * the respective person ID with the person in {@link org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract}
     *
     * @param keyPersonInfo - key person to compare
     * @param budgetPersonnelDetails person from BudgetPersonnelDetails
     * @return true if persons match, false otherwise
     */
    @Override
    public boolean keyPersonEqualsBudgetPerson(KeyPersonDto keyPersonInfo, BudgetPersonnelDetailsContract budgetPersonnelDetails) {
        boolean equal = false;
        if (keyPersonInfo != null && budgetPersonnelDetails != null) {
            String budgetPersonId = budgetPersonnelDetails.getPersonId();
            if ((keyPersonInfo.getPersonId() != null && keyPersonInfo.getPersonId().equals(budgetPersonId))
                    || (keyPersonInfo.getRolodexId() != null && keyPersonInfo.getRolodexId().toString().equals(budgetPersonId))) {
                equal = true;
            }
        }
        return equal;
    }

    public void setBudgetCategoryMapService(BudgetCategoryMapService budgetCategoryMapService) {
        this.budgetCategoryMapService = budgetCategoryMapService;
    }

    public BudgetCategoryMapService getBudgetCategoryMapService() {
        return budgetCategoryMapService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    public BudgetPersonSalaryService getBudgetPersonSalaryService() {
        return budgetPersonSalaryService;
    }

    public void setBudgetPersonSalaryService(BudgetPersonSalaryService budgetPersonSalaryService) {
        this.budgetPersonSalaryService = budgetPersonSalaryService;
    }

    public OrganizationRepositoryService getOrganizationRepositoryService() {
        return organizationRepositoryService;
    }

    public void setOrganizationRepositoryService(OrganizationRepositoryService organizationRepositoryService) {
        this.organizationRepositoryService = organizationRepositoryService;
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public KcPersonRepositoryService getKcPersonRepositoryService() {
        return kcPersonRepositoryService;
    }

    public void setKcPersonRepositoryService(KcPersonRepositoryService kcPersonRepositoryService) {
        this.kcPersonRepositoryService = kcPersonRepositoryService;
    }

    public TbnPersonService getTbnPersonService() {
        return tbnPersonService;
    }

    public void setTbnPersonService(TbnPersonService tbnPersonService) {
        this.tbnPersonService = tbnPersonService;
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }

    public S2SErrorHandlerService getS2SErrorHandlerService() {
        return s2SErrorHandlerService;
    }

    public void setS2SErrorHandlerService(S2SErrorHandlerService s2SErrorHandlerService) {
        this.s2SErrorHandlerService = s2SErrorHandlerService;
    }

    public S2SCommonBudgetService getS2SCommonBudgetService() {
        return s2SCommonBudgetService;
    }

    public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
        this.s2SCommonBudgetService = s2SCommonBudgetService;
    }
}
