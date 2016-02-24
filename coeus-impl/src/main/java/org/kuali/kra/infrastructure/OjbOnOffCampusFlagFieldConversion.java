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
package org.kuali.kra.infrastructure;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;


public class OjbOnOffCampusFlagFieldConversion implements FieldConversion {

    @Override
    public Object javaToSql(Object source) throws ConversionException {
        if (source instanceof Boolean) {
            if (source != null) {
                Boolean b = (Boolean) source;
                return b.booleanValue() ? "N" : "F";
            }
            else {
                return null;
            }
        }
        else if (source instanceof String) {
            if ("true".equals(source)) {
                return "N";
            }
            else if ("false".equals(source)) {
                return "F";
            }
        }
        return source;
    }

    /**
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#sqlToJava(java.lang.Object)
     * 'N' means 'ON', returns true, 'F' means 'OFF' returns false
     */
    public Object sqlToJava(Object source) throws ConversionException {
        try {
            if (source instanceof String) {
                if (source != null) {
                    String s = (String) source;
                    return s.equalsIgnoreCase("N");
                }
                else {
                    return null;
                }
            }
            return source;
        }
        catch (Throwable t) {
            throw new RuntimeException("I have exploded converting types", t);
        }
    }

    
    
    
}
