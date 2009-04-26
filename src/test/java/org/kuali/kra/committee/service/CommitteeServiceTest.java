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
package org.kuali.kra.committee.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.service.impl.CommitteeServiceImpl;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Test the methods in CommitteeServiceImpl.
 */
@RunWith(JMock.class)
public class CommitteeServiceTest {
    
    private static final String RESEARCH_AREA_CODE_1 = "01.0101";
    private static final String RESEARCH_AREA_CODE_2 = "01.0102";
    private static final String RESEARCH_AREA_CODE_3 = "01.0103";
    
    private Mockery context = new JUnit4Mockery();
    
    /**
     * Verify that the correct committee is returned if it is found.
     */
    @Test
    public void testGetCommitteeByIdFound() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        
        /*
         * The CommitteServiceImpl will use the Business Object Service
         * to query the database.  Since we "know" the internals to the
         * CommitteeServiceImpl, we know data to be sent to the Business
         * Object Service and what will be returned if the committee is
         * found.
         */
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "999");
        
        final Collection<Committee> committees = new ArrayList<Committee>();
        Committee committee = new Committee();
        committees.add(committee);
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues); will(returnValue(committees));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
        
        assertEquals(committee, committeeService.getCommitteeById("999"));
    }
    
    /**
     * Verify that null is returned if the committee is not found.
     */
    @Test
    public void testGetCommitteeByIdNotFound() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        
        /*
         * The CommitteServiceImpl will use the Business Object Service
         * to query the database.  Since we "know" the internals to the
         * CommitteeServiceImpl, we know data to be sent to the Business
         * Object Service and we know that an empty list of committees
         * is returned if the committee is not in the database.
         */
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "999");
        
        final Collection<Committee> committees = new ArrayList<Committee>();
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues); will(returnValue(committees));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
        
        assertEquals(null, committeeService.getCommitteeById("999"));
    }
    
    /**
     * Verify that we can add a valid research area to a committee.
     */
    @Test
    public void testAddResearchAreas() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        Committee committee = new Committee();
        
        List<ResearchArea> researchAreas = new ArrayList<ResearchArea>();
        ResearchArea researchArea1 = new ResearchArea();
        researchArea1.setResearchAreaCode(RESEARCH_AREA_CODE_1);
        researchAreas.add(researchArea1);
        
        ResearchArea researchArea2 = new ResearchArea();
        researchArea2.setResearchAreaCode(RESEARCH_AREA_CODE_2);
        researchAreas.add(researchArea2);
        
        committeeService.addResearchAreas(committee, researchAreas);
        List<CommitteeResearchArea> committeeResearchAreas = committee.getCommitteeResearchAreas();
        assertEquals(2, committeeResearchAreas.size());
        
        assertEquals(RESEARCH_AREA_CODE_1, committeeResearchAreas.get(0).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_2, committeeResearchAreas.get(1).getResearchAreaCode());
        
        // Try adding a new research along with two duplicates
        
        ResearchArea researchArea3 = new ResearchArea();
        researchArea3.setResearchAreaCode(RESEARCH_AREA_CODE_3);
        researchAreas.add(researchArea3);
        
        committeeService.addResearchAreas(committee, researchAreas);
        committeeResearchAreas = committee.getCommitteeResearchAreas();
        assertEquals(3, committeeResearchAreas.size());
        
        assertEquals(RESEARCH_AREA_CODE_1, committeeResearchAreas.get(0).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_2, committeeResearchAreas.get(1).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_3, committeeResearchAreas.get(2).getResearchAreaCode());
    }
}
