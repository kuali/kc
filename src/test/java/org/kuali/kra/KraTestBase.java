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

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.document.Document;
import org.kuali.core.service.DocumentService;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.lifecycle.Lifecycle;
import org.kuali.rice.testharness.KNSTestCase;
import org.kuali.rice.testharness.TransactionalLifecycle;

public abstract class KraTestBase extends KNSTestCase {

    private TransactionalLifecycle transactionalLifecycle;
    private DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        setContextName("/kra-dev");
        setRelativeWebappRoot("/src/main/webapp");
        setSqlFilename("classpath:DefaultTestData.sql");
        setSqlDelimiter(";");
        setXmlFilename("classpath:DefaultTestData.xml");
        setTestConfigFilename("classpath:META-INF/kra-test-config.xml");
        super.setUp();
        documentService = KNSServiceLocator.getDocumentService();
        GlobalVariables.setErrorMap(new ErrorMap());
        transactionalLifecycle = new TransactionalLifecycle();
        transactionalLifecycle.start();
    }

    @After
    public void tearDown() throws Exception {
        transactionalLifecycle.stop();
        GlobalVariables.setErrorMap(new ErrorMap());
        super.tearDown();
        documentService = null;
    }

    @Override
    protected String getModuleName() {
        return "";
    }


    @Override
    public List<Lifecycle> getSuiteLifecycles() {
        List<Lifecycle> lifeCycles= super.getSuiteLifecycles();
        lifeCycles.add(new KraSQLDataLoaderLifecycle());
        lifeCycles.add(new KraKEWXmlDataLoaderLifecycle());
        return lifeCycles;
    }

    public DocumentService getDocumentService() throws Exception {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected Document getDocument(String documentNumber) throws Exception {
        transactionalLifecycle.stop();
        Document doc=getDocumentService().getByDocumentHeaderId(documentNumber);
        transactionalLifecycle.start();
        return doc;

    }
}
