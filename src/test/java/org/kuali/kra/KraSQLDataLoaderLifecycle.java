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
package org.kuali.kra;

import java.io.File;

import org.kuali.rice.core.Core;
import org.kuali.rice.lifecycle.Lifecycle;
import org.kuali.rice.test.SQLDataLoader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import edu.iu.uis.eden.util.ClassLoaderUtils;

public class KraSQLDataLoaderLifecycle implements Lifecycle {
    private boolean started;

    private SQLDataLoader sqlDataLoader;

    private String filename;

    private String delimiter;

    public KraSQLDataLoaderLifecycle() {
    }

    public boolean isStarted() {
        return started;
    }

    public void start() throws Exception {
        if (new Boolean(Core.getCurrentContextConfig().getProperty("use.kraSqlDataLoaderLifecycle"))) {
            loadData();
            started = true;
        }
    }

    public void stop() throws Exception {
        // TODO: may way to do something with the dataLoader
        started = false;
    }
    
    
    private void loadData() throws Exception {
        Resource resource = getFileResource("classpath:sql/dml");
        String delimiter = ";";
        
        SQLDataLoader sqlDataLoader = new SQLDataLoader("classpath:sql/oracle_defer_constraints.sql", delimiter);
        sqlDataLoader.runSql();
        
        boolean sponsorTypeLoaded = false;

        for (File file : resource.getFile().listFiles()) {
            filename=file.getName();
            if (filename.endsWith(".sql")&& !filename.endsWith("_org.sql")) {
                // TODO : sponsor type must be loaded first. This is a temporary work around
               if (filename.equals("load_sponsor.sql")) {
                    if (!sponsorTypeLoaded) {
                        sqlDataLoader = new SQLDataLoader("classpath:sql/dml/load_sponsor_type.sql", delimiter);
                        sqlDataLoader.runSql();
                        sponsorTypeLoaded = true;
                    }
                    sqlDataLoader = new SQLDataLoader("classpath:sql/dml/" + filename, delimiter);
                    sqlDataLoader.runSql();
                }
                else if (filename.equals("load_sponsor_type.sql")) {
                    if (!sponsorTypeLoaded) {
                        sqlDataLoader = new SQLDataLoader("classpath:sql/dml/load_sponsor_type.sql", delimiter);
                        sqlDataLoader.runSql();
                        sponsorTypeLoaded = true;
                    }
                }
                else {
                    sqlDataLoader = new SQLDataLoader("classpath:sql/dml/" + filename, delimiter);
                    sqlDataLoader.runSql();
                }
            }
        }

    }
    
    private Resource getFileResource(String sourceName) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        return resourceLoader.getResource(sourceName);
    }

}
