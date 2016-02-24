/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * This class generates XML that conforms with the XSD related to Budget Summary
 * Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * 
 */
@Component("budgetSummaryXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BudgetSummaryXmlStream extends BudgetBaseStream {

	private static final Log LOG = LogFactory
			.getLog(BudgetSummaryXmlStream.class);

    /**
	 * This method generates XML for Award Delta Report. It uses data passed in
	 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl once
	 * generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return Map consisting of XML Objects mapped to bookmarks
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectMap = new LinkedHashMap<String, XmlObject>();
		this.budget = (Budget) printableBusinessObject;
		if (budget != null) {
			for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
				if (!(budgetPeriod.getBudgetLineItems() != null && !budgetPeriod.getBudgetLineItems().isEmpty())) {
					LOG.debug("Skipping printing of empty budget period, for Budget period - "
                            + budgetPeriod.getBudgetPeriod());
					continue;
				}
				this.budgetPeriod = budgetPeriod;
				BudgetSummaryReportDocument budgetSummaryReportDocument = BudgetSummaryReportDocument.Factory.newInstance();
                BudgetSummaryReport budgetSummaryReport = getBudgetSummaryReport();
				budgetSummaryReportDocument.setBudgetSummaryReport(budgetSummaryReport);
				xmlObjectMap.put(BUDGET_PERIOD + budgetPeriod.getBudgetPeriod(), budgetSummaryReportDocument);
			}
		}
		return xmlObjectMap;
	}

	private BudgetSummaryReport getBudgetSummaryReport() {
		BudgetSummaryReport budgetSummaryReport = BudgetSummaryReport.Factory.newInstance();
		ReportPageType cumilativePageType = ReportPageType.Factory.newInstance();
		BudgetParent budgetParent = budget.getBudgetParent();
		ReportHeaderType reportHeaderType = getReportHeaderTypeForCumulativeReport(budgetParent);
		budgetSummaryReport.setReportHeader(reportHeaderType);
		cumilativePageType = getBudgetSummaryReportPageType();
		budgetSummaryReport.setCumilativePage(cumilativePageType);
		ReportPageType[] reportPageTypeArray = getReportPageTypes();
		budgetSummaryReport.setReportPageArray(reportPageTypeArray);
		return budgetSummaryReport;
	}

	private ReportPageType[] getReportPageTypes() {
		ReportPageType reportPageType;
		List<ReportPageType> reportPageTypeList = new ArrayList<>();
		reportPageType = getBudgetSummaryReportPageType();
		reportPageTypeList.add(reportPageType);
		return reportPageTypeList.toArray(new ReportPageType[0]);
	}

	private ReportPageType getBudgetSummaryReportPageType() {
		ReportPageType reportPageType = ReportPageType.Factory.newInstance();
		BudgetSummary budgetSummary = getBudgetSummary();
		reportPageType.setBudgetSummary(budgetSummary);
		CalculationMethodology calculationMethodology = getCalculationMethodology();
		reportPageType.setCalculationMethodology(calculationMethodology);
		reportPageType.setPeriod(budgetPeriod.getBudgetPeriod());
		return reportPageType;
	}

	private BudgetSummary getBudgetSummary() {
		BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
		SubReportType subReportType;

		subReportType = getSalarySummary();
		budgetSummary.setSalarySummaryFromEDI(subReportType);

		subReportType = getBudgetSummaryNonPersonnel();
		budgetSummary.setBudgetSummaryNonPersonnel(subReportType);

		subReportType = getBudgetIndirectCostsForReport();
		budgetSummary.setBudgetIndirectCostsForReport(subReportType);

		budgetSummary.setTotalDirectCost(budgetPeriod.getTotalDirectCost().doubleValue());
		budgetSummary.setTotalCostToSponsor(budgetPeriod.getTotalCost().doubleValue());
		budgetSummary.setTotalUnderrecoveryAmount(budgetPeriod.getUnderrecoveryAmount().doubleValue());
		budgetSummary.setTotalCostSharingAmount(budgetPeriod.getCostSharingAmount().doubleValue());

		return budgetSummary;
	}

	private SubReportType getSalarySummary() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<>();
		setReportTypeForBudgetSalarySummary(reportTypeList);
		setBudgetLASalaryForBudgetRateAndBase(reportTypeList, new ArrayList<ReportTypeVO>());
		subReportType.setGroupArray(getGroupsType(reportTypeList, category));
		return subReportType;
	}

	private void setReportTypeForBudgetSalarySummary(List<ReportType> reportTypeList) {
		setReportTypeListFromReportTypeVOListForBudgetSalarySummary(reportTypeList, getReportTypeVOList(budgetPeriod));
	}

	private void setReportTypeListFromReportTypeVOListForBudgetSalarySummary(List<ReportType> reportTypeList, List<ReportTypeVO> reportTypeVOList) {
		Map<String, ReportTypeVO> reportTypeMap = new HashMap<>();
		for (ReportTypeVO reportTypeVO : reportTypeVOList) {
			String budgetSalarySummaryKey = getKeyForBudgetSalarySummary(reportTypeVO);
			if (reportTypeMap.containsKey(budgetSalarySummaryKey)) {
				continue;
			}
			ScaleTwoDecimal vacationRate = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal empBenefitRate = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal fringeCostSharing = ScaleTwoDecimal.ZERO;
			for (ReportTypeVO tempReportTypeVO : reportTypeVOList) {
				String budgetSalarySummaryTempKey = getKeyForBudgetSalarySummary(tempReportTypeVO);
				if (budgetSalarySummaryTempKey.equals(budgetSalarySummaryKey)) {
					if (vacationRate.isLessThan(tempReportTypeVO.getVacationRate())) {
						vacationRate = tempReportTypeVO.getVacationRate();
					}
					if (empBenefitRate.isLessThan(tempReportTypeVO.getEmployeeBenefitRate())) {
						empBenefitRate = tempReportTypeVO.getEmployeeBenefitRate();
					}
					fringe = fringe.add(tempReportTypeVO.getFringe());
					fringeCostSharing = fringeCostSharing.add(tempReportTypeVO.getCalculatedCost());
				}
			}
			ReportType reportType = getReportTypeForBudgetSalarySummary(vacationRate, empBenefitRate, fringe, fringeCostSharing, reportTypeVO);
			reportTypeMap.put(budgetSalarySummaryKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	private ReportType getReportTypeForBudgetSalarySummary(ScaleTwoDecimal vacationRate, ScaleTwoDecimal empBenefitRate,
                                                           ScaleTwoDecimal fringe, ScaleTwoDecimal fringeCostSharing, ReportTypeVO reportTypeVO) {
		SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT_MMDDYY);
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setStartDate(dateFormat.format(reportTypeVO.getStartDate()));
		reportType.setEndDate(dateFormat.format(reportTypeVO.getEndDate()));
		reportType.setBudgetCategoryDescription( reportTypeVO.getBudgetCategoryDesc());
		reportType.setPersonName(reportTypeVO.getPersonName());
		reportType.setPercentEffort(reportTypeVO.getPercentEffort() != null ? reportTypeVO.getPercentEffort().doubleValue() : 0.00);
		reportType.setPercentCharged(reportTypeVO.getPercentCharged() != null ? reportTypeVO.getPercentCharged().doubleValue() : 0.00);
		reportType.setVacationRate(vacationRate.toString().concat(PERCENTAGE));
		reportType.setEmployeeBenefitRate(empBenefitRate.toString().concat(PERCENTAGE));
		reportType.setCostSharingAmount(reportTypeVO.getCostSharingAmount().doubleValue());
		reportType.setCalculatedCost(fringeCostSharing.doubleValue());
		reportType.setFringe(fringe.doubleValue());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setInvestigatorFlag(reportTypeVO.getInvestigatorFlag());
		if (reportTypeVO.getBudgetCategoryCode() != null) {
			reportType.setBudgetCategoryCode(Integer.parseInt(reportTypeVO.getBudgetCategoryCode()));
		}
		reportType.setSalaryRequested(reportTypeVO.getSalaryRequested().doubleValue());
		return reportType;
	}

	private SubReportType getBudgetSummaryNonPersonnel() {
		SubReportType subReportType = SubReportType.Factory.newInstance();
		List<ReportType> reportTypeList = new ArrayList<ReportType>();
		ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
		if (getUnitNumber() > 0) {
			String categoryDesc = OTHER_DIRECT_COSTS;
			String costElementDesc = ALLOCATED_LAB_EXPENSE;
			for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
				calculatedCost = calculatedCost.add(getTotalCalculatedCostByRateClassTypeFromLineItem(RateClassType.LAB_ALLOCATION.getRateClassType(), budgetLineItem));
				costSharingAmount = costSharingAmount.add(getTotalCostSharingAmountByRateClassTypeFromLineItem(budgetLineItem, RateClassType.LAB_ALLOCATION.getRateClassType()));
			}
			ReportType reportType = getReportTypeForNonPersonnel(categoryDesc, costElementDesc, calculatedCost, costSharingAmount);
			 if(calculatedCost.doubleValue()>0.0d){
				 reportTypeList.add(reportType);
			 }
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

	private void setReportTypeForBudgetSummaryNonPersonnel(List<ReportType> reportTypeList) {
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
			ScaleTwoDecimal calculatedCost = ScaleTwoDecimal.ZERO;
			ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;
			for (ReportTypeVO reportTypeVO1 : tempReportTypeVOList) {
				String budgetSummaryNonPersTempKey = reportTypeVO1.getCostElementDesc();
				if (budgetSummaryNonPersTempKey.equals(budgetSummaryNonPersKey)) {
					calculatedCost = calculatedCost.add(reportTypeVO1.getCalculatedCost());
					costSharingAmount = costSharingAmount.add(reportTypeVO1.getCostSharingAmount());
				}
			}

			ReportType reportType = getReportTypeForBudgetSummaryNonPersonnel(calculatedCost, costSharingAmount, reportTypeVO);
			reportTypeMap.put(budgetSummaryNonPersKey, reportTypeVO);
			reportTypeList.add(reportType);
		}
	}

	private ReportType getReportTypeForBudgetSummaryNonPersonnel(ScaleTwoDecimal calculatedCost, ScaleTwoDecimal costSharingAmount, ReportTypeVO reportTypeVO) {
		ReportType reportType = ReportType.Factory.newInstance();
		reportType.setBudgetCategoryDescription(reportTypeVO.getBudgetCategoryDesc());
		reportType.setCostElementDescription(reportTypeVO.getCostElementDesc());
		reportType.setCalculatedCost(calculatedCost.doubleValue());
		reportType.setCostSharingAmount(costSharingAmount.doubleValue());
		return reportType;
	}

	private ReportTypeVO getReportTypeVOForBudgetSummaryNonPersonnel(BudgetLineItem budgetLineItem) {
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setBudgetCategoryDesc(budgetLineItem.getBudgetCategory().getDescription());
		reportTypeVO.setCostElementDesc(getCostElementDescription(budgetLineItem));
		reportTypeVO.setCostSharingAmount(budgetLineItem.getCostSharingAmount());
		reportTypeVO.setCalculatedCost(budgetLineItem.getLineItemCost());
		return reportTypeVO;
	}
}
