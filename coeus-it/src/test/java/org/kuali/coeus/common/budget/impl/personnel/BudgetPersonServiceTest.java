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
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
            public Integer getNextValue(String documentComponentIdKey) {
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
