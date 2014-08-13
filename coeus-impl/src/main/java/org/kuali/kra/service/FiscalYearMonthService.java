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
package org.kuali.kra.service;

import java.util.Calendar;


public interface FiscalYearMonthService {
    
    /**
     * 
     * This method returns the Integer value of the current month in the fiscal year.  January is 0, December is 11.
     * @return
     */
    Integer getCurrentFiscalMonth();
    
    /**
     * 
     * This method returns the Integer value of the current month in the fiscal year.  January is 1, December is 12.
     * @return
     */
    Integer getCurrentFiscalMonthForDisplay();
    
    /**
     * 
     * This method returns the current fiscal year.
     * @return
     */
    Integer getCurrentFiscalYear();
    
    /**
     * 
     * This method calculates what fiscal year the passed in date is in.  For example July 13 2012 is in fiscal year 2013 if
     * the fiscal starting month is less than July, but not January.
     * @param date
     * @return
     */
    Integer getFiscalYearFromDate(Calendar date);
    
    /**
     * 
     * This method determines the date the passed in fiscal year started on.
     * @param fiscalYear
     * @return
     */
    Calendar getFiscalYearStartDate(Integer fiscalYear);
    
    /**
     * 
     * This method  determines the date the passed in fiscal year ended on.
     * @param fiscalYear
     * @return
     */
    Calendar getFiscalYearEndDate(Integer fiscalYear);
    
}
