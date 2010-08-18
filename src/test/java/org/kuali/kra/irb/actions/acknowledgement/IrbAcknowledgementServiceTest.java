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
package org.kuali.kra.irb.actions.acknowledgement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class IrbAcknowledgementServiceTest extends KcUnitTestBase {

    private static final String COMMENTS = "test acknowledgement";
    private static final String VALID_REVIEW_TYPE = "7";
    private static final String VALID_CONTINGENCY_CODE_1 = "22";
    
    private IrbAcknowledgementService irbAcknowledgementService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private BusinessObjectService businessObjectService;  
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        irbAcknowledgementService = KraServiceLocator.getService(IrbAcknowledgementService.class);
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testIrbAcknowledgement() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();

        IrbAcknowledgementBean irbAcknowledgementBean = new IrbAcknowledgementBean();
        irbAcknowledgementBean.setComments(COMMENTS);
        irbAcknowledgementBean.setActionDate(new Date(System.currentTimeMillis()));
        addComments(protocolDocument.getProtocol(), irbAcknowledgementBean);
        
        ProtocolSubmitAction submitAction = createSubmitAction("668", "1", VALID_REVIEW_TYPE);
        submitAction.setSubmissionQualifierTypeCode("2");
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        irbAcknowledgementBean.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        irbAcknowledgementService.irbAcknowledgement(protocolDocument.getProtocol(), irbAcknowledgementBean);
    
        assertEquals("101", protocolDocument.getProtocol().getProtocolStatusCode());
        
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals(COMMENTS, protocolAction.getComments());
        
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmissions().get(0);
        assertEquals("212", submission.getSubmissionStatusCode());
    }

    
    private void addComments(Protocol protocol, IrbAcknowledgementBean irbAcknowledgementBean) {
        List<CommitteeScheduleMinute> comments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeScheduleMinute firstComment = new CommitteeScheduleMinute();
        firstComment.setProtocolContingencyCode(VALID_CONTINGENCY_CODE_1);
        firstComment.setPrivateCommentFlag(true);
        firstComment.setProtocolIdFk(protocol.getProtocolId());
        irbAcknowledgementBean.getReviewComments().setComments(comments);
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
        submitAction.setSubmissionTypeCode(ProtocolSubmissionType.NOTIFY_IRB);
        submitAction.setProtocolReviewTypeCode(protocolReviewTypeCode);
        submitAction.setCommitteeId(committeeId);
        submitAction.setScheduleId(scheduleId);
        return submitAction;
    }

}
