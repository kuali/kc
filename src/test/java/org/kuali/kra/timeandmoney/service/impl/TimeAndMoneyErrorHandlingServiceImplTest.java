/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.service.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyErrorHandlingService;

/**
 * This class...
 */
public class TimeAndMoneyErrorHandlingServiceImplTest extends KcUnitTestBase {
    
    private TimeAndMoneyErrorHandlingService service;
    
    private static final String DEFAULT_FIELD_NAME = "awardHierarchyNodeItems[1].currentFundEffectiveDate";
    private static final String OTHER_FIELD_NAME = "awardHierarchyNodeItems[1].obligationExpirationDate";
    
    @Before
    public void setup() {
        service = KraServiceLocator.getService(TimeAndMoneyErrorHandlingService.class);
        String[] errorParameters = {"Project Start Date", "Project End Date"};
        ErrorReporter er = new ErrorReporter();
        er.reportError(DEFAULT_FIELD_NAME, KeyConstants.ERROR_START_DATE_ON_OR_BEFORE, errorParameters);
    }

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        service = null;
    }

    /**
     * Test method for {@link org.kuali.kra.timeandmoney.service.impl.TimeAndMoneyErrorHandlingServiceImpl#isFieldInError(java.lang.String)}.
     */
    @Test
    public void testIsFieldInError1() {
        boolean result = service.isFieldInError(DEFAULT_FIELD_NAME);
        assertTrue(result);
    }
    
    @Test
    public void testIsFieldInError2() {
        boolean result = service.isFieldInError(OTHER_FIELD_NAME);
        assertFalse(result);
    }

}
