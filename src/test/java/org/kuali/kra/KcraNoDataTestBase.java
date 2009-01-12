/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.kuali.core.document.Document;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.config.spring.ConfigFactoryBean;
import org.kuali.rice.lifecycle.Lifecycle;
import org.kuali.rice.test.lifecycles.TransactionalLifecycle;
import org.kuali.rice.testharness.KNSTestCase;
import org.kuali.rice.util.OrmUtils;

/**
 * This class is the base class for all KCRA Tests requiring a Spring context with persistence, but no preloaded test data
 */
public abstract class KcraNoDataTestBase extends KNSTestCase {

    protected TransactionalLifecycle transactionalLifecycle;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        documentService = getService(DocumentService.class);
        GlobalVariables.setErrorMap(new ErrorMap());
        transactionalLifecycle = new TransactionalLifecycle();
        transactionalLifecycle.setTransactionManager(KNSServiceLocator.getTransactionManager());
        transactionalLifecycle.start();
    }

    @After
    public void tearDown() throws Exception {
        if(transactionalLifecycle != null) {
            transactionalLifecycle.stop();
        }
        GlobalVariables.setErrorMap(new ErrorMap());
        super.tearDown();
        documentService = null;
    }

    @Override
    protected String getModuleName() {
        return "";
    }
    
    @Override
    protected String getModuleTestConfigLocation() {
        return "classpath:META-INF/kra-test-config.xml";
    }

    @Override
    public List<Lifecycle> getSuiteLifecycles() {
        ConfigFactoryBean.CONFIG_OVERRIDE_LOCATION = "classpath:META-INF/kra-test-config.xml";
        List<Lifecycle> lifeCycles= super.getSuiteLifecycles();
        //lifeCycles.add(new KraSQLDataLoaderLifecycle());
        lifeCycles.add(new KraKEWXmlDataLoaderLifecycle());
        return lifeCycles;
    }

    protected BusinessObjectService getBusinessObjectService() throws Exception {
        if(businessObjectService == null) {
            businessObjectService = KNSServiceLocator.getBusinessObjectService();
        }
        return businessObjectService;
    }
    
    protected DocumentService getDocumentService() throws Exception {
        if(documentService == null) {
            documentService = KNSServiceLocator.getDocumentService();
        }
        return documentService;
    }

    protected void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected Document getDocument(String documentNumber) throws Exception {
        //transactionalLifecycle.stop();
        
        // Unfortunately, I can only clear the cache for OJB.  I have been
        // unable to force a refresh on a document when it is in the cache.
        // This is a pain if I need to recheck a document after the database
        // has been changed.  Therefore, for OJB, I clear the cache which
        // will force a new instance of the document to be retrieved from the database
        // instead of the cache.
        if (!OrmUtils.isJpaEnabled()) {
            KNSServiceLocator.getPersistenceServiceOjb().clearCache();
        }
        Document doc=getDocumentService().getByDocumentHeaderId(documentNumber);
       // transactionalLifecycle.start();
        return doc;

    }
    
    /**
     *  Delegate to <code>{@link KraServiceLocator#getService(Class)}</code>
     * @param <T>
     * @param serviceClass class of service to get instance for
     * @return Service instance
     */
    protected final <T> T getService(Class<T> serviceClass) {
        return KraServiceLocator.getService(serviceClass);
    }
}