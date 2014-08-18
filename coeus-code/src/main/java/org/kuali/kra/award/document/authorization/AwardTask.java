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
