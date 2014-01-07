/*
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.test.infrastructure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.lifecycle.Lifecycle;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;

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

	public ApplicationServerLifecycle(int port, String contextName, String relativeWebappRoot, ConfigMode configMode) {
        server = new ApplicationServer(port, contextName, relativeWebappRoot);
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
	        LOG.error("Error shutting down Application Server " + server.getContextName() + " " + server.getRelativeWebappRoot(), e);
        }
        started = false;
    }
}
