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
package org.kuali.kra.irb.actions.decision;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttendanceBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.document.Document;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 */
public class CommitteeMemberValuesFinder extends IrbActionsKeyValuesBase {
    

    private static final long serialVersionUID = -7110160295510181062L;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        Protocol protocol = getProtocol();
        if (protocol != null) {
            ProtocolSubmission submission = getCurrentSubmission(protocol);
            if (submission != null) {
                String committeeId = submission.getCommitteeId();
                Committee committee = getCommitteeService().getCommitteeById(committeeId);
                if (committee != null) {
                    List<CommitteeMembershipBase> members = committee.getCommitteeMemberships();
                    for (CommitteeMembershipBase member : members) {
                        if (member.isActive() && isReviewerAttendingMeeting((CommitteeMembership) member)) {
                            keyValues.add(new ConcreteKeyValue(member.getCommitteeMembershipId().toString(), member.getPersonName()));
                        }
                    }
                }
            }
        }
        
        return keyValues;
    }

    private ProtocolSubmission getCurrentSubmission(Protocol protocol) {
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return (ProtocolSubmission) submission;
            }
        }
        return null;
    }

    private Protocol getProtocol() {
        Document document = getDocument();
        if (document instanceof ProtocolDocument) {
            return ((ProtocolDocument) document).getProtocol();
        }
        return null;
    }
    
    private boolean isReviewerAttendingMeeting(CommitteeMembership member) {
        Protocol prot = getProtocol();
        boolean retVal = false;
        if (prot != null) {
            List<CommitteeScheduleAttendanceBase> attendees = prot.getProtocolSubmission().getCommitteeSchedule().getCommitteeScheduleAttendances();
            for (CommitteeScheduleAttendanceBase attendee : attendees) {
                if (attendee.isCommitteeMember(member)) {
                    return true;
                }
            }
        }
        return retVal;
        
    }
}
