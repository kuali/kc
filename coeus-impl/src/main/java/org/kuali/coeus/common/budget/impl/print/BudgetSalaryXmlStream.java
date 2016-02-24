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

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.kra.printing.schema.BudgetSalaryDocument;
import org.kuali.kra.printing.schema.BudgetSalaryDocument.BudgetSalary;
import org.kuali.kra.printing.schema.SalaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class generates XML that conforms with the XSD related to Budget Salary
 * Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
@Component("budgetSalaryXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BudgetSalaryXmlStream extends BudgetBaseSalaryStream {

	private static final String BUDGET_SALARY = "Budget Salary";

    @Autowired
    @Qualifier("budgetCalculationService")
	private BudgetCalculationService budgetCalculationService;

    /**
	 * This method generates XML for Budget Salary Report. It uses data passed
	 * in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl
	 * once generated is returned as {@link XmlObject}
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
		    if (isPersonnel(entry.getKey().getCode())) {
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
	
	private List<BudgetDataPeriodVO> getBudgetPeriodData(List<ScaleTwoDecimal> costs) {
        List<BudgetDataPeriodVO> budgetDataList = new ArrayList<BudgetDataPeriodVO>();
        int budgetPeriodId = 1;
        for (ScaleTwoDecimal cost : costs) {
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
