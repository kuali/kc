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
package org.kuali.coeus.common.budget.impl.nonpersonnel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.award.budget.AwardBudgetForm;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.stereotype.Component;

@Component("groupNameValuesFinder")
public class GroupNameValuesFinder extends UifKeyValuesFinderBase {
    
    /**
     * Constructs the list of existing Group Names.  
     * 
     * @return the list of &lt;key, value&gt; pairs of existing Group Names.  The first entry
     * is always &lt;"", "Default:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        keyValues.add(new ConcreteKeyValue("", "select"));
        AwardBudgetForm budgetForm = (AwardBudgetForm) KNSGlobalVariables.getKualiForm();
        AwardBudgetDocument budgetDocument = (AwardBudgetDocument) budgetForm.getBudgetDocument();
        BudgetPeriod curPeriod = null;
        Integer budgetPeriodNumber = budgetForm.getViewBudgetPeriod() == null ? 1 : budgetForm.getViewBudgetPeriod();
        for (BudgetPeriod period : budgetDocument.getBudget().getBudgetPeriods()) {
        	if (period.getBudgetPeriod().equals(budgetPeriodNumber)) {
        		curPeriod = period;
        		break;
        	}
        }
        keyValues.addAll(getKeyValues(curPeriod));
        return keyValues; 
    }
    
    public List<KeyValue> getKeyValues(ViewModel model) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        keyValues.add(new ConcreteKeyValue("", "Default"));
        BudgetPeriod budgetPeriod = ((ProposalBudgetForm)model).getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
        keyValues.addAll(getKeyValues(budgetPeriod));
        keyValues.add(new ConcreteKeyValue(BudgetConstants.BUDGET_PERSONNEL_NEW_GROUP_NAME, BudgetConstants.BUDGET_PERSONNEL_NEW_GROUP_NAME));
        return keyValues;
    }

    public List<KeyValue> getKeyValues(BudgetPeriod budgetPeriod) {
        Set<String> distinctGroupNames = new HashSet<String>();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
            if(StringUtils.isNotEmpty(budgetLineItem.getGroupName())) {
                boolean distinct = distinctGroupNames.add(budgetLineItem.getGroupName());
                if(distinct) {
                    keyValues.add(new ConcreteKeyValue(budgetLineItem.getGroupName(), budgetLineItem.getGroupName()));
                }
            }
        }
        return keyValues;
    }
    
}
