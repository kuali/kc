/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

public class JobCodeServiceTest extends KraTestBase {
    
    JobCodeService jobCodeService;
    
    private static final String CEO_CODE = "AA001";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        jobCodeService = getService(JobCodeService.class);
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        jobCodeService = null;
        super.tearDown();
    }
    
    /**
     * Test testGetJobTitle() method
     * 
     * @throws Exception
     */
    @Test
    public void testGetJobTitle() throws Exception {
        
        String  title = jobCodeService.findJobCodeTitle(CEO_CODE);
        
        log.debug("JobTitle for jobCode("+CEO_CODE+" is ["+title+"]");
        // Verify that status is final
        //assertEquals(RiceConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getFinancialDocumentStatusCode());
    }

}
