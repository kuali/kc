/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.service;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;

public interface BudgetSummaryService {

    /**
     * This method is used to generate all budget periods initially based on project/proposal start date
     * and end date.
     * @param budgetPeriods
     * @param projectStartDate
     * @param projectEndDate
     */
    public void generateBudgetPeriods(List<BudgetPeriod> budgetPeriods, Date projectStartDate, Date projectEndDate);
    public void addBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod newBudgetPeriod);
    public void deleteBudgetPeriod(BudgetDocument budgetDocument, int delPeriod);
    public boolean budgetPeriodExists(Integer budgetPeriod);
    public boolean budgetLineItemExists(BudgetDocument budgetDocument, Integer budgetPeriod);
    public void generateAllPeriods(BudgetDocument budgetDocument);
    public void updateBudgetPeriods(BudgetDocument budgetDocument);
    public Collection<BudgetLineItem> getBudgetLineItemForPeriod(BudgetDocument budgetDocument, int budgetPeriodNumber);
    public Collection<BudgetPersonnelDetails> getBudgetPersonnelDetailsForPeriod(BudgetDocument budgetDocument, int budgetPeriodNumber);
}
