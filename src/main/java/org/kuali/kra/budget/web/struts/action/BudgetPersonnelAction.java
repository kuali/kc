/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.web.struts.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.lookup.LookupResultsService;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class BudgetPersonnelAction extends BudgetAction {
    
    //@SuppressWarnings("something");
    private static final Log LOG = LogFactory.getLog(BudgetPersonnelAction.class);

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        
        // Process return from person/rolodex multi-value lookup
        String lookupResultsSequenceNumber = budgetForm.getLookupResultsSequenceNumber();
        Class<?> lookupResultsBOClass = Class.forName(budgetForm.getLookupResultsBOClassName());
        
        Collection<PersistableBusinessObject> rawValues = KraServiceLocator.getService(LookupResultsService.class)
            .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                    GlobalVariables.getUserSession().getUniversalUser().getPersonUniversalIdentifier());
        
        if (lookupResultsBOClass.isAssignableFrom(Person.class)) {
            for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                Person person = (Person) iter.next();
                BudgetPerson budgetPerson = new BudgetPerson();
                budgetPerson.setPersonId(person.getPersonId());
                budgetPerson.setPersonName(person.getFullName());
                budgetPerson.setJobCode("0");
                budgetPerson.setNonEmployeeFlag(false);
                budgetPerson.setAppointmentTypeCode("1");
                budgetPerson.setCalculationBase(BudgetDecimal.ZERO);
                budgetPerson.setEffectiveDate(KraServiceLocator.getService(DateTimeService.class).getCurrentSqlDate());
                budgetForm.getBudgetDocument().addBudgetPerson(budgetPerson);
            }
        } else if (lookupResultsBOClass.isAssignableFrom(Rolodex.class)) {
            for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                Rolodex rolodex = (Rolodex) iter.next();
                BudgetPerson budgetPerson = new BudgetPerson();
                budgetPerson.setPersonId(rolodex.getRolodexId().toString());
                budgetPerson.setPersonName(rolodex.getFirstName() + " " + rolodex.getLastName());
                budgetPerson.setJobCode("0");
                budgetPerson.setNonEmployeeFlag(true);
                budgetPerson.setAppointmentTypeCode("1");
                budgetPerson.setCalculationBase(BudgetDecimal.ZERO);
                budgetPerson.setEffectiveDate(KraServiceLocator.getService(DateTimeService.class).getCurrentSqlDate());
                budgetForm.getBudgetDocument().addBudgetPerson(budgetPerson);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}
