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
package org.kuali.kra.award.printing.xmlstream;

import java.util.Map;

import org.junit.Test;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.printing.util.XmlStreamTestBase;

/**
 * This class tests generation and validation of XML for Award Delta Report
 * 
 */

public class AwardDeltaXmlStreamTest extends
		XmlStreamTestBase<AwardDeltaXmlStream> {

	@Override
	protected Map<String, Object> getReportParameters() {
		return PrintingTestUtils.getAwardDeltaReportParameters();
	}

	@Override
	protected Class<AwardDeltaXmlStream> getXmlStream() {
		return AwardDeltaXmlStream.class;
	}

	@Override
	protected KraPersistableBusinessObjectBase prepareData() {
		return PrintingTestUtils.getAwardDocument().getAward();
	}

	@Test
	public void testAwardDeltaCalculation() {

	}
}
