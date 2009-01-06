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

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProtocolRequiredFieldsWebTest extends ProtocolWebTestBase {

    protected static final String PROTOCOL_DESCRIPTION_ID =  "document.description";
    protected static final String PROTOCOL_DESCRIPTION =  "keyword_to_test1";
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testRequiredFields() throws Exception {
        
        //Click to create new protocol link
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(portalPage, "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        
        setProtocolRequiredFields(page);
        HtmlPage resultPage = savePage(page);
        validateSavedPage(resultPage); 
        //getProtocolSavedRequiredFieldsPage();
        
        //Invoke save method by clicking save button on form
       // HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals("Kuali :: Protocol Document", resultPage.getTitleText()); 

        setProtocolDocument(null,resultPage); 
        
        //Assert Required Fields
        verifySavedRequiredFields();        
        
    }
    
}
