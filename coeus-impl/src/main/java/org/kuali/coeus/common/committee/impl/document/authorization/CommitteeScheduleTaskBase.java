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
