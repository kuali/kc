/*
 * Copyright 2006-2009 The Kuali Foundation
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.budget.bo.BudgetCategory;
import org.kuali.kra.budget.bo.BudgetCategoryType;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;

public class CostElementValuesFinder extends KeyValuesBase{
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
    
    private String budgetCategoryTypeCode;
    
    /**
     * Constructs the list of Budget Periods.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from the BudgetDocument if any are defined there. 
     * Otherwise, it is obtained from a lookup of the BUDGET_PERIOD database table
     * via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();        
        Collection costElements= keyValuesService.findAll(CostElement.class);
        Collection budgetCategoryCodes = keyValuesService.findAll(BudgetCategory.class);
        KualiForm form = GlobalVariables.getKualiForm();        
        
        
        BudgetForm budgetForm = (BudgetForm)form;
        for (Iterator iter = costElements.iterator(); iter.hasNext();) {
            CostElement costElement = (CostElement) iter.next();
            for(Iterator iter1 = budgetCategoryCodes.iterator(); iter1.hasNext();){
                BudgetCategory budgetCategory = (BudgetCategory) iter1.next();
                if(costElement.getBudgetCategoryCode().equalsIgnoreCase(budgetCategory.getBudgetCategoryCode())){
                    if(StringUtils.equalsIgnoreCase(budgetCategory.getBudgetCategoryTypeCode(),getBudgetCategoryTypeCode())){
                        keyValues.add(new KeyLabelPair(costElement.getCostElement().toString(), costElement.getDescription()));
                    }
                }
            } 
        }
        // added comparator below to alphabetize lists on label
        Collections.sort(keyValues, new KeyLabelPairComparator());
        keyValues.add(0, new KeyLabelPair("", "select"));
        return keyValues;
    }
    
    public String getBudgetCategoryTypeCode() {
        return budgetCategoryTypeCode;
    }
    public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
        this.budgetCategoryTypeCode = budgetCategoryTypeCode;
    }
    
    class KeyLabelPairComparator implements Comparator<KeyLabelPair> {
        public int compare(KeyLabelPair o1, KeyLabelPair o2) {
            return o1.getLabel().compareToIgnoreCase(o2.getLabel());
        }        
    }
}
