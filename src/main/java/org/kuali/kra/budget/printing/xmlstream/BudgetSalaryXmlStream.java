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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.BudgetSalaryDocument;
import noNamespace.SalaryType;
import noNamespace.BudgetSalaryDocument.BudgetSalary;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryType;
import org.kuali.kra.budget.core.CostElement;
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
	
	private BudgetCalculationService budgetCalculationService;

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

	/**
	 * This method will set the values to salary type attributes and finally
	 * return the array of Salary type.
	 */
	protected BudgetSalary getSalaryType() {
		List<SalaryTypeVO> salaryTypeVoList = new ArrayList<SalaryTypeVO>();
		getBudgetCalculationService().calculateBudgetSummaryTotals(budget);
		for (Map.Entry<BudgetCategoryType, List<CostElement>> entry : budget.getObjectCodeListByBudgetCategoryType().entrySet()) {
		    if (isPersonnel(entry.getKey().getBudgetCategoryTypeCode())) {
		        for (CostElement costElement : entry.getValue()) {
		            addSalaryDataForCostElement(costElement, salaryTypeVoList);
		        }
		    }
		}
		boolean includeNonPersonnel = false;
		setSalaryTypesForLineItemCalcuAmount(salaryTypeVoList,includeNonPersonnel);
		List<SalaryType> salaryTypeList = getListOfSalaryTypeXmlObjects(salaryTypeVoList);
		BudgetSalary budgetSalary = getBudgetSalaryTypeXmlObject();
		budgetSalary.setSalaryArray(salaryTypeList.toArray(new SalaryType[0]));
		return budgetSalary;
	}
	
	private void addSalaryDataForCostElement(CostElement costElement, List<SalaryTypeVO> salaryTypeVoList) {
        SalaryTypeVO groupVO = new SalaryTypeVO();
        groupVO.setCostElement(costElement.getDescription());
        salaryTypeVoList.add(groupVO);
        for (BudgetPersonnelDetails details : budget.getObjectCodePersonnelList().get(costElement)) {
            SalaryTypeVO salaryTypeVoPerPerson = new SalaryTypeVO();
            salaryTypeVoPerPerson.setName(details.getBudgetPerson().getPersonName());
            salaryTypeVoPerPerson.setBudgetPeriodVOs(getBudgetPeriodData(
                    budget.getObjectCodePersonnelSalaryTotals().get(costElement.getCostElement() + "," + details.getPersonId())));
            salaryTypeVoList.add(salaryTypeVoPerPerson);
        }
        if (budget.getObjectCodePersonnelSalaryTotals().get(costElement.getCostElement()) != null) {
            SalaryTypeVO salaryTypeVoPerPerson = new SalaryTypeVO();
            salaryTypeVoPerPerson.setName("Summary Line Item");
            salaryTypeVoPerPerson.setBudgetPeriodVOs(
                    getBudgetPeriodData(budget.getObjectCodePersonnelSalaryTotals().get(costElement.getCostElement())));
            salaryTypeVoList.add(salaryTypeVoPerPerson);                        
        }	    
	}
	
	private List<BudgetDataPeriodVO> getBudgetPeriodData(List<BudgetDecimal> costs) {
        List<BudgetDataPeriodVO> budgetDataList = new ArrayList<BudgetDataPeriodVO>();
        int budgetPeriodId = 1;
        for (BudgetDecimal cost : costs) {
            BudgetDataPeriodVO periodData = new BudgetDataPeriodVO();
            periodData.setBudgetPeriodId(budgetPeriodId++);
            periodData.setPeriodCost(cost);
            budgetDataList.add(periodData);
        }
        return budgetDataList;
	}
	

    protected BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
}
