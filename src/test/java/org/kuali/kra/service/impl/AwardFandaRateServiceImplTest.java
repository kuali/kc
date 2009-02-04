/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.infrastructure.Constants;

/**
 * 
 * This class tests <code>AwardFandaRateService</code>
 */
@RunWith(JMock.class)
public class AwardFandaRateServiceImplTest {
    
    //AwardFandaRateService awardFandaRateService;
    private Mockery context = new JUnit4Mockery();
    
    private static final String FISCAL_LEAP_YEAR_STRING = "2008";
    private static final String FISCAL_NON_LEAP_YEAR_STRING = "2010";
    private static final String MOCK_EXPECTED_DATE_STRING_LEAP_YEAR = "07/01/2007,06/30/2008";
    private static final String MOCK_EXPECTED_DATE_STRING_NON_LEAP_YEAR = "07/01/2009,06/30/2010";

    @Before
    public void setUp() throws Exception {
//        awardFandaRateService = new AwardFandaRateServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
  //      awardFandaRateService = null;
    }

    /*@Test
    public final void testGetStartAndEndDatesBasedOnFiscalYear() {
        
        String dates = awardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(FISCAL_YEAR_STRING);
        Assert.assertEquals(MOCK_EXPECTED_DATE_STRING,dates);        
    }*/
    
    @Test
    public final void testGetStartAndEndDatesWhenValidFiscalLeapYearPassed(){
        AwardFandaRateServiceImpl awardFandaRateService =  new AwardFandaRateServiceImpl();
        
        final KualiConfigurationService kualiConfigurationService
            = context.mock(KualiConfigurationService.class);
        context.checking(new Expectations() {{
            one(kualiConfigurationService).getParameterValue("KRA-B", "D", Constants.BUDGET_CURRENT_FISCAL_YEAR); will(returnValue("07/01/2000"));
        }});

        awardFandaRateService.setKualiConfigurationService(kualiConfigurationService);
                
        Assert.assertEquals(MOCK_EXPECTED_DATE_STRING_LEAP_YEAR,
                awardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(FISCAL_LEAP_YEAR_STRING));
    }
    
    @Test
    public final void testGetStartAndEndDatesWhenValidFiscalNonLeapYearPassed(){
        AwardFandaRateServiceImpl awardFandaRateService =  new AwardFandaRateServiceImpl();
        
        final KualiConfigurationService kualiConfigurationService
            = context.mock(KualiConfigurationService.class);
        context.checking(new Expectations() {{
            one(kualiConfigurationService).getParameterValue("KRA-B", "D", Constants.BUDGET_CURRENT_FISCAL_YEAR); will(returnValue("07/01/2000"));
        }});

        awardFandaRateService.setKualiConfigurationService(kualiConfigurationService);
                
        Assert.assertEquals(MOCK_EXPECTED_DATE_STRING_NON_LEAP_YEAR,
                awardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(FISCAL_NON_LEAP_YEAR_STRING));
    }
    
    @Test
    public final void testGetStartAndEndDatesWhenInvalidFiscalYearPassed(){
        AwardFandaRateServiceImpl awardFandaRateService =  new AwardFandaRateServiceImpl();
                
        Assert.assertEquals("",
                awardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(""));
    }
}
