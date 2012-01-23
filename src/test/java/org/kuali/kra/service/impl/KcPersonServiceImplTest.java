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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class KcPersonServiceImplTest extends KcUnitTestBase {
    
    private KcPersonService service;
    private KcPersonServiceImpl service2;

    @Before
    public void getServices() throws Exception {
        service = KraServiceLocator.getService(KcPersonService.class);
        service2 = (KcPersonServiceImpl)KraServiceLocator.getService(KcPersonService.class);
    }
    
    @Test
    public void testCorrectClass(){
        assertTrue("Should be the same", service.getClass().equals(KcPersonServiceImpl.class));
    }

    @Test
    public void testGetKcPersons() {
        Map<String,String> fieldValues = new HashMap<String, String>();
        fieldValues.put("names.nameCode", "PRFR"); 
        List<KcPerson> kcPersons = service.getKcPersons(fieldValues);
        Iterator<KcPerson> i = kcPersons.iterator();
        boolean foundExpectedPerson = false;
        while(i.hasNext()){
            KcPerson person = i.next();
            if("admin".equals(person.getUserName())){
                foundExpectedPerson = true;
            }
        }
        assertTrue("Should have found 'admin'", foundExpectedPerson);
    }

    @Test
    public void testGetKcPersonByUserName() {
        String userName = "quickstart";
        KcPerson person = service.getKcPersonByUserName(userName);
        assertTrue("Should have found 'quickstart', but found:" + person.getUserName(), userName.equals(person.getUserName()));
    }

    @Test
    public void testGetKcPersonByPersonId() {
        String personID = "10000000002";
        String expectedUserName = "jtester";
        KcPerson person = service.getKcPersonByPersonId(personID);
        assertTrue("Should have found:" + expectedUserName  + ", but found:" + person.getUserName(), expectedUserName.equals(person.getUserName()));
    }
}
