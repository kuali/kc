package org.kuali.kra.test.infrastructure.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle.BROWSER_ADDRESS;
import static org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle.BROWSER_PROTOCOL;
import static org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle.PORTAL_ADDRESS;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;

public class LifecycleTest extends KcUnitTestBase {
    
	@Test
	public void testConfig() {
		Config config = ConfigContext.getCurrentContextConfig();
	    assertNotNull("Config not loaded", config);
	    LOG.debug("***** application url = " + config.getObject("application.url"));
	}

	@Test
	public void testContext() {
        assertThat("Context not loaded", KraServiceLocator.getAppContext(), is(not(nullValue())));
	}
    
    @Test
    public void testServer() throws Throwable {
        URL url = new URL(BROWSER_PROTOCOL + "://" + BROWSER_ADDRESS + ":" + KcUnitTestBaseLifecycle.getPort() + "/" + PORTAL_ADDRESS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        assertTrue("Server not loaded", responseCode == 200);
    }

}