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
package org.kuali.coeus.propdev.impl.budget.subaward;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

import java.util.List;

public interface PropDevBudgetSubAwardService {

    void populateBudgetSubAwardFiles(Budget budget, BudgetSubAwards budgetSubAwards, String newFileName, byte[] newFileData);

    void populateBudgetSubAwardAttachments(Budget budget);

    void removeSubAwardAttachment(BudgetSubAwards subAward);
    
    void generateSubAwardLineItems(BudgetSubAwards subAward, ProposalDevelopmentBudgetExt budget);
    
    void prepareBudgetSubAwards(Budget budget);
    
    /**
     * Reads the XML from the PDF and parses out amounts from the budget periods. Returns true if the process succeeded, possibly with warnings.
     * Returns false if the process failed for any reason. The required list, errors, will be populated by a list of String arrays. Each array
     * will contain at least one string, the error key for the message. Any other Strings in the array should be considered message parameters 
     * for that error message.
     */
    void updateSubAwardBudgetDetails(Budget budget, BudgetSubAwards budgetSubAward, List<String[]> errors) throws Exception;
}
