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
package org.kuali.kra.irb.actions.responseapproval;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the ProtocolResponseApprovalService implementation.
 */
public class ProtocolResponseApprovalServiceTest extends KcUnitTestBase {

    private static final Date APPROVAL_DATE = new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), -1).getTime());
    private static final Date EXPIRATION_DATE = new Date(DateUtils.addYears(APPROVAL_DATE, 1).getTime());
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final String COMMENTS = "Testing response approval";
    
    private static final String COMMITTEE_ID = "668";
    private static final String SCHEDULE_ID = "1";
    private static final String RESPONSE_REVIEW_TYPE = "6";
    private static final String ANNUAL_SCHEDULED_BY_IRB_SUBMISSION_QUALIFIER = "2";
    
    private static final String VALID_SUBMISSION_TYPE = "100";
    
    private ProtocolResponseApprovalService protocolResponseApprovalService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private BusinessObjectService businessObjectService;  
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        protocolResponseApprovalService = KraServiceLocator.getService(ProtocolResponseApprovalService.class);
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testApproveResponse() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();

        ProtocolApproveBean responseApprovalBean = new ProtocolApproveBean(null);
        responseApprovalBean.setApprovalDate(APPROVAL_DATE);
        responseApprovalBean.setExpirationDate(EXPIRATION_DATE);
        responseApprovalBean.setActionDate(ACTION_DATE);
        responseApprovalBean.setComments(COMMENTS);
        addComments(protocolDocument.getProtocol(), responseApprovalBean);
        
        ProtocolSubmitAction submitAction = createSubmitAction(COMMITTEE_ID, SCHEDULE_ID, RESPONSE_REVIEW_TYPE);
        submitAction.setSubmissionQualifierTypeCode(ANNUAL_SCHEDULED_BY_IRB_SUBMISSION_QUALIFIER);
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        responseApprovalBean.getReviewComments().setProtocolId(protocolDocument.getProtocol().getProtocolId());
        protocolResponseApprovalService.approveResponse(protocolDocument.getProtocol(), responseApprovalBean);
    
        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals(COMMENTS, protocolAction.getComments());
        
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmissions().get(0);
        assertEquals(ProtocolSubmissionStatus.APPROVED, submission.getSubmissionStatusCode());
    }
    
    private void addComments(Protocol protocol, ProtocolApproveBean responseApprovalBean) {
        List<CommitteeScheduleMinute> comments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeScheduleMinute firstComment = new CommitteeScheduleMinute();
        firstComment.setProtocolIdFk(protocol.getProtocolId());
        responseApprovalBean.getReviewComments().setComments(comments);
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

    private ProtocolSubmitAction createSubmitAction(String committeeId, String scheduleId, String protocolReviewTypeCode) {
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(protocolReviewTypeCode);
        submitAction.setCommitteeId(committeeId);
        submitAction.setScheduleId(scheduleId);
        return submitAction;
    }
    
}