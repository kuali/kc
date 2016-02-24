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
