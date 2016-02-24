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
package org.kuali.kra.irb.actions.assigncmtsched;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Override
    public String getAssignedCommitteeId(Protocol protocol) {
        ProtocolSubmission submission = findSubmissionIncludingInAgenda(protocol);
        if ( submission != null && 
             (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)
              || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA) ||
              (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) & 
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.REQUEST_FOR_SUSPENSION))) ) {
            return submission.getCommitteeId();
        }
        return null;
    }
    
    @Override
    public String getAssignedScheduleId(Protocol protocol) {
        ProtocolSubmission submission = findSubmissionIncludingInAgenda(protocol);
        if (submission != null && StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)) {
            return submission.getScheduleId();
        }
        return null;
    }
    
    @Override
    public void assignToCommitteeAndSchedule(Protocol protocol, ProtocolAssignCmtSchedBean actionBean) throws Exception {
        assignToCommitteeAndSchedule(protocol, actionBean, false);
    }
    
    @Override
    public void assignToCommitteeAndSchedulePostAgendaAssignment(Protocol protocol, ProtocolAssignCmtSchedBean cmtAssignBean) throws Exception {
        assignToCommitteeAndSchedule(protocol, cmtAssignBean, true);
    }
    
    // refactored common code for assigning protocol to a committee and schedule, with conditional logic based on the isPostAgendaAssignment parameter
    private void assignToCommitteeAndSchedule(Protocol protocol, ProtocolAssignCmtSchedBean actionBean, boolean isPostAgendaAssignment) throws Exception {
        ProtocolSubmission submission = null;
        // we will include in-agenda submissions in our search depending on the parameter value
        if(isPostAgendaAssignment) {
            submission = findSubmissionIncludingInAgenda(protocol);
        }
        else {
            submission = findSubmission(protocol);
        }
        if (submission != null) {
            setSchedule(submission, actionBean.getNewCommitteeId(), actionBean.getNewScheduleId());
            if(!isPostAgendaAssignment) {
                submission.setSubmissionStatusCode(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
            }
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
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (submission.getSubmissionNumber().equals(protocol.getLastProtocolAction().getSubmissionNumber())) {
                setSchedule((ProtocolSubmission)submission, actionBean.getNewCommitteeId(), actionBean.getNewScheduleId());
                break;
            }
        }
    }
    
    private void addNewAction(Protocol protocol, ProtocolAssignCmtSchedBean actionBean) {
        //protected? both IACUC and IRB flavors of this service have identical methods.
        //with the exception of IRB setting an action of NOTIFIED_COMMITTEE, instead
        //of IACUC's ASSIGNED_TO_COMMITTEE...? Also, IACUC passes the previous submission status
        //code and sets that field on newAction here.
        ProtocolAction newAction = new ProtocolAction();
        // deep copy will replaced the last action with the new one after save
        newAction.setActionId(protocol.getNextValue(NEXT_ACTION_ID_KEY));
        newAction.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setProtocolActionTypeCode(ProtocolActionType.NOTIFIED_COMMITTEE);
        ProtocolSubmissionBase submission = protocol.getProtocolSubmission();
        if(submission != null) {
	        newAction.setSubmissionIdFk(submission.getSubmissionId());
	        newAction.setSubmissionNumber(submission.getSubmissionNumber());
        }
        newAction.setProtocolNumber(protocol.getProtocolNumber());
        newAction.setProtocolId(protocol.getProtocolId());
        newAction.setSequenceNumber(protocol.getSequenceNumber());
        //should we be setting prev submission status code here?
        protocol.getProtocolActions().add(newAction);

    }

    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee. 
     * @param protocol
     * @return
     */
    protected ProtocolSubmission findSubmission(Protocol protocol) {
        return findSubmission(protocol, false);
    }
    
    
    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee, or is in agenda.
     * @param protocol
     * @return
     */
    protected ProtocolSubmission findSubmissionIncludingInAgenda(Protocol protocol) {
        return findSubmission(protocol, true);
    }
    
    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee, or is in agenda if the includeInAgenda parameter is set. 
     * @param protocol
     * @return
     */
    private ProtocolSubmission findSubmission(Protocol protocol, boolean includeInAgenda) {
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if ( StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) 
                 ||
                 StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) 
                 ||
                 (includeInAgenda && StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)) ) {
                protocolSubmission = (ProtocolSubmission)submission;
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
            CommitteeSchedule schedule = committeeService.getCommitteeSchedule((Committee)submission.getCommittee(), scheduleId);
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
