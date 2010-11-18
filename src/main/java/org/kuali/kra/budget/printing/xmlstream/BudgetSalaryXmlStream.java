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

import noNamespace.BudgetSalaryDocument;
import noNamespace.SalaryType;
import noNamespace.BudgetSalaryDocument.BudgetSalary;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.printing.util.BudgetDataPeriodVO;
import org.kuali.kra.budget.printing.util.SalaryTypeVO;
import org.kuali.kra.document.ResearchDocumentBase;

/**
 * This class generates XML that conforms with the XSD related to Budget Salary
 * Report. The data for XML is derived from {@link ResearchDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class BudgetSalaryXmlStream extends BudgetBaseSalaryStream {

	private static final String BUDGET_SALARY = "Budget Salary";

	/**
	 * This method generates XML for Budget Salary Report. It uses data passed
	 * in {@link ResearchDocumentBase} for populating the XML nodes. The XMl
	 * once generated is returned as {@link XmlObject}
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
		BudgetSalaryDocument budgetSalaryDocument = BudgetSalaryDocument.Factory
				.newInstance();
		if (budget != null) {
			BudgetSalary budgetSalary = getSalaryType();
			budgetSalaryDocument.setBudgetSalary(budgetSalary);
			xmlObjectList.put(BUDGET_SALARY, budgetSalaryDocument);
		}

		return xmlObjectList;
	}

	/*
	 * This method will set the values to salary type attributes and finally
	 * return the array of Salary type
	 */
	protected BudgetSalary getSalaryType() {
		List<SalaryTypeVO> salaryTypeVoList = new ArrayList<SalaryTypeVO>();
		List<String> lineItems = getListOfCostElements();
		for (String costElemetDesc : lineItems) {
			setSalaryTypesForCostElement(costElemetDesc, salaryTypeVoList);
		}
		boolean includeNonPersonnel = false;
		setSalaryTypesForLineItemCalcuAmount(salaryTypeVoList,includeNonPersonnel);
		List<SalaryType> salaryTypeList = getListOfSalaryTypeXmlObjects(salaryTypeVoList);
		BudgetSalary budgetSalary = getBudgetSalaryTypeXmlObject();
		budgetSalary.setSalaryArray(salaryTypeList.toArray(new SalaryType[0]));
		return budgetSalary;
	}

	/*
	 * This method will get the unique list of cost elements.It is iterates over
	 * budget line items for each budget period of budget document and add the
	 * cost element description to list if cost element description not found in
	 * list
	 * 
	 */
	private List<String> getListOfCostElements() {
		List<String> lineItems = new ArrayList<String>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			this.budgetPeriod = budgetPeriod;
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				for (BudgetPersonnelDetails budgetPersonnelDetail : budgetLineItem
						.getBudgetPersonnelDetailsList()) {
					addCostElementDescriptionToList(lineItems,
							budgetPersonnelDetail, budgetLineItem);
				}
			}
		}
		return lineItems;
	}

	/*
	 * This method checks if cost element description not found in list then add
	 * it to list.
	 */
	private void addCostElementDescriptionToList(List<String> lineItems,
			BudgetPersonnelDetails budgetPersonnelDetail,
			BudgetLineItem budgetLineItem) {
		if (budgetLineItem.getCostElementBO() != null) {
			String budgetLineItemCostEleDesc = budgetLineItem
					.getCostElementBO().getDescription();
			if (!lineItems.contains(budgetLineItemCostEleDesc)) {
				lineItems.add(budgetLineItemCostEleDesc);
			}
		}
	}

	/*
	 * This method will return true if budget Personnel Cost Element Description
	 * is equal to budget LineItem Cost Element Description otherwise false
	 */
	private boolean isEqualToCostElementDescription(
			String budgetPeronnelCostEleDesc, String budgetLineItemCostEleDesc) {
		boolean isCostElementDescEqual = false;
		if (budgetPeronnelCostEleDesc != null
				&& budgetLineItemCostEleDesc != null) {
			isCostElementDescEqual = budgetPeronnelCostEleDesc
					.equals(budgetLineItemCostEleDesc);
		}
		return isCostElementDescEqual;
	}

	/*
	 * This method set the values to salary type attributes and add to the
	 * salary type list
	 */
	private void setSalaryTypesForCostElement(String costElementDesc,
			List<SalaryTypeVO> salaryTypeVoList) {
		Map<String, String> personMap = new HashMap<String, String>();
		SalaryTypeVO salaryTypeVO = new SalaryTypeVO();
		salaryTypeVO.setCostElement(costElementDesc);
		salaryTypeVoList.add(salaryTypeVO);
		List<String> persons = getPersonsForCostElementDescription(costElementDesc);
		for (String personName : persons) {
			if (personMap.containsKey(personName)) {
				continue;
			}
			SalaryTypeVO salaryTypeVoPerPerson = new SalaryTypeVO();
			salaryTypeVoPerPerson.setName(personName);
			salaryTypeVoPerPerson.setBudgetPeriodVOs(getBudgetDataPeriodVOs(
					costElementDesc, personName));
			salaryTypeVoList.add(salaryTypeVoPerPerson);
			personMap.put(personName, personName);
		}
	}

	/*
	 * This method will return the list budgetPeriod data for cost element and
	 * person name
	 */
	private List<BudgetDataPeriodVO> getBudgetDataPeriodVOs(
			String costElementDesc, String personName) {
		List<BudgetDataPeriodVO> budgetPeriodDataList = new ArrayList<BudgetDataPeriodVO>();
		int budgetPeriodDataId = 0;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			BudgetDataPeriodVO budgetPeriodVO = new BudgetDataPeriodVO();
			budgetPeriodVO.setBudgetPeriodId(++budgetPeriodDataId);
			BudgetDecimal periodCost = BudgetDecimal.ZERO;
			for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
				if (lineItem.getCostElementBO() != null
						&& isEqualToCostElementDescription(lineItem
								.getCostElementBO().getDescription(),
								costElementDesc)) {
					for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem
							.getBudgetPersonnelDetailsList()) {
						budgetPersonnelDetails.refreshNonUpdateableReferences();
						String budgetPersonName = budgetPersonnelDetails
								.getBudgetPerson().getPersonName();
						BudgetDecimal salaryRequested = budgetPersonnelDetails
								.getSalaryRequested();
						if (personName != null
								&& personName.equals(budgetPersonName)
								&& salaryRequested != null) {
							periodCost = periodCost.add(salaryRequested);
						}
					}
				}
			}
			budgetPeriodVO.setPeriodCost(periodCost);
			budgetPeriodDataList.add(budgetPeriodVO);
		}
		return budgetPeriodDataList;
	}

	/*
	 * This method will get the all persons belongs to this cost element. For
	 * given cost element description get the list of persons have these cost
	 * element by iterating over Budget Personnel Details of each budget period .
	 */
	private List<String> getPersonsForCostElementDescription(
			String costElementDesc) {
		List<String> persons = new ArrayList<String>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				if (budgetLineItem.getCostElementBO() != null
						&& isEqualToCostElementDescription(costElementDesc,
								budgetLineItem.getCostElementBO()
										.getDescription())) {
					for (BudgetPersonnelDetails budgetPersonnelDetail : budgetLineItem
							.getBudgetPersonnelDetailsList()) {
						budgetPersonnelDetail.refreshNonUpdateableReferences();
						String personName = budgetPersonnelDetail
								.getBudgetPerson().getPersonName();
						persons.add(personName);
					}
				}
			}
		}
		return persons;
	}
}
