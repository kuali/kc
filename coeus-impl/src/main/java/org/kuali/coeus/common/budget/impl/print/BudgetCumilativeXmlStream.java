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

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.printing.schema.*;
import org.kuali.kra.printing.schema.BudgetSummaryReportDocument.BudgetSummaryReport;
import org.kuali.kra.printing.schema.ReportPageType.BudgetSummary;
import org.kuali.kra.printing.schema.ReportPageType.CalculationMethodology;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class generates XML that conforms with the XSD related to Budget Summary
 * Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 */
@Component("budgetCumilativeXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BudgetCumilativeXmlStream extends BudgetBaseStream {

	private static final String CUMULATIVE_BUDGET = "Cumulative Budget";

    /**
	 * This method generates XML for Award Delta Report. It uses data passed in
	 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl once
	 * generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		this.budget = (Budget) printableBusinessObject;
		if (budget != null) {
			BudgetSummaryReport budgetSummaryReport = BudgetSummaryReport.Factory
					.newInstance();
			BudgetSummaryReportDocument budgetSummaryReportDocument = BudgetSummaryReportDocument.Factory
					.newInstance();
			budgetSummaryReport = getBudgetCumulativeReport();
			budgetSummaryReportDocument
					.setBudgetSummaryReport(budgetSummaryReport);
			xmlObjectList.put(CUMULATIVE_BUDGET, budgetSummaryReportDocument);
		}
		return xmlObjectList;
	}

	/*
	 * This method gets BudgetCumulativeReport for budgetPeriod.Used to set
	 * ReportHeaderType with latest BudgetPeriod, CumilativePage and
	 * RaportPageArray to budgetSummaryReport
	 */
	private BudgetSummaryReport getBudgetCumulativeReport() {
		BudgetSummaryReport budgetSummaryReport = BudgetSummaryReport.Factory
				.newInstance();
		ReportPageType cumulativePageType = ReportPageType.Factory
				.newInstance();
		
		BudgetParent budgetParent = budget.getBudgetParent();
		ReportHeaderType reportHeaderType = getReportHeaderTypeForCumulativeReport(budgetParent);
		budgetSummaryReport.setReportHeader(reportHeaderType);
		cumulativePageType = getCumulativeBudgetReportPageType();
		budgetSummaryReport.setCumilativePage(cumulativePageType);
        ReportPageType[] reportPageTypeList = new ReportPageType[1];
        reportPageTypeList[0]=cumulativePageType;
		budgetSummaryReport.setReportPageArray(reportPageTypeList);
		return budgetSummaryReport;
	}

	/*
	 * This method gets ReportHeaderType for cumulative Report from budget.It
	 * set all the data for RportHeader from budget and DevelopmentProposal
	 */
	protected ReportHeaderType getReportHeaderTypeForCumulativeReport(
			BudgetParent budgetParent) {
		ReportHeaderType reportHeaderType = ReportHeaderType.Factory
				.newInstance();
		if (budgetParent != null) {
		    reportHeaderType.setParentTypeName(budgetParent.getParentTypeName());
			reportHeaderType.setProposalNumber(budgetParent.getParentNumber());
		}
		if (budgetParent != null && budgetParent.getParentTitle() != null) {
			reportHeaderType.setProposalTitle(budgetParent.getParentTitle());
		}
		String principleInvestigatorName = budgetParent.getParentPIName();
		
		if (principleInvestigatorName != null) {
			reportHeaderType.setPIName(principleInvestigatorName);
		}
		if (budget.getVersionNumber() != null) {
			reportHeaderType.setBudgetVersion(budget.getBudgetVersionNumber()
					.intValue());
		}
		if (budget.getStartDate() != null) {
			reportHeaderType.setPeriodStartDate(DateFormatUtils.format(budget
					.getStartDate(), DATE_FORMAT));
		}
		if (budget.getEndDate() != null) {
			reportHeaderType.setPeriodEndDate(DateFormatUtils.format(budget
					.getEndDate(), DATE_FORMAT));
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

	/*
	 * This method gets RaportPageType based on BudgetPeriod. Used to set
	 * BusgetSummary, CalculationMethodology and period to reportPageType
	 */
	private ReportPageType getCumulativeBudgetReportPageType() {

		ReportPageType reportPageType = ReportPageType.Factory.newInstance();
		BudgetSummary budgetSummary = getCumulativeBudget();
		CalculationMethodology calculationMethodology = getCumulativeCalculationMethodology();

		reportPageType.setBudgetSummary(budgetSummary);
		reportPageType.setCalculationMethodology(calculationMethodology);
		reportPageType.setPeriod(budgetPeriod.getBudgetPeriod());
		return reportPageType;
	}

	/*
	 * This method gets CumulativeBudgetSummary for BudgetPeriod. Used to set
	 * cumulativeSalarySummary,
	 * CumulativeSummaryNonPersonnel,BudgetCumulativeIDCForReport,
	 * totalDirectCost, totalCosttoSponsor, totalUnderrecoveryAmount,
	 * TotalCostSharingAmount to budgetSummary
	 */
	private BudgetSummary getCumulativeBudget() {
		BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
		SubReportType subReportType = SubReportType.Factory.newInstance();

		subReportType = getCumulativeSalarySummary();
		budgetSummary.setSalarySummaryFromEDI(subReportType);

		subReportType = getBudgetCumulativeSummaryNonPersonnel();
		budgetSummary.setBudgetSummaryNonPersonnel(subReportType);

		subReportType = getBudgetCumulativeIDCForReport();
		budgetSummary.setBudgetIndirectCostsForReport(subReportType);

		budgetSummary.setTotalDirectCost(budget.getTotalDirectCost()
				.doubleValue());
		budgetSummary
				.setTotalCostToSponsor(budget.getTotalCost().doubleValue());
		budgetSummary.setTotalUnderrecoveryAmount(budget
				.getUnderrecoveryAmount().doubleValue());
		budgetSummary.setTotalCostSharingAmount(budget.getCostSharingAmount()
				.doubleValue());

		return budgetSummary;
	}

	/*
	 * This method gets set BudgetExclusions and BudgetRateAndBase to
	 * calculatedMethodology for BudgetPeriod
	 */
	private CalculationMethodology getCumulativeCalculationMethodology() {
		CalculationMethodology calculationMethodology = CalculationMethodology.Factory
				.newInstance();
		SubReportType subReportType = SubReportType.Factory.newInstance();

		subReportType = getCumulativeBudgetOHExclusions();
		calculationMethodology.setBudgetOHExclusions(subReportType);

		subReportType = getCumulativeBudgetLAExclusions();
		calculationMethodology.setBudgetLAExclusions(subReportType);

		subReportType = getCumulativeBudgetOHRateBase();
		calculationMethodology.setBudgetOHRateBaseForPeriod(subReportType);

		subReportType = getCumulativeBudgetEBRateBase();
		calculationMethodology.setBudgetEBRateBaseForPeriod(subReportType);

		subReportType = getCumulativeBudgetLARateBase();
		calculationMethodology.setBudgetLARateBaseForPeriod(subReportType);

		subReportType = getCumulativeBudgetVacRateBase();
		calculationMethodology.setBudgetVacRateBaseForPeriod(subReportType);

		subReportType = getCumulativeBudgetOtherRateBase();
		calculationMethodology.setBudgetOtherRateBaseForPeriod(subReportType);

		return calculationMethodology;
	}

	/*
	 * This method gets subReportType for CumulativeSalarySummary by
	 * BudgetPeriod. Here CumulativeBudgetSalary,
	 * LaSalaryForBudgetPersonnelRateAndBase and LaSalaryBudgetRateAndBase are
	 * set to reportTypeList
	 */
	private SubReportType getCumulativeSalarySummary() {

		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		setReportTypeForCumulativeBudgetSalary(reportTypeList);
		setBudgetLASalaryForBudgetRateAndBaseForCumulativeReport(reportTypeList);
		subReportType.setGroupArray(getGroupsType(reportTypeList, category));
		return subReportType;
	}

    protected void setBudgetLASalaryForBudgetRateAndBaseForCumulativeReport(List<ReportType> reportTypeList) {
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                this.budgetPeriod = budgetPeriod;
                setBudgetLASalaryForBudgetRateAndBase(reportTypeList, new ArrayList<ReportTypeVO>());

        }
    }

	/*
	 * This method sets reportType For CumulativeBudgetSalary in reportTypeList
	 * for a BudgetPeriod based on RateClassCode and RateTypeCode
	 */
    protected void setReportTypeForCumulativeBudgetSalary (List<ReportType> reportTypeList) {

		List<ReportTypeVO> reportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
		    reportTypeVOList.addAll(getReportTypeVOList(budgetPeriod));
        }
		reportTypeVOList.sort(Comparator.comparing(ReportTypeVO::getBudgetCategoryCode));
        setReportTypeListFromReportTypeVoListForCumulativeBudgetSalary(
                reportTypeList, reportTypeVOList);
	}

	/*
	 * This method sets reportTypeVO to ReportTypeList for
	 * CumulativeBudgetSalary by groping reportTypeVo based on
	 * cumulativeBudgetSalaryKey and gets sum of fringe also get calculated
	 * vacationRate, empBenefitRate
	 */
	private void setReportTypeListFromReportTypeVoListForCumulativeBudgetSalary(
			List<ReportType> reportTypeList, List<ReportTypeVO> reportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : reportTypeVOList) {
			String cumulativeBudgetSalaryKey = getKeyForBudgetSalarySummary(reportTypeVO);
			if (reportTypeMap.containsKey(cumulativeBudgetSalaryKey)) {
				continue;
			}
			ScaleTwoDecimal vacationRate = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal empBenefitRate = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : reportTypeVOList) {
				String cumulativeBudgetSalaryTempKey = getKeyForBudgetSalarySummary(tempReportTypeVO);
				if (cumulativeBudgetSalaryTempKey
						.equals(cumulativeBudgetSalaryKey)) {
					if (vacationRate.isLessThan(tempReportTypeVO
							.getVacationRate())) {
						vacationRate = tempReportTypeVO.getVacationRate();
					}
					if (empBenefitRate.isLessThan(tempReportTypeVO
							.getEmployeeBenefitRate())) {
						empBenefitRate = tempReportTypeVO
								.getEmployeeBenefitRate();
					}
					fringe = fringe.add(tempReportTypeVO.getFringe());
				}
			}
			ReportType reportType = getReportTypeForCumulativeBudgetSalary(
					vacationRate, empBenefitRate, fringe, reportTypeVO);
			reportTypeMap.put(cumulativeBudgetSalaryKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	/*
	 * This method gets reportType for CumulativeBudgetSalary by setting data to
	 * reportType from passed parameters
	 */
	private ReportType getReportTypeForCumulativeBudgetSalary(
			ScaleTwoDecimal vacationRate, ScaleTwoDecimal empBenefitRate,
			ScaleTwoDecimal fringe, ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setStartDate(reportTypeVO.getStartDate().toString());
		reportType.setEndDate(reportTypeVO.getEndDate().toString());
		reportType.setBudgetCategoryDescription(reportTypeVO
				.getBudgetCategoryDesc());
		reportType.setPersonName(reportTypeVO.getPersonName());
		reportType.setVacationRate(vacationRate.toString().concat(PERCENTAGE));
		reportType.setEmployeeBenefitRate(empBenefitRate.toString().concat(
				PERCENTAGE));
		reportType.setFringe(fringe.doubleValue());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setInvestigatorFlag(reportTypeVO.getInvestigatorFlag());
		if (reportTypeVO.getBudgetCategoryCode() != null) {
			reportType.setBudgetCategoryCode(Integer.parseInt(reportTypeVO
					.getBudgetCategoryCode()));
		}
		reportType.setSalaryRequested(reportTypeVO.getSalaryRequested()
				.doubleValue());
		return reportType;
	}

	/*
	 * This method gets SubreportType for BudgetCumulativeSummaryNonPersonnel
	 * from List of BudgetLineItem by checking unitUmber
	 */
	private SubReportType getBudgetCumulativeSummaryNonPersonnel() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		String categoryDesc = OTHER_DIRECT_COSTS;
		String costElementDesc = ALLOCATED_LAB_EXPENSE;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			if (getUnitNumber() > 0) {
				for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
					calculatedCost = calculatedCost.add(
                            getTotalCalculatedCostByRateClassTypeFromLineItem(RateClassType.LAB_ALLOCATION.getRateClassType(), budgetLineItem));
				}
			}
		}
		ReportType reportType = getReportTypeForNonPersonnel(categoryDesc,
				costElementDesc, calculatedCost, null);
		if(calculatedCost.doubleValue()>0.0){
			reportTypeList.add(reportType);
		}
		setReportTypeForBudgetCumulativeNonPersonnel(reportTypeList);
		Collections.sort(reportTypeList, new Comparator<ReportType>() {
			public int compare(ReportType reportType1, ReportType reportType2) {
				return reportType1.getBudgetCategoryDescription().compareTo(
						reportType2.getBudgetCategoryDescription());
			}
		});
		subReportType.setGroupArray(getGroupsType(reportTypeList, category));
		return subReportType;
	}

	/*
	 * This method set ReportType data to ReportTypeList for
	 * BudgetCumulativeNonPersonnel
	 */
	private void setReportTypeForBudgetCumulativeNonPersonnel(
			List<ReportType> reportTypeList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				if (!isBudgetCategoryPersonnel(budgetLineItem)) {
					ReportTypeVO tempReportTypeVO = getReportTypeVOForBudgetCumulativeNonPersonnel(budgetLineItem);
					tempReportTypeVOList.add(tempReportTypeVO);
				}
			}
		}
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String cumulativeNonPersKey = reportTypeVO.getCostElementDesc();
			if (reportTypeMap.containsKey(cumulativeNonPersKey)) {
				continue;
			}
			ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
			for (ReportTypeVO reportTypeVO1 : tempReportTypeVOList) {
				String cumulativeNonPersTempKey = reportTypeVO1
						.getCostElementDesc();
				if (cumulativeNonPersTempKey.equals(cumulativeNonPersKey)) {
					calculatedCost = calculatedCost.add(reportTypeVO1
							.getCalculatedCost());
				}
			}
			ReportType reportType = getReportTypeForBudgetCumulativeNonPersonnel(
					calculatedCost, reportTypeVO);
			reportTypeMap.put(cumulativeNonPersKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	/*
	 * This method gets reportType for BudgetCumulativeNonPersonnel by setting
	 * parameters data to reportType
	 */
	private ReportType getReportTypeForBudgetCumulativeNonPersonnel(
			ScaleTwoDecimal calculatedCost, ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setBudgetCategoryDescription(reportTypeVO
				.getBudgetCategoryDesc());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		return reportType;
	}

	/*
	 * This method gets reportTypeVO for BudgetCumulativeNonPersonnel by setting
	 * parameters data to reportType
	 */
	private ReportTypeVO getReportTypeVOForBudgetCumulativeNonPersonnel(
			BudgetLineItem budgetLineItem) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setBudgetCategoryDesc(budgetLineItem.getBudgetCategory()
				.getDescription());
		reportTypeVO
				.setCostElementDesc(getCostElementDescription(budgetLineItem));
		reportTypeVO.setCalculatedCost(budgetLineItem.getLineItemCost());
		return reportTypeVO;
	}

	/*
	 * This method gets subReportType for BudgetCumulativeIDCForReport by
	 * BudgetPeriod. It get the sum of calculatedCost based on BudgetLineItem
	 * OnOffCampusFlag and RateClassType
	 */
	private SubReportType getBudgetCumulativeIDCForReport() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		ScaleTwoDecimal calculatedCostForOn = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal calculatedCostForOff = ScaleTwoDecimal.ZERO;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				if (budgetLineItem.getOnOffCampusFlag().booleanValue()) {
					calculatedCostForOn = calculatedCostForOn
							.add(getTotalCalculatedCostByRateClassTypeFromLineItem(
									RateClassType.OVERHEAD.getRateClassType(),
									budgetLineItem));
				} else {
					calculatedCostForOff = calculatedCostForOff
							.add(getTotalCalculatedCostByRateClassTypeFromLineItem(
									RateClassType.OVERHEAD.getRateClassType(),
									budgetLineItem));
				}
			}
		}
		if (!calculatedCostForOn.equals(ScaleTwoDecimal.ZERO)) {
			ReportType reportTypeForOn = getReportTypeForBudgetIndirectCostsForReport(
					Boolean.TRUE, calculatedCostForOn, null);
			reportTypeList.add(reportTypeForOn);
		}
		if (!calculatedCostForOff.equals(ScaleTwoDecimal.ZERO)) {
			ReportType reportTypeForOff = getReportTypeForBudgetIndirectCostsForReport(
					Boolean.FALSE, calculatedCostForOff, null);
			reportTypeList.add(reportTypeForOff);
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	/*
	 * This method get CumulativeBudgetOHExclusions for a BudgetPeriod. It first
	 * check size of BudgetProposalLARates if it's it create ReportTypeList with
	 * sortId 1,2,3,4 else it create reportTypeList for sortId 1
	 */
	private SubReportType getCumulativeBudgetOHExclusions() {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		SubReportType subReportType = SubReportType.Factory.newInstance();
		int sortId;
		String categoryDesc = null;
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		if (budget.getBudgetLaRates().size() > 0) {
			sortId = 1;
			categoryDesc = ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId1ForCumulativeReport();
			ReportType reportTypeForSortId1 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId1);
			sortId = 2;
			categoryDesc = EMPLOYEE_BENEFITS_ON_ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetOHExclusionsSortId2ForCumulativeReport();
			ReportType reportTypeForSortId2 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId2);
			sortId = 3;
			setReportTypeOHExclusionForSortIdForCumulativeReport(reportTypeList, sortId);
			sortId = 4;
			categoryDesc = ALLOCATED_LAB_EXPENSE;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId4ForCumulativeReport();
			ReportType reportTypeForSortId4 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			if(calculatedCost.doubleValue()>0.0d){
	             reportTypeList.add(reportTypeForSortId4);
			}
		} else {
			sortId = 1;
			setReportTypeOHExclusionForSortId(reportTypeList, sortId);
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}
	   protected void setReportTypeOHExclusionForSortId(
	            List<ReportType> reportTypeList, int sortId) {
	        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
	        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
	            this.budgetPeriod = budgetPeriod;
	            setReportTypeVOListForOHExclusionSortId(tempReportTypeVOList);
	        }
	        setReportTypeListOHExclusionForSortId(reportTypeList, sortId,
	                tempReportTypeVOList);
	    }
	/*
	 * This method gets sum of calculatedCost from list of BudgetPeriod,
	 * BudgetLineItem and iterate through each BudgetLineItemCalculatedAmount
	 * for BudgetExclusionsSortId4 based on RateClassType LAB_ALLOCATION
	 * 
	 */
	private ScaleTwoDecimal getCalculatedCostForBudgetExclusionsSortId4ForCumulativeReport() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			calculatedCost = calculatedCost
					.add(getCalculatedCostForBudgetExclusionsSortId4());
		}
		return calculatedCost;
	}

	/*
	 * This method sets ReportType from list of BudgetLineItem by checking the
	 * lineItemNumber and create ReportTypeVO by setting data to it and add to
	 * ReportTypeVOList
	 * 
	 */
	private void setReportTypeOHExclusionForSortIdForCumulativeReport(
			List<ReportType> reportTypeList, int sortId) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			setReportTypeVOListForOHExclusionSortId(tempReportTypeVOList);
		}
		setReportTypeListOHExclusionForSortId(reportTypeList, sortId,
				tempReportTypeVOList);
	}

	/*
	 * This method gets sum of calculatedCost from list of BudgetPeriod,
	 * BudgetLineItem and iterate through each budgetRateAndBase for
	 * BudgetOHExclusionsSortId2 based on RateClassCode and RateTypeCode
	 */
	private ScaleTwoDecimal getCalculatedCostForBudgetOHExclusionsSortId2ForCumulativeReport() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			calculatedCost = calculatedCost
					.add(getCalculatedCostForBudgetOHExclusionsSortId2());
		}
		return calculatedCost;
	}

	/*
	 * This method gets sum of calculatedCost from list of BudgetPeriod,
	 * BudgetLineItem and iterate through each budgetRateAndBase for
	 * BudgetExclusionsSortId1 based on RateClassType LA_WITH_EB_VA
	 */
	private ScaleTwoDecimal getCalculatedCostForBudgetExclusionsSortId1ForCumulativeReport() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			calculatedCost = calculatedCost
					.add(getCalculatedCostForBudgetExclusionsSortId1());
		}
		return calculatedCost;
	}

	/*
	 * This method get CumulativeBudgetLAExclusions for a BudgetPeriod. It first
	 * check size of BudgetProposalLARates if it's it create ReportTypeList with
	 * sortId 1,2,3,4
	 */
	private SubReportType getCumulativeBudgetLAExclusions() {
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		SubReportType subReportType = SubReportType.Factory.newInstance();
		int sortId;
		String categoryDesc = null;
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		if (budget.getBudgetLaRates().size() > 0) {
			sortId = 1;
			categoryDesc = ALLOCATED_ADMINISTRATIVE_SUPPORT;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId1ForCumulativeReport();
			ReportType reportTypeForSortId1 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId1);
			sortId = 2;
			categoryDesc = TOTAL_EMPLOYEE_BENEFITS;
			calculatedCost = getCalculatedCostForBudgetLAExclusionsSortId2ForCumulativeReport();
			ReportType reportTypeForSortId2 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			reportTypeList.add(reportTypeForSortId2);
			setReportTypeForBudgetLAExclusionsSortId3ForCumulativeReport(reportTypeList);
			sortId = 4;
			categoryDesc = ALLOCATED_LAB_EXPENSE;
			calculatedCost = getCalculatedCostForBudgetExclusionsSortId4ForCumulativeReport();
			ReportType reportTypeForSortId4 = getReportTypeForExclusions(
					sortId, categoryDesc, calculatedCost);
			if(calculatedCost.doubleValue()>0.0d){
				reportTypeList.add(reportTypeForSortId4);
			}
		}
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	/*
	 * This method sets ReportType from list of BudgetLineItem by checking the
	 * lineItemNumber, budgetCategoryCode and create ReportTypeVO by setting
	 * data to it and add to ReportTypeVOList
	 */
	private void setReportTypeForBudgetLAExclusionsSortId3ForCumulativeReport(
			List<ReportType> reportTypeList) {
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			setReportTypeVOListForLAExclusionSortId3(tempReportTypeVOList);
		}
		setReportTypeList(reportTypeList, tempReportTypeVOList);
	}

	/*
	 * This method gets sum of calculatedCost from list of BudgetPeriod,
	 * BudgetLineItem and iterate through each budgetRateAndBase for
	 * BudgetLAExclusionsSortId2 based on RateClassType EMPLOYEE_BENEFITS and
	 * VACATION
	 */
	private ScaleTwoDecimal getCalculatedCostForBudgetLAExclusionsSortId2ForCumulativeReport() {
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			calculatedCost = calculatedCost
					.add(getCalculatedCostForBudgetLAExclusionsSortId2());
		}
		return calculatedCost;
	}

	/*
	 * This method gets subReportType for CumulativeBudgetOHRateBase by
	 * BudgetPeriods for RateClassType OVERHEAD
	 */
	private SubReportType getCumulativeBudgetOHRateBase() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		String rateClassType = RateClassType.OVERHEAD.getRateClassType();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				setBudgetPersRateAndBaseListForBudgetOHRateAndBase(
						tempReportTypeVOList, budgetLineItem);
				if (!isBudgetCategoryPersonnel(budgetLineItem)) {
					setBudgetRateAndBaseListForBudgetOHRateAndBase(
							tempReportTypeVOList, budgetLineItem);
				}
			}
		}
		setReportTypeMapForBudgetOHRateAndBase(tempReportTypeVOList,
				reportTypeMap);
		List<ReportType> reportTypeList = new ArrayList<ReportType>(
				reportTypeMap.values());
		subReportType.setGroupArray(getGroupsType(reportTypeList));
		return subReportType;
	}

	/*
	 * This method gets subReportType for CumulativeBudgetEBRateBase by
	 * BudgetPeriod for RateClassType EMPLOYEE_BENEFITS
	 */
	private SubReportType getCumulativeBudgetEBRateBase() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		String rateClassType = RateClassType.EMPLOYEE_BENEFITS
				.getRateClassType();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				setBudgetPersRateAndBaseListForBudgetEBRateAndBase(
						tempReportTypeVOList, budgetLineItem);
				if (!isBudgetCategoryPersonnel(budgetLineItem)) {
					setBudgetRateAndBaseListForBudgetEBRateAndBase(
							tempReportTypeVOList, budgetLineItem);
				}
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		List<ReportType> reportTypeList = new ArrayList<ReportType>(
				reportTypeMap.values());
		subReportType.setGroupArray(getGroupsType(reportTypeList, rateType));
		return subReportType;

	}

	/*
	 * This method gets subReportType for CumulativeBudgetLARateBase by
	 * BudgetPeriod for RateClassType LAB_ALLOCATION
	 */
	private SubReportType getCumulativeBudgetLARateBase() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		String rateClassType = RateClassType.LAB_ALLOCATION.getRateClassType();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				setBudgetPersRateAndBaseListForBudgetLARateAndBase(
						tempReportTypeVOList, budgetLineItem
                );
				if (!isBudgetCategoryPersonnel(budgetLineItem)) {
					setBudgetRateAndBaseListForBudgetLARateAndBase(
							tempReportTypeVOList, budgetLineItem
                    );
				}
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		List<ReportType> reportTypeList = new ArrayList<ReportType>(
				reportTypeMap.values());
		subReportType.setGroupArray(getGroupsType(reportTypeList,
				rateClassRateType));
		return subReportType;
	}

	/*
	 * This method gets subReportType for CumulativeBudgetVacRateBase by
	 * BudgetPeriod for RateClassType VACATION
	 */
	private SubReportType getCumulativeBudgetVacRateBase() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		String rateClassType = RateClassType.VACATION.getRateClassType();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				setBudgetPersRateAndBaseListForBudgetVacRateAndBase(
						tempReportTypeVOList, budgetLineItem);
				if (!isBudgetCategoryPersonnel(budgetLineItem)) {
					setBudgetRateAndBaseListForBudgetVacRateAndBase(
							tempReportTypeVOList, budgetLineItem);
				}
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		List<ReportType> reportTypeList = new ArrayList<ReportType>(
				reportTypeMap.values());

		subReportType.setGroupArray(getGroupsType(reportTypeList, rateType));
		return subReportType;
	}

	/*
	 * This method gets subReportType for CumulativeBudgetOtherRateBase by
	 * BudgetPeriod for RateClassType OTHER
	 */
	private SubReportType getCumulativeBudgetOtherRateBase() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		String rateClassType = RateClassType.OTHER.getRateClassType();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		Map<String, ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				setBudgetPersRateAndBaseListForBudgetOtherRateAndBase(
						tempReportTypeVOList, budgetLineItem);
				if (!isBudgetCategoryPersonnel(budgetLineItem)) {
					setBudgetRateAndBaseListForBudgetOtherRateAndBase(
							tempReportTypeVOList, budgetLineItem);
				}
			}
		}
		setReportTypeMapFromReportTypeVOList(tempReportTypeVOList,
				reportTypeMap);
		List<ReportType> reportTypeList = new ArrayList<ReportType>(
				reportTypeMap.values());

		subReportType.setGroupArray(getGroupsType(reportTypeList,
				rateClassRateType));
		return subReportType;

	}

}
