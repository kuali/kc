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
package org.kuali.coeus.propdev.impl.budget;



import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component("proposalBudgetNumberOfMonthsService")
public class ProposalBudgetNumberOfMonthsServiceImpl implements ProposalBudgetNumberOfMonthsService {

    @Override
    public double getNumberOfMonth(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            return 0.00;
        }

        final DateTime start = new DateTime(startDate);
    	final DateTime end = new DateTime(endDate).plusDays(1);

        final int daysInMonthForEndDate = end.dayOfMonth().getMaximumValue();
    	final int wholeMonths = Months.monthsBetween(start, end).getMonths();
    	final int daysRemaining = Days.daysBetween(start.plusMonths(wholeMonths), end).getDays();

        //casting to ensure we don't loose precision
    	final double numberOfMonths = wholeMonths + ((double) daysRemaining / (double) daysInMonthForEndDate);
    	
    	return new ScaleTwoDecimal(numberOfMonths).doubleValue();
    }
    
}
