/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.actions.modifysubmission;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
public class ProtocolModifySubmissionServiceTest extends KcIntegrationTestBase {
    
    private ProtocolModifySubmissionServiceImpl service;
    private BusinessObjectService businessObjectService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};

    @Before
    public void setUp() throws Exception {

        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        
        service = new ProtocolModifySubmissionServiceImpl();
        service.setTaskAuthorizationService(getMockTaskAuthorizationService());
        service.setBusinessObjectService(businessObjectService);
        service.setDocumentService(KcServiceLocator.getService(DocumentService.class));
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        businessObjectService = null;
        
    }

    @Test
    public void testModifySubmissionFromFullReviewType() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission oldSubmission = createSubmission(protocolDocument.getProtocol(), ProtocolReviewType.FULL_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(oldSubmission);
        
        service.modifySubmission(protocolDocument, getMockProtocolModifySubmissionBean(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE));
        
        ProtocolSubmission newSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE, newSubmission.getProtocolReviewTypeCode());
    }
    
    @Test
    public void testModifySubmissionFromExpeditedReviewType() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission oldSubmission = createSubmission(protocolDocument.getProtocol(), ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(oldSubmission);
        
        service.modifySubmission(protocolDocument, getMockProtocolModifySubmissionBean(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE));
        
        ProtocolSubmission newSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE, newSubmission.getProtocolReviewTypeCode());
    }
    
    @Test
    public void testModifySubmissionFromExemptReviewType() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission oldSubmission = createSubmission(protocolDocument.getProtocol(), ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(oldSubmission);
        
        service.modifySubmission(protocolDocument, getMockProtocolModifySubmissionBean(ProtocolReviewType.FULL_TYPE_CODE));
        
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
