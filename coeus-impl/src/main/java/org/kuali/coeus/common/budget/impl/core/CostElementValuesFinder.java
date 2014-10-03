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
package org.kuali.coeus.common.budget.impl.core;

import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.util.KRADPropertyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("costElementValuesFinder")
public class CostElementValuesFinder extends UifKeyValuesFinderBase {
    
	@Autowired
    @Qualifier("dataObjectService")
	DataObjectService dataObjectService;
    private String budgetCategoryTypeCode;

    /**
     * Constructs the list of COST ELEMENT.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed
     * by a user.  
     * via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        return getKeyValues(getBudgetCategoryTypeCode(), true);
    }

    public List<KeyValue> getKeyValues(String budgetCategoryTypeCode, boolean budgetCategoryTypeCodeEqual) {
        return getCostElementKeyValues(budgetCategoryTypeCode, budgetCategoryTypeCodeEqual);
    }
    
    private List<KeyValue> getCostElementKeyValues(String budgetCategoryTypeCode, boolean budgetCategoryTypeCodeEqual) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        List<CostElement> costElements = (List<CostElement>)getDataObjectService().findMatching(CostElement.class, QueryByCriteria.Builder.fromPredicates(getPredicates(budgetCategoryTypeCode, budgetCategoryTypeCodeEqual))).getResults();    
        for (CostElement costElement : costElements) {
            keyValues.add(new ConcreteKeyValue(costElement.getCostElement(), costElement.getDescription()));
        }
        // added comparator below to alphabetize lists on label
        Collections.sort(keyValues, new KeyValueComparator());
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        return keyValues;
    }
    
    private List<Predicate> getPredicates(String budgetCategoryTypeCode, boolean budgetCategoryTypeCodeEqual) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(PredicateFactory.equal(KRADPropertyConstants.ACTIVE, Boolean.TRUE));
        if(budgetCategoryTypeCodeEqual) {
            predicates.add(PredicateFactory.equal("budgetCategory.budgetCategoryTypeCode", budgetCategoryTypeCode));
        }else {
            predicates.add(PredicateFactory.notEqual("budgetCategory.budgetCategoryTypeCode", budgetCategoryTypeCode));
        }
        return predicates;
    }
    
    public String getBudgetCategoryTypeCode() {
        return budgetCategoryTypeCode;
    }
    public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
        this.budgetCategoryTypeCode = budgetCategoryTypeCode;
    }
    
    class KeyValueComparator implements Comparator<KeyValue> {
        public int compare(KeyValue o1, KeyValue o2) {
            return o1.getValue().compareToIgnoreCase(o2.getValue());
        }        
    }

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

}
