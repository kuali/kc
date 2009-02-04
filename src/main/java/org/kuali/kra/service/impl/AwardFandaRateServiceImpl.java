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
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.AwardFandaRateService;

/**
 * 
 * This is the implementation of <code>AwardFandaRateService</code> interface.
 */
public class AwardFandaRateServiceImpl implements AwardFandaRateService {
    
    public static final int FISCAL_YEAR_LENGTH = 4;
    public static final long MILLIS_IN_LEAP_YEAR = new Long("31557600000");//366 * 24 * 60 * 60 * 1000
    public static final long MILLIS_IN_NON_LEAP_YEAR = new Long("31471200000");//365 * 24 * 60 * 60 * 1000
        
    private KualiConfigurationService kualiConfigurationService;

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardFandaRateService#getStartAndEndDatesBasedOnFiscalYear(java.lang.String)
     */
    public String getStartAndEndDatesBasedOnFiscalYear(String fiscalYear){
        StringBuilder dates = new StringBuilder();
        
        if (StringUtils.isNotEmpty(fiscalYear) && fiscalYear.length()==FISCAL_YEAR_LENGTH) {            
            String budgetFiscalYearStart
                = kualiConfigurationService.getParameterValue(Constants.PARAMETER_MODULE_BUDGET
                        , Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_CURRENT_FISCAL_YEAR);
            
            Date startDate = getFiscalYearStartDate(fiscalYear, budgetFiscalYearStart.split("/"));      
            
            dates.append(getFormattedDate(startDate));            
            dates.append(",");
            if(new GregorianCalendar().isLeapYear(Integer.valueOf(fiscalYear))){
                dates.append(getFormattedDate(new Date(startDate.getTime() + MILLIS_IN_LEAP_YEAR)));    
            }else{
                dates.append(getFormattedDate(new Date(startDate.getTime() + MILLIS_IN_NON_LEAP_YEAR)));
            }            
        }
        return dates.toString();
    }
    
    public Date getFiscalYearStartDate(String fiscalYear, String[] dateParts){
        Calendar calendar = GregorianCalendar.getInstance();
        
        try{
            calendar.set(Integer.valueOf(fiscalYear)-1, Integer.valueOf(dateParts[0]) - 1
                    , Integer.valueOf(dateParts[1]));    
        }catch(NumberFormatException e){
            throw e;
        }
        
        return new Date(calendar.getTimeInMillis()); 
    }
    
    public String getFormattedDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
        return dateFormat.format(date);
    }
}
