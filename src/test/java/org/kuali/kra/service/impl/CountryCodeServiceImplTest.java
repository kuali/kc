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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.CountryCode;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CountryCodeService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

//@PerSuiteUnitTestData(
//        @UnitTestData(
//            sqlFiles = {
//                    @UnitTestFile(filename = "classpath:sql/dml/kc_country_codes_t_bootstrap.sql", delimiter = ";")
//            }
//        )
//    )


public class CountryCodeServiceImplTest extends KcUnitTestBase {
    
    
    private CountryCodeService service = null;
    

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        service = KraServiceLocator.getService(CountryCodeService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        service = null;
        super.tearDown();
    }
    
    @Test public void testGetCountryCodeByCountryName(){
        CountryCode cc = service.getCountryCodeByCountryName("Aruba");
        assertTrue("Aruba did not return 'AW' at the two character code", cc.getTwoCharacterCountryCode().equals("AW"));
    }
    
    @Test public void testGetCountryCodeByThreeCharacterCode(){
        CountryCode cc = service.getCountryCodeByThreeCharacterCode("AFG");
        assertTrue("AFG should have returned AF code, is Afghanistan", cc.getTwoCharacterCountryCode().equals("AF"));
    }
    
    @Test public void testGetCountryCodeByTwoCharacterCode(){
        CountryCode cc = service.getCountryCodeByTwoCharacterCode("AL");
        assertTrue("AL should have returned ALB, is Albania", cc.getThreeCharacterCountryCode().equals("ALB"));
    }
    
    @Test public void testInvalidSearch(){
        CountryCode cc = service.getCountryCodeByTwoCharacterCode("XX");
        assertNull(cc);
    }
}
