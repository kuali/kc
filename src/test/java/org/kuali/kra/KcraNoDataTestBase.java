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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rice.shim.KNSTestCase;
import org.kuali.rice.core.config.spring.ConfigFactoryBean;
import org.kuali.rice.core.lifecycle.Lifecycle;
import org.kuali.rice.core.util.OrmUtils;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.TransactionalLifecycle;
import org.kuali.rice.test.web.HtmlUnitUtil;

/**
 * This class is the base class for all KCRA Tests requiring a Spring context with persistence, but no preloaded test data
 */
public abstract class KcraNoDataTestBase extends KNSTestCase {

    protected TransactionalLifecycle transactionalLifecycle;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    
    // TODO Old KNSTestCase props; fix these.
    private String contextName = "/knstest";
    private String relativeWebappRoot = "/../kns/src/test/webapp";
    private String sqlFilename = "classpath:KNSDefaultTestData.sql";
    private String sqlDelimiter = ";";
    private String xmlFilename = "classpath:KNSDefaultTestData.xml";

    @Before
    public void setUp() throws Exception {
        setContextName("/kra-dev");
        setRelativeWebappRoot("/src/main/webapp");
        setSqlFilename("classpath:DefaultTestData.sql");
        setXmlFilename("classpath:DefaultTestData.xml");
        super.setUp();
        documentService = getService(DocumentService.class);
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setUserSession(new UserSession("quickstart"));
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
        GlobalVariables.setUserSession(null);
        super.tearDown();
        documentService = null;
    }
    
    protected int getPort() {
        return HtmlUnitUtil.getPort();
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

    /**
     * This method is overridden because the lifecycle starting error thrown in setUpInternal method can be hidden by an 
     * exception in the tearDown method.
     * @see org.kuali.rice.test.RiceTestCase#startLifecycles(java.util.List)
     */
    @Override
    protected void startLifecycles(List<Lifecycle> lifecycles) throws Exception {
        try {
            super.startLifecycles(lifecycles);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
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
     * Returns the location of the test harness spring beans context file.
     * Subclasses may override to specify a different location.
     * @return the location of the test harness spring beans context file.
     */
//    protected String[] getTestHarnessSpringBeansLocation() {
//        String[] locations;
//        String moduleTestHarnessSpringBeansPath = getModuleName().toUpperCase() + "TestHarnessSpringBeans.xml";
//        String kcSpringBeans = "classpath:SpringBeans.xml";
//        String awardSpringBeans = "classpath:org/kuali/kra/award/AwardSpringBeans.xml";
//        String irbSpringBeans = "classpath:org/kuali/kra/irb/IrbSpringBeans.xml";
//        String committeeSpringBeans = "classpath:org/kuali/kra/committee/CommitteeSpringBeans.xml";
//        String kewSpringBeansPath = "org/kuali/rice/kew/config/KEWSpringBeans.xml";
//            locations = new String[] { DEFAULT_TEST_HARNESS_SPRING_BEANS, "classpath:" + moduleTestHarnessSpringBeansPath
//                    , "classpath:" + kewSpringBeansPath};
//        return locations;
//    }
    
    /**
     *  Delegate to <code>{@link KraServiceLocator#getService(Class)}</code>
     * @param <T>
     * @param serviceClass class of service to get instance for
     * @return Service instance
     */
    protected final <T> T getService(Class<T> serviceClass) {
        return KraServiceLocator.getService(serviceClass);
    }
    
    protected String getContextName() {
        return contextName;
    }

    protected void setContextName(String contextName) {
        this.contextName = contextName;
    }

    protected String getRelativeWebappRoot() {
        return relativeWebappRoot;
    }
    
    protected String getXmlFilename() {
        return xmlFilename;
    }

    protected void setXmlFilename(String xmlFilename) {
        this.xmlFilename = xmlFilename;
    }
    
    protected String getSqlFilename() {
        return sqlFilename;
    }

    protected void setSqlFilename(String sqlFilename) {
        this.sqlFilename = sqlFilename;
    }
    
    protected void setRelativeWebappRoot(String relativeWebappRoot) {
        this.relativeWebappRoot = relativeWebappRoot;
    }
    
//    protected List<String> getConfigLocations() {
//        List<String> configLocations = super.getConfigLocations();
//        configLocations.add("classpath:META-INF/kew-config-defaults.xml");
//        configLocations.add("classpath:META-INF/kew-test-config.xml");
//        return configLocations;
//    }
}