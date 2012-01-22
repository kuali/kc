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
package org.kuali.kra.s2s.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.core.BudgetCategoryMapping;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetRateAndBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.TbnPerson;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularIdc;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.bo.BudgetPeriodInfo;
import org.kuali.kra.s2s.generator.bo.BudgetSummaryInfo;
import org.kuali.kra.s2s.generator.bo.CompensationInfo;
import org.kuali.kra.s2s.generator.bo.CostInfo;
import org.kuali.kra.s2s.generator.bo.EquipmentInfo;
import org.kuali.kra.s2s.generator.bo.IndirectCostDetails;
import org.kuali.kra.s2s.generator.bo.IndirectCostInfo;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.generator.bo.OtherDirectCostInfo;
import org.kuali.kra.s2s.generator.bo.OtherPersonnelInfo;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

/**
 * This class contains the implementation for common budget calculations
 * required for S2S Form generators
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class S2SBudgetCalculatorServiceImpl implements
		S2SBudgetCalculatorService {
	public static final String KEY_MAPPING_NAME = "mappingName";
	public static final String KEY_TARGET_CATEGORY_CODE = "targetCategoryCode";
	private static final int MAX_KEY_PERSON_COUNT = 8;
	private static final String BUDGET_CATEGORY_TYPE_PERSONNEL = "P";
	private static final String FILTER_CATEGORY_TYPE_PERSONNEL = "P";
	private static final String RATE_CLASS_TYPE_OTHER = "O";
	private static final String RATE_CLASS_TYPE_SALARIES_MS = "L";
	private static final String RATE_CLASS_TYPE_LAB_ALLOCATION_SALARIES = "Y";
	private static final String RATE_CLASS_TYPE_EMPLOYEE_BENEFITS = "E";
	private static final String RATE_CLASS_TYPE_VACATION = "V";
	private static final String RATE_TYPE_ADMINISTRATIVE_SALARIES = "2";
	private static final String RATE_TYPE_SUPPORT_STAFF_SALARIES = "3";
	private static final String RATE_CLASS_CODE_EMPLOYEE_BENEFITS = "5";
	private static final String RATE_CLASS_CODE_VACATION = "8";
	private static final String CATEGORY_TYPE_OTHER_DIRECT_COST = "O";
	private static final String CATEGORY_01_GRADUATES = "01-Graduates";
	private static final String CATEGORY_01_POSTDOCS = "01-PostDocs";
	private static final String CATEGORY_01_UNDERGRADS = "01-Undergrads";
	private static final String CATEGORY_01_SECRETARIAL = "01-Secretarial";
	private static final String CATEGORY_01_OTHER = "01-Other";
	private static final String CATEGORY_01_OTHER_PROFS = "01-Other Profs";
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
	private static final String ROLE_GRADUATE_OTHET_PROFESSIONALS = "Other Professionals";
	private static final String ROLE_GRADUATE_ALLOCATED_ADMIN_SUPPORT = "Allocated Admin Support";
	// private static final String PERIOD_TYPE_ACADEMIC_MONTHS = "AP";
	// private static final String PERIOD_TYPE_SUMMER_MONTHS = "SP";
	// private static final String PERIOD_TYPE_CALENDAR_MONTHS = "CC";
	private static final String PERIOD_TYPE_ACADEMIC_MONTHS = "2";
	private static final String PERIOD_TYPE_CALENDAR_MONTHS = "3";
	private static final String PERIOD_TYPE_SUMMER_MONTHS = "4";
	private static final String PERIOD_TYPE_CYCLE_MONTHS = "CY";
	private static final String TARGET_CATEGORY_CODE_EQUIPMENT_COST = "42";
	private static final String TARGET_CATEGORY_CODE_01 = "01";
	private static final String OTHER_DIRECT_COSTS = "Other Direct Costs";
	private static final String ALL_OTHER_COSTS = "All Other Costs";
	private static final String DESCRIPTION = "Description";
	private static final String DESCRIPTION_LA = "LA ";
	private static final String MATERIALS_AND_SUPPLIES_CATEGORY = "Materials and Supplies";
	private static final String CONSULTANT_COSTS_CATEGORY = "Consultant Costs";
	private static final String PUBLICATION_COSTS_CATEGORY = "Publication Costs";
	private static final String COMPUTER_SERVICES_CATEGORY = "Computer Services";
	private static final String ALTERATIONS_CATEGORY = "Alterations";
	private static final String SUBCONTRACT_CATEGORY = "Subcontract";
	private static final String EQUIPMENT_RENTAL_CATEGORY = "Equipment Rental";
	private static final String DOMESTIC_TRAVEL_CATEGORY = "Domestic Travel";
	private static final String FOREIGN_TRAVEL_CATEGORY = "Foreign Travel";
	private static final String PARTICIPANT_STIPENDS_CATEGORY = "Participant Stipends";
	private static final String PARTICIPANT_TRAVEL_CATEGORY = "Participant Travel";
	private static final String PARTICIPANT_TUTION_CATEGORY = "Participant Tuition";
	private static final String PARTICIPANT_SUBSISTENCE_CATEGORY = "Participant Subsistence";
	private static final String PARTICIPANT_OTHER_CATEGORY = "Participant Other";
	private static final String OTHER_DIRECT_COSTS_CATEGORY = "Other Direct Costs";
	private static final String KEYPERSON_CO_PD_PI = "CO-PD/PI";
	private static final String NID_PD_PI = "PD/PI";
	private static final String NID_CO_PD_PI = "CO-INVESTIGATOR";    
	private static final String KEYPERSON_OTHER = "Other (Specify)";
	private static final String APPOINTMENT_TYPE_SUM_EMPLOYEE = "SUM EMPLOYEE";
	private static final String APPOINTMENT_TYPE_TMP_EMPLOYEE = "TMP EMPLOYEE";
	private static final Log LOG = LogFactory
			.getLog(S2SBudgetCalculatorServiceImpl.class);
	private static final String PRINCIPAL_INVESTIGATOR_ROLE = "PD/PI";
	private static final String KEY_ROLODEX_ID = "rolodexId";
    private static final Object ONE_STRING = "1";
	private BusinessObjectService businessObjectService;
	private KcPersonService kcPersonService;
	private S2SUtilService s2SUtilService;
	private RolodexService rolodexService;
	private ParameterService parameterService;

	public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    public boolean isBudgetSummaryCostShareParameterValueEnabled(Budget budget) {
        return budget.getSubmitCostSharingFlag();
    }
//    public boolean isBudgetCostShareParameterValueEnabled() {
//        
//        String parameterValue = null;
//        try {
//            parameterValue = this.parameterService.getParameterValue(
//                    BudgetDocument.class, Constants.ENABLE_COST_SHARE_SUBMIT);
//        } catch (IllegalArgumentException e) {
//            LOG.error("Parameter not found - " + Constants.ENABLE_COST_SHARE_SUBMIT, e);
//        }
//        return parameterValue==null?true:ONE_STRING.equals(parameterValue);
//    }
	/**
	 * 
	 * This method does the budget related calculations for a given
	 * ProposalDevelopmentDocument and returns them in BudgetSummaryInfo
	 * 
	 * @param pdDoc
	 *            ProposalDevelopmentDocument.
	 * @return BudgetSummaryInfo corresponding to the
	 *         ProposalDevelopmentDocument object.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SBudgetCalculatorService#getBudgetInfo(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
	 */
	public BudgetSummaryInfo getBudgetInfo(ProposalDevelopmentDocument pdDoc, List<BudgetPeriodInfo> budgetPeriodInfos)
			throws S2SException {
		BudgetDocument budgetDocument = getFinalBudgetVersion(pdDoc);
		Budget budget = budgetDocument == null ? null : budgetDocument
				.getBudget();
		BudgetSummaryInfo budgetSummaryInfo = new BudgetSummaryInfo();
		if (budget == null) {
			return budgetSummaryInfo;
		}

		budgetSummaryInfo.setBudgetPeriods(budgetPeriodInfos);
		budgetSummaryInfo.setCumTotalCosts(budget.getTotalCost());
		budgetSummaryInfo.setCumTotalIndirectCosts(budget
				.getTotalIndirectCost());
		budgetSummaryInfo.setCumTotalDirectCosts(budget.getTotalDirectCost());
		if(budget.getSubmitCostSharingFlag()){
		    budgetSummaryInfo.setCumTotalCostSharing(budget.getCostSharingAmount());
		}

		BudgetDecimal totalDirectCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal totalIndirectCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal lineItemCost = BudgetDecimal.ZERO;
		BudgetDecimal lineItemCostSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal fringeCost = BudgetDecimal.ZERO;
		BudgetDecimal fringeCostSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal budgetDetailsCost = BudgetDecimal.ZERO;
		BudgetDecimal budgetDetailsCostSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal budgetDetailsFringeCost = BudgetDecimal.ZERO;
		BudgetDecimal budgetDetailsFringeCostSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal totPersFunds = BudgetDecimal.ZERO;
		BudgetDecimal totPersNonFunds = BudgetDecimal.ZERO;

		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
				totalDirectCostSharing = totalDirectCostSharing.add(lineItem
						.getCostSharingAmount());
				if (lineItem.getBudgetCategory().getBudgetCategoryType()
						.getBudgetCategoryTypeCode().equals(
								BUDGET_CATEGORY_TYPE_PERSONNEL)) {
					lineItemCost = lineItemCost.add(lineItem.getLineItemCost());
					if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
					    lineItemCostSharingAmount = lineItemCostSharingAmount.add(lineItem.getCostSharingAmount());
					}
				}

				for (BudgetLineItemCalculatedAmount lineItemCalAmt : lineItem
						.getBudgetLineItemCalculatedAmounts()) {
					lineItemCalAmt.refreshReferenceObject("rateClass");
					if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
    					if (lineItemCalAmt.getRateClass().getRateClassType()
    							.equals(RateClassType.OVERHEAD.getRateClassType())) {
                            totalIndirectCostSharing = totalIndirectCostSharing.add(lineItemCalAmt.getCalculatedCostSharing());
    					} else {
                            totalDirectCostSharing = totalDirectCostSharing.add(lineItemCalAmt.getCalculatedCostSharing());
    					}
					}
					if ((lineItemCalAmt.getRateClassCode().equals(
							RATE_CLASS_CODE_EMPLOYEE_BENEFITS) && !lineItemCalAmt
							.getRateTypeCode().equals(
									RATE_TYPE_SUPPORT_STAFF_SALARIES))
							|| (lineItemCalAmt.getRateClassCode().equals(
									RATE_CLASS_CODE_VACATION) && !lineItemCalAmt
									.getRateTypeCode().equals(
											RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
						if (lineItem.getBudgetCategory()
								.getBudgetCategoryType()
								.getBudgetCategoryTypeCode().equals(
										BUDGET_CATEGORY_TYPE_PERSONNEL)) {
							fringeCost = fringeCost.add(lineItemCalAmt
									.getCalculatedCost());
							if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
							    fringeCostSharingAmount = fringeCostSharingAmount.add(lineItemCalAmt.getCalculatedCostSharing());
							}
						}
					}
					if (lineItemCalAmt.getRateClass().getRateClassType()
							.equals(RATE_CLASS_TYPE_LAB_ALLOCATION_SALARIES)) {
						budgetDetailsCost = budgetDetailsCost.add(lineItemCalAmt.getCalculatedCost());
						if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
						    budgetDetailsCostSharingAmount = budgetDetailsCostSharingAmount.add(lineItemCalAmt.getCalculatedCostSharing());
						}
					}
					if ((lineItemCalAmt.getRateClass().getRateClassType()
							.equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS) && lineItemCalAmt
							.getRateTypeCode().equals(
									RATE_TYPE_SUPPORT_STAFF_SALARIES))
							|| (lineItemCalAmt.getRateClass()
									.getRateClassType().equals(
											RATE_CLASS_TYPE_VACATION) && lineItemCalAmt
									.getRateTypeCode().equals(
											RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
						budgetDetailsFringeCost = budgetDetailsFringeCost.add(lineItemCalAmt.getCalculatedCost());
						if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
						    budgetDetailsFringeCostSharingAmount = budgetDetailsFringeCostSharingAmount.add(lineItemCalAmt.getCalculatedCostSharing());
						}
					}
					
				}
			}
		}
		if(budget.getSubmitCostSharingFlag()){
    		budgetSummaryInfo.setCumTotalDirectCostSharing(totalDirectCostSharing);
    		budgetSummaryInfo
    				.setCumTotalIndirectCostSharing(totalIndirectCostSharing);
        }
		totPersFunds = totPersFunds.add(lineItemCost).add(fringeCost).add(
				budgetDetailsCost).add(budgetDetailsFringeCost);
		totPersNonFunds = totPersNonFunds.add(lineItemCostSharingAmount).add(
				fringeCostSharingAmount).add(budgetDetailsCostSharingAmount)
				.add(budgetDetailsFringeCostSharingAmount);

		budgetSummaryInfo.setCumTotalNonFundsForPersonnel(totPersNonFunds);
		budgetSummaryInfo.setCumTotalFundsForPersonnel(totPersFunds);

		OtherDirectCostInfo otherDirectCostInfo = new OtherDirectCostInfo();
		List<OtherDirectCostInfo> cvOtherDirectCost = new ArrayList<OtherDirectCostInfo>();
		List<Map<String, String>> cvOtherCosts = new ArrayList<Map<String, String>>();

		BudgetDecimal cumAlterations = BudgetDecimal.ZERO;
		BudgetDecimal cumConsultants = BudgetDecimal.ZERO;
		BudgetDecimal cumMaterials = BudgetDecimal.ZERO;
		BudgetDecimal cumPubs = BudgetDecimal.ZERO;
		BudgetDecimal cumSubAward = BudgetDecimal.ZERO;
		BudgetDecimal cumComputer = BudgetDecimal.ZERO;
		BudgetDecimal cumEquipRental = BudgetDecimal.ZERO;
		BudgetDecimal cumAll = BudgetDecimal.ZERO;
		BudgetDecimal cumOtherType1 = BudgetDecimal.ZERO;
		BudgetDecimal cumPartStipends = BudgetDecimal.ZERO;
		BudgetDecimal cumPartSubsistence = BudgetDecimal.ZERO;
		BudgetDecimal cumPartTuition = BudgetDecimal.ZERO;
		BudgetDecimal cumPartOther = BudgetDecimal.ZERO;
		BudgetDecimal cumPartTravel = BudgetDecimal.ZERO;
		int cumParticipantCount = 0;
		BudgetDecimal cumAlterationsCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumConsultantsCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumMaterialsCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumPubsCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumSubAwardCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumComputerCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumEquipRentalCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumAllCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumOtherType1CostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumPartStipendsCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumPartSubsistenceCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumPartTuitionCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumPartOtherCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal cumPartTravelCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal participantTotalCostSharing = BudgetDecimal.ZERO;

		BudgetDecimal totalDomesticTravel = BudgetDecimal.ZERO;
		BudgetDecimal totalForeignTravel = BudgetDecimal.ZERO;
		BudgetDecimal totalDomesticTravelNonFund = BudgetDecimal.ZERO;
		BudgetDecimal totalForeignTravelNonFund = BudgetDecimal.ZERO;
		BudgetDecimal cumTotalEquipFund = BudgetDecimal.ZERO;
		BudgetDecimal cumTotalEquipNonFund = BudgetDecimal.ZERO;
		BudgetDecimal totCountOtherPersonnel = BudgetDecimal.ZERO;

		if (budgetPeriodInfos != null) {
			for (BudgetPeriodInfo budgetPeriodInfo : budgetPeriodInfos) {
				cvOtherDirectCost = budgetPeriodInfo.getOtherDirectCosts();
				otherDirectCostInfo = cvOtherDirectCost.get(0);
				cumAlterations = cumAlterations.add(otherDirectCostInfo
						.getAlterations());
				cumConsultants = cumConsultants.add(otherDirectCostInfo
						.getConsultants());
				cumMaterials = cumMaterials.add(otherDirectCostInfo
						.getmaterials());
				cumPubs = cumPubs.add(otherDirectCostInfo.getpublications());
				cumSubAward = cumSubAward.add(otherDirectCostInfo
						.getsubAwards());
				cumComputer = cumComputer
						.add(otherDirectCostInfo.getcomputer());
				cumEquipRental = cumEquipRental.add(otherDirectCostInfo
						.getEquipRental());
				cumAll = cumAll.add(otherDirectCostInfo.gettotalOtherDirect());

				cumPartStipends = cumPartStipends.add(otherDirectCostInfo
						.getPartStipends() == null ? BudgetDecimal.ZERO
						: otherDirectCostInfo.getPartStipends());
				cumPartTravel = cumPartTravel.add(otherDirectCostInfo
						.getPartTravel() == null ? BudgetDecimal.ZERO
						: otherDirectCostInfo.getPartTravel());
				cumPartSubsistence = cumPartSubsistence.add(otherDirectCostInfo
						.getPartSubsistence() == null ? BudgetDecimal.ZERO
						: otherDirectCostInfo.getPartSubsistence());
				cumPartTuition = cumPartTuition.add(otherDirectCostInfo
						.getPartTuition() == null ? BudgetDecimal.ZERO
						: otherDirectCostInfo.getPartTuition());
				cumPartOther = cumPartOther.add(otherDirectCostInfo
						.getPartOther() == null ? BudgetDecimal.ZERO
						: otherDirectCostInfo.getPartOther());
				cumParticipantCount = cumParticipantCount
						+ (otherDirectCostInfo.getParticpantTotalCount() == 0 ? 0
								: otherDirectCostInfo.getParticpantTotalCount());
				if(budget.getSubmitCostSharingFlag()){
				cumAlterationsCostSharing = cumAlterationsCostSharing
						.add(otherDirectCostInfo.getAlterationsCostSharing());
				cumConsultantsCostSharing = cumConsultantsCostSharing
						.add(otherDirectCostInfo.getConsultantsCostSharing());
				cumMaterialsCostSharing = cumMaterialsCostSharing
						.add(otherDirectCostInfo.getMaterialsCostSharing());
				cumPubsCostSharing = cumPubsCostSharing.add(otherDirectCostInfo
						.getPublicationsCostSharing());
				cumSubAwardCostSharing = cumSubAwardCostSharing
						.add(otherDirectCostInfo.getSubAwardsCostSharing());
				cumComputerCostSharing = cumComputerCostSharing
						.add(otherDirectCostInfo.getComputerCostSharing());
				cumEquipRentalCostSharing = cumEquipRentalCostSharing
						.add(otherDirectCostInfo.getEquipRentalCostSharing());
				cumAllCostSharing = cumAllCostSharing.add(otherDirectCostInfo
						.getTotalOtherDirectCostSharing());

				cumPartStipendsCostSharing = cumPartStipendsCostSharing
						.add(otherDirectCostInfo.getPartStipendsCostSharing() == null ? BudgetDecimal.ZERO
								: otherDirectCostInfo
										.getPartStipendsCostSharing());
				cumPartTravelCostSharing = cumPartTravelCostSharing
						.add(otherDirectCostInfo.getPartTravelCostSharing() == null ? BudgetDecimal.ZERO
								: otherDirectCostInfo
										.getPartTravelCostSharing());
				cumPartSubsistenceCostSharing = cumPartSubsistenceCostSharing
						.add(otherDirectCostInfo
								.getPartSubsistenceCostSharing() == null ? BudgetDecimal.ZERO
								: otherDirectCostInfo
										.getPartSubsistenceCostSharing());
				cumPartTuitionCostSharing = cumPartTuitionCostSharing
						.add(otherDirectCostInfo.getPartTuitionCostSharing() == null ? BudgetDecimal.ZERO
								: otherDirectCostInfo
										.getPartTuitionCostSharing());

				cumPartOtherCostSharing = cumPartOtherCostSharing
						.add(otherDirectCostInfo.getPartOtherCostSharing() == null ? BudgetDecimal.ZERO
								: otherDirectCostInfo.getPartOtherCostSharing());
				}else{
				    cumAlterationsCostSharing = cumAlterationsCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumConsultantsCostSharing = cumConsultantsCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumMaterialsCostSharing = cumMaterialsCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumPubsCostSharing = cumPubsCostSharing.add(BudgetDecimal.ZERO);
                    cumSubAwardCostSharing = cumSubAwardCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumComputerCostSharing = cumComputerCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumEquipRentalCostSharing = cumEquipRentalCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumAllCostSharing = cumAllCostSharing.add(BudgetDecimal.ZERO);
                    cumPartStipendsCostSharing = cumPartStipendsCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumPartTravelCostSharing = cumPartTravelCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumPartSubsistenceCostSharing = cumPartSubsistenceCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumPartTuitionCostSharing = cumPartTuitionCostSharing
                            .add(BudgetDecimal.ZERO);
                    cumPartOtherCostSharing = cumPartOtherCostSharing
                            .add(BudgetDecimal.ZERO);
				}
				totalDomesticTravel = totalDomesticTravel.add(budgetPeriodInfo
						.getDomesticTravelCost());
				totalForeignTravel = totalForeignTravel.add(budgetPeriodInfo
						.getForeignTravelCost());
				totalDomesticTravelNonFund = totalDomesticTravelNonFund
						.add(budgetPeriodInfo.getDomesticTravelCostSharing());
				totalForeignTravelNonFund = totalForeignTravelNonFund
						.add(budgetPeriodInfo.getForeignTravelCostSharing());
				totCountOtherPersonnel = totCountOtherPersonnel
						.add(budgetPeriodInfo.getOtherPersonnelTotalNumber());

				for (EquipmentInfo equipmentInfo : budgetPeriodInfo
						.getEquipment()) {
					cumTotalEquipFund = cumTotalEquipFund.add(equipmentInfo
							.getTotalFund());
					cumTotalEquipNonFund = cumTotalEquipNonFund
							.add(equipmentInfo.getTotalNonFund());
				}

				Map<String, String> hmOthers = new HashMap<String, String>();
				cvOtherCosts = otherDirectCostInfo.getOtherCosts();
				for (int l = 0; l < cvOtherCosts.size(); l++) {
					hmOthers = cvOtherCosts.get(l);
					cumOtherType1 = cumOtherType1.add(new BudgetDecimal(
							hmOthers.get(S2SConstants.KEY_COST)));
					cumOtherType1CostSharing = cumOtherType1CostSharing
							.add(new BudgetDecimal(hmOthers
									.get(S2SConstants.KEY_COSTSHARING)));
				}
			}
		}

		budgetSummaryInfo
				.setCumDomesticTravelNonFund(totalDomesticTravelNonFund);
		budgetSummaryInfo.setCumForeignTravelNonFund(totalForeignTravelNonFund);
		budgetSummaryInfo.setCumTravelNonFund(totalDomesticTravelNonFund
				.add(totalForeignTravelNonFund));
		budgetSummaryInfo.setCumDomesticTravel(totalDomesticTravel);
		budgetSummaryInfo.setCumForeignTravel(totalForeignTravel);
		budgetSummaryInfo.setCumTravel(totalDomesticTravel
				.add(totalForeignTravel));
		budgetSummaryInfo.setpartOtherCost(cumPartOther);
		budgetSummaryInfo.setpartStipendCost(cumPartStipends);
		budgetSummaryInfo.setpartTravelCost(cumPartTravel);
		budgetSummaryInfo.setPartSubsistence(cumPartSubsistence);
		budgetSummaryInfo.setPartTuition(cumPartTuition);
		budgetSummaryInfo.setparticipantCount(cumParticipantCount);

		if(budget.getSubmitCostSharingFlag()){
		// add costSaring for fedNonFedBudget report
		budgetSummaryInfo.setPartOtherCostSharing(cumPartOtherCostSharing);
		budgetSummaryInfo.setPartStipendCostSharing(cumPartStipendsCostSharing);
		budgetSummaryInfo.setPartTravelCostSharing(cumPartTravelCostSharing);
		budgetSummaryInfo
				.setPartSubsistenceCostSharing(cumPartSubsistenceCostSharing);
		budgetSummaryInfo.setPartTuitionCostSharing(cumPartTuitionCostSharing);
		}

		OtherDirectCostInfo summaryOtherDirectCostInfo = new OtherDirectCostInfo();
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
		summaryOtherDirectCostInfo.setParticipantTotal(cumPartStipends
				.add(cumPartTravel.add(cumPartOther.add(cumPartSubsistence
						.add(cumPartTravel)))));
		summaryOtherDirectCostInfo
				.setParticipantTotalCount(cumParticipantCount);
		// start add costSaring for fedNonFedBudget report
		summaryOtherDirectCostInfo
				.setAlterationsCostSharing(cumAlterationsCostSharing);
		summaryOtherDirectCostInfo
				.setComputerCostSharing(cumComputerCostSharing);
		summaryOtherDirectCostInfo
				.setConsultantsCostSharing(cumConsultantsCostSharing);
		summaryOtherDirectCostInfo
				.setMaterialsCostSharing(cumMaterialsCostSharing);
		summaryOtherDirectCostInfo
				.setPublicationsCostSharing(cumPubsCostSharing);
		summaryOtherDirectCostInfo
				.setSubAwardsCostSharing(cumSubAwardCostSharing);
		summaryOtherDirectCostInfo
				.setEquipRentalCostSharing(cumEquipRentalCostSharing);
		summaryOtherDirectCostInfo
				.setTotalOtherDirectCostSharing(cumAllCostSharing);

		summaryOtherDirectCostInfo
				.setPartStipendsCostSharing(cumPartStipendsCostSharing);
		summaryOtherDirectCostInfo
				.setPartTravelCostSharing(cumPartTravelCostSharing);
		summaryOtherDirectCostInfo
				.setPartTuitionCostSharing(cumPartTuitionCostSharing);
		summaryOtherDirectCostInfo
				.setPartSubsistenceCostSharing(cumPartSubsistenceCostSharing);
		summaryOtherDirectCostInfo
				.setPartOtherCostSharing(cumPartOtherCostSharing);

		participantTotalCostSharing = participantTotalCostSharing
				.add(cumPartStipendsCostSharing);
		participantTotalCostSharing = participantTotalCostSharing
				.add(cumPartTravelCostSharing);
		participantTotalCostSharing = participantTotalCostSharing
				.add(cumPartOtherCostSharing);
		participantTotalCostSharing = participantTotalCostSharing
				.add(cumPartTuitionCostSharing);
		participantTotalCostSharing = participantTotalCostSharing
				.add(cumPartSubsistenceCostSharing);
		summaryOtherDirectCostInfo
				.setParticipantTotalCostSharing(participantTotalCostSharing);

		List<Map<String, String>> cvAllOthers = new ArrayList<Map<String, String>>();
		HashMap<String, String> hmAllOthers = new HashMap<String, String>();
		hmAllOthers.put(S2SConstants.KEY_COST, cumOtherType1.toString());
		hmAllOthers.put(S2SConstants.KEY_COSTSHARING, cumOtherType1CostSharing
				.toString());
		cvAllOthers.add(hmAllOthers);
		summaryOtherDirectCostInfo.setOtherCosts(cvAllOthers);

		List<OtherDirectCostInfo> cvCumOtherDirectCost = new ArrayList<OtherDirectCostInfo>(); // all
		// periods
		cvCumOtherDirectCost.add(summaryOtherDirectCostInfo);
		budgetSummaryInfo.setOtherDirectCosts(cvCumOtherDirectCost);

		budgetSummaryInfo.setCumEquipmentFunds(cumTotalEquipFund);
		budgetSummaryInfo.setCumEquipmentNonFunds(cumTotalEquipNonFund);

		// hardcoded
		budgetSummaryInfo.setCumFee(BudgetDecimal.ZERO);

		BudgetDecimal totSrFunds = BudgetDecimal.ZERO;
		BudgetDecimal totSrNonFunds = BudgetDecimal.ZERO;

		if (budgetPeriodInfos != null) {
			for (BudgetPeriodInfo budgetPeriodInfo : budgetPeriodInfos) {
				totSrFunds = totSrFunds.add(budgetPeriodInfo
						.getTotalFundsKeyPersons());
				totSrNonFunds = totSrNonFunds.add(budgetPeriodInfo
						.getTotalNonFundsKeyPersons());
			}
		}
		budgetSummaryInfo.setCumTotalFundsForSrPersonnel(totSrFunds);
		budgetSummaryInfo.setCumTotalNonFundsForSrPersonnel(totSrNonFunds);

		// other personnel - funds for other personnel will be difference
		// between total person funds and senior person funds
		budgetSummaryInfo.setCumTotalFundsForOtherPersonnel(totPersFunds
				.subtract(totSrFunds));
		budgetSummaryInfo.setCumTotalNonFundsForOtherPersonnel(totPersNonFunds
				.subtract(totSrNonFunds));
		budgetSummaryInfo.setCumNumOtherPersonnel(totCountOtherPersonnel);
		return budgetSummaryInfo;
	}

	/**
	 * This method gets the list of BudgetPeriodInfo for the latest
	 * BudgetDocument of the given ProposalDevelopmentDocument
	 * 
	 * @param pdDoc
	 *            ProposalDevelopmentDocument
	 * @return a List of BudgetPeriodInfo corresponding to the
	 *         ProposalDevelopmentDocument object.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SBudgetCalculatorService#getBudgetPeriods(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
	 */
	public List<BudgetPeriodInfo> getBudgetPeriods(
			ProposalDevelopmentDocument pdDoc) throws S2SException {
		List<BudgetPeriodInfo> budgetPeriods = new ArrayList<BudgetPeriodInfo>();
		BudgetDocument budgetDocument = getFinalBudgetVersion(pdDoc);
		Budget budget = budgetDocument == null ? null : budgetDocument
				.getBudget();
		if (budget == null) {
			return budgetPeriods;
		}

		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			BudgetPeriodInfo bpData = new BudgetPeriodInfo();
			BudgetDecimal totalCostSharing = BudgetDecimal.ZERO;
			BudgetDecimal totalDirectCostSharing = BudgetDecimal.ZERO;
			BudgetDecimal totalIndirectCostSharing = BudgetDecimal.ZERO;
			bpData.setLineItemCount(budgetPeriod.getBudgetLineItems().size());
            if(budget.getSubmitCostSharingFlag()){
			for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
    			    if(lineItem.getSubmitCostSharingFlag()){
    			        totalCostSharing = totalCostSharing.add(lineItem.getCostSharingAmount());
    			    }
				for (BudgetLineItemCalculatedAmount lineItemCalculatedAmt : lineItem
				        .getBudgetLineItemCalculatedAmounts()) {
				    lineItemCalculatedAmt.refreshReferenceObject("rateClass");
    				    if(lineItem.getSubmitCostSharingFlag()){
				    if (lineItemCalculatedAmt.getRateClass().getRateClassType()
				            .equals(RateClassType.OVERHEAD.getRateClassType())) {
        				        totalIndirectCostSharing = totalIndirectCostSharing.add(lineItemCalculatedAmt.getCalculatedCostSharing());
				    }else{
        				        totalDirectCostSharing  = totalDirectCostSharing.add(lineItemCalculatedAmt.getCalculatedCostSharing());
        				    }
    				    }
				    }
				}
    			totalDirectCostSharing = totalDirectCostSharing.add(totalCostSharing);
			}

			// populate the budgetPeriod data from the BudgetPeriod
			bpData.setFinalVersionFlag(budget.getFinalVersionFlag().toString());
			bpData.setProposalNumber(pdDoc.getDevelopmentProposal()
					.getProposalNumber());
			bpData.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
			bpData
					.setVersion(budgetPeriod.getBudget()
							.getBudgetVersionNumber());
			bpData.setStartDate(budgetPeriod.getStartDate());
			bpData.setEndDate(budgetPeriod.getEndDate());
			bpData.setTotalCosts(budgetPeriod.getTotalCost());
			bpData.setDirectCostsTotal(budgetPeriod.getTotalDirectCost());
			bpData.setTotalIndirectCost(budgetPeriod.getTotalIndirectCost());
			bpData.setCostSharingAmount(budgetPeriod.getCostSharingAmount());
			if(budget.getSubmitCostSharingFlag()){
			bpData.setTotalDirectCostSharing(totalDirectCostSharing);
			bpData.setTotalIndirectCostSharing(totalIndirectCostSharing);
		    }
			bpData.setCognizantFedAgency(s2SUtilService.getCognizantFedAgency(pdDoc.getDevelopmentProposal()));

			bpData.setIndirectCosts(getIndirectCosts(budget, budgetPeriod));
			bpData.setEquipment(getEquipment(budgetPeriod));
			bpData.setOtherDirectCosts(getOtherDirectCosts(budgetPeriod,
					S2SConstants.SPONSOR));
			if (bpData.getOtherDirectCosts().size() > 0) {
				OtherDirectCostInfo otherCostInfo = bpData
						.getOtherDirectCosts().get(0);
				bpData.setDomesticTravelCost(otherCostInfo.getDomTravel());

				bpData.setForeignTravelCost(otherCostInfo.getForeignTravel());
				bpData.setTotalTravelCost(otherCostInfo.getTotTravel());
				if(budget.getSubmitCostSharingFlag()){
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
				bpData.setparticipantCount(otherCostInfo
						.getParticpantTotalCount());
				if(budget.getSubmitCostSharingFlag()){
    				bpData.setPartOtherCostSharing(otherCostInfo.getPartOtherCostSharing());
    				bpData.setPartStipendCostSharing(otherCostInfo.getPartStipendsCostSharing());
    				bpData.setPartTravelCostSharing(otherCostInfo.getPartTravelCostSharing());
    				bpData.setPartTuitionCostSharing(otherCostInfo.getPartTuitionCostSharing());
    				bpData.setPartSubsistenceCostSharing(otherCostInfo.getPartSubsistenceCostSharing());
				}
			}
			List<List<KeyPersonInfo>> keyPersonList = getKeyPersons(
					budgetPeriod, pdDoc, MAX_KEY_PERSON_COUNT);
			List<KeyPersonInfo> keyPersons = new ArrayList<KeyPersonInfo>();
			List<KeyPersonInfo> extraPersons = new ArrayList<KeyPersonInfo>();
			if (keyPersonList.size() > 0) {
				keyPersons = keyPersonList.get(0);
			}
			if (keyPersonList.size() > 1) {
				extraPersons = keyPersonList.get(1);
			}

			bpData.setKeyPersons(keyPersons);
			bpData.setExtraKeyPersons(extraPersons);

			BudgetDecimal totalKeyPersonSum = BudgetDecimal.ZERO;
			BudgetDecimal totalKeyPersonSumCostSharing = BudgetDecimal.ZERO;
			BudgetDecimal totalAttKeyPersonSum = BudgetDecimal.ZERO;
			BudgetDecimal totalAttKeyPersonSumCostSharing = BudgetDecimal.ZERO;
			if(budget.getSubmitCostSharingFlag()){
			if (keyPersons != null) {
				for (KeyPersonInfo keyPerson : keyPersons) {
					totalKeyPersonSum = totalKeyPersonSum.add(keyPerson
							.getFundsRequested());
					totalKeyPersonSumCostSharing = totalKeyPersonSumCostSharing
							.add(keyPerson.getNonFundsRequested());
				}
			}

			if (extraPersons != null) {
				for (KeyPersonInfo keyPerson : extraPersons) {
					totalAttKeyPersonSum = totalAttKeyPersonSum.add(keyPerson
							.getFundsRequested());
					// start add costSaring for fedNonFedBudget report
					totalAttKeyPersonSumCostSharing = totalAttKeyPersonSumCostSharing
							.add(keyPerson.getNonFundsRequested());
				}
			}
			}
			bpData.setTotalFundsKeyPersons(totalKeyPersonSum
					.add(totalAttKeyPersonSum));
			bpData.setTotalFundsAttachedKeyPersons(totalAttKeyPersonSum);
			bpData.setTotalNonFundsKeyPersons(totalKeyPersonSumCostSharing
					.add(totalAttKeyPersonSumCostSharing));
			bpData
					.setTotalNonFundsAttachedKeyPersons(totalAttKeyPersonSumCostSharing);

			List<OtherPersonnelInfo> otherPersonnel = getOtherPersonnel(
					budgetPeriod, pdDoc);
			bpData.setOtherPersonnel(otherPersonnel);
			BudgetDecimal otherPersonnelCount = BudgetDecimal.ZERO;
			BudgetDecimal otherPersonnelTotalFunds = BudgetDecimal.ZERO;
			BudgetDecimal otherPersonnelTotalNonFunds = BudgetDecimal.ZERO;

			for (OtherPersonnelInfo otherPersonnelInfo : otherPersonnel) {
				otherPersonnelCount = otherPersonnelCount
						.add(new BudgetDecimal(otherPersonnelInfo
								.getNumberPersonnel()));
				otherPersonnelTotalFunds = otherPersonnelTotalFunds
						.add(otherPersonnelInfo.getCompensation()
								.getFundsRequested());
				otherPersonnelTotalNonFunds = otherPersonnelTotalNonFunds
						.add(otherPersonnelInfo.getCompensation()
								.getNonFundsRequested());
			}
			bpData.setTotalOtherPersonnelFunds(otherPersonnelTotalFunds);
			bpData.setOtherPersonnelTotalNumber(otherPersonnelCount);
			bpData.setTotalCompensation(otherPersonnelTotalFunds.add(
					totalKeyPersonSum).add(totalAttKeyPersonSum));
			bpData.setTotalOtherPersonnelNonFunds(otherPersonnelTotalNonFunds);
			bpData.setTotalCompensationCostSharing(otherPersonnelTotalNonFunds
					.add(totalKeyPersonSumCostSharing).add(
							totalAttKeyPersonSumCostSharing));

			budgetPeriods.add(bpData);
		}
		return budgetPeriods;
	}

	/**
	 * 
	 * This method populates the {@link OtherPersonnelInfo} business objects for
	 * the given {@link BudgetPeriod} and {@link ProposalDevelopmentDocument}
	 * 
	 * @param budgetPeriod
	 *            given budget period.
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return {@link List} of {@link OtherPersonnelInfo}
	 */
	protected List<OtherPersonnelInfo> getOtherPersonnel(
			BudgetPeriod budgetPeriod, ProposalDevelopmentDocument pdDoc) {
		List<OtherPersonnelInfo> cvOtherPersonnel = new ArrayList<OtherPersonnelInfo>();
		cvOtherPersonnel.add(getOtherPersonnelDetails(budgetPeriod, pdDoc,
				CATEGORY_01_GRADUATES, PERSONNEL_TYPE_GRAD,
				ROLE_GRADUATE_STUDENTS));
		cvOtherPersonnel.add(getOtherPersonnelDetails(budgetPeriod, pdDoc,
				CATEGORY_01_POSTDOCS, PERSONNEL_TYPE_POSTDOC,
				ROLE_POST_DOCTORAL_ASSOCIATES));
		cvOtherPersonnel.add(getOtherPersonnelDetails(budgetPeriod, pdDoc,
				CATEGORY_01_UNDERGRADS, PERSONNEL_TYPE_UNDERGRAD,
				ROLE_GRADUATE_UNDERGRADUATE_STUDENTS));
		cvOtherPersonnel.add(getOtherPersonnelDetails(budgetPeriod, pdDoc,
				CATEGORY_01_SECRETARIAL, PERSONNEL_TYPE_SEC,
				ROLE_GRADUATE_SECRETARIAL_OR_CLERICAL));
		cvOtherPersonnel.add(getOtherPersonnelDetails(budgetPeriod, pdDoc,
				CATEGORY_01_OTHER, PERSONNEL_TYPE_OTHER, ROLE_GRADUATE_OTHER));
		cvOtherPersonnel.add(getOtherPersonnelDetails(budgetPeriod, pdDoc,
                CATEGORY_01_OTHER_PROFS, PERSONNEL_TYPE_OTHER_PROFESSIONALS,
                ROLE_GRADUATE_OTHET_PROFESSIONALS));
		cvOtherPersonnel.add(getOtherPersonnelDetails(budgetPeriod, pdDoc,
				LASALARIES, PERSONNEL_TYPE_ALLOCATED_ADMIN_SUPPORT,
				ROLE_GRADUATE_ALLOCATED_ADMIN_SUPPORT));
		return cvOtherPersonnel;
	}

	/**
	 * 
	 * This method populates the details for {@link OtherPersonnelInfo} business
	 * object for the given {@link ProposalDevelopmentDocument}
	 * 
	 * @param budgetPeriod
	 *            given budget period.
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @param category
	 *            budget category
	 * @param personnelType
	 *            proposal personnel type.
	 * @param role
	 *            role of the proposal person.
	 * @return OtherPersonnelInfo information about the other personnel.
	 */
	protected OtherPersonnelInfo getOtherPersonnelDetails(
			BudgetPeriod budgetPeriod, ProposalDevelopmentDocument pdDoc,
			String category, String personnelType, String role) {
		OtherPersonnelInfo otherPersonnelInfo = new OtherPersonnelInfo();

		int count = 0;
		BudgetDecimal salaryRequested = BudgetDecimal.ZERO;
		BudgetDecimal salaryCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal lineItemCost = BudgetDecimal.ZERO;
		BudgetDecimal lineItemCostSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal mrLaCost = BudgetDecimal.ZERO;
		BudgetDecimal mrLaCostSharingAmount = BudgetDecimal.ZERO;

		BudgetDecimal fringeCost = BudgetDecimal.ZERO;
		BudgetDecimal fringeCostSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal mrLaFringeCost = BudgetDecimal.ZERO;
		BudgetDecimal mrLaFringeCostSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal budgetLineItemFringeCost = BudgetDecimal.ZERO;
		BudgetDecimal budgetLineItemFringeCostSharingAmount = BudgetDecimal.ZERO;

		BudgetDecimal bdSalary = BudgetDecimal.ZERO;
		BudgetDecimal bdFringe = BudgetDecimal.ZERO;
		BudgetDecimal bdFunds = BudgetDecimal.ZERO;
		BudgetDecimal bdSalaryCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal bdFringeCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal bdNonFunds = BudgetDecimal.ZERO;

		BudgetDecimal academicMonths = BudgetDecimal.ZERO;
		BudgetDecimal summerMonths = BudgetDecimal.ZERO;
		BudgetDecimal calendarMonths = BudgetDecimal.ZERO;
		BudgetDecimal cycleMonths = BudgetDecimal.ZERO;

		BudgetDecimal numberOfMonths = BudgetDecimal.ZERO;
		Map<String, String> personJobCodes = new HashMap<String, String>();
		boolean personExistsAsProposalPerson = false;
		// boolean lineItemMatched;
		for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
			// lineItemMatched = false;

			Map<String, String> categoryMap = new HashMap<String, String>();
			categoryMap.put(KEY_TARGET_CATEGORY_CODE, category);
			categoryMap.put(KEY_MAPPING_NAME, S2SConstants.SPONSOR);
			List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);

			for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
				if (categoryMapping.getBudgetCategoryCode().equals(
						lineItem.getBudgetCategoryCode())) {
					List<BudgetPersonnelDetails> lineItemPersonDetails = lineItem
							.getBudgetPersonnelDetailsList();
					boolean personExist = !lineItemPersonDetails.isEmpty();
					if (personExist) {
						for (BudgetPersonnelDetails personDetails : lineItemPersonDetails) {
							if (categoryMapping.getBudgetCategoryCode().equals(
									lineItem.getBudgetCategoryCode())) {
								String budgetPersonId = personDetails
										.getPersonId();
								personExistsAsProposalPerson = false;
								// get sum of salary of other personnel, but
								// exclude the key persons and investigators
								for (ProposalPerson proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
									if (budgetPersonId.equals(proposalPerson.getPersonId()) ||(proposalPerson.getRolodexId()!=null && budgetPersonId.equals(proposalPerson.getRolodexId().toString()))) {
										personExistsAsProposalPerson = true;
										break;
									}
								}
								if (!personExistsAsProposalPerson) {
									salaryRequested = salaryRequested.add(personDetails.getSalaryRequested());
									salaryCostSharing = salaryCostSharing.add(personDetails.getCostSharingAmount());

									numberOfMonths = s2SUtilService.getNumberOfMonths(personDetails.getStartDate(),personDetails.getEndDate());
									if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_ACADEMIC_MONTHS)) {
										academicMonths = academicMonths.add(personDetails.getPercentEffort().multiply(numberOfMonths).multiply(new BudgetDecimal(0.01)));
									} else if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_SUMMER_MONTHS)) {
										summerMonths = summerMonths.add(personDetails.getPercentEffort().multiply(numberOfMonths).multiply(new BudgetDecimal(0.01)));
									} else if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_CALENDAR_MONTHS)) {
										calendarMonths = calendarMonths.add(personDetails.getPercentEffort().multiply(numberOfMonths).multiply(new BudgetDecimal(0.01)));
									} else if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_CYCLE_MONTHS)) {
										cycleMonths = cycleMonths.add(personDetails.getPercentEffort().multiply(numberOfMonths).multiply(new BudgetDecimal(0.01)));
									}
									// Get total count of unique
									// personId+jobCode combination for those
									// persons who are part of
									// BudgetPersonnelDetails but are not
									// proposal persons
									personJobCodes.put(personDetails.getPersonId()+ personDetails.getJobCode(), "");
									// Calcculate the fringe cost
									for (BudgetPersonnelCalculatedAmount personCalculatedAmount : (List<BudgetPersonnelCalculatedAmount>) personDetails
											.getBudgetCalculatedAmounts()) {
										if ((personCalculatedAmount.getRateClassCode().equals(RATE_CLASS_CODE_EMPLOYEE_BENEFITS) && !personCalculatedAmount.getRateTypeCode().equals(RATE_TYPE_SUPPORT_STAFF_SALARIES))
												|| (personCalculatedAmount.getRateClassCode().equals(RATE_CLASS_CODE_VACATION) && !personCalculatedAmount.getRateTypeCode().equals(RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
											fringeCost = fringeCost.add(personCalculatedAmount.getCalculatedCost());
											fringeCostSharingAmount = fringeCostSharingAmount.add(personCalculatedAmount.getCalculatedCostSharing());
										}
									}
								}
								// if
								// (personDetails.getLineItemNumber().equals(lineItem.getLineItemNumber()))
								// {
								// lineItemMatched = true;
								// }
							}
						}
					} else {
						// personExist is false. No person found for the line
						// item.
						// get costs for this budget category that do not have
						// persons attached to the cost element
						lineItemCost = lineItemCost.add(lineItem.getLineItemCost());
						lineItemCostSharingAmount = lineItemCostSharingAmount.add(lineItem.getCostSharingAmount());
						count = lineItem.getQuantity();
						for (BudgetLineItemCalculatedAmount lineItemCalculatedAmount : lineItem.getBudgetLineItemCalculatedAmounts()) {
							lineItemCalculatedAmount.refreshReferenceObject("rateClass");

							// Calculate fringe cost
							if(lineItemCalculatedAmount.getRateClass().getRateClassType().equalsIgnoreCase("E")){
							    fringeCost = fringeCost.add(lineItemCalculatedAmount.getCalculatedCost());
							}
							if ((lineItemCalculatedAmount.getRateClassCode()
									.equals(RATE_CLASS_CODE_EMPLOYEE_BENEFITS) && !lineItemCalculatedAmount
									.getRateTypeCode().equals(RATE_TYPE_SUPPORT_STAFF_SALARIES))
									|| (lineItemCalculatedAmount.getRateClassCode().equals(
													RATE_CLASS_CODE_VACATION) && !lineItemCalculatedAmount
											.getRateTypeCode().equals(RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
								fringeCostSharingAmount = fringeCostSharingAmount.add(lineItemCalculatedAmount.getCalculatedCostSharing());
							}

							// Calculate the salary and fringe for category
							// LASALARIES
							if (category.equalsIgnoreCase(LASALARIES)) {
								// Caluclate LA for rate class type Y
								if (lineItemCalculatedAmount
										.getRateClass()
										.getRateClassType()
										.equals(
												RATE_CLASS_TYPE_LAB_ALLOCATION_SALARIES)) {
									mrLaCost = mrLaCost
											.add(lineItemCalculatedAmount
													.getCalculatedCost());
									mrLaCostSharingAmount = mrLaCostSharingAmount
											.add(lineItemCalculatedAmount
													.getCalculatedCostSharing());
								}

								// Calculate the fringe
								if ((lineItemCalculatedAmount
										.getRateClass()
										.getRateClassType()
										.equals(
												RATE_CLASS_TYPE_EMPLOYEE_BENEFITS) && lineItemCalculatedAmount
										.getRateTypeCode()
										.equals(
												RATE_TYPE_SUPPORT_STAFF_SALARIES))
										|| (lineItemCalculatedAmount
												.getRateClass()
												.getRateClassType()
												.equals(
														RATE_CLASS_TYPE_VACATION) && lineItemCalculatedAmount
												.getRateTypeCode()
												.equals(
														RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
									mrLaFringeCost = mrLaFringeCost
											.add(lineItemCalculatedAmount
													.getCalculatedCost());
									mrLaFringeCostSharingAmount = mrLaFringeCostSharingAmount
											.add(lineItemCalculatedAmount
													.getCalculatedCostSharing());
								}
							}
						}
					}
				}
			}
		}

		// Set the salary amounts
		bdSalary = bdSalary.add(salaryRequested).add(lineItemCost)
				.add(mrLaCost);
		bdSalaryCostSharing = bdSalaryCostSharing.add(salaryCostSharing).add(
				lineItemCostSharingAmount).add(mrLaCostSharingAmount);

		// Set the fringe costs
		bdFringe = bdFringe.add(fringeCost).add(budgetLineItemFringeCost).add(
				mrLaFringeCost);
		bdFringeCostSharing = bdFringeCostSharing.add(fringeCostSharingAmount)
				.add(budgetLineItemFringeCostSharingAmount).add(
						mrLaFringeCostSharingAmount);

		bdNonFunds = bdSalaryCostSharing.add(bdFringeCostSharing);
		bdFunds = bdSalary.add(bdFringe);
		
		count = personJobCodes.isEmpty()?count:personJobCodes.size();
		otherPersonnelInfo.setNumberPersonnel(count);
		otherPersonnelInfo.setPersonnelType(personnelType);
		otherPersonnelInfo.setRole(role);

		CompensationInfo compensationInfo = new CompensationInfo();
		// not sure that we need base salary
		compensationInfo.setBaseSalary(BudgetDecimal.ZERO);
		compensationInfo.setFringe(bdFringe);
		compensationInfo.setFundsRequested(bdFunds);
		compensationInfo.setRequestedSalary(bdSalary);
		compensationInfo.setSummerMonths(summerMonths.setScale());
		compensationInfo.setAcademicMonths(academicMonths.setScale());
		compensationInfo.setCalendarMonths(calendarMonths.setScale());

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
	 * This method computes the indirect costs for a given {@link BudgetPeriod}
	 * 
	 * @param budgetPeriod
	 *            given BudgetPeriod.
	 * @return IndirectCostInfo for the corresponding BudgetPeriod object.
	 */
	public IndirectCostInfo getIndirectCosts(Budget budget,
			BudgetPeriod budgetPeriod) {
		List<IndirectCostDetails> indirectCostDetailList = new ArrayList<IndirectCostDetails>();
		IndirectCostDetails indirectCostDetails;
		BudgetDecimal baseCost = BudgetDecimal.ZERO;
		BudgetDecimal baseCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		BudgetDecimal calculatedCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal appliedRate = BudgetDecimal.ZERO;
		BudgetDecimal totalIndirectCosts = BudgetDecimal.ZERO;
		BudgetDecimal totalIndirectCostSharing = BudgetDecimal.ZERO;

		String description = "";
		boolean firstLoop = true;

		if (budget.getModularBudgetFlag()) {
			for (BudgetModularIdc budgetModularIdc : budgetPeriod
					.getBudgetModular().getBudgetModularIdcs()) {
				if (firstLoop) {
					appliedRate = appliedRate
							.add(budgetModularIdc.getIdcRate());
					description = budgetModularIdc.getDescription();
					firstLoop = false;
				}
				baseCost = baseCost.add(budgetModularIdc.getIdcBase());
				calculatedCost = calculatedCost.add(budgetModularIdc
						.getFundsRequested());
			}
			indirectCostDetails = new IndirectCostDetails();
			indirectCostDetails.setBase(baseCost);
			indirectCostDetails.setBaseCostSharing(baseCostSharing);
			indirectCostDetails.setCostSharing(calculatedCostSharing);
			indirectCostDetails.setCostType(description);
			indirectCostDetails.setFunds(calculatedCost);
			indirectCostDetails.setRate(appliedRate);
			indirectCostDetailList.add(indirectCostDetails);
			totalIndirectCosts = totalIndirectCosts.add(calculatedCost);
			totalIndirectCostSharing = totalIndirectCostSharing
					.add(calculatedCostSharing);
		} else {
			Map<String, IndirectCostDetails> costDetailsMap = new HashMap<String, IndirectCostDetails>();
			for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
				for (BudgetRateAndBase rateBase : lineItem
						.getBudgetRateAndBaseList()) {
					RateClass rateClass = rateBase.getRateClass();
					if (rateClass.getRateClassType().equals(RateClassType.OVERHEAD.getRateClassType())) {
						String rateClassCode = rateClass.getRateClassCode();
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
							indirectCostDetails = new IndirectCostDetails();
							indirectCostDetails
									.setBase(rateBase.getBaseCost() == null ? BudgetDecimal.ZERO
											: rateBase.getBaseCost());
							indirectCostDetails
									.setBaseCostSharing(rateBase
											.getBaseCostSharing() == null ? BudgetDecimal.ZERO
											: rateBase.getBaseCostSharing());
							if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
							indirectCostDetails
									.setCostSharing(rateBase
											.getCalculatedCostSharing() == null ? BudgetDecimal.ZERO
											: rateBase
													.getCalculatedCostSharing());
							}
							indirectCostDetails.setCostType(rateClass
									.getDescription());
							indirectCostDetails
									.setFunds(rateBase.getCalculatedCost() == null ? BudgetDecimal.ZERO
											: rateBase.getCalculatedCost());
							indirectCostDetails.setRate(appliedRate);
						} else {
							indirectCostDetails = costDetailsMap.get(key);
							baseCost = indirectCostDetails
									.getBase()
									.add(
											rateBase.getBaseCost() == null ? BudgetDecimal.ZERO
													: rateBase.getBaseCost());
							baseCostSharing = indirectCostDetails
									.getBaseCostSharing()
									.add(
											rateBase.getBaseCostSharing() == null ? BudgetDecimal.ZERO
													: rateBase
															.getBaseCostSharing());
							calculatedCost = indirectCostDetails
									.getFunds()
									.add(
											rateBase.getCalculatedCost() == null ? BudgetDecimal.ZERO
													: rateBase
															.getCalculatedCost());
							if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
							calculatedCostSharing = indirectCostDetails
									.getCostSharing()
									.add(
											rateBase.getCalculatedCostSharing() == null ? BudgetDecimal.ZERO
													: rateBase
															.getCalculatedCostSharing());
							}
							indirectCostDetails.setBase(baseCost);
							indirectCostDetails
									.setBaseCostSharing(baseCostSharing);
							indirectCostDetails
									.setCostSharing(calculatedCostSharing);
							indirectCostDetails.setFunds(calculatedCost);
						}
						costDetailsMap.put(key, indirectCostDetails);

						indirectCostDetailList = new ArrayList<IndirectCostDetails>(
								costDetailsMap.values());
						totalIndirectCosts = totalIndirectCosts
								.add(rateBase.getCalculatedCost() == null ? BudgetDecimal.ZERO
										: rateBase.getCalculatedCost());
						if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
						totalIndirectCostSharing = totalIndirectCostSharing
								.add(rateBase.getCalculatedCostSharing() == null ? BudgetDecimal.ZERO
										: rateBase.getCalculatedCostSharing());
					}
				}
			}
		}
		}
		IndirectCostInfo indirectCostInfo = new IndirectCostInfo();
		indirectCostInfo.setIndirectCostDetails(indirectCostDetailList);
		indirectCostInfo.setTotalIndirectCosts(totalIndirectCosts);
		indirectCostInfo.setTotalIndirectCostSharing(totalIndirectCostSharing);
		return indirectCostInfo;
	}

	/**
	 * 
	 * This method computes Other Dirtect Costs for the given
	 * {@link BudgetPeriod} and Sponsor
	 * 
	 * @param budgetPeriod
	 *            given BudgetPeriod.
	 * @param sponsor
	 *            sponsor detail.
	 * @return List<OtherDirectCostInfo> list of OtherDirectCostInfo
	 *         corresponding to the BudgetPeriod object.
	 */
	protected List<OtherDirectCostInfo> getOtherDirectCosts(
			BudgetPeriod budgetPeriod, String sponsor) {
	    Budget budget = budgetPeriod.getBudget();
		OtherDirectCostInfo otherDirectCostInfo = new OtherDirectCostInfo();

		List<CostInfo> costInfoList = new ArrayList<CostInfo>();
		List<String> filterTargetCategoryCodes = new ArrayList<String>();
		filterTargetCategoryCodes.add(TARGET_CATEGORY_CODE_EQUIPMENT_COST);
		List<String> filterCategoryTypes = new ArrayList<String>();
		filterCategoryTypes.add(FILTER_CATEGORY_TYPE_PERSONNEL);
		List<BudgetCategoryMap> budgetCategoryMapList = getBudgetCategoryMapList(
				filterTargetCategoryCodes, filterCategoryTypes);

		boolean recordAdded;
		for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetCategoryMap budgetCategoryMap : budgetCategoryMapList) {
				recordAdded = false;
				for (BudgetCategoryMapping budgetCategoryMapping : budgetCategoryMap
						.getBudgetCategoryMappings()) {
					if (lineItem.getBudgetCategoryCode().equals(
							budgetCategoryMapping.getBudgetCategoryCode())) {
						CostInfo costInfo = new CostInfo();
						costInfo.setBudgetPeriod(budgetPeriod.getBudgetPeriod()
								.intValue());
						costInfo.setCost(lineItem.getLineItemCost());
						if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
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

			CostInfo lineItemcostInfo = new CostInfo();
			lineItemcostInfo.setBudgetPeriod(budgetPeriod.getBudgetPeriod()
					.intValue());
			lineItemcostInfo.setCategory(OTHER_DIRECT_COSTS);
			lineItemcostInfo.setCategoryType(CATEGORY_TYPE_OTHER_DIRECT_COST);
			lineItemcostInfo.setQuantity(1);

			BudgetDecimal totalCost = BudgetDecimal.ZERO;
			BudgetDecimal totalCostSharing = BudgetDecimal.ZERO;
			for (BudgetLineItemCalculatedAmount lineItemCalculatedAmt : lineItem
					.getBudgetLineItemCalculatedAmounts()) {
				lineItemCalculatedAmt.refreshReferenceObject("rateClass");
				if (lineItemCalculatedAmt.getRateClass().getRateClassType()
						.equals(RATE_CLASS_TYPE_SALARIES_MS)) {
					totalCost = totalCost.add(lineItemCalculatedAmt
							.getCalculatedCost());
					if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
					    totalCostSharing = totalCostSharing.add(lineItemCalculatedAmt.getCalculatedCostSharing());
					}
				}
			}
			lineItemcostInfo.setCost(totalCost);
			if(canBudgetLineItemCostSharingInclude(budget, lineItem)){
			lineItemcostInfo.setCostSharing(totalCostSharing);
			}
			StringBuilder description = new StringBuilder();
			description.append(DESCRIPTION_LA);
			description.append(totalCost);
			description.append(";");
			lineItemcostInfo.setDescription(description.toString());
			costInfoList.add(lineItemcostInfo);
		}

		BudgetDecimal totalOtherDirect = BudgetDecimal.ZERO;
		BudgetDecimal totalTravelCost = BudgetDecimal.ZERO;
		BudgetDecimal totalParticipantCost = BudgetDecimal.ZERO;
		BudgetDecimal totalOtherDirectCostSharing = BudgetDecimal.ZERO;

		BudgetDecimal totalTravelCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal totalParticipantCostSharing = BudgetDecimal.ZERO;
		int totalParticipantCount = 0;

		BudgetDecimal materialCost = BudgetDecimal.ZERO;
		BudgetDecimal materialCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal consultantCost = BudgetDecimal.ZERO;
		BudgetDecimal consultantCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal publicationCost = BudgetDecimal.ZERO;
		BudgetDecimal publicationCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal computerCost = BudgetDecimal.ZERO;
		BudgetDecimal computerCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal alterationsCost = BudgetDecimal.ZERO;
		BudgetDecimal alterationsCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal subContractCost = BudgetDecimal.ZERO;
		BudgetDecimal subContractCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal equipmentRentalCost = BudgetDecimal.ZERO;
		BudgetDecimal equipmentRentalCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal domesticTravelCost = BudgetDecimal.ZERO;
		BudgetDecimal domesticTravelCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal foreignTravelCost = BudgetDecimal.ZERO;
		BudgetDecimal foreignTravelCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal partStipendsCost = BudgetDecimal.ZERO;
		BudgetDecimal partStipendsCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal partTravelCost = BudgetDecimal.ZERO;
		BudgetDecimal partTravelCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal partTuitionCost = BudgetDecimal.ZERO;
		BudgetDecimal partTuitionCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal partSubsistenceCost = BudgetDecimal.ZERO;
		BudgetDecimal partSubsistenceCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal partOtherCost = BudgetDecimal.ZERO;
		BudgetDecimal partOtherCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal otherDirectCost = BudgetDecimal.ZERO;
		BudgetDecimal otherDirectCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal otherCost = BudgetDecimal.ZERO;
		BudgetDecimal otherCostSharing = BudgetDecimal.ZERO;

		for (CostInfo costInfo : costInfoList) {
			if (costInfo.getCategory().equals(MATERIALS_AND_SUPPLIES_CATEGORY)) {
				materialCost = materialCost.add(costInfo.getCost());
				if(budget.getSubmitCostSharingFlag()){
				    materialCostSharing = materialCostSharing.add(costInfo.getCostSharing());
				}
			} else if (costInfo.getCategory().equals(CONSULTANT_COSTS_CATEGORY)) {
				consultantCost = consultantCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    consultantCostSharing = consultantCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory()
					.equals(PUBLICATION_COSTS_CATEGORY)) {
				publicationCost = publicationCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    publicationCostSharing = publicationCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory()
					.equals(COMPUTER_SERVICES_CATEGORY)) {
				computerCost = computerCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    computerCostSharing = computerCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory().equals(ALTERATIONS_CATEGORY)) {
				alterationsCost = alterationsCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    alterationsCostSharing = alterationsCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory().equals(SUBCONTRACT_CATEGORY)) {
				subContractCost = subContractCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    subContractCostSharing = subContractCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory().equals(EQUIPMENT_RENTAL_CATEGORY)) {
				equipmentRentalCost = equipmentRentalCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    equipmentRentalCostSharing = equipmentRentalCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory().equals(DOMESTIC_TRAVEL_CATEGORY)) {
				domesticTravelCost = domesticTravelCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    domesticTravelCostSharing = domesticTravelCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory().equals(FOREIGN_TRAVEL_CATEGORY)) {
				foreignTravelCost = foreignTravelCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    foreignTravelCostSharing = foreignTravelCostSharing.add(costInfo.getCostSharing());
                }
			} else if (costInfo.getCategory().equals(
					PARTICIPANT_STIPENDS_CATEGORY)) {
				partStipendsCost = partStipendsCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    partStipendsCostSharing = partStipendsCostSharing.add(costInfo.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing.add(costInfo.getCostSharing());
                }
				totalParticipantCost = totalParticipantCost.add(costInfo.getCost());
				totalParticipantCount += costInfo.getQuantity();
			} else if (costInfo.getCategory().equals(
					PARTICIPANT_TRAVEL_CATEGORY)) {
				partTravelCost = partTravelCost.add(costInfo.getCost());
				if(budget.getSubmitCostSharingFlag()){
				partTravelCostSharing = partTravelCostSharing.add(costInfo
						.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing
                            .add(costInfo.getCostSharing());
				}
				totalParticipantCost = totalParticipantCost.add(costInfo
						.getCost());
				totalParticipantCount += costInfo.getQuantity();
			} else if (costInfo.getCategory().equals(
					PARTICIPANT_TUTION_CATEGORY)) {
				partTuitionCost = partTuitionCost.add(costInfo.getCost());
				if(budget.getSubmitCostSharingFlag()){
				    partTuitionCostSharing = partTuitionCostSharing.add(costInfo.getCostSharing());
				    totalParticipantCostSharing = totalParticipantCostSharing.add(costInfo.getCostSharing());
				}
				totalParticipantCost = totalParticipantCost.add(costInfo
						.getCost());
				totalParticipantCount += costInfo.getQuantity();
			} else if (costInfo.getCategory().equals(
					PARTICIPANT_SUBSISTENCE_CATEGORY)) {
				partSubsistenceCost = partSubsistenceCost.add(costInfo
						.getCost());
				if(budget.getSubmitCostSharingFlag()){
				partSubsistenceCostSharing = partSubsistenceCostSharing
						.add(costInfo.getCostSharing());
	                totalParticipantCostSharing = totalParticipantCostSharing
	                        .add(costInfo.getCostSharing());
				}
				totalParticipantCost = totalParticipantCost.add(costInfo
						.getCost());
				
				totalParticipantCount += costInfo.getQuantity();
			} else if (costInfo.getCategory()
					.equals(PARTICIPANT_OTHER_CATEGORY)) {
				partOtherCost = partOtherCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
				partOtherCostSharing = partOtherCostSharing.add(costInfo
						.getCostSharing());
                    totalParticipantCostSharing = totalParticipantCostSharing
                            .add(costInfo.getCostSharing());
                }
				totalParticipantCost = totalParticipantCost.add(costInfo
						.getCost());
				totalParticipantCount += costInfo.getQuantity();
			} else if (costInfo.getCategory().equals(
					OTHER_DIRECT_COSTS_CATEGORY)) {
				otherDirectCost = otherDirectCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
                    otherDirectCostSharing = otherDirectCostSharing.add(costInfo.getCostSharing());
                }
			} else {
				otherCost = otherCost.add(costInfo.getCost());
                if(budget.getSubmitCostSharingFlag()){
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
		otherDirectCostInfo
				.setEquipRentalCostSharing(equipmentRentalCostSharing);
		otherDirectCostInfo.setDomTravel(domesticTravelCost);
		otherDirectCostInfo.setDomTravelCostSharing(domesticTravelCostSharing);
		otherDirectCostInfo.setForeignTravel(foreignTravelCost);
		otherDirectCostInfo
				.setForeignTravelCostSharing(foreignTravelCostSharing);
		otherDirectCostInfo.setPartStipends(partStipendsCost);
		otherDirectCostInfo.setPartStipendsCostSharing(partStipendsCostSharing);
		otherDirectCostInfo.setPartTravel(partTravelCost);
		otherDirectCostInfo.setPartTravelCostSharing(partTravelCostSharing);
		otherDirectCostInfo.setPartTuition(partTuitionCost);
		otherDirectCostInfo.setPartTuitionCostSharing(partTuitionCostSharing);
		otherDirectCostInfo.setPartSubsistence(partSubsistenceCost);
		otherDirectCostInfo
				.setPartSubsistenceCostSharing(partSubsistenceCostSharing);
		otherDirectCostInfo.setPartOther(partOtherCost);
		otherDirectCostInfo.setPartOtherCostSharing(partOtherCostSharing);
		otherDirectCostInfo.setParticipantTotal(totalParticipantCost);
		otherDirectCostInfo
				.setParticipantTotalCostSharing(totalParticipantCostSharing);
		otherDirectCostInfo.setParticipantTotalCount(totalParticipantCount);

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
        if(budget.getSubmitCostSharingFlag()){
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(materialCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(consultantCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(publicationCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(computerCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(alterationsCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(subContractCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(equipmentRentalCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(otherDirectCostSharing);
		totalOtherDirectCostSharing = totalOtherDirectCostSharing
				.add(otherCostSharing);

		totalTravelCostSharing = totalTravelCostSharing
				.add(domesticTravelCostSharing);
		totalTravelCostSharing = totalTravelCostSharing
				.add(foreignTravelCostSharing);
        }
		otherDirectCostInfo.settotalOtherDirect(totalOtherDirect);
		otherDirectCostInfo
				.setTotalOtherDirectCostSharing(totalOtherDirectCostSharing);
		otherDirectCostInfo.setTotTravel(totalTravelCost);
		otherDirectCostInfo.setTotTravelCostSharing(totalTravelCostSharing);

		List<Map<String, String>> otherCostDetails = new ArrayList<Map<String, String>>();
		Map<String, String> hmOtherDirectCostDetails = new HashMap<String, String>();
		hmOtherDirectCostDetails.put(S2SConstants.KEY_COST, otherDirectCost
				.toString());
		hmOtherDirectCostDetails.put(DESCRIPTION, OTHER_DIRECT_COSTS);
		hmOtherDirectCostDetails.put(S2SConstants.KEY_COSTSHARING,
				otherDirectCostSharing.toString());
		otherCostDetails.add(hmOtherDirectCostDetails);

		Map<String, String> hmOtherCostDetails = new HashMap<String, String>();
		hmOtherCostDetails.put(S2SConstants.KEY_COST, otherCost.toString());
		hmOtherCostDetails.put(DESCRIPTION, ALL_OTHER_COSTS);
		hmOtherCostDetails.put(S2SConstants.KEY_COSTSHARING, otherCostSharing
				.toString());
		otherCostDetails.add(hmOtherCostDetails);

		otherDirectCostInfo.setOtherCosts(otherCostDetails);
		List<OtherDirectCostInfo> otherDirectCosts = new ArrayList<OtherDirectCostInfo>();
		otherDirectCosts.add(otherDirectCostInfo);
		return otherDirectCosts;
	}

    private boolean canBudgetLineItemCostSharingInclude(Budget budget, BudgetLineItem lineItem) {
        return budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag();
    }

	/**
	 * This method returns a list of BudgetCategoryMap based on the input. The
	 * list returned will not contain the categories that the codes passed as a
	 * list of String and also will not contain those that match the types
	 * passed as list of String. In case 2 empty lists are passed as parameters,
	 * the method will return entire list without applying any filters.
	 * 
	 * @param filterTargetCategoryCodes
	 *            Category Codes that must be filtered
	 * @param filterCategoryTypes
	 *            Category types that must be filtered
	 * @return a List of BudgetCategoryMap.
	 * @see org.kuali.kra.s2s.service.S2SBudgetCalculatorService#getBudgetCategoryMapList(java.util.List,
	 *      java.util.List)
	 */
	public List<BudgetCategoryMap> getBudgetCategoryMapList(
			List<String> filterTargetCategoryCodes,
			List<String> filterCategoryTypes) {
		List<BudgetCategoryMapping> budgetCategoryMappingList;
		List<BudgetCategoryMap> budgetCategoryMapList = new ArrayList<BudgetCategoryMap>();
		Map<String, String> categoryMap = new HashMap<String, String>();
		categoryMap.put(KEY_MAPPING_NAME, S2SConstants.SPONSOR);
		budgetCategoryMappingList = getBudgetCategoryMappings(categoryMap);

		boolean targetMatched;
		boolean duplicateExists;
		for (BudgetCategoryMapping categoryMapping : budgetCategoryMappingList) {
			targetMatched = true;
			// Apply mapping list filtering only if targetCategoryCodes size>0
			if (filterTargetCategoryCodes.size() > 0) {
				for (String targetCategoryCode : filterTargetCategoryCodes) {
					if (categoryMapping.getTargetCategoryCode().equals(
							targetCategoryCode)) {
						targetMatched = false;
						break;
					}
				}
			}

			if (targetMatched) {
				Map<String, String> conditionMap = new HashMap<String, String>();
				conditionMap.put(KEY_MAPPING_NAME, categoryMapping
						.getMappingName());
				conditionMap.put(KEY_TARGET_CATEGORY_CODE, categoryMapping
						.getTargetCategoryCode());
				Iterator<BudgetCategoryMap> filterList = businessObjectService
						.findMatching(BudgetCategoryMap.class, conditionMap)
						.iterator();
				while (filterList.hasNext()) {
					BudgetCategoryMap filterMap = filterList.next();

					// The database defines category type as CHAR(200), causing
					// spaces to be appended
					// to the category name. Trim this. Can be removed if the db
					// is chaged to VARCHAR
					filterMap.setCategoryType(filterMap.getCategoryType()
							.trim());

					// Apply categoryType filtering only if filterCategorytypes
					// size>0
					targetMatched = true;
					if (filterCategoryTypes.size() > 0) {
						for (String categoryType : filterCategoryTypes) {
							if (filterMap.getCategoryType()
									.equals(categoryType)) {
								targetMatched = false;
								break;
							}
						}
					}

					if (targetMatched) {
						// Avoid adding duplicates to list
						duplicateExists = false;
						for (BudgetCategoryMap map : budgetCategoryMapList) {
							if (filterMap.getTargetCategoryCode().equals(
									map.getTargetCategoryCode())) {
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
	 * This method computes the Equipment Costs for a given {@link BudgetPeriod}
	 * 
	 * @param budgetPeriod
	 *            given BudgetPeriod.
	 * @return List<EquipmentInfo> list of equipment cost corresponding to the
	 *         BudgetPeriod object.
	 */
	protected List<EquipmentInfo> getEquipment(BudgetPeriod budgetPeriod) {
		List<CostInfo> cvExtraEquipment = new ArrayList<CostInfo>();
		CostInfo equipCostInfo;
		List<BudgetCategoryMap> budgetCategoryMapList = getBudgetCategoryMapList(
				new ArrayList<String>(), new ArrayList<String>());

		BudgetDecimal totalEquipFund = BudgetDecimal.ZERO;
		BudgetDecimal totalExtraEquipFund = BudgetDecimal.ZERO;
		BudgetDecimal totalEquipNonFund = BudgetDecimal.ZERO;
		BudgetDecimal totalExtraEquipNonFund = BudgetDecimal.ZERO;
		Map<String, CostInfo> costInfoMap = new HashMap<String, CostInfo>();
		List<CostInfo> costInfos= new ArrayList<CostInfo>();
		for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetCategoryMap budgetCategoryMap : budgetCategoryMapList) {
				equipCostInfo = new CostInfo();
				for (BudgetCategoryMapping budgetCategoryMapping : budgetCategoryMap
						.getBudgetCategoryMappings()) {
					if (lineItem.getBudgetCategoryCode().equals(
							budgetCategoryMapping.getBudgetCategoryCode())
							&& (budgetCategoryMapping.getTargetCategoryCode()
									.equals(TARGET_CATEGORY_CODE_EQUIPMENT_COST))
							&& (budgetCategoryMapping.getMappingName()
									.equals(S2SConstants.SPONSOR))) {
						equipCostInfo.setBudgetPeriod(budgetPeriod
								.getBudgetPeriod().intValue());
						equipCostInfo.setCategory(budgetCategoryMap
								.getDescription());
						equipCostInfo.setCategoryType(budgetCategoryMap
								.getCategoryType());						
						if(lineItem.getLineItemDescription()!=null)
						    equipCostInfo.setDescription(lineItem
	                                .getLineItemDescription());						
						else
						    equipCostInfo.setDescription(lineItem
						            .getCostElementBO().getDescription()); 
						totalEquipFund = totalEquipFund.add(lineItem
								.getLineItemCost());
						if(canBudgetLineItemCostSharingInclude(budgetPeriod.getBudget(), lineItem)){
						totalEquipNonFund = totalEquipNonFund.add(lineItem
								.getCostSharingAmount());
					    }

						StringBuilder keyBuilder = new StringBuilder();
						keyBuilder.append(budgetCategoryMap.getCategoryType());
						keyBuilder.append("-");
						keyBuilder.append(lineItem.getLineItemDescription());
						String key = keyBuilder.toString();
						if (costInfoMap.get(key) == null) {
							equipCostInfo = new CostInfo();
							equipCostInfo.setBudgetPeriod(budgetPeriod
									.getBudgetPeriod().intValue());
							equipCostInfo.setCategory(budgetCategoryMap
									.getDescription());
							equipCostInfo.setCategoryType(budgetCategoryMap
									.getCategoryType());
							if(lineItem.getLineItemDescription()!=null)
	                            equipCostInfo.setDescription(lineItem
	                                    .getLineItemDescription());	                       
	                        else 
	                            equipCostInfo.setDescription(lineItem
	                                    .getCostElementBO().getDescription());	                        
							equipCostInfo.setCost(lineItem.getLineItemCost());
							if(canBudgetLineItemCostSharingInclude(budgetPeriod.getBudget(), lineItem)){
							    equipCostInfo.setCostSharing(lineItem.getCostSharingAmount());
							}
						} else {
							equipCostInfo = costInfoMap.get(key);
							equipCostInfo.setCost(equipCostInfo.getCost().add(
									lineItem.getLineItemCost()));
							if(canBudgetLineItemCostSharingInclude(budgetPeriod.getBudget(), lineItem)){
							    equipCostInfo.setCostSharing(equipCostInfo.getCostSharing().add(lineItem.getCostSharingAmount()));
							}
						}
						costInfoMap.put(key, equipCostInfo);
						costInfos.add(equipCostInfo);
					}
				}
			}
		}
		EquipmentInfo equipmentInfo = new EquipmentInfo();

		if (costInfos.size() > 10) {
			for (int j = costInfos.size() - 1; j > 9; j--) {
				cvExtraEquipment.add(costInfos.get(j));
				CostInfo extraCostInfo = (CostInfo) costInfos.get(j);
				totalExtraEquipFund = totalExtraEquipFund.add(extraCostInfo
						.getCost());
				totalExtraEquipNonFund = totalExtraEquipNonFund
						.add(extraCostInfo.getCostSharing());
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

		List<EquipmentInfo> equipmentInfos = new ArrayList<EquipmentInfo>();
		equipmentInfos.add(equipmentInfo);
		return equipmentInfos;
	}

	/**
	 * This method gets the {@link List} of Key Persons for a given
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param budgetPeriod
	 *            given BudgetPeriod.
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @param numKeyPersons
	 *            number of key persons.
	 * @return List<List<KeyPersonInfo>> list of KeyPersonInfo list.
	 */
	protected List<List<KeyPersonInfo>> getKeyPersons(BudgetPeriod budgetPeriod,
			ProposalDevelopmentDocument pdDoc, int numKeyPersons) {
		List<KeyPersonInfo> keyPersons = new ArrayList<KeyPersonInfo>();
		KeyPersonInfo keyPerson = new KeyPersonInfo();
		ProposalPerson principalInvestigator = s2SUtilService
				.getPrincipalInvestigator(pdDoc);
		
		// Create master list of contacts
		List<ProposalPerson> propPersons = new ArrayList<ProposalPerson>();
		if (principalInvestigator != null) {
			propPersons.add(principalInvestigator);
			keyPerson.setPersonId(principalInvestigator.getPersonId());
			keyPerson.setRolodexId(principalInvestigator.getRolodexId());
			keyPerson
					.setFirstName(principalInvestigator.getFirstName() == null ? S2SConstants.VALUE_UNKNOWN
							: principalInvestigator.getFirstName());
			keyPerson
					.setLastName(principalInvestigator.getLastName() == null ? S2SConstants.VALUE_UNKNOWN
							: principalInvestigator.getLastName());
			keyPerson
					.setMiddleName(principalInvestigator.getMiddleName());
			keyPerson.setRole(PRINCIPAL_INVESTIGATOR_ROLE);
			keyPerson
					.setNonMITPersonFlag(isPersonNonMITPerson(principalInvestigator));
			keyPersons.add(keyPerson);
		}
		
		for (ProposalPerson coInvestigator : s2SUtilService.getCoInvestigators(pdDoc)) {
			propPersons.add(coInvestigator);			
			keyPerson = new KeyPersonInfo();
			keyPerson.setPersonId(coInvestigator.getPersonId());
			keyPerson.setRolodexId(coInvestigator.getRolodexId());
			keyPerson
					.setFirstName(coInvestigator.getFirstName() == null ? S2SConstants.VALUE_UNKNOWN
							: coInvestigator.getFirstName());
			keyPerson
					.setLastName(coInvestigator.getLastName() == null ? S2SConstants.VALUE_UNKNOWN
							: coInvestigator.getLastName());
			keyPerson
					.setMiddleName(coInvestigator.getMiddleName());
			keyPerson.setNonMITPersonFlag(isPersonNonMITPerson(coInvestigator));
			
			SponsorService sponsorService = KraServiceLocator
                            .getService(SponsorService.class);
            if(sponsorService.isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal())){   
               if(coInvestigator.isMultiplePi())
                   keyPerson.setRole(NID_PD_PI);                               
               else 
                   keyPerson.setRole(NID_CO_PD_PI);
            }
            else
			keyPerson.setRole(KEYPERSON_CO_PD_PI);
			keyPersons.add(keyPerson);
		}
		
		for (ProposalPerson propPerson : s2SUtilService.getKeyPersons(pdDoc)) {		
			propPersons.add(propPerson);			
			keyPerson = new KeyPersonInfo();
			keyPerson.setPersonId(propPerson.getPersonId());
			keyPerson.setRolodexId(propPerson.getRolodexId());
			keyPerson
					.setFirstName(propPerson.getFirstName() == null ? S2SConstants.VALUE_UNKNOWN
							: propPerson.getFirstName());
			keyPerson
					.setLastName(propPerson.getLastName() == null ? S2SConstants.VALUE_UNKNOWN
							: propPerson.getLastName());
			keyPerson
					.setMiddleName(propPerson.getMiddleName());
			keyPerson.setNonMITPersonFlag(isPersonNonMITPerson(propPerson));
			
			keyPerson.setRole(KEYPERSON_OTHER);
			keyPerson.setKeyPersonRole(propPerson.getProjectRole());
			
			keyPersons.add(keyPerson);
		}
		
		boolean personAlreadyAdded = false;
		Map<String, String> categoryMap = new HashMap<String, String>();
		categoryMap.put(KEY_TARGET_CATEGORY_CODE, TARGET_CATEGORY_CODE_01);
		categoryMap.put(KEY_MAPPING_NAME, S2SConstants.SPONSOR);
		List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);

		for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
		
				for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem
						.getBudgetPersonnelDetailsList()) {

					personAlreadyAdded = false;
					for (ProposalPerson coInvestigator : pdDoc
							.getDevelopmentProposal().getInvestigators()) {
						// Ensure that coInvestigators are not added again
						// because
						// they are already added above
						if (s2SUtilService.proposalPersonEqualsBudgetPerson(
								coInvestigator, budgetPersonnelDetails)) {
							personAlreadyAdded = true;
							break;
						}
					}
					budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
					if (!personAlreadyAdded) {
						if (budgetPersonnelDetails.getNonEmployeeFlag()) {
						    if (budgetPersonnelDetails.getBudgetPerson().getRolodexId() != null) {
						        budgetPersonnelDetails.getBudgetPerson().refreshReferenceObject("rolodex");
    	                        Rolodex rolodexPerson = budgetPersonnelDetails.getBudgetPerson().getRolodex();
    							keyPerson = new KeyPersonInfo();
    							keyPerson
    									.setRolodexId(rolodexPerson.getRolodexId());
    							keyPerson
    									.setFirstName(rolodexPerson.getFirstName() == null ? S2SConstants.VALUE_UNKNOWN
    											: rolodexPerson.getFirstName());
    							keyPerson
    									.setLastName(rolodexPerson.getLastName() == null ? S2SConstants.VALUE_UNKNOWN
    											: rolodexPerson.getLastName());
    							keyPerson.setMiddleName(rolodexPerson.getMiddleName());
    							keyPerson.setRole(StringUtils.isNotBlank(rolodexPerson.getTitle()) ? rolodexPerson.getTitle() : KEYPERSON_OTHER);
    							keyPerson.setNonMITPersonFlag(true);
                                
                                if(isSeniorLineItem(keyPerson.getRole(),budgetCategoryList,lineItem.getBudgetCategoryCode())){
                                    keyPersons.add(keyPerson);
                                }
						    } else if (StringUtils.isNotBlank(budgetPersonnelDetails.getBudgetPerson().getTbnId())) {
	                            Map<String, String> searchMap = new HashMap<String, String>();
	                            searchMap.put("tbnId", budgetPersonnelDetails.getBudgetPerson().getTbnId());
	                            TbnPerson tbnPerson = (TbnPerson)businessObjectService.findByPrimaryKey(TbnPerson.class, searchMap);
	                            if (tbnPerson != null) {
	                                keyPerson = new KeyPersonInfo();
	                                keyPerson.setPersonId(tbnPerson.getJobCode());
	                                String[] tbnNames = tbnPerson.getPersonName().split(" ");
	                                int nameIndex = 0;
	                                keyPerson.setPersonId(tbnPerson.getTbnId());
	                                keyPerson.setFirstName(tbnNames.length >= 1 ? tbnNames[nameIndex++] : S2SConstants.VALUE_UNKNOWN);
	                                keyPerson.setMiddleName(tbnNames.length >= 3 ? tbnNames[nameIndex++] : " ");
	                                keyPerson.setLastName(tbnNames.length >= 2 ? tbnNames[nameIndex++] : S2SConstants.VALUE_UNKNOWN);
	                                keyPerson.setRole(tbnPerson.getPersonName());
	                                keyPerson.setNonMITPersonFlag(false);

                                    if(isSeniorLineItem(keyPerson.getRole(),budgetCategoryList,lineItem.getBudgetCategoryCode())){
                                        keyPersons.add(keyPerson);
                                    }
                                        
	                            }
	                        }							
						} else {
							KcPerson kcPerson = null;
							try {
								kcPerson = kcPersonService
										.getKcPersonByPersonId(budgetPersonnelDetails
												.getPersonId());
							} catch (Exception e) {
								LOG.error("Person not found " + e);
							}
							if (kcPerson != null) {
								keyPerson = new KeyPersonInfo();
								keyPerson.setPersonId(kcPerson.getPersonId());
								keyPerson
										.setFirstName(kcPerson.getFirstName() == null ? S2SConstants.VALUE_UNKNOWN
												: kcPerson.getFirstName());
								keyPerson
										.setLastName(kcPerson.getLastName() == null ? S2SConstants.VALUE_UNKNOWN
												: kcPerson.getLastName());
								keyPerson.setMiddleName(kcPerson.getMiddleName());
								keyPerson.setNonMITPersonFlag(false);
								keyPerson.setRole(KEYPERSON_OTHER);

                                if(isSeniorLineItem(keyPerson.getRole(),budgetCategoryList,lineItem.getBudgetCategoryCode())){
                                        keyPersons.add(keyPerson);
                                }
                                
							}
						} 
					}
				}

			
		}

		List<KeyPersonInfo> allPersons = new ArrayList<KeyPersonInfo>();
		allPersons.addAll(keyPersons);
		List<KeyPersonInfo> nKeyPersons = getNKeyPersons(keyPersons, true,
				numKeyPersons);
		List<KeyPersonInfo> extraPersons = getNKeyPersons(allPersons, false,
				numKeyPersons);

		CompensationInfo compensationInfo;
		for (KeyPersonInfo keyPersonInfo : nKeyPersons) {
			keyPerson = keyPersonInfo;
			compensationInfo = getCompensation(keyPerson, budgetPeriod, pdDoc
					.getDevelopmentProposal().getProposalNumber());
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
			for (KeyPersonInfo keyPersonInfo : extraPersons) {
				keyPerson = keyPersonInfo;
				compensationInfo = getCompensation(keyPerson, budgetPeriod,
						pdDoc.getDevelopmentProposal().getProposalNumber());

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

		List<List<KeyPersonInfo>> listKeyPersons = new ArrayList<List<KeyPersonInfo>>();
		listKeyPersons.add(nKeyPersons);
		listKeyPersons.add(extraPersons);
		return listKeyPersons;
	}
        private boolean isSeniorLineItem(String keyPersonRole, List<BudgetCategoryMapping> budgetCategoryList, String budgetCategoryCode) {
            boolean isSeniorLineItem = false;
            if(keyPersonRole.equalsIgnoreCase(KEYPERSON_OTHER)){
                for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
                if (categoryMapping.getBudgetCategoryCode().equals(
                        budgetCategoryCode)) {
                    isSeniorLineItem = true;
                }}
                }else{
                    isSeniorLineItem = true;
                }
            return isSeniorLineItem;
        }
	protected boolean budgetPersonExistInProposalPersons(
			BudgetPerson budgetPerson, List<ProposalPerson> propPersons) {
		for (ProposalPerson propPerson:propPersons) {
			if (budgetPerson.getPersonId() != null) {
				if (budgetPerson.getPersonId().equals(propPerson.getPersonId())){
					return true;
				}
			} else if (budgetPerson.getRolodexId() != null) {
				if (budgetPerson.getRolodexId().equals(propPerson.getRolodexId())){
					return true;
				}				
			}
		}
		return false;
	}

	/**
	 * This method determines whether a {@link ProposalPerson} is a Non MIT
	 * person
	 * 
	 * @param proposalPerson
	 *            ProposalPerson.
	 * @return boolean true if Non MIT Person false otherwise.
	 * @see org.kuali.kra.s2s.service.S2SBudgetCalculatorService#isPersonNonMITPerson(org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
	 */
	public boolean isPersonNonMITPerson(ProposalPerson proposalPerson) {
		return proposalPerson.getPersonId() == null;
	}

	protected List<BudgetCategoryMapping> getBudgetCategoryMappings(
			Map<String, String> conditionMap) {
		Collection<BudgetCategoryMapping> budgetCategoryCollection = businessObjectService
				.findMatching(BudgetCategoryMapping.class, conditionMap);
		List<BudgetCategoryMapping> budgetCategoryMappings = new ArrayList<BudgetCategoryMapping>();
		if (budgetCategoryCollection != null) {
			budgetCategoryMappings.addAll(budgetCategoryCollection);
		}
		return budgetCategoryMappings;
	}

	/**
	 * 
	 * This method computes the CompensationInfo for given person,
	 * {@link BudgetPeriod} and Proposal Numnber
	 * 
	 * @param personId
	 *            id of the proposal person.
	 * @param budgetPeriod
	 *            given BudgetPeriod.
	 * @param proposalNumber
	 *            propsal number.
	 * 
	 * @return {@link CompensationInfo} corresponding to the
	 *         personId,budgetPeriod and proposalNumber.
	 */
	protected CompensationInfo getCompensation(KeyPersonInfo keyPerson,
			BudgetPeriod budgetPeriod, String proposalNumber) {
		CompensationInfo compensationInfo = new CompensationInfo();
		BudgetDecimal summerMonths = BudgetDecimal.ZERO;
		BudgetDecimal academicMonths = BudgetDecimal.ZERO;
		BudgetDecimal calendarMonths = BudgetDecimal.ZERO;
		BudgetDecimal totalSal = BudgetDecimal.ZERO;
		BudgetDecimal fringe = BudgetDecimal.ZERO;
		BudgetDecimal baseAmount = BudgetDecimal.ZERO;
		BudgetDecimal totalSalCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal fringeCostSharing = BudgetDecimal.ZERO;
		BudgetDecimal numberOfMonths = BudgetDecimal.ZERO;

		for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetPersonnelDetails personDetails : lineItem
					.getBudgetPersonnelDetailsList()) {
				if (s2SUtilService.keyPersonEqualsBudgetPerson(keyPerson,
						personDetails)) {
					numberOfMonths = s2SUtilService.getNumberOfMonths(personDetails
							.getStartDate(), personDetails.getEndDate());
					if (personDetails.getPeriodTypeCode().equals(
							PERIOD_TYPE_ACADEMIC_MONTHS)) {
						academicMonths = academicMonths.add(personDetails
								.getPercentEffort().multiply(numberOfMonths)
								.multiply(new BudgetDecimal(0.01)));
					} else if (personDetails.getPeriodTypeCode().equals(
							PERIOD_TYPE_SUMMER_MONTHS)) {
						summerMonths = summerMonths.add(personDetails
								.getPercentEffort().multiply(numberOfMonths)
								.multiply(new BudgetDecimal(0.01)));
					} else {
						calendarMonths = calendarMonths.add(personDetails
								.getPercentEffort().multiply(numberOfMonths)
								.multiply(new BudgetDecimal(0.01)));
					}
					totalSal = totalSal.add(personDetails.getSalaryRequested());
					if (canBudgetLineItemCostSharingInclude(budgetPeriod.getBudget(), lineItem)) {
					totalSalCostSharing = totalSalCostSharing.add(personDetails
							.getCostSharingAmount());
					}
					for (BudgetPersonnelCalculatedAmount personCalculatedAmt : personDetails
							.getBudgetPersonnelCalculatedAmounts()) {
						personCalculatedAmt.refreshReferenceObject("rateClass");
						if ((personCalculatedAmt.getRateClass()
								.getRateClassType().equals(
										RATE_CLASS_TYPE_EMPLOYEE_BENEFITS) && !personCalculatedAmt
								.getRateTypeCode().equals(
										RATE_TYPE_SUPPORT_STAFF_SALARIES))
								|| (personCalculatedAmt.getRateClass()
										.getRateClassType().equals(
												RATE_CLASS_TYPE_VACATION) && !personCalculatedAmt
										.getRateTypeCode()
										.equals(
												RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
							fringe = fringe.add(personCalculatedAmt.getCalculatedCost());
							if(canBudgetLineItemCostSharingInclude(budgetPeriod.getBudget(), lineItem)){
							    fringeCostSharing = fringeCostSharing.add(personCalculatedAmt.getCalculatedCostSharing());
							}
						}
					}
					BudgetPerson budgetPerson = personDetails.getBudgetPerson();
					if (budgetPerson != null) {
						baseAmount = budgetPerson.getCalculationBase();
						// baseAmount must be set to the first record value in
						// case
						// the execution doesnt enter the if condition below
						String apptTypeCode = budgetPerson.getAppointmentType()
								.getAppointmentTypeCode();
						if (!apptTypeCode.equals(APPOINTMENT_TYPE_SUM_EMPLOYEE)
								&& !apptTypeCode
										.equals(APPOINTMENT_TYPE_TMP_EMPLOYEE)) {
							baseAmount = budgetPerson.getCalculationBase();
						}
					}
				}

			}
		}
		compensationInfo.setAcademicMonths(academicMonths.setScale());
		compensationInfo.setCalendarMonths(calendarMonths.setScale());
		compensationInfo.setSummerMonths(summerMonths.setScale());
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
	 * This method limits the number of key persons to n, returns list of key
	 * persons, first n in case firstN is true, or all other than first n, in
	 * case of firstN being false
	 * 
	 * @param keyPersons
	 *            list of {@link KeyPersonInfo}
	 * @param firstN
	 *            value that determines whether the returned list should contain
	 *            first n persons or the rest of persons
	 * @param n
	 *            number of key persons that are considered as not extra persons
	 * @return list of {@link KeyPersonInfo}
	 */
	protected List<KeyPersonInfo> getNKeyPersons(List<KeyPersonInfo> keyPersons,
			boolean firstN, int n) {
		KeyPersonInfo keyPersonInfo, previousKeyPersonInfo;
		int size = keyPersons.size();

		for (int i = size - 1; i > 0; i--) {
			keyPersonInfo = (KeyPersonInfo) (keyPersons.get(i));
			previousKeyPersonInfo = (KeyPersonInfo) (keyPersons.get(i - 1));
			if (keyPersonInfo.getPersonId() != null
					&& previousKeyPersonInfo.getPersonId() != null
					&& keyPersonInfo.getPersonId().equals(
							previousKeyPersonInfo.getPersonId())) {
				keyPersons.remove(i);
			} else if (keyPersonInfo.getRolodexId() != null
					&& previousKeyPersonInfo.getRolodexId() != null
					&& keyPersonInfo.getRolodexId().equals(
							previousKeyPersonInfo.getRolodexId())) {
				keyPersons.remove(i);
			}
		}

		size = keyPersons.size();
		if (firstN) {
			List<KeyPersonInfo> firstNPersons = new ArrayList<KeyPersonInfo>();

			// Make sure we don't exceed the size of the list.
			if (size > n) {
				size = n;
			}
			// remove extras
			for (int i = 0; i < size; i++) {
				firstNPersons.add(keyPersons.get(i));
			}
			return firstNPersons;
		} else {
			// return extra people
			List<KeyPersonInfo> extraPersons = new ArrayList<KeyPersonInfo>();
			for (int i = n; i < size; i++) {
				extraPersons.add(keyPersons.get(i));
			}
			return extraPersons;
		}
	}

	/**
	 * This method returns the final version of BudgetDocument for a given
	 * ProposalDevelopmentDocument.
	 * 
	 * @param pdDoc
	 *            Proposal development document.
	 * @return BudgetDocument final version of budget corresponding to the
	 *         ProposalDevelopmentDocument object.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SBudgetCalculatorService#getFinalBudgetVersion(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
	 */
	public BudgetDocument getFinalBudgetVersion(
			ProposalDevelopmentDocument pdDoc) throws S2SException {
		BudgetDocument budgetDocument = null;
		BudgetVersionOverview versionOverview = pdDoc.getFinalBudgetVersion() == null ? null
				: pdDoc.getFinalBudgetVersion().getBudgetVersionOverview();
		try {
			if (versionOverview != null) {
				budgetDocument = (BudgetDocument) KRADServiceLocatorWeb
						.getDocumentService().getByDocumentHeaderId(
								versionOverview.getDocumentNumber());
			} else {
				List<BudgetDocumentVersion> budgetVersions = pdDoc
						.getBudgetDocumentVersions();
				if (budgetVersions != null && budgetVersions.size() > 0) {
					// If no final version found and if there are more than zero
					// budget versions, get the last one
					budgetDocument = (BudgetDocument) KRADServiceLocatorWeb
							.getDocumentService().getByDocumentHeaderId(
									budgetVersions.get(budgetVersions.size() - 1).getBudgetVersionOverview().getDocumentNumber());
				}
			}
		} catch (WorkflowException e) {
			LOG.error("Error while getting Budget veraion", e);
			throw new S2SException(e);
		}
		return budgetDocument;
	}

	/**
	 * 
	 * This method gets the salary requested for a given proposal person.
	 * 
	 * @param pdDoc
	 *            {@link ProposalDevelopmentDocument} from which salary needs to
	 *            be fetched
	 * @param proposalPerson
	 *            proposal person whose salary needs to be fetched
	 * 
	 * @return {@link BudgetDecimal} salary of proposal person
	 * @throws S2SException
	 */
	public BudgetDecimal getProposalPersonSalary(
			ProposalDevelopmentDocument pdDoc, ProposalPerson proposalPerson)
			throws S2SException {
		BudgetDecimal salary = BudgetDecimal.ZERO;
		BudgetDocument budgetDoc = getFinalBudgetVersion(pdDoc);
		Budget budget = budgetDoc==null?null:budgetDoc.getBudget();
		if (budget != null) {
			for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
				for (BudgetLineItem lineItem : budgetPeriod
						.getBudgetLineItems()) {
					for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem
							.getBudgetPersonnelDetailsList()) {
						if (s2SUtilService.proposalPersonEqualsBudgetPerson(proposalPerson, budgetPersonnelDetails)) {
							salary = salary.add(budgetPersonnelDetails.getSalaryRequested());
						}
					}
				}
			}
		}
		return salary;
	}

	/**
	 * Sets the s2sUtilService attribute value.
	 * 
	 * @param generatorUtilService
	 *            The s2sUtilService to set.
	 */
	public void setS2SUtilService(S2SUtilService s2SUtilService) {
		this.s2SUtilService = s2SUtilService;
	}

	/**
	 * This method is to set businessObjectService
	 * 
	 * @param businessObjectService(BusinessObjectService)
	 */
	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	/**
	 * Sets the KC Person Service.
	 * 
	 * @param kcPersonService
	 *            the kc person service
	 */
	public void setKcPersonService(KcPersonService kcPersonService) {
		this.kcPersonService = kcPersonService;
	}

	/**
	 * @param rolodexService
	 *            the rolodexService to set
	 */
	public void setRolodexService(RolodexService rolodexService) {
		this.rolodexService = rolodexService;
	}
}
