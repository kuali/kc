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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.web.ui.KeyLabelPair;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Finds the available set of supported Narrative Statuses.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 */
public class BudgetPersonValuesFinder extends KeyValuesBase {
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
    
    /**
     * Constructs the list of Budget Persons.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * person sequence number and the "value" is the person name that is viewed
     * by a user.  The list is obtained from the BudgetDocument if any are defined there. 
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyLabelPairs = null;
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();
        KualiForm form = GlobalVariables.getKualiForm();
        if(form instanceof KualiDocumentFormBase) {
            Document doc = ((KualiDocumentFormBase) form).getDocument();
            if(doc instanceof BudgetDocument) {
                BudgetDocument budgetDocument = (BudgetDocument) doc;
                Map queryMap = new HashMap();
                queryMap.put("proposalNumber", budgetDocument.getProposalNumber());
                queryMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber());
                List<BudgetPerson> budgetPersons = (List<BudgetPerson>) boService.findMatching(BudgetPerson.class, queryMap);

                keyLabelPairs = buildKeyLabelPairs(budgetPersons);
            }
        }
        return keyLabelPairs; 
    }
    
    private List<KeyLabelPair> buildKeyLabelPairs(List<BudgetPerson> budgetPersons) {
        List<KeyLabelPair> keyLabelPairs = new ArrayList<KeyLabelPair>();
        keyLabelPairs.add(new KeyLabelPair(null, "Select"));
        List <BudgetPerson> dupBudgetPersons = (List <BudgetPerson>)ObjectUtils.deepCopy((Serializable)budgetPersons);
        for(BudgetPerson budgetPerson: budgetPersons) {
            if (StringUtils.isNotBlank(budgetPerson.getJobCode()) && StringUtils.isNotBlank(budgetPerson.getAppointmentTypeCode()) && budgetPerson.getCalculationBase().isGreaterEqual(BudgetDecimal.ZERO) && budgetPerson.getEffectiveDate() != null) {
                boolean duplicatePerson = false;
                for (BudgetPerson dupBudgetPerson : dupBudgetPersons) {
                    if (((dupBudgetPerson.getPersonId() != null && dupBudgetPerson.getPersonId().equals(budgetPerson.getPersonId())) || (dupBudgetPerson.getRolodexId() != null && dupBudgetPerson.getRolodexId().equals(budgetPerson.getRolodexId())) )
                            && dupBudgetPerson.getEffectiveDate().before(budgetPerson.getEffectiveDate())
                            && dupBudgetPerson.getJobCode().equals(budgetPerson.getJobCode())) {
                        duplicatePerson = true;
                    }
                    
                }
                if (!duplicatePerson) {
                  keyLabelPairs.add(new KeyLabelPair(budgetPerson.getPersonSequenceNumber(), budgetPerson.getPersonName() + " - " + budgetPerson.getJobCode()));
                }
            }
        }
        keyLabelPairs.add(new KeyLabelPair("-1", "Summary"));
        return keyLabelPairs;

    }
}
