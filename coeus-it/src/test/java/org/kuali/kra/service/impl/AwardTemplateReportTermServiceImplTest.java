/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.service.AwardTemplateReportTermService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class AwardTemplateReportTermServiceImplTest extends KcIntegrationTestBase {

    private AwardTemplateReportTermService awardTemplateReportTermService = null;
    
    @Before
    public void setUp() throws Exception {
        awardTemplateReportTermService = KcServiceLocator.getService(AwardTemplateReportTermService.class);
    }

    @After
    public void tearDown() throws Exception {
        awardTemplateReportTermService = null;
    }
    
    @Test public void testGetReportTypesUsingReportClassCode() {
        List<String> properties = new ArrayList<String>();
        properties.add("1");
        properties.add("7");
        properties.add("5");
        properties.add("40");
        properties.add("35");
        properties.add("39");
        Collection<String> reprotTypes = awardTemplateReportTermService.getReportTypesUsingReportClassCode("2");
        assertEquals(properties.size(), reprotTypes.size());

        for(String aReportType : reprotTypes) {
            assertTrue(properties.contains(aReportType));
        }
    }
    
    @Test
    public void testGetReportTypeForAjaxCall() {
        List<String> expected = Arrays.asList(",1;None,7;Progress/Status,5;Final,40;DD 1342,35;Form provided by sponsor,39;SF 1018".split(","));
        Collections.sort(expected);

        List<String> resultFields = Arrays.asList(awardTemplateReportTermService.getReportTypeForAjaxCall("2").split(","));
        Collections.sort(resultFields);

        assertEquals(expected, resultFields);
    }
    
    @Test
    public void testGetFrequencyUsingReportCodeAndClass() {
        List<String> properties = new ArrayList<String>();
        properties.add("1");
        Collection<String> frequencies = awardTemplateReportTermService.getFrequencyUsingReportCodeAndClass("1", "2");
        assertEquals(properties.size(), frequencies.size());

        for(String aFrequency : frequencies) {
            assertTrue(properties.contains(aFrequency));
        }
    }
    
    @Test
    public void testGetFrequencyForAjaxCall() {
        String properties = ",1;None";
        String resultFields = awardTemplateReportTermService.getFrequencyForAjaxCall("1", "2");
        assertEquals(properties,resultFields);
    }
    
    @Test
    public void testGetFrequencyBaseUsingFrequencyCode() {
        List<String> properties = new ArrayList<String>();
        properties.add("1");
        properties.add("2");
        properties.add("6");
        Collection<String> frequencyBases = awardTemplateReportTermService.getFrequencyBaseUsingFrequencyCode("2");
        assertEquals(properties.size(), frequencyBases.size());

        for(String aFrequencyBase : frequencyBases) {
            assertTrue(properties.contains(aFrequencyBase));
        }
    }
    
    @Test
    public void testGetFrequencyBaseForAjaxCall() {
        List<String> expected = Arrays.asList(",2;Project Start Date,1;Execution Date,6;As Required".split(","));
        Collections.sort(expected);

        List<String> resultFields = Arrays.asList(awardTemplateReportTermService.getFrequencyBaseForAjaxCall("2").split(","));
        Collections.sort(resultFields);

        assertEquals(expected,resultFields);
    }
}
