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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.lifecycle.Lifecycle;
import org.kuali.rice.core.util.ClassLoaderUtils;
import org.kuali.rice.kew.batch.StreamXmlDocCollection;
import org.kuali.rice.kew.batch.XmlDoc;
import org.kuali.rice.kew.batch.XmlDocCollection;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class KraKEWXmlDataLoaderLifecycle implements Lifecycle {

    private static final Collection<String> PARENT_KEW_FILES;
    static {
        final Collection<String> temp = new ArrayList<String>(); 
        temp.add("DefaultKewTestData.xml");
        temp.add("KualiDocument.xml");
        temp.add("RiceDocument.xml");
        temp.add("KC.xml"); 
        temp.add("KcMaintenanceDocument.xml");
        temp.add("KcProposalsMaintenanceDocument.xml");
        temp.add("KcAwardsMaintenanceDocument.xml");
        temp.add("KcComplianceMaintenanceDocument.xml");
        temp.add("KcSharedMaintenanceDocument.xml");
        temp.add("KcMiscellaneousMaintenanceDocument.xml");
        PARENT_KEW_FILES = Collections.unmodifiableCollection(temp);
    }
    
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
        List<XmlDocCollection> xmlFiles = new ArrayList<XmlDocCollection>();        
        
        Collection<String> rules = new ArrayList<String>();
        Resource resource = getFileResource(pathname);
        final File dir = resource.getFile();
        
        for(String parentKewFile : PARENT_KEW_FILES) {
            xmlFiles.add(createStreamXmlDocCollection(dir, parentKewFile));
        }
        for (File file : resource.getFile().listFiles()) {
            String filename=file.getName();
            if (PARENT_KEW_FILES.contains(filename)) {
                // do nothing
            } else if (filename.endsWith("Rules.xml")) {
                rules.add(filename);
            } else if (filename.endsWith(".xml")) {
                xmlFiles.add(createStreamXmlDocCollection(dir, filename));
            }
        }
        for (String filename : rules) {
            xmlFiles.add(createStreamXmlDocCollection(dir, filename));
        }

        loadXmlFiles(xmlFiles);
    }
    
    private StreamXmlDocCollection createStreamXmlDocCollection(File parent, String xmlFile) throws FileNotFoundException {
        return new StreamXmlDocCollection(new FileInputStream(new File(parent, xmlFile)));
    }

    private Resource getFileResource(String sourceName) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        return resourceLoader.getResource(sourceName);
    }
    
    protected void loadXmlFiles(List<XmlDocCollection> xmlFiles) throws Exception {
        KEWServiceLocator.getXmlIngesterService().ingest(xmlFiles);
        
        for (XmlDocCollection xmlFile : xmlFiles) {
            for (XmlDoc doc : xmlFile.getXmlDocs()) {
                if (!doc.isProcessed()) {
                    throw new KraKEWDataLoaderException("failed to ingest doc: " + doc.getName() + " see processing message: " + doc.getProcessingMessage());
                }
            }
        }
    }
    
    public static class KraKEWDataLoaderException extends RuntimeException {
        public KraKEWDataLoaderException(String msg) {
            super(msg);
        }
    }
}
