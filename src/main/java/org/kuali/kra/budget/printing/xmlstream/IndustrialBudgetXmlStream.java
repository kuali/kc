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

import java.sql.Date;
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

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetRateAndBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.printing.util.ReportTypeVO;
import org.kuali.kra.document.ResearchDocumentBase;

/**
 * This class generates XML that conforms with the XSD related to Budget Summary
 * Report. The data for XML is derived from {@link ResearchDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class IndustrialBudgetXmlStream extends BudgetBaseStream {

	private static final Log LOG = LogFactory
			.getLog(IndustrialBudgetXmlStream.class);

	/**
	 * This method generates XML for Award Delta Report. It uses data passed in
	 * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
	 * generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		this.budget = (Budget) printableBusinessObject;
		if (budget != null) {
			for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
				if (!(budgetPeriod.getBudgetLineItems() != null && !budgetPeriod
						.getBudgetLineItems().isEmpty())) {
					LOG.debug("Skipping printing of empty budget period, for Budget period - "+ budgetPeriod.getBudgetPeriod());
					continue;
				}
				this.budgetPeriod = budgetPeriod;
				BudgetSummaryReport budgetSummaryReport = BudgetSummaryReport.Factory.newInstance();
				BudgetSummaryReportDocument budgetSummaryReportDocument = BudgetSummaryReportDocument.Factory.newInstance();
				budgetSummaryReport = getIndustrialBudgetReport();
				budgetSummaryReportDocument.setBudgetSummaryReport(budgetSummaryReport);
				xmlObjectList.put(BUDGET_PERIOD+ budgetPeriod.getBudgetPeriod(),budgetSummaryReportDocument);
			}
		}
		return xmlObjectList;
	}

	/*
	 * This method gets IndustrialBudgetReport for budgetPeriods, Used to gets
	 * ReportHeaderType from latest budgetPeriod and gets sets reportHeader,
	 * cumulativePage, reportPageArray to budgetSummaryReport
	 */
	private BudgetSummaryReport getIndustrialBudgetReport() {

		BudgetSummaryReport budgetSummaryReport = BudgetSummaryReport.Factory.newInstance();
		ReportPageType cumilativePageType = ReportPageType.Factory.newInstance();
		BudgetParent budgetParent = budget.getBudgetParent();
		ReportHeaderType reportHeaderType = getReportHeaderTypeForCumulativeReport(budgetParent);
		budgetSummaryReport.setReportHeader(reportHeaderType);
		cumilativePageType = getIndustrialBudgetReportPageType();
		budgetSummaryReport.setCumilativePage(cumilativePageType);
		ReportPageType[] reportPageTypeArray = getReportPageTypes();
		budgetSummaryReport.setReportPageArray(reportPageTypeArray);

		return budgetSummaryReport;
	}

	/*
	 * This method gets array of ReportPageType based for
	 * BudgetPeriod.ReportPageType is created for each BudgetPeriod and then
	 * reportPageType add to ReportPageTypeList
	 */
	private ReportPageType[] getReportPageTypes() {
		ReportPageType reportPageType;
		List<ReportPageType> reportPageTypeList = new ArrayList<ReportPageType>();
		reportPageType = getIndustrialBudgetReportPageType();
		reportPageTypeList.add(reportPageType);
		return reportPageTypeList.toArray(new ReportPageType[0]);
	}

	/*
	 * This method gets RaportPageType for BudgetPeriod. BudgetSummary,
	 * CalculationMethodology and period are set to ReportPageType
	 */
	private ReportPageType getIndustrialBudgetReportPageType() {

		ReportPageType reportPageType = ReportPageType.Factory.newInstance();
		BudgetSummary budgetSummary = getIndustrialBudget();
		CalculationMethodology calculationMethodology = getCalculationMethodology();

		reportPageType.setBudgetSummary(budgetSummary);
		reportPageType.setCalculationMethodology(calculationMethodology);
		reportPageType.setPeriod(budgetPeriod.getBudgetPeriod());
		return reportPageType;
	}

	/*
	 * This method gets BudgetSummary for BudgetPeriod and gets sets
	 * IndustrialSalarySummary, IndustrialBudgetSummaryNonPersonnel,
	 * BudgetIndirectCost, totalDirectCost, TotalCostTOSponsor,
	 * TotalUnderrecoveryAmount, TotalCostSharingAmount to budgetSummary
	 */
	private BudgetSummary getIndustrialBudget() {
		BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
		SubReportType subReportType = SubReportType.Factory.newInstance();

		subReportType = getIndustrialSalarySummary();
		budgetSummary.setSalarySummaryFromEDI(subReportType);

		subReportType = getIndustrialBudgetSummaryNonPersonnel();
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
	private SubReportType getIndustrialSalarySummary() {

		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		setReportTypeForIndustrialBudgetSalary(reportTypeList);
		setIndustrialBudgetLASalaryForBudgetRateAndBase(reportTypeList);
		subReportType.setGroupArray(getGroupsType(reportTypeList, category));
		return subReportType;
	}

	/*
	 * This method sets reportType for IndustrialBudgetSalary from list of
	 * BudgetPersonnelDetails based on RateClassCode and RateTypeCode which
	 * iterate for each BudgetLineItem
	 */
	private void setReportTypeForIndustrialBudgetSalary(
			List<ReportType> reportTypeList) {
		setReportTypeListFromReportTypeVOListForIndustrialBudgetSalary(
				reportTypeList, getReportTypeVOList(budgetPeriod));
	}
	
	@Override
	/**
	 * Overridden method from BudgetBaseStream to filter for different rates.
	 */
    protected List<AbstractBudgetRateAndBase> getRatesApplicableToVOList(List<? extends AbstractBudgetRateAndBase> rates) {
        List<AbstractBudgetRateAndBase> result = new ArrayList<AbstractBudgetRateAndBase>();
        for (AbstractBudgetRateAndBase rate : rates) {
            if (!(isRateAndBaseOfRateClassTypeEB(rate) || isRateAndBaseOfRateClassTypeVacation(rate) 
                    || isRateAndBaseOfRateClassTypeLAwithEBVA(rate))) {
                result.add(rate);
            }
        }
        return result;
    }	

	/*
	 * This method sets reportTypeVO and add it to reportTypeVOList from list of
	 * BudgetLineItem and iterate through BudgetRateAndBase for BudgetLASalary
	 * based on RateClassType VACATION_ON_LA
	 * 
	 */
	private void setIndustrialBudgetLASalaryForBudgetRateAndBase(
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
					ReportTypeVO reportTypeVO = getReportTypeVOForIndustrialBudgetLASalaryForRateBase(
							budgetLineItem, budgetRateAndBase);
					reportTypeVOList.add(reportTypeVO);
					laRateBaseMap.put(key, budgetRateAndBase);
				}
			}
		}
		setReportTypeBudgetLASalary(reportTypeList, reportTypeVOList);
	}

	/*
	 * This method returns List of ReportTypeVO for BudgetLASalaryForRateAndBase
	 * by setting data from budgetLineItem and BudgetRateAndBase
	 * 
	 */
	private ReportTypeVO getReportTypeVOForIndustrialBudgetLASalaryForRateBase(
			BudgetLineItem budgetLineItem, BudgetRateAndBase budgetRateAndBase) {

		ReportTypeVO reportTypeVO = getReportTypeVOForBudgetLASalaryForRateBase(
				budgetLineItem, budgetRateAndBase);

		reportTypeVO.setSalaryRequested(reportTypeVO.getSalaryRequested().add(
				reportTypeVO.getFringe()));
		return reportTypeVO;
	}

	/*
	 * This method sets ReportTypeList from reportTypeVO by grouping
	 * reportTypeVO using reportTypeKey and get sum of salary
	 */
	private void setReportTypeListFromReportTypeVOListForIndustrialBudgetSalary(
			List<ReportType> reportTypeList, List<ReportTypeVO> reportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		for (ReportTypeVO reportTypeVO : reportTypeVOList) {
			String reportTypeKey = getKeyForIndustrialBudget(reportTypeVO);
			if (reportTypeMap.containsKey(reportTypeKey)) {
				continue;
			}
			BudgetDecimal salary = BudgetDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : reportTypeVOList) {
				String reportTypeTempKey = getKeyForIndustrialBudget(tempReportTypeVO);
				if (reportTypeTempKey.equals(reportTypeKey)) {
					salary = salary.add(tempReportTypeVO.getCalculatedCost());
				}
			}
			ReportType reportType = getReportTypeForIndustrialBudgetSalary(
					salary, reportTypeVO);
			reportTypeMap.put(reportTypeKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	/*
	 * This method gets reportType for BudgetSalarySummary by setting data to
	 * reportType from passed parameters
	 */
	private ReportType getReportTypeForIndustrialBudgetSalary(
			BudgetDecimal salary, ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setStartDate(DateFormatUtils.format(reportTypeVO.getStartDate(), DATE_FORMAT_MMDDYY));
		reportType.setEndDate(DateFormatUtils.format(reportTypeVO.getEndDate(), DATE_FORMAT_MMDDYY));
		reportType.setBudgetCategoryDescription(reportTypeVO.getBudgetCategoryDesc());
		reportType.setPersonName(reportTypeVO.getPersonName());
        reportType.setPercentEffort(reportTypeVO.getPercentEffort() != null ? reportTypeVO.getPercentEffort().doubleValue() : 0.00);
        reportType.setPercentCharged(reportTypeVO.getPercentCharged() != null ? reportTypeVO.getPercentCharged().doubleValue() : 0.00);
		if (reportTypeVO.getBudgetCategoryCode() != null) {
			reportType.setBudgetCategoryCode(Integer.parseInt(reportTypeVO.getBudgetCategoryCode()));
		}
		salary = salary.add(reportTypeVO.getSalaryRequested());
		reportType.setSalaryRequested(salary.doubleValue());
		reportType.setInvestigatorFlag(reportTypeVO.getInvestigatorFlag());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		return reportType;
	}

	/*
	 * This method gets key for Industrial Budget. Used to Group data based on
	 * key
	 * 
	 */
	private String getKeyForIndustrialBudget(ReportTypeVO reportTypeVO) {
		StringBuilder key = new StringBuilder();
		key.append(reportTypeVO.getStartDate().toString()).append(
				reportTypeVO.getEndDate().toString()).append(
				reportTypeVO.getPersonName()).append(
				reportTypeVO.getInvestigatorFlag().toString()).append(
				reportTypeVO.getBudgetCategoryCode()).append(
				reportTypeVO.getBudgetCategoryDesc());
		return key.toString();
	}

	/*
	 * This method gets subReportType for IndustrialBudgetSummaryNonPersonnel
	 * from List of BudgetLineItems by checking the UnitNumber
	 */
	private SubReportType getIndustrialBudgetSummaryNonPersonnel() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
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

			}
			ReportType reportType = getReportTypeForNonPersonnel(categoryDesc,
					costElementDesc, calculatedCost, null);
			reportTypeList.add(reportType);
		}
		setReportTypeForIndustrialBudgetNonPersonnel(reportTypeList);
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
	 * IndustrialBudgetNonPersonnel from List of BudgetLineItem based on
	 * BudgetCategoryCode and gets sum of calculatedCost , costSharingAmount by
	 * grouping ReportType based on key industrialBudgetNonPersKey
	 */
	private void setReportTypeForIndustrialBudgetNonPersonnel(
			List<ReportType> reportTypeList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<String, ReportTypeVO>();
		List<ReportTypeVO> tempReportTypeVOList = new ArrayList<ReportTypeVO>();
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (!isBudgetCategoryPersonnel(budgetLineItem)
					&& !checkLineItemNumberAvailableForIndustrialNonPersonnel(budgetLineItem)) {
				ReportTypeVO tempReportTypeVO = getReportTypeVOForIndustrialNonPersonnel(budgetLineItem);
				tempReportTypeVOList.add(tempReportTypeVO);
			}
		}
		for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
			if (!isBudgetCategoryPersonnel(budgetLineItem)
					&& isOverhead(budgetLineItem)) {
				ReportTypeVO tempReportTypeVO = getReportTypeVOForIndustrialNonPersonnel(budgetLineItem);
				tempReportTypeVOList.add(tempReportTypeVO);
			}
		}
		for (ReportTypeVO reportTypeVO : tempReportTypeVOList) {
			String industrialBudgetNonPersKey = reportTypeVO
					.getCostElementDesc();
			if (reportTypeMap.containsKey(industrialBudgetNonPersKey)) {
				continue;
			}
			BudgetDecimal calculatedCost = BudgetDecimal.ZERO;
			for (ReportTypeVO reportTypeVO1 : tempReportTypeVOList) {
				String industrialBudgetNonPersTempKey = reportTypeVO1
						.getCostElementDesc();
				if (industrialBudgetNonPersTempKey
						.equals(industrialBudgetNonPersKey)) {
					calculatedCost = calculatedCost.add(reportTypeVO1
							.getCalculatedCost());
				}
			}
			ReportType reportType = getReportTypeForIndustrialNonPersonnel(
					calculatedCost, reportTypeVO);
			reportTypeMap.put(industrialBudgetNonPersKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	/*
	 * This method gets reportType for IndustrialNonPersonnel by setting
	 * parameters data to reportType
	 */
	private ReportType getReportTypeForIndustrialNonPersonnel(
			BudgetDecimal calculatedCost, ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setBudgetCategoryDescription(reportTypeVO
				.getBudgetCategoryDesc());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		return reportType;
	}

	/*
	 * This method gets reportTypeVO for IndustrialNonPersonnel by setting
	 * parameters data to reportType
	 */
	private ReportTypeVO getReportTypeVOForIndustrialNonPersonnel(
			BudgetLineItem budgetLineItem) {
		ReportTypeVO tempReportTypeVO = new ReportTypeVO();
		tempReportTypeVO.setBudgetCategoryDesc(budgetLineItem
				.getBudgetCategory().getDescription());
		tempReportTypeVO
				.setCostElementDesc(getCostElementDescription(budgetLineItem));
		tempReportTypeVO.setCalculatedCost(budgetLineItem.getLineItemCost());
		return tempReportTypeVO;
	}

	/*
	 * This method check rateClassType is overhead or not in List of
	 * BudgetLineItemCalculatedAmount
	 */
	private boolean isOverhead(BudgetLineItem budgetLineItem) {
		boolean availabe = false;
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			if (budgetLineItemCalcAmount.getApplyRateFlag()
					&& isLineItemCalAmountOfRateClassTypeOverhead(budgetLineItemCalcAmount)) {
				availabe = true;
				break;
			}
		}
		return availabe;
	}

	/*
	 * This method check lineItemNumber available in List of
	 * BudgetLineItemCalculatedAmounts or not for IndustrialNonPersonnel
	 */
	private boolean checkLineItemNumberAvailableForIndustrialNonPersonnel(
			BudgetLineItem budgetLineItem) {
		boolean availabe = false;
		Integer lineItemNumber = budgetLineItem.getLineItemNumber();
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			if (isLineItemCalAmountOfRateClassTypeOverhead(budgetLineItemCalcAmount)
					&& budgetLineItemCalcAmount.getLineItemNumber().equals(
							lineItemNumber)) {
				availabe = true;
			}
		}
		return availabe;
	}
}
