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
package org.kuali.kra.committee.bo;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.meeting.CommitteeScheduleMinute;

/**
 * This is BO class to support CommitteeScheulde. It has three transient field to support UI.
 */
public class CommitteeSchedule extends CommitteeScheduleBase<CommitteeSchedule, Committee, ProtocolSubmission, CommitteeScheduleMinute> { 
    
    private static final long serialVersionUID = -360139608123017188L;
    
    private Committee committee;

    
    public Committee getParentCommittee() {
        return this.getCommittee();
    }
    
    public Committee getCommittee() {
        if (committee == null && getCommitteeIdFk() == null) {
            committee = new Committee();
        }
        return committee;
    }
    
	public void setCommittee(Committee committee) {
		this.committee = committee;
	}

}
