/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.attr.PersonAppointment;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class BudgetPersonServiceTest extends KcIntegrationTestBase {

    protected final static String testAppointmentPersonId = "10000000033";
    protected final static String testOtherPersonId = "10000000034";
    
    protected BudgetPersonServiceImpl budgetPersonService;
    protected KcPersonService kcPersonService;
    protected ProposalDevelopmentBudgetExt budget;
    protected Date startDate;
    protected Date endDate;
    
    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        budgetPersonService = (BudgetPersonServiceImpl) KcServiceLocator.getService(BudgetPersonService.class);
        budget = new ProposalDevelopmentBudgetExt() {
            int nextVal = 1;
            public Integer getHackedDocumentNextValue(String documentComponentIdKey) {
                return nextVal++;
            }
        };

        startDate = createDate(2010, 1, 1);
        endDate = createDate(2010, 12, 30);
        budget.setStartDate(startDate);
        budget.setEndDate(endDate);
        budget.setDevelopmentProposal(new DevelopmentProposal());
    }
    
    @After
    public void tearDown() throws Exception {
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
