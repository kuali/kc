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
package org.kuali.kra.coi.personfinancialentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class FinancialEntityServiceTest {
    private static final String  PERSON_ID = "10000000001";
    private static final String  UNIT_NUMBER = "000001";
    private static final String  UNIT_NAME = "University";
    private static final String  ENTITY_NUMBER = "1";
    Mockery context = new JUnit4Mockery();

    @Before
    public void setUp() throws Exception {
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
     @Test
    public void testGetActiveFinancialEntities() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
        personFinIntDisclosure.setPersonId(PERSON_ID);
        personFinIntDisclosure.setStatusCode(1);
        personFinIntDisclosure.setCurrentFlag(true);
        personFinIntDisclosure.setEntityNumber(ENTITY_NUMBER);
        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", PERSON_ID);
                fieldValues.put("statusCode", "1");     
                fieldValues.put("currentFlag", "Y");     
                one(businessObjectService).findMatching(PersonFinIntDisclosure.class, fieldValues);
                will(returnValue(activeEntities));
                Map fieldValues1 = new HashMap();
                fieldValues1.put("entityNumber", ENTITY_NUMBER);
                one(businessObjectService).findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues1, "sequenceNumber", false);
                will(returnValue(activeEntities));


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
        List<PersonFinIntDisclosure> entities = financialEntityService.getFinancialEntities(PERSON_ID, true);
        Assert.assertEquals(entities.size(), 1);
        Assert.assertEquals(entities.get(0).getPersonId(), PERSON_ID);
        Assert.assertEquals(entities.get(0).getStatusCode().intValue(), 1);
        Assert.assertEquals(entities.get(0).getVersions().size(), 1);
        Assert.assertEquals(entities.get(0).getVersions().get(0).getEntityNumber(), ENTITY_NUMBER);
       Assert.assertEquals(entities.get(0).isCurrentFlag(), true);

    }

     @Test
    public void testGetInactiveFinancialEntities() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
        personFinIntDisclosure.setPersonId(PERSON_ID);
        personFinIntDisclosure.setEntityNumber(ENTITY_NUMBER);
        personFinIntDisclosure.setStatusCode(2);
        personFinIntDisclosure.setCurrentFlag(true);
        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", PERSON_ID);
                fieldValues.put("statusCode", "2");     
                fieldValues.put("currentFlag", "Y");     
                one(businessObjectService).findMatching(PersonFinIntDisclosure.class, fieldValues);
                will(returnValue(activeEntities));
                Map fieldValues1 = new HashMap();
                fieldValues1.put("entityNumber", ENTITY_NUMBER);
                one(businessObjectService).findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues1, "sequenceNumber", false);
                will(returnValue(activeEntities));


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
        List<PersonFinIntDisclosure> entities = financialEntityService.getFinancialEntities(PERSON_ID, false);
        Assert.assertEquals(entities.size(), 1);
        Assert.assertEquals(entities.get(0).getPersonId(), PERSON_ID);
        Assert.assertEquals(entities.get(0).getStatusCode().intValue(), 2);
        Assert.assertEquals(entities.get(0).getVersions().size(), 1);
        Assert.assertEquals(entities.get(0).getVersions().get(0).getEntityNumber(), ENTITY_NUMBER);
        Assert.assertEquals(entities.get(0).isCurrentFlag(), true);

    }
     
     @Test
    public void testGetFinancialEntityReporter() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        FinancialEntityReporter reporter = new FinancialEntityReporter();
        reporter.setPersonId(PERSON_ID);
        reporter.setReporterRoleId("FER");
        reporter.setFinancialEntityReporterUnits(new ArrayList<FinancialEntityReporterUnit>());
        FinancialEntityReporterUnit financialEntityReporterUnit = new FinancialEntityReporterUnit() {
            public String getUnitName() {
                return UNIT_NAME;
            }
        };
        financialEntityReporterUnit.setUnitNumber(UNIT_NUMBER);
        financialEntityReporterUnit.setUnitName(UNIT_NAME);
        financialEntityReporterUnit.setLeadUnitFlag(true);
        reporter.getFinancialEntityReporterUnits().add(financialEntityReporterUnit);

        
        final List<FinancialEntityReporter> reporters = new ArrayList<FinancialEntityReporter>();
        reporters.add(reporter);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", PERSON_ID);
                fieldValues.put("reporterRoleId", "FER");     
                one(businessObjectService).findMatching(FinancialEntityReporter.class, fieldValues);
                will(returnValue(reporters));


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
//        financialEntityService.setKcPersonService(getMockKcPersonService());
        
        FinancialEntityReporter financialEntityReporter = financialEntityService.getFinancialEntityReporter(PERSON_ID);
        Assert.assertEquals(financialEntityReporter.getFinancialEntityReporterUnits().size(), 1);
        Assert.assertEquals(financialEntityReporter.getPersonId(), PERSON_ID);
        Assert.assertEquals(financialEntityReporter.getReporterRoleId(), "FER");
        Assert.assertEquals(financialEntityReporter.getFinancialEntityReporterUnits().get(0).getUnitNumber(), UNIT_NUMBER);
        Assert.assertEquals(financialEntityReporter.getFinancialEntityReporterUnits().get(0).getUnitName(), UNIT_NAME);

    }
     
  
     private KcPersonService getMockKcPersonService() {
         // not really referenced yet
         final KcPersonService kcPersonService = context.mock(KcPersonService.class);
         final KcPerson kcPerson = new KcPerson() {
             public String getUserName() {
                 
                 return "quickstart";
             }
             public Unit getUnit() {
                 final Unit unit = new Unit();
                 unit.setUnitNumber(UNIT_NUMBER);
                 unit.setUnitName(UNIT_NAME);
                 
                 return unit;
             }

         };
        // kcPerson.setPersonId(PERSON_ID);
         
         context.checking(new Expectations() {{
             allowing(kcPersonService).getKcPersonByPersonId(PERSON_ID);
             will(returnValue(kcPerson));
         }});
         return kcPersonService;
     }


}
