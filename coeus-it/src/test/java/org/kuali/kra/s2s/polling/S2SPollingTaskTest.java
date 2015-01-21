/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionService;
import org.kuali.coeus.propdev.impl.s2s.schedule.MailInfo;
import org.kuali.coeus.propdev.impl.s2s.schedule.S2SPollingTask;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class tests execution of {@link org.kuali.coeus.propdev.impl.s2s.schedule.S2SPollingTask}
 * 
 */
public class S2SPollingTaskTest extends KcIntegrationTestBase {
	private DataObjectService dataObjectService = null;
	private S2sSubmissionService s2sSubmissionService = null;

	@Before
	public void setUp() throws Exception {
		dataObjectService = KcServiceLocator
				.getService(DataObjectService.class);
        s2sSubmissionService = KcServiceLocator.getService(S2sSubmissionService.class);
	}

	@After
	public void tearDown() throws Exception {
		dataObjectService = null;
        s2sSubmissionService = null;
	}

	@Test
	public void tests2sPolling() {
		S2SPollingTask s2sPollingTask = new S2SPollingTask();
		s2sPollingTask.setDataObjectService(dataObjectService);
		s2sPollingTask.setS2sSubmissionService(s2sSubmissionService);
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
        Assert.assertTrue(true);
    }
}
