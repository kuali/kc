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
package org.kuali.kra.committee.document.authorization;

import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.infrastructure.TaskGroupName;

/**
 * A Committee Task is a task that performs an action against a
 * Committee.  To assist authorization, the Committee is available.
 * 
 * This extension is for checking authorization for the schedules
 * both the committee and schedule are needed to do this.
 * 
 */
public class CommitteeScheduleTask extends CommitteeScheduleTaskBase<Committee, CommitteeSchedule> {

    public CommitteeScheduleTask(String taskName, Committee committee, CommitteeSchedule schedule) {
        super(TaskGroupName.COMMITTEE, taskName, committee, schedule);
    }
}
