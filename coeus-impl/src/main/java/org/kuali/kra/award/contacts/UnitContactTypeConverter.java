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
package org.kuali.kra.award.contacts;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;
import org.kuali.coeus.common.framework.unit.UnitContactType;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;

/**
 * This class converts the UnitType type
 */
public class UnitContactTypeConverter implements FieldConversion {
    private static final long serialVersionUID = -3298305889586306843L;
    
    private static final String JAVA_TYPE_ERROR = "Java type not a UnitContactType";
    private static final String SQL_TYPE_ERROR = "SQL type not a String";
    

    @Override
    public Object javaToSql(Object source) {
        if(!(source instanceof UnitContactType || source instanceof UnitAdministratorType)) {
            throw new ConversionException(JAVA_TYPE_ERROR);
        }
        
        return ((UnitContactType)source).name();
    }

    @Override
    public Object sqlToJava(Object source) {
        if(!(source instanceof String)) {
            throw new ConversionException(SQL_TYPE_ERROR);
        }
        
        UnitContactType unitContactType;
        try {
            unitContactType = UnitContactType.valueOf((String) source);
        } catch(IllegalArgumentException e) {
            throw new ConversionException(e.getMessage(), e);
        }
         
        return unitContactType;
    }

}
