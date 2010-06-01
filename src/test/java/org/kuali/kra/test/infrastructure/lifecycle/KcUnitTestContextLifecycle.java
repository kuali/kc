/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.test.infrastructure.lifecycle;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * This class...
 */
public class KcUnitTestContextLifecycle extends KcUnitTestConfigLifecycle {
    private static boolean CONTEXT_LOADED = false;
    private boolean isTransactionActive = false;
    private TransactionStatus transactionStatus;

    protected static final String DEFAULT_TRANSACTION_MANAGER_NAME = "transactionManager";
    
    private PlatformTransactionManager transactionManager;

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestConfigLifecycle#doLaunch()
     */
    protected void doLaunch() throws Throwable {
        super.doLaunch();
        if (!CONTEXT_LOADED) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Loading Spring Context...");
            }
            KraServiceLocator.getAppContext();
            CONTEXT_LOADED = true;
        }
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestConfigLifecycle#doShutdown()
     */
    protected void doShutdown() throws Throwable {
        super.doShutdown();
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestConfigLifecycle#doStart()
     */
    protected void doStart() throws Throwable {
        super.doStart();
        if (!CONTEXT_LOADED) {
            throw new KcLifecycleException(new IllegalStateException("Context not loaded, transactions not available"));
        }
        if (!isTransactionActive) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Starting a transaction from KcUnitTestContextLifecycle...");
            }
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setTimeout(3600);
            transactionStatus = getTransactionManager().getTransaction(defaultTransactionDefinition);
            isTransactionActive = true;
        }
    }

    /**
     * @see org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestConfigLifecycle#doStop()
     */
    protected void doStop() throws Throwable {
        if (isTransactionActive) {
            if (LOG.isInfoEnabled()) {
                LOG.info("...rolling back transaction from KcUnitTestContextLifecycle.");
            }
            getTransactionManager().rollback(transactionStatus);
            isTransactionActive = false;
        }
        super.doStop();
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
    
}
