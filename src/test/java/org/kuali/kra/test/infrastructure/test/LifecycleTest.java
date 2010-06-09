package org.kuali.kra.test.infrastructure.test;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.kuali.kra.KraServiceLocatorConfigurer;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.test.web.HtmlUnitUtil;

public class LifecycleTest extends KcUnitTestBase {
	@Test
	public void testConfig() {
		assertNotNull("Config not loaded", ConfigContext.getCurrentContextConfig());
	}

	@Test
	public void testContext() {
        assertTrue("Context not loaded", KraServiceLocatorConfigurer.isApplicationContextInitialized());
	}
    
    @Test
    public void testServer() throws Throwable {
        int port = HtmlUnitUtil.getPort();
        URL url = new URL("http://localhost:"+port+"/kc-dev/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        assertTrue("Server not loaded", responseCode==200);
    }
}
