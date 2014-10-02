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
package org.kuali.coeus.common.budget.framework.core.category;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("budgetCategoryTypeValuesFinder")
public class BudgetCategoryTypeValuesFinder extends UifKeyValuesFinderBase {
	@Autowired
    @Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
    /**
     * Constructs the list of Budget Categories.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained BUDGET_CATEGORY database table
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
    	return getKeyValues(new ArrayList<Predicate>());
    }
    
    public List<KeyValue> getKeyValues(List<Predicate> predicates) {
    	QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
    	if(!predicates.isEmpty()) {
        	builder.setPredicates(PredicateFactory.and(predicates.toArray(new Predicate[] {})));
    	}
    	builder.setOrderByAscending("sortId");
    	List<BudgetCategoryType> budgetCategoryTypes = (List<BudgetCategoryType>)getDataObjectService().findMatching(BudgetCategoryType.class, 
    			builder.build()).getResults();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (BudgetCategoryType budgetCategoryType : budgetCategoryTypes) {
            keyValues.add(new ConcreteKeyValue(budgetCategoryType.getCode(), budgetCategoryType.getDescription()));
        }
        return keyValues;
    }
    
	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

}
