package org.kuali.kra.infrastructure;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class KraServiceLocatorListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		KraServiceLocator.getAppContext();
	}

}
