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
package org.kuali.kra.timeandmoney.document.authorization;

import org.kuali.coeus.common.framework.auth.task.Task;
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
