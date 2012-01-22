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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.dsig.XMLObject;

import noNamespace.BudgetSalaryDocument;
import noNamespace.SalaryType;
import noNamespace.BudgetSalaryDocument.BudgetSalary;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.printing.util.BudgetDataPeriodVO;
import org.kuali.kra.budget.printing.util.SalaryTypeVO;
import org.kuali.kra.document.ResearchDocumentBase;

/**
 * This class generates XML that conforms with the XSD related to Industrial
 * cumulative Budget Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class IndustrialCumBudgetXmlStream extends BudgetBaseSalaryStream {
	private static final String CUM_INDSTRL_BUDGET = "Cumulative Industrial Budget";

	/**
	 * This method generates XML for Industrial cumulative Budget Report. It
	 * uses data passed in {@link ResearchDocumentBase} for populating the XML
	 * nodes. The XMl once generated is returned as {@link XMLObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XMLObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		this.budget = (Budget) printableBusinessObject;
		BudgetSalaryDocument budgetSalaryDocument = BudgetSalaryDocument.Factory
				.newInstance();
		if (budget != null) {
			BudgetSalary budgetSalary = getBudgetSalary();
			budgetSalaryDocument.setBudgetSalary(budgetSalary);
		}
		xmlObjectList.put(CUM_INDSTRL_BUDGET, budgetSalaryDocument);
		return xmlObjectList;
	}

	/*
	 * This method set the values to budget salary attributes and return the
	 * budget salary type
	 */
	private BudgetSalary getBudgetSalary() {
		BudgetSalary budgetSalary = getBudgetSalaryTypeXmlObject();
		budgetSalary.setHeaderTitle(CUM_INDSTRL_BUDGET);
		budgetSalary.setSalaryArray(getIndustrialCumBudgetSalaryTypes());
		return budgetSalary;
	}

	/*
	 * This method will set the values to SalaryType xml object and finally
	 * returns as a array of SalaryType xml object.
	 */
	private SalaryType[] getIndustrialCumBudgetSalaryTypes() {
		List<SalaryTypeVO> salaryTypeVoList = new ArrayList<SalaryTypeVO>();
		Map<String, String> lineItems = new HashMap<String, String>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				if (budgetLineItem.getCostElementBO() != null) {
					String budgetPeronnelCostEleDesc = budgetLineItem
							.getCostElementBO().getDescription();
					if (budgetPeronnelCostEleDesc != null
							&& !lineItems
									.containsKey(budgetPeronnelCostEleDesc)
							&& budgetLineItem.getCostElementBO()
									.getCostElement() != null) {
						lineItems.put(budgetPeronnelCostEleDesc, budgetLineItem
								.getCostElementBO().getCostElement());
					}
				}
			}
		}
		for (String costElemetDesc : lineItems.keySet()) {
			salaryTypeVoList
					.add(getSalaryTypeVOForCostElementForIndustrialCumBudget(
							costElemetDesc, lineItems.get(costElemetDesc)));
		}
		List<SalaryType> salaryTypeList = getListOfSalaryTypeXmlObjects(salaryTypeVoList);
		return salaryTypeList.toArray(new SalaryType[0]);
	}

	/*
	 * This method gets SalaryTypeVO for costElement. For given cost element
	 * description get's list of budget period data's and finally set to
	 * SalaryTypeVO;
	 * 
	 */
	private SalaryTypeVO getSalaryTypeVOForCostElementForIndustrialCumBudget(
			String costElemetDesc, String costElementCode) {
		SalaryTypeVO salaryTypeVO = new SalaryTypeVO();
		salaryTypeVO.setCostElement(costElemetDesc);
		salaryTypeVO.setCostElementCode(costElementCode);
		salaryTypeVO.setName(costElemetDesc);
		salaryTypeVO
				.setBudgetPeriodVOs(getBudgetDataPeriodVOsForCostElementForIndustrialCumBudget(costElementCode));
		return salaryTypeVO;
	}

	/*
	 * This method will get the budget data periods . For a given cost element
	 * from budget get list of budget periods , iterate over budget periods and
	 * get list of budget line items ,iterate over budget line items compare
	 * with the cost element which ever matches add line item cost to period
	 * cost.finally set period cost to BudgetDataPeriodVO
	 * 
	 */
	private List<BudgetDataPeriodVO> getBudgetDataPeriodVOsForCostElementForIndustrialCumBudget(
			String costElementCode) {
		List<BudgetDataPeriodVO> budgetPeriodDataList = new ArrayList<BudgetDataPeriodVO>();
		int budgetPeriodDataId = 0;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			BudgetDataPeriodVO budgetDataPeriodVO = new BudgetDataPeriodVO();
			budgetDataPeriodVO.setBudgetPeriodId(++budgetPeriodDataId);
			BudgetDecimal periodCost = BudgetDecimal.ZERO;
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				if (budgetLineItem.getCostElementBO().getCostElement().equals(
						costElementCode)) {
					periodCost = periodCost.add(budgetLineItem
							.getLineItemCost());
					periodCost = periodCost
							.add(getBudgetDataPeriodsForCalculatedAmountsForIndustrialCumBudget(budgetLineItem));

				}
			}
			budgetDataPeriodVO.setPeriodCost(periodCost);
			budgetPeriodDataList.add(budgetDataPeriodVO);
		}
		return budgetPeriodDataList;
	}

	/*
	 * This method get sum of calculated cost from list of
	 * BudgetLineItemCalculatedAmount
	 */
	private BudgetDecimal getBudgetDataPeriodsForCalculatedAmountsForIndustrialCumBudget(
			BudgetLineItem budgetLineItem) {
		BudgetDecimal periodCost = BudgetDecimal.ZERO;
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			periodCost = periodCost.add(budgetLineItemCalcAmount
					.getCalculatedCost());

		}
		return periodCost;
	}
}
