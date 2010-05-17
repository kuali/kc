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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Handles the assignment of a protocol to a committee/schedule.
 */
public class ProtocolAssignCmtSchedServiceImpl implements ProtocolAssignCmtSchedService {

    private BusinessObjectService businessObjectService;
    private CommitteeService committeeService;
    
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
        setSchedule(submission, actionBean.getNewCommitteeId(), actionBean.getNewScheduleId());
        submission.setSubmissionStatusCode(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }

    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee. 
     * @param protocol
     * @return
     */
    private ProtocolSubmission findSubmission(Protocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return submission;
            }
        }
        return null;
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
            }
            else {
                submission.setScheduleId(schedule.getScheduleId());
                submission.setScheduleIdFk(schedule.getId());
                submission.setCommitteeSchedule(schedule);
            }
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
