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
package org.kuali.kra.infrastructure;

import java.math.BigDecimal;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.budget.BudgetDecimal;

/**
 * This class...
 */
public class OjbBudgetDecimalFieldConversion implements FieldConversion {

    /**
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#javaToSql(java.lang.Object)
     */
    public Object javaToSql(Object source) throws ConversionException {
        Object converted = source;

        if (source instanceof KualiDecimal) {
            converted = ((KualiDecimal) source).bigDecimalValue();
        }

        return converted;
    }

    /**
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#sqlToJava(java.lang.Object)
     */
    public Object sqlToJava(Object source) throws ConversionException {
        Object converted = source;

        if (source instanceof BigDecimal) {
            converted = new BudgetDecimal((BigDecimal) source);
        }

        return converted;
    }

    
    
    
}
