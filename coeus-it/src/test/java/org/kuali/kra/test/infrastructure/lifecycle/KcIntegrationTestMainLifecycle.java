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
package org.kuali.kra.test.infrastructure.lifecycle;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewServiceImplBase;
import org.kuali.kra.test.infrastructure.ApplicationServerLifecycle;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.impl.config.property.ConfigFactoryBean;
import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class implements all of the common lifecycle elements of a KC Unit Test
 */
public class KcIntegrationTestMainLifecycle extends KcIntegrationTestBaseLifecycle {
    
    private static final String TEST_CONFIG_XML = "classpath:META-INF/kc-test-config.xml";
    private static final String TEST_CONFIG_DEFAULTS_XML = "classpath:META-INF/test-config-defaults.xml";
    private static final String RELATIVE_KC_WEB_ROOT = "coeus-webapp/src/main/webapp";
    private static final String RELATIVE_HELP_WEB_ROOT = "coeus-webapp/target/generated-web-sources/help-web-sources";
    private static final String RELATIVE_RICE_WEB_ROOT = "coeus-webapp/target/generated-web-sources/rice-web-sources";

    private static final Collection<String> EXTRA_WEB_RESOURCES = new ArrayList<String>(){{
        add(RELATIVE_HELP_WEB_ROOT);
        add(RELATIVE_RICE_WEB_ROOT);
    }};

    private static final String DEFAULT_TRANSACTION_MANAGER_NAME = "transactionManager";

    private PlatformTransactionManager transactionManager;
    private ApplicationServerLifecycle server;
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
        System.setProperty("web.bootstrap.config.file", TEST_CONFIG_XML);
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

        if (LOG.isInfoEnabled()) {
            LOG.info("Loading Application Server...");
        }

        server = new ApplicationServerLifecycle(Integer.parseInt(KcIntegrationTestBaseLifecycle.getPort()), "/" + PORTAL_ADDRESS, RELATIVE_KC_WEB_ROOT,
                EXTRA_WEB_RESOURCES);
        server.start();
    }

    @Override
    protected void doPerSuiteStop() throws Throwable {
        server.stop();
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

        //clear service specific cache
        clearCache();
    }

    private void clearCache() throws Throwable {
        KcServiceLocator.getServicesOfType(ProtocolAmendRenewServiceImplBase.class).forEach(s -> {
            try {
                final Field cache = ProtocolAmendRenewServiceImplBase.class.getDeclaredField("amendmentAndRenewalsCache");
                cache.setAccessible(true);
                cache.getType().getMethod("invalidateAll").invoke(cache.get(s));
            } catch (NoSuchFieldException|NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected void doPerTestStop() throws Throwable {
        if (perTestTransactionStatus != null) {
            getTransactionManager().rollback(perTestTransactionStatus);
        }

        //clear service specific cache
        clearCache();
    }

    private PlatformTransactionManager getTransactionManager() {
        if (transactionManager == null) {
            transactionManager = (PlatformTransactionManager) KcServiceLocator.getService(DEFAULT_TRANSACTION_MANAGER_NAME);
        }
        return transactionManager;
    }


    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

}
