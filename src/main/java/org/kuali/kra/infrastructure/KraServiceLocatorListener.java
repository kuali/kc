package org.kuali.kra.infrastructure;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class KraServiceLocatorListener implements ServletContextListener {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KraServiceLocatorListener.class);

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
        LOG.error("Starting KraServiceLocatorListener");

		KraServiceLocator.getAppContext();
	}

}
