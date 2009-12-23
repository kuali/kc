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
package org.kuali.kra.irb.actions.assignagenda;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.meeting.ProtocolSubmittedBean;
import org.kuali.rice.kns.service.BusinessObjectService;
//import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedServiceImpl;

public class ProtocolAssignToAgendaServiceImpl implements ProtocolAssignToAgendaService {
    
    private BusinessObjectService businessObjectService;
    private CommitteeService committeeService;
    //private ProtocolAssignCmtSchedServiceImpl protocolAssignCmtSchedServiceImpl;
    
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
    
    private ProtocolSubmission findSubmission(Protocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return submission;
            }
        }
        return null;
    }

    public void assignToAgenda(Protocol protocol, ProtocolAssignToAgendaBean actionBean) throws Exception {
        // TODO Auto-generated method stub

    }

    public String getAssignToAgendaComments(Protocol protocol) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getAssignedCommitteeId(Protocol protocol) {
        ProtocolSubmission ps = findSubmission(protocol);
        return ps.getCommitteeId();
    }

    public String getAssignedCommitteeName(Protocol protocol) {
        ProtocolSubmission ps = findSubmission(protocol);
        return ps.getCommittee().getCommitteeName();
    }

    public Date getAssignedScheduleDate(Protocol protocol) {
        ProtocolSubmission ps = findSubmission(protocol);
        return ps.getCommitteeSchedule().getScheduledDate();
    }
    
    /**
    public String getAssignedScheduleId(Protocol protocol) {
        // TODO Auto-generated method stub
        return null;
    }
**/
}
