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
package org.kuali.kra.award.budget.document.authorization;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.infrastructure.TaskGroupName;

public class AwardBudgetTask extends Task {
    
    private AwardBudgetDocument awardBudgetDocument;
    public AwardBudgetTask(String taskName, AwardBudgetDocument awardBudgetDocument) {
        super(TaskGroupName.AWARD_BUDGET, taskName);
        this.awardBudgetDocument = awardBudgetDocument;
    }
    /**
     * Gets the awardBudgetDocument attribute. 
     * @return Returns the awardBudgetDocument.
     */
    public AwardBudgetDocument getAwardBudgetDocument() {
        return awardBudgetDocument;
    }
    /**
     * Sets the awardBudgetDocument attribute value.
     * @param awardBudgetDocument The awardBudgetDocument to set.
     */
    public void setAwardBudgetDocument(AwardBudgetDocument awardBudgetDocument) {
        this.awardBudgetDocument = awardBudgetDocument;
    }

}
