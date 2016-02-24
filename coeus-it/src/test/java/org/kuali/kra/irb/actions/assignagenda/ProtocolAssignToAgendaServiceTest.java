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
package org.kuali.kra.irb.actions.assignagenda;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.Time12HrFmt;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * Test the ProtocolDeleteService implementation.
 */
public class ProtocolAssignToAgendaServiceTest extends KcIntegrationTestBase {
    private static final String COMMITTEE_ID = "699";

    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolAssignToAgendaServiceImpl protocolAssignToAgendaServiceImpl;
    
    private BusinessObjectService businessObjectService;
    private LegacyDataAdapter legacyDataAdapter;

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KcServiceLocator.getService(DocumentService.class);
        protocolActionService = KcServiceLocator.getService(ProtocolActionService.class);
        protocolAssignToAgendaService = KcServiceLocator.getService(ProtocolAssignToAgendaService.class);
        protocolAssignToAgendaServiceImpl = (ProtocolAssignToAgendaServiceImpl) KcServiceLocator.getService(ProtocolAssignToAgendaService.class);
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        legacyDataAdapter = KcServiceLocator.getService(LegacyDataAdapter.class);
    }

    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        documentService = null;
        protocolActionService = null;
        protocolAssignToAgendaService = null;
        protocolAssignToAgendaServiceImpl = null;
        GlobalVariables.setUserSession(null);
    }
    @Test
    public void testSetDocumentService() {
        protocolAssignToAgendaServiceImpl.setDocumentService(documentService);
        assertTrue(true);
    }

    @Test
    public void testSetProtocolActionService() {
        protocolAssignToAgendaServiceImpl.setProtocolActionService(protocolActionService);
        assertTrue(true);
    }

    
    @Test
    public void testAssignToAgenda() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        Committee committee = getCommittee();
        committee.refreshReferenceObject("committeeType");
        submission.setCommittee(committee);
        submission.setCommitteeIdFk(committee.getId());
        submission.setCommitteeId(committee.getCommitteeId());
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        ProtocolForm form = new ProtocolForm();
        ActionHelper actionHelper = new ActionHelper(form);
        actionHelper.initializeProtocolActions();
        ProtocolAssignToAgendaBean actionBean = (ProtocolAssignToAgendaBean) actionHelper.getAssignToAgendaBean();
        actionBean.setComments("this is a comment");
        actionBean.setCommitteName("committee name");
        actionBean.setProtocolAssigned(true);
        //actionBean.setScheduleDate(new Date());
        protocolAssignToAgendaService.assignToAgenda(protocolDocument.getProtocol(), actionBean);
        assertTrue(true);
    }

    private Committee getCommittee() throws WorkflowException {
        
        Map<String,Object> keymap = new HashMap<String,Object>();
        keymap.put("committeeId", COMMITTEE_ID);
        List<Committee> comms = (List<Committee>)businessObjectService.findMatching(Committee.class, keymap);
        Committee committee = new Committee();
        if( comms.size() == 1 )
            committee = comms.get(0);
            
        if (committee==null)
            committee =  createCommittee(COMMITTEE_ID).getCommittee();
        committee.refreshReferenceObject("committeeType");
       // submitAction.setCommitteeId(committee.getCommitteeId());
        return committee;
    
    }
    
    private CommitteeDocument createCommittee(String committeeId) throws WorkflowException {
        
        CommitteeDocument committeeDocument = CommitteeFactory.createCommitteeDocument(committeeId);
        Committee committee = committeeDocument.getCommittee();
        CommitteeSchedule schedule = new CommitteeSchedule();
        schedule.setScheduleId("1");
        schedule.setPlace("my office");
        schedule.setEndTime(new Timestamp(System.currentTimeMillis() + 100));
        schedule.setScheduledDate(new Date(System.currentTimeMillis()));
        schedule.setStartTime(new Timestamp(System.currentTimeMillis() - 100));
        schedule.setFilter(false);
        schedule.setMaxProtocols(committee.getMaxProtocols());
        schedule.setTime(new Timestamp(System.currentTimeMillis()));
        schedule.setViewTime(new Time12HrFmt(new Timestamp(System.currentTimeMillis())));
        schedule.setProtocolSubDeadline(new Date(System.currentTimeMillis() - 500));
        schedule.setScheduleStatusCode(1);
        committee.getCommitteeSchedules().add(schedule);
//        addMembers(committee);
        documentService.saveDocument(committeeDocument);
        documentService.routeDocument(committeeDocument, "Test Routing", new ArrayList());
        return committeeDocument;
    }


    @Test
    public void testIsAssignedToAgenda1() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        List<ProtocolAction> actions = new ArrayList<ProtocolAction>();
        actions.add(new ProtocolAction(protocolDocument.getProtocol(), submission, ProtocolActionType.SUBMIT_TO_IRB));
        protocolDocument.getProtocol().setProtocolActions((List)actions);
        boolean result = protocolAssignToAgendaService.isAssignedToAgenda(protocolDocument.getProtocol());
        assertFalse(result);
    }
    
    @Test
    public void testIsAssignedToAgenda2() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.IN_AGENDA);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        List<ProtocolAction> actions = new ArrayList<ProtocolAction>();
        actions.add(new ProtocolAction(protocolDocument.getProtocol(), submission, ProtocolActionType.ASSIGN_TO_AGENDA));
        protocolDocument.getProtocol().setProtocolActions((List)actions);
        boolean result = protocolAssignToAgendaService.isAssignedToAgenda(protocolDocument.getProtocol());
        assertTrue(result);
    }

    @Test
    public void testGetAssignToAgendaComments() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        List<ProtocolAction> actions = new ArrayList<ProtocolAction>();
        ProtocolAction pa = new ProtocolAction(protocolDocument.getProtocol(), submission, ProtocolActionType.ASSIGN_TO_AGENDA);
        String comments = "My test protocol action comments";
        pa.setComments(comments);
        actions.add(pa);
        protocolDocument.getProtocol().setProtocolActions((List)actions);
        String result = protocolAssignToAgendaService.getAssignToAgendaComments(protocolDocument.getProtocol());
        assertEquals(comments, result);
    }

    @Test
    public void testGetAssignedCommitteeId() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        String committeeId = protocolAssignToAgendaService.getAssignedCommitteeId(protocolDocument.getProtocol());
        assertEquals("1", committeeId);
    }

    @Test
    public void testGetAssignedCommitteeName() throws Exception {
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        String passedInCommitteeName = "testCommitteeName";
        
        Committee com = new Committee();
        com.setCommitteeId("1");
        com.setHomeUnitNumber("000001");
        com.setCommitteeTypeCode("1");
        com.setAdvancedSubmissionDaysRequired(1);
        com.setReviewTypeCode("1");
        com.setMaxProtocols(1);
        com.setMinimumMembersRequired(1);
        com.setCommitteeName(passedInCommitteeName);
        submission.setCommittee(com);
        
        CommitteeDocument cd = (CommitteeDocument) documentService.getNewDocument(CommitteeDocument.class);
        cd.setCommittee(com);
        cd.getDocumentHeader().setDocumentDescription("super cool description");
        cd.setUpdateTimestamp(new Timestamp(20100305));
        cd.setUpdateUser("quickstart");
        cd.setVersionNumber(new Long(1));
        com.setCommitteeDocument(cd);

        documentService.saveDocument(cd);
        documentService.blanketApproveDocument(cd, "Test Committee", Collections.emptyList());
        
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        documentService.saveDocument(protocolDocument);
        
        String committeeName = protocolAssignToAgendaService.getAssignedCommitteeName(protocolDocument.getProtocol());
        assertEquals(passedInCommitteeName, committeeName);
    }

    @Test
    public void testGetAssignedScheduleDate() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        
                
        Committee com = new Committee();
        com.setCommitteeId("1");
        com.setId(new Long(1));
        com.setHomeUnitNumber("000001");
        com.setCommitteeTypeCode("1");
        com.setReviewTypeCode("1");
        com.setAdvancedSubmissionDaysRequired(new Integer(1));
        String passedInCommitteeName = "testCommitteeName";
        com.setCommitteeName(passedInCommitteeName);
        com.setMaxProtocols(1);
        com.setMinimumMembersRequired(1);
        submission.setCommittee(com);
        
        CommitteeSchedule cs = new CommitteeSchedule();
        cs.setCommittee(com);
        cs.setCommitteeIdFk(com.getId());
        cs.setScheduleId("1069");
        java.sql.Date basicDate = new java.sql.Date(2010, 3, 17);
        cs.setAgendaProdRevDate(basicDate);
        cs.setId(new Long(12345));
        cs.setScheduledDate(basicDate);
        cs.setProtocolSubDeadline(basicDate);
        cs.setScheduleStatusCode(new Integer(1));
        cs.setTime(new Timestamp(20100305));
        
        List<CommitteeSchedule> committeeSchedules = new ArrayList<CommitteeSchedule>();
        committeeSchedules.add(cs);
        com.setCommitteeSchedules(committeeSchedules);

        CommitteeDocument cd = (CommitteeDocument) documentService.getNewDocument(CommitteeDocument.class);
        cd.setCommittee(com);

        cd.getDocumentHeader().setDocumentDescription("super cool description");
        cd.setUpdateTimestamp(new Timestamp(20100305));
        cd.setUpdateUser("quickstart");
        cd.setVersionNumber(new Long(1));
        com.setCommitteeDocument(cd);
        
        documentService.saveDocument(cd);
        documentService.blanketApproveDocument(cd, "Test Committee", Collections.emptyList());
        legacyDataAdapter.save(com);
        legacyDataAdapter.save(cs);
        
        submission.setScheduleId(cs.getScheduleId());
        
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        documentService.saveDocument(protocolDocument);
        
        String agendaDate = protocolAssignToAgendaService.getAssignedScheduleDate(protocolDocument.getProtocol());
        
        assertNotNull(agendaDate);
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
        submission.setSubmissionDate(new Date(System.currentTimeMillis()));
        if (StringUtils.equals(statusCode, ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            submission.setCommitteeId("1");
        }
        return submission;
    }
}
