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
package org.kuali.coeus.sys.framework.controller;

import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.core.web.format.TimestampAMPMFormatter;

import java.sql.Timestamp;
import java.util.Calendar;

@Deprecated
public class CalendarTimestampAMPMFormatter extends Formatter {

    private TimestampAMPMFormatter formatter = new TimestampAMPMFormatter();

    @Override
    public Object convertToObject(String target) {
        if (target == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime((Timestamp) formatter.convertToObject(target));
        return c;
    }

    @Override
    public Object format(Object value) {
        if (value == null) {
            return "";
        }
        return formatter.format(new Timestamp(((Calendar) value).getTime().getTime()));
    }
}
