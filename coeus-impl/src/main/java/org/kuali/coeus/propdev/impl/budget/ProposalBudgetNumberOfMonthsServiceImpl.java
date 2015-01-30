/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
    	double daysLeft = period.getDays() + 1;
    	
    	double numberOfMonths = monthsBetween + (daysLeft / daysInMonthForEndDate);
    	
    	return new ScaleTwoDecimal(numberOfMonths).doubleValue();
    }
    
}
