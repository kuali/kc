/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.infrastructure;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KraServiceLocatorListener implements ServletContextListener {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KraServiceLocatorListener.class);

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
        LOG.debug("Starting KraServiceLocatorListener");
        
        ApplicationContext appContext = null;

        String bootstrapSpringBeans = "classpath:kc-bootstrap-springbeans.xml";
        if (!StringUtils.isBlank(System.getProperty(Constants.BOOTSTRAP_SPRING_FILE))) {
            bootstrapSpringBeans = System.getProperty(Constants.BOOTSTRAP_SPRING_FILE);
            LOG.info("Found bootstrap Spring Beans file defined in system properties: " + bootstrapSpringBeans);
        } else if (!StringUtils.isBlank(sce.getServletContext().getInitParameter(Constants.BOOTSTRAP_SPRING_FILE))) {
            bootstrapSpringBeans = sce.getServletContext().getInitParameter(Constants.BOOTSTRAP_SPRING_FILE);
            LOG.info("Found bootstrap Spring Beans file defined in servlet context: " + bootstrapSpringBeans);
        }

        try {
            appContext = new ClassPathXmlApplicationContext(bootstrapSpringBeans);
        } catch (RuntimeException e) {
            LOG.fatal("error during startup", e);
            throw e;
        } catch (Error e) {
            LOG.fatal("error during startup", e);
            throw e;
        }

        KraServiceLocator.setAppContext(appContext);
	}

}
