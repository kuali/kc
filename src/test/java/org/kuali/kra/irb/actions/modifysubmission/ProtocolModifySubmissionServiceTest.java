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

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kcb.service.impl.BusinessObjectServiceImpl;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.impl.DocumentServiceImpl;

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

    //@Test
    public void testModifySubmisison() throws Exception{
        /*
        ProtocolDocument pd = ProtocolFactory.createProtocolDocument("pdMPS101");
        pd.getProtocol().setProtocolId(new Long(101));
        
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setProtocol(pd.getProtocol());
        ps.setComments("test this");
        ps.setProtocolId(pd.getProtocol().getProtocolId());
        //ps.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);\
        ps.setProtocolReviewTypeCode("1");
        ps.setSubmissionTypeQualifierCode(ProtocolSubmissionQualifierType.COMPLAINT);
        ps.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        ps.setSubmissionId(new Long(666));
        ps.setSubmissionNumber(new Integer(666));
        ps.setProtocolId(pd.getProtocol().getProtocolId());
        ps.setSequenceNumber(new Integer(101));
        ps.setSubmissionStatusCode(ProtocolSubmissionStatus.PENDING);
        ps.setSubmissionDate(new Timestamp(2010, 8, 20, 12, 12, 12, 12));
        
        ProtocolSubmissionType type = new ProtocolSubmissionType();
        type.setDescription("test");
        type.setSubmissionTypeCode(ProtocolSubmissionType.NOTIFY_IRB);
        ps.setSubmissionTypeCode(type.getSubmissionTypeCode());
        ps.setProtocolSubmissionType(type);
        ps.setProtocolReviewTypeCode("tst");
        
        pd.getProtocol().setProtocolSubmission(ps);
        businessObjectService.save(pd);
        businessObjectService.save(ps);
        */
        Map fieldValue1 = new HashMap();
        fieldValue1.put("SUBMISSION_ID", "10712");
        ProtocolSubmission ps = (ProtocolSubmission)businessObjectService.findByPrimaryKey(ProtocolSubmission.class, fieldValue1);
        ProtocolDocument pd = ProtocolFactory.createProtocolDocument("pdMPS101");
        ps.getProtocol().setProtocolDocument(pd);
        pd.getProtocol().setProtocolSubmission(ps);
        ps.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        
        ProtocolModifySubmissionAction actionBean = new ProtocolModifySubmissionAction(ps);
        actionBean.setBillable(true);
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        actionBean.getExemptStudiesCheckList().get(0).setChecked(true);
        
        protocolModifySubmissionService.modifySubmisison(ps.getProtocol().getProtocolDocument(), actionBean);
        
        Map fieldValue = new HashMap();
        fieldValue.put("SUBMISSION_ID", ps.getSubmissionId());
        
        ProtocolSubmission psFromDB = (ProtocolSubmission)businessObjectService.findByPrimaryKey(ProtocolSubmission.class, fieldValue);
        
        assertEquals(ps.getProtocolReviewTypeCode(), actionBean.getProtocolReviewTypeCode());
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
