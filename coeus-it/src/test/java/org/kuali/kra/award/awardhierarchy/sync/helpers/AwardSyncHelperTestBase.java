/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import org.junit.After;
import org.junit.Before;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncHelpersService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public abstract class AwardSyncHelperTestBase extends KcUnitTestBase {

    protected AwardSyncHelpersService awardSyncHelpersService;
    protected String className;
    protected Award award;
    protected AwardSyncHelper awardSyncHelper;
    
    protected AwardSyncHelperTestBase(String className) {
        this.className = className;
    }
    
    @Before
    public void setUp() throws Exception {
        awardSyncHelpersService = KraServiceLocator.getService(AwardSyncHelpersService.class);
        awardSyncHelper = awardSyncHelpersService.getSyncHelper(className);
        award = new Award();
    }

    @After
    public void tearDown() throws Exception {
    }

}
