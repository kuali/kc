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

import java.util.*;

import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.KcraNoDataTestBase;
import org.kuali.kra.bo.*;
import org.kuali.kra.budget.core.*;
import org.kuali.kra.budget.document.*;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.*;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.service.*;
import org.kuali.kra.service.impl.KcPersonServiceImpl;
import org.kuali.kra.budget.*;
import org.kuali.rice.kim.service.IdentityService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.impl.ParameterServiceImpl;
import org.kuali.rice.kns.service.impl.ParameterServiceProxyImpl;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@PerSuiteUnitTestData(
        @UnitTestData(
                sqlFiles = {
                        @UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_PERSON_DATA.SQL", delimiter = ";"),
                        @UnitTestFile(filename = "classpath:sql/dml/load_person_appointments.sql", delimiter = ";")
                    }))
public class BudgetPersonServiceImplTest extends KcraNoDataTestBase {
    
    private BudgetPersonService budgetPersonService;
    private BudgetPersonServiceImpl budgetPersonService2;
    //private BudgetService budgetService;
    

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        budgetPersonService = KraServiceLocator.getService(BudgetPersonService.class);
        budgetPersonService2 = (BudgetPersonServiceImpl) KraServiceLocator.getService(BudgetPersonService.class);
        //budgetService = KraServiceLocator.getService(BudgetService.class);
        
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        budgetPersonService = null;
        super.tearDown();
    }
    
    @Test
    public void testCheckCorrectClass(){
        assertTrue("These should be equal: the class is:" + budgetPersonService.getClass(), 
                BudgetPersonServiceImpl.class.equals(budgetPersonService.getClass()));
        assertTrue("These should be equal: the class is:" + budgetPersonService2.getClass(), 
                BudgetPersonServiceImpl.class.equals(budgetPersonService2.getClass()));
    }

    @Test
    public void testPopulateBudgetPersonData() {
        Budget myBudget = new Budget();
        BudgetDocument myDocument = new BudgetDocument();
        BudgetParentDocument myParent = new ProposalDevelopmentDocument();
        myDocument.setParentDocument(myParent);
        myBudget.setBudgetDocument(myDocument);
        myBudget.setBudgetId(new Long(101));
        BudgetPerson budgetPerson = new BudgetPerson();
        budgetPerson.setPersonId("-666");
        budgetPerson.setCalculationBase(new BudgetDecimal(100));
        budgetPerson.setAppointmentTypeCode("tst");
        budgetPerson.setPersonName("Jay Hulslander");
        budgetPersonService.populateBudgetPersonData(myBudget, budgetPerson);
        assertTrue("no errors should happen, so I got here", true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSynchBudgetPersonsToProposal() {
        BudgetPersonServiceImpl budgetPersonService = new BudgetPersonServiceImpl();
        KcPersonServiceMockImpl personServMock = new KcPersonServiceMockImpl();
        personServMock.setIdentityService(KraServiceLocator.getService(IdentityService.class));        
        budgetPersonService.setKcPersonService(personServMock);
        budgetPersonService.setParameterService(new ParameterServiceMockImpl());
        budgetPersonService.setBusinessObjectService(KraServiceLocator.getService(BusinessObjectService.class));
        Budget myBudget = new Budget();
        BudgetDocument myDocument = new BudgetDocument();
        Calendar oneYear = Calendar.getInstance();
        oneYear.add(Calendar.YEAR, 1);
        myBudget.setStartDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        myBudget.setEndDate(new java.sql.Date(oneYear.getTime().getTime()));
        ProposalDevelopmentDocument myParent = new ProposalDevelopmentDocument();
        myDocument.setParentDocument(myParent);
        myBudget.setBudgetDocument(myDocument);
        myBudget.setBudgetId(new Long(101));
        ProposalPerson person = new ProposalPerson();
        person.setPersonId("1301");
        person.setProposalPersonRoleId("PI");
        myParent.getBudgetParent().getProposalPersons().add(person);
        ProposalPerson person2 = new ProposalPerson();
        person2.setPersonId("1302");
        person2.setProposalPersonRoleId("KP");
        myParent.getBudgetParent().getProposalPersons().add(person2);

        budgetPersonService.synchBudgetPersonsToProposal(myBudget);        
        assertTrue("Could not find expected persons in budget.", myBudget.getBudgetPersons().size()==2);
        for (BudgetPerson budgetPerson : myBudget.getBudgetPersons()) {
            if (budgetPerson.getPersonId().equals("1301")) {
                assertTrue("Appointment was not assigned as expected.",
                        budgetPerson.getJobCode().equals("AA0024"));
            }
        }        
    }

    @Test
    public void testFindBudgetPerson() {
        BudgetPersonnelDetails bpd = new BudgetPersonnelDetails();
        bpd.setBudgetId(new Long(1));
        bpd.setPersonSequenceNumber(1);
        BudgetPerson person = budgetPersonService.findBudgetPerson(bpd);
        if(person == null){
            assertTrue("no person found!!!", false);
        }else{
            //person.getPersonName()
            assertTrue("should be equal, the full name was:" + person.getPersonName(), 
                    "Jay Hulslander".equals(person.getPersonName()));
        }
    }

    @Test
    public void testGetBusinessObjectService() {
        BusinessObjectService BOservice = KraServiceLocator.getService(BusinessObjectService.class);
        budgetPersonService2.setBusinessObjectService(BOservice);
        assertTrue("Should be equal, but got:" + budgetPersonService2.getBusinessObjectService().getClass(), 
                budgetPersonService2.getBusinessObjectService().getClass().equals(BOservice.getClass()));
    }
 
    class KcPersonServiceMockImpl extends KcPersonServiceImpl {
        public KcPerson getKcPersonByPersonId(String personId) {
            KcPerson testPerson = new KcPerson();
            testPerson.setPersonId(personId);
            PersonAppointment personAppointment = new PersonAppointment();
            personAppointment.setAppointmentId(1);
            personAppointment.setJobCode("AA0024");
            personAppointment.setSalary(new BudgetDecimal(1000000.00));
            personAppointment.setUnitNumber("0000001");
            testPerson.getExtendedAttributes().getPersonAppointments().add(personAppointment);
            return testPerson;
        }
    }
    
    class ParameterServiceMockImpl extends ParameterServiceProxyImpl {
        public String getParameterValue(Class<? extends Object> componentClass,
            String parameterName) {
            return "0000";
        }
    }
}