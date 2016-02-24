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
package org.kuali.kra.award.commitments;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This is the implementation of <code>AwardFandaRateService</code> interface.
 */
public class AwardFandaRateServiceImpl implements AwardFandaRateService {
    
    public static final int FOUR_DIGIT_YEAR_LENGTH = 4;
    
    protected static final String F_AND_A_RATE_CLASS_TYPE_CODE = "O";
    
    protected BusinessObjectService businessObjectService;
    private FiscalYearMonthService fiscalYearMonthService;
    
    @Override
    public List<String> getStartAndEndDatesBasedOnFiscalYear(String fiscalYear){
        List<String> listDates = new ArrayList<String>();
        if (StringUtils.isNotEmpty(fiscalYear) && fiscalYear.length()==FOUR_DIGIT_YEAR_LENGTH) {
            DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
            Integer fy = Integer.parseInt(fiscalYear);
            listDates.add(dateFormat.format(new Date(this.getFiscalYearMonthService().getFiscalYearStartDate(fy).getTimeInMillis())));
            listDates.add(dateFormat.format(new Date(this.getFiscalYearMonthService().getFiscalYearEndDate(fy).getTimeInMillis())));
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
        List<ValidRates> rates = new ArrayList<ValidRates>(businessObjectService.findMatching(ValidRates.class, criteria));
        return rates;
    }
   
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public FiscalYearMonthService getFiscalYearMonthService() {
        return this.fiscalYearMonthService;
    }
    
    public void setFiscalYearMonthService(FiscalYearMonthService fiscalYearMonthService) {
        this.fiscalYearMonthService = fiscalYearMonthService;
    }
   
}
