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
package org.kuali.kra.s2s.service;

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CitizenshipDataTypeTest {
    
    Enum USCitizen;
    Enum nonUSCitizen;
    Enum permanentUSResident;

    @Before
    public void setUp() throws Exception {
        USCitizen = CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL;
        nonUSCitizen = CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA;
        permanentUSResident= CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
    }

    @After
    public void tearDown() throws Exception {
        USCitizen = null;
    }
    
    @Test
    public void testStaticIntegers() {
        assertEquals(3, CitizenshipDataType.INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA);
        assertEquals(2, CitizenshipDataType.INT_PERMANENT_RESIDENT_OF_U_S);
        assertEquals(1, CitizenshipDataType.INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL);
    }

    @Test
    public void testIntValsOfEnums() {
        assertEquals(CitizenshipDataType.INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL, USCitizen.intValue()); 
        assertEquals(CitizenshipDataType.INT_PERMANENT_RESIDENT_OF_U_S, permanentUSResident.intValue());
        assertEquals(CitizenshipDataType.INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA, nonUSCitizen.intValue());
    }

}