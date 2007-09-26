package org.kuali.kra.infrastructure;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kuali.kra.infrastructure.SystemParametersPropertyHolder;

public class KraServiceLocatorListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		KraServiceLocator.getAppContext();
        ServletContext context = sce.getServletContext();

        // publish application constants into JSP app context with name "Constants"
        context.setAttribute("SystemParameters", new SystemParametersPropertyHolder("SYSTEM"));

	}

}
