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
package org.kuali.kra.iacuc.committee.bo;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;

public class IacucCommitteeSchedule extends CommitteeScheduleBase<IacucCommitteeSchedule, IacucCommittee, IacucProtocolSubmission, IacucCommitteeScheduleMinute> {


    private static final long serialVersionUID = -579662475857490755L;
    
    private IacucCommittee committee;
    
    public IacucCommittee getParentCommittee() {
        return this.getCommittee();
    }
    
    public IacucCommittee getCommittee() {
        if (committee == null && getCommitteeIdFk() == null) {
            committee = new IacucCommittee();
        }
        return committee;
    }
    
    public void setCommittee(IacucCommittee committee) {
        this.committee = committee;
    }
    
}
