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
package org.kuali.coeus.common.budget.impl.period;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.rice.krad.bo.BusinessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;


@Component("budgetPeriodLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Lazy
public class BudgetPeriodLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

    @Autowired
    @Qualifier("awardBudgetService")
    protected AwardBudgetService awardBudgetService;

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        String awardNumber = fieldValues.get("budgetParentId");
        List<BudgetPeriod> budgetPeriods = getAwardBudgetService().findBudgetPeriodsFromLinkedProposal(awardNumber);
        filterSearchResults(budgetPeriods, fieldValues);
        return budgetPeriods;
    }    
    
    /**
     * Using the list of linked budget periods filter the result set by the field values given in the search
     * @param budgetPeriods
     * @param fieldValues
     */
    protected void filterSearchResults(List<BudgetPeriod> budgetPeriods, Map<String, String> fieldValues) {
        String instPropNumber = fieldValues.get("institutionalProposalNumber");
        String instPropVersion = fieldValues.get("institutionalProposalVersion");
        String budgetPeriod = fieldValues.get("budgetPeriod");
        String totalCost = fieldValues.get("totalCost");
        String directCost = fieldValues.get("totalDirectCost");
        String indirectCost = fieldValues.get("totalIndirectCost");
        String costSharingAmt = fieldValues.get("costSharingAmount");
        String underrecoveryAmount = fieldValues.get("underrecoveryAmount");
        String comments = fieldValues.get("comments");
        ListIterator<BudgetPeriod> iter = budgetPeriods.listIterator();
        while (iter.hasNext()) {
            BudgetPeriod period = iter.next();
            if (StringUtils.isNotBlank(instPropNumber) 
                    && !StringUtils.equals(instPropNumber, period.getInstitutionalProposalNumber())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(instPropVersion)
                    && !StringUtils.equals(instPropVersion, period.getInstitutionalProposalVersion().toString())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(budgetPeriod)
                    && !StringUtils.equals(budgetPeriod, period.getBudgetPeriod().toString())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(totalCost)
                    && !StringUtils.equals(totalCost, period.getTotalCost().toString())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(directCost)
                    && !StringUtils.equals(directCost, period.getTotalDirectCost().toString())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(indirectCost)
                    && !StringUtils.equals(indirectCost, period.getTotalIndirectCost().toString())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(costSharingAmt)
                    && !StringUtils.equals(costSharingAmt, period.getCostSharingAmount().toString())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(underrecoveryAmount)
                    && !StringUtils.equals(underrecoveryAmount, period.getUnderrecoveryAmount().toString())) {
                iter.remove();
            } else if (StringUtils.isNotBlank(comments)
                    && !StringUtils.equals(comments, period.getComments())) {
                iter.remove();
            }
        }
    }

    protected AwardBudgetService getAwardBudgetService() {
        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }
}
