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
package org.kuali.kra.committee.web;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CommitteeWebTest extends CommitteeWebTestBase {

    private static final String MY_COMMITTEE_ID = "32767";
    private static final String DESCRIPTION_VALUE = "Description text";
    private static final String DESCRIPTION_VALUE2 = "Another description text";
    
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
        if (webClient != null) {
            webClient.setJavaScriptEnabled(javaScriptEnabled);
        }
        super.tearDown();
    }
    
    /**
     * @throws Exception
     */
    @Test
    public void testSaveOfCommitteeFields() throws Exception {
       
        HtmlPage committeePage = this.getCommitteePage();
        
        setDefaultRequiredFields(committeePage);
        setFieldValue(committeePage, COMMITTEE_ID_ID, MY_COMMITTEE_ID);
        
        /*
         * Verify that we can save the document without getting any errors.
         */
        committeePage = this.saveDoc(committeePage);
        assertFalse(this.hasError(committeePage));
        
        /*
         * Now verify that we can search for document and that the 
         * displayed data is correct.
         */
        String docNbr = this.getDocNbr(committeePage);
        committeePage = docSearch(docNbr);
        
        assertEquals(DEFAULT_DOCUMENT_DESCRIPTION, getFieldValue(committeePage, DOCUMENT_DESCRIPTION_ID));
        assertEquals(DEFAULT_TYPE_CODE, getFieldValue(committeePage, COMMITTEE_TYPE_CODE_ID));
        assertEquals(DEFAULT_MAX_PROTOCOLS, getFieldValue(committeePage, COMMITTEE_MAX_PROTOCOLS_ID));
        assertEquals(DEFAULT_HOME_UNIT_NUMBER, getFieldValue(committeePage, COMMITTEE_HOME_UNIT_NUMBER_ID));
        assertEquals(DEFAULT_REVIEW_TYPE_CODE, getFieldValue(committeePage, COMMITTEE_REVIEW_TYPE_CODE_ID));
        assertEquals(DEFAULT_NAME, getFieldValue(committeePage, COMMITTEE_NAME_ID));
        assertEquals(DEFAULT_MIN_MEMBERS_REQUIRED, getFieldValue(committeePage, COMMITTEE_MIN_MEMBERS_REQUIRED_ID));
        assertEquals(DEFAULT_ADV_SUBMISSION_DAYS_REQUIRED, getFieldValue(committeePage, COMMITTEE_ADV_SUBMISSION_DAYS_REQUIRED_ID));
        //assertEquals(MY_COMMITTEE_ID, getValue(committeePage, "committeeId"));
        assertEquals(MY_COMMITTEE_ID,StringUtils.substringBetween(committeePage.asText(),"Committee ID: "," * Committee Name"));
        assertEquals(DEFAULT_DESCRIPTION, getFieldValue(committeePage, COMMITTEE_DESCRIPTION_ID));
        assertEquals(DEFAULT_SCHEDULE_DESCRIPTION, getFieldValue(committeePage, COMMITTEE_SCHEDULE_DESCRIPTION_ID));
    }
    
    /**
     * Verify that invalid home unit number will result in an error message.
     * @throws Exception
     */
    @Test
    public void testInvalidHomeUnit() throws Exception {
       
        HtmlPage committeePage = this.getCommitteePage();
        
        setDefaultRequiredFields(committeePage);
        setFieldValue(committeePage, COMMITTEE_HOME_UNIT_NUMBER_ID, "xxx");
        
        /*
         * Verify that we can save the document without getting any errors.
         */
        committeePage = this.saveDoc(committeePage);
        assertTrue(this.hasError(committeePage));
        assertContains(committeePage, "xxx is not a valid Unit");
    }
    
    /**
     * Verifies the expanded text for the Committee Description.
     *
     * @throws Exception
     */
    @Test
    public void testCommitteeDescriptionExpandedTextArea() throws Exception {

        HtmlPage committeePage = getCommitteePage();
        committeePage =
            this.checkExpandedTextArea(committeePage, 
                                       COMMITTEE_DESCRIPTION_ID,
                                       DESCRIPTION_VALUE,
                                       DESCRIPTION_VALUE2);
    }
    
    /**
     * Verifies the expanded text for the Schedule Description.
     *
     * @throws Exception
     */
    @Test
    public void testScheduleDescriptionExpandedTextArea() throws Exception {

        HtmlPage committeePage = getCommitteePage();
        committeePage =
            this.checkExpandedTextArea(committeePage, 
                                       COMMITTEE_SCHEDULE_DESCRIPTION_ID,
                                       DESCRIPTION_VALUE,
                                       DESCRIPTION_VALUE2);
    }
    
    private Object getValue(HtmlPage page, String id) {
        HtmlElement element = this.getElement(page, id);
        assertNotNull(element);
        return element.asText().trim();
    }
}
