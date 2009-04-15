/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;
import org.kuali.kra.bo.UnitContactType;

/**
 * This class converts the ContactType type
 */
public class UnitTypeConverter implements FieldConversion {
    private static final long serialVersionUID = -3298305889586306843L;
    
    private static final String JAVA_TYPE_ERROR = "Java type not a UnitTypeConverter";
    private static final String SQL_TYPE_ERROR = "SQL type not a String";
    

    /**
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#javaToSql(java.lang.Object)
     */
    public Object javaToSql(Object source) {
        if(!(source instanceof UnitContactType)) {
            throw new ConversionException(JAVA_TYPE_ERROR);
        }
        
        return ((UnitContactType)source).name();
    }

    /**
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#sqlToJava(java.lang.Object)
     */
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
