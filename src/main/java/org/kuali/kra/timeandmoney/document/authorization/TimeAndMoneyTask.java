/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.document.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;

/**
 * An Award Task is a task that performs an action against a
 * Award.  To assist authorization, the Award is available.
 */
public final class TimeAndMoneyTask extends Task {
    
    private TimeAndMoneyDocument timeAndMoneyDocument;
    
    /**
     * Constructs an TimeAndMoneyTask.
     * @param taskName the name of the task
     * @param timeAndMoneyDocument the TimeAndMoneyDocument
     */
    public TimeAndMoneyTask(String taskName, TimeAndMoneyDocument timeAndMoneyDocument) {
        super(TaskGroupName.TIME_AND_MONEY, taskName);
        this.timeAndMoneyDocument = timeAndMoneyDocument;
    }

    /**
     * Get the Award.
     * @return the Award
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return timeAndMoneyDocument;
    }
}
