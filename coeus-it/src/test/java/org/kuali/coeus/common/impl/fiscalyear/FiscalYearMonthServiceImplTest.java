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
package org.kuali.coeus.common.impl.fiscalyear;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.common.impl.fiscalyear.FiscalYearMonthServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;

import java.util.Calendar;
import java.util.Map;

import static org.junit.Assert.assertEquals;
/**
 * This test is very fragile.  It depends on test methods running in an exact order.  This has been partially fixed.
 */
public class FiscalYearMonthServiceImplTest extends KcIntegrationTestBase {
    FiscalYearMonthServiceImpl fiscalYearMonthService;
    @Before
    public void setUp() throws Exception {
        fiscalYearMonthService = (FiscalYearMonthServiceImpl) KcServiceLocator.getService(FiscalYearMonthService.class);
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
        Parameter parm = CoreFrameworkServiceLocator.getParameterService().getParameter(FiscalYearMonthServiceImpl.KC_GENERAL_NAMESPACE,
                FiscalYearMonthServiceImpl.DOCUMENT_COMPONENT_NAME, FiscalYearMonthServiceImpl.FISCAL_YEAR_MONTH_PARAMETER_NAME);
        Parameter.Builder parameterForUpdate = Parameter.Builder.create(parm);
        parameterForUpdate.setValue("0");
        CoreFrameworkServiceLocator.getParameterService().updateParameter(parameterForUpdate.build());
        
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

        parm = CoreFrameworkServiceLocator.getParameterService().getParameter(FiscalYearMonthServiceImpl.KC_GENERAL_NAMESPACE,
                FiscalYearMonthServiceImpl.DOCUMENT_COMPONENT_NAME, FiscalYearMonthServiceImpl.FISCAL_YEAR_MONTH_PARAMETER_NAME);
        parameterForUpdate = Parameter.Builder.create(parm);
        parameterForUpdate.setValue("6");
        CoreFrameworkServiceLocator.getParameterService().updateParameter(parameterForUpdate.build());
    }
}
