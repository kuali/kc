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
package org.kuali.coeus.common.committee.impl.document.authorization;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;

/**
 * A CommitteeBase Task is a task that performs an action against a
 * CommitteeBase.  To assist authorization, the CommitteeBase is available.
 * 
 * This extension is for checking authorization for the schedules
 * both the committee and schedule are needed to do this.
 * 
 */
public abstract class CommitteeScheduleTaskBase<CMT extends CommitteeBase<CMT, ?, CS>, 
                                            CS extends CommitteeScheduleBase<CS, CMT, ?, ?>> 
    
                                            extends CommitteeTaskBase<CMT> {

    protected CS schedule;
    
    public CommitteeScheduleTaskBase(String taskGroupName, String taskName, CMT committee, CS schedule) {
        super(taskGroupName, taskName, committee);
        this.schedule = schedule;
    }

    public CS getCommitteeSchedule() {
        return schedule;
    }
    
}
