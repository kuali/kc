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
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.printing.schema.BudgetSalaryDocument;
import org.kuali.kra.printing.schema.BudgetSalaryDocument.BudgetSalary;
import org.kuali.kra.printing.schema.SalaryType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.crypto.dsig.XMLObject;
import java.util.*;

/**
 * This class generates XML that conforms with the XSD related to Industrial
 * cumulative Budget Report. The data for XML is derived from
 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
@Component("industrialCumBudgetXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IndustrialCumBudgetXmlStream extends BudgetBaseSalaryStream {
	private static final String CUM_INDSTRL_BUDGET = "Cumulative Industrial Budget";

   /**
	 * This method generates XML for Industrial cumulative Budget Report. It
	 * uses data passed in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML
	 * nodes. The XMl once generated is returned as {@link XMLObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XMLObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
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
			ScaleTwoDecimal periodCost = ScaleTwoDecimal.ZERO;
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
	private ScaleTwoDecimal getBudgetDataPeriodsForCalculatedAmountsForIndustrialCumBudget(
			BudgetLineItem budgetLineItem) {
		ScaleTwoDecimal periodCost = ScaleTwoDecimal.ZERO;
		for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem
				.getBudgetLineItemCalculatedAmounts()) {
			periodCost = periodCost.add(budgetLineItemCalcAmount
					.getCalculatedCost());

		}
		return periodCost;
	}
}
