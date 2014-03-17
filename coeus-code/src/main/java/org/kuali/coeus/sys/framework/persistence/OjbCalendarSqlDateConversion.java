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
