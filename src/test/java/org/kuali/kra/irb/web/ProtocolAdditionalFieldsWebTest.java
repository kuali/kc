/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.web;

import org.junit.Test;
import org.kuali.core.document.TransactionalDocumentBase;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class tests additional fields data set. 
 */
public class ProtocolAdditionalFieldsWebTest extends ProtocolWebTestBase {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolAdditionalFieldsWebTest.class);
    
    //Test field for Additional Sub Panel
    protected static final String PROTOCOL_ISBILLABLE_ID =  "document.protocol.billable";
    protected static final String PROTOCOL_ISBILLABLE =  "on";
    
    protected static final String PROTOCOL_FDAAPPLICATIONNUMBER_ID = "document.protocol.fdaApplicationNumber";
    protected static final String PROTOCOL_FDAAPPLICATIONNUMBER = "A11111";
    
    protected static final String PROTOCOL_REFERENCENUMBER1_ID = "document.protocol.referenceNumber1";
    protected static final String PROTOCOL_REFERENCENUMBER1 = "0000";
    
    protected static final String PROTOCOL_REFERENCENUMBER2_ID = "document.protocol.referenceNumber2";
    protected static final String PROTOCOL_REFERENCENUMBER2 = "0010";
    
    protected static final String PROTOCOL_DESCRIPTION_ID =  "document.protocol.description";
    protected static final String PROTOCOL_DESCRIPTION =  "keyword_to_test1";
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testAdditionalFields() throws Exception {
        
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        
        LOG.info("Page Title - " + page.getTitleText());
        
        //Set Parent Html Page
        setPage(page);
        
        //Required Fields to begin with for saving protocol document
        setRequiredFields();
        
        //Additional Fields to populate for this test case
        setFieldValue(HTML_CHECK_BOX_INPUT, PROTOCOL_ISBILLABLE_ID, PROTOCOL_ISBILLABLE, -1);
        setFieldValue(HTML_TEXT_INPUT, PROTOCOL_FDAAPPLICATIONNUMBER_ID, PROTOCOL_FDAAPPLICATIONNUMBER, -1);
        setFieldValue(HTML_TEXT_INPUT, PROTOCOL_REFERENCENUMBER1_ID, PROTOCOL_REFERENCENUMBER1, -1);
        setFieldValue(HTML_TEXT_INPUT, PROTOCOL_REFERENCENUMBER2_ID, PROTOCOL_REFERENCENUMBER2, -1);
        setFieldValue(HTML_TEXT_AREA, PROTOCOL_DESCRIPTION_ID, PROTOCOL_DESCRIPTION, -1);
        
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = invokeLifeCycleMethod(HTML_SAVE);
        
        assertNotNull(resultPage);
        assertEquals("Kuali :: Protocol Document", resultPage.getTitleText());

        String pageAsText = resultPage.asText();
        String errorMessage = extractErrorMessage(pageAsText);
        assertFalse(errorMessage, pageAsText.contains(ERRORS_FOUND_ON_PAGE));
        
        setProtocolDocument(null); //Can also be set by child if required
        
        //Assert Required Fields
        verifySavedRequiredFields();        
    
        //Assert Additional Fields
        assertTrue(getProtocolDocument().getProtocol().isBillable());
        assertEquals(PROTOCOL_FDAAPPLICATIONNUMBER, getProtocolDocument().getProtocol().getFdaApplicationNumber());
        assertEquals(PROTOCOL_REFERENCENUMBER1, getProtocolDocument().getProtocol().getReferenceNumber1());
        assertEquals(PROTOCOL_REFERENCENUMBER2, getProtocolDocument().getProtocol().getReferenceNumber2());
        assertEquals(PROTOCOL_DESCRIPTION, getProtocolDocument().getProtocol().getDescription());
        
    }
}
