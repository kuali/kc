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

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.AwardFandaRateService;
import org.kuali.rice.kns.service.KualiConfigurationService;

/**
 * 
 * This is the implementation of <code>AwardFandaRateService</code> interface.
 */
public class AwardFandaRateServiceImpl implements AwardFandaRateService {
    
    public static final int FOUR_DIGIT_YEAR_LENGTH = 4;
    public static final long MILLIS_IN_LEAP_YEAR = new Long("31536000000");//365 * 24 * 60 * 60 * 1000
    public static final long MILLIS_IN_NON_LEAP_YEAR = new Long("31449600000");//364 * 24 * 60 * 60 * 1000
    DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
        
    private KualiConfigurationService kualiConfigurationService;

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardFandaRateService#getStartAndEndDatesBasedOnFiscalYear(java.lang.String)
     */
    public List<String> getStartAndEndDatesBasedOnFiscalYear(String fiscalYear){
        List<String> listDates = new ArrayList<String>();
                
        if (StringUtils.isNotEmpty(fiscalYear) && fiscalYear.length()==FOUR_DIGIT_YEAR_LENGTH) {            
            String budgetFiscalYearStart
                = kualiConfigurationService.getParameterValue(Constants.PARAMETER_MODULE_BUDGET
                        , Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_CURRENT_FISCAL_YEAR);
            
            for(Date date:getFiscalYearStartAndDates(
                                Integer.valueOf(fiscalYear), budgetFiscalYearStart.split("/"))){
                listDates.add(dateFormat.format(date));
            }
        }
        
        return listDates;
    }
    
    /**
     * 
     * This method retrieves the fiscal year start date using the fiscal year and 
     * default fiscal year start date
     * 
     * @param fiscalYear
     * @param dateParts
     * @return
     */
    protected List<Date> getFiscalYearStartAndDates(int fiscalYear, String[] dateParts){
        Calendar calendar = GregorianCalendar.getInstance();
        List<Date> dates = new ArrayList<Date>();
        
        try{
            calendar.set(fiscalYear-1, Integer.valueOf(dateParts[0]) - 1
                    , Integer.valueOf(dateParts[1]));            
        }catch(NumberFormatException e){
            throw e;
        }
        
        dates.add(new Date(calendar.getTimeInMillis()));        
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        dates.add(new Date(calendar.getTimeInMillis()));
        return dates; 
    }
   
}
