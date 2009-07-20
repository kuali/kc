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
package org.kuali.kra.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KualiConfigurationService;

@RunWith(JMock.class)
public class KcPessimisticLockServiceImplTest {

    private static final long ONE_DAY = 24L * 60L * 60L * 1000L;
    
    private Mockery context = new JUnit4Mockery();
    
    private List<PessimisticLock> locks;
    private Timestamp cronJobTime;
    
    /**
     * First, a cron job time must be established as the baseline.
     * The cron job time is the time that the service is invoked
     * to delete the expired locks.
     * 
     * We will test four pessimistic locks.  One is only a half
     * day old, one is two days old, one millisecond less than a
     * day old, and one millisecond greater than a day old.
     */
    @Before
    public void setUp() {
        long now = System.currentTimeMillis();
        cronJobTime = new Timestamp(now);
        locks = new ArrayList<PessimisticLock>();
        locks.add(createPessimisticLock(now - (ONE_DAY / 2)));
        locks.add(createPessimisticLock(now - (ONE_DAY * 2)));
        locks.add(createPessimisticLock(now - (ONE_DAY - 1)));
        locks.add(createPessimisticLock(now - (ONE_DAY + 1)));
    }
    
    /**
     * Create a Pessimistic Lock with the given timestamp.
     * @param timestamp the creation timestamp of the lock
     * @return the new Pessimistic Lock
     */
    @SuppressWarnings("deprecation")
    private PessimisticLock createPessimisticLock(long timestamp) {
        PessimisticLock lock = new PessimisticLock();
        lock.setGeneratedTimestamp(new Timestamp(timestamp));
        return lock;
    }

    /**
     * From the Pessimistic Locks created in setUp(), only the second and fourth
     * locks are supposed to be considered expired.  
     */
    @Test
    public void testExpiredLocks() {
        KcPessimisticLockServiceImpl pessimisticLockService = new KcPessimisticLockServiceImpl();
        
        /*
         * The configuration service will be invoked once to get the Lock Expiration Age
         * which for this test will be one day (1440 minutes).
         */
        final KualiConfigurationService configurationService = context.mock(KualiConfigurationService.class);
        context.checking(new Expectations() {{
            one(configurationService).getParameterValue(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                                                        Constants.PARAMETER_COMPONENT_DOCUMENT, 
                                                        KeyConstants.PESSIMISTIC_LOCKING_EXPIRATION_AGE); 
            will(returnValue("1440"));
        }});
        pessimisticLockService.setConfigurationService(configurationService);
        
        /*
         * The date time service is invoked once to get the current time of day.
         * We will use our cron job time.
         */
        final DateTimeService dateTimeService = context.mock(DateTimeService.class);
        context.checking(new Expectations() {{
            one(dateTimeService).getCurrentTimestamp(); will(returnValue(cronJobTime));
        }});
        pessimisticLockService.setDateTimeService(dateTimeService);
        
        /*
         * The business object service is used to retrieve all of the locks and
         * will then be used to delete the second and fourth only.
         */
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findAll(PessimisticLock.class); will(returnValue(locks));
            one(businessObjectService).delete(locks.get(1));
            one(businessObjectService).delete(locks.get(3));
        }});
        pessimisticLockService.setBusinessObjectService(businessObjectService);
        
        /*
         * Let's clear the expired locks and see if things
         * happen as we expect.
         */
        pessimisticLockService.clearExpiredLocks();
    }
}
