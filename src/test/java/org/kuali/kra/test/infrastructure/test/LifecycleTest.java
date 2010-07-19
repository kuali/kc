package org.kuali.kra.test.infrastructure.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.test.infrastructure.KcWebTestBase;
import org.kuali.rice.core.config.Config;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.test.web.HtmlUnitUtil;

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
        int port = HtmlUnitUtil.getPort();
        URL url = new URL(KcWebTestBase.PROTOCOL_AND_HOST + ":"+port+"/kc-dev/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        assertTrue("Server not loaded", responseCode==200);
    }
}
