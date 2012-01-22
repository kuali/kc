/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.lookup.keyvalue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;

public class GroupNameValuesFinder extends KeyValuesBase{
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
    
    /**
     * Constructs the list of existing Group Names.  
     * 
     * @return the list of &lt;key, value&gt; pairs of existing Group Names.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        keyValues.add(new ConcreteKeyValue("", "select"));

        Set<String> distinctGroupNames = new HashSet<String>();
        
        KualiForm form = KNSGlobalVariables.getKualiForm();        
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        Map fieldValues = new HashMap();
//        DevelopmentProposal proposal = budgetDocument.getParentDocument().getDevelopmentProposal();
        fieldValues.put("budgetId", budgetDocument.getBudget().getBudgetId());
        
        int budgetPeriodNumber = -1;
        if(budgetForm.getViewBudgetPeriod() == null)
            budgetPeriodNumber = 1;
        else 
            budgetPeriodNumber = budgetForm.getViewBudgetPeriod();
        
        fieldValues.put("budgetPeriod", budgetPeriodNumber);
        
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);        
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
        
//        if(StringUtils.isNotEmpty(budgetForm.getNewGroupName())) {
//            keyValues.add(new ConcreteKeyValue(budgetForm.getNewGroupName(), budgetForm.getNewGroupName()));
//        }
        
        return keyValues;
    }

}
