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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test authorizations for a protocol.
 */
public class ProtocolAuthorizationTest extends ProtocolWebTestBase {
    
    private static final String USERNAME = "woods";
    
    /***********************************************************************
     * Setup and TearDown
     ***********************************************************************/
    
    private boolean javaScriptEnabled;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        javaScriptEnabled = webClient.isJavaScriptEnabled();
        webClient.setJavaScriptEnabled(false);
    }
    
    @After
    public void tearDown() throws Exception {
        webClient.setJavaScriptEnabled(javaScriptEnabled);
        super.tearDown();
    }
    
    /***********************************************************************
     * Test Cases
     ***********************************************************************/
    
    /**
     * Simply verify that the user with permission can click 
     * on the "Create Protocol" link and get the protocol page.
     */
    @Test
    public void testCreateAuthorizationOK() throws Exception {
        buildProtocolDocumentPage();
    }
    
    /**
     * Verify that a user who doesn't have permission to create a protocol
     * will get an error when they click on the "Create Protocol" link.
     */
    @Test
    public void testCreateAuthorizationFailure() throws Exception {
        backdoorLogin(USERNAME);
        HtmlPage page = clickOn(getPortalPage(), "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        this.assertContains(page, "user '" + USERNAME + "' is not authorized to initiate document 'ProtocolDocument'");
    }
    
    public String getNewTestingProtocolId() throws Exception {
        HtmlPage page = createAndSaveProtocol();
        page = this.saveDoc(page);
        return this.getDocNbr(page);
    }
    
    /**
     * Verify that a user who has permission to create/modify a proposal 
     * can do so.  The save is done twice: once for create and once for
     * modify.
     */
    @Test
    public void testModifyAuthorization() throws Exception {
        HtmlPage page = createAndSaveProtocol();
        assertEquals(false, hasError(page));
        page = this.saveDoc(page);
        assertEquals(false, hasError(page));
    }
    
    /**
     * Verify that a user who doesn't have permission to view/modify
     * a protocol will not be able to access it.
     */
    @Test
    public void testNoAccessAuthorization() throws Exception {
        HtmlPage page = createAndSaveProtocol();
        String docNbr = this.getDocNbr(page);
        this.closeDoc(page);
        
        backdoorLogin(USERNAME);
        page = this.docSearch(docNbr);
        this.assertContains(page, "is not authorized to open document");
    }
    
    /**
     * Verify that a user who has read-only access to the protocol
     * can indeed access it and is unable to modify it.
     */
    @Test
    public void testReadOnlyAuthorization() throws Exception {
        // TODO : do later when I can add Protocol Viewers
    }
    
    /***********************************************************************
     * Helper Methods
     ***********************************************************************/
    
    private HtmlPage createAndSaveProtocol() throws Exception {
        HtmlPage page = buildProtocolDocumentPage();
        setProtocolRequiredFields(page);
        return this.saveDoc(page);
    }
}
