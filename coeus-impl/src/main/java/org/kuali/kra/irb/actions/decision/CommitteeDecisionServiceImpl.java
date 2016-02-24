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
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteAbstaineeBase;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteRecusedBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

import java.util.HashMap;
import java.util.Map;

/**
 * The CommitteeDecisionService implementation.
 */
public class CommitteeDecisionServiceImpl extends CommitteeDecisionServiceImplBase<CommitteeDecision> implements CommitteeDecisionService {

    protected String getProtocolActionTypeCodeForRecordCommitteeDecisionHook() {
        return ProtocolActionType.RECORD_COMMITTEE_DECISION;
    }

    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String recordCommitteeDecisionActionCode) {
        return new ProtocolAction((Protocol) protocol, (ProtocolSubmission) submission, recordCommitteeDecisionActionCode);
    }
    
    protected Class<? extends ProtocolVoteAbstaineeBase> getProtocolVoteAbstaineeBOClassHook() {
        return ProtocolVoteAbstainee.class;
    }

    protected ProtocolVoteAbstaineeBase getNewProtocolVoteAbstaineeInstanceHook() {
        return new ProtocolVoteAbstainee();
    }
        
    protected ProtocolVoteRecusedBase getNewProtocolVoteRecusedInstanceHook() {
        return new ProtocolVoteRecused();
    }
  
    protected Class<? extends ProtocolVoteRecusedBase> getProtocolVoteRecusedBOClassHook() {
        return ProtocolVoteRecused.class;
    }
    
    protected Map<String, Object> getFieldValuesMap(Long protocolId, Long scheduleIdFk, String personId, Integer rolodexId, Long submissionIdFk) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("PROTOCOL_ID_FK", protocolId.toString());
        //fieldValues.put("SCHEDULE_ID_FK", scheduleIdFk.toString());
        fieldValues.put("PERSON_ID", personId);
        fieldValues.put("ROLODEX_ID", rolodexId);
        fieldValues.put("SUBMISSION_ID_FK", submissionIdFk.toString());
        return fieldValues;
    }

    protected ProtocolSubmission getSubmission(ProtocolBase protocol) {
        // There are 'findCommission' in other classes.  Consider to create a utility static method for this
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)
                    || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                protocolSubmission = (ProtocolSubmission) submission;
            }
        }
        return protocolSubmission;
    }    
}
