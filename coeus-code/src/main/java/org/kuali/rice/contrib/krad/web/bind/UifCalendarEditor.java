/**
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.rice.contrib.krad.web.bind;

import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.core.web.format.FormatException;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

/**
 * PropertyEditor converts between date display strings and <code>java.util.Calendar</code> objects
 * copied from <code>org.kuali.rice.krad.web.bind.UifDateEditor</code>
 */
public class UifCalendarEditor extends PropertyEditorSupport implements Serializable {
    private static final long serialVersionUID = 8122469337264797008L;

    /** The date time service. */
    private transient DateTimeService dateTimeService;

    /**
     * This overridden method uses the
     * <code>org.kuali.rice.core.api.datetime.DateTimeService</code> to convert
     * the date object to the display string.
     *
     * @see java.beans.PropertyEditorSupport#getAsText()
     */
    @Override
    public String getAsText() {
        if (this.getValue() == null) {
            return null;
        }
        if ("".equals(this.getValue())) {
            return null;
        }
        return getDateTimeService().toDateString(new Date(((java.util.Calendar) this.getValue()).getTimeInMillis()));
    }

    /**
     * Gets the date time service.
     *
     * @return the date time service
     */
    protected DateTimeService getDateTimeService() {
        if (this.dateTimeService == null) {
            this.dateTimeService = GlobalResourceLoader.getService(CoreConstants.Services.DATETIME_SERVICE);
        }
        return this.dateTimeService;
    }

    /**
     * This overridden method converts the display string to a
     * <code>java.sql.Date</code> object using the
     * <code>org.kuali.rice.core.api.datetime.DateTimeService</code>.
     *
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(convertToObject(text));
    }

    /**
     * Convert display text to <code>java.sql.Date</code> object using the
     * <code>org.kuali.rice.core.api.datetime.DateTimeService</code>.
     *
     * @param text
     *            the display text
     * @return the <code>java.sql.Date</code> object
     * @throws IllegalArgumentException
     *             the illegal argument exception
     */
    protected Object convertToObject(String text) throws IllegalArgumentException {
        try {
            // Allow user to clear dates
            if (text == null || text.equals("")) {
                return null;
            }

            Date result = getDateTimeService().convertToSqlDate(text);
            Calendar calendar = getDateTimeService().getCalendar(result);
            calendar.setTime(result);
            if (calendar.get(Calendar.YEAR) < 1000 && verbatimYear(text).length() < 4) {
                throw new FormatException("illegal year format", RiceKeyConstants.ERROR_DATE, text);
            }
            return calendar;
        } catch (ParseException e) {
            throw new FormatException("parsing", RiceKeyConstants.ERROR_DATE, text, e);
        }
    }

    /**
     * For a given user input date, this method returns the exact string the
     * user entered after the last slash. This allows the formatter to
     * distinguish between ambiguous values such as "/06" "/6" and "/0006"
     *
     * @param date
     * @return
     */
    private String verbatimYear(String date) {
        String result = "";

        int pos = date.lastIndexOf("/");
        if (pos >= 0) {
            result = date.substring(pos);
        }

        return result;
    }

}
