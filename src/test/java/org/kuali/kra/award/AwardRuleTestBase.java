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
package org.kuali.kra.award;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * Base class for Award business rule tests.
 * 
 */
public abstract class AwardRuleTestBase extends KraTestBase {
    
    protected DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KraServiceLocator.getService(DocumentService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setErrorMap(null);
        GlobalVariables.setAuditErrorMap(null);
        documentService = null;
        super.tearDown();
    }
    
    /**
     * Get a new Award Document.
     * 
     * @return a new Award Document.
     * @throws WorkflowException
     */
    protected AwardDocument getNewAwardDocument() throws WorkflowException {
        return (AwardDocument) documentService.getNewDocument("AwardDocument");
    }
    

    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key and error key.
     * @param propertyKey
     * @param errorKey
     */
    protected void assertError(String propertyKey, String errorKey) {
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }
}
