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
package org.kuali.kra.award.document.authorization;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.TaskGroupName;

/**
 * An Award Task is a task that performs an action against a
 * Award.  To assist authorization, the Award is available.
 */
public final class AwardTask extends Task {
    
    private Award award;
    
    /**
     * Constructs an AwardTask.
     * @param taskName the name of the task
     * @param award the Award
     */
    public AwardTask(String taskName, Award award) {
        super(TaskGroupName.AWARD, taskName);
        this.award = award;
    }

    /**
     * Get the Award.
     * @return the Award
     */
    public Award getAward() {
        return award;
    }
}
