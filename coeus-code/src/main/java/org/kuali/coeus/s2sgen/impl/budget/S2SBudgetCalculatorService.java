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
package org.kuali.coeus.s2sgen.impl.budget;

import org.kuali.coeus.common.budget.api.core.BudgetContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract;

import java.util.List;

/**
 * This class contains the Budget related calculations for a proposal
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SBudgetCalculatorService {

    String getParticipantSupportCategoryCode();

    List<? extends BudgetLineItemContract> getMatchingLineItems(List<? extends BudgetLineItemContract> lineItems, List<String> budgetCategoryType);

    /**
     * 
     * This method returns a list of {@link BudgetCategoryMapContract} based on the input. The list returned will not contain the categories
     * that the codes passed as a list of {@link String} and also will not contain those that match the types passed as list of
     * {@link String}. In case 2 empty lists are passed as parameters, the method will return entire list without applying any
     * filters.
     * 
     * @param filterTargetCategoryCodes Category Codes that must be filtered
     * @param filterCategoryTypes Category types that must be filtered
     * @return a List of BudgetCategoryMap.
     */
    List<? extends BudgetCategoryMapContract> getBudgetCategoryMapList(List<String> filterTargetCategoryCodes, List<String> filterCategoryTypes);

    /**
     * 
     * This method does the budget related calculations for a given {@link ProposalDevelopmentDocumentContract} and returns them in
     * {@link BudgetSummaryDto}
     * 
     * @param pdDoc ProposalDevelopmentDocumentContract.
     * @return BudgetSummaryInfo corresponding to the ProposalDevelopmentDocumentContract object.
     * @throws S2SException
     */
    BudgetSummaryDto getBudgetInfo(ProposalDevelopmentDocumentContract pdDoc, List<BudgetPeriodDto> budgetperiodList) throws S2SException;

    /**
     * 
     * This method gets the list of {@link BudgetPeriodDto} for the latest {@link BudgetContract} of the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param pdDoc ProposalDevelopmentDocumentContract
     * @return a List of BudgetPeriodInfo corresponding to the ProposalDevelopmentDocumentContract object.
     * @throws S2SException
     */
    List<BudgetPeriodDto> getBudgetPeriods(ProposalDevelopmentDocumentContract pdDoc) throws S2SException;

    /**
     * 
     * This method determines whether a {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract} is a Non MIT person
     * 
     * @param proposalPerson ProposalPerson.
     * @return boolean true if Non MIT Person false otherwise.
     */
    boolean isPersonNonMITPerson(ProposalPersonContract proposalPerson);
    /**
     * 
     * This method computes the indirect costs for a given {@link org.kuali.coeus.common.budget.api.period.BudgetPeriodContract}
     * 
     * @param budgetPeriod
     *            given BudgetPeriod.
     * @return IndirectCostInfo for the corresponding BudgetPeriod object.
     */
    IndirectCostDto getIndirectCosts(BudgetContract budget,BudgetPeriodContract budgetPeriod);

    ScaleTwoDecimal getBaseSalaryByPeriod(Long budgetId, int budgetPeriod, KeyPersonDto keyPerson );

    /**
     *
     * This method compares a key person with budget person. It checks whether
     * the key person is from PERSON or ROLODEX and matches the respective
     * person ID with the person in {@link org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract}
     *
     * @param keyPersonInfo -
     *            key person to compare
     * @param budgetPersonnelDetails
     *            person from BudgetPersonnelDetails
     * @return true if persons match, false otherwise
     */
    boolean keyPersonEqualsBudgetPerson(KeyPersonDto keyPersonInfo,
                                               BudgetPersonnelDetailsContract budgetPersonnelDetails);

}
