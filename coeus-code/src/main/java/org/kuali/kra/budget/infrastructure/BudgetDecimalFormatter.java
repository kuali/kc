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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.core.web.format.BigDecimalFormatter;
import org.kuali.rice.core.web.format.FormatException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * This class makes a BudgetDecimal from a String
 */
public class BudgetDecimalFormatter extends BigDecimalFormatter {
    private static final long serialVersionUID = 8395988033199649377L;
    private static Log LOG = LogFactory.getLog(BudgetDecimalFormatter.class);

    public BudgetDecimalFormatter() {
        super();
//        if (this.settings == null) {
//            this.settings = new HashMap();
//        }
//        this.settings.put(CurrencyFormatter.SHOW_SYMBOL, "false");
    }
    /**
     * Overriden to produce a BudgetDecimal
     * @see org.kuali.core.web.format.BigDecimalFormatter#convertToObject(java.lang.String)
     */
    @Override
    protected Object convertToObject(String target) {
        
        return new BudgetDecimal(((BigDecimal)super.convertToObject(target)));
    }
    
    
    /**
     * Returns a string representation of its argument formatted as a currency value.
     */
    public Object format(Object obj) {
        
        if (LOG.isTraceEnabled()) {
            LOG.trace("format '" + obj + "'");
        }

        if (obj == null)
            return null;

        //NumberFormat formatter = NumberFormat.getCurrencyInstance();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        //if (formatter instanceof DecimalFormat) {
        ((DecimalFormat) formatter).setParseBigDecimal(true);
        ((DecimalFormat) formatter).setDecimalSeparatorAlwaysShown(true);
        //}
        String string = null;

        try {
            BudgetDecimal number = (BudgetDecimal) obj;
            string = formatter.format(number.doubleValue());
        }
        catch (IllegalArgumentException e) {
            throw new FormatException("formatting", RiceKeyConstants.ERROR_BIG_DECIMAL, obj.toString(), e);
        }
        catch (ClassCastException e) {
            throw new FormatException("formatting", RiceKeyConstants.ERROR_BIG_DECIMAL, obj.toString(), e);
        }

        if (obj.toString().length() > 15) {
            return obj.toString();
        }
        if (StringUtils.isNotBlank(string)) {
            if (string.indexOf(".") == string.length() - 1) {
                string = string +"00";
            } else if (string.indexOf(".") == string.length() - 2) {
                string = string +"0";
            }
        }
        return string;
    }
    
   
}
