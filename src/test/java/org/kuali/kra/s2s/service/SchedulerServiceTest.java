/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.service;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.s2s.service.impl.SchedulerServiceImpl;


public class SchedulerServiceTest extends Assert{

    private static final Logger LOG = Logger.getLogger(SchedulerServiceTest.class);
    SchedulerServiceImpl schedulerServiceImpl = new SchedulerServiceImpl();
    
    @Test
    public void testStartedAllServices(){
        
        schedulerServiceImpl.startAllServices();
        LOG.info("Started all services");
    }
 
    @Test
    public void testStopAllServices(){
        schedulerServiceImpl.stopAllServices();
        LOG.info("Stopped all services");
    }
}
