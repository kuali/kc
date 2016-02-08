/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.budget.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
        return getKeyValues(getBudgetCategoryTypeCode(), true, null);
    }

    public List<KeyValue> getKeyValues(String budgetCategoryTypeCode, boolean budgetCategoryTypeCodeEqual, String budgetCategoryCode) {
        return getCostElementKeyValues(budgetCategoryTypeCode, budgetCategoryTypeCodeEqual, budgetCategoryCode);
    }
    
    protected List<KeyValue> getCostElementKeyValues(String budgetCategoryTypeCode, boolean budgetCategoryTypeCodeEqual, String budgetCategoryCode) {
        List<CostElement> costElements = (List<CostElement>)getDataObjectService().findMatching(CostElement.class, QueryByCriteria.Builder.fromPredicates(getPredicates(budgetCategoryTypeCode, budgetCategoryTypeCodeEqual,budgetCategoryCode))).getResults();
        return getDropDownDisplay(costElements);
    }

    protected List<KeyValue> getDropDownDisplay(List<CostElement> costElements) {
        List<KeyValue> keyValues = new ArrayList<>();
        for (CostElement costElement : costElements) {
            keyValues.add(new ConcreteKeyValue(costElement.getCostElement(), costElement.getDescription()));
        }
        // added comparator below to alphabetize lists on label
        Collections.sort(keyValues, new KeyValueComparator());
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        return keyValues;
    }

    protected List<Predicate> getPredicates(String budgetCategoryTypeCode, boolean budgetCategoryTypeCodeEqual, String budgetCategoryCode) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(PredicateFactory.equal(KRADPropertyConstants.ACTIVE, Boolean.TRUE));
        if (!StringUtils.isBlank(budgetCategoryTypeCode)){
	        if(budgetCategoryTypeCodeEqual) {
	            predicates.add(PredicateFactory.equal("budgetCategory.budgetCategoryTypeCode", budgetCategoryTypeCode));
	        }else {
	            predicates.add(PredicateFactory.notEqual("budgetCategory.budgetCategoryTypeCode", budgetCategoryTypeCode));
	        }
        }
        if (StringUtils.isNotEmpty(budgetCategoryCode)) {
            predicates.add(PredicateFactory.equal("budgetCategory.code", budgetCategoryCode));
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
