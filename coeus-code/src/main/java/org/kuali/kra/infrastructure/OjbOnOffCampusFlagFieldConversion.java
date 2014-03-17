/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
