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
package org.kuali.coeus.propdev.impl.budget;


import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component("proposalBudgetNumberOfMonthsService")
public class ProposalBudgetNumberOfMonthsServiceImpl implements ProposalBudgetNumberOfMonthsService {

    @Override
    public double getNumberOfMonth(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0.00;
        } else {
            return getNumberOfMonths(startDate, endDate);
        }
    }

    protected double getNumberOfMonths(Date startDate, Date endDate) {
    	DateTime start = new DateTime(startDate);
    	DateTime end = new DateTime(endDate);
    	double daysInMonthForEndDate = end.dayOfMonth().getMaximumValue();
    	
    	PeriodType periodType = PeriodType.standard().withWeeksRemoved();
    	Period period = new Period(start, end, periodType);
    	double monthsBetween = Months.monthsBetween(start, end).getMonths();
    	double daysLeft = period.getDays();
    	
    	double numberOfMonths = monthsBetween + (daysLeft / daysInMonthForEndDate);
    	
    	return new ScaleTwoDecimal(numberOfMonths).doubleValue();
    }
    
}
