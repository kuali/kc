/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.ApplicationServerLifecycle;
import org.kuali.kra.test.infrastructure.TestHarnessServiceLocator;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.kuali.rice.core.impl.config.property.ConfigFactoryBean;
import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements all of the common lifecycle elements of a KC Unit Test
 */
public class KcIntegrationTestMainLifecycle extends KcIntegrationTestBaseLifecycle {
    
    private static final String TEST_CONFIG_XML = "classpath:META-INF/kc-test-config.xml";
    private static final String TEST_CONFIG_DEFAULTS_XML = "classpath:META-INF/test-config-defaults.xml";
    private static final String DEFAULT_TEST_HARNESS_SPRING_BEANS = "classpath:TestHarnessSpringBeans.xml";
    private static final String RELATIVE_WEB_ROOT = "coeus-webapp/src/main/webapp";
    private static final String DEFAULT_TRANSACTION_MANAGER_NAME = "transactionManager";

    private PlatformTransactionManager transactionManager;
    private SpringResourceLoader loader;
    private ApplicationServerLifecycle jetty;
    private TransactionStatus perTestTransactionStatus;

    @Override
    protected void doPerClassStart() throws Throwable {
    }

    @Override
    protected void doPerClassStop() throws Throwable {
    }

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
        configLocations.add(TEST_CONFIG_DEFAULTS_XML);
        configLocations.add(TEST_CONFIG_XML);
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
            LOG.info("Loading Application Server...");
        }
        jetty = new ApplicationServerLifecycle(Integer.parseInt(KcIntegrationTestBaseLifecycle.getPort()), "/" + PORTAL_ADDRESS, RELATIVE_WEB_ROOT, ApplicationServerLifecycle.ConfigMode.MERGE);
        jetty.start();
    }

    @Override
    protected void doPerSuiteStop() throws Throwable {
        jetty.stop();
        loader.stop();
    }

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

    @Override
    protected void doPerTestStop() throws Throwable {
        if (perTestTransactionStatus != null) {
            getTransactionManager().rollback(perTestTransactionStatus);
        }
    }

    private PlatformTransactionManager getTransactionManager() {
        if (transactionManager == null) {
            transactionManager = (PlatformTransactionManager) KraServiceLocator.getService(DEFAULT_TRANSACTION_MANAGER_NAME);
        }
        return transactionManager;
    }


    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

}