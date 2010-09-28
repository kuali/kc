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
package org.kuali.kra.irb.actions.genericactions;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.bo.AdHocRouteRecipient;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolGenericActionsServiceTest extends KcUnitTestBase {
    
    private BusinessObjectService businessObjectService;
    private ProtocolSubmitActionService submitActionService;
    private DocumentService documentService;
    private ProtocolGenericActionService genericActionService;
    
    private static final String BASIC_COMMENT = "some dummy comments here";
    private static final Date BASIC_ACTION_DATE = new Date(System.currentTimeMillis());
    
    private static final String COMMITTEE_ID = "1285093659990";
    private static final String SCHEDULE_ID = "10014";

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        submitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        genericActionService = KraServiceLocator.getService(ProtocolGenericActionService.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        genericActionService = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }

    @Test
    public void testClose() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.close(prot, actionBean);
        String expected = ProtocolStatus.CLOSED_ADMINISTRATIVELY;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testCloseEnrollment() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.closeEnrollment(prot, actionBean);
        String expected = ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testExpire() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.expire(prot, actionBean);
        String expected = ProtocolStatus.EXPIRED;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testPermitDataAnalysis() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.permitDataAnalysis(prot, actionBean);
        String expected = ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testReopen() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.reopen(prot, actionBean);
        String expected = ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    /**@Test
    public void testSuspendByIRB() throws Exception {
        GlobalVariables.setUserSession(new UserSession("testIrbAdmin")); //is an IRB
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.suspend(prot, actionBean);
        String expected = ProtocolStatus.SUSPENDED_BY_IRB;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
        GlobalVariables.setUserSession(new UserSession("quickstart")); //reset testing user for further testing
    }*/
    
    @Test
    public void testSuspendByPI() throws Exception {
        //quickstart is a PI
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.suspend(prot, actionBean);
        String expected = ProtocolStatus.SUSPENDED_BY_PI;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testSuspendByDsmb() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.suspendByDsmb(prot, actionBean);
        String expected = ProtocolStatus.SUSPENDED_BY_DSMB;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testTerminate() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.terminate(prot, actionBean);
        String expected = ProtocolStatus.TERMINATED_BY_IRB;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testDisapprove() throws Exception {
        Protocol prot = getNewProtocol();
        businessObjectService.save(prot);
        submitActionService.submitToIrbForReview(prot, buildProtocolSubmitAction());
        documentService.routeDocument(prot.getProtocolDocument(), "Initial Document Route", new ArrayList<AdHocRouteRecipient>());
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        genericActionService.disapprove(prot, actionBean);
        String expected = ProtocolStatus.DISAPPROVED;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
        assertTrue(prot.getProtocolDocument().getDocumentHeader().getWorkflowDocument().stateIsDisapproved());
    }
    
    @Test
    public void testReturnForSMR() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        ProtocolDocument oldDocument = prot.getProtocolDocument();
        ProtocolDocument newDocument = genericActionService.returnForSMR(prot, actionBean);
        String expectedStatus = ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED;
        String unexpectedDocumentNumber = oldDocument.getDocumentNumber();
        assertEquals(expectedStatus, prot.getProtocolStatus().getProtocolStatusCode());
        assertTrue(oldDocument.getDocumentHeader().getWorkflowDocument().stateIsCanceled());
        assertNotSame(unexpectedDocumentNumber, newDocument.getDocumentNumber());
    }
    
    @Test
    public void testReturnForSRR() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        ProtocolDocument oldDocument = prot.getProtocolDocument();
        ProtocolDocument newDocument = genericActionService.returnForSRR(prot, actionBean);
        String expectedStatus = ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED;
        String unexpectedDocumentNumber = oldDocument.getDocumentNumber();
        assertEquals(expectedStatus, prot.getProtocolStatus().getProtocolStatusCode());
        assertTrue(oldDocument.getDocumentHeader().getWorkflowDocument().stateIsCanceled());
        assertNotSame(unexpectedDocumentNumber, newDocument.getDocumentNumber());
    }
    
    private Protocol getNewProtocol() throws Exception{
        Protocol prot = ProtocolFactory.createProtocolDocument().getProtocol();
        return prot;
    }
    
    private ProtocolGenericActionBean buildProtocolGenericActionBean(){
        ProtocolGenericActionBean actionBean = new ProtocolGenericActionBean(null);
        actionBean.setComments(BASIC_COMMENT);
        actionBean.setActionDate(BASIC_ACTION_DATE);
        return actionBean;
    }
    
    private ProtocolSubmitAction buildProtocolSubmitAction() {
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submitAction.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submitAction.setSubmissionQualifierTypeCode(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB);
        submitAction.setCommitteeId(COMMITTEE_ID);
        submitAction.setScheduleId(SCHEDULE_ID);
        return submitAction;
    }

}
