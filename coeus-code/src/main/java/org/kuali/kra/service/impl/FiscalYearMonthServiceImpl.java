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

import org.kuali.kra.service.FiscalYearMonthService;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class FiscalYearMonthServiceImpl implements FiscalYearMonthService {
    
    private ParameterService parameterService;
    protected static final String FISCAL_YEAR_MONTH_PARAMETER_NAME = "KC_FISCAL_START_MONTH";
    protected static final String KC_GENERAL_NAMESPACE = "KC-GEN";
    protected static final String DOCUMENT_COMPONENT_NAME = "Document";
    protected static final String MONTH_KEY = "month";
    protected static final String YEAR_KEY = "year";
    
    @Override
    public Integer getCurrentFiscalMonth() {
        return getCurrentFiscalData(null).get(MONTH_KEY);
    }
    
    @Override
    public Integer getCurrentFiscalMonthForDisplay() {
        return getCurrentFiscalData(null).get(MONTH_KEY) + 1;
    }
    
    @Override
    public Integer getCurrentFiscalYear() {
        return getCurrentFiscalData(null).get(YEAR_KEY);
    }
    
    @Override
    public Integer getFiscalYearFromDate(Calendar date) {
        Integer fiscalStartMonth = getFiscalYearMonth();
        Integer year = date.get(Calendar.YEAR);
        Integer month = date.get(Calendar.MONTH);
        if (fiscalStartMonth != 0 && month >= fiscalStartMonth) {
            year = year.intValue() + 1;
        }
        return year;
    }
    
    /**
     * 
     * This method returns a Map object that contains the current fiscal month and year.  See protect static variables for they Keys.
     * @param calendar, if you want to use something other than the current day.
     * @return
     */
    protected Map<String, Integer> getCurrentFiscalData(Calendar calendar) {
        Map<String, Integer> data = new HashMap<String, Integer>();
        
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        Integer fiscalStartMonth = getFiscalYearMonth();
        //assuming July is the fiscal start month         
        if (calendar.get(Calendar.MONTH) == fiscalStartMonth.intValue()) {
            //July 1st, 2012, is the 1st month of FY 2013 
            data.put(MONTH_KEY, findMonth(fiscalStartMonth, calendar.get(Calendar.MONTH)));
            if (fiscalStartMonth.equals(Calendar.JANUARY)) {
                data.put(YEAR_KEY, calendar.get(Calendar.YEAR));
            } else {
                data.put(YEAR_KEY, calendar.get(Calendar.YEAR) + 1);
            }
        } else if (calendar.get(Calendar.MONTH) > fiscalStartMonth.intValue()) {
            //August 1st 2012, is the second month of FY 2013
            data.put(MONTH_KEY, findMonth(fiscalStartMonth, calendar.get(Calendar.MONTH)));
            if (fiscalStartMonth.equals(Calendar.JANUARY)) {
                data.put(YEAR_KEY, calendar.get(Calendar.YEAR));
            } else {
                data.put(YEAR_KEY, calendar.get(Calendar.YEAR) + 1);
            }
        } else {
            //June 1st 2012, is the 12th month of FY 2012
            data.put(MONTH_KEY, findMonth(fiscalStartMonth, calendar.get(Calendar.MONTH)));
            data.put(YEAR_KEY, calendar.get(Calendar.YEAR));
        }        
        return data;
    }
    
    private int findMonth(int startingMonth, int currentMonth) {
        /**
         * We are building an array of integers.  The array position number is the fiscal month position of the calendar month.
         * The array values are the calendar months.  So an example with a fiscal year starting in September would be as follows:
         * YEAR[0] = Calendar.September
         * Year[1] = Calendar.October
         * Year[11 = Calendar.August
         */
        int nextMonth = startingMonth;
        int[] YEAR = {0, 1, 2, 3, 4, 5, 6 ,7, 8, 9, 10, 11};
        for (int i = 0; i < 12; i++) {
            YEAR[i] = nextMonth;
            if (nextMonth == 11) {
                nextMonth = 0;
            } else {
                nextMonth++;
            }
        }
        for (int i : YEAR) {
            if (YEAR[i] == currentMonth) {
                return i;
            }
        }
        throw new IllegalArgumentException("Could not find the current month: " + currentMonth);
    }
    
    
    /**
     * 
     * This method returns the value of the KC_FISCAL_START_MONTH parameter.
     * @return
     */
    protected Integer getFiscalYearMonth() {
        Parameter parm = this.getParameterService().getParameter(KC_GENERAL_NAMESPACE, DOCUMENT_COMPONENT_NAME, FISCAL_YEAR_MONTH_PARAMETER_NAME);
        return Integer.parseInt(parm.getValue());
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public Calendar getFiscalYearStartDate(Integer fiscalYear) {
        Calendar cal =  Calendar.getInstance();
        if (getFiscalYearMonth().equals(new Integer(0))) {
            cal.set(Calendar.YEAR, fiscalYear);
        } else {
            cal.set(Calendar.YEAR, fiscalYear - 1);
        }        
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, getFiscalYearMonth());
        return cal;
    }
    
    public Calendar getFiscalYearEndDate(Integer fiscalYear) {
        Calendar cal =  getFiscalYearStartDate(fiscalYear);
        cal.add(Calendar.DATE, -1);
        cal.add(Calendar.YEAR, 1);
        return cal;
    }
}