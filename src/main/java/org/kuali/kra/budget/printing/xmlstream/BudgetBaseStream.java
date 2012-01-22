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
package org.kuali.kra.budget.printing.xmlstream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noNamespace.GroupsType;
import noNamespace.ReportHeaderType;
import noNamespace.ReportType;
import noNamespace.SubReportType;
import noNamespace.ReportPageType.CalculationMethodology;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.calculator.ValidCalcType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetRateAndBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.budget.printing.util.ReportTypeVO;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class will contain all common methods that can be used across all XML
 * generator streams related to budget. All budget report XML stream
 * implementations need to extend and use the functions defined in this class.
 * 
 */
public abstract class BudgetBaseStream implements XmlStream {

	private static final String RATE_CLASS_TYPE = "rateClassType";
	private static final String DEPENDENT_RATE_CLASS_TYPE = "dependentRateClassType";
	private static final String RATE_CLASS_CODE = "rateClassCode";
	private static final String UNIT_NUMBER = "unitNumber";
	private static final String PROPOSAL_NUMBER = "proposalNumber";
	private static final String GET_METHOD_PREFIX = "get";
	private static final String RATE_TYPE_CODE = "rateTypeCode";
	private static final String CLOSE_BRACES = ")";
	private static final String OPEN_BRACES = "(";
	private static final String PERSONNEL_SUMMARY_LINE_ITEM_NAME = "Summary";
	protected static final String TOTAL_EMPLOYEE_BENEFITS = "Total Employee Benefits";
	protected static final String ALLOCATED_LAB_EXPENSE = "Allocated Lab Expense";
	protected static final String EMPLOYEE_BENEFITS_ON_ALLOCATED_ADMINISTRATIVE_SUPPORT = "Employee Benefits on Allocated Administrative Support";
	protected static final String ALLOCATED_ADMINISTRATIVE_SUPPORT = "Allocated Administrative Support";
	protected static final String OTHER_DIRECT_COSTS = "Other Direct Costs";
	protected static final String ALLOCATED_ADMIN_SUPPORT = "Allocated Admin Support";
	protected static final String PERCENTAGE = "%";
	protected static final String LAB_ALLOCATION = "Lab Allocation";
	protected static final String CATEGORY_CODE_LA_SALARY = "99";
	protected static final String RATE_TYPE_CODE_VACATION_ON_LA = "2";
	protected static final String RATE_CLASS_CODE_VACATION_ON_LA = "8";
	protected static final String RATE_TYPE_CODE_LA_SALARY = "1";
	protected static final String RATE_CLASS_CODE_LA_SALARY = "10";
	protected static final String DEFAULT_RATE_TYPE_CODE_FOR_LI = "3";
	protected static final String RATE_CLASS_CODE_EB_ON_LA = "5";
	protected static final String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
	private static final String RATE_CLASS = "rateClass";
	private static final String STRING_SEPRATOR = "-";
	private static final String EMPTY_STRING = "";
	protected List<BudgetPersonnelRateAndBase> budgetPersRateAndBases;
	protected BusinessObjectService businessObjectService = null;
	protected Budget budget;
	protected List<BudgetPersonnelRateAndBase> budgetPersRateAndBaseList = new ArrayList<BudgetPersonnelRateAndBase>();
	protected String category[] = { "budgetCategoryDescription" };
	protected String rateType[] = { "rateTypeDesc" };
	protected String rateClass[] = { "rateClassDesc" };
	protected String rateClassRateType[] = { "rateClassDesc", "rateTypeDesc" };
	protected BudgetPeriod budgetPeriod;
	protected DateTimeService dateTimeService;
	protected static final String DATE_FORMAT = "dd MMM yyyy";
	protected static final String DATE_FORMAT_MMDDYY = "MM/dd/yy";
	protected static final String BUDGET_PERIOD = "Period";
	protected static final String BUDGET_CATEGORY_PERSONNEL = "P";
	

	private static final Log LOG = LogFactory.getLog(BudgetBaseStream.class);
	

	/**
	 * This method gets ReportHeaderType from first budgetPeriod.It set all the
	 * data for RportHeader from budgetPriod and budgetParent
	 * 
	 * @param budgetParent
	 * @return reportTypeHeader
	 */
	protected ReportHeaderType getReportHeaderTypeForCumulativeReport(BudgetParent budgetParent) {
		ReportHeaderType reportHeaderType = ReportHeaderType.Factory
				.newInstance();
		if (budgetParent != null) {
		    reportHeaderType.setParentTypeName(budgetParent.getParentTypeName());
			reportHeaderType.setProposalNumber(budgetParent.getParentNumber());
		}
		if (budgetParent != null && budgetParent.getParentTitle() != null) {
			reportHeaderType.setProposalTitle( budgetParent.getParentTitle());
		}
		String principleInvestigatorName = budgetParent.getParentPIName();
		if (principleInvestigatorName != null) {
			reportHeaderType.setPIName(principleInvestigatorName);
		}
		if (budgetPeriod.getVersionNumber() != null) {
			reportHeaderType.setBudgetVersion(budget.getBudgetVersionNumber()
					.intValue());
		}
		if (budgetPeriod.getStartDate() != null) {
			reportHeaderType.setPeriodStartDate(DateFormatUtils.format(
					budgetPeriod.getStartDate(), DATE_FORMAT));
		}
		if (budgetPeriod.getEndDate() != null) {
			reportHeaderType.setPeriodEndDate(DateFormatUtils.format(
					budgetPeriod.getEndDate(), DATE_FORMAT));
		}
		if (budgetPeriod.getBudgetPeriod() != null) {
			reportHeaderType.setPeriod(budgetPeriod.getBudgetPeriod());
		}
		reportHeaderType.setCreateDate(dateTimeService.getCurrentDate()
				.toString());
		if(budget.getComments()!=null){
		    if(budget.getPrintBudgetCommentFlag()!=null && budget.getPrintBudgetCommentFlag().equals("true"))
		        reportHeaderType.setComments(budget.getComments());
		}
		
		budget.setPrintBudgetCommentFlag(null);
		 
		return reportHeaderType;
	}
	
	/**
	 * This method gets ReportType for NonPersonnel data, by setting
	 * NonPersonnel data categoryDescription, costElementDescription,
	 * calculatedCost, costSharingAmount or default values to ReportType Object
	 * 
	 * @param categoryDesc
	 * @param costElementDesc
	 * @param calculatedCost
	 * @param costSharingAmount
	 * @return ReportType reportType
	 */
	protected ReportType getReportTypeForNonPersonnel(String categoryDesc,
			String costElementDesc, BudgetDecimal calculatedCost,
			BudgetDecimal costSharingAmount) {
		ReportType reportType = ReportType.Factory.newInstance();
		if (categoryDesc != null) {
			reportType.setBudgetCategoryDescription(categoryDesc);
		}
		if (costElementDesc != null) {
			reportType.setCostElementDescription(costElementDesc);
		}
		if (calculatedCost != null) {
			reportType.setCalculatedCost(calculatedCost.doubleValue());
		} else {
			reportType.setCalculatedCost(0);
		}
		if (costSharingAmount != null) {
			reportType.setCostSharingAmount(costSharingAmount.doubleValue());
		} else {
			reportType.setCostSharingAmount(0);
		}
		return reportType;
	}

	/**
	 * This method gets ReportType of BudgetIndirectCost data, by setting
	 * IndirectCost data OnOffCampus, costSharingAmount, calculatedCost or
	 * default values to ReportType Object
	 * 
	 * @param onOffCampus
	 * @param calculatedCost
	 * @param costSharingAmount
	 * @return ReportType reportType
	 */
	protected ReportType getReportTypeForBudgetIndirectCostsForReport(
			Boolean onOffCampus, BudgetDecimal calculatedCost,
			BudgetDecimal costSharingAmount) {
		ReportType reportType = ReportType.Factory.newInstance();
		if (onOffCampus != null) {
			reportType.setOnOffCampus(onOffCampus);
		}
		if (costSharingAmount != null) {
			reportType.setCostSharingAmount(costSharingAmount.doubleValue());
		} else {
			reportType.setCostSharingAmount(0);
		}
		if (calculatedCost != null) {
			reportType.setCalculatedCost(calculatedCost.doubleValue());
		} else {
			reportType.setCalculatedCost(0);
		}
		return reportType;
	}

	/**
	 * This method gets ReportType data for LA or OH Exclusion data, by setting
	 * Exclusion data sortId, categoryDescription, calculatedCost to ReportType
	 * Object
	 * 
	 * @param sortId
	 * @param categoryDesc
	 * @param calculatedCost
	 * @return ReportType reportType
	 */
	protected ReportType getReportTypeForExclusions(int sortId,
			String categoryDesc, BudgetDecimal calculatedCost) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setSortId(sortId);
		reportType.setCostElementDescription(categoryDesc);
		if (calculatedCost != null) {
			reportType.setCalculatedCost(calculatedCost.doubleValue());
		} else {
			reportType.setCalculatedCost(0);
		}
		return reportType;
	}

	/**
	 * This method gets sum of calculatedCost from list of
	 * BudgetLineItemCalculatedAmount based on RateClassType
	 * 
	 * @param rateClassType
	 * @param budgetLineItem
	 * @return BudgetDecimal sum of calculatedCost
	 */
	protected BudgetDecimal getTotalCalculatedCostByRateClassTypeFromLineItem(
			String rateClassType, BudgetLineItem budgetLineItem) {
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		for (BudgetLineItemCalculatedAmount lineItemCalAmt : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			lineItemCalAmt.refreshReferenceObject(RATE_CLASS);
			if (lineItemCalAmt.getRateClass().getRateClassType().equals(
					rateClassType)
					&& lineItemCalAmt.getCalculatedCost() != null) {
				calculatedCost = calculatedCost.add(lineItemCalAmt
						.getCalculatedCost());
			}
		}
		return calculatedCost;
	}

	/**
	 * This method gets sum of costSharingAmount from list of
	 * BudgetLineItemCalculatedAmount based on RateClassType
	 * 
	 * @param budgetLineItem
	 * @param rateClassType
	 * @return BudgetDecimal sum of costSharingAmount
	 */
	protected BudgetDecimal getTotalCostSharingAmountByRateClassTypeFromLineItem(
			BudgetLineItem budgetLineItem, String rateClassType) {
		BudgetDecimal costSharingAmount = BudgetDecimal.ZERO;
		for (BudgetLineItemCalculatedAmount lineItemCalAmt : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			lineItemCalAmt.refreshReferenceObject(RATE_CLASS);
			if (lineItemCalAmt.getRateClass().getRateClassType().equals(
					rateClassType)
					&& lineItemCalAmt.getCalculatedCostSharing() != null) {
				costSharingAmount = costSharingAmount.add(lineItemCalAmt
						.getCalculatedCostSharing());
			}
		}
		return costSharingAmount;
	}

	/**
	 * This method gets sum of calculatedCost from list of BudgetLineItem and
	 * iterate through each budgetRateAndBase for BudgetExclusionsSortId1 based
	 * on RateClassType LA_WITH_EB_VA and check key as startDate and endDate
	 * 
	 * @return BudgetDecimal sum of calculatedCost
	 */
	protected BudgetDecimal getCalculatedCostForBudgetExclusionsSortId1() {
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			Map<String, BudgetRateAndBase> laRateBaseMap = new HashMap<String, BudgetRateAndBase>();
			for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
					.getBudgetRateAndBaseList()) {
				if (budgetRateAndBase.getCalculatedCost() != null
						&& isRateAndBaseOfRateClassTypeLAwithEBVA(budgetRateAndBase)) {
					Date startDate = budgetRateAndBase.getStartDate();
					Date endDate = budgetRateAndBase.getEndDate();
					String key = new StringBuilder(startDate.toString())
							.append(endDate.toString()).toString();
					if (laRateBaseMap.containsKey(key)) {
						continue;
					}
					calculatedCost = calculatedCost.add(budgetRateAndBase
							.getCalculatedCost());
					laRateBaseMap.put(key, budgetRateAndBase);
				}
			}
		}
		return calculatedCost;
	}

	/**
	 * This method gets sum of calculatedCost from list of BudgetLineItem and
	 * iterate through each budgetRateAndBase for BudgetLAExclusionsSortId2
	 * based on RateClassType EMPLOYEE_BENEFITS and VACATION
	 * 
	 * @return BudgetDecimal sum of calculatedCost
	 */
	protected BudgetDecimal getCalculatedCostForBudgetLAExclusionsSortId2() {
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
					.getBudgetRateAndBaseList()) {
				if (budgetRateAndBase.getCalculatedCost() != null) {
					budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
					if (isRateAndBaseOfRateClassTypeEB(budgetRateAndBase)
							|| isRateAndBaseOfRateClassTypeVacation(budgetRateAndBase)) {
						calculatedCost = calculatedCost.add(budgetRateAndBase
								.getCalculatedCost());
					}
				}
			}
		}
		return calculatedCost;
	}

	/**
	 * This method gets sum of calculatedCost from list of BudgetLineItem and
	 * iterate through each budgetRateAndBase for BudgetOHExclusionsSortId2
	 * based on RateClassCode and RateTypeCode
	 * 
	 * @return BudgetDecimal sum of calculatedCost
	 */
	protected BudgetDecimal getCalculatedCostForBudgetOHExclusionsSortId2() {
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
					.getBudgetRateAndBaseList()) {
				if (budgetRateAndBase.getCalculatedCost() != null) {
					budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
					if (isRateAndBaseEBonLA(budgetRateAndBase)
							|| isRateAndBaseVAonLA(budgetRateAndBase)) {
						calculatedCost = calculatedCost.add(budgetRateAndBase
								.getCalculatedCost());
					}
				}
			}
		}
		return calculatedCost;
	}

	/**
	 * This method gets sum of calculatedCost from list of BudgetLineItem and
	 * iterate through each BudgetLineItemCalculatedAmount for
	 * BudgetExclusionsSortId4 based on RateClassType LAB_ALLOCATION
	 * 
	 * @return BudgetDecimal sum of calculatedCost
	 */
	protected BudgetDecimal getCalculatedCostForBudgetExclusionsSortId4() {
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem
					.getBudgetLineItemCalculatedAmounts()) {
				if (budgetLineItemCalcAmount.getCalculatedCost() != null) {
					budgetLineItemCalcAmount.refreshReferenceObject(RATE_CLASS);
					if (isLineItemCalAmountOfRateClassTypeLabAllocation(budgetLineItemCalcAmount)) {
						calculatedCost = calculatedCost
								.add(budgetLineItemCalcAmount
										.getCalculatedCost());
					}
				}
			}
		}
		return calculatedCost;
	}

	/**
	 * This method sets reportType to ReportTypeList for BudgetLASalary and get
	 * sum of fringe, calculatedCost, calculatedCostSharing and salary by
	 * grouping reportType based on budgetLASalaryKey
	 * 
	 * @param reportTypeList
	 * @param reportTypeVOList
	 */
	protected void setReportTypeBudgetLASalary(List<ReportType> reportTypeList,
			List<ReportTypeVO> reportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : reportTypeVOList) {
			String budgetLASalaryKey = reportTypeVO.getCostElementDesc();
			if (reportTypeMap.containsKey(budgetLASalaryKey)) {
				continue;
			}
			Date startDate = reportTypeVO.getStartDate();
			Date endDate = reportTypeVO.getEndDate();
			BudgetDecimal fringe = BudgetDecimal.ZERO;
			BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
			BudgetDecimal calculatedCostSharing = BudgetDecimal.ZERO;
			BudgetDecimal salary = BudgetDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : reportTypeVOList) {
				String budgetLASalaryTempKey = tempReportTypeVO
						.getCostElementDesc();
				if (budgetLASalaryTempKey.equals(budgetLASalaryKey)) {
					if (startDate.after(tempReportTypeVO.getStartDate())) {
						startDate = tempReportTypeVO.getStartDate();
					}
					if (endDate.before(tempReportTypeVO.getEndDate())) {
						endDate = tempReportTypeVO.getEndDate();
					}
					fringe = fringe.add(tempReportTypeVO.getFringe());
					calculatedCost = calculatedCost.add(tempReportTypeVO
							.getCalculatedCost());
					calculatedCostSharing = calculatedCostSharing
							.add(tempReportTypeVO.getCostSharingAmount());
					salary = salary.add(tempReportTypeVO.getSalaryRequested());
				}
			}
			ReportType reportType = getReportTypeForLASalary(fringe, salary,
					calculatedCost, calculatedCostSharing, reportTypeVO,
					startDate, endDate);
			reportTypeList.add(reportType);
			reportTypeMap.put(budgetLASalaryKey, reportTypeVO);
		}
	}

	/**
	 * This method gets reportType for LASalary by setting data from
	 * reportTypeVO and passed parameters
	 * 
	 */
	private ReportType getReportTypeForLASalary(BudgetDecimal fringe,
			BudgetDecimal salary, BudgetDecimal calculatedCost,
			BudgetDecimal calculatedCostSharing, ReportTypeVO reportTypeVO,
			Date startDate, Date endDate) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setBudgetCategoryDescription(LAB_ALLOCATION);
		reportType.setPersonName(ALLOCATED_ADMIN_SUPPORT);
		reportType.setPercentEffort(100);
		reportType.setPercentCharged(100);
		reportType.setBudgetCategoryCode(99);
		reportType.setInvestigatorFlag(3);
		reportType.setStartDate(DateFormatUtils.format(startDate,
				DATE_FORMAT_MMDDYY));
		reportType.setEndDate(DateFormatUtils.format(endDate,
				DATE_FORMAT_MMDDYY));
		reportType.setCostSharingAmount(calculatedCostSharing.doubleValue());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		reportType.setFringe(fringe.doubleValue());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setSalaryRequested(salary.doubleValue());
		return reportType;
	}

	/**
	 * This method sets reportTypeVO and add it to reportTypeVOList from list of
	 * BudgetLineItem and iterate through BudgetRateAndBase for BudgetLASalary
	 * based on RateClassType OTHER
	 * 
	 * @param reportTypeList
	 */
	protected void setBudgetLASalaryForBudgetRateAndBase(
			List<ReportType> reportTypeList) {
		List<ReportTypeVO> reportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			Map<String, BudgetRateAndBase> laRateBaseMap = new HashMap<String, BudgetRateAndBase>();
			for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
					.getBudgetRateAndBaseList()) {
				if (isRateAndBaseOfRateClassTypeLAwithEBVA(budgetRateAndBase)) {
					Date startDate = budgetRateAndBase.getStartDate();
					Date endDate = budgetRateAndBase.getEndDate();
					String key = new StringBuilder(startDate.toString())
							.append(endDate.toString()).toString();
					if (laRateBaseMap.containsKey(key)) {
						continue;
					}
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetLASalaryForRateBase(
							budgetLineItem, budgetRateAndBase);
					reportTypeVOList.add(reportTypeVO);
					laRateBaseMap.put(key, budgetRateAndBase);
				}
			}
		}
		setReportTypeBudgetLASalary(reportTypeList, reportTypeVOList);
	}

	/**
	 * This method returns List of ReportTypeVO for BudgetLASalaryForRateAndBase
	 * by setting data from budgetLineItem and BudgetRateAndBase
	 * 
	 * @param budgetLineItem
	 * @param budgetRateAndBase
	 * @return ReportTypeVO
	 */
	protected ReportTypeVO getReportTypeVOForBudgetLASalaryForRateBase(
			BudgetLineItem budgetLineItem, BudgetRateAndBase budgetRateAndBase) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setCostElementDesc(budgetLineItem.getCostElementBO()
				.getDescription());
		Date startDate = budgetRateAndBase.getStartDate();
		Date endDate = budgetRateAndBase.getEndDate();
		reportTypeVO.setStartDate(startDate);
		reportTypeVO.setEndDate(endDate);
		reportTypeVO.setSalaryRequested(budgetRateAndBase.getCalculatedCost());
		reportTypeVO.setCostSharingAmount(budgetRateAndBase
				.getCalculatedCostSharing());
		reportTypeVO.setFringe(getFringeForLASalaryForRateAndBase(
				budgetLineItem, startDate, endDate));
		reportTypeVO.setCalculatedCost(getFringeCostSharingLASalaryRateAndBase(
				budgetLineItem, startDate, endDate));
		return reportTypeVO;
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	/*
	 * This method gets LiVacOnLaRateTypeCode from ValidCalcType Table based on
	 * value of rateClassType EMPLOYEE_BENEFITS and LA_WITH_EB_VA if the value
	 * is not found it return default value
	 */
	private String getLiVacOnLaRateTypeCode() {
		String liVacOnLaRateTypeCode = DEFAULT_RATE_TYPE_CODE_FOR_LI;
		Map<String, String> liVacOnLaRateTypeCodeMap = new HashMap<String, String>();
		liVacOnLaRateTypeCodeMap.put(RATE_CLASS_TYPE,
				RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
		liVacOnLaRateTypeCodeMap.put(DEPENDENT_RATE_CLASS_TYPE,
				RateClassType.LA_SALARIES.getRateClassType());
		ValidCalcType validCalcType = (ValidCalcType) businessObjectService
				.findByPrimaryKey(ValidCalcType.class, liVacOnLaRateTypeCodeMap);
		if (validCalcType != null) {
			liVacOnLaRateTypeCode = validCalcType.getRateTypeCode();
		}
		return liVacOnLaRateTypeCode;
	}

	/*
	 * This method gets LiEbOnLaRateTypeCode from ValidCalcType Table based on
	 * value of rateClassType EMPLOYEE_BENEFITS and LA_WITH_EB_VA if the value
	 * is not found it return default value
	 */
	private String getLiEbOnLaRateTypeCode() {
		String liEbOnLaRateTypeCode = DEFAULT_RATE_TYPE_CODE_FOR_LI;
		Map<String, String> liVacOnLaRateTypeCodeMap = new HashMap<String, String>();
		liVacOnLaRateTypeCodeMap.put(RATE_CLASS_TYPE, RateClassType.VACATION
				.getRateClassType());
		liVacOnLaRateTypeCodeMap.put(DEPENDENT_RATE_CLASS_TYPE,
				RateClassType.LA_SALARIES.getRateClassType());
		ValidCalcType validCalcType = (ValidCalcType) businessObjectService
				.findByPrimaryKey(ValidCalcType.class, liVacOnLaRateTypeCodeMap);
		if (validCalcType != null) {
			liEbOnLaRateTypeCode = validCalcType.getRateTypeCode();
		}
		return liEbOnLaRateTypeCode;
	}

	/**
	 * This method gets count of unitNumber from InstitutionLaRate with
	 * proposalNumber and lsOwnedByUnit. lsOwnedByUnit values comes from
	 * LookupableDevelopmentProposal based on proposalNumber
	 * 
	 * @return integer liCount
	 */
	   protected int getUnitNumber() {
	        String lsOwnedByUnit = budgetPeriod.getBudget().getBudgetParent().getIsOwnedByUnit();       
	        Map<String, String> lsOwnedByUnitMap = new HashMap<String, String>();
	        lsOwnedByUnitMap.put(UNIT_NUMBER, lsOwnedByUnit);
	        int liCount = businessObjectService.findMatching(InstituteLaRate.class,
	                lsOwnedByUnitMap).size();
	        return liCount;
	    }

	/*
	 * This method get RateTypeDescription for BudgetRateAndBase based on
	 * RateTypeCode. It return String description
	 * 
	 * it return String description
	 */
	private String getRateTypeDesc(String rateClassCode, String rateTypeCode) {
		Map<String, String> rateTypeCodeMap = new HashMap<String, String>();
		rateTypeCodeMap.put(RATE_TYPE_CODE, rateTypeCode);
		rateTypeCodeMap.put(RATE_CLASS_CODE, rateClassCode);
		RateType rateType = (RateType) businessObjectService.findByPrimaryKey(
				RateType.class, rateTypeCodeMap);
		return rateType.getDescription();
	}

	/*
	 * This method add ReportType to Group Type
	 */
	private void addReportTypeToGroupType(GroupsType groupsType,
			ReportType reportType) {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		reportTypeList.add(reportType);
		groupsType.setDetailsArray(reportTypeList.toArray(new ReportType[0]));
	}

	/**
	 * This method gets Array of GroupsType for subReportType. ReportTypeList
	 * iterate through each ReportType and add reportType to GroupType and
	 * create a GroupTypeList
	 * 
	 * @param reportTypeList
	 * @return Array of groupsType
	 */
	protected GroupsType[] getGroupsType(List<ReportType> reportTypeList) {
		GroupsType groupsType = null;
		List<GroupsType> groupTypeList = new ArrayList<GroupsType>();
		for (ReportType reportType : reportTypeList) {
			groupsType = GroupsType.Factory.newInstance();
			addReportTypeToGroupType(groupsType, reportType);
			groupTypeList.add(groupsType);
		}
		return groupTypeList.toArray(new GroupsType[0]);
	}

	/**
	 * This method gets Array of GroupsType for subReport Type. ReportTypeList
	 * iterate through each ReportType and add reportType to GroupType group by
	 * Description and create a GroupTypeList
	 * 
	 * @param reportTypeList
	 * @param groupBy
	 * @return Array of groupsType
	 */
	protected GroupsType[] getGroupsType(List<ReportType> reportTypeList,
			String groupBy[]) {
		GroupsType groupsType = null;
		String presentGroup = EMPTY_STRING;
		String lastGroup = EMPTY_STRING;
		List<ReportType> reportTypeListForGroup = null;
		List<GroupsType> groupTypeList = new ArrayList<GroupsType>();
		for (ReportType reportType : reportTypeList) {
			presentGroup = EMPTY_STRING;
			for (int count = 0; count < groupBy.length; count++) {
				presentGroup = presentGroup
						+ getFieldValue(groupBy[count], reportType);
			}
			if (!presentGroup.equals(lastGroup)) {
				groupsType = GroupsType.Factory.newInstance();
				reportTypeListForGroup = new ArrayList<ReportType>();
				Object fieldValue = getFieldValue(groupBy[0], reportType);
				if (fieldValue != null) {
					groupsType.setDescription(getFieldValue(groupBy[0],
							reportType).toString());
				}
				groupTypeList.add(groupsType);
				lastGroup = presentGroup;
			}
			reportTypeListForGroup.add(reportType);
			groupsType.setDetailsArray(reportTypeListForGroup
					.toArray(new ReportType[0]));
		}
		return groupTypeList.toArray(new GroupsType[0]);
	}

	/*
	 * This method returns the field value in the base bean for the specified
	 * field.It dynamically create the method based on fieldName
	 */
	private Object getFieldValue(String fieldName, ReportType baseBean) {
		Class dataClass = baseBean.getClass();
		Object value = null;
		try {
			StringBuilder methodName = new StringBuilder(GET_METHOD_PREFIX);
			methodName.append(
					String.valueOf((fieldName.charAt(0))).toUpperCase())
					.append(fieldName.substring(1));
			Method method = dataClass.getMethod(methodName.toString(),
					(Class[]) null);
			value = method.invoke(baseBean, (Object[]) null);
		} catch (NoSuchMethodException noSuchMethodException) {
			LOG.error("Method not Found " + noSuchMethodException);
		} catch (InvocationTargetException invocationTargetException) {
			LOG.error("Error while Invoking the Method "
					+ invocationTargetException);
		} catch (IllegalAccessException illegalAccessException) {
			LOG.error("Illegal Access Exception" + illegalAccessException);
		}
		return value;
	}

	/**
	 * This method gets subReportType for BudgetIndirectCostForReportIDC by
	 * BudgetPeriod. It get the sum of calculatedCost and CostSharingAmount
	 * based on BudgetLineItem OnOffCampusFlag and RateClassType
	 * 
	 * @return subReportType
	 */
	protected SubReportType getBudgetIndirectCostsForReport() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		BudgetDecimal calculatedCostForOn = BudgetDecimal.ZERO;
		BudgetDecimal calculatedCostForOff = BudgetDecimal.ZERO;
		BudgetDecimal costSharingAmountForOn = BudgetDecimal.ZERO;
		BudgetDecimal costSharingAmountForOff = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (budgetLineItem.getOnOffCampusFlag().booleanValue()) {
				calculatedCostForOn = calculatedCostForOn
						.add(getTotalCalculatedCostByRateClassTypeFromLineItem(
								RateClassType.OVERHEAD.getRateClassType(),
								budgetLineItem));
				costSharingAmountForOn = costSharingAmountForOn
						.add(getTotalCostSharingAmountByRateClassTypeFromLineItem(
								budgetLineItem, RateClassType.OVERHEAD
										.getRateClassType()));
			} else {
				calculatedCostForOff = calculatedCostForOff
						.add(getTotalCalculatedCostByRateClassTypeFromLineItem(
								RateClassType.OVERHEAD.getRateClassType(),
								budgetLineItem));
				costSharingAmountForOff = costSharingAmountForOff
						.add(getTotalCostSharingAmountByRateClassTypeFromLineItem(
								budgetLineItem, RateClassType.OVERHEAD
										.getRateClassType()));
			}
		}
		if (!(calculatedCostForOn.equals(BudgetDecimal.ZERO) && costSharingAmountForOn
				.equals(BudgetDecimal.ZERO))) {
			ReportType reportTypeForOn = getReportTypeForBudgetIndirectCostsForReport(
					Boolean.TRUE, calculatedCostForOn, costSharingAmountForOn);
			reportTypeList.add(reportTypeForOn);
		}
		if (!(calculatedCostForOff.equals(BudgetDecimal.ZERO) && costSharingAmountForOff
				.equals(BudgetDecimal.ZERO))) {
			ReportType reportTypeForOff = getReportTypeForBudgetIndirectCostsForReport(
					Boolean.FALSE, calculatedCostForOff,
					costSharingAmountForOff);
			reportTypeList.add(reportTypeForOff);
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	/**
	 * This method gets Calculated Methodology for a BudgetPeriod. This method
	 * gets and sets data for Exclusions and RateBase
	 * 
	 * @return calculationMethodology
	 */
	protected CalculationMethodology getCalculationMethodology() {
		CalculationMethodology calculationMethodology = CalculationMethodology.Factory
				.newInstance();
		SubReportType subReportType = SubReportType.Factory.newInstance();

		subReportType = getBudgetOHExclusions();
		calculationMethodology.setBudgetOHExclusions(subReportType);

		subReportType = getBudgetLAExclusions();
		calculationMethodology.setBudgetLAExclusions(subReportType);

		subReportType = getBudgetOHRateBaseForPeriod();
		calculationMethodology.setBudgetOHRateBaseForPeriod(subReportType);

		subReportType = getBudgetEBRateBaseForPeriod();
		calculationMethodology.setBudgetEBRateBaseForPeriod(subReportType);

		subReportType = getBudgetLARateBaseForPeriod();
		calculationMethodology.setBudgetLARateBaseForPeriod(subReportType);

		subReportType = getBudgetVacRateBaseForPeriod();
		calculationMethodology.setBudgetVacRateBaseForPeriod(subReportType);

		subReportType = getBudgetOtherRateBaseForPeriod();
		calculationMethodology.setBudgetOtherRateBaseForPeriod(subReportType);

		return calculationMethodology;
	}

	/*
	 * This method get BudgetOHExclusions for a BudgetPeriod. It first check
	 * size of BudgetProposalLARates if it's it create ReportTypeList with
	 * sortId 1,2,3,4 else it create reportTypeList for sortId 1
	 */
	private SubReportType getBudgetOHExclusions() {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();

		SubReportType subReportType = SubReportType.Factory.newInstance();
		int sortId;
		String categoryDesc = null;
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		if (budget.getBudgetLaRates().size() > 0) {
			sortId = 1;
			categoryDesc = ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId1();
			ReportType reportTypeForSortId1 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId1);
			sortId = 2;
			categoryDesc = EMPLOYEE_BENEFITS_ON_ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetOHExclusionsSortId2();
			ReportType reportTypeForSortId2 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId2);
			sortId = 3;
			setReportTypeOHExclusionForSortId(reportTypeList, sortId);
			sortId = 4;
			categoryDesc = ALLOCATED_LAB_EXPENSE;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId4();
			ReportType reportTypeForSortId4 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId4);
		} else {
			sortId = 1;
			setReportTypeOHExclusionForSortId(reportTypeList, sortId);
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	/*
	 * This method get BudgetLAExclusions for a BudgetPeriod. It first check
	 * size of BudgetProposalLARates if it's it create ReportTypeList with
	 * sortId 1,2,3,4
	 */
	private SubReportType getBudgetLAExclusions() {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		SubReportType subReportType = SubReportType.Factory.newInstance();
		int sortId;
		String categoryDesc = null;
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		if (budget.getBudgetLaRates().size() > 0) {
			sortId = 1;
			categoryDesc = ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId1();
			ReportType reportTypeForSortId1 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId1);
			sortId = 2;
			categoryDesc = TOTAL_EMPLOYEE_BENEFITS;
			calculatedCost = getCalculatedCostForBudgetLAExclusionsSortId2();
			ReportType reportTypeForSortId2 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId2);
			setReportTypeForBudgetLAExclusionsSortId3(reportTypeList);
			sortId = 4;
			categoryDesc = ALLOCATED_LAB_EXPENSE;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId4();
			ReportType reportTypeForSortId4 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId4);
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	/**
	 * This method sets ReportType from list of BudgetLineItem by checking the
	 * lineItemNumber and create ReportTypeVO by setting data to it and add to
	 * ReportTypeVOList
	 * 
	 * @param reportTypeList
	 * @param sortId
	 */
	protected void setReportTypeOHExclusionForSortId(
			List<ReportType> reportTypeList, int sortId) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		setReportTypeVOListForOHExclusionSortId(tempReportTypeVOList);
		setReportTypeListOHExclusionForSortId(reportTypeList, sortId,
				tempReportTypeVOList);
	}

	/**
	 * This method set ReportTypeVO to ReportTypeVOList after setting values for
	 * ReportType from BudgetLineItems
	 * 
	 * @param tempReportTypeVOList
	 */
	protected void setReportTypeVOListForOHExclusionSortId(
			List<ReportTypeVO> tempReportTypeVOList) {
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (!checkLineItemNumberAvailableForOHExclusion(budgetLineItem)) {
				ReportTypeVO reportTypeVO = new ReportTypeVO();
				reportTypeVO
						.setCostElementDesc(getCostElementDescription(budgetLineItem));
				reportTypeVO
						.setCalculatedCost(budgetLineItem.getLineItemCost());
				tempReportTypeVOList.add(reportTypeVO);
			}
		}
	}

	/**
	 * This method sets ReportType grouped by budgetOHExclusionKey and get the
	 * sum of calculatedCost and add to ReportTypeList
	 * 
	 * @param reportTypeList
	 * @param sortId
	 * @param tempReportTypeVOList
	 */
	protected void setReportTypeListOHExclusionForSortId(
			List<ReportType> reportTypeList, int sortId,
			List<ReportTypeVO> tempReportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String budgetOHExclusionKey = reportTypeVO.getCostElementDesc();
			if (reportTypeMap.containsKey(budgetOHExclusionKey)) {
				continue;
			}
			BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
			for (ReportTypeVO reportTypeVO1 : tempReportTypeVOList) {
				String budgetOHExclusionTempKey = reportTypeVO1
						.getCostElementDesc();
				if (budgetOHExclusionTempKey.equals(budgetOHExclusionKey)) {
					calculatedCost = calculatedCost.add(reportTypeVO1
							.getCalculatedCost());
				}
			}
			reportTypeMap.put(budgetOHExclusionKey, reportTypeVO);
			ReportType reportType = ReportType.Factory.newInstance();
			reportType.setSortId(sortId);
			reportType.setCostElementDescription(reportTypeVO
					.getCostElementDesc());
			reportType.setCalculatedCost(calculatedCost.doubleValue());
			reportTypeList.add(reportType);
		}
	}

	/**
	 * This method sets ReportType from list of BudgetLineItem by checking the
	 * lineItemNumber, budgetCategoryCode and create ReportTypeVO by setting
	 * data to it and add to ReportTypeVOList
	 * 
	 * @param reportTypeList
	 */
	protected void setReportTypeForBudgetLAExclusionsSortId3(
			List<ReportType> reportTypeList) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		setReportTypeVOListForLAExclusionSortId3(tempReportTypeVOList);
		setReportTypeList(reportTypeList, tempReportTypeVOList);
	}

	/**
	 * This method sets ReportTypeVO from budgetLineItem values and further add
	 * in ReportTypeVOList
	 * 
	 * @param tempReportTypeVOList
	 */
	protected void setReportTypeVOListForLAExclusionSortId3(
			List<ReportTypeVO> tempReportTypeVOList) {
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (!isBudgetCategoryPersonnel(budgetLineItem)
					&& !checkLineItemNumberAvailableForLAExclusion(budgetLineItem)) {
				ReportTypeVO tempReportTypeVO = new ReportTypeVO();
				tempReportTypeVO
						.setCostElementDesc(getCostElementDescription(budgetLineItem));
				tempReportTypeVO.setCalculatedCost(budgetLineItem
						.getLineItemCost());
				tempReportTypeVOList.add(tempReportTypeVO);
			}
		}
	}

	/**
	 * This method sets ReportType grouped by budgetLAExclusionsKey and get the
	 * sum of calculatedCost and add to ReportTypeList
	 * 
	 * @param reportTypeList
	 * @param tempReportTypeVOList
	 */
	protected void setReportTypeList(List<ReportType> reportTypeList,
			List<ReportTypeVO> tempReportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String budgetLAExclusionsKey = reportTypeVO.getCostElementDesc();
			if (reportTypeMap.containsKey(budgetLAExclusionsKey)) {
				continue;
			}
			BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
			for (ReportTypeVO reportTypeVO1 : tempReportTypeVOList) {
				String budgetLAExclusionTempKey = reportTypeVO1
						.getCostElementDesc();
				if (budgetLAExclusionTempKey.equals(budgetLAExclusionsKey)) {
					calculatedCost = calculatedCost.add(reportTypeVO1
							.getCalculatedCost());
				}
			}
			reportTypeMap.put(budgetLAExclusionsKey, reportTypeVO);
			ReportType reportType = ReportType.Factory.newInstance();
			reportType.setSortId(3);
			reportType.setCostElementDescription(reportTypeVO
					.getCostElementDesc());
			reportType.setCalculatedCost(calculatedCost.doubleValue());
			reportTypeList.add(reportType);
		}
	}

	/*
	 * This method check lineItemNumber available in BudgetLineItem or not based
	 * on RateClassType LAB_ALLOCATION and LA_WITH_EB_VA
	 */
	private boolean checkLineItemNumberAvailableForLAExclusion(
			BudgetLineItem budgetLineItem) {
		boolean availabe = false;
		Integer lineItemNumber = budgetLineItem.getLineItemNumber();
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			budgetLineItemCalcAmount.refreshReferenceObject(RATE_CLASS);
			if (budgetLineItemCalcAmount.getApplyRateFlag()
					&& (isLineItemCalAmountOfRateClassTypeLabAllocation(budgetLineItemCalcAmount) || isLineItemCalAmountOfRateClassTypeLAWithEBVA(budgetLineItemCalcAmount))) {
				if (budgetLineItemCalcAmount.getLineItemNumber().equals(
						lineItemNumber)) {
					availabe = true;
				}
			}
		}
		return availabe;
	}

	/*
	 * This method check lineItemNumber available in BudgetLineItem or not based
	 * on RateClassType OVERHEAD
	 */
	private boolean checkLineItemNumberAvailableForOHExclusion(
			BudgetLineItem budgetLineItem) {
		boolean availabe = false;
		Integer lineItemNumber = budgetLineItem.getLineItemNumber();
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			budgetLineItemCalcAmount.refreshReferenceObject(RATE_CLASS);
			if (budgetLineItemCalcAmount.getApplyRateFlag()
					&& isLineItemCalAmountOfRateClassTypeOverhead(budgetLineItemCalcAmount)) {
				if (budgetLineItemCalcAmount.getLineItemNumber().equals(
						lineItemNumber)) {
					availabe = true;
				}
			}
		}
		return availabe;
	}

	/**
	 * This method gets costElement Description from BudgetLineItem by checking
	 * the LineItemDescription
	 * 
	 * @param budgetLineItem
	 * @return String costElementDesc
	 */
	protected String getCostElementDescription(BudgetLineItem budgetLineItem) {
		String costElementDesc = budgetLineItem
				.getCostElementBO()
				.getDescription()
				.concat(
						budgetLineItem.getLineItemDescription() == null ? EMPTY_STRING
								: STRING_SEPRATOR.concat(budgetLineItem
										.getLineItemDescription()));
		return costElementDesc;
	}

	/*
	 * This method gets subReportType for BudgetOHRateBase by BudgetPeriod. It
	 * get reportTypeList for BudgetOHRateBase based on RateClassType OVERHEAD
	 */
	private SubReportType getBudgetOHRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = getReportTypeListForBudgetOHRateAndBase(RateClassType.OVERHEAD
				.getRateClassType());
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	/*
	 * This method gets subReportType for BudgetEBRateBase by BudgetPeriod. It
	 * get reportTypeList for BudgetOHRateBase based on RateClassType
	 * EMPLOYEE_BENEFITS
	 */
	private SubReportType getBudgetEBRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = getReportTypeListForBudgetEBRateAndBase(RateClassType.EMPLOYEE_BENEFITS
				.getRateClassType());
		subReportType.setGroupArray(getGroupsType(reportTypeList, rateType));
		return subReportType;

	}

	/**
	 * This method gets List of ReportType for BudgetEBRateAndBase from List of
	 * BudgetLineItem and iterate through BudgetPersonnelRateAndBase,
	 * BudgetRateBase.
	 * 
	 * @param rateClassType
	 * @return List of ReportType
	 */
	protected List<ReportType> getReportTypeListForBudgetEBRateAndBase(
			String rateClassType) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetEBRateAndBase(
					tempReportTypeVOList, budgetLineItem, rateClassType);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetEBRateAndBase(
						tempReportTypeVOList, budgetLineItem, rateClassType);
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		reportTypeList = new ArrayList<ReportType>(reportTypeMap.values());
		return reportTypeList;
	}

	/*
	 * This method gets subReportType for BudgetLARateBase from List of
	 * BudgetLineitem based on RateClassType LAB_ALLOCATION. It set GroupArray
	 * to SubReportType from reportTypeList
	 */
	private SubReportType getBudgetLARateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = getReportTypeListForBudgetLARateAndBase(RateClassType.LAB_ALLOCATION
				.getRateClassType());
		subReportType.setGroupArray(getGroupsType(reportTypeList,
				rateClassRateType));
		return subReportType;
	}

	/**
	 * This method gets List of ReportType for BudgetLARateAndBase from List of
	 * BudgetLineItem and iterate through BudgetPersonnelRateAndBase,
	 * BudgetRateAndBase based on RateClasstype LA_WITH_EB_VA
	 * 
	 * @param rateClassType
	 * @return List of ReportType
	 */
	protected List<ReportType> getReportTypeListForBudgetLARateAndBase(
			String rateClassType) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetLARateAndBase(
					tempReportTypeVOList, budgetLineItem, rateClassType,
					RateClassType.LA_SALARIES.getRateClassType());
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetLARateAndBase(
						tempReportTypeVOList, budgetLineItem, rateClassType,
						RateClassType.LA_SALARIES.getRateClassType());
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		reportTypeList = new ArrayList<ReportType>(reportTypeMap.values());
		return reportTypeList;
	}

	/*
	 * This method gets subReportType for BudgetVacRateBase from List of
	 * BudgetLineitem based on RateClassType VACATION. It set GroupArray to
	 * SubReportType from reportTypeList
	 */
	private SubReportType getBudgetVacRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = getReportTypeListForBudgetVACRateAndBase(RateClassType.VACATION
				.getRateClassType());
		subReportType.setGroupArray(getGroupsType(reportTypeList, rateType));
		return subReportType;
	}

	/**
	 * This method gets List of ReportType for BudgetVACRateAndBase from List of
	 * BudgetLineItem and iterate through BudgetPersonnelRateAndBase,
	 * BudgetRateAndBase based on RateClasstype
	 * 
	 * @param rateClassType
	 * @return List of ReportType
	 */
	protected List<ReportType> getReportTypeListForBudgetVACRateAndBase(
			String rateClassType) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetVacRateAndBase(
					tempReportTypeVOList, budgetLineItem, rateClassType);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetVacRateAndBase(
						tempReportTypeVOList, budgetLineItem, rateClassType);
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		reportTypeList = new ArrayList<ReportType>(reportTypeMap.values());
		return reportTypeList;
	}

	/**
	 * This method set reportTypeMap from reportTypeVOList by grouping based on
	 * reportTypeKey and get sum of salaryRequested, calculatedCost, first
	 * startDate, last endDate
	 * 
	 * @param tempReportTypeVOList
	 * @param reportTypeMap
	 */
	protected void setReportTypeMapFromReportTypeVOList(
			List<ReportTypeVO> tempReportTypeVOList,
			Map<String, ReportType> reportTypeMap) {
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String reportTypeKey = getKeyForRateBase(reportTypeVO);
			if (reportTypeMap.containsKey(reportTypeKey)) {
				continue;
			}
			Date startDate = reportTypeVO.getStartDate();
			Date endDate = reportTypeVO.getEndDate();
			BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
			BudgetDecimal salaryRequested = BudgetDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : tempReportTypeVOList) {
				String reportTypeTempKey = getKeyForRateBase(tempReportTypeVO);
				if (reportTypeTempKey.equals(reportTypeKey)) {
					salaryRequested = salaryRequested.add(tempReportTypeVO
							.getSalaryRequested());
					calculatedCost = calculatedCost.add(tempReportTypeVO
							.getCalculatedCost());
					if (startDate.after(tempReportTypeVO.getStartDate())) {
						startDate = tempReportTypeVO.getStartDate();
					}
					if (endDate.before(tempReportTypeVO.getEndDate())) {
						endDate = tempReportTypeVO.getEndDate();
					}
				}
			}
			ReportType reportType = getReportTypeForRateAndBase(startDate,
					endDate, calculatedCost, salaryRequested, reportTypeVO);
			reportTypeMap.put(reportTypeKey, reportType);
		}
	}

	/*
	 * This method gets ReportType by setting data to reportType which is passed
	 * as parameters
	 */
	private ReportType getReportTypeForRateAndBase(Date startDate,
			Date endDate, BudgetDecimal calculatedCost,
			BudgetDecimal salaryRequested, ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setSalaryRequested(salaryRequested.doubleValue());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		reportType.setStartDate(DateFormatUtils.format(startDate, DATE_FORMAT));
		reportType.setEndDate(DateFormatUtils.format(endDate, DATE_FORMAT));
		reportType.setAppliedRate(reportTypeVO.getAppliedRate().doubleValue());
		reportType.setRateClassDesc(reportTypeVO.getRateClassDesc());
		reportType.setRateTypeDesc(reportTypeVO.getRateTypeDesc());
		reportType.setOnOffCampus(reportTypeVO.getOnOffCampusFlag());
		return reportType;
	}

	/*
	 * This method gets subReportType for BudgetOtherRateBase from List of
	 * BudgetLineItems based on rateClassType OTHER and setGroupArray
	 * to subreportType by getting groupType through ReportTypeList
	 */
	private SubReportType getBudgetOtherRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList;
		reportTypeList = getReportTypeListForBudgetOtherRateAndBase(RateClassType.OTHER
				.getRateClassType());
		subReportType.setGroupArray(getGroupsType(reportTypeList,
				rateClassRateType));
		return subReportType;

	}

	/**
	 * This method gets List of ReportType for BudgetOtherRateAndBase from List
	 * of BudgetLineItem and iterate through BudgetPersonnelRateAndBase,
	 * BudgetRateAndBase based on RateClasstype
	 * 
	 * @param rateClassType
	 * @return List of ReportType
	 */
	protected List<ReportType> getReportTypeListForBudgetOtherRateAndBase(
			String rateClassType) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetOtherRateAndBase(
					tempReportTypeVOList, budgetLineItem, rateClassType);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetOtherRateAndBase(
						tempReportTypeVOList, budgetLineItem, rateClassType);
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		reportTypeList = new ArrayList<ReportType>(reportTypeMap.values());
		return reportTypeList;
	}

	/**
	 * This method gets List of ReportType for BudgetOHRateAndBase from List of
	 * BudgetLineItem and iterate through BudgetPersonnelRateAndBase,
	 * BudgetRateAndBase based on RateClasstype and BudgetCategoryTypeCode
	 * 
	 * @param rateClassType
	 * @return List of ReportType
	 */
	protected List<ReportType> getReportTypeListForBudgetOHRateAndBase(
			String rateClassType) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetOHRateAndBase(
					tempReportTypeVOList, budgetLineItem, rateClassType);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetOHRateAndBase(
						tempReportTypeVOList, budgetLineItem, rateClassType);
			}
		}
		setReportTypeMapForBudgetOHRateAndBase(tempReportTypeVOList,
				reportTypeMap);
		reportTypeList = new ArrayList<ReportType>(reportTypeMap.values());
		return reportTypeList;
	}

	/**
	 * This method set reportTypeMap for BudgetOHRateAndBase by grouping based
	 * on budgetOHRateBaseKey and get sum of salaryRequested, calculatedCost,
	 * first startDate, last endDate
	 * 
	 * @param tempReportTypeVOList
	 * @param reportTypeMap
	 */
	protected void setReportTypeMapForBudgetOHRateAndBase(
			List<ReportTypeVO> tempReportTypeVOList,
			Map<String, ReportType> reportTypeMap) {
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String budgetOHRateBaseKey = getKeyForBudgetOHRateBase(reportTypeVO);
			if (reportTypeMap.containsKey(budgetOHRateBaseKey)) {
				continue;
			}
			Date startDate = reportTypeVO.getStartDate();
			Date endDate = reportTypeVO.getEndDate();
			BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
			BudgetDecimal salaryRequested = BudgetDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : tempReportTypeVOList) {
				String budgetOHRateBaseTempKey = getKeyForBudgetOHRateBase(tempReportTypeVO);
				if (budgetOHRateBaseTempKey.equals(budgetOHRateBaseKey)) {
					salaryRequested = salaryRequested.add(tempReportTypeVO
							.getSalaryRequested() == null ? BudgetDecimal.ZERO
							: tempReportTypeVO.getSalaryRequested());
					calculatedCost = calculatedCost.add(tempReportTypeVO
							.getCalculatedCost());
					if (startDate.after(tempReportTypeVO.getStartDate())) {
						startDate = tempReportTypeVO.getStartDate();
					}
					if (endDate.before(tempReportTypeVO.getEndDate())) {
						endDate = tempReportTypeVO.getEndDate();
					}
				}
			}
			ReportType reportType = getReportTypeForBudgetOHRateAndBase(
					startDate, endDate, calculatedCost, salaryRequested,
					reportTypeVO);
			reportTypeMap.put(budgetOHRateBaseKey, reportType);
		}
	}

	/*
	 * This method gets ReportType for BudgetOHRateAndbase by setting data to
	 * reportType which is passed as parameters
	 */
	private ReportType getReportTypeForBudgetOHRateAndBase(Date startDate,
			Date endDate, BudgetDecimal calculatedCost,
			BudgetDecimal salaryRequested, ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setSalaryRequested(salaryRequested.doubleValue());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		reportType.setStartDate(DateFormatUtils.format(startDate, DATE_FORMAT));
		reportType.setEndDate(DateFormatUtils.format(endDate, DATE_FORMAT));
		reportType.setAppliedRate(reportTypeVO.getAppliedRate().doubleValue());
		reportType.setRateClassDesc(reportTypeVO.getRateClassDesc());
		reportType.setOnOffCampus(reportTypeVO.getOnOffCampusFlag());
		return reportType;
	}

	/*
	 * This method gets key for vacation and Other RateBase for making groups
	 * based on key
	 */
	private String getKeyForRateBase(ReportTypeVO reportTypeVO) {
		StringBuilder key = new StringBuilder();
		key.append(reportTypeVO.getRateClassDesc()).append(
				reportTypeVO.getRateTypeDesc()).append(
				reportTypeVO.getOnOffCampusFlag().toString()).append(
				reportTypeVO.getAppliedRate().toString());
		return key.toString();
	}

	/*
	 * This method gets key for OHRateBase for making groups of BudgetOHRateBase
	 * based on key
	 */
	private String getKeyForBudgetOHRateBase(ReportTypeVO reportTypeVO) {
		StringBuilder key = new StringBuilder();
		key.append(reportTypeVO.getOnOffCampusFlag().toString()).append(
				reportTypeVO.getAppliedRate().toString()).append(
				reportTypeVO.getRateClassDesc());
		return key.toString();
	}

	/**
	 * This method sets BudgetRateAndBaseListForOtherrateAndBase from List of
	 * BudgetRateAndBase based on RateClassType
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetRateAndBaseListForBudgetOtherRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetRateAndBase> laRateBaseMap = new HashMap<String, BudgetRateAndBase>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
				.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassType().equals(
					rateClassType)) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (laRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				laRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	/**
	 * This method sets BudgetRateAndBaseList For BudgetVacRateAndBase from list
	 * of BudgetRateAndBase based on RateClassType and LiVacOnLaRateTypeCode
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetRateAndBaseListForBudgetVacRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetRateAndBase> vacBudgetRateBaseMap = new HashMap<String, BudgetRateAndBase>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
				.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassType().equals(
					rateClassType)
					&& !budgetRateAndBase.getRateClass().getRateClassType()
							.equals(getLiVacOnLaRateTypeCode())) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (vacBudgetRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				vacBudgetRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	/**
	 * This method sets BudgetRateAndBaseList For BudgetLARateAndBase from list
	 * of BudgetRateAndBase based on RateClassTypes
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType1
	 * @param rateClassType2
	 */
	protected void setBudgetRateAndBaseListForBudgetLARateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType1, String rateClassType2) {
		Map<String, BudgetRateAndBase> laRateBaseMap = new HashMap<String, BudgetRateAndBase>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
				.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassType().equals(
					rateClassType1)
					|| budgetRateAndBase.getRateClass().getRateClassType()
							.equals(rateClassType2)) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (laRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				laRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	/*
	 * This method return key for BudgetRateAndBase by appending
	 * startDate,endDate,rateTypeCode and RateClassCode
	 */
	private String getBudgetRateAndBaseKey(BudgetRateAndBase budgetRateAndBase) {
		String key = new StringBuilder(budgetRateAndBase.getStartDate()
				.toString()).append(budgetRateAndBase.getEndDate().toString())
				.append(budgetRateAndBase.getRateTypeCode()).append(
						budgetRateAndBase.getRateClassCode()).toString();
		return key;
	}

	/**
	 * This method sets BudgetRateAndBaseList For BudgetEBRateAndBase from list
	 * of BudgetRateAndBase based on rateClassType
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetRateAndBaseListForBudgetEBRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetRateAndBase> ebBudgetRateBaseMap = new HashMap<String, BudgetRateAndBase>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
				.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassType().equals(
					rateClassType)
					&& !budgetRateAndBase.getRateClass().getRateClassType()
							.equals(getLiEbOnLaRateTypeCode())) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (ebBudgetRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				ebBudgetRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	/**
	 * This method sets BudgetRateAndBaseList For BudgetOHRateAndBase from list
	 * of BudgetRateAndBase based on RateClassType
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetRateAndBaseListForBudgetOHRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetRateAndBase> ohBudgetRateBaseMap = new HashMap<String, BudgetRateAndBase>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
				.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassType().equals(
					rateClassType)) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (ohBudgetRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseListForBudgetOHRateBase(budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				ohBudgetRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	/*
	 * This method gets reportTypeVO from BudgetRateAndBase. It sets data from
	 * BudgetRateAndBase to reportTypeVO
	 */
	private ReportTypeVO getBudgetRateAndBaseList(
			BudgetRateAndBase budgetRateAndBase) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setRateClassDesc(budgetRateAndBase.getRateClass()
				.getDescription());
		reportTypeVO.setRateTypeDesc(getRateTypeDesc(budgetRateAndBase
				.getRateClassCode(), budgetRateAndBase.getRateTypeCode()));
		reportTypeVO.setStartDate(budgetRateAndBase.getStartDate());
		reportTypeVO.setEndDate(budgetRateAndBase.getEndDate());
		reportTypeVO.setAppliedRate(budgetRateAndBase.getAppliedRate());
		reportTypeVO.setSalaryRequested(budgetRateAndBase.getBaseCost());
		reportTypeVO.setCalculatedCost(budgetRateAndBase.getCalculatedCost());
		reportTypeVO.setOnOffCampusFlag(budgetRateAndBase.getOnOffCampusFlag());
		return reportTypeVO;

	}

	/*
	 * This method gets reportTypeVO from BudgetRateAndBase. It sets data from
	 * BudgetRateAndBase to reportTypeVO for BudgetOHRateBase
	 */
	private ReportTypeVO getBudgetRateAndBaseListForBudgetOHRateBase(
			BudgetRateAndBase budgetRateAndBase) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setRateClassDesc(budgetRateAndBase.getRateClass()
				.getDescription());
		reportTypeVO.setStartDate(budgetRateAndBase.getStartDate());
		reportTypeVO.setEndDate(budgetRateAndBase.getEndDate());
		reportTypeVO.setAppliedRate(budgetRateAndBase.getAppliedRate());
		reportTypeVO.setSalaryRequested(budgetRateAndBase.getBaseCost());
		reportTypeVO.setCalculatedCost(budgetRateAndBase.getCalculatedCost());
		reportTypeVO.setOnOffCampusFlag(budgetRateAndBase.getOnOffCampusFlag());
		return reportTypeVO;
	}

	/**
	 * This method sets BudgetPersonnelRateAndBase List from List of
	 * BudgetPersonnelDetails and iterate each BudgetPersonnelRateAndBase based
	 * on RateClassType for BudgetOtherRateAndBase
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetPersRateAndBaseListForBudgetOtherRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetPersonnelRateAndBase> otherBudgetPersRateBaseMap = new HashMap<String, BudgetPersonnelRateAndBase>();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
				.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails
					.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null
						&& rateClassType.equals(budgetPersRateAndBase
								.getRateClass().getRateClassType())) {
					String budgetPersRateBaseKey = getBudgetPersRateAndBaseKey(budgetPersRateAndBase);
					if (otherBudgetPersRateBaseMap
							.containsKey(budgetPersRateBaseKey)) {
						continue;
					}
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(
							budgetPersRateAndBase.getRateClassCode(),
							budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
					otherBudgetPersRateBaseMap.put(budgetPersRateBaseKey,
							budgetPersRateAndBase);
				}
			}
		}
	}

	/**
	 * This method sets BudgetPersonnelRateAndBase List from List of
	 * BudgetPersonnelDetails based on budgetCategoryCode P, and iterate each
	 * BudgetPersonnelRateAndBase based on RateClasstype for
	 * BudgetVacRateAndBase
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetPersRateAndBaseListForBudgetVacRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetPersonnelRateAndBase> vacBudgetPersRateBaseMap = new HashMap<String, BudgetPersonnelRateAndBase>();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
				.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails
					.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null
						&& budgetPersRateAndBase.getRateClass()
								.getRateClassType() != null
						&& budgetPersRateAndBase.getRateClass()
								.getRateClassType().equals(rateClassType)) {
					String budgetPersRateBaseKey = getBudgetPersRateAndBaseKey(budgetPersRateAndBase);
					if (vacBudgetPersRateBaseMap
							.containsKey(budgetPersRateBaseKey)) {
						continue;
					}
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(
							budgetPersRateAndBase.getRateClassCode(),
							budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
					vacBudgetPersRateBaseMap.put(budgetPersRateBaseKey,
							budgetPersRateAndBase);
				}
			}
		}
	}

	/**
	 * This method sets BudgetPersonnelRateAndBase List from List of
	 * BudgetPersonnelDetails and iterate each BudgetPersonnelRateAndBase based
	 * on RateClassTypes for BudgetLARateAndBase
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType1
	 * @param rateClassType2
	 */
	protected void setBudgetPersRateAndBaseListForBudgetLARateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType1, String rateClassType2) {
		Map<String, BudgetPersonnelRateAndBase> laRateBaseMap = new HashMap<String, BudgetPersonnelRateAndBase>();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
				.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails
					.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null
						&& (rateClassType1.equals(budgetPersRateAndBase
								.getRateClass().getRateClassType()) || rateClassType2
								.equals(budgetPersRateAndBase.getRateClass()
										.getRateClassType()))) {
					String budgetPersRateBaseKey = getBudgetPersRateAndBaseKey(budgetPersRateAndBase);
					if (laRateBaseMap.containsKey(budgetPersRateBaseKey)) {
						continue;
					}
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(
							budgetPersRateAndBase.getRateClassCode(),
							budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
					laRateBaseMap.put(budgetPersRateBaseKey,
							budgetPersRateAndBase);
				}
			}
		}
	}

	/**
	 * This method sets BudgetPersonnelRateAndBase List from List of
	 * BudgetPersonnelDetails based on budgetCategoryCode P, and iterate each
	 * BudgetPersonnelRateAndBase based on RateClasstype for BudgetEBRateAndBase
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetPersRateAndBaseListForBudgetEBRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetPersonnelRateAndBase> ebBudgetPersRateBaseMap = new HashMap<String, BudgetPersonnelRateAndBase>();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
				.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails
					.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null
						&& budgetPersRateAndBase.getRateClass()
								.getRateClassType() != null
						&& budgetPersRateAndBase.getRateClass()
								.getRateClassType().equals(rateClassType)) {
					String budgetPersRateBaseKey = getBudgetPersRateAndBaseKey(budgetPersRateAndBase);
					if (ebBudgetPersRateBaseMap
							.containsKey(budgetPersRateBaseKey)) {
						continue;
					}
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(
							budgetPersRateAndBase.getRateClassCode(),
							budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
					ebBudgetPersRateBaseMap.put(budgetPersRateBaseKey,
							budgetPersRateAndBase);
				}
			}
		}
	}

	/**
	 * This method sets BudgetPersonnelRateAndBase List from List of
	 * BudgetPersonnelDetails and iterate each BudgetPersonnelRateAndBase based
	 * on RateClassTypes for BudgetOHRateAndBase
	 * 
	 * @param reportTypeVOList
	 * @param budgetLineItem
	 * @param rateClassType
	 */
	protected void setBudgetPersRateAndBaseListForBudgetOHRateAndBase(
			List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem,
			String rateClassType) {
		Map<String, BudgetPersonnelRateAndBase> ohBudgetPersRateBaseMap = new HashMap<String, BudgetPersonnelRateAndBase>();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
				.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails
					.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null
						&& budgetPersRateAndBase.getRateClass()
								.getRateClassType() != null
						&& budgetPersRateAndBase.getRateClass()
								.getRateClassType().equals(rateClassType)) {
					String budgetPersRateBaseKey = getBudgetPersRateAndBaseKey(budgetPersRateAndBase);
					if (ohBudgetPersRateBaseMap
							.containsKey(budgetPersRateBaseKey)) {
						continue;
					}
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetPersRateAndBase);
					reportTypeVOList.add(reportTypeVO);
					ohBudgetPersRateBaseMap.put(budgetPersRateBaseKey,
							budgetPersRateAndBase);
				}
			}
		}
	}

	/*
	 * This method return key for BudgetPersonnelRateAndBase by appending
	 * startDate,endDate,rateTypeCode and RateClassCode
	 */
	private String getBudgetPersRateAndBaseKey(
			BudgetPersonnelRateAndBase budgetPersRateAndBase) {
		String key = new StringBuilder(budgetPersRateAndBase.getStartDate()
				.toString()).append(
				budgetPersRateAndBase.getEndDate().toString()).append(
				budgetPersRateAndBase.getRateTypeCode()).append(
				budgetPersRateAndBase.getRateClassCode()).toString();
		return key;
	}

	/*
	 * This method gets reportTypeVO from BudgetPersonnelRateAndBase. It sets
	 * data from BudgetPersonnelRateAndBase to reportTypeVO for
	 * BudgetPersonnelRateAndBase
	 */
	private ReportTypeVO getReportTypeVOForBudgetPersonnelRateAndBase(
			BudgetPersonnelRateAndBase budgetPersRateAndBase) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setRateClassDesc(budgetPersRateAndBase.getRateClass()
				.getDescription());
		reportTypeVO.setStartDate(budgetPersRateAndBase.getStartDate());
		reportTypeVO.setEndDate(budgetPersRateAndBase.getEndDate());
		reportTypeVO.setAppliedRate(budgetPersRateAndBase.getAppliedRate());
		reportTypeVO.setSalaryRequested(budgetPersRateAndBase
				.getSalaryRequested());// TODO Need to change this with
		// BaseCost budgetPersRateAndBase.getBaseCost()
		reportTypeVO.setCalculatedCost(budgetPersRateAndBase
				.getCalculatedCost());
		reportTypeVO.setOnOffCampusFlag(budgetPersRateAndBase
				.getOnOffCampusFlag());
		return reportTypeVO;
	}

	/**
	 * This method gets BudgetCategory Description for SalarySummary and based
	 * on BudgetPersonnelRateAndBase rateTypeCode and rateClassCode
	 * 
	 * @param budgetPersDetails
	 * @param budgetPersRateAndBase
	 * @return String category
	 */
	protected String getBudgetCategoryDescForSalarySummary(
	        BudgetLineItem budgetLineItem,
			BudgetLineItemBase budgetDetails,
			AbstractBudgetRateAndBase budgetRateAndBase) {
	    budgetDetails.refreshReferenceObject("costElementBO");
	    budgetLineItem.refreshReferenceObject("budgetCategory");
		String category = null;
		if (budgetRateAndBase != null && isRateAndBaseLASalary(budgetRateAndBase)) {
			category = LAB_ALLOCATION;
		} else {
		    //default to using the user settable budget category
		    if (budgetLineItem.getBudgetCategory() != null) {
		        category = budgetLineItem.getBudgetCategory().getDescription();
		    } else if (budgetDetails.getCostElementBO() != null && budgetDetails.getCostElementBO().getBudgetCategory() != null) {
		        category = budgetDetails.getCostElementBO().getBudgetCategory().getDescription();
		    }
		}
		return category;
	}

	/**
	 * This method gets fringe costSharing for BudgetSalarySummary based on
	 * rateClassType EMPLOYEE_BENEFITS and VACATION from
	 * BudgetPersonnelRateAndBase
	 * 
	 * @param budgetPersRateAndBase
	 * @return BudgetDecimal fringeCostSharing
	 */
	protected BudgetDecimal getFringeForBudgetSalarySummaryFromPersonnelRateAndBase(
			AbstractBudgetRateAndBase budgetPersRateAndBase) {
		BudgetDecimal fringeCostSharing = BudgetDecimal.ZERO;
		if (isRateAndBaseOfRateClassTypeEB(budgetPersRateAndBase)
				|| isRateAndBaseOfRateClassTypeVacation(budgetPersRateAndBase)) {
			fringeCostSharing = budgetPersRateAndBase.getCalculatedCost();
		}
		return fringeCostSharing;
	}

	/*
	 * This method gets fringeCostSharing for LASalaryForRateAndBase based on
	 * StartDate, EndDate, RateClassCode and RateTypeCode from List of
	 * BudgetRateAndBase
	 */
	private BudgetDecimal getFringeForLASalaryForRateAndBase(
			BudgetLineItem budgetLineItem, Date startDate, Date endDate) {
		BudgetDecimal fringe = BudgetDecimal.ZERO;
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
				.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getStartDate().equals(startDate)
					&& budgetRateAndBase.getEndDate().equals(endDate)
					&& budgetRateAndBase.getCalculatedCost() != null) {
				if (isRateAndBaseEBonLA(budgetRateAndBase)
						|| isRateAndBaseVAonLA(budgetRateAndBase)) {
					fringe = fringe.add(budgetRateAndBase.getCalculatedCost());
				}
			}
		}
		return fringe;
	}

	/*
	 * This method gets fringeCostSharing for LASalaryRateAndBase based on
	 * startDate, endDate, RateClassCode and RateTypeCode from BudgetRateAndBase
	 */
	private BudgetDecimal getFringeCostSharingLASalaryRateAndBase(
			BudgetLineItem budgetLineItem, Date startDate, Date endDate) {
		BudgetDecimal fringeCostSharing = BudgetDecimal.ZERO;
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem
				.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getStartDate().equals(startDate)
					&& budgetRateAndBase.getEndDate().equals(endDate)
					&& budgetRateAndBase.getCalculatedCostSharing() != null) {
				if (isRateAndBaseEBonLA(budgetRateAndBase)
						|| isRateAndBaseVAonLA(budgetRateAndBase)) {
					fringeCostSharing = fringeCostSharing.add(budgetRateAndBase
							.getCalculatedCostSharing());
				}
			}
		}
		return fringeCostSharing;
	}

	/**
	 * This method gets Investigator Flag from list of ProposalPerson by
	 * checking personID from BudgetPersonnelRateAndBase
	 * 
	 * @param budgetPersRateAndBase
	 * @return Integer
	 */	
	protected Integer getInvestigatorFlag(BudgetPersonnelDetails budgetPersonDetails) {
	        Integer flag = 3;
	        String personId = budgetPersonDetails.getPersonId();
	        flag = this.budget.getBudgetParent().getParentInvestigatorFlag(personId, flag);
	
	        return flag;
	    }

	/**
	 * This method gets vacationRate based on RateClassType VACATION from
	 * BudgetPersonnelRateAndBase
	 * 
	 * @param budgetPersRateAndBase
	 * @return BudgetDecimal vactationRate
	 */
	protected BudgetDecimal getVacationAppliedRateForPersonnel(
			AbstractBudgetRateAndBase budgetPersRateAndBase) {
		BudgetDecimal appliedRate = BudgetDecimal.ZERO;
		if (isRateAndBaseOfRateClassTypeVacation(budgetPersRateAndBase)) {
			appliedRate = budgetPersRateAndBase.getAppliedRate();
		}
		return appliedRate;
	}

	/**
	 * This method gets EmployeeBenefitRate based on RateClassType
	 * EMPLOYEE_BENEFITS from BudgetPersonnelRateAndBase
	 * 
	 * @param budgetPersRateAndBase
	 * @return BudgetDecimal empBenefitRate
	 */
	protected BudgetDecimal getEmpBenefitAppliedRateForPersonnel(
			AbstractBudgetRateAndBase budgetPersRateAndBase) {
		BudgetDecimal appliedRate = BudgetDecimal.ZERO;
		if (isRateAndBaseOfRateClassTypeEB(budgetPersRateAndBase)) {
			appliedRate = budgetPersRateAndBase.getAppliedRate();
		}
		return appliedRate;
	}

	/**
	 * This method gets key for BudgetSalarySummary for make group based on key
	 * 
	 * @param reportTypeVO
	 * @return String key
	 */
	protected String getKeyForBudgetSalarySummary(ReportTypeVO reportTypeVO) {
		StringBuilder key = new StringBuilder();
		key.append(reportTypeVO.getStartDate().toString()).append(
				reportTypeVO.getEndDate().toString()).append(
				reportTypeVO.getPersonName()).append(
				reportTypeVO.getInvestigatorFlag().toString()).append(
				reportTypeVO.getBudgetCategoryCode()).append(
				reportTypeVO.getBudgetCategoryDesc()).append(
				reportTypeVO.getSalaryRequested());
		return key.toString();
	}

	/**
	 * This method gets personName for from BudgetPersonnelDetails and based on
	 * RateClassCode and RateTypeCode construct name by appending quantity else
	 * return BudgetPerson name
	 * 
	 * @param budgetPerson
	 * 
	 * @param budgetPersRateAndBase
	 * @param quantity
	 * @return String personName
	 */
	protected String getPersonNameFromBudgetPersonByRateAndBase(
			BudgetPerson budgetPerson,
			BudgetPersonnelRateAndBase budgetPersRateAndBase, Integer quantity) {
		String personName = EMPTY_STRING;
		if (budgetPersRateAndBase != null && isRateAndBaseLASalary(budgetPersRateAndBase)) {
			personName = ALLOCATED_ADMIN_SUPPORT;
		} else if (budgetPerson != null) {
			if (budgetPerson.getPersonId() == null && budgetPerson.getRolodexId() == null) {
				// Budget Person is TBA
				StringBuilder pNmae = new StringBuilder();
				personName = pNmae.append(budgetPerson.getPersonName()).append(OPEN_BRACES).append(
						quantity.toString()).append(CLOSE_BRACES).toString();
			} else {
				personName = budgetPerson.getPersonName();
			}
		}
		return personName;
	}

	/**
	 * This method gets percentEffort for from BudgetPersonnelDetails or
	 * BudgetPersonnelRateAndBase based on RateClassCode and RateTypeCode
	 * 
	 * @param budgetLineItem
	 * @param budgetPersDetails
	 * @param budgetPersRateAndBase
	 * @return BudgetDecimal percentEffort
	 */
	protected BudgetDecimal getPercentEffortForBudgetPersonnelRateBase(
			BudgetLineItem budgetLineItem,
			BudgetPersonnelDetails budgetPersDetails,
			BudgetPersonnelRateAndBase budgetPersRateAndBase) {
		BudgetDecimal percentEffort = BudgetDecimal.ZERO;
		Integer personNumber = budgetPersDetails.getPersonNumber();
		if (budgetPersRateAndBase != null && isRateAndBaseLASalary(budgetPersRateAndBase)) {
			percentEffort = new BudgetDecimal(100);
		} else {
	        //why is this needed? Can't we just use the personnel details we already have??
			for (BudgetPersonnelDetails budgetPersDetail : budgetLineItem
					.getBudgetPersonnelDetailsList()) {
				if (budgetPersDetail.getPersonNumber().equals(personNumber)
						&& budgetPersDetails.getPercentEffort() != null) {
					percentEffort = percentEffort.add(budgetPersDetail
							.getPercentEffort());
				}
			}
		}
		return percentEffort;
	}

	/**
	 * This method gets percentageCharged from BudgetPersonnelRateAndBase or
	 * BudgetPersonnelDetails based on RateClassCode and RateTypeCode
	 * 
	 * @param budgetLineItem
	 * @param budgetPersDetails
	 * @param budgetPersRateAndBase
	 * @return BudgetDecimal percentCharged
	 */
	protected BudgetDecimal getPercentChargedForBudgetPersonnelRateBase(
			BudgetLineItem budgetLineItem,
			BudgetPersonnelDetails budgetPersDetails,
			BudgetPersonnelRateAndBase budgetPersRateAndBase) {
		BudgetDecimal percentCharged = BudgetDecimal.ZERO;
		Integer personNumber = budgetPersDetails.getPersonNumber();
		if (budgetPersRateAndBase != null && isRateAndBaseLASalary(budgetPersRateAndBase)) {
			percentCharged = new BudgetDecimal(100);
		} else {
		    //why is this needed? Can't we just use the personnel details we already have??
			for (BudgetPersonnelDetails budgetPersDetail : budgetLineItem
					.getBudgetPersonnelDetailsList()) {
				if (budgetPersDetail.getPersonNumber().equals(personNumber)
						&& budgetPersDetails.getPercentEffort() != null) {
					percentCharged = percentCharged.add(budgetPersDetail
							.getPercentCharged());
				}
			}
		}
		return percentCharged;
	}

	/**
	 * This method gets BudgetCategoryCode for BudgetSalarySummary from
	 * BudgetPersonnelDetails based on RateClassCode and RateTypeCode
	 * 
	 * @param budgetPersDetails
	 * @return String categoryCode
	 */
	protected String getBudgetCategoryCodeFroBudgetSalarySummary(
			AbstractBudgetRateAndBase budgetPersRateAndBase, BudgetLineItemBase budgetPersonnelDetails) {
        String categoryCode = null;
		if (budgetPersRateAndBase != null && isRateAndBaseLASalary(budgetPersRateAndBase)) {
			categoryCode = CATEGORY_CODE_LA_SALARY;
		} else if(budgetPersonnelDetails.getCostElementBO()!=null && budgetPersonnelDetails.getCostElementBO().getBudgetCategory()!=null){
			categoryCode = budgetPersonnelDetails.getCostElementBO().getBudgetCategory().getBudgetCategoryCode();
		}
		return categoryCode;
	}

	/**
	 * This method gets true value if budgetCatgeoryCode is Personnel else false
	 * 
	 * @param budgetDetails
	 * @return boolean
	 */
	protected boolean isBudgetCategoryPersonnel(BudgetLineItemBase budgetDetails) {
		budgetDetails.refreshNonUpdateableReferences();
		if (budgetDetails.getBudgetCategory() != null
				&& BUDGET_CATEGORY_PERSONNEL.equals(budgetDetails
						.getBudgetCategory().getBudgetCategoryTypeCode())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method gets true if rateClassCode is 10 and rateTypeCode is 1 else
	 * false
	 * 
	 * @param rateAndBase
	 * @return boolean
	 */
	protected boolean isRateAndBaseLASalary(
			AbstractBudgetRateAndBase rateAndBase) {
		rateAndBase.refreshNonUpdateableReferences();
		return rateAndBase.getRateClass().getRateClassType().equals(RateClassType.LA_SALARIES.getRateClassType());
	}

	/**
	 * This method gets true if rateClassCode is 5 and rateTypeCode is 3 else
	 * false
	 * 
	 * @param rateAndBase
	 * @return boolean
	 */
	protected boolean isRateAndBaseEBonLA(AbstractBudgetRateAndBase rateAndBase) {
	    ValidCalcType ebOnLaValidCalcType = getDependentValidRateClassTypeForLA(RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
		return ebOnLaValidCalcType!=null && rateAndBase.getRateClassCode().equals(ebOnLaValidCalcType.getRateClassCode())
				&& rateAndBase.getRateTypeCode().equals(ebOnLaValidCalcType.getRateTypeCode());
	}

    private ValidCalcType getDependentValidRateClassTypeForLA(String rateClassType) {
	    Map<String,String> param = new HashMap<String,String>();
	    param.put("rateClassType", rateClassType);
	    param.put("dependentRateClassType", RateClassType.LA_SALARIES.getRateClassType());
	    List<ValidCalcType> result = (List)getBusinessObjectService().findMatching(ValidCalcType.class, param);
        return result.isEmpty()?null:result.get(0);
    }

    /**
	 * This method gets true if rateClassCode is 8 and rateTypeCode is 2 else
	 * false
	 * 
	 * @param rateAndBase
	 * @return boolean
	 */
	protected boolean isRateAndBaseVAonLA(AbstractBudgetRateAndBase rateAndBase) {
	    ValidCalcType vacationOnLaValidCalcType = getDependentValidRateClassTypeForLA(RateClassType.VACATION.getRateClassType());
		return vacationOnLaValidCalcType!=null && 
		        rateAndBase.getRateClassCode().equals(vacationOnLaValidCalcType.getRateClassCode())
				    && rateAndBase.getRateTypeCode().equals(vacationOnLaValidCalcType.getRateTypeCode());
	}

    /**
	 * This method gets true if rateClassType is Y else false from
	 * BudgetLineItemCalculatedAmount
	 * 
	 * @param lineItemCalcAmount
	 * @return boolean
	 */
	protected boolean isLineItemCalAmountOfRateClassTypeLAWithEBVA(
			BudgetLineItemCalculatedAmount lineItemCalcAmount) {
		return lineItemCalcAmount.getRateClass().getRateClassType().equals(
				RateClassType.LA_SALARIES.getRateClassType());
	}

	/**
	 * This method gets true if rateClassType is L else false from
	 * BudgetLineItemCalculatedAmount
	 * 
	 * @param lineItemCalcAmount
	 * @return boolean
	 */
	protected boolean isLineItemCalAmountOfRateClassTypeLabAllocation(
			BudgetLineItemCalculatedAmount lineItemCalcAmount) {
		return lineItemCalcAmount.getRateClass().getRateClassType().equals(
				RateClassType.LAB_ALLOCATION.getRateClassType());
	}

	/**
	 * This method gets true if rateClassType is O else false from
	 * BudgetLineItemCalculatedAmount
	 * 
	 * @param lineItemCalcAmount
	 * @return boolean
	 */
	protected boolean isLineItemCalAmountOfRateClassTypeOverhead(
			BudgetLineItemCalculatedAmount lineItemCalcAmount) {
		return lineItemCalcAmount.getRateClass().getRateClassType().equals(
				RateClassType.OVERHEAD.getRateClassType());
	}

	/**
	 * This method gets true if rateClassType is Y else false from RateAndBase
	 * 
	 * @param rateAndBase
	 * @return boolean
	 */
	protected boolean isRateAndBaseOfRateClassTypeLAwithEBVA(
			AbstractBudgetRateAndBase rateAndBase) {
		boolean status = false;
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null) {
			return rateAndBase.getRateClass().getRateClassType().equals(
					RateClassType.LA_SALARIES.getRateClassType());
		}
		return status;
	}

	/**
	 * This method gets true if rateClassType is E else false from RateAndBase
	 * 
	 * @param rateAndBase
	 * @return boolean
	 */
	protected boolean isRateAndBaseOfRateClassTypeEB(
			AbstractBudgetRateAndBase rateAndBase) {
		boolean status = false;
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null) {
			return rateAndBase.getRateClass().getRateClassType().equals(
					RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
		}
		return status;
	}

	/**
	 * This method gets true if rateClassType is O else false from RateAndBase
	 * 
	 * @param rateAndBase
	 * @return boolean
	 */
	protected boolean isRateAndBaseOfRateClassTypeOverhead(
			AbstractBudgetRateAndBase rateAndBase) {
		boolean status = false;
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null) {
			return rateAndBase.getRateClass().getRateClassType().equals(
					RateClassType.OVERHEAD.getRateClassType());
		}
		return status;
	}

	/**
	 * This method gets true if rateClassType is V else false from RateAndBase
	 * 
	 * @param rateAndBase
	 * @return boolean
	 */
	protected boolean isRateAndBaseOfRateClassTypeVacation(
			AbstractBudgetRateAndBase rateAndBase) {
		boolean status = false;
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null) {
			return rateAndBase.getRateClass().getRateClassType().equals(
					RateClassType.VACATION.getRateClassType());
		}
		return status;
	}
	
    /*
     * This method sets reportType for BudgetSalarySummary from list of
     * BudgetPersonnelDetails based on RateClassCode and RateTypeCode which
     * iterate for each BudgetLineItem
     */
    protected List<ReportTypeVO> getReportTypeVOList(BudgetPeriod budgetPeriod) {
        List<ReportTypeVO> reportTypeVOList = new ArrayList<ReportTypeVO>();
        for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            if (StringUtils.equals(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(), BUDGET_CATEGORY_PERSONNEL)) {
                if (!budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
                    for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
                            .getBudgetPersonnelDetailsList()) {
                        addReportTypeVO(reportTypeVOList, budgetLineItem, 
                                budgetPersDetails, getRatesApplicableToVOList(budgetPersDetails.getBudgetPersonnelRateAndBaseList()));
                    }
                } else {
                    addReportTypeVO(reportTypeVOList, budgetLineItem, 
                            budgetLineItem, getRatesApplicableToVOList(budgetLineItem.getBudgetRateAndBaseList()));
                }
            }
        }
        return reportTypeVOList;
    }
    
    private void addReportTypeVO(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem, 
            BudgetLineItemBase budgetDetails, List<? extends AbstractBudgetRateAndBase> rates) {
        List<AbstractBudgetRateAndBase> fringeRates = getVAAndEBRates(rates);
        if (fringeRates != null && !fringeRates.isEmpty()) {
            for (AbstractBudgetRateAndBase rate : fringeRates) {
                ReportTypeVO reportTypeVO = 
                    getReportTypeVO(budgetLineItem, budgetDetails, rate);
                reportTypeVOList.add(reportTypeVO);
            }
        } else {
            ReportTypeVO reportTypeVO = 
                getReportTypeVO(budgetLineItem, budgetDetails);
            reportTypeVOList.add(reportTypeVO);
        }       
    }
    
    private List<AbstractBudgetRateAndBase> getVAAndEBRates(List<? extends AbstractBudgetRateAndBase> rates) {
        List<AbstractBudgetRateAndBase> result = new ArrayList<AbstractBudgetRateAndBase>();
        for (AbstractBudgetRateAndBase rate : rates) {
            if (!(isRateAndBaseEBonLA(rate) || isRateAndBaseVAonLA(rate) || isRateAndBaseLASalary(rate))
                    && (isRateAndBaseOfRateClassTypeEB(rate) || isRateAndBaseOfRateClassTypeVacation(rate))) {
                result.add(rate);
            }
        }
        return result;
    }
    
    /**
     * Override this method in child streams when you need to filter for different rates than the default of VA and EB rates.
     * @param rates
     * @return
     */
    protected List<AbstractBudgetRateAndBase> getRatesApplicableToVOList(List<? extends AbstractBudgetRateAndBase> rates) {
        return getVAAndEBRates(rates);
    }

    /*
     * This method gets reportTypeVO for BudgetSalarySummary by setting data to
     * reportTypeVO from budgetLineItem, budgetPersDetails and
     * budgetPersRateAndBase
     */
    private ReportTypeVO getReportTypeVO(BudgetLineItem budgetLineItem, BudgetLineItemBase budgetDetails, AbstractBudgetRateAndBase rate) {
        ReportTypeVO reportTypeVO = new ReportTypeVO();
        budgetDetails.refreshNonUpdateableReferences();
        reportTypeVO.setStartDate(rate.getStartDate());
        reportTypeVO.setEndDate(rate.getEndDate());
        reportTypeVO.setBudgetCategoryDesc(getBudgetCategoryDescForSalarySummary(budgetLineItem, budgetDetails, rate));
        if (budgetDetails instanceof BudgetPersonnelDetails) {
            BudgetPersonnelDetails budgetPersDetails = (BudgetPersonnelDetails) budgetDetails;
            BudgetPersonnelRateAndBase budgetPersRateAndBase = (BudgetPersonnelRateAndBase) rate;
            reportTypeVO.setPersonName(getPersonNameFromBudgetPersonByRateAndBase(
                    budgetPersDetails.getBudgetPerson(), budgetPersRateAndBase,
                    budgetLineItem.getQuantity()));
            reportTypeVO.setPercentEffort(getPercentEffortForBudgetPersonnelRateBase(
                            budgetLineItem, budgetPersDetails, budgetPersRateAndBase));
            reportTypeVO.setPercentCharged(getPercentChargedForBudgetPersonnelRateBase(
                            budgetLineItem, budgetPersDetails, budgetPersRateAndBase));
            reportTypeVO.setInvestigatorFlag(getInvestigatorFlag(budgetPersDetails));
            reportTypeVO.setSalaryRequested(budgetPersRateAndBase.getSalaryRequested());              
        } else {
            BudgetRateAndBase budgetRate = (BudgetRateAndBase) rate;
            //summary personnel line item
            reportTypeVO.setPersonName(PERSONNEL_SUMMARY_LINE_ITEM_NAME);  
            //summary items can't be investigators
            reportTypeVO.setInvestigatorFlag(3);
            reportTypeVO.setSalaryRequested(budgetRate.getBaseCost());             
        }
        if (this.isRateAndBaseOfRateClassTypeVacation(rate)) {
            reportTypeVO.setVacationRate(rate.getAppliedRate());
        } else {
            reportTypeVO.setVacationRate(BudgetDecimal.ZERO);
        }
        if (this.isRateAndBaseOfRateClassTypeEB(rate)) {
            reportTypeVO.setEmployeeBenefitRate(rate.getAppliedRate());
        } else {
            reportTypeVO.setEmployeeBenefitRate(BudgetDecimal.ZERO);
        }
        reportTypeVO.setCostSharingAmount(rate.getBaseCostSharing());
        reportTypeVO.setCalculatedCost(rate.getCalculatedCostSharing());
        reportTypeVO.setFringe(rate.getCalculatedCost());
        reportTypeVO.setCostElementDesc(budgetDetails.getCostElementBO().getDescription());

        reportTypeVO.setBudgetCategoryCode(getBudgetCategoryCodeFroBudgetSalarySummary(
                        rate, budgetDetails));

        return reportTypeVO;

    }
    
    /**
     * 
     * Get a ReportTypeVO for a budget person with no rates associated.
     * @param budgetLineItem
     * @param budgetDetails
     * @return
     */
    private ReportTypeVO getReportTypeVO(
            BudgetLineItem budgetLineItem,
            BudgetLineItemBase budgetDetails) {
        ReportTypeVO reportTypeVO = new ReportTypeVO();
        budgetDetails.refreshNonUpdateableReferences();
        reportTypeVO.setStartDate(budgetDetails.getStartDate());
        reportTypeVO.setEndDate(budgetDetails.getEndDate());
        reportTypeVO.setBudgetCategoryDesc(getBudgetCategoryDescForSalarySummary(budgetLineItem, budgetDetails, null));
        if (budgetDetails instanceof BudgetPersonnelDetails) {
            BudgetPersonnelDetails budgetPersDetails = (BudgetPersonnelDetails) budgetDetails;
            reportTypeVO.setPersonName(getPersonNameFromBudgetPersonByRateAndBase(
                    budgetPersDetails.getBudgetPerson(), null,
                    budgetLineItem.getQuantity()));
            reportTypeVO.setPercentEffort(getPercentEffortForBudgetPersonnelRateBase(
                            budgetLineItem, budgetPersDetails,
                            null));
            reportTypeVO.setPercentCharged(getPercentChargedForBudgetPersonnelRateBase(
                            budgetLineItem, budgetPersDetails,
                            null));
            reportTypeVO
                    .setInvestigatorFlag(getInvestigatorFlag(budgetPersDetails));
            reportTypeVO.setSalaryRequested(budgetPersDetails.getSalaryRequested());              
        } else {
            //summary personnel line item
            reportTypeVO.setPersonName(PERSONNEL_SUMMARY_LINE_ITEM_NAME);  
            //summary items can't be investigators
            reportTypeVO
                .setInvestigatorFlag(3);
            reportTypeVO.setSalaryRequested(budgetLineItem.getLineItemCost());             
        }
        reportTypeVO.setVacationRate(BudgetDecimal.ZERO);
        reportTypeVO.setEmployeeBenefitRate(BudgetDecimal.ZERO);
        reportTypeVO.setCostSharingAmount(budgetDetails.getCostSharingAmount());
        reportTypeVO.setCalculatedCost(budgetDetails.getCostSharingAmount());
        reportTypeVO.setFringe(BudgetDecimal.ZERO);
        reportTypeVO.setCostElementDesc(budgetDetails.getCostElementBO().getDescription());

        reportTypeVO.setBudgetCategoryCode(getBudgetCategoryCodeFroBudgetSalarySummary(
                        null, budgetDetails));

        return reportTypeVO;
    }	

	/**
	 * @return the dateTimeService
	 */
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	/**
	 * @param dateTimeService
	 *            the dateTimeService to set
	 */
	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}
}
