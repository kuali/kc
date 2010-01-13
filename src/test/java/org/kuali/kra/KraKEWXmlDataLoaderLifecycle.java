/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.lifecycle.Lifecycle;
import org.kuali.rice.core.util.ClassLoaderUtils;
import org.kuali.rice.kew.batch.KEWXmlDataLoader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class KraKEWXmlDataLoaderLifecycle implements Lifecycle {

    private boolean started;

    private String pathname;

    public KraKEWXmlDataLoaderLifecycle() {
        this("classpath:kew/xml");
    }

    public KraKEWXmlDataLoaderLifecycle(String pathname) {
        this.pathname = pathname;
    }

    public boolean isStarted() {
        return started;
    }

    public void start() throws Exception {
        if (new Boolean(ConfigContext.getCurrentContextConfig().getProperty("use.kraKewXmlDataLoaderLifecycle"))) {
            loadKraKewXml();
            started = true;
        }
    }

    public void stop() throws Exception {
        started = false;
    }


    
    private void loadKraKewXml() throws Exception {
        List<String> rules = new ArrayList<String>();
        Resource resource = getFileResource(pathname);
        String filename;
        
        List<String> parentKewFiles = new ArrayList<String>();
        parentKewFiles.add("DefaultKewTestData.xml");
        parentKewFiles.add("KualiDocument.xml");
        parentKewFiles.add("RiceDocument.xml");
        parentKewFiles.add("KC.xml"); 
        parentKewFiles.add("KcMaintenanceDocument.xml");
        parentKewFiles.add("KcProposalsMaintenanceDocument.xml");
        parentKewFiles.add("KcAwardsMaintenanceDocument.xml");
        parentKewFiles.add("KcComplianceMaintenanceDocument.xml");
        parentKewFiles.add("KcSharedMaintenanceDocument.xml");
        parentKewFiles.add("KcMiscellaneousMaintenanceDocument.xml");
        
        for(String parentKewFile : parentKewFiles) {
            this.loadXmlFile(pathname + File.separator + parentKewFile);
        }
        for (File file : resource.getFile().listFiles()) {
            filename=file.getName();
            if (parentKewFiles.contains(filename)) {
                // do nothing
            } else if (filename.endsWith("Rules.xml")) {
                rules.add(filename);
            } else if (filename.endsWith(".xml")) {
                this.loadXmlFile(pathname + File.separator + filename);
            }
        }
        for (String fileName : rules) {
            this.loadXmlFile(pathname + File.separator + fileName);
        }

    }

    private Resource getFileResource(String sourceName) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        return resourceLoader.getResource(sourceName);
    }

    protected void loadXmlFile(String fileName) throws Exception {
        KEWXmlDataLoader.loadXmlResource(fileName);
    }
}
