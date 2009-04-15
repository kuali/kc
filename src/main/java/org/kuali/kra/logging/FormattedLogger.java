/*
 * Copyright 2006-2009 The Kuali Foundation.
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
package org.kuali.kra.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class with static methods wrapping {@link Log} methods. Automatically sets up logger for you. It's called the <code>FormattedLogger</code> because
 * it handles everything in ansi-C standard printf format. For example, <code>printf("The epoch time is now %d", new Date().getTime())</code>.<br/>
 * <br/>
 *  
 * To use these just do
 * <code>
 * import org.kuali.kra.logging.FormattedLogger.*
 * </code>
 * 
 * @see org.apache.commons.logging.Log
 * 
 * @see org.apache.commons.logging.LogFactory
 */
public class FormattedLogger {
    
    /**
     * Applies a pattern with parameters to create a {@link String} used as a logging message
     *  
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     * @return Logging Message
     */
    private static final String getMessage(String pattern, Object ... objs) {
        StringWriter retval = new StringWriter();
        PrintWriter writer = new PrintWriter(retval);
        
        writer.printf(pattern, objs);
        
        return retval.toString();
    }
    
    /**
     * Uses {@link StackTraceElement[]} from {@link Throwable} to determine the calling class. Then, the {@link Log} is retrieved for it by
     * convention
     * 
     * 
     * @return Log for the calling class
     */
    private static final Log getLog() {
        try {
            return LogFactory.getLog(Class.forName(new Throwable().getStackTrace()[3].getClassName()));
        }
        catch (Exception e) {
            return LogFactory.getLog(FormattedLogger.class);
        }
    }

    /**
     * Wraps {@link Log#trace(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void trace(String pattern, Object ... objs) {
        System.out.printf(pattern, objs);
        System.out.println();
    }

    /**
     * Wraps {@link Log#debug(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void debug(String pattern, Object ... objs) {
        Log log = getLog();
        if (log.isDebugEnabled()) {
            log.debug(getMessage(pattern, objs));
        }
    }

    /**
     * Wraps {@link Log#info(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void info(String pattern, Object ... objs) {
        Log log = getLog();
        if (log.isInfoEnabled()) {
            log.info(getMessage(pattern, objs));
        }
    }

    /**
     * Wraps {@link Log#warn(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void warn(String pattern, Object ... objs) {
        Log log = getLog();
        if (log.isWarnEnabled()) {
            log.warn(getMessage(pattern, objs));
        }
    }

    /**
     * Wraps {@link Log#error(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void error(String pattern, Object ... objs) {
        Log log = getLog();
        if (log.isErrorEnabled()) {
            getLog().error(getMessage(pattern, objs));
        }
    }
    
    /**
     * Wraps {@link Log#fatal(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void fatal(String pattern, Object ... objs) {
        Log log = getLog();
        if (log.isFatalEnabled()) {
            log.fatal(getMessage(pattern, objs));
        }
    }

}
