package org.kuali.kra.test.infrastructure.lifecycle;

import org.kuali.rice.core.config.Config;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.config.JAXBConfigImpl;
import org.kuali.rice.core.config.spring.ConfigFactoryBean;

public class KcUnitTestConfigLifecycle extends KcUnitTestBaseLifecycle {
    private static final String TEST_CONFIG_XML = "classpath:META-INF/kc-test-config.xml";

    private static boolean CONFIG_LOADED = false;

    protected void doLaunch() throws Throwable {
        if (!CONFIG_LOADED) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Loading Configuration from " + TEST_CONFIG_XML);
            }
            ConfigFactoryBean.CONFIG_OVERRIDE_LOCATION = TEST_CONFIG_XML;
            Config config = new JAXBConfigImpl(TEST_CONFIG_XML, System.getProperties());
            config.parseConfig();
            ConfigContext.init(config);
            CONFIG_LOADED = true;
        }
    }

    protected void doShutdown() throws Throwable {
    }

    protected void doStart() throws Throwable {
    }

    protected void doStop() throws Throwable {
    }
}
