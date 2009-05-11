/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionServiceImpl;
import org.kuali.kra.irb.actions.submit.mocks.MockBusinessObjectService;
import org.kuali.kra.irb.actions.submit.mocks.MockCommitteeService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the ProtocolSubmitActionService implementation.
 * 
 * For each of the below tests, the submitToIrbForReview() method is
 * invoked.  This method has no return value.  Rather, this method is
 * simply creating database entries for the submission.  In order to
 * perform the testing, two Mock classes have been created: MockBusinessObjectService
 * and MockCommitteeService.  At the beginning of a test, each of those
 * mocks is created and initialized with the knowledge of how it will
 * be used for that test.  If something unexpected occurs, those mocks
 * will assert errors.
 */
public class ProtocolSubmitActionServiceTest extends KraTestBase {

    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "1";
    private static final String EXPEDITED_REVIEW_TYPE = "2";
    private static final String EXEMPT_REVIEW_TYPE = "3";
    
    private DocumentService documentService;
    private ProtocolSubmitActionServiceImpl protocolSubmitActionService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
        protocolSubmitActionService = new ProtocolSubmitActionServiceImpl();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * Test a submission without a committee.  This use case is OK
     * and thus no errors should occur.
     */
    @Test
    public void testSubmissionWithNoCommittee() throws WorkflowException {
        ProtocolDocument protocolDocument = createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction("", "", VALID_REVIEW_TYPE);
        
        CommitteeService committeeService = new MockCommitteeService(null);
        protocolSubmitActionService.setCommitteeService(committeeService);
        
        ProtocolSubmission protocolSubmission = createProtocolSubmission(protocolDocument.getProtocol(), submitAction);
        BusinessObjectService businessObjectService = new MockBusinessObjectService(protocolSubmission);
        protocolSubmitActionService.setBusinessObjectService(businessObjectService);
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
    }
    
    /**
     * Test a submission with no schedule specified.  This use case is OK
     * and thus no errors should occur.
     */
    @Test
    public void testSubmissionWithNoSchedule() throws WorkflowException {
        ProtocolDocument protocolDocument = createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction("666", "", VALID_REVIEW_TYPE);
        
        Committee committee = createCommittee("666");
        CommitteeService committeeService = new MockCommitteeService(committee);
        protocolSubmitActionService.setCommitteeService(committeeService);
        
        ProtocolSubmission protocolSubmission = createProtocolSubmission(protocolDocument.getProtocol(), submitAction);
        BusinessObjectService businessObjectService = new MockBusinessObjectService(protocolSubmission);
        protocolSubmitActionService.setBusinessObjectService(businessObjectService);
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
    }
    
    /**
     * Test a submission with no reviewers specified.  This use case is OK
     * and thus no errors should occur.
     */
    @Test
    public void testSubmissionWithNoReviewers() throws WorkflowException {
        ProtocolDocument protocolDocument = createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction("666", "1", VALID_REVIEW_TYPE);
        
        Committee committee = createCommittee("666");
        CommitteeService committeeService = new MockCommitteeService(committee);
        protocolSubmitActionService.setCommitteeService(committeeService);
        
        ProtocolSubmission protocolSubmission = createProtocolSubmission(protocolDocument.getProtocol(), submitAction);
        BusinessObjectService businessObjectService = new MockBusinessObjectService(protocolSubmission);
        protocolSubmitActionService.setBusinessObjectService(businessObjectService);
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
    }
    
    /**
     * Test a submission with a couple of reviewers of which only one is selected.
     * Only that selected reviewer will be added to the database.
     */
    @Test
    public void testSubmissionWithReviewers() throws WorkflowException {
        ProtocolDocument protocolDocument = createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction("666", "1", VALID_REVIEW_TYPE);
        addReviewers(submitAction);
        
        Committee committee = createCommittee("666");
        CommitteeService committeeService = new MockCommitteeService(committee);
        protocolSubmitActionService.setCommitteeService(committeeService);
        
        ProtocolSubmission protocolSubmission = createProtocolSubmission(protocolDocument.getProtocol(), submitAction);
        BusinessObjectService businessObjectService = new MockBusinessObjectService(protocolSubmission);
        protocolSubmitActionService.setBusinessObjectService(businessObjectService);
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
    }
    
    /**
     * Test the Exempt Studies review type. A couple of exempt studies check list
     * items will be available of which only one will be selected.  Only the selected
     * one should appear in the database. 
     */
    @Test
    public void testExemptCheckList() throws WorkflowException {
        ProtocolDocument protocolDocument = createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction("666", "1", EXEMPT_REVIEW_TYPE);
        addExemptCheckList(submitAction);
        
        Committee committee = createCommittee("666");
        CommitteeService committeeService = new MockCommitteeService(committee);
        protocolSubmitActionService.setCommitteeService(committeeService);
        
        ProtocolSubmission protocolSubmission = createProtocolSubmission(protocolDocument.getProtocol(), submitAction);
        BusinessObjectService businessObjectService = new MockBusinessObjectService(protocolSubmission);
        protocolSubmitActionService.setBusinessObjectService(businessObjectService);
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
    }
    
    /**
     * Test the Expedited Review review type. A couple of expedited review check list
     * items will be available of which only one will be selected.  Only the selected
     * one should appear in the database.  
     */
    @Test
    public void testExpeditedCheckList() throws WorkflowException {
        ProtocolDocument protocolDocument = createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction("666", "1", EXPEDITED_REVIEW_TYPE);
        addExpeditedCheckList(submitAction);
        
        Committee committee = createCommittee("666");
        CommitteeService committeeService = new MockCommitteeService(committee);
        protocolSubmitActionService.setCommitteeService(committeeService);
        
        ProtocolSubmission protocolSubmission = createProtocolSubmission(protocolDocument.getProtocol(), submitAction);
        BusinessObjectService businessObjectService = new MockBusinessObjectService(protocolSubmission);
        protocolSubmitActionService.setBusinessObjectService(businessObjectService);
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
    }
    
    /**
     * Add a couple of exempt check list items to the protocol submission action.
     */
    private void addExemptCheckList(ProtocolSubmitAction submitAction) {
        List<ExemptStudiesCheckListItem> list = new ArrayList<ExemptStudiesCheckListItem>();
        ExemptStudiesCheckListItem item = new ExemptStudiesCheckListItem();
        item.setExemptStudiesCheckListCode("1");
        item.setChecked(true);        
        list.add(item);
        
        item = new ExemptStudiesCheckListItem();
        item.setExemptStudiesCheckListCode("2");
        item.setChecked(false);        
        list.add(item);
        
        submitAction.setExemptStudiesCheckList(list);
    }
    
    /**
     * Add a couple of expedited review check list items to the protocol submission action.
     */
    private void addExpeditedCheckList(ProtocolSubmitAction submitAction) {
        List<ExpeditedReviewCheckListItem> list = new ArrayList<ExpeditedReviewCheckListItem>();
        ExpeditedReviewCheckListItem item = new ExpeditedReviewCheckListItem();
        item.setExpeditedReviewCheckListCode("1");
        item.setChecked(true);        
        list.add(item);
        
        item = new ExpeditedReviewCheckListItem();
        item.setExpeditedReviewCheckListCode("2");
        item.setChecked(false);        
        list.add(item);
        
        submitAction.setExpeditedReviewCheckListList(list);
    }

    /*
     * Add a couple of reviewers to the protocol submission action.
     */
    private void addReviewers(ProtocolSubmitAction submitAction) {
        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
        reviewer.setChecked(true);
        reviewer.setPersonId("dbarre");
        reviewer.setNonEmployeeFlag(false);
        reviewer.setReviewerTypeCode("101");
        submitAction.getReviewers().add(reviewer);
        
        reviewer = new ProtocolReviewerBean();
        reviewer.setChecked(false);
        reviewer.setPersonId("aslusar");
        reviewer.setNonEmployeeFlag(false);
        reviewer.setReviewerTypeCode("101");
        submitAction.getReviewers().add(reviewer);
    }

    /*
     * Create a committee.
     */
    private Committee createCommittee(String committeeId) {
        Committee committee = new Committee();
        committee.setCommitteeId(committeeId);
        CommitteeSchedule schedule = new CommitteeSchedule();
        schedule.setScheduleId("1");
        schedule.setScheduledDate(new Date(System.currentTimeMillis()));
        schedule.setTime(new Timestamp(0));
        committee.getCommitteeSchedules().add(schedule);
        return committee;
    }

    /*
     * Create protocol submission action.
     */
    private ProtocolSubmitAction createSubmitAction(String committeeId, String scheduleId, String protocolReviewTypeCode) {
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(protocolReviewTypeCode);
        submitAction.setCommitteeId(committeeId);
        submitAction.setScheduleId(scheduleId);
        return submitAction;
    }
    
    /*
     * Create a protocol document.
     */
    private ProtocolDocument createProtocolDocument() throws WorkflowException {
        ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getNewDocument(ProtocolDocument.class);
        protocolDocument.setDocumentNextvalues(new ArrayList<DocumentNextvalue>());
        protocolDocument.getProtocol().setProtocolDocument(protocolDocument);
        protocolDocument.getProtocol().setProtocolId(999L);
        protocolDocument.getProtocol().setProtocolNumber("999");
        protocolDocument.getProtocol().setSequenceNumber(0);
        return protocolDocument;
    }
    
    /*
     * Create a protocol submission.  This will be used by the Mock Business Object Service
     * to determine if the supplied protocol submission matches this one.
     */
    private ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolSubmitAction submitAction) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSequenceNumber(0);
        submission.setSubmissionNumber(1);
        submission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        submission.setSubmissionTypeCode(submitAction.getSubmissionTypeCode());
        submission.setSubmissionTypeQualifierCode(submitAction.getSubmissionQualifierTypeCode());
        submission.setProtocolReviewTypeCode(submitAction.getProtocolReviewTypeCode());
        submission.setSubmissionStatusCode("100"); 
        if (!StringUtils.isBlank(submitAction.getNewCommitteeId())) {
            submission.setCommitteeId(submitAction.getNewCommitteeId());
            if (!StringUtils.isBlank(submitAction.getNewScheduleId())) {
                submission.setScheduleId(submitAction.getNewScheduleId());
                for (ProtocolReviewerBean reviewer : submitAction.getReviewers()) {
                    if (reviewer.getChecked()) {
                        ProtocolReviewer pr = new ProtocolReviewer();
                        pr.setProtocolId(protocol.getProtocolId());
                        pr.setProtocolNumber(protocol.getProtocolNumber());
                        pr.setPersonId(reviewer.getPersonId());
                        pr.setReviewerTypeCode(reviewer.getReviewerTypeCode());
                        pr.setNonEmployeeFlag(reviewer.getNonEmployeeFlag());
                        pr.setSequenceNumber(0);
                        pr.setSubmissionNumber(1);
                        submission.getProtocolReviewers().add(pr);
                    }
                }
            }
        }
        if (submitAction.getExemptStudiesCheckList() != null) {
            for (ExemptStudiesCheckListItem item : submitAction.getExemptStudiesCheckList()) {
                if (item.getChecked()) {
                    ProtocolExemptStudiesCheckListItem pitem = new ProtocolExemptStudiesCheckListItem();
                    pitem.setExemptStudiesCheckListCode(item.getExemptStudiesCheckListCode());
                    pitem.setProtocolId(protocol.getProtocolId());
                    pitem.setProtocolNumber(protocol.getProtocolNumber());
                    pitem.setSequenceNumber(0);
                    pitem.setSubmissionNumber(1);
                    submission.getExemptStudiesCheckList().add(pitem);
                }
            }
        }
        if (submitAction.getExpeditedReviewCheckList() != null) {
            for (ExpeditedReviewCheckListItem item : submitAction.getExpeditedReviewCheckList()) {
                if (item.getChecked()) {
                    ProtocolExpeditedReviewCheckListItem pitem = new ProtocolExpeditedReviewCheckListItem();
                    pitem.setExpeditedReviewCheckListCode(item.getExpeditedReviewCheckListCode());
                    pitem.setProtocolId(protocol.getProtocolId());
                    pitem.setProtocolNumber(protocol.getProtocolNumber());
                    pitem.setSequenceNumber(0);
                    pitem.setSubmissionNumber(1);
                    submission.getExpeditedReviewCheckList().add(pitem);
                }
            }
        }
        return submission;
    }
}
