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
package org.kuali.kra.service.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.coeus.sys.impl.lock.KcPessimisticLockServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.GenericQueryResults.Builder;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(JMock.class)
public class KcPessimisticLockServiceImplTest {

    private static final long ONE_DAY = 24L * 60L * 60L * 1000L;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
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
        final ParameterService parameterService = context.mock(ParameterService.class);
        context.checking(new Expectations() {{
            one(parameterService).getParameterValueAsString(Constants.MODULE_NAMESPACE_SYSTEM, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE,
                                                        Constants.PESSIMISTIC_LOCKING_EXPIRATION_AGE); 
            will(returnValue("1440"));
        }});
        pessimisticLockService.setParameterService(parameterService);
        
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
        final DataObjectService dataObjectService = context.mock(DataObjectService.class);
        context.checking(new Expectations() {{
            final Builder<PessimisticLock> qr = Builder.<PessimisticLock>create();
            qr.setResults(locks);
            one(dataObjectService).findMatching(PessimisticLock.class, QueryByCriteria.Builder.create().build()); will(returnValue(qr.build()));
            one(dataObjectService).delete(locks.get(1));
            one(dataObjectService).delete(locks.get(3));
        }});
        pessimisticLockService.setDataObjectService(dataObjectService);
        
        /*
         * Let's clear the expired locks and see if things
         * happen as we expect.
         */
        pessimisticLockService.clearExpiredLocks();
    }
}
