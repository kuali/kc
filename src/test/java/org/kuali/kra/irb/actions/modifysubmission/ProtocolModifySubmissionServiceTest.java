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


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

public class ProtocolModifySubmissionServiceTest extends KcUnitTestBase {
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ProtocolModifySubmissionServiceImpl protocolModifySubmissionServiceImpl;
    private ProtocolModifySubmissionService protocolModifySubmissionService;

    @Before
    public void setUp() throws Exception {
        documentService = KraServiceLocator.getService(DocumentService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolModifySubmissionService = KraServiceLocator.getService(ProtocolModifySubmissionService.class);
        protocolModifySubmissionServiceImpl = new ProtocolModifySubmissionServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        documentService = null;
        businessObjectService = null;
        protocolModifySubmissionService = null;
        protocolModifySubmissionServiceImpl = null;
    }

    @Test
    public void testModifySubmisison1() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        ProtocolModifySubmissionBean actionBean = new ProtocolModifySubmissionBean(protocolDocument.getProtocol().getProtocolSubmission());
        actionBean.setBillable(true);
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        actionBean.getExemptStudiesCheckList().get(0).setChecked(true);
        
        protocolModifySubmissionService.modifySubmisison(protocolDocument, actionBean);
        
        Map fieldValue = new HashMap();
        fieldValue.put("SUBMISSION_ID", protocolDocument.getProtocol().getProtocolSubmission().getSubmissionId());
        
        ProtocolSubmission psFromDB = (ProtocolSubmission)businessObjectService.findByPrimaryKey(ProtocolSubmission.class, fieldValue);
        
        assertEquals(protocolDocument.getProtocol().getProtocolSubmission().getProtocolReviewTypeCode(), actionBean.getProtocolReviewTypeCode());
    }
    
    @Test
    public void testModifySubmisison2() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        ProtocolModifySubmissionBean actionBean = new ProtocolModifySubmissionBean(protocolDocument.getProtocol().getProtocolSubmission());
        actionBean.setBillable(true);
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        actionBean.getExemptStudiesCheckList().get(0).setChecked(true);
        
        protocolModifySubmissionService.modifySubmisison(protocolDocument, actionBean);
        
        Map fieldValue = new HashMap();
        fieldValue.put("SUBMISSION_ID", protocolDocument.getProtocol().getProtocolSubmission().getSubmissionId());
        
        ProtocolSubmission psFromDB = (ProtocolSubmission)businessObjectService.findByPrimaryKey(ProtocolSubmission.class, fieldValue);
        
        assertEquals(protocolDocument.getProtocol().getProtocolSubmission().getProtocolReviewTypeCode(), actionBean.getProtocolReviewTypeCode());
    }
    
    @Test
    public void testModifySubmisison3() throws Exception{       
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        ProtocolModifySubmissionBean actionBean = new ProtocolModifySubmissionBean(protocolDocument.getProtocol().getProtocolSubmission());
        actionBean.setBillable(true);
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        actionBean.getExemptStudiesCheckList().get(0).setChecked(true);
        
        protocolModifySubmissionService.modifySubmisison(protocolDocument, actionBean);
        
        Map fieldValue = new HashMap();
        fieldValue.put("SUBMISSION_ID", protocolDocument.getProtocol().getProtocolSubmission().getSubmissionId());
        
        ProtocolSubmission psFromDB = (ProtocolSubmission)businessObjectService.findByPrimaryKey(ProtocolSubmission.class, fieldValue);
        
        assertEquals(protocolDocument.getProtocol().getProtocolSubmission().getProtocolReviewTypeCode(), actionBean.getProtocolReviewTypeCode());
    }
    
    private ProtocolSubmission createSubmission(Protocol protocol, String statusCode) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocol(protocol);
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSubmissionNumber(1);
        submission.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submission.setSubmissionStatusCode(statusCode);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        if (StringUtils.equals(statusCode, ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            submission.setCommitteeId("1");
        }
        return submission;
    }

    @Test
    public void testSetDocumentService() {
        protocolModifySubmissionServiceImpl.setDocumentService(documentService);
        assertTrue(true);//if the above line didn't error, it's all good
    }

    @Test
    public void testSetBusinessObjectService() {
        protocolModifySubmissionServiceImpl.setBusinessObjectService(businessObjectService);
        assertTrue(true);//if the above line didn't error, it's all good
    }

}
