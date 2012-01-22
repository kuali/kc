/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.budget.personnel;

import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.PersonAppointment;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class BudgetPersonServiceTest extends KcUnitTestBase{

    protected final static String testAppointmentPersonId = "10000000033";
    protected final static String testOtherPersonId = "10000000034";
    
    protected BudgetPersonServiceImpl budgetPersonService;
    protected KcPersonService kcPersonService;
    protected Budget budget;
    protected Date startDate;
    protected Date endDate;
    
    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        budgetPersonService = (BudgetPersonServiceImpl) KraServiceLocator.getService(BudgetPersonService.class);
        budget = new Budget() {
            int nextVal = 1;
            public Integer getHackedDocumentNextValue(String documentComponentIdKey) {
                return nextVal++;
            }
        };
        budget.setBudgetDocument(new BudgetDocument());
        ProposalDevelopmentDocument parentDoc = new ProposalDevelopmentDocument();
        budget.getBudgetDocument().setParentDocument(parentDoc);
       
        startDate = createDate(2010, 1, 1);
        endDate = createDate(2010, 12, 30);
        budget.setStartDate(startDate);
        budget.setEndDate(endDate);
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testAddBudgetPersonWithAppointments() throws Exception {
        assertTrue(budget.getBudgetPersons().isEmpty());
        BudgetPerson testPerson = new BudgetPerson();
        KcPerson person = kcPersonService.getKcPersonByPersonId(testAppointmentPersonId);
        testPerson.setPersonId(testAppointmentPersonId);
        budgetPersonService.addBudgetPerson(budget, testPerson);
        assertFalse(person.getExtendedAttributes().getPersonAppointments().isEmpty());
        assertTrue(person.getExtendedAttributes().getPersonAppointments().size() == budget.getBudgetPersons().size());
    }
    
    @Test
    public void testAddBudgetPerson() throws Exception {
        assertTrue(budget.getBudgetPersons().isEmpty());
        BudgetPerson testPerson = new BudgetPerson();
        testPerson.setPersonId(testOtherPersonId);
        budgetPersonService.addBudgetPerson(budget, testPerson);
        assertTrue(budget.getBudgetPersons().size() == 1);
    }
    
    @Test
    public void testIsAppointmentApplicableToBudget() {
        PersonAppointment appointment = new PersonAppointment();
        appointment.setStartDate(createDate(2000, 1, 1));
        assertTrue(budgetPersonService.isAppointmentApplicableToBudget(budget, appointment));
        appointment.setEndDate(createDate(2009, 1, 1));
        assertFalse(budgetPersonService.isAppointmentApplicableToBudget(budget, appointment));
        appointment.setEndDate(createDate(2020, 1, 1));
        assertTrue(budgetPersonService.isAppointmentApplicableToBudget(budget, appointment));
        appointment.setStartDate(createDate(2019, 1, 1));
        assertFalse(budgetPersonService.isAppointmentApplicableToBudget(budget, appointment));
    }
    
    public Date createDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        return new Date(cal.getTime().getTime());
    }
}
