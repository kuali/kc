/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.scheduling.quartz;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.scheduling.quartz.KcCronTriggerBean;
import org.quartz.JobDetail;

/**
 * Tests for the KcCronTriggerBean class.
 */
@RunWith(JMock.class)
public class KcCronTriggerBeanTest {
    
    private static final String CRON_EXPRESSION = "1 3 22 * * ?";
    
    private Mockery context = new JUnit4Mockery();
    
    /**
     * The KC Cron Trigger needs to be tested to simply verify that it is getting the
     * correct cron expression.
     * @throws ParseException 
     */
    @Test
    public void testCronExpression() throws ParseException {
        KcCronTriggerBean cronTrigger = new KcCronTriggerBean();
        
        /*
         * The configuration service will be invoked once to get the Cron Expression.
         */
        final KualiConfigurationService configurationService = context.mock(KualiConfigurationService.class);
        context.checking(new Expectations() {{
            one(configurationService).getParameterValue(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                                                        Constants.PARAMETER_COMPONENT_DOCUMENT, 
                                                        KeyConstants.PESSIMISTIC_LOCKING_CRON_EXPRESSION); 
            will(returnValue(CRON_EXPRESSION));
        }});
        cronTrigger.setConfigurationService(configurationService);
        
        cronTrigger.setConfigurationService(configurationService);
        JobDetail jobDetail = new JobDetail();
        jobDetail.setName("test");
        cronTrigger.setBeanName("test");
        cronTrigger.setJobDetail(jobDetail);
        cronTrigger.afterPropertiesSet();
        
        assertEquals(CRON_EXPRESSION, cronTrigger.getCronExpression());
    }
}
