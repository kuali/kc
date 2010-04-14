package org.kuali.kra.award.printing.xmlstream;

import java.util.Map;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.printing.util.XmlStreamTestBase;

public class AwardBudgetHierarchyXmlStreamTest extends XmlStreamTestBase<AwardBudgetHierarchyXmlStream>{

	@Override
	protected Map<String, Object> getReportParameters() {
		return PrintingTestUtils.getAwardBudgetHierarchyXmlStream();
	}

	@Override
	protected Class<AwardBudgetHierarchyXmlStream> getXmlStream() {
		return AwardBudgetHierarchyXmlStream.class;
	}

	@Override
	protected ResearchDocumentBase prepareData() {
		return PrintingTestUtils.getAwardDocument();
	}
	

}
