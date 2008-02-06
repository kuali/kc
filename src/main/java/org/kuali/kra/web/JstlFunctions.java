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
package org.kuali.kra.web;

import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesFinder;

import static java.lang.Class.forName;
import static org.apache.commons.beanutils.PropertyUtils.setProperty;

/**
 * Full of static methods for JSTL function access.
 * 
 * @author $Author: lprzybyl $
 * @version $Revision: 1.1 $
 */
public class JstlFunctions {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(JstlFunctions.class);

    /**
     * 
     * 
     * @param valuesFinderClassName
     * @param params mapped parameters to make it simpler for <code>{@link PropertyUtils#setProperty(Object,String,Object)}</code>
     * @return List of <code>{@link KeyLabelPair}</code> instances
     */
    public static List getOptionList(String valuesFinderClassName, Map params) {
        return setupValuesFinder(valuesFinderClassName, (Map<String, String>) params).getKeyValues();
    }
    
    /**
     * 
     * 
     * @param valuesFinderClassName
     * @param params
     * @return KeyValuesFinder
     */
    private static KeyValuesFinder setupValuesFinder(String valuesFinderClassName, Map<String, String> params) {
        KeyValuesFinder retval = null;
        LOG.info("In setupValuesFinder");
        try {
            retval = (KeyValuesFinder) forName(valuesFinderClassName).newInstance();                        
        }
        catch (ClassNotFoundException cnfe) {
            LOG.warn("Could not find valuesFinder class " +  valuesFinderClassName + " in " + buildTraceMessage(cnfe));
        }
        catch (InstantiationException e) {
            LOG.warn("Could not instantiate valuesFinder class " +  valuesFinderClassName + " in " + buildTraceMessage(e));
        }
        catch (IllegalAccessException e) {
            LOG.warn("Could not instantiate valuesFinder class " +  valuesFinderClassName + " in " + buildTraceMessage(e));
        }
        
        LOG.info("Setting params " + params);
        
        if (retval != null && params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    setProperty(retval, entry.getKey(), entry.getValue());
                }
                catch (Exception e) {
                    LOG.warn("Could not set property " +  entry.getKey() + " in " + buildTraceMessage(e));
                }
            }
        }

        return retval;
    }

    /**
     * Get the stack trace from a <code>{@link Throwable}</code> and create a log message from it for tracing purposes
     * 
     * @param thrownObj 
     * @return String log message
     */
    private static String buildTraceMessage(Throwable thrownObj) {
        return thrownObj.getStackTrace()[0].getClassName() + "#" 
            + thrownObj.getStackTrace()[0].getMethodName() + ":" 
                + thrownObj.getStackTrace()[0].getLineNumber() + " " + thrownObj.getClass().getSimpleName();
    }
}
