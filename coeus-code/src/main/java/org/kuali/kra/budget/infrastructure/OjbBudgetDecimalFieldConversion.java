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
package org.kuali.kra.budget.infrastructure;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;
import org.kuali.kra.budget.BudgetDecimal;

import java.math.BigDecimal;


public class OjbBudgetDecimalFieldConversion implements FieldConversion {

    @Override
    public Object javaToSql(Object source) throws ConversionException {
        Object converted = source;

        if (source instanceof BudgetDecimal) {
            converted = ((BudgetDecimal) source).bigDecimalValue();
        }

        return converted;
    }

    @Override
    public Object sqlToJava(Object source) throws ConversionException {
        Object converted = source;

        if (source instanceof BigDecimal) {
            converted = new BudgetDecimal((BigDecimal) source);
        }

        return converted;
    }

    
    
    
}
