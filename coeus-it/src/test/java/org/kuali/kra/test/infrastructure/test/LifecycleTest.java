/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
