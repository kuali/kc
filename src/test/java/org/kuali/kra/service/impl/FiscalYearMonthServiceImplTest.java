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
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.FiscalYearMonthService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.coreservice.api.parameter.Parameter;

import java.util.Calendar;
import java.util.Map;

/**
 * This test is very fragile.  It depends on test methods running in an exact order.  This has been partially fixed.
 */
public class FiscalYearMonthServiceImplTest extends KcUnitTestBase {
    FiscalYearMonthServiceImpl fiscalYearMonthService;
    @Before
    public void setUp() throws Exception {
        fiscalYearMonthService = (FiscalYearMonthServiceImpl) KraServiceLocator.getService(FiscalYearMonthService.class);
    }

    @After
    public void tearDown() throws Exception {
        fiscalYearMonthService = null;
    }

    @Test
    public void testGetFiscalYearMonth() {
        Integer result = fiscalYearMonthService.getFiscalYearMonth();
        assertEquals(new Integer(6), result);
    }
    
    @Test
    public void testGetFiscalYearFromDate() {
        assertEquals(new Integer(6), fiscalYearMonthService.getFiscalYearMonth());
        
        Calendar january = Calendar.getInstance();
        january.set(Calendar.MONTH, Calendar.JANUARY);
        january.set(Calendar.DATE, 1);
        january.set(Calendar.YEAR, 2012);
        Integer result = fiscalYearMonthService.getFiscalYearFromDate(january);
        assertEquals(new Integer(2012), result);
        
        
        Calendar july = Calendar.getInstance();
        july.set(Calendar.MONTH, Calendar.JULY);
        july.set(Calendar.DATE, 1);
        july.set(Calendar.YEAR, 2012);
        result = fiscalYearMonthService.getFiscalYearFromDate(july);
        assertEquals(new Integer(2013), result);
    }
    
    @Test
    public void testGetCurrentFiscalData1() {
        Calendar july = Calendar.getInstance();
        july.set(Calendar.MONTH, Calendar.JULY);
        july.set(Calendar.DATE, 1);
        july.set(Calendar.YEAR, 2012);
        Map<String, Integer> data = fiscalYearMonthService.getCurrentFiscalData(july);
        assertEquals(new Integer(Calendar.JANUARY), data.get(FiscalYearMonthServiceImpl.MONTH_KEY));
        assertEquals(new Integer(2013), data.get(FiscalYearMonthServiceImpl.YEAR_KEY));
        
        Calendar june = Calendar.getInstance();
        june.set(Calendar.MONTH, Calendar.JUNE);
        june.set(Calendar.DATE, 1);
        june.set(Calendar.YEAR, 2012);
        data = fiscalYearMonthService.getCurrentFiscalData(june);
        assertEquals(new Integer(Calendar.DECEMBER), data.get(FiscalYearMonthServiceImpl.MONTH_KEY));
        assertEquals(new Integer(2012), data.get(FiscalYearMonthServiceImpl.YEAR_KEY));
        
        Calendar august = Calendar.getInstance();
        august.set(Calendar.MONTH, Calendar.AUGUST);
        august.set(Calendar.DATE, 1);
        august.set(Calendar.YEAR, 2012);
        data = fiscalYearMonthService.getCurrentFiscalData(august);
        assertEquals(new Integer(Calendar.FEBRUARY), data.get(FiscalYearMonthServiceImpl.MONTH_KEY));
        assertEquals(new Integer(2013), data.get(FiscalYearMonthServiceImpl.YEAR_KEY));
    }
    
    @Test
    public void testGetFiscalYearStartDate1() {
        assertEquals(new Integer(6), fiscalYearMonthService.getFiscalYearMonth());
        Calendar result = this.fiscalYearMonthService.getFiscalYearStartDate(2010);
        assertEquals(Calendar.JULY, result.get(Calendar.MONTH));
        assertEquals(2009, result.get(Calendar.YEAR));
        assertEquals(1, result.get(Calendar.DATE));
    }
    
    @Test
    public void testGetFiscalYearEndDate1() {
        assertEquals(new Integer(6), fiscalYearMonthService.getFiscalYearMonth());
        Calendar result = this.fiscalYearMonthService.getFiscalYearEndDate(2010);
        assertEquals(Calendar.JUNE, result.get(Calendar.MONTH));
        assertEquals(2010, result.get(Calendar.YEAR));
        assertEquals(30, result.get(Calendar.DATE));
    }
    
    @Test
    public void testGetCurrentFiscalData2() {
        Parameter parm = getParameterService().getParameter(FiscalYearMonthServiceImpl.KC_GENERAL_NAMESPACE, 
                FiscalYearMonthServiceImpl.DOCUMENT_COMPONENT_NAME, FiscalYearMonthServiceImpl.FISCAL_YEAR_MONTH_PARAMETER_NAME);
        Parameter.Builder parameterForUpdate = Parameter.Builder.create(parm);
        parameterForUpdate.setValue("0");
        getParameterService().updateParameter(parameterForUpdate.build());
        
        Calendar january = Calendar.getInstance();
        january.set(Calendar.MONTH, Calendar.JANUARY);
        january.set(Calendar.DATE, 1);
        january.set(Calendar.YEAR, 2012);
        Map<String, Integer> data = fiscalYearMonthService.getCurrentFiscalData(january);
        assertEquals(new Integer(Calendar.JANUARY), data.get(FiscalYearMonthServiceImpl.MONTH_KEY));
        assertEquals(new Integer(2012), data.get(FiscalYearMonthServiceImpl.YEAR_KEY));
        
        Calendar december = Calendar.getInstance();
        december.set(Calendar.MONTH, Calendar.DECEMBER);
        december.set(Calendar.DATE, 1);
        december.set(Calendar.YEAR, 2012);
        data = fiscalYearMonthService.getCurrentFiscalData(december);
        assertEquals(new Integer(Calendar.DECEMBER), data.get(FiscalYearMonthServiceImpl.MONTH_KEY));
        assertEquals(new Integer(2012), data.get(FiscalYearMonthServiceImpl.YEAR_KEY));
        
        Calendar july = Calendar.getInstance();
        july.set(Calendar.MONTH, Calendar.JULY);
        july.set(Calendar.DATE, 1);
        july.set(Calendar.YEAR, 2012);
        data = fiscalYearMonthService.getCurrentFiscalData(july);
        assertEquals(new Integer(Calendar.JULY), data.get(FiscalYearMonthServiceImpl.MONTH_KEY));
        assertEquals(new Integer(2012), data.get(FiscalYearMonthServiceImpl.YEAR_KEY));

        assertEquals(new Integer(0), fiscalYearMonthService.getFiscalYearMonth());
        Calendar result = this.fiscalYearMonthService.getFiscalYearStartDate(2010);
        assertEquals(Calendar.JANUARY, result.get(Calendar.MONTH));
        assertEquals(2010, result.get(Calendar.YEAR));
        assertEquals(1, result.get(Calendar.DATE));

        assertEquals(new Integer(0), fiscalYearMonthService.getFiscalYearMonth());
        result = this.fiscalYearMonthService.getFiscalYearEndDate(2010);
        assertEquals(Calendar.DECEMBER, result.get(Calendar.MONTH));
        assertEquals(2010, result.get(Calendar.YEAR));
        assertEquals(31, result.get(Calendar.DATE));

        parm = getParameterService().getParameter(FiscalYearMonthServiceImpl.KC_GENERAL_NAMESPACE,
                FiscalYearMonthServiceImpl.DOCUMENT_COMPONENT_NAME, FiscalYearMonthServiceImpl.FISCAL_YEAR_MONTH_PARAMETER_NAME);
        parameterForUpdate = Parameter.Builder.create(parm);
        parameterForUpdate.setValue("6");
        getParameterService().updateParameter(parameterForUpdate.build());
    }
}