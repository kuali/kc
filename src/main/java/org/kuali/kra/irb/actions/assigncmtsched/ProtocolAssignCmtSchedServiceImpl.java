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
package org.kuali.kra.irb.actions.assigncmtsched;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Handles the assignment of a protocol to a committee/schedule.
 */
public class ProtocolAssignCmtSchedServiceImpl implements ProtocolAssignCmtSchedService {

    private static final String NEXT_ACTION_ID_KEY = "actionId";
    private BusinessObjectService businessObjectService;
    private CommitteeService committeeService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Set the Committee Service
     * @param committeeService
     */
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    
    /**
     * Set the Protocol Online Review Service
     * @param protocolOnlineReviewService
     */
    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService#getAssignedCommittee(org.kuali.kra.irb.Protocol)
     */
    public String getAssignedCommitteeId(Protocol protocol) {
        ProtocolSubmission submission = findSubmission(protocol);
        if (submission != null && StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            return submission.getCommitteeId();
        }
        return null;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService#getAssignedScheduleId(org.kuali.kra.irb.Protocol)
     */
    public String getAssignedScheduleId(Protocol protocol) {
        ProtocolSubmission submission = findSubmission(protocol);
        if (submission != null && StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            return submission.getScheduleId();
        }
        return null;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService#assignToCommitteeAndSchedule(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean)
     */
    public void assignToCommitteeAndSchedule(Protocol protocol, ProtocolAssignCmtSchedBean actionBean) throws Exception {
        ProtocolSubmission submission = findSubmission(protocol);
        if (submission != null) {

            setSchedule(submission, actionBean.getNewCommitteeId(), actionBean.getNewScheduleId());
            submission.setSubmissionStatusCode(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
            protocol.refreshReferenceObject("protocolStatus");
            //Lets migrate the review comments
            if (actionBean.scheduleHasChanged() && 
                protocolOnlineReviewService.getProtocolReviewDocumentsForCurrentSubmission(protocol).size()>0) {
                ProtocolSubmission tmpSubmission = new ProtocolSubmission();
                tmpSubmission.setProtocolOnlineReviews(submission.getProtocolOnlineReviews());
                protocolOnlineReviewService.moveOnlineReviews(tmpSubmission, submission);
            }           
        } else if (ProtocolActionType.NOTIFIED_COMMITTEE.equals(protocol.getLastProtocolAction().getFollowupActionCode())) {
            // followup action
            updateSubmission(protocol, actionBean);
            addNewAction(protocol, actionBean);
        }
        businessObjectService.save(protocol);
    }

    private void updateSubmission(Protocol protocol, ProtocolAssignCmtSchedBean actionBean) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (submission.getSubmissionNumber().equals(protocol.getLastProtocolAction().getSubmissionNumber())) {
                setSchedule(submission, actionBean.getNewCommitteeId(), actionBean.getNewScheduleId());
                break;
            }
        }
    }
    
    private void addNewAction(Protocol protocol, ProtocolAssignCmtSchedBean actionBean) {
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        ProtocolAction newAction = new ProtocolAction();
        // deep copy will replaced the last action with the new one after save
       // ProtocolAction newAction = (ProtocolAction)ObjectUtils.deepCopy(protocol.getLastProtocolAction());
        newAction.setActionId(protocol.getNextValue(NEXT_ACTION_ID_KEY));
        newAction.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setProtocolActionTypeCode(ProtocolActionType.NOTIFIED_COMMITTEE);
        newAction.setSubmissionIdFk(lastAction.getSubmissionIdFk());
        newAction.setSubmissionNumber(lastAction.getSubmissionNumber());
        newAction.setProtocolNumber(protocol.getProtocolNumber());
        newAction.setProtocolId(protocol.getProtocolId());
        newAction.setSequenceNumber(protocol.getSequenceNumber());
        protocol.getProtocolActions().add(newAction);

    }

    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee. 
     * @param protocol
     * @return
     */
    protected ProtocolSubmission findSubmission(Protocol protocol) {
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                protocolSubmission = submission;
            }
        }
        return protocolSubmission;

    }
    
    /**
     * Set the schedule that the committee will use.
     * @param committeeId
     * @param scheduleId
     */
    public void setSchedule(ProtocolSubmission submission, String committeeId, String scheduleId) {
        if (!setCommittee(submission, committeeId)) {
            submission.setScheduleId(null);
            submission.setScheduleIdFk(null);
            submission.setCommitteeSchedule(null);
        }
        else {
            CommitteeSchedule schedule = committeeService.getCommitteeSchedule(submission.getCommittee(), scheduleId);
            if (schedule == null) {
                submission.setScheduleId(null);
                submission.setScheduleIdFk(null);
                submission.setCommitteeSchedule(null);
                updateDefaultSchedule(submission);
            }
            else {
                submission.setScheduleId(schedule.getScheduleId());
                submission.setScheduleIdFk(schedule.getId());
                submission.setCommitteeSchedule(schedule);
                updateDefaultSchedule(submission);
            }
        }
    }
    
    /*
     * update default schedule in minute when schedule is assigned.
     * TODO : copied from protocolsubmitactionservice, so this can be shared
     */
    @SuppressWarnings("unchecked")
    protected void updateDefaultSchedule(ProtocolSubmission submission) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolIdFk", submission.getProtocolId().toString());
//        fieldValues.put("scheduleIdFk", CommitteeSchedule.DEFAULT_SCHEDULE_ID.toString());
        List<CommitteeScheduleMinute> minutes = (List<CommitteeScheduleMinute>) businessObjectService.findMatching(CommitteeScheduleMinute.class, fieldValues);
        if (!minutes.isEmpty()) {
            for (CommitteeScheduleMinute minute : minutes) {
                if (submission.getScheduleIdFk() == null) {
                    minute.setScheduleIdFk(CommitteeSchedule.DEFAULT_SCHEDULE_ID);
                } else {
                    minute.setScheduleIdFk(submission.getScheduleIdFk());
                }
            }
            businessObjectService.save(minutes);
        }
    }

    /**
     * Set the committee that the submission will use.
     * @param committeeId
     * @return
     */
    public boolean setCommittee(ProtocolSubmission submission, String committeeId) {
        Committee committee = committeeService.getCommitteeById(committeeId);
        if (committee == null) {
            submission.setCommitteeId(null);
            submission.setCommitteeIdFk(null);
            submission.setCommittee(null);
            return false;
        }
        else {
            submission.setCommitteeId(committee.getCommitteeId());
            submission.setCommitteeIdFk(committee.getId());
            submission.setCommittee(committee);
            return true;
        }
    }
}
