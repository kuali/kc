/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.scheduling.quartz;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.coeus.sys.framework.scheduling.KcCronTriggerBean;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.impl.datetime.DateTimeServiceImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.quartz.JobDetail;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the KcCronTriggerBean class.
 */
@RunWith(JMock.class)
public class KcCronTriggerBeanTest {
    
    private static final String CRON_EXPRESSION = "1 3 22 * * ?";
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    /**
     * The KC Cron Trigger needs to be tested to simply verify that it is getting the
     * correct cron expression.
     * @throws ParseException 
     */
    @Test
    public void testCronExpression() throws Exception {
        KcCronTriggerBean cronTrigger = new KcCronTriggerBean();
        
        /*
         * The configuration service will be invoked once to get the Cron Expression.
         */
        final ParameterService parameterService = context.mock(ParameterService.class);
        context.checking(new Expectations() {
            {
                one(parameterService).parameterExists("KC-PD", "Document",
                        KeyConstants.PESSIMISTIC_LOCKING_CRON_EXPRESSION);
                will(returnValue(true));
                one(parameterService).getParameterValueAsString("KC-PD", "Document",
                        KeyConstants.PESSIMISTIC_LOCKING_CRON_EXPRESSION);
                will(returnValue(CRON_EXPRESSION));
            }
        });
        cronTrigger.setParameterService(parameterService);

        JobDetail jobDetail = new JobDetail();
        jobDetail.setName("test");
        cronTrigger.setBeanName("test");
        cronTrigger.setJobDetail(jobDetail);
        cronTrigger.setParameterNamespace("KC-PD");
        cronTrigger.setParameterComponent("Document");
        cronTrigger.setCronExpressionParameterName(KeyConstants.PESSIMISTIC_LOCKING_CRON_EXPRESSION);
        cronTrigger.setDateTimeService(new DateTimeServiceImpl());
        cronTrigger.afterPropertiesSet();
        
        assertEquals(CRON_EXPRESSION, cronTrigger.getCronExpression());
    }
}
