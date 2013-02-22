/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.web.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drools.core.util.StringUtils;
import org.kuali.rice.core.web.format.FormatException;
import org.kuali.rice.core.web.format.Formatter;

/**
 * 
 * This class creates a Time format function.  
 */
public class TimeFormatter extends Formatter {
    private static final long serialVersionUID = 1L;

    private static final Pattern TIME_PATTERN_12_HOUR = Pattern.compile("(0?[1-9]|1[0-2])" + // hour
            "(?:(?::|\\.)([0-5][0-9]))?" + // minute (optional)
            "(?:\\s*(([ap]m?|[ap]\\.m\\.)))?", // AM/PM (optional)
            Pattern.CASE_INSENSITIVE);
    private static final Pattern TIME_PATTERN_24_HOUR = Pattern.compile("([01][0-9]|2[0-3])" + // hour
            "(?:(?::|\\.)?([0-5][0-9]))" + // minute (optional)
            "(?:\\s*(([ap]m?|[ap]\\.m\\.)))?", // AM/PM (optional, tecnhincally irrelevant, but the user might put it in)
            Pattern.CASE_INSENSITIVE);

    @Override
    protected Object convertToObject(String input) {
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
    
            throw new FormatException("Invalid time input: \"" + input + "\"");
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

        return intHour + ":" + minute + " " + amPmMarker;
    }
}
