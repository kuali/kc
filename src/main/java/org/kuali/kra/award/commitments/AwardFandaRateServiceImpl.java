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
package org.kuali.kra.award.commitments;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This is the implementation of <code>AwardFandaRateService</code> interface.
 */
public class AwardFandaRateServiceImpl implements AwardFandaRateService {
    
    public static final int FOUR_DIGIT_YEAR_LENGTH = 4;
    public static final long MILLIS_IN_LEAP_YEAR = new Long("31536000000");//365 * 24 * 60 * 60 * 1000
    public static final long MILLIS_IN_NON_LEAP_YEAR = new Long("31449600000");//364 * 24 * 60 * 60 * 1000
    
    protected static final String F_AND_A_RATE_CLASS_TYPE_CODE = "O";
    
    protected BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    
    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * 
     * @see org.kuali.kra.award.commitments.AwardFandaRateService#getStartAndEndDatesBasedOnFiscalYear(java.lang.String)
     */
    public List<String> getStartAndEndDatesBasedOnFiscalYear(String fiscalYear){
        List<String> listDates = new ArrayList<String>();
                
        if (StringUtils.isNotEmpty(fiscalYear) && fiscalYear.length()==FOUR_DIGIT_YEAR_LENGTH) {            
            String budgetFiscalYearStart
                = this.parameterService.getParameterValue(BudgetDocument.class, Constants.BUDGET_CURRENT_FISCAL_YEAR);
            DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
            for(Date date:getFiscalYearStartAndDates(
                                Integer.valueOf(fiscalYear), budgetFiscalYearStart.split("/"))){
                listDates.add(dateFormat.format(date));
            }
        }
        
        return listDates;
    }
    
    public List<ValidRates> getValidRates(AwardFandaRate awardFandaRate) {
        if (ObjectUtils.isNull(awardFandaRate)) {
            return new ArrayList<ValidRates>();
        }
        Map<String, Object> criteria = new HashMap<String, Object>();
        if (awardFandaRate.getOnCampusFlag().equalsIgnoreCase("N")) {
            criteria.put("onCampusRate", awardFandaRate.getApplicableFandaRate());
        } else {
            criteria.put("offCampusRate", awardFandaRate.getApplicableFandaRate());
        }
        criteria.put("rateClassType", F_AND_A_RATE_CLASS_TYPE_CODE);
        @SuppressWarnings("unchecked")
        List<ValidRates> rates = (ArrayList<ValidRates>) businessObjectService.findMatching(ValidRates.class, criteria);
        return rates;
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

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
   
}
