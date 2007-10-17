package org.kuali.kra.infrastructure;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kuali.kra.infrastructure.SystemParametersPropertyHolder;

import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;

public class KraServiceLocatorListener implements ServletContextListener {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KraServiceLocatorListener.class);

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
        LOG.error("Starting KraServiceLocatorListener");

		KraServiceLocator.getAppContext();
        ServletContext context = sce.getServletContext();

        // publish application constants into JSP app context with name "Constants"
        context.setAttribute("ProposalDevelopmentParameters", new SystemParametersPropertyHolder(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT));

	}

}
