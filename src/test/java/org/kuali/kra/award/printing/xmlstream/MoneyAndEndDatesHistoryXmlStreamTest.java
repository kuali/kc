package org.kuali.kra.award.printing.xmlstream;

import java.util.Map;

import org.junit.Test;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.printing.util.XmlStreamTestBase;

public class MoneyAndEndDatesHistoryXmlStreamTest extends
		XmlStreamTestBase<MoneyAndEndDatesHistoryXmlStream> {

	@Override
	protected Map<String, Object> getReportParameters() {
		return PrintingTestUtils.getMoneyAndEndDatesHistoryReportParameters();
	}

	@Override
	protected Class<MoneyAndEndDatesHistoryXmlStream> getXmlStream() {
		return MoneyAndEndDatesHistoryXmlStream.class;
	}

	@Override
	protected KraPersistableBusinessObjectBase prepareData() {
		return PrintingTestUtils.getAwardDocument().getAward();
	}
	
	@Test
	public void testMoneyHistoryReports() {

	}
	
	@Test
	public void testMoneyHistoryTransactionInfos(){
		
	}
}
