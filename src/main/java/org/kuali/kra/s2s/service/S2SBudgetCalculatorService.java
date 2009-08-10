/*
 * Copyright 2008 The Kuali Foundation.
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

import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.bo.BudgetPeriodInfo;
import org.kuali.kra.s2s.generator.bo.BudgetSummaryInfo;

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
    public BudgetSummaryInfo getBudgetInfo(ProposalDevelopmentDocument pdDoc) throws S2SException;

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
     * This method returns the final version of {@link BudgetDocument} for a given {@link ProposalDevelopmentDocument}
     * 
     * @param pdDoc Proposal development document.
     * @return BudgetDocument final version of budget corresponding to the ProposalDevelopmentDocument object.
     * @throws S2SException
     */
    public BudgetDocument getFinalBudgetVersion(ProposalDevelopmentDocument pdDoc) throws S2SException;

    /**
     * 
     * This method gets the salary requested for a given proposal person.
     * 
     * @param pdDoc {@link ProposalDevelopmentDocument} from which salary needs to be fetched
     * @param proposalPerson proposal person whose salary needs to be fetched
     * 
     * @return {@link BudgetDecimal} salary of proposal person
     * @throws S2SException
     */
    public BudgetDecimal getProposalPersonSalary(ProposalDevelopmentDocument pdDoc, ProposalPerson proposalPerson)
            throws S2SException;

    /**
     * 
     * This method determines whether a {@link ProposalPerson} is a Non MIT person
     * 
     * @param proposalPerson ProposalPerson.
     * @return boolean true if Non MIT Person false otherwise.
     */
    public boolean isPersonNonMITPerson(ProposalPerson proposalPerson);

}
