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
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.math.BigDecimal;


public class OjbScaleTwoDecimalFieldConversion implements FieldConversion {

    @Override
    public Object javaToSql(Object source) throws ConversionException {
        Object converted = source;

        if (source instanceof ScaleTwoDecimal) {
            converted = ((ScaleTwoDecimal) source).bigDecimalValue();
        }

        return converted;
    }

    @Override
    public Object sqlToJava(Object source) throws ConversionException {
        Object converted = source;

        if (source instanceof BigDecimal) {
            converted = new ScaleTwoDecimal((BigDecimal) source);
        }

        return converted;
    }

    
    
    
}
