/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions.decision;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttendanceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.iacuc.actions.IacucActionsKeyValuesBase;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 */
public class IacucCommitteeMemberValuesFinder extends IacucActionsKeyValuesBase {
    

    private static final long serialVersionUID = 1547047028487421985L;

    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        ProtocolBase protocol = getProtocol();
        if (protocol != null) {
            ProtocolSubmissionBase submission = getCurrentSubmission(protocol);
            if (submission != null) {
                String committeeId = submission.getCommitteeId();
                CommitteeBase committee = getCommitteeService().getCommitteeById(committeeId);
                if (committee != null) {
                    List<CommitteeMembershipBase> members = committee.getCommitteeMemberships();
                    for (CommitteeMembershipBase member : members) {
                        if (member.isActive() && isReviewerAttendingMeeting(member)) {
                            keyValues.add(new ConcreteKeyValue(member.getCommitteeMembershipId().toString(), member.getPersonName()));
                        }
                    }
                }
            }
        }
        
        return keyValues;
    }

    private ProtocolSubmissionBase getCurrentSubmission(ProtocolBase protocol) {
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.IN_AGENDA) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return submission;
            }
        }
        return null;
    }

    private ProtocolBase getProtocol() {
        Object formOrView = getFormOrView();
        if (formOrView != null && formOrView instanceof ProtocolFormBase) {
            return ((ProtocolFormBase) formOrView).getProtocolDocument().getProtocol();
        }
        return null;
    }
    
    private boolean isReviewerAttendingMeeting(CommitteeMembershipBase member) {
        ProtocolBase prot = getProtocol();
        boolean retVal = false;
        if (prot != null) {
            CommitteeScheduleBase schedule = prot.getProtocolSubmission().getCommitteeSchedule();
            if(schedule != null) {
                List<CommitteeScheduleAttendanceBase> attendees = schedule.getCommitteeScheduleAttendances();
                for (CommitteeScheduleAttendanceBase attendee : attendees) {
                    if (attendee.isCommitteeMember(member)) {
                        return true;
                    }
                }
            }
        }
        return retVal;
        
    }
    
    
    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }
}
