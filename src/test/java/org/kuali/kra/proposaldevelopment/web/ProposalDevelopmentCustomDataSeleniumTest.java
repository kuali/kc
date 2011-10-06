/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Custom Data page of a Development Proposal.
 */
public class ProposalDevelopmentCustomDataSeleniumTest extends KcSeleniumTestBase {
    
    private static final String PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID = "Personnel Items for Review";
    private static final String ASDF_TAB_ID = "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf";

    private static final String CUSTOM_ATTRIBUTE_VALUES_ID = "customAttributeValues(id%d)";
    private static final String USER_NAME_ID = "userName";

    private static final String TENURED = "chew";
    private static final String GRADUATE_STUDENT_COUNT = "5";
    private static final String BILLING_ELEMENT = "College";
    private static final String LOCAL_REVIEW_DATE = "02/08/2008";
    
    private ProposalDevelopmentSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProposalDevelopmentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test the Custom Data page.
     */
    @Test
    public void testCustomDataPage() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentCustomDataPage();
  
        helper.openTab(PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID);
        helper.lookup(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 5), USER_NAME_ID, TENURED);
        helper.set(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 4), GRADUATE_STUDENT_COUNT);
        
        helper.openTab(ASDF_TAB_ID);
        helper.set(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 1), BILLING_ELEMENT);
        helper.set(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 8), LOCAL_REVIEW_DATE);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        assertEquals(TENURED, helper.get(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 5)));
        assertEquals(GRADUATE_STUDENT_COUNT, helper.get(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 4))); 
        assertEquals(BILLING_ELEMENT, helper.get(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 1)));
        assertEquals(LOCAL_REVIEW_DATE, helper.get(String.format(CUSTOM_ATTRIBUTE_VALUES_ID, 8)));
    }
    
}