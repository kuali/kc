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

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ArgValueLookupService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class ArgValueLookupServiceImplTest extends KcUnitTestBase{

    private ArgValueLookupService argValueLookupService = null;
    
    private static String expectedArgumentNames = ",AcademicAppointmentPeriod;AcademicAppointmentPeriod,AppointmentTypes;AppointmentTypes,FieldOfTraining;FieldOfTraining,FieldOfTraining-Broad;FieldOfTraining-Broad,FieldOfTraining-SubCategory;FieldOfTraining-SubCategory,GraduateLevelDegree;GraduateLevelDegree,GraduateLevelDegree1-2;GraduateLevelDegree1-2,Kirschstein-NRSAAwardTYPE;Kirschstein-NRSAAwardTYPE,Kirschstein-NRSASupportLevel;Kirschstein-NRSASupportLevel,KirschsteinLevel;KirschsteinLevel,KirschsteinType;KirschsteinType,PI_Question;PI_Question,PeriodTypes;PeriodTypes,ProjectRoles;ProjectRoles,Sponsor_routing;Sponsor_routing,human_subjects;human_subjects,interest_revenue;interest_revenue,revenue;revenue,yes_no_flag;yes_no_flag";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        argValueLookupService = KraServiceLocator.getService(ArgValueLookupService.class);
    }
    @After
    public void tearDown() throws Exception {
        argValueLookupService = null;
        super.tearDown();
    }
    
    /**
     * 
     * This method tests getArgumentNames
     * @throws Exception
     */
    @Test
    public void testGetArgumentNames() throws Exception {
        boolean success = true;
        String argumentNames = argValueLookupService.getArgumentNames();
        String[] argumentNamesArray = argumentNames.split(",");
        String[] expectedArgumentNamesArray = expectedArgumentNames.split(",");
        List<String> argumentNamesList = java.util.Arrays.asList(argumentNamesArray); 
        for (int i = 0; i < expectedArgumentNamesArray.length; i++) {
            if(success && !argumentNamesList.contains(expectedArgumentNamesArray[i])){
                success = false;
            }
        }
        assertTrue(success);
    }
}
