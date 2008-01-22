/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.pojo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.util.TypeUtils;

/**
 * Base class for all KIM Pojo classes.  The KIM Pojo classes are the objects
 * that are transfered between KIM and users of KIM.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class Pojo implements Serializable {

    /**
     * Constructs a Pojo.
     */
    protected Pojo() {
    }

    /**
     * Builds a string from a set of field values.
     * @param fieldValues the field values
     * @return a string representation of the field values
     */
    protected final String toStringBuilder(LinkedHashMap<String, Object> fieldValues) {
        String built = null;
        String className = StringUtils.uncapitalize(StringUtils.substringAfterLast(this.getClass().getName(), "."));

        if ((fieldValues == null) || fieldValues.isEmpty()) {
            built = super.toString();
        }
        else {

            StringBuffer prefix = new StringBuffer(className);
            StringBuffer suffix = new StringBuffer("=");

            prefix.append("(");
            suffix.append("(");
            for (Iterator<Map.Entry<String, Object>> i = fieldValues.entrySet().iterator(); i.hasNext();) {
                Map.Entry<String, Object> e = i.next();

                String fieldName = e.getKey().toString();
                Object fieldValue = e.getValue();

                String fieldValueString = String.valueOf(e.getValue()); // prevent NullPointerException;


                if ((fieldValue == null) || TypeUtils.isSimpleType(fieldValue.getClass())) {
                    prefix.append(fieldName);
                    suffix.append(fieldValueString);
                }
                else {
                    prefix.append("{");
                    prefix.append(fieldName);
                    prefix.append("}");

                    suffix.append("{");
                    suffix.append(fieldValueString);
                    suffix.append("}");
                }

                if (i.hasNext()) {
                    prefix.append(",");
                    suffix.append(",");
                }
            }
            prefix.append(")");
            suffix.append(")");

            built = prefix.toString() + suffix.toString();
        }

        return built;
    }
   
    /**
     * Get a map of the field values.
     * @return the field values
     */
    abstract protected LinkedHashMap<String, Object> toStringMapper();

    /**
     * @see java.lang.Object#toString()
     */
    public final String toString() {
        if (!StringUtils.contains(this.getClass().getName(), "$$")) {
            return toStringBuilder(toStringMapper());
        }
        else {
            return "Proxy: " + this.getClass().getName();
        }
    }
}
