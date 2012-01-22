/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.web.struts.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;
import org.kuali.kra.infrastructure.RateDecimalFormatter;
import org.kuali.rice.core.web.format.Formatter;

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
        temp.put(BudgetDecimal.class, BudgetDecimalFormatter.class);
        temp.put(RateDecimal.class, RateDecimalFormatter.class);
        
        KC_FORMATTERS = Collections.unmodifiableMap(temp);
    }
    
    
    private ActionServlet servlet;
    private ModuleConfig config;
    
    private final Map<Class<?>, Class<? extends Formatter>> previousRegisters = new HashMap<Class<?>, Class<? extends Formatter>>();
    
    /** {@inheritDoc} */
    public void init(final ActionServlet aServlet, final ModuleConfig aConfig) throws ServletException {
        servlet = aServlet;
        config = aConfig;
        
        register();
    }
    
    /** {@inheritDoc} */
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
}
