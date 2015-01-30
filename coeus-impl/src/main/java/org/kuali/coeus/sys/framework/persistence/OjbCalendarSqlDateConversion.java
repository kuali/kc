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
package org.kuali.coeus.sys.framework.persistence;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;

import java.util.Calendar;
import java.sql.Date;

public class OjbCalendarSqlDateConversion implements FieldConversion {

    @Override
    public Date javaToSql(Object source) throws ConversionException {
        if (source == null) {
            return null;
        }

        return new Date(((Calendar) source).getTime().getTime());
    }

    @Override
    public Calendar sqlToJava(Object source) throws ConversionException {
        if (source == null) {
            return null;
        }

        final Calendar c = Calendar.getInstance();
        c.setTime((Date) source);
        return c;
    }
}
