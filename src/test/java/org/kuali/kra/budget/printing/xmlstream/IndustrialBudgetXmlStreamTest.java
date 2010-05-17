/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.printing.xmlstream;

import java.util.Map;

import noNamespace.BudgetSummaryReportDocument;
import noNamespace.ReportPageType;

import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.printing.util.XmlStreamTestBase;

/**
 * 
 * This class tests generation and validation of XML for Industrial Budget
 * Report
 * 
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class IndustrialBudgetXmlStreamTest extends
		XmlStreamTestBase<IndustrialBudgetXmlStream> {

	@Override
	protected Map<String, Object> getReportParameters() {
		return PrintingTestUtils.getIndustrialBudgetReportParameters();
	}

	@Override
	protected Class<IndustrialBudgetXmlStream> getXmlStream() {
		return IndustrialBudgetXmlStream.class;
	}

	@Override
	protected ResearchDocumentBase prepareData() {
		return PrintingTestUtils.getBudgetDocument();
	}

	private BudgetSummaryReportDocument getBudgetSummaryReportDocument() {
		return (BudgetSummaryReportDocument) xmlObject;
	}
	
	@Test
	public void totalDirectCostTest(){
		BudgetSummaryReportDocument reportDoc=getBudgetSummaryReportDocument();
		ReportPageType[] reportTypes= reportDoc.getBudgetSummaryReport().getReportPageArray();
		assertEquals(new BudgetDecimal(100000), new BudgetDecimal(reportTypes[0].getBudgetSummary().getTotalDirectCost()));
	}
}
