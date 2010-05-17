package org.kuali.kra.budget.printing.xmlstream;

import java.util.Map;

import org.kuali.kra.budget.printing.xmlstream.BudgetBaseSalaryStream;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.printing.util.XmlStreamTestBase;

public class BudgetBaseSalaryStreamTest extends XmlStreamTestBase<BudgetBaseSalaryStream>{

	@Override
	protected Map<String, Object> getReportParameters() {
		PrintingTestUtils.getBudgetBaseSalaryStreamReportParameters();
		return null;
	}

	@Override
	protected Class<BudgetBaseSalaryStream> getXmlStream() {
		return BudgetBaseSalaryStream.class;
	}

	@Override
	protected ResearchDocumentBase prepareData() {
		return null;
	}

}
