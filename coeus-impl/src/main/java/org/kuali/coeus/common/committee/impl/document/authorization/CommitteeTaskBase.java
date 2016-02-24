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
import org.kuali.coeus.common.framework.auth.task.Task;

/**
 * A CommitteeBase Task is a task that performs an action against a
 * CommitteeBase.  To assist authorization, the CommitteeBase is available.
 */
public abstract class CommitteeTaskBase<CMT extends CommitteeBase<CMT, ?, ?>> extends Task {
    
    private CMT committee;
    
    /**
     * Constructs a CommitteeTaskBase.
     * @param taskGroupName the name of the task group
     * @param taskName the name of the task
     * @param committee the CommitteeBase
     */
    public CommitteeTaskBase(String taskGroupName, String taskName, CMT committee) {
        super(taskGroupName, taskName);
        this.committee = committee;
    }

    
    /**
     * Get the CommitteeBase.
     * @return the CommitteeBase
     */
    public CMT getCommittee() {
        return committee;
    }
}
