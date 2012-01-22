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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.BudgetSummaryReportDocument;
import noNamespace.ReportHeaderType;
import noNamespace.ReportPageType;
import noNamespace.ReportType;
import noNamespace.SubReportType;
import noNamespace.BudgetSummaryReportDocument.BudgetSummaryReport;
import noNamespace.ReportPageType.BudgetSummary;
import noNamespace.ReportPageType.CalculationMethodology;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.printing.util.ReportTypeVO;
import org.kuali.kra.document.ResearchDocumentBase;


/**
 * This class generates XML that conforms with the XSD related to Budget Summary
 * Report. The data for XML is derived from {@link ResearchDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * 
 */
public class BudgetSummaryXmlStream extends BudgetBaseStream {

	private static final Log LOG = LogFactory
			.getLog(BudgetSummaryXmlStream.class);

	/**
	 * This method generates XML for Award Delta Report. It uses data passed in
	 * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
	 * generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return Map consisting of XML Objects mapped to bookmarks
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectMap = new LinkedHashMap<String, XmlObject>();
		this.budget = (Budget) printableBusinessObject;
		if (budget != null) {
			for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
				if (!(budgetPeriod.getBudgetLineItems() != null && !budgetPeriod
						.getBudgetLineItems().isEmpty())) {
					LOG
							.debug("Skipping printing of empty budget period, for Budget period - "
									+ budgetPeriod.getBudgetPeriod());
					continue;
				}
				this.budgetPeriod = budgetPeriod;
				BudgetSummaryReport budgetSummaryReport = BudgetSummaryReport.Factory
						.newInstance();
				BudgetSummaryReportDocument budgetSummaryReportDocument = BudgetSummaryReportDocument.Factory
						.newInstance();
				budgetSummaryReport = getBudgetSummaryReport();
				budgetSummaryReportDocument
						.setBudgetSummaryReport(budgetSummaryReport);
				xmlObjectMap.put(
						BUDGET_PERIOD + budgetPeriod.getBudgetPeriod(),
						budgetSummaryReportDocument);
			}
		}
		return xmlObjectMap;
	}

	/*
	 * This method gets budgetSummaryReport for budgetPeriods, Used to get
	 * ReportHeaderType from latest budgetPeriod and sets reportHeader,
	 * cumulativePage, reportPageArray to budgetSummaryReport
	 */
	private BudgetSummaryReport getBudgetSummaryReport() {
		BudgetSummaryReport budgetSummaryReport = BudgetSummaryReport.Factory
				.newInstance();
		ReportPageType cumilativePageType = ReportPageType.Factory
				.newInstance();
	
		BudgetParent budgetParent = budget.getBudgetParent();

		ReportHeaderType reportHeaderType = getReportHeaderTypeForCumulativeReport(budgetParent);
		budgetSummaryReport.setReportHeader(reportHeaderType);
		cumilativePageType = getBudgetSummaryReportPageType();
		budgetSummaryReport.setCumilativePage(cumilativePageType);
		ReportPageType[] reportPageTypeArray = getReportPageTypes();
		budgetSummaryReport.setReportPageArray(reportPageTypeArray);
		return budgetSummaryReport;
	}

	/*
	 * This method gets array of ReportPageType for BudgetPeriod.ReportPageType.
	 * It is created for each BudgetPeriod and then reportPageType is added to
	 * ReportPageTypeList
	 */
	private ReportPageType[] getReportPageTypes() {
		ReportPageType reportPageType;
		List<ReportPageType> reportPageTypeList = new ArrayList<ReportPageType>();
		reportPageType = getBudgetSummaryReportPageType();
		reportPageTypeList.add(reportPageType);
		return reportPageTypeList.toArray(new ReportPageType[0]);
	}

	/*
	 * This method gets RaportPageType for BudgetPeriod. BudgetSummary,
	 * CalculationMethodology and period are set to ReportPageType
	 */
	private ReportPageType getBudgetSummaryReportPageType() {
		ReportPageType reportPageType = ReportPageType.Factory.newInstance();
		BudgetSummary budgetSummary = getBudgetSummary();
		reportPageType.setBudgetSummary(budgetSummary);
		CalculationMethodology calculationMethodology = getCalculationMethodology();
		reportPageType.setCalculationMethodology(calculationMethodology);
		reportPageType.setPeriod(budgetPeriod.getBudgetPeriod());
		return reportPageType;
	}

	/*
	 * This method gets BudgetSummary for BudgetPeriod and gets sets
	 * salarySummary, budgetSummaryNonPersonnel, BudgetIndirectCost,
	 * totalDirectCost, TotalCostTOSponsor, TotalUnderrecoveryAmount,
	 * TotalCostSharingAmount to budgetSummary
	 */
	private BudgetSummary getBudgetSummary() {
		BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
		SubReportType subReportType = SubReportType.Factory.newInstance();

		subReportType = getSalarySummary();
		budgetSummary.setSalarySummaryFromEDI(subReportType);

		subReportType = getBudgetSummaryNonPersonnel();
		budgetSummary.setBudgetSummaryNonPersonnel(subReportType);

		subReportType = getBudgetIndirectCostsForReport();
		budgetSummary.setBudgetIndirectCostsForReport(subReportType);

		budgetSummary.setTotalDirectCost(budgetPeriod.getTotalDirectCost()
				.doubleValue());
		budgetSummary.setTotalCostToSponsor(budgetPeriod.getTotalCost()
				.doubleValue());
		budgetSummary.setTotalUnderrecoveryAmount(budgetPeriod
				.getUnderrecoveryAmount().doubleValue());
		budgetSummary.setTotalCostSharingAmount(budgetPeriod
				.getCostSharingAmount().doubleValue());

		return budgetSummary;
	}

	/*
	 * This method gets subReportType for SalarySummary by BudgetPeriod. Here
	 * BudgetSalarySummary, LaSalaryForBudgetPersonnelRateAndBase and
	 * LaSalaryBudgetRateAndBase are set to reportTypeList
	 */
	private SubReportType getSalarySummary() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		setReportTypeForBudgetSalarySummary(reportTypeList);
		setBudgetLASalaryForBudgetRateAndBase(reportTypeList);
		subReportType.setGroupArray(getGroupsType(reportTypeList, category));
		return subReportType;
	}

	/*
	 * This method sets reportType for BudgetSalarySummary from list of
	 * BudgetPersonnelDetails based on RateClassCode and RateTypeCode which
	 * iterate for each BudgetLineItem
	 */
	private void setReportTypeForBudgetSalarySummary(
			List<ReportType> reportTypeList) {

		setReportTypeListFromReportTypeVOListForBudgetSalarySummary(
				reportTypeList, getReportTypeVOList(budgetPeriod));
	}
	

	/*
	 * This method sets reportTypeVO to ReportTypeList for BudgetSalarySummary
	 * by groping reportTypeVo based on budgetSalarySummaryKey and gets sum of
	 * fringe, fringeCostSharing also get calculated vacationRate,
	 * empBenefitRate
	 */
	private void setReportTypeListFromReportTypeVOListForBudgetSalarySummary(
			List<ReportType> reportTypeList, List<ReportTypeVO> reportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : reportTypeVOList) {
			String budgetSalarySummaryKey = getKeyForBudgetSalarySummary(reportTypeVO);
			if (reportTypeMap.containsKey(budgetSalarySummaryKey)) {
				continue;
			}
			BudgetDecimal vacationRate = BudgetDecimal.ZERO;
			BudgetDecimal empBenefitRate = BudgetDecimal.ZERO;
			BudgetDecimal fringe = BudgetDecimal.ZERO;
			BudgetDecimal fringeCostSharing = BudgetDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : reportTypeVOList) {
				String budgetSalarySummaryTempKey = getKeyForBudgetSalarySummary(tempReportTypeVO);
				if (budgetSalarySummaryTempKey.equals(budgetSalarySummaryKey)) {
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
					fringeCostSharing = fringeCostSharing.add(tempReportTypeVO
							.getCalculatedCost());
				}
			}
			ReportType reportType = getReportTypeForBudgetSalarySummary(
					vacationRate, empBenefitRate, fringe, fringeCostSharing,
					reportTypeVO);
			reportTypeMap.put(budgetSalarySummaryKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	/*
	 * This method gets reportType for BudgetSalarySummary by setting data to
	 * reportType from passed parameters
	 */
	private ReportType getReportTypeForBudgetSalarySummary(
			BudgetDecimal vacationRate, BudgetDecimal empBenefitRate,
			BudgetDecimal fringe, BudgetDecimal fringeCostSharing,
			ReportTypeVO reportTypeVO) {
		SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT_MMDDYY);
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setStartDate(dateFormat.format(reportTypeVO.getStartDate()));
		reportType.setEndDate(dateFormat.format(reportTypeVO.getEndDate()));
		reportType.setBudgetCategoryDescription( reportTypeVO
				.getBudgetCategoryDesc());
		reportType.setPersonName(reportTypeVO.getPersonName());
		reportType.setPercentEffort(reportTypeVO.getPercentEffort() != null ? reportTypeVO.getPercentEffort().doubleValue() : 0.00);
		reportType.setPercentCharged(reportTypeVO.getPercentCharged() != null ? reportTypeVO.getPercentCharged().doubleValue() : 0.00);
		reportType.setVacationRate(vacationRate.toString().concat(PERCENTAGE));
		reportType.setEmployeeBenefitRate(empBenefitRate.toString().concat(
				PERCENTAGE));
		reportType.setCostSharingAmount(reportTypeVO.getCostSharingAmount()
				.doubleValue());
		reportType.setCalculatedCost(fringeCostSharing.doubleValue());
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
	 * This method gets subReportType for BudgetSummaryNonPersonnel from List of
	 * BudgetLineItems by checking the UnitNumber
	 */
	private SubReportType getBudgetSummaryNonPersonnel() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		BudgetDecimal costSharingAmount = BudgetDecimal.ZERO;
		BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
		if (getUnitNumber() > 0) {
			String categoryDesc = OTHER_DIRECT_COSTS;
			String costElementDesc = ALLOCATED_LAB_EXPENSE;
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				calculatedCost = calculatedCost
						.add(getTotalCalculatedCostByRateClassTypeFromLineItem(
								RateClassType.LAB_ALLOCATION.getRateClassType(),
								budgetLineItem));
				costSharingAmount = costSharingAmount
						.add(getTotalCostSharingAmountByRateClassTypeFromLineItem(
								budgetLineItem, RateClassType.LAB_ALLOCATION
										.getRateClassType()));
			}
			ReportType reportType = getReportTypeForNonPersonnel(categoryDesc,
					costElementDesc, calculatedCost, costSharingAmount);
			reportTypeList.add(reportType);
		}
		setReportTypeForBudgetSummaryNonPersonnel(reportTypeList);
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
	 * BudgetSummaryNonPersonnel from List of BudgetLineItem based on
	 * BudgetCategoryCode and gets sum of calculatedCost , costSharingAmount by
	 * grouping ReportType based on key budgetSummaryNonPersKey
	 */
	private void setReportTypeForBudgetSummaryNonPersonnel(
			List<ReportType> reportTypeList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (!isBudgetCategoryPersonnel(budgetLineItem)) {
				ReportTypeVO tempReportTypeVO = getReportTypeVOForBudgetSummaryNonPersonnel(budgetLineItem);
				tempReportTypeVOList.add(tempReportTypeVO);
			}
		}
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String budgetSummaryNonPersKey = reportTypeVO.getCostElementDesc();
			if (reportTypeMap.containsKey(budgetSummaryNonPersKey)) {
				continue;
			}
			BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
			BudgetDecimal costSharingAmount = BudgetDecimal.ZERO;
			for (ReportTypeVO reportTypeVO1 : tempReportTypeVOList) {
				String budgetSummaryNonPersTempKey = reportTypeVO1
						.getCostElementDesc();
				if (budgetSummaryNonPersTempKey.equals(budgetSummaryNonPersKey)) {
					calculatedCost = calculatedCost.add(reportTypeVO1
							.getCalculatedCost());
					costSharingAmount = costSharingAmount.add(reportTypeVO1
							.getCostSharingAmount());
				}
			}
			ReportType reportType = getReportTypeForBudgetSummaryNonPersonnel(
					calculatedCost, costSharingAmount, reportTypeVO);
			reportTypeMap.put(budgetSummaryNonPersKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	/*
	 * This method gets reportType for BudgetSummaryNonPersonnel by setting
	 * parameters data to reportType
	 */
	private ReportType getReportTypeForBudgetSummaryNonPersonnel(
			BudgetDecimal calculatedCost, BudgetDecimal costSharingAmount,
			ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setBudgetCategoryDescription(reportTypeVO
				.getBudgetCategoryDesc());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		reportType.setCostSharingAmount(costSharingAmount.doubleValue());
		return reportType;
	}

	/*
	 * This method gets reportTypeVO for BudgetSummaryNonPersonnel by setting
	 * parameters data to reportType
	 */
	private ReportTypeVO getReportTypeVOForBudgetSummaryNonPersonnel(
			BudgetLineItem budgetLineItem) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setBudgetCategoryDesc(budgetLineItem.getBudgetCategory()
				.getDescription());
		reportTypeVO
				.setCostElementDesc(getCostElementDescription(budgetLineItem));
		reportTypeVO
				.setCostSharingAmount(budgetLineItem.getCostSharingAmount());
		reportTypeVO.setCalculatedCost(budgetLineItem.getLineItemCost());
		return reportTypeVO;
	}
}
