/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.web;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test the Person.
 */
public class PersonWebTest extends KraWebTestBase {
    
    private static final long SLEEP_TIME = 3000; // 3 seconds
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setPortalPage(clickOn(getPortalPage(), "System Admin"));
    }
    
    /**
     * Test the lookup for people based upon various values
     * of the Active flag.
     * @throws Exception
     */
    @Test
    public void testActiveFlagLookup() throws Exception {
        HtmlPage portalPage = getPortalPage();
        HtmlPage personPage = clickOn(portalPage, "Person");
        
        personPage = clickOn(personPage, "methodToCall.search");
        assertContains(personPage, "Terry");
        assertDoesNotContain(personPage, "KULUSER");
        
        setFieldValue(personPage, "active", "N");
        personPage = clickOn(personPage, "methodToCall.search");
        assertDoesNotContain(personPage, "Terry");
        assertContains(personPage, "KULUSER");
        
        setFieldValue(personPage, "active", "");
        personPage = clickOn(personPage, "methodToCall.search");
        assertContains(personPage, "Terry");
        assertContains(personPage, "KULUSER");
    }
    
    /**
     * Test the creation of a person.  We should be able
     * to search for it after it is created.
     * @throws Exception
     */
    @Test
    public void testCreatePerson() throws Exception {
        addPerson("111111111", "molly");
        
        HtmlPage portalPage = getPortalPage();
        HtmlPage personPage = clickOn(portalPage, "Person");
        
        personPage = clickOn(personPage, "methodToCall.search");
        assertContains(personPage, "molly");
    }
    
    /**
     * Change a person to inactive and then verify that the person
     * is indeed inactive by performing some lookups.
     * @throws Exception
     */
    @Test
    public void testInactivePerson() throws Exception {
        addPerson("111111112", "nancy");
        
        HtmlPage portalPage = getPortalPage();
        HtmlPage personPage = clickOn(portalPage, "Person");
        
        personPage = clickOn(personPage, "methodToCall.search");
        assertContains(personPage, "nancy");
        
        HtmlAnchor editHyperlink = getAnchor(personPage, "111111112&methodToCall=edit");
        HtmlPage page = clickOn(editHyperlink);
        
        setFieldValue(page, "document.documentHeader.documentDescription", "Test");
        setFieldValue(page, "document.newMaintainableObject.active", "off");
        clickOn(page, "submit");
        
        // Wait for workflow to finish processing the maintenance document.
        // We are crossing our fingers hoping that workflow will finish during
        // this sleep period.
        
        Thread.sleep(SLEEP_TIME);
        
        portalPage = getPortalPage();
        personPage = clickOn(portalPage, "Person");
        
        personPage = clickOn(personPage, "methodToCall.search");
        assertDoesNotContain(personPage, "nancy");
        
        setFieldValue(personPage, "active", "N");
        personPage = clickOn(personPage, "methodToCall.search");
        assertContains(personPage, "nancy");
    }
    
    /**
     * Create a new person.
     * @param personId the person id
     * @param username the username
     * @throws Exception
     */
    private void addPerson(String personId, String username) throws Exception {
        HtmlPage portalPage = getPortalPage();
        HtmlPage personPage = clickOn(portalPage, "Person");
    
        HtmlAnchor createNew = getAnchor(personPage, "Person");
        HtmlPage page = clickOn(createNew);
        
        setFieldValue(page, "document.documentHeader.documentDescription", "Test");
        setFieldValue(page, "document.newMaintainableObject.personId", personId);
        setFieldValue(page, "document.newMaintainableObject.userName", username);
        clickOn(page, "submit");
    }
    
}
