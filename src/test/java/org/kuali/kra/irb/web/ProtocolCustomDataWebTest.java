/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProtocolCustomDataWebTest extends ProtocolWebTestBase {

    private static final String GROUP_NAME = "Course Related";
    private static final String INSTRUCTOR_NAME_ID = "customDataHelper.customAttributeValues(id10)";
    private static final String INSTRUCTOR_NAME = "Mickey Mouse";
    private static final String COURSE_NAME_ID = "customDataHelper.customAttributeValues(id9)";
    private static final String COURSE_NAME = "Java for Dummies";

    @Test
    public void testCustomDataPage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        String documentNumber = getDocNbr(protocolPage);
        HtmlPage customDataPage = clickOnTab(protocolPage, CUSTOM_DATA_LINK_NAME);

        assertContains(customDataPage, GROUP_NAME);
  
        setFieldValue(customDataPage, INSTRUCTOR_NAME_ID, INSTRUCTOR_NAME);
        setFieldValue(customDataPage, COURSE_NAME_ID, COURSE_NAME);
        customDataPage = saveDoc(customDataPage);
        
        assertContains(customDataPage, "Document was successfully saved.");
        assertContains(customDataPage, INSTRUCTOR_NAME);
        assertContains(customDataPage, COURSE_NAME);
       
        // Verify that the values are stored in the database
        ProtocolDocument doc = (ProtocolDocument) getDocument(documentNumber);
        assertNotNull(doc);
        assertEquals(INSTRUCTOR_NAME, doc.getCustomAttributeDocuments("10").getCustomAttribute().getValue());
        assertEquals(COURSE_NAME, doc.getCustomAttributeDocuments("9").getCustomAttribute().getValue());
    }
}
