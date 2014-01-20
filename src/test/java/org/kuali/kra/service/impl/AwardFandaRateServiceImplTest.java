/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.commitments.AwardFandaRateService;
import org.kuali.kra.award.commitments.AwardFandaRateServiceImpl;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class tests <code>AwardFandaRateService</code>
 */
public class AwardFandaRateServiceImplTest extends KcUnitTestBase {
    
    private static final String FISCAL_LEAP_YEAR_STRING = "2008";
    private static final String FISCAL_NON_LEAP_YEAR_STRING = "2010";
    
    private static final List<String> MOCK_EXPECTED_DATE_NON_LEAP_YEAR = new ArrayList<String>();
    private static final List<String> MOCK_EXPECTED_DATE_LEAP_YEAR = new ArrayList<String>();
    private static final List<String> MOCK_EXPECTED_DATE_EMPTY = new ArrayList<String>();
    private AwardFandaRateServiceImpl awardFandaRateService;

    //private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        awardFandaRateService = (AwardFandaRateServiceImpl) KraServiceLocator.getService(AwardFandaRateService.class);
    }

    @After
    public void tearDown() throws Exception {
        awardFandaRateService = null;
    }    
    
    @Test
    public final void testGetStartAndEndDatesWhenValidFiscalLeapYearPassed(){
        MOCK_EXPECTED_DATE_LEAP_YEAR.add("07/01/2007");
        MOCK_EXPECTED_DATE_LEAP_YEAR.add("06/30/2008");
                
        Assert.assertEquals(MOCK_EXPECTED_DATE_LEAP_YEAR,
                awardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(FISCAL_LEAP_YEAR_STRING));
    }
    
    @Test
    public final void testGetStartAndEndDatesWhenValidFiscalNonLeapYearPassed(){ 
        MOCK_EXPECTED_DATE_NON_LEAP_YEAR.add("07/01/2009");
        MOCK_EXPECTED_DATE_NON_LEAP_YEAR.add("06/30/2010");        
                
        Assert.assertEquals(MOCK_EXPECTED_DATE_NON_LEAP_YEAR,
                awardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(FISCAL_NON_LEAP_YEAR_STRING));
    }
    
    @Test
    public final void testGetStartAndEndDatesWhenInvalidFiscalYearPassed(){
        AwardFandaRateServiceImpl awardFandaRateService =  new AwardFandaRateServiceImpl();
                
        Assert.assertEquals(MOCK_EXPECTED_DATE_EMPTY,
                awardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(""));
    }
}
