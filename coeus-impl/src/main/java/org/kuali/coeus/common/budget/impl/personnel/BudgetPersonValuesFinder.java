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
package org.kuali.coeus.common.budget.impl.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Finds the available set of supported Narrative Statuses.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 */
public class BudgetPersonValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
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
        List<KeyValue> KeyValues = null;
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();
        Document doc = getDocument();
        if (doc instanceof BudgetDocument) {
            BudgetDocument budgetDocument = (BudgetDocument) doc;
            Budget budget = budgetDocument.getBudget();
            Map queryMap = new HashMap();
            queryMap.put("budgetId", budget.getBudgetId());
            List<BudgetPerson> budgetPersons = (List<BudgetPerson>) boService.findMatching(BudgetPerson.class, queryMap);

            KeyValues = buildKeyValues(budgetPersons);
        }
        return KeyValues; 
    }
    
    private List<KeyValue> buildKeyValues(List<BudgetPerson> budgetPersons) {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();
        KeyValues.add(new ConcreteKeyValue(null, "Select"));
        List <BudgetPerson> dupBudgetPersons = (List <BudgetPerson>)ObjectUtils.deepCopy((Serializable)budgetPersons);
        for(BudgetPerson budgetPerson: budgetPersons) {
            if (StringUtils.isNotBlank(budgetPerson.getJobCode()) && StringUtils.isNotBlank(budgetPerson.getAppointmentTypeCode()) && budgetPerson.getCalculationBase().isGreaterEqual(ScaleTwoDecimal.ZERO) && budgetPerson.getEffectiveDate() != null) {
                boolean duplicatePerson = false;
                for (BudgetPerson dupBudgetPerson : dupBudgetPersons) {
                    if (((dupBudgetPerson.getPersonId() != null && dupBudgetPerson.getPersonId().equals(budgetPerson.getPersonId())) || (dupBudgetPerson.getRolodexId() != null && dupBudgetPerson.getRolodexId().equals(budgetPerson.getRolodexId())) )
                            && dupBudgetPerson.getEffectiveDate().before(budgetPerson.getEffectiveDate())
                            && dupBudgetPerson.getJobCode().equals(budgetPerson.getJobCode())) {
                        duplicatePerson = true;
                    }
                    
                }
                if (!duplicatePerson) {
                  KeyValues.add(new ConcreteKeyValue(budgetPerson.getPersonSequenceNumber().toString(), budgetPerson.getPersonName() + " - " + budgetPerson.getJobCode()));
                }
            }
        }
        KeyValues.add(new ConcreteKeyValue("-1", "Summary"));
        return KeyValues;

    }
}
