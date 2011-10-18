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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Custom Data page of a Protocol.
 */
public class ProtocolCustomDataSeleniumTest extends KcSeleniumTestBase {

    private static final String TAB_ID = "Course Related";
    
    private static final String HELPER_PREFIX = "customDataHelper.";
    
    private static final String PERSON_ID_ID = "personId";
    private static final String CUSTOM_ATTRIBUTE_VALUES_ID = "customAttributeValues(id%d)";
    private static final String HELPER_CUSTOM_ATTRIBUTE_VALUES_ID = HELPER_PREFIX + CUSTOM_ATTRIBUTE_VALUES_ID;
    
    private static final String PERSON_ID = "10000000004";
    private static final String PERSON_ID_NAME = "Nicholas Majors";
    private static final String COURSE_NAME = "Java for Dummies";

    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
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
        helper.createProtocol();
        helper.clickProtocolCustomDataPage();

        helper.openTab(TAB_ID);
  
        helper.lookup(String.format(HELPER_CUSTOM_ATTRIBUTE_VALUES_ID, 10), PERSON_ID_ID, PERSON_ID);
        helper.set(String.format(HELPER_CUSTOM_ATTRIBUTE_VALUES_ID, 9), COURSE_NAME);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(PERSON_ID_NAME);
        helper.assertElementContains(String.format(HELPER_CUSTOM_ATTRIBUTE_VALUES_ID, 9), COURSE_NAME);
    }
    
}