package org.kuali.kra.test.infrastructure.test;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraServiceLocatorConfigurer;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.test.web.HtmlUnitUtil;

public class TestUnitTestA extends KcUnitTestBase {
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
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("**UnitTestTest.setUp()");
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
        System.out.println("**UnitTestTest.tearDown()");
        super.tearDown();
    }
    
}
