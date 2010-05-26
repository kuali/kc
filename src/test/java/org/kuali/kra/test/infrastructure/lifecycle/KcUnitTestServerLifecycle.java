package org.kuali.kra.test.infrastructure.lifecycle;

public class KcUnitTestServerLifecycle extends KcUnitTestContextLifecycle {
    private static boolean SERVER_STARTED = false;

    protected void doLaunch() throws Throwable {
        super.doLaunch();
        if (!SERVER_STARTED) {
            System.out.println("*Loading Jetty Server");
            SERVER_STARTED = true;
        }        
    }

    protected void doShutdown() throws Throwable {
        System.out.println("*Halting Jetty Server");
        SERVER_STARTED = false;
    }

    protected void doStart() throws Throwable {
        super.doStart();
    }

    protected void doStop() throws Throwable {
        super.doStop();
    }
}
