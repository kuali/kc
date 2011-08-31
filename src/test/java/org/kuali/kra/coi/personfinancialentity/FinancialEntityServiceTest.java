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
import org.kuali.rice.kns.service.BusinessObjectService;

public class FinancialEntityServiceTest {
    private static final String  PERSON_ID = "000001";
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


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
        List<PersonFinIntDisclosure> entities = financialEntityService.getFinancialEntities(PERSON_ID, true);
        Assert.assertEquals(entities.size(), 1);
        Assert.assertEquals(entities.get(0).getPersonId(), PERSON_ID);
        Assert.assertEquals(entities.get(0).getStatusCode().intValue(), 1);
        Assert.assertEquals(entities.get(0).isCurrentFlag(), true);

    }

     @Test
    public void testGetInactiveFinancialEntities() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
        personFinIntDisclosure.setPersonId(PERSON_ID);
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


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
        List<PersonFinIntDisclosure> entities = financialEntityService.getFinancialEntities(PERSON_ID, false);
        Assert.assertEquals(entities.size(), 1);
        Assert.assertEquals(entities.get(0).getPersonId(), PERSON_ID);
        Assert.assertEquals(entities.get(0).getStatusCode().intValue(), 2);
        Assert.assertEquals(entities.get(0).isCurrentFlag(), true);

    }

}
