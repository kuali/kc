/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

package org.kuali.kra.infrastructure;

import org.apache.log4j.Logger;
import org.kuali.rice.lifecycle.BaseLifecycle;
import org.springframework.context.ApplicationContext;

/**
 * Core lifecycle for the KRA application.  Initializes Spring and OJB.
 * @author rkirkend
 * @author natjohns
 */
public class KraLifecycle extends BaseLifecycle {
	
    private static final Logger LOG = Logger.getLogger(KraLifecycle.class);

    public static final String DATASOURCE = "kraDataSource";

    private ApplicationContext context;

    public KraLifecycle(ApplicationContext context) {
        this.context = context;
    }

    public void start() throws Exception {
        LOG.info("Starting KraLifecycle");
        KraServiceLocator.getInstance().start();
        super.start();
    }

    public void stop() throws Exception {
        LOG.info("Stopping KraLifecycle");
        KraServiceLocator.getInstance().stop();
        super.stop();
    }
    
}