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
package org.kuali.kra.irb.actions.modifysubmission;

import java.sql.Date;
import java.util.Collections;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class ProtocolModifySubmissionServiceTest extends KcUnitTestBase {
    
    private ProtocolModifySubmissionServiceImpl service;
    private BusinessObjectService businessObjectService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        
        service = new ProtocolModifySubmissionServiceImpl();
        service.setTaskAuthorizationService(getMockTaskAuthorizationService());
        service.setBusinessObjectService(businessObjectService);
        service.setDocumentService(KraServiceLocator.getService(DocumentService.class));
    }

    @Override
    @After
    public void tearDown() throws Exception {
        service = null;
        businessObjectService = null;
        
        super.tearDown();
    }

    @Test
    public void testModifySubmissionFromFullReviewType() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission oldSubmission = createSubmission(protocolDocument.getProtocol(), ProtocolReviewType.FULL_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(oldSubmission);
        
        service.modifySubmisison(protocolDocument, getMockProtocolModifySubmissionBean(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE));
        
        ProtocolSubmission newSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE, newSubmission.getProtocolReviewTypeCode());
    }
    
    @Test
    public void testModifySubmissionFromExpeditedReviewType() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission oldSubmission = createSubmission(protocolDocument.getProtocol(), ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(oldSubmission);
        
        service.modifySubmisison(protocolDocument, getMockProtocolModifySubmissionBean(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE));
        
        ProtocolSubmission newSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE, newSubmission.getProtocolReviewTypeCode());
    }
    
    @Test
    public void testModifySubmissionFromExemptReviewType() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission oldSubmission = createSubmission(protocolDocument.getProtocol(), ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(oldSubmission);
        
        service.modifySubmisison(protocolDocument, getMockProtocolModifySubmissionBean(ProtocolReviewType.FULL_TYPE_CODE));
        
        ProtocolSubmission newSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolReviewType.FULL_TYPE_CODE, newSubmission.getProtocolReviewTypeCode());
    }
    
    private ProtocolSubmission createSubmission(Protocol protocol, String protocolReviewTypeCode) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocol(protocol);
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSubmissionNumber(1);
        submission.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submission.setSubmissionStatusCode(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submission.setProtocolReviewTypeCode(protocolReviewTypeCode);
        submission.setSubmissionDate(new Date(System.currentTimeMillis()));
        submission.setCommitteeId("1");
        return submission;
    }
    
    private TaskAuthorizationService getMockTaskAuthorizationService() {
        final TaskAuthorizationService service = context.mock(TaskAuthorizationService.class);
        
        context.checking(new Expectations() {{
            allowing(service).isAuthorized(with(any(String.class)), with(any(Task.class)));
            will(returnValue(true));
        }});
        
        return service;
    }
    
    private ProtocolModifySubmissionBean getMockProtocolModifySubmissionBean(final String protocolReviewTypeCode) {
        final ProtocolModifySubmissionBean bean = context.mock(ProtocolModifySubmissionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getSubmissionTypeCode();
            will(returnValue(ProtocolSubmissionType.INITIAL_SUBMISSION));
            
            allowing(bean).getSubmissionQualifierTypeCode();
            will(returnValue(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB));
            
            allowing(bean).getProtocolReviewTypeCode();
            will(returnValue(protocolReviewTypeCode));
            
            allowing(bean).isBillable();
            will(returnValue(true));
            
            allowing(bean).getExpeditedReviewCheckList();
            will(returnValue(Collections.emptyList()));
            
            allowing(bean).getExemptStudiesCheckList();
            will(returnValue(Collections.emptyList()));
        }});
        
        return bean;
    }

}