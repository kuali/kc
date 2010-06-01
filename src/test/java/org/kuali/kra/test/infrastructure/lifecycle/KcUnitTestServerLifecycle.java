/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.test.infrastructure.lifecycle;

import java.net.BindException;

import org.kuali.rice.core.config.Config;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.config.spring.ConfigFactoryBean;
import org.kuali.rice.core.util.RiceUtilities;
import org.kuali.rice.core.web.jetty.JettyServer;
import org.kuali.rice.test.web.HtmlUnitUtil;


public class KcUnitTestServerLifecycle extends KcUnitTestContextLifecycle {
    protected static final String CONTEXT_NAME = "/kc-dev";
    protected static final String RELATIVE_WEB_ROOT = "/src/main/webapp";
    private int port;
    private JettyServer jetty;
    
    private static boolean SERVER_STARTED = false;

    protected void doLaunch() throws Throwable {
        super.doLaunch();
        Config config = ConfigContext.getCurrentContextConfig();
        if (!SERVER_STARTED) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Loading Jetty Server...");
            }
            ConfigFactoryBean.CONFIG_OVERRIDE_LOCATION = TEST_CONFIG_XML;
            port = HtmlUnitUtil.getPort();
            jetty = new JettyServer(port, CONTEXT_NAME, RELATIVE_WEB_ROOT);
            jetty.setFailOnContextFailure(true);
            jetty.setTestMode(true);
            try {
                jetty.start();
                
            } catch (RuntimeException re) {
                // add some handling to make port conflicts more easily identified
                if (RiceUtilities.findExceptionInStack(re, BindException.class) != null) {
                    LOG.error("Jetty encountered BindException on port: " + jetty.getPort() + "; check logs for test failures or and the config for duplicate port specifications.");
                }
                throw re;
            }
            
            SERVER_STARTED = true;
        }        
    }

    protected void doShutdown() throws Throwable {
        if (SERVER_STARTED) {
            if (LOG.isInfoEnabled()) {
                LOG.info("... stopping Jetty Server");
            }
            jetty.stop();
            SERVER_STARTED = false;
        }
        super.doShutdown();
    }

    protected void doStart() throws Throwable {
        super.doStart();
    }

    protected void doStop() throws Throwable {
        super.doStop();
    }
}
