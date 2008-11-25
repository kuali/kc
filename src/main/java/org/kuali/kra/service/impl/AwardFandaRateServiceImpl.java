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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.service.AwardFandaRateService;

/**
 * 
 * This is the implementation of <code>AwardFandaRateService</code> interface.
 */
public class AwardFandaRateServiceImpl implements AwardFandaRateService {
    
    public static final int FISCAL_YEAR_LENGTH = 4;

    /**
     * 
     * @see org.kuali.kra.service.AwardFandaRateService#getStartAndEndDatesBasedOnFiscalYear(java.lang.String)
     */
    public String getStartAndEndDatesBasedOnFiscalYear(String fiscalYear){
        StringBuilder dates = new StringBuilder();
        String startDateYear = null;
        try{
            startDateYear = new Integer(Integer.parseInt(fiscalYear) - 1).toString();            
        }catch(NumberFormatException e){
            throw e;
        }
        
        if (StringUtils.isNotEmpty(fiscalYear) && fiscalYear.length()==FISCAL_YEAR_LENGTH) {
            dates.append("07/01/");
            dates.append(startDateYear);
            dates.append(",");
            dates.append("06/30/");
            dates.append(fiscalYear);            
        }
        return dates.toString();
    }

}
