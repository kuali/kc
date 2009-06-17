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

import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.FATAL;
import static org.apache.log4j.Level.WARN;

import org.apache.log4j.Logger;


/**
 * Class with static methods wrapping {@link Logger} methods. Automatically sets up logger for you. It's called the <code>BufferedLogger</code> because
 * it handles everything in a {@link StringBuilder} using {@link StringBuilder#append(CharSequence)} method<br/>
 * <br/>
 *  
 * To use these just do
 * <code>
 * import org.kuali.kra.logging.BufferedLogger.*
 * </code>
 * 
 * @see org.apache.log4j.Logger
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
     * Uses {@link StackTraceElement[]} from {@link Throwable} to determine the calling class. Then, the {@link Logger} is retrieved for it by
     * convention
     * 
     * 
     * @return Logger for the calling class
     */
    private static final Logger getLogger() {
        try {
            return Logger.getLogger(Class.forName(new Throwable().getStackTrace()[3].getClassName()));
        }
        catch (Exception e) {
            // This will never happen unless Java is broken
            return Logger.getLogger(BufferedLogger.class);
        }
    }

    /**
     * Uses {@link StackTraceElement[]} from {@link Throwable} to determine the calling class. Then, the {@link Logger} is retrieved for it by
     * convention. Just like {@link #getLogger()} except this is intended to be called directly from classes.
     * 
     * 
     * @return Logger for the calling class
     */
    public static final Logger logger() {
        try {
            return Logger.getLogger(Class.forName(new Throwable().getStackTrace()[1].getClassName()));
        }
        catch (Exception e) {
            // This will never happen unless Java is broken
            return Logger.getLogger(BufferedLogger.class);
        }
    }

    /**
     * Wraps {@link Logger#trace(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void trace(Object ... objs) {
        Logger log = getLogger();
        if (log.isTraceEnabled()) {
            log.trace(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Logger#debug(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void debug(Object ... objs) {
        Logger log = getLogger();
        if (log.isDebugEnabled()) {
            log.debug(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Logger#info(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void info(Object ... objs) {
        Logger log = getLogger();
        if (log.isInfoEnabled()) {
            log.info(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Logger#warn(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void warn(Object ... objs) {
        Logger log = getLogger();
        if (log.isEnabledFor(WARN)) {
            log.warn(getMessage(objs));
        }
    }

    /**
     * Wraps {@link Logger#error(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void error(Object ... objs) {
        Logger log = getLogger();
        if (log.isEnabledFor(ERROR)) {
            getLogger().error(getMessage(objs));
        }
    }
    
    /**
     * Wraps {@link Logger#fatal(String)}
     * 
     * @param pattern to format against
     * @param objs an array of objects used as parameters to the <code>pattern</code>
     */
    public static final void fatal(Object ... objs) {
        Logger log = getLogger();
        if (log.isEnabledFor(FATAL)) {
            log.fatal(getMessage(objs));
        }
    }
}
