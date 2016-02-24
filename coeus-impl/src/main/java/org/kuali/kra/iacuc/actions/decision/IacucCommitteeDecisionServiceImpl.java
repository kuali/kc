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
package org.kuali.kra.iacuc.actions.decision;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteAbstaineeBase;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteRecusedBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.committee.meeting.IacucProtocolVoteAbstainee;
import org.kuali.kra.iacuc.committee.meeting.IacucProtocolVoteRecused;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

import java.util.HashMap;
import java.util.Map;

public class IacucCommitteeDecisionServiceImpl extends CommitteeDecisionServiceImplBase<IacucCommitteeDecision> implements IacucCommitteeDecisionService {

    @Override
    protected String getProtocolActionTypeCodeForRecordCommitteeDecisionHook() {
        return IacucProtocolActionType.RECORD_COMMITTEE_DECISION;
    }

    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String recordCommitteeDecisionActionCode) {
        return new IacucProtocolAction((IacucProtocol) protocol, (IacucProtocolSubmission) submission,
            recordCommitteeDecisionActionCode);
    }

    @Override
    protected ProtocolSubmissionBase getSubmission(ProtocolBase protocol) {
        // There are 'findCommission' in other classes. Consider to create a utility static method for this
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmissionBase protocolSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.IN_AGENDA)) {
                protocolSubmission = submission;
            }
        }
        return protocolSubmission;
    }

    @Override
    protected Map<String, Object> getFieldValuesMap(Long protocolId, Long scheduleIdFk, String personId, Integer rolodexId, Long submissionIdFk) {
      Map<String, Object> fieldValues = new HashMap<String, Object>();
      fieldValues.put("protocolIdFk", protocolId.toString());
      fieldValues.put("personId", personId);
      fieldValues.put("rolodexId", rolodexId);
      fieldValues.put("submissionIdFk", submissionIdFk.toString());
      return fieldValues;
  }

    @Override
    protected Class<? extends ProtocolVoteAbstaineeBase> getProtocolVoteAbstaineeBOClassHook() {
        return IacucProtocolVoteAbstainee.class;
    }

    @Override
    protected ProtocolVoteAbstaineeBase getNewProtocolVoteAbstaineeInstanceHook() {
        return new IacucProtocolVoteAbstainee();
    }

    @Override
    protected Class<? extends ProtocolVoteRecusedBase> getProtocolVoteRecusedBOClassHook() {
        return IacucProtocolVoteRecused.class;
    }

    @Override
    protected ProtocolVoteRecusedBase getNewProtocolVoteRecusedInstanceHook() {
        return new IacucProtocolVoteRecused();
    }


}
