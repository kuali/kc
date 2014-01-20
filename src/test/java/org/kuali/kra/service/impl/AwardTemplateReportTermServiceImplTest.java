/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardTemplateReportTermService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AwardTemplateReportTermServiceImplTest extends KcUnitTestBase {

    private AwardTemplateReportTermService awardTemplateReportTermService = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardTemplateReportTermService = KraServiceLocator.getService(AwardTemplateReportTermService.class);
    }

    @After
    public void tearDown() throws Exception {
        awardTemplateReportTermService = null;
        super.tearDown();
    }
    
    @Test public void testGetReportTypesUsingReportClassCode() throws Exception {
        List<String> properties = new ArrayList<String>();
        properties.add("1");
        properties.add("7");
        properties.add("5");
        properties.add("40");
        properties.add("35");
        properties.add("39");
        Collection reprotTypes = awardTemplateReportTermService.getReportTypesUsingReportClassCode("2");
        assertEquals(properties.size(), reprotTypes.size());

        for(Object aReportType : reprotTypes) {
            assertTrue(properties.contains(aReportType));
        }
    }
    
    @Test
    public void testGetReportTypeForAjaxCall() throws Exception {
        String properties = ",1;None,7;Progress/Status,5;Final,40;DD 1342,35;Form provided by sponsor,39;SF 1018";
        String resultFields = awardTemplateReportTermService.getReportTypeForAjaxCall("2");
        assertEquals(properties,resultFields);
    }
    
    @Test
    public void testGetFrequencyUsingReportCodeAndClass() throws Exception {
        List<String> properties = new ArrayList<String>();
        properties.add("1");
        Collection frequencies = awardTemplateReportTermService.getFrequencyUsingReportCodeAndClass("1", "2");
        assertEquals(properties.size(), frequencies.size());

        for(Object aFrequency : frequencies) {
            assertTrue(properties.contains(aFrequency));
        }
    }
    
    @Test
    public void testGetFrequencyForAjaxCall() throws Exception {
        String properties = ",1;None";
        String resultFields = awardTemplateReportTermService.getFrequencyForAjaxCall("1", "2");
        assertEquals(properties,resultFields);
    }
    
    @Test
    public void testGetFrequencyBaseUsingFrequencyCode() throws Exception {
        List<String> properties = new ArrayList<String>();
        properties.add("1");
        properties.add("2");
        properties.add("6");
        Collection frequencyBases = awardTemplateReportTermService.getFrequencyBaseUsingFrequencyCode("2");
        assertEquals(properties.size(), frequencyBases.size());

        for(Object aFrequencyBase : frequencyBases) {
            assertTrue(properties.contains(aFrequencyBase));
        }
    }
    
    @Test
    public void testGetFrequencyBaseForAjaxCall() throws Exception {
        String properties = ",2;Project Start Date,1;Execution Date,6;As Required";
        String resultFields = awardTemplateReportTermService.getFrequencyBaseForAjaxCall("2");
        assertEquals(properties,resultFields);
    }
    
    
    
    
}
