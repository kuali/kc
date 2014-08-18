/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.framework.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.api.model.ScaleThreeDecimal;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.core.web.format.BigDecimalFormatter;
import org.kuali.rice.core.web.format.FormatException;
import org.kuali.rice.core.web.format.Formatter;

import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a central place for application-wide {@link Formatter Formatter} registration.
 * 
 * <p>
 * It's important to have Formatter registration happen early and in a centralized fashion so that our application
 * will always be in a state where Formatters are registered when they should be.
 * </p>
 * 
 * <p>
 * Without this central approach, we are relying on the registration code to be executed before a page is accessed
 * requiring a specific formatter.  This has been troublesome for KC in the past. 
 * </p>
 */
public class GlobalFormatterRegistry implements PlugIn {

    private static final Map<Class<?>, Class<? extends Formatter>> KC_FORMATTERS;
    static {
        final Map<Class<?>, Class<? extends Formatter>> temp = new HashMap<Class<?>, Class<? extends Formatter>>();
        temp.put(ScaleTwoDecimal.class, ScaleTwoDecimalFormatter.class);
        temp.put(ScaleThreeDecimal.class, ScaleThreeDecimalFormatter.class);
        
        KC_FORMATTERS = Collections.unmodifiableMap(temp);
    }
    
    
    private ActionServlet servlet;
    private ModuleConfig config;
    
    private final Map<Class<?>, Class<? extends Formatter>> previousRegisters = new HashMap<Class<?>, Class<? extends Formatter>>();
    
    @Override
    public void init(final ActionServlet aServlet, final ModuleConfig aConfig) throws ServletException {
        servlet = aServlet;
        config = aConfig;
        
        register();
    }
    
    @Override
    public void destroy() {
        servlet = null;
        config = null;
        
        deRegister();
    }
    
    /**
     * Saves the current Formatter registration then registers KC specific formatters possibly overwriting a registration.
     */
    private void register() {
        for (Map.Entry<Class<?>, Class<? extends Formatter>> entry : KC_FORMATTERS.entrySet()) {
            previousRegisters.put(entry.getKey(), Formatter.formatterForType(entry.getKey()));
            Formatter.registerFormatter(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Removes any KC Formatter registration and restores things they way they were.
     */
    private void deRegister() {
        for (Map.Entry<Class<?>, Class<? extends Formatter>> entry : previousRegisters.entrySet()) {
            Formatter.registerFormatter(entry.getKey(), entry.getValue());
        }
        
        previousRegisters.clear();
    }

    public static class ScaleThreeDecimalFormatter extends BigDecimalFormatter {
        private static final long serialVersionUID = 4658319828434873892L;

        /**
         * Overidden to create a {@link ScaleThreeDecimal} from a String
         */
        @Override
        protected Object convertToObject(String target) {
            return new ScaleThreeDecimal((BigDecimal)super.convertToObject(target));
        }

        @Override
        public Object format(Object obj) {
            return super.format(super.convertToObject(obj.toString()));
        }
    }

    /**
     * This class makes a {@link ScaleTwoDecimal} from a String
     */
    public static class ScaleTwoDecimalFormatter extends BigDecimalFormatter {
        private static final long serialVersionUID = 8395988033199649377L;
        private static Log LOG = LogFactory.getLog(ScaleTwoDecimalFormatter.class);

        public ScaleTwoDecimalFormatter() {
            super();
        }

        @Override
        protected Object convertToObject(String target) {

            return new ScaleTwoDecimal(((BigDecimal)super.convertToObject(target)));
        }


        /**
         * Returns a string representation of its argument formatted as a currency value.
         */
        @Override
        public Object format(Object obj) {

            if (LOG.isTraceEnabled()) {
                LOG.trace("format '" + obj + "'");
            }

            if (obj == null)
                return null;

            NumberFormat formatter = NumberFormat.getNumberInstance();
            ((DecimalFormat) formatter).setParseBigDecimal(true);
            ((DecimalFormat) formatter).setDecimalSeparatorAlwaysShown(true);
            String string;

            try {
                ScaleTwoDecimal number = (ScaleTwoDecimal) obj;
                string = formatter.format(number.doubleValue());
            }
            catch (IllegalArgumentException|ClassCastException e) {
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
}
