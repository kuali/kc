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
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionQualifierTypeBase;

/**
 * 
 * This class tracks the data associated with the submission of a protocol for review.
 */
public class IacucProtocolSubmission extends ProtocolSubmissionBase {
    

    private static final long serialVersionUID = 4270551170133689515L;

    @Override
    protected ProtocolSubmissionQualifierTypeBase getNewInstanceProtocolSubmissionQualifierTypeHook() {
        return new IacucProtocolSubmissionQualifierType();
    }
    
    
    public IacucCommittee getIacucCommittee() {
        return (IacucCommittee) super.getCommittee();
    }

    
    public IacucCommitteeSchedule getIacucCommitteeSchedule() {
        return (IacucCommitteeSchedule) super.getCommitteeSchedule();
    }
}
