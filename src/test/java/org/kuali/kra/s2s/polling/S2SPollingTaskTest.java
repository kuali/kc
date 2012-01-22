/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.polling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class tests execution of {@link S2SPollingTask}
 * 
 */
public class S2SPollingTaskTest extends KcUnitTestBase {
	private DateTimeService dateTimeService = null;
	private BusinessObjectService businessObjectService = null;
	private S2SService s2sService = null;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		businessObjectService = KraServiceLocator
				.getService(BusinessObjectService.class);
		dateTimeService = KraServiceLocator.getService(DateTimeService.class);
		s2sService = KraServiceLocator.getService(S2SService.class);
	}

	@After
	public void tearDown() throws Exception {
		businessObjectService = null;
		dateTimeService = null;
		s2sService = null;
		super.tearDown();
	}

	@Test
	public void tests2sPolling() {
		S2SPollingTask s2sPollingTask = new S2SPollingTask();
		s2sPollingTask.setBusinessObjectService(businessObjectService);
		s2sPollingTask.setDateTimeService(dateTimeService);
		s2sPollingTask.sets2SService(s2sService);
		s2sPollingTask.setStopPollInterval("4320");
		s2sPollingTask.setMailInterval("20");

		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("1", "Submitted to Grants.Gov");
		statusMap.put("2", "Receiving");
		statusMap.put("3", "Received");
		statusMap.put("4", "Processing");
		s2sPollingTask.setStatusMap(statusMap);

		MailInfo mailInfo = new MailInfo();
		mailInfo.setTo("geot@mit.edu");
		mailInfo.setBcc("");
		mailInfo.setCc("");
		mailInfo.setDunsNumber("");
		mailInfo.setFooter("");
		mailInfo.setSubject("Grants.Gov Submissions");

		List<MailInfo> mailInfoList = new ArrayList<MailInfo>();
		mailInfoList.add(mailInfo);
		s2sPollingTask.setMailInfoList(mailInfoList);
		s2sPollingTask.execute();
		assert true;
	}
}
