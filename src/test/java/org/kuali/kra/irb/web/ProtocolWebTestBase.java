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
package org.kuali.kra.irb.web;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * Abstract Protocol Web Test base class provides common functionalities required by extended class.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";") }))
public abstract class ProtocolWebTestBase extends KraWebTestBase {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolWebTestBase.class);
    
    // KEW Struts Constants
    protected static final String KUALI_FORM_NAME = "KualiForm";
    protected static final String KUALI_DOCUMENT_NUMBER = "document.documentHeader.documentNumber";

    // Services
    private DocumentService documentService;

    // Set by child class
    private ProtocolDocument protocolDocument;

    // Begin Required Fields
    protected enum UiLookupKey {       
        DOCUMENT_DESCRIPTION_ID("document.documentHeader.documentDescription"),
        PROTOCOL_STATUS_ID("document.protocol.protocolStatusCode"),
        PROTOCOL_TYPE_CODE_ID("document.protocol.protocolTypeCode"),
        PROTOCOL_TITLE_ID("document.protocol.title"),
        PROTOCOL_APPLICATION_DATE_ID("document.protocol.applicationDate");
        
        private String value;

        public String getValue() {
            return value;
        }
        UiLookupKey(String value){
            this.value = value;          
        }
    }
   
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Protocol Document";

    protected static final String PROTOCOL_STATUS = "100"; //test of option "Pending/In Progress";

    protected static final String PROTOCOL_TYPE_CODE = "1";//test of option "Standard";

    protected static final String PROTOCOL_TITLE = "Some text title goes here...";

    protected static final String PROTOCOL_APPLICATION_DATE = "11/12/2008";
    protected static final String PROTOCOL_APPLICATION_DATE_RESULT = "2008-11-12"; // TODO if required

    /**
     * Web test setup overloading. Sets up DocumentService and asserts.
     * @see org.kuali.kra.KraWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setDocumentService(KNSServiceLocator.getDocumentService());
        // Assert documentService, required to test save feature of form
        assertNotNull(getDocumentService());
    }

    /**
     * Web test tear down overloading.
     * @see org.kuali.kra.KraWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * @see org.kuali.kra.KraTestBase#setDocumentService(org.kuali.core.service.DocumentService)
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * @see org.kuali.kra.KraTestBase#getDocumentService()
     */
    public DocumentService getDocumentService() {
        return documentService;
    }
    
    /**
     * This method sets ProtocolDoucment, if null is passed along, it tries to get handle using Kuali Struts constant.
     * @param protocolDocument <code>{@link ProtocolDocument}</code>
     * @throws WorkflowException
     */
    public void setProtocolDocument(ProtocolDocument protocolDocument, HtmlPage page) throws WorkflowException {
        if (null == protocolDocument) {
            HtmlHiddenInput documentNumber = (HtmlHiddenInput) page.getFormByName(KUALI_FORM_NAME).getInputByName(
                    KUALI_DOCUMENT_NUMBER);
            this.protocolDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(documentNumber.getDefaultValue());
        }
        assertNotNull(getProtocolDocument());
    }

    /**
     * This method returns PrtocolDocument
     * @return <code>{@link ProtocolDocument}</code>
     */
    public ProtocolDocument getProtocolDocument() {
        return protocolDocument;
    }
    
    protected void setRequiredFields(HtmlPage page){ 
        setRequiredFields(page, buildDefaultMap()); 
    }
    
    private Map<UiLookupKey, String> buildDefaultMap() {
        Map<UiLookupKey, String> params = new HashMap<UiLookupKey, String>();
        params.put(UiLookupKey.DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        params.put(UiLookupKey.PROTOCOL_STATUS_ID, PROTOCOL_STATUS);
        params.put(UiLookupKey.PROTOCOL_TYPE_CODE_ID, PROTOCOL_TYPE_CODE);
        params.put(UiLookupKey.PROTOCOL_TITLE_ID, PROTOCOL_TITLE);
        params.put(UiLookupKey.PROTOCOL_APPLICATION_DATE_ID, PROTOCOL_APPLICATION_DATE);
        return params;
    }
    
    /**
     * Helper method for sub-classes to set required fields for saving form.
     */
    protected void setRequiredFields(HtmlPage page, Map<UiLookupKey, String> params) {
        super.setFieldValue(page, UiLookupKey.DOCUMENT_DESCRIPTION_ID.getValue(), params.get(UiLookupKey.DOCUMENT_DESCRIPTION_ID)); 
        super.setFieldValue(page, UiLookupKey.PROTOCOL_STATUS_ID.getValue(), params.get(UiLookupKey.PROTOCOL_STATUS_ID));
        super.setFieldValue(page, UiLookupKey.PROTOCOL_TYPE_CODE_ID.getValue(), params.get(UiLookupKey.PROTOCOL_TYPE_CODE_ID));; 
        super.setFieldValue(page, UiLookupKey.PROTOCOL_TITLE_ID.getValue(), params.get(UiLookupKey.PROTOCOL_TITLE_ID)); 
        super.setFieldValue(page, UiLookupKey.PROTOCOL_APPLICATION_DATE_ID.getValue(), params.get(UiLookupKey.PROTOCOL_APPLICATION_DATE_ID));
    }

    /**
     * This method asserts whether required fields where saved
     */
    protected void verifySavedRequiredFields() {
        assertEquals(DEFAULT_DOCUMENT_DESCRIPTION, getProtocolDocument().getDocumentHeader().getDocumentDescription()); //Not persisted BUG??
        assertEquals(PROTOCOL_STATUS, getProtocolDocument().getProtocol().getProtocolStatus().getProtocolStatusCode());
        assertEquals(PROTOCOL_TYPE_CODE, getProtocolDocument().getProtocol().getProtocolType().getProtocolTypeCode());
        assertEquals(PROTOCOL_TITLE, getProtocolDocument().getProtocol().getTitle());
        assertEquals(PROTOCOL_APPLICATION_DATE_RESULT, getProtocolDocument().getProtocol().getApplicationDate().toString());
    }
}
