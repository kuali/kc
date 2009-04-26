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
package org.kuali.kra.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Used for logging session information when it's created and destroyed. This allows us to monitor memory usage
 * of a session.
 */
public class SessionLoggingListener implements HttpSessionListener {
    private static final Logger LOG = Logger.getLogger(SessionLoggingListener.class);
    
    /**
     * Triggered when the session is created. Reports the amount of memory currently being used at the time the session
     * is created. It then stores that number to compare when the session becomes destroyed.
     * 
     *
     * @param se the {@link HttpSessionEvent}
     */
    public void sessionCreated(HttpSessionEvent se) {
        if (isLoggingAllowed()) {
            logSessionStart(se);
        }
    }

    
    /**
     * Triggered when the session is wiped out. Recovers amount of memory used when the session was created to compare
     * to the current amount of memory used.
     *
     * @param se the {@link HttpSessionEvent}
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        if (isLoggingAllowed()) {
            logSessionEnd(se);
        }
    }

    /**
     * Implementation for logging the end of an {@link HttpSession}. Override this if you want different logging at the end of the session.
     *
     * @param event the {@link HttpSessionEvent}
     */
    protected void logSessionEnd(HttpSessionEvent event) { 
        long startMem = Runtime.getRuntime().freeMemory();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long originalMemory = (Long) event.getSession().getAttribute("startingMemory");
        
        String difference = "";
        if (originalMemory < usedMemory) {
            difference = "Memory usage increased by " + (usedMemory - originalMemory);
        }
        else {
            difference = "Memory usage decreased by " + (originalMemory - usedMemory);
        }
        
        LOG.info("Session was just destroyed : " + usedMemory + " memory used. Originally created with " 
                 + originalMemory + " memory used. "+ difference);
    }
    
    /**
     * Implementation for logging the start of an {@link HttpSession}. Override this if you want different logging at the start of the session.
     *
     * @param event the {@link HttpSessionEvent}
     */
    protected  void logSessionStart(HttpSessionEvent event) {
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        event.getSession().setAttribute("startingMemory", usedMemory);
        LOG.info("Session was just created : " + usedMemory + " memory used");
    }

    /**
     * Determine if the logging is allowed by using {@link Logger#isInfoEnabled()}. Currently the <code>INFO</code> level
     * is required for logging here. It is possible that by extending this class and overriding {@link #isLoggingAllowed()} the 
     * required log level can be adjusted.
     *
     * @return the value of {@link Logger#isInfoEnabled()} directly.
     */
    protected boolean isLoggingAllowed() {
        return LOG.isInfoEnabled();
    }
}
