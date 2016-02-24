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
package org.kuali.kra.award.subcontracting.reporting;

import java.sql.Date;

public class SubcontractingExpenditureCategoryAmountsInDateRange extends SubcontractingExpenditureCategoryAmountsBase {


    private static final long serialVersionUID = 4702488408295836126L;
    
    private Date rangeStartDate;
    private Date rangeEndDate;
    
    public SubcontractingExpenditureCategoryAmountsInDateRange() {
        super();
    }
    
    public SubcontractingExpenditureCategoryAmountsInDateRange(String awardNumber, Date rangeStartDate, Date rangeEndDate) {
        this();
        // set the PK
        this.setAwardNumber(awardNumber);
        // set the range limits
        this.setRangeStartDate(rangeStartDate);
        this.setRangeEndDate(rangeEndDate);        
    }
    
    public void setRangeStartDate(Date rangeStartDate) {
        this.rangeStartDate = rangeStartDate;
    }
    public Date getRangeStartDate() {
        return rangeStartDate;
    }
    public void setRangeEndDate(Date rangeEndDate) {
        this.rangeEndDate = rangeEndDate;
    }
    public Date getRangeEndDate() {
        return rangeEndDate;
    }

}
