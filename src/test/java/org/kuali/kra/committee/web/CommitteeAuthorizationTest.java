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
package org.kuali.kra.committee.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test authorizations for a committee.
 */
public class CommitteeAuthorizationTest extends CommitteeWebTestBase {
    
    private static final String USERNAME = "quickstart";
    private static final String VIEWER_USERNAME = "jtester";
    
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
        if(webClient != null) {
            webClient.setJavaScriptEnabled(javaScriptEnabled);
        }
        super.tearDown();
    }
    
    /***********************************************************************
     * Test Cases
     ***********************************************************************/
    
    /**
     * Simply verify that the user with permission can click 
     * on the "Create Committee" link and get the committee page.
     */
    @Test
    public void testCreateAuthorizationOK() throws Exception {
        buildCommitteePage();
    }
    
    /**
     * Verify that a user who doesn't have permission to create a committee
     * will get an error when they click on the "Create Committee" link.
     */
    @Test
    public void testCreateAuthorizationFailure() throws Exception {
        backdoorLogin("majors");
        HtmlPage centralAdminPage = clickOn(getPortalPage(), "Central Admin");
        HtmlPage page = clickOn(centralAdminPage, "Create Committee", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        this.assertContains(page, "DocumentAuthorizationException");
    }
    
    /**
     * Verify that a user who has permission to create/modify a committee 
     * can do so.  The save is done twice: once for create and once for
     * modify.
     */
    @Test
    public void testModifyAuthorization() throws Exception {
        HtmlPage page = createAndSaveCommittee("777");
        assertEquals(false, hasError(page));
        page = this.saveDoc(page);
        assertEquals(false, hasError(page));
    }
    
    /**
     * Verify that a user who doesn't have permission to view/modify
     * a committee will not be able to access it.
     */
    @Test
    public void testNoAccessAuthorization() throws Exception {
        HtmlPage page = createAndSaveCommittee((new Long(new java.util.Date().getTime())).toString());
        String docNbr = this.getDocNbr(page);
        this.closeDoc(page);
        
        backdoorLogin("majors");
        page = this.docSearch(docNbr);
        this.assertContains(page, "is not authorized to open document");
    }
    
    /**
     * Verify that a user who has read-only access to the committee
     * can indeed access it and is unable to modify it.
     */
    @Test
    public void testReadOnlyAuthorization() throws Exception {
        HtmlPage page = createAndSaveCommittee("999");
        String docNbr = this.getDocNbr(page);
        this.closeDoc(page);
        
        backdoorLogin(VIEWER_USERNAME);
        page = this.docSearch(docNbr);
        
        // Should be able to access the committee but there will
        // be no save button.
        
        this.assertDoesNotContain(page, "is not authorized to view document");
        assertEquals(null, this.getElement(page, "save"));
    }
    
    /***********************************************************************
     * Helper Methods
     ***********************************************************************/
    
    private HtmlPage createAndSaveCommittee(String committeeId) throws Exception {
        HtmlPage page = buildCommitteePage();
        setDefaultRequiredFields(page);
        setFieldValue(page, COMMITTEE_ID_ID, committeeId);
        return this.saveDoc(page);
    }
}
