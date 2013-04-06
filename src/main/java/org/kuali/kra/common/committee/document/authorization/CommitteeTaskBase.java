/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.document.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.common.committee.bo.CommitteeBase;

/**
 * A CommitteeBase Task is a task that performs an action against a
 * CommitteeBase.  To assist authorization, the CommitteeBase is available.
 */
public abstract class CommitteeTaskBase<CMT extends CommitteeBase<CMT, ?, ?>> extends Task {
    
    private CMT committee;
    
// TODO *********commented the code below during IACUC refactoring*********     
//    /**
//     * Constructs a CommitteeTaskBase.
//     * @param taskName the name of the task
//     * @param committee the CommitteeBase
//     */
//    public CommitteeTaskBase(String taskName, CommonCommittee committee) {
//        super(TaskGroupName.IACUC_COMMITTEE, taskName);
//        this.committee = committee;
//    }

    
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
