/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.test.infrastructure.lifecycle;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.HtmlUnitUtil;
import org.kuali.kra.test.infrastructure.JettyServerLifecycle;
import org.kuali.kra.test.infrastructure.TestHarnessServiceLocator;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.impl.config.property.ConfigFactoryBean;
import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * This class implements all of the common lifecycle elements of a KC Unit Test
 */
public class KcUnitTestMainLifecycle extends KcUnitTestBaseLifecycle {
    protected static final String TEST_CONFIG_XML = "classpath:META-INF/kc-test-config.xml";
    protected static final String DEFAULT_TEST_HARNESS_SPRING_BEANS = "classpath:TestHarnessSpringBeans.xml";
    protected static final String CONTEXT_NAME = "/kc-dev";
    protected static final String RELATIVE_WEB_ROOT = "/src/main/webapp";
    protected static final String DEFAULT_TRANSACTION_MANAGER_NAME = "transactionManager";

    private PlatformTransactionManager transactionManager;
    private SpringResourceLoader loader;
    private int port;
    private JettyServerLifecycle jetty;
    private TransactionStatus perTestTransactionStatus;
//    private TransactionStatus perClassTransactionStatus;

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doPerClassStart()
     */
    @Override
    protected void doPerClassStart() throws Throwable {
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doPerClassStop()
     */
    @Override
    protected void doPerClassStop() throws Throwable {
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doPerSuiteStart()
     */
    @Override
    protected void doPerSuiteStart() throws Throwable {
        if (LOG.isInfoEnabled()) {
            LOG.info("Loading Configuration");
        }
        if (System.getProperty("module.name") == null) {
            System.setProperty("module.name", "");
        }
        if (System.getProperty("basedir") == null) {
            System.setProperty("basedir", System.getProperty("user.dir") + "/");
        }
        ConfigFactoryBean.CONFIG_OVERRIDE_LOCATION = TEST_CONFIG_XML;
        List<String> configLocations = new ArrayList<String>();
        configLocations.add("classpath:META-INF/test-config-defaults.xml");
        configLocations.add("classpath:META-INF/kc-test-config.xml");
        Config config = new JAXBConfigImpl(configLocations, System.getProperties());
        config.parseConfig();
        ConfigContext.init(config);
        if (LOG.isInfoEnabled()) {
            LOG.info("Loading Spring Context...");
        }
        loader = new SpringResourceLoader(new QName("TestHarnessSpringContext"), DEFAULT_TEST_HARNESS_SPRING_BEANS, null);
        TestHarnessServiceLocator.setContext(loader.getContext());
        loader.start();
        if (LOG.isInfoEnabled()) {
            LOG.info("Loading Jetty Server...");
        }
        port = HtmlUnitUtil.getPort();
        jetty = new JettyServerLifecycle(port, CONTEXT_NAME, RELATIVE_WEB_ROOT);
        jetty.setConfigMode(JettyServerLifecycle.ConfigMode.MERGE);
        jetty.start();
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doPerSuiteStop()
     */
    @Override
    protected void doPerSuiteStop() throws Throwable {
        jetty.stop();
        loader.stop();
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doPerTestStart()
     */
    @Override
    protected void doPerTestStart(boolean transactional) throws Throwable {
        if (transactional) {
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setTimeout(3600);
            perTestTransactionStatus = getTransactionManager().getTransaction(defaultTransactionDefinition);
        }
        else {
            perTestTransactionStatus = null;
        }
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestBaseLifecycle#doPerTestStop()
     */
    @Override
    protected void doPerTestStop() throws Throwable {
        if (perTestTransactionStatus != null) {
            getTransactionManager().rollback(perTestTransactionStatus);
        }
    }

    /**
     * This method...
     * @return
     */
    private PlatformTransactionManager getTransactionManager() {
        if (transactionManager == null) {
            transactionManager = (PlatformTransactionManager) KraServiceLocator.getService(DEFAULT_TRANSACTION_MANAGER_NAME);
        }
        return transactionManager;
    }
    
    /**
     * This method...
     * @param transactionManager
     */
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public int getPort() {
        return port;
    }

}
