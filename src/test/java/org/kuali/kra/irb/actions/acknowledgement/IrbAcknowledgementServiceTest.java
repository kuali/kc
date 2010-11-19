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
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
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
import org.kuali.rice.kns.service.DocumentService;

public class IrbAcknowledgementServiceTest extends KcUnitTestBase {

    private static final String COMMENTS = "Test IRB Acknowledgement";
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    
    private IrbAcknowledgementServiceImpl service;
    private ProtocolSubmitActionService protocolSubmitActionService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new IrbAcknowledgementServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());
        
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        protocolSubmitActionService = null;
        service = null;
        
        super.tearDown();
    }
    
    @Test
    public void testIrbAcknowledgement() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockSubmitAction());
        
        assertEquals(ProtocolStatus.SUBMITTED_TO_IRB, protocolDocument.getProtocol().getProtocolStatusCode());

        service.irbAcknowledgement(protocolDocument.getProtocol(), getMockIrbAcknowledgementBean());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(COMMENTS, action.getComments());
        assertEquals(ACTION_DATE, action.getActionDate());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.IRB_ACKNOWLEDGEMENT, submission.getSubmissionStatusCode());
    }
    
    private DocumentService getMockDocumentService() {
        final DocumentService service = context.mock(DocumentService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolSubmitAction getMockSubmitAction() {
        final ProtocolSubmitAction action = context.mock(ProtocolSubmitAction.class);
        
        context.checking(new Expectations() {{
            allowing(action).getSubmissionTypeCode();
            will(returnValue(ProtocolSubmissionType.NOTIFY_IRB));
            
            allowing(action).getProtocolReviewTypeCode();
            will(returnValue(ProtocolReviewType.FYI_TYPE_CODE));
            
            allowing(action).getSubmissionQualifierTypeCode();
            will(returnValue(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB));
            
            allowing(action).getNewCommitteeId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getNewScheduleId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getReviewers();
            will(returnValue(new ArrayList<ProtocolReviewerBean>()));
        }});
        
        return action;
    }
    
    private IrbAcknowledgementBean getMockIrbAcknowledgementBean() {
        final IrbAcknowledgementBean bean = context.mock(IrbAcknowledgementBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getComments();
            will(returnValue(COMMENTS));
            
            allowing(bean).getActionDate();
            will(returnValue(ACTION_DATE));
            
            allowing(bean).getReviewCommentsBean();
            will(returnValue(new ReviewCommentsBean(Constants.EMPTY_STRING)));
        }});
        
        return bean;
    }

}