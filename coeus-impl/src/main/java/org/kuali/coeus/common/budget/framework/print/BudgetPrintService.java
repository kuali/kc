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
package org.kuali.coeus.common.budget.framework.print;

import java.util.List;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.impl.print.BudgetPrintForm;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

public interface BudgetPrintService {
	
	/**
	 * Populates the various forms that are part of Budget on UI
	 * @param budget
	 */
    public void populateBudgetPrintForms(Budget budget);
    
    /**
     * Generates the report specified and returns the bytes
     * 
     * @param budget {@link Budget}
     * @param selectedBudgetPrintFormId form to print
     * @return {@link AttachmentDataSource} bytes of the generated form
     */
    public AttachmentDataSource readBudgetPrintStream(Budget budget, String selectedBudgetPrintFormId);
    
    /**
     * Generates the selected reports and returns the bytes
     * 
     * @param budget {@link Budget}
     * @param budgetPrintForms {@link BudgetPrintForm} selected forms to print
     * @param reportName report to be generated
     * @return {@link AttachmentDataSource} bytes of the generated form
     */
    public AttachmentDataSource readBudgetSelectedPrintStreams(Budget budget, List<BudgetPrintForm> budgetPrintForms, String reportName);
}
