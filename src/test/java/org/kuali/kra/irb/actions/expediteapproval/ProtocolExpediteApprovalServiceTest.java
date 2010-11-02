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
package org.kuali.kra.irb.actions.expediteapproval;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.DateUtils;

/**
 * Test the ProtocolWithdrawService implementation.
 */
public class ProtocolExpediteApprovalServiceTest extends KcUnitTestBase {

    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date APPROVAL_DATE = DateUtils.convertToSqlDate(DateUtils.addWeeks(ACTION_DATE, -1));
    private static final Date EXPIRATION_DATE = DateUtils.convertToSqlDate(DateUtils.addYears(ACTION_DATE, 1));
    private static final String COMMENTS = "Testing expedited approval";
    private static final String VALID_EXPEDITED_REVIEW_CHECKLIST_CODE = "1";

    private ProtocolExpediteApprovalServiceImpl service;
    private ProtocolSubmitActionService protocolSubmitActionService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Before
    public void setUp() throws Exception {
        super.setUp();

        service = new ProtocolExpediteApprovalServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());
        service.setProtocolActionCorrespondenceGenerationService(getMockActionCorrespondenceGenerationService());
        service.setProtocolOnlineReviewService(getMockOnlineReviewService());
        
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        protocolSubmitActionService = null;
        
        super.tearDown();
    }
    
    @Test
    public void testGrantExemption() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();

        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockSubmitAction());
        
        assertEquals(ProtocolStatus.SUBMITTED_TO_IRB, protocolDocument.getProtocol().getProtocolStatusCode());
        
        service.grantExpeditedApproval(protocolDocument.getProtocol(), getMockApproveBean());
    
        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(APPROVAL_DATE, protocolDocument.getProtocol().getApprovalDate());
        assertEquals(EXPIRATION_DATE, protocolDocument.getProtocol().getExpirationDate());
        assertEquals(COMMENTS, action.getComments());
        assertEquals(ACTION_DATE, action.getActionDate());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.APPROVED, submission.getSubmissionStatusCode());
    }
    
    private ProtocolSubmitAction getMockSubmitAction() {
        final ProtocolSubmitAction action = context.mock(ProtocolSubmitAction.class);
        
        context.checking(new Expectations() {{
            allowing(action).getSubmissionTypeCode();
            will(returnValue(ProtocolSubmissionType.INITIAL_SUBMISSION));
            
            allowing(action).getProtocolReviewTypeCode();
            will(returnValue(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE));
            
            allowing(action).getSubmissionQualifierTypeCode();
            will(returnValue(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB));
            
            allowing(action).getNewCommitteeId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getNewScheduleId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getReviewers();
            will(returnValue(new ArrayList<ProtocolReviewerBean>()));
            
            allowing(action).getExpeditedReviewCheckList();
            will(returnValue(Collections.singletonList(getMockExpeditedReviewCheckListItem())));
        }});
        
        return action;
    }
    
    private ExpeditedReviewCheckListItem getMockExpeditedReviewCheckListItem() {
        final ExpeditedReviewCheckListItem item = context.mock(ExpeditedReviewCheckListItem.class);
        
        context.checking(new Expectations() {{
            allowing(item).getExpeditedReviewCheckListCode();
            will(returnValue(VALID_EXPEDITED_REVIEW_CHECKLIST_CODE));
            
            allowing(item).getChecked();
            will(returnValue(true));
        }});
        
        return item;
    }
    
    private ProtocolApproveBean getMockApproveBean() {
        final ProtocolApproveBean bean = context.mock(ProtocolApproveBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getApprovalDate();
            will(returnValue(APPROVAL_DATE));
            
            allowing(bean).getExpirationDate();
            will(returnValue(EXPIRATION_DATE));
            
            allowing(bean).getActionDate();
            will(returnValue(ACTION_DATE));
            
            allowing(bean).getComments();
            will(returnValue(COMMENTS));
        }});
        
        return bean;
    }
    
    private DocumentService getMockDocumentService() {
        final DocumentService service = context.mock(DocumentService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolActionCorrespondenceGenerationService getMockActionCorrespondenceGenerationService() {
        final ProtocolActionCorrespondenceGenerationService service = context.mock(ProtocolActionCorrespondenceGenerationService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolOnlineReviewService getMockOnlineReviewService() {
        final ProtocolOnlineReviewService service = context.mock(ProtocolOnlineReviewService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
}