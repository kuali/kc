/*
 * Copyright 2005-2014 The Kuali Foundation
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