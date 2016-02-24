/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Used for logging session information when it's created and destroyed. This allows us to monitor memory usage
 * of a session.
 */
public class SessionLoggingListener implements HttpSessionListener {
    private static final Log LOG = LogFactory.getLog(SessionLoggingListener.class);
    
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
     * Determine if the logging is allowed by using {@link Log#isInfoEnabled()}. Currently the <code>INFO</code> level
     * is required for logging here. It is possible that by extending this class and overriding {@link #isLoggingAllowed()} the 
     * required log level can be adjusted.
     *
     * @return the value of {@link Log#isInfoEnabled()} directly.
     */
    protected boolean isLoggingAllowed() {
        return LOG.isInfoEnabled();
    }
}
