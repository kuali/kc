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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.lifecycle.BaseLifecycle;
import org.kuali.rice.core.util.ClassLoaderUtils;
import org.kuali.rice.kew.batch.FileXmlDocCollection;
import org.kuali.rice.kew.batch.XmlDocCollection;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public final class KraKEWXmlDataLoaderLifecycle extends BaseLifecycle {    
    private static final Logger LOG = Logger.getLogger(KraKEWXmlDataLoaderLifecycle.class);
    
    final Collection<File> files = new ArrayList<File>();

    public KraKEWXmlDataLoaderLifecycle(final String pathname) {
        try {
            addFile(getFileResource(pathname).getFile());
        } catch (IOException e) {
            throw new KraKEWDataLoaderException(e);
        }
    }
    
    /** recursively adds all kew files to the instance scoped collection. */
    private void addFile(final File file) {
        if (file.isDirectory()) {
            //assuming all files in these directories are kew files - not using a file name filter...
            for (final File kewFile : file.listFiles()) {
                addFile(kewFile);
            }
        } else if (file.isFile()) {
            files.add(file);
        }
    }


    @Override
    public void start() throws Exception {
        if (Boolean.valueOf(ConfigContext.getCurrentContextConfig().getProperty("use.kraKewXmlDataLoaderLifecycle")).booleanValue()) {
            KEWServiceLocator.getXmlIngesterService().ingest(fromFiles(this.files));
        }
        
        super.start();
    }
    
    /** converts a Collection of files to a List of XmlDocCollection which is required by the ingester. */
    private static List<XmlDocCollection> fromFiles(Collection<File> files) {
        final List<XmlDocCollection> xmlFiles = new ArrayList<XmlDocCollection>();
        for (final File file : files) {
            
            LOG.info("################################");
            LOG.info("#");
            LOG.info("#  Begin Loading file '" + file.getName() + "'");
            LOG.info("#");
            LOG.info("################################");
            
            xmlFiles.add(new FileXmlDocCollection(file));
        }
        
        return xmlFiles;
    }
    
    private Resource getFileResource(String sourceName) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        return resourceLoader.getResource(sourceName);
    }
    
    public static class KraKEWDataLoaderException extends RuntimeException {
        public KraKEWDataLoaderException(Throwable t) {
            super(t);
        }
    }
}
