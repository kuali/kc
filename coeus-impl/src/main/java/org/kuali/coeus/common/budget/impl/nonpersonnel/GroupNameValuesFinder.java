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
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.award.budget.AwardBudgetForm;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.*;

public class GroupNameValuesFinder extends UifKeyValuesFinderBase {
    
    /**
     * Constructs the list of existing Group Names.  
     * 
     * @return the list of &lt;key, value&gt; pairs of existing Group Names.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        AwardBudgetForm budgetForm = (AwardBudgetForm) KNSGlobalVariables.getKualiForm();
        AwardBudgetDocument budgetDocument = (AwardBudgetDocument) budgetForm.getBudgetDocument();
        
        return getKeyValues(budgetDocument.getBudget(), budgetForm.getViewBudgetPeriod() == null ? 1 : budgetForm.getViewBudgetPeriod()); 
    }
    
    protected List<KeyValue> getKeyValues(Budget budget, Integer budgetPeriodNumber) {
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        keyValues.add(new ConcreteKeyValue("", "select"));

        Set<String> distinctGroupNames = new HashSet<String>();
        
        BudgetPeriod curPeriod = null;
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
        	if (period.getBudgetPeriod().equals(budgetPeriodNumber)) {
        		curPeriod = period;
        	}
        }
        if (curPeriod != null) {        
	        boolean distinct = false;
	        for (BudgetLineItem budgetLineItem : curPeriod.getBudgetLineItems()) {
	             if(StringUtils.isNotEmpty(budgetLineItem.getGroupName())) {
	                 distinct = distinctGroupNames.add(budgetLineItem.getGroupName());
	                 if(distinct) {
	                     keyValues.add(new ConcreteKeyValue(budgetLineItem.getGroupName(), budgetLineItem.getGroupName()));
	                 }
	             }
	        }       
	        
	        for (BudgetLineItem newBudgetLineItem : budget.getBudgetPeriod(budgetPeriodNumber-1).getBudgetLineItems()) {
	            if(StringUtils.isNotEmpty(newBudgetLineItem.getGroupName())) {
	                distinct = distinctGroupNames.add(newBudgetLineItem.getGroupName());
	                if(distinct) {
	                    keyValues.add(new ConcreteKeyValue(newBudgetLineItem.getGroupName(), newBudgetLineItem.getGroupName()));
	                }
	            }
	        }
        }

        return keyValues;
    }

}
