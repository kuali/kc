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
package org.kuali.coeus.common.budget.impl.print;

import org.kuali.coeus.common.budget.framework.nonpersonnel.*;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.kra.printing.schema.GroupsType;
import org.kuali.kra.printing.schema.ReportHeaderType;
import org.kuali.kra.printing.schema.ReportPageType.CalculationMethodology;
import org.kuali.kra.printing.schema.ReportType;
import org.kuali.kra.printing.schema.SubReportType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.rate.InstituteLaRate;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.calculator.ValidCalcType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRateAndBase;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.*;

public abstract class BudgetBaseStream implements XmlStream {

    private static final Log LOG = LogFactory.getLog(BudgetBaseStream.class);
    public static final String RATE_CLASS_TYPE = "rateClassType";
	private static final String DEPENDENT_RATE_CLASS_TYPE = "dependentRateClassType";
	private static final String RATE_CLASS_CODE = "rateClassCode";
	private static final String UNIT_NUMBER = "unitNumber";
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
	protected static final String DEFAULT_RATE_TYPE_CODE_FOR_LI = "3";
	private static final String RATE_CLASS = "rateClass";
	private static final String STRING_SEPRATOR = "-";
	private static final String EMPTY_STRING = "";

    protected static final String DATE_FORMAT = "dd MMM yyyy";
    protected static final String DATE_FORMAT_MMDDYY = "MM/dd/yy";
    protected static final String BUDGET_PERIOD = "Period";
    protected static final String BUDGET_CATEGORY_PERSONNEL = "P";
    public static final String COST_ELEMENT_BO = "costElementBO";
    public static final String BUDGET_CATEGORY = "budgetCategory";

    protected Budget budget;
	protected String category[] = { "budgetCategoryDescription" };
	protected String rateType[] = { "rateTypeDesc" };
	protected String rateClassRateType[] = { "rateClassDesc", "rateTypeDesc" };
    protected BudgetPeriod budgetPeriod;

    @Autowired
    @Qualifier("businessObjectService")
    protected BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("dateTimeService")
	protected DateTimeService dateTimeService;

	@Autowired
	@Qualifier("dataObjectService")
	protected DataObjectService dataObjectService;

	protected ReportHeaderType getReportHeaderTypeForCumulativeReport(BudgetParent budgetParent) {
		ReportHeaderType reportHeaderType = ReportHeaderType.Factory.newInstance();
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
			reportHeaderType.setBudgetVersion(budget.getBudgetVersionNumber().intValue());
		}
		if (budgetPeriod.getStartDate() != null) {
			reportHeaderType.setPeriodStartDate(DateFormatUtils.format(budgetPeriod.getStartDate(), DATE_FORMAT));
		}
		if (budgetPeriod.getEndDate() != null) {
			reportHeaderType.setPeriodEndDate(DateFormatUtils.format(budgetPeriod.getEndDate(), DATE_FORMAT));
		}
		if (budgetPeriod.getBudgetPeriod() != null) {
			reportHeaderType.setPeriod(budgetPeriod.getBudgetPeriod());
		}
		reportHeaderType.setCreateDate(dateTimeService.getCurrentDate().toString());
		if(budget.getComments()!=null){
		    if(budget.getPrintBudgetCommentFlag()!=null && budget.getPrintBudgetCommentFlag().equals("true"))
		        reportHeaderType.setComments(budget.getComments());
		}
		
		budget.setPrintBudgetCommentFlag(null);
		 
		return reportHeaderType;
	}

	protected ReportType getReportTypeForNonPersonnel(String categoryDesc, String costElementDesc, ScaleTwoDecimal calculatedCost, ScaleTwoDecimal costSharingAmount) {
        ReportType reportType = getReportType();
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

	protected ReportType getReportTypeForBudgetIndirectCostsForReport(Boolean onOffCampus, ScaleTwoDecimal calculatedCost, ScaleTwoDecimal costSharingAmount) {
        ReportType reportType = getReportType();
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

	protected ReportType getReportTypeForExclusions(int sortId, String categoryDesc, ScaleTwoDecimal calculatedCost) {
        ReportType reportType = getReportType();
        reportType.setSortId(sortId);
		reportType.setCostElementDescription(categoryDesc);
		if (calculatedCost != null) {
			reportType.setCalculatedCost(calculatedCost.doubleValue());
		} else {
			reportType.setCalculatedCost(0);
		}
		return reportType;
	}

	protected ScaleTwoDecimal getTotalCalculatedCostByRateClassTypeFromLineItem(String rateClassType, BudgetLineItem budgetLineItem) {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetLineItemCalculatedAmount lineItemCalAmt : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
			lineItemCalAmt.refreshReferenceObject(RATE_CLASS);
			if (lineItemCalAmt.getRateClass().getRateClassTypeCode().equals(rateClassType) && lineItemCalAmt.getCalculatedCost() != null) {
				calculatedCost = calculatedCost.add(lineItemCalAmt.getCalculatedCost());
			}
		}
		return calculatedCost;
	}

	protected ScaleTwoDecimal getTotalCostSharingAmountByRateClassTypeFromLineItem(BudgetLineItem budgetLineItem, String rateClassType) {
		ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;
		for (BudgetLineItemCalculatedAmount lineItemCalAmt : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
			lineItemCalAmt.refreshReferenceObject(RATE_CLASS);
			if (lineItemCalAmt.getRateClass().getRateClassTypeCode().equals(rateClassType) && lineItemCalAmt.getCalculatedCostSharing() != null) {
				costSharingAmount = costSharingAmount.add(lineItemCalAmt.getCalculatedCostSharing());
			}
		}
		return costSharingAmount;
	}

	protected ScaleTwoDecimal getCalculatedCostForBudgetExclusionsSortId1() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
				if (budgetRateAndBase.getCalculatedCost() != null && isRateAndBaseOfRateClassTypeLAwithEBVA(budgetRateAndBase)) {
					calculatedCost = calculatedCost.add(budgetRateAndBase.getCalculatedCost());
				}
			}
		}
		return calculatedCost;
	}

	protected ScaleTwoDecimal getCalculatedCostForBudgetLAExclusionsSortId2() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
				if (budgetRateAndBase.getCalculatedCost() != null) {
					budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
					if (isRateAndBaseOfRateClassTypeEB(budgetRateAndBase) || isRateAndBaseOfRateClassTypeVacation(budgetRateAndBase)) {
						calculatedCost = calculatedCost.add(budgetRateAndBase.getCalculatedCost());
					}
				}
			}
		}
		return calculatedCost;
	}

	protected ScaleTwoDecimal getCalculatedCostForBudgetOHExclusionsSortId2() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
				if (budgetRateAndBase.getCalculatedCost() != null) {
					budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
					if (isRateAndBaseEBonLA(budgetRateAndBase) || isRateAndBaseVAonLA(budgetRateAndBase)) {
						calculatedCost = calculatedCost.add(budgetRateAndBase.getCalculatedCost());
					}
				}
			}
		}
		return calculatedCost;
	}

	protected ScaleTwoDecimal getCalculatedCostForBudgetExclusionsSortId4() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
				if (budgetLineItemCalcAmount.getCalculatedCost() != null) {
					budgetLineItemCalcAmount.refreshReferenceObject(RATE_CLASS);
					if (isLineItemCalAmountOfRateClassTypeLabAllocation(budgetLineItemCalcAmount)) {
						calculatedCost = calculatedCost.add(budgetLineItemCalcAmount.getCalculatedCost());
					}
				}
			}
		}
		return calculatedCost;
	}

	protected void setReportTypeBudgetLASalary(List<ReportType> reportTypeList, List<ReportTypeVO> reportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : reportTypeVOList) {
			String budgetLASalaryKey = reportTypeVO.getCostElementDesc();
			if (reportTypeMap.containsKey(budgetLASalaryKey)) {
				continue;
			}
			Date startDate = reportTypeVO.getStartDate();
			Date endDate = reportTypeVO.getEndDate();
			ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal calculatedCostSharing = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal salary = ScaleTwoDecimal.ZERO;
            HashSet<Long> lineItemFringe = new HashSet<>();
			for (ReportTypeVO tempReportTypeVO : reportTypeVOList) {
				String budgetLASalaryTempKey = tempReportTypeVO.getCostElementDesc();
				if (budgetLASalaryTempKey.equals(budgetLASalaryKey)) {
					if (startDate.after(tempReportTypeVO.getStartDate())) {
						startDate = tempReportTypeVO.getStartDate();
					}
					if (endDate.before(tempReportTypeVO.getEndDate())) {
						endDate = tempReportTypeVO.getEndDate();
					}
                    if (tempReportTypeVO.getBudgetLineItemId() == null || !lineItemFringe.contains(tempReportTypeVO.getBudgetLineItemId())) {
                        fringe = fringe.add(tempReportTypeVO.getFringe());
                    }
                    lineItemFringe.add(tempReportTypeVO.getBudgetLineItemId());
					calculatedCost = calculatedCost.add(tempReportTypeVO.getCalculatedCost());
					calculatedCostSharing = calculatedCostSharing.add(tempReportTypeVO.getCostSharingAmount());
					salary = salary.add(tempReportTypeVO.getSalaryRequested());
                }
			}
			ReportType reportType = getReportTypeForLASalary(fringe, salary, calculatedCost, calculatedCostSharing, reportTypeVO, startDate, endDate);
			reportTypeList.add(reportType);
			reportTypeMap.put(budgetLASalaryKey, reportTypeVO);
		}
	}

	private ReportType getReportTypeForLASalary(ScaleTwoDecimal fringe, ScaleTwoDecimal salary, ScaleTwoDecimal calculatedCost, ScaleTwoDecimal calculatedCostSharing, ReportTypeVO reportTypeVO, Date startDate, Date endDate) {
        ReportType reportType = getReportType();
        reportType.setBudgetCategoryDescription(LAB_ALLOCATION);
		reportType.setPersonName(ALLOCATED_ADMIN_SUPPORT);
		reportType.setPercentEffort(100);
		reportType.setPercentCharged(100);
		reportType.setBudgetCategoryCode(99);
		reportType.setInvestigatorFlag(3);
		reportType.setStartDate(DateFormatUtils.format(startDate, DATE_FORMAT_MMDDYY));
		reportType.setEndDate(DateFormatUtils.format(endDate, DATE_FORMAT_MMDDYY));
		reportType.setCostSharingAmount(calculatedCostSharing.doubleValue());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		reportType.setFringe(fringe.doubleValue());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setSalaryRequested(salary.doubleValue());
		return reportType;
	}

	protected void setBudgetLASalaryForBudgetRateAndBase (List<ReportType> reportTypeList) {
		List<ReportTypeVO> reportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
                if (isRateAndBaseOfRateClassTypeLAwithEBVA(budgetRateAndBase)) {
                    ReportTypeVO reportTypeVO = getReportTypeVOForBudgetLASalaryForRateBase(budgetLineItem, budgetRateAndBase);
					reportTypeVOList.add(reportTypeVO);
                }
			}
        }
		reportTypeVOList.sort(Comparator.comparing(ReportTypeVO::getBudgetCategoryCode));
		setReportTypeBudgetLASalary(reportTypeList, reportTypeVOList);
	}

    protected ReportTypeVO getReportTypeVOForBudgetLASalaryForRateBase(BudgetLineItem budgetLineItem, BudgetRateAndBase budgetRateAndBase) {
        ReportTypeVO reportTypeVO = new ReportTypeVO();
        reportTypeVO.setCostElementDesc(budgetLineItem.getCostElementBO().getDescription());
        Date startDate = budgetRateAndBase.getStartDate();
        Date endDate = budgetRateAndBase.getEndDate();
        reportTypeVO.setStartDate(startDate);
        reportTypeVO.setEndDate(endDate);
        reportTypeVO.setBudgetCategoryDesc(getBudgetCategoryDescForSalarySummary(budgetLineItem, budgetLineItem, budgetRateAndBase));
		reportTypeVO.setBudgetCategoryCode(getBudgetCategoryCodeFroBudgetSalarySummary(budgetRateAndBase, budgetLineItem));
        reportTypeVO.setSalaryRequested(budgetRateAndBase.getCalculatedCost());
        reportTypeVO.setFringe(getFringeForLASalaryForRateAndBase(budgetLineItem, startDate, endDate));
        reportTypeVO.setCostSharingAmount(budgetRateAndBase.getCalculatedCostSharing());
        reportTypeVO.setCalculatedCost(getFringeCostSharingLASalaryRateAndBase(budgetLineItem, startDate, endDate));
        reportTypeVO.setBudgetLineItemId(budgetLineItem.getBudgetLineItemId());
        return reportTypeVO;
    }

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	protected String getLiVacOnLaRateTypeCode() {
		String liVacOnLaRateTypeCode = DEFAULT_RATE_TYPE_CODE_FOR_LI;
		Map<String, String> liVacOnLaRateTypeCodeMap = new HashMap<>();
		liVacOnLaRateTypeCodeMap.put(RATE_CLASS_TYPE, RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
		liVacOnLaRateTypeCodeMap.put(DEPENDENT_RATE_CLASS_TYPE, RateClassType.LA_SALARIES.getRateClassType());
		ValidCalcType validCalcType = (ValidCalcType) businessObjectService.findByPrimaryKey(ValidCalcType.class, liVacOnLaRateTypeCodeMap);
		if (validCalcType != null) {
			liVacOnLaRateTypeCode = validCalcType.getRateTypeCode();
		}
		return liVacOnLaRateTypeCode;
	}

	private String getLiEbOnLaRateTypeCode() {
		String liEbOnLaRateTypeCode = DEFAULT_RATE_TYPE_CODE_FOR_LI;
		Map<String, String> liVacOnLaRateTypeCodeMap = new HashMap<>();
		liVacOnLaRateTypeCodeMap.put(RATE_CLASS_TYPE, RateClassType.VACATION.getRateClassType());
		liVacOnLaRateTypeCodeMap.put(DEPENDENT_RATE_CLASS_TYPE, RateClassType.LA_SALARIES.getRateClassType());
		ValidCalcType validCalcType = (ValidCalcType) businessObjectService.findByPrimaryKey(ValidCalcType.class, liVacOnLaRateTypeCodeMap);
		if (validCalcType != null) {
			liEbOnLaRateTypeCode = validCalcType.getRateTypeCode();
		}
		return liEbOnLaRateTypeCode;
	}

    protected int getUnitNumber() {
	    String lsOwnedByUnit = budgetPeriod.getBudget().getBudgetParent().getOwnedByUnitNumber();
        Map<String, String> lsOwnedByUnitMap = new HashMap<String, String>();
        lsOwnedByUnitMap.put(UNIT_NUMBER, lsOwnedByUnit);
        int liCount = businessObjectService.findMatching(InstituteLaRate.class, lsOwnedByUnitMap).size();
        return liCount;
    }

	protected String getRateTypeDesc(String rateClassCode, String rateTypeCode) {
		Map<String, String> rateTypeCodeMap = new HashMap<>();
		rateTypeCodeMap.put(RATE_TYPE_CODE, rateTypeCode);
		rateTypeCodeMap.put(RATE_CLASS_CODE, rateClassCode);
		RateType rateType = (RateType) businessObjectService.findByPrimaryKey(RateType.class, rateTypeCodeMap);
		return rateType.getDescription();
	}

	private void addReportTypeToGroupType(GroupsType groupsType, ReportType reportType) {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
        reportTypeList.add(reportType);
        groupsType.setDetailsArray(reportTypeList.toArray(new ReportType[0]));
	}

	protected GroupsType[] getGroupsType(List<ReportType> reportTypeList) {
		GroupsType groupsType = null;
		List<GroupsType> groupTypeList = new ArrayList<>();
		for (ReportType reportType : reportTypeList) {
			groupsType = GroupsType.Factory.newInstance();
			addReportTypeToGroupType(groupsType, reportType);
			groupTypeList.add(groupsType);
		}
		return groupTypeList.toArray(new GroupsType[0]);
	}

	protected GroupsType[] getGroupsType(List<ReportType> reportTypeList, String groupBy[]) {
		GroupsType groupsType = null;
		String presentGroup;
		String lastGroup = EMPTY_STRING;
		List<ReportType> reportTypeListForGroup = null;
		List<GroupsType> groupTypeList = new ArrayList<GroupsType>();
		for (ReportType reportType : reportTypeList) {
			presentGroup = EMPTY_STRING;
			for (int count = 0; count < groupBy.length; count++) {
				presentGroup = presentGroup + getFieldValue(groupBy[count], reportType);
			}
			if (!presentGroup.equals(lastGroup)) {
				groupsType = GroupsType.Factory.newInstance();
				reportTypeListForGroup = new ArrayList<>();
				Object fieldValue = getFieldValue(groupBy[0], reportType);
				if (fieldValue != null) {
					groupsType.setDescription(getFieldValue(groupBy[0], reportType).toString());
				}
				groupTypeList.add(groupsType);
                lastGroup = presentGroup;
			}
            reportTypeListForGroup.add(reportType);
            groupsType.setDetailsArray(reportTypeListForGroup.toArray(new ReportType[0]));
		}
		return groupTypeList.toArray(new GroupsType[0]);
	}

	private Object getFieldValue(String fieldName, ReportType baseBean) {
		Class dataClass = baseBean.getClass();
		Object value = null;
		try {
			StringBuilder methodName = new StringBuilder(GET_METHOD_PREFIX);
			methodName.append(String.valueOf((fieldName.charAt(0))).toUpperCase()).append(fieldName.substring(1));
			Method method = dataClass.getMethod(methodName.toString(), (Class[]) null);
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

	protected SubReportType getBudgetIndirectCostsForReport() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		ScaleTwoDecimal calculatedCostForOn = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal calculatedCostForOff = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal costSharingAmountForOn = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal costSharingAmountForOff = ScaleTwoDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            final ScaleTwoDecimal totalCostSharingAmount = getTotalCostSharingAmountByRateClassTypeFromLineItem(budgetLineItem, RateClassType.OVERHEAD.getRateClassType());
            final ScaleTwoDecimal totalCalculatedCost = getTotalCalculatedCostByRateClassTypeFromLineItem(RateClassType.OVERHEAD.getRateClassType(), budgetLineItem);
            if (budgetLineItem.getOnOffCampusFlag().booleanValue()) {
				calculatedCostForOn = calculatedCostForOn.add(totalCalculatedCost);
				costSharingAmountForOn = costSharingAmountForOn.add(totalCostSharingAmount);
			} else {
				calculatedCostForOff = calculatedCostForOff.add(totalCalculatedCost);
				costSharingAmountForOff = costSharingAmountForOff.add(totalCostSharingAmount);
			}
		}
		if (!(calculatedCostForOn.equals(ScaleTwoDecimal.ZERO) && costSharingAmountForOn.equals(ScaleTwoDecimal.ZERO))) {
			ReportType reportTypeForOn = getReportTypeForBudgetIndirectCostsForReport(Boolean.TRUE, calculatedCostForOn, costSharingAmountForOn);
			reportTypeList.add(reportTypeForOn);
		}
		if (!(calculatedCostForOff.equals(ScaleTwoDecimal.ZERO) && costSharingAmountForOff.equals(ScaleTwoDecimal.ZERO))) {
			ReportType reportTypeForOff = getReportTypeForBudgetIndirectCostsForReport(Boolean.FALSE, calculatedCostForOff, costSharingAmountForOff);
			reportTypeList.add(reportTypeForOff);
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	protected CalculationMethodology getCalculationMethodology() {
		CalculationMethodology calculationMethodology = CalculationMethodology.Factory.newInstance();
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

	private SubReportType getBudgetOHExclusions() {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();

		SubReportType subReportType = SubReportType.Factory.newInstance();
		int sortId;
		String categoryDesc = null;
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		if (budget.getBudgetLaRates().size() > 0) {
			sortId = 1;
			categoryDesc = ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId1();
			ReportType reportTypeForSortId1 = getReportTypeForExclusions(sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId1);

			sortId = 2;
			categoryDesc = EMPLOYEE_BENEFITS_ON_ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetOHExclusionsSortId2();
			ReportType reportTypeForSortId2 = getReportTypeForExclusions(sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId2);

			sortId = 3;
			setReportTypeOHExclusionForSortId(reportTypeList, sortId);

			sortId = 4;
			categoryDesc = ALLOCATED_LAB_EXPENSE;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId4();
			ReportType reportTypeForSortId4 = getReportTypeForExclusions(sortId, categoryDesc, calculatedCost);
			if (calculatedCost.doubleValue()>0.0d) {
				reportTypeList.add(reportTypeForSortId4);
			}
		} else {
			sortId = 1;
			setReportTypeOHExclusionForSortId(reportTypeList, sortId);
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	private SubReportType getBudgetLAExclusions() {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		SubReportType subReportType = SubReportType.Factory.newInstance();
		int sortId;
		String categoryDesc = null;
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		if (budget.getBudgetLaRates().size() > 0) {
			sortId = 1;
			categoryDesc = ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId1();
			ReportType reportTypeForSortId1 = getReportTypeForExclusions(sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId1);

			sortId = 2;
			categoryDesc = TOTAL_EMPLOYEE_BENEFITS;
			calculatedCost = getCalculatedCostForBudgetLAExclusionsSortId2();
			ReportType reportTypeForSortId2 = getReportTypeForExclusions(sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId2);
			setReportTypeForBudgetLAExclusionsSortId3(reportTypeList);

			sortId = 4;
			categoryDesc = ALLOCATED_LAB_EXPENSE;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId4();
			ReportType reportTypeForSortId4 = getReportTypeForExclusions(sortId, categoryDesc, calculatedCost);
			if (calculatedCost.doubleValue()>0.0d) {
				reportTypeList.add(reportTypeForSortId4);
			}
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	protected void setReportTypeOHExclusionForSortId(List<ReportType> reportTypeList, int sortId) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		setReportTypeVOListForOHExclusionSortId(tempReportTypeVOList);
        setReportTypeListOHExclusionForSortId(reportTypeList, sortId, tempReportTypeVOList);
	}

	protected void setReportTypeVOListForOHExclusionSortId(List<ReportTypeVO> tempReportTypeVOList) {
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (!checkLineItemNumberAvailableForOHExclusion(budgetLineItem)) {
				ReportTypeVO reportTypeVO = new ReportTypeVO();
				reportTypeVO.setCostElementDesc(getCostElementDescription(budgetLineItem));
				reportTypeVO.setCalculatedCost(budgetLineItem.getLineItemCost());
				tempReportTypeVOList.add(reportTypeVO);
			}
		}
	}

	protected void setReportTypeListOHExclusionForSortId(List<ReportType> reportTypeList, int sortId, List<ReportTypeVO> tempReportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<>();
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String budgetOHExclusionKey = reportTypeVO.getCostElementDesc();
			ScaleTwoDecimal calculatedCost = reportTypeVO.getCalculatedCost();
			if (reportTypeMap.containsKey(budgetOHExclusionKey)) {
			    ReportTypeVO reportTypeVO1 = reportTypeMap.get(budgetOHExclusionKey);
                calculatedCost = calculatedCost.add(reportTypeVO1.getCalculatedCost());
                reportTypeVO1.setCalculatedCost(calculatedCost);
			    reportTypeMap.put(budgetOHExclusionKey, reportTypeVO1);
			} else {
			    reportTypeMap.put(budgetOHExclusionKey, reportTypeVO);
			}
		}
		for (String budgetOHExclusionKey : reportTypeMap.keySet()) {
            ReportTypeVO reportTypeVO1 = reportTypeMap.get(budgetOHExclusionKey);
            ReportType reportType = getReportType();
            reportType.setSortId(sortId);
            reportType.setCostElementDescription(budgetOHExclusionKey);
            reportType.setCalculatedCost(reportTypeVO1.getCalculatedCost().doubleValue());
            reportTypeList.add(reportType);
        }

	}

	protected void setReportTypeForBudgetLAExclusionsSortId3(List<ReportType> reportTypeList) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		setReportTypeVOListForLAExclusionSortId3(tempReportTypeVOList);
		setReportTypeList(reportTypeList, tempReportTypeVOList);
	}

	protected void setReportTypeVOListForLAExclusionSortId3(List<ReportTypeVO> tempReportTypeVOList) {
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (!isBudgetCategoryPersonnel(budgetLineItem) && !checkLineItemNumberAvailableForLAExclusion(budgetLineItem)) {
				ReportTypeVO tempReportTypeVO = new ReportTypeVO();
				tempReportTypeVO.setCostElementDesc(getCostElementDescription(budgetLineItem));
				tempReportTypeVO.setCalculatedCost(budgetLineItem.getLineItemCost());
				tempReportTypeVOList.add(tempReportTypeVO);
			}
		}
	}

	protected void setReportTypeList(List<ReportType> reportTypeList, List<ReportTypeVO> tempReportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String budgetLAExclusionsKey = reportTypeVO.getCostElementDesc();
			if (reportTypeMap.containsKey(budgetLAExclusionsKey)) {
				continue;
			}
			ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
			for (ReportTypeVO reportTypeVO1 : tempReportTypeVOList) {
				String budgetLAExclusionTempKey = reportTypeVO1.getCostElementDesc();
				if (budgetLAExclusionTempKey.equals(budgetLAExclusionsKey)) {
					calculatedCost = calculatedCost.add(reportTypeVO1.getCalculatedCost());
				}
			}
			reportTypeMap.put(budgetLAExclusionsKey, reportTypeVO);
            ReportType reportType = getReportType();
            reportType.setSortId(3);
			reportType.setCostElementDescription(reportTypeVO
					.getCostElementDesc());
			reportType.setCalculatedCost(calculatedCost.doubleValue());
			reportTypeList.add(reportType);
		}
	}

	private boolean checkLineItemNumberAvailableForLAExclusion(BudgetLineItem budgetLineItem) {
		boolean available = false;
		Integer lineItemNumber = budgetLineItem.getLineItemNumber();
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
			budgetLineItemCalcAmount.refreshReferenceObject(RATE_CLASS);
            final boolean lineItemCalAmountLabAllocation = isLineItemCalAmountOfRateClassTypeLabAllocation(budgetLineItemCalcAmount);
            final boolean lineItemCalAmountLAWithEBVA = isLineItemCalAmountOfRateClassTypeLAWithEBVA(budgetLineItemCalcAmount);
            if (budgetLineItemCalcAmount.getApplyRateFlag() && (lineItemCalAmountLabAllocation || lineItemCalAmountLAWithEBVA)) {
				if (budgetLineItemCalcAmount.getLineItemNumber().equals(lineItemNumber)) {
					available = true;
				}
			}
		}
		return available;
	}

	private boolean checkLineItemNumberAvailableForOHExclusion(BudgetLineItem budgetLineItem) {
		boolean available = false;
		Integer lineItemNumber = budgetLineItem.getLineItemNumber();
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
			budgetLineItemCalcAmount.refreshReferenceObject(RATE_CLASS);
			if (budgetLineItemCalcAmount.getApplyRateFlag() && isLineItemCalAmountOfRateClassTypeOverhead(budgetLineItemCalcAmount)) {
				if (budgetLineItemCalcAmount.getLineItemNumber().equals(lineItemNumber)) {
					return true;
				}
			}
		}
		return available;
	}

	protected String getCostElementDescription(BudgetLineItem budgetLineItem) {
        final String lineItemDescription = budgetLineItem.getLineItemDescription() == null ? EMPTY_STRING : STRING_SEPRATOR.concat(budgetLineItem.getLineItemDescription());
        String costElementDesc = budgetLineItem.getCostElementBO().getDescription().concat(lineItemDescription);
		return costElementDesc;
	}

	private SubReportType getBudgetOHRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = getReportTypeListForBudgetOHRateAndBase();
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	private SubReportType getBudgetEBRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = getReportTypeListForBudgetEBRateAndBase();
		subReportType.setGroupArray(getGroupsType(reportTypeList, rateType));
		return subReportType;

	}

	protected List<ReportType> getReportTypeListForBudgetEBRateAndBase() {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetEBRateAndBase(tempReportTypeVOList, budgetLineItem);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetEBRateAndBase(tempReportTypeVOList, budgetLineItem);
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList, reportTypeMap);
		reportTypeList = new ArrayList<>(reportTypeMap.values());
		return reportTypeList;
	}

	private SubReportType getBudgetLARateBaseForPeriod() {
		List<ReportType> reportTypeList = getReportTypeListForBudgetLARateAndBase();
        SubReportType subReportType = SubReportType.Factory.newInstance();
        subReportType.setGroupArray(getGroupsType(reportTypeList, rateClassRateType));
		return subReportType;
	}

	protected List<ReportType> getReportTypeListForBudgetLARateAndBase() {
        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetLineItem);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetLineItem);
            }
        }
        setReportTypeMapFromReportTypeVOList(tempReportTypeVOList, reportTypeMap);
        List<ReportType> reportTypeList = new ArrayList<>(reportTypeMap.values());
		return reportTypeList;
	}

	private SubReportType getBudgetVacRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = getReportTypeListForBudgetVACRateAndBase();
		subReportType.setGroupArray(getGroupsType(reportTypeList, rateType));
		return subReportType;
	}

	protected List<ReportType> getReportTypeListForBudgetVACRateAndBase() {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetLineItem);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetLineItem);
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList, reportTypeMap);
		reportTypeList = new ArrayList<>(reportTypeMap.values());
		return reportTypeList;
	}

	protected void setReportTypeMapFromReportTypeVOList(List<ReportTypeVO> tempReportTypeVOList, Map<String, ReportType> reportTypeMap) {
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String reportTypeKey = getKeyForRateBase(reportTypeVO);
			if (reportTypeMap.containsKey(reportTypeKey)) {
				continue;
			}
			Date startDate = reportTypeVO.getStartDate();
			Date endDate = reportTypeVO.getEndDate();
			ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal salaryRequested = ScaleTwoDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : tempReportTypeVOList) {
				String reportTypeTempKey = getKeyForRateBase(tempReportTypeVO);
				if (reportTypeTempKey.equals(reportTypeKey)) {
					salaryRequested = salaryRequested.add(tempReportTypeVO.getSalaryRequested());
					calculatedCost = calculatedCost.add(tempReportTypeVO.getCalculatedCost());
					if (startDate.after(tempReportTypeVO.getStartDate())) {
						startDate = tempReportTypeVO.getStartDate();
					}
					if (endDate.before(tempReportTypeVO.getEndDate())) {
						endDate = tempReportTypeVO.getEndDate();
					}
				}
			}
			ReportType reportType = getReportTypeForRateAndBase(startDate, endDate, calculatedCost, salaryRequested, reportTypeVO);
			reportTypeMap.put(reportTypeKey, reportType);
		}
	}

	private ReportType getReportTypeForRateAndBase(Date startDate, Date endDate, ScaleTwoDecimal calculatedCost,
                                                   ScaleTwoDecimal salaryRequested, ReportTypeVO reportTypeVO) {
        ReportType reportType = getReportType();
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

	private SubReportType getBudgetOtherRateBaseForPeriod() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList;
		reportTypeList = getReportTypeListForBudgetOtherRateAndBase();
        subReportType.setGroupArray(getGroupsType(reportTypeList, rateClassRateType));
		return subReportType;

	}

	protected List<ReportType> getReportTypeListForBudgetOtherRateAndBase() {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			setBudgetPersRateAndBaseListForBudgetOtherRateAndBase(tempReportTypeVOList, budgetLineItem);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				setBudgetRateAndBaseListForBudgetOtherRateAndBase(tempReportTypeVOList, budgetLineItem);
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList, reportTypeMap);
		reportTypeList = new ArrayList<>(reportTypeMap.values());
		return reportTypeList;
	}

	protected List<ReportType> getReportTypeListForBudgetOHRateAndBase() {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		List<ReportType> reportTypeList;
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            setBudgetPersRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetLineItem);
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
                setBudgetRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetLineItem);
            }
        }

        setReportTypeMapForBudgetOHRateAndBase(tempReportTypeVOList, reportTypeMap);
		reportTypeList = new ArrayList<>(reportTypeMap.values());
		return reportTypeList;
	}

	protected void setReportTypeMapForBudgetOHRateAndBase(List<ReportTypeVO> tempReportTypeVOList, Map<String, ReportType> reportTypeMap) {
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String budgetOHRateBaseKey = getKeyForBudgetOHRateBase(reportTypeVO);
			if (reportTypeMap.containsKey(budgetOHRateBaseKey)) {
				continue;
			}
			Date startDate = reportTypeVO.getStartDate();
			Date endDate = reportTypeVO.getEndDate();
			ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal salaryRequested = ScaleTwoDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : tempReportTypeVOList) {
				String budgetOHRateBaseTempKey = getKeyForBudgetOHRateBase(tempReportTypeVO);
				if (budgetOHRateBaseTempKey.equals(budgetOHRateBaseKey)) {
					salaryRequested = salaryRequested.add(tempReportTypeVO.getSalaryRequested() == null ? ScaleTwoDecimal.ZERO : tempReportTypeVO.getSalaryRequested());
					calculatedCost = calculatedCost.add(tempReportTypeVO.getCalculatedCost());
					if (startDate.after(tempReportTypeVO.getStartDate())) {
						startDate = tempReportTypeVO.getStartDate();
					}
					if (endDate.before(tempReportTypeVO.getEndDate())) {
						endDate = tempReportTypeVO.getEndDate();
					}
				}
			}
			ReportType reportType = getReportTypeForBudgetOHRateAndBase(startDate, endDate, calculatedCost, salaryRequested, reportTypeVO);
			reportTypeMap.put(budgetOHRateBaseKey, reportType);
		}
	}

	private ReportType getReportTypeForBudgetOHRateAndBase(Date startDate, Date endDate, ScaleTwoDecimal calculatedCost,
                                                           ScaleTwoDecimal salaryRequested, ReportTypeVO reportTypeVO) {
        ReportType reportType = getReportType();
        reportType.setSalaryRequested(salaryRequested.doubleValue());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		reportType.setStartDate(DateFormatUtils.format(startDate, DATE_FORMAT));
		reportType.setEndDate(DateFormatUtils.format(endDate, DATE_FORMAT));
		reportType.setAppliedRate(reportTypeVO.getAppliedRate().doubleValue());
		reportType.setRateClassDesc(reportTypeVO.getRateClassDesc());
		reportType.setOnOffCampus(reportTypeVO.getOnOffCampusFlag());
		return reportType;
	}

    public ReportType getReportType() {
        return ReportType.Factory.newInstance();
    }

    private String getKeyForRateBase(ReportTypeVO reportTypeVO) {
		StringBuilder key = new StringBuilder();
		key.append(reportTypeVO.getRateClassDesc()).append(reportTypeVO.getRateTypeDesc()).append(reportTypeVO.getOnOffCampusFlag().toString())
                    .append(reportTypeVO.getAppliedRate().toString());
		return key.toString();
	}

	private String getKeyForBudgetOHRateBase(ReportTypeVO reportTypeVO) {
        StringBuilder key = new StringBuilder();
		key.append(reportTypeVO.getOnOffCampusFlag().toString()).append(reportTypeVO.getAppliedRate().toString())
                .append(reportTypeVO.getRateClassDesc());
		return key.toString();
	}

	protected void setBudgetRateAndBaseListForBudgetOtherRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
		Map<String, BudgetRateAndBase> laRateBaseMap = new HashMap<String, BudgetRateAndBase>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.OTHER.getRateClassType())) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (laRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetLineItem, budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				laRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	protected void setBudgetRateAndBaseListForBudgetVacRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
		Map<String, BudgetRateAndBase> vacBudgetRateBaseMap = new HashMap<>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.VACATION.getRateClassType())
					&& !budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(getLiVacOnLaRateTypeCode())) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (vacBudgetRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetLineItem, budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				vacBudgetRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	protected void setBudgetRateAndBaseListForBudgetLARateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
        Map<String, BudgetRateAndBase> laRateBaseMap = new HashMap<>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassTypeCode().equals( RateClassType.LAB_ALLOCATION.getRateClassType()) ||
                    budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.LA_SALARIES.getRateClassType())) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (laRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetLineItem, budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				laRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	private String getBudgetRateAndBaseKey(BudgetRateAndBase budgetRateAndBase) {
		String key = new StringBuilder(budgetRateAndBase.getStartDate()
				.toString()).append(budgetRateAndBase.getEndDate().toString())
				.append(budgetRateAndBase.getRateTypeCode()).append(
						budgetRateAndBase.getRateClassCode()).toString();
		return key;
	}

	protected void setBudgetRateAndBaseListForBudgetEBRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
        Map<String, BudgetRateAndBase> ebBudgetRateBaseMap = new HashMap<>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.EMPLOYEE_BENEFITS.getRateClassType())
					&& !budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(getLiEbOnLaRateTypeCode())) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (ebBudgetRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseList(budgetLineItem, budgetRateAndBase);
				reportTypeVOList.add(reportTypeVO);
				ebBudgetRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	protected void setBudgetRateAndBaseListForBudgetOHRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
		Map<String, BudgetRateAndBase> ohBudgetRateBaseMap = new HashMap<>();
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.OVERHEAD.getRateClassType())) {
				String budgetRateBaseKey = getBudgetRateAndBaseKey(budgetRateAndBase);
				if (ohBudgetRateBaseMap.containsKey(budgetRateBaseKey)) {
					continue;
				}
				ReportTypeVO reportTypeVO = getBudgetRateAndBaseListForBudgetOHRateBase(budgetRateAndBase, budgetLineItem);
				reportTypeVOList.add(reportTypeVO);
				ohBudgetRateBaseMap.put(budgetRateBaseKey, budgetRateAndBase);
			}
		}
	}

	private ReportTypeVO getBudgetRateAndBaseList(BudgetLineItem budgetLineItem, BudgetRateAndBase budgetRateAndBase) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setRateClassDesc(budgetRateAndBase.getRateClass().getDescription());
		reportTypeVO.setRateTypeDesc(getRateTypeDesc(budgetRateAndBase.getRateClassCode(), budgetRateAndBase.getRateTypeCode()));
		reportTypeVO.setStartDate(budgetRateAndBase.getStartDate());
		reportTypeVO.setEndDate(budgetRateAndBase.getEndDate());
		reportTypeVO.setAppliedRate(budgetRateAndBase.getAppliedRate());

        reportTypeVO.setSalaryRequested(ScaleTwoDecimal.ZERO);
        if (rateIsApplied(budgetLineItem.getBudgetLineItemCalculatedAmounts(), budgetRateAndBase.getRateClassCode(), budgetRateAndBase.getRateTypeCode())) {
            reportTypeVO.setSalaryRequested(budgetRateAndBase.getBaseCost());
        }

		reportTypeVO.setCalculatedCost(budgetRateAndBase.getCalculatedCost());
		reportTypeVO.setOnOffCampusFlag(budgetRateAndBase.getOnOffCampusFlag());
		return reportTypeVO;

	}

	protected ReportTypeVO getBudgetRateAndBaseListForBudgetOHRateBase(BudgetRateAndBase budgetRateAndBase, BudgetLineItem budgetLineItem) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setRateClassDesc(budgetRateAndBase.getRateClass().getDescription());
		reportTypeVO.setStartDate(budgetRateAndBase.getStartDate());
		reportTypeVO.setEndDate(budgetRateAndBase.getEndDate());
		reportTypeVO.setAppliedRate(budgetRateAndBase.getAppliedRate());
        reportTypeVO.setCalculatedCost(budgetRateAndBase.getCalculatedCost());

        reportTypeVO.setSalaryRequested(ScaleTwoDecimal.ZERO);
        if (rateIsApplied(budgetLineItem.getBudgetLineItemCalculatedAmounts(), budgetRateAndBase.getRateClassCode(), budgetRateAndBase.getRateTypeCode())) {
            reportTypeVO.setSalaryRequested(budgetRateAndBase.getBaseCost());
        }

        reportTypeVO.setOnOffCampusFlag(budgetRateAndBase.getOnOffCampusFlag());
		return reportTypeVO;
	}

    protected boolean rateIsApplied(List<BudgetLineItemCalculatedAmount> calculatedAmounts, String rateClassCode, String rateTypeCode) {
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount =
                calculatedAmounts.stream().filter(calculatedAmount -> rateTypeCode.equalsIgnoreCase(calculatedAmount.getRateTypeCode()) &&
                                                                rateClassCode.equalsIgnoreCase(calculatedAmount.getRateClassCode())).findFirst().orElse(null);
        return lineItemCalculatedAmount == null? Boolean.FALSE: lineItemCalculatedAmount.getApplyRateFlag();
    }

	protected void setBudgetPersRateAndBaseListForBudgetOtherRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null &&
                        RateClassType.OTHER.getRateClassType().equals(budgetPersRateAndBase.getRateClass().getRateClassTypeCode())) {
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetLineItem, budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(budgetPersRateAndBase.getRateClassCode(), budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
				}
			}
		}
	}

	protected void setBudgetPersRateAndBaseListForBudgetVacRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null && budgetPersRateAndBase.getRateClass().getRateClassTypeCode() != null
						&& budgetPersRateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.VACATION.getRateClassType())) {
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetLineItem, budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(budgetPersRateAndBase.getRateClassCode(), budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
				}
			}
		}
	}

	protected void setBudgetPersRateAndBaseListForBudgetLARateAndBase(
            List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
        String LAB_ALLOCATION_RATE_CLASS = RateClassType.LAB_ALLOCATION.getRateClassType();
        String LA_SALARIES_RATE_CLASS = RateClassType.LA_SALARIES.getRateClassType();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();

                if (budgetPersRateAndBase.getRateClass() != null &&
                        (LAB_ALLOCATION_RATE_CLASS.equals(budgetPersRateAndBase.getRateClass().getRateClassTypeCode())
                                || LA_SALARIES_RATE_CLASS.equals(budgetPersRateAndBase.getRateClass().getRateClassTypeCode()))) {
                    ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetLineItem, budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(budgetPersRateAndBase.getRateClassCode(), budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
				}
			}
		}
	}

	protected void setBudgetPersRateAndBaseListForBudgetEBRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
        String EB_RATE_CLASS_TYPE = RateClassType.EMPLOYEE_BENEFITS.getRateClassType();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails.getBudgetPersonnelRateAndBaseList()) {
				budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null && budgetPersRateAndBase.getRateClass().getRateClassTypeCode() != null
						&& budgetPersRateAndBase.getRateClass().getRateClassTypeCode().equals(EB_RATE_CLASS_TYPE)) {
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetLineItem, budgetPersRateAndBase);
					reportTypeVO.setRateTypeDesc(getRateTypeDesc(budgetPersRateAndBase.getRateClassCode(), budgetPersRateAndBase.getRateTypeCode()));
					reportTypeVOList.add(reportTypeVO);
				}
			}
		}
	}

	protected void setBudgetPersRateAndBaseListForBudgetOHRateAndBase(List<ReportTypeVO> reportTypeVOList, BudgetLineItem budgetLineItem) {
        String OVERHEAD_RATE_CLASS_TYPE = RateClassType.OVERHEAD.getRateClassType();
		for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
			for (BudgetPersonnelRateAndBase budgetPersRateAndBase : budgetPersDetails.getBudgetPersonnelRateAndBaseList()) {

                budgetPersRateAndBase.refreshNonUpdateableReferences();
				if (budgetPersRateAndBase.getRateClass() != null && budgetPersRateAndBase.getRateClass().getRateClassTypeCode() != null
						&& budgetPersRateAndBase.getRateClass().getRateClassTypeCode().equals(OVERHEAD_RATE_CLASS_TYPE)) {
					ReportTypeVO reportTypeVO = getReportTypeVOForBudgetPersonnelRateAndBase(budgetLineItem, budgetPersRateAndBase);
					reportTypeVOList.add(reportTypeVO);
				}
			}
		}
	}

    protected ReportTypeVO getReportTypeVOForBudgetPersonnelRateAndBase(BudgetLineItem budgetLineItem, BudgetPersonnelRateAndBase budgetPersRateAndBase) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setRateClassDesc(budgetPersRateAndBase.getRateClass().getDescription());
		reportTypeVO.setStartDate(budgetPersRateAndBase.getStartDate());
		reportTypeVO.setEndDate(budgetPersRateAndBase.getEndDate());
		reportTypeVO.setAppliedRate(budgetPersRateAndBase.getAppliedRate());
        reportTypeVO.setCalculatedCost(budgetPersRateAndBase.getCalculatedCost());


        reportTypeVO.setSalaryRequested(ScaleTwoDecimal.ZERO);
        if (rateIsApplied(budgetLineItem.getBudgetLineItemCalculatedAmounts(), budgetPersRateAndBase.getRateClassCode(), budgetPersRateAndBase.getRateTypeCode())) {
            reportTypeVO.setSalaryRequested(budgetPersRateAndBase.getSalaryRequested());
        }

		reportTypeVO.setOnOffCampusFlag(budgetPersRateAndBase.getOnOffCampusFlag());
		return reportTypeVO;
	}

	protected String getBudgetCategoryDescForSalarySummary(BudgetLineItem budgetLineItem, BudgetLineItemBase budgetDetails, AbstractBudgetRateAndBase budgetRateAndBase) {
		budgetLineItem.refreshReferenceObject(COST_ELEMENT_BO);
	    budgetLineItem.refreshReferenceObject(BUDGET_CATEGORY);
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

	protected ScaleTwoDecimal getFringeForLASalaryForRateAndBase(BudgetLineItem budgetLineItem, Date startDate, Date endDate) {
		ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getStartDate().equals(startDate) && budgetRateAndBase.getEndDate().equals(endDate)
                    && budgetRateAndBase.getCalculatedCost() != null) {
				if (isRateAndBaseEBonLA(budgetRateAndBase) || isRateAndBaseVAonLA(budgetRateAndBase)) {
					fringe = fringe.add(budgetRateAndBase.getCalculatedCost());
				}
			}
		}
		return fringe;
	}

    protected ScaleTwoDecimal getFringeCostSharingLASalaryRateAndBase(BudgetLineItem budgetLineItem, Date startDate, Date endDate) {
		ScaleTwoDecimal fringeCostSharing = ScaleTwoDecimal.ZERO;
		for (BudgetRateAndBase budgetRateAndBase : budgetLineItem.getBudgetRateAndBaseList()) {
			budgetRateAndBase.refreshReferenceObject(RATE_CLASS);
			if (budgetRateAndBase.getStartDate().equals(startDate) && budgetRateAndBase.getEndDate().equals(endDate)
					&& budgetRateAndBase.getCalculatedCostSharing() != null) {
				if (isRateAndBaseEBonLA(budgetRateAndBase) || isRateAndBaseVAonLA(budgetRateAndBase)) {
					fringeCostSharing = fringeCostSharing.add(budgetRateAndBase.getCalculatedCostSharing());
				}
			}
		}
		return fringeCostSharing;
	}

	protected Integer getInvestigatorFlag(BudgetPersonnelDetails budgetPersonDetails) {
	        Integer flag = 3;
	        String personId = budgetPersonDetails.getPersonId();
	        flag = this.budget.getBudgetParent().getParentInvestigatorFlag(personId, flag);
	        return flag;
    }

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

	protected String getPersonNameFromBudgetPersonByRateAndBase(BudgetPerson budgetPerson, BudgetPersonnelRateAndBase budgetPersRateAndBase, Integer quantity) {
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

	protected ScaleTwoDecimal getPercentEffortForBudgetPersonnelRateBase(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersDetails,
                                                                         BudgetPersonnelRateAndBase budgetPersRateAndBase) {
		ScaleTwoDecimal percentEffort = ScaleTwoDecimal.ZERO;
		Integer personNumber = budgetPersDetails.getPersonNumber();
		if (budgetPersRateAndBase != null && isRateAndBaseLASalary(budgetPersRateAndBase)) {
			percentEffort = new ScaleTwoDecimal(100);
		} else {
	        //why is this needed? Can't we just use the personnel details we already have??
			for (BudgetPersonnelDetails budgetPersDetail : budgetLineItem.getBudgetPersonnelDetailsList()) {
				if (budgetPersDetail.getPersonNumber().equals(personNumber)
						&& budgetPersDetails.getPercentEffort() != null) {
					percentEffort = percentEffort.add(budgetPersDetail.getPercentEffort());
				}
			}
		}
		return percentEffort;
	}

	protected ScaleTwoDecimal getPercentChargedForBudgetPersonnelRateBase(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersDetails,
                                                                          BudgetPersonnelRateAndBase budgetPersRateAndBase) {
		ScaleTwoDecimal percentCharged = ScaleTwoDecimal.ZERO;
		Integer personNumber = budgetPersDetails.getPersonNumber();
		if (budgetPersRateAndBase != null && isRateAndBaseLASalary(budgetPersRateAndBase)) {
			percentCharged = new ScaleTwoDecimal(100);
		} else {
		    //why is this needed? Can't we just use the personnel details we already have??
			for (BudgetPersonnelDetails budgetPersDetail : budgetLineItem.getBudgetPersonnelDetailsList()) {
				if (budgetPersDetail.getPersonNumber().equals(personNumber) && budgetPersDetails.getPercentEffort() != null) {
					percentCharged = percentCharged.add(budgetPersDetail.getPercentCharged());
				}
			}
		}
		return percentCharged;
	}

	protected String getBudgetCategoryCodeFroBudgetSalarySummary(AbstractBudgetRateAndBase budgetPersRateAndBase, BudgetLineItemBase budgetPersonnelDetails) {
        String categoryCode = null;
		if (budgetPersRateAndBase != null && isRateAndBaseLASalary(budgetPersRateAndBase)) {
			categoryCode = CATEGORY_CODE_LA_SALARY;
		} else if(budgetPersonnelDetails.getCostElementBO()!=null && budgetPersonnelDetails.getCostElementBO().getBudgetCategory()!=null){
			categoryCode = budgetPersonnelDetails.getCostElementBO().getBudgetCategory().getCode();
		}
		return categoryCode;
	}

	protected boolean isBudgetCategoryPersonnel(BudgetLineItemBase budgetDetails) {
		return budgetDetails.getBudgetCategory() != null && BUDGET_CATEGORY_PERSONNEL.equals(budgetDetails.getBudgetCategory().getBudgetCategoryTypeCode());
	}

	protected boolean isRateAndBaseLASalary(AbstractBudgetRateAndBase rateAndBase) {
		rateAndBase.refreshNonUpdateableReferences();
		return rateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.LA_SALARIES.getRateClassType());
	}

	protected boolean isRateAndBaseEBonLA(AbstractBudgetRateAndBase rateAndBase) {
	    ValidCalcType ebOnLaValidCalcType = getDependentValidRateClassTypeForLA(RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
		return ebOnLaValidCalcType!=null && rateAndBase.getRateClassCode().equals(ebOnLaValidCalcType.getRateClassCode())
				&& rateAndBase.getRateTypeCode().equals(ebOnLaValidCalcType.getRateTypeCode());
	}

    private ValidCalcType getDependentValidRateClassTypeForLA(String rateClassType) {
	    Map<String,String> param = new HashMap<String,String>();
	    param.put(RATE_CLASS_TYPE, rateClassType);
	    param.put(DEPENDENT_RATE_CLASS_TYPE, RateClassType.LA_SALARIES.getRateClassType());
	    List<ValidCalcType> result = (List)getBusinessObjectService().findMatching(ValidCalcType.class, param);
        return result.isEmpty()?null:result.get(0);
    }

	protected boolean isRateAndBaseVAonLA(AbstractBudgetRateAndBase rateAndBase) {
	    ValidCalcType vacationOnLaValidCalcType = getDependentValidRateClassTypeForLA(RateClassType.VACATION.getRateClassType());
		return vacationOnLaValidCalcType!=null && rateAndBase.getRateClassCode().equals(vacationOnLaValidCalcType.getRateClassCode())
				    && rateAndBase.getRateTypeCode().equals(vacationOnLaValidCalcType.getRateTypeCode());
	}

	protected boolean isLineItemCalAmountOfRateClassTypeLAWithEBVA(BudgetLineItemCalculatedAmount lineItemCalcAmount) {
		return lineItemCalcAmount.getRateClass().getRateClassTypeCode().equals(RateClassType.LA_SALARIES.getRateClassType());
	}

	protected boolean isLineItemCalAmountOfRateClassTypeLabAllocation(BudgetLineItemCalculatedAmount lineItemCalcAmount) {
		return lineItemCalcAmount.getRateClass().getRateClassTypeCode().equals(RateClassType.LAB_ALLOCATION.getRateClassType());
	}

	protected boolean isLineItemCalAmountOfRateClassTypeOverhead(BudgetLineItemCalculatedAmount lineItemCalcAmount) {
		return lineItemCalcAmount.getRateClass().getRateClassTypeCode().equals(RateClassType.OVERHEAD.getRateClassType());
	}

	protected boolean isRateAndBaseOfRateClassTypeLAwithEBVA(AbstractBudgetRateAndBase rateAndBase) {
		boolean status = false;
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null) {
			return rateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.LA_SALARIES.getRateClassType());
		}
		return status;
	}

	protected boolean isRateAndBaseOfRateClassTypeEB(AbstractBudgetRateAndBase rateAndBase) {
		boolean status = false;
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null) {
			return rateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
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
			return rateAndBase.getRateClass().getRateClassTypeCode().equals(RateClassType.VACATION.getRateClassType());
		}
		return status;
	}

    protected List<ReportTypeVO> getReportTypeVOList(BudgetPeriod budgetPeriod) {
        List<ReportTypeVO> reportTypeVOList = new ArrayList<ReportTypeVO>();
        for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            if (StringUtils.equals(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(), BUDGET_CATEGORY_PERSONNEL)) {
                if (!budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
                    for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                        addReportTypeVO(reportTypeVOList, budgetLineItem, budgetPersDetails,
                                getRatesApplicableToVOList(budgetPersDetails.getBudgetPersonnelRateAndBaseList()));
                    }
                } else {
                    addReportTypeVO(reportTypeVOList, budgetLineItem, budgetLineItem, getRatesApplicableToVOList(budgetLineItem.getBudgetRateAndBaseList()));
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
                ReportTypeVO reportTypeVO = getReportTypeVO(budgetLineItem, budgetDetails, rate);
                reportTypeVOList.add(reportTypeVO);
            }
        } else {
            ReportTypeVO reportTypeVO = getReportTypeVO(budgetLineItem, budgetDetails);
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

    protected List<AbstractBudgetRateAndBase> getRatesApplicableToVOList(List<? extends AbstractBudgetRateAndBase> rates) {
        return getVAAndEBRates(rates);
    }

    private ReportTypeVO getReportTypeVO(BudgetLineItem budgetLineItem, BudgetLineItemBase budgetDetails, AbstractBudgetRateAndBase rate) {
        ReportTypeVO reportTypeVO = new ReportTypeVO();
        reportTypeVO.setStartDate(rate.getStartDate());
        reportTypeVO.setEndDate(rate.getEndDate());
        reportTypeVO.setBudgetCategoryDesc(getBudgetCategoryDescForSalarySummary(budgetLineItem, budgetDetails, rate));
        if (budgetDetails instanceof BudgetPersonnelDetails) {
            BudgetPersonnelDetails budgetPersDetails = (BudgetPersonnelDetails) budgetDetails;
            BudgetPersonnelRateAndBase budgetPersRateAndBase = (BudgetPersonnelRateAndBase) rate;
            reportTypeVO.setPersonName(getPersonNameFromBudgetPersonByRateAndBase(budgetPersDetails.getBudgetPerson(), budgetPersRateAndBase,
                                                                                    budgetLineItem.getQuantity()));
            reportTypeVO.setPercentEffort(getPercentEffortForBudgetPersonnelRateBase(budgetLineItem, budgetPersDetails, budgetPersRateAndBase));
            reportTypeVO.setPercentCharged(getPercentChargedForBudgetPersonnelRateBase(budgetLineItem, budgetPersDetails, budgetPersRateAndBase));
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
            reportTypeVO.setVacationRate(ScaleTwoDecimal.ZERO);
        }
        if (this.isRateAndBaseOfRateClassTypeEB(rate)) {
            reportTypeVO.setEmployeeBenefitRate(rate.getAppliedRate());
        } else {
            reportTypeVO.setEmployeeBenefitRate(ScaleTwoDecimal.ZERO);
        }
        reportTypeVO.setCostSharingAmount(rate.getBaseCostSharing());
        reportTypeVO.setCalculatedCost(rate.getCalculatedCostSharing());
        reportTypeVO.setFringe(rate.getCalculatedCost());
        reportTypeVO.setCostElementDesc(budgetDetails.getCostElementBO().getDescription());
        reportTypeVO.setBudgetCategoryCode(getBudgetCategoryCodeFroBudgetSalarySummary(rate, budgetDetails));

        return reportTypeVO;

    }

    private ReportTypeVO getReportTypeVO(BudgetLineItem budgetLineItem, BudgetLineItemBase budgetDetails) {
        ReportTypeVO reportTypeVO = new ReportTypeVO();
        reportTypeVO.setStartDate(budgetDetails.getStartDate());
        reportTypeVO.setEndDate(budgetDetails.getEndDate());
        reportTypeVO.setBudgetCategoryDesc(getBudgetCategoryDescForSalarySummary(budgetLineItem, budgetDetails, null));
        if (budgetDetails instanceof BudgetPersonnelDetails) {
            BudgetPersonnelDetails budgetPersDetails = (BudgetPersonnelDetails) budgetDetails;
            reportTypeVO.setPersonName(getPersonNameFromBudgetPersonByRateAndBase(budgetPersDetails.getBudgetPerson(), null, budgetLineItem.getQuantity()));
            reportTypeVO.setPercentEffort(getPercentEffortForBudgetPersonnelRateBase(budgetLineItem, budgetPersDetails, null));
            reportTypeVO.setPercentCharged(getPercentChargedForBudgetPersonnelRateBase(budgetLineItem, budgetPersDetails, null));
            reportTypeVO.setInvestigatorFlag(getInvestigatorFlag(budgetPersDetails));
            reportTypeVO.setSalaryRequested(budgetPersDetails.getSalaryRequested());              
        } else {
            //summary personnel line item
            reportTypeVO.setPersonName(PERSONNEL_SUMMARY_LINE_ITEM_NAME);  
            //summary items can't be investigators
            reportTypeVO
                .setInvestigatorFlag(3);
            reportTypeVO.setSalaryRequested(budgetLineItem.getLineItemCost());             
        }
        reportTypeVO.setVacationRate(ScaleTwoDecimal.ZERO);
        reportTypeVO.setEmployeeBenefitRate(ScaleTwoDecimal.ZERO);
        reportTypeVO.setCostSharingAmount(budgetDetails.getCostSharingAmount());
        reportTypeVO.setCalculatedCost(budgetDetails.getCostSharingAmount());
        reportTypeVO.setFringe(ScaleTwoDecimal.ZERO);
        reportTypeVO.setCostElementDesc(budgetDetails.getCostElementBO().getDescription());
        reportTypeVO.setBudgetCategoryCode(getBudgetCategoryCodeFroBudgetSalarySummary(null, budgetDetails));

        return reportTypeVO;
    }	

	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public BudgetPeriod getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

}
