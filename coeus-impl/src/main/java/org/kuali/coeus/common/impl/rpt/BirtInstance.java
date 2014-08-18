/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.rpt;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

class BirtInstance {

    private static BirtInstance birtInstance;
    private IReportEngine engine; 
    private EngineConfig config; 
   

    private BirtInstance()throws Exception {
        init();
    }

    public void init() throws BirtException {
        config = new EngineConfig();
        Platform.startup(config);
        IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
        engine = factory.createReportEngine(config); 
        engine.changeLogLevel(java.util.logging.Level.WARNING);
    }

    private void shutdown() {
        engine.destroy();
        Platform.shutdown();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        shutdown();
    }

    public static synchronized BirtInstance getInstance()throws Exception {
        if (birtInstance == null) {
            birtInstance = new BirtInstance();
        }
        return birtInstance;
    }

    public IReportEngine getIReportEngine() {
        return engine;
    }

    public EngineConfig getEngineConfig() {
        return config;
    }
}
