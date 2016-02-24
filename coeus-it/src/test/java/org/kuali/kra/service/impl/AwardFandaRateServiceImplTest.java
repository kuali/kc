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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.commitments.AwardFandaRateService;
import org.kuali.kra.award.commitments.AwardFandaRateServiceImpl;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class tests <code>AwardFandaRateService</code>
 */
public class AwardFandaRateServiceImplTest extends KcIntegrationTestBase {
    
    private static final String FISCAL_LEAP_YEAR_STRING = "2008";
    private static final String FISCAL_NON_LEAP_YEAR_STRING = "2010";
    
    private static final List<String> MOCK_EXPECTED_DATE_NON_LEAP_YEAR = new ArrayList<String>();
    private static final List<String> MOCK_EXPECTED_DATE_LEAP_YEAR = new ArrayList<String>();
    private static final List<String> MOCK_EXPECTED_DATE_EMPTY = new ArrayList<String>();
    private AwardFandaRateServiceImpl awardFandaRateService;

    @Before
    public void setUp() throws Exception {
        awardFandaRateService = (AwardFandaRateServiceImpl) KcServiceLocator.getService(AwardFandaRateService.class);
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
