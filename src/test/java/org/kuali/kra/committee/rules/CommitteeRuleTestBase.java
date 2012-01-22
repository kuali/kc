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
package org.kuali.kra.committee.rules;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.AutoPopulatingList;

/**
 * Base class for Committee business rule tests.
 */
public abstract class CommitteeRuleTestBase extends KcUnitTestBase {

    protected DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setMessageMap(new MessageMap());
        KNSGlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KRADServiceLocatorWeb.getDocumentService();
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setMessageMap(null);
        KNSGlobalVariables.setAuditErrorMap(null);
        documentService = null;
        super.tearDown();
    }
    
    /**
     * Set the required fields for a committee.
     * @param document
     */
    protected void setCommitteeProperties(CommitteeDocument document) {
        Committee committee = document.getCommittee();
        document.getDocumentHeader().setDocumentDescription("test");
        committee.setCommitteeId((new Long(new java.util.Date().getTime())).toString());
        committee.setCommitteeName("test");
        committee.setCommitteeTypeCode("1");
        committee.setHomeUnitNumber("000001");
        committee.setCommitteeDescription("description");
        committee.setMaxProtocols(5);
        committee.setMinimumMembersRequired(4);
        committee.setAdvancedSubmissionDaysRequired(3);
        committee.setReviewTypeCode("1");
        committee.setScheduleDescription("schedule description");
    }
    
    /**
     * Get a new Committee Document.
     * 
     * @return a new Committee Document.
     * @throws WorkflowException
     */
    protected CommitteeDocument getNewCommitteeDocument() throws WorkflowException {
        return (CommitteeDocument) documentService.getNewDocument("CommitteeDocument");
    }
    
    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key and error key.
     * @param propertyKey
     * @param errorKey
     */
    protected void assertError(String propertyKey, String errorKey) {
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }
    
}
