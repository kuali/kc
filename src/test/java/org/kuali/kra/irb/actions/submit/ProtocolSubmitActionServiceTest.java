/*
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.service.RolodexService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * Test the ProtocolSubmitActionService implementation.
 * 
 * For each of the below tests, the submitToIrbForReview() method is
 * invoked.  This method has no return value.  Rather, this method is
 * simply creating database entries for the submission.  After calling
 * the submitToIrbForReview(), a check is done against the database to
 * verify that the changes occurred as expected.
 */
public class ProtocolSubmitActionServiceTest extends KcUnitTestBase {

    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "1";
    private static final String MEMBER_EXPERTISE_CODE = "05.0125";
    
    private ProtocolSubmitActionService protocolSubmitActionService;
    private BusinessObjectService businessObjectService;   
    private PersonService personService;
    private RolodexService rolodexService;
    private DocumentService documentService;
    private IdentityService identityManagementService;
    private List<ProtocolReviewerBean> defaultReviewers;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setMessageMap(new MessageMap());
        KNSGlobalVariables.setAuditErrorMap(new HashMap());
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        personService = KraServiceLocator.getService(PersonService.class);
        rolodexService = KraServiceLocator.getService(RolodexService.class);
        documentService = KraServiceLocator.getService("kraDocumentService");
        identityManagementService = KraServiceLocator.getService(IdentityService.class);
        defaultReviewers = getDefaultReviewers();
        
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setMessageMap(null);
        KNSGlobalVariables.setAuditErrorMap(null);
        super.tearDown();
    }
    
    /*
     * Test a submission without a committee.  This use case is OK
     * and thus no errors should occur.
     */
    @Test
    public void testSubmissionWithNoCommittee() throws Exception {
        runTest("", "", VALID_REVIEW_TYPE, null, null, null);
    }
    
    /*
     * Test a submission with no schedule specified.  This use case is OK
     * and thus no errors should occur.
     */
    @Test
    public void testSubmissionWithNoSchedule() throws Exception {
        runTest("666", "", VALID_REVIEW_TYPE, null, null, null);
    }
   
    /*
     * Test a submission with no reviewers specified.  This use case is OK
     * and thus no errors should occur.
     */
    @Test
    public void testSubmissionWithNoReviewers() throws Exception {
        runTest("667", "1", VALID_REVIEW_TYPE, null, null, null);
    }
    
    /*
     * Test a submission with a couple of reviewers of which only one is selected.
     * Only that selected reviewer will be added to the database.
     */
    @Test
    public void testSubmissionWithReviewers() throws Exception {
        runTest("668", "1", VALID_REVIEW_TYPE, defaultReviewers, null, null);
    }
   
    /*
     * Test the Exempt Studies review type. A couple of exempt studies check list
     * items will be available of which only one will be selected.  Only the selected
     * one should appear in the database. 
     */
    @Test
    public void testExemptCheckList() throws Exception {
        runTest("669", "1", ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE, null, getExemptCheckList(), null);
    }
    
    /*
     * Test the Expedited Review review type. A couple of expedited review check list
     * items will be available of which only one will be selected.  Only the selected
     * one should appear in the database.  
     */
    @Test
    public void testExpeditedCheckList() throws Exception {
        runTest("670", "1", ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE, null, null, getExpeditedCheckList());
    }
    
    /*
     * Runs a test for the configuration defined by the input parameters.
     */
    private void runTest(String committeeId, String scheduleId, String protocolReviewTypeCode, 
                         List<ProtocolReviewerBean> reviewers,
                         List<ExemptStudiesCheckListItem> exemptStudiesCheckList,
                         List<ExpeditedReviewCheckListItem> expeditedReviewCheckList) throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction(committeeId, scheduleId, protocolReviewTypeCode);
        
        
        submitAction.setExemptStudiesCheckList(exemptStudiesCheckList);
        submitAction.setExpeditedReviewCheckList(expeditedReviewCheckList);
        submitAction.setSubmissionQualifierTypeCode(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB);
        Committee committee = null;
        
        if(!StringUtils.isEmpty(committeeId)) {
            //see if the committee exists already
            Map<String,Object> keymap = new HashMap<String,Object>();
            keymap.put("committeeId", committeeId);
            List<Committee> comms = (List<Committee>)businessObjectService.findMatching(Committee.class, keymap);
            if( comms.size() == 1 )
                committee = comms.get(0);
                
            if (committee==null)
                committee =  createCommittee(committeeId).getCommittee();
            committee.refreshReferenceObject("committeeType");
            submitAction.setCommitteeId(committee.getCommitteeId());
        }

        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        ProtocolSubmission protocolSubmission = findSubmission(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolSubmission);
        
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        
        assertEquals(protocolDocument.getProtocol().getProtocolStatusCode(), ProtocolStatus.SUBMITTED_TO_IRB);
        verifyAction(protocolAction, protocolSubmission);
        List<ProtocolSubmission> protocolSubmissions =protocolDocument.getProtocol().getProtocolSubmissions();
        protocolSubmissions.add(protocolSubmission);
        protocolDocument.getProtocol().setProtocolSubmissions(protocolSubmissions);
        protocolDocument.getProtocol().setProtocolSubmission(protocolSubmission);
        verifySubmission(protocolSubmission, protocolDocument.getProtocol(), submitAction);
    }
    
    /*
     * Get a couple of exempt check list items.
     */
    private List<ExemptStudiesCheckListItem> getExemptCheckList() {
        List<ExemptStudiesCheckListItem> list = new ArrayList<ExemptStudiesCheckListItem>();
        ExemptStudiesCheckListItem item = new ExemptStudiesCheckListItem();
        item.setExemptStudiesCheckListCode("1");
        item.setChecked(true);        
        list.add(item);
        
        item = new ExemptStudiesCheckListItem();
        item.setExemptStudiesCheckListCode("2");
        item.setChecked(false);        
        list.add(item);
        
        return list;
    }
    
    /*
     * Get a couple of expedited review check list items.
     */
    private List<ExpeditedReviewCheckListItem> getExpeditedCheckList() {
        List<ExpeditedReviewCheckListItem> list = new ArrayList<ExpeditedReviewCheckListItem>();
        ExpeditedReviewCheckListItem item = new ExpeditedReviewCheckListItem();
        item.setExpeditedReviewCheckListCode("1");
        item.setChecked(true);        
        list.add(item);
        
        item = new ExpeditedReviewCheckListItem();
        item.setExpeditedReviewCheckListCode("2");
        item.setChecked(false);        
        list.add(item);
        
        return list;
    }

    /*
     * Get a couple of reviewers.
     */
    private List<ProtocolReviewerBean> getDefaultReviewers() {
        List<ProtocolReviewerBean> reviewers = new ArrayList<ProtocolReviewerBean>();
        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
        
        
        Principal prncpl = identityManagementService.getPrincipalByPrincipalName("quickstart");
        reviewer.setPersonId(prncpl.getPrincipalId());
        reviewer.setNonEmployeeFlag(false);
        reviewer.setReviewerTypeCode("1");
        reviewer.setFullName(prncpl.getPrincipalName());
        reviewers.add(reviewer);
        
        
        reviewer = new ProtocolReviewerBean();
        prncpl = identityManagementService.getPrincipalByPrincipalName("majors");
        reviewer.setPersonId(prncpl.getPrincipalId());
        reviewer.setNonEmployeeFlag(false);
        reviewer.setReviewerTypeCode("1");
        reviewer.setFullName(prncpl.getPrincipalName());
        reviewers.add(reviewer);
        
        //adding in a rolodex reviewer.
        reviewer = new ProtocolReviewerBean();
        Rolodex rolodex = rolodexService.getRolodex(253);
        reviewer = new ProtocolReviewerBean();
        reviewer.setNonEmployeeFlag(true);
        reviewer.setPersonId("253");
        reviewer.setReviewerTypeCode("1");
        reviewer.setFullName(rolodex.getFullName());
        
        return reviewers;
    }
    
    /*
     * Create a committee.
     */
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
        addMembers(committee);
        documentService.saveDocument(committeeDocument);
        documentService.routeDocument(committeeDocument, "Test Routing", new ArrayList());
        return committeeDocument;
    }

    private void addMember(String personId, Integer rolodexId, String personName, String membershipTypeCode, String membershipRoleCode, Committee committee) {
        CommitteeMembership member = new CommitteeMembership();
        member.setCommitteeIdFk(committee.getId());
        member.setPersonId(personId);
        member.setRolodexId(rolodexId);
        member.setPaidMember(true);
        member.setPersonName(personName);
        member.setTermStartDate(new Date(System.currentTimeMillis() - 10000));
        member.setTermEndDate(new Date(System.currentTimeMillis() + 10000));
        member.setMembershipTypeCode(membershipTypeCode);
        CommitteeMembershipExpertise expertise = new CommitteeMembershipExpertise();
        expertise.getResearchArea().setActive(true);
        expertise.setResearchAreaCode(MEMBER_EXPERTISE_CODE);
        member.getMembershipExpertise().add(expertise); 
        member.setMembershipId("0");
        List<CommitteeMembershipRole> roles = new ArrayList<CommitteeMembershipRole>();
        CommitteeMembershipRole role = new CommitteeMembershipRole();
        role.setStartDate(member.getTermStartDate());
        role.setEndDate(member.getTermEndDate());
        role.setMembershipRoleCode(membershipRoleCode);
        roles.add(role);
        member.setMembershipRoles(roles);
        committee.getCommitteeMemberships().add(member);
    }
    
    /*
     * Add a member to the committee.
     */
    private void addMembers(Committee committee) {
        //make each of the default reviewers a committee member.
        for( ProtocolReviewerBean reviewer : defaultReviewers ) {
            addMember(reviewer.getNonEmployeeFlag()?null:reviewer.getPersonId(), reviewer.getNonEmployeeFlag()?new Integer(Integer.parseInt(reviewer.getPersonId())):null, reviewer.getFullName(),"1","1",committee);
        }
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
     * Verify that the created submission and protocol action is what is expected
     * based upon the "submitAction".
     */
    private void verifySubmission(ProtocolSubmission submission, Protocol protocol, ProtocolSubmitAction submitAction) {
        
        assertEquals(protocol.getProtocolNumber(), submission.getProtocolNumber());
        assertEquals(protocol.getSequenceNumber(), submission.getSequenceNumber());
        assertEquals(new Integer(1), submission.getSubmissionNumber());
        
        if (StringUtils.isBlank(submitAction.getNewCommitteeId())) {
            assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        }
        else {
            assertEquals(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, submission.getSubmissionStatusCode());
        }
        assertTrue(submission.getSubmissionDate() != null);
        assertEquals(submission.getSubmissionDate(), protocol.getSubmissionDate());
        
        assertEquals(submitAction.getSubmissionTypeCode(), submission.getSubmissionTypeCode());
        assertEquals(submitAction.getSubmissionQualifierTypeCode(), submission.getSubmissionTypeQualifierCode());
        assertEquals(submitAction.getProtocolReviewTypeCode(), submission.getProtocolReviewTypeCode());
       
        assertEquals(convert(submitAction.getNewCommitteeId()), convert(submission.getCommitteeId()));
        assertEquals(convert(submitAction.getNewScheduleId()), convert(submission.getScheduleId()));
        
        assertEquals(getReviewCount(submitAction.getReviewers()), getCount(submission.getProtocolReviewers()));
        assertEquals(getExemptStudiesCount(submitAction.getExemptStudiesCheckList()), getCount(submission.getExemptStudiesCheckList()));
        assertEquals(getExpeditedReviewCount(submitAction.getExpeditedReviewCheckList()), getCount(submission.getExpeditedReviewCheckList()));
    
        int index = 0;
        if (submitAction.getReviewers() != null) {
            for (ProtocolReviewerBean reviewerBean : submitAction.getReviewers()) {
                if (StringUtils.isNotBlank(reviewerBean.getReviewerTypeCode())) {
                    verifyReviewer(reviewerBean, submission.getProtocolReviewers().get(index++));
                }
            }
        }
        
        index = 0;
        if (submitAction.getExemptStudiesCheckList() != null) {
            for (ExemptStudiesCheckListItem item : submitAction.getExemptStudiesCheckList()) {
                if (item.getChecked()) {
                    verifyExemptStudies(item, submission.getExemptStudiesCheckList().get(index++));
                }
            }
        }
        
        index = 0;
        if (submitAction.getExpeditedReviewCheckList() != null) {
            for (ExpeditedReviewCheckListItem item : submitAction.getExpeditedReviewCheckList()) {
                if (item.getChecked()) {
                    verifyExpeditedReview(item, submission.getExpeditedReviewCheckList().get(index++));
                }
            }
        }
    }
            
    /*
     * Count the number of reviewers that have been selected.
     */
    private Object getReviewCount(List<ProtocolReviewerBean> reviewers) {
        int count = 0;
        if (reviewers != null) {
            for (ProtocolReviewerBean reviewerBean : reviewers) {
                if (StringUtils.isNotBlank(reviewerBean.getReviewerTypeCode())) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Count the number of exempt studies items that have been checked.
     */
    private int getExemptStudiesCount(List<ExemptStudiesCheckListItem> exemptStudiesCheckList) {
        int count = 0;
        if (exemptStudiesCheckList != null) {
            for (ExemptStudiesCheckListItem item : exemptStudiesCheckList) {
                if (item.getChecked()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /*
     * Count the number of expedited review items that have been checked.
     */
    private int getExpeditedReviewCount(List<ExpeditedReviewCheckListItem> expeditedReviewCheckList) {
        int count = 0;
        if (expeditedReviewCheckList != null) {
            for (ExpeditedReviewCheckListItem item : expeditedReviewCheckList) {
                if (item.getChecked()) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Verify that the "protocolExemptStudiesCheckListItem" has the correct exempt studies
     * type code corresponding to the selected one.
     */
    private void verifyExemptStudies(ExemptStudiesCheckListItem exemptStudiesCheckListItem,
                                     ProtocolExemptStudiesCheckListItem protocolExemptStudiesCheckListItem) {
        
        assertEquals(exemptStudiesCheckListItem.getExemptStudiesCheckListCode(),
                     protocolExemptStudiesCheckListItem.getExemptStudiesCheckListCode());
    }
    
    /*
     * Verify that the "protocolExpeditedReviewCheckListItem" has the correct expedited review
     * type code corresponding to the selected one.
     */
    private void verifyExpeditedReview(ExpeditedReviewCheckListItem expeditedReviewCheckListItem,
            ProtocolExpeditedReviewCheckListItem protocolExpeditedReviewCheckListItem) {

        assertEquals(expeditedReviewCheckListItem.getExpeditedReviewCheckListCode(),
                     protocolExpeditedReviewCheckListItem.getExpeditedReviewCheckListCode());
    }

    /*
     * Verify the review in the submission matches the reviewer from the orignal submission request.
     */
    private void verifyReviewer(ProtocolReviewerBean protocolReviewerBean, ProtocolReviewer protocolReviewer) {
        assertTrue( protocolReviewerBean.isProtocolReviewerBeanForReviewer(protocolReviewer) );
    }
    
    /*
     * Verfy that the Protocol Action is correct.
     */
    private void verifyAction(ProtocolAction action, ProtocolSubmission submission) {
        assertEquals(ProtocolActionType.SUBMIT_TO_IRB, action.getProtocolActionTypeCode());
        assertEquals(submission.getSubmissionId(), action.getSubmissionIdFk());
    }

    /*
     * If a string is null, convert it to an empty string.
     */
    private String convert(String s) {
        if (s == null) return "";
        return s;
    }

    /*
     * Count the number of items in a list.  A null list returns zero.
     */
    private int getCount(List<?> list) {
        if (list == null) return 0;
        return list.size();
    }

    /**
     * Find the ProtocolAction in the database.
     */
    @SuppressWarnings("unchecked")
    private ProtocolAction findProtocolAction(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolAction> actions = (List<ProtocolAction>) businessObjectService.findMatching(ProtocolAction.class, fieldValues);
        
        assertEquals(1, actions.size());
        ProtocolAction action = actions.get(0);
        return action;
    }

    /*
     * Find the ProtocolSubmission in the database.
     */
    @SuppressWarnings("unchecked")
    private ProtocolSubmission findSubmission(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolSubmission> submissions = (List<ProtocolSubmission>) businessObjectService.findMatching(ProtocolSubmission.class, fieldValues);
        
        assertEquals(1, submissions.size());
        ProtocolSubmission submission = submissions.get(0);
        return submission;
    }
}