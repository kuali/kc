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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.drools.brms.FactBean;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/*
 * This class is for the condition attributes of of the protocol action.
 * i.e., the condition of protocol status, submissionstatus, action type code etc.
 */
public class ProtocolActionMapping implements FactBean {
    
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    private static final Map<String, String> ACTION_TYPE_SUBMISSION_TYPE_MAP;
    static {
        final Map<String, String> codeMap = new HashMap<String, String>();        
        codeMap.put(ProtocolActionType.SUSPENDED, ProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
        codeMap.put(ProtocolActionType.SUSPENDED_BY_DSMB, ProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
        codeMap.put(ProtocolActionType.CLOSED_FOR_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT);
        codeMap.put(ProtocolActionType.DATA_ANALYSIS_ONLY, ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
        codeMap.put(ProtocolActionType.REOPEN_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT);
        codeMap.put(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, ProtocolSubmissionType.REQUEST_TO_CLOSE);
        codeMap.put(ProtocolActionType.TERMINATED, ProtocolSubmissionType.REQUEST_FOR_TERMINATION);        
        ACTION_TYPE_SUBMISSION_TYPE_MAP = Collections.unmodifiableMap(codeMap);
    }

    private static final List<String> APPROVE_ACTION_TYPES;
    static {
        final List<String> codes = new ArrayList<String>();     
        codes.add(ProtocolActionType.APPROVED);
        codes.add(ProtocolActionType.EXPEDITE_APPROVAL);
        codes.add(ProtocolActionType.GRANT_EXEMPTION);
        APPROVE_ACTION_TYPES = codes;
    }


    private BusinessObjectService businessObjectService;
    
    private ProtocolDao dao;
    
    String submissionStatusCode;
    
    String submissionTypeCode;
    
    String protocolReviewTypeCode;
    
    String actionTypeCode;
    
    String protocolStatusCode;
    
    String scheduleId;
    
    Protocol protocol;
    
    Integer submissionNumber;

    boolean allowed = false;

    public ProtocolActionMapping(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId, Integer submissionNumber) {
        super();
        this.actionTypeCode = actionTypeCode;
        this.submissionStatusCode = submissionStatusCode;        
        this.submissionTypeCode = submissionTypeCode;
        this.protocolReviewTypeCode = protocolReviewTypeCode;
        this.protocolStatusCode = protocolStatusCode;
        this.scheduleId = scheduleId;
        this.submissionNumber = submissionNumber;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setDao(ProtocolDao dao) {
        this.dao = dao;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    @SuppressWarnings("unchecked")
    public String getProtocolSubmissionScheduleId() {
        // TODO : should not need to retrieve from DB because protocol.getProtocolSubmission() is
        // the same as the one retrieved.  The positiveFieldValues are the pk in coeus.
        // this is used in rule for null check.
        
        if (protocol.getProtocolSubmission() == null) {
            return null;
        } else {
            return protocol.getProtocolSubmission().getScheduleId();
        }        
    }
    
    /**
     * 
     * This method if this submission has committee schedule minutes
     * @return
     */
    public boolean getMinutesCount() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        fieldValues.put(SUBMISSION_NUMBER, protocol.getProtocolSubmission().getSubmissionNumber());
        return businessObjectService.countMatching(CommitteeScheduleMinute.class, fieldValues) > 0;
    }
        
    /**
     * Check if there are any pending submissions for this protocol
     *  whose submission type is not the matching submission type in ACTION_TYPE_SUBMISSION_TYPE_MAP.
     */
    public boolean getSubmissionCount() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
 
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        
        Map<String, Object> negativeFieldValues = new HashMap<String, Object>();        
        negativeFieldValues.put("submissionTypeCode", ACTION_TYPE_SUBMISSION_TYPE_MAP.get(actionTypeCode));
        
        return businessObjectService.countMatching(ProtocolSubmission.class, positiveFieldValues, negativeFieldValues) == 0;    
    }
    
    /**
     * 
     * This method Checks if there are any pending submissions for this protocol
     * @return
     */
    public boolean getSubmissionCountCond2() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        
        return businessObjectService.countMatching(ProtocolSubmission.class, positiveFieldValues) == 0;
    }
    
    /**
     * 
     * This method Check if there are any pending amendmends/renewals for this protocols
     * @return
     */
    public boolean getSubmissionCountCond3() {
        return dao.getProtocolSubmissionCountFromProtocol(protocol.getProtocolNumber());
    }
    
    /**
     * 
     * This method check to see if there is pending submission with one of the following submission type
     * 109 --Request to close
     * 110 --Request For Suspension
     * 111 --Request to Close Enrollment
     * 108 --Request for Termination
     * 113 --Request for data analysis
     * 114 --Request for re-open enrollment
     * @return
     */
    public boolean getSubmissionCountCond4() {
        
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        positiveFieldValues.put("submissionTypeCode", Arrays.asList(new String[] {
                ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, ProtocolSubmissionType.REQUEST_FOR_SUSPENSION,
                ProtocolSubmissionType.REQUEST_FOR_TERMINATION, ProtocolSubmissionType.REQUEST_TO_CLOSE,
                ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT}));
        
        return businessObjectService.countMatching(ProtocolSubmission.class, positiveFieldValues) == 0;

    }
        
    /*
     * Coeus called submission status of 100/101/102 as pending submission
     */
    private List<String> getPendingSubmissionStatusCodes() {
        List<String> submissionStatusCodes = new ArrayList<String>();
        submissionStatusCodes.add(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionStatusCodes.add(ProtocolSubmissionStatus.IN_AGENDA); 
        submissionStatusCodes.add(ProtocolSubmissionStatus.PENDING);
        return submissionStatusCodes;

    }
    
    /*
     * select count(protocol_number)
                                    into li_SubmissionCount
                                    from osp$protocol_submission
                                    where protocol_number = as_protocol_number and
                                    sequence_number = as_sequence_number and
                                    submission_status_code in (100, 101, 102) and
                                    submission_number = (select max(a.submission_number)
                                                 from osp$protocol_submission a
                                                 where osp$protocol_submission.protocol_number = a.protocol_number and
                                                 osp$protocol_submission.sequence_number = a.sequence_number);
     * 
     */
    
    /**
     * check if there are any other pending submissions.
     * Basically, check the matching protocol submission with the highest submission# does not have
     * status of (100,101,102)
     */
    @SuppressWarnings("unchecked")
    public boolean getSubmissionCountCond5() {                
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        List<ProtocolSubmission> submissions = (List<ProtocolSubmission>)businessObjectService.findMatchingOrderBy(ProtocolSubmission.class, positiveFieldValues, "submissionNumber", false);
        return submissions.isEmpty() || !getPendingSubmissionStatusCodes().contains(submissions.get(0).getSubmissionStatusCode());
        
    }
    
    /**
     * 
     * This method Check if protocol has a submission which is in statuscode (100,101,102, 201, 202)  
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean getSubmissionCountForWithdraw() {              
        List <String> statusCodes = Arrays.asList(new String[] {"100","101","102", "201", "202"});
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        List<ProtocolSubmission> submissions = (List<ProtocolSubmission>)businessObjectService.findMatchingOrderBy(ProtocolSubmission.class, positiveFieldValues, "submissionNumber", false);
        return !submissions.isEmpty() && statusCodes.contains(submissions.get(0).getSubmissionStatusCode());
        
    }
    

    /**
     * This method finds number of reviewers tied to protocol submission. Implementation in lieu of following query 
     *           SELECT count(OSP$PROTOCOL_REVIEWERS.PERSON_ID)
     *           INTO li_PersonCnt
     *           FROM OSP$PROTOCOL_REVIEWERS
     *           WHERE OSP$PROTOCOL_REVIEWERS.PROTOCOL_NUMBER = AS_PROTOCOL_NUMBER
     *           AND OSP$PROTOCOL_REVIEWERS.SUBMISSION_NUMBER = AS_SUBMISSION_NUMBER; 
     * @return
     */
    public boolean getProtocolReviewerCountCond1() {       
        return protocol.getProtocolSubmission().getProtocolReviewers().size() > 0;
    }
    
    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getSubmissionStatusCode() {
        return submissionStatusCode;
    }

    public void setSubmissionStatusCode(String submissionStatusCode) {
        this.submissionStatusCode = submissionStatusCode;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }
    
    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }    
    
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
    
    /**
     * check if this protocol has not been approved
     */
    public boolean isInitialProtocol() {
        boolean initialProtocol = true;
        for (ProtocolAction action : protocol.getProtocolActions()) {
            if (APPROVE_ACTION_TYPES.contains(action.getProtocolActionTypeCode())) {
                initialProtocol = false;
                break;
            }
        }
        return initialProtocol;
    }

    /**
     * check if user is PI
     */
    public boolean isPrincipalInvestigator() {
        Person user = GlobalVariables.getUserSession().getPerson();
        boolean isPi = false;
        if (user.getPrincipalId().equals(protocol.getPrincipalInvestigator().getPersonId())) {
            isPi = true;
        }
        return isPi;
    }

    /**
     * check if this submission is protocol is just SRR/SMR
     * protocolSubmissions is sorted by submissionNumber in repository.
     */
    public boolean isSubmitForRevision() {
        boolean revisionSubmission = false;
        if (protocol.getProtocolSubmissions().size() >= 2) {
            ProtocolSubmission prevSubmission = protocol.getProtocolSubmissions().get(protocol.getProtocolSubmissions().size() - 2);
            if (ProtocolSubmissionStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED.equals(prevSubmission.getSubmissionStatusCode()) || ProtocolSubmissionStatus.SUBSTANTIVE_REVISIONS_REQUIRED
                            .equals(prevSubmission.getSubmissionStatusCode())) {
                revisionSubmission = true;
            }
        }
        return revisionSubmission;
    }

}
