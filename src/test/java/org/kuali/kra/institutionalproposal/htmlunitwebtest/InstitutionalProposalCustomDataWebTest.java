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
package org.kuali.kra.institutionalproposal.htmlunitwebtest;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This is the integration test for Custom Data tab in Institutional Proposal module.
 */
public class InstitutionalProposalCustomDataWebTest extends InstitutionalProposalWebTestBase {

    private static final String GROUP_NAME = "Personnel Items for Review";
    private static final String TENURED_NAME_ID = "institutionalProposalCustomDataFormHelper.customAttributeValues(id5)";
    private static final String TENURED_NAME = "Mickey Mouse";
    private static final String GRADUATE_STUDENT_COUNT_ID = "institutionalProposalCustomDataFormHelper.customAttributeValues(id4)";
    private static final String GRADUATE_STUDENT_COUNT = "12";
    
    
    /**
     * This method tests adding custom data to document and saving page.
     * @throws Exception
     */
    @Test
    public void testCustomDataPage() throws Exception {
        HtmlPage proposalPage = getProposalHomePage();
        HtmlPage customDataPage = clickOnTab(proposalPage, CUSTOM_DATA_LINK_NAME);
        System.out.print(customDataPage.asText());

        assertContains(customDataPage, GROUP_NAME);
        
  
        setFieldValue(customDataPage, TENURED_NAME_ID, TENURED_NAME);
        setFieldValue(customDataPage, GRADUATE_STUDENT_COUNT_ID, GRADUATE_STUDENT_COUNT);
        customDataPage = saveDoc(customDataPage);
        
        assertContains(customDataPage, "Document was successfully saved.");
        assertContains(customDataPage, TENURED_NAME);
        assertContains(customDataPage, GRADUATE_STUDENT_COUNT);
       
    }
}
