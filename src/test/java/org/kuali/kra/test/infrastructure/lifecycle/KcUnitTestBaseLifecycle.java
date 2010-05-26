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
package org.kuali.kra.test.infrastructure.lifecycle;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * An abstract lifecycle.
 * 
 * This abstract lifecyle does the following:
 * <ul>
 * <li>wraps start, stop, launch and shutdown exceptions</li>
 * <li>logs lifecycle events</li>
 * <li>calculates and logs the amount of time a lifecycle stage takes</li>
 * <li>simplifies the implementation of a lifecycle through templating</li>
 * <li>does not allow lifecycle stages to be called at incorrect times (twice without the opposite in between)</li>
 * </ul>
 */
public abstract class KcUnitTestBaseLifecycle implements KcUnitTestLifecycle {
    // non static logger to allow it to be named after the runtime class
    protected final Log LOG = LogFactory.getLog(this.getClass());

    private boolean started;
    private boolean launched;

    /**
     * Hook to execute launch logic.
     * 
     * @throws Throwable just propagate any exceptions
     */
    protected abstract void doLaunch() throws Throwable;

    /**
     * Hook to execute shutdown logic.
     * 
     * @throws Throwable just propagate any exceptions
     */
    protected abstract void doShutdown() throws Throwable;

    /**
     * Hook to execute start logic.
     * 
     * @throws Throwable just propagate any exceptions
     */
    protected abstract void doStart() throws Throwable;

    /**
     * Hook to execute stop logic.
     * 
     * @throws Throwable just propagate any exceptions
     */
    protected abstract void doStop() throws Throwable;

    /** {@inheritDoc} */
    public final void launch() {
        if (this.launched) {
            throw new IllegalStateException("lifecycle already launched");
        }

        final StopWatch watch = new StopWatch();

        if (LOG.isDebugEnabled()) {
            watch.start();
            LOG.debug("launching lifecycle");
        }

        try {
            doLaunch();
            launched = true;
        }
        catch (Throwable e) {
            launched = false;
            throw new KcLifecycleException(e);
        }

        if (LOG.isDebugEnabled()) {
            watch.stop();
            LOG.debug("lifecycle launched in " + watch + " time");
        }
    }


    /** {@inheritDoc} */
    public final void shutdown() {
        if (!this.launched) {
            throw new IllegalStateException("lifecycle already shutdown");
        }

        final StopWatch watch = new StopWatch();

        if (LOG.isDebugEnabled()) {
            watch.start();
            LOG.debug("shuttingdown lifecycle");
        }

        try {
            doShutdown();
            launched = false;
        }
        catch (Throwable e) {
            launched = false;
            throw new KcLifecycleException(e);
        }

        if (LOG.isDebugEnabled()) {
            watch.stop();
            LOG.debug("lifecycle shutdown in " + watch + " time");
        }
    }

    /** {@inheritDoc} */
    public final void start() {
        if (this.started) {
            throw new IllegalStateException("lifecycle already started");
        }

        final StopWatch watch = new StopWatch();

        if (LOG.isDebugEnabled()) {
            watch.start();
            LOG.debug("starting lifecycle");
        }

        try {
            doStart();
            started = true;
        }
        catch (Throwable e) {
            started = false;
            throw new KcLifecycleException(e);
        }

        if (LOG.isDebugEnabled()) {
            watch.stop();
            LOG.debug("lifecycle started in " + watch + " time");
        }
    }

    /** {@inheritDoc} */
    public final void stop() {
        if (!this.started) {
            throw new IllegalStateException("lifecycle already stopped");
        }

        final StopWatch watch = new StopWatch();

        if (LOG.isDebugEnabled()) {
            watch.start();
            LOG.debug("stopping lifecycle");
        }

        try {
            doStop();
            started = false;
        }
        catch (Throwable e) {
            started = false;
            throw new KcLifecycleException(e);
        }

        if (LOG.isDebugEnabled()) {
            watch.stop();
            LOG.debug("lifecycle stopped in " + watch + " time");
        }
    }

    /** {@inheritDoc} */
    public final boolean isStarted() {
        return this.started;
    }

    /** {@inheritDoc} */
    public final boolean isLaunched() {
        return this.launched;
    }

    /** exception that wraps any lifecycle failures. */
    protected static class KcLifecycleException extends RuntimeException {
        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 6680874845872733891L;

        public KcLifecycleException(Throwable t) {
            super(t);
        }
    }
}
