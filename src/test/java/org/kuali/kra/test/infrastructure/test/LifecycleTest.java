package org.kuali.kra.test.infrastructure.test;

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.*;
import static org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle.*;

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