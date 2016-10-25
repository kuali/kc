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
package org.kuali.kra.irb.actions.grantexemption;

import org.apache.commons.lang3.time.DateUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
/**
 * Test the ProtocolWithdrawService implementation.
 */
public class ProtocolGrantExemptionServiceTest extends KcIntegrationTestBase {

    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date APPROVAL_DATE = org.kuali.coeus.sys.framework.util.DateUtils.convertToSqlDate(DateUtils.addWeeks(ACTION_DATE, -1));
    private static final String COMMENTS = "something silly";
    private static final String VALID_EXEMPT_STUDIES_ITEM_CODE = "1";
    
    private ProtocolGrantExemptionServiceImpl service;
    private ProtocolSubmitActionService protocolSubmitActionService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Before
    public void setUp() throws Exception {

        service = new ProtocolGrantExemptionServiceImpl();
        service.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());
        service.setProtocolActionCorrespondenceGenerationService(getMockActionCorrespondenceGenerationService());
        
        protocolSubmitActionService = KcServiceLocator.getService(ProtocolSubmitActionService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        protocolSubmitActionService = null;
        
    }
    
    @Test
    public void testGrantExemption() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument, getMockSubmitAction(), null);
        
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
