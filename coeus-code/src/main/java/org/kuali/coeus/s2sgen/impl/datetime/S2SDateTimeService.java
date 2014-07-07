package org.kuali.coeus.s2sgen.impl.datetime;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Calendar;

public interface S2SDateTimeService {

    ScaleTwoDecimal getNumberOfMonths(java.util.Date dateStart, java.util.Date dateEnd);

    String removeTimezoneFactor(String applicationXmlText);

    /**
     *
     * This method returns a {@link java.util.Calendar} whose date matches the date passed
     * as {@link String}
     *
     * @param dateStr
     *            string for which the Calendar value has to be found.
     * @return Calendar calendar value corresponding to the date string.
     */
    Calendar convertDateStringToCalendar(String dateStr);

    /**
     *
     * This method is used to get Calendar date
     *
     * @param date(Date)
     *            date for which Calendar value has to be found.
     * @return cal(Calendar) calendar value corresponding to the date.
     */
    Calendar convertDateToCalendar(java.util.Date date);
}
