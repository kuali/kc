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
package org.kuali.kra.institutionalproposal.web;

import org.junit.Test;
import org.kuali.kra.infrastructure.TestUtilities;

public class InstitutionalProposalCompleteSeleniumTest extends InstitutionalProposalSeleniumTestBase {
    
    private static final String GRADUATE_STUDENT_COUNT_ID = "institutionalProposalCustomDataFormHelper.customDataValues[3].value";
    private static final String BILLING_ELEMENT_ID = "institutionalProposalCustomDataFormHelper.customDataValues[0].value";
    
    @Test
    public void testInstitutionalProposalComplete() {
        createInstitutionalProposal();
        
        addCustomData();
        
        submit();
    }
    
    private void addCustomData() {
        clickInstitutionalProposalCustomDataPage();

        openTab("Personnel Items for Review");
        set(GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        
        openTab("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
        set(BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);
    }
    
    private void submit() {
        clickInstitutionalProposalActionsPage();

        routeDocument();
        assertRoute();
    }
    
}