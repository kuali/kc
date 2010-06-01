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

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.config.Config;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.config.JAXBConfigImpl;
import org.kuali.rice.core.config.spring.ConfigFactoryBean;

/**
 * This class...
 */
public class KcUnitTestConfigLifecycle extends KcUnitTestBaseLifecycle {
    protected static final String TEST_CONFIG_XML = "classpath:META-INF/kc-test-config.xml";

    private static boolean CONFIG_LOADED = false;

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doLaunch()
     */
    protected void doLaunch() throws Throwable {
        if (!CONFIG_LOADED) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Loading Configuration from " + TEST_CONFIG_XML);
            }
            ConfigFactoryBean.CONFIG_OVERRIDE_LOCATION = TEST_CONFIG_XML;
            List<String> configLocations = new ArrayList<String>();
            configLocations.add("classpath:META-INF/test-config-defaults.xml");
            configLocations.add(TEST_CONFIG_XML);
            Config config = new JAXBConfigImpl(configLocations, System.getProperties());
            config.parseConfig();
            ConfigContext.init(config);
            CONFIG_LOADED = true;
        }
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doShutdown()
     */
    protected void doShutdown() throws Throwable {
        // no-op
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doStart()
     */
    protected void doStart() throws Throwable {
        // no-op
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doStop()
     */
    protected void doStop() throws Throwable {
        // no-op
    }
}
