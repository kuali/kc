package org.kuali.kra.award.printing.xmlstream;

import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.printing.util.XmlStreamTestBase;

public class AwardBudgetHistoryTransactionXmlStreamTest extends
		XmlStreamTestBase<AwardBudgetHistoryTransactionXmlStream> {

	@Override
	protected Map<String, Object> getReportParameters() {
		return PrintingTestUtils.getAwardBudgetHistoryTransactionReportParameters();
	}

	@Override
	protected Class<AwardBudgetHistoryTransactionXmlStream> getXmlStream() {
		return AwardBudgetHistoryTransactionXmlStream.class;
	}

	@Override
	protected KraPersistableBusinessObjectBase prepareData() {
		//TODO Need to verify
		return PrintingTestUtils.getAwardDocument().getAward();
	}

}
