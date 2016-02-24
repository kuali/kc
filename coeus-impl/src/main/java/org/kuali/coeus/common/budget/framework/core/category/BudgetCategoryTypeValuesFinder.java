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
package org.kuali.coeus.common.budget.framework.core.category;

import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
		//if this is coming from award budget, the autowiring won't work
		if (dataObjectService == null) {
			dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		}
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

}
