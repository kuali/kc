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
package org.kuali.coeus.propdev.impl.budget.subaward;

import org.kuali.coeus.common.budget.framework.core.Budget;

import java.util.List;

/**
 * 
 * This class is for handling Budget Sub awards
 */
public interface PropDevBudgetSubAwardService {
    /**
     * 
     * This method is to populate the subaward files
     * @param budgetSubAwards
     */
    public void populateBudgetSubAwardFiles(Budget budget, BudgetSubAwards budgetSubAwards, String newFileName, byte[] newFileData);
    /**
     *
     * This method is to handle subaward attachments
     * @param budget
     */
    public void populateBudgetSubAwardAttachments(Budget budget);

    public void removeSubAwardAttachment(BudgetSubAwards subAward);
    
    public void generateSubAwardLineItems(BudgetSubAwards subAward, Budget budget);
    
    public void prepareBudgetSubAwards(Budget budget);
    
    /**
     * Reads the XML from the PDF and parses out amounts from the budget periods. Returns true if the process succeeded, possibly with warnings.
     * Returns false if the process failed for any reason. The required list, errors, will be populated by a list of String arrays. Each array
     * will contain at least one string, the error key for the message. Any other Strings in the array should be considered message parameters 
     * for that error message.
     * @param budget
     * @param budgetSubAward
     * @param errors
     * @return
     * @throws Exception
     */
    public boolean updateSubAwardBudgetDetails(Budget budget, BudgetSubAwards budgetSubAward, List<String[]> errors) throws Exception;
}
