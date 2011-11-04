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

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
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
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Test the ProtocolWithdrawService implementation.
 */
public class ProtocolWithdrawServiceTest extends KcUnitTestBase {

    private static final String REASON = "my test reason";
    
    private ProtocolWithdrawServiceImpl service;
    private ProtocolSubmitActionService protocolSubmitActionService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Before
    public void setUp() throws Exception {
        super.setUp();

        service = new ProtocolWithdrawServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setProtocolOnlineReviewService(getMockOnlineReviewService());
        service.setBusinessObjectService(getMockBusinessObjectService());
        service.setDocumentService(KraServiceLocator.getService(DocumentService.class));
        service.setProtocolVersionService(KraServiceLocator.getService(ProtocolVersionService.class));
        service.setProtocolAssignToAgendaService(getMockProtocolAssignToAgendaService());
        service.setProtocolActionCorrespondenceGenerationService(getMockActionCorrespondenceGenerationService());
        service.setKcNotificationService(getMockKcNotificationService());
        
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        protocolSubmitActionService = null;
        
        super.tearDown();
    }
    
    @Test
    public void testWithdrawal() throws Exception {
        ProtocolDocument oldProtocolDocument = ProtocolFactory.createProtocolDocument();
        
        protocolSubmitActionService.submitToIrbForReview(oldProtocolDocument.getProtocol(), getMockSubmitAction());
        assertEquals(ProtocolStatus.SUBMITTED_TO_IRB, oldProtocolDocument.getProtocol().getProtocolStatusCode());
        
        ProtocolDocument newProtocolDocument = service.withdraw(oldProtocolDocument.getProtocol(), getMockProtocolWithdrawBean());
    
        assertTrue(oldProtocolDocument.getDocumentHeader().getWorkflowDocument().stateIsCanceled());
        assertEquals(ProtocolStatus.WITHDRAWN, newProtocolDocument.getProtocol().getProtocolStatusCode());
        
        ProtocolAction protocolAction = oldProtocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(protocolAction);
        assertEquals(REASON, protocolAction.getComments());
        
        ProtocolSubmission submission = oldProtocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolSubmissionStatus.WITHDRAWN, submission.getSubmissionStatusCode());
    }
    
    private ProtocolOnlineReviewService getMockOnlineReviewService() {
        final ProtocolOnlineReviewService service = context.mock(ProtocolOnlineReviewService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    
    private ProtocolAssignToAgendaService getMockProtocolAssignToAgendaService() {
        final ProtocolAssignToAgendaService service = context.mock(ProtocolAssignToAgendaService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getAssignedToAgendaProtocolAction(with(any(Protocol.class)));
            will(returnValue(null));
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
    
    private ProtocolSubmitAction getMockSubmitAction() {
        final ProtocolSubmitAction action = context.mock(ProtocolSubmitAction.class);
        
        context.checking(new Expectations() {{
            allowing(action).getSubmissionTypeCode();
            will(returnValue(ProtocolSubmissionType.INITIAL_SUBMISSION));
            
            allowing(action).getProtocolReviewTypeCode();
            will(returnValue(ProtocolReviewType.FULL_TYPE_CODE));
            
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
    
    private ProtocolWithdrawBean getMockProtocolWithdrawBean() {
        final ProtocolWithdrawBean bean = context.mock(ProtocolWithdrawBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getReason();
            will(returnValue(REASON));
            
            allowing(bean).getCorrespondence();
            will(returnValue(new WithdrawCorrespondence()));
        }});
        
        return bean;
    }
    
    private KcNotificationService getMockKcNotificationService() {
        final KcNotificationService service = context.mock(KcNotificationService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
}