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
package org.kuali.kra.irb.actions.withdraw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the ProtocolWithdrawService implementation.
 */
public class ProtocolWithdrawServiceTest extends KcUnitTestBase {

    private static final String REASON = "my test reason";
    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "1";
    
    private ProtocolWithdrawService protocolWithdrawService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private BusinessObjectService businessObjectService;  
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        protocolWithdrawService = KraServiceLocator.getService(ProtocolWithdrawService.class);
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testWithdrawal() throws Exception {
        ProtocolWithdrawBean withdrawBean = new ProtocolWithdrawBean();
        withdrawBean.setReason(REASON);
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolSubmitAction submitAction = createSubmitAction("668", "1", VALID_REVIEW_TYPE);
        submitAction.setSubmissionQualifierTypeCode(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB);
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        Committee committee = new Committee();
        protocolDocument.getProtocol().getProtocolSubmission().setCommittee(committee);
        
        ProtocolDocument newProtocolDocument = protocolWithdrawService.withdraw(protocolDocument.getProtocol(), withdrawBean);
    
        assertTrue(protocolDocument.getDocumentHeader().getWorkflowDocument().stateIsCanceled());
        assertEquals(ProtocolStatus.WITHDRAWN, newProtocolDocument.getProtocol().getProtocolStatusCode());
        
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals(REASON, protocolAction.getComments());
        
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmissions().get(0);
        assertEquals(ProtocolSubmissionStatus.WITHDRAWN, submission.getSubmissionStatusCode());
    }

    @SuppressWarnings("unchecked")
    private ProtocolAction findProtocolAction(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolAction> actions = (List<ProtocolAction>) businessObjectService.findMatching(ProtocolAction.class, fieldValues);
        
        assertEquals(2, actions.size());
        ProtocolAction action = actions.get(1);
        return action;
    }
    
    /*
     * Create protocol submission action.
     */
    private ProtocolSubmitAction createSubmitAction(String committeeId, String scheduleId, String protocolReviewTypeCode) {
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(protocolReviewTypeCode);
        submitAction.setCommitteeId(committeeId);
        submitAction.setScheduleId(scheduleId);
        return submitAction;
    }
}
