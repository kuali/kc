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

import org.kuali.rice.test.web.HtmlUnitUtil;


public class KcUnitTestServerLifecycle extends KcUnitTestContextLifecycle {
    private static final String CONTEXT_NAME = "/kc-dev";
    private static final String RELATIVE_WEB_ROOT = "/src/main/webapp";
    private static final int PORT = HtmlUnitUtil.getPort();
    
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
        super.doShutdown();
    }

    protected void doStart() throws Throwable {
        super.doStart();
    }

    protected void doStop() throws Throwable {
        super.doStop();
    }
}
