package org.kuali.kra.test.infrastructure.test;

import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestBaseLifecycle;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestBaseLifecycle.*;
public class LifecycleTest extends KcIntegrationTestBase {
    
	@Test
	public void testConfig() {
		Config config = ConfigContext.getCurrentContextConfig();
	    assertNotNull("Config not loaded", config);
	    LOG.debug("***** application url = " + config.getObject("application.url"));
	}

	@Test
	public void testContext() {
        assertThat("Context not loaded", KcServiceLocator.getAppContext(), is(not(nullValue())));
	}
    
    @Test
    public void testServer() throws Throwable {
        URL url = new URL(BROWSER_PROTOCOL + "://" + BROWSER_ADDRESS + ":" + KcIntegrationTestBaseLifecycle.getPort() + "/" + PORTAL_ADDRESS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        assertTrue("Server not loaded", responseCode == 200);
        //wait for a little while before shutting down the server
        Thread.sleep(3000);
    }

}