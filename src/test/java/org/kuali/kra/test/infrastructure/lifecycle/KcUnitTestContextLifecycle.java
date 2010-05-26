package org.kuali.kra.test.infrastructure.lifecycle;

import javax.xml.namespace.QName;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.resourceloader.SpringResourceLoader;
import org.kuali.rice.test.TestHarnessServiceLocator;

public class KcUnitTestContextLifecycle extends KcUnitTestConfigLifecycle {
    private static boolean CONTEXT_LOADED = false;
    private static boolean TRANSACTION_ACTIVE = false;
    protected static final String DEFAULT_TEST_HARNESS_SPRING_BEANS = "classpath:TestHarnessSpringBeans.xml";
    private static SpringResourceLoader testHarnessSpringResourceLoader;

    protected void doLaunch() throws Throwable {
        try {
        super.doLaunch();
        if (!CONTEXT_LOADED) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Loading Spring Context");
            }
            testHarnessSpringResourceLoader = new SpringResourceLoader(new QName("TestHarnessSpringContext"), DEFAULT_TEST_HARNESS_SPRING_BEANS);
            TestHarnessServiceLocator.setContext(testHarnessSpringResourceLoader.getContext());
            testHarnessSpringResourceLoader.start();
            KraServiceLocator.getAppContext();
            CONTEXT_LOADED = true;
        }
        }
        catch (Throwable t) {
            t.printStackTrace(System.err);
            throw t;
        }
    }

    protected void doShutdown() throws Throwable {
        testHarnessSpringResourceLoader.stop();
    }

    protected void doStart() throws Throwable {
        if (!CONTEXT_LOADED) {
            throw new KcLifecycleException(new IllegalStateException("Context not loaded, transactions not available"));
        }
        if (!TRANSACTION_ACTIVE) {
            System.out.println("*Starting DB Transaction");
            TRANSACTION_ACTIVE = true;
        }
    }

    protected void doStop() throws Throwable {
        if (TRANSACTION_ACTIVE) {
            System.out.println("*Stopping DB Transaction");
            TRANSACTION_ACTIVE = false;
        }
    }
}
