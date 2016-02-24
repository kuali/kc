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
package org.kuali.coeus.common.budget.impl.personnel;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonSalaryService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonSalaryDetails;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service("budgetPersonSalaryService")
public class BudgetPersonSalaryServiceImpl implements BudgetPersonSalaryService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    protected List<BudgetPersonSalaryDetails> findSalaryDetailsByBudgetIdAndPersonIdAndBudgetPeriod(Long budgetId, String personId, Integer budgetPeriod) {
        final HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("budgetId", budgetId);
        criteria.put("personId", personId);
        criteria.put("budgetPeriod", budgetPeriod);
        return ListUtils.emptyIfNull((List<BudgetPersonSalaryDetails>) businessObjectService.findMatchingOrderBy(BudgetPersonSalaryDetails.class, criteria, "personSequenceNumber", true));
    }

    @Override
    public ScaleTwoDecimal findBaseSalaryForFirstPeriod(Long budgetId, String personId, Integer budgetPeriod) {
        if (budgetId == null) {
            throw new IllegalArgumentException("budgetId is null");
        }

        if (StringUtils.isBlank(personId)) {
            throw new IllegalArgumentException("personId is blank");
        }

        if (budgetPeriod == null) {
            throw new IllegalArgumentException("budgetPeriod is null");
        }

        Collection<BudgetPersonSalaryDetails> personSalaryDetails = findSalaryDetailsByBudgetIdAndPersonIdAndBudgetPeriod(budgetId, personId, budgetPeriod);
        if (!personSalaryDetails.isEmpty()) {
            return personSalaryDetails.iterator().next().getBaseSalary();
        }
        return null;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
