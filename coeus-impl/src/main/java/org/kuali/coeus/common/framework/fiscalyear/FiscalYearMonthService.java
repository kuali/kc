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
package org.kuali.coeus.common.framework.fiscalyear;

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
