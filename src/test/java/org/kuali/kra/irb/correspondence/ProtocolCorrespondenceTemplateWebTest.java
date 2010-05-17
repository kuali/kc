/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.irb.correspondence;

import java.io.IOException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraWebTestBase;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
               @UnitTestFile(filename = "classpath:sql/dml/load_PROTO_CORRESP_TYPE.sql", delimiter = ";")
              ,@UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";")
              ,@UnitTestFile(filename = "classpath:sql/dml/load_COMMITTEE_DOCUMENT.sql", delimiter = ";")
              ,@UnitTestFile(filename = "classpath:sql/dml/load_COMMITTEE.sql", delimiter = ";")
            }
        )
    )
    
public class ProtocolCorrespondenceTemplateWebTest extends KraWebTestBase{
	
	private static final String ADD_DEFAULT_TEMPLATE_BUTTON = "methodToCall.addDefaultCorrespondenceTemplate.correspondenceType[0]";
	private static final String REPLACE_DEFAULT_TEMPLATE_BUTTON = "methodToCall.replaceDefaultCorrespondenceTemplate.correspondenceType[0]";
	private static final String UPLOAD_DEFAULT_TEMPLATE_BUTTON = "methodToCall.replaceDefaultCorrespondenceTemplate.correspondenceType[0]";
	private static final String DELETE_DEFAULT_TEMPLATE_BUTTON = "methodToCall.deleteDefaultCorrespondenceTemplate.correspondenceType[0]";
	private static final String NEW_DEFAULT_FILE_FIELD = "newDefaultCorrespondenceTemplates[0].templateFile";

	private static final String ADD_COMMITTEE_TEMPLATE_BUTTON = "methodToCall.addCorrespondenceTemplate.correspondenceType[0]";
	private static final String REPLACE_COMMITTEE_TEMPLATE_BUTTON = "methodToCall.replaceCorrespondenceTemplate.correspondenceType[0].correspondenceTemplate[0]";
	private static final String UPLOAD_COMMITTEE_TEMPLATE_BUTTON = "methodToCall.replaceCorrespondenceTemplate.correspondenceType[0].correspondenceTemplate[0]";
	private static final String DELETE_COMMITTEE_TEMPLATE_BUTTON = "methodToCall.deleteCorrespondenceTemplate.correspondenceType[0].correspondenceTemplate[0]";
	private static final String NEW_COMMITTEE_ID_FIELD = "newCorrespondenceTemplates[0].committeeId";
	private static final String NEW_COMMITTEE_FILE_FIELD = "newCorrespondenceTemplates[0].templateFile";
	private static final String REPLACE_COMMITTEE_FILE_FIELD = "replaceCorrespondenceTemplates[0].list[0].templateFile";
	
	private static final String COMMITTEE_ID_1 = "1";
	
	// Any two xml files in the root of the project can be used for testing. Content doesn't matter as long as it's not an empty file.
	private static final String XML_TEST_FILES_DIRECTORY = "/testCorrespondenceTemplates/";
	private static final String XML_TEST_FILE_1 = "CorrespondenceTemplate1.xml";
	private static final String XML_TEST_FILE_2 = "CorrespondenceTemplate2.xml";
	
	private static final String MISSING_COMMITTEE_ID_ERROR_MSG = "Committee must be supplied.";
	private static final String NO_FILE_EMPTY_FILE_ERROR_MSG = "No file or empty file specified.";
	private static final String DOCUMENT_RELOAD_MSG = "Document was successfully reloaded.";
    private static final String DOCUMENT_SAVE_MSG = "Document was successfully saved.";
	
	private HtmlPage page;

    @Before
    public void setUp() throws Exception {
    	super.setUp();
        page = buildCorrespondenceTemplatePage();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * This method tests the default correspondence template.  
     * 
     * @throws Exception
     */
    @Test
    public void testAddReplaceDeleteDefaultCorrespondenceTemplate() throws Exception {
        // just making sure assumptions are correct
        assertFalse(hasError(page));
        assertDoesNotContain(page, NO_FILE_EMPTY_FILE_ERROR_MSG);
        assertDoesNotContain(page, XML_TEST_FILE_1);
        assertDoesNotContain(page, XML_TEST_FILE_2);

        // Test error
        page= clickOn(getElementByName(page, ADD_DEFAULT_TEMPLATE_BUTTON, true));
        assertTrue(hasError(page));
        assertContains(page, NO_FILE_EMPTY_FILE_ERROR_MSG);

        // Test add
        setFieldValue(page, NEW_DEFAULT_FILE_FIELD, getFilePath(XML_TEST_FILE_1));
    	page= clickOn(getElementByName(page, ADD_DEFAULT_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertContains(page, XML_TEST_FILE_1);
        
        // Test replace
    	page= clickOn(getElementByName(page, REPLACE_DEFAULT_TEMPLATE_BUTTON, true));
    	setFieldValue(page, NEW_DEFAULT_FILE_FIELD, getFilePath(XML_TEST_FILE_2));
    	page= clickOn(getElementByName(page, UPLOAD_DEFAULT_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        // XML_TEST_FILE_1 still exists on the page in the display only div, the file
        // tag will have the new file.
        assertContains(page, XML_TEST_FILE_1);
        assertContains(page, XML_TEST_FILE_2);
        
        // Test delete
    	page= clickOn(getElementByName(page, DELETE_DEFAULT_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertDoesNotContain(page, XML_TEST_FILE_1);
        assertDoesNotContain(page, XML_TEST_FILE_2);
    }

    /**
     * This method tests the committee specific correspondence template.
     * @throws Exception
     */
    @Test
    public void testAddReplaceDeleteCommitteeCorrespondenceTemplate() throws Exception {
        // just making sure assumptions are correct
        assertFalse(hasError(page));
        assertDoesNotContain(page, MISSING_COMMITTEE_ID_ERROR_MSG);
        assertDoesNotContain(page, NO_FILE_EMPTY_FILE_ERROR_MSG);
        assertDoesNotContain(page, XML_TEST_FILE_1);
        assertDoesNotContain(page, XML_TEST_FILE_2);

        // Test error
        page= clickOn(getElementByName(page, ADD_COMMITTEE_TEMPLATE_BUTTON, true));
        assertTrue(hasError(page));
        assertContains(page, MISSING_COMMITTEE_ID_ERROR_MSG);
        assertContains(page, NO_FILE_EMPTY_FILE_ERROR_MSG);

        // Test add
        setFieldValue(page, NEW_COMMITTEE_ID_FIELD, COMMITTEE_ID_1);
    	setFieldValue(page, NEW_COMMITTEE_FILE_FIELD, getFilePath(XML_TEST_FILE_1));
    	page= clickOn(getElementByName(page, ADD_COMMITTEE_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertContains(page, "Committee 1");
        assertContains(page, XML_TEST_FILE_1);

        // Test replace
    	page= clickOn(getElementByName(page, REPLACE_COMMITTEE_TEMPLATE_BUTTON, true));
    	setFieldValue(page, REPLACE_COMMITTEE_FILE_FIELD, getFilePath(XML_TEST_FILE_2));
    	page= clickOn(getElementByName(page, UPLOAD_COMMITTEE_TEMPLATE_BUTTON, true));
    	assertFalse(hasError(page));
        // XML_TEST_FILE_1 still exists on the page in the display only div, the file
        // tag will have the new file.
        assertContains(page, XML_TEST_FILE_1);
        assertContains(page, XML_TEST_FILE_2);

        // Test delete
        page= clickOn(getElementByName(page, DELETE_COMMITTEE_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertDoesNotContain(page, XML_TEST_FILE_1);
        assertDoesNotContain(page, XML_TEST_FILE_2);
    }

    /**
     * This method tests the document reload.
     * 
     * @throws Exception
     */
    @Test
    public void testReload() throws Exception {
        // just making sure assumptions are correct
        assertFalse(hasError(page));
        assertDoesNotContain(page, DOCUMENT_RELOAD_MSG);
        assertDoesNotContain(page, XML_TEST_FILE_1);
        assertDoesNotContain(page, XML_TEST_FILE_2);

        // add committee specific correspondence template
        setFieldValue(page, NEW_COMMITTEE_ID_FIELD, COMMITTEE_ID_1);
        setFieldValue(page, NEW_COMMITTEE_FILE_FIELD, getFilePath(XML_TEST_FILE_1));
        page= clickOn(getElementByName(page, ADD_COMMITTEE_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertContains(page, XML_TEST_FILE_1);
        
        // add default correspondence template
        setFieldValue(page, NEW_DEFAULT_FILE_FIELD, getFilePath(XML_TEST_FILE_2));
        page= clickOn(getElementByName(page, ADD_DEFAULT_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertContains(page, XML_TEST_FILE_1);
        assertContains(page, XML_TEST_FILE_2);

        // reload document 
        page= clickOn(getElementByName(page, "methodToCall.reload"));
        assertFalse(hasError(page));
        assertContains(page, DOCUMENT_RELOAD_MSG);
        assertDoesNotContain(page, XML_TEST_FILE_1);
        assertDoesNotContain(page, XML_TEST_FILE_2);
    }

    /**
     * This method tests the document save.
     * 
     * @throws Exception
     */
   @Test
    public void testSave() throws Exception {
        // just making sure assumptions are correct
        assertFalse(hasError(page));
        assertDoesNotContain(page, DOCUMENT_SAVE_MSG);
        assertDoesNotContain(page, DOCUMENT_RELOAD_MSG);
        assertDoesNotContain(page, XML_TEST_FILE_1);
        assertDoesNotContain(page, XML_TEST_FILE_2);

        // add committee specific correspondence template
        setFieldValue(page, NEW_COMMITTEE_ID_FIELD, COMMITTEE_ID_1);
        setFieldValue(page, NEW_COMMITTEE_FILE_FIELD, getFilePath(XML_TEST_FILE_1));
        page= clickOn(getElementByName(page, ADD_COMMITTEE_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertContains(page, XML_TEST_FILE_1);
        
        // add default correspondence template
        setFieldValue(page, NEW_DEFAULT_FILE_FIELD, getFilePath(XML_TEST_FILE_2));
        page= clickOn(getElementByName(page, ADD_DEFAULT_TEMPLATE_BUTTON, true));
        assertFalse(hasError(page));
        assertContains(page, XML_TEST_FILE_1);
        assertContains(page, XML_TEST_FILE_2);

        // save document
        page= clickOn(getElementByName(page, "methodToCall.save"));
        assertFalse(hasError(page));
        assertContains(page, DOCUMENT_SAVE_MSG);
        assertContains(page, XML_TEST_FILE_1);
        assertContains(page, XML_TEST_FILE_2);

        // reload document
        page= clickOn(getElementByName(page, "methodToCall.reload"));
        assertFalse(hasError(page));
        assertContains(page, DOCUMENT_RELOAD_MSG);
        assertContains(page, XML_TEST_FILE_1);
        assertContains(page, XML_TEST_FILE_2);
    }

    /**
     * Create a new instance of the correspondence template page by clicking on the link to the portal page. 
     * The resulting page of the click through is a frame, so it is important to get the inner page.
     * 
     * @return <code>{@link HtmlPage}</code> instance of the correspondence template page
     * @throws IOException
     */
    protected final HtmlPage buildCorrespondenceTemplatePage() throws Exception {
        HtmlPage centralAdminPage = clickOn(getPortalPage(), "Maintenance");
        HtmlPage page = clickOn(centralAdminPage, "Correspondence Template", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        assertEquals("Kuali :: Correspondence Template", page.getTitleText());
        return page;
    }

    /**
     * Gets the path of a given class file.
     * @param clazz the class
     * @return the path
     */
    private String getFilePath(String fileName) {
        URL fileUrl = getClass().getResource(XML_TEST_FILES_DIRECTORY + fileName);
        assertNotNull(fileUrl);
        return fileUrl.getPath();
    }
}
