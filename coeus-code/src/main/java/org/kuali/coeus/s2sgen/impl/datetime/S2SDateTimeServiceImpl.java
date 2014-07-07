package org.kuali.coeus.s2sgen.impl.datetime;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.TimeZone;

@Component("s2SDateTimeService")
public class S2SDateTimeServiceImpl implements S2SDateTimeService {

    /**
     *
     * This method computes the number of months between any 2 given {@link java.sql.Date} objects
     *
     * @param dateStart starting date.
     * @param dateEnd end date.
     *
     * @return number of months between the start date and end date.
     */
    @Override
    public ScaleTwoDecimal getNumberOfMonths(java.util.Date dateStart, java.util.Date dateEnd) {
        ScaleTwoDecimal monthCount = ScaleTwoDecimal.ZERO;
        int fullMonthCount = 0;

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(dateStart);
        endDate.setTime(dateEnd);

        startDate.clear(Calendar.HOUR);
        startDate.clear(Calendar.MINUTE);
        startDate.clear(Calendar.SECOND);
        startDate.clear(Calendar.MILLISECOND);

        endDate.clear(Calendar.HOUR);
        endDate.clear(Calendar.MINUTE);
        endDate.clear(Calendar.SECOND);
        endDate.clear(Calendar.MILLISECOND);

        if (startDate.after(endDate)) {
            return ScaleTwoDecimal.ZERO;
        }
        int startMonthDays = startDate.getActualMaximum(Calendar.DATE) - startDate.get(Calendar.DATE);
        startMonthDays++;
        int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
        BigDecimal startMonthFraction = BigDecimal.valueOf(startMonthDays).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(startMonthMaxDays).setScale(2, RoundingMode.HALF_UP), RoundingMode.HALF_UP);

        int endMonthDays = endDate.get(Calendar.DATE);
        int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

        BigDecimal endMonthFraction = BigDecimal.valueOf(endMonthDays).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(endMonthMaxDays).setScale(2, RoundingMode.HALF_UP), RoundingMode.HALF_UP);

        startDate.set(Calendar.DATE, 1);
        endDate.set(Calendar.DATE, 1);

        while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
            fullMonthCount++;
        }
        fullMonthCount = fullMonthCount - 1;
        monthCount = monthCount.add(new ScaleTwoDecimal(fullMonthCount)).add(new ScaleTwoDecimal(startMonthFraction)).add(new ScaleTwoDecimal(endMonthFraction));
        return monthCount;
    }

    @Override
    public String removeTimezoneFactor(String applicationXmlText) {
        Calendar cal = Calendar.getInstance();
        int dstOffsetMilli = cal.get(Calendar.DST_OFFSET);
        int zoneOffsetMilli = cal.get(Calendar.ZONE_OFFSET);
        zoneOffsetMilli = cal.getTimeZone().useDaylightTime()?zoneOffsetMilli+dstOffsetMilli:zoneOffsetMilli;
        int zoneOffset = zoneOffsetMilli/(1000*60*60);
        String timezoneId = TimeZone.getTimeZone("GMT" + zoneOffset).getID();
        String offset="+00:00";
        if(timezoneId.length()>6){
            offset = timezoneId.substring(timezoneId.length()-6);
        }
        String filteredApplicationStr = StringUtils.remove(applicationXmlText, offset);
        return filteredApplicationStr;
    }

    /**
     * This method returns a {@link Calendar} whose date matches the date passed as {@link String}
     *
     * @param dateStr string in "MM/dd/yyyy" format for which the Calendar value has to be returned.
     * @return Calendar calendar value corresponding to the date string.
     */
    @Override
    public Calendar convertDateStringToCalendar(String dateStr) {
        Calendar calendar = null;
        if (dateStr != null) {
            calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(dateStr.substring(6, 10)), Integer.parseInt(dateStr.substring(0, 2)) - 1,
                    Integer.parseInt(dateStr.substring(3, 5)));
        }
        return calendar;
    }

    /**
     * This method is used to get Calendar date for the corresponding date object.
     *
     * @param date(Date) date for which Calendar value has to be found.
     * @return calendar value corresponding to the date.
     */
    @Override
    public Calendar convertDateToCalendar(java.util.Date date) {
        Calendar calendar = null;
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }
}
