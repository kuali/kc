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
