/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utility methods for comparing dates
 * 
 * 
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
    /**
     * Adds null-safety to commons.DateUtils isSameDay method.
     * 
     * @return true if both dates are null or represent the same day
     */
    public static boolean isSameDay(Date date1, Date date2) {
        boolean same = false;

        if ((date1 == null) && (date2 == null)) {
            same = true;
        }
        else if ((date1 != null) && (date2 != null)) {
            return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
        }
        else {
            same = false;
        }

        return same;
    }

    /**
     * Adds null-safety to commons.DateUtils isSameDay method.
     * 
     * @return true if both calendars are null or represent the same day
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        boolean same = false;

        if ((cal1 == null) && (cal2 == null)) {
            same = true;
        }
        else if ((cal1 != null) && (cal2 != null)) {
            return org.apache.commons.lang.time.DateUtils.isSameDay(cal1, cal2);
        }
        else {
            same = false;
        }

        return same;
    }

    /**
     * Converts the given java.util.Date into an equivalent java.sql.Date
     * 
     * @param date
     * @return java.sql.Date constructed from the given java.util.Date
     */
    public static java.sql.Date convertToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }


    /**
     * Convert the given java.sql.date into a java.sql.date of which all the time fields are set to 0.
     * 
     * @param date
     * @return
     */
    public static java.sql.Date clearTimeFields(java.sql.Date date) {
        Calendar timelessCal = new GregorianCalendar();
        timelessCal.setTime(date);
        timelessCal.set(Calendar.HOUR_OF_DAY, 0);
        timelessCal.set(Calendar.MINUTE, 0);
        timelessCal.set(Calendar.SECOND, 0);
        timelessCal.set(Calendar.MILLISECOND, 0);

        return new java.sql.Date(timelessCal.getTimeInMillis());
    }


    /**
     * Convert the given java.util.date into a java.util.date of which all the time fields are set to 0.
     * 
     * @param date
     * @return
     */
    public static java.util.Date clearTimeFields(java.util.Date date) {
        Calendar timelessCal = new GregorianCalendar();
        timelessCal.setTime(date);
        timelessCal.set(Calendar.HOUR_OF_DAY, 0);
        timelessCal.set(Calendar.MINUTE, 0);
        timelessCal.set(Calendar.SECOND, 0);
        timelessCal.set(Calendar.MILLISECOND, 0);

        return new java.util.Date(timelessCal.getTimeInMillis());
    }

    /**
     * @param startDateTime
     * @param endDateTime
     * @return the difference in days between the second timestamp and first
     */
    public static double getDifferenceInDays(Timestamp startDateTime, Timestamp endDateTime) {
        int difference = 0;

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDateTime);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDateTime);

        // First, get difference in whole days
        Calendar startCompare = Calendar.getInstance();
        startCompare.setTime(startDateTime);
        startCompare.set(Calendar.HOUR_OF_DAY, 0);
        startCompare.set(Calendar.MINUTE, 0);
        startCompare.set(Calendar.SECOND, 0);
        startCompare.set(Calendar.MILLISECOND, 0);

        Calendar endCompare = Calendar.getInstance();
        endCompare.setTime(endDateTime);
        endCompare.set(Calendar.HOUR_OF_DAY, 0);
        endCompare.set(Calendar.MINUTE, 0);
        endCompare.set(Calendar.SECOND, 0);
        endCompare.set(Calendar.MILLISECOND, 0);

        return (endCompare.getTimeInMillis() - startCompare.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * @param startDateTime
     * @param endDateTime
     * @return the difference in hours between the second timestamp and first
     */
    public static double getDifferenceInHours(Timestamp startDateTime, Timestamp endDateTime) {
        int difference = 0;

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDateTime);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDateTime);

        // First, get difference in whole days
        Calendar startCompare = Calendar.getInstance();
        startCompare.setTime(startDateTime);
        startCompare.set(Calendar.HOUR_OF_DAY, 0);
        startCompare.set(Calendar.MINUTE, 0);

        Calendar endCompare = Calendar.getInstance();
        endCompare.setTime(endDateTime);
        endCompare.set(Calendar.HOUR_OF_DAY, 0);
        endCompare.set(Calendar.MINUTE, 0);

        return (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / (60.0000 * 60.0000 * 1000.0000);
    }

    /**
     * 
     * This method is a utility method to create a new java.sql.Date in one line.
     * 
     * @param year
     * @param month
     * @param day
     * 
     * @return a populated java.sql.Date with the year, month, and day specified, and no values for hour, minute, second,
     *         millisecond
     * 
     */
    public static java.sql.Date newDate(Integer year, Integer month, Integer day) {

        // test for null arguments
        if (year == null) {
            throw new IllegalArgumentException("Argument 'year' passed in was null.");
        }
        if (month == null) {
            throw new IllegalArgumentException("Argument 'month' passed in was null.");
        }
        if (day == null) {
            throw new IllegalArgumentException("Argument 'day' passed in was null.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        return new java.sql.Date(calendar.getTimeInMillis());
    }

    /**
     * 
     * This method is a utility method to create a new java.sql.Date in one line.
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * 
     * @return a populated java.sql.Date with the year, month, hour, minute, and second populated, with no value for millisecond.
     * 
     */
    public static java.sql.Date newDate(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {

        // test for null arguments
        if (year == null) {
            throw new IllegalArgumentException("Argument 'year' passed in was null.");
        }
        if (month == null) {
            throw new IllegalArgumentException("Argument 'month' passed in was null.");
        }
        if (day == null) {
            throw new IllegalArgumentException("Argument 'day' passed in was null.");
        }
        if (hour == null) {
            throw new IllegalArgumentException("Argument 'hour' passed in was null.");
        }
        if (minute == null) {
            throw new IllegalArgumentException("Argument 'minute' passed in was null.");
        }
        if (second == null) {
            throw new IllegalArgumentException("Argument 'second' passed in was null.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.clear(Calendar.MILLISECOND);

        return new java.sql.Date(calendar.getTimeInMillis());
    }
}
