/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.kra.s2s.service;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.bo.BudgetPeriodInfo;
import org.kuali.kra.s2s.generator.bo.BudgetSummaryInfo;
import org.kuali.kra.s2s.generator.bo.IndirectCostInfo;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;

import java.util.List;

/**
 * This class contains the Budget related calculations for a proposal
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SBudgetCalculatorService {

    /**
     * 
     * This method returns a list of {@link BudgetCategoryMap} based on the input. The list returned will not contain the categories
     * that the codes passed as a list of {@link String} and also will not contain those that match the types passed as list of
     * {@link String}. In case 2 empty lists are passed as parameters, the method will return entire list without applying any
     * filters.
     * 
     * @param filterTargetCategoryCodes Category Codes that must be filtered
     * @param filterCategoryTypes Category types that must be filtered
     * @return a List of BudgetCategoryMap.
     */
    public List<BudgetCategoryMap> getBudgetCategoryMapList(List<String> filterTargetCategoryCodes, List<String> filterCategoryTypes);

    /**
     * 
     * This method does the budget related calculations for a given {@link ProposalDevelopmentDocument} and returns them in
     * {@link BudgetSummaryInfo}
     * 
     * @param pdDoc ProposalDevelopmentDocument.
     * @return BudgetSummaryInfo corresponding to the ProposalDevelopmentDocument object.
     * @throws S2SException
     */
    public BudgetSummaryInfo getBudgetInfo(ProposalDevelopmentDocument pdDoc, List<BudgetPeriodInfo> budgetperiodList) throws S2SException;

    /**
     * 
     * This method gets the list of {@link BudgetPeriodInfo} for the latest {@link BudgetDocument} of the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param pdDoc ProposalDevelopmentDocument
     * @return a List of BudgetPeriodInfo corresponding to the ProposalDevelopmentDocument object.
     * @throws S2SException
     */
    public List<BudgetPeriodInfo> getBudgetPeriods(ProposalDevelopmentDocument pdDoc) throws S2SException;

    /**
     * 
     * This method determines whether a {@link ProposalPerson} is a Non MIT person
     * 
     * @param proposalPerson ProposalPerson.
     * @return boolean true if Non MIT Person false otherwise.
     */
    public boolean isPersonNonMITPerson(ProposalPerson proposalPerson);
    /**
     * 
     * This method computes the indirect costs for a given {@link BudgetPeriod}
     * 
     * @param budgetPeriod
     *            given BudgetPeriod.
     * @return IndirectCostInfo for the corresponding BudgetPeriod object.
     */
    public IndirectCostInfo getIndirectCosts(Budget budget,BudgetPeriod budgetPeriod);

    public ScaleTwoDecimal getBaseSalaryByPeriod(Long budgetId, int budgetPeriod, KeyPersonInfo keyPerson );

}
