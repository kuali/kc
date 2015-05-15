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
package org.kuali.coeus.sys.framework.scheduling.expr;

import org.kuali.coeus.sys.framework.scheduling.util.CronSpecialChars;
import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;

import java.text.ParseException;
import java.util.Date;

/**
 * This class extends CronExpression and provides WeekCronExpression implementation.
 * <p>
 * This implementation generates expression for weekly scheduling dates using day of week.
 * 
 * day of week: CronSpecialChars.SUN, CronSpecialChars.MON, CronSpecialChars.TUE, CronSpecialChars.WED, CronSpecialChars.THU,
 * CronSpecialChars.FRI, CronSpecialChars.SAT
 * 
 * Note: Start date is skipped, date boundary is handled by ScheduleSequence implementation during schedule generation.
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm) Format (second minute hour day month year) Generated Expression :0 10 10 ? * MON
 * Explanation: Generate dates for every week on Monday at 10:10 (hh:mm)
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm) Format (second minute hour day month year) Generated Expression :0 10 10 ? *
 * MON,FRI Explanation: Generate dates for every week on Monday &amp; Friday at 10:10 (hh:mm)
 */
public class WeekCronExpression extends CronExpression {

    private CronSpecialChars[] weekdays;

    public WeekCronExpression(Date startDate, Time24HrFmt time, CronSpecialChars[] weekdays) throws ParseException {
        super(startDate, time);
        this.weekdays = weekdays;
    }

    @Override
    public String getExpression() {

        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getTime().getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getTime().getHours()).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.QUESTION).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.STAR).append(CronSpecialChars.SPACE);
        exp.append(toStringWeekDays(weekdays, CronSpecialChars.COMMASEPRATOR));
        return exp.toString();
    }

    private String toStringWeekDays(CronSpecialChars[] day, CronSpecialChars seperator) {

        StringBuilder sb = new StringBuilder();
        for (CronSpecialChars str : day) {
            sb.append(str).append(seperator);
        }
        return sb.substring(0, sb.length() - 1);
    }
}
