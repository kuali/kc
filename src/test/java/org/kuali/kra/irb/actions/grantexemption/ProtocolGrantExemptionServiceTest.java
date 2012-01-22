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
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.util.DateUtils;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Test the ProtocolWithdrawService implementation.
 */
public class ProtocolGrantExemptionServiceTest extends KcUnitTestBase {

    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date APPROVAL_DATE = DateUtils.convertToSqlDate(DateUtils.addWeeks(ACTION_DATE, -1));
    private static final String COMMENTS = "something silly";
    private static final String VALID_EXEMPT_STUDIES_ITEM_CODE = "1";
    
    private ProtocolGrantExemptionServiceImpl service;
    private ProtocolSubmitActionService protocolSubmitActionService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Before
    public void setUp() throws Exception {
        super.setUp();

        service = new ProtocolGrantExemptionServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());
        service.setProtocolActionCorrespondenceGenerationService(getMockActionCorrespondenceGenerationService());
        
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
        
        service.grantExemption(protocolDocument.getProtocol(), getMockGrantExemptionBean());
    
        assertEquals(ProtocolStatus.EXEMPT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(APPROVAL_DATE, protocolDocument.getProtocol().getApprovalDate());
        assertEquals(COMMENTS, action.getComments());
        assertEquals(ACTION_DATE, action.getActionDate());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.EXEMPT, submission.getSubmissionStatusCode());
    }
    
    private ProtocolSubmitAction getMockSubmitAction() {
        final ProtocolSubmitAction action = context.mock(ProtocolSubmitAction.class);
        
        context.checking(new Expectations() {{
            allowing(action).getSubmissionTypeCode();
            will(returnValue(ProtocolSubmissionType.INITIAL_SUBMISSION));
            
            allowing(action).getProtocolReviewTypeCode();
            will(returnValue(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE));
            
            allowing(action).getSubmissionQualifierTypeCode();
            will(returnValue(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB));
            
            allowing(action).getNewCommitteeId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getNewScheduleId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getReviewers();
            will(returnValue(new ArrayList<ProtocolReviewerBean>()));
            
            allowing(action).getExemptStudiesCheckList();
            will(returnValue(Collections.singletonList(getMockExemptStudiesCheckListItem())));
        }});
        
        return action;
    }
    
    private ExemptStudiesCheckListItem getMockExemptStudiesCheckListItem() {
        final ExemptStudiesCheckListItem item = context.mock(ExemptStudiesCheckListItem.class);
        
        context.checking(new Expectations() {{
            allowing(item).getExemptStudiesCheckListCode();
            will(returnValue(VALID_EXEMPT_STUDIES_ITEM_CODE));
            
            allowing(item).getChecked();
            will(returnValue(true));
        }});
        
        return item;
    }
    
    private ProtocolGrantExemptionBean getMockGrantExemptionBean() {
        final ProtocolGrantExemptionBean bean = context.mock(ProtocolGrantExemptionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getActionDate();
            will(returnValue(ACTION_DATE));
            
            allowing(bean).getComments();
            will(returnValue(COMMENTS));
            
            allowing(bean).getApprovalDate();
            will(returnValue(APPROVAL_DATE));
            
            allowing(bean).getCorrespondence();
            will(returnValue(new GrantExemptionCorrespondence()));
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
    
}