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
package org.kuali.kra.iacuc.actions.decision;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;

public class IacucCommitteeDecisionServiceImpl extends CommitteeDecisionServiceImpl<IacucCommitteeDecision> implements IacucCommitteeDecisionService {

    @Override
    protected String getProtocolActionTypeCodeForRecordCommitteeDecisionHook() {
        return IacucProtocolActionType.RECORD_COMMITTEE_DECISION;
    }

    @Override
    protected ProtocolAction getNewProtocolActionInstanceHook(Protocol protocol, ProtocolSubmission submission, String recordCommitteeDecisionActionCode) {
        return new IacucProtocolAction((IacucProtocol) protocol, (IacucProtocolSubmission) submission,
            recordCommitteeDecisionActionCode);
    }

    @Override
    protected ProtocolSubmission getSubmission(Protocol protocol) {
        // There are 'findCommission' in other classes. Consider to create a utility static method for this
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if ((StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.IN_AGENDA)) ||
                    (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) && 
                    submission.getProtocolReviewTypeCode().equals(IacucProtocolReviewType.DESIGNATED_MEMBER_REVIEW))) {
            
//                    || 
//              StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE))
                protocolSubmission = submission;
            }
        }
        return protocolSubmission;
    }

    @Override
    protected Map<String, Object> getFieldValuesMap(Long protocolId, Long scheduleIdFk, String personId, Integer rolodexId, Long submissionIdFk) {
      Map<String, Object> fieldValues = new HashMap<String, Object>();
      fieldValues.put("protocolIdFk", protocolId.toString());
      //fieldValues.put("SCHEDULE_ID_FK", scheduleIdFk.toString());
      fieldValues.put("personId", personId);
      fieldValues.put("rolodexId", rolodexId);
      fieldValues.put("submissionIdFk", submissionIdFk.toString());
      return fieldValues;
  }


}
