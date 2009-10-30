/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.proposaldevelopment.document.*;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.service.*;
import org.kuali.kra.budget.*;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
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
                @UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_PERSON_DATA.sql", delimiter = ";")
                //@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_PERIOD_TYPE.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_TYPE.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_MAPPING.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_MAPS.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_budget_details_cam_amts.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_budget_line_item_for_total.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_PERIOD_TYPE.sql", delimiter = ";")
                //,@UnitTestFile(filename = "classpath:sql/dml/load_budget_status.sql", delimiter = ";")
            }
            )
            )

public class BudgetPersonServiceImplTest extends KcraNoDataTestBase{
    
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

    @Test
    public void testSynchBudgetPersonsToProposal() {
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
        budgetPersonService.synchBudgetPersonsToProposal(myBudget);
        assertTrue("no errors should happen, so I got here", true);
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
}