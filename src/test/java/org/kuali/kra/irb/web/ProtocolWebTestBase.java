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

import java.io.IOException;
import java.util.Iterator;

import org.kuali.core.service.DocumentService;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import edu.iu.uis.eden.exception.WorkflowException;

import org.junit.After;
import org.junit.Before;

/**
 * Abstract Protocol Web Test base class provides common functionalities required by extended class.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";") }))
public abstract class ProtocolWebTestBase extends KraWebTestBase {

    // KEW Struts Constants
    protected static final String KUALI_FORM_NAME = "KualiForm";
    protected static final String KUALI_DOCUMENT_NUMBER = "document.documentHeader.documentNumber";
    
    // HTML Constants
    protected static final int HTML_TEXT_INPUT = 0;
    protected static final int HTML_TEXT_AREA = 1;
    protected static final int HTML_SELECTED_INPUT = 2;
    protected static final int HTML_HIDDEN_INPUT = 3;
    protected static final int HTML_IMAGE_INPUT = 4;
    protected static final int HTML_SUBMIT_INPUT_BY_NAME = 5;
    protected static final int HTML_SUBMIT_INPUT_BY_VALUE = 6;
    protected static final int HTML_CHECK_BOX_INPUT = 7;
    protected static final int HTML_FILE_INPUT = 8;

    protected static final int HTML_SAVE = 1;

    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";

    // Services
    private DocumentService documentService;

    // Set by child class
    private HtmlPage page;
    private ProtocolDocument protocolDocument;

    // Begin Required Fields
    protected static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Protocol Document";

    protected static final String PROTOCOL_STATUS_ID = "document.protocolStatusCode";
    protected static final String PROTOCOL_STATUS = "Pending/In Progress";

    protected static final String PROTOCOL_TYPE_CODE_ID = "document.protocolTypeCode";
    protected static final String PROTOCOL_TYPE_CODE = "Standard";

    protected static final String PROTOCOL_TITLE_ID = "document.title";
    protected static final String PROTOCOL_TITLE = "Some text title goes here...";

    protected static final String PROTOCOL_APPLICATION_DATE_ID = "document.applicationDate";
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
     * This method is used to set HtmlPage from extended class.
     * @param <code>{@link HtmlPage}</code>
     */
    public void setPage(HtmlPage page) {
        this.page = page;
        assertTrue("Kuali :: Protocol Document".equals(getPage().getTitleText()));
    }

    /**
     * This method return HtmlPage
     * @return <code>{@link HtmlPage}</code>
     */
    public HtmlPage getPage() {
        return page;
    }
    
    
    /**
     * This method sets ProtocolDoucment, if null is passed along, it tries to get handle using Kuali Struts constant.
     * @param protocolDocument <code>{@link ProtocolDocument}</code>
     * @throws WorkflowException
     */
    public void setProtocolDocument(ProtocolDocument protocolDocument) throws WorkflowException {
        if (null == protocolDocument) {
            HtmlHiddenInput documentNumber = (HtmlHiddenInput) getPage().getFormByName(KUALI_FORM_NAME).getInputByName(
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

    /**
     * Helper method for sub-classes to set required fields for saving form.
     */
    protected void setRequiredFields() {
        setFieldValue(HTML_TEXT_INPUT, DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION, -1);
        setFieldValue(HTML_SELECTED_INPUT, PROTOCOL_STATUS_ID, PROTOCOL_STATUS, -1);
        setFieldValue(HTML_SELECTED_INPUT, PROTOCOL_TYPE_CODE_ID, PROTOCOL_TYPE_CODE, -1);
        setFieldValue(HTML_TEXT_AREA, PROTOCOL_TITLE_ID, PROTOCOL_TITLE, -1);
        setFieldValue(HTML_TEXT_INPUT, PROTOCOL_APPLICATION_DATE_ID, PROTOCOL_APPLICATION_DATE, -1);
    }

    /**
     * Helper method to set appropriate field type value on HtmlPage.
     * @param type
     * @param fieldName
     * @param value
     * @param optionSize
     */
    protected void setFieldValue(int type, String fieldName, String value, int optionSize) {
        switch (type) {
            case HTML_CHECK_BOX_INPUT:
                HtmlCheckBoxInput checkboxField = (HtmlCheckBoxInput) getPage().getFormByName(KUALI_FORM_NAME).getInputByName(
                        fieldName);
                if (value.equalsIgnoreCase("on")) {
                    checkboxField.setChecked(true);
                }
                else if (value.equalsIgnoreCase("off")) {
                    checkboxField.setChecked(false);
                }
                else {
                    assertTrue("Invalid checkbox value", false);
                }
                break;
            case HTML_TEXT_INPUT:
                HtmlTextInput text = (HtmlTextInput) getPage().getFormByName(KUALI_FORM_NAME).getInputByName(fieldName);
                text.setValueAttribute(value);
                break;
            case HTML_TEXT_AREA:
                HtmlTextArea textArea = (HtmlTextArea) getPage().getFormByName(KUALI_FORM_NAME).getTextAreasByName(fieldName)
                        .get(0);
                textArea.setText(value);
                break;
            case HTML_SELECTED_INPUT:
                HtmlSelect selected = (HtmlSelect) getPage().getFormByName(KUALI_FORM_NAME).getSelectByName(fieldName);
                selected.setSelectedAttribute(getHtmlOptionFromValue(getPage().getFormByName(KUALI_FORM_NAME), selected, value),
                        true);
                if (optionSize != -1) {
                    assertEquals(optionSize, selected.getOptionSize());
                }
                break;
            default:
                assertTrue(false);
                break;
        }
    }

    /**
     * Helper method to find HtmlOption object using string label/value
     * @param htmlForm <code>{@link HtmlForm}</code>
     * @param selected <code>{@link HtmlSelect}</code>
     * @param value
     * @return <code>{@link HtmlOption}</code>
     */
    @SuppressWarnings("unchecked")
    private HtmlOption getHtmlOptionFromValue(HtmlForm htmlForm, HtmlSelect selected, String value) {
        Iterator it = selected.getAllHtmlChildElements();
        HtmlOption option = null;
        while (it.hasNext()) {
            option = (HtmlOption) it.next();
            if (option.asText().equalsIgnoreCase(value)) {
                break; // Break if value or HtmlOption and supplied value is equal
            }
        }
        return option;
    }

    /**
     * Helper method to find image tag name
     * @param page <code>{@link HtmlPage}</code>
     * @param uniqueNamePrefix
     * @return tagName
     */
    protected String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        int idx1 = page.asXml().indexOf(uniqueNamePrefix);
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }

    /**
     * This method extracts the error message (if any) from the html page as text.
     * @param pageAsText text of the html page response to extract the error message from
     * @return error message from the page
     */
    protected String extractErrorMessage(String pageAsText) {
        String errorMessage = "Errors were found: ";
        int index1 = pageAsText.indexOf("error");
        if (index1 > -1) {
            int index2 = pageAsText.indexOf("Document Overview");
            if (index2 > -1) {
                errorMessage += pageAsText.substring(index1, index2);
            }
            else {
                errorMessage += pageAsText.substring(index1);
            }
        }
        return errorMessage;
    }

    /**
     * This method asserts whether required fields where saved
     */
    protected void verifySavedRequiredFields() {
        // TODO check with Bryan or Don
        // assertEquals(DEFAULT_DOCUMENT_DESCRIPTION, doc.getDocumentHeader().getDocumentDescription()); //Not persisted BUG??
        assertEquals(PROTOCOL_STATUS, getProtocolDocument().getProtocolStatus().getDescription());
        assertEquals(PROTOCOL_TYPE_CODE, getProtocolDocument().getProtocolType().getDescription());
        assertEquals(PROTOCOL_TITLE, getProtocolDocument().getTitle());
        assertEquals(PROTOCOL_APPLICATION_DATE_RESULT, getProtocolDocument().getApplicationDate().toString());
    }

    /**
     * This method invokes life cycle form method as requested
     * @param type
     * @return
     * @throws IOException <code>{@link IOException}</code>
     */
    protected HtmlPage invokeLifeCycleMethod(int type) throws IOException {
        String completeButtonName = null;
        switch (type) {
            case HTML_SAVE:
                completeButtonName = getImageTagName(page, "methodToCall.save");
                break;
            default:
                assertTrue(false);
                break;
        }
        HtmlImageInput button = (HtmlImageInput) getPage().getFormByName(KUALI_FORM_NAME).getInputByName(completeButtonName);
        return (HtmlPage) button.click();
    }
}
