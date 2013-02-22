package org.kuali.kra.infrastructure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drools.core.util.StringUtils;
import org.kuali.rice.core.web.format.FormatException;
import org.kuali.rice.core.web.format.Formatter;


public class TimeFormatter extends Formatter {
    private static final long serialVersionUID = 1L;

    /*
     * private static final Pattern TIME_PATTERN_12_HOUR = Pattern.compile("(0?[1-9]|1[0-2])" + // hour "(?:(?::|\\.)([0-5][0-9]))?"
     * + // minute (optional) "(?:\\s*(([ap]m?|[ap]\\.m\\.)))?", // AM/PM (optional) Pattern.CASE_INSENSITIVE); 
     * private static final Pattern TIME_PATTERN_24_HOUR = Pattern.compile("(0?[0-9]|1[0-9]|2[0-3])" + // hour "(?:(?::|\\.)([0-5][0-9]))?", // minute
     * (optional) Pattern.CASE_INSENSITIVE);
     */

    private static final Pattern TIME_PATTERN_12_HOUR = Pattern.compile("(0?[1-9]|1[0-2])" + // hour
            "(?:(?::|\\.)([0-5][0-9]))?" + // minute (optional)
            "(?:\\s*(([ap]m?|[ap]\\.m\\.)))?", // AM/PM (optional)
            Pattern.CASE_INSENSITIVE);
    private static final Pattern TIME_PATTERN_24_HOUR = Pattern.compile("([01][0-9]|2[0-3])" + // hour
            "(?:(?::|\\.)?([0-5][0-9]))" + // minute (optional)
            "(?:\\s*(([ap]m?|[ap]\\.m\\.)))?", // AM/PM (optional, tecnhincally irrelevant, but the user might put it in)
            Pattern.CASE_INSENSITIVE);

    @Override
    public Object convertToObject(String input) {
        if (!StringUtils.isEmpty(input)) {
            input = input.trim();
    
            Matcher matcher = TIME_PATTERN_12_HOUR.matcher(input);
            if (matcher.matches()) {
                return parseTwelveHourTime(matcher);
            }
    
            matcher = TIME_PATTERN_24_HOUR.matcher(input);
            if (matcher.matches()) {
                return parseTwentyFourHourTime(matcher);
            }
            return Constants.INVALID_TIME;
        } else {
            return "";
        }

    }

    private String parseTwelveHourTime(Matcher matcher) {
        String hour = matcher.group(1);
        if (hour.startsWith("0")) {
            hour = hour.substring(1); // strip off the leading 0
        }

        String minute = matcher.group(2);
        if (minute == null) {
            minute = "00";
        }

        String amPmMarker = matcher.group(3);
        if (amPmMarker == null) {
            amPmMarker = "12".equals(hour) ? "PM" : "AM";
        }
        else {
            amPmMarker = amPmMarker.replaceAll("\\.", "").toUpperCase();
            if (amPmMarker.length() == 1) {
                amPmMarker += "M";
            }
        }

        return hour + ":" + minute + " " + amPmMarker;
    }

    private String parseTwentyFourHourTime(Matcher matcher) {
        String hour = matcher.group(1);
        String minute = matcher.group(2);

        int intHour = Integer.parseInt(hour);
        String amPmMarker = "AM";
        if (intHour == 0) {
            intHour = 12;
        }
        else if (intHour >= 12) {
            amPmMarker = "PM";
            intHour -= 12;
        }
        if (minute == null) {
            minute = "00";
        }

        return intHour + ":" + minute + " " + amPmMarker;
    }
}
