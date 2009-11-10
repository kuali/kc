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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KcraNoDataTestBase;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

@PerTestUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_KRNS_PARM_T_data.sql", delimiter = ";")
            }
            )
            )

public class KcPersonServiceImplTest extends KcraNoDataTestBase {
    
    private KcPersonService service;
    private KcPersonServiceImpl service2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        service = KraServiceLocator.getService(KcPersonService.class);
        service2 = (KcPersonServiceImpl)KraServiceLocator.getService(KcPersonService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        service = null;
        service2 = null;
        super.tearDown();
    }
    
    @Test
    public void testCorrectClass(){
        assertTrue("Should be the same", service.getClass().equals(KcPersonServiceImpl.class));
    }

    @Test
    public void testGetKcPersons() {
        Map<String,String> fieldValues = new HashMap<String, String>();
        fieldValues.put("NM_TYP_CD", "PREFERRED");
        List<KcPerson> kcPersons = service.getKcPersons(fieldValues);
        Iterator<KcPerson> i = kcPersons.iterator();
        boolean foundExpectedPerson = false;
        while(i.hasNext()){
            KcPerson person = i.next();
            //System.err.println("The KC Person: " + person.toString());
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
        String entityID = "superuser";
        String expectedUserName = "superuser";
        KcPerson person = service.getKcPersonByPersonId(entityID);
        assertTrue("Should have found:" + expectedUserName  + ", but found:" + person.getUserName(), expectedUserName.equals(person.getUserName()));
    }
}
