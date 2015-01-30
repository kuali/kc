/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.test.infrastructure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.lifecycle.Lifecycle;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;

import java.util.Collection;

/**
 * A lifecycle for running a Application web server.
 */
public class ApplicationServerLifecycle implements Lifecycle {
    private static final Log LOG = LogFactory.getLog(ApplicationServerLifecycle.class);

    /**
     * Enum for dealing with the webapp's Config
     */
    public static enum ConfigMode {
        /**
         * Do nothing
         */
        NONE,
        /**
         * Override the Config for the context class loader
         */
        OVERRIDE,
        /**
         * Merge the webapp's Config into the existing context class loader config
         */
        MERGE
    }

    private boolean started;
    private final ConfigMode configMode;
	private final ApplicationServer server;

	public ApplicationServerLifecycle(int port, String contextName, Collection<String> relativeWebappRoots, ConfigMode configMode) {
        server = new ApplicationServer(port, contextName, relativeWebappRoots);
        this.configMode = configMode;
	}

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void start() throws Exception {
        server.start();

	    final ClassLoader webappClassLoader = server.getContextClassLoader();
        final ResourceLoader rl = GlobalResourceLoader.getResourceLoader(webappClassLoader);
        if (rl == null) {
            throw new RuntimeException("Could not find resource loader for test harness web app for: " + webappClassLoader);
        }
        GlobalResourceLoader.addResourceLoader(rl);
	    final Config webappConfig = ConfigContext.getConfig(webappClassLoader);
        if (ConfigMode.OVERRIDE == configMode) {
            // this overrides the test harness classloader config with the webapp's config...
            ConfigContext.overrideConfig(Thread.currentThread().getContextClassLoader(), webappConfig);
        } else if (ConfigMode.MERGE == configMode) {
            final Config curCtxConfig = ConfigContext.getCurrentContextConfig();
            if (webappConfig != null) {
                curCtxConfig.putProperties(webappConfig.getProperties());
                curCtxConfig.putObjects(webappConfig.getObjects());
            }
        }

        started = true;
    }

    @Override
    public void stop() throws Exception {
	    LOG.info("Shutting down Application Server: " + server);
        try {
	    	if (server.isStarted()) {
                server.stop();
            }
        } catch (Exception e) {
	        LOG.error("Error shutting down Application Server " + server.getContextName() + " " + server.getRelativeWebappRoots(), e);
        }
        started = false;
    }
}
