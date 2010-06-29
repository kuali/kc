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
package org.kuali.kra.irb.actions.grantexemption;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the ProtocolWithdrawService implementation.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_CONTINGENCY.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_EXEMPT_STUDIES_CHECKLIST.sql", delimiter = ";"), 
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";")
}))
public class ProtocolGrantExemptionServiceTest extends KraTestBase {

    private static final String COMMENTS = "something silly";
    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "3";
    private static final String VALID_CONTINGENCY_CODE_1 = "22";
    private static final String VALID_EXEMPT_STUDIES_ITEM_CODE = "1";
    
    private ProtocolGrantExemptionService protocolGrantExemptionService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private BusinessObjectService businessObjectService;  
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        protocolGrantExemptionService = KraServiceLocator.getService(ProtocolGrantExemptionService.class);
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testGrantExemption() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();

        ProtocolGrantExemptionBean grantExemptionBean = new ProtocolGrantExemptionBean();
        grantExemptionBean.setComments(COMMENTS);
        grantExemptionBean.setApprovalDate(new Date(System.currentTimeMillis()));
        grantExemptionBean.setActionDate(new Date(System.currentTimeMillis()));
        addComments(protocolDocument.getProtocol(), grantExemptionBean);
        
        ProtocolSubmitAction submitAction = createSubmitAction("668", "1", VALID_REVIEW_TYPE);
        addExemptStudiesCheckList(submitAction);
        submitAction.setSubmissionQualifierTypeCode("2");
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        grantExemptionBean.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        protocolGrantExemptionService.grantExemption(protocolDocument.getProtocol(), grantExemptionBean);
    
        assertEquals(ProtocolStatus.EXEMPT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals(COMMENTS, protocolAction.getComments());
        
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmissions().get(0);
        assertEquals(ProtocolSubmissionStatus.EXEMPT, submission.getSubmissionStatusCode());
    }

    private void addExemptStudiesCheckList(ProtocolSubmitAction submitAction) {
        List<ExemptStudiesCheckListItem> checkList = new ArrayList<ExemptStudiesCheckListItem>();
        ExemptStudiesCheckListItem exemptStudiesCheckListItem = new ExemptStudiesCheckListItem();
        exemptStudiesCheckListItem.setExemptStudiesCheckListCode(VALID_EXEMPT_STUDIES_ITEM_CODE);
        exemptStudiesCheckListItem.setChecked(true);
        submitAction.setExemptStudiesCheckList(checkList);
    }
    
    private void addComments(Protocol protocol, ProtocolGrantExemptionBean grantExemptionBean) {
        List<CommitteeScheduleMinute> comments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeScheduleMinute firstComment = new CommitteeScheduleMinute();
        firstComment.setProtocolContingencyCode(VALID_CONTINGENCY_CODE_1);
        firstComment.setPrivateCommentFlag(true);
        firstComment.setProtocolIdFk(protocol.getProtocolId());
        grantExemptionBean.getReviewComments().setComments(comments);
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
