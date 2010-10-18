/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.document.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.TaskGroupName;

/**
 * A Committee Task is a task that performs an action against a
 * Committee.  To assist authorization, the Committee is available.
 */
public class CommitteeTask extends Task {
    
    private Committee committee;
    
    /**
     * Constructs a CommitteeTask.
     * @param taskName the name of the task
     * @param committee the Committee
     */
    public CommitteeTask(String taskName, Committee committee) {
        super(TaskGroupName.COMMITTEE, taskName);
        this.committee = committee;
    }

    /**
     * Get the Committee.
     * @return the Committee
     */
    public Committee getCommittee() {
        return committee;
    }
}
