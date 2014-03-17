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
