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
package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.*;

public class GroupNameValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    /**
     * Constructs the list of existing Group Names.  
     * 
     * @return the list of &lt;key, value&gt; pairs of existing Group Names.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        keyValues.add(new ConcreteKeyValue("", "select"));

        Set<String> distinctGroupNames = new HashSet<String>();

        BudgetForm budgetForm = (BudgetForm) getFormOrView();
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        Map fieldValues = new HashMap();
        fieldValues.put("budgetId", budgetDocument.getBudget().getBudgetId());
        
        int budgetPeriodNumber = -1;
        if(budgetForm.getViewBudgetPeriod() == null)
            budgetPeriodNumber = 1;
        else 
            budgetPeriodNumber = budgetForm.getViewBudgetPeriod();
        
        fieldValues.put("budgetPeriod", budgetPeriodNumber);
        
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        List<BudgetPeriod> budgetPeriods = (List<BudgetPeriod>) businessObjectService.findMatching(BudgetPeriod.class, fieldValues);
        BudgetPeriod budgetPeriod = null;
        if(CollectionUtils.isNotEmpty(budgetPeriods)) {
            budgetPeriod = budgetPeriods.get(0);
        }
        
        fieldValues.remove("budgetPeriod");
        fieldValues.put("budgetPeriodId", budgetPeriod.getBudgetPeriodId());
        List<BudgetLineItem> budgetLineItems = (List<BudgetLineItem>) keyValuesService.findMatching(BudgetLineItem.class, fieldValues);
        
        boolean distinct = false;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
             if(StringUtils.isNotEmpty(budgetLineItem.getGroupName())) {
                 distinct = distinctGroupNames.add(budgetLineItem.getGroupName());
                 if(distinct) {
                     keyValues.add(new ConcreteKeyValue(budgetLineItem.getGroupName(), budgetLineItem.getGroupName()));
                 }
             }
        }       
        
        for (BudgetLineItem newBudgetLineItem : budgetDocument.getBudget().getBudgetPeriod(budgetPeriodNumber-1).getBudgetLineItems()) {
            if(StringUtils.isNotEmpty(newBudgetLineItem.getGroupName())) {
                distinct = distinctGroupNames.add(newBudgetLineItem.getGroupName());
                if(distinct) {
                    keyValues.add(new ConcreteKeyValue(newBudgetLineItem.getGroupName(), newBudgetLineItem.getGroupName()));
                }
            }
        }    

        
        return keyValues;
    }

}
