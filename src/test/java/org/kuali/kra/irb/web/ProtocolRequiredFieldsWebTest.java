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
        
        assertNotNull(resultPage);
        assertEquals("Kuali :: Protocol Document", resultPage.getTitleText()); 
        setProtocolDocument(null,resultPage);    
        
        //Assert Required Fields
        verifySavedRequiredFields();                
    }
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeInvestigatorId() throws Exception {        
        //Click to create new protocol link
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(portalPage, "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        setProtocolRequiredFields(page);
        setFieldValue(page, ProtocolRequiredFields.PROTOCOL_PI_ID.getCode(), "");        
        setFieldValue(page, ProtocolRequiredFields.PROTOCOL_PI_ID1.getCode(), "");        
        setFieldValue(page, ProtocolRequiredFields.PROTOCOL_PI_ID2.getCode(), "");        
        HtmlPage resultPage = savePage(page);
        assertContains(resultPage,ERRORS_FOUND_ON_PAGE);   
        assertContains(resultPage,"Principal Investigator (Principal Investigator) is a required field");                         
    }

    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeTypeCode() throws Exception {
        //Click to create new protocol link
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(portalPage, "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);       
        setProtocolRequiredFields(page);
        setFieldValue(page, ProtocolRequiredFields.PROTOCOL_TYPE_CODE.getCode(), "");        
        HtmlPage resultPage = savePage(page);
        assertContains(resultPage,ERRORS_FOUND_ON_PAGE);   
        assertContains(resultPage,"Protocol Type (Protocol Type) is a required field");                         
    }    
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeTitle() throws Exception {
        //Click to create new protocol link
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(portalPage, "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);       
        setProtocolRequiredFields(page);
        setFieldValue(page, ProtocolRequiredFields.PROTOCOL_TITLE.getCode(), "");        
        HtmlPage resultPage = savePage(page);
        assertContains(resultPage,ERRORS_FOUND_ON_PAGE);   
        assertContains(resultPage,"Title (Title) is a required field");                         
    }
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeLeadUnit() throws Exception {
        //Click to create new protocol link
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(portalPage, "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);       
        setProtocolRequiredFields(page);
        setFieldValue(page, ProtocolRequiredFields.PROTOCOL_LEAD_UNIT_NUM.getCode(), "");        
        HtmlPage resultPage = savePage(page);
        assertContains(resultPage,ERRORS_FOUND_ON_PAGE);   
        assertContains(resultPage,"Lead Unit (Lead Unit) is a required field");                         
    }
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeInvalidLeadUnit() throws Exception {
        //Click to create new protocol link
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(portalPage, "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);       
        setProtocolRequiredFields(page);
        setFieldValue(page, ProtocolRequiredFields.PROTOCOL_LEAD_UNIT_NUM.getCode(), "bogus");        
        HtmlPage resultPage = savePage(page);
        assertContains(resultPage,ERRORS_FOUND_ON_PAGE);   
        assertContains(resultPage,"Lead Unit is invalid");                         
    }
    
}
