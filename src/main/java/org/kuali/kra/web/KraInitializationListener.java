/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.kuali.kra.infrastructure.KraLifecycle;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class KraInitializationListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(KraInitializationListener.class);

	private KraLifecycle lifecycle;

	public void contextDestroyed(ServletContextEvent sce) {
		try {
			lifecycle.stop();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Failed to stop to the kra lifecycle", e);
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		LOG.info("Initializing KRA...");

		try {
			lifecycle = new KraLifecycle(WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()));
			lifecycle.start();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Failed to start kra lifecycle", e);
		}
	}
}