/*
 * Copyright 2005-2010 The Kuali Foundation.
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Class with static methods wrapping {@link Log} methods. Automatically sets up Log for you. It's called the <code>BufferedLogger</code> because
 * it handles everything in a {@link StringBuilder} using {@link StringBuilder#append(CharSequence)} method<br/>
 * <br/>
 *  
 * To use these just do
 * <code>
 * import org.kuali.kra.logging.BufferedLogger.*
 * </code>
 * 
 * @see org.apache.commons.logging.Log
 */
public class BufferedLogger {
    
    /**
     * Applies a pattern with parameters to create a {@link String} used as a logging message
     *  
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     * @return Logging Message
     */
    private static final CharSequence getMessage(Object ... objs) {
        StringBuilder retval = new StringBuilder();
        
        for (Object obj : objs) {
            retval.append(obj);
        }
        
        return retval;
    }
    
    /**
     * Uses {@link StackTraceElement[]} from {@link Throwable} to determine the calling class. Then, the {@link Log} is retrieved for it by
     * convention
     * 
     * 
     * @return Log for the calling class
     */
    private static final Log getLogger() {
        try {
            return LogFactory.getLog(Class.forName(new Throwable().getStackTrace()[2].getClassName()));
        }
        catch (Exception e) {
            // This will never happen unless Java is broken
            return LogFactory.getLog(BufferedLogger.class);
        }
    }

    /**
     * Uses {@link StackTraceElement[]} from {@link Throwable} to determine the calling class. Then, the {@link Log} is retrieved for it by
     * convention. Just like {@link #getLogger()} except this is intended to be called directly from classes.
     * 
     * 
     * @return Log for the calling class
     */
    public static final Log logger() {
        try {
            return LogFactory.getLog(Class.forName(new Throwable().getStackTrace()[1].getClassName()));
        }
        catch (Exception e) {
            // This will never happen unless Java is broken
            return LogFactory.getLog(BufferedLogger.class);
        }
    }

    /**
     * Wraps {@link Log#trace(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void trace(Object ... objs) {
        Log log = getLogger();
        if (log.isTraceEnabled()) {
            log.trace(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Log#debug(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void debug(Object ... objs) {
        Log log = getLogger();
        if (log.isDebugEnabled()) {
            log.debug(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Log#info(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void info(Object ... objs) {
        Log log = getLogger();
        if (log.isInfoEnabled()) {
            log.info(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Log#warn(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void warn(Object ... objs) {
        Log log = getLogger();
        if (log.isWarnEnabled()) {
            log.warn(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Log#error(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void error(Object ... objs) {
        Log log = getLogger();
        if (log.isErrorEnabled()) {
            getLogger().error(getMessage(objs));
        }
    }
    
    /**
     * Wraps {@link Log#fatal(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void fatal(Object ... objs) {
        Log log = getLogger();
        if (log.isFatalEnabled()) {
            log.fatal(getMessage(objs));
        }
    }
}
