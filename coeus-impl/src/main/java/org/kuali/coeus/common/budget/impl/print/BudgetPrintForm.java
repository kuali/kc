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
package org.kuali.coeus.common.budget.impl.print;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class BudgetPrintForm extends KcPersistableBusinessObjectBase {

    private String budgetReportId;

    private String budgetReportName;

    private Boolean selectToPrint;
    
    private Boolean selectToComment;

    /**
     * Gets the budgetReportId attribute. 
     * @return Returns the budgetReportId.
     */
    public String getBudgetReportId() {
        return budgetReportId;
    }

    /**
     * Sets the budgetReportId attribute value.
     * @param budgetReportId The budgetReportId to set.
     */
    public void setBudgetReportId(String budgetReportId) {
        this.budgetReportId = budgetReportId;
    }

    /**
     * Gets the budgetReportName attribute. 
     * @return Returns the budgetReportName.
     */
    public String getBudgetReportName() {
        return budgetReportName;
    }

    /**
     * Sets the budgetReportName attribute value.
     * @param budgetReportName The budgetReportName to set.
     */
    public void setBudgetReportName(String budgetReportName) {
        this.budgetReportName = budgetReportName;
    }

    public Boolean getSelectToPrint() {
        return selectToPrint;
    }

    public void setSelectToPrint(Boolean selectToPrint) {
        this.selectToPrint = selectToPrint;
    }

	public Boolean getSelectToComment() {
		return selectToComment;
	}

	public void setSelectToComment(Boolean selectToComment) {
		this.selectToComment = selectToComment;
	}
}
