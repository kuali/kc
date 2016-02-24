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
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.printing.schema.BudgetSalaryDocument;
import org.kuali.kra.printing.schema.BudgetSalaryDocument.BudgetSalary;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class generates XML that conforms with the XSD related to Budget Summary
 * total Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase}
 * and {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
@Component("budgetSummaryTotalXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BudgetSummaryTotalXmlStream extends BudgetBaseSalaryStream {
	private static final String BUDGET_SUMMARY_TOTAL = "Budget Summary Total";

	/**
	 * This method generates XML for Budget summary total Report. It uses data
	 * passed in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 *
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
			BudgetSalary budgetSalary = getBudgetSalary();
			budgetSalaryDocument.setBudgetSalary(budgetSalary);
			xmlObjectList.put(BUDGET_SUMMARY_TOTAL, budgetSalaryDocument);
		}
		return xmlObjectList;
	}

	/*
	 * This method set the values to budget salary attributes and return the
	 * budget salary type
	 */
	private BudgetSalary getBudgetSalary() {
		BudgetSalary budgetSalary = getBudgetSalaryTypeXmlObject();
		budgetSalary.setHeaderTitle(BUDGET_SUMMARY_TOTAL);
		budgetSalary.setSalaryArray(getBudgetTotalAndSummarySalaryTypes(true));
		return budgetSalary;
	}
}
