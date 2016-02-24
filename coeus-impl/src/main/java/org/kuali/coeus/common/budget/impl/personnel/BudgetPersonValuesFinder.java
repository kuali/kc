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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetContainer;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.List;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

/**
 * Finds the available set of supported Narrative Statuses.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 */
@Component("budgetPersonValuesFinder")
public class BudgetPersonValuesFinder extends UifKeyValuesFinderBase {
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    /**
     * Constructs the list of Budget Persons.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * person sequence number and the "value" is the person name that is viewed
     * by a user.  The list is obtained from the BudgetDocument if any are defined there. 
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        BudgetContainer form = (BudgetContainer) KNSGlobalVariables.getKualiForm();
    	Budget budget = form.getBudget();
        return buildKeyValues(budget.getBudgetPersons());
    }
    
    public List<KeyValue> getKeyValues(ViewModel model) {
    	setAddBlankOption(false);
    	return buildKeyValues(((BudgetContainer) model).getBudget().getBudgetPersons());
    }

    private List<KeyValue> buildKeyValues(List<BudgetPerson> budgetPersons) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "Select"));
        Set<String> distinctKeys = new HashSet<String>();
    	for(BudgetPerson budgetPerson : budgetPersons) {
    		boolean duplicatePerson = false;
            if (StringUtils.isNotBlank(budgetPerson.getJobCode()) && StringUtils.isNotBlank(budgetPerson.getAppointmentTypeCode()) && budgetPerson.getCalculationBase().isGreaterEqual(ScaleTwoDecimal.ZERO) && budgetPerson.getEffectiveDate() != null) {
            	duplicatePerson = !distinctKeys.add(getPersonUniqueKey(budgetPerson));
            }
            if (!duplicatePerson) {
            	keyValues.add(new ConcreteKeyValue(budgetPerson.getPersonSequenceNumber().toString(), getBudgetPersonLabel(budgetPerson)));
            }
    	}
    	keyValues.add(new ConcreteKeyValue(BudgetConstants.BudgetPerson.SUMMARYPERSON.getPersonId(), BudgetConstants.BudgetPerson.SUMMARYPERSON.getPersonName()));
        return keyValues;
    }
    
    private String getPersonUniqueKey(BudgetPerson budgetPerson) {
    	StringBuffer uniqueKey = new StringBuffer();
    	uniqueKey.append(budgetPerson.getPersonRolodexTbnId());
    	uniqueKey.append(budgetPerson.getJobCode());
    	uniqueKey.append(budgetPerson.getEffectiveDate());
    	return uniqueKey.toString();
    }
    
    protected String getBudgetPersonLabel(BudgetPerson budgetPerson) {
    	StringBuffer personLabel = new StringBuffer();
    	String personName = budgetPerson.getPersonName() != null ? budgetPerson.getPersonName() : "";
    	personLabel.append(personName);
    	if(budgetPerson.getJobCode() != null) {
        	personLabel.append(" (");
        	personLabel.append(budgetPerson.getJobCode());
        	personLabel.append(") ");
    	}
    	return personLabel.toString();
    }

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
