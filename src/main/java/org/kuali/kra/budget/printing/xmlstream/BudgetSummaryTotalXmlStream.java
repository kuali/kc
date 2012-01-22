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

import java.util.LinkedHashMap;
import java.util.Map;

import noNamespace.BudgetSalaryDocument;
import noNamespace.BudgetSalaryDocument.BudgetSalary;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.document.ResearchDocumentBase;

/**
 * This class generates XML that conforms with the XSD related to Budget Summary
 * total Report. The data for XML is derived from {@link ResearchDocumentBase}
 * and {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class BudgetSummaryTotalXmlStream extends BudgetBaseSalaryStream {
	private static final String BUDGET_SUMMARY_TOTAL = "Budget Summary Total";

	/**
	 * This method generates XML for Budget summary total Report. It uses data
	 * passed in {@link ResearchDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
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
